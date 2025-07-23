# QC Band SDK Sync Button Analysis & Unified Sync Proposal

## Executive Summary

This document provides a comprehensive analysis of all sync buttons in `device_screen.dart` and their underlying mechanisms. Each button communicates with a Bluetooth device using specific command protocols, handles multi-packet data responses, and implements various parsing strategies for different data types.

## Architecture Overview

### Bluetooth Communication Pattern
All sync operations follow a consistent pattern:
1. **Command Construction**: Using `QCBandSDK` static methods to build BLE command packets
2. **Command Transmission**: Writing to `_bluetoothCharacteristicWrite` 
3. **Response Reception**: Listening to `_bluetoothCharacteristicNotification.value`
4. **Data Parsing**: Custom parsing logic for each data type
5. **Data Accumulation**: Many operations use multi-packet responses requiring data accumulation

### Core Components
- **Primary Write Characteristic**: `_bluetoothCharacteristicWrite`
- **Primary Notification Characteristic**: `_bluetoothCharacteristicNotification`
- **Secondary Write Characteristic**: `_secondbluetoothCharacteristicWrite` (for newer operations)
- **Secondary Notification Characteristic**: `_secondbluetoothCharacteristicNotification`

---

## Detailed Sync Button Analysis

### 1. Battery Data
**Function**: `getDeviceBattery()`
**Command**: `QCBandSDK.GetDeviceBatteryLevel()`
**Purpose**: Retrieves current battery level
**Data Flow**: 
- Single packet response
- Uses `QCBandSDK.DataParsingWithData()` for parsing
- Simple, immediate response pattern

**Key Features**:
- No day-based querying
- Single response packet
- Standard parsing mechanism

---

### 2. Heart Rate Data
**Function**: `getHRData()`
**Command**: `QCBandSDK.buildReadHeartRateCommand(currentUnixTimestamp)`
**Purpose**: Retrieves heart rate data for a specific day
**Data Flow**:
- Multi-packet response system (packets indexed 1, 2, 3, etc.)
- Uses packet accumulation with `_accumulatedHrData` map
- First packet: 9 data points starting at index 6
- Subsequent packets: 13 data points starting at index 2
- 5-minute intervals throughout the day

**Key Features**:
- **Day Support**: Uses Unix timestamp to specify target day
- **Multi-packet handling**: Complex packet indexing and validation
- **Time-based parsing**: Maps data points to specific times (HH:MM format)
- **Data accumulation**: Builds complete daily picture from multiple packets
- **Duplicate prevention**: Tracks `_lastParsedPacketIndex` to avoid reprocessing

**Day Query Mechanism**: 
```dart
int currentUnixTimestamp = DateTime.now().toUtc().millisecondsSinceEpoch ~/ 1000;
Uint8List heartRateCommandPacket = QCBandSDK.buildReadHeartRateCommand(currentUnixTimestamp);
```

---

### 3. Step Data (Today)
**Function**: `stepData()`
**Command**: `QCBandSDK.GetStepOfToday()`
**Purpose**: Gets today's step count summary
**Data Flow**:
- Single packet response
- Uses standard parsing with `QCBandSDK.DataParsingWithData()`
- Immediate response pattern

**Key Features**:
- Fixed to "today" only
- No historical data support
- Simple summary data

---

### 4. Device Details Step Data (Historical)
**Function**: `deviceDetailStep()`
**Command**: `QCBandSDK.generateReadStepDetailsCommand(0, 0, 95)`
**Purpose**: Retrieves detailed historical step data
**Data Flow**:
- Multi-packet response with JSON accumulation
- Custom BCD parsing for date/time data
- Filters packets based on specific criteria (`value[1] != 240 && value[0] == 67`)
- Accumulates data until completion marker (`value[5] == value[6] - 1`)

**Key Features**:
- **Historical Support**: Can retrieve past days
- **Detailed Parsing**: Extracts year, month, day, time, calories, walk steps, distance
- **BCD Conversion**: Uses `bcdToDecimal()` for date parsing
- **Data Accumulation**: Builds JSON array of step details
- **Completion Detection**: Smart packet completion detection

