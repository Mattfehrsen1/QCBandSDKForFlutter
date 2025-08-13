package com.realsil.customer.bbpro.llapt;

import android.os.Parcel;
import android.os.Parcelable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/llapt/LlAptBrightnessStatus.class */
public class LlAptBrightnessStatus implements Parcelable {
    public static final byte INVALID_MAIN_LEVEL = -1;
    public static final short INVALID_SUB_VOLUME = -1;
    public static final Parcelable.Creator<LlAptBrightnessStatus> CREATOR = new a();
    public byte a;
    public byte b;
    public short c;
    public short d;
    public boolean e;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/llapt/LlAptBrightnessStatus$a.class */
    public class a implements Parcelable.Creator<LlAptBrightnessStatus> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public LlAptBrightnessStatus createFromParcel(Parcel parcel) {
            return new LlAptBrightnessStatus(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public LlAptBrightnessStatus[] newArray(int i) {
            return new LlAptBrightnessStatus[i];
        }
    }

    public LlAptBrightnessStatus(byte b, byte b2, short s, short s2, boolean z) {
        this.a = b;
        this.b = b2;
        this.c = s;
        this.d = s2;
        this.e = z;
    }

    public static LlAptBrightnessStatus builder(byte[] bArr) {
        if (bArr == null || bArr.length < 6) {
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        byte b = byteBufferWrap.get();
        short s = byteBufferWrap.getShort();
        byte b2 = byteBufferWrap.get();
        short s2 = byteBufferWrap.getShort();
        boolean z = false;
        if (bArr.length >= 7) {
            z = (byteBufferWrap.get() & 1) == 1;
        }
        return new LlAptBrightnessStatus(b, b2, s, s2, z);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LlAptBrightnessStatus{");
        sb.append(String.format(Locale.US, "\n\nL=%d(%d), R=%d(%d), rwsSyncEnabled=%b", Byte.valueOf(this.a), Short.valueOf(this.c), Byte.valueOf(this.b), Short.valueOf(this.d), Boolean.valueOf(this.e)));
        sb.append("\n}");
        return sb.toString();
    }

    public byte getMainLchLevel() {
        return this.a;
    }

    public byte getMainRchLevel() {
        return this.b;
    }

    public short getSubLchLevel() {
        return this.c;
    }

    public short getSubRchLevel() {
        return this.d;
    }

    public boolean isRwsSyncEnabled() {
        return this.e;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.a);
        parcel.writeByte(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeByte(this.e ? (byte) 1 : (byte) 0);
    }

    public LlAptBrightnessStatus(Parcel parcel) {
        this.e = false;
        this.a = parcel.readByte();
        this.b = parcel.readByte();
        this.c = (short) parcel.readInt();
        this.d = (short) parcel.readInt();
        this.e = parcel.readByte() != 0;
    }
}
