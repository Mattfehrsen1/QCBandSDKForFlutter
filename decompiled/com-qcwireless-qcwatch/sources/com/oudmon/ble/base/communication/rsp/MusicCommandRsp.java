package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes3.dex */
public class MusicCommandRsp extends BaseRspCmd {
    private int action = 0;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.action = bArr[0] & 255;
        return false;
    }

    public int getAction() {
        return this.action;
    }

    public String toString() {
        return "MusicCommandRsp{action=" + this.action + ", status=" + this.status + '}';
    }
}
