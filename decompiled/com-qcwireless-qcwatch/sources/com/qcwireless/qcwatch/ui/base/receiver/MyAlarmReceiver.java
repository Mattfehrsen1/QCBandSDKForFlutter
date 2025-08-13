package com.qcwireless.qcwatch.ui.base.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.legacy.content.WakefulBroadcastReceiver;
import androidx.work.PeriodicWorkRequest;
import com.qcwireless.qcwatch.ui.base.service.YService;

/* loaded from: classes3.dex */
public class MyAlarmReceiver extends WakefulBroadcastReceiver {
    private PendingIntent alarmIntent;
    private AlarmManager alarmMgr;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intent intent2 = new Intent(context, (Class<?>) YService.class);
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(intent2);
                startWakefulService(context, intent2);
            } else {
                startWakefulService(context, intent2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAlarm(Context context) {
        this.alarmMgr = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, new Intent(context, (Class<?>) MyAlarmReceiver.class), 201326592);
        this.alarmIntent = broadcast;
        this.alarmMgr.cancel(broadcast);
        this.alarmMgr.setInexactRepeating(2, PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, this.alarmIntent);
        context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, (Class<?>) RebootReceiver.class), 1, 1);
    }
}
