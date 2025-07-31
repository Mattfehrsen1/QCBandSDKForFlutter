# QC Band SDK for Flutter - Usage Guide

## Overview

The QC Band SDK for Flutter provides automatic hold commands for continuous heart rate monitoring and workout sessions, solving the 30-60 second timeout issue that causes device vibration and disconnection.

## Key Features

- **Auto-Hold Heart Rate Monitoring**: Prevents timeouts during continuous heart rate monitoring
- **Workout Session Persistence**: Maintains active workout sessions without disconnection
- **Enhanced Stop Commands**: Proper cleanup with dual stop commands
- **iOS SDK Compatible**: Uses proper command structure from official documentation

---

## 🫀 Live Heart Rate Monitoring

### Overview
The SDK provides an automatic hold system that sends an initial START command, then periodic HOLD commands every 15 seconds to maintain continuous heart rate monitoring.

### Implementation

#### 1. Setup Required Variables
```dart
class YourDeviceScreen extends StatefulWidget {
  // Bluetooth characteristics for communication
  late BluetoothCharacteristic _bluetoothCharacteristicWrite;
  late BluetoothCharacteristic _bluetoothCharacteristicNotification;
  
  // Timer and subscription management
  Timer? _heartRateRepeatingTimer;
  StreamSubscription<List<int>>? _heartRateNotificationSubscription;
}
```

#### 2. Setup Heart Rate Notification Listener
```dart
void _setupHeartRateNotificationListener() {
  // Cancel any existing subscription to prevent memory leaks
  if (_heartRateNotificationSubscription != null) {
    _heartRateNotificationSubscription!.cancel();
    _heartRateNotificationSubscription = null;
    print('DEBUG: Cancelled previous heart rate notification subscription.');
  }

  print('DEBUG: Setting up heart rate notification listener...');
  _heartRateNotificationSubscription =
      _bluetoothCharacteristicNotification.value.listen(
    (value) {
      // Handle the received heart rate data
      if (value.isNotEmpty &&
          value[0] == QcBandSdkConst.cmdGetRealTimeHeartRate) {
        int heartRate = value[1]; // Heart rate value is in second byte
        print('💓 Heart Rate: $heartRate BPM');
        
        // Update your UI here
        // setState(() {
        //   currentHeartRate = heartRate;
        // });
      }
    },
    onError: (error) {
      print('ERROR: Heart rate notification stream error: $error');
    },
    onDone: () {
      print('DEBUG: Heart rate notification stream completed.');
      _heartRateNotificationSubscription = null;
    },
  );
}
```

#### 3. Start Auto-Hold Heart Rate System
```dart
Timer _startHeartRatePeriodicWrite() {
  const intervalDuration = Duration(seconds: 15);

  print('🚀 Starting auto-hold heart rate system every ${intervalDuration.inSeconds} seconds...');

  // Send initial START command
  _bluetoothCharacteristicWrite.write(
    QCBandSDK.liveHeartData(1), // Initial START command [30, 1, ...]
  );
  print('✅ Initial START command sent: [30, 1, ...]');

  // Timer.periodic creates a repeating timer for HOLD commands
  return Timer.periodic(intervalDuration, (Timer timer) async {
    print('🔄 AUTO-HOLD TIMER FIRED - sending hold command');
    await _bluetoothCharacteristicWrite.write(
      QCBandSDK.holdLiveHeartRate(), // Send HOLD command [30, 3, ...]
    );
    print('✅ AUTO-HOLD SUCCESS: Hold command sent at ${DateTime.now()}');
  });
}
```

#### 4. Complete Start Function
```dart
void startLiveHeartRateMonitoring() async {
  try {
    // Setup notification listener first
    _setupHeartRateNotificationListener();
    
    // Start the auto-hold system
    _heartRateRepeatingTimer = _startHeartRatePeriodicWrite();
    
    print('🚀 Live heart rate monitoring started with auto-hold system');
  } catch (e) {
    print('❌ Error starting heart rate monitoring: $e');
  }
}
```

#### 5. Stop Heart Rate Monitoring
```dart
void stopLiveHeartRateMonitoring() {
  print('🛑 Stopping auto-hold heart rate system');
  
  // Stop the periodic timer if it's active
  if (_heartRateRepeatingTimer != null && _heartRateRepeatingTimer!.isActive) {
    _heartRateRepeatingTimer!.cancel();
    _heartRateRepeatingTimer = null;
    print('🛑 Auto-hold timer stopped at ${DateTime.now()}');
  } else {
    print('ℹ️ No active auto-hold timer to stop.');
  }

  // Send STOP commands to ensure monitoring stops
  try {
    _bluetoothCharacteristicWrite.write(QCBandSDK.liveHeartData(4)); // STOP command
    print('✅ STOP command sent: [30, 4, ...] - Heart rate monitoring should stop now');
    
    _bluetoothCharacteristicWrite.write(QCBandSDK.liveHeartData(2)); // END command
    print('✅ END command sent: [30, 2, ...] - Double ensuring stop');
  } catch (e) {
    print('❌ Error sending stop commands: $e');
  }

  // Stop the notification listener
  if (_heartRateNotificationSubscription != null) {
    _heartRateNotificationSubscription!.cancel();
    _heartRateNotificationSubscription = null;
    print('🛑 Heart rate notification listener stopped.');
  } else {
    print('ℹ️ No active heart rate notification listener to stop.');
  }
}
```

