package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/DegreeSwitchRsp.class */
public class DegreeSwitchRsp extends MixtureRsp {
    private boolean isEnable = false;
    private boolean isCelsius = false;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] subData) {
        this.isEnable = subData[1] == 1;
        this.isCelsius = subData[2] == 1;
    }

    public boolean isEnable() {
        return this.isEnable;
    }

    public void setEnable(boolean enable) {
        this.isEnable = enable;
    }

    public boolean isCelsius() {
        return this.isCelsius;
    }

    public void setCelsius(boolean celsius) {
        this.isCelsius = celsius;
    }

    public String toString() {
        return "DegreeSwitchRsp{isEnable=" + this.isEnable + ", isCelsius=" + this.isCelsius + '}';
    }
}
