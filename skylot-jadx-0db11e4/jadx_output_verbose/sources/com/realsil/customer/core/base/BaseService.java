package com.realsil.customer.core.base;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.IBinder;
import android.view.Display;
import androidx.annotation.Keep;
import androidx.core.app.NotificationCompat;
import com.realsil.customer.core.logger.ZLogger;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/base/BaseService.class */
public abstract class BaseService extends Service {

    @Keep
    protected Context mContext;
    public DisplayManager a;

    @Keep
    protected int notificationId = 1230;
    public final DisplayManager.DisplayListener b = new DisplayManager.DisplayListener() { // from class: com.realsil.customer.core.base.BaseService.1
        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int i) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int i) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int i) {
        }
    };

    public String getChannelId() {
        return "rtk_channel_id";
    }

    public String getChannelName() {
        return "rtk_channel_name";
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
        DisplayManager displayManager = (DisplayManager) getSystemService("display");
        this.a = displayManager;
        if (displayManager != null) {
            displayManager.registerDisplayListener(this.b, null);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(getChannelId(), getChannelName(), 0);
            notificationChannel.setLightColor(-16776961);
            notificationChannel.setLockscreenVisibility(0);
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        ZLogger.v("in onBind()");
        return null;
    }

    @Override // android.app.Service
    public void onRebind(Intent intent) {
        ZLogger.v("in onRebind()");
        super.onRebind(intent);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        ZLogger.d("Last client unbound from service");
        return true;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        DisplayManager displayManager = this.a;
        if (displayManager != null) {
            displayManager.unregisterDisplayListener(this.b);
        }
        if (Build.VERSION.SDK_INT >= 24) {
            stopForeground(1);
        } else {
            stopForeground(true);
        }
    }

    public boolean isScreenOn() {
        Display[] displays;
        DisplayManager displayManager = this.a;
        if (displayManager == null || (displays = displayManager.getDisplays()) == null) {
            return false;
        }
        for (Display display : displays) {
            if (Build.VERSION.SDK_INT >= 20 && display.getState() == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean isServiceRunningInForeground(Context context) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE)) {
            if (getClass().getName().equals(runningServiceInfo.service.getClassName()) && runningServiceInfo.foreground) {
                return true;
            }
        }
        return false;
    }

    public int getNotificationId() {
        return this.notificationId;
    }

    public void setNotificationId(int i) {
        this.notificationId = i;
    }

    public Notification getNotification(String str, String str2) {
        NotificationCompat.Builder when = new NotificationCompat.Builder(this, getChannelId()).setContentText(str2).setContentTitle(str).setOngoing(true).setPriority(1).setWhen(System.currentTimeMillis());
        if (Build.VERSION.SDK_INT >= 26) {
            when.setChannelId(getChannelId());
        }
        return when.build();
    }

    public Notification buildNotification(Context context) {
        Notification notificationBuild;
        Notification.Builder builder;
        int i = Build.VERSION.SDK_INT;
        if (i >= 16) {
            if (i >= 26) {
                builder = builder;
                Notification.Builder builder2 = new Notification.Builder(getApplicationContext(), getChannelId());
            } else {
                builder = builder;
                Notification.Builder builder3 = new Notification.Builder(getApplicationContext());
            }
            Notification.Builder builder4 = builder;
            builder4.setContentText("GuardService").setWhen(System.currentTimeMillis());
            notificationBuild = builder4.build();
        } else {
            notificationBuild = notification;
            Notification notification = new Notification();
        }
        return notificationBuild;
    }

    public void showNotification(String str, String str2, ComponentName componentName) {
        Intent intent = new Intent();
        intent.setComponent(componentName);
        showNotification(str, str2, PendingIntent.getActivity(this, 0, intent, 0), -1, -1);
    }

    public void showNotification(String str, String str2, PendingIntent pendingIntent, int i, int i2) {
        Notification notificationBuild;
        Notification.Builder builder;
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 16) {
            if (i3 >= 26) {
                builder = builder;
                Notification.Builder builder2 = new Notification.Builder(getApplicationContext(), getChannelId());
            } else {
                builder = builder;
                Notification.Builder builder3 = new Notification.Builder(getApplicationContext());
            }
            if (pendingIntent != null) {
                builder.setContentIntent(pendingIntent);
            }
            if (i != -1) {
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), i));
            }
            if (i2 != -1) {
                builder.setSmallIcon(i2);
            }
            Notification.Builder builder4 = builder;
            builder4.setContentTitle(str).setContentText(str2).setWhen(System.currentTimeMillis());
            notificationBuild = builder4.build();
        } else {
            notificationBuild = notification;
            Notification notification = new Notification();
        }
        if (notificationBuild != null) {
            notificationBuild.flags = 34;
            startForeground(getNotificationId(), notificationBuild);
        }
    }
}
