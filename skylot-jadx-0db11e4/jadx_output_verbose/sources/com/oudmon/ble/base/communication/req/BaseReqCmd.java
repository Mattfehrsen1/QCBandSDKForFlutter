package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/BaseReqCmd.class */
public abstract class BaseReqCmd {
    protected static final String TAG = "Jxr35";
    protected byte key;

    protected abstract byte[] getSubData();

    public BaseReqCmd(byte key) {
        this.key = key;
    }

    public byte[] getData() {
        byte[] bytes = new byte[Constants.CMD_DATA_LENGTH];
        bytes[0] = this.key;
        byte[] subData = getSubData();
        if (subData != null) {
            System.arraycopy(subData, 0, bytes, 1, subData.length);
        }
        addCRC(bytes);
        return bytes;
    }

    private void addCRC(byte[] data) {
        int crc = 0;
        for (int i = 0; i < data.length - 1; i++) {
            crc += data[i];
        }
        data[data.length - 1] = (byte) (crc & 255);
    }
}
