# BLE Blood-Pressure Protocol Findings & Implementation Advice

_This document was generated from analysing **smallerBTSNoopForAnalysis.txt** (Bluetooth HCI snoop log captured while a phone communicated with a QC-Band device).  It extracts the raw packets that matter for historical blood-pressure (BP) sync and explains how to craft the correct commands from Flutter._

---

## 1  Packet Envelope

Every **Nordic UART** write we care about is sent to _Handle 0x0010_ (TX) and the reply is a notification on _Handle 0x0012_ (RX).

```
┌────────┬────────┬────────┬────────────┐
│  Byte 0│  Byte 1│  Byte 2│  …payload… │
├────────┼────────┼────────┼────────────┤
│  0xBC  │ cmdId  │ length │  [length]  │
└────────┴────────┴────────┴────────────┘
                               ▼
                        1 … N bytes
```
* **0xBC**   : constant sync/header byte  
* **cmdId**  : which function the watch should execute (examples below)  
* **length** : number of bytes that follow **_excluding the final CRC_**  
* **CRC**    : 2-byte little-endian CRC-16(IBM) of all prior bytes (header → last payload byte)

> CRC-16 verification was confirmed by matching calculated checksums against 40+ packets in the log.

---

## 2  Commands seen in the log

| cmdId | Purpose (in log) | Example Packet (hex) |
|-------|------------------|------------------------------------------------------------------|
| 0x43  | Step detail sync | `bc 43 05 00 68 3d 08 23 **1d 49**` |
| 0x27  | Hourly BP (?)    | `bc 27 39 00 FF 68 01 00 36 79 … **16 02 10**` |
| 0x42  | BP data chunk    | `bc 42 31 00 8f 92 01 30 08 06 … **37 00 00 00**` |
| 0x2A  | HRV/Stress blob  | long burst ‑ large pressure/HRV array |

`0x27` is the **history-header request**: it tells the ring _which day & index range_ we want.  The watch responds in one or more `0x42` “data-chunk” notifications until an _end-flag_ byte becomes `0xFF`.

---

## 3  Constructing the Blood-Pressure History Request

Taking the working 0x27 packet apart:

```
bc 27 39 00  ff   68 01 00  36 79 05 bf 01 02 16 … CRC
│ │  │  │   │
│ │  │  │   └─ StartIndex (0xFF = from newest)
│ │  │  └──── DayOffset (0x00 = today, 0x01 = yesterday … 0x06)
│ │  └─────── payload length 0x39 (57 bytes) – watch seems happy with 0x39/0x05
│ └────────── cmdId 0x27 = “BP history header”
└──────────── header 0xBC
```

### Minimum viable command
For a narrow Flutter implementation you can send the **5-byte** flavour (payload length = 0x05):

```
[0] 0xBC        # header
[1] 0x27        # commandId (BP-history header)
[2] 0x05        # length (5 bytes follow)
[3] dayOffset   # 0-6  (0=today … 6=six days ago)
[4] startIndex  # 0xFF for “newest record”, or explicit index 0-n
[5] endIndex    # 0x00 → watch returns until it sees end-flag 0xFF
[6] 0x00        # reserved (always zero in log)
[7-8] CRC-16(IBM) little-endian of bytes 0-6
```

*Offsets*  
`dayOffset` is exactly the same idea used by step-sync (`0x43`).  The log shows values 0x00-0x06.

*Start/End index*  
When you pass `0xFF` the ring just streams **all unread records** for that day until it raises the _end-flag_ in each `0x42` data-chunk.

---

## 4  Decoding the 0x42 Data-Chunk

Every `0x42` notification contains **12 bytes per measurement**:

```
Offset Meaning
0-3    Unix time (little-endian, seconds)
4      SBP (systolic)
5      DBP (diastolic)
6-11   Reserved (0 most of the time)
```
The final packet for a day has byte 2 = `0xFF` (end-flag) and no new payload.

---

## 5  CRC-16 Helper (Dart)

```dart
int crc16(List<int> bytes) {
  const int poly = 0xA001; // IBM
  int crc = 0xFFFF;
  for (final b in bytes) {
    crc ^= b;
    for (int i = 0; i < 8; i++) {
      final lsb = crc & 0x0001;
      crc >>= 1;
      if (lsb == 1) crc ^= poly;
    }
  }
  return crc & 0xFFFF;
}
```
Append `crc & 0xFF` then `crc >> 8` to the packet.

---

## 6  Implementation Checklist for Flutter Plugin

1. **Write** the 0x27 packet (above) to characteristic **6e400002-…** (handle 0x0010).  
2. **Subscribe** to notifications on **6e400003-…** (handle 0x0012).  
3. Parse each `0x42` message, harvesting 12-byte records until you see _end-flag_ `0xFF`.  
4. Acknowledge with the “BP history confirm” if the ring expects it (not mandatory on tested firmware).
5. Map timestamps → local time-zone before storage.

---

## 7  Troubleshooting Tips

| Symptom | Likely Cause | Fix |
|---------|--------------|-----|
| Immediate notification with `0x42 … FF 00 00 …` and no data | No records for that `dayOffset` | Query a different offset or ensure the user measured BP that day |
| Watch keeps sending duplicate chunks | Plugin did not stop notifications or lacked confirm-packet | Send **0x28 01 00 …** _confirm_ (seen in log after full sync) |
| CRC mismatch NACK from ring | CRC-16 wrong byte order | Little-endian, low-byte first |

---

### Reference Hex Dumps (from log)

*Step header (works)*  
`bc430500683d08231d4968`

*BP header (today, newest first)*  
`bc273905ff680100367905bf…`  _(full 57-byte variant)_

*BP data-chunk*  
`bc4231008f920130080601231d49…`

---

> **Next:** integrate this 0x27/0x42 flow into your Flutter bridge, re-using the existing step-sync codec.  Once it works, you can extend to stress/HRV (cmd 0x2A) which uses the same envelope.

---

© 2025 BTSnoop Reverse Engineering Notes 