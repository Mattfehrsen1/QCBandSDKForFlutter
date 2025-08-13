package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes3.dex */
public class HealthEcgRsp extends BaseRspCmd {
    public int mEcgInterval;
    public int mPpgInterval;
    public int mStatus;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        if (bArr.length >= 3) {
            this.mStatus = bArr[0];
            this.mEcgInterval = bArr[1];
            this.mPpgInterval = bArr[2];
        }
        return false;
    }
}
