package com.baidu.geofence.a;

/* loaded from: classes.dex */
class h implements Runnable {
    final /* synthetic */ f a;

    h(f fVar) {
        this.a = fVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.a.k != null) {
            this.a.k.onGeoFenceCreateFinished(null, 9, this.a.l);
        }
    }
}
