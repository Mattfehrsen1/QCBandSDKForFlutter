package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/BpReadConformReq.class */
public class BpReadConformReq extends BaseReqCmd {
    private boolean isSuccess;

    public BpReadConformReq(boolean isSuccess) {
        super((byte) 14);
        this.isSuccess = isSuccess;
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        byte[] bArr = new byte[1];
        bArr[0] = (byte) (this.isSuccess ? 0 : 255);
        return bArr;
    }
}
