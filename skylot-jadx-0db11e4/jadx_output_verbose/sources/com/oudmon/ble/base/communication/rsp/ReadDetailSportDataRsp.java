package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.communication.entity.BleStepDetails;
import com.oudmon.ble.base.communication.utils.BLEDataFormatUtils;
import java.util.ArrayList;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/ReadDetailSportDataRsp.class */
public class ReadDetailSportDataRsp extends BaseRspCmd {
    private ArrayList<BleStepDetails> bleStepDetailses = new ArrayList<>();
    private int index = 0;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        byte flag = data[0];
        if (this.index == 0 && (flag & 255) == 255) {
            this.bleStepDetailses.clear();
            return false;
        }
        if (this.index == 0 && (flag & 255) == 240) {
            this.index++;
            this.bleStepDetailses.clear();
            return true;
        }
        BleStepDetails stepDetail = new BleStepDetails();
        stepDetail.setYear(BLEDataFormatUtils.BCDToDecimal(data[0]) + 2000);
        stepDetail.setMonth(BLEDataFormatUtils.BCDToDecimal(data[1]));
        stepDetail.setDay(BLEDataFormatUtils.BCDToDecimal(data[2]));
        stepDetail.setTimeIndex(data[3]);
        stepDetail.setCalorie(BLEDataFormatUtils.bytes2Int(new byte[]{data[7], data[6]}));
        stepDetail.setWalkSteps(BLEDataFormatUtils.bytes2Int(new byte[]{data[9], data[8]}));
        int distance = BLEDataFormatUtils.bytes2Int(new byte[]{data[11], data[10]});
        stepDetail.setDistance(distance);
        this.bleStepDetailses.add(stepDetail);
        this.index++;
        if (data[4] == data[5] - 1) {
            return false;
        }
        return true;
    }

    public ArrayList<BleStepDetails> getBleStepDetailses() {
        return this.bleStepDetailses;
    }
}
