package com.baidu.location.f;

import android.content.Context;
import android.location.GnssMeasurementsEvent;
import android.location.GnssNavigationMessage;
import android.location.GnssStatus;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.OnNmeaMessageListener;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import androidx.work.WorkRequest;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.b.aa;
import com.baidu.location.b.aj;
import com.baidu.location.b.ao;
import com.baidu.location.b.ap;
import com.baidu.location.h.s;
import com.hjq.permissions.Permission;
import com.king.zxing.util.LogUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Marker;

/* loaded from: classes.dex */
public class e {
    private static int A = 0;
    private static int B = 0;
    private static int C = 0;
    private static long D = 0;
    private static String R = null;
    private static double T = 100.0d;
    private static float W = -1.0f;
    public static int a = 0;
    private static final Lock al = new ReentrantLock();
    public static String b = "";
    public static String c = "";
    private static e f = null;
    private static Location j = null;
    private static int k = -1;
    private static int u;
    private static int v;
    private static int w;
    private static int x;
    private static int y;
    private static int z;
    private BDLocation ar;
    private String av;
    private Context g;
    private Location i;
    private GpsStatus n;
    private c o;
    private boolean p;
    private boolean r;
    private LocationManager h = null;
    private f l = null;
    private h m = null;
    private d q = null;
    private GpsStatus.NmeaListener s = null;
    private OnNmeaMessageListener t = null;
    private long E = 0;
    private boolean F = false;
    private boolean G = false;
    private String H = null;
    private boolean I = false;
    private long J = 0;
    private long K = 0;
    private double L = -1.0d;
    private double M = 0.0d;
    private double N = 0.0d;
    private long O = 0;
    private long P = 0;
    private long Q = 0;
    private HandlerC0020e S = null;
    private long U = 0;
    private long V = 0;
    private a X = null;
    private b Y = null;
    public ArrayList<ArrayList<Float>> d = new ArrayList<>();
    private ArrayList<ArrayList<Float>> Z = new ArrayList<>();
    private ArrayList<ArrayList<Float>> aa = new ArrayList<>();
    private ArrayList<ArrayList<Float>> ab = new ArrayList<>();
    private ArrayList<ArrayList<Float>> ac = new ArrayList<>();
    private ArrayList<ArrayList<Float>> ad = new ArrayList<>();
    private ArrayList<ArrayList<Float>> ae = new ArrayList<>();
    private ArrayList<ArrayList<Float>> af = new ArrayList<>();
    private String ag = null;
    private long ah = 0;
    private ArrayList<Integer> ai = new ArrayList<>();
    private final LinkedHashMap<String, Float> aj = new LinkedHashMap<>();
    private long ak = 0;
    private String am = null;
    private String an = null;
    private long ao = 0;
    private long ap = -1;
    private long aq = -1;
    private boolean as = false;
    private boolean at = false;
    private long au = 0;
    private long aw = 0;
    private boolean ax = false;
    private boolean ay = false;
    private boolean az = false;
    private StringBuilder aA = new StringBuilder();
    private String aB = "";
    private long aC = -1;
    private long aD = 0;
    private long aE = 0;
    private boolean aF = false;
    private long aG = 0;
    private long aH = 0;
    private long aI = 0;
    private long aJ = 0;
    public long e = 0;

    private class a extends GnssMeasurementsEvent.Callback {
        public int a;

        @Override // android.location.GnssMeasurementsEvent.Callback
        public void onGnssMeasurementsReceived(GnssMeasurementsEvent gnssMeasurementsEvent) {
        }

        @Override // android.location.GnssMeasurementsEvent.Callback
        public void onStatusChanged(int i) {
            this.a = i;
        }
    }

    private class b extends GnssNavigationMessage.Callback {
        public int a;

        private b() {
            this.a = 0;
        }

        /* synthetic */ b(e eVar, com.baidu.location.f.f fVar) {
            this();
        }

        @Override // android.location.GnssNavigationMessage.Callback
        public void onGnssNavigationMessageReceived(GnssNavigationMessage gnssNavigationMessage) {
            ap.a().a(gnssNavigationMessage, e.this.Q != 0 ? e.this.Q : System.currentTimeMillis() / 1000);
        }

        @Override // android.location.GnssNavigationMessage.Callback
        public void onStatusChanged(int i) {
            this.a = i;
        }
    }

    private class c extends GnssStatus.Callback {
        private c() {
        }

        /* synthetic */ c(e eVar, com.baidu.location.f.f fVar) {
            this();
        }

        @Override // android.location.GnssStatus.Callback
        public void onFirstFix(int i) {
        }

