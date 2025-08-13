package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/DisplayOrientationReq.class */
public class DisplayOrientationReq extends MixtureReq {
    private DisplayOrientationReq() {
        super((byte) 41);
        this.subData = new byte[]{1};
    }

    private DisplayOrientationReq(boolean isPortrait, boolean isLeft) {
        super((byte) 41);
        byte[] bArr = new byte[3];
        bArr[0] = 2;
        bArr[1] = (byte) (isPortrait ? 1 : 2);
        bArr[2] = (byte) (isPortrait ? 0 : isLeft ? 1 : 2);
        this.subData = bArr;
    }

    public static DisplayOrientationReq getReadInstance() {
        return new DisplayOrientationReq();
    }

    public static DisplayOrientationReq getWriteInstance(boolean isPortrait, boolean isLeft) {
        return new DisplayOrientationReq(isPortrait, isLeft);
    }
}
