# Sleep Data Sync Implementation Guide

This guide explains how to implement robust two-day sleep data synchronization using the QC Band SDK for Flutter plugin. The implementation handles cumulative sleep data packets and provides accurate sleep summaries for today and yesterday.

## Overview

The QC Band device returns cumulative sleep data when requesting historical data. For example:
- **Day 0 (Today)**: Returns only today's sleep data
- **Day 1 (Yesterday)**: Returns yesterday's sleep data + today's sleep data combined

This implementation correctly separates the data using subtraction and handles missing data gracefully.

## Prerequisites

### Required Dependencies

Add these to your `pubspec.yaml`:

```yaml
dependencies:
  flutter_blue_plus: ^1.0.0  # For BLE communication
  qc_band_sdk_for_flutter: ^1.0.0  # This plugin
```

### Required Imports

```dart
import 'dart:async';
import 'dart:developer';
import 'package:flutter/material.dart';
import 'package:flutter_blue_plus/flutter_blue_plus.dart';
import 'package:qc_band_sdk_for_flutter/qc_band_sdk_for_flutter.dart';
import 'package:qc_band_sdk_for_flutter/utils/qc_band_sdk_const.dart';
```

## Implementation

### 1. Required Class Variables

Add these variables to your device management class:

```dart
class YourDeviceManager extends StatefulWidget {
  // ... your existing code

  @override
  State<YourDeviceManager> createState() => _YourDeviceManagerState();
}

class _YourDeviceManagerState extends State<YourDeviceManager> {
  // BLE Characteristics (required for communication)
  late BluetoothCharacteristic _secondbluetoothCharacteristicNotification;
  late BluetoothCharacteristic _secondbluetoothCharacteristicWrite;
  
  // ... your existing variables
}
```

### 2. BLE Characteristic Setup

Ensure you have properly initialized the BLE characteristics during device connection:

```dart
// During service discovery, find and set up these characteristics:
// Service UUID: de5bf728-d711-4e47-af26-65e3012a5dc7
// Write UUID: de5bf72a-d711-4e47-af26-65e3012a5dc7  
// Notify UUID: de5bf729-d711-4e47-af26-65e3012a5dc7

if (service.uuid.str.toLowerCase().contains('de5bf728-d711-4e47-af26-65e3012a5dc7')) {
  for (final characteristic in service.characteristics) {
    if (characteristic.uuid.str.toLowerCase() == 'de5bf72a-d711-4e47-af26-65e3012a5dc7') {
      _secondbluetoothCharacteristicWrite = characteristic;
    }
    if (characteristic.uuid.str.toLowerCase() == 'de5bf729-d711-4e47-af26-65e3012a5dc7') {
      await characteristic.setNotifyValue(true);
      _secondbluetoothCharacteristicNotification = characteristic;
    }
  }
}
```

### 3. Main Sleep Sync Function

Copy this complete implementation:

