package com.oudmon.ble.base.communication.entity;

import java.util.ArrayList;

/* loaded from: classes3.dex */
public class BpDataEntity {
    private ArrayList<BpValue> bpValues = new ArrayList<>();
    private int day;
    private int mouth;
    private int timeDelay;
    private int year;

    public BpDataEntity(int i, int i2, int i3, int i4) {
        this.year = i;
        this.mouth = i2;
        this.day = i3;
        this.timeDelay = i4;
    }

    public void addBpIndex(int i) {
        this.bpValues.add(new BpValue(i));
    }

    public void addRealValue(int i, byte[] bArr) {
        int iMin = Math.min(this.bpValues.size() - i, bArr.length);
        for (int i2 = 0; i2 < iMin; i2++) {
            this.bpValues.get(i2 + i).value = bArr[i2] & 255;
        }
    }

    public class BpValue {
        int timeMinute;
        int value;

        public BpValue(int i) {
            this.timeMinute = i;
        }

        public int getTimeMinute() {
            return this.timeMinute;
        }

        public int getValue() {
            return this.value;
        }
    }

    public int getYear() {
        return this.year;
    }

    public int getMouth() {
        return this.mouth;
    }

    public int getDay() {
        return this.day;
    }

    public int getTimeDelay() {
        return this.timeDelay;
    }

    public ArrayList<BpValue> getBpValues() {
        return this.bpValues;
    }
}
