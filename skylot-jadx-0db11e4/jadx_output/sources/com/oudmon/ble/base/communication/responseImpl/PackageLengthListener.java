package com.oudmon.ble.base.communication.responseImpl;

import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.JPackageManager;
import com.oudmon.ble.base.communication.rsp.PackageLengthRsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/responseImpl/PackageLengthListener.class */
public class PackageLengthListener implements ICommandResponse<PackageLengthRsp> {
    @Override // com.oudmon.ble.base.communication.ICommandResponse
    public void onDataResponse(PackageLengthRsp resultEntity) {
        JPackageManager.getInstance().setLength(Math.max(resultEntity.mData, 20));
    }
}