```dart
Future<void> getTwoDaySleepSummary() async {
  print("\n=== TWO-DAY SLEEP DATA SYNC ===");

  // Helper to fetch data for a specific day
  Future<List<int>> fetchDayResponse(int day) async {
    final completer = Completer<List<int>>();
    List<int> longestPacket = [];
    Timer? settleTimer;

    // Helper to finish and clean-up
    void finish() {
      if (!completer.isCompleted) {
        completer.complete(longestPacket);
      }
    }

    final subscription = _secondbluetoothCharacteristicNotification.value.listen((value) {
      if (value.isEmpty || value.length < 5) return;
      if (value[1] != QcBandSdkConst.getSleepData) return; // Not sleep data

      // Keep the longest packet encountered – cumulative packets are always longer.
      if (value.length > longestPacket.length) {
        longestPacket = List<int>.from(value);
      }

      // Reset/Start settle timer (400 ms of silence => done)
      settleTimer?.cancel();
      settleTimer = Timer(const Duration(milliseconds: 400), finish);
    });

    // Send the command to request sleep data
    await _secondbluetoothCharacteristicWrite.write(QCBandSDK.getSleepData(day));
    print("Requesting sleep data for day $day...");

    // Global hard timeout safeguard (6 s)
    Future.delayed(const Duration(seconds: 6), () {
      if (!completer.isCompleted) {
        print("Timeout waiting for sleep data for day $day (global timeout).");
        finish();
      }
    });

    final response = await completer.future;

    settleTimer?.cancel();
    await subscription.cancel();

    return response;
  }

  // 1. Fetch data for Day 0 (Today) and Day 1 (Yesterday)
  final day0Response = await fetchDayResponse(0);
  // Add a small delay to ensure the device is ready for the next command
  await Future.delayed(const Duration(milliseconds: 300)); 
  final day1Response = await fetchDayResponse(1);

  if (day0Response.isEmpty) {
    print("Could not retrieve data for Today (Day 0). Aborting.");
    return;
  }
  if (day1Response.isEmpty) {
    print("Could not retrieve data for Yesterday (Day 1). Aborting.");
    return;
  }

  print("Day 0 (Today) raw response (${day0Response.length} bytes): $day0Response");
  print("Day 1 (Yesterday) raw response (${day1Response.length} bytes): $day1Response");

  // 2. Parse the responses to get summaries
  // The actual sleep data starts after a header, at index 13.
  final day0SleepData = day0Response.length > 13 ? day0Response.sublist(13) : <int>[];
  final day1SleepData = day1Response.length > 13 ? day1Response.sublist(13) : <int>[];

  final todaySummary = _parseSleepDataPacket(day0SleepData);
  final yesterdayAndTodaySummary = _parseSleepDataPacket(day1SleepData);

  // 2.5. Validate data integrity and handle edge cases
  final todayTotal = todaySummary['totalDuration'] ?? 0;
  final combinedTotal = yesterdayAndTodaySummary['totalDuration'] ?? 0;
  
  print("Today total: ${todayTotal}min, Combined total: ${combinedTotal}min");
  
  // 3. Analyze data independently for each day
  Map<String, int> yesterdaySummary;
  String todayStatus = "";
  String yesterdayStatus = "";
  
  // Validate today's data
  bool todayHasValidData = _isValidSleepData(todaySummary);
  if (todayHasValidData) {
    todayStatus = "✅ Valid sleep data";
  } else {
    todayStatus = "❌ No sleep data (band not worn or no sleep detected)";
  }
  
  // Determine yesterday's data
  bool combinedHasMoreData = combinedTotal > (todayTotal + 30); // At least 30min more data
  
  if (!combinedHasMoreData) {
    // Yesterday has no data - combined packet is same as today
    yesterdaySummary = _createEmptySummary();
    yesterdayStatus = "❌ No sleep data (band not worn or no sleep detected)";
  } else {
    // Calculate yesterday by subtraction
    yesterdaySummary = {
      'totalDuration': (yesterdayAndTodaySummary['totalDuration'] ?? 0) - (todaySummary['totalDuration'] ?? 0),
      'lightSleep': (yesterdayAndTodaySummary['lightSleep'] ?? 0) - (todaySummary['lightSleep'] ?? 0),
      'deepSleep': (yesterdayAndTodaySummary['deepSleep'] ?? 0) - (todaySummary['deepSleep'] ?? 0),
      'rapidEyeMovement': (yesterdayAndTodaySummary['rapidEyeMovement'] ?? 0) - (todaySummary['rapidEyeMovement'] ?? 0),
      'awake': (yesterdayAndTodaySummary['awake'] ?? 0) - (todaySummary['awake'] ?? 0),
    };
    
    // Validate subtraction results and yesterday's data
    yesterdaySummary = _clampNegativeValues(yesterdaySummary);
    
    bool yesterdayHasValidData = _isValidSleepData(yesterdaySummary);
    if (yesterdayHasValidData) {
      yesterdayStatus = "✅ Valid sleep data (calculated from cumulative data)";
    } else {
      yesterdayStatus = "❌ Insufficient sleep data (less than 30 minutes total)";
    }
  }

  // 4. Print the results
  print("\n=== SLEEP DATA ANALYSIS ===");
  print("Today: $todayStatus");
  print("Yesterday: $yesterdayStatus");
  
  _printSleepSummary("Today (Day 0)", todaySummary, todayHasValidData);
  _printSleepSummary("Yesterday (Day 1)", yesterdaySummary, _isValidSleepData(yesterdaySummary));
}
```

