# Sleep Data Implementation Guide

## Overview

This guide explains how to implement the sleep data functionality from the QCBandSDKForFlutter package in your Flutter application. The implementation supports both Day 0 (current day) and historical sleep data (Days 1-6) with different packet structures and parsing methods.

## Prerequisites

- Flutter development environment
- QCBandSDKForFlutter package added as a dependency
- BLE device connection established
- `flutter_blue_plus` package for BLE communication

## Package Structure

### Core Components

1. **SleepParser** (`lib/bean/models/sleepModel.dart`) - Main parsing engine
2. **SleepSegment** - Individual sleep stage segments with timestamps
3. **SleepSummary** - Aggregated sleep data with durations and statistics

### Key Classes

```dart
// Sleep segment representing a time period with sleep stage
class SleepSegment {
  final DateTime segmentStart;
  final DateTime segmentEnd;
  final int stageType;
  final int originalQuality;
  final int timeIndex;
  final int sleepIndex;
}

// Sleep summary with aggregated statistics
class SleepSummary {
  final DateTime? bedTime;
  final DateTime? wakeTime;
  final Map<String, int> durations;
  final List<SleepSegment> segments;
}
```

## Implementation Steps

### 1. BLE Communication Setup

```dart
import 'package:flutter_blue_plus/flutter_blue_plus.dart';
import 'package:qc_band_sdk_for_flutter/qc_band_sdk_for_flutter.dart';

class SleepDataManager {
  BluetoothCharacteristic? _writeCharacteristic;
  BluetoothCharacteristic? _notifyCharacteristic;
  
  // Set up BLE characteristics after device connection
  void setupCharacteristics(
    BluetoothCharacteristic writeChar,
    BluetoothCharacteristic notifyChar,
  ) {
    _writeCharacteristic = writeChar;
    _notifyCharacteristic = notifyChar;
  }
}
```

### 2. Sleep Data Request

```dart
// Request sleep data for a specific day (0-6)
Future<List<int>?> requestSleepData(int dayIndex) async {
  if (_writeCharacteristic == null || _notifyCharacteristic == null) {
    throw Exception('BLE characteristics not initialized');
  }

  // Generate command for specific day
  final List<int> command = QCBandSDK.getSleepData(dayIndex);
  
  // Set up response listener
  final completer = Completer<List<int>>();
  late StreamSubscription subscription;
  
  subscription = _notifyCharacteristic!.value.listen((value) {
    if (value.isNotEmpty && value[1] == QcBandSdkConst.getSleepData) {
      // Handle multi-packet assembly if needed
      if (_isCompletePacket(value)) {
        subscription.cancel();
        completer.complete(value);
      }
    }
  });
  
  // Send request
  await _writeCharacteristic!.write(command);
  
  // Wait for response with timeout
  return completer.future.timeout(
    const Duration(seconds: 10),
    onTimeout: () {
      subscription.cancel();
      return null;
    },
  );
}

bool _isCompletePacket(List<int> data) {
  // Implement packet validation logic
  return data.length >= 6 && data[0] == 188 && data[1] == 39;
}
```

### 3. Sleep Data Parsing

```dart
import 'package:qc_band_sdk_for_flutter/bean/models/sleepModel.dart';

// Parse raw BLE data into sleep segments
SleepSummary parseSleepData(List<int> rawData, int dayIndex) {
  // Create parser with day index
  final parser = SleepParser(rawData, currentIndex: dayIndex);
  
  // Parse to segments (handles Day 0 vs Days 1-6 automatically)
  final segments = parser.parseToSegments(null);
  
  // Generate summary with statistics
  return parser.getSleepSummaryWithTimestamps(null);
}
```

### 4. Complete Sleep Data Collection

