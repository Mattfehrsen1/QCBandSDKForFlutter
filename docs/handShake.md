## Handshake/init flow (as in decompiled app)

This document explains the connection handshake the production/decompiled app performs right after GATT connect. It’s a small, ordered sequence that prepares the device and the SDK for reliable sync.

### Overview
- Do these steps once after services are discovered and characteristics are resolved.
- Use short delays (100–300 ms) between writes, per-command timeouts (5–8 s), and single-flight queueing.
- Enable notifications before issuing requests so responses aren’t missed.

### Steps in order
1) Request MTU
- Target around 223 to improve throughput for chunked/vendor payloads.
- On iOS this is implicit; on Android request explicitly.

2) Enable notifications
- Standard command notify characteristic (classic 16‑byte protocol).
- Vendor/serial notify characteristic (0xBC framed payloads) for large data such as sleep and Sport+.

3) Set device time (with language)
- Command: SetTime (cmd 1, classic 16‑byte)
- Payload: current time encoded in BCD and a language byte; the decompiled app sends language derived from locale.
- Purpose: ensure timestamps align; some devices refuse history reads until time is set.

4) Read quick status
- Battery level (cmd 9), classic 16‑byte → quick health indicator and a wake-up ping.
- Optionally: device version/info for diagnostics.

5) Read function support flags
- Command: Function Support (cmd 60), classic 16‑byte.
- Returns: bitfields/bytes indicating which domains are supported (SpO2, blood pressure, temperature, manual heart, blood sugar, etc.).
- Purpose: gate the subsequent sync; the decompiled app branches by these flags.

6) Initialize auto‑measurement switches
- Command family: Auto‑measurement setting (cmd 22), classic 16‑byte.
- Read current settings first (mode/read = 1). Many firmwares expose per‑type switches (1=HR, 2=SpO2, 3=Temperature, 4=HRV), returning enable and interval.
- If policy/user preference requires, write new settings (mode/write = 2, enable and interval values), then re‑read to confirm.

7) Proceed to main sync (capability‑gated)
- Typical order in decompiled app: steps → sleep → HRV → heart → SpO2 → temperature → (optional BP, sugar) → Sport+ summary/details.
- Use per‑domain request/parse helpers, accumulate multi‑frame results, emit progress and a final end signal.

### Timing & reliability
- Add a simple throttle window (e.g., don’t re‑run full handshake more than once per 10 minutes) to reduce BLE traffic.
- Single‑flight queue: serialize commands; cancel and clean up on disconnect.
- Timeouts: 5–8 s per request; if no response, retry once or defer to next session.

### Mapping to this SDK (helpers you can call)
- Request MTU: `device.requestMtu(223)` (example app level).
- Enable notify: app level (both standard and vendor characteristics).
- Set time: `QCBandSDK.setDeviceTime(offsetSeconds)` (cmd 1 with locale byte).
- Battery: `QCBandSDK.GetDeviceBatteryLevel()` (cmd 9) → `ResolveUtil.getDeviceBattery`.
- Function support: `QCBandSDK.getDeviceFunctionSupport()` (cmd 60). Parser to be added (see parity plan).
- Auto‑measurement:
  - Read: `QCBandSDK.GetAutomaticHRMonitoring()` (cmd 22 mode=1) → `ResolveUtil.getAutoHeart` (returns enable/interval; extend to per‑type if firmware requires).
  - Write: `QCBandSDK.SetAutomaticHRMonitoring(enable, interval)` (cmd 22 mode=2).

### Pseudocode (app orchestration)
```dart
await device.connectAndUpdateStream();
await device.discoverServices();
await device.requestMtu(223);
await enableNotify(standardNotifyChar);
await enableNotify(vendorNotifyChar);

// Set time
await standardWrite.write(QCBandSDK.setDeviceTime(0));

// Quick status
await standardWrite.write(QCBandSDK.GetDeviceBatteryLevel());

// Function support
await standardWrite.write(QCBandSDK.getDeviceFunctionSupport());
// → parse in standard notify handler (parser to be implemented)

// Auto‑measurement switches
await standardWrite.write(QCBandSDK.GetAutomaticHRMonitoring());
// decide on enable/interval → optionally write
// await standardWrite.write(QCBandSDK.SetAutomaticHRMonitoring(true, 10));

// Now start main sync flows gated by support flags
```

### Notes from decompiled behavior
- SetTime is sent early after connect (language byte logged in decompiled `SetTimeReq`).
- Manual measurements (HR/SpO2/temp/HRV) use `StartHeartRateReq.getSimpleReq(type)` for ad‑hoc checks, but history sync relies on their respective history commands.
- Vendor 0xBC framing is used for large data (sleep, Sport+); enable its notify before issuing those requests.

### Where to implement
- Keep handshake orchestration in the app (controller/view‑model) so it can coordinate UI, permissions, and timing.
- SDK should provide stateless request/parse helpers and per‑domain assemblers.


