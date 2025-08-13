package com.baidu.location.indoor;

import com.baidu.location.b.al;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
class b implements Runnable {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.a.h != null) {
            a aVar = this.a;
            aVar.e = aVar.h;
            ExecutorService executorServiceC = al.a().c();
            if (executorServiceC != null) {
                this.a.a(executorServiceC, com.baidu.location.h.e.s);
            } else {
                this.a.e(com.baidu.location.h.e.s);
            }
        }
    }
}
