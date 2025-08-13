package com.realsil.customer.bbpro.equalizer;

import com.realsil.customer.audioconnect.localplayback.LocalPlaybackTransferEngine;
import com.realsil.customer.bbpro.equalizer.EqConstants;
import com.realsil.customer.core.logger.ZLogger;
import java.util.Arrays;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/equalizer/EqParameterInfo.class */
public class EqParameterInfo {
    public static final double a = Math.pow(2.0d, 10.0d);
    public double globalGain;
    public int samplingFreqIndex;
    public int stageNum;
    public int accuracy;
    public double[] freq;
    public double[] q;
    public double[] gains;
    public int[] biquadType;

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect negative register number in instruction: 0x004c: IPUT (r0 I:int[]), (r-1 I:com.realsil.customer.bbpro.equalizer.EqParameterInfo) com.realsil.customer.bbpro.equalizer.EqParameterInfo.biquadType int[]
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:76)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    public EqParameterInfo(double r24, int r26, int r27, double[] r28, double[] r29, double[] r30, int[] r31) {
        /*
            r23 = this;
            r0 = r23
            r1 = r31
            r2 = r23
            r3 = r30
            r4 = r23
            r5 = r29
            r6 = r23
            r7 = r28
            r8 = r23
            r9 = r27
            r10 = r23
            r11 = r26
            r12 = r23
            r13 = r24
            r14 = r23
            r15 = r14
            r16 = r15; r0 = r0; 
            r17 = r15; r18 = r16; 
            r18.<init>()
            r18 = 0
            r17.globalGain = r18
            r17 = 16384(0x4000, float:2.2959E-41)
            r16.accuracy = r17
            double[] r16 = com.realsil.customer.bbpro.equalizer.AudioEq.FREQ
            r15.freq = r16
            double[] r15 = com.realsil.customer.bbpro.equalizer.AudioEq.Q
            r14.q = r15
            double[] r14 = com.realsil.customer.bbpro.equalizer.AudioEq.GAIN_FLAT
            r13.gains = r14
            int r13 = com.realsil.customer.bbpro.equalizer.AudioEq.MAX_DB_VALUE
            r11.globalGain = r12
            r9.samplingFreqIndex = r10
            r7.stageNum = r8
            r5.freq = r6
            r3.q = r4
            r1.gains = r2
            r-1.biquadType = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.customer.bbpro.equalizer.EqParameterInfo.<init>(double, int, int, double[], double[], double[], int[]):void");
    }

    public static EqParameterInfo parse(byte[] bArr) {
        if (bArr == null || bArr.length < 4) {
            ZLogger.v("invalid packet");
            return null;
        }
        byte b = bArr[0];
        int i = b & 15;
        int i2 = (b >> 4) & 15;
        double dA = a(((bArr[3] << 8) | (bArr[2] & 255)) & LocalPlaybackTransferEngine.MAX_SEQUENCE_NUMBER, a);
        if (i > 10) {
            ZLogger.v(String.format(Locale.US, "invalid stageNum(%d)", Integer.valueOf(i)));
            return null;
        }
        int length = bArr.length - 4;
        if (i * 8 > length) {
            ZLogger.v(String.format(Locale.US, "invalid paramLen(%d),stageNum=%d", Integer.valueOf(length), Integer.valueOf(i)));
            return null;
        }
        ZLogger.v(String.format(Locale.US, "parse:stageNum=%d, samplingFreqIndex=%d, globalGain=%f", Integer.valueOf(i), Integer.valueOf(i2), Double.valueOf(dA)));
        double[] dArrCopyOf = Arrays.copyOf(AudioEq.FREQ, 10);
        double[] dArrCopyOf2 = Arrays.copyOf(AudioEq.Q, 10);
        double[] dArrCopyOf3 = Arrays.copyOf(AudioEq.GAIN_FLAT, 10);
        int[] iArrCopyOf = Arrays.copyOf(AudioEq.BIQUAD_TYPE, 10);
        int i3 = 4;
        for (int i4 = 0; i4 < i; i4++) {
            iArrCopyOf[i4] = bArr[i3] & 15;
            dArrCopyOf3[i4] = a(((bArr[i3 + 3] << 8) | (bArr[i3 + 2] & 255)) & LocalPlaybackTransferEngine.MAX_SEQUENCE_NUMBER, a);
            dArrCopyOf[i4] = (bArr[i3 + 5] << 8) | (bArr[i3 + 4] & 255);
            dArrCopyOf2[i4] = ((bArr[i3 + 7] << 8) | (bArr[i3 + 6] & 255)) / 100.0f;
            i3 += 8;
        }
        return new EqParameterInfo(dA, i2, i, dArrCopyOf, dArrCopyOf2, dArrCopyOf3, iArrCopyOf);
    }

    public static EqParameterInfo parseV11(byte[] bArr) {
        if (bArr == null || bArr.length < 4) {
            ZLogger.v("invalid packet");
            return null;
        }
        byte b = bArr[0];
        int i = b & 15;
        int i2 = (b >> 4) & 15;
        double dA = a(((bArr[3] << 8) | (bArr[2] & 255)) & LocalPlaybackTransferEngine.MAX_SEQUENCE_NUMBER, 100.0d);
        if (i > 10) {
            ZLogger.v(String.format(Locale.US, "invalid stageNum(%d)", Integer.valueOf(i)));
            return null;
        }
        int length = bArr.length - 4;
        if (i * 5 > length) {
            ZLogger.v(String.format(Locale.US, "invalid paramLen(%d),stageNum=%d", Integer.valueOf(length), Integer.valueOf(i)));
            return null;
        }
        double[] dArrCopyOf = Arrays.copyOf(AudioEq.FREQ, 10);
        double[] dArrCopyOf2 = Arrays.copyOf(AudioEq.Q, 10);
        double[] dArrCopyOf3 = Arrays.copyOf(AudioEq.GAIN_FLAT, 10);
        int[] iArrCopyOf = Arrays.copyOf(AudioEq.BIQUAD_TYPE, 10);
        int i3 = 4;
        for (int i4 = 0; i4 < i; i4++) {
            iArrCopyOf[i4] = bArr[i3] & 7;
            byte b2 = bArr[i3 + 1];
            dArrCopyOf2[i4] = (((r3 >> 3) & 31) | ((b2 << 5) & 992)) / 100.0f;
            dArrCopyOf3[i4] = b((((((b2 & 224) >> 5) & 255) | (bArr[i3 + 2] << 3)) & 2047) | (((bArr[i3 + 3] & 1) == 0 ? 0 : 255) << 11), 100.0d);
            dArrCopyOf[i4] = ((bArr[r1] >> 1) & 127) | ((bArr[i3 + 4] << 7) & 32640);
            i3 += 5;
        }
        return new EqParameterInfo(dA, i2, i, dArrCopyOf, dArrCopyOf2, dArrCopyOf3, iArrCopyOf);
    }

    public static double a(int i, double d) {
        return (i & 32768) == 0 ? i / d : 0.0d - ((((i ^ (-1)) + 1) & LocalPlaybackTransferEngine.MAX_SEQUENCE_NUMBER) / d);
    }

    public static double b(int i, double d) {
        return (i & EqConstants.CodeIndex.GAMING_MODE_EQ) == 0 ? i / d : 0.0d - ((((i ^ (-1)) + 1) & LocalPlaybackTransferEngine.MAX_SEQUENCE_NUMBER) / d);
    }

    public byte[] encode(int i) {
        if (i == 256) {
            return a();
        }
        if (i == 257 || i >= 258) {
            return b();
        }
        return null;
    }

    public AudioEq toAudioEq() {
        return new AudioEq(this.stageNum, this.globalGain, this.samplingFreqIndex, this.accuracy, this.freq, this.q, this.gains, this.biquadType, null);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EqParameterInfo {");
        sb.append(String.format(Locale.US, "\nStageNum=%d, globalGain=%f, sampleRate=%d", Integer.valueOf(this.stageNum), Double.valueOf(this.globalGain), Integer.valueOf(this.samplingFreqIndex)));
        sb.append("\nFreq=" + Arrays.toString(this.freq));
        sb.append("\nGain=" + Arrays.toString(this.gains));
        sb.append("\nQ=" + Arrays.toString(this.q));
        sb.append("\nbiquadType=" + Arrays.toString(this.biquadType));
        sb.append("\n}");
        return sb.toString();
    }

    public final byte[] a() {
        int i = this.stageNum;
        byte[] bArr = new byte[(i * 8) + 4];
        bArr[0] = (byte) ((i & 15) | ((this.samplingFreqIndex & 15) << 4));
        int i2 = (int) (this.globalGain * a);
        bArr[2] = (byte) (i2 & 255);
        bArr[3] = (byte) ((i2 >> 8) & 255);
        int i3 = 4;
        for (int i4 = 0; i4 < this.stageNum; i4++) {
            bArr[i3] = (byte) (this.biquadType[i4] & 15);
            int i5 = (int) (this.gains[i4] * a);
            bArr[i3 + 2] = (byte) (i5 & 255);
            bArr[i3 + 3] = (byte) ((i5 >> 8) & 255);
            int i6 = (int) this.freq[i4];
            bArr[i3 + 4] = (byte) (i6 & 255);
            bArr[i3 + 5] = (byte) ((i6 >> 8) & 255);
            int i7 = (int) (this.q[i4] * 100.0d);
            bArr[i3 + 6] = (byte) (i7 & 255);
            bArr[i3 + 7] = (byte) ((i7 >> 8) & 255);
            i3 += 8;
        }
        return bArr;
    }

    public final byte[] b() {
        int i = this.stageNum;
        byte[] bArr = new byte[(i * 5) + 4];
        bArr[0] = (byte) ((i & 15) | ((this.samplingFreqIndex & 15) << 4));
        int i2 = (int) (this.globalGain * 100.0d);
        bArr[2] = (byte) (i2 & 255);
        bArr[3] = (byte) ((i2 >> 8) & 255);
        int i3 = 4;
        for (int i4 = 0; i4 < this.stageNum; i4++) {
            int i5 = (int) (this.q[i4] * 100.0d);
            int i6 = (int) (this.gains[i4] * 100.0d);
            int i7 = i6;
            int i8 = (int) this.freq[i4];
            if (i6 < 0) {
                i7 = ((-i7) ^ (-1)) & LocalPlaybackTransferEngine.MAX_SEQUENCE_NUMBER;
            }
            int i9 = (this.biquadType[i4] & 7) | (i5 << 3) | ((i7 & 4095) << 13) | ((i8 & 127) << 25);
            bArr[i3] = (byte) (i9 & 255);
            bArr[i3 + 1] = (byte) ((i9 >> 8) & 255);
            bArr[i3 + 2] = (byte) ((i9 >> 16) & 255);
            bArr[i3 + 3] = (byte) ((i9 >> 24) & 255);
            bArr[i3 + 4] = (byte) ((i8 >> 7) & 255);
            i3 += 5;
        }
        return bArr;
    }

    public static EqParameterInfo parse(AudioEq audioEq) {
        return new EqParameterInfo(audioEq.getGlobalGain(), audioEq.getSampleRate(), audioEq.getStageNum(), audioEq.getFreq(), audioEq.getQ(), audioEq.getGains(), audioEq.getBiquadType());
    }
}
