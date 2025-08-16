## Alarm Feature: Protocol, SDK APIs, and Example UI

This document explains exactly how the alarm feature works in this SDK and example app, covering the classic UART commands, the recommended vendor (0xBC) protocol, the BLE characteristics used, and how to test/troubleshoot.

### Overview

- **Recommended path**: Vendor serial protocol `0xBC 0x2C` for alarms.
- **Fallback path**: Classic UART `0x23` (set) / `0x24` (get). Some firmware revisions accept classic frames but do not persist them reliably, so use vendor path.
- **DND/Night mode**: If enabled on the band, it can suppress alarm vibration. Always verify DND state.
- **Time sync**: Set device time first before setting alarms, to avoid writing an alarm time that falls in the past from the device perspective.

### BLE Services and Characteristics

- **Vendor Serial Port (for alarms/DND/sleep/etc):**
  - Service: `de5bf728-d711-4e47-af26-65e3012a5dc7`
  - Write: `de5bf72a-d711-4e47-af26-65e3012a5dc7`
  - Notify: `de5bf729-d711-4e47-af26-65e3012a5dc7`

- **Nordic UART (classic, legacy commands):**
  - Service: `6e40fff0-b5a3-f393-e0a9-e50e24dcca9e`
  - Write: `6e400002-b5a3-f393-e0a9-e50e24dcca9e`
  - Notify: `6e400003-b5a3-f393-e0a9-e50e24dcca9e`

### Protocols

#### Classic UART (legacy)

- Set alarm (`cmd=0x23` / 35): 16-byte frame with BCD hour/min, 7 repeat flags (Su..Sa), and CRC=8-bit sum in the last byte.
- Get alarm (`cmd=0x24` / 36): 16-byte frame 

This path is implemented in the SDK but NOT recommended as primary on newer firmware.

#### Vendor Serial (recommended)

- Command: `0xBC 0x2C`
- Read payload: `[0x01]` → device responds with `[readFlag=0x01][total][alarm...]...`
- Write payloads:
  - Write one or more alarms: `[0x02][total][alarm1][alarm2]...` where each alarm block is:
    - `[len][repeatAndEnable][minLo][minHi][content...]`
    - `len = 4 + content.length`
    - `repeatAndEnable`: bit7 = enabled flag, bits0..6 = weekday repeat mask (Su..Sa)
    - `minutes since midnight = (minHi<<8 | minLo)`

Example vendor read (hex):

```
bc 2c 01 00 7e 80 01          # request
bc 2c 0b 00 .. .. 01 01 09 ff 06 04 41 6c 61 72 6d  # response payload
```

The example response contains 1 alarm, enabled, at 9:255 (just an example), name "Alarm".

### SDK APIs

File: `lib/qc_band_sdk_for_flutter.dart`

- Vendor packets utility: `buildVendorPacket(cmd, [payload])`
- Alarms (vendor):
  - `buildVendorAlarmRead()` → build `0xBC 0x2C` read.
  - `buildVendorAlarmWriteSingle(minutesSinceMidnight, enabled: true, repeatDays: [...], content: 'Alarm')`
  - `buildVendorAlarmWriteMany([{ minutesSinceMidnight, enabled, repeatDays, content }, ...])`
  - `buildVendorAlarmClear()` → remove all alarms (total=0)
  - `parseVendorAlarmPayload(Uint8List payload)` → returns list of `{minutes, enabled, repeatDays, content}`

- DND (for reference): `0xBC 0x06` with payload `[0x01]` returns DND state and window.

- Classic (legacy):
  - `buildSetAlarmClassic(Alarm alarm)` (cmd 35)
  - `buildGetAlarmClassic(int index)` (cmd 36)

### Example UI Hooks

File: `example/lib/screens/device_screen.dart`

- Quick test buttons (vendor path):
  - “Quick Alarm +120s (Slot 1/2)” → sets a vendor alarm ~2 minutes in the future after a time sync and DND check.

- Vendor alarm management UI:
  - “Refresh Vendor Alarms” → sends `buildVendorAlarmRead`, parses with `parseVendorAlarmPayload`, and lists alarms.
  - “Add Vendor Alarm” → uses the selected TimeOfDay and repeat days, writes via `buildVendorAlarmWriteMany`, then refreshes.
  - “Clear All Vendor Alarms” → sends `buildVendorAlarmClear`, then refreshes.

These use the Serial Port characteristic (write to `de5bf72a-...`, listen on `de5bf729-...`).

### Usage Example (code snippets)

Read alarms:

```dart
final req = QCBandSDK.buildVendorAlarmRead();
await _secondbluetoothCharacteristicWrite.write(req);
// On notify:
final payload = value.sublist(6, 6 + totalLen);
final list = QCBandSDK.parseVendorAlarmPayload(Uint8List.fromList(payload));
```

Write one alarm (daily repeat, named):

```dart
final minutes = hour * 60 + minute;
final pkt = QCBandSDK.buildVendorAlarmWriteSingle(
  minutes,
  enabled: true,
  repeatDays: const [true, true, true, true, true, true, true],
  content: 'Alarm',
);
await _secondbluetoothCharacteristicWrite.write(pkt);
```

Clear all:

```dart
await _secondbluetoothCharacteristicWrite.write(
  QCBandSDK.buildVendorAlarmClear(),
);
```

### Notes & Troubleshooting

- **DND/Night mode**: If alarms don’t vibrate, read `0xBC 0x06` and ensure it’s disabled.
- **Time sync**: Call `QCBandSDK.setDeviceTime(0)` over the classic UART write char before writing alarms.
- **Characteristic routing**: Vendor alarms must use the Serial Port service; classic UART won’t affect vendor-stored alarms on newer firmware.
- **Parsing**: On vendor read, the first payload byte is `1` (read flag), second is `total`. Each alarm block begins with `len`.

### Testing Steps

1. Connect device and enable notifications for both Serial Port and Nordic UART.
2. Tap “Set Time” (or the quick alarm buttons, which also perform time sync).
3. Tap “Quick Alarm +120s” to set an alarm ~2 minutes from now.
4. Tap “Refresh Vendor Alarms” to verify it appears with ON, correct time, and repeat mask.
5. Wait for alarm time and confirm band vibration.
6. Optionally clear all and re-add using the “Add Vendor Alarm” control.


