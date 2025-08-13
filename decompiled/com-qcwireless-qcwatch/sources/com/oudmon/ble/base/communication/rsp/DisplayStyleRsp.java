package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes3.dex */
public class DisplayStyleRsp extends MixtureRsp {
    private int styleIndex;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] bArr) {
        this.styleIndex = bArr[1];
    }

    public int getStyleIndex() {
        return this.styleIndex;
    }

    public void setStyleIndex(int i) {
        this.styleIndex = i;
    }
}
