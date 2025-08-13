package com.baidu.location.f;

import com.baidu.location.b.aa;
import com.baidu.location.b.aj;
import com.baidu.location.b.ap;
import com.baidu.location.f.h;

/* loaded from: classes.dex */
class j implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ h.b b;

    j(h.b bVar, boolean z) {
        this.b = bVar;
        this.a = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (!h.this.l) {
            h.this.l = this.a;
        }
        aa.c().j();
        if (com.baidu.location.indoor.n.a().e()) {
            com.baidu.location.indoor.n.a().a.obtainMessage(41).sendToTarget();
        }
        if (System.currentTimeMillis() - aj.b() <= 5000) {
            ap.a().c();
        }
    }
}
