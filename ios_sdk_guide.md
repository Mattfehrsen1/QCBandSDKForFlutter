# iOS SDK Development Guide

## Table of Contents

1. [Environment Configuration](#environment-configuration)
2. [Usage](#usage)
3. [Instructions Supported by the Device](#instructions-supported-by-the-device)

## Environment Configuration

### 1.1 Add Framework

Add `QCBandSDK.framework` to the project. The framework supports iOS 9.0 and above.

**Note:** Because the classification is used in the framework, you need to add settings to the project:

- **Target → Build Settings → Other Linker Flags**: Add `-ObjC`

Add the following code in **Target → Build Settings → Excluded Architectures**:

```
EXCLUDED_ARCHS__EFFECTIVE_PLATFORM_SUFFIX_simulator__NATIVE_ARCH_64_BIT_x86_64=arm64 arm64e armv7 armv7s armv6 armv8 
EXCLUDED_ARCHS=$(inherited) $(EXCLUDED_ARCHS__EFFECTIVE_PLATFORM_SUFFIX_$(EFFECTIVE_PLATFORM_SUFFIX)__NATIVE_ARCH_64_BIT_$(NATIVE_ARCH_64_BIT))
```

### 1.2 Configure Bluetooth Permissions

Configure bluetooth permissions in `info.plist` file:

```xml
<key>NSBluetoothAlwaysUsageDescription</key>
<string>App needs to use your bluetooth device</string>
<key>NSBluetoothPeripheralUsageDescription</key>
<string>App needs to use your bluetooth device</string>
```

## Usage

### 2.1 Service UUIDs Supported by the Device

Defined in `QCSDKManager.h`, the service UUID supported by the device:

```objc
extern NSString *const QCBANDSDKSERVERUUID1;
extern NSString *const QCBANDSDKSERVERUUID2;
```

### 2.2 Import Framework Library

Introduce the framework library into the code:

```objc
#import <QCBandSDK/QCSDKManager.h>
#import <QCBandSDK/QCSDKCmdCreator.h>
```

Initialize `[QCSDKManager shareInstance]` with a singleton:

- **QCSDKManager**: Peripherals for joining connections
- **QCSDKCmdCreator**: Used to send commands to peripherals

### 2.3 Scan Ring

#### Initialization

Scanning can only be started when permissions are allowed and Bluetooth is turned on.

Import Apple's CoreBluetooth library and follow two protocols:

```objc
#import <CoreBluetooth/CoreBluetooth.h>
// Follow protocols: <CBCentralManagerDelegate, CBPeripheralDelegate>
```

#### Declare Central and Peripheral Roles

```objc
/* Central Role, app */
@property (strong, nonatomic) CBCentralManager *centerManager;
/* Peripheral role, scanned peripherals */
@property (strong, nonatomic) NSMutableArray<CBPeripheral *> *peripherals;
/* Connected peripheral role */
@property (strong, nonatomic) CBPeripheral *connectedPeripheral;
```

#### Instantiate the Central Role

```objc
self.centerManager = [[CBCentralManager alloc] initWithDelegate:self queue:nil];
```

#### Scan Ring

Using Scan Peripherals:

```objc
NSArray *serviceUUIDStrings = @[QCBANDSDKSERVERUUID1, QCBANDSDKSERVERUUID2];
NSMutableArray *uuids = [NSMutableArray array];
for (id obj in serviceUUIDStrings) {
    if ([obj isKindOfClass:[NSString class]]) {
        CBUUID *uuid = [CBUUID UUIDWithString:obj];
        [uuids addObject:uuid];
    }
}
NSDictionary *option = @{CBCentralManagerScanOptionAllowDuplicatesKey : [NSNumber numberWithBool:NO]};
[self.centerManager scanForPeripheralsWithServices:uuids options:option];
```

**Note:** To obtain the scanned peripheral devices in the delegate, you can perform secondary filtering through the device name and other related information.

```objc
- (void)centralManager:(CBCentralManager *)central 
 didDiscoverPeripheral:(CBPeripheral *)peripheral 
     advertisementData:(NSDictionary<NSString *,id> *)advertisementData 
                  RSSI:(NSNumber *)RSSI {
    if (peripheral.name.length > 0) {
        [self.peripherals addObject:peripheral];
        [self.deviceList reloadData];
    }
}
```

### 2.4 Cancel the Scan of the Ring

Call the interface of the central role to stop scanning:

```objc
[self.centerManager stopScan];
```

### 2.5 Connect the Ring

Start connecting:

```objc
self.connectedPeripheral = self.peripherals[indexPath.row];
[self.centerManager connectPeripheral:self.connectedPeripheral options:nil];
```

After the connection is successful, pass in the peripheral device to the SDK:

```objc
- (void)centralManager:(CBCentralManager *)central didConnectPeripheral:(CBPeripheral *)peripheral {
    [[QCSDKManager shareInstance] addPeripheral:peripheral];
}
```

### 2.6 Disconnect

```objc
[self.centerManager cancelPeripheralConnection:self.connectedPeripheral];
```

After disconnecting, remove peripherals:

```objc
- (void)centralManager:(CBCentralManager *)central 
didDisconnectPeripheral:(CBPeripheral *)peripheral 
                 error:(nullable NSError *)error {
    [[QCSDKManager shareInstance] removePeripheral:peripheral];
}
```

## Instructions Supported by the Device

### 3.1 Set Ring Time

```objc
/**
 * Set the time of the Ring
 */
+ (void)setTime:(NSDate *)date 
        success:(void (^)(NSDictionary *))suc 
         failed:(void (^)(void))fail;
```

### 3.2 Read Ring Battery

```objc
/*!
 * @func Read Ring battery
 * @param suc battery: Power level (0~8)
 */
+ (void)readBatterySuccess:(void (^)(int battery))suc 
                    failed:(void (^)(void))fail;
```

### 3.3 Bound Vibration

```objc
/**
 * Bound Vibration
 */
+ (void)alertBindingSuccess:(nullable void (^)(void))suc 
                       fail:(nullable void (^)(void))fail;
```

### 3.4 Set Wristband Time Base/User Personal Information

```objc
/**
 * Set wristband time base/user personal information
 * @param twentyfourHourFormat: YES 24 hour system; NO 12 hour system
 * @param metricSystem: YES Metric; NO Imperial
 * @param gender: gender (0=male, 1=female)
 * @param age: age (years)
 * @param height: height (cm)
 * @param weight: weight (kg)
 * @param sbpBase: systolic blood pressure base (mmhg) (reserved value, Defaults:0)
 * @param dbpBase: Diastolic blood pressure base (mmhg) (reserved value, Defaults:0)
 * @param hrAlarmValue: Heart rate alarm value (bpm) (reserved value, Defaults:0)
 */
+ (void)setTimeFormatTwentyfourHourFormat:(BOOL)twentyfourHourFormat
                             metricSystem:(BOOL)metricSystem
                                   gender:(NSInteger)gender
                                      age:(NSInteger)age
                                   height:(NSInteger)height
                                   weight:(NSInteger)weight
                                  sbpBase:(NSInteger)sbpBase
                                  dbpBase:(NSInteger)dbpBase
                             hrAlarmValue:(NSInteger)hrAlarmValue
                                  success:(void (^)(BOOL, BOOL, NSInteger, NSInteger, NSInteger, NSInteger, NSInteger, NSInteger, NSInteger))success
                                     fail:(void (^)(void))fail;
```

### 3.5 Get Ring Time Base/User Personal Information

```objc
/**
 * Get Ring time base/user personal information
 * @param success isTwentyfour: YES 24 hour system; NO 12 hour system
 *                isMetricSystem: YES Metric; NO Imperial
 *                gender: gender (0=male, 1=female)
 *                age: age (years)
 *                height: height (cm)
 *                weight: weight (kg)
 *                sbpBase: systolic blood pressure base (mmhg) (reserved value, Defaults:0)
 *                dbpBase: Diastolic blood pressure base (mmhg) (reserved value, Defaults:0)
 *                hrAlarmValue: Heart rate alarm value (bpm) (reserved value, Defaults:0)
 */
+ (void)getTimeFormatInfo:(nullable void (^)(BOOL isTwentyfour, BOOL isMetricSystem, NSInteger gender, NSInteger age, NSInteger height, NSInteger weight, NSInteger sbpBase, NSInteger dbpBase, NSInteger hrAlarmValue))success
                     fail:(nullable void (^)(void))fail;
```

### 3.6 Get the Version Number of the Ring Firmware

```objc
/**
 * @func Get the version number of the Ring firmware
 * @param success The format of software and hardware version numbers is generally "x.x.x"
 */
+ (void)getDeviceSoftAndHardVersionSuccess:(void (^)(NSString *_Nonnull, NSString *_Nonnull))success
                                      fail:(void (^)(void))fail;
```

### 3.7 Get Current Steps

```objc
/*!
 * @func Get current steps
 */
+ (void)getCurrentSportSucess:(void (^)(SportModel *sport))suc 
                       failed:(void (^)(void))fail;
```

### 3.8 Get Total Statistics for a Day (Steps, Calories, Distance, Time)

```objc
/*!
 * @func Get total statistics for a day
 * @param index: 0->Today 1->1 day ago. Maximum 29
 * @param suc: Using this command cannot accurately obtain the statistical data of the day,
 *             the device will save the data every 15 minutes, so there will be a 15-minute interval
 */
+ (void)getOneDaySportBy:(NSInteger)index 
                 success:(void (^)(SportModel *model))suc 
                    fail:(void (^)(void))fail;
```

### 3.9 Get Detailed Exercise Data for a Day

```objc
/*!
 * @func Get detailed exercise data for a day
 * @discussion There is a tick every 15 minutes, and there will be a maximum of 96 pieces of data per day.
 * @param items sports: return all sports models
 */
+ (void)getSportDetailDataByDay:(NSInteger)dayIndex 
                     sportDatas:(nullable void (^)(NSArray<SportModel *> *sports))items 
                           fail:(nullable void (^)(void))fail;
```

### 3.10 Get Detailed Exercise Data for a Specified Time Period on a Certain Day

```objc
/*!
 * @func Get detailed exercise data for a specified time period on a certain day
 * @param minuteInterval minute interval for each index
 * @param beginIndex time period start index
 * @param endIndex time period end index
 * @param items sports: return all sports models
 */
+ (void)getSportDetailDataByDay:(NSInteger)dayIndex 
                 minuteInterval:(NSInteger)minuteInterval 
                     beginIndex:(NSInteger)beginIndex 
                       endIndex:(NSInteger)endIndex
                     sportDatas:(nullable void (^)(NSArray<SportModel *> *sports))items 
                           fail:(nullable void (^)(void))fail;
```

### 3.11 Get Detailed Sleep Data for a Day

#### Sleep Data Type

```objc
//sleep data type
typedef NS_ENUM(NSInteger, SLEEPTYPE) {
    SLEEPTYPENONE,      //no data
    SLEEPTYPESOBER,     //wide awake
    SLEEPTYPELIGHT,     //light sleep
    SLEEPTYPEDEEP,      //Deep sleep
    SLEEPTYPEUNWEARED   //not worn
};

@interface QCSleepModel : NSObject
@property (nonatomic, assign) SLEEPTYPE type;           //sleep type
@property (nonatomic, strong) NSString *happenDate;     //Start Time yyyy-MM-dd HH:mm:ss
@property (nonatomic, strong) NSString *endTime;       //End Time yyyy-MM-dd HH:mm:ss
@property (nonatomic, assign) NSInteger total;         //Time interval between start time and end time (unit: minutes)
@end
```

#### Get Sleep Data

```objc
/*!
 * @func Get detailed sleep data for a day
 * @discussion The time period corresponding to each sleep type
 * @param items sleeps: return all sleep models
 */
+ (void)getSleepDetailDataByDay:(NSInteger)dayIndex 
                     sleepDatas:(nullable void (^)(NSArray<QCSleepModel *> *sleeps))items 
                           fail:(nullable void (^)(void))fail;
```

### 3.12 Find Ring

```objc
/**
 * Find Ring
 */
+ (void)lookupDeviceSuccess:(void (^)(void))suc 
                       fail:(void (^)(void))fail;
```

### 3.13 Ring to the Camera Interface

```objc
/**
 * Switch the lower computer to the camera interface
 */
+ (void)switchToPhotoUISuccess:(nullable void (^)(void))success 
                          fail:(nullable void (^)(void))fail;
```

### 3.14 Keep the Camera Interface

```objc
/**
 * Keep the camera interface of the lower computer
 */
+ (void)holdPhotoUISuccess:(nullable void (^)(void))success 
                      fail:(nullable void (^)(void))fail;
```

### 3.15 Stop Taking Pictures

```objc
/**
 * Stop the lower computer to take pictures
 */
+ (void)stopTakingPhotoSuccess:(nullable void (^)(void))success 
                          fail:(nullable void (^)(void))fail;
```

### 3.16 Restart the Ring

```objc
/**
 * Restart the Ring
 */
+ (void)resetBandHardlySuccess:(nullable void (^)(void))suc 
                          fail:(nullable void (^)(void))fail;
```

### 3.17 Get Ring Mac Address

```objc
/**
 * @func Get Ring Mac address
 * @param success The Mac address format is "AA:BB:CC:DD:EE:FF"
 */
+ (void)getDeviceMacAddressSuccess:(nullable void (^)(NSString *_Nullable macAddress))success 
                              fail:(nullable void (^)(void))fail;
```

### 3.18 Get Information About the Timed Blood Pressure Measurement Function

```objc
/**
 * Get information about the timed blood pressure measurement function
 * @param success featureOn YES: ON; NO: OFF
 *                beginTime Start time, format "HH:mm"
 *                endTime End time, the format is "HH:mm"
 *                minuteInterval minute interval (minutes)
 */
+ (void)getSchedualBPInfo:(nullable void (^)(BOOL featureOn, NSString *beginTime, NSString *endTime, NSInteger minuteInterval))success 
                     fail:(void (^)(void))fail;
```

### 3.19 Information on Setting the Timed Blood Pressure Measurement Function

```objc
/**
 * Information on setting the timed blood pressure measurement function
 * @param featureOn YES: ON; NO: OFF
 * @param beginTime Start time, format "HH:mm"
 * @param endTime End time, the format is "HH:mm"
 * @param minuteInterval minute interval (minutes)
 */
+ (void)setSchedualBPInfoOn:(BOOL)featureOn 
                  beginTime:(NSString *)beginTime 
                    endTime:(NSString *)endTime 
             minuteInterval:(NSInteger)minuteInterval 
                    success:(nullable void (^)(BOOL featureOn, NSString *beginTime, NSString *endTime, NSInteger minuteInterval))success 
                       fail:(void (^)(void))fail;
```

### 3.20 Obtain Historical Data for Timed Blood Pressure Measurements

```objc
/**
 * Obtain historical data for timed blood pressure measurements
 * @param userAge: User age
 * @param success data: Heart rate module data, the current reply is actually unified as heart rate, 
 *                      which can be processed by itself in the callback
 */
+ (void)getSchedualBPHistoryDataWithUserAge:(NSInteger)userAge 
                                    success:(nullable void (^)(NSArray<BloodPressureModel *> *data))success 
                                       fail:(nullable void (^)(void))fail;
```

### 3.21 Reset the Ring to Factory Settings

```objc
/**
 * Reset the Ring to factory settings
 */
+ (void)resetBandToFacotrySuccess:(nullable void (^)(void))success 
                             fail:(nullable void (^)(void))fail;
```

### 3.22 Get Historical Data of Exercise Records

```objc
/**
 * @func Get historical data of exercise records
 * @param lastUnixSeconds The time when the last exercise data occurred (seconds since 1970-01-01 00:00:00)
 * @note success models Motion data array
 */
+ (void)getExerciseDataWithLastUnixSeconds:(NSUInteger)lastUnixSeconds 
                                   getData:(nullable void (^)(NSArray<ExerciseModel *> *models))getData 
                                      fail:(nullable void (^)(void))fail;
```

### 3.23 Get Historical Data for Manual Blood Pressure Measurements

```objc
/**
 * Get historical data for manual blood pressure measurements
 * @param lastUnixSeconds Time when the last manual blood pressure data occurred (seconds since 1970-01-01 00:00:00)
 * @param success data blood pressure data array
 */
+ (void)getManualBloodPressureDataWithLastUnixSeconds:(NSUInteger)lastUnixSeconds 
                                              success:(nullable void (^)(NSArray<BloodPressureModel *> *data))success 
                                                 fail:(nullable void (^)(void))fail;
```

### 3.24 Get Timed Heart Rate Historical Data

```objc
/**
 * @func Get timed heart rate historical data
 * @param dates: List of dates for which historical data needs to be obtained
 * @note success models Timed heart rate data array
 */
+ (void)getSchedualHeartRateDataWithDates:(NSArray<NSDate *> *)dates 
                                  success:(nullable void (^)(NSArray<SchedualHeartRateModel *> *models))success 
                                     fail:(nullable void (^)(void))fail;

/**
 * @func Get timed heart rate historical data
 * @param dayIndexs The number of days for which historical data needs to be obtained 
 *                  (0->today, 1->yesterday, 2->the day before yesterday, and so on)
 * @note success models Timed heart rate data array
 */
+ (void)getSchedualHeartRateDataWithDayIndexs:(NSArray<NSNumber*> *)dayIndexs 
                                      success:(void (^)(NSArray<QCSchedualHeartRateModel *> *_Nonnull))success 
                                         fail:(void (^)(void))fail;
```

### 3.25 Get Information About the Timed Heart Rate Function

```objc
/**
 * Get information about the timed heart rate function
 * @param success enable Whether the timed heart rate function is enabled. YES: ON; NO: OFF
 */
+ (void)getSchedualHeartRateStatusWithCurrentState:(BOOL)enable 
                                           success:(nullable void (^)(BOOL enable))success 
                                              fail:(nullable void (^)(void))fail;
```

### 3.26 Information on Setting the Timed Heart Rate Function

```objc
/**
 * Information on setting the timed heart rate function
 * @param enable Whether the timed heart rate function is enabled. YES: ON; NO: OFF
 */
+ (void)setSchedualHeartRateStatus:(BOOL)enable 
                           success:(nullable void (^)(BOOL enable))success 
                              fail:(nullable void (^)(void))fail;
```

### 3.27 Get Sports+ (V2) Data Summary Information

```objc
/**
 * According to the specified time stamp, the new version of Sports+ (V2) data summary information
 * @param timestamp
 * @param finished spSummary - Motion+Summary info array
 */
+ (void)getSportPlusSummaryFromTimestamp:(NSTimeInterval)timestamp 
                                finished:(nullable void (^)(NSArray *_Nullable spSummary, NSError *_Nullable error))finished;
```

### 3.28 Get Sports+ Campaign Details

```objc
/**
 * According to the specified new version of the campaign + summary information, get some summary information and detailed data of the campaign
 * @param finished spSummary - Motion+Summary info array
 */
+ (void)getSportPlusDetailsWithSummary:(OdmGeneralExerciseSummaryModel *)summary 
                              finished:(nullable void (^)(OdmGeneralExerciseSummaryModel *_Nullable summary, OdmGeneralExerciseDetailModel *_Nullable detail, NSError *_Nullable error))finished;
```

### 3.29 Get/Set User Target Information

#### Get User Target Information

```objc
/**
 * Get/set user target information
 * @param suc stepTarget: Step target
 *            calorieTarget: Calorie Goal, Unit: Calories
 *            distanceTarget: Distance to target, unit: meters
 *            sportDuration: Exercise duration target Unit: minutes (reserved value, default: 0)
 *            sleepDuration: Sleep duration target unit: minutes (reserved value, default: 0)
 */
+ (void)getStepTargetInfoWithSuccess:(nullable void (^)(NSInteger stepTarget, NSInteger calorieTarget, NSInteger distanceTarget, NSInteger sportDuration, NSInteger sleepDuration))suc 
                                fail:(nullable void (^)(void))fail;
```

#### Set User Target Information

```objc
/**
 * Set user target information
 * @param stepTarget: Step target
 * @param calorieTarget: Calorie Goal, Unit: Calories
 * @param distanceTarget: Distance to target, unit: meters
 * @param sportDuration: Exercise duration target Unit: minutes (reserved value, default: 0)
 * @param sleepDuration: Sleep duration target unit: minutes (reserved value, default: 0)
 */
+ (void)setStepTarget:(NSInteger)stepTarget 
        calorieTarget:(NSInteger)calorieTarget 
       distanceTarget:(NSInteger)distanceTarget 
   sportDurationTarget:(NSInteger)sportDuration 
   sleepDurationTarget:(NSInteger)sleepDuration 
              success:(nullable void (^)(void))suc 
                 fail:(nullable void (^)(void))fail;
```

### 3.30 Obtain Historical Data of Timed Body Temperature Measurement

```objc
/**
 * Obtain historical data of timed body temperature measurement
 */
+ (void)getSchedualTemperatureDataByDayIndex:(NSInteger)dayIndex 
                                    finished:(nullable void (^)(NSArray *_Nullable temperatureList, NSError *_Nullable error))finished;
```

### 3.31 Get Historical Data for Manual Body Temperature Measurements

```objc
/**
 * Get historical data for manual body temperature measurements
 */
+ (void)getManualTemperatureDataByDayIndex:(NSInteger)dayIndex 
                                  finished:(nullable void (^)(NSArray *_Nullable temperatureList, NSError *_Nullable error))finished;
```

### 3.32 Get Historical Data for Blood Oxygen Measurements

```objc
/**
 * Get historical data for blood oxygen measurements
 */
+ (void)getBloodOxygenDataByDayIndex:(NSInteger)dayIndex 
                            finished:(void (^)(NSArray *_Nullable, NSError *_Nullable))finished;
```

### 3.33 Send Firmware File

```objc
/**
 * Send the firmware file and request to use the bin file to upgrade, the result will be processed in the callback
 * @param data OTA binary character stream
 * @param start start sending callback
 * @param percentage progress callback
 * @param success success callback
 * @param failed failure callback
 */
+ (void)syncOtaBinData:(NSData *)data
                 start:(nullable void (^)(void))start
            percentage:(nullable void (^)(int percentage))percentage
               success:(nullable void (^)(int seconds))success
                failed:(nullable void (^)(NSError *error))failed;
```

### 3.34 Receive a Ring Message

```objc
@interface QCSDKManager : NSObject

/*
 * Receive notifications from Ring, find phone
 */
@property(nonatomic,copy)void(^findPhone)(void);

/*
 * Receive notifications from Ring, enter camera
 */
@property(nonatomic,copy)void(^switchToPicture)(void);

/*
 * Receive notification of Ring, take photo
 */
@property(nonatomic,copy)void(^takePicture)(void);

/*
 * Receive a notification from the Ring to end taking pictures
 */
@property(nonatomic,copy)void(^stopTakePicture)(void);

// singleton class instance
+ (instancetype)shareInstance;

@end
```

### 3.35 Set/Get Timed Blood Oxygen Switch Status

#### Set Timed Blood Oxygen Status

```objc
/**
 * Information on setting the timed oximetry function
 * @param featureOn YES: ON; NO: OFF
 */
+ (void)setSchedualBOInfoOn:(BOOL)featureOn 
                    success:(nullable void (^)(BOOL featureOn))success 
                       fail:(void (^)(void))fail;
```

#### Get Timed Blood Oxygen Status

```objc
/**
 * Get information about the timed oximetry function
 * @param success featureOn YES: ON; NO: OFF
 */
+ (void)getSchedualBOInfoSuccess:(nullable void (^)(BOOL featureOn))success 
                            fail:(void (^)(void))fail;
```

### 3.36 Send Measurement Commands

#### Measurement Types

```objc
typedef NS_ENUM(NSInteger, QCMeasuringType) {
    QCMeasuringTypeHeartRate = 0,       //Heart rate measurement
    QCMeasuringTypeBloodPressue,        //blood pressure measurement
    QCMeasuringTypeBloodOxygen,         //blood oxygen measurement
    QCMeasuringTypeOneKeyMeasure,       //One-click measurement
    QCMeasuringTypeStress,
    QCMeasuringTypeBloodGlucose,
    QCMeasuringTypeCount,
};
```

#### Start Measurement

```objc
/**
 * Send measurement order
 * @param type: Measurement type
 * @param measuring: Real-Time Measuring Value
 * @param handle: Measurement result callback (error code: -1: failed to send start command, 
 *                -2: failed to send end command, -3: bracelet is not properly worn)
 */
- (void)startToMeasuringWithOperateType:(QCMeasuringType)type 
                       measuringHandle:(void(^)(id _Nullable result))measuring 
                      completedHandle:(void(^)(BOOL isSuccess, id _Nullable result, NSError *_Nullable error))handle;

/**
 * Send measurement order with timeout
 * @param type: Measurement type
 * @param timeout: Timeout value
 * @param measuring: Real-Time Measuring Value
 * @param handle: Measurement result callback
 */
- (void)startToMeasuringWithOperateType:(QCMeasuringType)type 
                                timeout:(NSInteger)timeout
                       measuringHandle:(void(^)(id _Nullable result))measuring 
                      completedHandle:(void(^)(BOOL isSuccess, id _Nullable result, NSError *_Nullable error))handle;
```

#### Stop Measurement

```objc
/**
 * stop measurement command
 * @param type: Measurement type
 * @param handle: Measurement result callback (error code:-1: Failed to send end command)
 */
- (void)stopToMeasuringWithOperateType:(QCMeasuringType)type 
                      completedHandle:(void(^)(BOOL isSuccess, NSError *error))handle;
```

#### Measurement Results

- **When measuring heart rate**: result returns NSNumber: @(60)
- **When measuring blood pressure**: result returns NSDictionary: @{@"sbp":@"120", @"dbp":@"60"}
- **When measuring blood oxygen**: result returns NSNumber: @(98)

### 3.37 Sleep Protocol (Get a Day to Today)

```objc
/*!
 * @func Get all sleep data from a certain day to today
 * @param fromDayIndex The number of days from today, (0: means today, 1: means yesterday)
 * @param items Returned sleep data (key: days from today, value: corresponding sleep data)
 * @param fail failed callback
 */
+ (void)getSleepDetailDataFromDay:(NSInteger)fromDayIndex 
                       sleepDatas:(nullable void (^)(NSDictionary <NSString*,NSArray<QCSleepModel*>*>*_Nonnull))items 
                             fail:(nullable void (^)(void))fail;
```

### 3.38 RealTime HeartRate Measuring

#### Command Types

```objc
typedef enum {
    QCBandRealTimeHeartRateCmdTypeStart = 0x01,  //Start real-time heart rate measurement
    QCBandRealTimeHeartRateCmdTypeEnd,           //End real-time heart rate measurement
    QCBandRealTimeHeartRateCmdTypeHold,          //Continuous heart rate test (for continuous measurement to keep alive)
} QCBandRealTimeHeartRateCmdType;
```

#### Real-Time Heart Rate Measurement

```objc
/**
 * RealTime HeartRate Measuring
 * @param type: command type
 * @param finished: finish callback
 */
+ (void)realTimeHeartRateWithCmd:(QCBandRealTimeHeartRateCmdType)type 
                        finished:(nullable void (^)(BOOL))finished;
```

### 3.39 Set Sport Mode State (Only Ring Support)

```objc
/**
 * Set Sport Mode State
 * @param sportType: type
 * @param state: state
 * @param finished: finished callback
 */
+ (void)operateSportModeWithType:(OdmSportPlusExerciseModelType)sportType 
                           state:(QCSportState)state 
                          finish:(void(^)(id _Nullable, NSError *_Nullable))finished;
```

#### Get Sport Mode Callback

```objc
[QCSDKManager shareInstance].currentSportInfo = ^(QCSportInfoModel * _Nonnull sportInfo) {
    NSLog(@"sportType:%zd,duration:%zd,state:%u,hr:%zd,step:%zd,calorie(unit:calorie):%zd,distance(unit:meter):%zd",
          sportInfo.sportType, sportInfo.duration, sportInfo.state, sportInfo.hr, sportInfo.step, sportInfo.calorie, sportInfo.distance);
};
```

### 3.40 Get Schedual Stress Datas (Only Ring Support)

```objc
/**
 * Get Schedual Stress Datas (Only Ring Support)
 * @param dates: 0-6, 0:today, 1:yesterday....
 * @param finished: finished callback
 */
+ (void)getSchedualStressDataWithDates:(NSArray<NSNumber*> *)dates 
                              finished:(void (^)(NSArray *_Nullable, NSError *_Nullable))finished;
```

#### Get Schedual Stress Status

```objc
/**
 * Get Schedual Stress Status
 * @param finished: finished callback
 */
+ (void)getSchedualStressStatusWithFinshed:(nullable void (^)(BOOL, NSError *_Nullable error))finished;
```

#### Set Schedual Stress Status

```objc
/**
 * Set Schedual Stress Status
 * @param enable: YES:On, NO:Off
 * @param finished: finished callback
 */
+ (void)setSchedualStressStatus:(BOOL)enable 
                        finshed:(nullable void (^)(NSError *_Nullable error))finished;
```

### 3.42 Get Schedual HRV Datas (Only Ring Support)

```objc
/**
 * Get Schedual HRV Datas (Only Ring Support)
 * @param dates: 0-6, 0:today, 1:yesterday....
 * @param finished: finished callback
 */
+ (void)getSchedualHRVDataWithDates:(NSArray<NSNumber*> *)dates 
                           finished:(void (^)(NSArray *_Nullable, NSError *_Nullable))finished;
```

#### Get Schedual HRV Status

```objc
/**
 * Get Schedual HRV Status
 * @param finished: finished callback
 */
+ (void)getSchedualHRVWithFinshed:(nullable void (^)(BOOL, NSError *_Nullable error))finished;
```

#### Set Schedual HRV Status

```objc
/**
 * Set Schedual HRV Status
 * @param enable: YES:On, NO:Off
 * @param finished: finished callback
 */
+ (void)setSchedualHRVStatus:(BOOL)enable 
                     finshed:(nullable void (^)(NSError *_Nullable error))finished;
```

### 3.43 Get Touch Control Type

```objc
/**
 * Get Touch Control Type
 * @param finished: callback -> type:QCTouchGestureControlType, strength:1-10
 */
+ (void)getTouchControlFinshed:(nullable void (^)(QCTouchGestureControlType, NSInteger, NSError *_Nullable error))finished;
```

#### Set Touch Control Type

```objc
/**
 * Set Touch Control Type
 * @param type: type
 * @param strength: 1-10
 * @param finished: callback
 */
+ (void)setTouchControl:(QCTouchGestureControlType)type 
               strength:(NSInteger)strength 
                finshed:(nullable void (^)(NSError *_Nullable error))finished;
```

#### Get Gesture Control Type

```objc
/**
 * Get Gesture Control Type
 * @param finished: callback -> type:QCTouchGestureControlType, strength:1-10
 */
+ (void)getGestureControlFinshed:(nullable void (^)(QCTouchGestureControlType, NSInteger, NSError *_Nullable error))finished;
```

#### Set Gesture Control Type

```objc
/**
 * Set Gesture Control Type
 * @param type: type
 * @param strength: 1-10
 * @param finished: callback
 */
+ (void)setGestureControl:(QCTouchGestureControlType)type 
                 strength:(NSInteger)strength 
                  finshed:(nullable void (^)(NSError *_Nullable error))finished;
```

### 3.43 Wearing Calibration

```objc
/**
 * Wearing Calibration
 * @param type: 1->Start calibration (reset ring data), 2->End calibration, 3->Get single data, 
 *              4->Power consumption mode, 5->Stop power consumption, 6->App starts calibration
 * @param finished: finished callback
 */
+ (void)wearCalibration:(NSInteger)type 
                finshed:(nullable void (^)(NSError *_Nullable error))finished;
```

### 3.44 Get Sedentary Reminder

```objc
/**
 * Get Sedentary Reminder (Only Ring Support)
 * @param fromDayIndex: 0->Today, 1->Yesterday, 2->The day before yesterday ....
 * @param finished: callback
 */
+ (void)getSedentaryReminderFromDay:(NSInteger)fromDayIndex 
                           finished:(nullable void (^)(NSDictionary <NSString*,NSArray<QCSedentaryModel*>*>*_Nullable datas, NSError *_Nullable error))finished;
```

## Notes

- All methods are class methods and should be called on the appropriate class
- Remember to handle both success and failure callbacks appropriately
- Some features are Ring-specific as indicated in the method descriptions
- Battery level is returned as an integer from 0-8
- Time formats should follow the specified patterns (e.g., "HH:mm" for time strings)
- Day index parameters typically use 0 for today, 1 for yesterday, etc.
- Error codes are documented in the measurement command sections

## Error Codes

### Measurement Command Error Codes:
- **-1**: Failed to send start command
- **-2**: Failed to send end command  
- **-3**: Bracelet is not properly worn

## Data Models

The SDK uses various data models including:
- `SportModel`: For exercise/sport data
- `QCSleepModel`: For sleep data with sleep types
- `BloodPressureModel`: For blood pressure measurements
- `ExerciseModel`: For exercise records
- `SchedualHeartRateModel`: For scheduled heart rate data
- `QCSportInfoModel`: For current sport information
- `QCSedentaryModel`: For sedentary reminder data

Make sure to import the appropriate headers and handle the callback data according to these model structures.