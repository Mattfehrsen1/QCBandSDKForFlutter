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