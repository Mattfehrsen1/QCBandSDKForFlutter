## Alarm integration guide (Flutter)

This guide explains how to use the classic alarm commands implemented in this SDK to set and read alarms on supported bands.

### What’s included
- Classic 16‑byte alarm commands (reliable across firmwares):
  - Set alarm (0x23 / 35)
  - Get alarm (0x24 / 36)
- Dart helpers for building commands and parsing responses
- Example UI wiring in the example app

Notes:
- Supported indexes: 0..4 (5 alarms).
- Time uses BCD encoding over BLE but is handled by helpers here.
- Repeat days are Sun..Sat (7 flags) as booleans.

### Dependencies
- Flutter Blue Plus in your app (or any BLE GATT client)
- This package as a dependency

```yaml
dependencies:
  flutter_blue_plus: ^1.31.17
  qc_band_sdk_for_flutter: ^<current-version>
```

### BLE characteristics
Use the Nordic UART service to write/read alarm commands:

- Service: `6e40fff0-b5a3-f393-e0a9-e50e24dcca9e`
- Write characteristic: `6e400002-b5a3-f393-e0a9-e50e24dcca9e`
- Notify characteristic: `6e400003-b5a3-f393-e0a9-e50e24dcca9e`

You can also import the constants:

```dart
import 'package:qc_band_sdk_for_flutter/utils/qc_band_sdk_const.dart';
// QcBandSdkConst.uuidService, uuidWrite, uuidRead
```

### API overview

```dart
import 'package:qc_band_sdk_for_flutter/qc_band_sdk_for_flutter.dart';
import 'package:qc_band_sdk_for_flutter/bean/models/alarm.dart';

// Build classic commands (16 bytes with sum-CRC)
Uint8List QCBandSDK.buildSetAlarmClassic(Alarm alarm);
Uint8List QCBandSDK.buildGetAlarmClassic(int index);

// Parse an incoming device notification (returns a Map)
Map QCBandSDK.DataParsingWithData(List<int> value); // routes to ResolveUtil.getClockData for opcode 36
```

Alarm model:

```dart
class Alarm {
  final int index;          // 0..4
  final bool enabled;       // true/false
  final int hour;           // 0..23
  final int minute;         // 0..59
  final List<bool> repeatDays; // length 7: Sun..Sat
  final int? type;          // reserved for vendor path
  final String? name;       // reserved for vendor path
}
```

### Quick start: set an alarm

```dart
// 1) Ensure you discovered services and obtained the UART write/notify characteristics
final BluetoothCharacteristic writeChar = ...;  // 6e400002-...
final BluetoothCharacteristic notifyChar = ...; // 6e400003-...
await notifyChar.setNotifyValue(true);

// 2) Build and write the command
final alarm = Alarm(
  index: 0,
  enabled: true,
  hour: 7,
  minute: 30,
  // Sun..Sat flags: Su, Mo, Tu, We, Th, Fr, Sa
  repeatDays: [false, true, true, true, true, true, false],
);
final cmd = QCBandSDK.buildSetAlarmClassic(alarm);
await writeChar.write(cmd);
```

### Quick start: get an alarm

```dart
// Listen for a single response for index 0
final completer = Completer<Map<String, dynamic>>();
late final StreamSubscription<List<int>> sub;
sub = notifyChar.value.listen((value) {
  if (value.isNotEmpty && value[0] == QcBandSdkConst.cmdGetAlarmClockInt && value[1] == 0) {
    final parsed = QCBandSDK.DataParsingWithData(value);
    completer.complete(Map<String, dynamic>.from(parsed));
    sub.cancel();
  }
});

// Send the read request for index 0
await writeChar.write(QCBandSDK.buildGetAlarmClassic(0));

// Wait up to 3 seconds for the response
final result = await completer.future.timeout(const Duration(seconds: 3), onTimeout: () => {});
// result shape:
// {
//   'dataType': 'GetAlarmClock',
//   'end': true,
//   'data': {
//     'index': 0,
//     'enabled': true,
//     'hour': 7,
//     'minute': 30,
//     'days': [false,true,true,true,true,true,false],
//     'raw': [ ...original bytes... ]
//   }
// }
```

### Reading all alarms (0..4)

```dart
final List<Map<String, dynamic>> alarms = [];
for (int i = 0; i <= 4; i++) {
  final completer = Completer<Map<String, dynamic>>();
  late final StreamSubscription<List<int>> sub;
  sub = notifyChar.value.listen((value) {
    if (value.isNotEmpty && value[0] == QcBandSdkConst.cmdGetAlarmClockInt && value[1] == i) {
      final parsed = QCBandSDK.DataParsingWithData(value);
      completer.complete(Map<String, dynamic>.from(parsed));
      sub.cancel();
    }
  });
  await writeChar.write(QCBandSDK.buildGetAlarmClassic(i));
  final r = await completer.future.timeout(const Duration(seconds: 3), onTimeout: () => {});
  if (r.isNotEmpty) alarms.add(r);
  await Future.delayed(const Duration(milliseconds: 150));
}
```

### Day-of-week mapping
- repeatDays[0] = Sunday
- repeatDays[1] = Monday
- ...
- repeatDays[6] = Saturday

Example: Mon–Fri only → `[false, true, true, true, true, true, false]`

### Troubleshooting
- No response to Get Alarm:
  - Ensure notifications are enabled on `6e400003-...` before writing
  - Keep a small delay between sequential reads (e.g., 150 ms)
  - Verify the device supports classic alarms and you are connected to the correct UART service
- Times seem wrong:
  - Hours/minutes must be integers in 24h format (0..23 / 0..59)
- Index out of range:
  - Only 0..4 are supported by classic path

### Notes on future enhancements
Some firmwares support a vendor channel (0xBC) bulk alarm interface with names and types. The current classic path is prioritized for reliability. A vendor-based bulk get/set may be added later once verified across devices.


