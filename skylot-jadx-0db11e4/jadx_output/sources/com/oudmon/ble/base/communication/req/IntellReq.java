package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/IntellReq.class */
public class IntellReq extends MixtureReq {
    private boolean isEnable;
    private byte delaySecond;

    private IntellReq() {
        super((byte) 9);
        this.subData = new byte[]{1};
    }

    private IntellReq(boolean isEnable, byte delaySecond) {
        super((byte) 9);
        byte[] bArr = new byte[3];
        bArr[0] = 2;
        bArr[1] = (byte) (isEnable ? 1 : 2);
        bArr[2] = delaySecond;
        this.subData = bArr;
    }

    public static IntellReq getReadInstance() {
        return new IntellReq();
    }

    public static IntellReq getWriteInstance(boolean isEnable, byte delaySecond) {
        return new IntellReq(isEnable, delaySecond);
    }
}
