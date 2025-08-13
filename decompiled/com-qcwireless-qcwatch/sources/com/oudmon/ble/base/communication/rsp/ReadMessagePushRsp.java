package com.oudmon.ble.base.communication.rsp;

import com.qcwireless.qc_utils.bytes.DataTransferUtils;

/* loaded from: classes3.dex */
public class ReadMessagePushRsp extends BaseRspCmd {
    private int deviceSupport1 = 0;
    private int deviceSupport2 = 0;
    private int deviceSupport3 = 0;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.deviceSupport1 = DataTransferUtils.bytesToInt(bArr, 2);
        this.deviceSupport2 = DataTransferUtils.bytesToInt(bArr, 4);
        this.deviceSupport3 = DataTransferUtils.bytesToInt(bArr, 6);
        return false;
    }

    public int getDeviceSupport1() {
        return this.deviceSupport1;
    }

    public int getDeviceSupport2() {
        return this.deviceSupport2;
    }

    public int getDeviceSupport3() {
        return this.deviceSupport3;
    }
}
