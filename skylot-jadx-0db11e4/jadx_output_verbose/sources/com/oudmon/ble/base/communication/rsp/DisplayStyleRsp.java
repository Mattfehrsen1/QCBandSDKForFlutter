package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/DisplayStyleRsp.class */
public class DisplayStyleRsp extends MixtureRsp {
    private int styleIndex;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] subData) {
        this.styleIndex = subData[1];
    }

    public int getStyleIndex() {
        return this.styleIndex;
    }

    public void setStyleIndex(int index) {
        this.styleIndex = index;
    }
}
