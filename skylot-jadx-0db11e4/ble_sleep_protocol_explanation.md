# BLE Large Sleep Data Protocol - Comprehensive Guide

## Overview

This document explains how the BLE (Bluetooth Low Energy) device sends sleep data using the large data protocol (command 0xBC/188 with sub-command 0x27/39). The protocol can handle anywhere from 1 to 7 days of sleep data in a single packet.

## Single Day Sleep Data

When you query sleep data for one day, the device sends a packet with this structure:

```
[0xBC, 0x27, 0x00, 0x0A, 0x04, 0x50, 0x05, 0xA0, 0x02, 0x0A, 0x03, 0x1E]
```

### Packet Header (6 bytes)
- `0xBC` - Command byte (188 in decimal, indicates large data protocol)
- `0x27` - Sub-command byte (39 in decimal, indicates sleep data)
- `0x00, 0x0A` - Total packet length (10 bytes in this example)
- Next 2 bytes would be CRC checksum (not shown in this simple example)

### Sleep Data Structure
- `0x04, 0x50` - Sleep start time (1104 minutes from midnight = 18:24)
- `0x05, 0xA0` - Sleep end time (1440 minutes from midnight = 24:00)
- `0x02, 0x0A` - First sleep segment: type 2 (light sleep), duration 10 minutes
- `0x03, 0x1E` - Second sleep segment: type 3 (deep sleep), duration 30 minutes

### Sleep Type Mappings
- Type 0/1: Other/Unknown → Display Type 5
- Type 2: Light Sleep → Display Type 2
- Type 3: Deep Sleep → Display Type 1
- Type 4: REM Sleep → Display Type 4
- Type 5: Awake → Display Type 3

### Time Calculations
1. **Start Time**: Convert minutes to timestamp: `midnight_timestamp + (1104 * 60) seconds`
2. **Overnight Sleep**: If start time > 1080 minutes (18:00), subtract one day from the date
3. **Segment Times**: Chain segments sequentially from start time
   - First segment: 18:24 to 18:34 (10 minutes of light sleep)
   - Second segment: 18:34 to 19:04 (30 minutes of deep sleep)

## Multi-Day Sleep Data (2-7 Days)

When you query multiple days of sleep data, the device concatenates all day records into one large packet.

### 3-Day Example Structure
```
[Header: 0xBC, 0x27, length_low, length_high, CRC_low, CRC_high]
[Day 0 Data: start0, end0, segment_pairs...]
[Day 1 Header: day_id=1, day_length]
[Day 1 Data: start1, end1, segment_pairs...]
[Day 2 Header: day_id=2, day_length]
[Day 2 Data: start2, end2, segment_pairs...]
```

### 7-Day Example Structure
```
[Main Header: 0xBC, 0x27, total_length, CRC]
[Day 0: start0, end0, segments0...]
[Day 1: 1, length1, start1, end1, segments1...]
[Day 2: 2, length2, start2, end2, segments2...]
[Day 3: 3, length3, start3, end3, segments3...]
[Day 4: 4, length4, start4, end4, segments4...]
[Day 5: 5, length5, start5, end5, segments5...]
[Day 6: 6, length6, start6, end6, segments6...]
```

### Day Record Structure
Each day after the first has this format:
- **Day ID byte**: Which day this record represents (1-6 for days 1-6)
- **Length byte**: How many bytes of sleep data follow (not including the day ID and length bytes)
- **Sleep data**: Same format as single day (start time, end time, segment pairs)

## Packet Length Calculations

### Typical Sizes
- **1 Day**: ~50-150 bytes (depending on sleep segment count)
- **3 Days**: ~200-400 bytes
- **7 Days**: ~500-1000 bytes

### Why Size Varies
- More sleep segments = more bytes needed
- Each sleep segment = 2 bytes (type + duration)
- A night with many wake-ups needs more segments than deep sleep

## Parsing Process (Recursive)

1. **Parse Main Header**: Extract command, sub-command, total length
2. **Parse First Day**: Extract start/end times and sleep segments
3. **Check for More Data**: If current position < total length, continue
4. **Parse Next Day**: Extract day ID, length, then sleep data
5. **Repeat**: Continue until all data in packet is processed

### Code Flow
```
while (current_position < total_packet_length) {
    if (first_day) {
        parse_sleep_data(data, current_position)
    } else {
        day_id = read_byte(current_position)
        day_length = read_byte(current_position + 1)
        parse_sleep_data(data, current_position + 2, day_length)
    }
    current_position += parsed_length
}
```

## Edge Cases and Special Handling

### Overnight Sleep
- If sleep starts after 18:00 (1080 minutes), the date is adjusted backward by one day
- This handles people who go to sleep late and wake up the next day

### Negative Duration Bytes
- Duration bytes can be negative (signed byte values)
- Use `ByteUtil.byteToInt()` to convert properly to positive integers

### Consecutive Same-Type Segments
- The parser merges consecutive segments of the same sleep type
- Example: [Type 2, 10min] + [Type 2, 15min] = [Type 2, 25min]

### Empty Days
- Some days might have no sleep data (length = 0)
- Parser handles this gracefully and continues to next day

## Real-World 7-Day Query Example

When you request 7 days of sleep data, you might get a packet like:

```
Byte Position | Content | Description
0-1          | BC 27   | Command + Sub-command
2-3          | 03 E8   | Total length (1000 bytes)
4-5          | XX XX   | CRC checksum
6-7          | 04 B0   | Day 0: Sleep start (1200 min = 20:00)
8-9          | 06 90   | Day 0: Sleep end (1680 min = 28:00 = 4:00 next day)
10-50        | XX XX   | Day 0: Sleep segments (20 pairs = 40 bytes)
51           | 01      | Day 1 identifier
52           | 2A      | Day 1 length (42 bytes)
53-54        | 05 A0   | Day 1: Sleep start
55-56        | 07 80   | Day 1: Sleep end
57-94        | XX XX   | Day 1: Sleep segments
95           | 02      | Day 2 identifier
... and so on for days 3-6
```

This structure allows the device to send a week's worth of sleep data in one efficient packet, while the parser can extract each day's data individually and convert it into structured sleep information with proper timestamps and segment details.
