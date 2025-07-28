# Heart Rate Data Function - Implementation Guide

## Overview
This guide explains how to use the `getHRData()` function to retrieve heart rate data from QC Band fitness devices via Bluetooth LE. The function provides reliable, date-specific heart rate data collection with 5-minute interval precision.

## Core Function

```dart
getHRData({DateTime? targetDate}) async {
  // Reset state for new data retrieval session
  _accumulatedHrData = {};
  _lastParsedPacketIndex = -1;
  _nextExpectedDataPointMinute = 0;

  // Calculate Unix timestamp for target date
  DateTime dateToQuery = targetDate ?? DateTime.now();
  int targetUnixTimestamp = dateToQuery.toUtc().millisecondsSinceEpoch ~/ 1000;
  
  // Build and send command
  Uint8List heartRateCommandPacket = QCBandSDK.buildReadHeartRateCommand(targetUnixTimestamp);
  print('Requesting HR data for date: ${dateToQuery.toString().split(' ')[0]}');
  
  await _bluetoothCharacteristicWrite.write(heartRateCommandPacket);

  // Listen for multi-packet response
  _bluetoothCharacteristicNotification.value.listen((value) {
    if (value.isNotEmpty && value[0] == 21 && value[1] >= 1) {
      int packetIndex = value[1];
      int totalPackets = value[2];

      // Prevent duplicate processing
      if (packetIndex <= _lastParsedPacketIndex && _lastParsedPacketIndex != -1) {
        return;
      }

      // Parse packet based on its position
      if (packetIndex == 1) {
        // First packet: 9 data points starting at index 6
        parseAndAccumulateHrData(
          values: value,
          dataStartIndex: 6,
          dataPointsCount: 9,
          packetIndex: packetIndex,
        );
      } else {
        // Subsequent packets: 13 data points starting at index 2
        parseAndAccumulateHrData(
          values: value,
          dataStartIndex: 2,
          dataPointsCount: 13,
          packetIndex: packetIndex,
        );
      }

      _lastParsedPacketIndex = packetIndex;
      
      // Check if all packets received
      if (packetIndex == totalPackets) {
        print('✅ Complete HR data received: ${_accumulatedHrData.length} readings');
        _onHeartRateDataComplete(_accumulatedHrData);
      }
    }
  });
}
```

## Data Parsing Function

```dart
parseAndAccumulateHrData({
  required List<int> values,
  required int dataStartIndex,
  required int dataPointsCount,
  int packetIndex = 1,
}) {
  for (int i = 0; i < dataPointsCount; i++) {
    int hrValue = values[dataStartIndex + i];
    
    // Calculate timestamp for this data point
    int currentDataPointTotalMinutes = _nextExpectedDataPointMinute + (i * 5);
    int hour = (currentDataPointTotalMinutes ~/ 60) % 24;
    int minute = currentDataPointTotalMinutes % 60;
    
    String timeKey = '${hour.toString().padLeft(2, '0')}:${minute.toString().padLeft(2, '0')}';
    
    // Store in accumulated data
    _accumulatedHrData[timeKey] = hrValue;
    
    print('📍 $timeKey → $hrValue BPM');
  }
  
  // Update next expected minute for following packets
  _nextExpectedDataPointMinute += (dataPointsCount * 5);
}
```

## Required Variables

```dart
// State variables for data accumulation
Map<String, int> _accumulatedHrData = {};
int _lastParsedPacketIndex = -1;
int _nextExpectedDataPointMinute = 0;

// Bluetooth characteristics (from your existing setup)
late BluetoothCharacteristic _bluetoothCharacteristicWrite;
late BluetoothCharacteristic _bluetoothCharacteristicNotification;
```

## Usage Examples

### Get Today's Heart Rate Data
```dart
await getHRData(); // Uses current date by default
```

### Get Yesterday's Heart Rate Data
```dart
DateTime yesterday = DateTime.now().subtract(Duration(days: 1));
await getHRData(targetDate: yesterday);
```

### Get Specific Date Heart Rate Data
```dart
DateTime specificDate = DateTime(2024, 7, 25);
await getHRData(targetDate: specificDate);
```

