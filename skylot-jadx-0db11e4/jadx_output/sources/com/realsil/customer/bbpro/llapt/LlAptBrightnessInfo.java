package com.realsil.customer.bbpro.llapt;

import android.os.Parcel;
import android.os.Parcelable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/llapt/LlAptBrightnessInfo.class */
public final class LlAptBrightnessInfo implements Parcelable {
    public static final byte INVALID_BRIGHTNESS_MAIN = -1;
    public static final short INVALID_BRIGHTNESS_SUB = -1;
    public static final byte INVALID_BRIGHTNESS_LEVEL = -1;
    public static final short INVALID_BRIGHTNESS_WEIGHT = -1;
    public static final Parcelable.Creator<LlAptBrightnessInfo> CREATOR = new a();
    public int a;
    public byte b;
    public byte c;
    public int d;
    public short e;
    public short f;
    public boolean g;
    public boolean h;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/llapt/LlAptBrightnessInfo$a.class */
    public class a implements Parcelable.Creator<LlAptBrightnessInfo> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public LlAptBrightnessInfo createFromParcel(Parcel parcel) {
            return new LlAptBrightnessInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public LlAptBrightnessInfo[] newArray(int i) {
            return new LlAptBrightnessInfo[i];
        }
    }

    public LlAptBrightnessInfo(int i, int i2, byte b, short s, byte b2, short s2, boolean z, boolean z2) {
        this.a = i;
        this.b = b;
        this.c = b2;
        this.d = i2;
        this.e = s;
        this.f = s2;
        this.g = z;
        this.h = z2;
    }

    public static LlAptBrightnessInfo builder(byte[] bArr) {
        if (bArr == null || bArr.length < 9) {
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        int i = byteBufferWrap.get() & 255;
        int i2 = byteBufferWrap.getShort() & 65535;
        byte b = byteBufferWrap.get();
        short s = byteBufferWrap.getShort();
        byte b2 = byteBufferWrap.get();
        short s2 = byteBufferWrap.getShort();
        boolean z = false;
        boolean z2 = false;
        if (bArr.length >= 10) {
            z = true;
            z2 = (byteBufferWrap.get() & 1) == 1;
        }
        return new LlAptBrightnessInfo(i, i2, b, s, b2, s2, z, z2);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LlAptBrightnessInfo {");
        if (this.g) {
            sb.append(String.format("\n\trwsSyncEnabled=%b", Boolean.valueOf(this.h)));
        } else {
            sb.append(String.format("\n\trwsSyncSupported=%b", Boolean.FALSE));
        }
        Locale locale = Locale.US;
        sb.append(String.format(locale, "\n\tMain (L:%d,R:%d)/%d,", Byte.valueOf(this.b), Byte.valueOf(this.c), Integer.valueOf(this.a)));
        sb.append(String.format(locale, "\n\tSub (L:%d,R:%d)/%d,", Short.valueOf(this.e), Short.valueOf(this.f), Integer.valueOf(this.d)));
        sb.append("\n}");
        return sb.toString();
    }

    public int getMainMaxLevel() {
        return this.a;
    }

    public int getMainLchLevel() {
        byte b = this.b;
        if (b == -1) {
            return 0;
        }
        return b & (-1);
    }

    public int getMainRchLevel() {
        byte b = this.c;
        if (b == -1) {
            return 0;
        }
        return b & (-1);
    }

    public int getSubMaxLevel() {
        return this.d;
    }

    public int getSubLchLevel() {
        short s = this.e;
        if (s == -1) {
            return 0;
        }
        return s & (-1);
    }

    public int getSubRchLevel() {
        short s = this.f;
        if (s == -1) {
            return 0;
        }
        return s & (-1);
    }

    public boolean isRwsSyncSupported() {
        return this.g;
    }

    public boolean isRwsSyncEnabled() {
        return this.h;
    }

    public int getLchMinLevel() {
        return 0;
    }

    public int getLchMinWeight() {
        return 0;
    }

    public void updateLlAptBrighenessStatus(LlAptBrightnessStatus llAptBrightnessStatus) {
        boolean zIsRwsSyncEnabled = llAptBrightnessStatus.isRwsSyncEnabled();
        this.h = zIsRwsSyncEnabled;
        if (!zIsRwsSyncEnabled) {
            if (llAptBrightnessStatus.getMainLchLevel() != -1) {
                this.b = llAptBrightnessStatus.getMainLchLevel();
            }
            if (llAptBrightnessStatus.getMainRchLevel() != -1) {
                this.c = llAptBrightnessStatus.getMainRchLevel();
            }
            if (llAptBrightnessStatus.getSubLchLevel() != -1) {
                this.e = llAptBrightnessStatus.getSubLchLevel();
            }
            if (llAptBrightnessStatus.getSubRchLevel() != -1) {
                this.f = llAptBrightnessStatus.getSubRchLevel();
                return;
            }
            return;
        }
        if (llAptBrightnessStatus.getMainLchLevel() != -1) {
            this.b = llAptBrightnessStatus.getMainLchLevel();
            this.c = llAptBrightnessStatus.getMainLchLevel();
        } else {
            this.b = llAptBrightnessStatus.getMainRchLevel();
            this.c = llAptBrightnessStatus.getMainRchLevel();
        }
        if (llAptBrightnessStatus.getMainLchLevel() != -1) {
            this.e = llAptBrightnessStatus.getSubLchLevel();
            this.f = llAptBrightnessStatus.getSubLchLevel();
        } else {
            this.e = llAptBrightnessStatus.getSubRchLevel();
            this.f = llAptBrightnessStatus.getSubRchLevel();
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeByte(this.b);
        parcel.writeByte(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        parcel.writeByte(this.g ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.h ? (byte) 1 : (byte) 0);
    }

    public LlAptBrightnessInfo(Parcel parcel) {
        this.g = false;
        this.h = false;
        this.a = parcel.readInt();
        this.b = parcel.readByte();
        this.c = parcel.readByte();
        this.d = parcel.readInt();
        this.e = (short) parcel.readInt();
        this.f = (short) parcel.readInt();
        this.g = parcel.readByte() != 0;
        this.h = parcel.readByte() != 0;
    }
}
