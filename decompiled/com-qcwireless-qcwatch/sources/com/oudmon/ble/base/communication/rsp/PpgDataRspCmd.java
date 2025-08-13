package com.oudmon.ble.base.communication.rsp;

import com.qcwireless.qc_utils.bytes.DataTransferUtils;

/* loaded from: classes3.dex */
public class PpgDataRspCmd extends BaseRspCmd {
    public int mPpgValue;
    public int mRate;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.mRate = bArr[0];
        this.mPpgValue = DataTransferUtils.bytesToInt(bArr, 1);
        return false;
    }
}
