package com.realsil.customer.bbpro.multilink;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/multilink/MultilinkInfo.class */
public class MultilinkInfo implements Parcelable {
    public static final Parcelable.Creator<MultilinkInfo> CREATOR = new a();
    public int a;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/multilink/MultilinkInfo$a.class */
    public class a implements Parcelable.Creator<MultilinkInfo> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public MultilinkInfo createFromParcel(Parcel parcel) {
            return new MultilinkInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public MultilinkInfo[] newArray(int i) {
            return new MultilinkInfo[i];
        }
    }

    public MultilinkInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
    }

    public int getConnNum() {
        return this.a;
    }

    public void setConnNum(int i) {
        this.a = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MultilinkInfo {");
        sb.append(String.format(Locale.US, "\n\tconnNum=%d", Integer.valueOf(this.a)));
        sb.append("\n}");
        return sb.toString();
    }

    public MultilinkInfo(Parcel parcel) {
        this.a = parcel.readInt();
    }
}