### 4. Helper Functions

Add these helper functions to your class:

```dart
// Helper to parse the raw sleep data pairs
Map<String, int> _parseSleepDataPacket(List<int> sleepData) {
  int lightSleep = 0, deepSleep = 0, remSleep = 0, awake = 0;
  for (int i = 0; i < sleepData.length - 1; i += 2) {
    final type = sleepData[i];
    final duration = sleepData[i + 1];
    // Assuming duration is in minutes and we add a basic sanity check
    if (duration > 0 && duration < 240) { 
      switch (type) {
        case 2: lightSleep += duration; break;
        case 3: deepSleep += duration; break;
        case 4: remSleep += duration; break;
        case 5: awake += duration; break;
      }
    }
  }
  return {
    'totalDuration': lightSleep + deepSleep + remSleep + awake,
    'lightSleep': lightSleep,
    'deepSleep': deepSleep,
    'rapidEyeMovement': remSleep,
    'awake': awake,
  };
}

// Helper to print summaries in a readable format
void _printSleepSummary(String title, Map<String, int> summary, bool isValid) {
  // Clamp values to be non-negative before printing
  final total = summary['totalDuration']?.clamp(0, 1440) ?? 0;
  final hours = total ~/ 60;
  final minutes = total % 60;

  print("\n--- $title ---");
  print("  Total Sleep: ${hours}h ${minutes}min");
  print("  Light: ${summary['lightSleep']?.clamp(0, 1440) ?? 0} min");
  print("  Deep: ${summary['deepSleep']?.clamp(0, 1440) ?? 0} min");
  print("  REM: ${summary['rapidEyeMovement']?.clamp(0, 1440) ?? 0} min");
  print("  Awake: ${summary['awake']?.clamp(0, 1440) ?? 0} min");
  
  if (isValid) {
    print("  Status: Valid sleep data");
  } else {
    print("  Status: Invalid sleep data");
  }
}

Map<String, int> _createEmptySummary() {
  return {
    'totalDuration': 0,
    'lightSleep': 0,
    'deepSleep': 0,
    'rapidEyeMovement': 0,
    'awake': 0,
  };
}

Map<String, int> _clampNegativeValues(Map<String, int> summary) {
  return {
    'totalDuration': summary['totalDuration']?.clamp(0, 1440) ?? 0,
    'lightSleep': summary['lightSleep']?.clamp(0, 1440) ?? 0,
    'deepSleep': summary['deepSleep']?.clamp(0, 1440) ?? 0,
    'rapidEyeMovement': summary['rapidEyeMovement']?.clamp(0, 1440) ?? 0,
    'awake': summary['awake']?.clamp(0, 1440) ?? 0,
  };
}

bool _isValidSleepData(Map<String, int> summary) {
  // Check if any of the sleep categories are non-zero and reasonable (1-480 minutes)
  final lightSleep = summary['lightSleep'] ?? 0;
  final deepSleep = summary['deepSleep'] ?? 0;
  final remSleep = summary['rapidEyeMovement'] ?? 0;
  final awake = summary['awake'] ?? 0;
  final total = summary['totalDuration'] ?? 0;
  
  // Valid if total sleep is at least 30 minutes and individual values are reasonable
  if (total >= 30 && total <= 960) { // 30 minutes to 16 hours
    if ((lightSleep > 0 && lightSleep <= 480) ||
        (deepSleep > 0 && deepSleep <= 480) ||
        (remSleep > 0 && remSleep <= 480)) {
      return true;
    }
  }
  return false;
}
```

