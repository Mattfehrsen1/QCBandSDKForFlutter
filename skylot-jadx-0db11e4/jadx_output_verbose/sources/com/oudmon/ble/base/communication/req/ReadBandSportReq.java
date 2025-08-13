package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.utils.DataParseUtils;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/ReadBandSportReq.class */
public class ReadBandSportReq extends BaseReqCmd {
    private byte[] data;

    public ReadBandSportReq(long time) {
        super((byte) 19);
        this.data = DataParseUtils.intToByteArray((int) time);
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.data;
    }
}
