## DeviceScreen Sleep Implementation (reference)

Path: `example/lib/screens/device_screen.dart`

### Overview
This document collects the sleep-related code from `DeviceScreen` so you can reuse it in other projects. It covers:
- Imports used for sleep
- Per-day sleep requests over the vendor Serial service (0xBC/0x27)
- N-day batch helpers
- Per-day fetch + parse via `SleepParser`
- Pretty summary helpers
- Packet validation
- UI hooks (buttons/controls)

---

### Imports
```dart
import 'package:qc_band_sdk_for_flutter/qc_band_sdk_for_flutter.dart';
import 'package:qc_band_sdk_for_flutter/bean/models/sleepModel.dart';
import 'package:qc_band_sdk_for_flutter/utils/resolve_util.dart';
```

---

### Vendor sleep request (chunked path)
```dart
Future<void> _sleepRequestForOffset(int offset) async {
  try {
    print('[SleepUI] Requesting sleep for offset=$offset (0=today).');
    await _secondbluetoothCharacteristicWrite
        .write(QCBandSDK.getSleepDetailForOffset(offset));
  } catch (_) {
    await _bluetoothCharacteristicWrite
        .write(QCBandSDK.getSleepDetailForOffset(offset));
  }

  final subV = _secondbluetoothCharacteristicNotification.value.listen((value) {
    if (value.isEmpty) return;
    if (value[0] == 0xBC) {
      final cmd = value.length > 1 ? value[1] : -1;
      final total = value.length > 3 ? ((value[2] & 0xFF) | ((value[3] & 0xFF) << 8)) : -1;
      final chunk = value.length >= 6 ? (value.length - 6) : value.length;
      print('[SleepUI] Vendor frame: cmd=0x${cmd.toRadixString(16)}, expectedTotalBytes=$total, chunkBytes=$chunk');
      QCBandSDK.ingestVendorNotification(value);
    } else if (_sleepBusy) {
      QCBandSDK.ingestVendorNotification(value);
    }
  });
  final subS = _bluetoothCharacteristicNotification.value.listen((value) {
    if (value.isEmpty) return;
    if (value[0] == 0xBC || _sleepBusy) {
      final cmd = value.length > 1 ? value[1] : -1;
      print('[SleepUI] Standard notify carrying vendor bytes. len=${value.length}, cmd=0x${cmd.toRadixString(16)}');
      QCBandSDK.ingestVendorNotification(value);
    }
  });

  QCBandSDK.setSleepListener((m) {
    try {
      final data = m['data'] as Map? ?? {};
      final date = data['date'];
      final totals = data['totals'] as Map? ?? {};
      final segLen = (data['segments'] as List?)?.length ?? 0;
      print('[SleepUI] Done $date → segments=$segLen, deep=${totals['deep']}, light=${totals['light']}, rem=${totals['rem']}, awake=${totals['awake']}');
    } catch (_) {}
  });

  await Future.any([
    Future.delayed(const Duration(seconds: 9)),
  ]);

  await subV.cancel();
  await subS.cancel();
}
```

---

### Batch fetch (N days)
```dart
Future<void> _getSleepNDays(int days) async {
  if (_sleepBusy) return;
  setState(() => _sleepBusy = true);
  try {
    for (int offset = 0; offset < days; offset++) {
      // Use the same classic per-day path as the working "Get Sleep Data" flow
      await _fetchAndDisplaySleepData(offset);
      await Future.delayed(const Duration(milliseconds: 300));
    }
  } finally {
    if (mounted) setState(() => _sleepBusy = false);
  }
}
```

---

### Get sleep (all days helper)
```dart
Future<void> getSleepDataForAllDays() async {
  print("🌙 Fetching sleep data for all available days...");
  for (int dayIndex = 0; dayIndex < 7; dayIndex++) {
    await _fetchAndDisplaySleepData(dayIndex);
    await Future.delayed(const Duration(milliseconds: 300));
  }
  print("✅ Sleep data collection complete!");
}
```

---

### Per‑day fetch + parse + display
```dart
Future<void> _fetchAndDisplaySleepData(int dayIndex) async {
  try {
    List<int> sleepData = await _requestSleepDataFromDevice(dayIndex);
    if (sleepData.isNotEmpty && sleepData.length >= 13) {
      final parser = SleepParser(sleepData, currentIndex: dayIndex);
      final summary = parser.getSleepSummaryWithTimestamps();
      _displaySleepSummary(dayIndex, summary);
    } else {
      print("Day $dayIndex: No sleep data available");
    }
  } catch (e) {
    print("Error fetching sleep data for day $dayIndex: $e");
  }
}
```

