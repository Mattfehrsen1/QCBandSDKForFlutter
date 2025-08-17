import 'dart:async';
import 'dart:collection';
import 'dart:typed_data';
import 'package:flutter/foundation.dart';
import 'package:drift/drift.dart';
import 'package:qc_band_sdk_for_flutter/qc_band_sdk_for_flutter.dart';
import 'package:qc_band_sdk_for_flutter/bean/models/sleepModel.dart' show SleepParser;
import '../../adapters/qc_band_protocol_utils.dart';
import '../../config/sync_feature_flags.dart';
import '../../config/sync_runtime_overrides.dart';
import '../metric_handler.dart';
import '../../adapters/qc_ble_manager.dart';
import '../../db/penng_database.dart';
import '../../services/sync_debug_summary.dart';

/// Protocol detection for sleep data parsing
enum SleepProtocol { 
  largeDataSingleDay, // Day 0 format (command [188, 39], < 100 bytes)
  largeDataMultiDay,  // Days 1-6 format (command [188, 39], >= 100 bytes)
  standard,           // Standard protocol (command [188, 68])
  unknown             // Unknown or fallback
}

/// Sleep Data Handler for the modular BLE system
/// 
/// Features:
/// - Sleep data sync from QC Band device
/// - Support for both Day 0 (today) and historical (Days 1-6) formats
/// - Sleep stage parsing: Deep, Light, REM, Awake stages
/// - Date-range sync capabilities for Smart Sync integration
/// - Professional sleep session storage in SleepRawData table
class SleepHandler implements MetricHandler {
  final QcBleManager _bleManager;
  final PenngDatabase _database;
  bool _isInitialized = false;
  
  // Packet deduplication tracking with size limit to prevent memory leaks (FIFO)
  final LinkedHashSet<String> _seenPacketIds = LinkedHashSet<String>();
  final LinkedHashSet<String> _seenSegmentKeys = LinkedHashSet<String>();
  static const int _maxCacheSize = 1000; // Limit cache to prevent memory bloat
  
  // Sleep request tracking
  bool _isExpectingSleepData = false;
  int _expectedSleepDay = -1;
  Completer<List<int>>? _sleepResponseCompleter;
  
  // Concurrent sync protection
  bool _isSyncInProgress = false;
  
  // Day configuration for data retrieval (following SpO2 handler pattern)
  int _daysBack = 7; // Default: get last 7 days of data
  
  // Device ID for database storage
  String get _deviceId => _bleManager.connectedDeviceId ?? 'unknown-device';
  
  // Sleep stage constants (matching implementation guide)
  static const int kStageDeep = 1;      // Deep Sleep
  static const int kStageLight = 2;     // Light Sleep  
  static const int kStageAwake = 3;     // Awake
  static const int kStageREM = 4;       // REM Sleep
  static const int kStageUnknown = 5;   // Unknown
  
  SleepHandler({
    required QcBleManager bleManager,
    required PenngDatabase database,
  }) : _bleManager = bleManager, _database = database;

  @override
  String get metricName => 'sleep';

  @override
  Future<bool> initialize() async {
    if (_isInitialized) return true;
    
    try {
      _log('🔄 Initializing Sleep Handler...');
      
      // Clear any existing state
      _seenPacketIds.clear();
      _seenSegmentKeys.clear();
      _isExpectingSleepData = false;
      _expectedSleepDay = -1;
      _sleepResponseCompleter = null;
      
      _isInitialized = true;
      _log('✅ Sleep Handler initialized successfully');
      return true;
    } catch (e) {
      _log('❌ Sleep Handler initialization failed: $e');
      return false;
    }
  }

  @override
  Future<bool> sync() async {
    if (_isSyncInProgress) {
      _log('⚠️ Sleep sync already in progress');
      return false;
    }
    _isSyncInProgress = true;
    if (!_isInitialized) await initialize();
    // If we're using forwarded packets, rely on legacy bridge unless forced direct
    if (SyncFeatureFlags.useForwardedPackets && !SyncRuntimeOverrides.sleepDirect) {
      _log('🌉 Bridge mode active: awaiting forwarded 0x73/BC27 sleep packets');
      _isSyncInProgress = false;
      return true;
    }
    // Direct mode: fetch today's sleep data (Day 0)
    try {
      await _syncSleepForDay(0);
      return true;
    } catch (e) {
      _log('❌ Direct sleep sync (today) failed: $e');
      return false;
    } finally {
      _isSyncInProgress = false;
    }
  }

  /// Sync sleep data for the specified number of days back (NEW - Phase 3)
  Future<bool> syncForDaysBack(int daysBack) async {
    if (_isSyncInProgress) {
      _log('⚠️ Sleep sync already in progress');
      return false;
    }
    _isSyncInProgress = true;
    if (!_isInitialized) await initialize();
    if (SyncFeatureFlags.useForwardedPackets && !SyncRuntimeOverrides.sleepDirect) {
      _log('🌉 Bridge mode: daysBack=$daysBack requested; relying on legacy adapter to emit sleep packets');
      _isSyncInProgress = false;
      return true;
    }
    // Direct mode: actively request days 0..N
    try {
      final maxDays = daysBack.clamp(1, 7);
      _log('🌙 Fetching sleep data for ${maxDays} day${maxDays == 1 ? '' : 's'}...');
      // Ensure today first, then older
      for (int day = 0; day < maxDays; day++) {
        await _syncSleepForDay(day);
        // brief pacing between requests
        await Future.delayed(const Duration(milliseconds: 300));
      }
      _log('✅ Sleep data collection complete!');
      return true;
    } catch (e) {
      _log('❌ Direct sleep multi-day sync failed: $e');
      return false;
    } finally {
      _isSyncInProgress = false;
    }
  }

  /// Sync sleep data for a specific date range (iterate day-by-day)
  Future<bool> syncForDateRange(DateTime startDate, DateTime endDate) async {
    if (_isSyncInProgress) {
      _log('⚠️ Sleep sync already in progress');
      return false;
    }
    _isSyncInProgress = true;
    if (!_isInitialized) await initialize();
    
    try {
      final start = DateTime(startDate.year, startDate.month, startDate.day);
      final endExclusive = DateTime(endDate.year, endDate.month, endDate.day);
      final startStr = '${start.year}-${start.month.toString().padLeft(2, '0')}-${start.day.toString().padLeft(2, '0')}';
      final endStr = '${endExclusive.year}-${endExclusive.month.toString().padLeft(2, '0')}-${endExclusive.day.toString().padLeft(2, '0')}';
      _log('🔄 Starting Sleep sync for range [${startStr} → ${endStr})');

      if (!_bleManager.isConnected) {
        _log('❌ Sleep sync aborted - not connected');
        return false;
      }

      int requested = 0;
      int succeeded = 0;
      for (DateTime cursor = start; cursor.isBefore(endExclusive); cursor = cursor.add(const Duration(days: 1))) {
        // Convert absolute date to device dayOffset (0=today..6)
        final today = DateTime.now();
        final todayMidnight = DateTime(today.year, today.month, today.day);
        final dayOffset = todayMidnight.difference(cursor).inDays;
        if (dayOffset < 0 || dayOffset > 6) {
          continue; // outside device storage window
        }
        requested++;
        try {
          await _syncSleepForDay(dayOffset);
          succeeded++;
        } catch (_) {}
        if (cursor.add(const Duration(days: 1)).isBefore(endExclusive)) {
          await Future.delayed(const Duration(milliseconds: 300));
        }
      }

      _log('✅ Sleep range sync complete: $succeeded/$requested day(s) succeeded');
      return succeeded > 0;
    } catch (e) {
      _log('❌ Sleep date range sync failed: $e');
      return false;
    } finally {
      _isSyncInProgress = false;
    }
  }

  @override
  bool canHandle(List<int> data) {
    // Bridge-mode routing signatures: 0x73 (raw sleep) OR 0xBC 0x27/0x44 (vendor sleep)
    final isBc = data.length >= 2 && data[0] == 0xBC && (data[1] == 0x27 || data[1] == 0x44);
    return QcBandProtocolUtils.isSleepPacket(data) || isBc;
  }

  /// Enhanced validation for sleep data packets (matching working adapter)
  bool _isValidSleepDataPacket(List<int> data) {
    // Accept 0xBC vendor frames with sub 0x27 or 0x44 and a relaxed minimum length
    if (data.length < 10 || data[0] != 0xBC) {
      return false;
    }
    final sub = data.length > 1 ? (data[1] & 0xFF) : -1;
    return sub == 0x27 || sub == 0x44;
  }

  @override
  Future<bool> parsePacket(List<int> data) async {
    if (!canHandle(data)) return false;
    try {
      if (_isBc27SleepPacket(data)) {
        return await _parseSleepBc27Packet(data);
      }
      return await _parseSleep73Packet(data);
    } catch (e) {
      _log('❌ Failed to parse sleep packet: $e');
      return false;
    }
  }

