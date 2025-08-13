package com.oudmon.ble.base.communication.req;

import android.util.Log;
import com.oudmon.ble.base.communication.Constants;
import com.qcwireless.qc_utils.bytes.DataTransferUtils;

/* loaded from: classes3.dex */
public class ReadSleepDetailsReq extends BaseReqCmd {
    private byte[] data;

    public ReadSleepDetailsReq(int i, int i2, int i3) {
        super(Constants.CMD_GET_SLEEP);
        if (i > 29) {
            throw new IllegalArgumentException("dayOffset 最大只到29");
        }
        if (i2 > i3 || i3 > 95) {
            throw new IllegalArgumentException("数据段索引值异常");
        }
        Log.i(getClass().getSimpleName(), "ReadSleepDetailsReq: dayOffset=" + i);
        this.data = new byte[]{(byte) i, 15, (byte) i2, (byte) i3};
        Log.i(getClass().getSimpleName(), "ReadSleepDetailsReq: data=" + DataTransferUtils.getHexString(this.data));
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.data;
    }
}
