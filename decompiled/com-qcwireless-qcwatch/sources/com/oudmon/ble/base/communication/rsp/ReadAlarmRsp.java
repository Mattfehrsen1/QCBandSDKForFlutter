package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.communication.entity.AlarmEntity;
import com.oudmon.ble.base.communication.utils.BLEDataFormatUtils;

/* loaded from: classes3.dex */
public class ReadAlarmRsp extends BaseRspCmd {
    private AlarmEntity alarmEntity;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        byte b = 0;
        for (int i = 0; i < 7; i++) {
            b = (byte) ((bArr[i + 4] << i) | b);
        }
        this.alarmEntity = new AlarmEntity(bArr[0], bArr[1], BLEDataFormatUtils.BCDToDecimal(bArr[2]), BLEDataFormatUtils.BCDToDecimal(bArr[3]), b);
        return false;
    }

    public AlarmEntity getAlarmEntity() {
        return this.alarmEntity;
    }
}
