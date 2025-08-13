package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/DeviceNotifyRsp.class */
public class DeviceNotifyRsp extends BaseRspCmd {
    private int dataType;
    private byte[] loadData;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.dataType = data[0];
        this.loadData = data;
        return false;
    }

    public int getDataType() {
        return this.dataType;
    }

    public byte[] getLoadData() {
        return this.loadData;
    }
}
