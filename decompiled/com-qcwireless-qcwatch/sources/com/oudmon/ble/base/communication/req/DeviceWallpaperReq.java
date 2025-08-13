package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class DeviceWallpaperReq extends MixtureReq {
    private DeviceWallpaperReq() {
        super((byte) 63);
        this.subData = new byte[]{1};
    }

    public static DeviceWallpaperReq getReadInstance() {
        return new DeviceWallpaperReq();
    }

    public static DeviceWallpaperReq getWriteInstance() {
        return new DeviceWallpaperReq((byte) 0);
    }

    private DeviceWallpaperReq(byte b) {
        super((byte) 63);
        this.subData = new byte[]{2, b};
    }
}
