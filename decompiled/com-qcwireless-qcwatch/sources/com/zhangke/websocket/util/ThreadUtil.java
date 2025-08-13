package com.zhangke.websocket.util;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes4.dex */
public class ThreadUtil {
    private static Handler sMainHandler;

    public static boolean checkMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static void runOnMainThread(Runnable runnable) {
        checkMainHandlerIsNull();
        sMainHandler.post(runnable);
    }

    private static void checkMainHandlerIsNull() {
        if (sMainHandler == null) {
            sMainHandler = new Handler(Looper.getMainLooper());
        }
    }
}
