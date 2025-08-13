package com.oudmon.ble.base.communication.rsp;

import android.util.Log;
import com.oudmon.ble.base.util.DateUtil;

/* loaded from: classes3.dex */
public class HRVRsp extends BaseRspCmd {
    private byte[] pressureArray;
    private DateUtil today;
    private int size = 0;
    private int index = 0;
    private boolean endFlag = false;
    private int range = 30;
    private int offset = -1;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        try {
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
                this.range = bArr[2];
                this.pressureArray = new byte[i2 * 13];
                Log.i("Jxr35", "0x00.. size: " + this.size);
            } else if (i == 1) {
                byte b2 = bArr[1];
                this.offset = b2;
                DateUtil dateUtil = new DateUtil();
                this.today = dateUtil;
                dateUtil.addDay(-b2);
                System.arraycopy(bArr, 2, this.pressureArray, 0, bArr.length - 2);
                this.index += bArr.length - 2;
            } else {
                System.arraycopy(bArr, 1, this.pressureArray, this.index, bArr.length - 1);
                this.index += 13;
                if (b == this.size - 1) {
                    this.endFlag = true;
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getOffset() {
        return this.offset;
    }

    public boolean isEndFlag() {
        return this.endFlag;
    }

    public byte[] getHrvArray() {
        if (this.pressureArray == null) {
            this.pressureArray = new byte[0];
        }
        return this.pressureArray;
    }

    public int getRange() {
        return this.range;
    }

    public DateUtil getToday() {
        return this.today;
    }
}
