## Implementation prompt: HRV ingestion wiring and 5‑minute normalization

Goal: Implement robust HRV history ingestion (classic cmd 57) with a multi‑frame assembler, produce a fixed 5‑minute grid (288 points) per day, and add a simple test flow in the example device screen.

### Context you must use
- HRV history uses the classic 16‑byte protocol (cmd 57). Frames are split and include a header (size, range), a first data frame (with day offset), followed by sequential data frames, then an end marker.
- The decompiled `HRVRsp` shows:
  - flag 0x00 → header: `size`, `range` (minutes) and allocate `size*13` bytes buffer.
  - flag 0x01 → first data: `offsetDays` (0=today, 1=yesterday, ...) + payload; copy into buffer.
  - flag 0x02..size-1 → next frames; copy 13‑byte slices into buffer; end when last frame is received or an 0xFF end flag appears.
- This SDK already implements a similar multi‑frame assembler for heart (cmd 21) and stress (cmd 55). Reuse the approach to avoid bugs.

### What to implement (in order)
1) SDK request helper
- File: `lib/qc_band_sdk_for_flutter.dart`
- Add: `static Uint8List getHrvByOffset(int offset)`
  - Valid offsets: 0..6 (or up to 7 days, clamp safely to device limit).
  - Build a classic 16‑byte command using the existing `getHRV(offset)` or create a new method that writes cmd 57 with `[0]=57, [1]=offset` and CRC at the last byte.
  - Return the 16‑byte packet.

2) HRV assembler and parser (stateless per frame, stateful across frames)
- File: `lib/utils/resolve_util.dart`
- Add a stateful handler similar to `handleIncomingDataHeartData`, e.g., `static Map<String, dynamic> handleIncomingDataHrv(Uint8List data)` that:
  - Validates `data[0] == QcBandSdkConst.cmdHrv`.
  - Reads `sub = data[1] & 0xFF`.
  - Header (sub==0x00):
    - `size = data[2] & 0xFF`, `rangeMin = data[3] & 0xFF`.
    - Allocate an internal `_hrvRawBuffer = List<int>.filled(size*13, 0)`; set `_hrvWriteIndex = 0`.
  - First data (sub==0x01):
    - `offsetDays = data[2] & 0xFF`.
    - Copy payload safely: from index 3 to `length-1` (strip trailing CRC if present) into `_hrvRawBuffer` at `_hrvWriteIndex`; update `_hrvWriteIndex`.
  - Subsequent frames (sub in 0x02..0xFE):
    - Copy payload from index 2 to `length-1` into `_hrvRawBuffer` at `_hrvWriteIndex`; increment `_hrvWriteIndex` by the copied length (normally 13).
  - End flag (sub==0xFF or sub==size-1 after copy): finalize and emit `end=true`.
  - Emit progress for non‑final frames:
    ```dart
    { dataType: 'HRVData', end: false, data: { progress: _hrvWriteIndex, totalExpected: _hrvRawBuffer.length } }
    ```

3) 5‑minute normalization and final shape
- On completion, convert the raw HRV series to a fixed 5‑minute grid (288 points):
  - Determine the number of raw samples `n = (24*60)/rangeMin`.
  - Build a `List<int> hrvArray288 = List.filled(288, 0)`.
  - For each raw sample index `i`, calculate the slot `slot = (i * rangeMin) / 5` (integer), and place the value at `hrvArray288[slot]`.
  - Fill intermediate gaps by carry‑forward (hold the last seen value) for better graphs. Keep future slots (later today) as 0.
  - Date: compute from `offsetDays`: `date = DateTime.now().subtract(Duration(days: offsetDays))` (local), formatted `yyyy-MM-dd`.
- Emit a final map:
  ```dart
  {
    'dataType': 'HRVData',
    'end': true,
    'data': {
      'date': 'yyyy-MM-dd',
      'range': rangeMin,            // minutes per raw sample
      'hrvArray': hrvArray288,      // 288 points at 5‑minute cadence
      'totalValues': _hrvWriteIndex // bytes written in raw buffer
    }
  }
  ```

4) Route HRV frames through the standard ingestion path
- File: `lib/qc_band_sdk_for_flutter.dart`
- In `DataParsingWithData(List<int> value)` case for `cmdHrv`, delegate to `ResolveUtil.handleIncomingDataHrv(Uint8List.fromList(value))` (replace any demo parser).

5) Example app test path
- File: `example/lib/screens/device_screen.dart`
- Add UI state:
  - `int _hrvDays = 1; bool _hrvBusy = false;`
- Add helpers:
  - `_hrvRequestForOffset(int offset)`:
    - Write request on the standard write characteristic: `await _bluetoothCharacteristicWrite.write(QCBandSDK.getHrvByOffset(offset));`
    - Subscribe to standard notify stream and call `QCBandSDK.ingestStandardNotification(value)` for each frame.
    - Collect the final `HRVData` map when `end==true` and print a summary:
      - `HRV yyyy‑MM‑dd: points=288 (non‑zero=X) range=Rmin`
    - Timeout after ~8 s; cancel subscription.
  - `_getHrvNDays(int days)` that sequentially calls `_hrvRequestForOffset(offset)` for 0..days‑1 with a small delay.
- UI controls (near Sleep/Stress):
  - Dropdown 1..7 for `_hrvDays`, button: `Get HRV (N d)` disabled while `_hrvBusy`.

### How you verify (manual test)
1. Connect, MTU ~223, enable standard notify.
2. Tap `Get HRV (N d)`.
3. Expect logs like:
   - `HRV 2025‑08‑05: points=288 (non‑zero=48) range=30min`
4. Validate that future slots for today are zero and that values appear every `(range/5)` slots.
5. Try offsets 0..2. Ensure progress logs increment and a final `end=true` arrives.

### Acceptance criteria
- Multi‑frame HRV responses assemble reliably with end detection on both `0xFF` and `index==size-1`.
- Final output always returns a 288‑point `hrvArray` with 5‑minute cadence, properly aligned to the start of the day.
- Robust to short/partial frames; returns progress and never crashes.
- Example app fetches multiple days sequentially with clear summaries.

### Notes
- Some devices fix `range=30` for HRV. The normalization is a presentation convenience and does not change device sampling.
- Use the same internal patterns and safety guards as `ReadHeartRateResponse` (bounds checks, guard against malformed lengths, strip trailing CRC byte when present).
- Keep code small and focused; avoid refactoring unrelated areas.


