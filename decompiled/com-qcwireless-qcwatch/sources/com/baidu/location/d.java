package com.baidu.location;

import com.baidu.location.h.s;

/* loaded from: classes.dex */
class d extends Thread {
    final /* synthetic */ LocationClient a;

    d(LocationClient locationClient) {
        this.a = locationClient;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            if (this.a.I != null) {
                if (s.h(this.a.f) > 0) {
                    this.a.I.a();
                }
                this.a.I.c();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
