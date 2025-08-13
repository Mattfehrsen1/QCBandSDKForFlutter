package com.realsil.customer.core.utility;

import android.os.Build;
import com.realsil.customer.core.logger.ZLogger;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Objects;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/utility/DataConverter.class */
public final class DataConverter {
    public static String str2Hex(String str) {
        return str2Hex(str, Charset.defaultCharset().name(), true);
    }

    public static String hex2Str(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        String upperCase = str.toUpperCase();
        char[] charArray = upperCase.toCharArray();
        int length = upperCase.length() / 2;
        byte[] bArr = new byte[length];
        if (upperCase.length() % 2 == 1) {
            return "";
        }
        for (int i = 0; i < upperCase.length(); i++) {
            char c = charArray[i];
            if ((c < '0' || c > '9') && (c < 'A' || c > 'F')) {
                return "";
            }
        }
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = (byte) (("0123456789ABCDEF".indexOf(charArray[i3 + 1]) + ("0123456789ABCDEF".indexOf(charArray[i3]) * 16)) & 255);
        }
        return new String(bArr);
    }

    public static String str2Unicode(String str) throws Exception {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            String hexString = Integer.toHexString(cCharAt);
            if (cCharAt > 128) {
                sb.append("\\u" + hexString);
            } else {
                sb.append("\\u00" + hexString);
            }
        }
        return sb.toString();
    }

    public static String unicode2Str(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        int length = str.length() / 6;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < length) {
            int i2 = i * 6;
            int i3 = i + 1;
            i = i3;
            String strSubstring = str.substring(i2, i3 * 6);
            String str2 = strSubstring.substring(2, 4) + "00";
            sb.append(new String(Character.toChars(Integer.valueOf(strSubstring.substring(4), 16).intValue() + Integer.valueOf(str2, 16).intValue())));
        }
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v5, types: [byte[]] */
    public static byte[] str2Bytes(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return new byte[0];
        }
        Object bytes = str;
        byte[] bArr = new byte[bytes.length() / 2];
        try {
            bytes = bytes.getBytes("US-ASCII");
            bArr = bytes;
        } catch (UnsupportedEncodingException unused) {
            ZLogger.w(bytes.toString());
        }
        return bArr;
    }

    public static String bytes2Str(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append((char) b);
        }
        return sb.toString();
    }

    public static String bytes2HexWithSeparate(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder("");
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            String strConcat = hexString;
            if (hexString.length() == 1) {
                strConcat = "0".concat(strConcat);
            }
            sb.append(strConcat);
            sb.append(" ");
        }
        return sb.toString().toUpperCase().trim();
    }

    public static String bytes2Hex(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder("");
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            String strConcat = hexString;
            if (hexString.length() == 1) {
                strConcat = "0".concat(strConcat);
            }
            sb.append(strConcat);
        }
        return sb.toString().toUpperCase().trim();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    public static byte[] hex2Bytes(String str) {
        ?? IsEmpty = StringUtils.isEmpty(str);
        if (IsEmpty != 0) {
            return new byte[0];
        }
        try {
            int length = str.length();
            byte[] bArr = new byte[length / 2];
            String upperCase = str.toUpperCase();
            char[] charArray = upperCase.toCharArray();
            if (length % 2 == 1) {
                return null;
            }
            for (int i = 0; i < length; i++) {
                char c = charArray[i];
                if ((c < '0' || c > '9') && (c < 'A' || c > 'F')) {
                    return null;
                }
            }
            for (int i2 = 0; i2 < length; i2 += 2) {
                IsEmpty = bArr;
                int i3 = i2;
                IsEmpty[i3 / 2] = (byte) ((Character.digit(upperCase.charAt(i3), 16) << 4) + Character.digit(upperCase.charAt(i2 + 1), 16));
            }
            return bArr;
        } catch (Exception unused) {
            IsEmpty.printStackTrace();
            return null;
        }
    }

    public static byte[] hex2BigBytes(String str) {
        if (StringUtils.isEmpty(str)) {
            return new byte[0];
        }
        int length = str.length();
        int i = length / 2;
        byte[] bArr = new byte[i];
        String upperCase = str.toUpperCase();
        char[] charArray = upperCase.toCharArray();
        if (length % 2 == 1) {
            return null;
        }
        for (int i2 = 0; i2 < length; i2++) {
            char c = charArray[i2];
            if ((c < '0' || c > '9') && (c < 'A' || c > 'F')) {
                return null;
            }
        }
        for (int i3 = 0; i3 < length; i3 += 2) {
            bArr[(i - (i3 / 2)) - 1] = (byte) (Character.digit(upperCase.charAt(i3 + 1), 16) + (Character.digit(upperCase.charAt(i3), 16) << 4));
        }
        return bArr;
    }

    public static String ellipsize(String str) {
        return str == null ? "" : str.length() < 3 ? str : str.charAt(0) + "⋯" + str.charAt(str.length() - 1);
    }

    public static void copyStream(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        if (inputStream == null || outputStream == null) {
            return;
        }
        byte[] bArr = new byte[i];
        while (true) {
            int i2 = inputStream.read(bArr);
            if (i2 < 0) {
                return;
            } else {
                outputStream.write(bArr, 0, i2);
            }
        }
    }

    public static void safeCloseStream(InputStream inputStream) {
        if (inputStream == null) {
            return;
        }
        try {
            inputStream.close();
        } catch (Throwable th) {
            ZLogger.w("Error closing stream: " + th.toString());
        }
    }

    public static int unsignedByteToInt(byte b) {
        return b & 255;
    }

    public static int littleEndianByteArrayToInt(byte[] bArr) {
        int length = bArr.length;
        if (length == 0) {
            return 0;
        }
        int iUnsignedByteToInt = 0;
        for (int i = length - 1; i >= 0; i--) {
            iUnsignedByteToInt += unsignedByteToInt(bArr[i]) << (i * 8);
        }
        return iUnsignedByteToInt;
    }

    public static byte[] reverse(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        for (int length = bArr.length - 1; length >= 0; length--) {
            bArr2[i] = bArr[length];
            i++;
        }
        return bArr2;
    }

    public static boolean equals(Object obj, Object obj2) {
        return Build.VERSION.SDK_INT >= 19 ? Objects.equals(obj, obj2) : obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3 */
    public static String str2Hex(String str, String str2, boolean z) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        ?? bytes = str;
        char[] charArray = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        try {
            bytes = bytes.getBytes(str2);
            int length = bytes.length;
            for (int i = 0; i < length; i++) {
                ?? r3 = bytes[i];
                sb.append(charArray[((r3 == true ? 1 : 0) & 240) >> 4]);
                sb.append(charArray[(r3 == true ? 1 : 0) & 15]);
                if (z) {
                    sb.append(' ');
                }
            }
            return sb.toString().trim();
        } catch (UnsupportedEncodingException unused) {
            bytes.printStackTrace();
            return "";
        }
    }

    public static void safeCloseStream(OutputStream outputStream) {
        if (outputStream == null) {
            return;
        }
        try {
            outputStream.close();
        } catch (Throwable th) {
            ZLogger.w("Error closing stream: " + th.toString());
        }
    }

    public static String hex2Str(String str, String str2) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        String upperCase = str.toUpperCase();
        char[] charArray = upperCase.toCharArray();
        int length = upperCase.length() / 2;
        byte[] bArr = new byte[length];
        if (upperCase.length() % 2 == 1) {
            return "";
        }
        for (int i = 0; i < upperCase.length(); i++) {
            char c = charArray[i];
            if ((c < '0' || c > '9') && (c < 'A' || c > 'F')) {
                return "";
            }
        }
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = (byte) (("0123456789ABCDEF".indexOf(charArray[i3 + 1]) + ("0123456789ABCDEF".indexOf(charArray[i3]) * 16)) & 255);
        }
        try {
            return new String(bArr, str2);
        } catch (UnsupportedEncodingException unused) {
            return new String(bArr);
        }
    }
}
