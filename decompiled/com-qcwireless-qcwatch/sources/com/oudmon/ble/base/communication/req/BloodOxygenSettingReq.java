package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class BloodOxygenSettingReq extends MixtureReq {
    private BloodOxygenSettingReq() {
        super((byte) 44);
        this.subData = new byte[]{1};
    }

    private BloodOxygenSettingReq(boolean z) {
        super((byte) 44);
        this.subData = new byte[]{2, z ? (byte) 1 : (byte) 0};
    }

    public static BloodOxygenSettingReq getReadInstance() {
        return new BloodOxygenSettingReq();
    }

    public static BloodOxygenSettingReq getWriteInstance(boolean z) {
        return new BloodOxygenSettingReq(z);
    }
}
