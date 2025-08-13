package com.oudmon.ble.base.communication.utils;

/* loaded from: classes3.dex */
public class BLEDataFormatUtils {
    public static int BCDToDecimal(byte b) {
        return (((b >> 4) & 15) * 10) + (b & 15);
    }

    public static byte decimalToBCD(int i) {
        return (byte) (((i / 10) << 4) | (i % 10));
    }

    public static int bytes2Int(byte[] bArr) {
        int length = bArr.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i |= (bArr[i2] & 255) << (((length - 1) - i2) * 8);
        }
        return i;
    }
}
