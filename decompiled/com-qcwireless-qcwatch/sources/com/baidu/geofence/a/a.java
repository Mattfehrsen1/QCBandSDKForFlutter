package com.baidu.geofence.a;

import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public class a {
    private static String a(String str) throws NoSuchAlgorithmException {
        String string = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] bArrDigest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < bArrDigest.length; i++) {
                int i2 = bArrDigest[i];
                if (i2 < 0) {
                    i2 += 256;
                }
                if (i2 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i2));
            }
            string = stringBuffer.toString();
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return string;
        }
    }

    public static String a(HashMap<String, String> map, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(b(map, str));
        return a(stringBuffer.toString() + "99754106633f94d350db34d548d6091a");
    }

    private static String b(HashMap<String, String> map, String str) throws UnsupportedEncodingException {
        Iterator<String> it = map.keySet().iterator();
        ArrayList arrayList = new ArrayList();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        Collections.sort(arrayList);
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (i < arrayList.size()) {
            String str2 = (String) arrayList.get(i);
            stringBuffer.append(str2);
            stringBuffer.append("=");
            String strEncode = null;
            if (str == null) {
                try {
                    strEncode = URLEncoder.encode(map.get(str2), Key.STRING_CHARSET_NAME);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (NullPointerException e2) {
                    e2.printStackTrace();
                }
            } else {
                strEncode = URLEncoder.encode(map.get(str2), Key.STRING_CHARSET_NAME).replaceAll("\\+", "%20").replaceAll("\\%7E", "~");
            }
            stringBuffer.append(strEncode);
            i++;
            if (i < arrayList.size() && str != null) {
                stringBuffer.append(str);
            }
        }
        return stringBuffer.toString();
    }
}
