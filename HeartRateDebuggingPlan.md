# Heart Rate Data Parsing Debug Plan - July 26th, 2025

## 🎯 Mission Statement

**GOAL**: Fix the heart rate data parsing bug where high values (110, 145, 171, 178 BPM) appear at wrong timestamps (19:40-19:50) instead of correct timestamps (12:40-12:55).

**ROOT CAUSE**: 7-hour timing offset suggesting timezone calculation issue in timestamp mapping logic.

## 📊 Current Problem Analysis

### Known Good Data (Reference)
```
July 24th:
00:00 → 57 BPM
00:05 → 55 BPM  
00:10 → 51 BPM
...
18:10 → 99 BPM
18:15 → 170 BPM
18:20 → 164 BPM
18:25 → 176 BPM
18:30 → 88 BPM

July 25th:
00:00 → 57 BPM
00:05 → 57 BPM
00:10 → 50 BPM
...
12:40 → 110 BPM ← TARGET VALUES
12:45 → 145 BPM ← TARGET VALUES  
12:50 → 171 BPM ← TARGET VALUES
12:55 → 178 BPM ← TARGET VALUES
```

### Current Bug Symptoms
- High values appear at 19:40-19:50 instead of 12:40-12:55
- **7-hour offset** consistently observed
- All other heart rate values also shifted by same amount
- Device timestamp extraction working correctly
- Packet data extraction working correctly
- **Issue**: Timestamp calculation/mapping logic

## 🔄 Systematic Debugging Plan

### **PHASE 1: Reset to Plugin Guide Implementation**
**Objective**: Start with proven baseline implementation

#### 1.1 Implement Clean Plugin Guide Code
- [ ] Remove all recent debugging modifications
- [ ] Implement exact `getHRData()` from Plugin_Guide.md
- [ ] Implement exact `parseAndAccumulateHrData()` from Plugin_Guide.md  
- [ ] Implement exact `extractRealTimestamp()` from Plugin_Guide.md
- [ ] Ensure packet parsing follows guide specifications:
  - Packet 1: 9 data points starting at byte index 6
  - Packets 2+: 13 data points starting at byte index 2

#### 1.2 Verify Baseline Functionality
- [ ] Test heart rate sync for July 26th data
- [ ] Confirm 288 readings collected
- [ ] Confirm all packets received properly
- [ ] Confirm real timestamp extracted from packet 1

### **PHASE 2: Timestamp Investigation**
**Objective**: Identify exact source of 7-hour offset

#### 2.1 Analyze calculateHistoricalTimestamp() Function
- [ ] Read current `calculateHistoricalTimestamp(int daysBack)` implementation
- [ ] Test with `daysBack = 0` (today, July 26th)
- [ ] Verify Unix timestamp calculation matches expected
- [ ] Check timezone handling (UTC vs local time)

#### 2.2 Compare Sent vs Received Timestamps
- [ ] Log timestamp we send to device in command
- [ ] Log timestamp device returns in packet 1, bytes 2-5
- [ ] Calculate difference between sent and received
- [ ] Determine if difference matches the 7-hour offset observed

#### 2.3 Timezone Validation
- [ ] Check if device operates in UTC
- [ ] Check if app calculations use local timezone
- [ ] Verify `DateTime.now()` vs `DateTime.now().toUtc()` usage
- [ ] Test with explicit timezone conversions

### **PHASE 3: Fresh Data Collection & Analysis**
**Objective**: Test with July 26th data and track high values

#### 3.1 Targeted Data Collection
- [ ] Run heart rate sync for July 26th
- [ ] Track where high values (>100 BPM) appear in timeline
- [ ] Log exact reading indices for high values
- [ ] Compare expected vs actual reading indices

#### 3.2 Index Calculation Verification
- [ ] For 12:40 → should be reading index 152 (12*60+40)/5
- [ ] For 12:45 → should be reading index 153
- [ ] For 12:50 → should be reading index 154  
- [ ] For 12:55 → should be reading index 155
- [ ] Check if high values appear at indices 152+84 = 236-239 (7 hour offset)

#### 3.3 Data Mapping Analysis
- [ ] Create verification function to find where specific BPM values appear
- [ ] Map actual indices to timestamps using current logic
- [ ] Identify exact offset pattern (should be 84 readings = 7 hours)

### **PHASE 4: Root Cause Analysis**
**Objective**: Pinpoint exact bug location

#### 4.1 Timestamp Source Investigation
Investigate these potential causes:
- [ ] **Device timezone**: Device might store data in different timezone than expected
- [ ] **Unix timestamp calculation**: `calculateHistoricalTimestamp()` using wrong timezone
- [ ] **Reading index calculation**: `absoluteReadingIndex` formula incorrect
- [ ] **Base timestamp interpretation**: Device start timestamp interpretation wrong

#### 4.2 Timeline Calculation Testing
- [ ] Test with hardcoded known good timestamps
- [ ] Verify 5-minute increment logic
- [ ] Check for off-by-one errors in index calculations
- [ ] Validate 24-hour wraparound handling

