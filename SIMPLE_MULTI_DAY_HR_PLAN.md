# Simple Multi-Day Heart Rate Implementation Plan

## 🎯 Simple Approach: Sequential Day Requests

**Core Idea**: Instead of trying to parse multi-day data from single requests, simply make multiple sequential requests for different days using the existing proven system.

---

## 🔍 Why This Is Much Simpler

### Current Working System
✅ **Single-day HR collection works perfectly**  
✅ **Real timestamp extraction from packet 1 works**  
✅ **288 readings per day with 5-minute intervals**  
✅ **Multi-packet assembly is reliable**  

### Simple Multi-Day Strategy
🎯 **Use existing system multiple times**  
🎯 **Request yesterday, day before yesterday, etc.**  
🎯 **Combine results into multi-day structure**  
🎯 **Zero changes to packet parsing logic**  

---

## 🚀 Implementation Plan (30 Minutes Total)

### Step 1: Calculate Historical Unix Timestamps (10 minutes)
```dart
// Calculate Unix timestamp for any historical date
int calculateHistoricalTimestamp(int daysBack) {
  DateTime targetDate = DateTime.now().subtract(Duration(days: daysBack));
  DateTime midnightUTC = DateTime.utc(
    targetDate.year,
    targetDate.month, 
    targetDate.day,
    0, 0, 0, 0
  );
  return midnightUTC.millisecondsSinceEpoch ~/ 1000;
}
```

### Step 2: Create Multi-Day Wrapper Function (15 minutes)
```dart
Future<Map<String, Map<String, int>>> getMultiDayHRData(int numberOfDays) async {
  Map<String, Map<String, int>> allDaysData = {};
  
  for (int dayOffset = 0; dayOffset < numberOfDays; dayOffset++) {
    print('🔄 Requesting day $dayOffset (${dayOffset == 0 ? "today" : "$dayOffset days back"})');
    
    // Calculate target date and timestamp
    DateTime targetDate = DateTime.now().subtract(Duration(days: dayOffset));
    String dateKey = "${targetDate.year}-${targetDate.month.toString().padLeft(2, '0')}-${targetDate.day.toString().padLeft(2, '0')}";
    int historicalTimestamp = calculateHistoricalTimestamp(dayOffset);
    
    // Use existing system with historical timestamp
    Map<String, int> dayData = await getSingleDayHRData(historicalTimestamp);
    
    if (dayData.isNotEmpty) {
      allDaysData[dateKey] = dayData;
      print('✅ Collected ${dayData.length} readings for $dateKey');
    } else {
      print('❌ No data for $dateKey');
    }
    
    // Small delay between requests
    if (dayOffset < numberOfDays - 1) {
      await Future.delayed(Duration(seconds: 2));
    }
  }
  
  return allDaysData;
}
```

### Step 3: Modify Existing Function to Accept Custom Timestamp (5 minutes)
```dart
// Modify existing getHRData to accept custom timestamp
getHRData({int? customUnixTimestamp}) async {
  // Use custom timestamp if provided, otherwise use current time
  int targetTimestamp = customUnixTimestamp ?? 
      DateTime.now().toUtc().millisecondsSinceEpoch ~/ 1000;
  
  // Rest of existing function stays exactly the same
  _accumulatedHrData = {};
  _lastParsedPacketIndex = -1;
  _nextExpectedDataPointMinute = 0;
  // ... existing code unchanged
  
  Uint8List heartRateCommandPacket =
      QCBandSDK.buildReadHeartRateCommand(targetTimestamp);
  
  // ... rest of existing code unchanged
}
```

---

## 📋 Simple Task List (3 Tasks Only!)

### Task 1: Add Historical Timestamp Calculation (10 min)
- [ ] Add `calculateHistoricalTimestamp(int daysBack)` function
- [ ] Test timestamp calculation for yesterday, 2 days ago, etc.
- [ ] Validate timestamps are reasonable (midnight UTC)

### Task 2: Create Multi-Day Wrapper (15 min)  
- [ ] Add `getMultiDayHRData(int numberOfDays)` function
- [ ] Implement sequential day requests with delays
- [ ] Add proper date key generation (YYYY-MM-DD format)
- [ ] Add basic error handling and logging

### Task 3: Modify Existing Function (5 min)
- [ ] Add optional `customUnixTimestamp` parameter to `getHRData()`
- [ ] Use custom timestamp when provided
- [ ] Ensure backward compatibility (no parameters = today)

**Total Implementation Time: 30 minutes**

---

## 🎯 Usage Examples

### Get 2 Days of HR Data
```dart
// Simple call to get today + yesterday
var twoDaysData = await getMultiDayHRData(2);

// Result:
// {
//   "2025-01-22": {"00:00": 65, "00:05": 68, ...}, // Today: 288 readings
//   "2025-01-21": {"00:00": 62, "00:05": 70, ...}  // Yesterday: 288 readings
// }
```

### Get a Week of HR Data
```dart
// Get last 7 days
var weekData = await getMultiDayHRData(7);

// Result: 7 days of complete HR data, each with 288 readings
```

### Backward Compatibility
```dart
// Existing code continues to work unchanged
getHRData(); // Still gets today's data

// New functionality
getHRData(customUnixTimestamp: calculateHistoricalTimestamp(1)); // Yesterday
```

---

## ✅ Why This Approach Is Much Better

### 1. **Minimal Risk**
- Uses existing proven packet parsing logic
- No changes to complex multi-packet assembly
- No changes to timestamp extraction from packets
- Existing functionality remains untouched

### 2. **Simple to Implement**
- Only 3 small functions to add
- 30 minutes total implementation time
- Easy to test and debug
- Easy to understand and maintain

### 3. **Reliable**
- Each day request is independent
- Failed day doesn't break other days
- Uses the already-working real timestamp system
- Proven device communication patterns

### 4. **Flexible**
- Can request any number of days
- Can request specific historical dates
- Easy to add features like parallel requests later
- Easy to add retry logic if needed

---

## 🔧 Implementation Strategy

### Development Approach
1. **Add helper function first** (timestamp calculation)
2. **Test with single historical day** (yesterday)
3. **Add multi-day wrapper** (2 days, then 7 days)
4. **Add parameter to existing function** (maintain compatibility)

### Testing Strategy
1. **Test yesterday's data first** (1 day back)
2. **Test 2-day collection** (today + yesterday)
3. **Test with device disconnection** (error handling)
4. **Test with empty days** (weekends, etc.)

### Risk Mitigation
- **Each day independent**: Failed day doesn't break others
- **Existing code unchanged**: No risk to current functionality  
- **Simple fallback**: Can always fall back to single-day requests
- **Clear error messages**: Easy to debug issues

---

## 📊 Expected Results (Same Quality, Much Simpler)

```json
{
  "2025-01-22": {
    "00:00": 65, "00:05": 68, "00:10": 70, // ... 288 readings
    "23:50": 67, "23:55": 64
  },
  "2025-01-21": {
    "00:00": 62, "00:05": 67, "00:10": 69, // ... 288 readings
    "23:50": 65, "23:55": 61  
  }
}
```

**Same precise results, same real timestamps, same reliability - just much simpler to implement!**

---

## 🚀 Success Criteria (Much More Achievable)

- [ ] Can request yesterday's HR data successfully
- [ ] Can request 2 days of HR data (today + yesterday)  
- [ ] Can request 7 days of HR data (full week)
- [ ] Existing single-day functionality unchanged
- [ ] Implementation completed in 30 minutes
- [ ] Zero packet parsing changes needed
- [ ] Backward compatible with existing code

---

**This approach leverages your existing working system instead of rebuilding it!** 