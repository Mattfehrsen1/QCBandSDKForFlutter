package com.app.watch.keeping.ext;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.PowerManager;
import android.os.Process;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ManagerExt.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0012\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f\u001a\u0012\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\f\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0018\u0010\u0004\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0003\"\u0018\u0010\u0005\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0003\"\u0018\u0010\u0006\u001a\u00020\u0007*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"isForeground", "", "Landroid/content/Context;", "(Landroid/content/Context;)Z", "isMain", "isScreenOn", "mainPid", "", "getMainPid", "(Landroid/content/Context;)I", "isRunningTaskExist", "processName", "", "isServiceRunning", "className", "keeping_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ManagerExtKt {
    public static final boolean isScreenOn(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            Object systemService = context.getApplicationContext().getSystemService("power");
            if (systemService == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.os.PowerManager");
            }
            return ((PowerManager) systemService).isScreenOn();
        } catch (Exception unused) {
            return false;
        }
    }

    public static final int getMainPid(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object systemService = context.getSystemService("activity");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        int iMyPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) systemService).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            Intrinsics.checkNotNullExpressionValue(runningAppProcesses, "runningAppProcesses");
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Intrinsics.areEqual(runningAppProcessInfo.processName, context.getPackageName())) {
                    iMyPid = runningAppProcessInfo.pid;
                }
            }
        }
        return iMyPid;
    }

    public static final boolean isMain(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object systemService = context.getSystemService("activity");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) systemService).getRunningAppProcesses();
        Intrinsics.checkNotNullExpressionValue(runningAppProcesses, "activityManager.runningAppProcesses");
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == Process.myPid() && Intrinsics.areEqual(runningAppProcessInfo.processName, context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isForeground(Context context) throws SecurityException {
        ComponentName componentName;
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object systemService = context.getSystemService("activity");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) systemService).getRunningTasks(1);
        if (runningTasks == null) {
            return false;
        }
        Intrinsics.checkNotNullExpressionValue(runningTasks, "getRunningTasks(1)");
        if (!(true ^ runningTasks.isEmpty()) || (componentName = runningTasks.get(0).topActivity) == null) {
            return false;
        }
        return Intrinsics.areEqual(componentName.getPackageName(), context.getPackageName());
    }

    public static final boolean isServiceRunning(Context context, String className) throws SecurityException {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(className, "className");
        Object systemService = context.getSystemService("activity");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE);
        if (runningServices == null) {
            return false;
        }
        Iterator<ActivityManager.RunningServiceInfo> it = runningServices.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(className, it.next().service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isRunningTaskExist(Context context, String processName) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(processName, "processName");
        Object systemService = context.getSystemService("activity");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) systemService).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        Iterator<T> it = runningAppProcesses.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(((ActivityManager.RunningAppProcessInfo) it.next()).processName, context.getPackageName() + ':' + processName)) {
                return true;
            }
        }
        return false;
    }
}
