package com.oudmon.ble.base.communication.utils;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/utils/DataParseUtils.class */
public class DataParseUtils {
    public static byte[] intToByteArray(int num) {
        int temp = num;
        byte[] b = new byte[4];
        for (int i = 0; i < b.length; i++) {
            b[i] = Long.valueOf(temp & 255).byteValue();
            temp >>= 8;
        }
        return b;
    }

    public static int byteArrayToInt(byte[] b) {
        int s0 = b[0] & 255;
        int s1 = b[1] & 255;
        int s2 = b[2] & 255;
        int s3 = b[3] & 255;
        int s = s0 | (s1 << 8) | (s2 << 16) | (s3 << 24);
        return s;
    }
}