### Expected Output

#### Starting Heart Rate Monitoring:
```
🚀 Starting auto-hold heart rate system every 15 seconds...
✅ Initial START command sent: [30, 1, ...]
🚀 Live heart rate monitoring started with auto-hold system
💓 Heart Rate: 0 BPM (initializing)
💓 Heart Rate: 72 BPM
💓 Heart Rate: 74 BPM
💓 Heart Rate: 73 BPM
🔄 AUTO-HOLD TIMER FIRED - sending hold command
✅ AUTO-HOLD SUCCESS: Hold command sent at 2025-07-31 12:15:30.123456
💓 Heart Rate: 75 BPM
💓 Heart Rate: 76 BPM
...continues indefinitely...
```

#### Stopping Heart Rate Monitoring:
```
🛑 Stopping auto-hold heart rate system
🛑 Auto-hold timer stopped at 2025-07-31 12:20:30.123456
✅ STOP command sent: [30, 4, ...] - Heart rate monitoring should stop now
✅ END command sent: [30, 2, ...] - Double ensuring stop
🛑 Heart rate notification listener stopped.
```

---

## 🏃‍♂️ Live Workout Monitoring

### Overview
Similar to heart rate monitoring, the SDK provides hold commands for workout sessions to prevent timeouts during sports activities.

### Implementation

#### 1. Setup Workout Variables
```dart
class YourDeviceScreen extends StatefulWidget {
  Timer? _workoutHoldTimer;
  StreamSubscription<List<int>>? _workoutNotificationSubscription;
}
```

#### 2. Start Workout with Auto-Hold
```dart
void startWorkoutWithAutoHold(int sportType) async {
  try {
    // Send initial workout start command
    await _bluetoothCharacteristicWrite.write(
      QCBandSDK.startWorkOut(sportType), // [119, 1, sportType, ...]
    );
    print('🏃‍♂️ Workout started: Sport type $sportType');
    
    // Setup auto-hold timer for workout (every 30 seconds)
    _workoutHoldTimer = Timer.periodic(Duration(seconds: 30), (timer) async {
      print('🔄 WORKOUT HOLD TIMER FIRED');
      await _bluetoothCharacteristicWrite.write(
        QCBandSDK.holdWorkOut(), // [119, 3, 4, ...]
      );
      print('✅ Workout hold command sent at ${DateTime.now()}');
    });
    
    // Setup workout data listener
    _setupWorkoutNotificationListener();
    
  } catch (e) {
    print('❌ Error starting workout: $e');
  }
}
```

#### 3. Workout Data Listener
```dart
void _setupWorkoutNotificationListener() {
  _workoutNotificationSubscription = 
      _bluetoothCharacteristicNotification.value.listen((value) {
    if (value.isNotEmpty && value[0] == 119) { // Workout command
      print('🏃‍♂️ Workout data received: $value');
      
      // Parse workout data here based on your needs
      // Examples: duration, calories, steps, etc.
    }
  });
}
```

#### 4. Stop Workout
```dart
void stopWorkout() async {
  print('🛑 Stopping workout session');
  
  // Cancel hold timer
  if (_workoutHoldTimer != null && _workoutHoldTimer!.isActive) {
    _workoutHoldTimer!.cancel();
    _workoutHoldTimer = null;
    print('🛑 Workout hold timer stopped');
  }
  
  try {
    // Send stop command
    await _bluetoothCharacteristicWrite.write(QCBandSDK.stopWorkOut());
    print('✅ Workout stop command sent');
  } catch (e) {
    print('❌ Error stopping workout: $e');
  }
  
  // Cancel notification listener
  if (_workoutNotificationSubscription != null) {
    _workoutNotificationSubscription!.cancel();
    _workoutNotificationSubscription = null;
    print('🛑 Workout notification listener stopped');
  }
}
```

### Expected Output

#### Starting Workout:
```
🏃‍♂️ Workout started: Sport type 4
🏃‍♂️ Workout data received: [119, 1, 4, ...]
🔄 WORKOUT HOLD TIMER FIRED
✅ Workout hold command sent at 2025-07-31 12:25:30.123456
🏃‍♂️ Workout data received: [119, 2, 1, 5, 30, ...] (example data)
...continues with hold commands every 30 seconds...
```

#### Stopping Workout:
```
🛑 Stopping workout session
🛑 Workout hold timer stopped
✅ Workout stop command sent
🛑 Workout notification listener stopped
```

