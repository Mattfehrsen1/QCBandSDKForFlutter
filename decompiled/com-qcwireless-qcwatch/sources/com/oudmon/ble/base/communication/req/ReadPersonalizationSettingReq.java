package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes3.dex */
public class ReadPersonalizationSettingReq extends BaseReqCmd {
    private byte[] data;

    private ReadPersonalizationSettingReq() {
        super(Constants.CMD_GET_PERSONALIZATION_SETTING);
        this.data = new byte[]{1, 2, 3};
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.data;
    }

    public static ReadPersonalizationSettingReq getReadInstance() {
        return new ReadPersonalizationSettingReq();
    }
}
