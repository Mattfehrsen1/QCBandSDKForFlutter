package com.oudmon.ble.base.communication.utils;

import com.realsil.customer.audioconnect.localplayback.LocalPlaybackTransferEngine;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/utils/ByteUtil.class */
public class ByteUtil {
    protected static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static byte[] intToByte(int i, int len) {
        byte[] abyte;
        if (len == 1) {
            abyte = new byte[len];
            abyte[0] = (byte) (255 & i);
        } else {
            abyte = new byte[len];
            abyte[0] = (byte) (255 & i);
            abyte[1] = (byte) ((65280 & i) >> 8);
            abyte[2] = (byte) ((16711680 & i) >> 16);
            abyte[3] = (byte) (((-16777216) & i) >> 24);
        }
        return abyte;
    }

    public static String byteToBit(byte b) {
        return "" + ((int) ((byte) ((b >> 7) & 1))) + ((int) ((byte) ((b >> 6) & 1))) + ((int) ((byte) ((b >> 5) & 1))) + ((int) ((byte) ((b >> 4) & 1))) + ((int) ((byte) ((b >> 3) & 1))) + ((int) ((byte) ((b >> 2) & 1))) + ((int) ((byte) ((b >> 1) & 1))) + ((int) ((byte) ((b >> 0) & 1)));
    }

    public static int bytes2IntIncludeSignBit(byte[] bytes) {
        int addr = 0;
        if (bytes.length == 1) {
            addr = bytes[0];
        } else if (bytes.length == 4) {
            int addr2 = bytes[3] << 24;
            addr = addr2 | ((bytes[2] << 24) >>> 8) | ((bytes[1] << 24) >>> 16) | ((bytes[0] << 24) >>> 24);
        } else if (bytes.length == 2) {
            int addr3 = bytes[1] << 8;
            addr = addr3 | ((bytes[0] << 24) >>> 24);
        } else if (bytes.length == 3) {
            int addr4 = bytes[2] << 16;
            addr = addr4 | ((bytes[1] << 24) >>> 16) | ((bytes[0] << 24) >>> 24);
        }
        return addr;
    }

    public static byte int2byte(int integer) {
        return (byte) (255 & integer);
    }

    public static byte[] getBooleanArray(byte b) {
        byte[] array = new byte[8];
        for (int i = 7; i >= 0; i--) {
            array[i] = (byte) (b & 1);
            b = (byte) (b >> 1);
        }
        return array;
    }

    public static int bytesToInt(byte[] bytes) {
        int addr = 0;
        if (bytes.length == 1) {
            addr = bytes[0] & 255;
        } else if (bytes.length == 4) {
            int addr2 = bytes[0] & 255;
            addr = addr2 | ((bytes[1] << 8) & 65280) | ((bytes[2] << 16) & 16711680) | ((bytes[3] << 24) & (-16777216));
        } else if (bytes.length == 2) {
            int addr3 = bytes[0] & 255;
            addr = addr3 | ((bytes[1] << 8) & 65280);
        } else if (bytes.length == 3) {
            int addr4 = bytes[0] & 255;
            addr = addr4 | ((bytes[1] << 8) & 65280) | ((bytes[2] << 16) & 16711680);
        }
        return addr;
    }

    public static int byteToInt(byte bytes) {
        return bytes & 255;
    }

