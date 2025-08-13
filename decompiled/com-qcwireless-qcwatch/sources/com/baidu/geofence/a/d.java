package com.baidu.geofence.a;

/* loaded from: classes.dex */
class d implements Runnable {
    final /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.a.e != null) {
            this.a.e.onGeoFenceCreateFinished(null, 9, null);
        }
    }
}
