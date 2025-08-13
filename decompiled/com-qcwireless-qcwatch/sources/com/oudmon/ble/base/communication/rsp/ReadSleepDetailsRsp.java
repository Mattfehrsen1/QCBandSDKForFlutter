package com.oudmon.ble.base.communication.rsp;

import android.util.Log;
import com.oudmon.ble.base.communication.entity.BleSleepDetails;
import com.oudmon.ble.base.communication.utils.BLEDataFormatUtils;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class ReadSleepDetailsRsp extends BaseRspCmd {
    private ArrayList<BleSleepDetails> bleSleepDetailses = new ArrayList<>();
    private int index = 0;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        byte b = bArr[0];
        int i = this.index;
        if (i == 0 && (b & 255) == 255) {
            this.bleSleepDetailses.clear();
            return false;
        }
        if (i == 0 && (b & 255) == 240) {
            Log.i("Jxr35", "acceptData: init data list");
            this.bleSleepDetailses.clear();
            this.index++;
        } else {
            BleSleepDetails bleSleepDetails = new BleSleepDetails();
            bleSleepDetails.setYear(BLEDataFormatUtils.BCDToDecimal(bArr[0]) + 2000);
            bleSleepDetails.setMonth(BLEDataFormatUtils.BCDToDecimal(bArr[1]));
            bleSleepDetails.setDay(BLEDataFormatUtils.BCDToDecimal(bArr[2]));
            bleSleepDetails.setTimeIndex(bArr[3]);
            int[] iArr = new int[8];
            for (int i2 = 1; i2 < 8; i2++) {
                iArr[i2] = bArr[i2 + 5] & 255;
            }
            bleSleepDetails.setSleepQualities(iArr);
            this.bleSleepDetailses.add(bleSleepDetails);
            this.index++;
            if (bArr[4] == bArr[5] - 1) {
                return false;
            }
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
