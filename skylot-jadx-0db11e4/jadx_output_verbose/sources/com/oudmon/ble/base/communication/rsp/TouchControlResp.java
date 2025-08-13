package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/TouchControlResp.class */
public class TouchControlResp extends BaseRspCmd {
    private int appType;
    private int strength;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.appType = data[2];
        this.strength = data[3];
        return false;
    }

    public int getAppType() {
        return this.appType;
    }

    public int getStrength() {
        return this.strength;
    }
}
