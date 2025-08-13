package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes3.dex */
public class ReadAlarmReq extends BaseReqCmd {
    private int alarmIndex;

    public ReadAlarmReq(int i) {
        super(Constants.CMD_GET_ALARM_CLOCK);
        if (i > 4) {
            throw new IllegalArgumentException("闹钟索引只能0 到 4");
        }
        this.alarmIndex = i;
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return new byte[]{(byte) this.alarmIndex};
    }
}
