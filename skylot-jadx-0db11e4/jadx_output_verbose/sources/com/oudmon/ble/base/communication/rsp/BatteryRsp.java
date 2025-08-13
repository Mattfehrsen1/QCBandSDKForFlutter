package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/BatteryRsp.class */
public class BatteryRsp extends BaseRspCmd {
    private int batteryValue;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.batteryValue = data[0];
        return false;
    }

    public int getBatteryValue() {
        return this.batteryValue;
    }
}
