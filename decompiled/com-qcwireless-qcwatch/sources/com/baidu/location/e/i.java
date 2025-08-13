package com.baidu.location.e;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import com.baidu.location.BDLocation;
import com.baidu.location.h.s;
import com.qcwireless.qcwatch.ui.home.gps.service.TrackingService;
import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;

/* loaded from: classes.dex */
public final class i {
    static final String a = "http://ofloc.map.baidu.com/offline_loc";
    private static Context b;
    private static volatile i c;
    private static Object d = new Object();
    private final File e;
    private final l f;
    private final com.baidu.location.e.c g;
    private final m h;
    private final f i;
    private ExecutorService j = null;

    public enum a {
        NEED_TO_LOG,
        NO_NEED_TO_LOG
    }

    public enum b {
        IS_MIX_MODE,
        IS_NOT_MIX_MODE
    }

    private enum c {
        NETWORK_UNKNOWN,
        NETWORK_WIFI,
        NETWORK_2G,
        NETWORK_3G,
        NETWORK_4G
    }

    private i() {
        File file;
        File file2 = null;
        try {
            file = new File(b.getFilesDir(), "ofld");
        } catch (Exception unused) {
        }
        try {
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception unused2) {
            file2 = file;
            file = file2;
            this.e = file;
            com.baidu.location.e.c cVar = new com.baidu.location.e.c(this);
            this.g = cVar;
            this.f = new l(cVar.a());
            f fVar = new f(this, cVar.a());
            this.i = fVar;
            this.h = new m(this, cVar.a(), fVar.n());
        }
        this.e = file;
        com.baidu.location.e.c cVar2 = new com.baidu.location.e.c(this);
        this.g = cVar2;
        this.f = new l(cVar2.a());
        f fVar2 = new f(this, cVar2.a());
        this.i = fVar2;
        this.h = new m(this, cVar2.a(), fVar2.n());
    }

    private BDLocation a(String[] strArr) {
        new BDLocation();
        if (this.j == null) {
            this.j = Executors.newSingleThreadExecutor();
        }
        FutureTask futureTask = (FutureTask) this.j.submit(new j(this, strArr));
        try {
            return (BDLocation) futureTask.get(TrackingService.Constant.FASTEST_UPDATE_INTERVAL, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException unused) {
            futureTask.cancel(true);
            return null;
        }
    }

    public static i a() {
        i iVar;
        synchronized (d) {
            if (c == null) {
                if (b == null) {
                    a(com.baidu.location.f.getServiceContext());
                }
                c = new i();
            }
            c.q();
            iVar = c;
        }
        return iVar;
    }

    public static void a(Context context) {
        if (b == null) {
            b = context;
            com.baidu.location.h.b.a().a(b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Uri c(String str) {
        return Uri.parse(String.format("content://%s/", str));
    }

    private void q() throws JSONException {
        this.i.g();
    }

    private boolean r() {
        String packageName = b.getPackageName();
        ProviderInfo providerInfoResolveContentProvider = null;
        for (String str : this.i.o()) {
            try {
                providerInfoResolveContentProvider = b.getPackageManager().resolveContentProvider(str, 0);
            } catch (Exception unused) {
                providerInfoResolveContentProvider = null;
            }
            if (providerInfoResolveContentProvider != null) {
                break;
            }
        }
        return providerInfoResolveContentProvider == null || packageName.equals(providerInfoResolveContentProvider.packageName);
    }

    public long a(String str) {
        return this.i.a(str);
    }

    public BDLocation a(com.baidu.location.f.a aVar, com.baidu.location.f.p pVar, BDLocation bDLocation, b bVar, a aVar2) {
        String strE;
        int iA;
        if (bVar == b.IS_MIX_MODE) {
            iA = this.i.a();
            strE = com.baidu.location.h.b.a().e() + "&mixMode=1";
        } else {
            strE = com.baidu.location.h.b.a().e();
            iA = 0;
        }
        String[] strArrA = k.a(aVar, pVar, bDLocation, strE, (aVar2 == a.NEED_TO_LOG).booleanValue(), iA);
        BDLocation bDLocationA = null;
        if (strArrA.length > 0 && (bDLocationA = a(strArrA)) != null) {
            bDLocationA.getLocType();
        }
        return bDLocationA;
    }

    public Context b() {
        return b;
    }

    File c() {
        return this.e;
    }

    public boolean d() {
        return this.i.h();
    }

    public boolean e() {
        return this.i.i();
    }

    public boolean f() {
        return this.i.j();
    }

    public boolean g() {
        return this.i.k();
    }

    public boolean h() {
        return this.i.m();
    }

    public void i() {
        if (s.b()) {
            return;
        }
        this.f.a();
    }

    l j() {
        return this.f;
    }

    m k() {
        return this.h;
    }

    f l() {
        return this.i;
    }

    public void m() {
        if (!r() || s.b()) {
            return;
        }
        this.g.b();
    }

    public void n() {
    }

    public double o() {
        NetworkInfo activeNetworkInfo;
        try {
            activeNetworkInfo = ((ConnectivityManager) b.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Throwable th) {
            th.printStackTrace();
            activeNetworkInfo = null;
        }
        c cVar = c.NETWORK_UNKNOWN;
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            if (activeNetworkInfo.getType() == 1) {
                cVar = c.NETWORK_WIFI;
            }
            if (activeNetworkInfo.getType() == 0) {
                int subtype = activeNetworkInfo.getSubtype();
                if (subtype == 1 || subtype == 2 || subtype == 4 || subtype == 7 || subtype == 11) {
                    cVar = c.NETWORK_2G;
                } else if (subtype == 3 || subtype == 5 || subtype == 6 || subtype == 8 || subtype == 9 || subtype == 10 || subtype == 12 || subtype == 14 || subtype == 15) {
                    cVar = c.NETWORK_3G;
                } else if (subtype == 13) {
                    cVar = c.NETWORK_4G;
                }
            }
        }
        if (cVar == c.NETWORK_UNKNOWN) {
            return this.i.b();
        }
        if (cVar == c.NETWORK_WIFI) {
            return this.i.c();
        }
        if (cVar == c.NETWORK_2G) {
            return this.i.d();
        }
        if (cVar == c.NETWORK_3G) {
            return this.i.e();
        }
        if (cVar == c.NETWORK_4G) {
            return this.i.f();
        }
        return 0.0d;
    }
}
