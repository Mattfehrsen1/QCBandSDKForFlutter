package com.oudmon.ble.base.communication.rsp;

import android.util.Log;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.utils.ByteUtil;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/FindPhoneRsp.class */
public class FindPhoneRsp extends BaseRspCmd {
    private int openOrClose;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.openOrClose = data[0];
        Log.i(Constants.TAG, ByteUtil.byteArrayToString(data));
        return false;
    }

    public int getSwitchStatue() {
        return this.openOrClose;
    }
}
