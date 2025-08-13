package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/IntellRsp.class */
public class IntellRsp extends MixtureRsp {
    private boolean isEnable;
    private byte delaySecond;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] subData) {
        this.isEnable = subData[1] == 1;
        this.delaySecond = subData[2];
    }

    public boolean isEnable() {
        return this.isEnable;
    }

    public byte getDelaySecond() {
        return this.delaySecond;
    }
}
