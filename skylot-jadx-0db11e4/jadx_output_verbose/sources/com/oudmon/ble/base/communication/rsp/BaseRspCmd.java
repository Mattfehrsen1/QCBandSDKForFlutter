package com.oudmon.ble.base.communication.rsp;

import java.lang.reflect.ParameterizedType;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/BaseRspCmd.class */
public abstract class BaseRspCmd {
    protected static final String TAG = "Jxr35";
    public static final int RESULT_OK = 0;
    protected int status;
    private Class<?> clazz;

    public abstract boolean acceptData(byte[] bArr);

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Class getRealType() {
        ParameterizedType pt = (ParameterizedType) super.getClass().getGenericSuperclass();
        this.clazz = (Class) pt.getActualTypeArguments()[0];
        return this.clazz;
    }
}
