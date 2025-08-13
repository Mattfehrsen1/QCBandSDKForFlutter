package com.baidu.location.indoor;

import androidx.work.WorkRequest;
import com.baidu.location.BDLocation;
import com.baidu.location.indoor.y;

/* loaded from: classes.dex */
class o implements y.a {
    final /* synthetic */ n a;

    o(n nVar) {
        this.a = nVar;
    }

    @Override // com.baidu.location.indoor.y.a
    public void a(BDLocation bDLocation) {
        String strG;
        if (this.a.f()) {
            if (this.a.ag != null && System.currentTimeMillis() - this.a.ag.c > 20000 && System.currentTimeMillis() - this.a.ag.e < WorkRequest.MIN_BACKOFF_MILLIS) {
                bDLocation.setLocType(61);
                bDLocation.setFloor(null);
                bDLocation.setBuildingID(null);
                bDLocation.setBuildingName(null);
            }
            BDLocation bDLocation2 = new BDLocation(bDLocation);
            if (com.baidu.location.f.e.a().k() && (strG = com.baidu.location.f.e.a().g()) != null) {
                BDLocation bDLocation3 = new BDLocation(strG);
                if (bDLocation3.getSatelliteNumber() > 0 && bDLocation3.getSpeed() > 0.0f) {
                    bDLocation2.setLocType(61);
                    bDLocation2.setSatelliteNumber(bDLocation3.getSatelliteNumber());
                    bDLocation2.setSpeed(bDLocation3.getSpeed());
                    bDLocation2.setAltitude(bDLocation3.getAltitude());
                    bDLocation2.setDirection(bDLocation3.getDirection());
                }
            }
            this.a.a(bDLocation2, 29);
            this.a.ah.a(bDLocation);
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.a.ag == null || jCurrentTimeMillis - this.a.ag.c <= WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS || jCurrentTimeMillis - this.a.ag.e <= WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS) {
            return;
        }
        this.a.d();
    }
}
