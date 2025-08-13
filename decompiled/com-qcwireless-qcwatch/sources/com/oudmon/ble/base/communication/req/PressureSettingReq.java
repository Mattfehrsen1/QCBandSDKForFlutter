package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class PressureSettingReq extends MixtureReq {
    private PressureSettingReq(boolean z) {
        super((byte) 54);
        this.subData = new byte[]{2, z ? (byte) 1 : (byte) 0};
    }

    private PressureSettingReq() {
        super((byte) 54);
        this.subData = new byte[]{1};
    }

    public static PressureSettingReq getWriteInstance(boolean z) {
        return new PressureSettingReq(z);
    }

    public static PressureSettingReq getReadInstance() {
        return new PressureSettingReq();
    }
}
