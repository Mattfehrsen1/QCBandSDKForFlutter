package com.oudmon.ble.base.communication.bigData;

import java.util.List;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/bigData/BloodOxygenEntity.class */
public class BloodOxygenEntity {
    private String dateStr;
    private List<Integer> minArray;
    private List<Integer> maxArray;
    private long unix_time;

    public String getDateStr() {
        return this.dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public List<Integer> getMinArray() {
        return this.minArray;
    }

    public void setMinArray(List<Integer> minArray) {
        this.minArray = minArray;
    }

    public List<Integer> getMaxArray() {
        return this.maxArray;
    }

    public void setMaxArray(List<Integer> maxArray) {
        this.maxArray = maxArray;
    }

    public long getUnix_time() {
        return this.unix_time;
    }

    public void setUnix_time(long unix_time) {
        this.unix_time = unix_time;
    }
}
