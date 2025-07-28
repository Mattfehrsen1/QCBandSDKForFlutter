Analyzing a decompiled APK requires referencing the Android Documentation to properly understand its components, including any integrated .aar libraries

1. unable to find the alarm option in ios Documentation . [ Check the Demo Application SDK ] [ Left]
2. unable to find the alarm option in android Documentation .
3. Checking the decompiled apk
4. Made the Android Documentation but did not able to get the command write correct 
5. Checking the Android App with Charactertics listen Might able to find the command.  
Found these two 
[38, 8, 0, 32, 0, 127, 60, 0, 0, 0, 0, 0, 0, 0, 0, 9] Explaination it might be sendimentory alarm as I think 60 is representing. Reminder will go on after 60 min and that was the case. 8 might start from 8 am and next 0 means mintues. Regarding 32 I think it mean 10 but verfication needed. with 127 it might mean if alarm is on or off. 
[188, 44, 6, 0, 233, 127, 1, 1, 4, 190, 78, 2] Explaination it might be alarm get response. In the app I have 1 alarm for 09:50 time. On from Monday to Friday and is On. Doing the Off the Alarm and checking the response. 
[188, 44, 6, 0, 232, 151, 1, 1, 4, 62, 78, 2]
and for sendimentory which I think is 
[38, 8, 0, 32, 0, 127, 60, 0, 0, 0, 0, 0, 0, 0, 0, 9]. So, theory is correct that 38 is the command Id of the sendimentory alarm and [ 188,44 ] is the command of the alarms. 
Things to Remember the [38, 8, 0, 32, 0, 127, 60, 0, 0, 0, 0, 0, 0, 0, 0, 9] is using the characteristic_uuid: 6e400003-b5a3-f393-e0a9-e50e24dcca9e
and 
[188, 44, 6, 0, 232, 151, 1, 1, 4, 62, 78, 2] is using de5bf729-d711-4e47-af26-65e3012a5dc7
Running this command 
[188, 44, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 232]
Returns 
[188, 44, 1, 0, 62, 129, 2] -> Disection needed. 
Running this command 
[188, 44, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 233]
Returns 
[188, 44, 1, 0, 62, 129, 2] -> Disection needed. 
Running this command 
[188, 44, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 233]
Returns 
[188, 44, 1, 0, 62, 129, 2] -> Disection needed. 
Running this command 
[188, 44, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 233]
Returns 
[188, 44, 1, 0, 62, 129, 2] -> Disection needed. 
--
So, the response when changing the value[2] to 0,1,2 is the same so, it means Documentation of the get Alarms is completely wrong. Also response from the provider app is 
[188, 44, 6, 0, 232, 151, 1, 1, 4, 62, 78, 2]
but my response is 
[188, 44, 1, 0, 62, 129, 2] Might be the issue with the command. Write the value[3] to 6 and check the reponse. No same response. 
Left Check the iOS sdk to check whether they have the alarm set Options.
When on the alarm of 9:50 this  is response [38, 8, 0, 32, 0, 127, 60, 0, 0, 0, 0, 0, 0, 0, 0, 9]
--
Created two alarms to check if the value changes Recieved from the provider app
[188, 44, 10, 0, 206, 229, 1, 2, 4, 190, 78, 2, 4, 255, 149, 2]
Created two alarms to check if the value changes Recieved from the flutter app command. 
 [188, 44, 1, 0, 62, 129, 2]
 ----
 Found the Option in iOS side in the 
 /**
 *  Get the wristband alarm clock
 *
 *  获取手环闹钟
 */
+ (void)getBandAlarmsWithFinish:(void(^)(NSArray <QCAlarmModel*>* _Nullable,NSError * _Nullable))finished;
2025-07-28 20:16:03.008281+0500 QCBandSDKDemo[691:45103] [QCLog] Phone >>>> Band:{length = 7, bytes = 0xbc2c01007e8001}
2025-07-28 20:16:03.182748+0500 QCBandSDKDemo[691:45103] [QCLog] Band >>>> Phone:{length = 16, bytes = 0xbc2c0a00cee5010204be4e0204ff9502}
2025-07-28 20:16:03.183587+0500 QCBandSDKDemo[691:45103] Successfully retrieved 2 alarms:
2025-07-28 20:16:03.184012+0500 QCBandSDKDemo[691:45103] Alarm: Name='(null)', Time=09:50, Type=1, RepeatDays=(
    0,
    1,
    1,
    1,
    1,
    1,
    0
)
2025-07-28 20:16:03.184411+0500 QCBandSDKDemo[691:45103] Alarm: Name='(null)', Time=11:01, Type=1, RepeatDays=(
    1,
    1,
    1,
    1,
    1,
    1,
    1
)
2025-07-28 20:16:15.710161+0500 QCBandSDKDemo[691:45103] [QCLog] Band >>>> Phone:{length = 16, bytes = 0x02020000000000000000000000000004}
2025-07-28 20:16:15.710873+0500 QCBandSDKDemo[691:45103] [QCLog] 拍照数据状态:2
2025-07-28 20:16:15.711156+0500 QCBandSDKDemo[691:45103] Click on the watch to take a photo
2025-07-28 20:16:27.262457+0500 QCBandSDKDemo[691:45103] [QCLog] Band >>>> Phone:{length = 16, bytes = 0x02020000000000000000000000000004}
2025-07-28 20:16:27.263027+0500 QCBandSDKDemo[691:45103] [QCLog] 拍照数据状态:2
2025-07-28 20:16:27.263301+0500 QCBandSDKDemo[691:45103] Click on the watch to take a photo
/**
 *  Set the wristband alarm clock
 *
 *  设置手环闹钟
 */
+ (void)setBandAlarms:(NSArray <QCAlarmModel*>*)alarms finish:(void(^)(NSArray * _Nullable,NSError * _Nullable))finished;


/**
 *
 *  Period reminder function setting: send the setting to the wristband
 *
 *  经期提醒功能设置：发送设置到手环
 *
 * @param open                                   :Switch 1=on, 0 off, 2=invalid (when the APP reads the configuration from the wristband, if this bit is 2, the wristband parameter is invalid)
 * @param durationday                   :Menstrual period duration, in days; (default 6 days)
 * @param intervalday                   :Menstrual cycle, unit day; (default 28 days)
 * @param startday                          :How many days ago was the last start, 0=starts today; (default 0 means, APP does not display)
 * @param endday                              :How many days ago was the last end, 0=end today; (the default value of 0 means that the APP does not display) (when endday is not equal to startday+durationday, it means that the user manually modified the end time)
 * @param remindOpen                    :Reminder switch 1=on, others off; (default off)
 * @param beforemenstrday         :How many days in advance to remind the menstrual period, 1 = one day in advance. 1~3. (default 2)
 * @param beforeovulateday      :How many days in advance to remind the ovulation period, 1~3. (default 2)
 * @param hour                                 :Reminder time point-Hour
 * @param minute                            :Reminder time point - minutes