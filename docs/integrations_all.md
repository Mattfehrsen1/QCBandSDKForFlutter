## QCBand SDK – Integrations Guide (All-in-One)

This document consolidates the integration notes from all domain docs (sleep, HR/HRV, SpO2, stress, alarms, sport+, etc.) into a single reference for app developers.

### Contents
- Overview and setup
- Device feature support and init
- Core transports and packet formats
- Steps and activity
- Sleep (vendor large-data)
- Heart rate (history + realtime)
- HRV (history)
- SpO2 (classic history + vendor trend)
- Stress (pressure) history and setting
- Alarms (set/get)
- Sport: on-device and phone-controlled; Sport+ history
- Common error handling and logging

---

### Overview and setup
- Request MTU ~223 after connect.
- Enable notifications on both standard UART and vendor serial.
- Classic commands are fixed 16 bytes with 8-bit sum CRC in the last byte.
- Vendor packets (0xBC) include 2-byte length and 2-byte CRC16 of payload followed by chunked data.

In code:
```dart
// Standard UART UUIDs (FlutterBluePlus):
QcBandSdkConst.uuidService
QcBandSdkConst.uuidRead
QcBandSdkConst.uuidWrite

// Vendor Serial UUIDs:
QcBandSdkConst.serialPortService
QcBandSdkConst.serialPortNotify
QcBandSdkConst.serialPortWrite
```

---

### Device feature support and init
- Read device function support early to know which domains are available (SpO2, BP, temperature, manual HR, etc.).
- Then sequence syncs (gated): steps → sleep → HRV → heart → SpO2 → temp → sport+

```dart
// Request feature flags (classic cmd 60)
final cmd = QCBandSDK.getDeviceFunctionSupport();
// Parse via QCBandSDK.ingestStandardNotification(value)
```

---

### Core transports and packet formats
- Classic (16 bytes): first byte = command id; optional sub-data; last byte = sum CRC.
- Vendor (0xBC): `[0xBC][cmd][lenLo][lenHi][crcLo][crcHi][payload...]` with chunking.

Helpers:
```dart
// Build vendor packet:
QCBandSDK.buildVendorPacket(cmd, payload);

// Route standard notifications:
QCBandSDK.ingestStandardNotification(value);

// Route vendor notifications (sleep, sport+, spo2 trend):
QCBandSDK.ingestVendorNotification(value);
```

---

### Steps and activity
- Today summary: classic `cmdStepDataToday (72)`.
- Details: classic `cmdStepDataDetails (67)` with dayOffset and index ranges.

```dart
QCBandSDK.GetStepOfToday();
QCBandSDK.generateReadStepDetailsCommand(dayOffset, startIndex, endIndex);
// Parse via getStepToday(...) for today; details are app-level accumulation.
```

---

### Sleep (vendor large-data)
- Vendor 0xBC commands (0x39 or 0x27) stream sleep detail by day offset (0=today..29).
- We assemble chunks internally and then parse to segments and totals.

```dart
// Request sleep by offset
final req = QCBandSDK.getSleepDetailForOffset(offsetDays);
// Write to vendor serial write char; enable notify on vendor serial notify.
// Assembled payload is parsed and returned via internal callback set with setSleepListener(...).
```

Output includes ISO timestamps for segments and stage mapping (deep/light/REM/awake).

---

### Heart rate (history + realtime)
- History: classic `cmdReadHrData (21)` multi-frame flow; parsed and normalized to arrays.

```dart
QCBandSDK.buildReadHeartRateCommand(unixTimestamp);
// or date-based helper: QCBandSDK.getHRate("yyyy-MM-dd HH:mm:ss");
// Route via QCBandSDK.ingestStandardNotification; parser: ResolveUtil.handleIncomingDataHeartData
```

- Realtime HR: `cmdGetRealTimeHeartRate (30)` or preferred `cmdStartHeartRateInt (105 type=6)` for start/stop.
```dart
QCBandSDK.realtimeHeartControl(QcBandSdkConst.ACTION_START);
QCBandSDK.realtimeHeartControl(QcBandSdkConst.ACTION_STOP);
```

---

### HRV (history)
- Classic multi-frame: `cmdHrv (57)`. Header announces frame count/interval; subsequent frames carry payload.
- SDK assembles and normalizes to a 288-point (5-minute grid) daily array.

```dart
QCBandSDK.getHrvByOffset(offsetDays); // 0..6
// Route via QCBandSDK.ingestStandardNotification; parser: ResolveUtil.handleIncomingDataHrv
```

---

### SpO2 (blood oxygen)
Supports two paths: classic history (timestamped samples) and vendor trend (daily 24×min/max arrays).

1) Classic history (16-byte classic cmd 42):
```dart
// Builders
QCBandSDK.getSpO2HistoryLatest();   // mode 0
QCBandSDK.getSpO2HistoryContinue(); // mode 2
QCBandSDK.deleteSpO2History();      // mode 99
QCBandSDK.getSpO2HistoryFrom("yyyy-MM-dd HH:mm:ss");

// Routing
// Route standard notify through QCBandSDK.ingestStandardNotification
// Parser: ResolveUtil.parseSpO2Classic(List<int>)
// Returns: { dataType: 'SpO2Classic', end: bool, data: { samples: [ { timestamp, spo2 }, ... ] } }
```

