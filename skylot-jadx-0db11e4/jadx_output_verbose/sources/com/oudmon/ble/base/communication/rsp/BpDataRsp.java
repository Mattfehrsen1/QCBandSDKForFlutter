package com.oudmon.ble.base.communication.rsp;

import android.util.Log;
import com.oudmon.ble.base.communication.entity.BpDataEntity;
import com.oudmon.ble.base.util.DataTransferUtils;
import java.util.Arrays;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/BpDataRsp.class */
public class BpDataRsp extends BaseRspCmd {
    private int valueIndex = 0;
    private BpDataEntity bpDataEntity;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        Log.i("Jxr35", "acceptData.. data: " + DataTransferUtils.getHexString(data));
        byte b = data[0];
        if (b != 0) {
            if (b == 1) {
                if (this.bpDataEntity == null) {
                    return true;
                }
                this.bpDataEntity.addRealValue(this.valueIndex * 13, Arrays.copyOfRange(data, 1, data.length));
                this.valueIndex++;
                Log.i("Jxr35", "acceptData: size=" + this.bpDataEntity.getBpValues().size() + " cur offset=" + (this.valueIndex * 13));
                if (this.valueIndex * 13 >= this.bpDataEntity.getBpValues().size()) {
                    Log.i("Jxr35", "acceptData: 成功");
                    return false;
                }
                return true;
            }
            if ((b & 255) == 255) {
                return false;
            }
            return true;
        }
        this.valueIndex = 0;
        this.bpDataEntity = new BpDataEntity(data[1] + 2000, data[2], data[3], data[4]);
        byte b2 = data[4];
        for (int i = 0; i < 6; i++) {
            byte cur = data[i + 5];
            for (int j = 0; j < 8; j++) {
                byte mask = (byte) (cur >>> j);
                if ((1 & mask) == 1) {
                    this.bpDataEntity.addBpIndex(((i * 8) + j) * b2);
                }
            }
        }
        return true;
    }

    public BpDataEntity getBpDataEntity() {
        return this.bpDataEntity;
    }
}
