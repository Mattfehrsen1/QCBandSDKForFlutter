package com.baidu.lbsapi.auth;

/* loaded from: classes.dex */
class f implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ e e;

    f(e eVar, String str, int i, String str2, String str3) {
        this.e = eVar;
        this.a = str;
        this.b = i;
        this.c = str2;
        this.d = str3;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        b.a("postWithHttps start Thread id = " + String.valueOf(Thread.currentThread().getId()));
        this.e.a(new i(this.e.a).a(this.e.b, this.a, this.b, this.c, this.d));
    }
}
