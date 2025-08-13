package com.baidu.location.c;

import android.net.wifi.WifiConfiguration;
import android.os.Handler;
import android.util.Base64;
import com.baidu.location.Jni;
import com.baidu.location.b.al;
import com.baidu.location.h.s;
import com.bumptech.glide.load.Key;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class h {
    private static Object a = new Object();
    private static h b;
    private Handler c = null;
    private String d = null;
    private int e = 24;
    private a f = null;
    private long g = 0;

    private class a extends com.baidu.location.h.h {
        private boolean b = false;
        private int c = 0;
        private JSONArray d = null;
        private JSONArray e = null;

        a() {
            this.ei = new HashMap();
        }

        @Override // com.baidu.location.h.h
        public void a(boolean z) throws JSONException, IOException {
            JSONObject jSONObject;
            boolean z2;
            if (z && this.eg != null) {
                try {
                    jSONObject = new JSONObject(this.eg);
                    z2 = true;
                } catch (Exception unused) {
                    jSONObject = null;
                    z2 = false;
                }
                if (z2 && jSONObject != null) {
                    try {
                        jSONObject.put("tt", System.currentTimeMillis());
                        jSONObject.put("data", this.e);
                        try {
                            File file = new File(h.this.d, "wcnf.dat");
                            if (!file.exists()) {
                                file.createNewFile();
                            }
                            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));
                            bufferedWriter.write(new String(Base64.encode(jSONObject.toString().getBytes(), 0), Key.STRING_CHARSET_NAME));
                            bufferedWriter.flush();
                            bufferedWriter.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (Exception unused2) {
                    }
                }
            }
            this.b = false;
        }

        public void a(boolean z, JSONArray jSONArray, JSONArray jSONArray2) {
            if (this.b) {
                return;
            }
            this.b = true;
            if (z) {
                this.c = 1;
            } else {
                this.c = 0;
            }
            this.d = jSONArray;
            this.e = jSONArray2;
            ExecutorService executorServiceC = al.a().c();
            if (executorServiceC != null) {
                a(executorServiceC, s.d());
            } else {
                e(s.d());
            }
        }

        @Override // com.baidu.location.h.h
        public void b() throws JSONException {
            this.ee = s.d();
            this.ei.clear();
            this.ei.put("qt", "cltrw");
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("data", this.d);
                jSONObject.put("frt", this.c);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String strEncodeOfflineLocationUpdateRequest = Jni.encodeOfflineLocationUpdateRequest(jSONObject.toString());
            this.ei.put("cltr[0]", "" + strEncodeOfflineLocationUpdateRequest);
            this.ei.put("cfg", 1);
            this.ei.put("info", Jni.encode(com.baidu.location.h.b.a().d()));
            this.ei.put("trtm", String.format(Locale.CHINA, "%d", Long.valueOf(System.currentTimeMillis())));
        }
    }

    private class b {
        public String a;
        public int b;

        b(String str, int i) {
            this.a = null;
            this.b = 0;
            this.a = str;
            this.b = i;
        }
    }

    public static h a() {
        h hVar;
        synchronized (a) {
            if (b == null) {
                b = new h();
            }
            hVar = b;
        }
        return hVar;
    }

    private List<b> a(List<WifiConfiguration> list) {
        int iIntValue;
        if (list == null || list.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (WifiConfiguration wifiConfiguration : list) {
            String str = wifiConfiguration.SSID;
            try {
                iIntValue = ((Integer) s.b(wifiConfiguration, "numAssociation")).intValue();
            } catch (Throwable unused) {
                iIntValue = 0;
            }
            if (iIntValue > 0 && str != null) {
                arrayList.add(new b(str, iIntValue));
            }
        }
        return arrayList;
    }

    private void a(boolean z, JSONArray jSONArray, JSONArray jSONArray2) {
        if (this.f == null) {
            this.f = new a();
        }
        if (s.b()) {
            return;
        }
        this.f.a(z, jSONArray, jSONArray2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:114:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:115:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00e3 A[Catch: Exception -> 0x01c8, TryCatch #1 {Exception -> 0x01c8, blocks: (B:4:0x0012, B:49:0x00e3, B:50:0x00e9, B:52:0x00fb, B:55:0x010d, B:57:0x0113, B:58:0x0121, B:60:0x0127, B:89:0x01c4, B:63:0x0149, B:65:0x014f, B:67:0x0156, B:69:0x015c, B:70:0x0162, B:72:0x0168, B:74:0x0188, B:81:0x019e, B:82:0x01a3, B:45:0x00da), top: B:96:0x0012 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00fb A[Catch: Exception -> 0x01c8, TryCatch #1 {Exception -> 0x01c8, blocks: (B:4:0x0012, B:49:0x00e3, B:50:0x00e9, B:52:0x00fb, B:55:0x010d, B:57:0x0113, B:58:0x0121, B:60:0x0127, B:89:0x01c4, B:63:0x0149, B:65:0x014f, B:67:0x0156, B:69:0x015c, B:70:0x0162, B:72:0x0168, B:74:0x0188, B:81:0x019e, B:82:0x01a3, B:45:0x00da), top: B:96:0x0012 }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01c2 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void d() throws org.json.JSONException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 461
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.c.h.d():void");
    }

    public void b() {
        if (this.c == null) {
            this.c = new i(this);
        }
        this.d = s.f();
    }

    public void c() {
        Handler handler;
        if (System.currentTimeMillis() - this.g <= 3600000 || (handler = this.c) == null) {
            return;
        }
        handler.sendEmptyMessage(1);
        this.g = System.currentTimeMillis();
    }
}
