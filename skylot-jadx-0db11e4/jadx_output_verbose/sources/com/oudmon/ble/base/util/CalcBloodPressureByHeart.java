package com.oudmon.ble.base.util;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/util/CalcBloodPressureByHeart.class */
public class CalcBloodPressureByHeart {
    public static int MIN_BP_DIFF = 37;
    public static int MAX_BP_DIFF = 43;
    public static int MAX_SBP = 120;
    public static int MIN_SBP = 100;
    public static int HR_UPPER = 85;
    public static int HR_LOWER = 65;
    public static float HR_BP_RATE = 0.45f;
    public static int HR_DEFAULT_VALUE = 80;
    public static int AGE_DEFAULT = 25;
    public static int[] AGE = {20, 30, 40, 50, 60};
    public static int[] AGE_BP_COF = {-10, 5, 15, 20, 25, 30};
    public static int g_reserve_sbp = 0;
    public static int g_last_sbp = 0;
    public static int g_last_hr = 0;
    public static int g_reserve_age = -1;
    public static int last_sbp = 0;
    public static int last_dbp = 0;

    public static int cal_sbp(int hr, int age) {
        int sbp;
        int sbp2;
        if (g_reserve_sbp > 0 && age == g_reserve_age) {
            sbp2 = g_last_sbp;
            if (hr > g_last_hr) {
                int sbp3 = (int) (sbp2 + ((hr - g_last_hr) * HR_BP_RATE));
                g_last_sbp = ((g_reserve_sbp + sbp3) + g_last_sbp) / 3;
                sbp2 = sbp3 - ((int) (Math.random() * 4.0d));
            }
            g_last_hr = hr;
        } else {
            int index = 0;
            int i = 0;
            while (true) {
                if (i >= AGE.length) {
                    break;
                }
                if (age >= AGE[i]) {
                    i++;
                } else {
                    index = i;
                    break;
                }
            }
            int sbp4 = MIN_SBP + ((int) (Math.random() * ((MAX_SBP - MIN_SBP) + 1))) + AGE_BP_COF[index];
            g_reserve_sbp = sbp4;
            if (hr < HR_LOWER) {
                sbp = (int) (sbp4 - ((HR_LOWER - hr) * HR_BP_RATE));
            } else {
                sbp = (int) (sbp4 + ((hr - HR_LOWER) * HR_BP_RATE));
            }
            sbp2 = (g_reserve_sbp + sbp) / 2;
            g_last_sbp = sbp2;
            g_last_hr = hr;
            g_reserve_age = age;
        }
        return sbp2;
    }

    public static int cal_dbp(int sbp) {
        return (sbp - MIN_BP_DIFF) + ((int) (Math.random() * (MAX_BP_DIFF - MIN_BP_DIFF)));
    }
}
