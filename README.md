# QC Band SDK for Flutter

A Flutter plugin for QC Band fitness trackers with advanced features including automatic hold commands for continuous monitoring.

## Features

### ✨ **Auto-Hold Commands**
- **Continuous Heart Rate Monitoring**: Automatic keep-alive system prevents device timeouts
- **Extended Workout Sessions**: Hold commands maintain active workout monitoring  
- **No More Disconnections**: Eliminates 30-60 second timeouts that cause device vibration
- **iOS SDK Compatible**: Uses proper command structure from official documentation

### 🔧 **Enhanced Functionality**
- Automatic 15-second hold commands for heart rate monitoring
- 30-second hold commands for workout sessions
- Dual STOP and END commands for proper session termination
- Comprehensive error handling and logging
- Memory leak prevention with proper timer cleanup

## Quick Start

### 1. Add to Dependencies
```yaml
dependencies:
  qc_band_sdk_for_flutter: ^0.1.0
```

### 2. Import the SDK
```dart
import 'package:qc_band_sdk_for_flutter/qc_band_sdk_for_flutter.dart';
```

### 3. Start Live Heart Rate Monitoring
```dart
// Send initial START command
await _bluetoothCharacteristic.write(QCBandSDK.liveHeartData(1));

// Setup auto-hold timer (every 15 seconds)
Timer.periodic(Duration(seconds: 15), (timer) async {
  await _bluetoothCharacteristic.write(QCBandSDK.holdLiveHeartRate());
});
```

### 4. Start Workout Monitoring
```dart
// Start workout (sport type 4 = walking)
await _bluetoothCharacteristic.write(QCBandSDK.startWorkOut(4));

// Setup workout hold timer (every 30 seconds)  
Timer.periodic(Duration(seconds: 30), (timer) async {
  await _bluetoothCharacteristic.write(QCBandSDK.holdWorkOut());
});
```

## 📚 Complete Documentation

For detailed implementation guides, examples, and troubleshooting:

### **[📖 SDK Usage Guide](SDK_USAGE_GUIDE.md)**

This comprehensive guide includes:
- **Step-by-step implementation** for both heart rate and workout monitoring
- **Complete code examples** with proper error handling
- **Expected output** and logging examples
- **Memory management** best practices
- **Troubleshooting** common issues
- **Full API reference** with all available functions

## 🚀 New in v0.1.0

- **Automatic Hold Commands**: Prevents 30-60 second timeouts
- **Enhanced Stop Functionality**: Dual stop commands ensure proper cleanup
- **iOS SDK Compatibility**: Uses action=3 hold commands as specified
- **Improved Logging**: Enhanced debugging with emoji-coded messages
- **Memory Leak Prevention**: Proper timer and subscription cleanup

## 📋 Available Functions

### Heart Rate Monitoring
```dart
QCBandSDK.liveHeartData(1)         // START monitoring
QCBandSDK.holdLiveHeartRate()      // HOLD command (prevents timeout)
QCBandSDK.liveHeartData(4)         // STOP monitoring
```

### Workout Sessions
```dart
QCBandSDK.startWorkOut(sportType)  // START workout
QCBandSDK.holdWorkOut()            // HOLD command (prevents timeout)  
QCBandSDK.stopWorkOut()            // STOP workout
```

## 💡 Why Use Auto-Hold Commands?

**Problem**: QC Band devices timeout after 30-60 seconds during live monitoring, causing:
- Device vibration alerts
- Unexpected disconnections
- Interrupted monitoring sessions

**Solution**: Our auto-hold system:
- Sends initial START command to begin monitoring
- Automatically sends HOLD commands every 15 seconds (heart rate) or 30 seconds (workouts)
- Maintains continuous monitoring without timeouts
- Provides proper cleanup with enhanced stop commands

## 🔗 Links

- **[Complete Usage Guide](SDK_USAGE_GUIDE.md)** - Detailed implementation documentation
- **[Changelog](CHANGELOG.md)** - Version history and updates  
- **[iOS SDK Guide](ios_sdk_guide copy.md)** - iOS compatibility documentation