        @Override // android.location.GnssStatus.Callback
        public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
            ArrayList arrayList;
            if (e.this.h == null || gnssStatus == null) {
                return;
            }
            e.this.V = System.currentTimeMillis();
            int satelliteCount = gnssStatus.getSatelliteCount();
            e.this.ac.clear();
            e.this.ad.clear();
            e.this.ae.clear();
            e.this.af.clear();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < satelliteCount; i4++) {
                i++;
                ArrayList arrayList2 = new ArrayList();
                int constellationType = gnssStatus.getConstellationType(i4);
                arrayList2.add(Float.valueOf(gnssStatus.getAzimuthDegrees(i4)));
                arrayList2.add(Float.valueOf(gnssStatus.getElevationDegrees(i4)));
                arrayList2.add(Float.valueOf(gnssStatus.getCn0DbHz(i4)));
                if (gnssStatus.usedInFix(i4)) {
                    i2++;
                    arrayList2.add(Float.valueOf(1.0f));
                    if (constellationType == 1) {
                        i3++;
                    }
                } else {
                    arrayList2.add(Float.valueOf(0.0f));
                }
                arrayList2.add(Float.valueOf(gnssStatus.getSvid(i4)));
                if (constellationType == 1) {
                    arrayList2.add(Float.valueOf(1.0f));
                    arrayList = e.this.ac;
                } else {
                    if (constellationType == 5) {
                        arrayList2.add(Float.valueOf(2.0f));
                        e.this.ad.add(arrayList2);
                        e.this.aD = System.currentTimeMillis();
                    } else if (constellationType == 3) {
                        arrayList2.add(Float.valueOf(3.0f));
                        arrayList = e.this.ae;
                    } else if (constellationType == 6) {
                        arrayList2.add(Float.valueOf(4.0f));
                        arrayList = e.this.af;
                    }
                }
                arrayList.add(arrayList2);
            }
            ArrayList arrayList3 = new ArrayList();
            arrayList3.addAll(e.this.ac);
            arrayList3.addAll(e.this.ad);
            arrayList3.addAll(e.this.ae);
            arrayList3.addAll(e.this.af);
            e.this.b((ArrayList<ArrayList<Float>>) arrayList3);
            e eVar = e.this;
            eVar.Z = eVar.a(true, false, false, false, true, -1.0f);
            e eVar2 = e.this;
            e.b = eVar2.a((ArrayList<ArrayList<Float>>) eVar2.Z);
            e eVar3 = e.this;
            eVar3.aa = eVar3.a(true, true, true, true, true, -1.0f);
            e eVar4 = e.this;
            eVar4.ab = eVar4.a(true, true, true, true, false, -1.0f);
            e eVar5 = e.this;
            e.c = eVar5.a((ArrayList<ArrayList<Float>>) eVar5.ab);
            if (com.baidu.location.b.e.a().bZ == 1) {
                com.baidu.location.b.n.a().a(e.this.ab);
            }
            e.a = i2;
            int unused = e.u = i3;
            int unused2 = e.C = i;
            long unused3 = e.D = System.currentTimeMillis();
            e eVar6 = e.this;
            int unused4 = e.v = eVar6.a((ArrayList<ArrayList<Float>>) eVar6.ae, true, -1.0f).size();
            e eVar7 = e.this;
            int unused5 = e.w = eVar7.a((ArrayList<ArrayList<Float>>) eVar7.af, true, -1.0f).size();
            e eVar8 = e.this;
            int unused6 = e.x = eVar8.a((ArrayList<ArrayList<Float>>) eVar8.ad, true, -1.0f).size();
            e eVar9 = e.this;
            int unused7 = e.y = eVar9.a((ArrayList<ArrayList<Float>>) eVar9.ac, false, -1.0f).size();
            e eVar10 = e.this;
            int unused8 = e.z = eVar10.a((ArrayList<ArrayList<Float>>) eVar10.ae, false, -1.0f).size();
            e eVar11 = e.this;
            int unused9 = e.A = eVar11.a((ArrayList<ArrayList<Float>>) eVar11.af, false, -1.0f).size();
            e eVar12 = e.this;
            int unused10 = e.B = eVar12.a((ArrayList<ArrayList<Float>>) eVar12.ad, false, -1.0f).size();
        }

        @Override // android.location.GnssStatus.Callback
        public void onStarted() {
        }

        @Override // android.location.GnssStatus.Callback
        public void onStopped() {
            e.this.e((Location) null);
            e.this.b(false);
            e.a = 0;
            int unused = e.u = 0;
            int unused2 = e.v = 0;
            int unused3 = e.w = 0;
            int unused4 = e.x = 0;
            int unused5 = e.y = 0;
            int unused6 = e.z = 0;
            int unused7 = e.A = 0;
            int unused8 = e.B = 0;
            int unused9 = e.C = 0;
            int unused10 = e.k = -1;
            Location unused11 = e.j = null;
        }
    }

    private class d implements GpsStatus.Listener {
        private long b;

        private d() {
            this.b = 0L;
        }

        /* synthetic */ d(e eVar, com.baidu.location.f.f fVar) {
            this();
        }

        @Override // android.location.GpsStatus.Listener
        public void onGpsStatusChanged(int i) {
            ArrayList arrayList;
            if (e.this.h == null) {
                return;
            }
            int i2 = 0;
            if (i == 2) {
                e.this.e((Location) null);
                e.this.b(false);
                e.a = 0;
                int unused = e.u = 0;
                int unused2 = e.v = 0;
                int unused3 = e.w = 0;
                int unused4 = e.x = 0;
                return;
            }
            if (i == 4 && e.this.G) {
                try {
                    if (e.this.n == null) {
                        e eVar = e.this;
                        eVar.n = eVar.h.getGpsStatus(null);
                    } else {
                        e.this.h.getGpsStatus(e.this.n);
                    }
                    e.this.V = System.currentTimeMillis();
                    e.this.ac.clear();
                    e.this.ad.clear();
                    e.this.ae.clear();
                    e.this.af.clear();
                    int i3 = 0;
                    for (GpsSatellite gpsSatellite : e.this.n.getSatellites()) {
                        ArrayList arrayList2 = new ArrayList();
                        int prn = gpsSatellite.getPrn();
                        arrayList2.add(Float.valueOf(gpsSatellite.getAzimuth()));
                        arrayList2.add(Float.valueOf(gpsSatellite.getElevation()));
                        arrayList2.add(Float.valueOf(gpsSatellite.getSnr()));
                        if (gpsSatellite.usedInFix()) {
                            i2++;
                            arrayList2.add(Float.valueOf(1.0f));
                            if (prn >= 1 && prn <= 32) {
                                i3++;
                            }
                        } else {
                            arrayList2.add(Float.valueOf(0.0f));
                        }
                        arrayList2.add(Float.valueOf(prn));
                        if (prn >= 1 && prn <= 32) {
                            arrayList2.add(Float.valueOf(1.0f));
                            arrayList = e.this.ac;
                        } else if (prn >= 201 && prn <= 261) {
                            arrayList2.add(Float.valueOf(2.0f));
                            arrayList = e.this.ad;
                        } else if (prn >= 65 && prn <= 96) {
                            arrayList2.add(Float.valueOf(3.0f));
                            arrayList = e.this.ae;
                        } else if (prn >= 301 && prn <= 336) {
                            arrayList2.add(Float.valueOf(4.0f));
                            arrayList = e.this.af;
                        }
                        arrayList.add(arrayList2);
                    }
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.addAll(e.this.ac);
                    arrayList3.addAll(e.this.ad);
                    arrayList3.addAll(e.this.ae);
                    arrayList3.addAll(e.this.af);
                    e.this.b((ArrayList<ArrayList<Float>>) arrayList3);
                    e eVar2 = e.this;
                    eVar2.Z = eVar2.a(true, false, false, false, true, -1.0f);
                    e eVar3 = e.this;
                    e.b = eVar3.a((ArrayList<ArrayList<Float>>) eVar3.Z);
                    e eVar4 = e.this;
                    eVar4.aa = eVar4.a(true, true, true, true, true, -1.0f);
                    e eVar5 = e.this;
                    eVar5.ab = eVar5.a(true, true, true, true, false, -1.0f);
                    e eVar6 = e.this;
                    e.c = eVar6.a((ArrayList<ArrayList<Float>>) eVar6.ab);
                    if (com.baidu.location.b.e.a().bZ == 1) {
                        com.baidu.location.b.n.a().a(e.this.ab);
                    }
                    if (i3 > 0) {
                        int unused5 = e.u = i3;
                    }
                    if (i2 > 0 || System.currentTimeMillis() - this.b > 100) {
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        this.b = jCurrentTimeMillis;
                        e.a = i2;
                    }
                    long unused6 = e.D = System.currentTimeMillis();
                } catch (Exception unused7) {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.baidu.location.f.e$e, reason: collision with other inner class name */
    static class HandlerC0020e extends Handler {
        WeakReference<e> a;
        e b;

        HandlerC0020e(e eVar) {
            this.a = new WeakReference<>(eVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            e eVar;
            Location location;
            String str;
            if (com.baidu.location.f.isServing) {
                e eVar2 = this.a.get();
                this.b = eVar2;
                if (eVar2 == null) {
                    return;
                }
                int i = message.what;
                if (i == 1) {
                    Location location2 = (Location) message.obj;
                    this.b.a(location2, com.baidu.location.b.e.a().bZ == 1 ? com.baidu.location.b.n.a().a(location2) : -1);
                    return;
                }
                if (i == 3) {
                    eVar = this.b;
                    location = (Location) message.obj;
                    str = "&og=1";
                } else if (i != 4) {
                    if (i != 5) {
                        return;
                    }
                    this.b.a((String) message.obj);
                    return;
                } else {
                    eVar = this.b;
                    location = (Location) message.obj;
                    str = "&og=2";
                }
                eVar.a(str, location);
            }
        }
    }

    private class f implements LocationListener {
        private f() {
        }

        /* synthetic */ f(e eVar, com.baidu.location.f.f fVar) {
            this();
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            if (location == null && s.f == 4) {
                return;
            }
            if (s.l()) {
                com.baidu.location.f.c.a().a(location);
                return;
            }
            if (!s.a(location) && Math.abs(location.getLatitude()) <= 360.0d && Math.abs(location.getLongitude()) <= 360.0d) {
                e.this.Q = location.getTime() / 1000;
                e.this.ap = System.currentTimeMillis();
                if (e.this.P != 0) {
                    e.this.O = System.currentTimeMillis() - e.this.P;
                }
                e.this.P = System.currentTimeMillis();
                int i = e.a;
                if (i == 0) {
                    try {
                        i = location.getExtras().getInt("satellites");
                    } catch (Exception unused) {
                    }
                }
                if (i == 0 || aa.c().k()) {
                    System.currentTimeMillis();
                    long unused2 = e.this.V;
                }
                e.this.b(true);
                e.this.e(location);
                e.this.F = false;
            }
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
            e.this.e((Location) null);
            e.this.b(false);
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
            if (i == 0) {
                e.this.e((Location) null);
            } else if (i != 1) {
                if (i != 2) {
                    return;
                }
                e.this.F = false;
                return;
            } else {
                e.this.E = System.currentTimeMillis();
                e.this.F = true;
            }
            e.this.b(false);
        }
    }

    private class g implements GpsStatus.NmeaListener {
        private g() {
        }

        /* synthetic */ g(e eVar, com.baidu.location.f.f fVar) {
            this();
        }

        @Override // android.location.GpsStatus.NmeaListener
        public void onNmeaReceived(long j, String str) {
            if (e.this.S != null) {
                e.this.S.sendMessage(e.this.S.obtainMessage(5, str));
            }
        }
    }

    private class h implements LocationListener {
        private long b;

        private h() {
            this.b = 0L;
        }

        /* synthetic */ h(e eVar, com.baidu.location.f.f fVar) {
            this();
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            if (!(e.this.G && s.f == 4) && location != null && TextUtils.equals(location.getProvider(), "gps") && System.currentTimeMillis() - this.b >= WorkRequest.MIN_BACKOFF_MILLIS && Math.abs(location.getLatitude()) <= 360.0d && Math.abs(location.getLongitude()) <= 360.0d && ao.a(location, false)) {
                this.b = System.currentTimeMillis();
                if (e.this.S != null) {
                    e.this.e = System.currentTimeMillis();
                    e.this.S.sendMessage(e.this.S.obtainMessage(4, location));
                }
            }
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }

    private e() throws ClassNotFoundException {
        this.p = false;
        this.r = false;
        this.av = null;
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Class.forName("android.location.GnssStatus");
                this.p = true;
            } catch (ClassNotFoundException unused) {
                this.p = false;
            }
        }
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                this.av = Build.MANUFACTURER;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        this.r = false;
    }

    public static synchronized e a() {
        if (f == null) {
            f = new e();
        }
        return f;
    }

    public static String a(Location location) {
        StringBuilder sb;
        if (location == null) {
            return null;
        }
        float speed = (float) (location.getSpeed() * 3.6d);
        if (!location.hasSpeed()) {
            speed = -1.0f;
        }
        int accuracy = (int) (location.hasAccuracy() ? location.getAccuracy() : -1.0f);
        double altitude = location.hasAltitude() ? location.getAltitude() : 555.0d;
        float bearing = location.hasBearing() ? location.getBearing() : -1.0f;
        String str = W < -0.01f ? String.format(Locale.CHINA, "&ll=%.5f|%.5f&s=%.1f&d=%.1f&ll_r=%d&ll_n=%d&ll_h=%.2f&ll_t=%d&ll_sn=%d|%d|%d|%d|%d&ll_asn=%d|%d|%d|%d|%d&ll_snr=%.1f", Double.valueOf(location.getLongitude()), Double.valueOf(location.getLatitude()), Float.valueOf(speed), Float.valueOf(bearing), Integer.valueOf(accuracy), Integer.valueOf(a), Double.valueOf(altitude), Long.valueOf(location.getTime() / 1000), Integer.valueOf(a), Integer.valueOf(u), Integer.valueOf(v), Integer.valueOf(w), Integer.valueOf(x), Integer.valueOf(C), Integer.valueOf(y), Integer.valueOf(z), Integer.valueOf(A), Integer.valueOf(B), Double.valueOf(T)) : String.format(Locale.CHINA, "&ll=%.5f|%.5f&s=%.1f&d=%.1f&ll_r=%d&ll_n=%d&ll_h=%.2f&ll_t=%d&ll_sn=%d|%d|%d|%d|%d&ll_asn=%d|%d|%d|%d|%d&ll_snr=%.1f&ll_bp=%.2f", Double.valueOf(location.getLongitude()), Double.valueOf(location.getLatitude()), Float.valueOf(speed), Float.valueOf(bearing), Integer.valueOf(accuracy), Integer.valueOf(a), Double.valueOf(altitude), Long.valueOf(location.getTime() / 1000), Integer.valueOf(a), Integer.valueOf(u), Integer.valueOf(v), Integer.valueOf(w), Integer.valueOf(x), Integer.valueOf(C), Integer.valueOf(y), Integer.valueOf(z), Integer.valueOf(A), Integer.valueOf(B), Double.valueOf(T), Float.valueOf(W));
        try {
            if (k != 2 || j == null) {
                sb = new StringBuilder();
                sb.append(str);
                sb.append("&ll_fake=");
                sb.append(k);
            } else {
                sb = new StringBuilder();
                sb.append(str);
                sb.append(String.format(Locale.CHINA, "&ll_fake=%d|%.5f|%.5f|%d", Integer.valueOf(k), Double.valueOf(j.getLongitude()), Double.valueOf(j.getLatitude()), Long.valueOf(j.getTime() / 1000)));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(ArrayList<ArrayList<Float>> arrayList) {
        StringBuilder sb = new StringBuilder();
        if (arrayList.size() == 0) {
            return sb.toString();
        }
        Iterator<ArrayList<Float>> it = arrayList.iterator();
        boolean z2 = true;
        while (it.hasNext()) {
            ArrayList<Float> next = it.next();
            if (next.size() == 6) {
                if (z2) {
                    z2 = false;
                } else {
                    sb.append(LogUtils.VERTICAL);
                }
                sb.append(String.format("%.1f;", next.get(0)));
                sb.append(String.format("%.1f;", next.get(1)));
                sb.append(String.format("%.1f;", next.get(2)));
                sb.append(String.format("%.0f;", next.get(3)));
                sb.append(String.format("%.0f;", next.get(4)));
                sb.append(String.format("%.0f", next.get(5)));
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<ArrayList<Float>> a(ArrayList<ArrayList<Float>> arrayList, boolean z2, float f2) {
        ArrayList<ArrayList<Float>> arrayList2 = new ArrayList<>();
        if (arrayList.size() <= 40 && arrayList.size() != 0) {
            Iterator<ArrayList<Float>> it = arrayList.iterator();
            while (it.hasNext()) {
                ArrayList<Float> next = it.next();
                if (next.size() == 6) {
                    float fFloatValue = next.get(3).floatValue();
                    float fFloatValue2 = next.get(2).floatValue();
                    if (!z2 || fFloatValue >= 1.0f) {
                        if (f2 <= 0.0f || fFloatValue2 >= f2) {
                            arrayList2.add(next);
                        }
                    }
                }
            }
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<ArrayList<Float>> a(boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, float f2) {
        ArrayList<ArrayList<Float>> arrayList = new ArrayList<>();
        if (z2) {
            arrayList.addAll(a(this.ac, z6, f2));
        }
        if (z3) {
            arrayList.addAll(a(this.ad, z6, f2));
        }
        if (z4) {
            arrayList.addAll(a(this.ae, z6, f2));
        }
        if (z5) {
            arrayList.addAll(a(this.af, z6, f2));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0161, code lost:
    
        r14.setGnssProvider(com.baidu.location.BDLocation.BDLOCATION_GNSS_PROVIDER_FROM_BAIDU_BEIDOU);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(android.location.Location r13, int r14) {
        /*
            Method dump skipped, instructions count: 480
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.f.e.a(android.location.Location, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (TextUtils.isEmpty(str) || !b(str)) {
            return;
        }
        if (str.startsWith("$GPGGA,")) {
            a(str, 2, 4, 6);
        } else if (str.startsWith("$GPRMC,")) {
            a(str, 3, 5, 2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00f9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(java.lang.String r11, int r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.f.e.a(java.lang.String, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, Location location) {
        if (location == null) {
            return;
        }
        String str2 = str + com.baidu.location.b.c.a().d();
        boolean zJ = com.baidu.location.f.h.a().j();
        com.baidu.location.f.a aVarF = com.baidu.location.f.h.a().f();
        if (aVarF != null) {
            aj.a(new com.baidu.location.f.a(aVarF));
        }
        aj.a(System.currentTimeMillis());
        aj.a(new Location(location));
        aj.a(str2);
        aj.b(com.baidu.location.b.i.a().c());
        if (zJ) {
            return;
        }
        ao.a(aj.c(), (p) null, aj.d(), str2, aj.e());
    }

    public static boolean a(Location location, Location location2, boolean z2) {
        if (location == location2) {
            return false;
        }
        if (location == null || location2 == null) {
            return true;
        }
        float speed = location2.getSpeed();
        if (z2 && ((s.u == 3 || !com.baidu.location.h.g.a().a(location2.getLongitude(), location2.getLatitude())) && speed < 5.0f)) {
            return true;
        }
        float fDistanceTo = location2.distanceTo(location);
        return speed > s.K ? fDistanceTo > s.M : speed > s.J ? fDistanceTo > s.L : fDistanceTo > 5.0f;
    }

    public static String b(Location location) {
        String strA = a(location);
        if (strA == null) {
            return strA;
        }
        return strA + "&g_tp=0";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(ArrayList<ArrayList<Float>> arrayList) {
        String string;
        if (arrayList == null || arrayList.size() <= 0) {
            string = null;
        } else {
            StringBuilder sb = new StringBuilder(100);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(this.ac);
            sb.append(com.baidu.location.h.d.g(arrayList2));
            sb.append(LogUtils.VERTICAL);
            sb.append(com.baidu.location.h.d.f(arrayList2));
            sb.append(LogUtils.VERTICAL);
            sb.append(com.baidu.location.h.d.a(arrayList2));
            sb.append(LogUtils.VERTICAL);
            sb.append(com.baidu.location.h.d.h(arrayList2));
            sb.append(LogUtils.VERTICAL);
            sb.append(com.baidu.location.h.d.b(arrayList2));
            sb.append(LogUtils.VERTICAL);
            sb.append(com.baidu.location.h.d.c(arrayList2));
            sb.append(LogUtils.VERTICAL);
            sb.append(com.baidu.location.h.d.e(arrayList2));
            sb.append(LogUtils.VERTICAL);
            sb.append(com.baidu.location.h.d.d(arrayList2));
            string = sb.toString();
        }
        this.ag = string;
        this.ah = System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z2) {
        this.I = z2;
        W = -1.0f;
    }

    private boolean b(String str) {
        int i;
        if (str.indexOf(Marker.ANY_MARKER) != -1 && str.indexOf("$") != -1 && str.indexOf("$") <= str.indexOf(Marker.ANY_MARKER) && str.length() >= str.indexOf(Marker.ANY_MARKER)) {
            byte[] bytes = str.substring(0, str.indexOf(Marker.ANY_MARKER)).getBytes();
            int i2 = bytes[1];
            for (int i3 = 2; i3 < bytes.length; i3++) {
                i2 ^= bytes[i3];
            }
            String str2 = String.format("%02x", Integer.valueOf(i2));
            int iIndexOf = str.indexOf(Marker.ANY_MARKER);
            if (iIndexOf != -1 && str.length() >= (i = iIndexOf + 3) && str2.equalsIgnoreCase(str.substring(iIndexOf + 1, i))) {
                return true;
            }
        }
        return false;
    }

    public static String c(Location location) {
        String strA = a(location);
        if (strA == null) {
            return strA;
        }
        return strA + R;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Location location) {
        if (this.S == null || System.currentTimeMillis() - this.aw <= 3000) {
            return;
        }
        this.S.sendMessage(this.S.obtainMessage(1, location));
    }

    private int f(Location location) {
        if (location == null) {
            return 0;
        }
        if (Build.VERSION.SDK_INT > 17 && location.isFromMockProvider()) {
            return 100;
        }
        if (Math.abs(this.ap - this.aq) >= 3000) {
            this.aq = -1L;
            this.at = false;
            this.as = false;
            this.ar = null;
        } else if (this.ar == null) {
            if (!this.as) {
                return 200;
            }
            if (this.at) {
                return 300;
            }
        } else if (!this.at && this.as) {
            return 400;
        }
        if (this.ap > 0) {
            if (this.aq == -1) {
                return CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
            }
        }
        return 0;
    }

    public static String m() {
        long jCurrentTimeMillis = System.currentTimeMillis() - D;
        if (jCurrentTimeMillis < 0 || jCurrentTimeMillis >= 3000) {
            return null;
        }
        return String.format(Locale.US, "&gsvn=%d&gsfn=%d", Integer.valueOf(C), Integer.valueOf(a));
    }

    public void a(int i) {
        a aVar;
        LocationManager locationManager;
        if (i != 0) {
            if (i == 2) {
                this.az = false;
            } else if (i == 1) {
            }
            if (!this.ay || this.az || !this.p || (aVar = this.X) == null || (locationManager = this.h) == null) {
                return;
            }
            try {
                locationManager.unregisterGnssMeasurementsCallback(aVar);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.X = null;
            this.ax = false;
            return;
        }
        this.az = false;
        this.ay = false;
        if (this.ay) {
        }
    }

    public void a(BDLocation bDLocation) {
        if (s.l || f(this.i) <= 0) {
            com.baidu.location.b.c.a().d(bDLocation);
        } else {
            com.baidu.location.b.c.a().c(bDLocation);
        }
    }

    public void a(boolean z2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (z2) {
            c();
        } else {
            d();
        }
    }

    public synchronized void b() {
        if (com.baidu.location.f.isServing) {
            Context serviceContext = com.baidu.location.f.getServiceContext();
            this.g = serviceContext;
            try {
                this.h = (LocationManager) serviceContext.getSystemService("location");
            } catch (Exception unused) {
            }
            this.S = new HandlerC0020e(this);
        }
    }

    public void c() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.G) {
            return;
        }
        com.baidu.location.f.f fVar = null;
        try {
            if (!this.p) {
                d dVar = new d(this, fVar);
                this.q = dVar;
                this.h.addGpsStatusListener(dVar);
            } else if (s.a(this.g, Permission.ACCESS_FINE_LOCATION) == 1) {
                c cVar = new c(this, fVar);
                this.o = cVar;
                this.h.registerGnssStatusCallback(cVar);
            }
            h hVar = new h(this, fVar);
            this.m = hVar;
            this.h.requestLocationUpdates("passive", 9000L, 0.0f, hVar);
        } catch (Exception unused) {
        }
        try {
            this.l = new f(this, fVar);
            try {
                if (s.a(this.g, "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS") == 1) {
                    this.h.sendExtraCommand("gps", "force_xtra_injection", new Bundle());
                }
            } catch (Exception unused2) {
            }
            if (s.a(this.g, Permission.ACCESS_FINE_LOCATION) == 1) {
                this.h.requestLocationUpdates("gps", 1000L, 0.0f, this.l);
                this.aF = true;
            }
            if (this.p && this.Y == null && s.aC == 1 && new Random().nextDouble() < s.aB) {
                this.Y = new b(this, fVar);
            }
            b bVar = this.Y;
            if (bVar != null) {
                this.h.registerGnssNavigationMessageCallback(bVar);
            }
            this.U = System.currentTimeMillis();
            if (!s.l && s.aX == 1) {
                if (Build.VERSION.SDK_INT >= 24) {
                    com.baidu.location.f.f fVar2 = new com.baidu.location.f.f(this);
                    this.t = fVar2;
                    this.h.addNmeaListener(fVar2);
                } else {
                    this.s = new g(this, fVar);
                    Class.forName("android.location.LocationManager").getMethod("addNmeaListener", GpsStatus.NmeaListener.class).invoke(this.h, this.s);
                }
            }
            this.G = true;
        } catch (Exception unused3) {
        }
    }

    public void d() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        c cVar;
        if (this.G) {
            LocationManager locationManager = this.h;
            if (locationManager != null) {
                try {
                    d dVar = this.q;
                    if (dVar != null) {
                        locationManager.removeGpsStatusListener(dVar);
                        this.q = null;
                    }
                    if (this.p && (cVar = this.o) != null) {
                        this.h.unregisterGnssStatusCallback(cVar);
                        this.o = null;
                    }
                    h hVar = this.m;
                    if (hVar != null) {
                        this.h.removeUpdates(hVar);
                        this.m = null;
                    }
                } catch (Exception unused) {
                }
                try {
                    f fVar = this.l;
                    if (fVar != null) {
                        this.h.removeUpdates(fVar);
                        this.aF = false;
                    }
                    OnNmeaMessageListener onNmeaMessageListener = this.t;
                    if (onNmeaMessageListener != null) {
                        this.h.removeNmeaListener(onNmeaMessageListener);
                    }
                    if (this.s != null) {
                        Class.forName("android.location.LocationManager").getMethod("removeNmeaListener", GpsStatus.NmeaListener.class).invoke(this.h, this.s);
                    }
                    b bVar = this.Y;
                    if (bVar != null) {
                        this.h.unregisterGnssNavigationMessageCallback(bVar);
                    }
                    a(0);
                } catch (Exception unused2) {
                }
            }
            s.d = 0;
            s.u = 0;
            this.l = null;
            this.G = false;
            b(false);
        }
    }

    public synchronized void e() {
        HandlerC0020e handlerC0020e;
        d();
        if (this.h == null) {
            return;
        }
        try {
            handlerC0020e = this.S;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (handlerC0020e == null) {
            this.h = null;
            if (com.baidu.location.b.e.a().bZ == 1) {
                com.baidu.location.b.n.a().c();
            }
            return;
        } else {
            handlerC0020e.removeCallbacksAndMessages(null);
            this.h = null;
            if (com.baidu.location.b.e.a().bZ == 1 && com.baidu.location.b.n.a().b()) {
                com.baidu.location.b.n.a().c();
            }
            return;
        }
    }

    public String f() {
        Location location;
        if (!k() || (location = this.i) == null) {
            return null;
        }
        return String.format("%s&idgps_tp=%s", a(location).replaceAll("ll", "idll").replaceAll("&d=", "&idd=").replaceAll("&s", "&ids="), this.i.getProvider());
    }

    public String g() {
        boolean z2;
        StringBuilder sb;
        String str;
        if (this.i == null) {
            return null;
        }
        String str2 = "{\"result\":{\"time\":\"" + s.a() + "\",\"error\":\"61\"},\"content\":{\"point\":{\"x\":\"%f\",\"y\":\"%f\"},\"radius\":\"%d\",\"d\":\"%f\",\"s\":\"%f\",\"n\":\"%d\"";
        int accuracy = (int) (this.i.hasAccuracy() ? this.i.getAccuracy() : 10.0f);
        float speed = (float) (this.i.getSpeed() * 3.6d);
        if (!this.i.hasSpeed()) {
            speed = -1.0f;
        }
        double[] dArrCoorEncrypt = new double[2];
        if (com.baidu.location.h.g.a().a(this.i.getLongitude(), this.i.getLatitude())) {
            dArrCoorEncrypt = Jni.coorEncrypt(this.i.getLongitude(), this.i.getLatitude(), BDLocation.BDLOCATION_WGS84_TO_GCJ02);
            if (dArrCoorEncrypt[0] <= 0.0d && dArrCoorEncrypt[1] <= 0.0d) {
                dArrCoorEncrypt[0] = this.i.getLongitude();
                dArrCoorEncrypt[1] = this.i.getLatitude();
            }
            z2 = true;
        } else {
            dArrCoorEncrypt[0] = this.i.getLongitude();
            dArrCoorEncrypt[1] = this.i.getLatitude();
            if (dArrCoorEncrypt[0] <= 0.0d && dArrCoorEncrypt[1] <= 0.0d) {
                dArrCoorEncrypt[0] = this.i.getLongitude();
                dArrCoorEncrypt[1] = this.i.getLatitude();
            }
            z2 = false;
        }
        String str3 = String.format(Locale.CHINA, str2, Double.valueOf(dArrCoorEncrypt[0]), Double.valueOf(dArrCoorEncrypt[1]), Integer.valueOf(accuracy), Float.valueOf(this.i.getBearing()), Float.valueOf(speed), Integer.valueOf(a));
        if (!z2) {
            str3 = str3 + ",\"in_cn\":\"0\"";
        }
        if (!s.l) {
            str3 = str3 + String.format(Locale.CHINA, ",\"is_mock\":%d", Integer.valueOf(f(this.i)));
        }
        if (this.i.hasAltitude()) {
            sb = new StringBuilder();
            sb.append(str3);
            str = String.format(Locale.CHINA, ",\"h\":%.2f}}", Double.valueOf(this.i.getAltitude()));
        } else {
            sb = new StringBuilder();
            sb.append(str3);
            str = "}}";
        }
        sb.append(str);
        return sb.toString();
    }

    public Location h() {
        if (this.i != null && Math.abs(System.currentTimeMillis() - this.i.getTime()) <= 60000) {
            return this.i;
        }
        return null;
    }

    public BDLocation i() {
        if (this.ar != null && Math.abs(System.currentTimeMillis() - this.aq) <= 3000) {
            return this.ar;
        }
        return null;
    }

    public boolean j() {
        try {
            System.currentTimeMillis();
            if (a == 0) {
                try {
                    this.i.getExtras().getInt("satellites");
                } catch (Exception unused) {
                }
            }
            Location location = this.i;
            if (location != null && location.getLatitude() != 0.0d) {
                if (this.i.getLongitude() != 0.0d) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused2) {
            Location location2 = this.i;
            return (location2 == null || location2.getLatitude() == 0.0d || this.i.getLongitude() == 0.0d) ? false : true;
        }
    }

    public boolean k() {
        if (!j() || s.l() || System.currentTimeMillis() - this.J > WorkRequest.MIN_BACKOFF_MILLIS) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (!this.F || jCurrentTimeMillis - this.E >= 3000) {
            return this.I;
        }
        return true;
    }

    public boolean l() {
        return this.aF;
    }

    public synchronized String n() {
        String str;
        str = "-2";
        try {
            if (Math.abs(System.currentTimeMillis() - this.ah) < 3000) {
                String str2 = this.ag;
                str = str2 == null ? "0" : str2;
            } else {
                str = "-1";
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return "&gnsf=" + str;
    }
}
