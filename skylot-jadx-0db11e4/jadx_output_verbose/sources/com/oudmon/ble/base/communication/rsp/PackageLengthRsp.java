package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/PackageLengthRsp.class */
public class PackageLengthRsp extends BaseRspCmd {
    public int mData = 0;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.mData = data[0] & 255;
        return false;
    }
}
