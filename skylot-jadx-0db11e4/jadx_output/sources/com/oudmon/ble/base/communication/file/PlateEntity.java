package com.oudmon.ble.base.communication.file;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/file/PlateEntity.class */
public class PlateEntity {
    public boolean mDelete;
    public String mPlateName;

    public PlateEntity(boolean mDelete, String mPlateName) {
        this.mDelete = false;
        this.mPlateName = "";
        this.mDelete = mDelete;
        this.mPlateName = mPlateName;
    }

    public String toString() {
        return "PlateEntity{mDelete=" + this.mDelete + ", mPlateName='" + this.mPlateName + "'}";
    }
}
