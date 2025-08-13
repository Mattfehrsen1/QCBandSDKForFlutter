Workout control and persistence (final, production-verified)

What works (from production APK behavior and device logs):
- Phone-controlled sport operations are sent over the Nordic UART service (not vendor 0xBC).
- The watch saves sessions, which you then fetch via Sport+ (0x41/0x42 and 0x43/0x45) on the vendor channel.
- No keepalive/hold commands are needed. Use one-shot state changes only.

BLE UUIDs
- Service: `6e40fff0-b5a3-f393-e0a9-e50e24dcca9e`
- Write:   `6e400002-b5a3-f393-e0a9-e50e24dcca9e`
- Notify:  `6e400003-b5a3-f393-e0a9-e50e24dcca9e`

Operate command (phone-controlled, cmd 0x77)
- Packet is 16 bytes. Layout:
  - byte[0]   = 0x77
  - byte[1]   = status
  - byte[2]   = sportType
  - byte[3..14] = 0x00 (padding)
  - byte[15]  = CRC8 (simple sum of bytes[0..14] & 0xFF)
- Status values observed:
  - 1 = Start
  - 2 = Pause
  - 3 = Continue
  - 4 = Stop
  - 6 = Pre-stop (some devices expect 6 before 4)

Minimal examples (sportType 0x07 Running; last byte shown is the computed sum):
- Start:    `[77, 1, 7, 0..0, crc]`
- Pause:    `[77, 2, 7, 0..0, crc]`
- Continue: `[77, 3, 7, 0..0, crc]`
- Pre-stop: `[77, 6, 7, 0..0, crc]`
- Stop:     `[77, 4, 7, 0..0, crc]`

Live metrics (notify 0x78 on 6e400003…)
- Shape from logs (values are little-endian unless noted):
  - `[0]=0x78`
  - `[1]=sportType`
  - `[2..3]=durationSec (LE)`
  - `[4]=heartRate`
  - `[5..7]=steps (24-bit LE)`
  - `[8..10]=distance (24-bit LE)`
  - `[11..13]=calories (24-bit LE)`

Sport+ history fetch (vendor 0xBC)
- Service: `de5bf728-d711-4e47-af26-65e3012a5dc7`
- Write:   `de5bf72a-d711-4e47-af26-65e3012a5dc7`
- Notify:  `de5bf729-d711-4e47-af26-65e3012a5dc7`
- Frame: `[0xBC, cmdId, lenLo, lenHi, crcLo, crcHi, payload…]`, CRC16-IBM over payload, 0xFFFF init, poly 0xA001, CRC inserted LE.
- Summary request (0x41): payload = `[ts:4 LE]`
- Summary notify (0x42): chunked total in len bytes; data starts at offset 6.
- Details request (0x43): payload = `[sportType:1][startTime:4 LE]`
- Details notify (0x45): chunked, same framing.

Recommended sequence
1) Start: write 0x77 with status=1 on Nordic write.
2) During workout: read live via 0x78 from Nordic notify.
3) Pause/Continue: write 0x77 with status=2/3.
4) Stop: write 0x77 with status=6, wait ~100–300 ms, then status=4.
5) After stop: wait ~500–1000 ms, then fetch Sport+ summary (0x41). Request details (0x43) as needed.

Pseudocode (Dart-like)
```
// Start
await standardWrite.write(phoneSportStart(sportType));

// Pause
await standardWrite.write(phoneSportPause(sportType));

// Continue
await standardWrite.write(phoneSportContinue(sportType));

// Stop
await standardWrite.write(phoneSportPreStop(sportType));
await Future.delayed(const Duration(milliseconds: 150));
await standardWrite.write(phoneSportStop(sportType));

// Fetch recently saved sessions
await vendorWrite.write(buildSportPlusSummaryReq(nowSec - 24*3600));
```

Sport types
- Common: 4 (Walking), 5 (Jump Rope), 7 (Running), 8 (Hiking), 9 (Cycling), 10 (Other), and many others (20–36, 60, 80+, etc.). Use the values your device supports.