package com.baidu.geofence.a;

import java.util.ArrayList;

/* loaded from: classes.dex */
class i implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ ArrayList b;
    final /* synthetic */ f c;

    i(f fVar, int i, ArrayList arrayList) {
        this.c = fVar;
        this.a = i;
        this.b = arrayList;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.c.o != null) {
            this.c.o.a(this.a, this.b);
        }
    }
}