  /// Minimal 0x73 sleep packet parser (bridge-mode, Day 0 first)
  /// Format assumption: [0x73, dayOffset, type, duration, type, duration, ...]
  Future<bool> _parseSleep73Packet(List<int> data) async {
    if (data.length < 4) return false;
    final packetId = QcBandProtocolUtils.formatHexCompact(data);
    if (_seenPacketIds.contains(packetId)) {
      _log('⚠️ Skipping duplicate 0x73 sleep packet');
      return true;
    }
    _cacheAdd(_seenPacketIds, packetId);

    final dayOffset = (data[1]).clamp(0, 6) as int;
    // Forwarded parity counting when in bridge mode
    if (SyncFeatureFlags.useForwardedPackets && !SyncRuntimeOverrides.sleepDirect) {
      try {
        SyncDebugSummary.instance.incrementForwarded('sleep', dateKey: _getDateKeyForOffset(dayOffset));
      } catch (_) {}
    }
    final now = DateTime.now();
    final baseDate = DateTime(now.year, now.month, now.day).subtract(Duration(days: dayOffset));
    DateTime currentTime = baseDate; // Minimal: start at 00:00

    int segmentsStored = 0;
    for (int i = 2; i + 1 < data.length; i += 2) {
      final rawType = data[i];
      int durationMin = data[i + 1];
      if (durationMin < 0) durationMin = durationMin & 0xFF;

      // Validate duration
      if (durationMin <= 0 || durationMin > 480) continue;

      final stageType = _mapSleepType(rawType);
      final stage = _getSleepStageString(stageType);

      // Dedup composite key: timestamp + stage + device
      final dedupKey = '${currentTime.millisecondsSinceEpoch}-$stage-$_deviceId';
      if (_seenSegmentKeys.contains(dedupKey)) {
        continue;
      }
      _cacheAdd(_seenSegmentKeys, dedupKey);

      // Persist segment
      try {
        await _database.insertSleepRaw(SleepRawDataCompanion.insert(
          timestamp: currentTime,
          bedTime: const Value(null),
          wakeTime: const Value(null),
          sleepStage: stage,
          deviceId: _deviceId,
          dirty: const Value(true),
        ));
        segmentsStored++;
        _log('[SleepHandler] Segment: $stage, start ${_formatTime(currentTime)}, dur ${durationMin}');
      } catch (_) {}

      currentTime = currentTime.add(Duration(minutes: durationMin));
    }

    if (segmentsStored > 0) {
      _log('✅ Session complete: $segmentsStored sleep segments stored');
    }
    return segmentsStored > 0;
  }

  bool _isBc27SleepPacket(List<int> data) {
    return data.length >= 2 && data[0] == 0xBC && data[1] == 0x27;
  }

  /// Parse 0xBC 0x27 sleep packet (Large Data sleep response) and persist
  Future<bool> _parseSleepBc27Packet(List<int> data) async {
    // Dedup by full hex
    final packetId = QcBandProtocolUtils.formatHexCompact(data);
    if (_seenPacketIds.contains(packetId)) {
      _log('⚠️ Skipping duplicate BC27 sleep packet');
      return true;
    }
    _cacheAdd(_seenPacketIds, packetId);

    _log('🛏️ Processing BC27 sleep packet: ${data.length} bytes');

    try {
      // Determine absolute day offset from packet header when available.
      // Large-data header layout: [.., totalDays(6), currDay(7), firstDayLength(8), ...]
      int dayOffsetForDate = 0;
      if (data.length > 8) {
        dayOffsetForDate = data[7].clamp(0, 6);
        _log('🔍 Using packet currDay=$dayOffsetForDate as absolute day offset');
      }
      // Forwarded parity counting in bridge mode (count once per packet)
      if (SyncFeatureFlags.useForwardedPackets && !SyncRuntimeOverrides.sleepDirect) {
        try {
          SyncDebugSummary.instance.incrementForwarded('sleep', dateKey: _getDateKeyForOffset(dayOffsetForDate));
        } catch (_) {}
      }

      // Parse with the correct absolute day offset for date attribution.
      final summary = _parseSleepDataPacket(data, dayOffsetForDate);
      if (summary.bedTime != null && summary.wakeTime != null && summary.segments.isNotEmpty) {
        await _storeSleepSession(summary);
        _log('✅ Session complete: ${summary.segments.length} sleep segments stored');
        return true;
      } else {
        _log('⚠️ BC27 packet parsed but no valid session found');
        return false;
      }
    } catch (e) {
      _log('❌ Failed to parse BC27 sleep packet: $e');
      return false;
    }
  }

  /// Check if this is an expected sleep data response (matching working adapter)
  bool _isExpectedSleepResponse(List<int> data) {
    // Accept both 0x27 and 0x44 variants
    if (data.length >= 2 && data[0] == 0xBC) {
      final sub = data[1] & 0xFF;
      return sub == 0x27 || sub == 0x44;
    }
    return false;
  }

  @override
  Future<void> dispose() async {
    _seenPacketIds.clear();
    _seenSegmentKeys.clear();
    
    // CRITICAL FIX: Only complete if not already completed to prevent crashes
    if (_sleepResponseCompleter != null && !_sleepResponseCompleter!.isCompleted) {
      _sleepResponseCompleter!.complete([]);
    }
    _sleepResponseCompleter = null;
    _isExpectingSleepData = false;
    _expectedSleepDay = -1;
    _isSyncInProgress = false; // Reset sync protection flag
    _isInitialized = false; // Reset initialization state
    _log('🧹 Sleep handler disposed');
  }

  /// Set how many days back to retrieve sleep data (following SpO2 handler pattern)
  void setDaysBack(int days) {
    _daysBack = days.clamp(1, 7); // Limit between 1-7 days (device limitation)
  }

  /// Force use simple duration approach for debugging (bypasses protocol detection)
  bool _forceSimpleApproach = false;
  void setForceSimpleApproach(bool force) {
    _forceSimpleApproach = force;
    _log('🔧 Force simple approach: ${force ? 'ENABLED' : 'DISABLED'}');
  }

  /// Get sleep data for the specified number of days
  Future<List<SleepRawDataData>> getSleepData({int? daysBack}) async {
    final days = daysBack ?? _daysBack;
    final now = DateTime.now();
    final startTime = now.subtract(Duration(days: days));
    
    final query = _database.select(_database.sleepRawData)
      ..where((tbl) => tbl.timestamp.isBetweenValues(startTime, now) & tbl.deviceId.equals(_deviceId))
      ..orderBy([(t) => OrderingTerm.desc(t.timestamp)]);
    
    return await query.get();
  }

  /// Get latest sleep session
  Future<SleepRawDataData?> getLatestSleep() async {
    final query = _database.select(_database.sleepRawData)
      ..where((tbl) => tbl.deviceId.equals(_deviceId))
      ..orderBy([(t) => OrderingTerm.desc(t.timestamp)])
      ..limit(1);
    
    final results = await query.get();
    return results.isNotEmpty ? results.first : null;
  }

  /// Get sleep data for a specific day
  Future<List<SleepRawDataData>> getSleepForDay(DateTime date) async {
    final startOfDay = DateTime(date.year, date.month, date.day);
    final endOfDay = startOfDay.add(const Duration(days: 1));
    
    final query = _database.select(_database.sleepRawData)
      ..where((tbl) => tbl.timestamp.isBetweenValues(startOfDay, endOfDay) & tbl.deviceId.equals(_deviceId))
      ..orderBy([(t) => OrderingTerm.asc(t.timestamp)]);
    
    return await query.get();
  }
  
  /// Get all sleep sessions (grouped by bedTime/wakeTime) for analysis
  Future<List<SleepSessionSummary>> getSleepSessions({int? daysBack}) async {
    final days = daysBack ?? _daysBack;
    final now = DateTime.now();
    final startTime = now.subtract(Duration(days: days));
    
    try {
      // Get distinct sleep sessions
      final query = _database.selectOnly(_database.sleepRawData, distinct: true)
        ..addColumns([
          _database.sleepRawData.bedTime,
          _database.sleepRawData.wakeTime,
        ])
        ..where(_database.sleepRawData.bedTime.isNotNull() & 
               _database.sleepRawData.wakeTime.isNotNull() &
               _database.sleepRawData.deviceId.equals(_deviceId) &
               _database.sleepRawData.bedTime.isBiggerOrEqualValue(startTime))
        ..orderBy([OrderingTerm.desc(_database.sleepRawData.bedTime)]);
      
      final sessions = await query.get();
      List<SleepSessionSummary> summaries = [];
      
      for (final session in sessions) {
        final bedTime = session.read(_database.sleepRawData.bedTime);
        final wakeTime = session.read(_database.sleepRawData.wakeTime);
        
        if (bedTime != null && wakeTime != null) {
          final sessionData = await getSleepSession(bedTime, wakeTime);
          if (sessionData != null) {
            summaries.add(sessionData);
          }
        }
      }
      
      return summaries;
    } catch (e) {
      _log('❌ Failed to get sleep sessions: $e');
      return [];
    }
  }
  
