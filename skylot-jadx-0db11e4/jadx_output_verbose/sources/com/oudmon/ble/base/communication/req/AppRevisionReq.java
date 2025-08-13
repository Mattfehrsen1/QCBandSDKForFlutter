package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/AppRevisionReq.class */
public class AppRevisionReq extends MixtureReq {
    private AppRevisionReq(int type) {
        super((byte) -95);
        this.subData = new byte[]{(byte) type};
    }

    public static AppRevisionReq getWriteInstance(int type) {
        return new AppRevisionReq(type);
    }
}
