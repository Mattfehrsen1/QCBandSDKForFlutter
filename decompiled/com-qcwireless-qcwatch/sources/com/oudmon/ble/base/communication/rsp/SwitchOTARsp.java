package com.oudmon.ble.base.communication.rsp;

import com.qcwireless.qc_utils.bytes.DataTransferUtils;

/* loaded from: classes3.dex */
public class SwitchOTARsp extends BaseRspCmd {
    private short stateMask;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.stateMask = DataTransferUtils.bytesToShort(bArr, 0);
        return false;
    }

    public short getStateMask() {
        return this.stateMask;
    }

    public void setStateMask(short s) {
        this.stateMask = s;
    }
}
