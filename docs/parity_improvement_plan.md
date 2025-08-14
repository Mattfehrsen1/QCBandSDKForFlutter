## SDK data parity plan (match decompiled project)

This plan describes how to bring HRV, SpO2, Sleep, and related areas up to the same completeness and reliability as the decompiled app. Keep changes small, focused, and thoroughly tested.

### Principles
- Prefer classic 16‑byte UART protocol where stable; add vendor (0xBC) only when required.
- One request helper per domain, one stateless per‑frame parser, optional stateful assembler that emits consistent outputs.
- Emit a single, normalized data shape per domain (clear `dataType`, `end`, `data` keys).
- Unit‑test parser logic with recorded frames; verify progress/end markers and edge cases.

### Files to touch (high‑level)
- `lib/qc_band_sdk_for_flutter.dart`: request builders, public helpers.
- `lib/utils/resolve_util.dart`: parsers, assemblers, routing.
- `lib/bean/models/...`: data models where needed (simple POJOs only).
- `docs/`: short per‑domain guides mirroring `docs/hr_integration.md` and `docs/stress_integration.md`.
- `example/lib/screens/device_screen.dart`: use unified helpers; remove ad‑hoc demo parsers.

---

## 1) HRV history (cmd 57)

**Current**
- Request helper exists (`getHRV(offset)`).
- Parsing routes to `getHrvTestData` (single‑shot test), and demo UI uses ad‑hoc packet‑index parsing.

**Gaps vs decompiled**
- No multi‑frame assembler (header/size/range, offsetDays, 13‑byte stride).
- No end detection or progress reporting; no 5‑minute normalization.

**Target behavior**
- Mirror heart history: header `0x00 → size, range`; first `0x01 → offsetDays + payload`; next `0x02..size-1 → payload`; end on `0xFF` or `index == size-1`.
- Produce a 24h time‑series normalized to 5‑minute cadence (288 points) for the target day.

**Implementation steps**
1. Add `ResolveUtil.handleIncomingDataHrv(Uint8List)` with an internal assembler (like `ReadHeartRateResponse`).
2. Key assembler by day offset; accept frames; on completion, emit:
   - `dataType: 'HRVData'`, `end: true|false`, `data: { date, range, hrvArray(288), progress, totalExpected }`.
3. Route `QcBandSdkConst.cmdHrv` in `DataParsingWithData` to the new handler (keep `getHrvTestData` behind a dedicated code path if needed).
4. Add simple public helpers: `getHrvByOffset(int offset)` and `getHrvNDays(int days)` (sequential, small delay).
5. Replace demo parsing in `example` with the unified ingestion flow.

**Acceptance criteria**
- Handles early `0xFF` termination and size‑based end.
- Correct date resolution from `offsetDays`; future slots zeroed for today.
- Stable on malformed/short frames (no crashes; returns progress).

**Tests**
- Golden frames: header+first+next+end; header+first only; odd sizes; different ranges (5, 10, 15).

---

## 2) SpO2 (blood oxygen) history

**Current**
- Guessed vendor packet (`0xBC/42 + 0xFF`) without ingestion path.
- Standalone parser (`blood_oxygen_entity.dart`) incomplete and not integrated.

**Gaps vs decompiled**
- Manual measurement uses `105 type=3`; history uses classic commands with mode (0 latest, 2 continue, 99 delete).
- No classic request/parse wiring; no end detection; model not aligned.

**Target behavior**
- Implement classic read for history and auto history; keep manual measurement via 105 type=3.
- Emit per‑day arrays (24 values) or min/max pairs per hour if supported.

**Implementation steps**
1. Add builders in `QCBandSDK`:
   - `getBloodOxygenHistory({required int mode, String? time})` (classic 16B).
   - `getAutoBloodOxygen({required int mode, String? time})` if device supports auto history.
   - Keep realtime/manual via `startRealtimeHeart(type=3)` if necessary.
2. Implement `ResolveUtil.parseBloodOxygenFrames(List<int>)`:
   - Detect record size (10‑byte classic or vendor 49‑byte); parse BCD timestamps; accumulate list per day.
   - End on trailing `0xFF` or per‑packet close marker; emit `{ frame: 'header'|'data', records: [...] }`.
3. Route `cmd` values in `DataParsingWithData` and/or `ingestVendorNotification` accordingly.
4. Replace demo parser, ensure `BloodOxygenEntity` is complete (fix missing `recordStartOffset`, encapsulate helpers).

**Acceptance criteria**
- Correctly parses at least: latest (mode 0), continue (mode 2), delete (mode 99 no‑op) sequences.
- Produces per‑day series and date string; robust to partial frames.