**Day Query Mechanism**:
```dart
// Command parameters: (dayOffset, startIndex, maxRecords)
QCBandSDK.generateReadStepDetailsCommand(0, 0, 95) // Today
// Could be modified for other days
```

---

### 5. HRV Details (Heart Rate Variability)
**Function**: `hrvDetails()`
**Command**: `QCBandSDK.getHRV(1)`
**Purpose**: Retrieves HRV data with complex multi-packet parsing
**Data Flow**:
- Multi-packet system with 4 different packet types
- Complex time-based parsing (30-minute intervals)
- Uses `LinkedHashMap` for ordered data accumulation
- Different parsing logic per packet index

**Key Features**:
- **Multi-day Support**: Parameter can specify different days (0=today, 1=yesterday, etc.)
- **Complex Time Mapping**: Each packet covers different time periods:
  - Packet 1: 00:00-05:30 (12 data points)
  - Packet 2: 06:00-12:30 (14 data points including special handling)
  - Packet 3: 13:00-18:30 (12 data points)
  - Packet 4: 19:00-23:30 (10 data points)
- **Ordered Data**: Uses `LinkedHashMap` to maintain time sequence
- **Advanced Parsing**: Different data extraction logic per packet

**Day Query Mechanism**:
```dart
QCBandSDK.getHRV(1) // 1 for yesterday, 0 for today, etc.
```

---

### 6. Live Heart Rate
**Function**: `liveHeartRate()`
**Command**: `QCBandSDK.liveHeartData(1)`
**Purpose**: Real-time heart rate monitoring
**Data Flow**:
- Continuous streaming data
- Filters for specific command response (`QcBandSdkConst.cmdGetRealTimeHeartRate`)
- Single value extraction from `value[1]`

**Key Features**:
- **Real-time**: Continuous monitoring rather than historical
- **Single Value**: Returns current heart rate
- **Live Streaming**: Ongoing data flow

---

### 7. Sleep Details Data
**Function**: `sleepDetailData()`
**Command**: `QCBandSDK.getSleepData(currentDay)`
**Purpose**: Retrieves sleep analysis for specified day
**Data Flow**:
- Complex historical parsing system
- Different parsing strategies based on day offset
- Uses `SleepParser` and `HistoricalSleepDataParser` classes
- Calculates deep sleep, light sleep, REM, and awake time

**Key Features**:
- **Multi-day Support**: `currentDay` parameter (0=today, 1=yesterday, 2=day before, etc.)
- **Complex Analysis**: Breaks down sleep into phases
- **Historical Parsing**: Different logic for current vs historical data
- **Data Segmentation**: Separates data by date markers
- **Sleep Phase Calculation**: Sums different sleep types based on data patterns

**Day Query Mechanism**:
```dart
int currentDay = 2; // 0=today, 1=yesterday, 2=two days ago, etc.
QCBandSDK.getSleepData(currentDay)
```

---

### 8. Blood Oxygen History
**Function**: `getBloodOxygenDevice()`
**Command**: `QCBandSDK.getBloodOxygen()`
**Purpose**: Retrieves blood oxygen measurement history
**Data Flow**:
- Uses secondary BLE characteristics
- Custom `BloodOxygenEntity` parsing
- Processes raw data into structured entities

**Key Features**:
- **Historical Data**: Retrieves past measurements
- **Entity Parsing**: Converts raw data to structured objects
- **Secondary Characteristics**: Uses newer BLE communication path

---

### 9. Blood Pressure History
**Function**: `getBloodPressureDevice()`
**Command**: `QCBandSDK.getBloodPressure(offset)`
**Purpose**: Retrieves blood pressure measurements with offset support
**Data Flow**:
- Offset-based querying (0-6 range)
- Command validation and error handling
- Raw data logging for analysis

