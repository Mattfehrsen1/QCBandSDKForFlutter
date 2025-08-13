package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.utils.DataParseUtils;

/* loaded from: classes3.dex */
public class ReadHeartRateReq extends BaseReqCmd {
    private byte[] data;

    public ReadHeartRateReq(long j) {
        super((byte) 21);
        this.data = DataParseUtils.intToByteArray((int) j);
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.data;
    }
}
