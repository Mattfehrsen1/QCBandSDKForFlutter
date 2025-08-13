package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/RestoreKeyReq.class */
public class RestoreKeyReq extends SimpleKeyReq {
    public RestoreKeyReq(byte key) {
        super(key);
    }

    @Override // com.oudmon.ble.base.communication.req.SimpleKeyReq, com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        byte[] sub = {102, 102};
        return sub;
    }
}
