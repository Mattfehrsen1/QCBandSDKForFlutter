package com.baidu.geofence.a;

/* loaded from: classes.dex */
class e implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ b b;

    e(b bVar, int i) {
        this.b = bVar;
        this.a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.b.f != null) {
            this.b.f.a(this.a);
        }
    }
}
