package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/HeartRateSettingReq.class */
public class HeartRateSettingReq extends MixtureReq {
    private HeartRateSettingReq() {
        super((byte) 22);
        this.subData = new byte[]{1};
    }

    private HeartRateSettingReq(boolean isEnable, int interval) {
        super((byte) 22);
        byte[] bArr = new byte[3];
        bArr[0] = 2;
        bArr[1] = (byte) (isEnable ? 1 : 2);
        bArr[2] = (byte) interval;
        this.subData = bArr;
    }

    public static HeartRateSettingReq getReadInstance() {
        return new HeartRateSettingReq();
    }

    public static HeartRateSettingReq getWriteInstance(boolean isEnable, int interval) {
        return new HeartRateSettingReq(isEnable, interval);
    }
}
