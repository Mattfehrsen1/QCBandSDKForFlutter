package com.oudmon.ble.base.communication;

import com.oudmon.ble.base.communication.rsp.BaseRspCmd;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/ICommandResponse.class */
public interface ICommandResponse<T extends BaseRspCmd> {
    void onDataResponse(T t);
}