**Key Features**:
- **Offset Support**: Can query different time periods using offset (0-6)
- **Error Handling**: Validates offset range and characteristic availability
- **Range Querying**: Multiple offsets can cover extended time periods

**Day Query Mechanism**:
```dart
int offset = 0; // 0-6 range for different time periods
QCBandSDK.getBloodPressure(offset)
```

---

### 10. Device Time Set
**Function**: `deviceTimeSet()`
**Command**: `QCBandSDK.setDeviceTime(1)`
**Purpose**: Synchronizes device time with current time
**Data Flow**:
- Single command transmission
- Confirmation response handling
- Time synchronization

**Key Features**:
- **Time Sync**: Ensures device time accuracy
- **Single Operation**: One-time command execution
- **Sync Verification**: Can check response for success/failure

---

## 🎯 Dummy's Guide: How to Use Each Sync Function

*This section provides simple, copy-paste examples for each sync function with expected outputs and common usage patterns.*

### 1. Battery Data - `getDeviceBattery()`

**How to Call:**
```dart
// Simple button press handler
TextButton(
  onPressed: () {
    getDeviceBattery(); // That's it!
  },
  child: Text('Get Battery'),
)
```

**Expected Response:**
```json
{
  "batteryLevel": 85,
  "status": "charging",
  "voltage": 4.1
}
```

**What You Get:** Current battery percentage, charging status, and voltage level.

---

### 2. Heart Rate Data - `getHRData()`

**How to Call:**
```dart
// Gets heart rate data for today
TextButton(
  onPressed: () {
    getHRData(); // Automatically uses current day
  },
  child: Text('Get Heart Rate'),
)
```

**Expected Response:**
```json
{
  "00:00": 65,
  "00:05": 68,
  "00:10": 70,
  "00:15": 67,
  "06:00": 72,
  "06:05": 75,
  "12:00": 82,
  "18:30": 78,
  "23:55": 64
}
```

**What You Get:** Heart rate readings every 5 minutes throughout the day, formatted as time → BPM.

**Pro Tips:**
- Data comes in multiple packets (be patient!)
- Check `_accumulatedHrData` for complete results
- Each reading represents a 5-minute interval

---

### 3. Step Data (Today) - `stepData()`

**How to Call:**
```dart
// Get today's step summary
TextButton(
  onPressed: () {
    stepData();
  },
  child: Text('Get Steps'),
)
```

**Expected Response:**
```json
{
  "totalSteps": 8542,
  "distance": 6.2,
  "calories": 312,
  "date": "2025-07-22"
}
```

**What You Get:** Today's step count, distance walked (km), calories burned, and date.

---

### 4. 🔧 Device Details Step Data - **FIXED & ENHANCED**

**Status**: ✅ **FULLY FUNCTIONAL WITH MULTI-DAY SYNC** (Enhanced July 23, 2025)

**Buttons**: 
- "Device Details Step Data" (single day)
- "Multi Day Step Sync (3 Days)" (multi-day) - NEW!

**Function**: `deviceDetailStep({int daysBack = 0, int dayCount = 1})`

**Purpose**: Retrieve detailed step data broken down into 15-minute intervals for single or multiple consecutive days.

## Usage Examples

### Single Day Sync (Backward Compatible)
```dart
// Today only (default)
deviceDetailStep();
// OR explicitly:
deviceDetailStep(daysBack: 0, dayCount: 1);

// Yesterday only
deviceDetailStep(daysBack: 1, dayCount: 1);

// 1 week ago (single day)
deviceDetailStep(daysBack: 7, dayCount: 1);
```

### Multi-Day Sync (NEW FEATURE)
```dart
// Last 3 days (today + yesterday + day before)
deviceDetailStep(daysBack: 0, dayCount: 3);

// 3 days starting from yesterday (skip today)
deviceDetailStep(daysBack: 1, dayCount: 3);

// Full week of data (7 days back to today)
deviceDetailStep(daysBack: 6, dayCount: 7);

// Any single day up to 1 month back
deviceDetailStep(daysBack: 29, dayCount: 1);
```

