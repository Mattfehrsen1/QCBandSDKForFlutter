package com.baidu.location.f.a;

import com.baidu.location.f.a.a;
import com.baidu.location.f.k;

/* loaded from: classes.dex */
class c implements Runnable {
    final /* synthetic */ a.d a;

    c(a.d dVar) {
        this.a = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (com.baidu.location.f.b.a.c && com.baidu.location.f.b.a.d) {
                k.h().a("cell received cellinfo change");
            }
            a.this.f();
        } catch (Exception e) {
            if (com.baidu.location.f.b.a.c) {
                e.printStackTrace();
            }
        }
    }
}
