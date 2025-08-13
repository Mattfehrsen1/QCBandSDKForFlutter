package com.oudmon.ble.base.communication.entity;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/entity/BleSport.class */
public class BleSport {
    private long startTime;
    private long duration;
    private float calories;
    private float distance;
    private int sportType;
    private int stepCount;
    private int[] rateArray;

    public int[] getRateValue() {
        return this.rateArray;
    }

    public void setRateValue(int[] rateValue) {
        this.rateArray = rateValue;
    }

    public int getStepCount() {
        return this.stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public int getSportType() {
        return this.sportType;
    }

    public void setSportType(int sportType) {
        this.sportType = sportType;
    }

    public float getDistance() {
        return this.distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getCalories() {
        return this.calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
