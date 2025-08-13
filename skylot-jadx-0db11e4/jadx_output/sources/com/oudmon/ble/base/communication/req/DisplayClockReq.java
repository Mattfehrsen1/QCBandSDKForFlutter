package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/DisplayClockReq.class */
public class DisplayClockReq extends MixtureReq {
    private DisplayClockReq() {
        super((byte) 18);
        this.subData = new byte[]{1};
    }

    private DisplayClockReq(boolean enable) {
        super((byte) 18);
        byte[] bArr = new byte[2];
        bArr[0] = 2;
        bArr[1] = (byte) (enable ? 1 : 2);
        this.subData = bArr;
    }

    public static DisplayClockReq getReadInstance() {
        return new DisplayClockReq();
    }

    public static DisplayClockReq getWriteInstance(boolean enable) {
        return new DisplayClockReq(enable);
    }
}
