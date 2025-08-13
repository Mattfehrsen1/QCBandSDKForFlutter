package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes3.dex */
public class MusicSwitchRsp extends BaseRspCmd {
    private int action;
    private boolean enable;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.action = bArr[0];
        this.enable = bArr[1] == 1;
        return false;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public int getAction() {
        return this.action;
    }
}
