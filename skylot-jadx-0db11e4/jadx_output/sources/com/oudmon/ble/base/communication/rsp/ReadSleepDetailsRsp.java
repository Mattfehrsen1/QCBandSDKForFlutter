package com.oudmon.ble.base.communication.rsp;

import android.util.Log;
import com.oudmon.ble.base.communication.entity.BleSleepDetails;
import com.oudmon.ble.base.communication.utils.BLEDataFormatUtils;
import java.util.ArrayList;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/ReadSleepDetailsRsp.class */
public class ReadSleepDetailsRsp extends BaseRspCmd {
    private ArrayList<BleSleepDetails> bleSleepDetailses = new ArrayList<>();
    private int index = 0;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        byte flag = data[0];
        if (this.index == 0 && (flag & 255) == 255) {
            this.bleSleepDetailses.clear();
            return false;
        }
        if (this.index == 0 && (flag & 255) == 240) {
            Log.i("Jxr35", "acceptData: init data list");
            this.bleSleepDetailses.clear();
            this.index++;
            return true;
        }
        BleSleepDetails sleepDetail = new BleSleepDetails();
        sleepDetail.setYear(BLEDataFormatUtils.BCDToDecimal(data[0]) + 2000);
        sleepDetail.setMonth(BLEDataFormatUtils.BCDToDecimal(data[1]));
        sleepDetail.setDay(BLEDataFormatUtils.BCDToDecimal(data[2]));
        sleepDetail.setTimeIndex(data[3]);
        int[] sleepQualities = new int[8];
        for (int i = 1; i < sleepQualities.length; i++) {
            sleepQualities[i] = data[5 + i] & 255;
        }
        sleepDetail.setSleepQualities(sleepQualities);
        this.bleSleepDetailses.add(sleepDetail);
        this.index++;
        if (data[4] == data[5] - 1) {
            return false;
        }
        return true;
    }

    public ArrayList<BleSleepDetails> getBleSleepDetailses() {
        return this.bleSleepDetailses;
    }

    public String toString() {
        return "ReadSleepDetailsRsp{status=" + this.status + ", bleSleepDetailses=" + this.bleSleepDetailses + ", index=" + this.index + '}';
    }
}
