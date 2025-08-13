package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes3.dex */
public class RealTimeHeartRateRsp extends BaseRspCmd {
    private int heart;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.heart = bArr[0];
        return false;
    }

    public int getHeart() {
        return this.heart;
    }
}
