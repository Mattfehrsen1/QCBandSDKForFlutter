package com.baidu.location;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
class h implements Parcelable.Creator<PoiRegion> {
    h() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public PoiRegion createFromParcel(Parcel parcel) {
        return new PoiRegion(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public PoiRegion[] newArray(int i) {
        return new PoiRegion[i];
    }
}
