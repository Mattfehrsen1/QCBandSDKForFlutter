package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes3.dex */
public abstract class BaseReqCmd {
    protected static final String TAG = "Jxr35";
    protected byte key;
    protected int type;

    protected abstract byte[] getSubData();

    public BaseReqCmd(byte b) {
        this.key = b;
    }

    public byte[] getData() {
        byte[] bArr = new byte[Constants.CMD_DATA_LENGTH];
        bArr[0] = this.key;
        byte[] subData = getSubData();
        if (subData != null) {
            System.arraycopy(subData, 0, bArr, 1, subData.length);
        }
        addCRC(bArr);
        return bArr;
    }

    private void addCRC(byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < bArr.length - 1; i2++) {
            i += bArr[i2];
        }
        bArr[bArr.length - 1] = (byte) (i & 255);
    }
}
