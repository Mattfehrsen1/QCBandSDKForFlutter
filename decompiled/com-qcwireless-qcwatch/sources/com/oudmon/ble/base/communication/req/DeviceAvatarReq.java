package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class DeviceAvatarReq extends BaseReqCmd {
    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return new byte[0];
    }

    public DeviceAvatarReq() {
        super((byte) 50);
    }
}
