package com.oudmon.ble.base.communication.req;

import android.util.Log;
import com.oudmon.ble.base.util.DataTransferUtils;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/ReadSleepDetailsReq.class */
public class ReadSleepDetailsReq extends BaseReqCmd {
    private byte[] data;

    public ReadSleepDetailsReq(int dayOffset, int startIndex, int endIndex) {
        super((byte) 68);
        if (dayOffset > 29) {
            throw new IllegalArgumentException("dayOffset 最大只到29");
        }
        if (startIndex > endIndex || endIndex > 95) {
            throw new IllegalArgumentException("数据段索引值异常");
        }
        Log.i(getClass().getSimpleName(), "ReadSleepDetailsReq: dayOffset=" + dayOffset);
        this.data = new byte[]{(byte) dayOffset, 15, (byte) startIndex, (byte) endIndex};
        Log.i(getClass().getSimpleName(), "ReadSleepDetailsReq: data=" + DataTransferUtils.getHexString(this.data));
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.data;
    }
}
