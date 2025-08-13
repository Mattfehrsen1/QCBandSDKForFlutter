package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/PalmScreenReq.class */
public class PalmScreenReq extends MixtureReq {
    private PalmScreenReq() {
        super((byte) 5);
        this.subData = new byte[]{1};
    }

    private PalmScreenReq(boolean isEnable, boolean isLeft, boolean needPalm) {
        super((byte) 5);
        byte[] bArr = new byte[3];
        bArr[0] = 2;
        bArr[1] = (byte) (isEnable ? 1 : 2);
        bArr[2] = (byte) ((isLeft ? 1 : 2) | (needPalm ? 4 : 0));
        this.subData = bArr;
    }

    public static PalmScreenReq getReadInstance() {
        return new PalmScreenReq();
    }

    public static PalmScreenReq getWriteInstance(boolean isEnable, boolean isLeft) {
        return new PalmScreenReq(isEnable, isLeft, true);
    }
}
