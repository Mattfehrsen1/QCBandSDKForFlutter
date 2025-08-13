package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/MixtureReq.class */
public abstract class MixtureReq extends BaseReqCmd {
    protected byte[] subData;

    public MixtureReq(byte key) {
        super(key);
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.subData;
    }
}
