package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/PhoneNotifyRsp.class */
public class PhoneNotifyRsp extends BaseRspCmd {
    private int action = 0;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.action = data[0] & 255;
        return false;
    }

    public int getAction() {
        return this.action;
    }

    public boolean isReject() {
        return this.action == 1;
    }
}
