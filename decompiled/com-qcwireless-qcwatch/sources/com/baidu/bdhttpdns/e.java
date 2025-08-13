package com.baidu.bdhttpdns;

import android.util.Base64;
import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* loaded from: classes.dex */
final class e {
    private static String b = a();
    private static Pattern a = Pattern.compile("^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$");

    private static String a() {
        try {
            byte[] bArr = new byte[20];
            SecureRandom.getInstance("SHA1PRNG").nextBytes(bArr);
            return a(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String a(String str, byte[] bArr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(1, h(str), new IvParameterSpec("01020304".getBytes()));
            return Base64.encodeToString(cipher.doFinal(bArr), 0);
        } catch (Exception unused) {
            return null;
        }
    }

    private static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b2 : bArr) {
            a(stringBuffer, b2);
        }
        return stringBuffer.toString();
    }

    private static void a(StringBuffer stringBuffer, byte b2) {
        stringBuffer.append("0123456789ABCDEF".charAt((b2 >> 4) & 15));
        stringBuffer.append("0123456789ABCDEF".charAt(b2 & 15));
    }

    static boolean a(String str) {
        return a.matcher(str).matches();
    }

    private static String b(String str, byte[] bArr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(2, h(str), new IvParameterSpec("01020304".getBytes()));
            return new String(cipher.doFinal(bArr));
        } catch (Exception unused) {
            return null;
        }
    }

    static boolean b(String str) {
        String strReplaceAll = str.replaceAll("[\\[\\]]", "");
        return c(strReplaceAll) || d(strReplaceAll);
    }

    public static boolean c(String str) {
        return Pattern.matches("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$", str);
    }

    public static boolean d(String str) {
        return Pattern.matches("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$", str);
    }

    static String e(String str) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(str.getBytes(Key.STRING_CHARSET_NAME));
            StringBuilder sb = new StringBuilder(bArrDigest.length * 2);
            for (byte b2 : bArrDigest) {
                int i = b2 & 255;
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    static String f(String str) {
        return a(b, str.getBytes());
    }

    static String g(String str) {
        return b(b, Base64.decode(str, 0));
    }

    private static java.security.Key h(String str) {
        return SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes()));
    }
}
