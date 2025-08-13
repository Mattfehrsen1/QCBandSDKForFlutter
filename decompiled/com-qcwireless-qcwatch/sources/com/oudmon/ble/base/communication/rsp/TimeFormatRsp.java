package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes3.dex */
public class TimeFormatRsp extends MixtureRsp {
    private boolean is24;
    private boolean isMetric;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] bArr) {
        this.is24 = bArr[1] == 0;
        this.isMetric = bArr[2] == 0;
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
