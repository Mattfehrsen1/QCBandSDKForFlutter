package com.qcwireless.qc_utils.app;

import android.app.ActivityManager;
import android.content.Context;
import java.util.List;

/* loaded from: classes3.dex */
public class AppUtil {
    public static boolean isBackground(Context context) {
        try {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(context.getPackageName())) {
                    return runningAppProcessInfo.importance != 100;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isApplicationBroughtToBackground(Context context) throws SecurityException {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        return (runningTasks.isEmpty() || runningTasks.get(0).topActivity.getPackageName().equals(context.getPackageName())) ? false : true;
    }
}
