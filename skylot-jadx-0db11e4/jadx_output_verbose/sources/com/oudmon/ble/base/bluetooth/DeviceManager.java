package com.oudmon.ble.base.bluetooth;

import android.text.TextUtils;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/bluetooth/DeviceManager.class */
public class DeviceManager {
    private String mDeviceName = "";
    private String mDeviceAddress;
    private static DeviceManager mInstance;

    public static DeviceManager getInstance() {
        if (mInstance == null) {
            synchronized (DeviceManager.class) {
                if (mInstance == null) {
                    mInstance = new DeviceManager();
                }
            }
        }
        return mInstance;
    }

    public void reSet() {
        mInstance = null;
        this.mDeviceName = "";
        this.mDeviceAddress = null;
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public void setDeviceName(String name) {
        if (TextUtils.isEmpty(name)) {
            name = "";
        }
        this.mDeviceName = name;
    }

    public String getDeviceAddress() {
        return this.mDeviceAddress;
    }

    public void setDeviceAddress(String address) {
        this.mDeviceAddress = address;
    }
}
