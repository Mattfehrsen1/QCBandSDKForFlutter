package com.baidu.location.h;

import android.content.Context;
import android.os.Build;
import com.king.zxing.util.LogUtils;

/* loaded from: classes.dex */
public class b {
    public static String e = null;
    public static String f = null;
    public static String g = null;
    public static String h = null;
    public static String i = null;
    public static int j = 0;
    public static int k = -2;
    public static long l = -1;
    public String a;
    public String b;
    public String c;
    public String d;
    private boolean m;

    private static class a {
        public static final b a = new b();
    }

    private b() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.m = false;
        if (com.baidu.location.f.getServiceContext() != null) {
            a(com.baidu.location.f.getServiceContext());
        }
    }

    public static b a() {
        return a.a;
    }

    private boolean f() {
        return false;
    }

    public String a(boolean z) {
        return a(z, (String) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0118  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String a(boolean r4, java.lang.String r5) {
        /*
            Method dump skipped, instructions count: 309
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.h.b.a(boolean, java.lang.String):java.lang.String");
    }

    public void a(Context context) {
        if (context == null || this.m) {
            return;
        }
        try {
            e = context.getPackageName();
        } catch (Exception unused) {
            e = null;
        }
        try {
            i = s.g(context);
        } catch (Exception unused2) {
            i = null;
        }
        s.n = "" + this.c;
        this.m = true;
    }

    public void a(String str) {
        this.c = str;
        s.n = "" + this.c;
    }

    public void a(String str, String str2) {
        f = str;
        e = str2;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        StringBuilder sb;
        String str;
        if (this.c != null) {
            sb = new StringBuilder();
            sb.append("v9.601|");
            str = this.c;
        } else {
            sb = new StringBuilder();
            sb.append("v9.601|");
            str = this.a;
        }
        sb.append(str);
        sb.append(LogUtils.VERTICAL);
        sb.append(Build.MODEL);
        return sb.toString();
    }

    public String d() {
        String str;
        StringBuffer stringBuffer = new StringBuffer(200);
        if (this.c != null) {
            stringBuffer.append("&cu=");
            str = this.c;
        } else {
            stringBuffer.append("&im=");
            str = this.a;
        }
        stringBuffer.append(str);
        try {
            stringBuffer.append("&mb=");
            stringBuffer.append(Build.MODEL);
        } catch (Exception unused) {
        }
        stringBuffer.append("&pack=");
        try {
            stringBuffer.append(e);
        } catch (Exception unused2) {
        }
        stringBuffer.append("&sdk=");
        stringBuffer.append(9.601f);
        return stringBuffer.toString();
    }

    public String e() {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        if (this.c == null) {
            stringBuffer.append("&im=");
            str = this.a;
        } else {
            stringBuffer.append("&cu=");
            str = this.c;
        }
        stringBuffer.append(str);
        stringBuffer.append("&sdk=");
        stringBuffer.append(9.601f);
        stringBuffer.append("&mb=");
        stringBuffer.append(Build.MODEL);
        stringBuffer.append("&stp=1");
        stringBuffer.append("&os=A");
        stringBuffer.append(Build.VERSION.SDK);
        stringBuffer.append("&prod=");
        stringBuffer.append(f + ":" + e);
        stringBuffer.append(s.f(com.baidu.location.f.getServiceContext()));
        stringBuffer.append("&resid=");
        stringBuffer.append("12");
        return stringBuffer.toString();
    }
}
