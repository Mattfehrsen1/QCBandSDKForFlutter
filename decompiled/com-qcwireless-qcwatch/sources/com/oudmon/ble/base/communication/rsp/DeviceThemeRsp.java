package com.oudmon.ble.base.communication.rsp;

import java.util.Arrays;

/* loaded from: classes3.dex */
public class DeviceThemeRsp extends BaseRspCmd {
    private String themeName = "";
    private int themeType;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        byte b = bArr[1];
        this.themeType = b;
        if (b != 1) {
            return false;
        }
        this.themeName = new String(Arrays.copyOfRange(bArr, 2, bArr.length));
        return false;
    }

    public int getThemeType() {
        return this.themeType;
    }

    public String getThemeName() {
        return this.themeName;
    }
}
