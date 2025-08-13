package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class ReadTotalSportDataReq extends BaseReqCmd {
    private int theDayOffset;

    public ReadTotalSportDataReq(int i) {
        super((byte) 7);
        this.theDayOffset = 0;
        this.theDayOffset = i;
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return new byte[]{(byte) (this.theDayOffset & 255)};
    }
}
