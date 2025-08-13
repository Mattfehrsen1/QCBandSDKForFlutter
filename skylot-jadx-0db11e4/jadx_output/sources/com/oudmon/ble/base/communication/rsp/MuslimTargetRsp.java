package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.communication.utils.ByteUtil;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/MuslimTargetRsp.class */
public class MuslimTargetRsp extends BaseRspCmd {
    private int muslimTarget = 0;
    private int muslimTargetEnable;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        try {
            this.muslimTarget = ByteUtil.bytesToInt(new byte[]{data[2], data[3], data[4], data[5]});
            this.muslimTargetEnable = data[6];
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getMuslimTarget() {
        return this.muslimTarget;
    }

    public int isMuslimTargetEnable() {
        return this.muslimTargetEnable;
    }
}
