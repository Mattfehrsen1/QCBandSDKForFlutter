package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes3.dex */
public abstract class BaseRspCmd {
    public static final int RESULT_OK = 0;
    protected static final String TAG = "Jxr35";
    protected int status;

    public abstract boolean acceptData(byte[] bArr);

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }
}
