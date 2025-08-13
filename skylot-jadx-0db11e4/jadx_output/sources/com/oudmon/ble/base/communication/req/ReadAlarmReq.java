package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/ReadAlarmReq.class */
public class ReadAlarmReq extends BaseReqCmd {
    private int alarmIndex;

    public ReadAlarmReq(int alarmIndex) {
        super((byte) 36);
        if (alarmIndex > 4) {
            throw new IllegalArgumentException("闹钟索引只能0 到 4");
        }
        this.alarmIndex = alarmIndex;
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return new byte[]{(byte) this.alarmIndex};
    }
}