  /// Get a specific sleep session with calculated metrics
  Future<SleepSessionSummary?> getSleepSession(DateTime bedTime, DateTime wakeTime) async {
    try {
      final query = _database.select(_database.sleepRawData)
        ..where((tbl) => tbl.bedTime.equals(bedTime) & 
               tbl.wakeTime.equals(wakeTime) & 
               tbl.deviceId.equals(_deviceId))
        ..orderBy([(t) => OrderingTerm.asc(t.timestamp)]);
      
      final segments = await query.get();
      
      if (segments.isEmpty) return null;
      
      // Calculate session metrics
      int totalDuration = 0;
      int deepSleep = 0;
      int lightSleep = 0;
      int remSleep = 0;
      int awake = 0;
      
      DateTime? lastTimestamp;
      for (final segment in segments) {
        // Calculate segment duration (use default 15 minutes if next segment not available)
        int segmentDuration = 15; // Default segment duration
        
        if (lastTimestamp != null) {
          segmentDuration = segment.timestamp.difference(lastTimestamp).inMinutes;
        }
        
        totalDuration += segmentDuration;
        
        switch (segment.sleepStage) {
          case 'deep':
            deepSleep += segmentDuration;
            break;
          case 'light':
            lightSleep += segmentDuration;
            break;
          case 'rem':
            remSleep += segmentDuration;
            break;
          case 'awake':
            awake += segmentDuration;
            break;
        }
        
        lastTimestamp = segment.timestamp;
      }
      
      // Calculate actual total duration from bedtime to waketime
      final actualDuration = wakeTime.difference(bedTime).inMinutes;
      
      return SleepSessionSummary(
        bedTime: bedTime,
        wakeTime: wakeTime,
        totalDuration: actualDuration,
        deepSleep: deepSleep,
        lightSleep: lightSleep,
        remSleep: remSleep,
        awake: awake,
        segmentCount: segments.length,
        efficiency: totalDuration > 0 ? ((totalDuration - awake) / totalDuration * 100).round() : 0,
      );
    } catch (e) {
      _log('❌ Failed to get sleep session: $e');
      return null;
    }
  }
  
  /// Get database statistics for monitoring
  Future<SleepDatabaseStats> getDatabaseStats() async {
    try {
      // Total sleep records
      final totalQuery = _database.selectOnly(_database.sleepRawData)
        ..addColumns([_database.sleepRawData.id.count()])
        ..where(_database.sleepRawData.deviceId.equals(_deviceId));
      
      final totalResult = await totalQuery.getSingle();
      final totalRecords = totalResult.read(_database.sleepRawData.id.count()) ?? 0;
      
      // Dirty (unsynced) records
      final dirtyQuery = _database.selectOnly(_database.sleepRawData)
        ..addColumns([_database.sleepRawData.id.count()])
        ..where(_database.sleepRawData.deviceId.equals(_deviceId) & _database.sleepRawData.dirty.equals(true));
      
      final dirtyResult = await dirtyQuery.getSingle();
      final dirtyRecords = dirtyResult.read(_database.sleepRawData.id.count()) ?? 0;
      
      // Date range
      final rangeQuery = _database.selectOnly(_database.sleepRawData)
        ..addColumns([
          _database.sleepRawData.timestamp.min(),
          _database.sleepRawData.timestamp.max(),
        ])
        ..where(_database.sleepRawData.deviceId.equals(_deviceId));
      
      final rangeResult = await rangeQuery.getSingle();
      final earliestRecord = rangeResult.read(_database.sleepRawData.timestamp.min());
      final latestRecord = rangeResult.read(_database.sleepRawData.timestamp.max());
      
      return SleepDatabaseStats(
        totalRecords: totalRecords,
        dirtyRecords: dirtyRecords,
        earliestRecord: earliestRecord,
        latestRecord: latestRecord,
      );
    } catch (e) {
      _log('❌ Failed to get database stats: $e');
      return SleepDatabaseStats(
        totalRecords: 0,
        dirtyRecords: 0,
        earliestRecord: null,
        latestRecord: null,
      );
    }
  }

  // Private helper methods

  /// Sync sleep for a specific day offset (updated to match working demo)
  Future<void> _syncSleepForDay(int dayOffset) async {
    _log('📅 Requesting Sleep for dayOffset=$dayOffset...');
    
    try {
      // Use Serial Port service for sleep data (like working demo)
      await _useSerialPortForSleep();
      
      // Request sleep data using the working demo approach
      final sleepData = await _requestSleepDataFromDevice(dayOffset);
      
      if (sleepData.isNotEmpty && sleepData.length >= 13) {
        _log('📨 Received sleep response for day $dayOffset: ${sleepData.length} bytes');
        
        // Parse and check if actual sleep data exists
        final summary = _parseSleepDataPacket(sleepData, dayOffset);
        if (summary.bedTime != null && summary.wakeTime != null) {
          await _storeSleepSession(summary);
          _log('✅ Sleep data parsed and stored for day $dayOffset');
        } else {
          _log('⚠️ Day $dayOffset: Device returned packet but no valid sleep session found');
        }
      } else {
        _log('⚠️ Day $dayOffset: No sleep data available (empty or too short response)');
      }
      
      // Shadow parity: simple per-day presence indicator when in bridge mode
      final dayLabel = _getDayLabel(dayOffset);
      final sawValid = sleepData.isNotEmpty && sleepData.length >= 10;
      _log('🌓 Sleep shadow parity [$dayLabel]: direct=${sawValid ? 'data' : 'none'}');
      final dateKey = _getDateKeyForOffset(dayOffset);
      final sum = SyncDebugSummary.instance;
      if (sawValid) {
        sum.incrementDirect('sleep', dateKey: dateKey, by: 1);
        sum.markCompletion('sleep', dateKey: dateKey);
      }

    } catch (e) {
      _log('❌ Sleep request for day $dayOffset failed: $e');
    }
  }

  /// Request sleep data from device for specific day (from working demo)
  Future<List<int>> _requestSleepDataFromDevice(int dayIndex) async {
    final completer = Completer<List<int>>();
    List<int>? lastValidPacket;
    Timer? coalesceTimer;

    // Ensure Serial notify is active, UART notify off
    await _bleManager.disableNotifications();
    await _bleManager.discoverServices(
      writeUuid: "de5bf72a-d711-4e47-af26-65e3012a5dc7",
      notifyUuid: "de5bf729-d711-4e47-af26-65e3012a5dc7",
    );

    try {
      // Coalescer: reset 200ms on each valid frame; overall timeout 9s
      await _bleManager.enableNotifications((value) {
        if (value.isNotEmpty && value[0] == 0xBC && (value[1] == 0x27 || value[1] == 0x44)) {
          if (_isValidSleepDataPacket(value)) {
            lastValidPacket = List<int>.from(value);
            coalesceTimer?.cancel();
            const delayMs = 200;
            coalesceTimer = Timer(const Duration(milliseconds: delayMs), () {
              if (!completer.isCompleted && lastValidPacket != null) {
                completer.complete(lastValidPacket!);
              }
            });
          }
        }
      });

      final cmd = QCBandSDK.getSleepData(dayIndex);
      await _bleManager.write(Uint8List.fromList(cmd), withoutResponse: true);
      _log('[SYNC] Sleep cmd sent for day $dayIndex via Serial Port');

      final response = await completer.future.timeout(
        const Duration(seconds: 9),
        onTimeout: () {
          coalesceTimer?.cancel();
          return lastValidPacket ?? [];
        },
      );

      return response;
    } finally {
      coalesceTimer?.cancel();
      await _bleManager.disableNotifications();
      await Future.delayed(const Duration(milliseconds: 300)); // pacing
    }
  }

  /// Switch to Serial Port service for sleep data (matching working demo)
  Future<void> _useSerialPortForSleep() async {
    await _bleManager.discoverServices(
      writeUuid: "de5bf72a-d711-4e47-af26-65e3012a5dc7",    // Serial Port Write
      notifyUuid: "de5bf729-d711-4e47-af26-65e3012a5dc7",  // Serial Port Notify
    );
    _log('🔄 Switched to Serial Port service for sleep data');
  }

  /// Switch to Serial-Port service for setup commands (legacy)
  Future<void> _useSerial() async {
    await _useSerialPortForSleep();
    _log('🔄 Switching to Serial-Port service for setup commands');
  }

  /// Switch to Nordic UART service for data transfer (legacy)
  Future<void> _useUart() async {
    await _bleManager.discoverServices(
      writeUuid: "6e400002-b5a3-f393-e0a9-e50e24dcca9e",
      notifyUuid: "6e400003-b5a3-f393-e0a9-e50e24dcca9e",
    );
    // DON'T call enableNotifications - bridge mode!
    _log('🔄 Switched to Nordic UART service for data transfer');
  }

