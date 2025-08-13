package com.baidu.location.indoor;

import com.baidu.location.BDLocation;
import com.baidu.location.indoor.y;

/* loaded from: classes.dex */
class z implements Runnable {
    final /* synthetic */ y a;

    z(y yVar) {
        this.a = yVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        y yVar = this.a;
        y.b bVarA = yVar.a(yVar.e);
        if (bVarA != null && this.a.a != null) {
            y yVar2 = this.a;
            yVar2.e = yVar2.e.b(bVarA);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (!bVarA.b(2.0E-6d) && jCurrentTimeMillis - this.a.k > this.a.b) {
                BDLocation bDLocation = new BDLocation(this.a.c);
                bDLocation.setLatitude(this.a.e.a);
                bDLocation.setLongitude(this.a.e.b);
                this.a.a.a(bDLocation);
                this.a.k = jCurrentTimeMillis;
            }
        }
        this.a.m.postDelayed(this.a.o, 450L);
    }
}
