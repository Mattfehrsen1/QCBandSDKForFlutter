package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.communication.utils.ByteUtil;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/HeartRateSettingRsp.class */
public class HeartRateSettingRsp extends MixtureRsp {
    private boolean isEnable;
    private int heartInterval;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] subData) {
        this.isEnable = subData[1] == 1;
        this.heartInterval = ByteUtil.byteToInt(subData[2]);
    }

    public boolean isEnable() {
        return this.isEnable;
    }

    public int getHeartInterval() {
        return this.heartInterval;
    }
}
