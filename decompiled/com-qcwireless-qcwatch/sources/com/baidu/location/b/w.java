package com.baidu.location.b;

import android.util.Base64;
import com.baidu.location.Jni;
import com.bumptech.glide.load.Key;
import com.king.zxing.util.LogUtils;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class w {
    private boolean a;
    private String[] b;

    private static class a {
        private static w a = new w();
    }

    private w() {
        this.a = false;
        this.b = null;
        try {
            String str = Jni.getldkaiv();
            if (str == null || !str.contains(LogUtils.VERTICAL)) {
                return;
            }
            String[] strArrSplit = str.split("\\|");
            this.b = strArrSplit;
            if (strArrSplit == null || strArrSplit.length != 2) {
                return;
            }
            this.a = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static w a() {
        return a.a;
    }

    public synchronized String a(String str) {
        if (this.a) {
            try {
                IvParameterSpec ivParameterSpec = new IvParameterSpec(this.b[1].getBytes(Key.STRING_CHARSET_NAME));
                SecretKeySpec secretKeySpec = new SecretKeySpec(this.b[0].getBytes(Key.STRING_CHARSET_NAME), "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                cipher.init(1, secretKeySpec, ivParameterSpec);
                return Base64.encodeToString(cipher.doFinal(str.getBytes()), 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public synchronized String b(String str) {
        if (!this.a) {
            return null;
        }
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(this.b[1].getBytes(Key.STRING_CHARSET_NAME));
            SecretKeySpec secretKeySpec = new SecretKeySpec(this.b[0].getBytes(Key.STRING_CHARSET_NAME), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(2, secretKeySpec, ivParameterSpec);
            return new String(cipher.doFinal(Base64.decode(str, 0)), Key.STRING_CHARSET_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean b() {
        return this.a;
    }
}
