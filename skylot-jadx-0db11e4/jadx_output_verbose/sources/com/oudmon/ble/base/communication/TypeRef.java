package com.oudmon.ble.base.communication;

import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import java.lang.reflect.ParameterizedType;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/TypeRef.class */
public abstract class TypeRef<T extends BaseRspCmd> {
    protected TypeRef(ICommandResponse<T> factory) {
    }

    public Class<?> getGenericType() {
        return (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
