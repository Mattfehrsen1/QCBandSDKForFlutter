package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.util.DataTransferUtils;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/PpgDataRspCmd.class */
public class PpgDataRspCmd extends BaseRspCmd {
    public int mRate;
    public int mPpgValue;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.mRate = data[0];
        this.mPpgValue = DataTransferUtils.bytesToInt(data, 1);
        return false;
    }
}
