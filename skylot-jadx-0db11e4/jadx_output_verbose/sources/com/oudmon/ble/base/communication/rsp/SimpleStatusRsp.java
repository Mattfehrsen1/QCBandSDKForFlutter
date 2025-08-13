package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/SimpleStatusRsp.class */
public class SimpleStatusRsp extends BaseRspCmd {
    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        return false;
    }
}
