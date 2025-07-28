# QC Band SDK Changes Log

## Project: Multi-Day Sleep Data Sync Debugging & Refactoring

### Objective
To fix, stabilize, and optimize the multi-day sleep data synchronization logic in the Flutter example app. The goal was to correctly handle the BLE device's complex multi-packet responses, eliminate race conditions and deadlocks, and ensure accurate parsing of sleep data for the last 7 days.

### Initial Problems
- **Deadlocks & Timeouts:** The application would frequently hang and timeout when fetching sleep data, particularly for historical days.
- **Race Conditions:** Asynchronous requests for multiple days at once led to mixed data and incorrect parsing.
- **Incorrect Data:** Sleep summaries were often wrong due to a misunderstanding of the device's data protocol, where requesting data for day `N` also returns data required to parse day `N-1`.
- **Stale Data:** The BLE notification listener was not robust enough to filter out old or irrelevant data packets from previous requests.

### Solution and Key Changes

The `sleepDetailData` function in `device_screen.dart` was completely refactored. The final implementation is a robust, sequential process that respects the device's protocol.

1.  **Sequential, Day-by-Day Fetching:**
    *   Implemented a `for` loop to request sleep data one day at a time, from `day 0` (Today) to `day 6` (6 days ago).
    *   Using `async/await` within the loop ensures that the request for one day completes fully before the next one begins, which was the fundamental fix for all race conditions.

2.  **Encapsulated Response Handling (`fetchSingleDayResponse` helper):**
    *   A local helper function was created to manage the logic for a single day's request/response cycle.
    *   It uses a `Completer` to bridge the gap between sending a command and receiving the asynchronous BLE notification. This, combined with a `timeout`, prevents the function from ever hanging indefinitely.

3.  **Protocol-Aware Data Parsing:**
    *   The logic now correctly handles the device's specific protocol:
        *   It fetches data for `Day 0` first.
        *   For subsequent days (`Day 1` to `Day 6`), it uses the data received for the current day (`dayNData`) and the data from the *previous* iteration (`dayNPlus1Data`) to correctly parse the historical sleep summary using `SleepParser.getSleepSummaryYesterday`.

4.  **Intelligent & Simplified BLE Listener:**
    *   The listener for BLE notifications was simplified. After extensive debugging and log analysis, we discovered that a filter checking a specific byte for the day index (`value[6] == day`) was based on a false assumption and was the root cause of the final timeout issues.
    *   The final listener is simple and effective: it waits for the next valid sleep data packet (`value[1] == QcBandSdkConst.getSleepData`) and trusts the sequential flow to ensure it's the correct one.

### Files Modified
*   **`example/lib/screens/device_screen.dart`**: Received a major overhaul of the `sleepDetailData` function and its supporting logic.

### Outcome
The multi-day sleep data synchronization feature is now fully functional, stable, and accurate. It reliably fetches and parses 7 days of sleep data without deadlocks, timeouts, or data corruption, providing a solid foundation for this core feature.

## 2025-07-23 - DeviceDetailStep Function Fix

### Issue Identified
The `deviceDetailStep()` function was receiving data correctly but had three critical bugs:

1. **Wrong completion logic**: `value[5] == value[6] - 1` condition was incorrect
2. **Immediate data clearing**: `jsonData = []` was destroying accumulated data
3. **Local variable scope**: `jsonData` was local, so data disappeared after function ended

### Log Analysis Results
- ✅ Command sent correctly: `[67, 0, 15, 0, 95, ...]`
- ✅ Status packet received: `[67, 240, 8, 0, ...]` (should be ignored)  
- ✅ 8 data packets received: `[67, 37, 7, 35, ...]` with sequential indices 0-7 in `value[5]`
- ✅ Parsing function `parseDetailStepData()` working correctly

### Solution Implemented
**Minimal, surgical fix following Pattern B from other working functions:**

1. **Added class-level accumulator**: `List<Map> _accumulatedStepDetailData = [];`
2. **Fixed packet filtering**: `value[1] == 37` for data packets, `value[1] == 240` for status  
3. **Fixed completion detection**: `value[5] == 7` (last packet index)
4. **Removed data clearing**: Keep accumulated data until explicitly accessed
5. **Added proper error handling**: `onError` callback like other functions

### Files Modified
- `example/lib/screens/device_screen.dart` - Fixed `deviceDetailStep()` function

### Changes Made
1. **Added class-level accumulator**: `List<Map> _accumulatedStepDetailData = [];`
2. **Fixed packet filtering logic**: 
   - Data packets: `value[1] == 37` 
   - Status packets: `value[1] == 240` (ignored)
3. **Fixed completion detection**: `value[5] == 7` (last packet index)
4. **Removed immediate data clearing**: Data persists in class variable
5. **Added proper error handling**: `onError` callback
6. **Added comprehensive logging**: Track packet reception and processing
7. **Added data sorting**: Optional time-based sorting for consistency

### Implementation Results
✅ **COMPLETED**: Function now properly accumulates all 8 step detail packets  
✅ **COMPLETED**: Data persists in `_accumulatedStepDetailData` class variable  
✅ **COMPLETED**: Complete step detail array available after all packets received  
✅ **COMPLETED**: Follows same reliable Pattern B as `getHRData()` and `hrvDetails()`

---

**Status**: ✅ **COMPLETED & FULLY WORKING** 🎉
**Complexity**: Minimal - 95% of original function preserved, only data management fixed
**Risk**: Low - No changes to BLE communication or parsing logic

### ✅ **Testing Results - SUCCESS!**
- **All 8 data packets processed correctly**: ✅
- **Status packet properly ignored**: ✅  
- **Data accumulation working perfectly**: ✅
- **Complete hourly step breakdown**: 7 AM - 2 PM with calories, steps, distance ✅
- **Function follows established patterns**: Same reliability as other working functions ✅

**Final Result**: The `deviceDetailStep()` function now works exactly as intended, providing detailed hourly step data in a properly accumulated format.

### 📅 **Additional Discovery - Historical Data Access**

**Day Offset Parameter**: The function supports historical data retrieval through the `dayOffset` parameter in `QCBandSDK.generateReadStepDetailsCommand(dayOffset, startIndex, endIndex)`:

- **dayOffset = 0**: Today's data (current implementation)
- **dayOffset = 1**: Yesterday's data  
- **dayOffset = 2**: 2 days ago
- **dayOffset = 7**: 1 week ago
- **Range**: 0-29 days (up to 1 month of historical data)

**Usage Examples**:
```dart
// Today (current implementation)
QCBandSDK.generateReadStepDetailsCommand(0, 0, 95);

// Yesterday  
QCBandSDK.generateReadStepDetailsCommand(1, 0, 95);

// 1 week ago
QCBandSDK.generateReadStepDetailsCommand(7, 0, 95);
```

**Time Range Parameters**:
- **startIndex**: 0-95 (time periods within the day)
- **endIndex**: 0-95 (time periods within the day)  
- **Current usage**: `(0, 95)` gets full day data

## 2025-07-23 — Multi-Day Sync Upgrade

- `deviceDetailStep({int daysBack, int dayCount})` now supports **multi-day** fetches.
  - `daysBack`: start offset (0 = today)
  - `dayCount`: number of consecutive days
- Automatic sequential requests, packet counting (8/day), progress logs, chronological sort.
- Added UI button **“Multi Day Step Sync (3 Days)”** for quick testing.
- Back-compatible: default call still returns today only.


