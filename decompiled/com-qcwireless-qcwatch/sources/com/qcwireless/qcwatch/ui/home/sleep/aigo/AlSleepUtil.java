package com.qcwireless.qcwatch.ui.home.sleep.aigo;

/* loaded from: classes3.dex */
public class AlSleepUtil {
    public static float calScore(float x, float y, float z) {
        return z * (1.0f - ((x > y ? x - y : y - x) / y));
    }

    public static int calcSleepScore(int totalTime, int deepSleep, int lightSleep, int awake) {
        float[] fArr = {0.2f, 0.5f, 100.0f, 250.0f, 500.0f};
        double d = 100.0f;
        float f = (float) (0.025d * d);
        float f2 = (float) (0.05d * d);
        float[] fArr2 = {f, f, f2, f2, (float) (0.7d * d), (float) (d * 0.15d)};
        float[] fArr3 = new float[5];
        fArr3[4] = totalTime / 60;
        fArr3[2] = deepSleep / 60;
        fArr3[3] = lightSleep / 60;
        fArr3[0] = fArr3[2] / fArr3[4];
        fArr3[1] = fArr3[3] / fArr3[4];
        float f3 = 0.0f;
        for (int i = 0; i < 5; i++) {
            float fCalScore = calScore(fArr3[i], fArr[i], fArr2[i]);
            if (fCalScore < 0.0f) {
                fCalScore = 0.0f;
            }
            f3 += fCalScore;
        }
        float f4 = fArr2[5] - ((fArr2[5] / 4.0f) * awake);
        return Math.round(f3 + (f4 >= 0.0f ? f4 : 0.0f));
    }
}
