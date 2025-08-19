## Heart Rate integration (shareable overview)

Simple overview of how HR works in this SDK. Matches the production Android app protocol.

### What’s supported

- History HR (full day, cmd 21)
- Realtime HR control (start/stop over cmd 105 type=6; values on cmd 30)
- Automatic HR setting read/write (cmd 22)

### Quick start: History HR (cmd 21)

1) Choose the day you want (use local midnight), convert to Unix seconds:
```dart
// Local midnight for a given day
final localMidnight = DateTime(target.year, target.month, target.day);
final unixTs = localMidnight.toUtc().millisecondsSinceEpoch ~/ 1000;
```

2) Build and write the history request:
```dart
final cmd = QCBandSDK.buildReadHeartRateCommand(unixTs);
await standardWriteCharacteristic.write(cmd);
```

3) Listen to notifications and parse:
```dart
standardNotifyStream.listen((value) {
  final parsed = QCBandSDK.ingestStandardNotification(value);
  if (parsed['dataType'] == 'HeartRateData') {
    final end = parsed['end'] == true || parsed['End'] == true || parsed['dataEnd'] == true;
    final data = parsed['data'] ?? parsed['Data'] ?? parsed['dicData'];
    if (data is Map) {
      // While streaming you may get progress
      final progress = data['progress'];      // optional
      final total = data['totalExpected'];    // optional (288)

      // On completion you get the final array
      final range = data['range'];            // minutes per sample (usually 5)
      final arr = data['heartRateArray'];     // length 288 at 5‑min cadence
      final utc = data['utcTime'];            // start-of-day Unix seconds
      if (end == true && arr is List) {
        // Use arr (5‑min cadence). Future slots for today are zeroed.
      }
    }
  }
});
```

Protocol details (handled for you):
- Frames: header `[21,0,size,range,...]`, first `[21,1,ts(LE),payload...]`, then `[21,n,payload...]`.
- The SDK assembles payloads, maps to 5‑minute cadence (288 points). If `range != 5`, values are resampled to nearest 5‑min slot.

### Example: Today and “3 days ago”

```dart
Future<void> getToday() async {
  final ts = DateTime.now().toUtc().millisecondsSinceEpoch ~/ 1000;
  await standardWriteCharacteristic.write(QCBandSDK.buildReadHeartRateCommand(ts));
}

Future<void> getThreeDaysAgo() async {
  final now = DateTime.now();
  final threeDaysAgoLocalMidnight = DateTime(now.year, now.month, now.day).subtract(const Duration(days: 3));
  final ts = threeDaysAgoLocalMidnight.toUtc().millisecondsSinceEpoch ~/ 1000;
  await standardWriteCharacteristic.write(QCBandSDK.buildReadHeartRateCommand(ts));
}
```

### Realtime HR

- Control: cmd 105, type=6. Helpers:
```dart
await standardWriteCharacteristic.write(QCBandSDK.startRealtimeHeart());
// ... later
await standardWriteCharacteristic.write(QCBandSDK.stopRealtimeHeart());
```

- Data: arrives on cmd 30; parsing helper:
```dart
final parsed = QCBandSDK.ingestStandardNotification(value);
if (parsed['dataType'] == 'RealtimeHeartRate') {
  final data = parsed['data'] ?? parsed['Data'] ?? parsed['dicData'];
  final bpm = data['HeartRate'] ?? data['heartRate'];
}
```

- Legacy control (still available): `QCBandSDK.GetRealTimeHeartRate()` / `QCBandSDK.liveHeartData(action)`. Prefer 105‑based control.

### Auto HR setting (cmd 22)

```dart
// Read
await standardWriteCharacteristic.write(QCBandSDK.GetAutomaticHRMonitoring());

// Write (enable = 1 or 2, interval in minutes)
await standardWriteCharacteristic.write(QCBandSDK.SetAutomaticHRMonitoring(enable, interval));
```

### Notes

- Only one history request at a time per device is recommended.
- History output is normalized to a 5‑minute cadence day array of length 288.
- Timestamps are handled to match the app’s behavior (timezone/local midnight alignment).

## Historical Heart Rate (HR) Sync — Debug Notes and Fixes

### Overview
Your handler can drop or misalign data because of three main issues:
- It skips legitimate “0” BPM values and advances time incorrectly, which compresses the day and misaligns later values.
- It assumes fixed counts (9/13) for packets and rejects variable-sized packets.
- It dedupes using “index <= last”, which discards valid out-of-order packets.

