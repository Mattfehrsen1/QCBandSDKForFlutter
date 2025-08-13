package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public abstract class MixtureReq extends BaseReqCmd {
    protected byte[] subData;

    public MixtureReq(byte b) {
        super(b);
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.subData;
    }
}
