package com.realsil.sdk.bbpro.apt;

import android.os.Parcel;
import android.os.Parcelable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class AptVolumeStatus implements Parcelable {
    public static final Parcelable.Creator<AptVolumeStatus> CREATOR = new a();
    public static final byte INVALID_MAIN_VOLUME = -1;
    public static final short INVALID_SUB_VOLUME = -1;
    public int a;
    public int b;
    public int c;
    public int d;

    public class a implements Parcelable.Creator<AptVolumeStatus> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AptVolumeStatus createFromParcel(Parcel parcel) {
            return new AptVolumeStatus(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AptVolumeStatus[] newArray(int i) {
            return new AptVolumeStatus[i];
        }
    }

    public AptVolumeStatus(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public static AptVolumeStatus builder(byte[] bArr) {
        if (bArr.length < 6) {
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        byte b = byteBufferWrap.get();
        short s = byteBufferWrap.getShort();
        byte b2 = byteBufferWrap.get();
        short s2 = byteBufferWrap.getShort();
        if (b == -1) {
            b = 0;
        }
        if (s == -1) {
            s = 0;
        }
        if (b2 == -1) {
            b2 = 0;
        }
        if (s2 == -1) {
            s2 = 0;
        }
        return new AptVolumeStatus(b, s, b2, s2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getMainLchVolumeLevel() {
        return this.a;
    }

    public int getMainRchVolumeLevel() {
        return this.c;
    }

    public int getSubLchVolumeLevel() {
        return this.b;
    }

    public int getSubRchVolumeLevel() {
        return this.d;
    }

    public String toString() {
        return String.format(Locale.US, "Volume:  L=%d(%d), R=%d(%d)", Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
    }

    public AptVolumeStatus(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
    }
}
