package com.oudmon.ble.base.communication.utils;

/* loaded from: classes3.dex */
public class CRC16 {
    public static int calcCrc16(byte[] bArr) {
        if (bArr.length == 0) {
            return 65535;
        }
        int i = 65535;
        for (byte b : bArr) {
            i ^= b & 255;
            for (int i2 = 0; i2 < 8; i2++) {
                i = (i & 1) != 0 ? (i >> 1) ^ 40961 : i >> 1;
            }
        }
        return i & 65535;
    }
}
