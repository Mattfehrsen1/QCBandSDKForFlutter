## Sport+ workouts: fetching the full heart-rate array (offline supported)

This guide explains how our SDK fetches the per-sample heart-rate array for Sport+ workouts and how to use it in your app. It works even if the phone was disconnected during the workout, as long as the device stored the workout (Sport+ path).

### What is supported
- Device records Sport+ sessions on-device (offline).
- After you reconnect, you can request:
  - Summary (counts, duration, min/avg/max HR, distance, calories, steps)
  - Details (per-sample HR series with sampling interval)

If the firmware stored details, you will get the full HR array. Some sessions or firmwares may store only summary stats; then the HR array won’t be available.

### Protocol overview (for context)
- Transport: vendor serial channel framed with `0xBC` header
- Summary: request `0x41` → device responds in chunks `0x42`
- Details: request `0x43` → meta `0x44`, data `0x45` (records contain key `17 = rate_real`)

Our SDK assembles these chunks and extracts HR samples for you.

### SDK entry points (Dart)
Files to reference:
- `lib/qc_band_sdk_for_flutter.dart` (ingestion, requests, callbacks)
- `lib/utils/resolve_util.dart` (parsers and HR extraction)

Key APIs:
- Register and request summary:
  - `QCBandSDK.getSportPlusSummaryRequest(int unixTs, void onResult(List<Map<String,dynamic>> summaries))`
  - or `QCBandSDK.buildSportPlusSummaryReq(int unixTs)` then parse via `QCBandSDK.ingestVendorNotification`
- Register and request details for a selected workout:
  - `QCBandSDK.getSportPlusDetailsRequest(int sportType, int startTime, void onResult(Map summary, List<int> hrSeries, int sampleSecond))`
  - or `QCBandSDK.buildSportPlusDetailsReq(int sportType, int startTime)` and ensure a details callback is set
- Feed incoming notifications (from your BLE notify stream) into:
  - `QCBandSDK.ingestVendorNotification(List<int> value)`
- Optional helper to build a normalized workout payload you can share/store:
  - `QCBandSDK.buildWorkoutJson(summary, hrSeries, sampleSecond)`

### Minimal usage example
1) Route BLE notifications to the ingestor (both vendor 0xBC frames and any continuation chunks):

```dart
// Inside your BLE notify stream listener:
_notifySubscription = characteristic.value.listen((List<int> value) {
  QCBandSDK.ingestVendorNotification(value);
});
```

2) Request summary since a cutoff timestamp (UTC seconds). The callback receives a list of sessions:

```dart
final int cutoffUnixTs = DateTime.now().toUtc().millisecondsSinceEpoch ~/ 1000 - 7 * 86400;
final Uint8List summaryReq = QCBandSDK.getSportPlusSummaryRequest(
  cutoffUnixTs,
  (List<Map<String, dynamic>> summaries) async {
    if (summaries.isEmpty) return;
    final latest = summaries.last; // choose a session (or show a list to user)

    // Step 3: register details callback and send details request
    final Uint8List detailsReq = QCBandSDK.getSportPlusDetailsRequest(
      latest['sportType'] ?? 0,
      latest['startTime'] ?? 0,
      (Map<String, dynamic> summary, List<int> hrSeries, int sampleSecond) {
        // hrSeries now contains the full per-sample HR array
        // sampleSecond is the sampling interval in seconds
        final workout = QCBandSDK.buildWorkoutJson(summary, hrSeries, sampleSecond);
        print('Workout HR samples: ${hrSeries.length}, every ${sampleSecond}s');
        // TODO: save or upload `workout`
      },
    );

    await bleWrite(detailsReq); // write to the same vendor serial characteristic
  },
);

await bleWrite(summaryReq); // write to vendor serial characteristic
```

Notes:
- `bleWrite(...)` is your app’s BLE write to the device’s vendor serial characteristic used by the app (the same characteristic your notifications come from).
- You can select any session from `summaries` (each item has keys like `sportType`, `startTime`, `duration`, `distance`, `calories`, `hrMin`, `hrAvg`, `hrMax`, `steps`).

### What you get in the details callback
- `hrSeries`: `List<int>` of heart rates (bpm) for the session
- `sampleSecond`: sampling period in seconds
- `summary`: a `Map` with top-level fields (if you associated one), or `{}` if not linked

To reconstruct timestamps for each HR sample:
- Start time = `summary['startTime']` (UTC seconds) or your selected session’s `startTime`
- Timestamp of sample i = `startTime + i * sampleSecond`

### Edge cases and verification
- If `hrSeries` is empty, the device likely did not store detailed samples for that workout. You still have min/avg/max HR in the summary if present.
- Some firmwares report `packageCount = 0` (no details). The SDK handles this; the details callback will simply deliver an empty series.
- Keep feeding notifications into `ingestVendorNotification` until the SDK logs “samples=…”.

### Where this is implemented
- Ingestion and assembly:
  - `QCBandSDK.ingestVendorNotification` in `lib/qc_band_sdk_for_flutter.dart`
- Summary parser (extracts `hrMin`, `hrAvg`, `hrMax`, etc.):
  - `ResolveUtil.parseSportPlusSummaryBuffer` in `lib/utils/resolve_util.dart`
- Details meta and HR extraction (key 17):
  - `ResolveUtil.parseSportPlusDetailsMeta`
  - `ResolveUtil.extractHrSeriesFromDetails`

### FAQ
**Can I fetch workouts recorded while the phone was disconnected?**
Yes. If the device recorded the session as Sport+ and stored details, you can fetch them after reconnecting.

**How do I know if details exist?**
Send the details request; if the callback returns a non-empty `hrSeries`, details were present. Otherwise, rely on summary HR stats.

**Do I need native (Android/iOS) code?**
No. The SDK code shown above is pure Dart. You only need to write BLE packets and pipe notifications into `ingestVendorNotification`.


