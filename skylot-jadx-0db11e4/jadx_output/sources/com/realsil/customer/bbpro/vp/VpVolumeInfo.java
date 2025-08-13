package com.realsil.customer.bbpro.vp;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/vp/VpVolumeInfo.class */
public class VpVolumeInfo implements Parcelable {
    public static final VpVolumeInfo DEFAULT = new VpVolumeInfo();
    public static final Parcelable.Creator<VpVolumeInfo> CREATOR = new a();
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public boolean rwsSyncSupported;
    public boolean rwsSyncEnabled;
    public int g;
    public int h;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/vp/VpVolumeInfo$a.class */
    public class a implements Parcelable.Creator<VpVolumeInfo> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public VpVolumeInfo createFromParcel(Parcel parcel) {
            return new VpVolumeInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public VpVolumeInfo[] newArray(int i) {
            return new VpVolumeInfo[i];
        }
    }

    public VpVolumeInfo() {
        this.rwsSyncSupported = false;
        this.rwsSyncEnabled = false;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        parcel.writeByte(this.rwsSyncSupported ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.rwsSyncEnabled ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.g);
        parcel.writeInt(this.h);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getLeftMinVolumeLevel() {
        return this.a;
    }

    public int getLeftMaxVolumeLevel() {
        return this.b;
    }

    public int getLeftCurVolumeLevel() {
        return this.c;
    }

    public int getRightMinVolumeLevel() {
        return this.d;
    }

    public int getRightMaxVolumeLevel() {
        return this.e;
    }

    public int getRightCurVolumeLevel() {
        return this.f;
    }

    public int getSyncMinVolumeLevel() {
        return this.g;
    }

    public int getSyncMaxVolumeLevel() {
        return this.h;
    }

    public boolean isRwsSyncSupported() {
        return this.rwsSyncSupported;
    }

    public boolean isRwsSyncEnabled() {
        return this.rwsSyncEnabled;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("VpVolumeInfo {");
        sb.append(String.format("\n\trwsSyncSupported=%b", Boolean.valueOf(this.rwsSyncSupported)));
        if (this.rwsSyncSupported) {
            sb.append(String.format(Locale.US, "\n\t\trwsSyncEnabled=%b, min=%d, max=%d", Boolean.valueOf(this.rwsSyncEnabled), Integer.valueOf(this.g), Integer.valueOf(this.h)));
        }
        Locale locale = Locale.US;
        sb.append(String.format(locale, "\n\tLCH: min=%d, max=%d, cur=%d", Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c)));
        sb.append(String.format(locale, "\n\tRCH: min=%d, max=%d, cur=%d", Integer.valueOf(this.d), Integer.valueOf(this.e), Integer.valueOf(this.f)));
        sb.append("\n}");
        return sb.toString();
    }

    public VpVolumeInfo(int i, int i2, byte b, int i3, int i4, byte b2, boolean z, boolean z2) {
        this.a = i;
        this.b = i2;
        this.d = i3;
        this.e = i4;
        this.rwsSyncSupported = z;
        this.rwsSyncEnabled = z2;
        if (!z || !z2) {
            b = b == -1 ? (byte) 0 : b;
            b2 = b2 == -1 ? (byte) 0 : b2;
            this.c = b & 255;
            this.f = b2 & 255;
        } else if (b != -1) {
            int i5 = b & 255;
            this.c = i5;
            this.f = i5;
        } else {
            int i6 = b2 & 255;
            this.c = i6;
            this.f = i6;
        }
        this.h = Math.min(i2, i4);
        this.g = Math.max(i, i3);
    }

    public VpVolumeInfo(Parcel parcel) {
        this.rwsSyncSupported = false;
        this.rwsSyncEnabled = false;
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.rwsSyncSupported = parcel.readByte() != 0;
        this.rwsSyncEnabled = parcel.readByte() != 0;
        this.g = parcel.readInt();
        this.h = parcel.readInt();
    }
}
