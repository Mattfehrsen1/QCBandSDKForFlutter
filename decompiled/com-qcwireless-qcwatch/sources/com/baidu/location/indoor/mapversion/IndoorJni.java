package com.baidu.location.indoor.mapversion;

import android.util.Log;

/* loaded from: classes.dex */
public class IndoorJni {
    public static boolean a = false;
    private static StringBuffer b;
    private static StringBuffer c;
    private static StringBuffer d;

    static {
        try {
            System.loadLibrary("indoor");
            a = true;
            System.err.println("load vdr indoor lib success.");
            Log.i("indoorJNI", "indoor lib loadJniSuccess:" + a);
        } catch (Throwable th) {
            Log.i("indoorJNI", "indoor lib annot load indoor lib:" + th.toString());
            System.err.println("Cannot load indoor lib");
            th.printStackTrace();
            a = false;
        }
    }

    public static native String decrypt(int i, int i2, byte[] bArr, byte[] bArr2);

    public static native int destroyGpsChecker();

    public static native String getBuildingId(long j);

    public static native String getFloor(long j, int i);

    public static native int getGpsStatus(int i, String str);

    public static native int getInout(long j, int i);

    public static native int initGpsChecker(int i, String str, String str2);

    public static native void initPf();

    public static native float pgo();

    public static native String phs(int i, float f, float f2, float f3, long j);

    public static native void resetPf();

    public static native void setBarometers(float f, long j);

    public static native void setBleLoc4Scenario(double d2, double d3, String str, int i, long j, int i2);

    public static native void setGps(double d2, double d3, float f, float f2, float f3, double d4, int i, long j);

    public static native double[] setPfBle(double d2, double d3, double d4, double d5, String str, String str2, long j, int i, String str3);

    public static native double[] setPfDr(double d2, double d3, String str, long j);

    public static native void setPfGeoMap(double[][] dArr, String str, int i, int i2);

    public static native void setPfRdnt(String str, short[][] sArr, double d2, double d3, int i, int i2, double d4, double d5, String str2);

    public static native double[] setPfWf(double d2, double d3, double d4, long j);

    public static native void startPdr();

    public static native void stopPdr();
}
