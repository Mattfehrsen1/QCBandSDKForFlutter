package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.utils.DataParseUtils;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/ReadPressureReq.class */
public class ReadPressureReq extends BaseReqCmd {
    private byte[] data;

    public ReadPressureReq(long time) {
        super((byte) 20);
        byte[] timeArray = DataParseUtils.intToByteArray((int) time);
        this.data = new byte[timeArray.length + 2];
        System.arraycopy(timeArray, 0, this.data, 0, timeArray.length);
        this.data[4] = 0;
        this.data[5] = 50;
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.data;
    }
}