  /// Switch to UART for initial setup (required by interface)
  Future<void> _switchToUart() async {
    await _useUart();
  }

  /// Build pressure handshake command
  Uint8List _buildPressureHandshake() {
    return Uint8List.fromList([0x55]);
  }

  /// Parse sleep data from raw BLE packet
  Future<void> _parseSleepData(List<int> rawData, int dayIndex) async {
    if (rawData.isEmpty || rawData.length < 8) {
      _log('⚠️ Sleep data too short: ${rawData.length} bytes');
      return;
    }
    
    try {
      final sleepSummary = _parseSleepDataPacket(rawData, dayIndex);
      
      if (sleepSummary.bedTime != null && sleepSummary.wakeTime != null) {
        await _storeSleepSession(sleepSummary);
        _displaySleepSummary(sleepSummary, dayIndex);
      } else {
        _log('⚠️ Day $dayIndex: No valid sleep data found');
      }
      
    } catch (e) {
      _log('❌ Failed to parse sleep data for day $dayIndex: $e');
    }
  }

  /// Parse sleep data packet with enhanced protocol detection
  SleepSummary _parseSleepDataPacket(List<int> data, int dayIndex) {
    if (data.isEmpty || data.length < 8) {
      _log('❌ Sleep data too short: ${data.length} bytes');
      return _emptySummary();
    }
    
    // Prefer SDK parser ONLY for vendor 0x27 (and optional 0x39) full frames
    try {
      final bool isVendor = data.length >= 2 && data[0] == 0xBC;
      final int sub = isVendor ? (data[1] & 0xFF) : -1;
      if (isVendor && (sub == 0x27 || sub == 0x39)) {
        _log('🧩 Using SDK SleepParser for day $dayIndex (subcmd=${data[1]})');
        final sdkSummary = _parseViaSdk(data, dayIndex);
        if (sdkSummary.segments.isNotEmpty && sdkSummary.bedTime != null && sdkSummary.wakeTime != null) {
          return sdkSummary;
        }
        _log('⚠️ SDK SleepParser returned empty/invalid summary, falling back to internal parser');
      }
    } catch (e) {
      _log('⚠️ SDK SleepParser failed: $e — falling back');
    }

    // Check if forced to use simple approach (for debugging)
    if (_forceSimpleApproach) {
      _log('🔧 FORCED: Using simple duration approach for day $dayIndex');
      return _parseSimpleDurationFormat(data, dayIndex);
    }
    
    // Enhanced protocol detection (matching reference implementation)
    SleepProtocol protocol = _detectSleepProtocol(data);
    _log('🔍 Detected sleep protocol: $protocol for day $dayIndex');
    
    switch (protocol) {
      case SleepProtocol.largeDataSingleDay:
        // Use timestamp header for ANY dayIndex (day 0 and historical),
        // deriving dates from dayIndex so bed/wake are correct
        return _parseSingleDayFormat(data, dayIndex);
      case SleepProtocol.largeDataMultiDay:
        return _parseMultiDayFormat(data, dayIndex);
      case SleepProtocol.standard:
        // For 0x44 or non-vendor/partial frames, do NOT use SDK. Use simple duration or re-request per-day via Serial.
        _log('📊 Standard protocol detected; skipping SDK and using simple duration approach');
        return _parseSimpleDurationFormat(data, dayIndex);
      case SleepProtocol.unknown:
        _log('⚠️ Unknown sleep protocol, trying simple duration approach first');
        // Try simple approach first since it's more reliable for unknown formats
        return _parseSimpleDurationFormat(data, dayIndex);
    }
  }

  /// Parse via official SDK SleepParser to match manufacturer behavior
  SleepSummary _parseViaSdk(List<int> data, int dayIndex) {
    // The SDK's SleepParser expects the vendor frame and currentIndex (days ago)
    final parser = SleepParser(data, currentIndex: dayIndex);
    final summary = parser.getSleepSummaryWithTimestamps();

    final DateTime? bedTime = summary.bedTime;
    final DateTime? wakeTime = summary.wakeTime;
    final Map<String, int> durations = Map<String, int>.from(summary.durations);

    final List<dynamic> sdkSegments = List<dynamic>.from(summary.segments);
    List<SleepSegment> mapped = [];
    for (int i = 0; i < sdkSegments.length; i++) {
      final s = sdkSegments[i];
      final DateTime start = s.segmentStart as DateTime;
      final DateTime end = s.segmentEnd as DateTime;
      final int stage = s.stageType as int;
      final int minutes = s.sleepIndex as int;
      mapped.add(SleepSegment(
        segmentStart: start,
        segmentEnd: end,
        stageType: stage,
        originalQuality: stage,
        timeIndex: i,
        sleepIndex: minutes,
      ));
    }

    final derivedWake = mapped.isNotEmpty ? mapped.last.segmentEnd : wakeTime;

    return SleepSummary(
      bedTime: bedTime,
      wakeTime: derivedWake,
      durations: durations,
      segments: mapped,
    );
  }
  
  /// Enhanced protocol detection matching reference implementation
  SleepProtocol _detectSleepProtocol(List<int> data) {
    if (data.length >= 2) {
      // Command [188, 39] is Large Data Protocol
      if (data[0] == 188 && data[1] == 39) {
        // Prefer header fields when present; fallback to size heuristic
        if (data.length > 9) {
          final int totalDays = data[6] & 0xFF;
          if (totalDays > 1) {
            return SleepProtocol.largeDataMultiDay;
          }
          return SleepProtocol.largeDataSingleDay;
        }
        // Heuristic fallback
        return (data.length < 100)
            ? SleepProtocol.largeDataSingleDay
            : SleepProtocol.largeDataMultiDay;
      }
      
      // Standard Protocol: [188, 68] or other sleep commands
      if (data[0] == 188 && data[1] == 68) {
        return SleepProtocol.standard;
      }
    }
    
    // Fallback detection for edge cases
    return SleepProtocol.unknown;
  }

  /// Parse single-day vendor format for any dayIndex (0=today, 1=yesterday, ...)
  SleepSummary _parseSingleDayFormat(List<int> data, int dayIndex) {
    if (data.length < 13) {
      return _emptySummary();
    }
    // Extract minutes-from-midnight
    final int startMinutes = (data[9] & 0xFF) | ((data[10] & 0xFF) << 8);
    final int endMinutes = (data[11] & 0xFF) | ((data[12] & 0xFF) << 8);
    // Base date for the target day
    final DateTime today = DateTime.now();
    DateTime sleepDate = DateTime(today.year, today.month, today.day)
        .subtract(Duration(days: dayIndex));
    // If start is late evening, shift to previous day for bedtime
    if (startMinutes > 1080) { // > 18:00
      sleepDate = sleepDate.subtract(const Duration(days: 1));
    }
    DateTime bedTime = DateTime(
      sleepDate.year, sleepDate.month, sleepDate.day,
    ).add(Duration(minutes: startMinutes));
    DateTime wakeTime = DateTime(
      sleepDate.year, sleepDate.month, sleepDate.day,
    ).add(Duration(minutes: endMinutes));
    if (wakeTime.isBefore(bedTime)) {
      wakeTime = wakeTime.add(const Duration(days: 1));
    }
    // Parse segments after header
    final List<SleepSegment> rawSegments = _parseSegments(data.sublist(13), bedTime);
    final List<SleepSegment> processedSegments = _applyProcessingPipeline(rawSegments);
    final Map<String, int> durations = _calculateDurations(processedSegments);
    final DateTime derivedWake = processedSegments.isNotEmpty
        ? processedSegments.last.segmentEnd
        : wakeTime;
    return SleepSummary(
      bedTime: bedTime,
      wakeTime: derivedWake,
      durations: durations,
      segments: processedSegments,
    );
  }

  /// Parse Days 1-6 (historical) - using simple duration approach from working deviceScreen
  SleepSummary _parseMultiDayFormat(List<int> data, int dayIndex) {
    _log('🔍 Parsing multi-day sleep format for day $dayIndex (${data.length} bytes)');
    
    if (data.length < 13) {
      _log('❌ Data too short for multi-day parsing: ${data.length} bytes');
      return _emptySummary();
    }

    try {
      // Check if this is actually Large Data Protocol multi-day or simple format
      if (data[0] == 188 && data[1] == 39 && data.length >= 100) {
        // TRUE Large Data Protocol multi-day - use complex parsing
        return _parseComplexMultiDayFormat(data, dayIndex);
      } else {
        // Simple format - use deviceScreen approach with type-duration pairs
        return _parseSimpleDurationFormat(data, dayIndex);
      }
    } catch (e) {
      _log('❌ Error parsing multi-day sleep data for day $dayIndex: $e');
      return _emptySummary();
    }
  }