    public static byte[] concat(byte[] a, byte[] b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public static int bytesToIntForVersion(byte[] bytes) {
        int addr = 0;
        if (bytes.length == 1) {
            addr = bytes[0] & 255;
        } else if (bytes.length == 4) {
            int addr2 = bytes[3] & 255;
            addr = addr2 | ((bytes[2] << 8) & 65280) | ((bytes[1] << 16) & 16711680) | ((bytes[0] << 24) & (-16777216));
        } else if (bytes.length == 2) {
            int addr3 = bytes[0] & 255;
            addr = addr3 | ((bytes[1] << 8) & 65280);
        } else if (bytes.length == 3) {
            int addr4 = bytes[0] & 255;
            addr = addr4 | ((bytes[1] << 8) & 65280) | ((bytes[2] << 16) & 16711680);
        }
        return addr;
    }

    public static byte[] intToByteBig(int i, int len) {
        byte[] abyte;
        if (len == 1) {
            abyte = new byte[len];
            abyte[0] = (byte) (255 & i);
        } else {
            abyte = new byte[len];
            abyte[0] = (byte) ((i >>> 24) & 255);
            abyte[1] = (byte) ((i >>> 16) & 255);
            abyte[2] = (byte) ((i >>> 8) & 255);
            abyte[3] = (byte) (i & 255);
        }
        return abyte;
    }

    public static int bytesToIntBig(byte[] bytes) {
        int addr;
        if (bytes.length == 1) {
            addr = bytes[0] & 255;
        } else {
            int addr2 = bytes[0] & 255;
            addr = (((((addr2 << 8) | (bytes[1] & 255)) << 8) | (bytes[2] & 255)) << 8) | (bytes[3] & 255);
        }
        return addr;
    }

    public static String bytesToString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder(bytes.length);
        for (byte byteChar : bytes) {
            stringBuilder.append(String.format("%02X", Byte.valueOf(byteChar)));
        }
        return stringBuilder.toString();
    }

    public static String bytesToStringFormat(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder(bytes.length);
        for (byte byteChar : bytes) {
            stringBuilder.append(String.format("%01X", Byte.valueOf(byteChar)));
        }
        return stringBuilder.toString();
    }

    public static int loword(int i) {
        return i & LocalPlaybackTransferEngine.MAX_SEQUENCE_NUMBER;
    }

    public static int hiword(int i) {
        return i >>> 8;
    }

    public static String binaryString2hexString(String bString) {
        if (bString == null || bString.equals("") || bString.length() % 8 != 0) {
            return null;
        }
        StringBuffer tmp = new StringBuffer();
        for (int i = 0; i < bString.length(); i += 4) {
            int iTmp = 0;
            for (int j = 0; j < 4; j++) {
                iTmp += Integer.parseInt(bString.substring(i + j, (i + j) + 1)) << ((4 - j) - 1);
            }
            tmp.append(Integer.toHexString(iTmp));
        }
        return tmp.toString();
    }

    public static String byteArrayToString(byte[] copyOfRange) {
        StringBuilder sb = new StringBuilder();
        for (byte b : copyOfRange) {
            String i = Integer.toHexString(b & 255);
            sb.append(i.length() == 1 ? "0" + i : i);
        }
        return sb.toString();
    }

    public static byte[] byteToBitArray(int b) {
        byte[] array = {(byte) ((b >> 7) & 1), (byte) ((b >> 6) & 1), (byte) ((b >> 5) & 1), (byte) ((b >> 4) & 1), (byte) ((b >> 3) & 1), (byte) ((b >> 2) & 1), (byte) ((b >> 1) & 1), (byte) ((b >> 0) & 1)};
        return array;
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[(j * 2) + 1] = hexArray[v & 15];
        }
        return new String(hexChars);
    }

    public static String bytesToString1(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(bytes.length);
        for (byte byteChar : bytes) {
            stringBuilder.append(String.format("%02X ", Byte.valueOf(byteChar)));
        }
        return stringBuilder.toString();
    }

    public static byte[] hexToBytes(String hexStrings) {
        if (hexStrings == null || hexStrings.equals("")) {
            return null;
        }
        String hexString = hexStrings.toLowerCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            int h = "0123456789abcdef".indexOf(hexChars[pos]) << 4;
            int l = "0123456789abcdef".indexOf(hexChars[pos + 1]);
            if (h == -1 || l == -1) {
                return null;
            }
            bytes[i] = (byte) (h | l);
        }
        return bytes;
    }

    public static String byteAsciiToChar(int... ascii) {
        String str = "";
        for (int i : ascii) {
            char ch = (char) i;
            str = str + ch;
        }
        return str;
    }
}
