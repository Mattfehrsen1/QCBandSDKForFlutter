package com.oudmon.ble.base.communication.rsp;

import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.utils.ByteUtil;

/* loaded from: classes3.dex */
public class FindPhoneRsp extends BaseRspCmd {
    private int openOrClose;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.openOrClose = bArr[0];
        XLog.i(ByteUtil.byteArrayToString(bArr));
        return false;
    }

    public int getSwitchStatue() {
        return this.openOrClose;
    }
}
