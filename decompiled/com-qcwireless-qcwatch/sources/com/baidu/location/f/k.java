package com.baidu.location.f;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.telephony.TelephonyManager;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes.dex */
public class k {
    private static boolean d = false;
    private static g e;
    private boolean a = true;
    private boolean b = true;
    private boolean c = false;

    public enum a {
        ONLY_CELL_MODE,
        ONLY_WIFI_MODE,
        GET_ALL_DATA
    }

    public static boolean a() {
        return d;
    }

    public static g h() {
        return e;
    }

    public com.baidu.location.f.a a(com.baidu.location.f.a aVar, TelephonyManager telephonyManager) {
        return com.baidu.location.f.a.a.a().b(aVar, telephonyManager);
    }

    public String a(int i, boolean z, p pVar, int i2) {
        return com.baidu.location.f.a.d.a().a(i, z, pVar, i2);
    }

    public String a(WifiInfo wifiInfo, String str) {
        return com.baidu.location.f.a.d.a().a(wifiInfo, str);
    }

    public String a(com.baidu.location.f.a aVar) {
        return com.baidu.location.f.a.a.a().a(aVar);
    }

    public String a(p pVar, int i, String str, boolean z, int i2) {
        return com.baidu.location.f.a.d.a().a(pVar, i, str, z, i2);
    }

    public void a(int i) {
        if (i >= 0) {
            com.baidu.location.f.a.a.a().a(i);
        }
    }

    public void a(Context context, List<String> list) {
        if (this.a) {
            com.baidu.location.f.a.a.a().a(context);
        }
        if (this.b) {
            com.baidu.location.f.a.d.a().a(context, list);
        }
        this.c = true;
    }

    public void a(g gVar) {
        e = gVar;
    }

    public void a(a aVar) {
        int i = l.a[aVar.ordinal()];
        if (i == 1) {
            this.a = true;
            this.b = false;
            return;
        }
        if (i == 2) {
            this.a = false;
        } else {
            if (i != 3) {
                throw new IllegalArgumentException("Illegal this mode : " + aVar);
            }
            this.a = true;
        }
        this.b = true;
    }

    public void a(boolean z) {
        com.baidu.location.f.a.a.a().a(z);
    }

    public boolean a(com.baidu.location.f.a aVar, com.baidu.location.f.a aVar2) {
        return com.baidu.location.f.a.a.a().a(aVar, aVar2);
    }

    public HashSet<String> b(com.baidu.location.f.a aVar) {
        return com.baidu.location.f.a.a.a().c(aVar);
    }

    public void b() {
        if (this.a) {
            com.baidu.location.f.a.a.a().b();
        }
        if (this.b) {
            com.baidu.location.f.a.d.a().b();
        }
        this.c = false;
    }

    public void b(int i) {
        if (i >= 0) {
            com.baidu.location.f.a.d.a().a(i);
        }
    }

    public void b(boolean z) {
        com.baidu.location.f.a.a.a().b(z);
    }

    public long c() {
        return com.baidu.location.f.a.d.a().c();
    }

    public String c(com.baidu.location.f.a aVar) {
        return com.baidu.location.f.a.a.a().b(aVar);
    }

    public void c(int i) {
        com.baidu.location.f.a.a.a().b(Math.max(i, 29));
    }

    public com.baidu.location.f.a d(int i) {
        if (this.c && this.a) {
            return com.baidu.location.f.a.a.a().c(i);
        }
        return null;
    }

    public boolean d() {
        return com.baidu.location.f.a.a.a().c();
    }

    public p e(int i) {
        if (this.c && this.b) {
            return com.baidu.location.f.a.d.a().a(i);
        }
        return null;
    }

    public String e() {
        return com.baidu.location.f.a.d.a().e();
    }

    public WifiInfo f() {
        return com.baidu.location.f.a.d.a().f();
    }

    public p g() {
        return com.baidu.location.f.a.d.a().d();
    }
}
