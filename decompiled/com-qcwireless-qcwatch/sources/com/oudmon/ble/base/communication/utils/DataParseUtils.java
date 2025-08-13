package com.oudmon.ble.base.communication.utils;

/* loaded from: classes3.dex */
public class DataParseUtils {
    public static byte[] intToByteArray(int i) {
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2] = Long.valueOf(i & 255).byteValue();
            i >>= 8;
        }
        return bArr;
    }

    public static int byteArrayToInt(byte[] bArr) {
        int i = bArr[0] & 255;
        int i2 = bArr[1] & 255;
        return ((bArr[3] & 255) << 24) | i | (i2 << 8) | ((bArr[2] & 255) << 16);
    }
}
