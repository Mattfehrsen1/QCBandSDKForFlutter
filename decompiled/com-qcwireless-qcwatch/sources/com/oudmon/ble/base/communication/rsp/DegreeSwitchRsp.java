package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes3.dex */
public class DegreeSwitchRsp extends MixtureRsp {
    private boolean isEnable = false;
    private boolean isCelsius = false;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] bArr) {
        this.isEnable = bArr[1] == 1;
        this.isCelsius = bArr[2] == 1;
    }

    public boolean isEnable() {
        return this.isEnable;
    }

    public void setEnable(boolean z) {
        this.isEnable = z;
    }

    public boolean isCelsius() {
        return this.isCelsius;
    }

    public void setCelsius(boolean z) {
        this.isCelsius = z;
    }

    public String toString() {
        return "DegreeSwitchRsp{isEnable=" + this.isEnable + ", isCelsius=" + this.isCelsius + '}';
    }
}
