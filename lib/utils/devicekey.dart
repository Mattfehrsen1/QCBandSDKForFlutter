class DeviceKey {
  static const String DataType = "dataType";
  static const String Data = "dicData";
  static const String End = "dataEnd";
  static const String BatteryLevel =
      "batteryLevel"; // 电量级别    READ_DEVICE_BATTERY

  static const String GetDeviceBatteryLevel = "9";
  static const String GetAutomaticHRMonitoring = "16";
  /*
     *  GET_AUTOMIC_HEART
     *workModel         工作模式
     *heartStartHour    开始运动时间的小时
     *heartStartMinter  开始运动时间的分钟
     *heartEndHour      结束运动时间的小时
     *heartEndMinter      结束运动时间的分钟
     *heartWeek         星期使能
     *workTime          工作模式时间
     */
  static const String WorkMode = "workModel";
  static const String StartTime = "heartStartHour";
  static const String KHeartStartMinter = "heartStartMinter";
  static const String EndTime = "heartEndHour";
  static const String KHeartEndMinter = "heartEndMinter";
  static const String Weeks = "weekValue";
  static const String IntervalTime = "intervalTime";
}
