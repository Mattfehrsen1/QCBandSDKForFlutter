package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.communication.entity.BleStepTotal;
import com.oudmon.ble.base.communication.utils.BLEDataFormatUtils;
import java.util.Calendar;

/* loaded from: classes3.dex */
public class TodaySportDataRsp extends BaseRspCmd {
    private BleStepTotal sportTotal;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        BleStepTotal bleStepTotal = new BleStepTotal();
        this.sportTotal = bleStepTotal;
        bleStepTotal.setTotalSteps(BLEDataFormatUtils.bytes2Int(new byte[]{bArr[0], bArr[1], bArr[2]}));
        this.sportTotal.setRunningSteps(BLEDataFormatUtils.bytes2Int(new byte[]{bArr[3], bArr[4], bArr[5]}));
        this.sportTotal.setCalorie(BLEDataFormatUtils.bytes2Int(new byte[]{bArr[6], bArr[7], bArr[8]}));
        this.sportTotal.setWalkDistance(BLEDataFormatUtils.bytes2Int(new byte[]{bArr[9], bArr[10], bArr[11]}));
        this.sportTotal.setSportDuration(BLEDataFormatUtils.bytes2Int(new byte[]{bArr[12], bArr[13]}) * 60);
        Calendar calendar = Calendar.getInstance();
        this.sportTotal.setDaysAgo(0);
        this.sportTotal.setYear(calendar.get(1));
        this.sportTotal.setMonth(calendar.get(2) + 1);
        this.sportTotal.setDay(calendar.get(5));
        return false;
    }

    public BleStepTotal getSportTotal() {
        return this.sportTotal;
    }
}
