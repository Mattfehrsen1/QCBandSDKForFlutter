package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/SimpleKeyReq.class */
public class SimpleKeyReq extends BaseReqCmd {
    public SimpleKeyReq(byte key) {
        super(key);
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return null;
    }
}
