package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes3.dex */
public class BrightnessSettingsRsp extends MixtureRsp {
    private int level = 0;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] bArr) {
        this.level = bArr[1];
    }

    public int getLevel() {
        return this.level;
    }
}
