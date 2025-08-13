package com.baidu.location;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
class b implements Parcelable.Creator<BDLocation> {
    b() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public BDLocation createFromParcel(Parcel parcel) {
        return new BDLocation(parcel, null);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public BDLocation[] newArray(int i) {
        return new BDLocation[i];
    }
}
