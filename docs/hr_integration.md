## Heart Rate integration (aligned with production app)

This document explains the updated heart rate commands and parsing flow used by the Flutter SDK. The implementation matches the protocol observed in the production Android app.

### Concepts

- History heart rate (cmd 21) returns a full day’s series in multiple frames, with a header indicating packet count and sampling range.
- Realtime heart (cmd 105 type 6) controls the device’s realtime HR stream; values are notified under cmd 30.
- Auto HR setting is read/written via cmd 22.

### Commands

- History HR request (cmd 21):
  - Build with a 4‑byte little‑endian timestamp after the command byte.
  - SDK helper: `QCBandSDK.buildReadHeartRateCommand(unixTs)`.

- Auto HR setting (cmd 22):
  - Read: `[22, 1, 0, ...]` → `QCBandSDK.GetAutomaticHRMonitoring()`
  - Write: `[22, 2, enable(1|2), interval(min)]` → `QCBandSDK.SetAutomaticHRMonitoring(enable, interval)`

- Realtime heart control:
  - Preferred: cmd 105 with `type=6` and `sub=action` → `QCBandSDK.realtimeHeartControl(action)`
    - Start: `QCBandSDK.startRealtimeHeart()`
    - Stop: `QCBandSDK.stopRealtimeHeart()`
  - Legacy (still available): cmd 30 with sub‑action → `QCBandSDK.liveHeartData(action)` / `QCBandSDK.GetRealTimeHeartRate()`

### Notifications parsing

- History HR (cmd 21):
  - Routed via `QCBandSDK.DataParsingWithData` to `ResolveUtil.handleIncomingDataHeartData(data)`.
  - Frame types:
    - Header: `[21, 0, size, range, ...]` → allocates a buffer of `size*13`
    - First data: `[21, 1, ts(4 LE), payload...]` → sets `utcTime` (timezone adjusted), appends payload
    - Subsequent: `[21, n, payload...]` → appends 13‑byte blocks. End when `n == size-1` or `0xFF` seen.
  - On completion the 288‑length daily array is produced. If `range != 5`, samples are mapped to nearest 5‑minute slot.
  - Today’s future slots are zeroed like the app.
  - Emitted map:
    - `dataType: 'HeartRateData'`
    - `end: true|false`
    - `data: { utcTime, range, heartRateArray, totalValues | progress }`

- Realtime HR (cmd 30):
  - Routed to `ResolveUtil.parseRealtimeHeart(value)`.
  - Returns: `{ dataType: 'RealtimeHeartRate', end: true, data: { HeartRate: bpm } }`

### Public SDK API (selected)

- Build history request: `QCBandSDK.buildReadHeartRateCommand(unixTs)`
- Parse notifications: `QCBandSDK.ingestStandardNotification(value)`
- Auto HR read/write:
  - `QCBandSDK.GetAutomaticHRMonitoring()`
  - `QCBandSDK.SetAutomaticHRMonitoring(enable, interval)`
- Realtime HR control:
  - `QCBandSDK.startRealtimeHeart()`, `QCBandSDK.stopRealtimeHeart()` (cmd 105)
  - Legacy: `QCBandSDK.GetRealTimeHeartRate()`, `QCBandSDK.liveHeartData(action)` (cmd 30)

### Notes

- Timezone: Unix time in history responses is adjusted to match app behavior.
- Range: Consumers should treat the produced `heartRateArray` as 5‑minute cadence for 24h (288 points).
- Backward compatibility: legacy realtime functions remain available; prefer the 105‑based control for consistency.


