package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/TimeFormatRsp.class */
public class TimeFormatRsp extends MixtureRsp {
    private boolean is24;
    private boolean isMetric;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] subData) {
        this.is24 = subData[1] == 0;
        this.isMetric = subData[2] == 0;
    }

    public boolean is24() {
        return this.is24;
    }

    public boolean isMetric() {
        return this.isMetric;
    }

    public String toString() {
        return "TimeFormatRsp{is24=" + this.is24 + '}';
    }
}
