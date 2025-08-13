package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/DialIndexReq.class */
public class DialIndexReq extends MixtureReq {
    public DialIndexReq() {
        super((byte) 117);
        this.subData = new byte[]{0};
    }

    private DialIndexReq(int index) {
        super((byte) 117);
        this.subData = new byte[]{1, (byte) index};
    }

    public static DialIndexReq getReadInstance() {
        return new DialIndexReq();
    }

    public static DialIndexReq getWriteInstance(int index) {
        return new DialIndexReq(index);
    }
}
