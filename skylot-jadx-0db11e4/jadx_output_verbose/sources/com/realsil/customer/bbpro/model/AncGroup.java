package com.realsil.customer.bbpro.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.realsil.customer.core.logger.ZLogger;
import com.realsil.customer.core.utility.DataConverter;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/model/AncGroup.class */
public final class AncGroup implements Parcelable {
    public static final byte ANC_SCENARIO_NA = 0;
    public static final byte ANC_SCENARIO_UNKNOWN = -1;
    public static final byte ANC_SCENARIO_INACTIVE = -1;
    public static final byte ANC_SCENARIO_UNUSED = -2;
    public static final byte INVALID_GROUP_INDEX = -1;
    public static final Parcelable.Creator<AncGroup> CREATOR = new Parcelable.Creator<AncGroup>() { // from class: com.realsil.customer.bbpro.model.AncGroup.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AncGroup createFromParcel(Parcel parcel) {
            return new AncGroup(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AncGroup[] newArray(int i) {
            return new AncGroup[i];
        }
    };
    public byte a;
    public byte b;
    public int c;
    public int d;
    public byte[] e;

    public static AncGroup builder(byte[] bArr) {
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
        return new AncGroup(b, b2, i, i2, bArr2);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AncGroup{");
        sb.append("\n\ttype=" + ((int) this.a));
        sb.append(", status=" + ((int) this.b));
        sb.append(", activeGroupIndex=" + this.c);
        sb.append("\n\tgroupNum=" + this.d);
        sb.append(", groupScenario=" + DataConverter.bytes2Hex(this.e));
        sb.append("\n}");
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

    public int getActiveGroupIndex() {
        return this.c;
    }

    public void setActiveGroupIndex(int i) {
        if (((byte) (i & 255)) != -1) {
            this.c = i;
            a();
        } else {
            byte[] bArr = this.e;
            if (bArr != null && bArr.length > 0) {
                a();
            }
        }
        ZLogger.v(String.format(Locale.US, "activeGroupIndex=%d, groupIndex=%d", Integer.valueOf(this.c), Integer.valueOf(i)));
    }

    public int getGroupNum() {
        return this.d;
    }

    public byte[] getGroupScenario() {
        return this.e;
    }

    public boolean isAncScenarioSupported() {
        return getActiveSenario(0) != -1;
    }

    public byte getScenario(int i) {
        byte[] bArr = this.e;
        if (bArr != null && i >= 0 && i <= bArr.length) {
            return bArr[i];
        }
        return (byte) -1;
    }

    public byte getActiveSenario(int i) {
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

    public AncGroup(byte b, byte b2, int i, int i2, byte[] bArr) {
        this.a = b;
        this.b = b2;
        this.d = i2;
        this.e = bArr;
        setActiveGroupIndex(i);
    }

    public AncGroup(Parcel parcel) {
        this.a = parcel.readByte();
        this.b = parcel.readByte();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.createByteArray();
    }
}
