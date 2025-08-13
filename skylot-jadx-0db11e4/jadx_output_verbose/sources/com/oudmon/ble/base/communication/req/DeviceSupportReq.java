package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/DeviceSupportReq.class */
public class DeviceSupportReq extends MixtureReq {
    private DeviceSupportReq() {
        super((byte) 60);
    }

    public static DeviceSupportReq getReadInstance() {
        return new DeviceSupportReq();
    }
}
