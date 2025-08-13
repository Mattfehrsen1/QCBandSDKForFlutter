package com.oudmon.ble.base.bluetooth;

import android.text.TextUtils;

/* loaded from: classes3.dex */
public class DeviceManager {
    private static DeviceManager mInstance;
    private String mDeviceName = "";
    private String mDeviceAddress = "";

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

    public void setDeviceName(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        this.mDeviceName = str;
    }

    public String getDeviceAddress() {
        return this.mDeviceAddress;
    }

    public void setDeviceAddress(String str) {
        this.mDeviceAddress = str;
    }
}
