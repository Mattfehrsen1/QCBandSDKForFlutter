package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/AgpsRsp.class */
public class AgpsRsp extends MixtureRsp {
    private boolean mEnable;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] subData) {
        this.mEnable = subData[1] == 1;
    }

    public boolean isEnable() {
        return this.mEnable;
    }
}
