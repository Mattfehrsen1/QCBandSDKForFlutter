package com.oudmon.ble.base.communication.req;

import android.util.Log;
import com.oudmon.ble.base.communication.entity.StartEndTimeEntity;
import com.oudmon.ble.base.communication.utils.BLEDataFormatUtils;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/SetSitLongReq.class */
public class SetSitLongReq extends BaseReqCmd {
    private byte[] data;

    public SetSitLongReq(StartEndTimeEntity startEndTimeEntity, byte weekMask, int cycle) {
        super((byte) 37);
        if (cycle != 30 && cycle != 60 && cycle != 90) {
            Log.i("Jxr35", "时间周期参数错误，已调整为正常的60s，原参数为: " + cycle);
            cycle = 60;
        }
        this.data = new byte[]{BLEDataFormatUtils.decimalToBCD(startEndTimeEntity.getStartHour()), BLEDataFormatUtils.decimalToBCD(startEndTimeEntity.getStartMinute()), BLEDataFormatUtils.decimalToBCD(startEndTimeEntity.getEndHour()), BLEDataFormatUtils.decimalToBCD(startEndTimeEntity.getEndMinute()), weekMask, (byte) cycle};
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.data;
    }
}
