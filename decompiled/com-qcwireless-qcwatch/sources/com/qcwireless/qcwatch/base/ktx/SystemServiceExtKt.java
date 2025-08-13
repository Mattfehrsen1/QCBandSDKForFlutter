package com.qcwireless.qcwatch.base.ktx;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.UiModeManager;
import android.app.job.JobScheduler;
import android.content.ClipboardManager;
import android.content.Context;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.PowerManager;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SystemServiceExt.kt */
@Metadata(d1 = {"\u0000Ö\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001c\u0010i\u001a\u0004\u0018\u0001Hj\"\u0006\b\u0000\u0010j\u0018\u0001*\u00020\u0002H\u0086\b¢\u0006\u0002\u0010k\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u0017\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u0017\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\"\u0017\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\"\u0017\u0010\u0015\u001a\u0004\u0018\u00010\u0016*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018\"\u0017\u0010\u0019\u001a\u0004\u0018\u00010\u001a*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\"\u0017\u0010\u001d\u001a\u0004\u0018\u00010\u001e*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 \"\u0017\u0010!\u001a\u0004\u0018\u00010\"*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b#\u0010$\"\u0017\u0010%\u001a\u0004\u0018\u00010&*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b'\u0010(\"\u0017\u0010)\u001a\u0004\u0018\u00010**\u00020\u00028F¢\u0006\u0006\u001a\u0004\b+\u0010,\"\u0017\u0010-\u001a\u0004\u0018\u00010.*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b/\u00100\"\u0017\u00101\u001a\u0004\u0018\u000102*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b3\u00104\"\u0017\u00105\u001a\u0004\u0018\u000106*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b7\u00108\"\u0017\u00109\u001a\u0004\u0018\u00010:*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b;\u0010<\"\u0017\u0010=\u001a\u0004\u0018\u00010>*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b?\u0010@\"\u0017\u0010A\u001a\u0004\u0018\u00010B*\u00020\u00028F¢\u0006\u0006\u001a\u0004\bC\u0010D\"\u0017\u0010E\u001a\u0004\u0018\u00010F*\u00020\u00028F¢\u0006\u0006\u001a\u0004\bG\u0010H\"\u0017\u0010I\u001a\u0004\u0018\u00010J*\u00020\u00028F¢\u0006\u0006\u001a\u0004\bK\u0010L\"\u0017\u0010M\u001a\u0004\u0018\u00010N*\u00020\u00028F¢\u0006\u0006\u001a\u0004\bO\u0010P\"\u0017\u0010Q\u001a\u0004\u0018\u00010R*\u00020\u00028F¢\u0006\u0006\u001a\u0004\bS\u0010T\"\u0017\u0010U\u001a\u0004\u0018\u00010V*\u00020\u00028F¢\u0006\u0006\u001a\u0004\bW\u0010X\"\u0017\u0010Y\u001a\u0004\u0018\u00010Z*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b[\u0010\\\"\u0017\u0010]\u001a\u0004\u0018\u00010^*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b_\u0010`\"\u0017\u0010a\u001a\u0004\u0018\u00010b*\u00020\u00028F¢\u0006\u0006\u001a\u0004\bc\u0010d\"\u0017\u0010e\u001a\u0004\u0018\u00010f*\u00020\u00028F¢\u0006\u0006\u001a\u0004\bg\u0010h¨\u0006l"}, d2 = {"accessibilityManager", "Landroid/view/accessibility/AccessibilityManager;", "Landroid/content/Context;", "getAccessibilityManager", "(Landroid/content/Context;)Landroid/view/accessibility/AccessibilityManager;", "activityManager", "Landroid/app/ActivityManager;", "getActivityManager", "(Landroid/content/Context;)Landroid/app/ActivityManager;", "alarmManager", "Landroid/app/AlarmManager;", "getAlarmManager", "(Landroid/content/Context;)Landroid/app/AlarmManager;", "audioManager", "Landroid/media/AudioManager;", "getAudioManager", "(Landroid/content/Context;)Landroid/media/AudioManager;", "batteryManager", "Landroid/os/BatteryManager;", "getBatteryManager", "(Landroid/content/Context;)Landroid/os/BatteryManager;", "carrierConfigManager", "Landroid/telephony/CarrierConfigManager;", "getCarrierConfigManager", "(Landroid/content/Context;)Landroid/telephony/CarrierConfigManager;", "clipboardManager", "Landroid/content/ClipboardManager;", "getClipboardManager", "(Landroid/content/Context;)Landroid/content/ClipboardManager;", "connectivityManager", "Landroid/net/ConnectivityManager;", "getConnectivityManager", "(Landroid/content/Context;)Landroid/net/ConnectivityManager;", "downloadManager", "Landroid/app/DownloadManager;", "getDownloadManager", "(Landroid/content/Context;)Landroid/app/DownloadManager;", "inputMethodManager", "Landroid/view/inputmethod/InputMethodManager;", "getInputMethodManager", "(Landroid/content/Context;)Landroid/view/inputmethod/InputMethodManager;", "jobScheduler", "Landroid/app/job/JobScheduler;", "getJobScheduler", "(Landroid/content/Context;)Landroid/app/job/JobScheduler;", "keyguardManager", "Landroid/app/KeyguardManager;", "getKeyguardManager", "(Landroid/content/Context;)Landroid/app/KeyguardManager;", "layoutInflater", "Landroid/view/LayoutInflater;", "getLayoutInflater", "(Landroid/content/Context;)Landroid/view/LayoutInflater;", "locationManager", "Landroid/location/LocationManager;", "getLocationManager", "(Landroid/content/Context;)Landroid/location/LocationManager;", "mediaRouter", "Landroid/media/MediaRouter;", "getMediaRouter", "(Landroid/content/Context;)Landroid/media/MediaRouter;", "notificationManager", "Landroid/app/NotificationManager;", "getNotificationManager", "(Landroid/content/Context;)Landroid/app/NotificationManager;", "powerManager", "Landroid/os/PowerManager;", "getPowerManager", "(Landroid/content/Context;)Landroid/os/PowerManager;", "searchManager", "Landroid/app/SearchManager;", "getSearchManager", "(Landroid/content/Context;)Landroid/app/SearchManager;", "sensorManager", "Landroid/hardware/SensorManager;", "getSensorManager", "(Landroid/content/Context;)Landroid/hardware/SensorManager;", "storageManager", "Landroid/os/storage/StorageManager;", "getStorageManager", "(Landroid/content/Context;)Landroid/os/storage/StorageManager;", "subscriptionManager", "Landroid/telephony/SubscriptionManager;", "getSubscriptionManager", "(Landroid/content/Context;)Landroid/telephony/SubscriptionManager;", "telephonyManager", "Landroid/telephony/TelephonyManager;", "getTelephonyManager", "(Landroid/content/Context;)Landroid/telephony/TelephonyManager;", "uiModeManager", "Landroid/app/UiModeManager;", "getUiModeManager", "(Landroid/content/Context;)Landroid/app/UiModeManager;", "vibrator", "Landroid/os/Vibrator;", "getVibrator", "(Landroid/content/Context;)Landroid/os/Vibrator;", "wifiManager", "Landroid/net/wifi/WifiManager;", "getWifiManager", "(Landroid/content/Context;)Landroid/net/wifi/WifiManager;", "windowManager", "Landroid/view/WindowManager;", "getWindowManager", "(Landroid/content/Context;)Landroid/view/WindowManager;", "getSystemService", ExifInterface.GPS_DIRECTION_TRUE, "(Landroid/content/Context;)Ljava/lang/Object;", "app_qwatch_proRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SystemServiceExtKt {
    public static final /* synthetic */ <T> T getSystemService(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) ContextCompat.getSystemService(context, Object.class);
    }

    public static final WindowManager getWindowManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (WindowManager) ContextCompat.getSystemService(context, WindowManager.class);
    }

    public static final ClipboardManager getClipboardManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (ClipboardManager) ContextCompat.getSystemService(context, ClipboardManager.class);
    }

    public static final LayoutInflater getLayoutInflater(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (LayoutInflater) ContextCompat.getSystemService(context, LayoutInflater.class);
    }

    public static final ActivityManager getActivityManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (ActivityManager) ContextCompat.getSystemService(context, ActivityManager.class);
    }

    public static final PowerManager getPowerManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (PowerManager) ContextCompat.getSystemService(context, PowerManager.class);
    }

    public static final AlarmManager getAlarmManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (AlarmManager) ContextCompat.getSystemService(context, AlarmManager.class);
    }

    public static final NotificationManager getNotificationManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (NotificationManager) ContextCompat.getSystemService(context, NotificationManager.class);
    }

    public static final KeyguardManager getKeyguardManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (KeyguardManager) ContextCompat.getSystemService(context, KeyguardManager.class);
    }

    public static final LocationManager getLocationManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (LocationManager) ContextCompat.getSystemService(context, LocationManager.class);
    }

    public static final SearchManager getSearchManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (SearchManager) ContextCompat.getSystemService(context, SearchManager.class);
    }

    public static final StorageManager getStorageManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (StorageManager) ContextCompat.getSystemService(context, StorageManager.class);
    }

    public static final Vibrator getVibrator(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (Vibrator) ContextCompat.getSystemService(context, Vibrator.class);
    }

    public static final ConnectivityManager getConnectivityManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (ConnectivityManager) ContextCompat.getSystemService(context, ConnectivityManager.class);
    }

    public static final WifiManager getWifiManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (WifiManager) ContextCompat.getSystemService(context, WifiManager.class);
    }

    public static final AudioManager getAudioManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (AudioManager) ContextCompat.getSystemService(context, AudioManager.class);
    }

    public static final MediaRouter getMediaRouter(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (MediaRouter) ContextCompat.getSystemService(context, MediaRouter.class);
    }

    public static final TelephonyManager getTelephonyManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (TelephonyManager) ContextCompat.getSystemService(context, TelephonyManager.class);
    }

    public static final SensorManager getSensorManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (SensorManager) ContextCompat.getSystemService(context, SensorManager.class);
    }

    public static final SubscriptionManager getSubscriptionManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (SubscriptionManager) ContextCompat.getSystemService(context, SubscriptionManager.class);
    }

    public static final CarrierConfigManager getCarrierConfigManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (CarrierConfigManager) ContextCompat.getSystemService(context, CarrierConfigManager.class);
    }

    public static final InputMethodManager getInputMethodManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (InputMethodManager) ContextCompat.getSystemService(context, InputMethodManager.class);
    }

    public static final UiModeManager getUiModeManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (UiModeManager) ContextCompat.getSystemService(context, UiModeManager.class);
    }

    public static final DownloadManager getDownloadManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (DownloadManager) ContextCompat.getSystemService(context, DownloadManager.class);
    }

    public static final BatteryManager getBatteryManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (BatteryManager) ContextCompat.getSystemService(context, BatteryManager.class);
    }

    public static final JobScheduler getJobScheduler(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (JobScheduler) ContextCompat.getSystemService(context, JobScheduler.class);
    }

    public static final AccessibilityManager getAccessibilityManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (AccessibilityManager) ContextCompat.getSystemService(context, AccessibilityManager.class);
    }
}
