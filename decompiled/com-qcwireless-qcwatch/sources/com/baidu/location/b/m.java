package com.baidu.location.b;

import com.baidu.location.b.k;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
class m extends TimerTask {
    final /* synthetic */ Timer a;
    final /* synthetic */ k.b b;

    m(k.b bVar, Timer timer) {
        this.b = bVar;
        this.a = timer;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        if (!this.b.d) {
            this.b.c();
        }
        this.a.cancel();
        this.a.purge();
    }
}
