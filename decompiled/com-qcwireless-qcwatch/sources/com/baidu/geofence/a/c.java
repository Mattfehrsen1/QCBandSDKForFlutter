package com.baidu.geofence.a;

/* loaded from: classes.dex */
class c implements Runnable {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.a.e != null) {
            this.a.e.onGeoFenceCreateFinished(null, 10, null);
        }
    }
}
