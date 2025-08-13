package com.realsil.customer.bbpro.apt;

import android.os.Parcel;
import android.os.Parcelable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/apt/AptVolumeInfo.class */
public final class AptVolumeInfo implements Parcelable {
    public static final byte INVALID_MAIN_VOLUME = -1;
    public static final short INVALID_SUB_VOLUME = -1;
    public static final int RWS_SYNC_APT_VOLME_ENABLED = 1;
    public static final Parcelable.Creator<AptVolumeInfo> CREATOR = new a();
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public boolean g;
    public boolean h;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/apt/AptVolumeInfo$a.class */
    public class a implements Parcelable.Creator<AptVolumeInfo> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AptVolumeInfo createFromParcel(Parcel parcel) {
            return new AptVolumeInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AptVolumeInfo[] newArray(int i) {
            return new AptVolumeInfo[i];
        }
    }

    public AptVolumeInfo(int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2) {
        this.a = i;
        this.b = i3;
        this.c = i5;
        this.d = i2;
        this.e = i4;
        this.f = i6;
        this.g = z;
        this.h = z2;
    }

    public static AptVolumeInfo builderV1(byte[] bArr) {
        if (bArr.length < 3) {
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        int i = byteBufferWrap.get(1) & 255;
        int i2 = byteBufferWrap.get(2) & 255;
        return new AptVolumeInfo(i2, i2, i, i, i, i, false, false);
    }

    public static AptVolumeInfo builderV2(byte[] bArr) {
        if (bArr.length < 1) {
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        int i = byteBufferWrap.get() & 15;
        return new AptVolumeInfo(15, 15, i, i, i, i, false, false);
    }

    public static AptVolumeInfo builder(byte[] bArr) {
        if (bArr.length < 9) {
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        int i = byteBufferWrap.get() & 255;
        int i2 = byteBufferWrap.getShort() & 65535;
        byte b = byteBufferWrap.get();
        byte b2 = b;
        short s = byteBufferWrap.getShort();
        byte b3 = byteBufferWrap.get();
        short s2 = byteBufferWrap.getShort();
        if (b == -1) {
            b2 = 0;
        }
        if (s == -1) {
            s = 0;
        }
        if (b3 == -1) {
            b3 = 0;
        }
        if (s2 == -1) {
            s2 = 0;
        }
        boolean z = false;
        boolean z2 = false;
        if (bArr.length >= 10) {
            z = true;
            z2 = (byteBufferWrap.get() & 255) == 1;
        }
        return new AptVolumeInfo(i, i2, b2, s, b3, s2, z, z2);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        parcel.writeByte(this.g ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.h ? (byte) 1 : (byte) 0);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AptVolumeInfo {");
        boolean z = this.g;
        if (z) {
            sb.append(String.format(Locale.US, "\n\trwsSyncSupported=%b", Boolean.valueOf(z)));
        } else {
            sb.append(String.format(Locale.US, "\n\trwsSyncSupported=%b, enabled:%b", Boolean.valueOf(z), Boolean.valueOf(this.h)));
        }
        Locale locale = Locale.US;
        sb.append(String.format(locale, "\n\tLevel (L:%d,R:%d)/%d,", Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.a)));
        sb.append(String.format(locale, "\n\tStep (L:%d,R:%d)/%d,", Integer.valueOf(this.e), Integer.valueOf(this.f), Integer.valueOf(this.d)));
        sb.append("\n}");
        return sb.toString();
    }

    public int getMainMaxVolumeLevel() {
        return this.a;
    }

    public int getMainVolumeLevel() {
        return this.b;
    }

    public int getMainLchVolumeLevel() {
        return this.b;
    }

    public int getMainRchVolumeLevel() {
        return this.c;
    }

    public int getSubMaxVolumeLevel() {
        return this.d;
    }

    public int getSubLchVolumeLevel() {
        return this.e;
    }

    public int getSubRchVolumeLevel() {
        return this.f;
    }

    public boolean isRwsSyncSupported() {
        return this.g;
    }

    public boolean isRwsSyncEnabled() {
        return this.h;
    }

    public void setRwsSyncEnabled(boolean z) {
        this.h = z;
    }

    public void updateAptVolumeStatus(AptVolumeStatus aptVolumeStatus) {
        this.b = aptVolumeStatus.getMainLchVolumeLevel();
        this.c = aptVolumeStatus.getMainRchVolumeLevel();
        this.e = aptVolumeStatus.getSubLchVolumeLevel();
        this.f = aptVolumeStatus.getSubRchVolumeLevel();
    }

    public AptVolumeInfo(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = parcel.readByte() != 0;
        this.h = parcel.readByte() != 0;
    }
}
