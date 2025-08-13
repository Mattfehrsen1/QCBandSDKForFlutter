package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/ReadPersonalizationSettingRsp.class */
public class ReadPersonalizationSettingRsp extends BaseRspCmd {
    private int mClockSetting;
    private int mPowerOnSetting;
    private int mPowerOffSetting;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.mClockSetting = data[0];
        this.mPowerOnSetting = data[1];
        this.mPowerOffSetting = data[2];
        return false;
    }

    public int getClockSetting() {
        return this.mClockSetting;
    }

    public int getPowerOnSetting() {
        return this.mPowerOnSetting;
    }

    public int getPowerOffSetting() {
        return this.mPowerOffSetting;
    }

    public String toString() {
        return "ReadPersonalizationSettingRsp{status=" + this.status + ", mClockSetting=" + this.mClockSetting + ", mPowerOnSetting=" + this.mPowerOnSetting + ", mPowerOffSetting=" + this.mPowerOffSetting + '}';
    }
}
