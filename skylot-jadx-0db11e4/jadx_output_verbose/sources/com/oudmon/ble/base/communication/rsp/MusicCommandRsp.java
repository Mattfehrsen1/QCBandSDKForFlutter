package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/MusicCommandRsp.class */
public class MusicCommandRsp extends BaseRspCmd {
    private int action = 0;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.action = data[0] & 255;
        return false;
    }

    public int getAction() {
        return this.action;
    }

    public String toString() {
        return "MusicCommandRsp{action=" + this.action + ", status=" + this.status + '}';
    }
}
