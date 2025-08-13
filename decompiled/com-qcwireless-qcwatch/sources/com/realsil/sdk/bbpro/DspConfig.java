package com.realsil.sdk.bbpro;

import com.realsil.sdk.core.logger.ZLogger;

/* loaded from: classes3.dex */
public class DspConfig {
    static {
        try {
            System.loadLibrary("DspConfig");
        } catch (UnsatisfiedLinkError e) {
            ZLogger.w(e.toString());
            System.err.println("Cannot load libDspConfig:\n " + e.toString());
        }
    }

    public native byte[] calculateEq(double d, int i, double[] dArr, double[] dArr2, double[] dArr3, int[] iArr);

    public native byte[] calculateEq2(double d, double d2, int i, double[] dArr, double[] dArr2, double[] dArr3, int[] iArr);

    public native String getMessage();

    public native SigProcEQ_T parseEq(byte[] bArr, int i);

    public static class SigProcEQ_T {
        public static final int MAX_EQ_STAGE_NUM = 10;
        public int Accuracy;
        public int[] BiquadType;
        public double[] Freq;
        public double[] Gain;
        public double GlobalGain;
        public double[] Q;
        public int SampleFreq;
        public int StageNum;

        public SigProcEQ_T() {
            this.Freq = new double[10];
            this.Gain = new double[10];
            this.Q = new double[10];
            this.BiquadType = new int[10];
        }

        public SigProcEQ_T(int i, double d, int i2, int i3, double[] dArr, double[] dArr2, double[] dArr3, int[] iArr) {
            this.StageNum = i;
            this.GlobalGain = d;
            this.SampleFreq = i2;
            this.Accuracy = i3;
            this.Freq = dArr;
            this.Gain = dArr2;
            this.Q = dArr3;
            this.BiquadType = iArr;
        }
    }
}
