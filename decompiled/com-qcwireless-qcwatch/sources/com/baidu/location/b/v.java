package com.baidu.location.b;

import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class v {
    public static String d;
    public a e;
    public com.baidu.location.f.p a = null;
    public com.baidu.location.f.a b = null;
    public HashSet<String> c = null;
    private boolean g = true;
    private boolean h = true;
    private boolean i = false;
    private long j = 0;
    final Handler f = new b();
    private String k = null;
    private String l = null;
    private boolean m = false;
    private long n = 0;
    private int o = 0;

    static class a extends com.baidu.location.h.h {
        private long a;
        private int b;

        public a() {
            this.ei = new HashMap();
        }

        @Override // com.baidu.location.h.h
        public void a(boolean z) {
            if (this.ei != null) {
                this.ei.clear();
            }
        }

        @Override // com.baidu.location.h.h
        public void b() {
            StringBuffer stringBuffer = new StringBuffer(256);
            stringBuffer.append("os=A");
            stringBuffer.append(Build.VERSION.SDK_INT);
            stringBuffer.append("&prod=");
            stringBuffer.append(com.baidu.location.h.b.e);
            stringBuffer.append("&resid=");
            stringBuffer.append("12");
            String str = com.baidu.location.h.b.i != null ? com.baidu.location.h.b.i : "";
            stringBuffer.append("&mapver=");
            stringBuffer.append(str);
            stringBuffer.append(com.baidu.location.h.s.f(com.baidu.location.f.getServiceContext()));
            stringBuffer.append("&cu=");
            stringBuffer.append(com.baidu.location.h.b.a().b());
            stringBuffer.append("&error=");
            stringBuffer.append(this.b);
            if (this.a > 0) {
                stringBuffer.append("&tm=");
                stringBuffer.append(this.a);
            }
            this.ei.put("info", Jni.encodeTp4(stringBuffer.toString()));
            this.ei.put("qt", "monitor");
        }
    }

    public class b extends Handler {
        public b() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (com.baidu.location.f.isServing) {
                int i = message.what;
                if (i == 21) {
                    v.this.a(message);
                } else if (i == 62 || i == 63) {
                    v.this.a();
                }
            }
        }
    }

    class c extends com.baidu.location.h.h {
        String a = null;
        String b = null;
        long c = 0;
        long d = 0;
        long e = 0;

        public c() {
            this.ei = new HashMap();
        }

        public void a(String str, long j) {
            this.b = str;
            this.d = System.currentTimeMillis();
            this.c = j;
            ExecutorService executorServiceB = al.a().b();
            if (com.baidu.location.h.s.b()) {
                a(executorServiceB, false, null);
            } else if (executorServiceB != null) {
                a(executorServiceB, com.baidu.location.h.e.e);
            } else {
                e(com.baidu.location.h.e.e);
            }
        }

        @Override // com.baidu.location.h.h
        public void a(boolean z) {
            BDLocation bDLocation;
            Message messageObtainMessage;
            if (!z || this.eg == null) {
                Message messageObtainMessage2 = v.this.f.obtainMessage(63);
                messageObtainMessage2.obj = "HttpStatus error";
                messageObtainMessage2.sendToTarget();
            } else {
                try {
                    String strB = this.eg;
                    v.d = strB;
                    if (strB.contains("enc3")) {
                        strB = com.baidu.location.h.s.e(strB);
                    } else if (strB.contains("enc") && w.a().b()) {
                        try {
                            JSONObject jSONObject = new JSONObject(strB);
                            if (jSONObject.has("enc")) {
                                strB = w.a().b(jSONObject.getString("enc"));
                            }
                        } catch (Exception unused) {
                        }
                    }
                    int iOptInt = 1;
                    if (strB.contains("net_loc_save")) {
                        try {
                            JSONObject jSONObject2 = new JSONObject(strB);
                            JSONObject jSONObject3 = jSONObject2.has(Constant.MODIFY_ACTIVITY_INTENT_CONTENT) ? jSONObject2.getJSONObject(Constant.MODIFY_ACTIVITY_INTENT_CONTENT) : null;
                            if (jSONObject3 != null && jSONObject3.has("net_loc_save")) {
                                iOptInt = jSONObject3.optInt("net_loc_save", 1);
                            }
                        } catch (Exception unused2) {
                        }
                    }
                    try {
                        bDLocation = new BDLocation(strB);
                        bDLocation.getLocType();
                        if (com.baidu.location.h.s.l() && bDLocation.getLocType() == 161) {
                            bDLocation.setLocType(BDLocation.TypeCoarseLocation);
                            bDLocation.setRadius(2000.0f);
                        }
                        if (bDLocation.getLocType() == 161) {
                            s.a().a(strB);
                        }
                        if (ah.a().d()) {
                            bDLocation.setDirection(ah.a().e());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        bDLocation = new BDLocation();
                        bDLocation.setLocType(0);
                    }
                    this.a = null;
                    if (bDLocation.getLocType() == 0 && bDLocation.getLatitude() == Double.MIN_VALUE && bDLocation.getLongitude() == Double.MIN_VALUE) {
                        messageObtainMessage = v.this.f.obtainMessage(63);
                        messageObtainMessage.obj = "HttpStatus error";
                    } else {
                        long jCurrentTimeMillis = (System.currentTimeMillis() - this.d) / 1000;
                        if (jCurrentTimeMillis < 0) {
                            jCurrentTimeMillis = 0;
                        }
                        if (this.c < 0) {
                            this.c = 0L;
                        }
                        bDLocation.setDelayTime(this.c + jCurrentTimeMillis);
                        messageObtainMessage = v.this.f.obtainMessage(21);
                        messageObtainMessage.obj = bDLocation;
                        messageObtainMessage.arg1 = iOptInt;
                    }
                    messageObtainMessage.sendToTarget();
                } catch (Exception unused3) {
                }
            }
            if (this.ei != null) {
                this.ei.clear();
            }
        }

        @Override // com.baidu.location.h.h
        public void b() {
            if ((com.baidu.location.h.s.g || com.baidu.location.h.s.i) && v.this.k != null && v.this.l != null) {
                this.b += String.format(Locale.CHINA, "&ki=%s&sn=%s", v.this.k, v.this.l);
            }
            if (w.a().b()) {
                this.b += "&enc=2";
            }
            String strT = com.baidu.location.f.h.a().t();
            if (strT != null) {
                this.eo = Jni.encodeTp4(strT);
            }
            String strEncodeTp4 = Jni.encodeTp4(this.b);
            this.b = null;
            if (this.a == null) {
                this.a = ao.b();
            }
            this.ei.put("bloc", strEncodeTp4);
            if (this.a != null) {
                this.ei.put("up", this.a);
            }
            this.ei.put("trtm", String.format(Locale.CHINA, "%d", Long.valueOf(System.currentTimeMillis())));
            this.ep = 0L;
        }
    }

    public String a(String str) throws IOException {
        com.baidu.location.f.p pVar;
        String strQ;
        if (this.k == null) {
            this.k = com.baidu.location.a.a.b(com.baidu.location.f.getServiceContext());
        }
        if (this.l == null) {
            this.l = com.baidu.location.a.a.c(com.baidu.location.f.getServiceContext());
        }
        com.baidu.location.f.a aVar = this.b;
        if (aVar == null || !aVar.a()) {
            this.b = com.baidu.location.f.h.a().f();
        }
        com.baidu.location.f.p pVar2 = this.a;
        if (pVar2 == null || !pVar2.b()) {
            this.a = com.baidu.location.f.h.a().u();
        }
        Location locationH = com.baidu.location.f.e.a().k() ? com.baidu.location.f.e.a().h() : null;
        com.baidu.location.f.a aVar2 = this.b;
        if ((aVar2 == null || aVar2.d() || this.b.c()) && (((pVar = this.a) == null || pVar.a() == 0) && locationH == null)) {
            return null;
        }
        String strB = b();
        if (s.a().d() == -2) {
            strB = strB + "&imo=1";
        }
        int iC = com.baidu.location.h.s.c(com.baidu.location.f.getServiceContext());
        if (iC >= 0) {
            strB = strB + "&lmd=" + iC;
            if (Build.VERSION.SDK_INT >= 28 && !this.m) {
                this.m = true;
                try {
                    if (com.baidu.location.f.getServiceContext().getPackageManager().hasSystemFeature("android.hardware.wifi.rtt")) {
                        strB = strB + "&rtt=1";
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
        com.baidu.location.f.p pVar3 = this.a;
        if ((pVar3 == null || pVar3.a() == 0) && (strQ = com.baidu.location.f.h.a().q()) != null) {
            strB = strQ + strB;
        }
        if (com.baidu.location.f.h.a().n()) {
            strB = strB + "&wf_freq=1";
        }
        String str2 = strB;
        if (!this.h) {
            return com.baidu.location.h.s.a(this.b, this.a, locationH, str2, 0);
        }
        this.h = false;
        return com.baidu.location.h.s.a(this.b, this.a, locationH, str2, 0, true);
    }

    public abstract void a();

    public abstract void a(Message message);

    public String b() throws IOException {
        String strD = com.baidu.location.b.c.a().d();
        String str = com.baidu.location.f.h.a().m() ? "&cn=32" : String.format(Locale.CHINA, "&cn=%d", Integer.valueOf(com.baidu.location.f.h.a().g()));
        long jCurrentTimeMillis = System.currentTimeMillis() - this.n;
        if (Build.VERSION.SDK_INT >= 18 && jCurrentTimeMillis > 60000) {
            this.n = System.currentTimeMillis();
            String strC = com.baidu.location.h.s.c();
            if (!TextUtils.isEmpty(strC)) {
                str = str + "&qcip6c=" + strC;
            }
        }
        if (this.g) {
            this.g = false;
            int i = Build.VERSION.SDK_INT;
        } else if (!this.i) {
            String strE = ao.e();
            if (strE != null) {
                str = str + strE;
            }
            this.i = true;
        }
        return str + strD;
    }
}
