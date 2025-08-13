package com.baidu.location.b;

import android.os.Build;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
class f implements Runnable {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (Build.VERSION.SDK_INT >= 17) {
            ExecutorService executorServiceC = al.a().c();
            if (executorServiceC != null) {
                this.a.a(executorServiceC, com.baidu.location.h.e.f);
            } else {
                this.a.e(com.baidu.location.h.e.f);
            }
        }
    }
}
