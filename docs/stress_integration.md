## Stress (pressure) integration guide (Flutter)

This guide shows, in simple steps, how to read multi-day stress data and how to read/write the auto-stress switch using this SDK.

### What’s included

- Classic 16‑byte UART protocol (reliable across firmwares)
  - Read stress for a given day offset (0 = today, 1..6 = past days)
  - Read/write the auto-stress setting
- High-level helpers to build requests and parse responses
- End-to-end examples you can copy/paste

### BLE characteristics

Use Nordic UART (NUS):

- Service: `6e40fff0-b5a3-f393-e0a9-e50e24dcca9e`
- Write characteristic: `6e400002-b5a3-f393-e0a9-e50e24dcca9e`
- Notify characteristic: `6e400003-b5a3-f393-e0a9-e50e24dcca9e`

Import constants if desired:

```dart
import 'package:qc_band_sdk_for_flutter/utils/qc_band_sdk_const.dart';
// QcBandSdkConst.uuidService, uuidWrite, uuidRead
```

### API overview

```dart
import 'package:qc_band_sdk_for_flutter/qc_band_sdk_for_flutter.dart';
import 'package:qc_band_sdk_for_flutter/utils/resolve_util.dart';

// Build classic 16B commands (CRC added for you)
Uint8List QCBandSDK.getStressByOffset(int offset);       // cmd 55, offset 0..6
Uint8List QCBandSDK.getStressSetting();                  // cmd 54 (read)
Uint8List QCBandSDK.setStressSetting(bool enable);       // cmd 54 (write)

// Parse per-frame responses (stateless, easy to stream-accumulate)
// Returns a Map with:
//  - dataType: '55'
//  - end: bool
//  - data: { frame: 'header'|'first'|'next', ...fields }
Map<String, dynamic> ResolveUtil.parsePressureDataFrames(List<int> bytes);
```

Frame shapes you will see over notify (all are 16 bytes on-wire, last byte is CRC):

- Header: `[55, 0, size, interval, ..., crc]`
  - size: number of data frames that follow (typically 5)
  - interval: minutes per point (e.g., 30)
- First data frame: `[55, 1, offsetDays, payload..., crc]`
  - offsetDays: 0 for today, 1 for yesterday, etc.
  - payload: stress values for the first chunk
- Next frames: `[55, n(2..size-1), payload..., crc]`

Note: The parser strips CRC automatically and returns only the useful payload bytes.

### Quick start: get today’s stress

```dart
// Ensure notify is enabled first
await notifyChar.setNotifyValue(true);

// Build and send (today = offset 0)
final cmd = QCBandSDK.getStressByOffset(0);
await writeChar.write(cmd);

// Accumulate frames for this day
int expectedFrames = -1;
int intervalMinutes = 0;
final List<int> stressValues = [];

late final StreamSubscription<List<int>> sub;
sub = notifyChar.value.listen((bytes) {
  final parsed = ResolveUtil.parsePressureDataFrames(bytes);
  if (parsed['dataType'] != QcBandSdkConst.cmdPressure) return;

  final isEnd = parsed['end'] == true;
  final data = parsed['data'] as Map?;
  if (data == null) return;
  final frame = data['frame'];

  if (frame == 'header') {
    expectedFrames = (data['size'] ?? -1) as int;
    intervalMinutes = (data['intervalMinutes'] ?? 0) as int;
  } else if (frame == 'first' || frame == 'next') {
    final List<int> pl = (data['payload'] as List).cast<int>();
    stressValues.addAll(pl);
  }

  // Complete on end marker or after last indexed frame
  if (isEnd || (frame == 'next' && expectedFrames > 0 && (data['index'] == expectedFrames - 1))) {
    sub.cancel();
    debugPrint('Stress today: ${stressValues.length} samples @ ${intervalMinutes}m');
  }
});
```

### Get N days (1..7)

```dart
Future<void> getStressNDays({required BluetoothCharacteristic writeChar, required BluetoothCharacteristic notifyChar, int days = 3}) async {
  for (int offset = 0; offset < days; offset++) {
    final cmd = QCBandSDK.getStressByOffset(offset);
    int expected = -1;
    int interval = 0;
    final values = <int>[];
    final completer = Completer<void>();

    late final StreamSubscription<List<int>> sub;
    sub = notifyChar.value.listen((bytes) {
      final p = ResolveUtil.parsePressureDataFrames(bytes);
      if (p['dataType'] != QcBandSdkConst.cmdPressure) return;
      final data = p['data'] as Map?;
      if (data == null) return;
      final f = data['frame'];
      if (f == 'header') {
        expected = (data['size'] ?? -1) as int;
        interval = (data['intervalMinutes'] ?? 0) as int;
      } else if (f == 'first' || f == 'next') {
        values.addAll((data['payload'] as List).cast<int>());
        if (f == 'next' && expected > 0 && data['index'] == expected - 1) {
          sub.cancel();
          completer.complete();
        }
      }
      if (p['end'] == true) {
        sub.cancel();
        completer.complete();
      }
    });

    await writeChar.write(cmd);
    await completer.future.timeout(const Duration(seconds: 8), onTimeout: () { sub.cancel(); });

    final date = DateTime.now().subtract(Duration(days: offset));
    debugPrint('Stress ${date.toIso8601String().substring(0,10)}: ${values.length} samples @ ${interval}m');
    await Future.delayed(const Duration(milliseconds: 200));
  }
}
```

### Read/write auto-stress setting

```dart
// Read
await notifyChar.setNotifyValue(true);
late final StreamSubscription<List<int>> subR;
final readDone = Completer<bool>();
subR = notifyChar.value.listen((bytes) {
  final parsed = QCBandSDK.DataParsingWithData(bytes);
  if (parsed['dataType'] == QcBandSdkConst.cmdPressureSetting) {
    final data = parsed['data'] as Map?;
    final enabled = data?['enabled'] == true;
    debugPrint('Auto-stress enabled: $enabled');
    subR.cancel();
    readDone.complete(true);
  }
});
await writeChar.write(QCBandSDK.getStressSetting());
await readDone.future.timeout(const Duration(seconds: 3), onTimeout: () { subR.cancel(); });

// Write
await writeChar.write(QCBandSDK.setStressSetting(true));
```

### Understanding the values

- Each payload byte is a stress score (0..100)
- `interval` tells you spacing between samples (minutes)
- You can compute min/avg/max easily:

```dart
int minVal = values.isEmpty ? 0 : values.reduce((a,b) => a < b ? a : b);
int maxVal = values.isEmpty ? 0 : values.reduce((a,b) => a > b ? a : b);
double avg = values.isEmpty ? 0 : values.reduce((a,b) => a + b) / values.length;
```

### Troubleshooting

- No frames? Ensure notify is enabled on `6e400003-...` before writing.
- Only header arrives? Wait for `size-1` frames or the `end` flag.
- Unexpected bytes? Log the raw hex and use `ResolveUtil.parsePressureDataFrames` to see `frame` and `payload` parts.
- Slow/partial days? Add small delays (150–250ms) between day requests.

### Notes

- Protocol verified on UART path; some firmwares may also expose vendor channels. This guide focuses on the reliable UART classic path.
- The parser intentionally stays stateless so you can build your own accumulator (as shown above) or store chunks progressively.


