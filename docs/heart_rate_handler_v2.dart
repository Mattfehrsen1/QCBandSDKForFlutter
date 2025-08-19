import 'dart:async';
import 'dart:typed_data';
import 'package:flutter/foundation.dart';

// NOTE: Update these imports to match your app structure.
import '../path/to/metric_handler.dart';
import '../path/to/adapters/qc_ble_manager.dart';
import '../path/to/adapters/qc_band_protocol_utils.dart';
import '../path/to/db/penng_database.dart';
import '../path/to/config/sync_feature_flags.dart';
import '../path/to/config/sync_runtime_overrides.dart';
import '../path/to/services/sync_debug_summary.dart';
import '../path/to/providers/modular_sync_provider.dart';

/// Historical Heart Rate Handler v2
///
/// Fixes:
/// - Dedupe by packet index only (supports out-of-order arrival).
/// - Keep zero BPM values to preserve 5-minute alignment.
/// - Compute start-minute from packet index and learned counts.
/// - Use local day for future-sample filtering; store UTC to DB.
/// - Use UART write without response for throughput.
class HistoricalHeartRateHandlerV2 implements MetricHandler {
  final QcBleManager _bleManager;
  final PenngDatabase _database;

  bool _isInitialized = false;
  bool _sessionProcessed = false;

  final Set<int> _seenPacketIndices = {};
  final Map<String, int> _accumulatedData = {};

  int _firstPacketPoints = 9; // learned from pktIndex=1
  DateTime? _currentFinalizeTargetDate; // local day anchor

  String get _deviceId => _bleManager.connectedDeviceId ?? 'unknown-device';

  HistoricalHeartRateHandlerV2({
    required QcBleManager bleManager,
    required PenngDatabase database,
  })  : _bleManager = bleManager,
        _database = database;

  @override
  String get metricName => 'Historical Heart Rate (v2)';

  @override
  Future<bool> initialize() async {
    if (_isInitialized) return true;
    try {
      _log('🔄 Initializing HR v2 handler...');
      _clearAccumulatedData();
      _isInitialized = true;
      _log('✅ HR v2 initialized');
      return true;
    } catch (e) {
      _log('❌ HR v2 init failed: $e');
      return false;
    }
  }

  @override
  Future<bool> sync() async {
    if (!_isInitialized) await initialize();
    try {
      if (!_bleManager.isConnected) {
        _log('❌ HR v2 sync aborted - not connected');
        return false;
      }

      if (SyncFeatureFlags.useForwardedPackets && !SyncRuntimeOverrides.heartRateDirect) {
        _log('🌉 Bridge mode active: awaiting forwarded HR packets');
        return true;
      }

      return await syncForDate(DateTime.now());
    } catch (e) {
      _log('❌ HR v2 sync failed: $e');
      return false;
    }
  }

  Future<bool> syncForDate(DateTime targetDate) async {
    if (!_isInitialized) await initialize();
    try {
      if (!_bleManager.isConnected) {
        _log('❌ HR v2 sync aborted - not connected');
        return false;
      }

      _clearAccumulatedData();
      await _switchToUart();
      await _syncHeartRateForDate(targetDate);
      return true;
    } catch (e) {
      _log('❌ HR v2 syncForDate failed: $e');
      return false;
    }
  }

  Future<bool> syncForLastDays(int numberOfDays) async {
    if (!_isInitialized) await initialize();
    if (numberOfDays <= 0) return false;

    int ok = 0;
    for (int i = 0; i < numberOfDays; i++) {
      final day = DateTime.now().subtract(Duration(days: i));
      final done = await syncForDate(DateTime(day.year, day.month, day.day));
      if (done) ok++;
      if (i < numberOfDays - 1) {
        await Future.delayed(const Duration(milliseconds: 300));
      }
    }
    _log('🎉 HR v2 multi-day: $ok/$numberOfDays succeeded');
    return ok == numberOfDays;
  }

  Future<bool> syncForDateRange(DateTime startDate, DateTime endDate) async {
    if (!_isInitialized) await initialize();
    final start = DateTime(startDate.year, startDate.month, startDate.day);
    final endEx = DateTime(endDate.year, endDate.month, endDate.day);
    int requested = 0, succeeded = 0;
    for (DateTime d = start; d.isBefore(endEx); d = d.add(const Duration(days: 1))) {
      requested++;
      if (await syncForDate(d)) succeeded++;
      if (d.add(const Duration(days: 1)).isBefore(endEx)) {
        await Future.delayed(const Duration(milliseconds: 300));
      }
    }
    _log('✅ HR v2 range: $succeeded/$requested succeeded');
    return succeeded > 0;
  }

