package com.realsil.sdk.bbpro.equalizer;

import com.realsil.sdk.core.logger.ZLogger;
import java.util.Arrays;
import java.util.Locale;

/* loaded from: classes3.dex */
public class EqParameterInfo {
    public static final double a = Math.pow(2.0d, 10.0d);
    public int accuracy = 16384;
    public int[] biquadType;
    public double[] freq;
    public double[] gains;
    public double globalGain;
    public double[] q;
    public int samplingFreqIndex;
    public int stageNum;

    public EqParameterInfo(double d, int i, int i2, double[] dArr, double[] dArr2, double[] dArr3, int[] iArr) {
        this.globalGain = 0.0d;
        this.freq = AudioEq.FREQ;
        this.q = AudioEq.Q;
        this.gains = AudioEq.GAIN_FLAT;
        int[] iArr2 = AudioEq.BIQUAD_TYPE;
        this.globalGain = d;
        this.samplingFreqIndex = i;
        this.stageNum = i2;
        this.freq = dArr;
        this.q = dArr2;
        this.gains = dArr3;
        this.biquadType = iArr;
    }

    public static double a(int i, double d) {
        return (32768 & i) == 0 ? i / d : 0.0d - (((~i) & 65535) / d);
    }

    public static double b(int i, double d) {
        return (i & 2048) == 0 ? i / d : 0.0d - (((~i) & 65535) / d);
    }

    public static EqParameterInfo parse(byte[] bArr) {
        if (bArr != null) {
            int i = 4;
            if (bArr.length >= 4) {
                int i2 = 0;
                int i3 = bArr[0] & 15;
                int i4 = (bArr[0] >> 4) & 15;
                int i5 = 65535;
                double dA = a(((bArr[3] << 8) | (bArr[2] & 255)) & 65535, a);
                if (i3 > 10) {
                    ZLogger.v(String.format(Locale.US, "invalid stageNum(%d)", Integer.valueOf(i3)));
                    return null;
                }
                int length = bArr.length - 4;
                if (i3 * 8 > length) {
                    ZLogger.v(String.format(Locale.US, "invalid paramLen(%d),stageNum=%d", Integer.valueOf(length), Integer.valueOf(i3)));
                    return null;
                }
                ZLogger.v(String.format(Locale.US, "parse:stageNum=%d, samplingFreqIndex=%d, globalGain=%f", Integer.valueOf(i3), Integer.valueOf(i4), Double.valueOf(dA)));
                double[] dArrCopyOf = Arrays.copyOf(AudioEq.FREQ, 10);
                double[] dArrCopyOf2 = Arrays.copyOf(AudioEq.Q, 10);
                double[] dArrCopyOf3 = Arrays.copyOf(AudioEq.GAIN_FLAT, 10);
                int[] iArrCopyOf = Arrays.copyOf(AudioEq.BIQUAD_TYPE, 10);
                while (i2 < i3) {
                    iArrCopyOf[i2] = bArr[i] & 15;
                    dArrCopyOf3[i2] = a(((bArr[i + 3] << 8) | (bArr[i + 2] & 255)) & i5, a);
                    dArrCopyOf[i2] = (bArr[i + 5] << 8) | (bArr[i + 4] & 255);
                    dArrCopyOf2[i2] = ((bArr[i + 7] << 8) | (bArr[i + 6] & 255)) / 100.0f;
                    i += 8;
                    i2++;
                    i4 = i4;
                    i5 = 65535;
                }
                return new EqParameterInfo(dA, i4, i3, dArrCopyOf, dArrCopyOf2, dArrCopyOf3, iArrCopyOf);
            }
        }
        ZLogger.v("invalid packet");
        return null;
    }

