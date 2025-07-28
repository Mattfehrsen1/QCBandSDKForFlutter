# QC Band BLE Implementation Analysis

## 🔍 **Current Implementation Analysis**

### Current BLE Service & Characteristics
From `lib/utils/qc_band_sdk_const.dart` and `example/lib/const.dart`:

```dart
// Primary Service
uuidService = '6e40fff0-b5a3-f393-e0a9-e50e24dcca9e'

// Characteristics 
uuidRead    = '6e400003-b5a3-f393-e0a9-e50e24dcca9e'  // Notifications
uuidWrite   = '6e400002-b5a3-f393-e0a9-e50e24dcca9e'  // Write commands

// Secondary Serial Port Service  
serialPortService = 'de5bf728-d711-4e47-af26-65e3012a5dc7'
serialPortNotify  = 'de5bf729-d711-4e47-af26-65e3012a5dc7'  // Notifications
serialPortWrite   = 'de5bf72a-d711-4e47-af26-65e3012a5dc7'  // Write commands
```

### Current Sport Mode Commands

#### 1. Start/Stop Workout Command
```dart
QCBandSDK.sportMode(int sportType, int status)
// Command Structure:
// [0] = 0x29 (startSportMode)
// [1] = sportType (1-8 for different activities)
// [2] = status (1=start, 2=stop, 3=pause, 4=continue)
```

#### 2. Get Sport Data Command
```dart
// Command ID: 95 (getSportMode) 
// Command ID: 18 (alternative sport data)
// Both route to: ResolveUtil.getSportMode(value)
```

### Current Data Flow Issues

#### ❌ **Problem 1: Wrong Data Type**
- **Current**: We send `sportMode` command and listen for `getSportMode` responses
- **Issue**: `getSportMode` returns **historical workout summaries**, not real-time data
- **Evidence**: Duration stuck at 4 seconds, steps fluctuating wildly

#### ❌ **Problem 2: Wrong Notification Handling**
- **Current**: Listening on `uuidRead` characteristic for all data
- **Issue**: Real-time sport data likely comes on different characteristic or uses different command structure
- **Evidence**: The data doesn't match expected real-time patterns

#### ❌ **Problem 3: Packet Structure Mismatch**
- **Current**: Parsing as historical summary (16+ bytes with timestamps)
- **Should Be**: Real-time data (14 bytes with live metrics)

## 🎯 **Required Changes**

### 1. Identify Real-Time Sport Data Characteristic

Based on SDK documentation patterns, real-time data likely uses:
- **Same service**: `6e40fff0-b5a3-f393-e0a9-e50e24dcca9e`
- **Different command flow**: Start workout → listen for real-time notifications
- **Different command IDs**: Not 18 or 95, but something like 0x78 (Android SDK reference)

### 2. Correct Packet Structure

From Android SDK documentation, real-time sport data should be:
```
byte[0]: Sport type
byte[1]: Error status  
byte[2-3]: Duration (seconds) - 16-bit little-endian
byte[4]: Heart rate
byte[5-7]: Steps (24-bit little-endian)
byte[8-10]: Distance (24-bit little-endian) 
byte[11-13]: Calories (24-bit little-endian)
```

### 3. Proper Workflow

**Current Flow:**
1. Send `sportMode` command
2. Device starts workout  
3. App requests `getSportMode` data periodically
4. Device returns historical summary

**Required Flow:**
1. Send `sportMode` command to start workout
2. Device confirms workout started
3. Device automatically sends real-time notifications
4. App listens and parses real-time data
5. Send `sportMode` stop command when done

## 📋 **Implementation Plan**

### Phase 1: Research & Discovery
1. ✅ **Document current implementation** 
2. 🔄 **Packet sniffing**: Monitor actual BLE traffic during workouts
3. 🔄 **Command ID discovery**: Find real-time data command ID
4. 🔄 **Characteristic verification**: Confirm which characteristic carries real-time data

### Phase 2: Fix Implementation
1. 🔄 **Add real-time data parser**: Create `RealTimeSportParser.dart`
2. 🔄 **Update notification routing**: Handle real-time command IDs
3. 🔄 **Fix UI integration**: Use real-time data instead of historical
4. 🔄 **Add proper session management**: Track workout state properly

## 🔬 **Testing Strategy**

### 1. Packet Analysis
- Monitor BLE traffic during actual workout
- Compare historical vs real-time packet structures
- Document actual command IDs used

### 2. Incremental Testing
- Test new parser with known good data
- Verify real-time notifications are received
- Confirm data updates every second during workout

### 3. Validation
- Duration increments properly (not stuck)
- Steps only increase (never decrease)
- Distance progresses smoothly
- Realistic calorie calculation

## 🚨 **Current Status**

- ✅ **Analysis Complete**: Identified root cause
- 🔄 **Research Phase**: Need to find real-time command IDs
- ⏳ **Implementation**: Ready to begin fixes
- ⏳ **Testing**: Pending implementation

## 📝 **Key Findings**

1. **Primary Issue**: Using historical data API instead of real-time data stream
2. **Command Mix-up**: `getSportMode` (cmd 95) is for historical data, not live data
3. **Packet Structure**: Current parsing expects wrong byte layout
4. **Notification Flow**: Missing proper real-time data listener
5. **Session Management**: Lacks proper workout lifecycle handling

This analysis provides the foundation for implementing the correct real-time sport data parsing system. 