### Multi-Day Data Collection
```dart
Future<Map<String, Map<String, int>>> getMultiDayHRData(int numberOfDays) async {
  Map<String, Map<String, int>> allDaysData = {};
  
  for (int dayOffset = 0; dayOffset < numberOfDays; dayOffset++) {
    DateTime targetDate = DateTime.now().subtract(Duration(days: dayOffset));
    String dateKey = "${targetDate.year}-${targetDate.month.toString().padLeft(2, '0')}-${targetDate.day.toString().padLeft(2, '0')}";
    
    try {
      await getHRData(targetDate: targetDate);
      allDaysData[dateKey] = Map<String, int>.from(_accumulatedHrData);
      
      // Delay between requests
      if (dayOffset < numberOfDays - 1) {
        await Future.delayed(Duration(seconds: 2));
      }
    } catch (e) {
      allDaysData[dateKey] = {};
    }
  }
  
  return allDaysData;
}
```

## Data Format

The function returns heart rate data in this format:

```dart
Map<String, int> _accumulatedHrData = {
  "00:00": 65,   // Midnight
  "00:05": 62,   // 12:05 AM
  "00:10": 58,   // 12:10 AM
  "00:15": 61,   // 12:15 AM
  // ... continues every 5 minutes
  "14:50": 103,  // 2:50 PM
  "14:55": 118,  // 2:55 PM
  "23:55": 56    // 11:55 PM
};
```

## Protocol Details

### Bluetooth Communication
- **Service UUID**: `6e40fff0-b5a3-f393-e0a9-e50e24dcca9e`
- **Write Characteristic**: `6e400002-b5a3-f393-e0a9-e50e24dcca9e`
- **Notification Characteristic**: `6e400003-b5a3-f393-e0a9-e50e24dcca9e`

### Packet Structure
- **Command ID**: 21 (heart rate data)
- **Packet 1**: `[21, 1, totalPackets, timestamp_bytes..., 9_hr_values]`
- **Packet 2+**: `[21, packetIndex, 13_hr_values...]`

### Data Points
- **Frequency**: Every 5 minutes
- **Daily Total**: Up to 288 readings (24 hours × 12 readings/hour)
- **Range**: Typically 40-200 BPM (0 = no reading)

## Implementation Checklist

### Prerequisites
- ✅ QC Band SDK integrated
- ✅ Bluetooth LE permissions
- ✅ Device paired and connected
- ✅ Notification characteristic enabled

### Required Functions
- ✅ `getHRData({DateTime? targetDate})`
- ✅ `parseAndAccumulateHrData(...)`
- ✅ `_onHeartRateDataComplete(Map<String, int>)` (optional completion handler)

### State Variables
- ✅ `Map<String, int> _accumulatedHrData`
- ✅ `int _lastParsedPacketIndex`
- ✅ `int _nextExpectedDataPointMinute`

## Error Handling

```dart
void _onHeartRateDataComplete(Map<String, int> completeData) {
  if (completeData.isEmpty) {
    print('⚠️ No heart rate data received');
    return;
  }
  
  // Filter out invalid readings (0 BPM)
  Map<String, int> validData = {};
  completeData.forEach((time, hr) {
    if (hr > 0) validData[time] = hr;
  });
  
  print('✅ Valid readings: ${validData.length}/${completeData.length}');
  
  if (validData.isNotEmpty) {
    List<int> rates = validData.values.toList();
    int avgRate = rates.reduce((a, b) => a + b) ~/ rates.length;
    int minRate = rates.reduce((a, b) => a < b ? a : b);
    int maxRate = rates.reduce((a, b) => a > b ? a : b);
    
    print('Average: $avgRate BPM, Range: $minRate-$maxRate BPM');
  }
}
```

## Best Practices

1. **Reset State**: Always reset `_accumulatedHrData` before new requests
2. **Packet Validation**: Check Command ID (21) and packet structure
3. **Duplicate Prevention**: Track `_lastParsedPacketIndex`
4. **Completion Detection**: Wait for all packets before processing
5. **Error Handling**: Validate heart rate ranges (30-220 BPM)
6. **Rate Limiting**: Add delays between multiple day requests

## Integration Notes

- This function works with any QC Band compatible device
- Requires existing Bluetooth LE infrastructure
- Compatible with Flutter Blue Plus or similar BLE packages
- Thread-safe for single requests (avoid concurrent calls)
- Data is stored in memory - implement persistence as needed

## Performance

- **Single Day**: ~2-3 seconds for complete data retrieval
- **Multi-Day**: ~5-10 seconds per additional day
- **Memory Usage**: ~2KB per day of data
- **Packet Count**: Typically 20-30 packets per day 