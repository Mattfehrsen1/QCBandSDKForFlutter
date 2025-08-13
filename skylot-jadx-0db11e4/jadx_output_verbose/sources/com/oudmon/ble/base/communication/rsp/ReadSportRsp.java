package com.oudmon.ble.base.communication.rsp;

import android.util.Log;
import com.oudmon.ble.base.communication.entity.BleSport;
import com.oudmon.ble.base.communication.utils.DataParseUtils;
import com.oudmon.ble.base.util.DataTransferUtils;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/ReadSportRsp.class */
public class ReadSportRsp extends BaseRspCmd {
    private byte[] valueData;
    private int size = 0;
    private int index = 0;
    private BleSport sport = new BleSport();
    private boolean endFlag = false;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        byte flag = data[0];
        if ((flag & 255) == 255) {
            this.endFlag = true;
            return false;
        }
        if ((flag & 255) == 0) {
            this.endFlag = false;
            this.size = data[1];
            this.valueData = new byte[this.size * 13];
            Log.i("Jxr35", "0x00.. size: " + this.size + ", valueData: " + this.valueData);
            return true;
        }
        for (int i = 1; i < data.length; i++) {
            this.valueData[(this.index + i) - 1] = data[i];
        }
        Log.e("Jxr35", "valueData = " + DataTransferUtils.getHexString(this.valueData));
        Log.i("Jxr35", "0x00.. size: " + this.size + ", valueData: " + this.valueData);
        this.index += 13;
        if (flag == this.size - 1) {
            byte[] sub = new byte[4];
            int rateCount = 0;
            for (int i2 = 0; i2 < 7; i2++) {
                for (int j = 0; j < 4; j++) {
                    sub[j] = this.valueData[(i2 * 4) + j];
                }
                switch (i2) {
                    case 0:
                        this.sport.setStartTime(DataParseUtils.byteArrayToInt(sub));
                        break;
                    case 1:
                        this.sport.setDuration(DataParseUtils.byteArrayToInt(sub));
                        break;
                    case 2:
                        this.sport.setSportType(DataParseUtils.byteArrayToInt(sub));
                        break;
                    case 3:
                        this.sport.setStepCount(DataParseUtils.byteArrayToInt(sub));
                        break;
                    case 4:
                        this.sport.setDistance(DataParseUtils.byteArrayToInt(sub));
                        break;
                    case 5:
                        this.sport.setCalories(DataParseUtils.byteArrayToInt(sub));
                        break;
                    case 6:
                        rateCount = DataParseUtils.byteArrayToInt(sub);
                        break;
                }
            }
            Log.e("Jxr35", "rateCount = " + rateCount);
            int[] rateArray = new int[rateCount];
            int index = 0;
            for (int i3 = 28; i3 < rateCount + 28; i3++) {
                int value = this.valueData[i3] & 255;
                rateArray[index] = value;
                index++;
            }
            this.sport.setRateValue(rateArray);
            return false;
        }
        return true;
    }

    public BleSport getBleSport() {
        return this.sport;
    }

    public boolean isEndFlag() {
        return this.endFlag;
    }
}
