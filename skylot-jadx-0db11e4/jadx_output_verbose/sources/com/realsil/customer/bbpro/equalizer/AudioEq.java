package com.realsil.customer.bbpro.equalizer;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.realsil.customer.core.utility.DataConverter;
import java.util.Arrays;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/equalizer/AudioEq.class */
public final class AudioEq implements Parcelable {
    public static final int MAX_DB_VALUE = 12;
    public static final int MIN_DB_VALUE = -12;
    public static final int MAX_EQ_STAGE_NUM = 10;
    public static final int SEND_EQ_DATA_LENGTH = 204;
    public static final int PARSE_EQ_DATA_LENGTH = 212;
    public static final int SW_EQ_DATA_LENGTH = 208;
    public static final int PEAK_FILTER = 0;
    public static final int SHELVING_LP_FILTER = 1;
    public static final int SHELVING_HP_FILTER = 2;
    public static final int LOWPASS_FILTER = 3;
    public static final int HIGHPASS_FILTER = 4;
    public static final int BANDPASS_FILTER = 5;
    public static final int BANDREJECT_FILTER = 6;
    public static final int ALL_PASS_FILTER = 7;
    public static final int ALL_EQ_TYPE = 8;
    public int a;
    public double b;
    public int c;
    public int d;
    public double[] e;
    public double[] f;
    public double[] g;
    public int[] h;
    public byte[] i;
    public static final double[] FREQ = {32.0d, 64.0d, 125.0d, 250.0d, 500.0d, 1000.0d, 2000.0d, 4000.0d, 8000.0d, 16000.0d};
    public static final double[] Q = {2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d};
    public static final double[] GAIN_FLAT = {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d};
    public static final int[] BIQUAD_TYPE = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static final Parcelable.Creator<AudioEq> CREATOR = new a();

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/equalizer/AudioEq$a.class */
    public class a implements Parcelable.Creator<AudioEq> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AudioEq createFromParcel(Parcel parcel) {
            return new AudioEq(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AudioEq[] newArray(int i) {
            return new AudioEq[i];
        }
    }

    public AudioEq() {
        this.a = 10;
        this.b = 0.0d;
        this.c = 3;
        this.e = FREQ;
        this.f = Q;
        this.g = GAIN_FLAT;
        this.h = BIQUAD_TYPE;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeDouble(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeDoubleArray(this.e);
        parcel.writeDoubleArray(this.f);
        parcel.writeDoubleArray(this.g);
        parcel.writeIntArray(this.h);
        parcel.writeByteArray(this.i);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public double getGlobalGain() {
        return this.b;
    }

    public void setGlobalGain(double d) {
        this.b = d;
    }

    public int getSampleRate() {
        return this.c;
    }

    public void setSampleRate(int i) {
        this.c = i;
    }

    public int getStateNum() {
        return this.a;
    }

    public void setStateNum(int i) {
        this.a = i;
    }

    public int getStageNum() {
        return this.a;
    }

    public void setStageNum(int i) {
        this.a = i;
    }

    public double[] getFreq() {
        return this.e;
    }

    public double[] getQ() {
        return this.f;
    }

    public double[] getGains() {
        return this.g;
    }

    public void setFreq(double[] dArr) {
        this.e = dArr;
    }

    public void setQ(double[] dArr) {
        this.f = dArr;
    }

    public void setGains(double[] dArr) {
        this.g = dArr;
    }

    public int[] getBiquadType() {
        return this.h;
    }

    public void setBiquadType(int[] iArr) {
        this.h = iArr;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AudioEq {");
        byte[] bArr = this.i;
        if (bArr != null) {
            sb.append(String.format(Locale.US, "\neqData=(%d)%s", Integer.valueOf(bArr.length), DataConverter.bytes2Hex(this.i)));
        }
        sb.append(String.format(Locale.US, "\n\tStageNum=%d, globalGain=%f, sampleRate=%d, accuracy=%d", Integer.valueOf(this.a), Double.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d)));
        sb.append("\n\tFreq=" + Arrays.toString(this.e));
        sb.append("\n\tGain=" + Arrays.toString(this.g));
        sb.append("\n\tQ=" + Arrays.toString(this.f));
        sb.append("\n\tbiquadType=" + Arrays.toString(this.h));
        sb.append("\n}");
        return sb.toString();
    }

    public AudioEq(double[] dArr, double[] dArr2, double[] dArr3) {
        this.a = 10;
        this.b = 0.0d;
        this.c = 3;
        this.h = BIQUAD_TYPE;
        this.e = dArr;
        this.f = dArr2;
        this.g = dArr3;
    }

    public AudioEq(int i, double[] dArr, double[] dArr2, double[] dArr3) {
        this.b = 0.0d;
        this.c = 3;
        this.h = BIQUAD_TYPE;
        this.a = i;
        this.e = dArr;
        this.f = dArr2;
        this.g = dArr3;
    }

    public AudioEq(int i, double d, int i2, int i3, double[] dArr, double[] dArr2, double[] dArr3, int[] iArr) {
        this(i, d, i2, i3, dArr, dArr2, dArr3, iArr, null);
    }

    public AudioEq(int i, double d, int i2, int i3, double[] dArr, double[] dArr2, double[] dArr3, int[] iArr, byte[] bArr) {
        this.a = i;
        this.b = d;
        this.c = i2;
        this.d = i3;
        this.e = dArr;
        this.f = dArr2;
        this.g = dArr3;
        this.h = iArr;
        this.i = bArr;
    }

    public AudioEq(Parcel parcel) {
        this.a = 10;
        this.b = 0.0d;
        this.c = 3;
        this.e = FREQ;
        this.f = Q;
        this.g = GAIN_FLAT;
        this.h = BIQUAD_TYPE;
        this.a = parcel.readInt();
        this.b = parcel.readDouble();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.createDoubleArray();
        this.f = parcel.createDoubleArray();
        this.g = parcel.createDoubleArray();
        this.h = parcel.createIntArray();
        this.i = parcel.createByteArray();
    }
}
