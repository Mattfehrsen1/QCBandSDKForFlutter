package com.baidu.location.b;

import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import androidx.work.WorkRequest;
import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.baidu.location.PoiRegion;
import com.baidu.location.b.v;
import com.baidu.location.e.i;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class aa extends v {
    public static String h = "0";
    public static boolean j = false;
    private static aa k;
    private long B;
    private double F;
    private double G;
    public v.c g;
    private boolean l = true;
    private String m = null;
    private BDLocation n = null;
    private BDLocation o = null;
    private Location p = null;
    private com.baidu.location.f.p q = null;
    private com.baidu.location.f.a r = null;
    private HashSet<String> s = null;
    private com.baidu.location.f.p t = null;
    private com.baidu.location.f.a u = null;
    private boolean v = true;
    private volatile boolean w = false;
    private boolean x = false;
    private long y = 0;
    private long z = 0;
    private Address A = null;
    private String C = null;
    private List<Poi> D = null;
    private PoiRegion E = null;
    private boolean H = false;
    private long I = 0;
    private long J = 0;
    private a K = null;
    private boolean L = false;
    private boolean M = false;
    private boolean N = true;
    public final Handler i = new v.b();
    private boolean O = false;
    private boolean P = false;
    private b Q = null;
    private boolean R = false;
    private int S = 0;
    private long T = 0;
    private boolean U = false;
    private String V = null;
    private boolean W = false;
    private boolean X = false;
    private boolean Y = false;
    private long Z = 0;
    private Address aa = null;
    private boolean ab = true;

    private class a implements Runnable {
        private a() {
        }

        /* synthetic */ a(aa aaVar, ab abVar) {
            this();
        }

        @Override // java.lang.Runnable
        public void run() {
            if (aa.this.L) {
                aa.this.L = false;
                if (aa.this.M || com.baidu.location.f.e.a().k()) {
                    return;
                }
                aa.this.a(false, false);
            }
        }
    }

    private class b implements Runnable {
        private b() {
        }

        /* synthetic */ b(aa aaVar, ab abVar) {
            this();
        }

        @Override // java.lang.Runnable
        public void run() {
            if (aa.this.R) {
                aa.this.R = false;
            }
            if (aa.this.x) {
                aa.this.x = false;
                aa.this.h(null);
            }
        }
    }

    private aa() {
        this.g = null;
        this.g = new v.c();
        this.e = new v.a();
    }

    private boolean a(com.baidu.location.f.a aVar) {
        if (aVar == null) {
            return false;
        }
        if (this.u == null) {
            return true;
        }
        return !aVar.a(r0);
    }

    private boolean a(com.baidu.location.f.a aVar, com.baidu.location.f.a aVar2) {
        if (aVar2 == aVar) {
            return false;
        }
        if (aVar2 == null || aVar == null) {
            return true;
        }
        return !aVar.a(aVar2);
    }

    private boolean a(com.baidu.location.f.a aVar, HashSet<String> hashSet) {
        this.b = com.baidu.location.f.h.a().f();
        boolean zA = a(aVar, this.b);
        if (com.baidu.location.h.s.aG == 0) {
            return zA;
        }
        boolean z = zA || com.baidu.location.f.h.a().a(aVar, this.b);
        this.c = com.baidu.location.f.h.a().c(this.b);
        return z || a(hashSet, this.c);
    }

    private boolean a(com.baidu.location.f.p pVar) {
        this.a = com.baidu.location.f.h.a().u();
        if (pVar == this.a) {
            return false;
        }
        if (this.a == null || pVar == null) {
            return true;
        }
        return !com.baidu.location.f.h.a().a(this.a, pVar, com.baidu.location.h.s.aA);
    }

    private boolean a(HashSet<String> hashSet, HashSet<String> hashSet2) {
        if ((hashSet == null || hashSet.isEmpty()) && (hashSet2 == null || hashSet2.isEmpty())) {
            return false;
        }
        if (hashSet == null || hashSet.isEmpty() || hashSet2 == null || hashSet2.isEmpty()) {
            return true;
        }
        int size = hashSet.size();
        Iterator<String> it = hashSet.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (hashSet2.contains(it.next())) {
                i++;
            }
        }
        return ((float) i) < ((float) size) * com.baidu.location.h.s.aH;
    }

    private void b(String str) {
        this.X = str != null && "subway".equals(str.toLowerCase());
    }

    public static synchronized aa c() {
        if (k == null) {
            k = new aa();
        }
        return k;
    }

    private void c(Message message) {
        if (com.baidu.location.h.s.ax && !com.baidu.location.h.s.d(com.baidu.location.f.getServiceContext())) {
            BDLocation bDLocation = new BDLocation();
            bDLocation.setLocType(62);
            c.a().a(bDLocation);
            return;
        }
        if (com.baidu.location.h.s.b()) {
            Log.d("baidu_location_service", "isInforbiddenTime on request location ...");
        }
        if (message.getData().getBoolean("isWaitingLocTag", false)) {
            j = true;
        }
        if (com.baidu.location.indoor.n.a().f()) {
            return;
        }
        com.baidu.location.f.c.a().b();
        int iD = c.a().d(message);
        if (iD == 1) {
            d(message);
            return;
        }
        if (iD == 2) {
            if (com.baidu.location.f.e.a().k()) {
                e(message);
            }
        } else {
            if (iD != 3 && iD != 4) {
                throw new IllegalArgumentException(String.format("this type %d is illegal", Integer.valueOf(iD)));
            }
            g(message);
        }
    }

    private void d(Message message) {
        if (com.baidu.location.f.e.a().k()) {
            e(message);
            ah.a().c();
        } else {
            g(message);
            ah.a().b();
        }
    }

    private void d(BDLocation bDLocation) {
        if (com.baidu.location.h.s.l || bDLocation.getMockGpsStrategy() <= 0) {
            c.a().a(bDLocation);
        } else {
            c.a().c(bDLocation);
        }
    }

    private void e(Message message) {
        BDLocation bDLocation = new BDLocation(com.baidu.location.f.e.a().g());
        Location locationH = com.baidu.location.f.e.a().h();
        if (locationH != null && BDLocation.BDLOCATION_GNSS_PROVIDER_FROM_BAIDU_BEIDOU.equals(locationH.getProvider())) {
            bDLocation.setGnssProvider(BDLocation.BDLOCATION_GNSS_PROVIDER_FROM_BAIDU_BEIDOU);
        }
        if (locationH != null) {
            bDLocation.setExtrainfo(locationH.getExtras());
        }
        if (com.baidu.location.h.s.e.equals("all") || com.baidu.location.h.s.g || com.baidu.location.h.s.i) {
            float[] fArr = new float[2];
            Location.distanceBetween(this.G, this.F, bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
            if (fArr[0] < 100.0f) {
                Address address = this.A;
                if (address != null) {
                    bDLocation.setAddr(address);
                }
                String str = this.C;
                if (str != null) {
                    bDLocation.setLocationDescribe(str);
                }
                List<Poi> list = this.D;
                if (list != null) {
                    bDLocation.setPoiList(list);
                }
                PoiRegion poiRegion = this.E;
                if (poiRegion != null) {
                    bDLocation.setPoiRegion(poiRegion);
                }
            } else {
                this.H = true;
                g(null);
            }
        }
        this.n = bDLocation;
        this.o = null;
        d(bDLocation);
    }

    private void e(BDLocation bDLocation) {
        this.Y = bDLocation != null && bDLocation.isInIndoorPark();
    }

    private void f(Message message) {
        b bVar;
        if (!com.baidu.location.f.h.a().k()) {
            h(message);
            return;
        }
        this.x = true;
        if (this.Q == null) {
            this.Q = new b(this, null);
        }
        if (this.R && (bVar = this.Q) != null) {
            this.i.removeCallbacks(bVar);
        }
        this.i.postDelayed(this.Q, 3500L);
        this.R = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(Message message) {
        this.S = 0;
        if (!this.v) {
            f(message);
            this.J = SystemClock.uptimeMillis();
            return;
        }
        this.S = 1;
        this.J = SystemClock.uptimeMillis();
        if (com.baidu.location.f.h.a().b(com.baidu.location.h.s.af)) {
            f(message);
        } else {
            h(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void h(android.os.Message r19) {
        /*
            Method dump skipped, instructions count: 846
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.aa.h(android.os.Message):void");
    }

    private void m() {
        try {
            String str = null;
            String strA = af.a().a("FirstLocAddr", (String) null);
            if (strA != null) {
                String[] strArrSplit = new String(Base64.decode(strA.getBytes(), 0)).split("_");
                if (strArrSplit.length == 2) {
                    this.Z = Long.parseLong(strArrSplit[0]);
                    str = strArrSplit[1];
                }
                if (str != null) {
                    String[] strArrSplit2 = str.split(";");
                    if (strArrSplit2.length == 10) {
                        this.aa = new Address.Builder().country(strArrSplit2[0]).countryCode(strArrSplit2[1]).province(strArrSplit2[2]).city(strArrSplit2[3]).cityCode(strArrSplit2[4]).district(strArrSplit2[5]).street(strArrSplit2[6]).streetNumber(strArrSplit2[7]).adcode(strArrSplit2[8]).town(strArrSplit2[9]).build();
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean n() {
        /*
            r15 = this;
            double r0 = java.lang.Math.random()
            android.os.SystemClock.uptimeMillis()
            com.baidu.location.f.h r2 = com.baidu.location.f.h.a()
            com.baidu.location.f.a r2 = r2.f()
            com.baidu.location.f.h r3 = com.baidu.location.f.h.a()
            com.baidu.location.f.p r3 = r3.u()
            if (r3 == 0) goto L28
            int r4 = r3.a()
            if (r4 <= 0) goto L28
            com.baidu.location.f.h r4 = com.baidu.location.f.h.a()
            long r4 = r4.c(r3)
            goto L2a
        L28:
            r4 = 0
        L2a:
            r6 = 1
            r7 = 0
            if (r2 == 0) goto L3e
            boolean r2 = r2.e()
            if (r2 == 0) goto L3e
            if (r3 == 0) goto L3c
            int r2 = r3.a()
            if (r2 != 0) goto L3e
        L3c:
            r2 = 1
            goto L3f
        L3e:
            r2 = 0
        L3f:
            com.baidu.location.e.i r3 = com.baidu.location.e.i.a()
            boolean r3 = r3.d()
            r8 = 0
            if (r3 == 0) goto Lbc
            com.baidu.location.e.i r3 = com.baidu.location.e.i.a()
            boolean r3 = r3.f()
            if (r3 == 0) goto Lbc
            r9 = 60
            int r3 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r3 >= 0) goto Lbc
            if (r2 != 0) goto L6e
            r2 = 0
            int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r4 >= 0) goto Lbc
            com.baidu.location.e.i r2 = com.baidu.location.e.i.a()
            double r2 = r2.o()
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto Lbc
        L6e:
            com.baidu.location.e.i r9 = com.baidu.location.e.i.a()
            com.baidu.location.f.h r0 = com.baidu.location.f.h.a()
            com.baidu.location.f.a r10 = r0.f()
            com.baidu.location.f.h r0 = com.baidu.location.f.h.a()
            com.baidu.location.f.p r11 = r0.u()
            r12 = 0
            com.baidu.location.e.i$b r13 = com.baidu.location.e.i.b.IS_MIX_MODE
            com.baidu.location.e.i$a r14 = com.baidu.location.e.i.a.NEED_TO_LOG
            com.baidu.location.BDLocation r0 = r9.a(r10, r11, r12, r13, r14)
            if (r0 != 0) goto L8f
        L8d:
            r1 = 0
            goto Lb8
        L8f:
            java.lang.String r1 = com.baidu.location.h.s.e
            java.lang.String r2 = "all"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto La1
            java.lang.String r1 = r0.getAddrStr()
            if (r1 != 0) goto La1
            r1 = 0
            goto La2
        La1:
            r1 = 1
        La2:
            boolean r2 = com.baidu.location.h.s.g
            if (r2 == 0) goto Lad
            java.lang.String r2 = r0.getLocationDescribe()
            if (r2 != 0) goto Lad
            r1 = 0
        Lad:
            boolean r2 = com.baidu.location.h.s.i
            if (r2 == 0) goto Lb8
            java.util.List r2 = r0.getPoiList()
            if (r2 != 0) goto Lb8
            goto L8d
        Lb8:
            if (r1 != 0) goto Lbb
            goto Lbc
        Lbb:
            r8 = r0
        Lbc:
            if (r8 == 0) goto Le4
            int r0 = r8.getLocType()
            r1 = 66
            if (r0 != r1) goto Le4
            boolean r0 = r15.w
            if (r0 == 0) goto Le4
            com.baidu.location.BDLocation r0 = new com.baidu.location.BDLocation
            r0.<init>(r8)
            r1 = 161(0xa1, float:2.26E-43)
            r0.setLocType(r1)
            boolean r1 = r15.w
            if (r1 == 0) goto Le4
            r15.M = r6
            com.baidu.location.b.c r1 = com.baidu.location.b.c.a()
            r1.a(r0)
            r15.n = r0
            goto Le5
        Le4:
            r6 = 0
        Le5:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.aa.n():boolean");
    }

    private String[] o() {
        boolean z;
        j jVarA;
        int i;
        String[] strArr = {"", "Location failed beacuse we can not get any loc information!"};
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("&apl=");
        int iB = com.baidu.location.h.s.b(com.baidu.location.f.getServiceContext());
        String str = "Location failed beacuse we can not get any loc information in airplane mode, you can turn it off and try again!!";
        if (iB == 1) {
            strArr[1] = "Location failed beacuse we can not get any loc information in airplane mode, you can turn it off and try again!!";
        }
        stringBuffer.append(iB);
        String strE = com.baidu.location.h.s.e(com.baidu.location.f.getServiceContext());
        if (strE.contains("per=0|0|")) {
            strArr[1] = "Location failed beacuse we can not get any loc information without any location permission!";
        }
        stringBuffer.append(strE);
        if (Build.VERSION.SDK_INT >= 23) {
            stringBuffer.append("&loc=");
            int iC = com.baidu.location.h.s.c(com.baidu.location.f.getServiceContext());
            if (iC == 0) {
                strArr[1] = "Location failed beacuse we can not get any loc information with the phone loc mode is off, you can turn it on and try again!";
                z = true;
            } else {
                z = false;
            }
            stringBuffer.append(iC);
        } else {
            z = false;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            stringBuffer.append("&lmd=");
            int iC2 = com.baidu.location.h.s.c(com.baidu.location.f.getServiceContext());
            if (iC2 >= 0) {
                stringBuffer.append(iC2);
            }
        }
        String strH = com.baidu.location.f.h.a().h();
        String strP = com.baidu.location.f.h.a().p();
        stringBuffer.append(strP);
        stringBuffer.append(strH);
        stringBuffer.append(com.baidu.location.h.s.f(com.baidu.location.f.getServiceContext()));
        if (iB != 1) {
            if (strE.contains("per=0|0|")) {
                j.a().a(62, 4, "Location failed beacuse we can not get any loc information without any location permission!");
            } else if (z) {
                j.a().a(62, 5, "Location failed beacuse we can not get any loc information with the phone loc mode is off, you can turn it on and try again!");
            } else if (strH != null && strP != null && strH.equals("&sim=1") && !strP.equals("&wifio=1")) {
                jVarA = j.a();
                i = 6;
                str = "Location failed beacuse we can not get any loc information , you can insert a sim card or open wifi and try again!";
            } else if (!com.baidu.location.h.s.l()) {
                j.a().a(62, 9, "Location failed beacuse we can not get any loc information!");
            }
            strArr[0] = stringBuffer.toString();
            return strArr;
        }
        jVarA = j.a();
        i = 7;
        jVarA.a(62, i, str);
        strArr[0] = stringBuffer.toString();
        return strArr;
    }

    private void p() {
        this.w = false;
        this.M = false;
        this.N = false;
        this.H = false;
        q();
        if (this.ab) {
            this.ab = false;
        }
    }

    private void q() {
        if (this.n == null || !com.baidu.location.f.h.a().m()) {
            return;
        }
        ap.a().d();
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.baidu.location.Address a(com.baidu.location.BDLocation r12) {
        /*
            r11 = this;
            java.lang.String r0 = com.baidu.location.h.s.e
            java.lang.String r1 = "all"
            boolean r0 = r0.equals(r1)
            r1 = 0
            if (r0 != 0) goto L13
            boolean r0 = com.baidu.location.h.s.g
            if (r0 != 0) goto L13
            boolean r0 = com.baidu.location.h.s.i
            if (r0 == 0) goto L3a
        L13:
            r0 = 2
            float[] r0 = new float[r0]
            double r2 = r11.G
            double r4 = r11.F
            double r6 = r12.getLatitude()
            double r8 = r12.getLongitude()
            r10 = r0
            android.location.Location.distanceBetween(r2, r4, r6, r8, r10)
            r2 = 4636737291354636288(0x4059000000000000, double:100.0)
            r4 = 4652007308841189376(0x408f400000000000, double:1000.0)
            r12 = 0
            r6 = r0[r12]
            double r6 = (double) r6
            int r8 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r8 >= 0) goto L3b
            com.baidu.location.Address r12 = r11.A
            if (r12 == 0) goto L3a
            return r12
        L3a:
            return r1
        L3b:
            com.baidu.location.Address r2 = r11.A
            if (r2 != 0) goto L4f
            long r2 = java.lang.System.currentTimeMillis()
            long r4 = r11.Z
            long r2 = r2 - r4
            r4 = 3000(0xbb8, double:1.482E-320)
            int r12 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r12 >= 0) goto La0
            com.baidu.location.Address r12 = r11.aa
            goto La1
        L4f:
            r12 = r0[r12]
            double r2 = (double) r12
            int r12 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r12 >= 0) goto La0
            com.baidu.location.Address$Builder r12 = new com.baidu.location.Address$Builder
            r12.<init>()
            com.baidu.location.Address r0 = r11.A
            java.lang.String r0 = r0.country
            com.baidu.location.Address$Builder r12 = r12.country(r0)
            com.baidu.location.Address r0 = r11.A
            java.lang.String r0 = r0.countryCode
            com.baidu.location.Address$Builder r12 = r12.countryCode(r0)
            com.baidu.location.Address r0 = r11.A
            java.lang.String r0 = r0.province
            com.baidu.location.Address$Builder r12 = r12.province(r0)
            com.baidu.location.Address r0 = r11.A
            java.lang.String r0 = r0.city
            com.baidu.location.Address$Builder r12 = r12.city(r0)
            com.baidu.location.Address r0 = r11.A
            java.lang.String r0 = r0.cityCode
            com.baidu.location.Address$Builder r12 = r12.cityCode(r0)
            com.baidu.location.Address r0 = r11.A
            java.lang.String r0 = r0.district
            com.baidu.location.Address$Builder r12 = r12.district(r0)
            com.baidu.location.Address r0 = r11.A
            java.lang.String r0 = r0.adcode
            com.baidu.location.Address$Builder r12 = r12.adcode(r0)
            com.baidu.location.Address r0 = r11.A
            java.lang.String r0 = r0.town
            com.baidu.location.Address$Builder r12 = r12.town(r0)
            com.baidu.location.Address r12 = r12.build()
            goto La1
        La0:
            r12 = r1
        La1:
            r11.C = r1
            r11.D = r1
            r11.E = r1
            r0 = 1
            r11.H = r0
            android.os.Handler r0 = r11.i
            com.baidu.location.b.ab r1 = new com.baidu.location.b.ab
            r1.<init>(r11)
            r0.post(r1)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.aa.a(com.baidu.location.BDLocation):com.baidu.location.Address");
    }

    @Override // com.baidu.location.b.v
    public void a() {
        BDLocation bDLocation;
        a aVar = this.K;
        if (aVar != null && this.L) {
            this.L = false;
            this.i.removeCallbacks(aVar);
        }
        if (com.baidu.location.f.e.a().k()) {
            BDLocation bDLocation2 = new BDLocation(com.baidu.location.f.e.a().g());
            Location locationH = com.baidu.location.f.e.a().h();
            if (locationH != null && BDLocation.BDLOCATION_GNSS_PROVIDER_FROM_BAIDU_BEIDOU.equals(locationH.getProvider())) {
                bDLocation2.setGnssProvider(BDLocation.BDLOCATION_GNSS_PROVIDER_FROM_BAIDU_BEIDOU);
            }
            if (locationH != null) {
                bDLocation2.setExtrainfo(locationH.getExtras());
            }
            if (com.baidu.location.h.s.e.equals("all") || com.baidu.location.h.s.g || com.baidu.location.h.s.i) {
                float[] fArr = new float[2];
                Location.distanceBetween(this.G, this.F, bDLocation2.getLatitude(), bDLocation2.getLongitude(), fArr);
                if (fArr[0] < 100.0f) {
                    Address address = this.A;
                    if (address != null) {
                        bDLocation2.setAddr(address);
                    }
                    String str = this.C;
                    if (str != null) {
                        bDLocation2.setLocationDescribe(str);
                    }
                    List<Poi> list = this.D;
                    if (list != null) {
                        bDLocation2.setPoiList(list);
                    }
                    PoiRegion poiRegion = this.E;
                    if (poiRegion != null) {
                        bDLocation2.setPoiRegion(poiRegion);
                    }
                }
            }
            c.a().a(bDLocation2);
        } else {
            if (this.M) {
                p();
                return;
            }
            if (com.baidu.location.e.i.a().d() && com.baidu.location.e.i.a().e()) {
                bDLocation = com.baidu.location.e.i.a().a(com.baidu.location.f.h.a().f(), com.baidu.location.f.h.a().u(), null, i.b.IS_NOT_MIX_MODE, i.a.NEED_TO_LOG);
                if (bDLocation != null && bDLocation.getLocType() == 66) {
                    c.a().a(bDLocation);
                }
            } else {
                bDLocation = null;
            }
            if (bDLocation == null || bDLocation.getLocType() == 67) {
                if (this.l || this.n == null) {
                    if (com.baidu.location.e.a.a().a) {
                        bDLocation = com.baidu.location.e.a.a().a(false);
                    } else if (bDLocation == null) {
                        bDLocation = new BDLocation();
                        bDLocation.setLocType(67);
                    }
                    if (bDLocation != null) {
                        c.a().a(bDLocation);
                        if (bDLocation.getLocType() == 67 && !this.P) {
                            j.a().a(67, 3, "Offline location failed, please check the net (wifi/cell)!");
                        }
                        boolean z = true;
                        if (com.baidu.location.h.s.e.equals("all") && bDLocation.getAddrStr() == null) {
                            z = false;
                        }
                        if (com.baidu.location.h.s.g && bDLocation.getLocationDescribe() == null) {
                            z = false;
                        }
                        if (!((com.baidu.location.h.s.i && bDLocation.getPoiList() == null) ? false : z)) {
                            bDLocation.setLocType(67);
                        }
                    }
                } else {
                    c.a().a(this.n);
                }
            }
            this.o = null;
        }
        p();
    }

    @Override // com.baidu.location.b.v
    public void a(Message message) {
        a aVar = this.K;
        if (aVar != null && this.L) {
            this.L = false;
            this.i.removeCallbacks(aVar);
        }
        BDLocation bDLocation = (BDLocation) message.obj;
        int i = message.arg1;
        if (bDLocation != null && bDLocation.getLocType() == 161) {
            b(bDLocation.getTraffic());
            e(bDLocation);
            if (i == 1) {
                i.a().a(bDLocation, "gcj02", null);
            }
        }
        if (bDLocation != null && bDLocation.getLocType() == 167 && this.P) {
            bDLocation.setLocType(62);
        }
        if (!this.U && bDLocation != null && bDLocation.getLocType() == 161) {
            String cityCode = bDLocation.getCityCode();
            if (!TextUtils.isEmpty(cityCode)) {
                af.a().b("mapcity", cityCode);
                e.a().a(cityCode);
                this.U = true;
            }
        }
        if (bDLocation != null) {
            t.a().a(bDLocation);
        }
        b(bDLocation);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(boolean r9, boolean r10) {
        /*
            r8 = this;
            com.baidu.location.e.i r0 = com.baidu.location.e.i.a()
            boolean r0 = r0.d()
            r1 = 0
            if (r0 == 0) goto L47
            com.baidu.location.e.i r0 = com.baidu.location.e.i.a()
            boolean r0 = r0.g()
            if (r0 == 0) goto L47
            com.baidu.location.e.i r2 = com.baidu.location.e.i.a()
            com.baidu.location.f.h r0 = com.baidu.location.f.h.a()
            com.baidu.location.f.a r3 = r0.f()
            com.baidu.location.f.h r0 = com.baidu.location.f.h.a()
            com.baidu.location.f.p r4 = r0.u()
            r5 = 0
            com.baidu.location.e.i$b r6 = com.baidu.location.e.i.b.IS_NOT_MIX_MODE
            com.baidu.location.e.i$a r7 = com.baidu.location.e.i.a.NEED_TO_LOG
            com.baidu.location.BDLocation r0 = r2.a(r3, r4, r5, r6, r7)
            if (r0 == 0) goto L3c
            int r2 = r0.getLocType()
            r3 = 67
            if (r2 != r3) goto L5b
        L3c:
            if (r9 == 0) goto L5b
            com.baidu.location.e.a r9 = com.baidu.location.e.a.a()
            boolean r9 = r9.a
            if (r9 == 0) goto L5b
            goto L51
        L47:
            if (r9 == 0) goto L5a
            com.baidu.location.e.a r9 = com.baidu.location.e.a.a()
            boolean r9 = r9.a
            if (r9 == 0) goto L5a
        L51:
            com.baidu.location.e.a r9 = com.baidu.location.e.a.a()
            com.baidu.location.BDLocation r0 = r9.a(r1)
            goto L5b
        L5a:
            r0 = 0
        L5b:
            if (r0 == 0) goto L99
            int r9 = r0.getLocType()
            r2 = 66
            if (r9 != r2) goto L99
            r9 = 1
            java.lang.String r2 = com.baidu.location.h.s.e
            java.lang.String r3 = "all"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L77
            java.lang.String r2 = r0.getAddrStr()
            if (r2 != 0) goto L77
            r9 = 0
        L77:
            boolean r2 = com.baidu.location.h.s.g
            if (r2 == 0) goto L82
            java.lang.String r2 = r0.getLocationDescribe()
            if (r2 != 0) goto L82
            r9 = 0
        L82:
            boolean r2 = com.baidu.location.h.s.i
            if (r2 == 0) goto L8d
            java.util.List r2 = r0.getPoiList()
            if (r2 != 0) goto L8d
            goto L8e
        L8d:
            r1 = r9
        L8e:
            if (r1 != 0) goto L92
            if (r10 == 0) goto L99
        L92:
            com.baidu.location.b.c r9 = com.baidu.location.b.c.a()
            r9.a(r0)
        L99:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.aa.a(boolean, boolean):void");
    }

    public void b(Message message) {
        if (this.O) {
            c(message);
        }
    }

    public void b(BDLocation bDLocation) {
        j jVarA;
        int i;
        String str;
        String strP;
        int iC;
        BDLocation bDLocation2;
        BDLocation bDLocation3 = new BDLocation(bDLocation);
        if (bDLocation.hasAddr()) {
            Address address = bDLocation.getAddress();
            this.A = address;
            if (address != null && address.cityCode != null) {
                h = this.A.cityCode;
                this.B = System.currentTimeMillis();
            }
            this.F = bDLocation.getLongitude();
            this.G = bDLocation.getLatitude();
        }
        if (bDLocation.getLocationDescribe() != null) {
            this.C = bDLocation.getLocationDescribe();
            this.F = bDLocation.getLongitude();
            this.G = bDLocation.getLatitude();
        }
        if (bDLocation.getPoiList() != null) {
            this.D = bDLocation.getPoiList();
            this.F = bDLocation.getLongitude();
            this.G = bDLocation.getLatitude();
        }
        if (bDLocation.getPoiRegion() != null) {
            this.E = bDLocation.getPoiRegion();
            this.F = bDLocation.getLongitude();
            this.G = bDLocation.getLatitude();
        }
        boolean z = false;
        if (com.baidu.location.f.e.a().k()) {
            BDLocation bDLocation4 = new BDLocation(com.baidu.location.f.e.a().g());
            Location locationH = com.baidu.location.f.e.a().h();
            if (locationH != null && BDLocation.BDLOCATION_GNSS_PROVIDER_FROM_BAIDU_BEIDOU.equals(locationH.getProvider())) {
                bDLocation4.setGnssProvider(BDLocation.BDLOCATION_GNSS_PROVIDER_FROM_BAIDU_BEIDOU);
            }
            if (locationH != null) {
                bDLocation4.setExtrainfo(locationH.getExtras());
            }
            if (com.baidu.location.h.s.e.equals("all") || com.baidu.location.h.s.g || com.baidu.location.h.s.i) {
                float[] fArr = new float[2];
                Location.distanceBetween(this.G, this.F, bDLocation4.getLatitude(), bDLocation4.getLongitude(), fArr);
                if (fArr[0] < 100.0f) {
                    Address address2 = this.A;
                    if (address2 != null) {
                        bDLocation4.setAddr(address2);
                    }
                    String str2 = this.C;
                    if (str2 != null) {
                        bDLocation4.setLocationDescribe(str2);
                    }
                    List<Poi> list = this.D;
                    if (list != null) {
                        bDLocation4.setPoiList(list);
                    }
                    PoiRegion poiRegion = this.E;
                    if (poiRegion != null) {
                        bDLocation4.setPoiRegion(poiRegion);
                    }
                }
            }
            d(bDLocation4);
            p();
            return;
        }
        if (this.M) {
            float[] fArr2 = new float[2];
            BDLocation bDLocation5 = this.n;
            if (bDLocation5 != null) {
                Location.distanceBetween(bDLocation5.getLatitude(), this.n.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude(), fArr2);
            }
            if (fArr2[0] > 10.0f) {
                this.n = bDLocation;
                if (!this.N) {
                    this.N = false;
                    c.a().a(bDLocation);
                }
            } else if (bDLocation.getUserIndoorState() > -1) {
                this.n = bDLocation;
                c.a().a(bDLocation);
            }
            p();
            return;
        }
        if (bDLocation.getLocType() == 167) {
            j.a().a(BDLocation.TypeServerError, 8, "NetWork location failed because baidu location service can not caculate the location!");
        } else if (bDLocation.getLocType() == 161) {
            if (Build.VERSION.SDK_INT >= 19 && ((iC = com.baidu.location.h.s.c(com.baidu.location.f.getServiceContext())) == 0 || iC == 2)) {
                j.a().a(BDLocation.TypeNetWorkLocation, 1, "NetWork location successful, open gps will be better!");
            } else if (bDLocation.getRadius() >= 100.0f && bDLocation.getNetworkLocationType() != null && bDLocation.getNetworkLocationType().equals("cl") && (strP = com.baidu.location.f.h.a().p()) != null && !strP.equals("&wifio=1")) {
                j.a().a(BDLocation.TypeNetWorkLocation, 2, "NetWork location successful, open wifi will be better!");
            }
        } else {
            int locType = bDLocation.getLocType();
            int i2 = BDLocation.TypeCoarseLocation;
            if (locType == 160) {
                jVarA = j.a();
                i = 10;
                str = "Coarse location successful, open Accurately locate permission will be better!";
            } else if (com.baidu.location.h.s.l()) {
                i2 = 62;
                if (bDLocation.getLocType() == 62) {
                    jVarA = j.a();
                    i = 11;
                    str = "Coarse location failed because we can not get any loc result";
                }
            }
            jVarA.a(i2, i, str);
        }
        String strB = null;
        this.o = null;
        if (bDLocation.getLocType() == 161 && "cl".equals(bDLocation.getNetworkLocationType()) && (bDLocation2 = this.n) != null && bDLocation2.getLocType() == 161 && "wf".equals(this.n.getNetworkLocationType()) && System.currentTimeMillis() - this.z < WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS) {
            this.o = bDLocation;
            z = true;
        }
        c cVarA = c.a();
        if (z) {
            cVarA.a(this.n);
        } else {
            cVarA.a(bDLocation);
            this.z = System.currentTimeMillis();
        }
        if (!com.baidu.location.h.s.a(bDLocation)) {
            this.n = null;
        } else if (!z) {
            this.n = bDLocation;
        }
        int iA = com.baidu.location.h.s.a(d, "ssid\":\"", "\"");
        if (iA != Integer.MIN_VALUE && this.q != null) {
            strB = com.baidu.location.f.h.a().b(iA, this.q);
        }
        this.m = strB;
        if (com.baidu.location.e.i.a().d() && bDLocation.getLocType() == 161 && "cl".equals(bDLocation.getNetworkLocationType()) && a(this.r)) {
            com.baidu.location.e.i.a().a(this.r, null, bDLocation3, i.b.IS_NOT_MIX_MODE, i.a.NO_NEED_TO_LOG);
            this.u = this.r;
        }
        if (com.baidu.location.e.i.a().d() && bDLocation.getLocType() == 161 && "wf".equals(bDLocation.getNetworkLocationType())) {
            com.baidu.location.e.i.a().a(null, this.q, bDLocation3, i.b.IS_NOT_MIX_MODE, i.a.NO_NEED_TO_LOG);
            this.t = this.q;
        }
        if (this.r != null && this.q != null) {
            com.baidu.location.e.a.a().a(d, this.r, this.q, bDLocation3);
        }
        if (com.baidu.location.f.h.a().m()) {
            com.baidu.location.e.i.a().i();
            com.baidu.location.e.i.a().m();
        }
        p();
    }

    public void c(BDLocation bDLocation) {
        this.n = new BDLocation(bDLocation);
    }

    public void d() {
        this.v = true;
        this.w = false;
        this.O = true;
        m();
    }

    public void e() {
        this.w = false;
        this.x = false;
        this.M = false;
        this.N = true;
        l();
        this.O = false;
    }

    public String f() {
        return this.C;
    }

    public List<Poi> g() {
        return this.D;
    }

    public PoiRegion h() {
        return this.E;
    }

    public boolean i() {
        return this.l;
    }

    public void j() {
        if (!this.x) {
            com.baidu.location.c.b.a().d();
        } else {
            h(null);
            this.x = false;
        }
    }

    public boolean k() {
        return this.Y;
    }

    public void l() {
        this.n = null;
    }
}
