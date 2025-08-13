package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.util.DataTransferUtils;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/SwitchOTARsp.class */
public class SwitchOTARsp extends BaseRspCmd {
    private short stateMask;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.stateMask = DataTransferUtils.bytesToShort(data, 0);
        return false;
    }

    public short getStateMask() {
        return this.stateMask;
    }

    public void setStateMask(short stateMask) {
        this.stateMask = stateMask;
    }
}
