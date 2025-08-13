package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.communication.entity.BleStepDetails;
import com.oudmon.ble.base.communication.utils.BLEDataFormatUtils;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class ReadDetailSportDataRsp extends BaseRspCmd {
    private ArrayList<BleStepDetails> bleStepDetailses = new ArrayList<>();
    private int index = 0;
    private boolean calorieNewProtocol = false;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        byte b = bArr[0];
        int i = this.index;
        if (i == 0 && (b & 255) == 255) {
            this.bleStepDetailses.clear();
            return false;
        }
        if (i == 0 && (b & 255) == 240) {
            if (bArr[2] == 1) {
                this.calorieNewProtocol = true;
            }
            this.index = i + 1;
            this.bleStepDetailses.clear();
        } else {
            BleStepDetails bleStepDetails = new BleStepDetails();
            bleStepDetails.setYear(BLEDataFormatUtils.BCDToDecimal(bArr[0]) + 2000);
            bleStepDetails.setMonth(BLEDataFormatUtils.BCDToDecimal(bArr[1]));
            bleStepDetails.setDay(BLEDataFormatUtils.BCDToDecimal(bArr[2]));
            bleStepDetails.setTimeIndex(bArr[3]);
            int iBytes2Int = BLEDataFormatUtils.bytes2Int(new byte[]{bArr[7], bArr[6]});
            if (this.calorieNewProtocol) {
                iBytes2Int *= 10;
            }
            bleStepDetails.setCalorie(iBytes2Int);
            bleStepDetails.setWalkSteps(BLEDataFormatUtils.bytes2Int(new byte[]{bArr[9], bArr[8]}));
            bleStepDetails.setDistance(BLEDataFormatUtils.bytes2Int(new byte[]{bArr[11], bArr[10]}));
            this.bleStepDetailses.add(bleStepDetails);
            this.index++;
            if (bArr[4] == bArr[5] - 1) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<BleStepDetails> getBleStepDetailses() {
        return this.bleStepDetailses;
    }
}
