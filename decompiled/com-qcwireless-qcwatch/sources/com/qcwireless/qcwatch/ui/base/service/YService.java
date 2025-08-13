package com.qcwireless.qcwatch.ui.base.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.ui.base.util.NotificationUtils;

/* loaded from: classes3.dex */
public class YService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
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
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Build.VERSION.SDK_INT >= 26) {
            Notification notificationInitBandNotification = new NotificationUtils(QJavaApplication.getInstance().getApplication()).initBandNotification();
            if (notificationInitBandNotification != null) {
                startForeground(NotificationUtils.QCNotification, notificationInitBandNotification);
            } else {
                startForeground(NotificationUtils.QCNotification, new Notification());
            }
        }
        Intent intent2 = new Intent(this, (Class<?>) XService.class);
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                startForegroundService(intent2);
            } else {
                startService(intent2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        stopForeground(true);
        stopSelf();
        return 2;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
