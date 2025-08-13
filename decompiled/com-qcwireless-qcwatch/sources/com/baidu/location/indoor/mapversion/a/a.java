package com.baidu.location.indoor.mapversion.a;

import android.os.Build;
import com.baidu.location.BDLocation;
import com.baidu.location.indoor.f;
import com.baidu.location.indoor.m;
import com.baidu.location.indoor.mapversion.IndoorJni;
import com.baidu.location.indoor.mapversion.b.a;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes.dex */
public class a {
    private static final Lock b = new ReentrantLock();
    public static boolean a = false;

    public static boolean a() {
        return IndoorJni.a && Build.VERSION.SDK_INT > 19;
    }

    public static synchronized boolean a(String str) {
        if (!a()) {
            return false;
        }
        a.d dVarB = com.baidu.location.indoor.mapversion.b.a.a().b(str);
        double[][] dArrC = com.baidu.location.indoor.mapversion.b.a.a().c(str);
        if (dVarB == null) {
            return false;
        }
        dVarB.a("gcj02");
        short[][] sArr = dVarB.h;
        double d = dVarB.a().a;
        double d2 = dVarB.a().b;
        a.d dVarC = com.baidu.location.indoor.mapversion.b.a.a().c();
        if (dVarC == null) {
            return false;
        }
        double dA = dVarC.a(-dVarB.a().d);
        double dB = dVarC.b(-dVarB.a().f);
        Lock lock = b;
        lock.lock();
        try {
            IndoorJni.setPfRdnt(str, sArr, d, d2, (int) dVarB.g.g, (int) dVarB.g.h, dA, dB, dVarB.c);
            IndoorJni.setPfGeoMap(dArrC, str, (int) dVarB.g.g, (int) dVarB.g.h);
            lock.unlock();
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                b.unlock();
            } catch (Throwable th2) {
                b.unlock();
                throw th2;
            }
        }
        return true;
    }

    private static double[] a(double d, double d2, double d3, double d4, String str, String str2, long j, int i, String str3) {
        String str4 = str;
        double[] pfBle = {-1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d};
        if (a()) {
            if (str4 == null || "".equals(str4)) {
                str4 = "unknow";
            }
            String str5 = str4;
            Lock lock = b;
            lock.lock();
            try {
                pfBle = IndoorJni.setPfBle(d, d2, d3, d4, str5, str2, j, i, str3);
            } catch (Throwable th) {
                try {
                    th.printStackTrace();
                    lock = b;
                } catch (Throwable th2) {
                    b.unlock();
                    throw th2;
                }
            }
            lock.unlock();
        }
        return pfBle;
    }

    public static synchronized double[] a(String str, double d, double d2, double d3, String str2) {
        if (!a()) {
            return null;
        }
        a.d dVarC = com.baidu.location.indoor.mapversion.b.a.a().c();
        double[] pfDr = {-1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d};
        if (dVarC != null) {
            Lock lock = b;
            lock.lock();
            try {
                pfDr = IndoorJni.setPfDr(d2, d3, str2, System.currentTimeMillis());
            } catch (Throwable th) {
                try {
                    th.printStackTrace();
                    lock = b;
                } catch (Throwable th2) {
                    b.unlock();
                    throw th2;
                }
            }
            lock.unlock();
            if (pfDr[0] == 0.0d) {
                double dC = dVarC.c(pfDr[1]);
                double d4 = dVarC.d(pfDr[2]);
                pfDr[1] = dC;
                pfDr[2] = d4;
            }
        }
        return pfDr;
    }

    public static synchronized double[] a(boolean z, BDLocation bDLocation) {
        if (!a()) {
            return null;
        }
        a.d dVarC = com.baidu.location.indoor.mapversion.b.a.a().c();
        double[] pfWf = {-1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d};
        if (dVarC != null) {
            double dA = dVarC.a(bDLocation.getLongitude());
            double dB = dVarC.b(bDLocation.getLatitude());
            if (z) {
                String strB = m.b(2);
                if (strB != null || !"".equals(strB)) {
                    strB = strB.split("_")[0];
                }
                if (strB == null || "".equals(strB)) {
                    strB = "unknow";
                }
                String str = strB;
                String strE = f.a().e();
                if (strE == null || "".equals(strE)) {
                    strE = "unknow";
                }
                String str2 = strE;
                str.toUpperCase();
                int iA = m.a(2);
                String buildingName = bDLocation.getBuildingName();
                if (buildingName == null || "".equals(buildingName)) {
                    buildingName = "unknown";
                }
                pfWf = a(dA, dB, bDLocation.getRadius(), bDLocation.getAcc(), str, str2, System.currentTimeMillis(), iA, buildingName);
            } else {
                Lock lock = b;
                lock.lock();
                try {
                    pfWf = IndoorJni.setPfWf(dA, dB, 8.0d, System.currentTimeMillis());
                } catch (Throwable th) {
                    try {
                        th.printStackTrace();
                        lock = b;
                    } catch (Throwable th2) {
                        b.unlock();
                        throw th2;
                    }
                }
                lock.unlock();
            }
            if (pfWf[0] == 0.0d) {
                double dC = dVarC.c(pfWf[1]);
                double d = dVarC.d(pfWf[2]);
                pfWf[1] = dC;
                pfWf[2] = d;
            }
        }
        return pfWf;
    }

    public static void b() {
        if (a()) {
            Lock lock = b;
            lock.lock();
            try {
                IndoorJni.initPf();
            } catch (Throwable th) {
                try {
                    th.printStackTrace();
                    lock = b;
                } catch (Throwable th2) {
                    b.unlock();
                    throw th2;
                }
            }
            lock.unlock();
        }
    }

    public static void c() {
        if (a()) {
            Lock lock = b;
            lock.lock();
            try {
                IndoorJni.resetPf();
            } catch (Throwable th) {
                try {
                    th.printStackTrace();
                    lock = b;
                } catch (Throwable th2) {
                    b.unlock();
                    throw th2;
                }
            }
            lock.unlock();
        }
    }
}
