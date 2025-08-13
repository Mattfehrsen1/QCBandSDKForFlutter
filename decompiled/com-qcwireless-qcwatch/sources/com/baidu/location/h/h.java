package com.baidu.location.h;

import android.os.Looper;
import com.baidu.location.b.ae;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* loaded from: classes.dex */
public abstract class h {
    public String ee = null;
    public int ef = 1;
    public String eg = null;
    public byte[] eh = null;
    public Map<String, Object> ei = null;
    public String ej = null;
    public byte[] ek = null;
    public byte[] el = null;
    public String em = null;
    public boolean en = false;
    public String eo = null;
    public long ep = 0;
    public static int ed = com.baidu.location.h.a.f;
    private static String a = "10.0.0.172";
    private static int b = 80;
    protected static int eq = 0;

    /* JADX INFO: Access modifiers changed from: private */
    static class a implements HostnameVerifier {
        private HttpsURLConnection a;

        public a(HttpsURLConnection httpsURLConnection) {
            this.a = httpsURLConnection;
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            String requestProperty = this.a.getRequestProperty("Host");
            if (requestProperty == null) {
                requestProperty = this.a.getURL().getHost();
            }
            return HttpsURLConnection.getDefaultHostnameVerifier().verify(requestProperty, sSLSession);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        ae aeVarA;
        String str2;
        b();
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.eo != null) {
            aeVarA = ae.a();
            str2 = this.eo;
        } else {
            aeVarA = ae.a();
            str2 = null;
        }
        aeVarA.a(str2);
        ae.a().a(this.ei, str, new j(this, jCurrentTimeMillis));
    }

    public void a(ExecutorService executorService) {
        try {
            executorService.execute(new k(this));
        } catch (Throwable unused) {
            a(false);
        }
    }

    public void a(ExecutorService executorService, String str) {
        try {
            executorService.execute(new q(this, str));
        } catch (Throwable unused) {
            a(false);
        }
    }

    public void a(ExecutorService executorService, boolean z, String str) {
        try {
            executorService.execute(new l(this, str, z));
        } catch (Throwable unused) {
            a(false);
        }
    }

    public abstract void a(boolean z);

    public void a(boolean z, String str) {
        try {
            new m(this, str, z).start();
        } catch (Throwable unused) {
            a(false);
        }
    }

    public abstract void b();

    public void c(boolean z) {
        try {
            new i(this, z).start();
        } catch (Throwable unused) {
            a(false);
        }
    }

    public void e(String str) {
        if (com.baidu.location.b.e.a().ds == 1) {
            (Looper.getMainLooper().getThread() == Thread.currentThread() ? new Thread(new n(this, str)) : new Thread(new o(this, str))).start();
            return;
        }
        try {
            new p(this, str).start();
        } catch (Throwable unused) {
            a(false);
        }
    }
}