**Tests**
- Golden classic records (multiple days), auto mode stream, malformed tail, vendor vs classic paths.

---

## 3) Sleep details (vendor 0xBC 0x27/0x39)

**Current**
- `SleepParser` is solid (standard + large‑data). Request/ingestion are not wired; helpers return hardcoded payloads.

**Gaps vs decompiled**
- No vendor assembler for sleep; no dispatch in `ingestVendorNotification` for sleep opcodes.

**Target behavior**
- Add vendor assembler for sleep similar to Sport+ (0x42/0x44/0x45), then feed the final payload(s) into `SleepParser` per day.

**Implementation steps**
1. Define request helper: `QCBandSDK.getSleepDetailForOffset(int offset)` (0=today..N), using confirmed sleep cmd.
2. Extend `ingestVendorNotification` to route sleep cmd(s) to a simple assembler:
   - Track `total` length, append payload chunks, detect completion.
   - For multi‑day payloads, split per day before parsing.
3. Feed each day’s bytes to `SleepParser` with the correct protocol detection and `currentIndex`.
4. Emit unified result:
   - `dataType: 'SleepDetail'`, `end`, `data: { date, bedTime, wakeTime, segments: [...], totals:{deep,light,rem,awake} }`.
5. Update docs mirroring `docs/hr_integration.md` with byte layout and examples.

**Acceptance criteria**
- Handles day 0 single‑day and multi‑day packets reliably.
- Segments chain without gaps; stage mapping per official mapping.

**Tests**
- Golden vendor payloads (day 0, multi‑day, short/long); edge cases crossing midnight.

---

## 4) Stress (pressure) refinements (cmd 55/54)

**Current**
- Good baseline: offset‑based requests, per‑frame parser, UI demo batches days.

**Improvements**
1. Add a tiny assembler helper that accumulates `payload` arrays and exposes `onComplete` with `{date, intervalMinutes, values}`.
2. Provide `getStressNDays(int days)` in SDK for reuse (sequential with delay, timeout handling).
3. Ensure CRC stripping works across firmwares (some omit trailing CRC; parser already guards this—keep it).

**Acceptance criteria**
- Same outputs across devices with/without CRC; stable timeout behavior.

---

## 5) Blood pressure & Temperature

**Current**
- Request stubs; parsers commented.

**Target behavior**
- Implement classic builders and parsers for history (auto/manual, modes 0/2/99), matching the comment templates.

**Steps**
1. Add request helpers for BP/Temperature history.
2. Port compact parsers from comments into `ResolveUtil`, emitting `dataType: 'BloodPressureData'|'TemperatureData'` with normalized day arrays.
3. Add simple guides in `docs/`.

**Tests**
- Golden frames for per‑record history; malformed tails; mode switching.

---

## 6) Device function support (cmd 60)

**Target behavior**
- Parse support flags and expose a `DeviceSupport` struct; gate optional sync steps.

**Steps**
1. Add `ResolveUtil.parseDeviceFunctionSupport(List<int>)` mapping bytes → booleans (spo2, bp, temp, manual HR, etc.).
2. Route `cmd 60` in `DataParsingWithData`.
3. Update `docs/main_sync.md` flow to branch on flags.

---

## 7) Sync API surface and docs

**Helpers to expose**
- HRV: `getHrvByOffset(offset)`, `getHrvNDays(days)`.
- SpO2: `getBloodOxygenHistory(mode, time?)`, `getAutoBloodOxygen(mode, time?)`.
- Sleep: `getSleepDetailForOffset(offset)`, `getSleepNDays(days)`.
- Stress: `getStressNDays(days)`.

**Docs**
- Add `docs/hrv_integration.md`, `docs/spo2_integration.md`, `docs/sleep_integration.md` with copy‑paste examples like existing HR/Stress guides.

---

## Testing & Verification

**Unit tests**
- For each domain parser: header/first/next/end, early end, short frames, different ranges, date offsets.

**Integration (example app)**
- Buttons per domain for N‑day fetch with progress logs (replace ad‑hoc HRV demo parser).
- Snapshots of final arrays/segments printed for quick manual diff against golden.

**Acceptance**
- Stable across reconnects; no crashes on malformed data; consistent final shapes across domains.

---

## Rollout
1. Ship stress refinements (low risk).
2. Wire sleep ingestion to existing parser (medium risk).
3. Implement HRV assembler and replace demo logic (medium risk).
4. Implement SpO2 classic flows and parser; integrate (medium risk).
5. Add BP/Temp and support flags parsing (optional/low priority).

