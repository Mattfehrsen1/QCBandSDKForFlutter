import 'dart:typed_data';
import 'dart:async'; // Required for StreamController if used elsewhere

// Assuming these are external dependencies or part of your core setup
// You might need to import flutter_blue_plus here if you use its types directly.
// import 'package:flutter_blue_plus/flutter_blue_plus.dart';

// Helper to convert 4 little-endian bytes to int (Unix timestamp)
// This should be accessible to ReadHeartRateResponse
int bytesToLittleEndianInt(Uint8List bytes, int offset) {
  final byteData = ByteData.view(bytes.buffer, offset, 4);
  return byteData.getInt32(0, Endian.little);
}

// Represents the parsed heart rate response for a full day (aligned with app protocol)
class ReadHeartRateResponse {
  int? mUtcTime; // Unix timestamp (seconds) for the day's heart rate data (adjusted for timezone like app)
  List<int> mHeartRateArray = List.filled(288, 0); // 24h at 5-min resolution default
  int currentHrArrayFilledSize = 0; // Progress (capped to 288)

  // Internal assembly buffer based on header size*13
  int _rangeMinutes = 5; // sampling interval minutes from header (default 5)
  int _packetCount = 0; // header size
  int _writeIndex = 0; // write pointer in _rawBuffer
  List<int> _rawBuffer = const <int>[];
  bool _complete = false;

  int get range => _rangeMinutes;
  bool get isComplete => _complete;

  // Accepts one incoming frame of cmd 21 as seen on the wire: [21, sub, ...]
  // Returns true when transfer completes (end flag reached), false otherwise.
  bool acceptData(Uint8List data) {
    if (data.isEmpty || data[0] != 21) {
      return false;
    }

    final int sub = data.length > 1 ? (data[1] & 0xFF) : 0xFF;

    // Early termination frames: in production app, 0xFF or (today and 0x17) can signal end
    // Handle special "today terminator" 0x17 to avoid hanging on today's history
    if (sub == 0x17) {
      if (mUtcTime != null) {
        final dtLocal = DateTime.fromMillisecondsSinceEpoch(mUtcTime! * 1000, isUtc: true).toLocal();
        final nowLocal = DateTime.now();
        final bool isSameLocalDay = dtLocal.year == nowLocal.year && dtLocal.month == nowLocal.month && dtLocal.day == nowLocal.day;
        if (isSameLocalDay) {
          _finalizeToDailyArray();
          _complete = true;
          return true;
        }
      }
      // If timestamp/day unknown yet, fall through and wait for normal completion
    }

    if (sub == 0xFF) {
      _finalizeToDailyArray();
      _complete = true;
      return true;
    }

    // Header
    if (sub == 0x00) {
      if (data.length >= 4) {
        _packetCount = data[2] & 0xFF;
        _rangeMinutes = data[3] & 0xFF;
        final int totalLen = _packetCount * 13;
        _rawBuffer = List<int>.filled(totalLen, 0);
        _writeIndex = 0;
        _complete = false;
      }
      return false;
    }

    // First data with timestamp
    if (sub == 0x01) {
      if (data.length >= 6) {
        // LE timestamp at bytes [2..5]
        final ts = bytesToLittleEndianInt(Uint8List.sublistView(data, 2, 6), 0);
        // Align with app: subtract local timezone to get UTC-like seconds
        final int tzOffsetSec = DateTime.now().timeZoneOffset.inSeconds;
        mUtcTime = ts - tzOffsetSec;
        // Copy remaining payload starting at byte 6
        final payload = data.sublist(6);
        _copyIntoRaw(payload);
      }
      return false;
    }

    // Subsequent data
    if (sub >= 0x02) {
      if (data.length > 2) {
        final payload = data.sublist(2);
        _copyIntoRaw(payload);
      }
      // End when sub == size-1 (per app)
      if (_packetCount > 0 && sub == (_packetCount - 1)) {
        _finalizeToDailyArray();
        _complete = true;
        return true;
      }
      return false;
    }

    return false;
  }

  void _copyIntoRaw(List<int> payload) {
    if (_rawBuffer.isEmpty || payload.isEmpty) {
      return;
    }
    final int remaining = _rawBuffer.length - _writeIndex;
    if (remaining <= 0) return;
    final int n = payload.length < remaining ? payload.length : remaining;
    for (int i = 0; i < n; i++) {
      _rawBuffer[_writeIndex + i] = payload[i] & 0xFF;
    }
    _writeIndex += n;
    currentHrArrayFilledSize = _writeIndex > 288 ? 288 : _writeIndex;
  }

  void _finalizeToDailyArray() {
    // Map raw bytes into 288-length array. If sampling range != 5 min, we place
    // samples at their nearest 5-min slots and cap array length.
    if (_rawBuffer.isEmpty) return;
    final int step = (_rangeMinutes <= 0) ? 5 : _rangeMinutes;
    final int slots = 24 * 60 ~/ 5; // 288
    final List<int> out = List<int>.filled(slots, 0);
    int minutes = 0;
    for (int i = 0; i < _rawBuffer.length && minutes < 24 * 60; i++) {
      final int slot = (minutes ~/ 5);
      if (slot >= 0 && slot < slots) {
        out[slot] = _rawBuffer[i] & 0xFF;
      }
      minutes += step;
    }

    // If today, zero future samples beyond current minute as app does
    if (mUtcTime != null) {
      final dt = DateTime.fromMillisecondsSinceEpoch(mUtcTime! * 1000, isUtc: true).toLocal();
      final nowLocal = DateTime.now();
      if (dt.year == nowLocal.year && dt.month == nowLocal.month && dt.day == nowLocal.day) {
        final int todayMin = nowLocal.hour * 60 + nowLocal.minute;
        final int cutoffSlot = todayMin ~/ 5;
        for (int i = cutoffSlot + 1; i < slots; i++) {
          out[i] = 0;
        }
      }
    }

    mHeartRateArray = out;
    currentHrArrayFilledSize = _writeIndex > 288 ? 288 : _writeIndex;
  }
}