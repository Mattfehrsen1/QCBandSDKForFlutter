package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes3.dex */
public class BatteryRsp extends BaseRspCmd {
    private int batteryValue;
    private boolean charging;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.batteryValue = bArr[0];
        this.charging = bArr[1] == 1;
        return false;
    }

    public int getBatteryValue() {
        return this.batteryValue;
    }

    public boolean isCharging() {
        return this.charging;
    }
}
