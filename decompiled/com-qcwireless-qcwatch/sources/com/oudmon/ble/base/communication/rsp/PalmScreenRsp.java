package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes3.dex */
public class PalmScreenRsp extends MixtureRsp {
    private boolean isEnable;
    private boolean isLeft;
    private boolean needPalm;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] bArr) {
        this.isEnable = bArr[1] == 1;
        this.isLeft = (bArr[2] & 1) == 1;
        this.needPalm = (bArr[2] & 4) == 4;
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

    public void setEnable(boolean z) {
        this.isEnable = z;
    }

    public void setLeft(boolean z) {
        this.isLeft = z;
    }
}