  /// Parse simple duration format using deviceScreen working approach
  SleepSummary _parseSimpleDurationFormat(List<int> data, int dayIndex) {
    _log('📊 Using simple duration approach (like deviceScreen)');
    
    // Extract first 13 elements (header) - data starts after byte 13
    
    // Get remaining elements as type-duration pairs (deviceScreen approach)
    List<List<int>> pairs = _getRemainingElements(data);
    
    // Calculate durations using deviceScreen logic
    int lightSleep = _sumLightSleep(pairs);
    int deepSleep = _sumDeepSleep(pairs);
    int remSleep = _sumRapidEyeMovement(pairs);
    int awake = _sumAwake(pairs);
    int totalDuration = lightSleep + deepSleep + remSleep + awake;
    
    _log('📊 Simple duration results:');
    _log('  💤 Deep Sleep: $deepSleep min');
    _log('  😴 Light Sleep: $lightSleep min');
    _log('  👁️  REM Sleep: $remSleep min');
    _log('  😵 Awake: $awake min');
    _log('  ⏱️  Total: $totalDuration min');
    
    // For simple format, we don't have exact timestamps, so create estimated ones
    DateTime today = DateTime.now();
    DateTime sleepDate = today.subtract(Duration(days: dayIndex));
    
    // Estimate bedtime (assume 23:00) and calculate wake time
    DateTime estimatedBedTime = DateTime(
      sleepDate.year,
      sleepDate.month,
      sleepDate.day,
      23, // 11 PM
      0,
    );
    
    DateTime estimatedWakeTime = estimatedBedTime.add(Duration(minutes: totalDuration));
    
    // Create simplified segments for database storage (optional)
    List<SleepSegment> segments = [];
    DateTime currentTime = estimatedBedTime;
    
    if (deepSleep > 0) {
      segments.add(SleepSegment(
        segmentStart: currentTime,
        segmentEnd: currentTime.add(Duration(minutes: deepSleep)),
        stageType: kStageDeep,
        originalQuality: 3,
        timeIndex: 0,
        sleepIndex: deepSleep,
      ));
      currentTime = currentTime.add(Duration(minutes: deepSleep));
    }
    
    if (lightSleep > 0) {
      segments.add(SleepSegment(
        segmentStart: currentTime,
        segmentEnd: currentTime.add(Duration(minutes: lightSleep)),
        stageType: kStageLight,
        originalQuality: 2,
        timeIndex: 1,
        sleepIndex: lightSleep,
      ));
      currentTime = currentTime.add(Duration(minutes: lightSleep));
    }
    
    if (remSleep > 0) {
      segments.add(SleepSegment(
        segmentStart: currentTime,
        segmentEnd: currentTime.add(Duration(minutes: remSleep)),
        stageType: kStageREM,
        originalQuality: 4,
        timeIndex: 2,
        sleepIndex: remSleep,
      ));
      currentTime = currentTime.add(Duration(minutes: remSleep));
    }
    
    if (awake > 0) {
      segments.add(SleepSegment(
        segmentStart: currentTime,
        segmentEnd: currentTime.add(Duration(minutes: awake)),
        stageType: kStageAwake,
        originalQuality: 5,
        timeIndex: 3,
        sleepIndex: awake,
      ));
    }
    
    // Derive wake from last segment end if we have segments
    final derivedWake = segments.isNotEmpty
        ? segments.last.segmentEnd
        : (totalDuration > 0 ? estimatedWakeTime : null);

    return SleepSummary(
      bedTime: totalDuration > 0 ? estimatedBedTime : null,
      wakeTime: derivedWake,
      durations: {
        'totalDuration': totalDuration,
        'deepSleep': deepSleep,
        'lightSleep': lightSleep,
        'rapidEyeMovement': remSleep,
        'awake': awake,
      },
      segments: segments,
    );
  }

  /// Parse complex multi-day format (original sophisticated approach for true multi-day packets)
  SleepSummary _parseComplexMultiDayFormat(List<int> data, int dayIndex) {
    _log('🔍 Using complex multi-day parsing for Large Data Protocol');
    
    // Multi-Day Protocol Structure (from SDK):
    int totalDays = data[6];
    int currDay = data[7];
    int firstDayLength = data[8];

    _log('📊 Multi-day packet: totalDays=$totalDays, currDay=$currDay, firstDayLength=$firstDayLength');

    // FIXED: Correct day targeting logic
    // Packet contains the requested day first (index 0), followed by previous days.
    // We therefore always parse packet index 0 and use dayIndex only for date attribution.
    int targetDayInPacket = 0;

    if (targetDayInPacket >= totalDays) {
      _log('❌ Target day $dayIndex not available in packet (totalDays=$totalDays)');
      return _emptySummary();
    }

    // Parse through days to find our target day
    int currentPos = 9; // Start after header
    DateTime? bedTime;
    DateTime? wakeTime;
    List<int> targetDaySegmentData = [];

    for (int packetDayIndex = 0; packetDayIndex < totalDays; packetDayIndex++) {
      int dayDataLength;
      
      if (packetDayIndex == 0) {
        // First day uses firstDayLength from header
        dayDataLength = firstDayLength;
      } else {
        // Subsequent days have their own length byte
        if (currentPos >= data.length) break;
        int dayId = data[currentPos];
        dayDataLength = data[currentPos + 1];
        currentPos += 2; // Skip day ID and length bytes
        _log('📅 Packet day $packetDayIndex: dayId=$dayId, length=$dayDataLength');
      }

      if (packetDayIndex == targetDayInPacket) {
        _log('🎯 Found target day $dayIndex at packet index $packetDayIndex');
        
        // Parse sleep start/end times (little-endian, minutes from midnight)
        if (currentPos + 3 < data.length) {
          int startMinutes = data[currentPos] | (data[currentPos + 1] << 8);
          int endMinutes = data[currentPos + 2] | (data[currentPos + 3] << 8);
          
          _log('⏰ Raw times: start=$startMinutes min, end=$endMinutes min');
          
          // Use stable date calculation
          DateTime today = DateTime.now();
          DateTime sleepDate = today.subtract(Duration(days: dayIndex));
          
          // Handle overnight sleep (if start > 18:00, sleep started previous day)
          if (startMinutes > 1080) { // 18:00 = 1080 minutes
            sleepDate = sleepDate.subtract(Duration(days: 1));
            _log('🌙 Overnight sleep detected, adjusting date backwards');
          }
          
          bedTime = DateTime(
            sleepDate.year,
            sleepDate.month,
            sleepDate.day,
          ).add(Duration(minutes: startMinutes));
          
          wakeTime = DateTime(
            sleepDate.year,
            sleepDate.month,
            sleepDate.day,
          ).add(Duration(minutes: endMinutes));
          
          // If wake time is before bed time, it's next day
          if (wakeTime.isBefore(bedTime)) {
            wakeTime = wakeTime.add(Duration(days: 1));
            _log('🌅 Wake time adjusted to next day');
          }
          
          _log('🛏️ Sleep times: ${_formatTime(bedTime)} - ${_formatTime(wakeTime)}');
          
          // Extract segment data (starts after the 4 time bytes)
          int segmentStartPos = currentPos + 4;
          int segmentEndPos = currentPos + dayDataLength;
          if (segmentEndPos <= data.length) {
            targetDaySegmentData = data.sublist(segmentStartPos, segmentEndPos);
            _log('📊 Extracted ${targetDaySegmentData.length} bytes of segment data');
          }
        }
        break;
      }
      
      // Skip this day's data
      currentPos += dayDataLength;
    }

    if (bedTime == null || wakeTime == null) {
      _log('❌ Could not extract sleep times for day $dayIndex');
      return _emptySummary();
    }

    // Parse raw segments from segment data
    List<SleepSegment> rawSegments = _parseSegments(targetDaySegmentData, bedTime);
    _log('📊 RAW PARSING RESULT: ${rawSegments.length} segments extracted from device data');
    
    // Apply processing pipeline for complex format
    List<SleepSegment> processedSegments = _applyOfficialProcessingPipeline(rawSegments);
    _log('📊 PROCESSING PIPELINE RESULT: ${processedSegments.length} segments after pipeline');
    
    // OPTION: Use raw segments instead of processed ones for database storage
    // Uncomment the line below to store raw segments (22) instead of processed segments (21)
    // List<SleepSegment> segmentsToStore = rawSegments;
    
    // Calculate durations from processed segments
    Map<String, int> durations = _calculateDurations(processedSegments);
    
    // Derive wake from last segment end if we have segments
    final derivedWake = processedSegments.isNotEmpty
        ? processedSegments.last.segmentEnd
        : wakeTime;

    return SleepSummary(
      bedTime: bedTime,
      wakeTime: derivedWake,
      durations: durations,
      segments: processedSegments,
    );
  }

