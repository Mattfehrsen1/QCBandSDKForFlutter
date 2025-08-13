package com.oudmon.ble.base.communication.entity;

/* loaded from: classes3.dex */
public class BlePressure {
    public int dbp;
    public int sbp;
    public long time;

    public BlePressure(long j, int i, int i2) {
        this.time = j;
        this.sbp = i;
        this.dbp = i2;
    }
}
