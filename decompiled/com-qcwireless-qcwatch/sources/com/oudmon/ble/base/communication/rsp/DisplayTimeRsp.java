package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes3.dex */
public class DisplayTimeRsp extends MixtureRsp {
    private int mAlpha;
    private int mDisplayTime;
    private int mDisplayType;
    private boolean mIsCustom;
    private int total;
    private int type;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] bArr) {
        this.mDisplayTime = bArr[1];
        this.mDisplayType = bArr[2];
        this.mAlpha = bArr[3];
        this.mIsCustom = bArr[4] != 0;
        this.total = bArr[5];
        this.type = bArr[6];
    }

    public int getDisplayTime() {
        return this.mDisplayTime;
    }

    public int getDisplayType() {
        return this.mDisplayType;
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    public boolean isCustom() {
        return this.mIsCustom;
    }

    public int getTotal() {
        return this.total;
    }

    public int getType() {
        return this.type;
    }
}
