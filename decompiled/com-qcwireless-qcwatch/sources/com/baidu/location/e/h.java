package com.baidu.location.e;

import com.baidu.location.b.al;
import com.baidu.location.e.f;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
class h implements Runnable {
    final /* synthetic */ f.a a;

    h(f.a aVar) {
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        ExecutorService executorServiceC = al.a().c();
        if (executorServiceC != null) {
            this.a.a(executorServiceC, "https://ofloc.map.baidu.com/offline_loc");
        } else {
            this.a.e("https://ofloc.map.baidu.com/offline_loc");
        }
    }
}
