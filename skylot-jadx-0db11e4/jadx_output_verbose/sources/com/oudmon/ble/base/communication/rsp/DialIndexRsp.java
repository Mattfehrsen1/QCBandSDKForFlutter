package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/DialIndexRsp.class */
public class DialIndexRsp extends BaseRspCmd {
    private int index = 0;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.index = data[1];
        return false;
    }

    public int getIndex() {
        return this.index;
    }
}
