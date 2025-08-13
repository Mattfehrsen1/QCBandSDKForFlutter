package com.oudmon.ble.base.communication.dfu_temperature;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/dfu_temperature/TemperatureOnceEntity.class */
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
