package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/DisplayClockRsp.class */
public class DisplayClockRsp extends MixtureRsp {
    private boolean isClock;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] subData) {
        this.isClock = subData[1] == 1;
    }

    public boolean isClock() {
        return this.isClock;
    }

    public void setClock(boolean clock) {
        this.isClock = clock;
    }
}
