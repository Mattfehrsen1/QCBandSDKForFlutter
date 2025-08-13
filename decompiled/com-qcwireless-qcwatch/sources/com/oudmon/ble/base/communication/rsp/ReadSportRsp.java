package com.oudmon.ble.base.communication.rsp;

import android.util.Log;
import com.oudmon.ble.base.communication.entity.BleSport;
import com.oudmon.ble.base.communication.utils.DataParseUtils;
import com.qcwireless.qc_utils.bytes.DataTransferUtils;

/* loaded from: classes3.dex */
public class ReadSportRsp extends BaseRspCmd {
    private byte[] valueData;
    private int size = 0;
    private int index = 0;
    private BleSport sport = new BleSport();
    private boolean endFlag = false;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        byte b = bArr[0];
        int i = b & 255;
        if (i == 255) {
            this.endFlag = true;
            return false;
        }
        if (i == 0) {
            this.endFlag = false;
            int i2 = bArr[1];
            this.size = i2;
            this.valueData = new byte[i2 * 13];
            Log.i("Jxr35", "0x00.. size: " + this.size + ", valueData: " + this.valueData);
        } else {
            for (int i3 = 1; i3 < bArr.length; i3++) {
                this.valueData[(this.index + i3) - 1] = bArr[i3];
            }
            Log.e("Jxr35", "valueData = " + DataTransferUtils.getHexString(this.valueData));
            Log.i("Jxr35", "0x00.. size: " + this.size + ", valueData: " + this.valueData);
            this.index = this.index + 13;
            if (b == this.size - 1) {
                byte[] bArr2 = new byte[4];
                int iByteArrayToInt = 0;
                for (int i4 = 0; i4 < 7; i4++) {
                    for (int i5 = 0; i5 < 4; i5++) {
                        bArr2[i5] = this.valueData[(i4 * 4) + i5];
                    }
                    switch (i4) {
                        case 0:
                            this.sport.setStartTime(DataParseUtils.byteArrayToInt(bArr2));
                            break;
                        case 1:
                            this.sport.setDuration(DataParseUtils.byteArrayToInt(bArr2));
                            break;
                        case 2:
                            this.sport.setSportType(DataParseUtils.byteArrayToInt(bArr2));
                            break;
                        case 3:
                            this.sport.setStepCount(DataParseUtils.byteArrayToInt(bArr2));
                            break;
                        case 4:
                            this.sport.setDistance(DataParseUtils.byteArrayToInt(bArr2));
                            break;
                        case 5:
                            this.sport.setCalories(DataParseUtils.byteArrayToInt(bArr2));
                            break;
                        case 6:
                            iByteArrayToInt = DataParseUtils.byteArrayToInt(bArr2);
                            break;
                    }
                }
                Log.e("Jxr35", "rateCount = " + iByteArrayToInt);
                int[] iArr = new int[iByteArrayToInt];
                int i6 = 0;
                for (int i7 = 28; i7 < iByteArrayToInt + 28; i7++) {
                    iArr[i6] = this.valueData[i7] & 255;
                    i6++;
                }
                this.sport.setRateValue(iArr);
                return false;
            }
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