Below are minimal, reliable fixes to apply without changing your overall architecture.

---

### Symptoms
- Gaps or “missing” HR readings for a day.
- Logs show packets arriving but few stored samples.
- Finalization happens mid-stream, cutting off data.

---

### Root Causes
- Filtering zeros removes 5-minute slots; later values shift earlier.
- Hardcoded “9 for first packet, 13 for others” fails if the device varies payload size.
- A single moving pointer (`_nextExpectedDataPointMinute`) ties parsing to arrival order; if packets arrive out of order, time mapping breaks.
- Dedupe by “index <= last” causes out-of-order packets to be dropped.

---

### Minimal Fixes (Checklist)
- Include zeros in the timeline (0 means “no measurement”, but still occupies 5 minutes).
- Learn the first packet’s point count from its payload; compute per-packet counts dynamically.
- Compute each packet’s start minute from its index and learned counts, not from arrival order.
- Dedupe with a Set of packet indices; allow out-of-order arrival.
- Extend completion wait or finalize when coverage reaches 24 hours.
- Add missing `Uint8List` import.

---

### Drop‑in Code Edits

#### 1) Add missing import
```dart
import 'dart:typed_data';
```

#### 2) Add fields for dedupe and learned counts
```dart
// Class fields
final Set<int> _seenPacketIndices = {};
int _firstPacketPoints = 9; // learned from the first data packet (pktIndex=1)
```

#### 3) Replace the “new format” handler (Command ID 21)
```dart
Future<void> _handleNewFormatPacket(List<int> data, String hex) async {
  final int packetIndex = (data.length >= 2) ? (data[1] & 0xFF) : -1;
  if (packetIndex < 0) {
    _log('❌ HR packet missing index: $hex');
    return;
  }

  if (_seenPacketIndices.contains(packetIndex)) {
    _log('↪️ Skipping already processed HR packet $packetIndex');
    return;
  }

  if (packetIndex == 0) {
    _log('📋 HR header received');
    return;
  }

  int dataStartIndex;
  if (packetIndex == 1) {
    // Optional UTC anchor (bytes 2..5)
    if (data.length >= 6) {
      final ts = (data[2] & 0xFF) |
                 ((data[3] & 0xFF) << 8) |
                 ((data[4] & 0xFF) << 16) |
                 ((data[5] & 0xFF) << 24);
      final baseLocal = DateTime.fromMillisecondsSinceEpoch(ts * 1000, isUtc: true).toLocal();
      _currentFinalizeTargetDate = DateTime(baseLocal.year, baseLocal.month, baseLocal.day);
      _log('🕐 HR base from device utcTime: ${_currentFinalizeTargetDate!.toIso8601String()}');
    }
    dataStartIndex = 6;
  } else {
    dataStartIndex = 2;
  }

  final int available = data.length - dataStartIndex;
  if (available <= 0) {
    _log('❌ HR packet $packetIndex has no data payload: $hex');
    return;
  }

  // Typical max is <= 13, but compute from payload
  final int pointsInThisPacket = available.clamp(0, 13);
  if (packetIndex == 1) _firstPacketPoints = pointsInThisPacket;

  // Derive start minute by index and learned counts
  final int pointsBefore = (packetIndex == 1)
      ? 0
      : _firstPacketPoints + (packetIndex - 2).clamp(0, 255) * 13;
  final int startMinute = pointsBefore * 5;

  _log('📈 HR pkt $packetIndex → startMinute=$startMinute, points=$pointsInThisPacket');

  for (int i = 0; i < pointsInThisPacket; i++) {
    final hrValue = data[dataStartIndex + i] & 0xFF;
    final int totalMinutes = startMinute + (i * 5);
    final int hour = (totalMinutes ~/ 60) % 24;
    final int minute = totalMinutes % 60;
    final String timeKey = '${hour.toString().padLeft(2, '0')}:${minute.toString().padLeft(2, '0')}';
    _accumulatedData[timeKey] = hrValue; // keep zeros to preserve alignment
    if (hrValue > 0) _log('💓 $timeKey: $hrValue BPM');
  }

  _seenPacketIndices.add(packetIndex);

  // Finalize if we reached full-day coverage
  if ((startMinute + pointsInThisPacket * 5) >= 24 * 60) {
    _log('🎉 Full-day coverage reached (${_accumulatedData.length} points)');
    await _finalizeHeartRateSession();
    final now = DateTime.now();
    final dateKey = '${now.year}-${now.month.toString().padLeft(2, '0')}-${now.day.toString().padLeft(2, '0')}';
    SyncDebugSummary.instance.markCompletion('hr', dateKey: dateKey);
  }
}
```

