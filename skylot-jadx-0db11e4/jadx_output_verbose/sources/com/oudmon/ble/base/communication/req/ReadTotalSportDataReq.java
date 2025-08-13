package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/ReadTotalSportDataReq.class */
public class ReadTotalSportDataReq extends BaseReqCmd {
    private int theDayOffset;

    public ReadTotalSportDataReq(int theDayOffset) {
        super((byte) 7);
        this.theDayOffset = 0;
        this.theDayOffset = theDayOffset;
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return new byte[]{(byte) (this.theDayOffset & 255)};
    }
}
