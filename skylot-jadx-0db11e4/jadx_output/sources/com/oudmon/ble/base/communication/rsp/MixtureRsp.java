package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/MixtureRsp.class */
public abstract class MixtureRsp extends BaseRspCmd {
    public static final byte ACTION_READ = 1;
    public static final byte ACTION_WRITE = 2;
    public static final byte ACTION_DELETE = 3;
    private byte action;

    protected abstract void readSubData(byte[] bArr);

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.action = data[0];
        if (this.action == 1) {
            readSubData(data);
            return false;
        }
        return false;
    }

    public byte getAction() {
        return this.action;
    }
}
