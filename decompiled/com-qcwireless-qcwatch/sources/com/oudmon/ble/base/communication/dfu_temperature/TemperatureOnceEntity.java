package com.oudmon.ble.base.communication.dfu_temperature;

/* loaded from: classes3.dex */
public class TemperatureOnceEntity {
    public long mTime;
    public float mValue;

    public void clear() {
        this.mTime = 0L;
        this.mValue = 0.0f;
    }

    public String toString() {
        return "TemperatureOnceEntity{mTime=" + this.mTime + ", mValue=" + this.mValue + '}';
    }
}