  Future<void> _syncHeartRateForDate(DateTime targetDate) async {
    final localMidnight = DateTime(targetDate.year, targetDate.month, targetDate.day);
    _currentFinalizeTargetDate = localMidnight; // set anchor before write (fallback if pkt1 absent)

    final tsUtcSeconds = localMidnight.toUtc().millisecondsSinceEpoch ~/ 1000;
    _log('📅 HR v2 requesting ${localMidnight.toIso8601String()} (utcSeconds=$tsUtcSeconds)');

    final command = _buildGetHRDataCommand(tsUtcSeconds);
    _log('📡 write HR cmd len=${command.length}');

    // Write without response for throughput
    await _bleManager.write(command, withoutResponse: true);

    await _waitForHeartRateCompletion();
  }

  Uint8List _buildGetHRDataCommand(int utcSecondsAtLocalMidnight) {
    final cmd = <int>[21];
    cmd.add((utcSecondsAtLocalMidnight >> 0) & 0xFF);
    cmd.add((utcSecondsAtLocalMidnight >> 8) & 0xFF);
    cmd.add((utcSecondsAtLocalMidnight >> 16) & 0xFF);
    cmd.add((utcSecondsAtLocalMidnight >> 24) & 0xFF);
    while (cmd.length < 16) cmd.add(0);
    return Uint8List.fromList(cmd);
  }

  Future<void> _waitForHeartRateCompletion() async {
    const int maxWaitSeconds = 20;
    const int checkMs = 500;
    int lastSize = -1;
    int stableMs = 0;

    for (int elapsed = 0; elapsed < maxWaitSeconds * 1000; elapsed += checkMs) {
      await Future.delayed(const Duration(milliseconds: checkMs));
      final sizeNow = _accumulatedData.length;
      if (sizeNow == lastSize) {
        stableMs += checkMs;
        if (stableMs >= 4000) {
          _log('📊 HR v2 collected ${_accumulatedData.length} readings (stable)');
          await _finalizeHeartRateSession();
          final now = DateTime.now();
          final dk = '${now.year}-${now.month.toString().padLeft(2, '0')}-${now.day.toString().padLeft(2, '0')}';
          SyncDebugSummary.instance.markCompletion('hr', dateKey: dk);
          return;
        }
      } else {
        stableMs = 0;
        lastSize = sizeNow;
      }
    }

    _log('⏰ HR v2 timeout; finalizing ${_accumulatedData.length} readings');
    await _finalizeHeartRateSession();
    final now = DateTime.now();
    final dk = '${now.year}-${now.month.toString().padLeft(2, '0')}-${now.day.toString().padLeft(2, '0')}';
    SyncDebugSummary.instance.markTimeout('hr', dateKey: dk);
  }

  Future<void> _finalizeHeartRateSession() async {
    if (_accumulatedData.isEmpty) {
      _log('⚠️ No HR data to store');
      return;
    }
    final targetLocal = _currentFinalizeTargetDate ?? DateTime.now();
    int valid = 0, total = 0;

    for (final e in _accumulatedData.entries) {
      final timeKey = e.key; // HH:MM
      final bpm = e.value;
      final realLocal = _parseDeviceTimeKey(timeKey, targetLocal);
      if (realLocal == null) continue;

      // Local-day guard for future samples (today only)
      final nowLocal = DateTime.now();
      final sameDay = realLocal.year == nowLocal.year &&
          realLocal.month == nowLocal.month &&
          realLocal.day == nowLocal.day;
      if (sameDay && realLocal.isAfter(nowLocal)) {
        _log('↪️ Skip future sample ${realLocal.toIso8601String()}');
        continue;
      }

      if (bpm > 0 && bpm <= 220) {
        final tsUtc = realLocal.toUtc();
        final exists = await _database.heartRateExistsAt(tsUtc, _deviceId);
        if (!exists) {
          await _database.insertHeartRate(HeartRateDataCompanion.insert(
            timestamp: tsUtc,
            bpm: bpm,
            deviceId: _deviceId,
          ));
        }
        valid++;
        total += bpm;
      }
    }

    if (valid > 0) {
      final avg = (total / valid).round();
      _log('✅ Stored HR: $valid samples, avg=$avg BPM');
    } else {
      _log('❌ No valid HR samples stored');
    }

    _sessionProcessed = true;
    _clearAccumulatedData();
  }

  DateTime? _parseDeviceTimeKey(String timeKey, DateTime targetLocalDay) {
    try {
      final p = timeKey.split(':');
      if (p.length != 2) return null;
      final h = int.parse(p[0]);
      final m = int.parse(p[1]);
      return DateTime(targetLocalDay.year, targetLocalDay.month, targetLocalDay.day)
          .add(Duration(hours: h, minutes: m));
    } catch (_) {
      return null;
    }
  }

  Future<void> _switchToUart() async {
    await _bleManager.discoverServices(
      writeUuid: '6e400002-b5a3-f393-e0a9-e50e24dcca9e',
      notifyUuid: '6e400003-b5a3-f393-e0a9-e50e24dcca9e',
    );
    try {
      await _bleManager.enableNotifications((value) {
        ModularSyncGlobal.handlePacket(value);
      });
      _log('🔔 UART RX enabled');
    } catch (e) {
      _log('⚠️ Unable to enable UART RX: $e');
    }
  }

