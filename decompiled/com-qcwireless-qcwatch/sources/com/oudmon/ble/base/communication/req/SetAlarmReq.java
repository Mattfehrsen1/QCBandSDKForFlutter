package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.entity.AlarmEntity;
import com.oudmon.ble.base.communication.utils.BLEDataFormatUtils;

/* loaded from: classes3.dex */
public class SetAlarmReq extends BaseReqCmd {
    private byte[] data;

    public SetAlarmReq(AlarmEntity alarmEntity) {
        super(Constants.CMD_SET_ALARM_CLOCK);
        if (alarmEntity.getAlarmIndex() > 4) {
            throw new IllegalArgumentException("闹钟索引只能0 到 4");
        }
        if (alarmEntity.getEnable() > 2) {
            throw new IllegalArgumentException("闹钟使能设置只能0 到 2");
        }
        byte weekMask = alarmEntity.getWeekMask();
        this.data = new byte[]{(byte) alarmEntity.getAlarmIndex(), (byte) alarmEntity.getEnable(), BLEDataFormatUtils.decimalToBCD(alarmEntity.getHour()), BLEDataFormatUtils.decimalToBCD(alarmEntity.getMinute()), (byte) ((weekMask >> 0) & 1), (byte) ((weekMask >> 1) & 1), (byte) ((weekMask >> 2) & 1), (byte) ((weekMask >> 3) & 1), (byte) ((weekMask >> 4) & 1), (byte) ((weekMask >> 5) & 1), (byte) ((weekMask >> 6) & 1)};
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.data;
    }
}
