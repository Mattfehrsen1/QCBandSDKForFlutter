package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class SetMessagePushReq extends BaseReqCmd {
    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return null;
    }

    public SetMessagePushReq() {
        super((byte) 97);
    }
}
