package com.baidu.location.indoor;

import com.baidu.location.BDLocation;
import com.baidu.location.indoor.n.i;
import com.baidu.location.indoor.v;
import java.util.Date;

/* loaded from: classes.dex */
class p implements v.b {
    final /* synthetic */ n a;

    p(n nVar) {
        this.a = nVar;
    }

    @Override // com.baidu.location.indoor.v.b
    public synchronized void a(double d, double d2, double d3, long j, String str) {
        if (this.a.l) {
            this.a.H = 0.4d;
            this.a.ag.a(d, d2, d3, j);
            double[] dArrA = com.baidu.location.indoor.mapversion.a.a.a(this.a.u, d, d2, d3, str);
            if (dArrA != null && dArrA[0] != -1.0d && dArrA[0] == 0.0d) {
                this.a.G = dArrA[2];
                this.a.F = dArrA[1];
                if (this.a.K.size() > 50) {
                    this.a.K.clear();
                }
                this.a.K.add(this.a.new i(v.a().b(), d, d3, d2));
                n.f(this.a);
                try {
                    BDLocation bDLocation = new BDLocation();
                    bDLocation.setLocType(BDLocation.TypeNetWorkLocation);
                    bDLocation.setLatitude(dArrA[2]);
                    bDLocation.setLongitude(dArrA[1]);
                    bDLocation.setDirection((float) d3);
                    bDLocation.setTime(this.a.b.format(new Date()));
                    bDLocation.setFloor(this.a.u);
                    bDLocation.setBuildingID(this.a.v);
                    bDLocation.setBuildingName(this.a.x);
                    bDLocation.setParkAvailable(this.a.A);
                    bDLocation.setIndoorLocMode(true);
                    if (this.a.R) {
                        bDLocation.setRadius(8.0f);
                    } else {
                        bDLocation.setRadius(15.0f);
                    }
                    bDLocation.setFusionLocInfo("res", dArrA);
                    bDLocation.setRadius((float) dArrA[5]);
                    bDLocation.setDirection((float) dArrA[6]);
                    bDLocation.setSpeed((float) dArrA[8]);
                    bDLocation.setNetworkLocationType("dr");
                    BDLocation bDLocation2 = new BDLocation(bDLocation);
                    bDLocation2.setNetworkLocationType("dr2");
                    if (this.a.T == null || !this.a.T.c()) {
                        this.a.a(bDLocation2, 21);
                    } else {
                        this.a.T.a(bDLocation2);
                    }
                    if (!this.a.ag.a(bDLocation, dArrA[5], "dr")) {
                        this.a.d();
                    }
                } catch (Exception unused) {
                }
            }
        }
    }
}
