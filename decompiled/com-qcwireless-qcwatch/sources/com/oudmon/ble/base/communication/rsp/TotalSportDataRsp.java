package com.oudmon.ble.base.communication.rsp;

import android.util.Log;
import com.oudmon.ble.base.communication.entity.BleStepTotal;
import com.oudmon.ble.base.communication.utils.BLEDataFormatUtils;

/* loaded from: classes3.dex */
public class TotalSportDataRsp extends BaseRspCmd {
    private BleStepTotal bleStepTotal;
    private int pocketCount = 2;
    private int curIndex = 0;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        byte b = bArr[0];
        int i = this.curIndex;
        if (b != i || b >= this.pocketCount) {
            Log.e("Jxr35", "acceptData: index 错误 need=" + this.curIndex + " received=" + ((int) b));
            this.bleStepTotal = null;
            return false;
        }
        if (bArr[2] == 0 && bArr[3] == 0 && bArr[4] == 0) {
            Log.d("Jxr35", "没有存储数据");
            this.bleStepTotal = null;
            return false;
        }
        if (i == 0) {
            BleStepTotal bleStepTotal = new BleStepTotal();
            this.bleStepTotal = bleStepTotal;
            bleStepTotal.setDaysAgo(BLEDataFormatUtils.BCDToDecimal(bArr[1]));
            this.bleStepTotal.setYear(BLEDataFormatUtils.BCDToDecimal(bArr[2]) + 2000);
            this.bleStepTotal.setMonth(BLEDataFormatUtils.BCDToDecimal(bArr[3]));
            this.bleStepTotal.setDay(BLEDataFormatUtils.BCDToDecimal(bArr[4]));
            this.bleStepTotal.setTotalSteps(BLEDataFormatUtils.bytes2Int(new byte[]{bArr[5], bArr[6], bArr[7]}));
            this.bleStepTotal.setRunningSteps(BLEDataFormatUtils.bytes2Int(new byte[]{bArr[8], bArr[9], bArr[10]}));
            this.bleStepTotal.setCalorie(BLEDataFormatUtils.bytes2Int(new byte[]{bArr[11], bArr[12], bArr[13]}));
        } else if (i == 1) {
            int iBCDToDecimal = BLEDataFormatUtils.BCDToDecimal(bArr[1]);
            int iBCDToDecimal2 = BLEDataFormatUtils.BCDToDecimal(bArr[2]) + 2000;
            int iBCDToDecimal3 = BLEDataFormatUtils.BCDToDecimal(bArr[3]);
            int iBCDToDecimal4 = BLEDataFormatUtils.BCDToDecimal(bArr[4]);
            BleStepTotal bleStepTotal2 = this.bleStepTotal;
            if (bleStepTotal2 != null && bleStepTotal2.getDaysAgo() == iBCDToDecimal && this.bleStepTotal.getYear() == iBCDToDecimal2 && this.bleStepTotal.getMonth() == iBCDToDecimal3 && this.bleStepTotal.getDay() == iBCDToDecimal4) {
                this.bleStepTotal.setWalkDistance(BLEDataFormatUtils.bytes2Int(new byte[]{bArr[5], bArr[6], bArr[7]}));
                this.bleStepTotal.setSportDuration(BLEDataFormatUtils.bytes2Int(new byte[]{bArr[8], bArr[9]}) * 60);
                this.bleStepTotal.setSleepDuration(BLEDataFormatUtils.bytes2Int(new byte[]{bArr[10], bArr[11]}) * 60);
            }
        }
        int i2 = this.curIndex + 1;
        this.curIndex = i2;
        return i2 != this.pocketCount;
    }

    public BleStepTotal getBleStepTotal() {
        return this.bleStepTotal;
    }
}
