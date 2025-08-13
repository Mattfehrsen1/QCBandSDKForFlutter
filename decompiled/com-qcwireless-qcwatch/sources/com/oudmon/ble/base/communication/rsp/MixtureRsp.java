package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes3.dex */
public abstract class MixtureRsp extends BaseRspCmd {
    public static final byte ACTION_DELETE = 3;
    public static final byte ACTION_READ = 1;
    public static final byte ACTION_WRITE = 2;
    private byte action;

    protected abstract void readSubData(byte[] bArr);

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        byte b = bArr[0];
        this.action = b;
        if (b == 1) {
            readSubData(bArr);
        }
        return false;
    }

    public byte getAction() {
        return this.action;
    }
}
