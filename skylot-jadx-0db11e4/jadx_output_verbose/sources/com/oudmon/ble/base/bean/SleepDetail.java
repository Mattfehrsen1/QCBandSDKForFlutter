package com.oudmon.ble.base.bean;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/bean/SleepDetail.class */
public class SleepDetail {
    private String deviceAddress;
    private String dateStr;
    private int interval;
    private String index_str;
    private String quality;
    private long unixTime;

    public long getUnixTime() {
        return this.unixTime;
    }

    public void setUnixTime(long unixTime) {
        this.unixTime = unixTime;
    }

    public String getDeviceAddress() {
        return this.deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public String getDateStr() {
        return this.dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public int getInterval() {
        return this.interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getIndex_str() {
        return this.index_str;
    }

    public void setIndex_str(String index_str) {
        this.index_str = index_str;
    }

    public String getQuality() {
        return this.quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String toString() {
        return "SleepDetail{deviceAddress='" + this.deviceAddress + "', dateStr='" + this.dateStr + "', interval=" + this.interval + ", index_str='" + this.index_str + "', quality='" + this.quality + "'}";
    }
}
