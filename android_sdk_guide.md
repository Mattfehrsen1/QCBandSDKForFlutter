# Android SDK Development Guide

## Table of Contents

1. [Introduction](#introduction)
2. [Access Conditions](#access-conditions)
3. [API Description](#api-description)
4. [Data Synchronization](#data-synchronization)
5. [Manual Measurement](#manual-measurement)
6. [Touch and Gestures](#touch-and-gestures)
7. [Device Notifications](#device-notifications)
8. [Exercise Management](#exercise-management)
9. [Muslim Data Synchronization](#muslim-data-synchronization)
10. [OTA Upgrade](#ota-upgrade)

## Introduction

### 1.1 The Role of the SDK

Provides partner companies with the Android Bluetooth SDK for use with Green Orange wireless devices that provide basic and advanced functionality for a major watch or other device.

**Intended Audience:**
- Software Architecture Engineers: Architecture Analysis and Technical Guidance
- Android Development Engineers: Have certain android development ability, understand BLE related development technology

## Access Conditions

### 2.0 System Requirements

- Android 5.0 or above
- Bluetooth 4.0 or above

### 2.1 Permissions Required by the SDK

Add these permissions to your `AndroidManifest.xml`:

```xml
<!-- Network permissions -->
<uses-permission android:name="android.permission.INTERNET" />

<!-- Bluetooth related permissions -->
<uses-permission android:name="android.permission.BLUETOOTH" />
<uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />

<!-- Storage related permissions -->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

<!-- Location permissions -->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_BACKGROUND_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />

<!-- For Android 12 or higher system -->
<uses-permission android:name="android.permission.BLUETOOTH_CONNECT" />
<uses-permission android:name="android.permission.BLUETOOTH_SCAN" />
<uses-permission android:name="android.permission.BLUETOOTH_ADVERTISE" />
```

### 2.2 Access Conditions

- Green Orange Wireless Wearables
- Green Orange Wireless SDK and documentation

## API Description

### 2.3.1 Scanning Devices

#### Start Scan

```java
// Start scan
BleScannerHelper.getInstance().scanDevice(
    final Context context, 
    UUID mUuid, 
    final ScanWrapperCallback scanCallBack
);
```

#### Stop Scan

```java
// Stop scan
BleScannerHelper.getInstance().stopScan(Context context);
```

#### Specified Device Scan

```java
// Specified device scan
BleScannerHelper.getInstance().scanTheDevice(
    final Context context, 
    final String macAddress, 
    final OnTheScanResult scanResult
);
```

### Device Connection: BleOperateManager.getInstance()

#### Direct Connection

```java
// Direct connection
BleOperateManager.getInstance().connectDirectly(smartWatch.deviceAddress);
```

#### Scan Connections

```java
// Scan connections
BleOperateManager.getInstance().connectWithScan(smartWatch.deviceAddress);
```

#### Disconnect

```java
// Disconnect
BleOperateManager.getInstance().unBindDevice();
```

#### Reconnect

```java
// Reconnect
BleOperateManager.getInstance().setNeedConnect(boolean needConnect);
```

#### Bluetooth Management

```java
// Called when bluetooth is turned off
BleOperateManager.getInstance().setBluetoothTurnOff(false);
BleOperateManager.getInstance().disconnect();

// Turn on the system bluetooth monitor
BleOperateManager.getInstance().setBluetoothTurnOff(true);
```

### 2.3.2 Feature List

#### Synchronize Time

```java
// Set time
CommandHandle.getInstance().executeReqCmd(
    SetTimeReq(0),
    ICommandResponse<SetTimeRsp>() {}
);
```

**SetTimeRsp Callback Description:**

```java
public class SetTimeRsp extends BaseRspCmd {
    // Support body temperature
    public boolean mSupportTemperature;
    // Watch face
    public boolean mSupportPlate;
    // Support the menstrual cycle
    public boolean mSupportMenstruation;
    // Support custom watch faces
    public boolean mSupportCustomWallpaper;
    // Support blood oxygen
    public boolean mSupportBloodOxygen;
    // Blood pressure support
    public boolean mSupportBloodPressure;
    // Support fatigue
    public boolean mSupportFeature;
    // Support one-key detection
    public boolean mSupportOneKeyCheck;
    // Weather support
    public boolean mSupportWeather;
    // Support for new sleep protocol
    public boolean mNewSleepProtocol;
    // Supports up to 6 or 3 dials
    public int mMaxWatchFace;
    // HRV support
    public boolean mSupportHrv;
}
```

#### Functional Support

```java
CommandHandle.getInstance().executeReqCmd(
    DeviceSupportReq.getReadInstance(),
    ICommandResponse<DeviceSupportFunctionRsp>() {
        // Support touch
        public boolean supportTouch;
        // Support muslim
        public boolean supportMoslin;
        // Support ble pair
        public boolean supportAPPRevision;
        // Support Heart rate calibration
        public boolean supportBlePair;
        // Support gesture
        public boolean supportGesture;
        // Support music
        public boolean supportRingMusic;
        // Support video
        public boolean supportRingVideo;
        // Support ebook
        public boolean supportRingEbook;
        // Support camera
        public boolean supportRingCamera;
        // Support phone call
        public boolean supportRingPhoneCall;
        // Support game
        public boolean supportRingGame;
    }
);
```

#### Bracelet Battery

```java
CommandHandle.getInstance().executeReqCmd(
    new SimpleKeyReq(Constants.CMD_GET_DEVICE_ELECTRICITY_VALUE),
    new ICommandResponse<BatteryRsp>() {
        @Override
        public void onDataResponse(BatteryRsp resultEntity) {
            if (resultEntity.getStatus() == BaseRspCmd.RESULT_OK) {
                // Get battery successfully
                // Battery level [0-100]
                int batteryValue = resultEntity.getBatteryValue();
            }
        }
    }
);
```

#### Continuous Heart Rate, Blood Oxygen, Blood Pressure Switch

**Read Continuous Heart Rate Settings:**

```java
CommandHandle.getInstance().executeReqCmd(
    HeartRateSettingReq.getReadInstance(),
    ICommandResponse<HeartRateSettingRsp>() {
        // it.isEnable switch
        // it.heartInterval heart rate measurement interval
    }
);
```

**Write Continuous Heart Rate Switch:**

```java
// isEnable: true on, false: off
// hrInterval: 10, 15, 20, 30, 60
CommandHandle.getInstance().executeReqCmd(
    HeartRateSettingReq.getWriteInstance(true, hrInterval),
    ICommandResponse<HeartRateSettingRsp>() {}
);
```

**Read Continuous SpO2 Settings:**

```java
CommandHandle.getInstance().executeReqCmd(
    BloodOxygenSettingReq.getReadInstance(),
    ICommandResponse<BloodOxygenSettingRsp>() {}
);
```

**Write Continuous Blood Oxygen Switch:**

```java
// isEnable: true on, false: off
CommandHandle.getInstance().executeReqCmd(
    BloodOxygenSettingReq.getWriteInstance(boolean isEnable),
    ICommandResponse<BloodOxygenSettingRsp>() {}
);
```

**Read Continuous Blood Pressure Settings:**

```java
CommandHandle.getInstance().executeReqCmd(
    BpSettingReq.getReadInstance(),
    ICommandResponse<BpSettingRsp>() {}
);
```

**Write Blood Pressure Switch:**

```java
CommandHandle.getInstance().executeReqCmd(
    BpSettingReq.getWriteInstance(boolean isEnable, StartEndTimeEntity startEndTimeEntity, int multiple),
    ICommandResponse<BpSettingRsp>() {}
);
// Parameter Description:
// isEnable: true on false off
// StartEndTimeEntity: The parameter description is the same as above
// multiple: default 60
```

**Read Pressure Setting:**

```java
CommandHandle.getInstance().executeReqCmd(
    PressureSettingReq.getReadInstance(),
    ICommandResponse<PressureSettingRsp>() {
        // switch: it.isEnable
    }
);
```

**Write Pressure Setting Switch:**

```java
// switch isEnable: true on, false: off
CommandHandle.getInstance().executeReqCmd(
    PressureSettingReq.getWriteInstance(isEnable),
    ICommandResponse<PressureSettingRsp>() {}
);
```

**HRV Settings:**

```java
// HRV read
CommandHandle.getInstance().executeReqCmd(
    HrvSettingReq.getReadInstance(),
    ICommandResponse<HRVSettingRsp>() {}
);

// HRV write
CommandHandle.getInstance().executeReqCmd(
    HrvSettingReq(true),
    ICommandResponse<HRVSettingRsp>() {}
);
```

#### Set Watch Sports Goals

```java
CommandHandle.getInstance().executeReqCmd(
    TargetSettingReq.getWriteInstance(
        final int step,          // Step target
        final int calorie,       // Calorie target target, unit card, write kcal to a*1000
        final int distance,      // Distance to target, in meters
        final int sportMinute,   // Exercise duration target, in minutes
        final int sleepMinute    // Sleep duration target, in minutes
    ), 
    null
);
```

#### Find Equipment

```java
CommandHandle.getInstance().executeReqCmd(FindDeviceReq(), null);
```

#### Ring Photo Control

**Enter Camera Interface:**

```java
// The bracelet enters the camera interface
CommandHandle.getInstance().executeReqCmd(
    CameraReq(CameraReq.ACTION_INTO_CAMARA_UI), 
    null
);
```

**Keep Screen On:**

```java
// The wristband is controlled by the bright screen on the camera interface
// The APP will send the bright screen command to keep the watch bright
// It is recommended to send it every 2 seconds
CommandHandle.getInstance().executeReqCmd(
    CameraReq(CameraReq.ACTION_KEEP_SCREEN_ON),
    null
);
```

**Monitor Photo Events:**

```java
// Bracelet click to take a photo event monitoring
BleOperateManager.getInstance().addNotifyListener(
    Constants.CMD_TAKING_PICTURE,
    new ICommandResponse<CameraNotifyRsp>() {
        @Override
        public void onDataResponse(CameraNotifyRsp resultEntity) {
            int action = resultEntity.getAction();
            // CameraNotifyRsp.ACTION_FINISH - The watch exits the camera interface
            // CameraNotifyRsp.ACTION_TAKE_PHOTO - The watch clicked on the photo event
        }
    }
);
```

**Exit Camera:**

```java
CommandHandle.getInstance().executeReqCmd(
    CameraReq(CameraReq.ACTION_FINISH), 
    null
);
```

#### Set the Ring to Factory Reset

```java
CommandHandle.getInstance().executeReqCmd(
    RestoreKeyReq(Constants.CMD_RE_STORE),
    null
);
```

#### Message Push

**Enable Message Push:**

```java
// The watch message push switch is fully turned on, and the APP should be actively opened
CommandHandle.getInstance().executeReqCmd(SetANCSReq(), null);
```

**Send Message Push:**

```java
// Send message push to watch
MessPushUtil.pushMsg(type, message:String);
```

**Message Types:**
- `0x00`: Call reminder
- `0x01`: SMS reminder
- `0x02`: QQ reminder
- `0x03`: WeChat reminder
- `0x04`: Incoming call to answer or hang up
- `0x05`: Facebook message reminder
- `0x06`: WhatsApp message reminder
- `0x07`: Twitter message reminder
- `0x08`: Skype message reminder
- `0x09`: Line message reminder
- `0x0a`: LinkedIn
- `0x0b`: Instagram
- `0x0c`: TIM message
- `0x0d`: Snapchat
- `0x0e`: Others - other types of notifications

#### Wearing Calibration

```java
// start: enable:true
// end: enable:false
BleOperateManager.getInstance().ringCalibration(false) {
    // 2:Measuring 1:success 3:fail
    it.success
}
```

#### Firmware and Hardware Version

```java
// Hardware information
CommandHandle.getInstance().execReadCmd(
    CommandHandle.getInstance().getReadHwRequest()
);

// Firmware information
CommandHandle.getInstance().execReadCmd(
    CommandHandle.getInstance().getReadFmRequest()
);
```

**Callback Implementation:**

```java
// Receiving implements this QCBluetoothCallbackCloneReceiver
@Override
public void onCharacteristicRead(String uuid, byte[] data) {
    if (uuid != null && data != null) {
        String version = new String(data, StandardCharsets.UTF_8);
        switch(uuid) {
            case Constants.CHAR_FIRMWARE_REVISION:
                // Firmware version number
                break;
            case Constants.CHAR_HW_REVISION:
                // Hardware version number
                break;
        }
    }
}
```

## Data Synchronization

### 2.3.3 Synchronize Steps, Distance, Kcal for the Day

```java
CommandHandle.getInstance().executeReqCmd(
    SimpleKeyReq(Constants.CMD_GET_STEP_TODAY),
    ICommandResponse<TodaySportDataRsp>() {}
);
```

**TodaySportDataRsp Parameters:**

```java
public class TodaySportDataRsp extends BaseRspCmd {
    private int daysAgo;           // Days ago
    private int year;              // Date: year
    private int month;             // Date: month
    private int day;               // Date: day
    private int totalSteps;        // Total steps
    private int runningSteps;      // Running steps/aerobic steps
    private int calorie;           // Calorie value
    private int walkDistance;      // Walking distance
    private int sportDuration;     // Movement time, in seconds
    private int sleepDuration;     // Sleep time in seconds
}
```

### Synchronized Step Data Details

```java
// dayOffset 0: Today 1: Yesterday 2: The day before yesterday, supports synchronization for up to 7 days
CommandHandle.getInstance().executeReqCmd(
    ReadDetailSportDataReq(dayOffset, 0, 95),
    ICommandResponse<ReadDetailSportDataRsp>() {}
);
```

**BleStepDetails Parameters:**

```java
public class BleStepDetails {
    private int year;              // Year
    private int month;             // Month
    private int day;               // Day
    private int timeIndex = 0;     // 15 minutes a point, total 96 points per day [0, 95]
    private int calorie = 0;       // Calorie unit card
    private int walkSteps = 0;     // Step count
    private int distance = 0;      // Distance in meters
    private int runSteps = 0;      // Keep for now
}
```

### Sync Sleep Data Details

```java
// deviceAddress: Device mac address
// dayOffset: 0: Today 1: Yesterday 2: The day before yesterday, supports synchronization for up to 7 days
// ISleepCallback: callback
SleepAnalyzerUtils.getInstance().syncSleepReturnSleepDisplay(
    deviceAddress,
    dayOffset, 
    ISleepCallback() {
        // callback: SleepDisplay
    }
);
```

**SleepDisplay Parameters:**

```java
public class SleepDisplay {
    private int totalSleepDuration;        // Total sleep time
    private int deepDuration;              // Total time of deep sleep
    private int shallowDuration;           // Total time of light sleep
    private int sleepTime;                 // Go to sleep timestamp in seconds
    private int wakeTime;                  // Wake up timestamp in seconds
    private List<SleepDataBean> list;      // A set of sleep data
    private String address;
}

public class SleepDataBean {
    int sleepStart;    // Start timestamp of a sleep in seconds
    int sleepEnd;      // End timestamp of a sleep in seconds
    int type;          // Sleep type 2: light sleep 1: deep sleep 3: awake
}
```

### Synchronize New Sleep Data Details

```java
// offset 0 today 1 yesterday
public void syncSleepList(int offset, final ILargeDataSleepResponse response);

// Support lunch
public void syncSleepList(int offset, final ILargeDataSleepResponse response, final ILargeDataLaunchSleepResponse lunchSleepResponse);
```

**SleepNewProtoResp Parameters:**

```java
public class SleepNewProtoResp {
    private int st;                        // Sleep start time
    private int et;                        // Sleep end time
    private List<DetailBean> list;         // Sleep collection
    
    // Lunch support
    private int lunchSt;                   // Lunch start time
    private int lunchEt;                   // Lunch end time
    private boolean lunchBreak;
}

public class DetailBean {
    private int d;      // Duration of a sleep type
    private int t;      // Type of sleep 2: light sleep 3: deep sleep 5: awake
}
```

### Sedentary Data Synchronization

```java
// offset 0 today 1 yesterday
public void syncLongSitList(int offset, final ILargeSettingForLongDataResponse response);
```

**LongSitResp Parameters:**

```java
public class LongSitResp {
    private int index;                     // Offset 0 today 1 yesterday
    private List<DetailBean> list;         // Sedentary collection
}

public class DetailBean {
    private int d;      // Duration of a sedentary type
    private int t;      // 0 static (less than 30 steps in 1 minute), 1 trigger sedentary, 2 movement (more than 30 steps)
}
```

### Sync Heart Rate Data

```java
// nowTime: current time zone * 3600 + unix second value of current time
// Sync yesterday: nowTime-(24*3600)*1
// Sync the day before yesterday: nowTime-(24*3600)*2
// Data can be synchronized for up to three days
val time = (getTimeZone() * 3600).toInt();
val nowTime = date.unixTimestamp + time;

CommandHandle.getInstance().executeReqCmd(
    ReadHeartRateReq(nowTime),
    ICommandResponse<ReadHeartRateRsp>() {}
);
```

**ReadHeartRateRsp Parameters:**

```java
public class ReadHeartRateRsp {
    private int size = 0;                  // Nothing yet
    private int index = 0;                 // Nothing yet
    private int mUtcTime;                  // Unix second value of heart rate data
    private byte[] mHeartRateArray;        // Heart rate data array, one point every 5 minutes
    private boolean endFlag = false;
}
```

### Synchronized Blood Pressure Function

```java
// Synchronized automatic blood pressure, measured once an hour
CommandHandle.getInstance().executeReqCmd(
    SimpleKeyReq(Constants.CMD_BP_TIMING_MONITOR_DATA),
    ICommandResponse<BpDataRsp>() {}
);
```

**BpDataEntity Parameters:**

```java
public class BpDataEntity {
    private int year;                      // Year
    private int mouth;                     // Month
    private int day;                       // Day
    private int timeDelay;
    private ArrayList<BpValue> bpValues;
}

public class BpValue {
    int timeMinute;     // The minute of the day, usually the whole hour
    int value;          // Measured heart rate value
}
```

**Calculate Blood Pressure Values:**

```java
// The heart rate value returned by the hr callback, age is the age of the user
val sbp = CalcBloodPressureByHeart.cal_sbp(hr, age); // (systolic blood pressure)
val dbp = CalcBloodPressureByHeart.cal_dbp(sbp);     // (diastolic pressure)
```

**Confirm Blood Pressure Synchronization:**

```java
// Confirm blood pressure synchronization, call after receiving the callback
// The watch will delete the records that have been synchronized
CommandHandle.getInstance().executeReqCmd(BpReadConformReq(true), null);
```

**Synchronize Manual Blood Pressure:**

```java
CommandHandle.getInstance().executeReqCmd(
    ReadPressureReq(0),
    ICommandResponse<ReadBlePressureRsp>() {}
);
```

**ReadBlePressureRsp Parameters:**

```java
public class BlePressure {
    public long time;       // Time seconds value
    public int dbp;         // (Diastolic pressure)
    public int sbp;         // (systolic blood pressure)
}
```

### Synchronized Blood Oxygen Function

```java
LargeDataHandler.getInstance().syncBloodOxygenWithCallback(new IBloodOxygenCallback() {
    @Override
    public void readBloodOxygen(List<BloodOxygenEntity> data) {
        // Process blood oxygen data
    }
});
```

**BloodOxygenEntity Parameters:**

```java
public class BloodOxygenEntity {
    private String dateStr;                // Data date
    private List<Integer> minArray;        // Data minimum value array, one data per hour, total 24
    private List<Integer> maxArray;        // Data maximum value array, one data per hour, total 24
    private long unix_time;                // Data value at 0:00 on a certain day
}
```

### Synchronized Pressure Function

```java
// offset 0-6
// Synchronization pressure 7 days 0 only synchronizes today 1 yesterday 2 synchronizes the day before yesterday
// Supports up to 7 days
CommandHandle.getInstance().executeReqCmd(
    PressureReq(offset),
    ICommandResponse<PressureRsp>() {}
);
```

**PressureRsp Parameters:**

```java
public class PressureRsp {
    private byte[] pressureArray;          // Pressure data, return value divided by 10 is the value displayed by APP
    private int range = 30;                // Stress test interval
    private DateUtil today;                // Pressure data date
}
```

### Synchronized HRV Function

```java
// offset 0-6
// Synchronization hrv 7 days 0 only synchronizes today 1 yesterday 2 synchronizes the day before yesterday
// Supports up to 7 days
CommandHandle.getInstance().executeReqCmd(
    HRVReq(0),
    ICommandResponse<HRVRsp>() {}
);
```

**HRVRsp Parameters:**

```java
public class HRVRsp {
    private byte[] pressureArray;          // HRV data, return value divided by 10 is the value displayed by APP
    private int range = 30;                // HRV test interval
    private DateUtil today;                // HRV data date
}
```

### Synchronized Skin Temperature Function

```java
public void registerTempCallback() {
    // Register a callback
    FileHandle.getInstance().clearCallback();
    FileHandle.getInstance().registerCallback(new Callback());
    FileHandle.getInstance().initRegister();
}

// Synchronized automatic skin temperature
public void syncAutoTemperature() {
    // Synchronize automatic body temperature for 3 days
    // 0: Synchronize only today 1: Synchronize today and yesterday 2: Synchronize today, yesterday and the day before yesterday
    // Support up to 7 days
    FileHandle.getInstance().startObtainTemperatureSeries(2);
}

// Synchronous manual skin temperature
public void syncManual() {
    // Synchronize automatic body temperature for 3 days
    // 0: Synchronize only today 1: Synchronize today and yesterday 2: Synchronize today, yesterday and the day before yesterday
    // Support up to 7 days
    FileHandle.getInstance().startObtainTemperatureOnce(0);
}

class Callback extends SimpleCallback {
    // Continuous temperature callback
    @Override
    public void onUpdateTemperature(TemperatureEntity data) {
        // Process temperature data
    }
    
    // Single temperature callback
    @Override
    public void onUpdateTemperatureList(List<TemperatureOnceEntity> array) {
        // Process temperature list
    }
}
```

**TemperatureEntity Parameters:**

```java
public class TemperatureEntity {
    public int mIndex;          // Represents today 1 represents yesterday ...6
    public int mTimeSpan;       // Test interval
    public float[] mValues;     // Temperature array cursor*mTimeSpan = measurement time of the day, in minutes
}
```

**TemperatureOnceEntity Parameters:**

```java
public class TemperatureOnceEntity {
    public long mTime;          // Measurement timestamp, seconds
    public float mValue;        // Measurement value
}
```

### Synchronous Training Records

```java
val syncSport = SportPlusHandle();
syncSport.timeFormat = "yyyy-MM-dd HH:mm";
syncSport.syncSportPlus { errorCode, t -> }

// 0 is passed for the first synchronization, and the time of the last movement after synchronization is passed later
syncSport.cmdSummary(0);
```

**Sport Data Parameters:**

```java
public class SportData {
    public int mSportType;          // Movement type index
    public int mStartTime;          // Start time seconds
    public int mDuration;           // Movement duration seconds
    public int mDistance;           // Movement mileage meters
    public float mCalories;         // Calories small calories
    public int mSpeedAvg;           // Average speed cm/s
    public int mSpeedMax;           // Maximum speed cm/s
    public int mRateAvg;            // Average heart rate beats/minute
    public int mRateMin;            // Minimum heart rate beats/minute
    public int mRateMax;            // Maximum heart rate beats/minute
    public int mElevation;          // Average altitude cm
    public int mUphill;             // Cumulative climbing cm
    public int mDownhill;           // Cumulative downhill cm
    public int mStepRate;           // Average cadence steps/minute
    public int mSportCount;         // Number of exercises times
    public int steps;
    public List<SportLocation> mLocations;
}

public class SportLocation {
    public int mRateReal;           // Real-time heart rate beats/minute
}
```

## Manual Measurement

### 2.3.7 Manual Measurement

**StartHeartRateRsp Parameters:**

```java
public class StartHeartRateRsp {
    private byte type;              // Type 1: heart rate 2: blood pressure 3: blood oxygen
    private byte errCode;           // Measurement error code 0: normal 1: measurement failed 2: measurement failed
    private byte value;             // Measurement value: heart rate or blood oxygen
    private byte sbp;               // Blood pressure sbp
    private byte dbp;               // Blood pressure dbp
}
```

**Manual Heart Rate:**

```java
// Boolean stop: true stop false start
BleOperateManager.getInstance().manualModeHeart(
    new ICommandResponse<StartHeartRateRsp>() {
        @Override
        public void onDataResponse(StartHeartRateRsp resultEntity) {
            // Process heart rate data
        }
    }, 
    Boolean stop
);
```

**Manual Blood Pressure:**

```java
BleOperateManager.getInstance().manualModeBP(
    new ICommandResponse<StartHeartRateRsp>() {
        @Override
        public void onDataResponse(StartHeartRateRsp resultEntity) {
            // Process blood pressure data
        }
    }, 
    Boolean stop
);
```

**Manual Blood Oxygen:**

```java
BleOperateManager.getInstance().manualModeSpO2(
    new ICommandResponse<StartHeartRateRsp>() {
        @Override
        public void onDataResponse(StartHeartRateRsp resultEntity) {
            // Process blood oxygen data
        }
    }, 
    Boolean stop
);
```

**Manual Pressure:**

```java
BleOperateManager.getInstance().manualModePressure(
    new ICommandResponse<StartHeartRateRsp>() {
        @Override
        public void onDataResponse(StartHeartRateRsp resultEntity) {
            // Process pressure data
        }
    }, 
    Boolean stop
);
```

**Manual HRV:**

```java
BleOperateManager.getInstance().manualModeHrv(
    new ICommandResponse<StartHeartRateRsp>() {
        @Override
        public void onDataResponse(StartHeartRateRsp resultEntity) {
            // Process HRV data
        }
    }, 
    Boolean stop
);
```

**Manual Temperature:**

```java
BleOperateManager.getInstance().manualTemperature(
    new ICommandResponse<StartHeartRateRsp>() {
        @Override
        public void onDataResponse(StartHeartRateRsp resultEntity) {
            // The temperature value obtained should be divided by 10 to get the normal temperature
        }
    }, 
    Boolean stop
);
```

## Touch and Gestures

### 2.3.8 Touch and Gestures

**Read Touch/Gesture Settings:**

```java
// read
// touch: true touch false: gestures
CommandHandle.getInstance().executeReqCmd(
    TouchControlReq.getReadInstance(false),
    ICommandResponse<TouchControlResp>() {
        // appType: 0:close 1:music 2:video 3:muslim 4:ebook 5:camera 6:phone call 7:game 8:heart
        // strength: Dynamics
    }
);
```

**Write Touch/Gesture Settings:**

```java
// write:
// appType: 0:close 1:music 2:video 3:muslim 4:ebook 5:camera 6:phone call 7:game 8:heart
// touch: true touch false: gestures
// Strength: 1-10
CommandHandle.getInstance().executeReqCmdNoCallback(
    TouchControlReq.getWriteInstance(appType, false, currStrength)
);
```

## Device Notifications

### 2.3.9 Changes in Ring Measurement Data are Proactively Reported to the APP

**Add Listener:**

```java
BleOperateManager.getInstance().addOutDeviceListener(
    ListenerKey.Heart,
    myDeviceNotifyListener
);
```

**ListenerKey Parameters:**

```java
public class ListenerKey {
    public static int Heart = 1;           // Heart
    public static int BloodPressure = 2;   // Blood Pressure
    public static int BloodOxygen = 3;     // Blood Oxygen
    public static int Temperature = 5;     // Temperature
    public static int SportRecord = 7;     // Sport Record
    public static int All = 7;             // All
}
```

**Monitoring Implementation:**

```java
class MyDeviceNotifyListener extends DeviceNotifyListener {
    @Override
    public void onDataResponse(DeviceNotifyRsp resultEntity) {
        if (resultEntity.getStatus() == BaseRspCmd.RESULT_OK) {
            BleOperateManager.getInstance().removeOthersListener();
            switch (resultEntity.dataType) {
                case 1:
                    // Watch heart rate test changes
                    break;
                case 2:
                    // Watch blood pressure test changes
                    break;
                case 3:
                    // Watch blood oxygen test changes
                    break;
                case 4:
                    // Changes in watch step counting details
                    break;
                case 5:
                    // Watch body temperature changes on the day
                    break;
                case 7:
                    // Generate new exercise records
                    break;
                case 0x0c:
                    // Battery and charging status
                    // 730c57010000000000000000000000d7
                    int charging = BLEDataFormatUtils.bytes2Int(
                        new byte[]{resultEntity.loadData[2]}
                    );
                    if (charging > 0) {
                        // Charging
                    } else {
                        int battery = BLEDataFormatUtils.bytes2Int(
                            new byte[]{resultEntity.loadData[1]}
                        );
                        // Battery power
                    }
                    break;
                case 0x12:
                    // Real-time step, calorie, distance data
                    // 7312 00005200025100003c0000000066
                    int step = BLEDataFormatUtils.bytes2Int(new byte[]{
                        resultEntity.loadData[1],
                        resultEntity.loadData[2],
                        resultEntity.loadData[3]
                    });
                    int calorie = BLEDataFormatUtils.bytes2Int(new byte[]{
                        resultEntity.loadData[4],
                        resultEntity.loadData[5],
                        resultEntity.loadData[6]
                    });
                    int distance = BLEDataFormatUtils.bytes2Int(new byte[]{
                        resultEntity.loadData[7],
                        resultEntity.loadData[8],
                        resultEntity.loadData[9]
                    });
                    deviceNotification(step, distance, calorie);
                    break;
            }
        }
    }
}
```

**Remove Listeners:**

```java
// Remove the listener. It must be removed after registration, otherwise multiple callbacks will appear
BleOperateManager.getInstance().removeNotifyListener(ListenerKey.Heart);

// Remove all listeners
BleOperateManager.getInstance().removeNotifyListener(ListenerKey.All);
```

## Exercise Management

### 2.3.10 APP Opens Exercise Type

**Start Exercise:**

```java
// status: 1 Start movement 2 Pause 3 Continue 4 End 6 Movement start timestamp
// Sport types: 4 Walking 5 Jumping rope 7 Running 8 Hiking 9 Cycling 10 Other sports
// 20 Hiking 21 Badminton 22 Yoga 23 Aerobics 24 Spinning 25 Kayaking 26 Elliptical machine
// 27 Rowing machine 28 Table tennis 29 Tennis 30 Golf 31 Basketball 32 Football 33 Volleyball
// 34 Rock climbing 35 Dance 36 Roller skating 60 Outdoor hiking
CommandHandle.getInstance().executeReqCmd(
    PhoneSportReq.getSportStatus(1, sportType),
    gpsResponse
);
```

**GPS Response Handler:**

```java
private ICommandResponse<AppSportRsp> gpsResponse = new ICommandResponse<AppSportRsp>() {
    @Override
    public void onDataResponse(AppSportRsp resultEntity) {
        if (resultEntity != null) {
            switch (resultEntity.gpsStatus) {
                case 6:
                    // Exercise start time (Unit second)
                    break;
                case 2:
                    // Exercise pause
                    break;
                case 3:
                    // Exercise continues
                    break;
                case 4:
                    // Exercise end
                    break;
                case 0x25:
                    // Muslim ring click real-time data
                    // 732500000013000700000000000000b2
                    int count = BLEDataFormatUtils.bytes2Int(new byte[]{
                        resultEntity.loadData[1],
                        resultEntity.loadData[2],
                        resultEntity.loadData[3],
                        resultEntity.loadData[4]
                    });
                    break;
            }
        }
    }
};
```

**Report Data During Exercise:**

```java
// Add motion data reporting and monitoring
BleOperateManager.getInstance().addSportDeviceListener(0x78, myDeviceSportNotifyListener);

// Remove sports data reporting monitoring
BleOperateManager.getInstance().removeSportDeviceListener(0x78);
```

**Sport Data Listener:**

```java
class MyDeviceSportNotifyListener extends DeviceSportNotifyListener {
    @Override
    public void onDataResponse(DeviceNotifyRsp resultEntity) {
        super.onDataResponse(resultEntity);
        
        if (resultEntity.getStatus() == BaseRspCmd.RESULT_OK) {
            // Movement duration, unit seconds
            int sportTime = bytes2Int(new byte[]{
                resultEntity.loadData[2],
                resultEntity.loadData[3]
            });
            
            // Exercise real-time heart rate
            int heart = bytes2Int(new byte[]{
                resultEntity.loadData[4]
            });
            
            // The number of steps generated during exercise
            // Will only have data when the exercise type is 4, 7, or 8, otherwise it will be 0
            int step = bytes2Int(new byte[]{
                resultEntity.loadData[5],
                resultEntity.loadData[6],
                resultEntity.loadData[7]
            });
            
            // The distance generated during exercise
            // Will only have data when the exercise type is 4, 7, or 8, and the others will be 0
            int distance = bytes2Int(new byte[]{
                resultEntity.loadData[8],
                resultEntity.loadData[9],
                resultEntity.loadData[10]
            });
            
            // Calories generated during exercise
            int calorie = bytes2Int(new byte[]{
                resultEntity.loadData[11],
                resultEntity.loadData[12],
                resultEntity.loadData[13]
            });
            
            // Error status during exercise
            int status = bytes2Int(new byte[]{
                resultEntity.loadData[1]
            });
            
            int sportType = BLEDataFormatUtils.bytes2Int(new byte[]{
                resultEntity.loadData[0]
            });
            
            if (status == 0x03) {
                // Not wearing
            }
        }
    }
}
```

**Utility Method:**

```java
/**
 * Convert the byte array to int type, with the high byte of the array first
 */
public static int bytes2Int(byte[] data) {
    int length = data.length;
    int res = 0;
    for (int i = 0; i < length; i++) {
        res |= (data[i] & 0xFF) << (8 * (length - 1 - i));
    }
    return res;
}
```

## Muslim Data Synchronization

### Muslim Data Synchronization

```java
// dayOffset 0: Today 1: Yesterday 2: The day before yesterday, supports synchronization for up to 7 days
CommandHandle.getInstance().executeReqCmd(
    MuslimReq(0),
    ICommandResponse<MuslimRsp>() {}
);
```

**MuslimRsp Parameters:**

```java
public class MuslimRsp {
    private int size = 0;
    private int index = 0;
    private byte[] pressureArray;           // Muslim data one data point per hour
    private boolean endFlag = false;
    private int range = 60;
    private DateUtil today;
    private int offset = -1;
}
```

**Real-time Data Reporting:**

Ring single reporting, please refer to section 2.3.9 Type 0x25

## OTA Upgrade

### 2.3.6 OTA Upgrade Function

```java
// DFU upgrade instance
val fuHandle = DfuHandle.getInstance();

// Initialize callback
dfuHandle.initCallback();

// DFU file verification, path firmware file path
if (dfuHandle.checkFile(path)) {
    dfuHandle.start(dfuOpResult);
}
```

**DFU Operation Result Callback:**

```java
private DfuHandle.IOpResult dfuOpResult = new DfuHandle.IOpResult() {
    @Override
    public void onActionResult(int type, int errCode) {
        if (errCode == DfuHandle.RSP_OK) {
            switch (type) {
                case 1:
                    dfuHandle.init();
                    break;
                case 2:
                    dfuHandle.sendPacket();
                    break;
                case 3:
                    dfuHandle.check();
                    break;
                case 4:
                    // The upgrade is successful, wait for the device to restart
                    dfuHandle.endAndRelease();
                    break;
            }
        } else {
            // Upgrade exception or failure
        }
    }
    
    @Override
    public void onProgress(int percent) {
        // File upgrade progress
    }
};
```

## Sport Types Reference

### Exercise Model Types

```java
// Sport type definitions
public enum OdmSportPlusExerciseModelType {
    OdmSportPlusExerciseModelTypeGpsRun = 1,            // GPS running
    OdmSportPlusExerciseModelTypeGpsBike = 2,           // GPS cycling
    OdmSportPlusExerciseModelTypeGpsWalk = 3,           // GPS walking
    OdmSportPlusExerciseModelTypeIndoorRun = 4,         // Bracelet walking
    OdmSportPlusExerciseModelTypeRopeSkipping = 5,      // Bracelet rope skipping
    OdmSportPlusExerciseModelTypeSwimming = 6,          // Bracelet swimming
    OdmSportPlusExerciseModelTypeRun = 7,               // Bracelet running-outdoor
    OdmSportPlusExerciseModelTypeWalk = 8,              // Bracelet hiking
    OdmSportPlusExerciseModelTypeBike = 9,              // Bracelet cycling
    OdmSportPlusExerciseModelTypeExercise = 10,         // Others
    OdmSportPlusExerciseModelTypeSwing = 11,            // Bracelet swinging
    OdmSportPlusExerciseModelTypeClimb = 20,            // Bracelet climbing
    OdmSportPlusExerciseModelTypeBadminton = 21,        // Bracelet badminton
    OdmSportPlusExerciseModelTypeYoga = 22,             // Bracelet yoga
    OdmSportPlusExerciseModelTypeAerobics = 23,         // Bracelet aerobics
    OdmSportPlusExerciseModelTypeSpinningBike = 24,     // Bracelet spinning bike
    OdmSportPlusExerciseModelTypeKayaking = 25,         // Bracelet kayaking
    OdmSportPlusExerciseModelTypeEllipticalMachine = 26, // Bracelet elliptical machine
    OdmSportPlusExerciseModelTypeRowingMachine = 27,    // Bracelet rowing machine
    OdmSportPlusExerciseModelTypePingpong = 28,         // Bracelet table tennis
    OdmSportPlusExerciseModelTypeTennis = 29,           // Bracelet tennis
    OdmSportPlusExerciseModelTypeGolf = 30,             // Bracelet golf
    OdmSportPlusExerciseModelTypeBasketball = 31,       // Bracelet basketball
    OdmSportPlusExerciseModelTypeFootball = 32,         // Bracelet football
    OdmSportPlusExerciseModelTypeVolleyball = 33,       // Bracelet volleyball
    OdmSportPlusExerciseModelTypeRockClimbing = 34,     // Bracelet rock climbing
    OdmSportPlusExerciseModelTypeDance = 35,            // Bracelet dance
    OdmSportPlusExerciseModelTypeRollerSkating = 36,    // Bracelet roller skating
    OdmSportPlusExerciseModelTypeTreadmill = 40,        // Running-treadmill
    OdmSportPlusExerciseModelTypeIndoorWalking = 41,    // Running-indoor walking
    OdmSportPlusExerciseModelTypeTrailRunning = 42,     // Running-cross-country running
    OdmSportPlusExerciseModelTypeRaceWalk = 43,         // Running-race walking
    OdmSportPlusExerciseModelTypePlaygroundRunning = 44, // Running-playground running
    OdmSportPlusExerciseModelTypeFatLossRunning = 45,   // Running-fat loss running
    OdmSportPlusExerciseModelTypeOutdoorCycling = 50,   // Cycling-outdoor cycling
    OdmSportPlusExerciseModelTypeIndoorCycling = 51,    // Cycling-indoor cycling
    OdmSportPlusExerciseModelTypeMountainBiking = 52,   // Cycling-mountain biking
    OdmSportPlusExerciseModelTypeBMX = 53,              // Cycling-BMX
    OdmSportPlusExerciseModelTypeSwimmingPool = 55,     // Swimming-swimming pool swimming
    OdmSportPlusExerciseModelTypeOutdoorSwimming = 56,  // Swimming-outdoor swimming
    OdmSportPlusExerciseModelTypeFinSwimming = 57,      // Swimming-Fin Swimming
    OdmSportPlusExerciseModelTypeSynchronizedSwimming = 58, // Swimming-Synchronized Swimming
    OdmSportPlusExerciseModelTypeOutdoorHiking = 60,    // Outdoor Sports-Outdoor Hiking
    // ... and many more sport types
}
```

## Notes

- All methods should be called with proper error handling
- Remember to remove listeners after use to prevent memory leaks
- Battery level is returned as an integer from 0-100
- Time formats should follow the specified patterns
- Day offset parameters typically use 0 for today, 1 for yesterday, etc.
- Temperature values should be divided by 10 to get normal temperature readings

## Error Codes

### Common Error Codes:
- **0**: Normal/Success
- **1**: Measurement failed
- **2**: Measurement failed
- **3**: Not wearing (during exercise)

### DFU Error Codes:
- **DfuHandle.RSP_OK**: Success
- Other values indicate upgrade exceptions or failures

## Data Models

The SDK uses various data models including:
- `TodaySportDataRsp`: For daily sport statistics
- `BleStepDetails`: For detailed step data
- `SleepDisplay` / `SleepDataBean`: For sleep data
- `BpDataEntity` / `BpValue`: For blood pressure data
- `BloodOxygenEntity`: For blood oxygen measurements
- `TemperatureEntity` / `TemperatureOnceEntity`: For temperature data
- `StartHeartRateRsp`: For manual measurement responses

Make sure to import the appropriate SDK classes and handle the callback data according to these model structures.