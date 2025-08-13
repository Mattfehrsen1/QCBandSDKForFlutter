package com.baidu.location.b;

import android.content.Intent;
import android.location.Location;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.LruCache;
import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationConst;
import com.baidu.location.Poi;
import com.baidu.location.PoiRegion;
import com.king.zxing.util.LogUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class c {
    public static long c = 0;
    public static int d = -1;
    public boolean a;
    boolean b;
    int e;
    private ArrayList<a> f;
    private boolean g;
    private boolean h;
    private BDLocation i;
    private BDLocation j;
    private Object k;
    private long l;
    private LruCache<String, JSONObject> m;
    private String n;
    private String o;
    private String p;
    private final String[] q;
    private BDLocation r;
    private boolean s;
    private boolean t;
    private RunnableC0014c u;

    private class a {
        public String a;
        public Messenger b;
        public LocationClientOption c = new LocationClientOption();
        public int d = 0;
        public int e;

        public a(Message message) {
            this.a = null;
            this.b = null;
            this.e = 1;
            this.b = message.replyTo;
            this.e = message.arg1;
            this.a = message.getData().getString("packName");
            this.c.prodName = message.getData().getString("prodName");
            com.baidu.location.h.b.a().a(this.c.prodName, this.a);
            this.c.coorType = message.getData().getString("coorType");
            this.c.addrType = message.getData().getString("addrType");
            this.c.enableSimulateGps = message.getData().getBoolean("enableSimulateGps", false);
            com.baidu.location.h.s.l = com.baidu.location.h.s.l || this.c.enableSimulateGps;
            if (!com.baidu.location.h.s.e.equals("all")) {
                com.baidu.location.h.s.e = this.c.addrType;
            }
            this.c.openGps = message.getData().getBoolean("openGPS");
            this.c.scanSpan = message.getData().getInt("scanSpan");
            this.c.timeOut = message.getData().getInt("timeOut");
            this.c.priority = message.getData().getInt("priority");
            this.c.location_change_notify = message.getData().getBoolean("location_change_notify");
            this.c.mIsNeedDeviceDirect = message.getData().getBoolean("needDirect", false);
            this.c.isNeedAltitude = message.getData().getBoolean("isneedaltitude", false);
            this.c.isNeedNewVersionRgc = message.getData().getBoolean("isneednewrgc", false);
            com.baidu.location.h.s.h = com.baidu.location.h.s.h || this.c.isNeedNewVersionRgc;
            com.baidu.location.h.s.g = com.baidu.location.h.s.g || message.getData().getBoolean("isneedaptag", false);
            com.baidu.location.h.s.i = com.baidu.location.h.s.i || message.getData().getBoolean("isneedaptagd", false);
            com.baidu.location.h.s.R = message.getData().getFloat("autoNotifyLocSensitivity", 0.5f);
            int i = message.getData().getInt("wfnum", com.baidu.location.h.s.ay);
            float f = message.getData().getFloat("wfsm", com.baidu.location.h.s.aA);
            int i2 = message.getData().getInt("gnmcon", com.baidu.location.h.s.aC);
            double d = message.getData().getDouble("gnmcrm", com.baidu.location.h.s.aB);
            int i3 = message.getData().getInt("iupl", 1);
            com.baidu.location.h.s.aR = message.getData().getInt("ct", 10);
            com.baidu.location.h.s.aS = message.getData().getInt("suci", 3);
            com.baidu.location.h.s.aU = message.getData().getDoubleArray("cgs");
            com.baidu.location.h.s.aV = message.getData().getInt("ums", 1);
            com.baidu.location.h.s.aT = message.getData().getInt("smn", 40);
            if (i3 <= 0) {
                com.baidu.location.h.s.aQ = 0;
            } else if (com.baidu.location.h.s.aQ == -1) {
                com.baidu.location.h.s.aQ = 1;
            }
            if (message.getData().getInt("opetco", 1) == 0) {
                com.baidu.location.h.s.aW = 0;
            }
            if (message.getData().getInt("lpcs", com.baidu.location.h.s.aX) == 0) {
                com.baidu.location.h.s.aX = 0;
            }
            if (i2 == 1) {
                com.baidu.location.h.s.aC = 1;
            }
            if (d > com.baidu.location.h.s.aB) {
                com.baidu.location.h.s.aB = d;
            }
            com.baidu.location.h.s.ax = com.baidu.location.h.s.ax || message.getData().getBoolean("ischeckper", false);
            boolean z = message.getData().getBoolean("isEnableBeidouMode", false);
            if (Build.VERSION.SDK_INT >= 28) {
                com.baidu.location.h.s.aY = com.baidu.location.h.s.aY || z;
            }
            if (i > com.baidu.location.h.s.ay) {
                com.baidu.location.h.s.ay = i;
            }
            if (f > com.baidu.location.h.s.aA) {
                com.baidu.location.h.s.aA = f;
            }
            int i4 = message.getData().getInt("wifitimeout", Integer.MAX_VALUE);
            if (i4 < com.baidu.location.h.s.af) {
                com.baidu.location.h.s.af = i4;
            }
            int i5 = message.getData().getInt("autoNotifyMaxInterval", 0);
            if (i5 >= com.baidu.location.h.s.W) {
                com.baidu.location.h.s.W = i5;
            }
            int i6 = message.getData().getInt("autoNotifyMinDistance", 0);
            if (i6 >= com.baidu.location.h.s.Y) {
                com.baidu.location.h.s.Y = i6;
            }
            int i7 = message.getData().getInt("autoNotifyMinTimeInterval", 0);
            if (i7 >= com.baidu.location.h.s.X) {
                com.baidu.location.h.s.X = i7;
            }
            if (this.c.mIsNeedDeviceDirect || this.c.isNeedAltitude) {
                ah.a().a(this.c.mIsNeedDeviceDirect);
                ah.a().b();
            }
            c.this.b = c.this.b || this.c.isNeedAltitude;
            if (message.getData().getInt("hpdts", com.baidu.location.h.s.aD) == 1) {
                com.baidu.location.h.s.aD = 1;
            } else {
                com.baidu.location.h.s.aD = 0;
            }
            if (message.getData().getInt("oldts", com.baidu.location.h.s.aE) == 1) {
                com.baidu.location.h.s.aE = 1;
            } else {
                com.baidu.location.h.s.aE = 0;
            }
            int i8 = message.getData().getInt("onic", com.baidu.location.h.s.aF);
            if (i8 == 0) {
                com.baidu.location.h.s.aF = i8;
            }
            int i9 = message.getData().getInt("nlcs", com.baidu.location.h.s.aG);
            if (i9 == 1) {
                com.baidu.location.h.s.aG = i9;
            }
            com.baidu.location.h.s.aH = message.getData().getFloat("ncsr", com.baidu.location.h.s.aH);
            com.baidu.location.h.s.aI = message.getData().getFloat("cscr", com.baidu.location.h.s.aI);
            com.baidu.location.h.s.aJ = message.getData().getInt("cls", com.baidu.location.h.s.aJ);
            com.baidu.location.h.s.aK = message.getData().getIntArray("ocs");
            com.baidu.location.h.s.aL = com.baidu.location.h.s.a(com.baidu.location.h.s.aK);
            com.baidu.location.h.s.aM = message.getData().getInt("topCellNumber");
            com.baidu.location.h.s.aN = message.getData().getInt("locStrLength");
            com.baidu.location.h.s.aO = message.getData().getInt("hils");
            com.baidu.location.f.h.a().a((WifiInfo) null, message.getData().getString("connectBssid", null));
        }

        private double a(boolean z, BDLocation bDLocation, BDLocation bDLocation2) {
            double d;
            double latitude;
            double longitude;
            double latitude2;
            double longitude2;
            double dA;
            double[] dArrCoorEncrypt;
            if (z) {
                if (TextUtils.equals(bDLocation2.getCoorType(), bDLocation.getCoorType())) {
                    if (TextUtils.equals("bd09", bDLocation2.getCoorType())) {
                        double[] dArrCoorEncrypt2 = Jni.coorEncrypt(bDLocation2.getLongitude(), bDLocation2.getLatitude(), BDLocation.BDLOCATION_BD09_TO_GCJ02);
                        double[] dArrCoorEncrypt3 = Jni.coorEncrypt(bDLocation.getLongitude(), bDLocation.getLatitude(), BDLocation.BDLOCATION_BD09_TO_GCJ02);
                        latitude = dArrCoorEncrypt2[1];
                        longitude = dArrCoorEncrypt2[0];
                        latitude2 = dArrCoorEncrypt3[1];
                        longitude2 = dArrCoorEncrypt3[0];
                        dA = com.baidu.location.h.s.a(latitude, longitude, latitude2, longitude2);
                    }
                    dA = com.baidu.location.h.s.a(bDLocation2.getLatitude(), bDLocation2.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude());
                } else {
                    if (TextUtils.equals("wgs84", bDLocation.getCoorType())) {
                        dArrCoorEncrypt = new double[]{bDLocation.getLongitude(), bDLocation.getLatitude()};
                    } else {
                        double[] dArrCoorEncrypt4 = TextUtils.equals("bd09", bDLocation.getCoorType()) ? Jni.coorEncrypt(bDLocation.getLongitude(), bDLocation.getLatitude(), BDLocation.BDLOCATION_BD09_TO_GCJ02) : TextUtils.equals("bd09ll", bDLocation.getCoorType()) ? Jni.coorEncrypt(bDLocation.getLongitude(), bDLocation.getLatitude(), BDLocation.BDLOCATION_BD09LL_TO_GCJ02) : new double[]{bDLocation.getLongitude(), bDLocation.getLatitude()};
                        dArrCoorEncrypt = Jni.coorEncrypt(dArrCoorEncrypt4[0], dArrCoorEncrypt4[1], "gcj2wgs");
                    }
                    bDLocation.setLatitude(dArrCoorEncrypt[1]);
                    d = dArrCoorEncrypt[0];
                    bDLocation.setLongitude(d);
                    bDLocation.setTime(com.baidu.location.h.s.a());
                    bDLocation.setCoorType("wgs84");
                    dA = com.baidu.location.h.s.a(bDLocation2.getLatitude(), bDLocation2.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude());
                }
            } else if (TextUtils.equals(bDLocation2.getCoorType(), bDLocation.getCoorType())) {
                latitude = bDLocation2.getLatitude();
                longitude = bDLocation2.getLongitude();
                latitude2 = bDLocation.getLatitude();
                longitude2 = bDLocation.getLongitude();
                dA = com.baidu.location.h.s.a(latitude, longitude, latitude2, longitude2);
            } else {
                double[] dArrCoorEncrypt5 = Jni.coorEncrypt(bDLocation.getLongitude(), bDLocation.getLatitude(), "gcj2wgs");
                bDLocation.setLatitude(dArrCoorEncrypt5[1]);
                d = dArrCoorEncrypt5[0];
                bDLocation.setLongitude(d);
                bDLocation.setTime(com.baidu.location.h.s.a());
                bDLocation.setCoorType("wgs84");
                dA = com.baidu.location.h.s.a(bDLocation2.getLatitude(), bDLocation2.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude());
            }
            bDLocation2.setDisToRealLocation(dA);
            if (bDLocation != null) {
                bDLocation2.setReallLocation(bDLocation);
            }
            return dA;
        }

        private int a(double d) {
            if (d >= 0.0d && d <= 10.0d) {
                return 0;
            }
            if (d <= 10.0d || d > 100.0d) {
                return (d <= 100.0d || d > 200.0d) ? 3 : 2;
            }
            return 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(int i) throws RemoteException {
            Message messageObtain = Message.obtain((Handler) null, i);
            try {
                Messenger messenger = this.b;
                if (messenger != null) {
                    messenger.send(messageObtain);
                }
                this.d = 0;
            } catch (Exception e) {
                if (e instanceof DeadObjectException) {
                    this.d++;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(int i, Bundle bundle) throws RemoteException {
            Message messageObtain = Message.obtain((Handler) null, i);
            messageObtain.setData(bundle);
            try {
                Messenger messenger = this.b;
                if (messenger != null) {
                    messenger.send(messageObtain);
                }
                this.d = 0;
            } catch (Exception e) {
                if (e instanceof DeadObjectException) {
                    this.d++;
                }
                e.printStackTrace();
            }
        }

        private void a(int i, String str, BDLocation bDLocation) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putParcelable(str, bDLocation);
            bundle.setClassLoader(BDLocation.class.getClassLoader());
            Message messageObtain = Message.obtain((Handler) null, i);
            messageObtain.setData(bundle);
            try {
                Messenger messenger = this.b;
                if (messenger != null) {
                    messenger.send(messageObtain);
                }
                this.d = 0;
            } catch (Exception e) {
                if (e instanceof DeadObjectException) {
                    this.d++;
                }
            }
        }

        private BDLocation c() {
            BDLocation bDLocationI = com.baidu.location.f.e.a().i();
            if (bDLocationI == null) {
                return null;
            }
            double[] dArrCoorEncrypt = Jni.coorEncrypt(bDLocationI.getLongitude(), bDLocationI.getLatitude(), BDLocation.BDLOCATION_WGS84_TO_GCJ02);
            double[] dArrCoorEncrypt2 = Jni.coorEncrypt(dArrCoorEncrypt[0], dArrCoorEncrypt[1], this.c.coorType);
            BDLocation bDLocation = new BDLocation();
            bDLocation.setLongitude(dArrCoorEncrypt2[0]);
            bDLocation.setLatitude(dArrCoorEncrypt2[1]);
            bDLocation.setTime(com.baidu.location.h.s.a());
            bDLocation.setLocType(61);
            bDLocation.setCoorType(this.c.coorType);
            return bDLocation;
        }

        private BDLocation d() {
            BDLocation bDLocationI = com.baidu.location.f.e.a().i();
            if (bDLocationI == null) {
                return null;
            }
            double[] dArrCoorEncrypt = Jni.coorEncrypt(bDLocationI.getLongitude(), bDLocationI.getLatitude(), BDLocation.BDLOCATION_WGS84_TO_GCJ02);
            BDLocation bDLocation = new BDLocation();
            bDLocation.setLongitude(dArrCoorEncrypt[0]);
            bDLocation.setLatitude(dArrCoorEncrypt[1]);
            bDLocation.setTime(com.baidu.location.h.s.a());
            bDLocation.setLocType(61);
            bDLocation.setCoorType("gcj02");
            return bDLocation;
        }

        public int a(int i, boolean z, BDLocation bDLocation) {
            double dA;
            if (i == 100) {
                if (z) {
                    BDLocation bDLocationC = c();
                    if (bDLocationC == null) {
                        return 3;
                    }
                    a(true, bDLocationC, bDLocation);
                    return 3;
                }
                BDLocation bDLocationD = d();
                if (bDLocationD == null) {
                    return 3;
                }
                a(false, bDLocationD, bDLocation);
                return 3;
            }
            if (i == 200 || i == 300) {
                return 1;
            }
            if (i != 400) {
                return i == 500 ? 1 : 0;
            }
            if (z) {
                BDLocation bDLocationC2 = c();
                if (bDLocationC2 == null) {
                    return -1;
                }
                dA = a(true, bDLocationC2, bDLocation);
            } else {
                BDLocation bDLocationD2 = d();
                if (bDLocationD2 == null) {
                    return -1;
                }
                dA = a(false, bDLocationD2, bDLocation);
            }
            return a(dA);
        }

        public void a() throws RemoteException {
            a(111);
        }

        public void a(BDLocation bDLocation) throws RemoteException {
            a(bDLocation, 21);
        }

        public void a(BDLocation bDLocation, int i) throws RemoteException {
            int iA;
            String str;
            BDLocation bDLocation2 = new BDLocation(bDLocation);
            if (com.baidu.location.indoor.n.a().e()) {
                bDLocation2.setIndoorLocMode(true);
            }
            if (i == 21) {
                a(27, "locStr", bDLocation2);
            }
            if (this.c.coorType != null && !this.c.coorType.equals("gcj02")) {
                double longitude = bDLocation2.getLongitude();
                double latitude = bDLocation2.getLatitude();
                if (longitude != Double.MIN_VALUE && latitude != Double.MIN_VALUE) {
                    if ((bDLocation2.getCoorType() != null && bDLocation2.getCoorType().equals("gcj02")) || bDLocation2.getCoorType() == null) {
                        double[] dArrCoorEncrypt = Jni.coorEncrypt(longitude, latitude, this.c.coorType);
                        bDLocation2.setLongitude(dArrCoorEncrypt[0]);
                        bDLocation2.setLatitude(dArrCoorEncrypt[1]);
                        str = this.c.coorType;
                    } else if (bDLocation2.getCoorType() != null && bDLocation2.getCoorType().equals("wgs84") && !this.c.coorType.equals("bd09ll")) {
                        double[] dArrCoorEncrypt2 = Jni.coorEncrypt(longitude, latitude, "wgs842mc");
                        bDLocation2.setLongitude(dArrCoorEncrypt2[0]);
                        bDLocation2.setLatitude(dArrCoorEncrypt2[1]);
                        str = "wgs84mc";
                    }
                    bDLocation2.setCoorType(str);
                }
                if (!com.baidu.location.h.s.l && bDLocation2.getMockGpsStrategy() > 0) {
                    iA = a(bDLocation2.getMockGpsStrategy(), true, bDLocation2);
                    bDLocation2.setMockGpsProbability(iA);
                }
            } else if (!com.baidu.location.h.s.l && bDLocation2.getMockGpsStrategy() > 0) {
                iA = a(bDLocation2.getMockGpsStrategy(), false, bDLocation2);
                bDLocation2.setMockGpsProbability(iA);
            }
            a(i, "locStr", bDLocation2);
        }

        public void b() throws RemoteException {
            if (this.c.location_change_notify) {
                a(com.baidu.location.h.s.b ? 54 : 55);
            }
        }
    }

    private static class b {
        private static c a = new c();
    }

    /* renamed from: com.baidu.location.b.c$c, reason: collision with other inner class name */
    private class RunnableC0014c implements Runnable {
        final /* synthetic */ c a;
        private int b;
        private boolean c;

        @Override // java.lang.Runnable
        public void run() {
            if (this.c) {
                return;
            }
            this.b++;
            this.a.t = false;
        }
    }

    private c() throws JSONException {
        this.f = null;
        this.g = false;
        this.h = true;
        this.a = false;
        this.b = false;
        this.i = null;
        this.j = null;
        this.k = new Object();
        this.l = -1L;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = new String[]{"name", "mac", "onLng", "onLat", "onLocType", "onTime", "offLng", "offLat", "offLocType", "offTime"};
        this.e = 0;
        this.r = null;
        this.s = false;
        this.t = false;
        this.u = null;
        this.f = new ArrayList<>();
        this.m = new LruCache<>(3);
        this.o = af.a().a("sp_loc_map_end_str", "");
        this.n = af.a().a("sp_loc_navi_end_str", "");
        this.p = af.a().a("sp_loc_last_navi_end_str", "");
        String strA = af.a().a("sp_bluetooth_info", "");
        if (strA == null || "".equals(strA)) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(strA);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                this.m.put(jSONObject.getString("mac"), jSONObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private a a(Messenger messenger) {
        if (this.f == null) {
            return null;
        }
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        synchronized (this.k) {
            Iterator<a> it = this.f.iterator();
            while (it.hasNext()) {
                a next = it.next();
                if (next.b.equals(messenger)) {
                    return next;
                }
            }
            return null;
        }
    }

    public static c a() {
        return b.a;
    }

    private void a(a aVar) {
        if (aVar == null) {
            return;
        }
        synchronized (this.k) {
            if (a(aVar.b) != null) {
                aVar.a(14);
            } else {
                this.f.add(aVar);
                aVar.a(13);
            }
        }
    }

    private void a(String str) {
        Intent intent = new Intent("com.baidu.location.flp.log");
        intent.setPackage("com.baidu.baidulocationdemo");
        intent.putExtra("data", str);
        intent.putExtra("pack", com.baidu.location.h.b.e);
        intent.putExtra("tag", LocationConst.HDYawConst.KEY_HD_YAW_STATE);
        com.baidu.location.f.getServiceContext().sendBroadcast(intent);
    }

    private void f() throws Throwable {
        g();
        e();
        h();
    }

    private void g() throws Throwable {
        boolean z;
        boolean z2 = false;
        try {
            try {
                synchronized (this.k) {
                    try {
                        Iterator<a> it = this.f.iterator();
                        z = false;
                        while (it.hasNext()) {
                            a next = it.next();
                            if (next.c.openGps) {
                                z2 = true;
                            }
                            if (next.c.location_change_notify) {
                                z = true;
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        z = false;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                throw th;
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
                com.baidu.location.h.s.a = z;
                if (this.g != z2 || (!com.baidu.location.f.e.a().l() && this.g)) {
                    this.g = z2;
                    com.baidu.location.f.e.a().a(this.g);
                }
            }
        } catch (Exception e2) {
            e = e2;
            z = false;
        }
    }

    private void h() {
        try {
            Iterator<a> it = this.f.iterator();
            while (it.hasNext()) {
                com.baidu.location.h.s.f = Math.min(com.baidu.location.h.s.f, it.next().c.priority);
            }
            if (com.baidu.location.f.isServing) {
                return;
            }
            com.baidu.location.h.s.f = 4;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(Bundle bundle, int i) {
        synchronized (this.k) {
            Iterator<a> it = this.f.iterator();
            while (it.hasNext()) {
                try {
                    a next = it.next();
                    next.a(i, bundle);
                    if (next.d > 4) {
                        it.remove();
                    }
                } catch (Exception unused) {
                }
            }
        }
    }

    public void a(Message message) {
        if (message == null || message.replyTo == null) {
            return;
        }
        c = System.currentTimeMillis();
        this.a = true;
        com.baidu.location.f.h.a().i();
        a(new a(message));
        f();
        if (this.s) {
            a("start");
            this.e = 0;
        }
    }

    public void a(BDLocation bDLocation) {
        b(bDLocation);
    }

    public void a(boolean z) {
        this.a = z;
        d = z ? 1 : 0;
    }

    public void b() {
        synchronized (this.k) {
            try {
                ArrayList<a> arrayList = this.f;
                if (arrayList != null) {
                    arrayList.clear();
                }
            } catch (Throwable unused) {
            }
        }
        this.i = null;
        f();
    }

    public void b(Message message) {
        synchronized (this.k) {
            a aVarA = a(message.replyTo);
            if (aVarA != null) {
                this.f.remove(aVarA);
            }
        }
        ah.a().c();
        f();
        if (this.s) {
            a("stop");
            this.e = 0;
        }
    }

    public void b(BDLocation bDLocation) {
        BDLocation bDLocation2;
        if (bDLocation == null || bDLocation.getLocType() != 161 || com.baidu.location.a.a.a().c()) {
            if (!bDLocation.hasAltitude() && this.b && (bDLocation.getLocType() == 161 || bDLocation.getLocType() == 66)) {
                double d2 = com.baidu.location.c.a.a().a(bDLocation.getLongitude(), bDLocation.getLatitude())[0];
                com.baidu.location.c.a.a();
                if (d2 < 9999.0d) {
                    bDLocation.setAltitude(d2);
                }
            }
            if (bDLocation.getLocType() == 61) {
                bDLocation.setGpsAccuracyStatus(com.baidu.location.c.a.a().a(bDLocation));
            }
            synchronized (this.k) {
                Iterator<a> it = this.f.iterator();
                while (it.hasNext()) {
                    try {
                        a next = it.next();
                        next.a(bDLocation);
                        if (next.d > 4) {
                            it.remove();
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        } else {
            if (this.j == null) {
                BDLocation bDLocation3 = new BDLocation();
                this.j = bDLocation3;
                bDLocation3.setLocType(505);
            }
            synchronized (this.k) {
                Iterator<a> it2 = this.f.iterator();
                while (it2.hasNext()) {
                    try {
                        a next2 = it2.next();
                        next2.a(this.j);
                        if (next2.d > 4) {
                            it2.remove();
                        }
                    } catch (Exception unused2) {
                    }
                }
            }
        }
        if (bDLocation != null && (bDLocation.getLocType() == 61 || bDLocation.getLocType() == 161 || bDLocation.getLocType() == 66)) {
            e.a().a(bDLocation.getLatitude(), bDLocation.getLongitude());
            e.a().a(bDLocation.getCityCode());
        }
        boolean z = aa.j;
        if (z) {
            aa.j = false;
        }
        if (com.baidu.location.h.s.W >= 10000) {
            if (bDLocation.getLocType() == 61 || bDLocation.getLocType() == 161 || bDLocation.getLocType() == 66) {
                BDLocation bDLocation4 = this.i;
                if (bDLocation4 != null) {
                    float[] fArr = new float[1];
                    Location.distanceBetween(bDLocation4.getLatitude(), this.i.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
                    if (fArr[0] <= com.baidu.location.h.s.Y && !z) {
                        return;
                    }
                    this.i = null;
                    bDLocation2 = new BDLocation(bDLocation);
                } else {
                    bDLocation2 = new BDLocation(bDLocation);
                }
                this.i = bDLocation2;
            }
        }
    }

    public void c() {
        synchronized (this.k) {
            Iterator<a> it = this.f.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }
    }

    public void c(BDLocation bDLocation) {
        Address addressA = aa.c().a(bDLocation);
        String strF = aa.c().f();
        List<Poi> listG = aa.c().g();
        PoiRegion poiRegionH = aa.c().h();
        if (addressA != null) {
            bDLocation.setAddr(addressA);
        }
        if (strF != null) {
            bDLocation.setLocationDescribe(strF);
        }
        if (listG != null) {
            bDLocation.setPoiList(listG);
        }
        if (poiRegionH != null) {
            bDLocation.setPoiRegion(poiRegionH);
        }
        if (com.baidu.location.indoor.n.a().f() && com.baidu.location.indoor.n.a().g() != null) {
            bDLocation.setFloor(com.baidu.location.indoor.n.a().g());
            bDLocation.setIndoorLocMode(true);
            if (com.baidu.location.indoor.n.a().h() != null) {
                bDLocation.setBuildingID(com.baidu.location.indoor.n.a().h());
            }
        }
        a(bDLocation);
        aa.c().c(bDLocation);
    }

    public boolean c(Message message) {
        a aVarA = a(message.replyTo);
        if (aVarA == null) {
            return false;
        }
        int i = aVarA.c.scanSpan;
        aVarA.c.scanSpan = message.getData().getInt("scanSpan", aVarA.c.scanSpan);
        if (aVarA.c.scanSpan < 1000) {
            ah.a().c();
            this.a = false;
        } else {
            this.a = true;
        }
        if (aVarA.c.scanSpan > 999 && i < 1000) {
            if (aVarA.c.mIsNeedDeviceDirect || aVarA.c.isNeedAltitude) {
                ah.a().a(aVarA.c.mIsNeedDeviceDirect);
                ah.a().b();
            }
            this.b = this.b || aVarA.c.isNeedAltitude;
            z = true;
        }
        aVarA.c.openGps = message.getData().getBoolean("openGPS", aVarA.c.openGps);
        String string = message.getData().getString("coorType");
        LocationClientOption locationClientOption = aVarA.c;
        if (string == null || string.equals("")) {
            string = aVarA.c.coorType;
        }
        locationClientOption.coorType = string;
        String string2 = message.getData().getString("addrType");
        LocationClientOption locationClientOption2 = aVarA.c;
        if (string2 == null || string2.equals("")) {
            string2 = aVarA.c.addrType;
        }
        locationClientOption2.addrType = string2;
        if (!com.baidu.location.h.s.e.equals(aVarA.c.addrType)) {
            aa.c().l();
        }
        aVarA.c.timeOut = message.getData().getInt("timeOut", aVarA.c.timeOut);
        aVarA.c.location_change_notify = message.getData().getBoolean("location_change_notify", aVarA.c.location_change_notify);
        aVarA.c.priority = message.getData().getInt("priority", aVarA.c.priority);
        com.baidu.location.h.s.f = aVarA.c.priority;
        int i2 = message.getData().getInt("wifitimeout", Integer.MAX_VALUE);
        if (i2 < com.baidu.location.h.s.af) {
            com.baidu.location.h.s.af = i2;
        }
        f();
        return z;
    }

    public int d(Message message) {
        a aVarA;
        if (message == null || message.replyTo == null || (aVarA = a(message.replyTo)) == null || aVarA.c == null) {
            return 1;
        }
        return com.baidu.location.h.s.f;
    }

    public String d() {
        StringBuilder sb;
        StringBuffer stringBuffer = new StringBuffer(256);
        if (this.f.isEmpty()) {
            return "&prod=" + com.baidu.location.h.b.f + ":" + com.baidu.location.h.b.e;
        }
        String string = stringBuffer.toString();
        try {
            a aVar = this.f.get(0);
            if (aVar.c.prodName != null) {
                stringBuffer.append(aVar.c.prodName);
            }
            if (aVar.a != null) {
                stringBuffer.append(":");
                stringBuffer.append(aVar.a);
                stringBuffer.append(LogUtils.VERTICAL);
            }
            if (string == null || string.equals("")) {
                sb = new StringBuilder();
                sb.append("&prod=");
                sb.append(com.baidu.location.h.b.f);
                sb.append(":");
                string = com.baidu.location.h.b.e;
            } else {
                sb = new StringBuilder();
                sb.append("&prod=");
            }
            sb.append(string);
            return sb.toString();
        } catch (Exception unused) {
            return "&prod=" + com.baidu.location.h.b.f + ":" + com.baidu.location.h.b.e;
        }
    }

    public void d(BDLocation bDLocation) {
        c(bDLocation);
    }

    public int e(Message message) {
        a aVarA;
        if (message == null || message.replyTo == null || (aVarA = a(message.replyTo)) == null || aVarA.c == null) {
            return 1000;
        }
        return aVarA.c.scanSpan;
    }

    public void e() {
        try {
            synchronized (this.k) {
                Iterator<a> it = this.f.iterator();
                while (it.hasNext()) {
                    it.next().b();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
