package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/HrvSettingReq.class */
public class HrvSettingReq extends MixtureReq {
    private HrvSettingReq() {
        super((byte) 56);
        this.subData = new byte[]{1};
    }

    public HrvSettingReq(boolean isEnable) {
        super((byte) 56);
        byte[] bArr = new byte[2];
        bArr[0] = 2;
        bArr[1] = (byte) (isEnable ? 1 : 0);
        this.subData = bArr;
    }

    public static HrvSettingReq getReadInstance() {
        return new HrvSettingReq();
    }
}
