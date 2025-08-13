package com.realsil.customer.bbpro.llapt;

import android.os.Parcel;
import android.os.Parcelable;
import com.realsil.customer.core.utility.DataConverter;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/llapt/LlAptBasicInfo.class */
public final class LlAptBasicInfo implements Parcelable {
    public static final byte INVALID_GROUP_INDEX = -1;
    public static final byte INVALID_SCENARIO = -1;
    public static final Parcelable.Creator<LlAptBasicInfo> CREATOR = new a();
    public static final byte LLAPT_SCENARIO_INACTIVE = -1;
    public static final byte LLAPT_SCENARIO_UNUSED = -2;
    public byte a;
    public byte b;
    public int c;
    public int d;
    public byte[] e;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/llapt/LlAptBasicInfo$a.class */
    public class a implements Parcelable.Creator<LlAptBasicInfo> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public LlAptBasicInfo createFromParcel(Parcel parcel) {
            return new LlAptBasicInfo(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public LlAptBasicInfo[] newArray(int i) {
            return new LlAptBasicInfo[i];
        }
    }

    public static LlAptBasicInfo builder(byte[] bArr) {
        if (bArr.length < 4) {
            return null;
        }
        byte b = bArr[0];
        byte b2 = bArr[1];
        int i = bArr[2] & 255;
        int i2 = bArr[3] & 255;
        byte[] bArr2 = null;
        int length = bArr.length - 4;
        if (length > 0) {
            bArr2 = new byte[length];
            System.arraycopy(bArr, 4, bArr2, 0, length);
        }
        return new LlAptBasicInfo(b, b2, i, i2, bArr2);
    }

    public /* synthetic */ LlAptBasicInfo(Parcel parcel, a aVar) {
        this(parcel);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("type=" + ((int) this.a));
        sb.append(", status=" + ((int) this.b));
        sb.append(", activeGroupIndex=" + this.c);
        sb.append(", groupNum=" + this.d);
        sb.append(", groupScenario=" + DataConverter.bytes2Hex(this.e));
        return sb.toString();
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
        parcel.writeByteArray(this.e);
    }

    public byte getStatus() {
        return this.b;
    }

    public void setActiveGroupIndex(int i) {
        if (((byte) (i & 255)) != -1) {
            this.c = i;
            a();
            return;
        }
        byte[] bArr = this.e;
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        a();
    }

    public int getActiveGroupIndex() {
        return this.c;
    }

    public int getGroupNum() {
        return this.d;
    }

    public byte[] getGroupScenario() {
        return this.e;
    }

    public boolean isScenarioSupported() {
        byte[] bArr = this.e;
        return bArr != null && bArr.length > 0;
    }

    public byte getScenario(int i) {
        byte[] bArr = this.e;
        if (bArr != null && i >= 0 && i <= bArr.length) {
            return bArr[i];
        }
        return (byte) -1;
    }

    public byte getActiveScenario(int i) {
        byte[] bArr = this.e;
        if (bArr == null || i < 0 || i > bArr.length) {
            return (byte) -1;
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            byte[] bArr2 = this.e;
            if (i4 >= bArr2.length) {
                return (byte) -1;
            }
            byte b = bArr2[i3];
            if (b != -1) {
                if (i2 == i) {
                    return b;
                }
                i2++;
            }
            i3++;
        }
    }

    public final void a() {
        byte[] bArr = this.e;
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        int i = 0;
        int i2 = -1;
        int i3 = -1;
        int i4 = 0;
        while (true) {
            int i5 = i4;
            byte[] bArr2 = this.e;
            if (i5 >= bArr2.length) {
                break;
            }
            byte b = bArr2[i4];
            if (b != -1) {
                if (b == -2) {
                    i++;
                } else {
                    if (i2 < 0) {
                        i2 = i;
                    }
                    i3 = i;
                    i++;
                }
            }
            i4++;
        }
        int i6 = this.c;
        if (i6 < i2 || i6 > i3) {
            this.c = i2;
        }
    }

    public LlAptBasicInfo(byte b, byte b2, int i, int i2, byte[] bArr) {
        this.a = b;
        this.b = b2;
        this.d = i2;
        this.e = bArr;
        setActiveGroupIndex(i);
    }

    public LlAptBasicInfo(Parcel parcel) {
        this.a = parcel.readByte();
        this.b = parcel.readByte();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.createByteArray();
    }
}
