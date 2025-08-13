package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class BlackListReq extends BaseReqCmd {
    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return new byte[]{1};
    }

    public BlackListReq() {
        super((byte) 45);
    }
}
