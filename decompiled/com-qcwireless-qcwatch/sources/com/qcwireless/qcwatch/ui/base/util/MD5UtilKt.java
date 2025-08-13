package com.qcwireless.qcwatch.ui.base.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: MD5Util.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001¨\u0006\u0003"}, d2 = {"encode", "", "text", "app_qwatch_proRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MD5UtilKt {
    public static final String encode(String text) throws NoSuchAlgorithmException {
        Intrinsics.checkNotNullParameter(text, "text");
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            Intrinsics.checkNotNullExpressionValue(messageDigest, "getInstance(\"MD5\")");
            byte[] bytes = text.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            byte[] bArrDigest = messageDigest.digest(bytes);
            Intrinsics.checkNotNullExpressionValue(bArrDigest, "instance.digest(text.toByteArray())");
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bArrDigest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() < 2) {
                    hexString = '0' + hexString;
                }
                stringBuffer.append(hexString);
            }
            String string = stringBuffer.toString();
            Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
            return string;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
