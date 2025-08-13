package com.oudmon.ble.base.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import java.util.List;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/util/AppUtil.class */
public class AppUtil {
    public static boolean isBackground(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
                if (appProcess.processName.equals(context.getPackageName())) {
                    if (appProcess.importance != 100) {
                        return true;
                    }
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isApplicationBroughtToBackground(Context context) throws SecurityException {
        ActivityManager am = (ActivityManager) context.getSystemService("activity");
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
            return false;
        }
        return false;
    }
}
