package com.realsil.customer.bbpro.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/model/DspParams.class */
public class DspParams implements Parcelable {
    public static final Parcelable.Creator<DspParams> CREATOR = new Parcelable.Creator<DspParams>() { // from class: com.realsil.customer.bbpro.model.DspParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DspParams createFromParcel(Parcel parcel) {
            return new DspParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DspParams[] newArray(int i) {
            return new DspParams[i];
        }
    };
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;

    public DspParams(byte[] bArr) {
        if (bArr == null || bArr.length <= 18) {
            return;
        }
        this.a = bArr[0] & 255;
        this.b = bArr[1] & 255;
        this.c = ((bArr[5] << 24) | (bArr[4] << 16) | (bArr[3] << 8) | (bArr[2] & 255)) & (-1);
        this.d = ((bArr[9] << 24) | (bArr[8] << 16) | (bArr[7] << 8) | (bArr[6] & 255)) & (-1);
        this.e = ((bArr[13] << 24) | (bArr[12] << 16) | (bArr[11] << 8) | (bArr[10] & 255)) & (-1);
        this.f = ((bArr[17] << 24) | (bArr[16] << 16) | (bArr[15] << 8) | (bArr[14] & 255)) & (-1);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("scenario=" + this.a);
        sb.append(", sampleRate=" + this.b);
        sb.append(", romVersion=" + this.c);
        sb.append(", ramVersion=" + this.d);
        sb.append(", patchVersion=" + this.e);
        sb.append(", sdkVersion=" + this.f);
        return sb.toString();
    }

    public int getScenario() {
        return this.a;
    }

    public void setScenario(int i) {
        this.a = i;
    }

    public int getSampleRate() {
        return this.b;
    }

    public void setSampleRate(int i) {
        this.b = i;
    }

    public int getRomVersion() {
        return this.c;
    }

    public void setRomVersion(int i) {
        this.c = i;
    }

    public int getRamVersion() {
        return this.d;
    }

    public void setRamVersion(int i) {
        this.d = i;
    }

    public int getPatchVersion() {
        return this.e;
    }

    public void setPatchVersion(int i) {
        this.e = i;
    }

    public int getSdkVersion() {
        return this.f;
    }

    public void setSdkVersion(int i) {
        this.f = i;
    }

    public DspParams(int i, int i2, int i3, int i4, int i5, int i6) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = i6;
    }

    public DspParams(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
    }
}