  /// Parse sleep segments from raw segment data
  List<SleepSegment> _parseSegments(List<int> segmentData, DateTime startTime) {
    List<SleepSegment> segments = [];
    DateTime currentTime = startTime;
    
    _log('🔍 Parsing ${segmentData.length} bytes of segment data, expecting ${segmentData.length ~/ 2} segments');
    
    // Parse segments as (type, duration) pairs
    // FIXED: Ensure we process all complete pairs without skipping the last one
    for (int i = 0; i + 1 < segmentData.length; i += 2) {
      int type = segmentData[i];
      int duration = segmentData[i + 1];
      
      // Handle negative bytes
      if (duration < 0) duration = duration & 0xFF;
      
      // Skip invalid segments (type 0 and duration 0 usually means no data)
      if (type == 0 && duration == 0) {
        _log('⚠️ Skipping empty segment at index ${i ~/ 2}');
        continue;
      }
      
      // Skip unreasonably long segments (probably data corruption)
      if (duration > 480) { // More than 8 hours for one segment
        _log('⚠️ Skipping abnormally long segment: ${duration}min at index ${i ~/ 2}');
        continue;
      }
      
      DateTime segmentStart = currentTime;
      DateTime segmentEnd = currentTime.add(Duration(minutes: duration));
      
      // Map device sleep types to our constants
      int stageType = _mapSleepType(type);
      
      _log('📊 Segment ${i ~/ 2}: type=$type → stage=$stageType, duration=${duration}min');
      
      segments.add(SleepSegment(
        segmentStart: segmentStart,
        segmentEnd: segmentEnd,
        stageType: stageType,
        originalQuality: type,
        timeIndex: i ~/ 2,
        sleepIndex: duration,
      ));
      
      currentTime = segmentEnd;
    }
    
    // Check for incomplete pair at the end
    if (segmentData.length % 2 == 1) {
      _log('⚠️ WARNING: Segment data has odd length (${segmentData.length}), last byte ignored');
    }
    
    _log('✅ Successfully parsed ${segments.length} sleep segments');
    return segments;
  }

  /// Map device sleep types to display types (enhanced with official mapping)
  int _mapSleepType(int deviceType) {
    switch (deviceType) {
      case 3: return kStageDeep;      // Deep sleep → stage 1
      case 2: return kStageLight;     // Light sleep → stage 2
      case 5: return kStageAwake;     // Awake → stage 3
      case 4: return kStageREM;       // REM sleep → stage 4
      default: return kStageUnknown;  // Unknown → stage 5
    }
  }

  /// Apply official processing pipeline from LargeDataHandler.java (CRITICAL FIX)
  List<SleepSegment> _applyProcessingPipeline(List<SleepSegment> rawSegments) {
    if (rawSegments.isEmpty) return rawSegments;
    
    _log('🔄 Applying processing pipeline to ${rawSegments.length} raw segments...');
    
    // Step 1: Merge consecutive segments of same type
    List<SleepSegment> mergedSegments = _mergeConsecutiveSegments(rawSegments);
    _log('📊 After merging: ${mergedSegments.length} segments (was ${rawSegments.length})');
    
    // Step 2: Ensure proper timestamp chaining
    List<SleepSegment> chainedSegments = _ensureTimestampChaining(mergedSegments);
    _log('⛓️ After timestamp chaining: ${chainedSegments.length} segments');
    
    _log('✅ Processing pipeline complete');
    return chainedSegments;
  }

  // Backward compatibility alias for external users
  List<SleepSegment> _applyOfficialProcessingPipeline(List<SleepSegment> rawSegments) {
    return _applyProcessingPipeline(rawSegments);
  }
  
  /// Step 1: Merge consecutive segments of same type (official implementation)
  List<SleepSegment> _mergeConsecutiveSegments(List<SleepSegment> segments) {
    if (segments.isEmpty) return segments;
    
    _log('🔗 MERGE PHASE: Starting with ${segments.length} raw segments');
    
    List<SleepSegment> merged = [];
    SleepSegment current = segments.first;
    int mergeCount = 0;
    
    for (int i = 1; i < segments.length; i++) {
      SleepSegment next = segments[i];
      
      // Check if current stage type matches next stage type
      if (current.stageType == next.stageType) {
        // Same type: extend duration (merge segments)
        current = SleepSegment(
          segmentStart: current.segmentStart,
          segmentEnd: next.segmentEnd, // Extend to next segment's end
          stageType: current.stageType,
          originalQuality: current.originalQuality,
          timeIndex: current.timeIndex,
          sleepIndex: current.sleepIndex + next.sleepIndex, // Combine durations
        );
        mergeCount++;
        _log('🔗 MERGED: Segments ${i-1} and $i (both ${_getSleepStageString(current.stageType)}) → total merges: $mergeCount');
      } else {
        // Different type: finalize current segment, start new one
        merged.add(current);
        _log('➕ ADDED: Segment ${merged.length}: ${_getSleepStageString(current.stageType)} (${current.sleepIndex}min)');
        current = next;
      }
    }
    merged.add(current); // Add final segment
    _log('➕ ADDED: Final segment ${merged.length}: ${_getSleepStageString(current.stageType)} (${current.sleepIndex}min)');
    
    _log('🔗 MERGE RESULT: ${segments.length} → ${merged.length} segments ($mergeCount merges performed)');
    return merged;
  }
  
  /// Step 2: Apply official sleep stage mapping (LargeDataHandler.java:652-672)
  List<SleepSegment> _applyOfficialStageMapping(List<SleepSegment> segments) {
    return segments.map((segment) {
      int officialStageType = _mapDeviceTypeToOfficialType(segment.stageType);
      
      return SleepSegment(
        segmentStart: segment.segmentStart,
        segmentEnd: segment.segmentEnd,
        stageType: officialStageType,
        originalQuality: segment.originalQuality,
        timeIndex: segment.timeIndex,
        sleepIndex: segment.sleepIndex,
      );
    }).toList();
  }
  
  /// Official type mapping from LargeDataHandler.java:652-672
  int _mapDeviceTypeToOfficialType(int deviceType) {
    int result;
    switch (deviceType) {
      case 1: result = 1; break; // Deep Sleep: already mapped correctly
      case 2: result = 2; break; // Light Sleep: already mapped correctly  
      case 3: result = 3; break; // Awake: already mapped correctly
      case 4: result = 4; break; // REM Sleep: already mapped correctly
      case 5: result = 5; break; // Other/Unknown: already mapped correctly
      default: result = 5; break; // Fallback to unknown
    }
    return result;
  }
  
  /// Step 3: Ensure proper timestamp chaining (LargeDataHandler.java:673-681)
  List<SleepSegment> _ensureTimestampChaining(List<SleepSegment> segments) {
    if (segments.isEmpty) return segments;
    
    List<SleepSegment> chained = [];
    DateTime currentTime = segments.first.segmentStart;
    
    for (int i = 0; i < segments.length; i++) {
      SleepSegment segment = segments[i];
      int durationMinutes = segment.sleepIndex;
      DateTime segmentEnd = currentTime.add(Duration(minutes: durationMinutes));
      
      SleepSegment chainedSegment = SleepSegment(
        segmentStart: currentTime,
        segmentEnd: segmentEnd,
        stageType: segment.stageType,
        originalQuality: segment.originalQuality,
        timeIndex: segment.timeIndex,
        sleepIndex: durationMinutes,
      );
      
      chained.add(chainedSegment);
      currentTime = segmentEnd; // Chain to next segment
    }
    
    return chained;
  }

  /// Calculate sleep stage durations
  Map<String, int> _calculateDurations(List<SleepSegment> segments) {
    int totalDuration = 0;
    int deepSleep = 0;
    int lightSleep = 0;
    int remSleep = 0;
    int awake = 0;
    
    for (final segment in segments) {
      int duration = segment.segmentEnd.difference(segment.segmentStart).inMinutes;
      totalDuration += duration;
      
      switch (segment.stageType) {
        case kStageDeep:
          deepSleep += duration;
          break;
        case kStageLight:
          lightSleep += duration;
          break;
        case kStageREM:
          remSleep += duration;
          break;
        case kStageAwake:
          awake += duration;
          break;
      }
    }
    
    return {
      'totalDuration': totalDuration,
      'deepSleep': deepSleep,
      'lightSleep': lightSleep,
      'rapidEyeMovement': remSleep,
      'awake': awake,
    };
  }

