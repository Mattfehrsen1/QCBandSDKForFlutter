## Implementation prompt: Sleep ingestion wiring and test path

Goal: Wire the vendor sleep response stream into the existing `SleepParser`, expose a simple SDK helper to request by day offset, and add a test button/flow in the example device screen to validate end‑to‑end.

### Context you must use
- Parser exists in `lib/bean/models/sleepModel.dart` (`SleepParser`) and already supports Standard vs Large Data.
- Vendor assembler pattern exists for Sport+ in `lib/qc_band_sdk_for_flutter.dart` (`ingestVendorNotification` for 0x42/0x44/0x45). Reuse this pattern for sleep.

### What to implement (in order)
1) SDK request helper
- File: `lib/qc_band_sdk_for_flutter.dart`
- Add: `static Uint8List getSleepDetailForOffset(int offset)`
  - Offset range: 0=today, 1..29 previous days (validate and clamp).
  - Build packet using the existing function `generateReadSleepDetailsCommand(offset, 0, 0)` OR a small dedicated builder that sets vendor cmd 0x27/0x39 depending on what `SleepParser` expects. Prefer 0x39 as used in `generateReadSleepDetailsCommand`.
  - Return the 16‑byte request (with CRC populated like other classic/vendor helpers).

2) Vendor routing for sleep
- File: `lib/qc_band_sdk_for_flutter.dart`
- Extend `ingestVendorNotification(List<int> value)` to assemble sleep messages, mirroring the Sport+ approach:
  - Detect 0xBC header with sleep cmd (0x39). If your environment supports 0x27 too, route it as well.
  - Track total length from bytes 2..3 (LE). Accumulate payload bytes from index 6 onward into a `_sleepBuf` with `_sleepTotal` and `_sleepReceived` counters.
  - When completed, call a new private handler `_onSleepPayloadAssembled(Uint8List payload, int offset)`.

3) Parse and emit using SleepParser
- File: `lib/qc_band_sdk_for_flutter.dart`
- Implement `_onSleepPayloadAssembled(...)`:
  - Accept both single‑day and multi‑day payloads. If payload encodes multiple days, iterate and feed each to `SleepParser` with the correct `currentIndex` (daysAgo) based on the user’s requested offset.
  - For single day, do: `final segments = SleepParser(payload, currentIndex: offset).parse();`
  - Build a normalized result map:
    ```dart
    {
      'dataType': 'SleepDetail',
      'end': true,
      'data': {
        'date': 'yyyy-MM-dd',
        'bedTime': bedTime.toIso8601String(),
        'wakeTime': wakeTime.toIso8601String(),
        'totals': {'deep': x, 'light': y, 'rem': z, 'awake': a},
        'segments': [
          {'start': iso, 'end': iso, 'stage': 1..4}
        ]
      }
    }
    ```
  - Print a concise log summary (`Sleep date ... segments=N deep=.. light=.. rem=.. awake=..`).

4) Public callback hook (optional but helpful)
- File: `lib/qc_band_sdk_for_flutter.dart`
- Add: `static void Function(Map<String, dynamic> sleep)? _onSleep;` and a setter `setSleepListener(...)`.
- Call listener with the normalized map when parsing completes.

5) Example app test path
- File: `example/lib/screens/device_screen.dart`
- Add UI state:
  - `int _sleepDays = 1; bool _sleepBusy = false;`
- Add helpers:
  - `_sleepRequestForOffset(int offset)`:
    - Write the request: `await _bluetoothCharacteristicWrite.write(QCBandSDK.getSleepDetailForOffset(offset));`
    - Subscribe to the vendor notify characteristic and feed all 0xBC frames to `QCBandSDK.ingestVendorNotification(value);`
    - Listen via `QCBandSDK.setSleepListener((m) { print summary; Snackbar.show(...); });`
    - Add timeout (e.g., 8–10s) to avoid hanging.
  - `_getSleepNDays(int days)`:
    - Sequentially call `_sleepRequestForOffset(offset)` for `offset=0..days-1` with a small delay.
- Add UI controls in the page (near stress controls):
  - Dropdown `1..7` for `_sleepDays`, and a button `Get Sleep (N d)` that triggers `_getSleepNDays(_sleepDays)`; disable while `_sleepBusy`.

### How you verify (manual test)
1. Pair/connect to the device, request MTU ~223, enable notify on both standard and vendor characteristics.
2. Tap the new button `Get Sleep (N d)`.
3. Expect per‑day toast/log lines like:
   - `Sleep 2025-08-05: segments=21 deep=84 light=236 rem=42 awake=18`
4. Inspect segment timeline in logs if needed (first 3 entries printed). Confirm bed/wake times make sense (e.g., start ~23:xx, end next morning).
5. Try multiple offsets (0..2). Ensure no crashes on partial data; timeouts yield a graceful message.

### Acceptance criteria
- Sleep responses are assembled end‑to‑end and parsed into segments with correct bed/wake handling across midnight.
- Emits a uniform result structure with `end=true` upon completion.
- Example screen fetches N days sequentially, printing concise summaries.
- Robust to partial/malformed frames (no exceptions; timeouts fire).

### Notes
- If both 0x27 and 0x39 are present, route both to the same assembler, or branch if payload shapes differ. Prefer minimal assumptions and reuse `SleepParser` protocol detection.
- Keep code small and readable. Avoid refactoring unrelated areas.


