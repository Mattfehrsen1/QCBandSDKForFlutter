## 0.1.0

### ✨ New Features
* **Auto-Hold Commands for Live Heart Rate Monitoring**: Implemented automatic keep-alive system that prevents device timeouts during continuous heart rate monitoring
* **Hold Commands for Workout Sessions**: Added hold/keep-alive functionality for sports mode and workout sessions
* **Enhanced Stop Commands**: Proper stop functionality with dual STOP and END commands for complete session termination

### 🔧 Technical Improvements
* Added `holdLiveHeartRate()` function with iOS SDK-compatible action=3 command
* Added `holdWorkOut()` function for workout session persistence
* Enhanced periodic heart rate system to use proper HOLD commands instead of repeated START commands
* Improved logging and error handling for debugging
* Added `ACTION_HOLD` constant for keep-alive operations

### 🐛 Bug Fixes
* Fixed heart rate monitoring timeouts (30-60 seconds) with automatic hold commands
* Proper timer cleanup on device disconnect
* Enhanced device connection state validation

### 📚 Documentation
* Updated function documentation with iOS SDK compatibility notes
* Enhanced logging for better debugging experience

## 0.0.1

* Initial release with basic QC Band SDK functionality