### **PHASE 5: Fix Implementation**
**Objective**: Implement targeted fix based on root cause

#### 5.1 Apply Timezone Fix
Based on Phase 2-4 findings, implement one of:
- [ ] **Option A**: Convert device timestamps from UTC to local time
- [ ] **Option B**: Convert app calculations from local to UTC
- [ ] **Option C**: Apply 7-hour offset correction in reading index calculation
- [ ] **Option D**: Fix `calculateHistoricalTimestamp()` timezone handling

#### 5.2 Verification System
- [ ] Create `verifyTargetTimeFrameValues()` function
- [ ] Test known values appear at known times
- [ ] Validate with both July 25th and 26th data
- [ ] Ensure fix doesn't break other functionalities

### **PHASE 6: Complete Testing & Validation**
**Objective**: Ensure fix works consistently

#### 6.1 Multi-Day Testing
- [ ] Test heart rate sync for July 24th (verify 18:15 → 170 BPM)
- [ ] Test heart rate sync for July 25th (verify 12:40-12:55 values)
- [ ] Test heart rate sync for July 26th (verify new data)
- [ ] Test edge cases (midnight, day boundaries)

#### 6.2 Success Validation
- [ ] ✅ High values appear at correct times (12:40-12:55 for July 25th)
- [ ] ✅ All 288 readings map to correct 5-minute intervals
- [ ] ✅ Real device timestamp matches our calculation  
- [ ] ✅ Consistent behavior across multiple days

## 🛠️ Actionable Implementation Tasks

### Task 1: Plugin Guide Reset
**Estimated Time**: 30 minutes
```bash
# Steps:
1. Create backup of current implementation
2. Replace heart rate functions with Plugin_Guide.md versions
3. Test basic functionality
4. Verify 288 readings collected
```

### Task 2: Timestamp Function Analysis  
**Estimated Time**: 20 minutes
```bash
# Steps:
1. Locate calculateHistoricalTimestamp() function
2. Add debug logging to show input/output
3. Test with daysBack=0 for July 26th
4. Compare with manual UTC timestamp calculation
```

### Task 3: Device vs App Timestamp Comparison
**Estimated Time**: 15 minutes
```bash
# Steps:
1. Add logging in getHRData() to show sent timestamp
2. Add logging in extractRealTimestamp() to show received timestamp  
3. Calculate and log the difference
4. Determine if difference equals 7 hours (25200 seconds)
```

### Task 4: Reading Index Verification
**Estimated Time**: 25 minutes
```bash
# Steps:
1. Create function to find where specific BPM values appear
2. Test with known high values (110, 145, 171, 178)
3. Calculate expected vs actual reading indices
4. Verify 84-reading offset pattern (7 hours × 12 readings/hour)
```

### Task 5: Timezone Fix Implementation
**Estimated Time**: 20 minutes
```bash
# Steps:
1. Based on Tasks 2-4, identify root cause
2. Implement appropriate timezone correction
3. Test with July 25th data
4. Verify 12:40-12:55 values appear correctly
```

### Task 6: Multi-Day Validation
**Estimated Time**: 15 minutes  
```bash
# Steps:
1. Test July 24th, 25th, 26th data
2. Verify all known reference values appear at correct times
3. Document fix and update implementation
4. Clean up debug logging
```

## 🔍 Debug Logging Strategy

### Essential Log Points
1. **Timestamp calculation**: Log sent vs received timestamps
2. **Reading index calculation**: Log absolute reading index for each value
3. **High value detection**: Log where values >100 BPM appear
4. **Target timeframe**: Log 12:40-12:55 values specifically
5. **Timezone conversion**: Log before/after timezone adjustments

### Debug Output Format
```
🕒 TIMESTAMP: Sent=1721952000, Received=1721926800, Diff=-25200s (-7h)
📍 INDEX: Reading 152 → 12:40 → 67 BPM (EXPECTED: 110 BPM)
🔥 HIGH VALUE: 110 BPM found at reading 236 → 19:40 (WRONG TIME)
🎯 TARGET: 12:40-12:55 values: [67, 65, 67, 100] (SHOULD BE: [110, 145, 171, 178])
```

## 📈 Expected Outcomes

### Immediate Success Criteria
- [ ] High values (110, 145, 171, 178) appear at 12:40-12:55 for July 25th data
- [ ] Zero offset between expected and actual timestamps
- [ ] All 288 daily readings correctly mapped to 5-minute intervals

### Long-term Validation
- [ ] Consistent behavior across historical dates
- [ ] Robust timezone handling for different device settings  
- [ ] Reliable heart rate data for UI display and analysis

## 🚀 Getting Started

**NEXT ACTION**: Begin with Task 1 (Plugin Guide Reset) to establish clean baseline, then proceed through tasks sequentially.

**TOTAL ESTIMATED TIME**: ~2 hours

**PRIORITY**: High - Core functionality affecting all heart rate data display and analysis. 