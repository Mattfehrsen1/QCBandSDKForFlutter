package com.realsil.sdk.bbpro.multilink;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

/* loaded from: classes3.dex */
public class MultilinkInfo implements Parcelable {
    public static final Parcelable.Creator<MultilinkInfo> CREATOR = new a();
    public int a;

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

    public int getConnNum() {
        return this.a;
    }

    public void setConnNum(int i) {
        this.a = i;
    }

    public String toString() {
        return "MultilinkInfo {" + String.format(Locale.US, "\n\tconnNum=%d", Integer.valueOf(this.a)) + "\n}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
    }

    public MultilinkInfo(Parcel parcel) {
        this.a = parcel.readInt();
    }
}