#### 4) Replace the legacy handler (keep zeros; approximate minute mapping)
```dart
Future<void> _handleLegacyFormatPacket(List<int> data, String hex) async {
  _log('💓 Legacy HR packet: $hex');
  if (data.length <= 2) return;

  final int packetIndex = (data.length >= 2) ? (data[1] & 0xFF) : -1;
  if (packetIndex >= 0 && _seenPacketIndices.contains(packetIndex)) {
    _log('↪️ Skipping already processed legacy HR packet $packetIndex');
    return;
  }

  const int dataStartIndex = 2;
  final int available = data.length - dataStartIndex;
  if (available <= 0) return;

  final int pointsInThisPacket = available.clamp(0, 13);

  // If legacy uses similar indexing, approximate with 13 points per packet
  final int startMinute = (packetIndex > 0)
      ? ((packetIndex - 1) * 13 * 5)
      : (_accumulatedData.length * 5); // fallback if no meaningful index

  for (int i = 0; i < pointsInThisPacket; i++) {
    final hrValue = data[dataStartIndex + i] & 0xFF; // store zeros too
    final int totalMinutes = startMinute + (i * 5);
    final int hour = (totalMinutes ~/ 60) % 24;
    final int minute = totalMinutes % 60;
    final String timeKey = '${hour.toString().padLeft(2, '0')}:${minute.toString().padLeft(2, '0')}';
    _accumulatedData[timeKey] = (hrValue <= 220 ? hrValue : 0);
    if (hrValue > 0) _log('📍 $timeKey → $hrValue BPM');
  }

  if (packetIndex >= 0) _seenPacketIndices.add(packetIndex);
  _log('📊 Total accumulated HR readings: ${_accumulatedData.length}');
}
```

#### 5) Safer completion wait (avoid mid-stream finalize)
```dart
Future<void> _waitForHeartRateCompletion() async {
  const int maxWaitSeconds = 20;
  const int checkIntervalMs = 500;
  int lastSize = -1;
  int stableMs = 0;

  for (int elapsed = 0; elapsed < maxWaitSeconds * 1000; elapsed += checkIntervalMs) {
    await Future.delayed(const Duration(milliseconds: checkIntervalMs));
    final int sizeNow = _accumulatedData.length;
    if (sizeNow == lastSize) {
      stableMs += checkIntervalMs;
      if (stableMs >= 4000) { // 4 seconds of stability
        _log('📊 HR data collection complete: ${_accumulatedData.length} readings');
        await _finalizeHeartRateSession();
        final now = DateTime.now();
        final dateKey = '${now.year}-${now.month.toString().padLeft(2, '0')}-${now.day.toString().padLeft(2, '0')}';
        SyncDebugSummary.instance.markCompletion('hr', dateKey: dateKey);
        return;
      }
    } else {
      stableMs = 0;
      lastSize = sizeNow;
    }
  }

  _log('⏰ HR data collection timeout reached, finalizing with ${_accumulatedData.length} readings');
  await _finalizeHeartRateSession();
  final now = DateTime.now();
  final dateKey = '${now.year}-${now.month.toString().padLeft(2, '0')}-${now.day.toString().padLeft(2, '0')}';
  SyncDebugSummary.instance.markTimeout('hr', dateKey: dateKey);
}
```

---

### Optional transport tweak
- If your device expects “write without response” on UART, change:
  - `await _bleManager.write(command, withoutResponse: false);`
  - to `await _bleManager.write(command, withoutResponse: true);`
- Keep notifications enabled on RX before writing (as you already do).

---

### Validation Tips
- Log per-packet: index, payload length, points derived, start minute.
- After a full sync, ensure there are at most 288 entries (24h × 12 per hour).
- Confirm zeros exist for expected gaps (e.g., early morning).
- Check that “today” doesn’t store future timestamps.

---

### TL;DR
- Keep zeros.
- Derive time from packet index, not arrival order.
- Learn counts dynamically.
- Dedupe by index with a Set.
- Wait a bit longer before finalizing.


