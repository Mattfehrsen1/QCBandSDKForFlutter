package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/BatterySavingRsp.class */
public class BatterySavingRsp extends BaseRspCmd {
    private boolean open;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.open = data[1] == 1;
        return false;
    }

    public boolean isOpen() {
        return this.open;
    }
}
