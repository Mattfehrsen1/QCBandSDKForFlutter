### Goal

Implement Ring on-device sport mode and robust post-session sync, so workouts continue offline and sync when reconnected (matching production app behavior).

### Overview

- Move from phone-controlled sport (cmd 119) to on-device sport mode (vendor "operateSportModeWithType").
- Use the vendor serial-port service (`de5bf728` service; `de5bf72a` write, `de5bf729` notify) with 0xBC header packets.
- Add Sport+ history fetch to retrieve full workout data (HR array, duration, distance, calories) after reconnect.

### Key concepts

- OdmSportPlusExerciseModelType (examples): 4=Walk, 7=Run, 8=Hike, 9=Bike, 10=Other, 20..36, 60, etc.
- QCSportState: 1=Start, 2=Pause, 3=Continue, 4=Stop.
- Live updates: device can still emit 0x78 (120) frames when connected (hr, duration, steps, distance, calories).
- Historical sync (Sport+): 0xBC header, summary 0x41/0x42, details 0x43/0x45.

### Deliverables

1) On-device sport control (start/pause/continue/stop) over vendor serial channel.
2) Sport+ sync: summary fetch + per-session details fetch (HR array and metrics).
3) UI hooks in `example/lib/screens/device_screen.dart` to drive and test the above.
4) Human-readable logs for live frames and historical results.

### Task list

1. Vendor serial helpers (0xBC header)
   - [x] Add Dart helpers in `QCBandSDK` to build 0xBC header packets (reuse `ResolveUtil.addHeader`).
   - [ ] Create a thin wrapper to write/read via `de5bf72a` (write) and listen on `de5bf729` (notify) in `device_screen.dart` (already initialized; just reuse the existing characteristic refs).

2. On-device sport control (operateSportModeWithType)
   - [ ] Research the exact vendor opcode for "operateSportModeWithType" on the serial channel (payload `[sportType, state]`).
   - [x] Implement Dart methods in `QCBandSDK`:
     - `startOnDeviceSport(int sportType)` → vendor serial packet
     - `pauseOnDeviceSport(int sportType)`
     - `continueOnDeviceSport(int sportType)`
     - `stopOnDeviceSport(int sportType)`
   - Note: using tentative opcode 0x40 until confirmed by device logs; adjust if acks indicate a different cmd.
   - [ ] Wire these to buttons in `device_screen.dart` (separate from the phone-controlled 119 flow).

3. Live updates during on-device sport
   - [ ] Keep current listener for 0x78 frames and map: duration (min+sec), hr, steps (24-bit LE), distance (24-bit LE), calories (24-bit LE).
   - [ ] Print readable logs for each 0x78 frame (already added) and update UI state accordingly.
   - [ ] Do not resend cmd 30 repeatedly. Only start once if needed; on-device mode should not require periodic HR restarts.

4. Sport+ history fetch (sync after reconnect)
   - [x] Implement summary request builder (0x41) and parser for 0x42 (chunked buffer) → summaries with fields listed.
   - [x] Implement details request builder (0x43), parse 0x44 meta and 0x45 chunks; extract HR series (key 17) with `sampleSecond`.
   - [ ] Expose both as Dart functions in `QCBandSDK`.
   - [x] Add "Sync Workouts" button in `device_screen.dart` to execute: fetch summaries since last TS → auto-fetch latest details → log decoded output.

5. UI integration
   - [ ] Add sportType dropdown (reuse existing) and new buttons:
     - Start On-Device Sport
     - Pause On-Device Sport
     - Continue On-Device Sport
     - Stop On-Device Sport
   - [ ] Add "Sync Workouts (Sport+)" action that pulls summaries and details; show a concise summary with sample counts and stats.

6. Validation & tests
   - [ ] Live > 10 minutes without 60/120s timeout while using on-device flow.
   - [ ] Walk out of Bluetooth range for 1–5 minutes; return and confirm:
     - The on-device sport continued recording.
     - Sport+ sync retrieves the session with full HR array and totals.
   - [ ] Confirm UI updates from 0x78 when connected.
   - [ ] Confirm logs are readable (one-line decodes with fields labeled).

7. Edge cases & robustness
   - [ ] Handle Android GATT 133 by backing off and reconnecting.
   - [ ] Debounce duplicate 0x78 frames.
   - [ ] Guard against empty/short vendor responses during summary/details fetch.
   - [ ] Ensure no periodic writes that could cause mode exit.

### Notes

- Phone-controlled (119) mode appears to time out without an undocumented keepalive; we will prefer on-device mode instead.
- Historical sync is the authoritative source of the full HR series; live 0x78 frames are for UI only.
- The vendor serial protocol is already present in this project; we will reuse `ResolveUtil.addHeader` and the existing vendor characteristic references.


