package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes3.dex */
public class DeviceNotifyRsp extends BaseRspCmd {
    private int dataType;
    private byte[] loadData;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.dataType = bArr[0];
        this.loadData = bArr;
        return false;
    }

    public int getDataType() {
        return this.dataType;
    }

    public byte[] getLoadData() {
        return this.loadData;
    }
}
