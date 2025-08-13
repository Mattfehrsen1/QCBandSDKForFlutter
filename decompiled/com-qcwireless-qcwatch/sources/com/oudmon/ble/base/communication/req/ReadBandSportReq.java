package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.utils.DataParseUtils;

/* loaded from: classes3.dex */
public class ReadBandSportReq extends BaseReqCmd {
    private byte[] data;

    public ReadBandSportReq(long j) {
        super((byte) 19);
        this.data = DataParseUtils.intToByteArray((int) j);
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.data;
    }
}
