package com.baidu.location.indoor;

import android.os.Build;
import com.baidu.location.indoor.mapversion.IndoorJni;
import com.realsil.sdk.bbpro.params.Mmi;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes.dex */
public class m {
    private static Lock a = new ReentrantLock();
    private static final byte[] b = {Mmi.AU_MMI_AUDIO_EFFECT_PREVIOUS, Mmi.AU_MMI_AUDIO_PASS_THROUGH, Mmi.AU_MMI_SWITCH_NEXT_VOICE_PROMPT_LANGUAGE, Mmi.AU_MMI_OUTPUT_INDICATION_1, Mmi.AU_MMI_OUTPUT_INDICATION_2, 105, 112, Mmi.AU_MMI_RWS_LINKBACK, 114, 115, 116, Mmi.AU_MMI_RWS_BUNDLE_PAIRING, Mmi.AU_MMI_RWS_RESET_TO_DEFAULT, 119, 120, Mmi.AU_MMI_RWS_SYNC_RINGTONE};
    private static boolean c = false;

    public static int a(int i) {
        if (a()) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            a.lock();
            try {
                return IndoorJni.getInout(jCurrentTimeMillis, i);
            } catch (Throwable th) {
                try {
                    th.printStackTrace();
                } finally {
                    a.unlock();
                }
            }
        }
        return -1;
    }

    public static void a(double d, double d2, float f, float f2, float f3, double d3, int i, long j) {
        if (a()) {
            a.lock();
            try {
                IndoorJni.setGps(d, d2, f, f2, f3, d3, i, j);
            } finally {
                try {
                } finally {
                }
            }
        }
    }

    public static void a(double d, double d2, String str, int i, long j, int i2) {
        String str2 = str;
        if (a()) {
            if (str2 == null || "".equals(str)) {
                str2 = "unknow";
            }
            String str3 = str2;
            a.lock();
            try {
                IndoorJni.setBleLoc4Scenario(d, d2, str3, i, j, i2);
            } finally {
                try {
                } finally {
                }
            }
        }
    }

    public static void a(float f, long j) {
        if (a()) {
            a.lock();
            try {
                IndoorJni.setBarometers(f, j);
            } finally {
                try {
                } finally {
                }
            }
        }
    }

    public static boolean a() {
        if (Build.VERSION.SDK_INT < 19) {
            return false;
        }
        return IndoorJni.a;
    }

    public static String[] a(int i, int i2, String str) {
        String[] strArr = new String[0];
        byte[] bArrD = com.baidu.location.h.s.d(str);
        if (!a()) {
            return strArr;
        }
        try {
            return IndoorJni.decrypt(i, i2, bArrD, b).split(",");
        } catch (Throwable unused) {
            return strArr;
        }
    }

    public static String b() {
        if (a()) {
            a.lock();
            try {
                String buildingId = IndoorJni.getBuildingId(System.currentTimeMillis());
                a.unlock();
                return buildingId;
            } catch (Throwable unused) {
                a.unlock();
            }
        }
        return "";
    }

    public static String b(int i) {
        if (a()) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            a.lock();
            try {
                return IndoorJni.getFloor(jCurrentTimeMillis, i);
            } catch (Throwable th) {
                try {
                    th.printStackTrace();
                } finally {
                    a.unlock();
                }
            }
        }
        return "";
    }
}
