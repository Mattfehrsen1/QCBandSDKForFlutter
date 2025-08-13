package com.oudmon.ble.base.communication.entity;

/* loaded from: classes3.dex */
public class BleStepTotal {
    private int calorie;
    private int day;
    private int daysAgo;
    private int month;
    private int runningSteps;
    private int sleepDuration;
    private int sportDuration;
    private int totalSteps;
    private int walkDistance;
    private int year;

    public String toString() {
        return "BleStepTotal{ daysAgo=" + this.daysAgo + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", totalSteps=" + this.totalSteps + ", runningSteps=" + this.runningSteps + ", calorie=" + this.calorie + ", walkDistance=" + this.walkDistance + ", sportDuration=" + this.sportDuration + ", sleepDuration=" + this.sleepDuration + " }";
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public BleStepTotal m241clone() {
        BleStepTotal bleStepTotal = new BleStepTotal();
        bleStepTotal.setDaysAgo(this.daysAgo);
        bleStepTotal.setYear(this.year);
        bleStepTotal.setMonth(this.month);
        bleStepTotal.setDay(this.day);
        bleStepTotal.setTotalSteps(this.totalSteps);
        bleStepTotal.setRunningSteps(this.runningSteps);
        bleStepTotal.setCalorie(this.calorie);
        bleStepTotal.setSportDuration(this.sportDuration);
        bleStepTotal.setSleepDuration(this.sleepDuration);
        return bleStepTotal;
    }

    public int getDaysAgo() {
        return this.daysAgo;
    }

    public void setDaysAgo(int i) {
        this.daysAgo = i;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int i) {
        this.day = i;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public int getTotalSteps() {
        return this.totalSteps;
    }

    public void setTotalSteps(int i) {
        this.totalSteps = i;
    }

    public int getRunningSteps() {
        return this.runningSteps;
    }

    public void setRunningSteps(int i) {
        this.runningSteps = i;
    }

    public int getCalorie() {
        return this.calorie;
    }

    public void setCalorie(int i) {
        this.calorie = i;
    }

    public int getWalkDistance() {
        return this.walkDistance;
    }

    public void setWalkDistance(int i) {
        this.walkDistance = i;
    }

    public int getSportDuration() {
        return this.sportDuration;
    }

    public void setSportDuration(int i) {
        this.sportDuration = i;
    }

    public int getSleepDuration() {
        return this.sleepDuration;
    }

    public void setSleepDuration(int i) {
        this.sleepDuration = i;
    }
}