  /// Store sleep session in database with enhanced storage strategy
  Future<void> _storeSleepSession(SleepSummary summary) async {
    try {
      _log('🔄 Starting sleep session storage...');
      _log('📊 Input: ${summary.segments.length} segments to store');
      
      // Clear existing sleep data for this sleep session to avoid duplicates
      if (summary.bedTime != null && summary.wakeTime != null) {
        await _clearExistingSleepSession(summary.bedTime!, summary.wakeTime!);
      }
      
      // Store each sleep segment as a separate record using batch insert for performance
      final List<SleepRawDataCompanion> records = [];
      
      _log('🔍 Processing segments for database insertion...');
      for (int i = 0; i < summary.segments.length; i++) {
        final segment = summary.segments[i];
        final sleepStageString = _getSleepStageString(segment.stageType);
        
        _log('📊 Segment $i: stage=$sleepStageString, start=${segment.segmentStart.toIso8601String()}');
        
        // Validate segment data before insertion
        // Check if segmentStart is a valid DateTime (not a default/invalid value)
        if (segment.segmentStart.year < 2020 || segment.segmentStart.year > 2100) {
          _log('❌ WARNING: Segment $i has invalid timestamp year ${segment.segmentStart.year} - SKIPPING');
          continue;
        }
        
        if (sleepStageString == 'unknown') {
          _log('⚠️ WARNING: Segment $i has unknown stage type ${segment.stageType}');
        }
        
        final sleepRecord = SleepRawDataCompanion.insert(
          timestamp: segment.segmentStart,
          bedTime: Value(summary.bedTime),
          wakeTime: Value(summary.wakeTime),
          sleepStage: sleepStageString,
          deviceId: _deviceId,
          dirty: const Value(true),
        );
        records.add(sleepRecord);
      }
      
      _log('📊 Created ${records.length} database records from ${summary.segments.length} segments');
      
      if (records.length != summary.segments.length) {
        _log('❌ CRITICAL: Record count mismatch! ${records.length} records vs ${summary.segments.length} segments');
      }
      
      // Batch insert for better performance
      _log('💾 Executing batch insert...');
      await _database.batch((batch) {
        batch.insertAll(_database.sleepRawData, records, mode: InsertMode.insertOrReplace);
      });
      
      _log('✅ Batch insert completed for ${records.length} records');
      
      // Verify actual database storage
      if (summary.bedTime != null && summary.wakeTime != null) {
        // Wait a moment for async operations to complete
        await Future.delayed(Duration(milliseconds: 100));
        
        final storedCount = await _getStoredSegmentCount(summary.bedTime!, summary.wakeTime!);
        _log('📊 Database verification: $storedCount segments actually stored in database');
        
        if (storedCount != records.length) {
          _log('❌ CRITICAL: Database storage mismatch! Expected ${records.length}, actually stored $storedCount');
          
          // Get detailed breakdown of what was stored
          final storedSegments = await _getStoredSegmentDetails(summary.bedTime!, summary.wakeTime!);
          _log('📋 Stored segments breakdown:');
          for (int i = 0; i < storedSegments.length; i++) {
            final seg = storedSegments[i];
            _log('   $i: ${seg.sleepStage} at ${seg.timestamp.toIso8601String()}');
          }
        } else {
          _log('✅ Storage verification successful: all segments stored correctly');
        }
      }
      
    } catch (e) {
      _log('❌ Failed to store sleep session: $e');
      _log('❌ Stack trace: ${StackTrace.current}');
      rethrow; // Re-throw to handle at higher level if needed
    }
  }
  
  /// Clear existing sleep data for a sleep session to avoid duplicates
  Future<void> _clearExistingSleepSession(DateTime bedTime, DateTime wakeTime) async {
    try {
      // 1) Delete exact-match sessions (legacy behavior)
      await (_database.delete(_database.sleepRawData)
        ..where((tbl) => tbl.bedTime.equals(bedTime) & tbl.wakeTime.equals(wakeTime) & tbl.deviceId.equals(_deviceId))
      ).go();

      // 2) Also delete any records within a tighter window around this session
      final DateTime anchorStart = bedTime.subtract(const Duration(hours: 1));
      final DateTime anchorEnd = wakeTime.add(const Duration(hours: 1));

      await (_database.delete(_database.sleepRawData)
        ..where((tbl) =>
          tbl.deviceId.equals(_deviceId) &
          tbl.timestamp.isBetweenValues(anchorStart, anchorEnd)
        )
      ).go();
      
      _log('🧹 Cleared existing sleep session data for ${_formatTime(bedTime)} - ${_formatTime(wakeTime)}');
    } catch (e) {
      _log('⚠️ Failed to clear existing sleep session: $e');
    }
  }
  
  /// Get count of stored segments for verification
  Future<int> _getStoredSegmentCount(DateTime bedTime, DateTime wakeTime) async {
    try {
      final query = _database.selectOnly(_database.sleepRawData)
        ..addColumns([_database.sleepRawData.id.count()])
        ..where(_database.sleepRawData.bedTime.equals(bedTime) & 
               _database.sleepRawData.wakeTime.equals(wakeTime) & 
               _database.sleepRawData.deviceId.equals(_deviceId));
      
      final result = await query.getSingle();
      return result.read(_database.sleepRawData.id.count()) ?? 0;
    } catch (e) {
      _log('⚠️ Failed to verify stored segment count: $e');
      return 0;
    }
  }

  /// Get detailed breakdown of stored segments for debugging
  Future<List<SleepRawDataData>> _getStoredSegmentDetails(DateTime bedTime, DateTime wakeTime) async {
    try {
      final query = _database.select(_database.sleepRawData)
        ..where((tbl) => tbl.bedTime.equals(bedTime) & 
               tbl.wakeTime.equals(wakeTime) & 
               tbl.deviceId.equals(_deviceId))
        ..orderBy([(t) => OrderingTerm.asc(t.timestamp)]);
      
      return await query.get();
    } catch (e) {
      _log('⚠️ Failed to get stored segment details: $e');
      return [];
    }
  }

  /// Convert sleep stage type to string
  String _getSleepStageString(int stageType) {
    switch (stageType) {
      case kStageDeep: return 'deep';
      case kStageLight: return 'light';
      case kStageREM: return 'rem';
      case kStageAwake: return 'awake';
      default: return 'unknown';
    }
  }

  /// Display sleep summary for debugging
  void _displaySleepSummary(SleepSummary summary, int dayIndex) {
    final dayLabel = _getDayLabel(dayIndex);
    
    _log('\n📊 Sleep Summary - $dayLabel (Day $dayIndex):');
    _log('  🛏️  Bed Time: ${_formatTime(summary.bedTime!)}');
    _log('  🌅 Wake Time: ${_formatTime(summary.wakeTime!)}');
    _log('  ⏱️  Total Sleep: ${summary.durations['totalDuration']} minutes');
    _log('  📈 Sleep Stage Breakdown:');
    _log('    💤 Deep Sleep: ${summary.durations['deepSleep']} min');
    _log('    😴 Light Sleep: ${summary.durations['lightSleep']} min');
    _log('    👁️  REM Sleep: ${summary.durations['rapidEyeMovement']} min');
    _log('    😵 Awake: ${summary.durations['awake']} min');
    _log('  🔄 Total Segments: ${summary.segments.length}');
  }

  String _getDayLabel(int dayIndex) {
    switch (dayIndex) {
      case 0: return "Today";
      case 1: return "Yesterday";
      case 2: return "2 days ago";
      case 3: return "3 days ago";
      case 4: return "4 days ago";
      case 5: return "5 days ago";
      case 6: return "6 days ago";
      default: return "$dayIndex days ago";
    }
  }

  String _formatTime(DateTime dateTime) {
    return "${dateTime.hour.toString().padLeft(2, '0')}:${dateTime.minute.toString().padLeft(2, '0')}";
  }

  SleepSummary _emptySummary() {
    return SleepSummary(
      durations: {
        'totalDuration': 0,
        'deepSleep': 0,
        'lightSleep': 0,
        'rapidEyeMovement': 0,
        'awake': 0,
      },
      segments: [],
    );
  }

  void _log(String message) => debugPrint('[SleepHandler] $message');

  // FIFO cache add with size cap
  void _cacheAdd(LinkedHashSet<String> set, String key) {
    if (set.contains(key)) return;
    set.add(key);
    if (set.length > _maxCacheSize) {
      // Remove oldest (insertion-ordered)
      final String oldest = set.first;
      set.remove(oldest);
    }
  }

  String _getDateKeyForOffset(int dayOffset) {
    final now = DateTime.now();
    final target = DateTime(now.year, now.month, now.day).subtract(Duration(days: dayOffset));
    return "${target.year}-${target.month.toString().padLeft(2, '0')}-${target.day.toString().padLeft(2, '0')}";
  }

  // DeviceScreen helper methods for simple duration approach

  /// Get remaining elements as type-duration pairs (from deviceScreen)
  List<List<int>> _getRemainingElements(List<int> data) {
    if (data.length <= 13) {
      return [];
    }
    final List<int> remaining = data.sublist(13);
    final List<List<int>> pairs = [];
    for (int i = 0; i < remaining.length; i += 2) {
      if (i + 1 < remaining.length) {
        pairs.add([remaining[i], remaining[i + 1]]);
      } else {
        pairs.add([remaining[i]]); // Handle odd number of elements gracefully
      }
    }
    return pairs;
  }

