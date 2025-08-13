package com.oudmon.ble.base.util;

import com.realsil.customer.bbpro.equalizer.EqConstants;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/util/DataTransferUtils.class */
public class DataTransferUtils {
    public static String getHexString(byte[] data) {
        StringBuilder s = new StringBuilder();
        if (data != null) {
            for (byte aData : data) {
                String hex = Integer.toHexString(aData & 255);
                if (hex.length() == 1) {
                    hex = '0' + hex;
                }
                s.append(hex);
            }
        }
        return s.toString();
    }

    public static byte[] intToBytes(int value) {
        byte[] src = {(byte) (value & 255), (byte) ((value >> 8) & 255), (byte) ((value >> 16) & 255), (byte) ((value >> 24) & 255)};
        return src;
    }

    public static int bytesToInt(byte[] src, int offset) {
        return (src[offset] & 255) | ((src[offset + 1] & 255) << 8) | ((src[offset + 2] & 255) << 16) | ((src[offset + 3] & 255) << 24);
    }

    public static int byte2Int(byte[] src, int offset) {
        return (src[offset] & 255) | ((src[offset + 1] & 255) << 8);
    }

    public static short bytesToShort(byte[] src, int offset) {
        return (short) ((src[offset] & 255) | ((src[offset + 1] & 255) << 8));
    }

    public static byte[] shortToBytes(short value) {
        byte[] src = {(byte) (value & 255), (byte) ((value >> 8) & 255)};
        return src;
    }

    public static int arrays2Int(byte[] data) {
        if (data.length == 1) {
            return 255 & data[0];
        }
        if (data.length == 2) {
            return bytesToShort(data, 0);
        }
        if (data.length == 4) {
            return bytesToInt(data, 0);
        }
        return -1;
    }

    public static float bytes2Float(byte[] data, int offset) {
        return Float.intBitsToFloat(bytesToInt(data, offset));
    }

    public static int enableWeek(int week) {
        return week & EqConstants.CodeIndex.BUILD_IN_EQ_4;
    }
}
