package com.baidu.location.indoor.mapversion;

import android.os.Build;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes.dex */
public class a {
    private static Lock a = new ReentrantLock();

    public static synchronized String a(int i, float[] fArr, long j) {
        String strPhs;
        Lock lock;
        strPhs = null;
        a.lock();
        try {
            if (c() && fArr != null && fArr.length >= 3) {
                strPhs = IndoorJni.phs(i, fArr[0], fArr[1], fArr[2], j);
            }
            lock = a;
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                lock = a;
            } catch (Throwable th2) {
                a.unlock();
                throw th2;
            }
        }
        lock.unlock();
        return strPhs;
    }

    public static void a() {
        a.lock();
        try {
            IndoorJni.startPdr();
        } finally {
            try {
            } finally {
            }
        }
    }

    public static void b() {
        a.lock();
        try {
            IndoorJni.stopPdr();
        } finally {
            try {
            } finally {
            }
        }
    }

    public static boolean c() {
        if (Build.VERSION.SDK_INT < 19) {
            return false;
        }
        return IndoorJni.a;
    }

    public static float d() {
        a.lock();
        try {
            return IndoorJni.pgo();
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                a.unlock();
                return -1.0f;
            } finally {
                a.unlock();
            }
        }
    }
}
