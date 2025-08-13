package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class ReadDrinkAlarmReq extends BaseReqCmd {
    private int alarmIndex;

    public ReadDrinkAlarmReq(int i) {
        super((byte) 40);
        if (i > 7) {
            throw new IllegalArgumentException("闹钟索引只能0 到 7");
        }
        this.alarmIndex = i;
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return new byte[]{(byte) this.alarmIndex};
    }
}
