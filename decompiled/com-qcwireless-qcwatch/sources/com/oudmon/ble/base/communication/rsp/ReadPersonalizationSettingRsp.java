package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes3.dex */
public class ReadPersonalizationSettingRsp extends BaseRspCmd {
    private int mClockSetting;
    private int mPowerOffSetting;
    private int mPowerOnSetting;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.mClockSetting = bArr[0];
        this.mPowerOnSetting = bArr[1];
        this.mPowerOffSetting = bArr[2];
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
