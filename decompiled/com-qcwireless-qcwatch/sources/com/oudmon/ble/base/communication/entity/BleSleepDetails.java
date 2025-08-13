package com.oudmon.ble.base.communication.entity;

import java.util.Arrays;

/* loaded from: classes3.dex */
public class BleSleepDetails {
    private int day;
    private int month;
    private int[] sleepQualities;
    private int timeIndex;
    private int year;

    public int getYear() {
        return this.year;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int i) {
        this.day = i;
    }

    public int getTimeIndex() {
        return this.timeIndex;
    }

    public void setTimeIndex(int i) {
        this.timeIndex = i;
    }

    public int[] getSleepQualities() {
        return this.sleepQualities;
    }

    public void setSleepQualities(int[] iArr) {
        this.sleepQualities = iArr;
    }

    public String toString() {
        return "BleSleepDetails{year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", timeIndex=" + this.timeIndex + ", sleepQualities=" + Arrays.toString(this.sleepQualities) + '}';
    }
}
