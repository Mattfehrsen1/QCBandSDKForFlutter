package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes3.dex */
public class PackageLengthRsp extends BaseRspCmd {
    public int mData = 0;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.mData = bArr[0] & 255;
        return false;
    }
}
