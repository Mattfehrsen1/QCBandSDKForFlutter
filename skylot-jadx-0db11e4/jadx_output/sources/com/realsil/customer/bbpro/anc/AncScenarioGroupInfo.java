package com.realsil.customer.bbpro.anc;

import android.os.Parcel;
import android.os.Parcelable;
import com.realsil.customer.core.utility.DataConverter;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/anc/AncScenarioGroupInfo.class */
public final class AncScenarioGroupInfo implements Parcelable {
    public static final byte INVALID_SCENARIO = -1;
    public static final Parcelable.Creator<AncScenarioGroupInfo> CREATOR = new a();
    public int a;
    public byte[] b;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/anc/AncScenarioGroupInfo$a.class */
    public class a implements Parcelable.Creator<AncScenarioGroupInfo> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AncScenarioGroupInfo createFromParcel(Parcel parcel) {
            return new AncScenarioGroupInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AncScenarioGroupInfo[] newArray(int i) {
            return new AncScenarioGroupInfo[i];
        }
    }

    public AncScenarioGroupInfo(int i, byte[] bArr) {
        this.a = i;
        this.b = bArr;
    }

    public static AncScenarioGroupInfo builder(byte[] bArr) {
        if (bArr.length < 1) {
            return null;
        }
        int i = bArr[0] & 255;
        byte[] bArr2 = null;
        int length = bArr.length - 1;
        if (length > 0) {
            bArr2 = new byte[length];
            System.arraycopy(bArr, 1, bArr2, 0, length);
        }
        return new AncScenarioGroupInfo(i, bArr2);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeByteArray(this.b);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LlAptScenarioGroupInfo{");
        sb.append("\n\tgroupNum=" + this.a);
        sb.append("\n\tgroupScenario=" + DataConverter.bytes2Hex(this.b));
        sb.append("\n}");
        return sb.toString();
    }

    public int getGroupNum() {
        return this.a;
    }

    public byte[] getGroupScenario() {
        return this.b;
    }

    public boolean isSenarioSupported() {
        byte[] bArr = this.b;
        return bArr != null && bArr.length > 0;
    }

    public byte getSenario(int i) {
        byte[] bArr = this.b;
        if (bArr != null && i >= 0 && i <= bArr.length) {
            return bArr[i];
        }
        return (byte) 0;
    }

    public AncScenarioGroupInfo(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.createByteArray();
    }
}
