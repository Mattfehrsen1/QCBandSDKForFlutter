package com.oudmon.ble.base.communication.entity;

import java.util.Arrays;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/entity/BleSleepDetails.class */
public class BleSleepDetails {
    private int year;
    private int month;
    private int day;
    private int timeIndex;
    private int[] sleepQualities;

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTimeIndex() {
        return this.timeIndex;
    }

    public void setTimeIndex(int timeIndex) {
        this.timeIndex = timeIndex;
    }

    public int[] getSleepQualities() {
        return this.sleepQualities;
    }

    public void setSleepQualities(int[] sleepQualities) {
        this.sleepQualities = sleepQualities;
    }

    public String toString() {
        return "BleSleepDetails{year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", timeIndex=" + this.timeIndex + ", sleepQualities=" + Arrays.toString(this.sleepQualities) + '}';
    }
}
