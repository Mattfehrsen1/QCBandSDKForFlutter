package com.qcwireless.qcwatch.ui.home.bp.util;

/* loaded from: classes3.dex */
public class CalcBloodPressureByHeart {
    public static int AGE_DEFAULT = 25;
    public static float HR_BP_RATE = 0.45f;
    public static int HR_DEFAULT_VALUE = 80;
    public static int HR_LOWER = 65;
    public static int HR_UPPER = 85;
    public static int MAX_BP_DIFF = 43;
    public static int MAX_SBP = 120;
    public static int MIN_BP_DIFF = 37;
    public static int MIN_SBP = 100;
    public static int[] AGE = {20, 30, 40, 50, 60};
    public static int[] AGE_BP_COF = {-10, 5, 15, 20, 25, 30};
    public static int g_reserve_sbp = 0;
    public static int g_last_sbp = 0;
    public static int g_last_hr = 0;
    public static int g_reserve_age = -1;
    public static int last_sbp = 0;
    public static int last_dbp = 0;

    public static int cal_sbp(int hr, int age) {
        float f;
        int i = g_reserve_sbp;
        if (i > 0 && age == g_reserve_age) {
            int iRandom = g_last_sbp;
            if (hr > g_last_hr) {
                int i2 = (int) (iRandom + ((hr - r1) * HR_BP_RATE));
                g_last_sbp = ((i + i2) + iRandom) / 3;
                iRandom = i2 - ((int) (Math.random() * 4.0d));
            }
            g_last_hr = hr;
            return iRandom;
        }
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int[] iArr = AGE;
            if (i4 >= iArr.length) {
                break;
            }
            if (age < iArr[i4]) {
                i3 = i4;
                break;
            }
            i4++;
        }
        int iRandom2 = MIN_SBP + ((int) (Math.random() * ((MAX_SBP - MIN_SBP) + 1))) + AGE_BP_COF[i3];
        g_reserve_sbp = iRandom2;
        if (hr < HR_LOWER) {
            f = iRandom2 - ((r0 - hr) * HR_BP_RATE);
        } else {
            f = iRandom2 + ((hr - r0) * HR_BP_RATE);
        }
        int i5 = (iRandom2 + ((int) f)) / 2;
        g_last_sbp = i5;
        g_last_hr = hr;
        g_reserve_age = age;
        return i5;
    }

    public static int cal_dbp(int sbp) {
        return (sbp - MIN_BP_DIFF) + ((int) (Math.random() * (MAX_BP_DIFF - MIN_BP_DIFF)));
    }
}