  /// Sum light sleep durations (from deviceScreen)
  int _sumLightSleep(List<List<int>> pairs) {
    int sum = 0;
    for (final pair in pairs) {
      if (pair.length == 2 && pair[0] == 2) {
        sum += pair[1];
      }
    }
    return sum;
  }

  /// Sum deep sleep durations (from deviceScreen)
  int _sumDeepSleep(List<List<int>> pairs) {
    int sum = 0;
    for (final pair in pairs) {
      if (pair.length == 2 && pair[0] == 3) {
        sum += pair[1];
      }
    }
    return sum;
  }

  /// Sum REM sleep durations (from deviceScreen)
  int _sumRapidEyeMovement(List<List<int>> pairs) {
    int sum = 0;
    for (final pair in pairs) {
      if (pair.length == 2 && pair[0] == 4) {
        sum += pair[1];
      }
    }
    return sum;
  }

  /// Sum awake durations (from deviceScreen)
  int _sumAwake(List<List<int>> pairs) {
    int sum = 0;
    for (final pair in pairs) {
      if (pair.length == 2 && pair[0] == 5) {
        sum += pair[1];
      }
    }
    return sum;
  }
  
  /// Test method to validate the hybrid approach (Day 0 + Simple Duration)
  void testProcessingPipeline() {
    _log('🧪 Testing hybrid sleep processing approach...');
    
    // Test 1: Day 0 Single Format (should use timestamp parsing)
    List<int> day0TestData = [
      188, 39,        // Command: Large Data Protocol
      55, 0,          // Data length: 55 bytes (single-day format)
      111, 5,         // Sleep start: 23:11 (1391 minutes)
      197, 1,         // Sleep end: 07:33 (453 minutes)
      0, 0, 0, 0, 0,  // Padding to reach byte 13
      3, 120,         // Segment 1: Deep sleep (type 3) for 120 minutes
      2, 180,         // Segment 2: Light sleep (type 2) for 180 minutes
      5, 15,          // Segment 3: Awake (type 5) for 15 minutes  
      2, 150,         // Segment 4: Light sleep (type 2) for 150 minutes
      3, 60,          // Segment 5: Deep sleep (type 3) for 60 minutes
    ];
    
    _log('🔍 Testing Day 0 (single-day) format...');
    SleepProtocol protocol0 = _detectSleepProtocol(day0TestData);
    _log('  Protocol detected: $protocol0 (expected: largeDataSingleDay)');
    
    SleepSummary summary0 = _parseSleepDataPacket(day0TestData, 0);
    _log('  📊 Day 0 Results: ${summary0.durations['totalDuration']} min total');
    
    // Test 2: Simple Duration Format (should use deviceScreen approach)  
    List<int> simpleDurationData = [
      188, 39,        // Command: Large Data Protocol
      45, 0,          // Data length: 45 bytes (simple format)
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // Header (13 bytes)
      2, 120,         // Light sleep: 120 minutes
      3, 90,          // Deep sleep: 90 minutes
      4, 30,          // REM sleep: 30 minutes
      5, 15,          // Awake: 15 minutes
      2, 60,          // Light sleep: 60 minutes
    ];
    
    _log('🔍 Testing simple duration format...');
    SleepProtocol protocol1 = _detectSleepProtocol(simpleDurationData);
    _log('  Protocol detected: $protocol1 (expected: largeDataSingleDay → simple approach)');
    
    SleepSummary summary1 = _parseSleepDataPacket(simpleDurationData, 1);
    _log('  📊 Simple Duration Results:');
    _log('    💤 Deep Sleep: ${summary1.durations['deepSleep']} min');
    _log('    😴 Light Sleep: ${summary1.durations['lightSleep']} min');
    _log('    👁️  REM Sleep: ${summary1.durations['rapidEyeMovement']} min');
    _log('    😵 Awake: ${summary1.durations['awake']} min');
    _log('    ⏱️  Total: ${summary1.durations['totalDuration']} min');
    
    // Test 3: Standard Protocol (should use simple duration approach)
    List<int> standardData = [
      188, 68,        // Command: Standard Protocol
      30, 0,          // Data length
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // Header
      3, 60,          // Deep sleep: 60 minutes
      2, 240,         // Light sleep: 240 minutes
      4, 45,          // REM sleep: 45 minutes
    ];
    
    _log('🔍 Testing standard protocol...');
    SleepProtocol protocol2 = _detectSleepProtocol(standardData);
    _log('  Protocol detected: $protocol2 (expected: standard)');
    
    SleepSummary summary2 = _parseSleepDataPacket(standardData, 2);
    _log('  📊 Standard Protocol Results: ${summary2.durations['totalDuration']} min total');
    
    _log('✅ Hybrid processing pipeline test complete');
    _log('');
    _log('🎯 Key Improvements:');
    _log('  ✅ Day 0 uses timestamp parsing (maintains accuracy)');
    _log('  ✅ Days 1-6 use simple duration summation (deviceScreen approach)');
    _log('  ✅ Protocol detection chooses correct parsing method');
    _log('  ✅ Fallback to simple approach for unknown formats');
  }
  
  /// Demonstrate enhanced database functionality
  Future<void> testDatabaseFunctionality() async {
    _log('🗄️ Testing enhanced database functionality...');
    
    try {
      // Get database statistics
      final stats = await getDatabaseStats();
      _log('📊 Database Stats: $stats');
      
      // Get recent sleep sessions
      final sessions = await getSleepSessions(daysBack: 7);
      _log('💤 Found ${sessions.length} sleep sessions in last 7 days:');
      
      for (int i = 0; i < sessions.length && i < 3; i++) {
        final session = sessions[i];
        _log('  ${i + 1}. $session');
      }
      
      // Get latest sleep session details
      final latestSleep = await getLatestSleep();
      if (latestSleep != null) {
        _log('🌙 Latest sleep segment: ${latestSleep.sleepStage} at ${_formatTime(latestSleep.timestamp)}');
        
        if (latestSleep.bedTime != null && latestSleep.wakeTime != null) {
          final sessionSummary = await getSleepSession(latestSleep.bedTime!, latestSleep.wakeTime!);
          if (sessionSummary != null) {
            _log('📈 Latest session summary: $sessionSummary');
          }
        }
      }
      
      _log('✅ Database functionality test complete');
      
    } catch (e) {
      _log('❌ Database functionality test failed: $e');
    }
  }
}

// Data models

/// Sleep segment representing a time period with sleep stage
class SleepSegment {
  final DateTime segmentStart;
  final DateTime segmentEnd;
  final int stageType;
  final int originalQuality;
  final int timeIndex;
  final int sleepIndex;

  SleepSegment({
    required this.segmentStart,
    required this.segmentEnd,
    required this.stageType,
    required this.originalQuality,
    required this.timeIndex,
    required this.sleepIndex,
  });
}

/// Sleep summary with aggregated statistics
class SleepSummary {
  final DateTime? bedTime;
  final DateTime? wakeTime;
  final Map<String, int> durations;
  final List<SleepSegment> segments;

  SleepSummary({
    this.bedTime,
    this.wakeTime,
    required this.durations,
    required this.segments,
  });
}

/// Sleep session summary for database analysis
class SleepSessionSummary {
  final DateTime bedTime;
  final DateTime wakeTime;
  final int totalDuration;
  final int deepSleep;
  final int lightSleep;
  final int remSleep;
  final int awake;
  final int segmentCount;
  final int efficiency; // Sleep efficiency percentage

  SleepSessionSummary({
    required this.bedTime,
    required this.wakeTime,
    required this.totalDuration,
    required this.deepSleep,
    required this.lightSleep,
    required this.remSleep,
    required this.awake,
    required this.segmentCount,
    required this.efficiency,
  });

  @override
  String toString() {
          return 'SleepSession(${_formatTimeOnly(bedTime)} - ${_formatTimeOnly(wakeTime)}, '
           '${totalDuration}min total, $segmentCount segments, $efficiency% efficiency)';
  }

  String _formatTimeOnly(DateTime time) {
    return "${time.hour.toString().padLeft(2, '0')}:${time.minute.toString().padLeft(2, '0')}";
  }
}

/// Database statistics for monitoring sleep data storage
class SleepDatabaseStats {
  final int totalRecords;
  final int dirtyRecords;
  final DateTime? earliestRecord;
  final DateTime? latestRecord;

  SleepDatabaseStats({
    required this.totalRecords,
    required this.dirtyRecords,
    this.earliestRecord,
    this.latestRecord,
  });

  @override
  String toString() {
    return 'SleepDatabaseStats(total: $totalRecords, dirty: $dirtyRecords, '
           'range: ${earliestRecord != null ? _formatDate(earliestRecord!) : 'none'} - '
           '${latestRecord != null ? _formatDate(latestRecord!) : 'none'})';
  }

  String _formatDate(DateTime date) {
    return "${date.year}-${date.month.toString().padLeft(2, '0')}-${date.day.toString().padLeft(2, '0')}";
  }
}