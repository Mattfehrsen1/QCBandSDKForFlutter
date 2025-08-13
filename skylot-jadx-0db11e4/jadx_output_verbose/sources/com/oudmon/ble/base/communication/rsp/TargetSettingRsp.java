package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/TargetSettingRsp.class */
public class TargetSettingRsp extends MixtureRsp {
    private int mStep;
    private int mCalorie;
    private int mDistance;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] subData) {
        this.mStep = subData[1];
        this.mCalorie = subData[2];
        this.mDistance = subData[3];
    }

    public int getStep() {
        return this.mStep;
    }

    public int getCalorie() {
        return this.mCalorie;
    }

    public int getDistance() {
        return this.mDistance;
    }
}
