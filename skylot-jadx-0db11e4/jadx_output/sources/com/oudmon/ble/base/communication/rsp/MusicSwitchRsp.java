package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/MusicSwitchRsp.class */
public class MusicSwitchRsp extends BaseRspCmd {
    private int action;
    private boolean enable;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.action = data[0];
        this.enable = data[1] == 1;
        return false;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public int getAction() {
        return this.action;
    }
}
