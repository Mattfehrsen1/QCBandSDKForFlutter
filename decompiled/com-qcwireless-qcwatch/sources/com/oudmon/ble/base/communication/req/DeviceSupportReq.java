package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class DeviceSupportReq extends MixtureReq {
    private DeviceSupportReq() {
        super((byte) 60);
    }

    public static DeviceSupportReq getReadInstance() {
        return new DeviceSupportReq();
    }
}
