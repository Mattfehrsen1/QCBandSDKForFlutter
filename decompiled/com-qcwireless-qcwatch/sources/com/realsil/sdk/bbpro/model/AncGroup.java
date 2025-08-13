package com.realsil.sdk.bbpro.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class AncGroup implements Parcelable {
    public static final byte ANC_SCENARIO_INACTIVE = -1;
    public static final byte ANC_SCENARIO_NA = 0;
    public static final byte ANC_SCENARIO_UNKNOWN = -1;
    public static final byte ANC_SCENARIO_UNUSED = -2;
    public static final Parcelable.Creator<AncGroup> CREATOR = new Parcelable.Creator<AncGroup>() { // from class: com.realsil.sdk.bbpro.model.AncGroup.1
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
    public static final byte INVALID_GROUP_INDEX = -1;
    public byte a;
    public byte b;
    public int c;
    public int d;
    public byte[] e;

    public static AncGroup builder(byte[] bArr) {
        byte[] bArr2 = null;
        if (bArr.length < 4) {
            return null;
        }
        byte b = bArr[0];
        byte b2 = bArr[1];
        int i = bArr[2] & 255;
        int i2 = bArr[3] & 255;
        int length = bArr.length - 4;
        if (length > 0) {
            bArr2 = new byte[length];
            System.arraycopy(bArr, 4, bArr2, 0, length);
        }
        return new AncGroup(b, b2, i, i2, bArr2);
    }

    public final void a() {
        byte[] bArr = this.e;
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        int i = 0;
        int i2 = 0;
        int i3 = -1;
        int i4 = -1;
        while (true) {
            byte[] bArr2 = this.e;
            if (i >= bArr2.length) {
                break;
            }
            if (bArr2[i] != -1) {
                if (bArr2[i] == -2) {
                    i2++;
                } else {
                    if (i3 < 0) {
                        i3 = i2;
                    }
                    i4 = i2;
                    i2++;
                }
            }
            i++;
        }
        int i5 = this.c;
        if (i5 < i3 || i5 > i4) {
            this.c = i3;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getActiveGroupIndex() {
        return this.c;
    }

    public byte getActiveSenario(int i) {
        byte[] bArr = this.e;
        if (bArr != null && i >= 0 && i <= bArr.length) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                byte[] bArr2 = this.e;
                if (i2 >= bArr2.length) {
                    break;
                }
                if (bArr2[i2] != -1) {
                    if (i3 == i) {
                        return bArr2[i2];
                    }
                    i3++;
                }
                i2++;
            }
        }
        return (byte) -1;
    }

    public int getGroupNum() {
        return this.d;
    }

    public byte[] getGroupScenario() {
        return this.e;
    }

    public byte getScenario(int i) {
        byte[] bArr = this.e;
        if (bArr != null && i >= 0 && i <= bArr.length) {
            return bArr[i];
        }
        return (byte) -1;
    }

    public byte getStatus() {
        return this.b;
    }

    public boolean isAncScenarioSupported() {
        return getActiveSenario(0) != -1;
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
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.a);
        parcel.writeByte(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeByteArray(this.e);
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
