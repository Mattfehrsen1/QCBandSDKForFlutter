package com.baidu.location.f;

import java.util.TimerTask;

/* loaded from: classes.dex */
class d extends TimerTask {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        this.a.c();
    }
}
