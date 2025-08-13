package com.baidu.location.f.a;

import com.baidu.location.f.a.d;
import com.baidu.location.f.k;

/* loaded from: classes.dex */
class f implements Runnable {
    final /* synthetic */ d.b a;

    f(d.b bVar) {
        this.a = bVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        d.this.i();
        synchronized (d.this.n) {
            d.this.n.notifyAll();
            if (com.baidu.location.f.b.a.c && com.baidu.location.f.b.a.d) {
                k.h().a("WifiScan finished, in callback.");
            }
        }
    }
}
