package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes3.dex */
public class AgpsRsp extends MixtureRsp {
    private boolean mEnable;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] bArr) {
        this.mEnable = bArr[1] == 1;
    }

    public boolean isEnable() {
        return this.mEnable;
    }
}
