package com.oudmon.ble.base.communication.rsp;

import android.util.Log;
import com.oudmon.ble.base.util.DataTransferUtils;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/MenstruationDataRsp.class */
public class MenstruationDataRsp extends BaseRspCmd {
    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        Log.i("Jxr35", "acceptData.. data: " + DataTransferUtils.getHexString(data));
        return true;
    }
}
