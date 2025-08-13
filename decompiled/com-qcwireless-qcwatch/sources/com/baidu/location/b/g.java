package com.baidu.location.b;

/* loaded from: classes.dex */
class g implements Runnable {
    final /* synthetic */ e a;

    g(e eVar) {
        this.a = eVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (com.baidu.location.h.s.i(com.baidu.location.f.getServiceContext())) {
            this.a.c();
        } else {
            this.a.j();
        }
    }
}
