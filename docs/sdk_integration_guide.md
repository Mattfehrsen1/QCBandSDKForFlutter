## QCBand SDK Integration Guide

This guide shows how to use the SDK to:
- Connect to the device and enable notifications
- Control on-device sport mode
- Sync Sport+ history (summaries and details)
- Build a portable JSON for a workout

### BLE UUIDs
- Vendor service: `de5bf728-d711-4e47-af26-65e3012a5dc7`
  - Write: `de5bf72a-d711-4e47-af26-65e3012a5dc7`
  - Notify: `de5bf729-d711-4e47-af26-65e3012a5dc7`
- Optional standard services (if available):
  - Heart Rate (180D/2A37)
  - Running Speed & Cadence (1814/2A53)

### Setup
1) Discover services/characteristics and enable notify on vendor notify.
2) Listen to vendor notify and feed data to the SDK assembler:

```dart
vendorNotify.value.listen((data) {
  QCBandSDK.ingestVendorNotification(data);
});
```

### On-device sport control
All control packets are vendor packets with 0xBC header. Use the helpers to construct them and write to vendor write characteristic:

```dart
// Start/Pause/Continue/Stop on-device sport for a given sportType
await vendorWrite.write(QCBandSDK.startOnDeviceSport(sportType));
await vendorWrite.write(QCBandSDK.pauseOnDeviceSport(sportType));
await vendorWrite.write(QCBandSDK.continueOnDeviceSport(sportType));
await vendorWrite.write(QCBandSDK.stopOnDeviceSport(sportType));
```

Constraints:
- Do not send periodic HR commands (cmd 0x30) during on-device sport.
- Do not send 0x5A heartbeat packets (used for phone-controlled multi-sport).

### Sport+ history sync

Fetch summaries since a timestamp (Unix seconds). Responses arrive as chunked 0x42 packets and are assembled by the SDK. Register a callback and then send the request packet:

```dart
QCBandSDK.getSportPlusSummaryFromTimestamp(0, (summaries) {
  for (final s in summaries) {
    // s contains: sportType, startTime, duration, distance, calories,
    // optional: hrMin, hrAvg, hrMax, steps
  }
});
await vendorWrite.write(QCBandSDK.buildSportPlusSummaryReq(0));
```

Fetch details for a specific summary (0x44 meta + 0x45 data). The SDK assembles and decodes the HR series (key=17):

```dart
final sportType = summary['sportType'];
final startTime = summary['startTime'];
QCBandSDK.getSportPlusDetailsFor(sportType, startTime, (sum, hrSeries, sampleSecond) {
  // hrSeries: List<int> of heart rate samples
  // sampleSecond: sampling interval in seconds
});
await vendorWrite.write(QCBandSDK.buildSportPlusDetailsReq(sportType, startTime));
```

### Build workout JSON
After you have a summary and the HR details, build a portable JSON object:

```dart
final workout = QCBandSDK.buildWorkoutJson(summary, hrSeries, sampleSecond);
final jsonString = jsonEncode(workout);
```

JSON example:

```json
{
  "id": "7-1754869570",
  "source": "sport+",
  "sportType": 7,
  "startTime": 1754869570,
  "endTime": 1754873794,
  "durationSec": 4224,
  "distanceMeters": 2896,
  "steps": 1234,
  "caloriesRaw": 145058,
  "caloriesKcal": 1450.6,
  "hr": {
    "sampleSecond": 2,
    "min": 57,
    "avg": 101,
    "max": 169,
    "samples": [72,74,72,68,...]
  }
}
```

Notes:
- Calories may be fixed-point; SDK exposes both raw and a normalized guess in kcal.
- If details include more streams (e.g., GPS), the SDK will still parse HR; you can extend your JSON model as needed.

### Live data (optional)
When connected and in phone-controlled sport (cmd 119), live frames (0x78) decode via `ResolveUtil.parseLiveSportNotify` and are surfaced through `QCBandSDK.DataParsingWithData(value)`. On-device sport typically won’t stream the same 0x78 frames; rely on post-session Sport+ for full stats.

### Reliability tips
- Avoid periodic writes during on-device sport to prevent session exits.
- Do not block notification listeners; the SDK assembles buffers efficiently and calls your callbacks when complete.
- Handle reconnects gracefully (Android GATT 133): backoff and retry.


