package com.baidu.location.b;

import android.location.Location;
import java.io.IOException;

/* loaded from: classes.dex */
class p implements Runnable {
    final /* synthetic */ Location a;
    final /* synthetic */ o b;

    p(o oVar, Location location) {
        this.b = oVar;
        this.a = location;
    }

    @Override // java.lang.Runnable
    public void run() throws IOException {
        this.b.b(this.a);
    }
}
