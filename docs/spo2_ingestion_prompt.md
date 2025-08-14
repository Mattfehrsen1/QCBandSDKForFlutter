## Implementation prompt: SpO₂ (blood oxygen) history ingestion

Goal: Implement reliable SpO₂ history ingestion. Support the classic 16‑byte protocol (record list per timestamp) and optionally the vendor (0xBC) trend format (per‑day 24×min/max pairs) if present. Add a simple tester in the example app.

### Context you must use
- Classic history exists in the original Java (10‑byte records):
  - BCD timestamp at bytes 3..8, value at byte 9; multiple 10‑byte blocks per notification.
  - Mode semantics: `0 = latest`, `2 = continue`, `99 = delete`.
  - End marker: trailing `0xFF` on the last frame or explicit final frame size.
- Vendor path (0xBC/42) may return per‑day trend chunks (49‑byte per day: `dateOffsetDays` + 24 pairs of `min,max`). Treat this as optional (feature‑flagged) and fall back to classic if absent.
- Manual spot measurement uses realtime control (cmd 105 type=3) and is out of scope for history, but you can keep it in mind for a debug button.

### What to implement (in order)
1) SDK request helpers (classic path)
- File: `lib/qc_band_sdk_for_flutter.dart`
- Add convenience wrappers that build the 16‑byte classic commands:
  - `static Uint8List getSpO2HistoryLatest()` → mode 0
  - `static Uint8List getSpO2HistoryContinue()` → mode 2
  - `static Uint8List deleteSpO2History()` → mode 99
- For time‑based reads (optional): `static Uint8List getSpO2HistoryFrom(String time)` (yyyy‑MM‑dd HH:mm:ss) mirroring step/sleep builders.

2) Classic parser (stateless per notify, accumulates externally)
- File: `lib/utils/resolve_util.dart`
- Add `static Map<String, dynamic> parseSpO2Classic(List<int> value)` that:
  - Splits payload into 10‑byte blocks; for each block:
    - Parse BCD datetime at indices 3..8.
    - Read SpO₂ value at index 9 (unsigned byte).
    - Push a sample: `{ 'timestamp': iso8601, 'spo2': value }`.
  - End detection: if the last byte is `0xFF`, set `end=true`.
  - Return:
    ```dart
    {
      'dataType': 'SpO2Classic',
      'end': bool,
      'data': { 'samples': List<Map<String, dynamic>> }
    }
    ```

3) Vendor parser (optional, per‑day trend)
- File: `lib/utils/resolve_util.dart`
- Add `static Map<String, dynamic> parseSpO2VendorPayload(Uint8List payload)` that:
  - Expects 49‑byte records: `[dateOffsetDays][min,max × 24]`.
  - For each 49‑byte chunk, compute date = today‑offset and build:
    ```dart
    {
      'date': 'yyyy-MM-dd',
      'min': List<int>(24),
      'max': List<int>(24)
    }
    ```
  - Return:
    ```dart
    {
      'dataType': 'SpO2Trend',
      'end': true,
      'data': { 'days': List<...> }
    }
    ```

4) Routing
- File: `lib/qc_band_sdk_for_flutter.dart`
- In `DataParsingWithData(List<int> value)`: add a case for the classic SpO₂ command → delegate to `ResolveUtil.parseSpO2Classic(value)`.
- In `ingestVendorNotification(List<int> value)`: if `0xBC` and cmd `0x2A (42)`, assemble the total payload (like sleep/sport+), then call `ResolveUtil.parseSpO2VendorPayload()` on completion.
- Emit clear logs for both paths; prefer vendor trend if available, else classic.

5) Example app test path
- File: `example/lib/screens/device_screen.dart`
- Add UI controls:
  - Classic history: buttons `Get SpO2 Latest` (mode 0) and `Continue` (mode 2). On click, write to the standard write characteristic and listen on standard notify. Accumulate samples across notifies until `end=true`; show count and last timestamp.
  - Vendor (optional): `Get SpO2 Trend (N d)` to request vendor 0xBC path (if your device supports it). Write to vendor write characteristic, enable vendor notify, and assemble chunks; display per‑day 24×min/max arrays.
- Add timeouts (~8–10s) and a small sequential delay between requests when fetching multiple days.

### How you verify (manual test)
1. Connect, request MTU ~223, enable notifies.
2. Classic: tap `Get SpO2 Latest`, expect logs like `SpO2 samples: 37 (end=true)` with the last ISO timestamp printed.
3. Classic: tap `Continue` repeatedly until no more data; total count should increase, then finish.
4. Vendor (if supported): fetch 1–2 days; verify each day shows 24 pairs of min/max values.

### Acceptance criteria
- Classic path: correctly parses 10‑byte blocks into timestamped samples; sets `end=true` on completion; robust to partial frames.
- Vendor path (if present): assembles 0xBC payload and returns per‑day min/max arrays (24 values each), with correct `date` from offset.
- Example app can run both flows with clear logs and timeouts; no crashes on malformed data.

### Notes
- Prioritize classic history first; vendor trend is optional and device‑specific.
- Keep both parsers small and defensive (bounds checks, strip CRC where needed).
- Do not change device sampling; this is history ingestion only. Manual spot checks (105 type=3) can be kept separate for a debug button.