    public static EqParameterInfo parseV11(byte[] bArr) {
        if (bArr == null || bArr.length < 4) {
            ZLogger.v("invalid packet");
            return null;
        }
        int i = bArr[0] & 15;
        int i2 = (bArr[0] >> 4) & 15;
        int i3 = 255;
        double dA = a(((bArr[3] << 8) | (bArr[2] & 255)) & 65535, 100.0d);
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
        int i4 = 0;
        int i5 = 4;
        while (i4 < i) {
            iArrCopyOf[i4] = bArr[i5] & 7;
            int i6 = i5 + 1;
            dArrCopyOf2[i4] = (((bArr[i6] << 5) & 992) | ((bArr[i5] >> 3) & 31)) / 100.0f;
            dArrCopyOf3[i4] = b((((((bArr[i6] & 224) >> 5) & i3) | (bArr[i5 + 2] << 3)) & 2047) | (((bArr[i5 + 3] & 1) == 0 ? 0 : 255) << 11), 100.0d);
            dArrCopyOf[i4] = ((bArr[r6] >> 1) & 127) | ((bArr[i5 + 4] << 7) & 32640);
            i5 += 5;
            i4++;
            i3 = 255;
        }
        return new EqParameterInfo(dA, i2, i, dArrCopyOf, dArrCopyOf2, dArrCopyOf3, iArrCopyOf);
    }

    public final byte[] a() {
        int i = this.stageNum;
        int i2 = 4;
        byte[] bArr = new byte[(i * 8) + 4];
        bArr[0] = (byte) ((i & 15) | ((this.samplingFreqIndex & 15) << 4));
        int i3 = (int) (this.globalGain * a);
        bArr[2] = (byte) (i3 & 255);
        bArr[3] = (byte) ((i3 >> 8) & 255);
        for (int i4 = 0; i4 < this.stageNum; i4++) {
            bArr[i2] = (byte) (this.biquadType[i4] & 15);
            int i5 = (int) (this.gains[i4] * a);
            bArr[i2 + 2] = (byte) (i5 & 255);
            bArr[i2 + 3] = (byte) ((i5 >> 8) & 255);
            int i6 = (int) this.freq[i4];
            bArr[i2 + 4] = (byte) (i6 & 255);
            bArr[i2 + 5] = (byte) ((i6 >> 8) & 255);
            int i7 = (int) (this.q[i4] * 100.0d);
            bArr[i2 + 6] = (byte) (i7 & 255);
            bArr[i2 + 7] = (byte) ((i7 >> 8) & 255);
            i2 += 8;
        }
        return bArr;
    }

    public final byte[] b() {
        int i = this.stageNum;
        int i2 = 4;
        byte[] bArr = new byte[(i * 5) + 4];
        bArr[0] = (byte) ((i & 15) | ((this.samplingFreqIndex & 15) << 4));
        int i3 = (int) (this.globalGain * 100.0d);
        bArr[2] = (byte) (i3 & 255);
        bArr[3] = (byte) ((i3 >> 8) & 255);
        for (int i4 = 0; i4 < this.stageNum; i4++) {
            int i5 = (int) (this.q[i4] * 100.0d);
            int i6 = (int) (this.gains[i4] * 100.0d);
            int i7 = (int) this.freq[i4];
            if (i6 < 0) {
                i6 = (~(-i6)) & 65535;
            }
            int i8 = (i5 << 3) | (this.biquadType[i4] & 7) | ((i6 & 4095) << 13) | ((i7 & 127) << 25);
            bArr[i2] = (byte) (i8 & 255);
            bArr[i2 + 1] = (byte) ((i8 >> 8) & 255);
            bArr[i2 + 2] = (byte) ((i8 >> 16) & 255);
            bArr[i2 + 3] = (byte) ((i8 >> 24) & 255);
            bArr[i2 + 4] = (byte) ((i7 >> 7) & 255);
            i2 += 5;
        }
        return bArr;
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

    public static EqParameterInfo parse(AudioEq audioEq) {
        return new EqParameterInfo(audioEq.getGlobalGain(), audioEq.getSampleRate(), audioEq.getStageNum(), audioEq.getFreq(), audioEq.getQ(), audioEq.getGains(), audioEq.getBiquadType());
    }
}
