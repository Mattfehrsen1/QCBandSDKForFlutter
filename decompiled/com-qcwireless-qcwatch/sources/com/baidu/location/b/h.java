package com.baidu.location.b;

import android.os.Build;
import com.baidu.location.indoor.mapversion.IndoorJni;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes.dex */
public class h {
    private static final Lock a = new ReentrantLock();

    public static int a(String str) {
        if (str != null && !"".equals(str) && a()) {
            Lock lock = a;
            lock.lock();
            try {
                int gpsStatus = IndoorJni.getGpsStatus(0, str);
                lock.unlock();
                return gpsStatus;
            } catch (Throwable unused) {
                a.unlock();
            }
        }
        return -1;
    }

    public static int a(String str, String str2) {
        if (str == null || "".equals(str)) {
            return -100;
        }
        if (str2 == null || "".equals(str2)) {
            str2 = "default";
        }
        if (!a()) {
            return -101;
        }
        Lock lock = a;
        lock.lock();
        try {
            int iInitGpsChecker = IndoorJni.initGpsChecker(0, str, str2);
            lock.unlock();
            return iInitGpsChecker;
        } catch (Throwable unused) {
            a.unlock();
            return -101;
        }
    }

    public static boolean a() {
        if (Build.VERSION.SDK_INT < 19) {
            return false;
        }
        return IndoorJni.a;
    }

    public static int b() {
        if (!a()) {
            return 0;
        }
        Lock lock = a;
        lock.lock();
        try {
            int iDestroyGpsChecker = IndoorJni.destroyGpsChecker();
            lock.unlock();
            return iDestroyGpsChecker;
        } catch (Throwable unused) {
            a.unlock();
            return 0;
        }
    }
}
