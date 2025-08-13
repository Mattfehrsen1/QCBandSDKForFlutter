package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/HRVSettingRsp.class */
public class HRVSettingRsp extends MixtureRsp {
    private boolean isEnable;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] subData) {
        this.isEnable = subData[1] == 1;
    }

    public boolean isEnable() {
        return this.isEnable;
    }
}
