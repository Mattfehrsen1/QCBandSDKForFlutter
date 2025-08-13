package com.baidu.geofence.a;

/* loaded from: classes.dex */
class g implements Runnable {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.a.k != null) {
            this.a.k.onGeoFenceCreateFinished(null, 10, this.a.l);
        }
    }
}
