package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class SetANCSReq extends BaseReqCmd {
    public SetANCSReq() {
        super((byte) 96);
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return new byte[]{-1, -97, -1, -1};
    }
}
