package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/ReadDetailSportDataReq.class */
public class ReadDetailSportDataReq extends BaseReqCmd {
    private byte[] data;

    public ReadDetailSportDataReq(int dayOffset, int startIndex, int endIndex) {
        super((byte) 67);
        if (dayOffset > 29) {
            throw new IllegalArgumentException("dayOffset 最大只到29");
        }
        if (startIndex > endIndex || endIndex > 95) {
            throw new IllegalArgumentException("数据段索引值异常");
        }
        this.data = new byte[]{(byte) dayOffset, 15, (byte) startIndex, (byte) endIndex};
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.data;
    }
}
