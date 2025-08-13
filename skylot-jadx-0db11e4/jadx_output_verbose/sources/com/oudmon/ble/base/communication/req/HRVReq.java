package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/HRVReq.class */
public class HRVReq extends MixtureReq {
    private byte index;

    public HRVReq(byte index) {
        super((byte) 57);
        this.subData = new byte[]{index};
    }
}