### Parameters
- **`daysBack`**: How many days back to start from (0 = today, 1 = yesterday, etc.)
- **`dayCount`**: How many consecutive days to sync
- **Range**: 0-29 days back (up to 1 month of history)

**Expected Response:**
```json
[
  {
    "year": 2025,
    "month": 7,
    "day": 22,
    "time": 9.25,  // 9:15 AM (15-minute intervals)
    "calorie": 45,
    "walkSteps": 180,
    "distance": 120  // meters
  },
  {
    "year": 2025,
    "month": 7,
    "day": 22,
    "time": 9.5,   // 9:30 AM
    "calorie": 32,
    "walkSteps": 145,
    "distance": 95
  }
  // ... more 15-minute intervals
]
```

**What You Get:** Array of detailed step data broken down by 15-minute intervals with precise time, calories, steps, and distance.

**Pro Tips:**
- `time` is in decimal hours (9.25 = 9:15 AM)
- Data accumulates in `jsonData` array
- Multiple packets build the complete picture

---

### 5. HRV Details - `hrvDetails()`

**How to Call:**
```dart
// Get HRV (Heart Rate Variability) data
TextButton(
  onPressed: () {
    hrvDetails(); // Currently set to yesterday (parameter=1)
  },
  child: Text('Get HRV'),
)

// To get different days, modify the function:
// QCBandSDK.getHRV(0) // Today
// QCBandSDK.getHRV(1) // Yesterday  
// QCBandSDK.getHRV(2) // 2 days ago
```

**Expected Response:**
```json
{
  "00:00": 25,
  "00:30": 28,
  "01:00": 22,
  "01:30": 30,
  "06:00": 35,
  "06:30": 32,
  "12:00": 40,
  "12:30": 0,   // Special handling - device shows 0 for some readings
  "13:00": 38,
  "19:00": 37,
  "23:30": 28
}
```

**What You Get:** HRV measurements every 30 minutes throughout the day. Higher values generally indicate better recovery.

**Pro Tips:**
- Data comes in 4 packets covering different time periods
- Some values may be 0 (device-specific behavior)
- Uses `LinkedHashMap` to maintain time order

---

### 6. Live Heart Rate - `liveHeartRate()`

**How to Call:**
```dart
// Get real-time heart rate (streaming)
TextButton(
  onPressed: () {
    liveHeartRate(); // Starts live monitoring
  },
  child: Text('Live Heart Rate'),
)
```

**Expected Response:**
```dart
// Continuous stream of single values:
72  // BPM right now
74  // BPM 1 second later
73  // BPM 1 second later
// ... continues until stopped
```

**What You Get:** Real-time heart rate readings as they happen (not historical data).

**Pro Tips:**
- This is live streaming, not stored data
- Values come from `value[1]` in the response
- Keep listening to get continuous updates

---

### 7. Sleep Details Data - `sleepDetailData()` ⚡ **UPDATED JULY 2025**

**How to Call:**
```dart
// Get sleep analysis (updated implementation)
TextButton(
  onPressed: () {
    sleepDetailData();
  },
  child: Text('Get Sleep Data'),
)

// To change the day, modify currentDay in the function:
// int currentDay = 0; // Today (simplified approach)
// int currentDay = 1; // Yesterday (improved dual-packet approach)
// int currentDay = 2; // 2 days ago (improved dual-packet approach)
```

**Expected Response:**
```json
{
  "lightSleep": 245,     // minutes
  "deepSleep": 118,      // minutes  
  "rapidEyeMovement": 87, // minutes (REM sleep)
  "awake": 25,           // minutes awake during night
  "totalSleep": 450,     // total sleep minutes
  "sleepEfficiency": 94,  // percentage
  "date": "2025-07-20"
}
```

**What You Get:** Complete sleep analysis broken down by sleep phases, with totals and efficiency metrics.

