package com.oudmon.ble.base.communication.rsp;

import android.util.Log;
import com.oudmon.ble.base.util.DateUtil;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/HRVRsp.class */
public class HRVRsp extends BaseRspCmd {
    private byte[] pressureArray;
    private DateUtil today;
    private int size = 0;
    private int index = 0;
    private boolean endFlag = false;
    private int range = 30;
    private int offset = -1;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        try {
            byte flag = data[0];
            if ((flag & 255) == 255) {
                this.endFlag = true;
                return false;
            }
            if ((flag & 255) == 0) {
                this.endFlag = false;
                this.size = data[1];
                this.range = data[2];
                this.pressureArray = new byte[this.size * 13];
                Log.i("Jxr35", "0x00.. size: " + this.size);
                return true;
            }
            if ((flag & 255) == 1) {
                byte b = data[1];
                this.offset = b;
                this.today = new DateUtil();
                this.today.addDay(-b);
                System.arraycopy(data, 2, this.pressureArray, 0, data.length - 2);
                this.index += data.length - 2;
                return true;
            }
            System.arraycopy(data, 1, this.pressureArray, this.index, data.length - 1);
            this.index += 13;
            if (flag == this.size - 1) {
                this.endFlag = true;
                return false;
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
