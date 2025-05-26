<!-- Task Need to be Complete -->

1. Connect the QC Band Device.
2. setTime Option
3. readBatterySuccess
4. Decision logic
   If sleepScore == 0 → Full-Sync. Else if lastSync < 2 h → skip sync. Otherwise → Partial-Sync.
5. getOneDaySportBy:1

---

Copied form doc File-
3.2 Full-sync (comprehensive daily ingest)
Seq
SDK method / action
Data or action
1
getOneDaySportBy:1
Yesterday totals (steps, kcal, distance, activeTime)
2
getSportDetailDataByDay:1
Yesterday 15-min slices
3 a
getSleepDetailDataByDay:1
Yesterday sleep stages
3 b
—
Compute final yesterday strain
3 c
—
Write & mark yesterday doc finalized=true
4
getSportDetailDataByDay:0
Today 15-min slices
5
getSchedualHeartRateDataWithDayIndexs:@[@0,@1]
Minute-HR arrays (today + overnight)
6
getSchedualHRVDataWithDates:@[@0]
Overnight HRV
7
getSchedualStressDataWithDates:@[@0]
Stress trend (optional)
8
getBloodOxygenDataByDayIndex:0
SpO₂ trend
9
getSchedualTemperatureDataByDayIndex:0
Skin-temperature trend
10
getSportPlusSummaryFromTimestamp:
Auto-detected workouts
11
Compute
Recovery, sleep-score, body-battery, strain
12
Write
Persist raw arrays & derived metrics to Firestore

3.3 Partial-sync (runs when sleepScore ≠ 0 and lastSync ≥ 2 h)
Seq
SDK method / action
Purpose
1
getCurrentSportSucess:
Quick totals (steps, kcal, distance, activeTime)
2
getCurrentSportSucess:
Quick totals (steps, kcal, distance, activeTime)
3
Compute
Update strain + body-battery; detect new workouts
4
Write
Persist updates to Firestore

3.4 Live-workout session
Phase
SDK method / action
Purpose
Start
operateSportModeWithType:state:Start
Begin selected sport mode
Live (connected)
realTimeHeartRateWithCmd:Start → currentSportInfo callback
Display live HR, strain, kcal, distance
Live (disconnected)
—
Band buffers HR until reconnection
End
operateSportModeWithType:state:End
Stop sport mode
Fetch summary
getSportPlusSummaryFromTimestamp:
Pull second-by-second HR & totals
Follow-up
Trigger Partial-Sync
Refresh day stats

3.5 Utility actions
Use-case
SDK method
Note
Live HR outside workout
realTimeHeartRateWithCmd: (Start / Hold / End)
Instant HR check
Time-based alarm
Firmware API not exposed. If added, use setSchedualAlarmInfo:; otherwise schedule vibration locally from phone.

Find my device
lookupDeviceSuccess:
Vibrates until band button pressed