**🔄 Latest Implementation Changes (July 2025):**
- **Enhanced Data Handling**: Added `secondQuery` flag to track query state
- **Improved Variable Naming**: Renamed variables to `sleepData1` (query data) and `sleepData2` (-1 day data)
- **Robust Synchronization**: Increased delay between requests to 1 second for better reliability
- **Enhanced SleepParser**: Constructor now accepts optional `currentIndex` parameter
- **New Method**: Added `getSleepSummaryYesterday()` for better historical data processing
- **Streamlined Logic**: Improved condition handling with `currentDay > 0 && currentDay < 7`

**Technical Details:**
- **Current Day (0)**: Single packet using direct notification stream listener
- **Historical Days (1-6)**: Two-packet approach with proper query flag tracking
- **Data Processing Flow**:
  1. Request data for day N-1 (previous day)
  2. Store response in `sleepData1` and set `secondQuery = true`
  3. Request data for day N (current day)
  4. Store response in `sleepData2`
  5. Process both datasets with `getSleepSummaryYesterday()`
- **New SDK Constant**: Uses `QcBandSdkConst.getSleepData = 39` for command identification
- **Improved Error Handling**: Better BLE error management with try/catch blocks

---

### 8. Blood Oxygen History - `getBloodOxygenDevice()`

**How to Call:**
```dart
// Get blood oxygen measurement history
TextButton(
  onPressed: () {
    getBloodOxygenDevice();
  },
  child: Text('Get Blood Oxygen'),
)
```

**Expected Response:**
```json
[
  {
    "timestamp": "2025-07-22T14:30:00Z",
    "oxygenLevel": 98,
    "quality": "good",
    "duration": 30  // measurement duration in seconds
  },
  {
    "timestamp": "2025-07-22T10:15:00Z",
    "oxygenLevel": 97,
    "quality": "excellent",
    "duration": 25
  }
  // ... more historical readings
]
```

**What You Get:** Array of blood oxygen measurements with timestamps, oxygen saturation percentage, measurement quality, and duration.

**Pro Tips:**
- Uses secondary BLE characteristics
- Returns `BloodOxygenEntity` objects
- Historical data from multiple measurement sessions

---

### 9. Blood Pressure History - `getBloodPressureDevice()`

**How to Call:**
```dart
// Get blood pressure history (offset 0 = most recent)
TextButton(
  onPressed: () {
    getBloodPressureDevice(); // Currently uses offset=0
  },
  child: Text('Get Blood Pressure'),
)

// To get different time periods, modify offset in function:
// int offset = 0; // Most recent
// int offset = 1; // Previous period
// int offset = 6; // Oldest available (max)
```

**Expected Response:**
```json
[
  {
    "timestamp": "2025-07-22T09:30:00Z",
    "systolic": 120,
    "diastolic": 80,
    "pulse": 72,
    "quality": "normal"
  },
  {
    "timestamp": "2025-07-21T18:45:00Z",
    "systolic": 118,
    "diastolic": 78,
    "pulse": 68,
    "quality": "good"
  }
  // ... more readings for this offset period
]
```

**What You Get:** Blood pressure readings with systolic/diastolic values, pulse, and quality assessment.

**Pro Tips:**
- Offset range is 0-6 (7 different time periods)
- Use multiple offsets to get extended history
- Function validates offset range automatically

---

### 10. Device Time Set - `deviceTimeSet()`

**How to Call:**
```dart
// Sync device time with current time
TextButton(
  onPressed: () {
    deviceTimeSet();
  },
  child: Text('Sync Time'),
)
```

**Expected Response:**
```json
{
  "status": "success",
  "previousTime": "2025-07-22T14:25:30Z",
  "newTime": "2025-07-22T14:47:03Z",
  "timeZone": "+02:00"
}
```

**What You Get:** Confirmation that time sync was successful with before/after timestamps.

**Pro Tips:**
- This is a one-time operation, not continuous
- Ensures device time accuracy for all other data
- Run this first if you suspect time sync issues

---

## 🚀 Quick Start Template

Here's a complete example showing how to set up and use any sync function:

```dart
class MyDeviceSyncScreen extends StatefulWidget {
  @override
  _MyDeviceSyncScreenState createState() => _MyDeviceSyncScreenState();
}

class _MyDeviceSyncScreenState extends State<MyDeviceSyncScreen> {
  // Your BLE characteristics (set up during device connection)
  late BluetoothCharacteristic _bluetoothCharacteristicWrite;
  late BluetoothCharacteristic _bluetoothCharacteristicNotification;
  
  // Data storage
  Map<String, dynamic> syncedData = {};
  bool isLoading = false;
  
  // Example: Get heart rate data
  void getHeartRateData() async {
    setState(() { isLoading = true; });
    
    try {
      // Send command
      int currentUnixTimestamp = DateTime.now().toUtc().millisecondsSinceEpoch ~/ 1000;
      Uint8List command = QCBandSDK.buildReadHeartRateCommand(currentUnixTimestamp);
      await _bluetoothCharacteristicWrite.write(command);
      
      // Listen for response
      _bluetoothCharacteristicNotification.value.listen((value) {
        if (value.isNotEmpty && value[0] == 21) {
          // Process heart rate data
          // (Use the existing parsing logic from device_screen.dart)
          
          setState(() {
            syncedData['heartRate'] = _accumulatedHrData;
            isLoading = false;
          });
        }
      });
      
    } catch (e) {
      print('Error getting heart rate data: $e');
      setState(() { isLoading = false; });
    }
  }
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Device Sync')),
      body: Column(
        children: [
          ElevatedButton(
            onPressed: isLoading ? null : getHeartRateData,
            child: isLoading 
              ? CircularProgressIndicator() 
              : Text('Get Heart Rate Data'),
          ),
          
          // Display results
          if (syncedData.isNotEmpty)
            Expanded(
              child: ListView(
                children: [
                  Text('Synced Data:', style: TextStyle(fontWeight: FontWeight.bold)),
                  Text(jsonEncode(syncedData)),
                ],
              ),
            ),
        ],
      ),
    );
  }
}
```

## ⚠️ Common Gotchas & Tips

1. **Multi-packet Data**: Heart Rate, HRV, and Step Details come in multiple packets. Don't expect immediate results!

2. **Characteristic Setup**: Make sure your BLE characteristics are properly discovered and connected before calling sync functions.

3. **Day Parameters**: Different functions use different day parameter systems:
   - Heart Rate: Unix timestamps
   - HRV/Sleep: Day offsets (0, 1, 2, etc.)
   - Blood Pressure: Offset ranges (0-6)

4. **Data Accumulation**: Many functions build data over multiple packets. Check the global variables like `_accumulatedHrData`.

5. **Error Handling**: Always wrap sync calls in try-catch blocks and handle BLE disconnections gracefully.

6. **Timing**: Some devices need delays between commands. Don't spam multiple sync calls simultaneously.

---

## Common Patterns & Similarities

### 1. Command-Response Pattern
All sync operations follow the same basic pattern:
```dart
await characteristic.write(command);
characteristic.value.listen((response) => {
  // Parse response
});
```

### 2. Multi-packet Handling
Several operations use sophisticated multi-packet systems:
- **Heart Rate Data**: Packet indexing with accumulation
- **HRV Details**: Time-based packet segmentation  
- **Sleep Data**: Historical data parsing
- **Step Details**: JSON accumulation with completion detection

### 3. Day-based Querying
Multiple operations support historical day queries:
- **Heart Rate**: Unix timestamp-based
- **HRV**: Integer day offset (0, 1, 2, etc.)
- **Sleep**: Integer day offset
- **Blood Pressure**: Offset range (0-6)
- **Step Details**: Day offset in command parameters

### 4. Data Accumulation Strategies
- **Map-based**: Heart Rate and HRV use maps with time keys
- **Array-based**: Step details uses JSON array accumulation
- **Entity-based**: Blood oxygen uses structured entity parsing

---

## Differences & Unique Aspects

