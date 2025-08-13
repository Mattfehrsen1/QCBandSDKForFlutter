package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/RealTimeHeartRateRsp.class */
public class RealTimeHeartRateRsp extends BaseRspCmd {
    private int heart;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.heart = data[0];
        return false;
    }

    public int getHeart() {
        return this.heart;
    }
}
