package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes3.dex */
public class DisplayOrientationRsp extends MixtureRsp {
    private boolean isLeft;
    private boolean isPortrait;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] bArr) {
        this.isPortrait = bArr[1] == 1;
        this.isLeft = bArr[2] == 1;
    }

    public boolean isPortrait() {
        return this.isPortrait;
    }

    public boolean isLeft() {
        return this.isLeft;
    }

    public void setPortrait(boolean z) {
        this.isPortrait = z;
    }

    public void setLeft(boolean z) {
        this.isLeft = z;
    }
}
