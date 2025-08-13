package com.oudmon.ble.base.communication.entity;

/* loaded from: classes3.dex */
public class BleSport {
    private float calories;
    private float distance;
    private long duration;
    private int[] rateArray;
    private int sportType;
    private long startTime;
    private int stepCount;

    public int[] getRateValue() {
        return this.rateArray;
    }

    public void setRateValue(int[] iArr) {
        this.rateArray = iArr;
    }

    public int getStepCount() {
        return this.stepCount;
    }

    public void setStepCount(int i) {
        this.stepCount = i;
    }

    public int getSportType() {
        return this.sportType;
    }

    public void setSportType(int i) {
        this.sportType = i;
    }

    public float getDistance() {
        return this.distance;
    }

    public void setDistance(float f) {
        this.distance = f;
    }

    public float getCalories() {
        return this.calories;
    }

    public void setCalories(float f) {
        this.calories = f;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }
}
