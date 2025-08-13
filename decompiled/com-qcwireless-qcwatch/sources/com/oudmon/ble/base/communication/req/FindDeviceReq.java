package com.oudmon.ble.base.communication.req;

import com.realsil.sdk.bbpro.params.Mmi;

/* loaded from: classes3.dex */
public class FindDeviceReq extends BaseReqCmd {
    public FindDeviceReq() {
        super((byte) 80);
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return new byte[]{Mmi.AU_MMI_DEV_POWER_ON_BUTTON_RELEASE, -86};
    }
}
