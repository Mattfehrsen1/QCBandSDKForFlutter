package com.oudmon.ble.base.communication.rsp;

import java.util.Arrays;

/* loaded from: classes3.dex */
public class DeviceWallpaperRsp extends BaseRspCmd {
    private String wallpaperName = "";
    private int wallpaperType;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        byte b = bArr[1];
        this.wallpaperType = b;
        if (b != 1) {
            return false;
        }
        this.wallpaperName = new String(Arrays.copyOfRange(bArr, 2, bArr.length));
        return false;
    }

    public int getWallpaperType() {
        return this.wallpaperType;
    }

    public String getWallpaperName() {
        return this.wallpaperName;
    }
}
