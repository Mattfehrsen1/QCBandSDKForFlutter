package com.baidu.geofence;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.baidu.geofence.a.f;
import com.baidu.geofence.a.j;
import com.baidu.geofence.model.DPoint;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.h.s;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class GeoFenceClient extends BDAbstractLocationListener implements j.a {
    public static final String BD09LL = "bd09ll";
    public static final String BD09MC = "bd09mc";
    public static final String GCJ02 = "gcj02";
    public static final int GEOFENCE_IN = 1;
    public static final int GEOFENCE_IN_OUT = 4;
    public static final int GEOFENCE_IN_OUT_STAYED = 7;
    public static final int GEOFENCE_IN_STAYED = 5;
    public static final int GEOFENCE_OUT = 2;
    public static final int GEOFENCE_OUT_STAYED = 6;
    public static final int GEOFENCE_STAYED = 3;
    public static final String WGS84 = "wgs84";
    private static Handler y;
    private String a;
    private GeoFenceListener c;
    private LocationClient e;
    private Intent f;
    private PendingIntent g;
    private Context h;
    private LocationClientOption l;
    private long o;
    private int b = 600;
    private int i = 1;
    private int j = 1;
    private boolean k = true;
    private boolean m = false;
    private boolean n = false;
    private int u = Integer.MAX_VALUE;
    private int v = Integer.MAX_VALUE;
    private int w = Integer.MAX_VALUE;
    private ArrayList<GeoFence> d = new ArrayList<>();
    private Map<String, Integer> x = new HashMap();
    private StringBuilder p = new StringBuilder();
    private StringBuilder q = new StringBuilder();
    private StringBuilder r = new StringBuilder();
    private StringBuilder s = new StringBuilder();
    private StringBuilder t = new StringBuilder();

    public GeoFenceClient(Context context) {
        this.h = context;
        y = Looper.myLooper() == null ? new Handler(Looper.getMainLooper()) : new Handler(Looper.myLooper());
        try {
            this.e = new LocationClient(this.h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocationClientOption locationClientOption = new LocationClientOption();
        this.l = locationClientOption;
        locationClientOption.setScanSpan(5000);
        this.l.setIsNeedAddress(true);
        LocationClient locationClient = this.e;
        if (locationClient != null) {
            locationClient.setLocOption(this.l);
            this.e.registerLocationListener(this);
        }
        this.o = System.currentTimeMillis();
        com.baidu.location.h.b.a().a(this.l.prodName, this.h.getPackageName());
    }

    private long a(long j, long j2) {
        return (j2 - j) / 1000;
    }

    private BDLocation a(DPoint dPoint, String str) {
        String str2;
        BDLocation bDLocation = new BDLocation();
        bDLocation.setLatitude(dPoint.getLatitude());
        bDLocation.setLongitude(dPoint.getLongitude());
        if (TextUtils.equals("bd09mc", str)) {
            str2 = BDLocation.BDLOCATION_BD09_TO_GCJ02;
        } else if (TextUtils.equals("bd09ll", str)) {
            str2 = BDLocation.BDLOCATION_BD09LL_TO_GCJ02;
        } else {
            if (!TextUtils.equals("wgs84", str)) {
                if (!TextUtils.equals("gcj02", str)) {
                    return null;
                }
                BDLocation bDLocation2 = new BDLocation();
                bDLocation2.setLatitude(dPoint.getLatitude());
                bDLocation2.setLongitude(dPoint.getLongitude());
                return bDLocation2;
            }
            str2 = BDLocation.BDLOCATION_WGS84_TO_GCJ02;
        }
        return LocationClient.getBDLocationInCoorType(bDLocation, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.k = true;
        LocationClient locationClient = this.e;
        if (locationClient == null || locationClient.isStarted()) {
            return;
        }
        this.e.start();
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(int r19, com.baidu.location.BDLocation r20, com.baidu.geofence.GeoFence r21) throws android.app.PendingIntent.CanceledException {
        /*
            Method dump skipped, instructions count: 444
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.geofence.GeoFenceClient.a(int, com.baidu.location.BDLocation, com.baidu.geofence.GeoFence):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(GeoFence geoFence) {
        String activatesAction = geoFence.getActivatesAction();
        if (geoFence == null || TextUtils.isEmpty(activatesAction)) {
            return;
        }
        if (activatesAction.contains("1")) {
            int i = this.u;
            if (i < 1) {
                geoFence.setInTriggerCount(1);
            } else {
                geoFence.setInTriggerCount(i);
            }
        }
        if (activatesAction.contains("2")) {
            int i2 = this.v;
            if (i2 < 1) {
                geoFence.setOutTriggerCount(1);
            } else {
                geoFence.setOutTriggerCount(i2);
            }
        }
        if (activatesAction.contains("3")) {
            int i3 = this.w;
            if (i3 < 1) {
                geoFence.setStayTriggerCount(1);
            } else {
                geoFence.setStayTriggerCount(i3);
            }
        }
    }

    private void a(GeoFence geoFence, BDLocation bDLocation) throws PendingIntent.CanceledException {
        if (geoFence.getActivatesAction().contains("3") && !geoFence.isIn()) {
            geoFence.setStartTimeMillis(System.currentTimeMillis());
            geoFence.setIn(true);
        }
        if (!geoFence.isSend()) {
            a(24, geoFence, bDLocation.getLocType());
            if (geoFence.getActivatesAction().contains("1")) {
                c(geoFence, bDLocation);
            }
            geoFence.setStatus(24);
            geoFence.setSend(true);
            return;
        }
        if (this.x.get(geoFence.getFenceId()).intValue() != 0 && geoFence.getActivatesAction().contains("1")) {
            c(geoFence, bDLocation);
        }
        if (!geoFence.getActivatesAction().contains("3") || !geoFence.isIn() || geoFence.isOut() || a(geoFence.getStartTimeMillis(), System.currentTimeMillis()) <= geoFence.getStayTime()) {
            return;
        }
        d(geoFence, bDLocation);
    }

    private void a(String str, int i) {
        SharedPreferences.Editor editorEdit = this.h.getApplicationContext().getSharedPreferences("geofence", 0).edit();
        editorEdit.putInt(str, i);
        editorEdit.apply();
    }

    private boolean a(int i, GeoFence geoFence, int i2) throws PendingIntent.CanceledException {
        Bundle bundle = new Bundle();
        bundle.putInt("3", i);
        bundle.putString("2", geoFence.getCustomId());
        bundle.putParcelable(GeoFence.BUNDLE_KEY_FENCE, geoFence);
        bundle.putInt(GeoFence.BUNDLE_KEY_LOCERRORCODE, i2);
        bundle.putString("1", geoFence.getFenceId());
        this.f.setExtrasClassLoader(GeoFence.class.getClassLoader());
        this.f.putExtras(bundle);
        PendingIntent broadcast = PendingIntent.getBroadcast(this.h, this.i, this.f, 201326592);
        this.g = broadcast;
        this.i++;
        try {
            broadcast.send();
            return true;
        } catch (PendingIntent.CanceledException unused) {
            return false;
        }
    }

    private boolean a(String str) {
        return "gcj02".equalsIgnoreCase(str) || "bd09ll".equalsIgnoreCase(str) || "bd09mc".equalsIgnoreCase(str) || "wgs84".equalsIgnoreCase(str);
    }

    private int b(String str) {
        return this.h.getApplicationContext().getSharedPreferences("geofence", 0).getInt(str, 0);
    }

    private void b(GeoFence geoFence) {
        if (this.l.getScanSpan() != 1000) {
            this.l.setScanSpan(1000);
            if (this.m && this.l.getLocationMode() != LocationClientOption.LocationMode.Hight_Accuracy) {
                this.l.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
            }
            LocationClient locationClient = this.e;
            if (locationClient != null) {
                locationClient.setLocOption(this.l);
            }
            geoFence.setOneSecond(true);
        }
    }

    private void b(GeoFence geoFence, BDLocation bDLocation) {
        geoFence.setCurrentLoc(bDLocation);
        geoFence.setStatus(16);
        if (a(16, geoFence, bDLocation.getLocType())) {
            geoFence.setOutTriggerCount(geoFence.getOutTriggerCount() - 1);
        }
        if (geoFence.getOutTriggerCount() != 0 || TextUtils.isEmpty(geoFence.getActivatesAction())) {
            return;
        }
        geoFence.setActivatesAction(geoFence.getActivatesAction().replace("2", ""));
    }

    private boolean b() {
        ArrayList<GeoFence> arrayList = this.d;
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<GeoFence> it = this.d.iterator();
            while (it.hasNext()) {
                if (!TextUtils.isEmpty(it.next().getActivatesAction())) {
                    return false;
                }
            }
        }
        return true;
    }

    private void c() {
        SharedPreferences.Editor editorEdit = this.h.getApplicationContext().getSharedPreferences("geofence", 0).edit();
        editorEdit.putInt("1", 0);
        editorEdit.putInt("2", 0);
        editorEdit.putInt("3", 0);
        editorEdit.putInt(GeoFence.BUNDLE_KEY_LOCERRORCODE, 0);
        editorEdit.putInt(GeoFence.BUNDLE_KEY_FENCE, 0);
        editorEdit.apply();
    }

    private void c(GeoFence geoFence) {
        geoFence.setIn(false);
        geoFence.setOut(false);
        geoFence.setStartTimeMillis(0L);
        geoFence.setEndTimeMillis(0L);
    }

    private void c(GeoFence geoFence, BDLocation bDLocation) {
        geoFence.setCurrentLoc(bDLocation);
        geoFence.setStatus(15);
        if (a(15, geoFence, bDLocation.getLocType())) {
            geoFence.setInTriggerCount(geoFence.getInTriggerCount() - 1);
        }
        if (geoFence.getInTriggerCount() != 0 || TextUtils.isEmpty(geoFence.getActivatesAction())) {
            return;
        }
        geoFence.setActivatesAction(geoFence.getActivatesAction().replace("1", ""));
    }

    private void d() {
        if (!(b("1") == 0 && b("2") == 0 && b("3") == 0 && b(GeoFence.BUNDLE_KEY_LOCERRORCODE) == 0 && b(GeoFence.BUNDLE_KEY_FENCE) == 0) && a(this.o, System.currentTimeMillis()) > 60) {
            ArrayList<StringBuilder> arrayList = new ArrayList<>();
            arrayList.add(this.p);
            arrayList.add(this.q);
            arrayList.add(this.r);
            arrayList.add(this.s);
            arrayList.add(this.t);
            j jVar = new j();
            jVar.a(arrayList);
            jVar.a(this);
            jVar.a(new String[]{"circleFence:" + b("1"), "polygonFence:" + b("2"), "poiCircleFence:" + b("3"), "poiRegionFence:" + b(GeoFence.BUNDLE_KEY_LOCERRORCODE), "regionFence:" + b(GeoFence.BUNDLE_KEY_FENCE)});
            this.o = System.currentTimeMillis();
        }
    }

    private void d(GeoFence geoFence, BDLocation bDLocation) {
        geoFence.setCurrentLoc(bDLocation);
        geoFence.setStatus(17);
        if (a(17, geoFence, bDLocation.getLocType())) {
            geoFence.setStayTriggerCount(geoFence.getStayTriggerCount() - 1);
            c(geoFence);
        }
        if (geoFence.getStayTriggerCount() != 0 || TextUtils.isEmpty(geoFence.getActivatesAction())) {
            return;
        }
        geoFence.setActivatesAction(geoFence.getActivatesAction().replace("3", ""));
    }

    static /* synthetic */ int e(GeoFenceClient geoFenceClient) {
        int i = geoFenceClient.j;
        geoFenceClient.j = i + 1;
        return i;
    }

    private void e(GeoFence geoFence, BDLocation bDLocation) throws PendingIntent.CanceledException {
        if (geoFence.isIn() && !geoFence.isOut()) {
            geoFence.setEndTimeMillis(System.currentTimeMillis());
            geoFence.setOut(true);
        }
        if (!geoFence.isSend()) {
            a(25, geoFence, bDLocation.getLocType());
            if (geoFence.getActivatesAction().contains("2")) {
                b(geoFence, bDLocation);
            }
            geoFence.setStatus(25);
            geoFence.setSend(true);
            return;
        }
        if (this.x.get(geoFence.getFenceId()).intValue() != 1 && geoFence.getActivatesAction().contains("2")) {
            b(geoFence, bDLocation);
        }
        if (geoFence.getActivatesAction().contains("3") && geoFence.isIn() && a(geoFence.getStartTimeMillis(), geoFence.getEndTimeMillis()) > geoFence.getStayTime()) {
            d(geoFence, bDLocation);
        }
    }

    public static Handler getHandlerInstance() {
        if (y == null) {
            y = Looper.myLooper() == null ? new Handler(Looper.getMainLooper()) : new Handler(Looper.myLooper());
        }
        return y;
    }

    public void addGeoFence(DPoint dPoint, String str, float f, String str2) {
        if (dPoint == null) {
            return;
        }
        StringBuilder sb = this.p;
        sb.append("[");
        sb.append(dPoint.getLatitude());
        sb.append(",");
        sb.append(dPoint.getLongitude());
        sb.append(",");
        sb.append(str);
        sb.append(",");
        sb.append(f);
        sb.append(",");
        sb.append(str2);
        sb.append("]");
        a("1", b("1") + 1);
        if (TextUtils.isEmpty(str) || !a(str) || f <= 0.0f || TextUtils.isEmpty(str2)) {
            GeoFenceListener geoFenceListener = this.c;
            if (geoFenceListener != null) {
                geoFenceListener.onGeoFenceCreateFinished(null, 8, str2);
                return;
            }
            return;
        }
        BDLocation bDLocationA = a(dPoint, str);
        if (Math.abs(bDLocationA.getLatitude()) > 90.0d || Math.abs(bDLocationA.getLongitude()) > 180.0d) {
            GeoFenceListener geoFenceListener2 = this.c;
            if (geoFenceListener2 != null) {
                geoFenceListener2.onGeoFenceCreateFinished(null, 8, str2);
                return;
            }
            return;
        }
        DPoint dPoint2 = new DPoint(bDLocationA.getLatitude(), bDLocationA.getLongitude());
        Iterator<GeoFence> it = this.d.iterator();
        while (it.hasNext()) {
            GeoFence next = it.next();
            if (next.getType() == 20 && next.getCenter().getLatitude() == dPoint2.getLatitude() && next.getCenter().getLongitude() == dPoint2.getLongitude() && next.getRadius() == f) {
                this.c.onGeoFenceCreateFinished(this.d, 14, str2);
                return;
            }
        }
        GeoFence geoFence = new GeoFence();
        geoFence.setFenceType(20);
        geoFence.setActivatesAction(this.a);
        if (!TextUtils.isEmpty(this.a) && this.a.contains("3")) {
            geoFence.setStayTime(this.b);
        }
        geoFence.setCustomId(str2);
        int i = this.j;
        this.j = i + 1;
        geoFence.setFenceId(String.valueOf(i));
        geoFence.setCenter(dPoint2);
        geoFence.setRadius(f);
        a(geoFence);
        this.d.add(geoFence);
        this.x.put(geoFence.getFenceId(), 2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(geoFence);
        GeoFenceListener geoFenceListener3 = this.c;
        if (geoFenceListener3 != null) {
            geoFenceListener3.onGeoFenceCreateFinished(arrayList, 7, str2);
        }
        this.n = false;
        a();
    }

    public void addGeoFence(String str, String str2) {
        StringBuilder sb = this.t;
        sb.append("[");
        sb.append(str);
        sb.append(",");
        sb.append(str2);
        sb.append("]");
        a(GeoFence.BUNDLE_KEY_FENCE, b(GeoFence.BUNDLE_KEY_FENCE) + 1);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            GeoFenceListener geoFenceListener = this.c;
            if (geoFenceListener != null) {
                geoFenceListener.onGeoFenceCreateFinished(null, 8, str2);
                return;
            }
            return;
        }
        Iterator<GeoFence> it = this.d.iterator();
        while (it.hasNext()) {
            GeoFence next = it.next();
            if (next.getType() == 23 && TextUtils.equals(next.getRegion(), str)) {
                GeoFenceListener geoFenceListener2 = this.c;
                if (geoFenceListener2 != null) {
                    geoFenceListener2.onGeoFenceCreateFinished(null, 14, str2);
                    return;
                }
                return;
            }
        }
        com.baidu.geofence.a.b bVar = new com.baidu.geofence.a.b(str, this.h);
        bVar.a(this.c);
        bVar.a(new d(this, str2, str));
    }

    public void addGeoFence(String str, String str2, DPoint dPoint, String str3, float f, int i, String str4) {
        if (dPoint == null) {
            return;
        }
        StringBuilder sb = this.r;
        sb.append("[");
        sb.append(str);
        sb.append(",");
        sb.append(str2);
        sb.append(",");
        sb.append("(");
        sb.append(dPoint.getLatitude());
        sb.append(",");
        sb.append(dPoint.getLongitude());
        sb.append(")");
        sb.append(",");
        sb.append(str3);
        sb.append(",");
        sb.append(f);
        sb.append(",");
        sb.append(i);
        sb.append(",");
        sb.append(str4);
        sb.append("]");
        a("3", b("3") + 1);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || f < 1.0f || f > 5000.0f || i <= 0 || i > 25 || TextUtils.isEmpty(str4) || !a(str3)) {
            GeoFenceListener geoFenceListener = this.c;
            if (geoFenceListener != null) {
                geoFenceListener.onGeoFenceCreateFinished(null, 8, str4);
                return;
            }
            return;
        }
        BDLocation bDLocationA = a(dPoint, str3);
        if (Math.abs(bDLocationA.getLatitude()) > 90.0d || Math.abs(bDLocationA.getLongitude()) > 180.0d) {
            GeoFenceListener geoFenceListener2 = this.c;
            if (geoFenceListener2 != null) {
                geoFenceListener2.onGeoFenceCreateFinished(null, 8, str4);
                return;
            }
            return;
        }
        if (!s.a(this.h)) {
            GeoFenceListener geoFenceListener3 = this.c;
            if (geoFenceListener3 != null) {
                geoFenceListener3.onGeoFenceCreateFinished(null, 9, str4);
                return;
            }
            return;
        }
        DPoint dPoint2 = new DPoint(bDLocationA.getLatitude(), bDLocationA.getLongitude());
        f fVar = new f(this.h, true, this.c, this.d);
        fVar.a(i);
        fVar.b(str);
        fVar.c(str2);
        fVar.a(dPoint2);
        fVar.a(f);
        fVar.a(str4);
        fVar.a(new b(this, str4, str));
    }

    public void addGeoFence(String str, String str2, String str3, int i, String str4) {
        StringBuilder sb = this.s;
        sb.append("[");
        sb.append(str);
        sb.append(",");
        sb.append(str2);
        sb.append(",");
        sb.append(str3);
        sb.append(",");
        sb.append(i);
        sb.append(",");
        sb.append(str4);
        sb.append("]");
        a(GeoFence.BUNDLE_KEY_LOCERRORCODE, b(GeoFence.BUNDLE_KEY_LOCERRORCODE) + 1);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || i == 0 || TextUtils.isEmpty(str4)) {
            GeoFenceListener geoFenceListener = this.c;
            if (geoFenceListener != null) {
                geoFenceListener.onGeoFenceCreateFinished(null, 8, str4);
                return;
            }
            return;
        }
        if (!s.a(this.h)) {
            GeoFenceListener geoFenceListener2 = this.c;
            if (geoFenceListener2 != null) {
                geoFenceListener2.onGeoFenceCreateFinished(null, 9, str4);
                return;
            }
            return;
        }
        f fVar = new f(this.h, false, this.c, this.d);
        fVar.b(str);
        fVar.d(str3);
        fVar.c(str2);
        fVar.a(i);
        fVar.a(str4);
        fVar.a(new c(this, str4, str));
    }

    public void addGeoFence(ArrayList<DPoint> arrayList, String str, String str2) {
        StringBuilder sb = this.q;
        sb.append("[");
        sb.append(str);
        sb.append(",");
        sb.append(str2);
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<DPoint> it = arrayList.iterator();
            while (it.hasNext()) {
                DPoint next = it.next();
                StringBuilder sb2 = this.q;
                sb2.append(",");
                sb2.append("(");
                sb2.append(next.getLatitude());
                sb2.append(",");
                sb2.append(next.getLongitude());
                sb2.append(")");
            }
        }
        this.q.append("]");
        a("2", b("2") + 1);
        if (arrayList == null || arrayList.size() < 3 || TextUtils.isEmpty(str) || !a(str) || TextUtils.isEmpty(str2)) {
            GeoFenceListener geoFenceListener = this.c;
            if (geoFenceListener != null) {
                geoFenceListener.onGeoFenceCreateFinished(null, 8, str2);
                return;
            }
            return;
        }
        ArrayList<DPoint> arrayList2 = new ArrayList<>();
        Iterator<DPoint> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            BDLocation bDLocationA = a(it2.next(), str);
            if (Math.abs(bDLocationA.getLatitude()) > 90.0d || Math.abs(bDLocationA.getLongitude()) > 180.0d) {
                GeoFenceListener geoFenceListener2 = this.c;
                if (geoFenceListener2 != null) {
                    geoFenceListener2.onGeoFenceCreateFinished(null, 8, str2);
                    return;
                }
                return;
            }
            arrayList2.add(new DPoint(bDLocationA.getLatitude(), bDLocationA.getLongitude()));
        }
        Iterator<GeoFence> it3 = this.d.iterator();
        while (true) {
            if (!it3.hasNext()) {
                GeoFence geoFence = new GeoFence();
                geoFence.setPoints(arrayList2);
                geoFence.setFenceType(21);
                geoFence.setActivatesAction(this.a);
                if (!TextUtils.isEmpty(this.a) && this.a.contains("3")) {
                    geoFence.setStayTime(this.b);
                }
                geoFence.setCustomId(str2);
                int i = this.j;
                this.j = i + 1;
                geoFence.setFenceId(String.valueOf(i));
                a(geoFence);
                this.d.add(geoFence);
                this.x.put(geoFence.getFenceId(), 2);
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(geoFence);
                GeoFenceListener geoFenceListener3 = this.c;
                if (geoFenceListener3 != null) {
                    geoFenceListener3.onGeoFenceCreateFinished(arrayList3, 7, str2);
                }
                this.n = false;
                a();
                return;
            }
            GeoFence next2 = it3.next();
            if (next2.getType() == 21 && next2.getPoints().size() == arrayList2.size()) {
                int i2 = 0;
                for (int i3 = 0; i3 < arrayList2.size() && arrayList2.get(i3).getLatitude() == next2.getPoints().get(i3).getLatitude() && arrayList2.get(i3).getLongitude() == next2.getPoints().get(i3).getLongitude(); i3++) {
                    i2++;
                }
                if (i2 == arrayList2.size()) {
                    GeoFenceListener geoFenceListener4 = this.c;
                    if (geoFenceListener4 != null) {
                        geoFenceListener4.onGeoFenceCreateFinished(null, 14, str2);
                        return;
                    }
                    return;
                }
            }
        }
    }

    @Override // com.baidu.geofence.a.j.a
    public void clear() {
        c();
    }

    public void createPendingIntent(String str) {
        this.f = new Intent(str);
    }

    public List<GeoFence> getAllGeoFence() {
        return this.d;
    }

    public void isHighAccuracyLoc(boolean z) {
        this.m = z;
    }

    public boolean isPause() {
        LocationClient locationClient = this.e;
        return (locationClient == null || this.k || locationClient.isStarted()) ? false : true;
    }

    @Override // com.baidu.location.BDAbstractLocationListener
    public void onReceiveLocation(BDLocation bDLocation) throws PendingIntent.CanceledException {
        LocationClientOption locationClientOption;
        LocationClientOption.LocationMode locationMode;
        int i;
        d();
        if (this.k) {
            if (bDLocation.getLocType() == 161 || bDLocation.getLocType() == 66 || bDLocation.getLocType() == 61) {
                Iterator<GeoFence> it = this.d.iterator();
                while (it.hasNext()) {
                    GeoFence next = it.next();
                    if (next.isAble() && !TextUtils.isEmpty(next.getActivatesAction())) {
                        if (next.getType() == 20 || next.getType() == 22) {
                            a(1, bDLocation, next);
                        } else {
                            if (next.getType() == 21) {
                                i = 2;
                            } else if (next.getType() == 23) {
                                i = 3;
                            }
                            a(i, bDLocation, next);
                        }
                    }
                }
            } else {
                Iterator<GeoFence> it2 = this.d.iterator();
                while (it2.hasNext()) {
                    GeoFence next2 = it2.next();
                    if (!next2.isSend() || !TextUtils.isEmpty(next2.getActivatesAction())) {
                        a(18, next2, bDLocation.getLocType());
                    }
                }
            }
        }
        if (this.n) {
            return;
        }
        Iterator<GeoFence> it3 = this.d.iterator();
        boolean z = true;
        boolean z2 = false;
        boolean z3 = false;
        while (it3.hasNext()) {
            GeoFence next3 = it3.next();
            if (!TextUtils.isEmpty(next3.getActivatesAction())) {
                z = false;
            }
            if (next3.getType() == 23 && !TextUtils.isEmpty(next3.getActivatesAction())) {
                z2 = true;
            }
            if (next3.isOneSecond()) {
                z3 = true;
            }
        }
        if (z) {
            pauseGeoFence();
            return;
        }
        this.k = true;
        this.n = false;
        LocationClientOption locationClientOption2 = this.l;
        if (!z2) {
            locationClientOption2.setIsNeedAddress(false);
        } else if (!TextUtils.equals(locationClientOption2.getAddrType(), "all")) {
            this.l.setIsNeedAddress(true);
        }
        LocationClientOption locationClientOption3 = this.l;
        if (z3) {
            locationClientOption3.setScanSpan(1000);
            if (this.m && this.l.getLocationMode() != LocationClientOption.LocationMode.Hight_Accuracy) {
                locationClientOption = this.l;
                locationMode = LocationClientOption.LocationMode.Hight_Accuracy;
                locationClientOption.setLocationMode(locationMode);
            }
        } else {
            locationClientOption3.setScanSpan(5000);
            if (this.l.getLocationMode() == LocationClientOption.LocationMode.Hight_Accuracy) {
                locationClientOption = this.l;
                locationMode = LocationClientOption.LocationMode.Battery_Saving;
                locationClientOption.setLocationMode(locationMode);
            }
        }
        LocationClient locationClient = this.e;
        if (locationClient != null) {
            locationClient.setLocOption(this.l);
        }
        a();
    }

    public void pauseGeoFence() {
        this.n = true;
        this.k = false;
        LocationClient locationClient = this.e;
        if (locationClient == null || !locationClient.isStarted()) {
            return;
        }
        this.e.stop();
    }

    public void removeGeoFence() {
        ArrayList<GeoFence> arrayList = this.d;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        this.d.clear();
        this.k = false;
        this.j = 1;
        this.x.clear();
        LocationClient locationClient = this.e;
        if (locationClient == null || !locationClient.isStarted()) {
            return;
        }
        this.e.stop();
    }

    public boolean removeGeoFence(GeoFence geoFence) {
        ArrayList<GeoFence> arrayList;
        if (geoFence != null && (arrayList = this.d) != null && !arrayList.isEmpty()) {
            Iterator<GeoFence> it = this.d.iterator();
            while (it.hasNext()) {
                if (TextUtils.equals(it.next().getFenceId(), geoFence.getFenceId())) {
                    this.d.remove(geoFence);
                    return true;
                }
            }
        }
        return false;
    }

    public void resumeGeoFence() {
        ArrayList<GeoFence> arrayList = this.d;
        if (arrayList == null || arrayList.size() == 0 || b() || !isPause()) {
            return;
        }
        this.k = true;
        this.n = false;
        a();
    }

    public void setActivateAction(int i) {
        String str;
        if (i == 1) {
            str = "1";
        } else if (i == 2) {
            str = "2";
        } else if (i == 3) {
            str = "3";
        } else if (i == 4) {
            str = "12";
        } else if (i == 5) {
            str = "13";
        } else {
            if (i != 6) {
                if (i == 7) {
                    str = "123";
                }
                TextUtils.isEmpty(this.a);
            }
            str = "23";
        }
        this.a = str;
        TextUtils.isEmpty(this.a);
    }

    public void setGeoFenceAble(String str, boolean z) {
        ArrayList<GeoFence> arrayList;
        if (TextUtils.isEmpty(str) || (arrayList = this.d) == null || arrayList.size() == 0) {
            return;
        }
        Iterator<GeoFence> it = this.d.iterator();
        while (it.hasNext()) {
            GeoFence next = it.next();
            if (TextUtils.equals(next.getFenceId(), str)) {
                next.setAble(z);
            }
        }
    }

    public void setGeoFenceListener(GeoFenceListener geoFenceListener) {
        this.c = geoFenceListener;
    }

    public void setStayTime(int i) {
        this.b = i;
    }

    public void setTriggerCount(int i, int i2, int i3) {
        if (TextUtils.isEmpty(this.a)) {
            return;
        }
        if (this.a.contains("1")) {
            if (i < 1) {
                this.u = 1;
            } else {
                this.u = i;
            }
        }
        if (this.a.contains("2")) {
            if (i2 < 1) {
                this.v = 1;
            } else {
                this.v = i2;
            }
        }
        if (this.a.contains("3")) {
            if (i3 < 1) {
                this.w = 1;
            } else {
                this.w = i3;
            }
        }
    }
}
