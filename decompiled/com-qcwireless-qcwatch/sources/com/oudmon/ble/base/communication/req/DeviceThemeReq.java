package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class DeviceThemeReq extends MixtureReq {
    private DeviceThemeReq() {
        super((byte) 61);
        this.subData = new byte[]{1};
    }

    public static DeviceThemeReq getReadInstance() {
        return new DeviceThemeReq();
    }

    public static DeviceThemeReq getWriteInstance() {
        return new DeviceThemeReq((byte) 0);
    }

    private DeviceThemeReq(byte b) {
        super((byte) 61);
        this.subData = new byte[]{2, b};
    }
}