```dart
void _displaySleepSummary(int dayIndex, SleepSummary summary) {
  String dayLabel = dayIndex == 0 ? "Today" : "${dayIndex} day${dayIndex > 1 ? 's' : ''} ago";
  print("\n📊 Sleep Summary - $dayLabel (Day $dayIndex):");
  if (summary.bedTime != null && summary.wakeTime != null) {
    print("  🛏️  Bed Time: ${_formatTime(summary.bedTime!)}");
    print("  🌅 Wake Time: ${_formatTime(summary.wakeTime!)}");
    print("  ⏱️  Total Sleep: ${summary.durations['totalDuration']} minutes");
    print("\n  📈 Sleep Stage Breakdown:");
    print("    💤 Deep Sleep: ${summary.durations['deepSleep']} min");
    print("    😴 Light Sleep: ${summary.durations['lightSleep']} min");
    print("    👁️  REM Sleep: ${summary.durations['rapidEyeMovement']} min");
    print("    😵 Awake: ${summary.durations['awake']} min");
    print("  🔄 Total Segments: ${summary.segments.length}");
    if (summary.segments.isNotEmpty) {
      print("\n  📊 Sleep Timeline (first 5 segments):");
      int maxSegments = summary.segments.length > 5 ? 5 : summary.segments.length;
      for (int i = 0; i < maxSegments; i++) {
        final segment = summary.segments[i];
        String stageType = _getSleepStageDisplayName(segment.stageType);
        int duration = segment.segmentEnd.difference(segment.segmentStart).inMinutes;
        print("    ${i + 1}. ${_formatTime(segment.segmentStart)} → ${_formatTime(segment.segmentEnd)} ($duration min) - $stageType");
      }
      if (summary.segments.length > 5) {
        print("    ... and ${summary.segments.length - 5} more segments");
      }
    }
  } else {
    print("  ❌ No valid sleep times available");
  }
  print("  ${'-' * 50}");
}
```

```dart
String _formatTime(DateTime time) {
  return "${time.hour.toString().padLeft(2, '0')}:${time.minute.toString().padLeft(2, '0')}";
}

String _getSleepStageDisplayName(int stageType) {
  switch (stageType) {
    case 1: return "Deep Sleep";
    case 2: return "Light Sleep"; 
    case 3: return "Awake";
    case 4: return "REM Sleep";
    case 5: return "Unknown";
    default: return "Unknown";
  }
}
```

---

### Per‑day device request (vendor 0xBC/0x27 on Serial notify)
```dart
Future<List<int>> _requestSleepDataFromDevice(int dayIndex) async {
  final completer = Completer<List<int>>();
  List<int>? receivedData;
  Timer? timeoutTimer;

  final subscription = _secondbluetoothCharacteristicNotification.value.listen((value) {
    if (value.isNotEmpty && value[1] == QcBandSdkConst.getSleepData) {
      if (_isValidSleepDataPacket(value)) {
        receivedData = List<int>.from(value);
        timeoutTimer?.cancel();
        timeoutTimer = Timer(const Duration(milliseconds: 200), () {
          if (!completer.isCompleted && receivedData != null) {
            completer.complete(receivedData!);
          }
        });
      }
    }
  });

  await _secondbluetoothCharacteristicWrite.write(QCBandSDK.getSleepData(dayIndex));

  final response = await completer.future.timeout(
    const Duration(seconds: 5),
    onTimeout: () {
      timeoutTimer?.cancel();
      return receivedData ?? [];
    },
  );

  subscription.cancel();
  timeoutTimer?.cancel();
  return response;
}
```

```dart
bool _isValidSleepDataPacket(List<int> data) {
  return data.length >= 10 && 
         data[0] == 188 && 
         (data[1] == 39 || data[1] == 68);
}
```

---

### UI hooks (sleep controls)
```dart
// In the build method
Padding(
  padding: const EdgeInsets.symmetric(horizontal: 12.0, vertical: 4.0),
  child: Row(
    children: [
      const Text('Sleep days:'),
      const SizedBox(width: 8),
      DropdownButton<int>(
        value: _sleepDays,
        items: const [
          DropdownMenuItem(value: 1, child: Text('1')),
          DropdownMenuItem(value: 2, child: Text('2')),
          DropdownMenuItem(value: 3, child: Text('3')),
          DropdownMenuItem(value: 4, child: Text('4')),
          DropdownMenuItem(value: 5, child: Text('5')),
          DropdownMenuItem(value: 6, child: Text('6')),
          DropdownMenuItem(value: 7, child: Text('7')),
        ],
        onChanged: (v) => setState(() => _sleepDays = v ?? 1),
      ),
      const SizedBox(width: 12),
      ElevatedButton(
        onPressed: _sleepBusy ? null : () => _getSleepNDays(_sleepDays),
        child: Text(_sleepBusy ? 'Fetching…' : 'Get Sleep ($_sleepDays d)')),
    ],
  ),
),

TextButton(
  onPressed: () {
    getSleepDataForAllDays();
  },
  child: Text('Get Sleep Data (All Days)'),
),
```

---

### Notes
- Requests are sent over the Serial Port service: notify `de5bf729-d711-4e47-af26-65e3012a5dc7`, write `de5bf72a-d711-4e47-af26-65e3012a5dc7`.
- Coalescing delay (200ms) ensures the complete vendor frame is captured before parsing.
- Parsing uses `SleepParser(sleepData, currentIndex: dayIndex).getSleepSummaryWithTimestamps()`.


