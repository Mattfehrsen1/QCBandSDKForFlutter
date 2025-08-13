package com.qcwireless.qcwatch.ui.base.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.ui.base.receiver.MyAlarmReceiver;
import com.qcwireless.qcwatch.ui.base.receiver.XReceiver;
import com.qcwireless.qcwatch.ui.base.util.NotificationUtils;
import java.util.Calendar;

/* loaded from: classes3.dex */
public class XService extends Service {
    public static Intent serviceIntent;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        serviceIntent = intent;
        if (Build.VERSION.SDK_INT >= 26) {
            Notification notificationInitBandNotification = new NotificationUtils(QJavaApplication.getInstance().getApplication()).initBandNotification();
            if (notificationInitBandNotification != null) {
                startForeground(NotificationUtils.QCNotification, notificationInitBandNotification);
            } else {
                startForeground(NotificationUtils.QCNotification, new Notification());
            }
        }
        MyAlarmReceiver.completeWakefulIntent(intent);
        return 2;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        serviceIntent = null;
        if (Build.VERSION.SDK_INT >= 23) {
            setAlarmTimer();
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            Notification notificationInitBandNotification = new NotificationUtils(QJavaApplication.getInstance().getApplication()).initBandNotification();
            if (notificationInitBandNotification != null) {
                startForeground(NotificationUtils.QCNotification, notificationInitBandNotification);
            } else {
                startForeground(NotificationUtils.QCNotification, new Notification());
            }
        }
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    protected void setAlarmTimer() {
        XLog.e("99999999999999999999");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(13, 1);
        Intent intent = new Intent(this, (Class<?>) XReceiver.class);
        intent.setAction("com.qcwx.test");
        sendBroadcast(intent);
        ((AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM)).set(0, calendar.getTimeInMillis(), PendingIntent.getBroadcast(this, 0, intent, 201326592));
    }
}
