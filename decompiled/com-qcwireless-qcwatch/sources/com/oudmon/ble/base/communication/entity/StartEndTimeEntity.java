package com.oudmon.ble.base.communication.entity;

/* loaded from: classes3.dex */
public class StartEndTimeEntity {
    private int endHour;
    private int endMinute;
    private int startHour;
    private int startMinute;

    public StartEndTimeEntity(int i, int i2, int i3, int i4) {
        this.startHour = i;
        this.startMinute = i2;
        this.endHour = i3;
        this.endMinute = i4;
    }

    public int getStartHour() {
        return this.startHour;
    }

    public int getStartMinute() {
        return this.startMinute;
    }

    public int getEndHour() {
        return this.endHour;
    }

    public int getEndMinute() {
        return this.endMinute;
    }

    public void setStartHour(int i) {
        this.startHour = i;
    }

    public void setStartMinute(int i) {
        this.startMinute = i;
    }

    public void setEndHour(int i) {
        this.endHour = i;
    }

    public void setEndMinute(int i) {
        this.endMinute = i;
    }
}