### 1. Communication Channels
- **Primary Channel**: Most operations use `_bluetoothCharacteristicWrite/Notification`
- **Secondary Channel**: Blood oxygen and sleep use `_secondbluetoothCharacteristic*`

### 2. Time Representation
- **Unix Timestamp**: Heart rate data
- **Day Offset**: HRV, sleep, step details (0, 1, 2, etc.)
- **Offset Range**: Blood pressure (0-6)
- **Fixed Today**: Basic step data

### 3. Parsing Complexity
- **Simple**: Battery, basic steps (single packet)
- **Moderate**: Live heart rate (streaming single values)
- **Complex**: Heart rate history, HRV (multi-packet with accumulation)
- **Advanced**: Sleep data (multi-parser system with phase analysis)

### 4. Data Types
- **Scalar Values**: Battery level, live heart rate
- **Time Series**: Heart rate history, HRV details
- **Structured Entities**: Blood oxygen measurements
- **Aggregated Analysis**: Sleep phase breakdowns

---

## Unified Sync Approach Proposal

### Core Design Principles

1. **Unified Interface**: Single method to sync any data type for any date range
2. **Parallel Execution**: Multiple data types can be synced simultaneously
3. **Progress Tracking**: Real-time progress updates for long operations
4. **Error Handling**: Robust error recovery and retry mechanisms
5. **Data Aggregation**: Automatic combination of multi-day results

### Proposed Implementation

```dart
class UnifiedSyncManager {
  // Main unified sync method
  Future<Map<String, dynamic>> syncMultipleDays({
    required List<SyncDataType> dataTypes,
    required int daysBack, // How many days to sync (e.g., 3 for last 3 days)
    Function(double progress)? onProgress,
    Function(SyncDataType type, int day, dynamic data)? onDataReceived,
  }) async {
    
    Map<String, dynamic> allResults = {};
    double totalOperations = dataTypes.length * daysBack;
    double completed = 0;
    
    for (SyncDataType dataType in dataTypes) {
      allResults[dataType.name] = {};
      
      for (int dayOffset = 0; dayOffset < daysBack; dayOffset++) {
        try {
          dynamic dayData = await _syncSingleDataTypeForDay(dataType, dayOffset);
          allResults[dataType.name]['day_$dayOffset'] = dayData;
          
          onDataReceived?.call(dataType, dayOffset, dayData);
          completed++;
          onProgress?.call(completed / totalOperations);
          
        } catch (e) {
          print('Error syncing ${dataType.name} for day $dayOffset: $e');
          allResults[dataType.name]['day_$dayOffset'] = {'error': e.toString()};
        }
      }
    }
    
    return allResults;
  }
  
  // Internal method to handle individual data type sync
  Future<dynamic> _syncSingleDataTypeForDay(SyncDataType dataType, int dayOffset) async {
    switch (dataType) {
      case SyncDataType.heartRate:
        return await _syncHeartRateForDay(dayOffset);
      case SyncDataType.hrv:
        return await _syncHRVForDay(dayOffset);
      case SyncDataType.sleep:
        return await _syncSleepForDay(dayOffset);
      case SyncDataType.stepDetails:
        return await _syncStepDetailsForDay(dayOffset);
      case SyncDataType.bloodOxygen:
        return await _syncBloodOxygenForDay(dayOffset);
      case SyncDataType.bloodPressure:
        return await _syncBloodPressureForDay(dayOffset);
      default:
        throw Exception('Unsupported data type: $dataType');
    }
  }
  
  // Specialized methods for each data type
  Future<Map<String, int>> _syncHeartRateForDay(int dayOffset) async {
    DateTime targetDate = DateTime.now().subtract(Duration(days: dayOffset));
    int targetTimestamp = targetDate.toUtc().millisecondsSinceEpoch ~/ 1000;
    
    // Reset accumulation state
    _accumulatedHrData.clear();
    _lastParsedPacketIndex = -1;
    
    // Build and send command
    Uint8List command = QCBandSDK.buildReadHeartRateCommand(targetTimestamp);
    await _bluetoothCharacteristicWrite.write(command);
    
    // Wait for all packets and return accumulated data
    return await _waitForCompleteHeartRateData();
  }
  
  Future<Map<String, int>> _syncHRVForDay(int dayOffset) async {
    _accumulatedHrvData.clear();
    
    await _bluetoothCharacteristicWrite.write(QCBandSDK.getHRV(dayOffset));
    return await _waitForCompleteHRVData();
  }
  
  Future<Map<String, int>> _syncSleepForDay(int dayOffset) async {
    await _secondbluetoothCharacteristicWrite.write(QCBandSDK.getSleepData(dayOffset));
    return await _waitForCompleteSleepData(dayOffset);
  }
  
  // ... additional specialized methods for other data types
}

enum SyncDataType {
  heartRate,
  hrv, 
  sleep,
  stepDetails,
  bloodOxygen,
  bloodPressure,
  battery, // Note: battery is current-only, not historical
  liveHeartRate, // Note: live data, not historical
}
```

