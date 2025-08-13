package com.oudmon.ble.base.communication.entity;

/* loaded from: classes3.dex */
public class BleStepDetails {
    private int day;
    private int month;
    private int year;
    private int timeIndex = 0;
    private int calorie = 0;
    private int walkSteps = 0;
    private int distance = 0;
    private int runSteps = 0;

    public String toString() {
        return "BleStepDetails{year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", timeIndex=" + this.timeIndex + ", calorie=" + this.calorie + ", walkSteps=" + this.walkSteps + ", distance=" + this.distance + ", runSteps=" + this.runSteps + '}';
    }

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

    public int getCalorie() {
        return this.calorie;
    }

    public void setCalorie(int i) {
        this.calorie = i;
    }

    public int getWalkSteps() {
        return this.walkSteps;
    }

    public void setWalkSteps(int i) {
        this.walkSteps = i;
    }

    public int getRunSteps() {
        return this.runSteps;
    }

    public void setRunSteps(int i) {
        this.runSteps = i;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int i) {
        this.distance = i;
    }
}
