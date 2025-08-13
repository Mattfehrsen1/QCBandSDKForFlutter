package com.baidu.lbsapi.auth;

/* loaded from: classes.dex */
class h implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ g e;

    h(g gVar, String str, int i, String str2, String str3) {
        this.e = gVar;
        this.a = str;
        this.b = i;
        this.c = str2;
        this.d = str3;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        g gVar = this.e;
        gVar.a(gVar.b, this.a, this.b, this.c, this.d);
    }
}
