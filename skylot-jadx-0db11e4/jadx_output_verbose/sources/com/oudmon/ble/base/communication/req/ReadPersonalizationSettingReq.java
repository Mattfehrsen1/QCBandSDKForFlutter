package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/ReadPersonalizationSettingReq.class */
public class ReadPersonalizationSettingReq extends BaseReqCmd {
    private byte[] data;

    private ReadPersonalizationSettingReq() {
        super((byte) 23);
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
