package com.qcwireless.qcwatch.ui.base.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.pref.PreUtil;
import com.qcwireless.qcwatch.ui.activity.MainActivity;
import com.qcwireless.qcwatch.ui.device.camera.CameraActivity;
import com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity;

/* loaded from: classes3.dex */
public class NotificationUtils extends ContextWrapper {
    public static final int QCNotification = 95279568;
    private static final String TAG = "QWatch_Pro";
    public static final String cameraId = "com.qcwatch_pro.channel_3";
    public static final String id = "com.qcwatch_pro.channel_2";
    public static boolean lowBatteryNotification = false;
    public static final String name = "com.qcwatch_pro.channel_name_2";
    private NotificationManager mManager;
    public Notification notification;

    public NotificationUtils(Context context) {
        super(context);
        initManager();
    }

    private void initManager() {
        if (this.mManager == null) {
            this.mManager = (NotificationManager) getSystemService("notification");
        }
    }

    public Notification initBandNotification() {
        try {
            XLog.i("----initBandNotification");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String str = "--";
            if (BleOperateManager.getInstance().isConnected()) {
                str = PreUtil.getInt(PreUtil.Action_Today_Steps, 0) + "";
            }
            if (Build.VERSION.SDK_INT >= 26) {
                createNotificationChannel();
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id);
                builder.setSmallIcon(R.mipmap.notification).setContentTitle(getResources().getString(R.string.app_name)).setContentText(getResources().getString(R.string.qc_text_76, str + "")).setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) MainActivity.class), 201326592)).setOngoing(true).setSound(null).setLights(0, 0, 0).setVibrate(null).setWhen(System.currentTimeMillis()).setPriority(0);
                this.notification = builder.build();
            } else {
                PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) MainActivity.class), 201326592);
                NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this);
                builder2.setSmallIcon(R.mipmap.notification).setContentTitle(getResources().getString(R.string.app_name)).setContentText(getResources().getString(R.string.qc_text_76, str + "")).setContentIntent(activity).setWhen(System.currentTimeMillis()).setOngoing(true).setSound(null).setLights(0, 0, 0).setVibrate(null);
                this.notification = builder2.build();
            }
            this.notification.flags = 2;
            this.notification.flags |= 32;
            this.notification.flags |= 64;
            this.mManager.notify(QCNotification, this.notification);
            return this.notification;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public Notification initCameraNotification() {
        try {
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            if (Build.VERSION.SDK_INT >= 26) {
                notificationManager.createNotificationChannel(new NotificationChannel("001", "channel_name", 4));
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(QJavaApplication.getInstance().getApplication(), "001");
            builder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) CameraActivity.class), 201326592));
            builder.setSmallIcon(R.mipmap.notification).setContentTitle(getResources().getString(R.string.app_name));
            builder.setContentText(getResources().getString(R.string.qc_text_513));
            Notification notificationBuild = builder.build();
            this.notification = notificationBuild;
            notificationManager.notify(9999, notificationBuild);
            return this.notification;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void cancelNotification() {
        try {
            this.mManager.cancel(9999);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Notification initCSNotification() {
        try {
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            if (Build.VERSION.SDK_INT >= 26) {
                notificationManager.createNotificationChannel(new NotificationChannel("002", "channel_name", 4));
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(QJavaApplication.getInstance().getApplication(), "002");
            builder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) QcChatActivity.class), 201326592));
            builder.setSmallIcon(R.mipmap.notification).setContentTitle(getResources().getString(R.string.app_name));
            builder.setContentText(getResources().getString(R.string.ring_text_mine_123));
            Notification notificationBuild = builder.build();
            this.notification = notificationBuild;
            notificationManager.notify(9999, notificationBuild);
            return this.notification;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Notification initLowBatteryNotification() {
        try {
            lowBatteryNotification = true;
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            if (Build.VERSION.SDK_INT >= 26) {
                notificationManager.createNotificationChannel(new NotificationChannel("001", "channel_name", 4));
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(QJavaApplication.getInstance().getApplication(), "001");
            builder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) MainActivity.class), 201326592));
            builder.setSmallIcon(R.mipmap.notification).setContentTitle(getResources().getString(R.string.app_name));
            builder.setContentText(getResources().getString(R.string.qc_text_76660022));
            Notification notificationBuild = builder.build();
            this.notification = notificationBuild;
            notificationManager.notify(9998, notificationBuild);
            return this.notification;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createNotificationChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(id, name, 3);
        notificationChannel.enableLights(false);
        notificationChannel.enableVibration(false);
        notificationChannel.setSound(null, null);
        this.mManager.createNotificationChannel(notificationChannel);
    }
}
