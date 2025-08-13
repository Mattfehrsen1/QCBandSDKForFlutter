package com.oudmon.ble.base.communication.entity;

/* loaded from: classes3.dex */
public class AlarmEntity {
    private int alarmIndex;
    private int enable;
    private int hour;
    private int minute;
    private byte weekMask;

    public AlarmEntity(int i, int i2, int i3, int i4, byte b) {
        this.alarmIndex = i;
        this.enable = i2;
        this.hour = i3;
        this.minute = i4;
        this.weekMask = b;
    }

    public AlarmEntity(int i, boolean z, int i2, int i3, byte b) {
        this.alarmIndex = i;
        if (z) {
            this.enable = 1;
        } else {
            this.enable = 0;
        }
        this.hour = i2;
        this.minute = i3;
        this.weekMask = b;
    }

    public int getAlarmIndex() {
        return this.alarmIndex;
    }

    public int getEnable() {
        return this.enable;
    }

    public boolean isEnable() {
        return this.enable != 0;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public byte getWeekMask() {
        return this.weekMask;
    }

    public void setEnable(boolean z) {
        this.enable = z ? 1 : 0;
    }

    public void setWeekMask(byte b) {
        this.weekMask = b;
    }

    public void setHour(int i) {
        this.hour = i;
    }

    public void setMinute(int i) {
        this.minute = i;
    }

    public void enableTheWeek(int i, boolean z) {
        int i2 = 1 << i;
        byte b = (byte) ((~i2) & this.weekMask);
        this.weekMask = b;
        if (z) {
            this.weekMask = (byte) (i2 | b);
        }
    }

    public AlarmEntity cloneMyself() {
        return new AlarmEntity(this.alarmIndex, this.enable, this.hour, this.minute, this.weekMask);
    }
}
