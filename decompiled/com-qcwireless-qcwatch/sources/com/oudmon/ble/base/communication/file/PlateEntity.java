package com.oudmon.ble.base.communication.file;

/* loaded from: classes3.dex */
public class PlateEntity {
    public boolean mDelete;
    public String mPlateName;

    public PlateEntity(boolean z, String str) {
        this.mDelete = false;
        this.mPlateName = "";
        this.mDelete = z;
        this.mPlateName = str;
    }

    public String toString() {
        return "PlateEntity{mDelete=" + this.mDelete + ", mPlateName='" + this.mPlateName + "'}";
    }
}