### Usage Examples

```dart
// Sync last 3 days of heart rate and HRV data
UnifiedSyncManager syncManager = UnifiedSyncManager();

Map<String, dynamic> results = await syncManager.syncMultipleDays(
  dataTypes: [SyncDataType.heartRate, SyncDataType.hrv, SyncDataType.sleep],
  daysBack: 3,
  onProgress: (progress) => print('Sync progress: ${(progress * 100).toInt()}%'),
  onDataReceived: (type, day, data) => print('Received ${type.name} data for day $day'),
);

// Results structure:
// {
//   'heartRate': {
//     'day_0': {'00:00': 65, '00:05': 68, ...}, // Today
//     'day_1': {'00:00': 62, '00:05': 70, ...}, // Yesterday  
//     'day_2': {'00:00': 67, '00:05': 65, ...}, // 2 days ago
//   },
//   'hrv': {
//     'day_0': {'00:00': 25, '00:30': 28, ...},
//     'day_1': {'00:00': 22, '00:30': 30, ...},
//     'day_2': {'00:00': 27, '00:30': 25, ...},
//   },
//   'sleep': {
//     'day_0': {'lightSleep': 180, 'deepSleep': 120, 'rem': 90, 'awake': 30},
//     'day_1': {'lightSleep': 190, 'deepSleep': 110, 'rem': 95, 'awake': 25},
//     'day_2': {'lightSleep': 175, 'deepSleep': 125, 'rem': 85, 'awake': 35},
//   }
// }
```

### Key Benefits of Unified Approach

1. **Simplified Interface**: One method call for complex multi-day, multi-type syncs
2. **Efficient Execution**: Parallel processing where possible, sequential where required
3. **Consistent Data Format**: Standardized response structure across all data types  
4. **Progress Visibility**: Real-time feedback on sync progress
5. **Error Isolation**: Individual failures don't break entire sync operation
6. **Extensible Design**: Easy to add new data types or modify existing ones

### Implementation Considerations

1. **BLE Characteristic Management**: Ensure proper characteristic availability before operations
2. **Timing Coordination**: Some operations may need delays between commands to avoid device overload
3. **Memory Management**: Large multi-day datasets may require streaming or chunked processing
4. **Retry Logic**: Implement intelligent retry for failed operations
5. **Data Validation**: Verify data integrity and completeness before returning results

---

## Conclusion

The current sync system is highly sophisticated but fragmented across multiple specialized functions. Each data type has evolved its own patterns and optimizations, resulting in a powerful but complex codebase.

The proposed unified approach maintains the sophisticated parsing and multi-packet handling while providing a clean, consistent interface for multi-day, multi-type data synchronization. This would enable your dream scenario of easily syncing "the last 3 days worth of data for all data types" through a single, intuitive method call.

The key to success will be preserving the existing parsing logic while wrapping it in a more user-friendly and unified interface that handles the complexity of coordinating multiple sync operations across different time periods.
