package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/ReadDrinkAlarmReq.class */
public class ReadDrinkAlarmReq extends BaseReqCmd {
    private int alarmIndex;

    public ReadDrinkAlarmReq(int alarmIndex) {
        super((byte) 40);
        if (alarmIndex > 7) {
            throw new IllegalArgumentException("闹钟索引只能0 到 7");
        }
        this.alarmIndex = alarmIndex;
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return new byte[]{(byte) this.alarmIndex};
    }
}
