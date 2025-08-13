package com.oudmon.ble.base.communication;

import com.oudmon.ble.base.communication.rsp.BaseRspCmd;

/* loaded from: classes3.dex */
public interface ICommandResponse<T extends BaseRspCmd> {
    void onDataResponse(T t);
}
