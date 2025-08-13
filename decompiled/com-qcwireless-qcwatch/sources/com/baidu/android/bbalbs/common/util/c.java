package com.baidu.android.bbalbs.common.util;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: classes.dex */
public final class c {
    private static b b = null;
    private static String c = "";
    private static volatile String d = "";
    private final Context a;

    private c(Context context) {
        this.a = context.getApplicationContext();
    }

    static String a() {
        if (TextUtils.isEmpty(c)) {
            c = "0newiqr3mini0";
        }
        return c;
    }

    public static String a(Context context) {
        return b(context).a();
    }

    public static void a(String str) {
        if (!d.a(str, 5)) {
            throw new IllegalArgumentException("expect src only letter or number , less than 6");
        }
        synchronized (b.class) {
            if (TextUtils.isEmpty(d)) {
                d = str;
                int length = 5 - str.length();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("0newiqr3");
                stringBuffer.append(str);
                for (int i = 0; i < length; i++) {
                    stringBuffer.append("0");
                }
                c = stringBuffer.toString().trim();
            }
        }
    }

    private b b() {
        b bVarB = b.b(this.a);
        boolean z = bVarB == null;
        if (bVarB == null) {
            a aVarB = a.b(this.a);
            if (aVarB == null) {
                bVarB = b.a(this.a, a());
            } else {
                aVarB.c();
                bVarB = b.a(aVarB);
            }
        }
        if (z) {
            bVarB.a(this.a);
        }
        a.a(this.a);
        return bVarB;
    }

    private static b b(Context context) {
        if (b == null) {
            synchronized (b.class) {
                if (b == null) {
                    b = new c(context).b();
                }
            }
        }
        return b;
    }
}
