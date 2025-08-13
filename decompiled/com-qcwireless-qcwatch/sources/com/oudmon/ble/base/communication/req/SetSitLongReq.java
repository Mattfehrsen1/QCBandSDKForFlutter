package com.oudmon.ble.base.communication.req;

import android.util.Log;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.entity.StartEndTimeEntity;
import com.oudmon.ble.base.communication.utils.BLEDataFormatUtils;

/* loaded from: classes3.dex */
public class SetSitLongReq extends BaseReqCmd {
    private byte[] data;

    public SetSitLongReq(StartEndTimeEntity startEndTimeEntity, byte b, int i) {
        super(Constants.CMD_SET_SIT_LONG);
        if (i != 30 && i != 60 && i != 90) {
            Log.i("Jxr35", "时间周期参数错误，已调整为正常的60s，原参数为: " + i);
            i = 60;
        }
        this.data = new byte[]{BLEDataFormatUtils.decimalToBCD(startEndTimeEntity.getStartHour()), BLEDataFormatUtils.decimalToBCD(startEndTimeEntity.getStartMinute()), BLEDataFormatUtils.decimalToBCD(startEndTimeEntity.getEndHour()), BLEDataFormatUtils.decimalToBCD(startEndTimeEntity.getEndMinute()), b, (byte) i};
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.data;
    }
}
