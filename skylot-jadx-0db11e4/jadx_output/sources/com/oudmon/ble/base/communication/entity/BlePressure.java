package com.oudmon.ble.base.communication.entity;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/entity/BlePressure.class */
public class BlePressure {
    public long time;
    public int dbp;
    public int sbp;

    public BlePressure(long time, int sbp, int dbp) {
        this.time = time;
        this.sbp = sbp;
        this.dbp = dbp;
    }
}
