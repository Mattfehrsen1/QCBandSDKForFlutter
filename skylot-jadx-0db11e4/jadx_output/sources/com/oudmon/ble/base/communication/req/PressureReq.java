package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/PressureReq.class */
public class PressureReq extends MixtureReq {
    private byte index;

    public PressureReq(byte index) {
        super((byte) 55);
        this.subData = new byte[]{index};
    }
}