  @override
  bool canHandle(List<int> data) {
    return QcBandProtocolUtils.isHeartRatePacket(data);
  }

  @override
  Future<bool> parsePacket(List<int> data) async {
    try {
      final now = DateTime.now();
      final dk = '${now.year}-${now.month.toString().padLeft(2, '0')}-${now.day.toString().padLeft(2, '0')}';
      if (SyncFeatureFlags.useForwardedPackets && !SyncRuntimeOverrides.heartRateDirect) {
        SyncDebugSummary.instance.incrementForwarded('hr', dateKey: dk);
      } else {
        SyncDebugSummary.instance.incrementDirect('hr', dateKey: dk);
      }

      if (data.length < 2) return false;
      final hex = QcBandProtocolUtils.formatHexCompact(data);

      if (data[0] == 21) {
        await _handleNewFormatPacket(data, hex);
      } else {
        await _handleLegacyFormatPacket(data, hex);
      }

      _log('📦 pkts=${_seenPacketIndices.length}, points=${_accumulatedData.length}');
      return true;
    } catch (e) {
      _log('❌ parsePacket failed: $e');
      return false;
    }
  }

  Future<void> _handleNewFormatPacket(List<int> data, String hex) async {
    final pktIndex = (data.length >= 2) ? (data[1] & 0xFF) : -1;
    if (pktIndex < 0) {
      _log('❌ HR pkt missing index: $hex');
      return;
    }
    if (pktIndex == 0) {
      _log('📋 HR header');
      return;
    }
    if (_seenPacketIndices.contains(pktIndex)) {
      _log('↪️ Skip duplicate pkt $pktIndex');
      return;
    }

    int startIdx;
    if (pktIndex == 1) {
      if (data.length >= 6) {
        final ts = (data[2] & 0xFF) |
            ((data[3] & 0xFF) << 8) |
            ((data[4] & 0xFF) << 16) |
            ((data[5] & 0xFF) << 24);
        final baseLocal = DateTime.fromMillisecondsSinceEpoch(ts * 1000, isUtc: true).toLocal();
        _currentFinalizeTargetDate = DateTime(baseLocal.year, baseLocal.month, baseLocal.day);
        _log('🕐 Anchor from pkt1 utcTime → ${_currentFinalizeTargetDate!.toIso8601String()}');
      }
      startIdx = 6;
    } else {
      startIdx = 2;
    }

    final available = data.length - startIdx;
    if (available <= 0) {
      _log('❌ HR pkt $pktIndex payload empty');
      return;
    }

    final points = available.clamp(0, 13);
    if (pktIndex == 1) _firstPacketPoints = points;

    final pointsBefore = (pktIndex == 1) ? 0 : _firstPacketPoints + (pktIndex - 2).clamp(0, 255) * 13;
    final startMinute = pointsBefore * 5;
    _log('📈 pkt=$pktIndex startMin=$startMinute points=$points');

    for (int i = 0; i < points; i++) {
      final v = data[startIdx + i] & 0xFF;
      final totalMin = startMinute + i * 5;
      final h = (totalMin ~/ 60) % 24;
      final m = totalMin % 60;
      final key = '${h.toString().padLeft(2, '0')}:${m.toString().padLeft(2, '0')}';
      _accumulatedData[key] = (v <= 220 ? v : 0); // keep zeros
      if (v > 0) _log('💓 $key → $v');
    }

    _seenPacketIndices.add(pktIndex);
  }

  Future<void> _handleLegacyFormatPacket(List<int> data, String hex) async {
    _log('💓 Legacy HR pkt: $hex');
    if (data.length <= 2) return;

    final values = <int>[];
    for (int i = 2; i < data.length; i++) {
      final v = data[i] & 0xFF;
      if (v >= 0 && v <= 220) values.add(v); // include zeros
    }
    if (values.isEmpty) return;

    // Approximate index-based placement if index present
    final pktIndex = (data.length >= 2) ? (data[1] & 0xFF) : 0;
    final startMinute = pktIndex > 0 ? (pktIndex - 1) * 13 * 5 : (_accumulatedData.length * 5);

    for (int i = 0; i < values.length; i++) {
      final totalMin = startMinute + i * 5;
      final h = (totalMin ~/ 60) % 24;
      final m = totalMin % 60;
      final key = '${h.toString().padLeft(2, '0')}:${m.toString().padLeft(2, '0')}';
      final v = values[i];
      _accumulatedData[key] = v;
      if (v > 0) _log('📍 $key → $v');
    }

    if (pktIndex > 0) _seenPacketIndices.add(pktIndex);
  }

  void _clearAccumulatedData() {
    _accumulatedData.clear();
    _sessionProcessed = false;
    _currentFinalizeTargetDate = null;
    _seenPacketIndices.clear();
    _firstPacketPoints = 9;
  }

  @override
  Future<void> dispose() async {
    _isInitialized = false;
    _clearAccumulatedData();
    _log('🧹 HR v2 disposed');
  }

  void _log(String msg) => debugPrint('[HistoricalHeartRateHandlerV2] $msg');
}


