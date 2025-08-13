package com.baidu.location.b;

import android.os.HandlerThread;

/* loaded from: classes.dex */
public class ak {
    private static HandlerThread a;

    public static synchronized HandlerThread a() {
        if (a == null) {
            try {
                HandlerThread handlerThread = new HandlerThread("ServiceStartArguments", 10);
                a = handlerThread;
                handlerThread.start();
            } catch (Throwable th) {
                th.printStackTrace();
                a = null;
            }
        }
        return a;
    }
}