### 5. UI Integration

Add a button to trigger the sleep sync:

```dart
ElevatedButton(
  onPressed: () {
    getTwoDaySleepSummary();
  },
  child: Text('Get Today & Yesterday Sleep'),
),
```

## How It Works

### Data Flow

1. **Request Day 0**: Gets today's sleep data only (typically ~39 bytes)
2. **Request Day 1**: Gets cumulative data (yesterday + today, typically ~90+ bytes)
3. **Parse Both**: Extract sleep categories from each packet
4. **Subtract**: Yesterday = (Day 1 Combined) - (Day 0 Today)
5. **Validate**: Check if each day has realistic sleep data
6. **Display**: Show results with clear status messages

### Sleep Data Categories

The device returns sleep data in pairs:
- **Type 2**: Light Sleep (minutes)
- **Type 3**: Deep Sleep (minutes)
- **Type 4**: REM Sleep (minutes)
- **Type 5**: Awake time (minutes)

### Missing Data Handling

The implementation gracefully handles:
- **No data for today**: Shows "No sleep data" for today, yesterday calculation still works
- **No data for yesterday**: Shows "No sleep data" for yesterday, today's data unaffected
- **No data for either day**: Shows "No sleep data" for both days
- **Invalid data**: Filters out unrealistic values and negative results

### Expected Output

```
=== TWO-DAY SLEEP DATA SYNC ===
Today total: 233min, Combined total: 716min
=== SLEEP DATA ANALYSIS ===
Today: ✅ Valid sleep data
Yesterday: ✅ Valid sleep data (calculated from cumulative data)

--- Today (Day 0) ---
  Total Sleep: 3h 53min
  Light: 74 min
  Deep: 101 min
  REM: 52 min
  Awake: 6 min
  Status: Valid sleep data

--- Yesterday (Day 1) ---
  Total Sleep: 8h 3min
  Light: 198 min
  Deep: 144 min
  REM: 123 min
  Awake: 18 min
  Status: Valid sleep data
```

## Important Notes

### Device Requirements

- Device must be connected via BLE
- Device must have sleep data recorded
- Serial port service must be properly initialized

### Data Validation

- Total sleep must be 30-960 minutes (0.5-16 hours)
- Individual categories must be 1-480 minutes (8 hours max)
- Negative values are automatically clamped to 0

### Error Handling

- Timeouts are handled gracefully (6-second maximum wait)
- Empty responses are detected and reported
- Invalid data is filtered out

### Performance

- Uses packet settling (400ms) to ensure complete data reception
- Includes delays between requests to prevent device overload
- Efficient memory usage with proper cleanup

## Troubleshooting

### Common Issues

1. **Timeout errors**: Ensure device is connected and responding
2. **No data returned**: Check if sleep data exists on the device
3. **Negative values**: Implementation automatically handles this
4. **Unrealistic values**: Data validation filters these out

### Debug Tips

- Monitor console output for detailed packet information
- Check raw packet lengths to verify cumulative data
- Verify BLE characteristic setup if no responses received

## Integration Examples

### Simple Integration

```dart
// Just call the function when needed
await getTwoDaySleepSummary();
```

### With Error Handling

```dart
try {
  await getTwoDaySleepSummary();
} catch (e) {
  print('Error syncing sleep data: $e');
  // Handle error appropriately
}
```

### With UI Updates

```dart
setState(() {
  _isLoadingSleep = true;
});

try {
  await getTwoDaySleepSummary();
  // Process results here
} finally {
  setState(() {
    _isLoadingSleep = false;
  });
}
```

This implementation provides robust, production-ready sleep data synchronization that handles all edge cases and provides clear feedback about data quality. 