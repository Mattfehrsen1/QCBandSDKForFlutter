package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/PressureSettingReq.class */
public class PressureSettingReq extends MixtureReq {
    private PressureSettingReq(boolean open) {
        super((byte) 54);
        byte[] bArr = new byte[2];
        bArr[0] = 2;
        bArr[1] = (byte) (open ? 1 : 0);
        this.subData = bArr;
    }

    private PressureSettingReq() {
        super((byte) 54);
        this.subData = new byte[]{1};
    }

    public static PressureSettingReq getWriteInstance(boolean open) {
        return new PressureSettingReq(open);
    }

    public static PressureSettingReq getReadInstance() {
        return new PressureSettingReq();
    }
}
