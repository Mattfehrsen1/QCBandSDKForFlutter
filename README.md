# QC Band SDK for Flutter

A comprehensive Flutter plugin for QC Band SDK integration featuring advanced sleep data analysis, dual-protocol BLE communication, and detailed sleep stage processing.

## 🌟 Features

### 🌙 **Advanced Sleep Data Analysis**
- **Dual-Protocol Support**: Handles both Standard Protocol (recent data) and Large Data Protocol (historical data)
- **Official Processing Pipeline**: Implements the exact sleep stage processing from the official Android SDK
- **Detailed Sleep Analytics**: Individual segment breakdowns with precise timestamps
- **Multi-Day Support**: Efficient handling of historical sleep data across multiple days

### 📊 **Sleep Stage Processing**
- **Accurate Stage Mapping**: Deep, Light, REM, Awake, and Unknown sleep stages
- **Segment Merging**: Consecutive same-type segments are intelligently merged
- **Timeline Analysis**: Complete sleep timeline with start/end times for each segment
- **Duration Breakdown**: Total time spent in each sleep stage

### ⚡ **Performance & Reliability**
- **Multi-Packet Assembly**: Handles large BLE responses split across multiple packets
- **Smart Validation**: Multi-level packet validation with day-specific filtering
- **Error Handling**: Comprehensive error handling with graceful fallbacks
- **Production Ready**: Clean API designed for production mobile applications

## 🚀 Quick Start

### Basic Usage

```dart
import 'package:qc_band_sdk_for_flutter/qc_band_sdk_for_flutter.dart';

// Get today's sleep data
SleepSummary todaysSleep = await getSleepForDay(0);

if (todaysSleep.bedTime != null) {
  print("Bed Time: ${todaysSleep.bedTime}");
  print("Wake Time: ${todaysSleep.wakeTime}");
  print("Deep Sleep: ${todaysSleep.durations['deepSleep']} minutes");
  print("Light Sleep: ${todaysSleep.durations['lightSleep']} minutes");
  print("REM Sleep: ${todaysSleep.durations['rapidEyeMovement']} minutes");
  print("Total Segments: ${todaysSleep.segments.length}");
}
```

### Batch Data Fetching

```dart
// Get last week's sleep data
Map<int, SleepSummary> weekData = await getSleepForDays([0, 1, 2, 3, 4, 5, 6]);

for (int day in weekData.keys) {
  SleepSummary daySleep = weekData[day]!;
  print("Day $day: ${daySleep.durations['totalDuration']} minutes");
}
```

### Detailed Sleep Timeline

```dart
SleepSummary sleepData = await getSleepForDay(1); // Yesterday

for (var segment in sleepData.segments) {
  String stageName = segment.stage; // "deep", "light", "rem", "awake"
  int duration = segment.segmentEnd.difference(segment.segmentStart).inMinutes;
  print("${segment.segmentStart.hour}:${segment.segmentStart.minute} - $stageName ($duration min)");
}
```

## 📱 Integration

### API Reference

#### Core Functions

- **`getSleepForDay(int daysAgo)`**: Get sleep data for a single day
- **`getSleepForDays(List<int> days)`**: Get sleep data for multiple days
- **`fetchSingleDayResponse(int day)`**: Low-level BLE data fetching

#### Data Models

- **`SleepSummary`**: Complete sleep analysis with bed/wake times, durations, and segments
- **`SleepSegment`**: Individual sleep stage segment with timestamps
- **`SleepParser`**: Advanced parser with dual-protocol support

### Performance Tips

1. **Use Caching**: Implement local caching to avoid redundant BLE requests
2. **Batch Requests**: Use `getSleepForDays()` for efficient multi-day fetching
3. **Background Sync**: Fetch sleep data in background when app opens
4. **Error Handling**: Always check for null values and handle "no data" cases

## 🔧 Requirements

- Flutter SDK ^3.3.0
- Dart SDK ^3.6.1
- Android: API Level 21+
- iOS: iOS 11.0+

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

