package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.entity.AlarmEntity;
import com.oudmon.ble.base.communication.utils.BLEDataFormatUtils;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/SetDrinkAlarmReq.class */
public class SetDrinkAlarmReq extends BaseReqCmd {
    private byte[] data;

    public SetDrinkAlarmReq(AlarmEntity alarmEntity) {
        super((byte) 39);
        if (alarmEntity.getAlarmIndex() > 7) {
            throw new IllegalArgumentException("闹钟索引只能0 到 7");
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
