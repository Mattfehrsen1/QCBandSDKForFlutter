package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.util.Arrays;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/AppSportRsp.class */
public class AppSportRsp extends BaseRspCmd {
    private int gpsStatus;
    private int timeStamp = 0;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.gpsStatus = data[0];
        if (this.gpsStatus == 6) {
            this.timeStamp = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 2, 6));
            return false;
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
