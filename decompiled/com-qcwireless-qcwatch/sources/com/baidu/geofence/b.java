package com.baidu.geofence;

import android.text.TextUtils;
import com.baidu.geofence.a.f;
import com.baidu.geofence.model.DPoint;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
class b implements f.a {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ GeoFenceClient c;

    b(GeoFenceClient geoFenceClient, String str, String str2) {
        this.c = geoFenceClient;
        this.a = str;
        this.b = str2;
    }

    @Override // com.baidu.geofence.a.f.a
    public void a(int i, ArrayList<PoiItem> arrayList) {
        GeoFence geoFence;
        if (i != 0) {
            if (this.c.c != null) {
                this.c.c.onGeoFenceCreateFinished(null, 13, this.a);
                return;
            }
            return;
        }
        if (arrayList == null || arrayList.size() == 0) {
            return;
        }
        Iterator<PoiItem> it = arrayList.iterator();
        ArrayList arrayList2 = null;
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                break;
            }
            PoiItem next = it.next();
            if (this.c.d == null || this.c.d.size() == 0) {
                geoFence = new GeoFence();
                geoFence.setFenceType(22);
                geoFence.setPoiItem(next);
                geoFence.setRadius(200.0f);
                geoFence.setKeyWord(this.b);
                geoFence.setActivatesAction(this.c.a);
                if (!TextUtils.isEmpty(this.c.a) && this.c.a.contains("3")) {
                    geoFence.setStayTime(this.c.b);
                }
                geoFence.setCustomId(this.a);
                geoFence.setFenceId(String.valueOf(GeoFenceClient.e(this.c)));
                this.c.x.put(geoFence.getFenceId(), 2);
                geoFence.setCenter(new DPoint(next.getLatitude(), next.getLongitude()));
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                }
                this.c.a(geoFence);
                arrayList2.add(geoFence);
            } else {
                Iterator it2 = this.c.d.iterator();
                while (it2.hasNext()) {
                    GeoFence geoFence2 = (GeoFence) it2.next();
                    if (geoFence2.getType() == 22 && next.getLatitude() == geoFence2.getPoiItem().getLatitude() && next.getLongitude() == geoFence2.getPoiItem().getLongitude()) {
                        if (this.c.c != null) {
                            this.c.c.onGeoFenceCreateFinished(null, 14, this.a);
                        }
                        z = true;
                    }
                }
                if (!z) {
                    geoFence = new GeoFence();
                    geoFence.setFenceType(22);
                    geoFence.setPoiItem(next);
                    geoFence.setRadius(200.0f);
                    geoFence.setKeyWord(this.b);
                    geoFence.setActivatesAction(this.c.a);
                    if (!TextUtils.isEmpty(this.c.a) && this.c.a.contains("3")) {
                        geoFence.setStayTime(this.c.b);
                    }
                    geoFence.setCustomId(this.a);
                    geoFence.setFenceId(String.valueOf(GeoFenceClient.e(this.c)));
                    this.c.x.put(geoFence.getFenceId(), 2);
                    geoFence.setCenter(new DPoint(next.getLatitude(), next.getLongitude()));
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                    }
                    this.c.a(geoFence);
                    arrayList2.add(geoFence);
                }
            }
        }
        if (arrayList2 == null) {
            if (this.c.c != null) {
                this.c.c.onGeoFenceCreateFinished(null, 13, this.a);
            }
        } else {
            if (arrayList2.isEmpty() || this.c.d == null) {
                return;
            }
            this.c.d.addAll(arrayList2);
            if (this.c.c != null) {
                this.c.c.onGeoFenceCreateFinished(arrayList2, 7, this.a);
            }
            this.c.n = false;
            this.c.a();
        }
    }
}