2) Vendor trend (0xBC cmd 0x2A):
```dart
// Request via vendor serial write
final vendorReq = QCBandSDK.getBloodOxygen(); // sends 0xBC/0x2A with 0xFF payload
// Route vendor notify through QCBandSDK.ingestVendorNotification
// Assembled payload parsed by ResolveUtil.parseSpO2VendorPayload(Uint8List)
// Returns: { dataType: 'SpO2Trend', end: true, data: { days: [ { date, min[24], max[24] }, ... ] } }
```

3) Auto SpO2 setting (classic cmd 44):
```dart
// Read current auto SpO2 state
QCBandSDK.getAutoSpO2Setting(); // [44, 1, ...]

// Write enable/interval (minutes)
QCBandSDK.setAutoSpO2Setting(enable: true, intervalMinutes: 15); // [44, 2, 1, 15, ...]

// Routing & parser
// Route via QCBandSDK.ingestStandardNotification; parser: ResolveUtil.parseAutoBloodOxygenSetting
// Returns: { enabled: bool, intervalMinutes: int?, mode: byte }
```

Verification tips:
- Classic: request latest, then continue until `end=true`. Log total samples and last timestamp.
- Vendor: log per-day 24×min/max arrays; ensure total bytes match reported length.

---

### Stress (pressure)
- History: classic multi-frame `cmdPressureInt (55)`; header (0), first data (1, with offset), then subsequent frames; ends with 0xFF.
- Auto-stress setting: `cmdPressureSettingInt (54)` read/write.

```dart
QCBandSDK.getStressByOffset(offset);          // history
QCBandSDK.getStressSetting();                  // read auto setting
QCBandSDK.setStressSetting(enable: true/false);
// Parsers: ResolveUtil.parsePressureDataFrames, ResolveUtil.parsePressureSetting
```

---

### Alarms
- Classic set/get: `cmdSetAlarmClockInt (35)`, `cmdGetAlarmClockInt (36)`.

```dart
QCBandSDK.buildSetAlarmClassic(Alarm alarm);
QCBandSDK.buildGetAlarmClassic(index);
// Parse get: ResolveUtil.getClockData
```

---

### Sport (on-device and phone-controlled)
1) On-device sport (vendor 0xBC cmd 0x40):
```dart
QCBandSDK.startOnDeviceSport(sportType);
QCBandSDK.pauseOnDeviceSport(sportType);
QCBandSDK.continueOnDeviceSport(sportType);
QCBandSDK.stopOnDeviceSport(sportType);
```

2) Phone-controlled sport (classic 0x77):
```dart
QCBandSDK.phoneSportStart(sportType);
QCBandSDK.phoneSportPause(sportType);
QCBandSDK.phoneSportContinue(sportType);
QCBandSDK.phoneSportPreStop(sportType);
QCBandSDK.phoneSportStop(sportType);
```

3) Live notifications during phone sport (cmd 0x78)
```dart
// Route via QCBandSDK.ingestStandardNotification
// Parser: ResolveUtil.parseLiveSportNotify
```

4) Sport+ history over vendor channel:
- Summary: request 0x41, receive 0x42 (assembled); details: request 0x43, receive 0x44 meta + 0x45 data.

```dart
QCBandSDK.getSportPlusSummaryRequest(unixTs, onSummaries);
QCBandSDK.getSportPlusDetailsRequest(sportType, startTime, onDetails);
// Parsers: ResolveUtil.parseSportPlusSummaryBuffer, parseSportPlusDetailsMeta, extractHrSeriesFromDetails
```

---

### Common error handling and logging
- Classic `end=true` is often signalled by last byte 0xFF; multi-frame flows may also use explicit last index.
- Vendor frames: always verify total vs received; CRC16 is calculated on payload.
- Add small backoff/retry on stalled flows; use timeouts (~8–10s) and print friendly logs.

Example log patterns used by the SDK/UI:
```text
[UI] Starting SpO2 history sync (classic)...
[SpO2 Classic] Parsed N samples in this packet. Last time: .... End of history: true/false
[SpO2 Trend] Receiving vendor trend data... X/Y bytes (Z%)
[UI] SpO2 trend done. Number of days received: D
```

---

### Quick integration checklist
- [ ] Connect, negotiate MTU ~223, enable standard + vendor notifications
- [ ] Read function support (cmd 60) and gate flows accordingly
- [ ] Steps today/details
- [ ] Sleep by offset (vendor 0x27/0x39) – assemble → parse → store
- [ ] HR history (21), HRV (57)
- [ ] SpO2 classic (42) and vendor trend (0xBC/0x2A); optional delete-after-ingest
- [ ] Stress history (55) and setting (54)
- [ ] Alarms set/get
- [ ] Sport live + phone/on-device control; Sport+ history (0x41..0x45)
- [ ] Auto-measure settings as needed (HR: 22, SpO2: 44, Stress: 54)

References: see individual docs in `docs/` for deeper dives on each domain.


