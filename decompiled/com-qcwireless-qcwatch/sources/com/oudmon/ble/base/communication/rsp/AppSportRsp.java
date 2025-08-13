package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.util.Arrays;

/* loaded from: classes3.dex */
public class AppSportRsp extends BaseRspCmd {
    private int gpsStatus;
    private int timeStamp = 0;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        byte b = bArr[0];
        this.gpsStatus = b;
        if (b == 6) {
            this.timeStamp = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 2, 6));
        }
        return false;
    }

    public int getTimeStamp() {
        return this.timeStamp;
    }

    public int getGpsStatus() {
        return this.gpsStatus;
    }
}
