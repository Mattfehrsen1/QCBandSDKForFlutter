package com.realsil.sdk.bbpro.llapt;

import android.os.Parcel;
import android.os.Parcelable;
import com.realsil.sdk.core.utility.DataConverter;

/* loaded from: classes3.dex */
public final class LlAptScenarioGroupInfo implements Parcelable {
    public static final Parcelable.Creator<LlAptScenarioGroupInfo> CREATOR = new a();
    public static final byte INVALID_SCENARIO = -1;
    public int a;
    public byte[] b;

    public class a implements Parcelable.Creator<LlAptScenarioGroupInfo> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public LlAptScenarioGroupInfo createFromParcel(Parcel parcel) {
            return new LlAptScenarioGroupInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public LlAptScenarioGroupInfo[] newArray(int i) {
            return new LlAptScenarioGroupInfo[i];
        }
    }

    public LlAptScenarioGroupInfo(int i, byte[] bArr) {
        this.a = i;
        this.b = bArr;
    }

    public static LlAptScenarioGroupInfo builder(byte[] bArr) {
        byte[] bArr2 = null;
        if (bArr.length < 1) {
            return null;
        }
        int i = bArr[0] & 255;
        int length = bArr.length - 1;
        if (length > 0) {
            bArr2 = new byte[length];
            System.arraycopy(bArr, 1, bArr2, 0, length);
        }
        return new LlAptScenarioGroupInfo(i, bArr2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getGroupNum() {
        return this.a;
    }

    public byte[] getGroupSenario() {
        return this.b;
    }

    public byte getSenario(int i) {
        byte[] bArr = this.b;
        if (bArr != null && i >= 0 && i <= bArr.length) {
            return bArr[i];
        }
        return (byte) 0;
    }

    public boolean isSenarioSupported() {
        byte[] bArr = this.b;
        return bArr != null && bArr.length > 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LlAptScenarioGroupInfo{");
        sb.append("\n\tgroupNum=" + this.a);
        sb.append("\n\tgroupScenario=" + DataConverter.bytes2Hex(this.b));
        sb.append("\n}");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeByteArray(this.b);
    }

    public LlAptScenarioGroupInfo(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.createByteArray();
    }
}
