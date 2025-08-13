package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/BloodOxygenSettingReq.class */
public class BloodOxygenSettingReq extends MixtureReq {
    private BloodOxygenSettingReq() {
        super((byte) 44);
        this.subData = new byte[]{1};
    }

    private BloodOxygenSettingReq(boolean isEnable) {
        super((byte) 44);
        byte[] bArr = new byte[2];
        bArr[0] = 2;
        bArr[1] = (byte) (isEnable ? 1 : 0);
        this.subData = bArr;
    }

    public static BloodOxygenSettingReq getReadInstance() {
        return new BloodOxygenSettingReq();
    }

    public static BloodOxygenSettingReq getWriteInstance(boolean isEnable) {
        return new BloodOxygenSettingReq(isEnable);
    }
}
