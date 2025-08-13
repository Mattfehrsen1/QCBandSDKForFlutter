package com.baidu.location.h;

/* loaded from: classes.dex */
class n implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ h b;

    n(h hVar, String str) {
        this.b = hVar;
        this.a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.a(this.a);
    }
}
