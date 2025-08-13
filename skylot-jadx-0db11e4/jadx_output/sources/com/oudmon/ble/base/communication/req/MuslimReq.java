package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/MuslimReq.class */
public class MuslimReq extends MixtureReq {
    private byte index;

    public MuslimReq(byte index) {
        super((byte) 122);
        this.subData = new byte[]{1, index};
    }

    public MuslimReq(boolean clear) {
        super((byte) 122);
        this.subData = new byte[]{2, 1};
    }

    public static MuslimReq getWriteInstance(boolean clear) {
        return new MuslimReq(clear);
    }
}
