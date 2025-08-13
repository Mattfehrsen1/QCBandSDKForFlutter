package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/RealTimeHeartRate.class */
public class RealTimeHeartRate extends BaseReqCmd {
    private int type;
    private byte[] mData;

    public RealTimeHeartRate(int type) {
        super((byte) 30);
        this.mData = new byte[1];
        this.type = type;
        this.mData[0] = (byte) type;
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.mData;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
