import 'package:flutter_blue_plus/flutter_blue_plus.dart';

class QcBandSdkConst {
  // Service & Characteristic UUIDs
  static final uuidService = Guid('6e40fff0-b5a3-f393-e0a9-e50e24dcca9e');
  static final uuidRead = Guid('6e400003-b5a3-f393-e0a9-e50e24dcca9e');
  static final uuidWrite = Guid('6e400002-b5a3-f393-e0a9-e50e24dcca9e');
  static final gattNotifyConfig = Guid('00002902-0000-1000-8000-00805f9b34fb');
  static final serviceDeviceInfo = Guid('0000180A-0000-1000-8000-00805F9B34FB');
  static final charFirmwareRevision =
      Guid('00002A26-0000-1000-8000-00805F9B34FB');
  static final charHwRevision = Guid('00002A27-0000-1000-8000-00805F9B34FB');
  static final charSoftwareRevision =
      Guid('00002A28-0000-1000-8000-00805F9B34FB');
  static final serialPortService = 'de5bf728-d711-4e47-af26-65e3012a5dc7';
  static final serialPortNotify = 'de5bf729-d711-4e47-af26-65e3012a5dc7';
  static final serialPortWrite = 'de5bf72a-d711-4e47-af26-65e3012a5dc7';

  // BLE Constants
  static const int cmdDataLength = 16;
  static const int flagMaskError = 128;
  static const int rspOk = 0;
  static const int stringLimit = 64;
  static const int bandPressureCount = 50;

  // BLE Commands (converted as strings for easy mapping)
  static const String cmdSetDeviceTime = '1';
  static const String cmdTakingPicture = '2';
  static const cmdGetDeviceElectricityValue = 3;
  static const cmdStepDataToday = 72;
  static const cmdStepDataDetails = 67;
  static const cmdHrData = 22;
  static const cmdDeviceCalibaration = 115;
  static const cmdReadHrData = 21;
  static const cmdStartHeartRateInt = 105;
  static const cmdGetRealTimeHeartRate = 30;
  static const int cmdHrv = 57;
  static const int liveHeart = 6;
  static const int setTime = 1;
  static const int actionBloodOxygen = 188;
  static const int getBloodPressureWrong = 55;
  static const int getBloodPressure = 13;
  static const int getSleepData = 39;

  static const int ACTION_START = 1;
  static const int ACTION_PAUSE = 2;
  static const int ACTION_CONTINUE = 3;
  static const int ACTION_STOP = 4;

  static const String cmdDeviceFunctionSupport = '60';
  static const String cmdDeviceTouch = '59';
  static const String cmdSetPhoneOs = '4';
  static const String cmdGetStepTotalSomeday = '7';
  static const String cmdSetAlarmClock = '35';
  static const String cmdGetAlarmClock = '36';
  // Int variants for parsing and building classic 16-byte commands
  static const int cmdSetAlarmClockInt = 35;
  static const int cmdGetAlarmClockInt = 36;
  static const String cmdDeviceRevision = '-95';
  static const String cmdSetSitLong = '37';
  static const String cmdGetSitLong = '38';
  static const String cmdSetDrinkTime = '39';
  static const String cmdGetDrinkTime = '40';
  static const String cmdGetStepSomedayDetail = '67';
  static const String cmdQueryDataDistribution = '70';
  static const String cmdGetStepToday = '72';
  static const String cmdAntiLostRate = '80';
  static const String cmdGetTimeSetting = '10';
  static const String cmdSetAncsOnOff = '96';
  static const String cmdGetAncsOnOff = '97';
  static const String cmdStartHeartRate = '105';
  static const String cmdStopHeartRate = '106';
  static const String cmdHealthEcgStart = '108';
  static const String cmdHealthEcgData = '109';
  static const String cmdHealthPpgData = '110';
  static const String cmdEcgStatusData = '111';
  static const String cmdEcgMeasureTime = '112';
  static const String cmdTuneTimeDirect = '115';
  static const String cmdTuneTimeInverse = '116';
  static const String cmdMsgNotify = '114';
  static const String cmdMsgGetHwAndFwVersion = '-109';
  static const String cmdPushMsg = '114';
  static const String cmdFanwan = '5';
  static const String cmdMute = '6';
  static const String cmdIntell = '9';
  static const String cmdGetSleep = '68';
  static const String cmdReboot = '8';
  static const String cmdRestore = '-1';
  static const String cmdBindSuccess = '16';
  static const String cmdDisplayClock = '18';
  static const String cmdDisplayStyle = '42';
  static const String cmdBpTimingMonitorSwitch = '12';
  static const String cmdBpTimingMonitorData = '13';
  static const String cmdBpTimingMonitorConfirm = '14';
  static const String cmdHrTimingMonitorSwitch = '22';
  static const String cmdHrTimingMonitorData = '13';
  static const String cmdHrTimingMonitorConfirm = '14';
  static const String cmdOrientation = '41';
  static const String cmdPhoneNotify = '17';
  static const String cmdGetSport = '19';
  static const String cmdGetBandPressure = '20';
  static const String cmdCalibrationRate = '32';
  static const String cmdGetHeartRate = '21';
  static const String cmdGetPersonalizationSetting = '23';
  static const String cmdGetDegreeSwitch = '25';
  static const String cmdSendWeatherForecast = '26';
  static const String cmdGetBrightness = '27';
  static const String cmdGetMusicSwitch = '28';
  static const String cmdMusicCommand = '29';
  static const String cmdDisplayTime = '31';
  static const String cmdGpsOnline = '84';
  static const String cmdPackageLength = '47';
  static const String cmdMenstruation = '43';
  static const String cmdDeviceNotify = '115';
  static const String cmdDeviceDialIndex = '117';
  static const String cmdDeviceBatterySaving = '118';
  static const String cmdPhoneSport = '119';
  static const String cmdPhoneSportNotify = '120';
  static const String cmdTargetSetting = '33';
  static const String cmdFindThePhone = '34';
  static const String cmdAgpsSwitch = '48';
  static const String intellTime = '5';
  static const String toOta = '15';
  static const String cmdTestOpen = '-55';
  static const String cmdTestClose = '-54';
  static const String cmdAutoBloodOxygen = '44';
  // Numeric variant for auto SpO2 setting/history command id
  static const int cmdAutoBloodOxygenInt = 44;
  static const String cmdRealTimeHeartRate = '30';
  static const String cmdPressureSetting = '54';
  static const String cmdPressure = '55';
  static const String cmdHrvEnable = '56';
  static const int cmdHrvEnableInt = 56;
  // Numeric variants for switch/parse routing
  static const int cmdPressureInt = 55;
  static const int cmdPressureSettingInt = 54;
  // static const String cmdHrv = '57';
}
