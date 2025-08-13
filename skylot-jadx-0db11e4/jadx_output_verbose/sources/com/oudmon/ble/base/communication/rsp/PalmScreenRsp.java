package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/PalmScreenRsp.class */
public class PalmScreenRsp extends MixtureRsp {
    private boolean isEnable;
    private boolean isLeft;
    private boolean needPalm;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] subData) {
        this.isEnable = subData[1] == 1;
        this.isLeft = (subData[2] & 1) == 1;
        this.needPalm = (subData[2] & 4) == 4;
    }

    public boolean isEnable() {
        return this.isEnable;
    }

    public boolean isLeft() {
        return this.isLeft;
    }

    public boolean isNeedPalm() {
        return this.needPalm;
    }

    public void setEnable(boolean enable) {
        this.isEnable = enable;
    }

    public void setLeft(boolean left) {
        this.isLeft = left;
    }
}