---

## 🔧 Available SDK Functions

### Heart Rate Functions
```dart
// Core heart rate functions
QCBandSDK.liveHeartData(1)         // START command [30, 1, ...]
QCBandSDK.liveHeartData(2)         // END command [30, 2, ...]  
QCBandSDK.liveHeartData(3)         // CONTINUE command [30, 3, ...]
QCBandSDK.liveHeartData(4)         // STOP command [30, 4, ...]

// NEW: Auto-hold function
QCBandSDK.holdLiveHeartRate()      // HOLD command [30, 3, ...] (iOS SDK compatible)
```

### Workout Functions
```dart
// Core workout functions
QCBandSDK.startWorkOut(sportType)  // START workout [119, 1, sportType, ...]
QCBandSDK.pauseWorkOut()           // PAUSE workout [119, 2, 4, ...]
QCBandSDK.continueWorkOut()        // CONTINUE workout [119, 3, 4, ...]
QCBandSDK.stopWorkOut()            // STOP workout [119, 4, 4, ...]

// NEW: Auto-hold function
QCBandSDK.holdWorkOut()            // HOLD workout [119, 3, 4, ...]
```

### Constants
```dart
// Action constants
QcBandSdkConst.ACTION_START = 1
QcBandSdkConst.ACTION_PAUSE = 2  
QcBandSdkConst.ACTION_CONTINUE = 3
QcBandSdkConst.ACTION_STOP = 4
QcBandSdkConst.ACTION_HOLD = 5      // NEW: Keep-alive constant

// Command constants
QcBandSdkConst.cmdGetRealTimeHeartRate = 30  // Heart rate command ID
```

---

## 📋 Complete Integration Example

Here's a complete example showing how to integrate both heart rate and workout monitoring:

```dart
class QCBandIntegration {
  // Bluetooth characteristics
  late BluetoothCharacteristic _writeCharacteristic;
  late BluetoothCharacteristic _notificationCharacteristic;
  
  // Timers
  Timer? _heartRateTimer;
  Timer? _workoutTimer;
  
  // Subscriptions
  StreamSubscription<List<int>>? _heartRateSubscription;
  StreamSubscription<List<int>>? _workoutSubscription;
  
  // Start comprehensive monitoring
  void startCompleteMonitoring() {
    // Start heart rate monitoring with auto-hold
    startLiveHeartRateMonitoring();
    
    // Start workout (walking = sport type 4)
    startWorkoutWithAutoHold(4);
  }
  
  // Stop all monitoring
  void stopAllMonitoring() {
    stopLiveHeartRateMonitoring();
    stopWorkout();
  }
  
  // Implementation methods would go here...
  // (Use the implementations from the sections above)
}
```

---

## ⚠️ Important Notes

### Memory Management
- **Always cancel timers** when stopping monitoring to prevent memory leaks
- **Always cancel subscriptions** when disposing of the widget
- Use proper disposal in your widget's `dispose()` method:

```dart
@override
void dispose() {
  stopLiveHeartRateMonitoring();
  stopWorkout();
  super.dispose();
}
```

### Error Handling
- Wrap Bluetooth operations in try-catch blocks
- Check device connection status before sending commands
- Handle stream errors in notification listeners

### Performance Tips
- Don't start multiple monitoring sessions simultaneously
- Use appropriate timer intervals (15s for heart rate, 30s for workouts)
- Stop monitoring when not needed to preserve battery

---

## 🐛 Troubleshooting

### Common Issues

1. **Device Still Disconnects**: 
   - Ensure you're calling the hold functions, not just start commands
   - Check timer intervals (15s for heart rate works best)

2. **No Heart Rate Data**:
   - Verify notification listener is setup before starting monitoring
   - Check that characteristic notifications are enabled

3. **Memory Leaks**:
   - Always cancel timers in dispose() method
   - Cancel subscriptions when stopping monitoring

4. **Commands Not Working**:
   - Verify Bluetooth characteristics are correctly initialized
   - Check device connection status before sending commands

### Debug Logging
Enable comprehensive logging to track the auto-hold system:
```dart
// The SDK includes built-in logging with emojis for easy debugging
// Look for these log patterns:
// 🚀 = Starting operations
// ✅ = Successful operations  
// 🔄 = Timer/periodic operations
// 🛑 = Stopping operations
// ❌ = Errors
// 💓 = Heart rate data
// 🏃‍♂️ = Workout data
```

---

## 📚 Additional Resources

- **iOS SDK Documentation**: Check `ios_sdk_guide copy.md` for iOS compatibility details
- **Command Structure**: All commands follow the iOS SDK specification
- **Changelog**: See `CHANGELOG.md` for version history and updates

This auto-hold system eliminates the 30-60 second timeout issue and provides reliable, continuous monitoring for both heart rate and workout sessions.