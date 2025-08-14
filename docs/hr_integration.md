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


