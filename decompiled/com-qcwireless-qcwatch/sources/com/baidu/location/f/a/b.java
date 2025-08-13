package com.baidu.location.f.a;

import com.baidu.location.f.k;

/* loaded from: classes.dex */
class b implements Runnable {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.f();
            synchronized (this.a.I) {
                this.a.I.notifyAll();
                if (com.baidu.location.f.b.a.c && com.baidu.location.f.b.a.d) {
                    k.h().a("update mCellInfo completed");
                }
            }
        } catch (Exception e) {
            if (com.baidu.location.f.b.a.c && com.baidu.location.f.b.a.d) {
                k.h().a("handleCellInfo error = " + e);
            }
        }
    }
}