```dart
class SleepDataCollector {
  final SleepDataManager _manager;
  
  SleepDataCollector(this._manager);
  
  // Collect sleep data for all available days
  Future<Map<int, SleepSummary>> collectAllSleepData() async {
    final Map<int, SleepSummary> sleepData = {};
    
    for (int dayIndex = 0; dayIndex < 7; dayIndex++) {
      try {
        print("Fetching sleep data for day $dayIndex...");
        
        // Request raw data
        final rawData = await _manager.requestSleepData(dayIndex);
        
        if (rawData != null && rawData.isNotEmpty) {
          // Parse data
          final summary = parseSleepData(rawData, dayIndex);
          
          if (summary.bedTime != null && summary.wakeTime != null) {
            sleepData[dayIndex] = summary;
            _displaySleepSummary(summary, dayIndex);
          } else {
            print("Day $dayIndex: No valid sleep data");
          }
        } else {
          print("Day $dayIndex: No data received");
        }
        
        // Small delay between requests
        await Future.delayed(const Duration(milliseconds: 300));
        
      } catch (e) {
        print("Error fetching sleep data for day $dayIndex: $e");
      }
    }
    
    return sleepData;
  }
  
  void _displaySleepSummary(SleepSummary summary, int dayIndex) {
    final dayLabel = _getDayLabel(dayIndex);
    
    print("\n📊 Sleep Summary - $dayLabel (Day $dayIndex):");
    print("  🛏️  Bed Time: ${_formatTime(summary.bedTime!)}");
    print("  🌅 Wake Time: ${_formatTime(summary.wakeTime!)}");
    print("  ⏱️  Total Sleep: ${summary.durations['totalDuration']} minutes");
    print("  📈 Sleep Stage Breakdown:");
    print("    💤 Deep Sleep: ${summary.durations['deepSleep']} min");
    print("    😴 Light Sleep: ${summary.durations['lightSleep']} min");
    print("    👁️  REM Sleep: ${summary.durations['rapidEyeMovement']} min");
    print("    😵 Awake: ${summary.durations['awake']} min");
    print("  🔄 Total Segments: ${summary.segments.length}");
    
    // Show first 5 segments
    if (summary.segments.isNotEmpty) {
      print("  📊 Sleep Timeline (first 5 segments):");
      for (int i = 0; i < math.min(5, summary.segments.length); i++) {
        final segment = summary.segments[i];
        final duration = segment.segmentEnd.difference(segment.segmentStart).inMinutes;
        final stageName = _getSleepStageDisplayName(segment.stageType);
        
        print("    ${i + 1}. ${_formatTime(segment.segmentStart)} → ${_formatTime(segment.segmentEnd)} ($duration min) - $stageName");
      }
      
      if (summary.segments.length > 5) {
        print("    ... and ${summary.segments.length - 5} more segments");
      }
    }
  }
  
  String _getDayLabel(int dayIndex) {
    switch (dayIndex) {
      case 0: return "Today";
      case 1: return "Yesterday";
      case 2: return "2 days ago";
      case 3: return "3 days ago";
      case 4: return "4 days ago";
      case 5: return "5 days ago";
      case 6: return "6 days ago";
      default: return "$dayIndex days ago";
    }
  }
  
  String _formatTime(DateTime dateTime) {
    return "${dateTime.hour.toString().padLeft(2, '0')}:${dateTime.minute.toString().padLeft(2, '0')}";
  }
  
  String _getSleepStageDisplayName(int stageType) {
    switch (stageType) {
      case 1: return "Deep Sleep";
      case 2: return "Light Sleep";
      case 3: return "Awake";
      case 4: return "REM Sleep";
      default: return "Unknown";
    }
  }
}
```

## Usage Example

```dart
class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  SleepDataManager? _sleepManager;
  SleepDataCollector? _collector;
  Map<int, SleepSummary> _sleepData = {};
  
  @override
  void initState() {
    super.initState();
    _initializeSleepData();
  }
  
  void _initializeSleepData() {
    // Initialize after BLE connection is established
    _sleepManager = SleepDataManager();
    _collector = SleepDataCollector(_sleepManager!);
  }
  
  Future<void> _collectSleepData() async {
    try {
      setState(() {
        _isLoading = true;
      });
      
      final data = await _collector!.collectAllSleepData();
      
      setState(() {
        _sleepData = data;
        _isLoading = false;
      });
      
    } catch (e) {
      print("Error collecting sleep data: $e");
      setState(() {
        _isLoading = false;
      });
    }
  }
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Sleep Data')),
      body: Column(
        children: [
          ElevatedButton(
            onPressed: _collectSleepData,
            child: Text('Collect Sleep Data'),
          ),
          Expanded(
            child: ListView.builder(
              itemCount: _sleepData.length,
              itemBuilder: (context, index) {
                final dayIndex = _sleepData.keys.elementAt(index);
                final summary = _sleepData[dayIndex]!;
                
                return SleepDataCard(
                  dayIndex: dayIndex,
                  summary: summary,
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}
```

## Key Features

### Automatic Protocol Detection

The implementation automatically detects and handles different packet formats:

- **Day 0**: Single-day format with times at bytes 9-12
- **Days 1-6**: Multi-day format with standard structure
- **Multi-packet assembly**: Automatically handles large packets split across multiple BLE notifications

### Sleep Stage Types

```dart
const int kStageDeep = 1;      // Deep Sleep
const int kStageShallow = 2;   // Light Sleep  
const int kStageAwake = 3;     // Awake
const int kStageREM = 4;       // REM Sleep
const int kStageUnknown = 5;   // Unknown/Transitional
```

### Error Handling

- Automatic timeout handling for BLE requests
- Packet validation and corruption detection
- Graceful handling of missing or invalid data
- Multi-packet assembly with error recovery

## Best Practices

1. **Connection Management**: Ensure stable BLE connection before requesting sleep data
2. **Request Throttling**: Add delays between requests to avoid overwhelming the device
3. **Error Recovery**: Implement retry logic for failed requests
4. **Data Validation**: Always check for null/empty data before processing
5. **Memory Management**: Process large datasets efficiently to avoid memory issues

## Troubleshooting

### Common Issues

1. **Day 0 shows wrong times**: Ensure protocol detection is working correctly
2. **Multi-packet assembly fails**: Check BLE notification handling and timeout values
3. **Missing sleep stages**: Verify packet structure and parsing logic
4. **Connection timeouts**: Increase timeout values or implement retry logic

### Debug Information

Enable debug output during development:

```dart
// Add this to see detailed parsing information
final parser = SleepParser(rawData, currentIndex: dayIndex);
parser.testTimestampCalculation(); // Only for debugging
```

## Dependencies

Add to your `pubspec.yaml`:

```yaml
dependencies:
  flutter_blue_plus: ^1.32.12
  qc_band_sdk_for_flutter:
    git:
      url: https://github.com/Mattfehrsen1/QCBandSDKForFlutter.git
      ref: main
```

## Support

For issues or questions about the sleep implementation:

1. Check the example app in `/example` folder
2. Review the test methods in `sleepModel.dart`
3. Examine the BLE communication patterns in `device_screen.dart`

This implementation provides robust, production-ready sleep data parsing with comprehensive error handling and multi-format support.