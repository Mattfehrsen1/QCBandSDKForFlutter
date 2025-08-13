package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/FindDeviceReq.class */
public class FindDeviceReq extends BaseReqCmd {
    public FindDeviceReq() {
        super((byte) 80);
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return new byte[]{85, -86};
    }
}
