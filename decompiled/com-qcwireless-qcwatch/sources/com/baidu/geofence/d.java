package com.baidu.geofence;

import android.text.TextUtils;
import com.baidu.geofence.a.b;
import java.util.ArrayList;

/* loaded from: classes.dex */
class d implements b.a {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ GeoFenceClient c;

    d(GeoFenceClient geoFenceClient, String str, String str2) {
        this.c = geoFenceClient;
        this.a = str;
        this.b = str2;
    }

    @Override // com.baidu.geofence.a.b.a
    public void a(int i) {
        if (i != 0) {
            if (this.c.c != null) {
                this.c.c.onGeoFenceCreateFinished(null, 13, this.a);
                return;
            }
            return;
        }
        GeoFence geoFence = new GeoFence();
        geoFence.setFenceType(23);
        geoFence.setRegion(this.b);
        geoFence.setKeyWord(this.b);
        geoFence.setCustomId(this.a);
        geoFence.setActivatesAction(this.c.a);
        if (!TextUtils.isEmpty(this.c.a) && this.c.a.contains("3")) {
            geoFence.setStayTime(this.c.b);
        }
        geoFence.setFenceId(String.valueOf(GeoFenceClient.e(this.c)));
        this.c.a(geoFence);
        this.c.d.add(geoFence);
        this.c.x.put(geoFence.getFenceId(), 2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(geoFence);
        if (this.c.c != null) {
            this.c.c.onGeoFenceCreateFinished(arrayList, 7, this.a);
        }
        if (!TextUtils.equals(this.c.l.getAddrType(), "all") && this.c.e != null) {
            this.c.l.setIsNeedAddress(true);
            this.c.e.setLocOption(this.c.l);
        }
        this.c.n = false;
        this.c.a();
    }
}
