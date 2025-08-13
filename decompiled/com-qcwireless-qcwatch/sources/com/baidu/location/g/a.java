package com.baidu.location.g;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.location.LLSInterface;
import com.baidu.location.b.aa;
import com.baidu.location.b.ak;
import com.baidu.location.b.al;
import com.baidu.location.b.ao;
import com.baidu.location.b.ap;
import com.baidu.location.b.ar;
import com.baidu.location.b.c;
import com.baidu.location.b.j;
import com.baidu.location.b.s;
import com.baidu.location.b.t;
import com.baidu.location.c.d;
import com.baidu.location.c.e;
import com.baidu.location.c.g;
import com.baidu.location.e.i;
import com.baidu.location.f;
import com.baidu.location.f.h;
import com.baidu.location.indoor.n;
import java.io.IOException;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class a extends Service implements LLSInterface {
    static HandlerC0021a a;
    public static long c;
    private static long g;
    Messenger b = null;
    private Looper d = null;
    private HandlerThread e = null;
    private boolean f = true;
    private int h = 0;
    private boolean i = true;

    /* renamed from: com.baidu.location.g.a$a, reason: collision with other inner class name */
    public static class HandlerC0021a extends Handler {
        private final WeakReference<a> a;

        public HandlerC0021a(Looper looper, a aVar) {
            super(looper);
            this.a = new WeakReference<>(aVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            a aVar = this.a.get();
            if (aVar == null) {
                return;
            }
            if (f.isServing) {
                int i = message.what;
                if (i == 11) {
                    aVar.a(message);
                } else if (i == 12) {
                    aVar.b(message);
                } else if (i == 15) {
                    aVar.c(message);
                } else if (i == 22) {
                    aa.c().b(message);
                } else if (i == 28) {
                    aa.c().a(true, true);
                } else if (i == 41) {
                    aa.c().j();
                } else if (i == 114) {
                    Object obj = message.obj;
                    if (obj != null) {
                        n.a().a((Bundle) obj);
                    }
                } else if (i == 401) {
                    try {
                        message.getData();
                    } catch (Exception unused) {
                    }
                } else if (i == 406) {
                    s.a().e();
                } else if (i != 705) {
                    switch (i) {
                        case 110:
                            n.a().c();
                            break;
                        case 111:
                            n.a().d();
                            break;
                        case 112:
                            n.a().b();
                            break;
                    }
                } else {
                    c.a().a(message.getData().getBoolean("foreground"));
                }
            }
            if (message.what == 1) {
                aVar.d();
            }
            if (message.what == 0) {
                aVar.c();
            }
            super.handleMessage(message);
        }
    }

    public static Handler a() {
        return a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Message message) {
        c.a().a(message);
        i.a();
        e.a().d();
    }

    public static long b() {
        return g;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Message message) {
        c.a().b(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        com.baidu.location.h.a.a.a().b();
        e.a().b();
        com.baidu.location.h.b.a();
        com.baidu.location.b.i.a().a(f.getServiceContext());
        com.baidu.location.b.e.a().b(false);
        com.baidu.location.b.e.a().c();
        try {
            ap.a().e();
        } catch (Exception unused) {
        }
        t.a().a(f.getServiceContext());
        s.a().b();
        com.baidu.location.f.e.a().b();
        h.a().b();
        h.a().a(f.getServiceContext());
        aa.c().d();
        com.baidu.location.e.a.a().c();
        d.a().b();
        g.a().b();
        com.baidu.location.c.a.a().b();
        this.h = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Message message) {
        c.a().c(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        com.baidu.location.f.e.a().e();
        if (this.i) {
            i.a().n();
        }
        ap.a().f();
        e.a().c();
        d.a().c();
        com.baidu.location.c.b.a().c();
        com.baidu.location.c.a.a().c();
        j.a().b();
        h.a().d();
        aa.c().e();
        n.a().d();
        s.a().c();
        if (this.i) {
            ao.d();
        }
        c.a().b();
        try {
            al.a().d();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.h = 4;
        if (this.f) {
            return;
        }
        Process.killProcess(Process.myPid());
    }

    @Override // com.baidu.location.LLSInterface
    public double getVersion() {
        return 9.60099983215332d;
    }

    @Override // android.app.Service, com.baidu.location.LLSInterface
    public IBinder onBind(Intent intent) {
        boolean z;
        String string;
        Bundle extras = intent.getExtras();
        String str = null;
        if (extras != null) {
            com.baidu.location.h.b.h = extras.getString("key");
            com.baidu.location.h.b.g = extras.getString("sign");
            this.f = extras.getBoolean("kill_process");
            boolean z2 = extras.getBoolean("cache_exception");
            String string2 = extras.getString("auth_key");
            string = extras.getString("cuid");
            com.baidu.location.h.s.aZ = extras.getString("proxyHost");
            com.baidu.location.h.s.ba = extras.getInt("proxyPort");
            com.baidu.location.h.s.bb = extras.getString("username");
            com.baidu.location.h.s.bc = extras.getString("password");
            z = z2;
            str = string2;
        } else {
            z = false;
            string = null;
        }
        if (str != null) {
            com.baidu.location.a.a.a().a(f.getServiceContext(), str);
        }
        if (!TextUtils.isEmpty(com.baidu.location.h.s.aZ) && com.baidu.location.h.s.ba != -1) {
            LBSAuthManager.getInstance(f.getServiceContext()).setProxy(com.baidu.location.h.s.aZ, com.baidu.location.h.s.ba);
        }
        if (!TextUtils.isEmpty(com.baidu.location.h.s.bb) && !TextUtils.isEmpty(com.baidu.location.h.s.bc)) {
            LBSAuthManager.getInstance(f.getServiceContext()).setHttpProxyUsernameAndPassword(com.baidu.location.h.s.bb, com.baidu.location.h.s.bc);
        }
        com.baidu.location.a.a.a().a(f.getServiceContext());
        com.baidu.location.h.b.a().a(string);
        if (!z) {
            Thread.setDefaultUncaughtExceptionHandler(g.a());
        }
        return this.b.getBinder();
    }

    @Override // com.baidu.location.LLSInterface
    public void onCreate(Context context) throws IOException {
        ar.a().a(context);
        LBSAuthManager.getInstance(f.getServiceContext()).setPrivacyMode(true);
        try {
            com.baidu.location.h.s.aw = context.getPackageName();
        } catch (Exception unused) {
        }
        g = System.currentTimeMillis();
        HandlerThread handlerThreadA = ak.a();
        this.e = handlerThreadA;
        if (handlerThreadA != null) {
            this.d = handlerThreadA.getLooper();
        }
        a = this.d == null ? new HandlerC0021a(Looper.getMainLooper(), this) : new HandlerC0021a(this.d, this);
        c = System.currentTimeMillis();
        this.b = new Messenger(a);
        a.sendEmptyMessage(0);
        this.h = 1;
    }

    @Override // android.app.Service, com.baidu.location.LLSInterface
    public void onDestroy() {
        try {
            a.sendEmptyMessage(1);
        } catch (Exception unused) {
            Log.d("baidu_location_service", "baidu location service stop exception...");
            this.i = false;
            d();
            Process.killProcess(Process.myPid());
        }
        this.h = 3;
        new Handler(Looper.getMainLooper()).postDelayed(new b(this, new WeakReference(this)), 1000L);
    }

    @Override // android.app.Service, com.baidu.location.LLSInterface
    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    @Override // android.app.Service, com.baidu.location.LLSInterface
    public void onTaskRemoved(Intent intent) {
        Log.d("baidu_location_service", "baidu location service remove task...");
    }

    @Override // com.baidu.location.LLSInterface
    public boolean onUnBind(Intent intent) {
        return false;
    }
}
