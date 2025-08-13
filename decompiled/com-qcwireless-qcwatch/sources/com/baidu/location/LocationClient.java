package com.baidu.location;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationConst;
import com.baidu.location.b.k;
import com.baidu.location.b.y;
import com.baidu.location.h.s;
import com.bumptech.glide.load.Key;
import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class LocationClient implements k.a {
    private static String B = null;
    public static final int CONNECT_HOT_SPOT_FALSE = 0;
    public static final int CONNECT_HOT_SPOT_TRUE = 1;
    public static final int CONNECT_HOT_SPOT_UNKNOWN = -1;
    public static final int LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_FINE_PERMISSION = 10;
    public static final int LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_GPS = 1;
    public static final int LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_WIFI = 2;
    public static final int LOC_DIAGNOSTIC_TYPE_COARSE_FAIL = 11;
    public static final int LOC_DIAGNOSTIC_TYPE_FAIL_UNKNOWN = 9;
    public static final int LOC_DIAGNOSTIC_TYPE_NEED_CHECK_LOC_PERMISSION = 4;
    public static final int LOC_DIAGNOSTIC_TYPE_NEED_CHECK_NET = 3;
    public static final int LOC_DIAGNOSTIC_TYPE_NEED_CLOSE_FLYMODE = 7;
    public static final int LOC_DIAGNOSTIC_TYPE_NEED_INSERT_SIMCARD_OR_OPEN_WIFI = 6;
    public static final int LOC_DIAGNOSTIC_TYPE_NEED_OPEN_PHONE_LOC_SWITCH = 5;
    public static final int LOC_DIAGNOSTIC_TYPE_SERVER_FAIL = 8;
    private static boolean M = false;
    private String A;
    private boolean C;
    private boolean D;
    private Boolean E;
    private Boolean F;
    private Boolean G;
    private boolean H;
    private k I;
    private boolean J;
    private boolean K;
    private boolean L;
    private String N;
    private ServiceConnection O;
    private long a;
    private String b;
    private LocationClientOption c;
    private LocationClientOption d;
    private boolean e;
    private Context f;
    private Messenger g;
    private a h;
    private final Messenger i;
    private ArrayList<BDLocationListener> j;
    private ArrayList<BDAbstractLocationListener> k;
    private BDLocation l;
    private BDLocation m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private b r;
    private boolean s;
    private final Object t;
    private long u;
    private long v;
    private long w;
    private com.baidu.location.d.a x;
    private BDLocationListener y;
    private String z;

    /* JADX INFO: Access modifiers changed from: private */
    static class a extends Handler {
        private final WeakReference<LocationClient> a;

        a(Looper looper, LocationClient locationClient) {
            super(looper);
            this.a = new WeakReference<>(locationClient);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) throws RemoteException {
            LocationClient locationClient = this.a.get();
            if (locationClient == null) {
                return;
            }
            int i = message.what;
            int i2 = 21;
            boolean z = true;
            if (i != 21) {
                try {
                    if (i == 303) {
                        Bundle data = message.getData();
                        int i3 = data.getInt("loctype");
                        int i4 = data.getInt("diagtype");
                        byte[] byteArray = data.getByteArray("diagmessage");
                        if (i3 <= 0 || i4 <= 0 || byteArray == null || locationClient.k == null) {
                            return;
                        }
                        Iterator it = locationClient.k.iterator();
                        while (it.hasNext()) {
                            ((BDAbstractLocationListener) it.next()).onLocDiagnosticMessage(i3, i4, new String(byteArray, Key.STRING_CHARSET_NAME));
                        }
                        return;
                    }
                    if (i == 406) {
                        Bundle data2 = message.getData();
                        byte[] byteArray2 = data2.getByteArray("mac");
                        String str = byteArray2 != null ? new String(byteArray2, Key.STRING_CHARSET_NAME) : null;
                        int i5 = data2.getInt("hotspot", -1);
                        if (locationClient.k != null) {
                            Iterator it2 = locationClient.k.iterator();
                            while (it2.hasNext()) {
                                ((BDAbstractLocationListener) it2.next()).onConnectHotSpotMessage(str, i5);
                            }
                            return;
                        }
                        return;
                    }
                    if (i == 701) {
                        locationClient.b((BDLocation) message.obj);
                        return;
                    }
                    if (i == 708) {
                        locationClient.a((String) message.obj);
                        return;
                    }
                    if (i == 804) {
                        Bundle data3 = message.getData();
                        data3.setClassLoader(BDLocation.class.getClassLoader());
                        BDLocation bDLocation = (BDLocation) data3.getParcelable(LocationConst.NaviVdrConst.KEY_VDR_LOCATION);
                        if (locationClient.k != null) {
                            Iterator it3 = locationClient.k.iterator();
                            while (it3.hasNext()) {
                                ((BDAbstractLocationListener) it3.next()).onReceiveVdrLocation(bDLocation);
                            }
                            return;
                        }
                        return;
                    }
                    if (i == 1300) {
                        locationClient.f(message);
                        return;
                    }
                    if (i == 1400) {
                        locationClient.g(message);
                        return;
                    }
                    i2 = 26;
                    if (i != 26) {
                        if (i == 27) {
                            locationClient.i(message);
                            return;
                        }
                        if (i != 54) {
                            z = false;
                            if (i != 55) {
                                if (i == 703) {
                                    Bundle data4 = message.getData();
                                    int i6 = data4.getInt(BreakpointSQLiteKey.ID, 0);
                                    if (i6 > 0) {
                                        locationClient.a(i6, (Notification) data4.getParcelable("notification"));
                                        return;
                                    }
                                    return;
                                }
                                if (i == 704) {
                                    locationClient.a(message.getData().getBoolean("removenotify"));
                                    return;
                                }
                                switch (i) {
                                    case 1:
                                        locationClient.c();
                                        break;
                                    case 2:
                                        locationClient.d();
                                        break;
                                    case 3:
                                        locationClient.c(message);
                                        break;
                                    case 4:
                                        locationClient.h();
                                        break;
                                    case 5:
                                        locationClient.e(message);
                                        break;
                                    case 6:
                                        locationClient.h(message);
                                        break;
                                    case 7:
                                        break;
                                    case 8:
                                        locationClient.d(message);
                                        break;
                                    case 9:
                                        locationClient.a(message);
                                        break;
                                    case 10:
                                        locationClient.b(message);
                                        break;
                                    case 11:
                                        locationClient.g();
                                        break;
                                    case 12:
                                        locationClient.a();
                                        break;
                                    default:
                                        super.handleMessage(message);
                                        break;
                                }
                                return;
                            }
                            if (!locationClient.c.location_change_notify) {
                                return;
                            }
                        } else if (!locationClient.c.location_change_notify) {
                            return;
                        }
                        locationClient.s = z;
                        return;
                    }
                } catch (Exception unused) {
                    return;
                }
            } else {
                Bundle data5 = message.getData();
                data5.setClassLoader(BDLocation.class.getClassLoader());
                BDLocation bDLocation2 = (BDLocation) data5.getParcelable("locStr");
                if (!locationClient.K && locationClient.J && bDLocation2.getLocType() == 66) {
                    return;
                }
                if (!locationClient.K && locationClient.J) {
                    locationClient.K = true;
                    return;
                } else if (!locationClient.K) {
                    locationClient.K = true;
                }
            }
            locationClient.a(message, i2);
        }
    }

    private class b implements Runnable {
        private b() {
        }

        /* synthetic */ b(LocationClient locationClient, com.baidu.location.c cVar) {
            this();
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (LocationClient.this.t) {
                LocationClient.this.q = false;
                if (LocationClient.this.g != null && LocationClient.this.i != null) {
                    if ((LocationClient.this.j != null && LocationClient.this.j.size() >= 1) || (LocationClient.this.k != null && LocationClient.this.k.size() >= 1)) {
                        if (!LocationClient.this.o) {
                            LocationClient.this.h.obtainMessage(4).sendToTarget();
                            return;
                        }
                        if (LocationClient.this.r == null) {
                            LocationClient locationClient = LocationClient.this;
                            locationClient.r = locationClient.new b();
                        }
                        LocationClient.this.h.postDelayed(LocationClient.this.r, LocationClient.this.c.scanSpan);
                    }
                }
            }
        }
    }

    private class c extends Thread {
        private c() {
        }

        /* synthetic */ c(LocationClient locationClient, com.baidu.location.c cVar) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                com.baidu.location.a.a.a().d();
                if (LocationClient.this.G.booleanValue()) {
                    if (LocationClient.this.I == null) {
                        com.baidu.location.f.h.a().b();
                        LocationClient.this.I = new k(LocationClient.this.f, LocationClient.this.d, LocationClient.this, null, false);
                    }
                    LocationClient locationClient = LocationClient.this;
                    locationClient.N = locationClient.I.g();
                    if (LocationClient.this.d.firstLocType == LocationClientOption.FirstLocType.ACCURACY_IN_FIRST_LOC) {
                        LocationClient.this.I.d();
                        LocationClient.this.I.e();
                    }
                }
                LocationClient.this.h.obtainMessage(1).sendToTarget();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public LocationClient(Context context) throws Exception {
        this.a = 0L;
        this.b = null;
        this.c = new LocationClientOption();
        this.d = new LocationClientOption();
        this.e = false;
        this.f = null;
        this.g = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = null;
        this.s = false;
        this.t = new Object();
        this.u = 0L;
        this.v = 0L;
        this.w = -1L;
        this.x = null;
        this.y = null;
        this.z = null;
        this.C = false;
        this.D = true;
        this.E = false;
        this.F = false;
        this.G = true;
        this.I = null;
        this.J = false;
        this.K = false;
        this.L = false;
        this.N = null;
        this.O = new com.baidu.location.c(this);
        e();
        this.f = context;
        this.c = new LocationClientOption();
        this.h = new a(Looper.getMainLooper(), this);
        this.i = new Messenger(this.h);
    }

    public LocationClient(Context context, LocationClientOption locationClientOption) throws Exception {
        this.a = 0L;
        this.b = null;
        this.c = new LocationClientOption();
        this.d = new LocationClientOption();
        this.e = false;
        this.f = null;
        this.g = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = null;
        this.s = false;
        this.t = new Object();
        this.u = 0L;
        this.v = 0L;
        this.w = -1L;
        this.x = null;
        this.y = null;
        this.z = null;
        this.C = false;
        this.D = true;
        this.E = false;
        this.F = false;
        this.G = true;
        this.I = null;
        this.J = false;
        this.K = false;
        this.L = false;
        this.N = null;
        this.O = new com.baidu.location.c(this);
        e();
        this.f = context;
        this.c = locationClientOption;
        this.d = new LocationClientOption(locationClientOption);
        this.h = new a(Looper.getMainLooper(), this);
        this.i = new Messenger(this.h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() throws RemoteException {
        Message messageObtain = Message.obtain((Handler) null, 28);
        try {
            messageObtain.replyTo = this.i;
            this.g.send(messageObtain);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, Notification notification) {
        try {
            Intent intent = new Intent(this.f, (Class<?>) f.class);
            intent.putExtra("notification", notification);
            intent.putExtra(BreakpointSQLiteKey.ID, i);
            intent.putExtra("command", 1);
            if (Build.VERSION.SDK_INT >= 26) {
                this.f.startForegroundService(intent);
            } else {
                this.f.startService(intent);
            }
            this.L = true;
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Message message) {
        if (message == null || message.obj == null) {
            return;
        }
        BDNotifyListener bDNotifyListener = (BDNotifyListener) message.obj;
        if (this.x == null) {
            this.x = new com.baidu.location.d.a(this.f, this);
        }
        this.x.a(bDNotifyListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Message message, int i) {
        if (this.e) {
            try {
                Bundle data = message.getData();
                data.setClassLoader(BDLocation.class.getClassLoader());
                BDLocation bDLocation = (BDLocation) data.getParcelable("locStr");
                this.l = bDLocation;
                if (bDLocation.getLocType() == 61) {
                    this.u = System.currentTimeMillis();
                }
                if (this.l.getLocType() == 61 || this.l.getLocType() == 161) {
                    com.baidu.location.b.a.a().a(this.l.getLatitude(), this.l.getLongitude(), this.l.getCoorType());
                }
                b(i);
            } catch (Exception unused) {
            }
        }
    }

    private void a(BDLocation bDLocation) {
        ArrayList<BDLocationListener> arrayList = this.j;
        if (arrayList != null) {
            Iterator<BDLocationListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onReceiveLocation(bDLocation);
            }
        }
        ArrayList<BDAbstractLocationListener> arrayList2 = this.k;
        if (arrayList2 != null) {
            Iterator<BDAbstractLocationListener> it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                it2.next().onReceiveLocation(bDLocation);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        ArrayList<BDAbstractLocationListener> arrayList = this.k;
        if (arrayList != null) {
            Iterator<BDAbstractLocationListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onReceiveLocString(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        try {
            Intent intent = new Intent(this.f, (Class<?>) f.class);
            intent.putExtra("removenotify", z);
            intent.putExtra("command", 2);
            this.f.startService(intent);
            this.L = true;
        } catch (Exception unused) {
        }
    }

    private boolean a(int i) throws RemoteException {
        if (this.g != null && this.e) {
            try {
                this.g.send(Message.obtain((Handler) null, i));
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private void b() {
        LBSAuthManager.getInstance(this.f.getApplicationContext()).setPrivacyMode(M);
        com.baidu.location.a.a.a().a(this.f, B);
        com.baidu.location.a.a.a().a(this.f);
    }

    private void b(int i) {
        if (this.l.getCoorType() == null) {
            this.l.setCoorType(this.c.coorType);
        }
        if (this.n || ((this.c.location_change_notify && this.l.getLocType() == 61) || this.l.getLocType() == 66 || this.l.getLocType() == 67 || this.C || this.l.getLocType() == 161)) {
            if (this.p || this.w == -1 || System.currentTimeMillis() - this.w >= getLocOption().getScanSpan() - 300) {
                ArrayList<BDLocationListener> arrayList = this.j;
                if (arrayList != null) {
                    Iterator<BDLocationListener> it = arrayList.iterator();
                    while (it.hasNext()) {
                        it.next().onReceiveLocation(this.l);
                    }
                }
                ArrayList<BDAbstractLocationListener> arrayList2 = this.k;
                if (arrayList2 != null) {
                    Iterator<BDAbstractLocationListener> it2 = arrayList2.iterator();
                    while (it2.hasNext()) {
                        it2.next().onReceiveLocation(this.l);
                    }
                }
                this.w = System.currentTimeMillis();
                if (this.p) {
                    this.p = false;
                }
            }
            if (this.l.getLocType() == 66 || this.l.getLocType() == 67) {
                return;
            }
            this.n = false;
            this.v = System.currentTimeMillis();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Message message) {
        if (message == null || message.obj == null) {
            return;
        }
        BDNotifyListener bDNotifyListener = (BDNotifyListener) message.obj;
        com.baidu.location.d.a aVar = this.x;
        if (aVar != null) {
            aVar.c(bDNotifyListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(BDLocation bDLocation) {
        if (this.D) {
            return;
        }
        this.l = bDLocation;
        if (!this.K && bDLocation.getLocType() == 161) {
            this.J = true;
            com.baidu.location.b.a.a().a(bDLocation.getLatitude(), bDLocation.getLongitude(), bDLocation.getCoorType());
        }
        if (bDLocation == null || bDLocation.getLocType() != 161 || com.baidu.location.a.a.a().c()) {
            ArrayList<BDLocationListener> arrayList = this.j;
            if (arrayList != null) {
                Iterator<BDLocationListener> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().onReceiveLocation(bDLocation);
                }
            }
            ArrayList<BDAbstractLocationListener> arrayList2 = this.k;
            if (arrayList2 != null) {
                Iterator<BDAbstractLocationListener> it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    it2.next().onReceiveLocation(bDLocation);
                }
                return;
            }
            return;
        }
        if (this.m == null) {
            BDLocation bDLocation2 = new BDLocation();
            this.m = bDLocation2;
            bDLocation2.setLocType(505);
        }
        ArrayList<BDLocationListener> arrayList3 = this.j;
        if (arrayList3 != null) {
            Iterator<BDLocationListener> it3 = arrayList3.iterator();
            while (it3.hasNext()) {
                it3.next().onReceiveLocation(this.m);
            }
        }
        ArrayList<BDAbstractLocationListener> arrayList4 = this.k;
        if (arrayList4 != null) {
            Iterator<BDAbstractLocationListener> it4 = arrayList4.iterator();
            while (it4.hasNext()) {
                it4.next().onReceiveLocation(this.m);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.e) {
            return;
        }
        if (this.G.booleanValue()) {
            boolean zD = s.d(this.f);
            if (this.d.isOnceLocation()) {
                zD = true;
            }
            if (zD) {
                try {
                    new d(this).start();
                } catch (Throwable unused) {
                }
            }
        }
        if (this.d.isOnceLocation()) {
            return;
        }
        this.G = false;
        this.b = this.f.getPackageName();
        this.z = this.b + "_bdls_v2.9";
        Intent intent = new Intent(this.f, (Class<?>) f.class);
        try {
            intent.putExtra("debug_dev", this.H);
        } catch (Exception unused2) {
        }
        if (this.c == null) {
            this.c = new LocationClientOption();
        }
        intent.putExtra("cache_exception", this.c.isIgnoreCacheException);
        intent.putExtra("kill_process", this.c.isIgnoreKillProcess);
        intent.putExtra("auth_key", B);
        intent.putExtra("cuid", LBSAuthManager.getInstance(this.f).getCUID());
        try {
            this.f.bindService(intent, this.O, 1);
        } catch (Exception e) {
            e.printStackTrace();
            this.e = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Message message) throws RemoteException {
        this.o = false;
        if (message == null || message.obj == null) {
            return;
        }
        LocationClientOption locationClientOption = (LocationClientOption) message.obj;
        if (this.c.optionEquals(locationClientOption)) {
            return;
        }
        com.baidu.location.c cVar = null;
        if (this.c.scanSpan != locationClientOption.scanSpan) {
            try {
                synchronized (this.t) {
                    if (this.q) {
                        this.h.removeCallbacks(this.r);
                        this.q = false;
                    }
                    if (locationClientOption.scanSpan >= 1000 && !this.q) {
                        if (this.r == null) {
                            this.r = new b(this, cVar);
                        }
                        this.h.postDelayed(this.r, locationClientOption.scanSpan);
                        this.q = true;
                    }
                }
            } catch (Exception unused) {
            }
        }
        this.c = new LocationClientOption(locationClientOption);
        if (this.g != null && s.h(this.f) >= 1) {
            try {
                Message messageObtain = Message.obtain((Handler) null, 15);
                messageObtain.replyTo = this.i;
                messageObtain.setData(f());
                this.g.send(messageObtain);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() throws RemoteException {
        if (!this.e || this.g == null) {
            return;
        }
        Message messageObtain = Message.obtain((Handler) null, 12);
        messageObtain.replyTo = this.i;
        try {
            this.g.send(messageObtain);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.f.unbindService(this.O);
            if (this.L) {
                try {
                    this.f.stopService(new Intent(this.f, (Class<?>) f.class));
                } catch (Exception unused) {
                }
                this.L = false;
            }
        } catch (Exception unused2) {
        }
        synchronized (this.t) {
            try {
                if (this.q) {
                    this.h.removeCallbacks(this.r);
                    this.q = false;
                }
            } catch (Exception unused3) {
            }
        }
        com.baidu.location.d.a aVar = this.x;
        if (aVar != null) {
            aVar.a();
        }
        this.g = null;
        this.o = false;
        this.C = false;
        this.e = false;
        this.J = false;
        this.K = false;
        this.w = -1L;
        this.p = false;
        this.G = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Message message) {
        if (message == null || message.obj == null) {
            return;
        }
        this.y = (BDLocationListener) message.obj;
    }

    private void e() throws Exception {
        if (M) {
            return;
        }
        Log.e("baidu_location_Client", "The location function has been stopped because you do not agree with the privacy compliance policy. Please recheck the setAgreePrivacy interface");
        throw new Exception("The location function has been stopped because you do not agree with the privacy compliance policy. Please recheck the setAgreePrivacy interface");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Message message) {
        if (message == null || message.obj == null) {
            return;
        }
        BDLocationListener bDLocationListener = (BDLocationListener) message.obj;
        if (this.j == null) {
            this.j = new ArrayList<>();
        }
        if (this.j.contains(bDLocationListener)) {
            return;
        }
        this.j.add(bDLocationListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle f() {
        if (this.c == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("packName", this.b);
        bundle.putString("prodName", this.c.prodName);
        bundle.putString("coorType", this.c.coorType);
        bundle.putString("addrType", this.c.addrType);
        bundle.putBoolean("openGPS", this.c.openGps);
        bundle.putBoolean("location_change_notify", this.c.location_change_notify);
        bundle.putInt("scanSpan", this.c.scanSpan);
        bundle.putBoolean("enableSimulateGps", this.c.enableSimulateGps);
        bundle.putInt("timeOut", this.c.timeOut);
        bundle.putInt("priority", this.c.priority);
        bundle.putBoolean("map", this.E.booleanValue());
        bundle.putBoolean("import", this.F.booleanValue());
        bundle.putBoolean("needDirect", this.c.mIsNeedDeviceDirect);
        bundle.putBoolean("isneedaptag", this.c.isNeedAptag);
        bundle.putBoolean("isneedpoiregion", this.c.isNeedPoiRegion);
        bundle.putBoolean("isneedregular", this.c.isNeedRegular);
        bundle.putBoolean("isneedaptagd", this.c.isNeedAptagd);
        bundle.putBoolean("isneedaltitude", this.c.isNeedAltitude);
        bundle.putBoolean("isneednewrgc", this.c.isNeedNewVersionRgc);
        bundle.putInt("autoNotifyMaxInterval", this.c.a());
        bundle.putInt("autoNotifyMinTimeInterval", this.c.getAutoNotifyMinTimeInterval());
        bundle.putInt("autoNotifyMinDistance", this.c.getAutoNotifyMinDistance());
        bundle.putFloat("autoNotifyLocSensitivity", this.c.b());
        bundle.putInt("wifitimeout", this.c.wifiCacheTimeOut);
        bundle.putInt("wfnum", com.baidu.location.b.a.a().b);
        bundle.putBoolean("ischeckper", com.baidu.location.b.a.a().a);
        bundle.putFloat("wfsm", (float) com.baidu.location.b.a.a().c);
        bundle.putDouble("gnmcrm", com.baidu.location.b.a.a().f);
        bundle.putInt("gnmcon", com.baidu.location.b.a.a().g);
        bundle.putInt("iupl", com.baidu.location.b.a.a().h);
        bundle.putInt("lpcs", com.baidu.location.b.a.a().e);
        bundle.putInt("hpdts", com.baidu.location.b.a.a().o);
        bundle.putInt("oldts", com.baidu.location.b.a.a().p);
        bundle.putBoolean("isEnableBeidouMode", this.c.isEnableBeidouMode);
        bundle.putInt("onic", com.baidu.location.b.a.a().q);
        bundle.putInt("nlcs", com.baidu.location.b.a.a().r);
        bundle.putFloat("ncsr", com.baidu.location.b.a.a().s);
        bundle.putFloat("cscr", com.baidu.location.b.a.a().t);
        bundle.putString("connectBssid", this.N);
        bundle.putInt("cls", com.baidu.location.b.a.a().u);
        bundle.putIntArray("ocs", com.baidu.location.b.a.a().v);
        bundle.putInt("topCellNumber", com.baidu.location.b.a.a().w);
        bundle.putInt("locStrLength", com.baidu.location.b.a.a().x);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(Message message) {
        if (message == null || message.obj == null) {
            return;
        }
        BDAbstractLocationListener bDAbstractLocationListener = (BDAbstractLocationListener) message.obj;
        if (this.k == null) {
            this.k = new ArrayList<>();
        }
        if (this.k.contains(bDAbstractLocationListener)) {
            return;
        }
        this.k.add(bDAbstractLocationListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() throws RemoteException {
        if (this.g == null) {
            return;
        }
        Message messageObtain = Message.obtain((Handler) null, 22);
        try {
            messageObtain.replyTo = this.i;
            this.g.send(messageObtain);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(Message message) {
        if (message == null || message.obj == null) {
            return;
        }
        BDAbstractLocationListener bDAbstractLocationListener = (BDAbstractLocationListener) message.obj;
        ArrayList<BDAbstractLocationListener> arrayList = this.k;
        if (arrayList == null || !arrayList.contains(bDAbstractLocationListener)) {
            return;
        }
        this.k.remove(bDAbstractLocationListener);
    }

    public static BDLocation getBDLocationInCoorType(BDLocation bDLocation, String str) {
        BDLocation bDLocation2 = new BDLocation(bDLocation);
        double[] dArrCoorEncrypt = Jni.coorEncrypt(bDLocation.getLongitude(), bDLocation.getLatitude(), str);
        bDLocation2.setLatitude(dArrCoorEncrypt[1]);
        bDLocation2.setLongitude(dArrCoorEncrypt[0]);
        return bDLocation2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() throws RemoteException {
        int i;
        LocationClientOption locationClientOption;
        if (this.g == null) {
            return;
        }
        int iH = s.h(this.f);
        com.baidu.location.c cVar = null;
        if ((System.currentTimeMillis() - this.u > 3000 || (!((locationClientOption = this.c) == null || locationClientOption.location_change_notify) || this.o)) && iH == 1) {
            if (!this.C || System.currentTimeMillis() - this.v > 20000 || this.o) {
                Message messageObtain = Message.obtain((Handler) null, 22);
                if (this.o) {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("isWaitingLocTag", this.o);
                    this.o = false;
                    messageObtain.setData(bundle);
                }
                try {
                    messageObtain.replyTo = this.i;
                    this.g.send(messageObtain);
                    this.a = System.currentTimeMillis();
                    this.n = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (iH < 1) {
            BDLocation bDLocation = new BDLocation();
            if (iH == -1) {
                i = 69;
            } else if (iH == -2) {
                i = 70;
            } else {
                if (iH == 0) {
                    i = 71;
                }
                a(bDLocation);
            }
            bDLocation.setLocType(i);
            a(bDLocation);
        }
        synchronized (this.t) {
            LocationClientOption locationClientOption2 = this.c;
            if (locationClientOption2 != null && locationClientOption2.scanSpan >= 1000 && !this.q) {
                if (this.r == null) {
                    this.r = new b(this, cVar);
                }
                this.h.postDelayed(this.r, this.c.scanSpan);
                this.q = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(Message message) {
        if (message == null || message.obj == null) {
            return;
        }
        BDLocationListener bDLocationListener = (BDLocationListener) message.obj;
        ArrayList<BDLocationListener> arrayList = this.j;
        if (arrayList == null || !arrayList.contains(bDLocationListener)) {
            return;
        }
        this.j.remove(bDLocationListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(Message message) {
        try {
            Bundle data = message.getData();
            data.setClassLoader(BDLocation.class.getClassLoader());
            BDLocation bDLocation = (BDLocation) data.getParcelable("locStr");
            if (this.y != null) {
                LocationClientOption locationClientOption = this.c;
                if (locationClientOption != null && locationClientOption.isDisableCache() && bDLocation.getLocType() == 65) {
                    return;
                }
                this.y.onReceiveLocation(bDLocation);
            }
        } catch (Exception unused) {
        }
    }

    public static void setAgreePrivacy(boolean z) {
        M = z;
    }

    public static void setKey(String str) {
        B = str;
    }

    public void disableAssistantLocation() {
        y.a().b();
    }

    public void disableLocInForeground(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("removenotify", z);
        Message messageObtainMessage = this.h.obtainMessage(TypedValues.TransitionType.TYPE_AUTO_TRANSITION);
        messageObtainMessage.setData(bundle);
        messageObtainMessage.sendToTarget();
    }

    public void enableAssistantLocation(WebView webView) {
        y.a().a(this.f, webView, this);
    }

    public void enableLocInForeground(int i, Notification notification) {
        if (i <= 0 || notification == null) {
            Log.e("baidu_location_Client", "can not startLocInForeground if the param is unlegal");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(BreakpointSQLiteKey.ID, i);
        bundle.putParcelable("notification", notification);
        Message messageObtainMessage = this.h.obtainMessage(703);
        messageObtainMessage.setData(bundle);
        messageObtainMessage.sendToTarget();
    }

    public String getAccessKey() {
        try {
            String strB = com.baidu.location.a.a.b(this.f);
            this.A = strB;
            if (TextUtils.isEmpty(strB)) {
                throw new IllegalStateException("please setting key from Manifest.xml");
            }
            return String.format("KEY=%s", this.A);
        } catch (Exception unused) {
            return null;
        }
    }

    public BDLocation getLastKnownLocation() {
        return this.l;
    }

    public LocationClientOption getLocOption() {
        return this.c;
    }

    public String getVersion() {
        return "9.6.0.1";
    }

    public boolean isStarted() {
        return this.e;
    }

    public void onReceiveLightLocString(String str) {
        Message messageObtainMessage = this.h.obtainMessage(708);
        messageObtainMessage.obj = str;
        messageObtainMessage.sendToTarget();
    }

    @Override // com.baidu.location.b.k.a
    public void onReceiveLocation(BDLocation bDLocation) {
        if ((!this.K || this.J) && bDLocation != null) {
            Message messageObtainMessage = this.h.obtainMessage(TypedValues.TransitionType.TYPE_FROM);
            messageObtainMessage.obj = bDLocation;
            messageObtainMessage.sendToTarget();
        }
    }

    public void registerLocationListener(BDAbstractLocationListener bDAbstractLocationListener) {
        if (bDAbstractLocationListener == null) {
            throw new IllegalStateException("please set a non-null listener");
        }
        Message messageObtainMessage = this.h.obtainMessage(1300);
        messageObtainMessage.obj = bDAbstractLocationListener;
        messageObtainMessage.sendToTarget();
    }

    @Deprecated
    public void registerLocationListener(BDLocationListener bDLocationListener) {
        if (bDLocationListener == null) {
            throw new IllegalStateException("please set a non-null listener");
        }
        Message messageObtainMessage = this.h.obtainMessage(5);
        messageObtainMessage.obj = bDLocationListener;
        messageObtainMessage.sendToTarget();
    }

    public void registerNotify(BDNotifyListener bDNotifyListener) {
        Message messageObtainMessage = this.h.obtainMessage(9);
        messageObtainMessage.obj = bDNotifyListener;
        messageObtainMessage.sendToTarget();
    }

    public void registerNotifyLocationListener(BDLocationListener bDLocationListener) {
        Message messageObtainMessage = this.h.obtainMessage(8);
        messageObtainMessage.obj = bDLocationListener;
        messageObtainMessage.sendToTarget();
    }

    public void removeNotifyEvent(BDNotifyListener bDNotifyListener) {
        Message messageObtainMessage = this.h.obtainMessage(10);
        messageObtainMessage.obj = bDNotifyListener;
        messageObtainMessage.sendToTarget();
    }

    public boolean requestHotSpotState() throws RemoteException {
        if (this.g != null && this.e) {
            try {
                this.g.send(Message.obtain((Handler) null, 406));
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public int requestLocation() {
        ArrayList<BDAbstractLocationListener> arrayList;
        if (this.g == null || this.i == null) {
            return 1;
        }
        ArrayList<BDLocationListener> arrayList2 = this.j;
        if ((arrayList2 == null || arrayList2.size() < 1) && ((arrayList = this.k) == null || arrayList.size() < 1)) {
            return 2;
        }
        if (System.currentTimeMillis() - this.a < 1000) {
            return 6;
        }
        this.o = true;
        this.p = true;
        Message messageObtainMessage = this.h.obtainMessage(4);
        messageObtainMessage.arg1 = 0;
        messageObtainMessage.sendToTarget();
        return 0;
    }

    public void requestNotifyLocation() {
        this.h.obtainMessage(11).sendToTarget();
    }

    public int requestOfflineLocation() {
        ArrayList<BDAbstractLocationListener> arrayList;
        if (this.g == null || this.i == null) {
            return 1;
        }
        ArrayList<BDLocationListener> arrayList2 = this.j;
        if ((arrayList2 == null || arrayList2.size() < 1) && ((arrayList = this.k) == null || arrayList.size() < 1)) {
            return 2;
        }
        this.h.obtainMessage(12).sendToTarget();
        return 0;
    }

    public void restart() {
        stop();
        this.D = false;
        this.h.sendEmptyMessageDelayed(1, 1000L);
    }

    public void setLocOption(LocationClientOption locationClientOption) {
        if (locationClientOption == null) {
            locationClientOption = new LocationClientOption();
        }
        if (locationClientOption.a() > 0) {
            locationClientOption.setScanSpan(0);
            locationClientOption.setLocationNotify(true);
        }
        this.d = new LocationClientOption(locationClientOption);
        Message messageObtainMessage = this.h.obtainMessage(3);
        messageObtainMessage.obj = locationClientOption;
        messageObtainMessage.sendToTarget();
    }

    public void start() {
        this.D = false;
        if (s.b()) {
            return;
        }
        b();
        com.baidu.location.b.a.a().a(this.f, this.d, (String) null);
        new c(this, null).start();
    }

    public boolean startIndoorMode() throws RemoteException {
        boolean zA = a(110);
        if (zA) {
            this.C = true;
        }
        return zA;
    }

    public boolean startVdr(ArrayList<String> arrayList) throws RemoteException {
        if (this.g == null || !this.e || arrayList == null) {
            return false;
        }
        if (arrayList != null) {
            try {
                if (arrayList.size() == 1) {
                    String str = arrayList.get(0);
                    Message messageObtain = Message.obtain((Handler) null, 802);
                    Bundle bundle = new Bundle();
                    bundle.putByteArray("naviLinkList_gz", s.a(str.getBytes(Key.STRING_CHARSET_NAME)));
                    messageObtain.setData(bundle);
                    this.g.send(messageObtain);
                }
            } catch (Exception unused) {
                return false;
            }
        }
        return true;
    }

    public void stop() {
        this.D = true;
        this.h.obtainMessage(2).sendToTarget();
        k kVar = this.I;
        if (kVar != null) {
            kVar.f();
            this.I = null;
        }
    }

    public boolean stopIndoorMode() throws RemoteException {
        boolean zA = a(111);
        if (zA) {
            this.C = false;
        }
        return zA;
    }

    public void unRegisterLocationListener(BDAbstractLocationListener bDAbstractLocationListener) {
        if (bDAbstractLocationListener == null) {
            throw new IllegalStateException("please set a non-null listener");
        }
        Message messageObtainMessage = this.h.obtainMessage(1400);
        messageObtainMessage.obj = bDAbstractLocationListener;
        messageObtainMessage.sendToTarget();
    }

    @Deprecated
    public void unRegisterLocationListener(BDLocationListener bDLocationListener) {
        if (bDLocationListener == null) {
            throw new IllegalStateException("please set a non-null listener");
        }
        Message messageObtainMessage = this.h.obtainMessage(6);
        messageObtainMessage.obj = bDLocationListener;
        messageObtainMessage.sendToTarget();
    }

    public boolean updateLocation(Location location) throws RemoteException {
        if (this.g == null || this.i == null || location == null) {
            return false;
        }
        try {
            Message messageObtain = Message.obtain((Handler) null, 57);
            messageObtain.obj = location;
            this.g.send(messageObtain);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
