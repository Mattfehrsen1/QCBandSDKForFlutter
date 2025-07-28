# QC Band SDK for Flutter - Workout Data Parsing Guide

## Overview

The QC Band SDK for Flutter provides comprehensive support for parsing workout (sport) data from BLE fitness bands. This document covers the recent improvements and correct usage patterns for handling workout data.

## Key Improvements Made

### ✅ Fixed Command ID Routing
- **Issue**: Previously used `value[0]` as command ID
- **Fix**: Now correctly uses `value[1]` as the command ID
- **Impact**: Workout data packets are now properly routed to the correct parsing methods

### ✅ Enhanced UI Integration
- Real-time workout metrics visualization
- Live timer during active workouts
- Comprehensive workout data display with metrics cards
- Error handling with user-friendly feedback

### ✅ Robust Error Handling
- Comprehensive data validation for all incoming packets
- Graceful handling of malformed or corrupted data
- Automatic data sanitization and correction
- Detailed logging for debugging

## Workout Data Flow

### 1. Command IDs
```dart
// Sport mode control
static const int sportMode = 0x5A;        // 90 - Start/stop workout
static const int getSportMode = 95;       // Get sport capabilities

// Data packets
Command ID 18:  Sport data from band      // Real workout metrics
Command ID 238: Workout status (start)    // Status notifications  
Command ID 239: Workout status (end)      // Status notifications
```

### 2. Starting a Workout
```dart
// Start workout with sport type selection
void startWorkout() async {
  int? sportType = await _showWorkoutTypeDialog(); // 1=Walking, 2=Running, etc.
  if (sportType == null) return;

  final command = QCBandSDK.sportMode(sportType, 1); // 1 = start
  await characteristic.write(command);
}
```

### 3. Data Structure
```dart
class SportData {
  int mSportType;     // Movement type (1=Walking, 2=Running, etc.)
  int mStartTime;     // Start time (Unix timestamp)
  int mDuration;      // Duration in seconds
  int mDistance;      // Distance in meters
  int mCalorie;       // Calories burned
  int mStep;          // Step count
  double mSpeed;      // Speed in km/h (calculated)
  int mPace;          // Pace in minutes/km (calculated)
  int mHeartRate;     // Average heart rate
  List<int> mHeartRateArr; // Heart rate array
}
```

### 4. Notification Handling
```dart
characteristic.onValueReceived.listen((value) {
  if (value.isEmpty || value.length < 2) return;
  
  int commandId = value[1]; // ✅ Correct: Use value[1] as command ID
  
  if (commandId == 238) {
    // Workout started
    _handleWorkoutStatusUpdate(value);
  } else if (commandId == 18) {
    // Workout data received
    _handleWorkoutDataUpdate(value);
  }
});
```

### 5. Data Parsing with Validation
```dart
void _handleWorkoutDataUpdate(List<int> value) {
  try {
    var result = QCBandSDK.DataParsingWithData(value);
    
    if (result != null && 
        result['method'] == 'getSportMode' && 
        result['data'] is SportData) {
      
      var sportData = result['data'] as SportData;
      
      // Validate data before using
      if (_validateSportData(sportData)) {
        setState(() {
          _currentWorkoutData = sportData;
        });
      }
    }
  } catch (e) {
    print('Error parsing workout data: $e');
  }
}
```

## Sport Types

| ID | Sport Type |
|----|------------|
| 1  | Walking    |
| 2  | Running    |
| 3  | Cycling    |
| 4  | Swimming   |
| 5  | Yoga       |
| 6  | Basketball |
| 7  | Football   |
| 8  | Tennis     |

## UI Components

### Workout Display Widget
The SDK now includes a comprehensive workout display widget that shows:
- Real-time workout status (ACTIVE/COMPLETED)
- Live duration timer
- Metrics grid (sport type, duration, distance, calories, steps, speed)
- Heart rate information
- Start time display

### Error Handling
- Data validation prevents crashes from malformed packets
- User-friendly error messages
- Automatic data correction when possible
- Comprehensive logging for debugging

## Best Practices

### 1. Always Validate Data
```dart
bool _validateSportData(SportData data) {
  if (data.mSportType < 0 || data.mSportType > 100) return false;
  if (data.mDuration < 0) return false;
  if (data.mDistance < 0) return false;
  // ... additional validation
  return true;
}
```

### 2. Handle Errors Gracefully
```dart
try {
  // Parse workout data
} catch (e) {
  // Show user-friendly error message
  Snackbar.show(context, 'Error processing workout data', success: false);
}
```

### 3. Provide User Feedback
```dart
// Show status updates
Snackbar.show(context, 'Workout started (Type: Running)', success: true);

// Display loading states
if (_isWorkoutActive && _currentWorkoutData == null) {
  return CircularProgressIndicator();
}
```

## Testing

To test the workout data parsing:

1. **Start a workout** using the improved UI
2. **Monitor the console** for detailed parsing logs
3. **Verify data validation** by checking edge cases
4. **Test different sport types** to ensure compatibility
5. **Test error scenarios** with corrupted data

## Migration Notes

If updating from a previous version:

1. **Command ID**: Change from `value[0]` to `value[1]`
2. **Error Handling**: Add try-catch blocks around parsing
3. **Data Validation**: Implement validation before using parsed data
4. **UI Updates**: Use the new workout display components

## Common Issues

### Issue: No workout data received
- **Check**: Command ID routing (should use `value[1]`)
- **Check**: Characteristic notifications are enabled
- **Check**: Workout is actually started on the device

### Issue: App crashes on malformed data
- **Solution**: Use the improved `getSportMode` with validation
- **Solution**: Add try-catch blocks around all parsing

### Issue: Incorrect metrics displayed
- **Check**: Data validation is working
- **Check**: Calculation logic for speed/pace
- **Check**: Unit conversions (meters to km, etc.)

---

*This documentation reflects the current state after the workout data parsing handoff improvements.*

