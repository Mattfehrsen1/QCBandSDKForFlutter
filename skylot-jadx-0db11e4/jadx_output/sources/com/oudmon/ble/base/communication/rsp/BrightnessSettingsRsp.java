package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/BrightnessSettingsRsp.class */
public class BrightnessSettingsRsp extends MixtureRsp {
    private int level = 0;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] subData) {
        this.level = subData[1];
    }

    public int getLevel() {
        return this.level;
    }
}
