## Sleep integration (shareable overview)

Simple overview of how Sleep works in this SDK, matching the vendor 0xBC serial protocol. The SDK assembles multi‑chunk frames and parses them into normalized segments and totals.

### What’s supported

- Request per‑day sleep details by day offset (0=today, 1..29)
- Vendor serial (0xBC) assembler for sleep (cmd 0x39 or 0x27)
- Parsing into segments with bed/wake timestamps via `SleepParser`
- Normalized callback with totals and segments when complete

### Quick start: Request N days of sleep

1) Enable notifications on both standard and vendor characteristics (your app likely already does this for other features).

2) Subscribe and route vendor frames to the SDK assembler:
```dart
vendorNotifyStream.listen((data) {
  if (data.isNotEmpty) {
    QCBandSDK.ingestVendorNotification(data);
  }
});
```

3) Set a listener to receive the final parsed result:
```dart
QCBandSDK.setSleepListener((m) {
  final data = m['data'] as Map? ?? {};
  final date = data['date'];
  final totals = (data['totals'] as Map?) ?? {};
  final segments = (data['segments'] as List?) ?? const [];
  // Use: date (yyyy-MM-dd), totals {deep, light, rem, awake}, segments array
});
```

4) Build and write the request for a given day offset (0..29):
```dart
final req = QCBandSDK.getSleepDetailForOffset(offset); // 0=today
await vendorWriteCharacteristic.write(req);
```

The SDK will assemble all incoming 0xBC sleep frames (0x39 or 0x27), parse them, and invoke the listener once per completed day payload.

### Normalized output shape

On completion, the listener receives:
```dart
{
  'dataType': 'SleepDetail',
  'end': true,
  'data': {
    'date': 'yyyy-MM-dd',           // day represented by the offset
    'bedTime': 'ISO-8601',          // start of sleep session
    'wakeTime': 'ISO-8601',         // end of sleep session
    'totals': {
      'deep':   int,                // minutes
      'light':  int,
      'rem':    int,
      'awake':  int,
    },
    'segments': [
      {
        'start': 'ISO-8601',
        'end':   'ISO-8601',
        'stage': 1..5               // 1=deep, 2=light, 3=awake, 4=rem, 5=other
      }
    ]
  }
}
```

### Expected logs (helpful during development)

When requesting/assembling sleep:
- `[SleepSDK] Requesting sleep for offset=1 (0=today). Sending vendor cmd 0x27...`
- `[SleepSDK] Vendor frame received (0xBC). Command=0x39, expectedTotalBytes=312, chunkBytes=182`
- `[SleepSDK] Sleep data header. totalBytes=312, forOffset=1 (0=today)`
- `[SleepSDK] Receiving sleep data... 182/312 bytes (58%)`
- `[SleepSDK] Sleep data fully received for offset=1. Parsing...`
- `[SleepSDK] Parsed sleep for 2025-08-05 → segments=21, deep=84, light=236, rem=42, awake=18`
- `[SleepSDK] First segments: 2025-08-04T23:21:00Z to 2025-08-04T23:51:00Z (stage 1); ...`

If you only see `Command=0x2`, that is an ACK from the device. Some days may have no sleep data; the device can ACK and not stream further.

### API reference (sleep)

- `Uint8List QCBandSDK.getSleepDetailForOffset(int offset)`
  - Builds the vendor 0xBC/0x27 request (0..29, clamped). Returns a 16‑byte packet with CRC.

- `void QCBandSDK.ingestVendorNotification(List<int> value)`
  - Feeds vendor notify frames. Assembles 0x39/0x27 sleep streams and triggers parsing on completion.

- `void QCBandSDK.setSleepListener(void Function(Map<String, dynamic> sleep)? listener)`
  - Register/unregister a callback to receive the normalized map when a day’s sleep payload is parsed.

### Example: Request today and yesterday

```dart
Future<void> getTodayAndYesterday() async {
  // Register listener
  QCBandSDK.setSleepListener((m) {
    final data = m['data'] as Map? ?? {};
    final date = data['date'];
    final totals = (data['totals'] as Map?) ?? {};
    print('Sleep $date: deep=${totals['deep']} light=${totals['light']} rem=${totals['rem']} awake=${totals['awake']}');
  });

  // Today (0)
  await vendorWriteCharacteristic.write(QCBandSDK.getSleepDetailForOffset(0));
  await Future.delayed(const Duration(seconds: 2));

  // Yesterday (1)
  await vendorWriteCharacteristic.write(QCBandSDK.getSleepDetailForOffset(1));
}
```

### Differences vs previous implementation

- Adds a clear SDK helper for offset‑based requests (no ad‑hoc payload crafting needed)
- Robust 0xBC assembler for sleep with total‑length buffering and continuation handling
- Uniform, normalized result (`end=true`, totals, segments) using the same `SleepParser`
- Developer‑friendly logs for request, assembly progress, and parsed summary

### Notes

- Offsets: 0=today, 1..29 prior days. If the device has no sleep for a requested day, you may only see a short ACK frame (0x02) and no data payload.
- Only one sleep request at a time per device is recommended.
- The SDK accepts both 0x39 and 0x27 sleep commands on the vendor channel.


