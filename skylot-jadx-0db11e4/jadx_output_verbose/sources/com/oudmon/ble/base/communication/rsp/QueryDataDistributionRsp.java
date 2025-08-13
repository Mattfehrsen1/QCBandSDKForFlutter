package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.communication.utils.BLEDataFormatUtils;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/QueryDataDistributionRsp.class */
public class QueryDataDistributionRsp extends BaseRspCmd {
    private int distribution;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.distribution = BLEDataFormatUtils.bytes2Int(new byte[]{data[0], data[1], data[2], data[3]});
        return false;
    }

    public boolean isTheDayHasData(int dayOffset) {
        int tmp = this.distribution >> dayOffset;
        return (tmp & 1) != 0;
    }
}
