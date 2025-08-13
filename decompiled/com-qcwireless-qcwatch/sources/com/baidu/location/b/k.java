package com.baidu.location.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import androidx.work.WorkRequest;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.LocationClientOption;
import com.bumptech.glide.load.Key;
import com.hjq.permissions.Permission;
import com.king.zxing.util.LogUtils;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class k {
    private static char[] s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_.".toCharArray();
    String a;
    String b;
    private Context d;
    private TelephonyManager e;
    private WifiManager g;
    private String i;
    private String j;
    private LocationClientOption k;
    private a l;
    private String n;
    private String o;
    private boolean p;
    private com.baidu.location.f.a f = new com.baidu.location.f.a();
    private com.baidu.location.f.p h = null;
    private String m = null;
    b c = new b();
    private String q = null;
    private long r = 0;
    private boolean t = false;
    private long u = 0;
    private boolean v = false;

    public interface a {
        void onReceiveLocation(BDLocation bDLocation);
    }

    class b extends com.baidu.location.h.h {
        LocationManager b;
        a c;
        String a = null;
        boolean d = false;

        private class a implements LocationListener {
            private a() {
            }

            /* synthetic */ a(b bVar, l lVar) {
                this();
            }

            @Override // android.location.LocationListener
            public void onLocationChanged(Location location) {
                b.this.c();
                b.this.d = true;
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

        b() {
            this.ei = new HashMap();
        }

        private void a() {
            try {
                this.b = (LocationManager) k.this.d.getSystemService("location");
                a aVar = new a(this, null);
                this.c = aVar;
                LocationManager locationManager = this.b;
                if (locationManager != null) {
                    try {
                        locationManager.requestLocationUpdates("network", 1000L, 0.0f, aVar, Looper.getMainLooper());
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Throwable unused) {
            }
        }

        private void a(BDLocation bDLocation) {
            try {
                if (bDLocation.hasAddr()) {
                    Address address = bDLocation.getAddress();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(address.country);
                    stringBuffer.append(";");
                    stringBuffer.append(address.countryCode);
                    stringBuffer.append(";");
                    stringBuffer.append(address.province);
                    stringBuffer.append(";");
                    stringBuffer.append(address.city);
                    stringBuffer.append(";");
                    stringBuffer.append(address.cityCode);
                    stringBuffer.append(";");
                    stringBuffer.append(address.district);
                    stringBuffer.append(";");
                    stringBuffer.append(address.street);
                    stringBuffer.append(";");
                    stringBuffer.append(address.streetNumber);
                    stringBuffer.append(";");
                    stringBuffer.append(address.adcode);
                    stringBuffer.append(";");
                    stringBuffer.append(address.town);
                    String strEncodeToString = Base64.encodeToString((System.currentTimeMillis() + "_" + stringBuffer.toString()).getBytes(Key.STRING_CHARSET_NAME), 0);
                    SharedPreferences sharedPreferencesA = af.a(k.this.d);
                    if (sharedPreferencesA != null) {
                        SharedPreferences.Editor editorEdit = sharedPreferencesA.edit();
                        editorEdit.putString("FirstLocAddr", strEncodeToString);
                        editorEdit.apply();
                    }
                }
            } catch (Exception unused) {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c() {
            LocationManager locationManager;
            a aVar = this.c;
            if (aVar == null || (locationManager = this.b) == null) {
                return;
            }
            try {
                locationManager.removeUpdates(aVar);
            } catch (Exception unused) {
            }
        }

        public void a(String str) {
            this.a = str;
            e(com.baidu.location.h.e.e);
            if (k.this.t) {
                a();
                Timer timer = new Timer();
                timer.schedule(new m(this, timer), WorkRequest.MIN_BACKOFF_MILLIS);
                SharedPreferences.Editor editorEdit = k.this.d.getSharedPreferences("cuidRelate", 0).edit();
                editorEdit.putLong("reqtime", System.currentTimeMillis());
                editorEdit.apply();
            }
        }

        @Override // com.baidu.location.h.h
        public void a(boolean z) throws JSONException {
            BDLocation bDLocation;
            String str;
            if (!z || this.eg == null) {
                k.this.b(63);
            } else {
                try {
                    String strB = this.eg;
                    if (strB.contains("enc3")) {
                        strB = com.baidu.location.h.s.e(strB);
                    } else if (strB.contains("\"enc\"")) {
                        try {
                            JSONObject jSONObject = new JSONObject(strB);
                            if (jSONObject.has("enc")) {
                                strB = w.a().b(jSONObject.getString("enc"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        bDLocation = new BDLocation(strB);
                        k.this.a(strB);
                        if (!k.this.k.isOnceLocation()) {
                            a(bDLocation);
                        }
                    } catch (Exception unused) {
                        bDLocation = new BDLocation();
                        bDLocation.setLocType(63);
                    }
                    if (bDLocation.getLocType() == 161) {
                        if ("wgs84".equals(bDLocation.getCoorType())) {
                            if (k.this.k.coorType.equals("bd09")) {
                                str = "wgs84mc";
                            }
                            t.a().a(bDLocation);
                            bDLocation.setLocationID(Jni.en1(k.this.a + ";" + k.this.b + ";" + bDLocation.getTime()));
                            bDLocation.setRoadLocString(0.0f, 0.0f, null, null);
                            k.this.v = true;
                            k.this.l.onReceiveLocation(bDLocation);
                        } else {
                            str = k.this.k.coorType;
                        }
                        bDLocation.setCoorType(str);
                        t.a().a(bDLocation);
                        bDLocation.setLocationID(Jni.en1(k.this.a + ";" + k.this.b + ";" + bDLocation.getTime()));
                        bDLocation.setRoadLocString(0.0f, 0.0f, null, null);
                        k.this.v = true;
                        k.this.l.onReceiveLocation(bDLocation);
                    } else {
                        k.this.b(bDLocation.getLocType());
                    }
                } catch (Exception e2) {
                    k.this.b(63);
                    e2.printStackTrace();
                }
            }
            if (this.ei != null) {
                this.ei.clear();
            }
        }

        @Override // com.baidu.location.h.h
        public void b() {
            if (k.this.n != null && k.this.o != null) {
                this.a += String.format(Locale.CHINA, "&ki=%s&sn=%s", k.this.n, k.this.o);
            }
            String str = this.a + "&enc=2";
            this.a = str;
            String strEncodeTp4 = Jni.encodeTp4(str);
            this.a = null;
            this.ei.put("bloc", strEncodeTp4);
            this.ei.put("trtm", String.format(Locale.CHINA, "%d", Long.valueOf(System.currentTimeMillis())));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class c {
        public String a;
        public int b;

        c(String str, int i) {
            this.a = str;
            this.b = i;
        }
    }

    public k(Context context, LocationClientOption locationClientOption, a aVar, String str, boolean z) {
        StringBuilder sb;
        String str2 = null;
        this.d = null;
        this.e = null;
        this.g = null;
        this.i = null;
        this.j = null;
        this.n = null;
        this.o = null;
        this.a = null;
        this.b = null;
        this.p = false;
        Context applicationContext = context.getApplicationContext();
        this.d = applicationContext;
        try {
            com.baidu.location.h.s.aw = applicationContext.getPackageName();
        } catch (Exception unused) {
        }
        this.p = true;
        this.k = new LocationClientOption(locationClientOption);
        this.l = aVar;
        this.a = this.d.getPackageName();
        this.b = null;
        try {
            this.e = (TelephonyManager) this.d.getSystemService("phone");
            this.g = (WifiManager) this.d.getApplicationContext().getSystemService("wifi");
        } catch (Exception unused2) {
        }
        if (this.k.firstLocType == LocationClientOption.FirstLocType.ACCURACY_IN_FIRST_LOC) {
            com.baidu.location.f.h.a().a(this.d);
        }
        this.j = "&" + this.a + "&" + ((String) null);
        try {
            this.b = LBSAuthManager.getInstance(this.d).getCUID();
        } catch (Throwable unused3) {
            this.b = null;
            this.e = null;
            this.g = null;
        }
        if (this.b != null) {
            com.baidu.location.h.s.n = "" + this.b;
            sb = new StringBuilder();
            sb.append("&prod=");
            sb.append(this.k.prodName);
            sb.append(":");
            sb.append(this.a);
            sb.append("|&cu=");
            str2 = this.b;
        } else {
            sb = new StringBuilder();
            sb.append("&prod=");
            sb.append(this.k.prodName);
            sb.append(":");
            sb.append(this.a);
            sb.append("|&im=");
        }
        sb.append(str2);
        sb.append("&coor=");
        sb.append(locationClientOption.getCoorType());
        this.i = sb.toString();
        StringBuffer stringBuffer = new StringBuffer(256);
        stringBuffer.append("&fw=");
        stringBuffer.append("9.601");
        stringBuffer.append("&sdk=");
        stringBuffer.append("9.601");
        stringBuffer.append("&lt=1");
        stringBuffer.append("&mb=");
        stringBuffer.append(Build.MODEL);
        stringBuffer.append("&resid=");
        stringBuffer.append("12");
        locationClientOption.getAddrType();
        if (locationClientOption.getAddrType() != null && locationClientOption.getAddrType().equals("all")) {
            this.i += "&addr=allj2";
            if (locationClientOption.isNeedNewVersionRgc) {
                stringBuffer.append("&adtp=n2");
            }
        }
        if (locationClientOption.isNeedAptag || locationClientOption.isNeedAptagd) {
            this.i += "&sema=";
            if (locationClientOption.isNeedAptag) {
                this.i += "aptag|";
            }
            if (locationClientOption.isNeedAptagd) {
                this.i += "aptagd2|";
            }
            this.n = com.baidu.location.a.a.b(this.d);
            this.o = com.baidu.location.a.a.c(this.d);
        }
        stringBuffer.append("&first=1");
        if (z) {
            stringBuffer.append("&state=fore");
        }
        stringBuffer.append("&os=A");
        stringBuffer.append(Build.VERSION.SDK);
        this.i += stringBuffer.toString();
    }

    private Object a(Object obj, String str) throws Exception {
        return obj.getClass().getField(str).get(obj);
    }

    private String a(int i) throws NoSuchAlgorithmException {
        String strB;
        String strA;
        com.baidu.location.f.a aVar;
        try {
            com.baidu.location.f.a aVarA = com.baidu.location.f.h.a().a(this.f, this.e);
            this.f = aVarA;
            strB = (aVarA == null || !aVarA.b()) ? null : com.baidu.location.f.h.a().b(this.f);
            try {
                if (!TextUtils.isEmpty(strB) && (aVar = this.f) != null && aVar.n != null) {
                    strB = strB + this.f.n;
                }
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            strB = null;
        }
        try {
            this.h = null;
        } catch (Exception unused3) {
        }
        if (!a(this.g) || this.k.priority == 4) {
            strA = null;
        } else {
            this.h = new com.baidu.location.f.p(this.g.getScanResults(), 0L);
            strA = com.baidu.location.f.h.a().a(this.h, i, h(), this.t, com.baidu.location.b.a.a().b);
            try {
                LocationClientOption locationClientOption = this.k;
                if (locationClientOption != null && locationClientOption.isOnceLocation()) {
                    this.g.startScan();
                }
            } catch (Exception unused4) {
            }
        }
        if (strB == null && strA == null) {
            this.m = null;
            return null;
        }
        if (strA != null) {
            if (strB == null) {
                strB = strA;
            } else {
                strB = strB + strA;
            }
        }
        if (strB == null) {
            return null;
        }
        this.m = strB;
        if (this.i != null) {
            this.m += this.i;
        }
        j();
        return strB + this.i;
    }

    private String a(List<WifiConfiguration> list) {
        ArrayList<c> arrayList;
        int iIntValue;
        int i = 0;
        if (list == null || list.size() <= 0) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            for (WifiConfiguration wifiConfiguration : list) {
                String str = wifiConfiguration.SSID;
                try {
                    iIntValue = ((Integer) a(wifiConfiguration, "numAssociation")).intValue();
                } catch (Throwable unused) {
                    iIntValue = 0;
                }
                if (iIntValue > 0 && !TextUtils.isEmpty(str)) {
                    arrayList.add(new c(str, iIntValue));
                }
            }
        }
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        if (arrayList.size() > 1) {
            Collections.sort(arrayList, new l(this));
        }
        StringBuffer stringBuffer = new StringBuffer(200);
        for (c cVar : arrayList) {
            stringBuffer.append(cVar.a);
            stringBuffer.append(",");
            stringBuffer.append(cVar.b);
            stringBuffer.append(LogUtils.VERTICAL);
            i++;
            if (i == 4) {
                break;
            }
        }
        if (arrayList.size() >= 5) {
            stringBuffer.append(((c) arrayList.get(4)).a);
            stringBuffer.append(",");
            stringBuffer.append(((c) arrayList.get(4)).b);
        }
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) throws JSONException, NumberFormatException {
        String[] strArrSplit;
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject(Constant.MODIFY_ACTIVITY_INTENT_CONTENT);
            String string = jSONObject.has("ideocfre") ? jSONObject.getString("ideocfre") : null;
            if (TextUtils.isEmpty(string) || !string.contains(LogUtils.VERTICAL) || (strArrSplit = string.split("\\|")) == null || strArrSplit.length < 2) {
                return;
            }
            int i = Integer.parseInt(strArrSplit[0]);
            long j = Long.parseLong(strArrSplit[1]);
            SharedPreferences.Editor editorEdit = this.d.getSharedPreferences("cuidRelate", 0).edit();
            editorEdit.putInt("cuidoc", i);
            editorEdit.putLong("cuidfreq", j);
            editorEdit.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean a(WifiManager wifiManager) {
        try {
            if (!wifiManager.isWifiEnabled()) {
                if (Build.VERSION.SDK_INT <= 17) {
                    return false;
                }
                if (!wifiManager.isScanAlwaysAvailable()) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        LocationClientOption locationClientOption = this.k;
        if (locationClientOption == null || !locationClientOption.isOnceLocation()) {
            return;
        }
        BDLocation bDLocation = new BDLocation();
        bDLocation.setLocType(i);
        bDLocation.setLocationID(Jni.en1(this.a + ";" + this.b + ";" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date(System.currentTimeMillis()))));
        a aVar = this.l;
        if (aVar != null) {
            aVar.onReceiveLocation(bDLocation);
        }
    }

    private boolean i() {
        if (com.baidu.location.b.a.a().d == 0) {
            return false;
        }
        SharedPreferences sharedPreferences = this.d.getApplicationContext().getSharedPreferences("cuidRelate", 0);
        if (!sharedPreferences.contains("isInstalled")) {
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            if (!com.baidu.location.h.s.b(this.d, "com.baidu.map.location")) {
                editorEdit.putInt("isInstalled", 0);
                return false;
            }
            editorEdit.putInt("isInstalled", 1);
            editorEdit.apply();
        } else if (sharedPreferences.getInt("isInstalled", -1) == 0) {
            return false;
        }
        return sharedPreferences.getInt("cuidoc", 1) != 0 && (System.currentTimeMillis() - sharedPreferences.getLong("reqtime", 0L)) / 1000 >= sharedPreferences.getLong("cuidfreq", 60L) && com.baidu.location.h.s.c(this.d) >= 2 && a(this.g) && this.h.a() > 3;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0067  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void j() throws java.security.NoSuchAlgorithmException {
        /*
            r11 = this;
            boolean r0 = r11.i()
            r1 = 0
            if (r0 == 0) goto Lc2
            r0 = 1
            r11.t = r0
            com.baidu.location.f.p r2 = r11.h
            r3 = 0
            if (r2 == 0) goto L67
            int r2 = r2.a()
            r4 = 10
            if (r2 < r4) goto L3e
            com.baidu.location.f.h r5 = com.baidu.location.f.h.a()
            com.baidu.location.f.p r6 = r11.h
            r7 = 9
            java.lang.String r8 = r11.h()
            boolean r9 = r11.t
            com.baidu.location.b.a r2 = com.baidu.location.b.a.a()
            int r10 = r2.b
            java.lang.String r2 = r5.a(r6, r7, r8, r9, r10)
            boolean r4 = android.text.TextUtils.isEmpty(r2)
            if (r4 != 0) goto L67
            byte[] r2 = r2.getBytes()
            java.lang.String r2 = com.baidu.location.h.s.a(r2, r1)
            goto L68
        L3e:
            com.baidu.location.f.h r4 = com.baidu.location.f.h.a()
            com.baidu.location.f.p r5 = r11.h
            int r6 = r5.a()
            java.lang.String r7 = r11.h()
            boolean r8 = r11.t
            com.baidu.location.b.a r2 = com.baidu.location.b.a.a()
            int r9 = r2.b
            java.lang.String r2 = r4.a(r5, r6, r7, r8, r9)
            boolean r4 = android.text.TextUtils.isEmpty(r2)
            if (r4 != 0) goto L67
            byte[] r2 = r2.getBytes()
            java.lang.String r2 = com.baidu.location.h.s.a(r2, r1)
            goto L68
        L67:
            r2 = r3
        L68:
            java.util.List r4 = r11.k()
            java.lang.String r4 = r11.a(r4)
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto L7e
            byte[] r3 = r4.getBytes()
            java.lang.String r3 = com.baidu.location.h.s.a(r3, r1)
        L7e:
            boolean r4 = android.text.TextUtils.isEmpty(r2)
            if (r4 != 0) goto L9f
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = r11.m
            r1.append(r4)
            java.lang.String r4 = "&swf5="
            r1.append(r4)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r11.m = r1
            r11.t = r0
            goto La1
        L9f:
            r11.t = r1
        La1:
            boolean r1 = android.text.TextUtils.isEmpty(r3)
            if (r1 != 0) goto Lc4
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r11.m
            r1.append(r2)
            java.lang.String r2 = "&hwf5="
            r1.append(r2)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            r11.m = r1
            r11.t = r0
            goto Lc4
        Lc2:
            r11.t = r1
        Lc4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.k.j():void");
    }

    private List<WifiConfiguration> k() {
        try {
            WifiManager wifiManager = this.g;
            if (wifiManager != null) {
                return wifiManager.getConfiguredNetworks();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public void a() {
        b();
    }

    public String b() {
        try {
            return a(15);
        } catch (Exception unused) {
            return null;
        }
    }

    public void c() {
        LocationClientOption locationClientOption;
        BDLocation bDLocationA;
        if (this.m == null) {
            int i = 62;
            int iH = com.baidu.location.h.s.h(this.d);
            if (iH == -1) {
                i = 69;
            } else if (iH == -2) {
                i = 70;
            } else if (iH == 0) {
                i = 71;
            }
            b(i);
            return;
        }
        if (this.p) {
            if (this.d != null) {
                i.a().a(this.d);
                this.m += i.a().b();
            }
            String strB = com.baidu.location.a.a.a().b();
            if (strB != null) {
                this.m += "&ak=" + strB + "&aks=lbs_locsdk";
            }
            this.m += "&cnloc=" + t.a().b();
            BDLocation bDLocation = null;
            if (this.g != null && (locationClientOption = this.k) != null && locationClientOption.scanSpan >= 1000 && !this.k.getAddrType().equals("all") && !this.k.isNeedAptag && !this.k.isNeedAptagd && !this.k.isOnceLocation()) {
                try {
                    String strD = this.f != null ? com.baidu.location.f.h.a().d(this.f) : null;
                    if (this.g != null) {
                        bDLocationA = com.baidu.location.e.a.a().a(strD, this.k.priority != 4 ? this.g.getScanResults() : null, false);
                        if (bDLocationA != null && bDLocationA.getLocType() == 66 && Math.abs(bDLocationA.getLatitude()) < 0.10000000149011612d && Math.abs(bDLocationA.getLongitude()) < 0.10000000149011612d) {
                            bDLocationA.setLocType(67);
                        }
                    } else {
                        bDLocationA = null;
                    }
                    if (bDLocationA != null) {
                        bDLocationA.getLocType();
                    }
                    if (bDLocationA != null) {
                        bDLocationA.getLocType();
                    }
                    if (!this.k.coorType.equals("gcj02") && bDLocationA != null && bDLocationA.getLocType() == 66) {
                        double longitude = bDLocationA.getLongitude();
                        double latitude = bDLocationA.getLatitude();
                        if (Math.abs(longitude) > 0.10000000149011612d && Math.abs(latitude) > 0.10000000149011612d) {
                            double[] dArrCoorEncrypt = Jni.coorEncrypt(longitude, latitude, this.k.coorType);
                            bDLocationA.setLongitude(dArrCoorEncrypt[0]);
                            bDLocationA.setLatitude(dArrCoorEncrypt[1]);
                            bDLocationA.setCoorType(this.k.coorType);
                        }
                    }
                    if (bDLocationA != null && bDLocationA.getLocType() == 66 && Math.abs(bDLocationA.getLatitude()) > 0.10000000149011612d && Math.abs(bDLocationA.getLongitude()) > 0.10000000149011612d) {
                        if (!this.v) {
                            this.l.onReceiveLocation(bDLocationA);
                        }
                        bDLocation = bDLocationA;
                    }
                } catch (Exception unused) {
                }
            }
            if (bDLocation == null) {
                this.c.a(this.m);
            }
        }
    }

    public void d() {
        if ((this.g.isWifiEnabled() || this.g.isScanAlwaysAvailable()) && this.k.priority != 4) {
            com.baidu.location.f.h.a().a(0);
        }
        if (com.baidu.location.h.s.a(this.d, Permission.ACCESS_FINE_LOCATION) == 1) {
            com.baidu.location.f.h.a().f();
        }
    }

    public void e() {
        com.baidu.location.f.h.a().c();
    }

    public void f() {
        com.baidu.location.f.h.a().d();
    }

    public String g() {
        WifiInfo connectionInfo;
        if (this.g == null) {
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.r > 1000 && (connectionInfo = this.g.getConnectionInfo()) != null) {
            this.q = connectionInfo.getBSSID();
            this.r = jCurrentTimeMillis;
        }
        return this.q;
    }

    public String h() {
        try {
            String strG = g();
            String strReplace = strG != null ? strG.replace(":", "") : null;
            if (strReplace == null || strReplace.length() == 12) {
                return new String(strReplace);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }
}
