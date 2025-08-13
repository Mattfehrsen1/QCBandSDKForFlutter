package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/HealthEcgRsp.class */
public class HealthEcgRsp extends BaseRspCmd {
    public int mStatus;
    public int mEcgInterval;
    public int mPpgInterval;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        if (data.length >= 3) {
            this.mStatus = data[0];
            this.mEcgInterval = data[1];
            this.mPpgInterval = data[2];
            return false;
        }
        return false;
    }
}
