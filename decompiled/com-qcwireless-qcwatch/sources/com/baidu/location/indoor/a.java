package com.baidu.location.indoor;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.Base64;
import com.baidu.location.b.al;
import com.bumptech.glide.load.Key;
import io.reactivex.annotations.SchedulerSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public class a extends com.baidu.location.h.h {
    private static HashMap<String, Long> a = new HashMap<>();
    private static Object k = new Object();
    private static a l = null;
    private Context c;
    private String e;
    private InterfaceC0025a g;
    private Handler i;
    private Runnable j;
    private final SimpleDateFormat b = new SimpleDateFormat("yyyyMM");
    private String h = null;
    private HashSet<String> f = new HashSet<>();
    private boolean d = false;

    /* renamed from: com.baidu.location.indoor.a$a, reason: collision with other inner class name */
    public interface InterfaceC0025a {
        void a(boolean z);
    }

    public a(Context context) {
        this.c = context;
        this.ei = new HashMap();
        this.i = new Handler();
        this.j = new b(this);
    }

    private String a(Date date) throws NoSuchAlgorithmException, IOException {
        File file = new File(this.c.getCacheDir(), com.baidu.location.h.s.a((this.e + this.b.format(date)).getBytes(), false));
        if (!file.isFile()) {
            return null;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String str = "";
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                str = str + line + "\n";
            }
            bufferedReader.close();
            if (!str.equals("")) {
                return new String(Base64.decode(str.getBytes(), 0));
            }
        } catch (Exception unused) {
        }
        return null;
    }

    private void c(String str) {
        for (String str2 : str.split(",")) {
            this.f.add(str2.toLowerCase());
        }
    }

    private Date d() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(2, -1);
        return calendar.getTime();
    }

    private void d(String str) throws NoSuchAlgorithmException {
        try {
            FileWriter fileWriter = new FileWriter(new File(this.c.getCacheDir(), com.baidu.location.h.s.a((this.e + this.b.format(new Date())).getBytes(), false)));
            fileWriter.write(new String(Base64.encode(str.getBytes(), 0), Key.STRING_CHARSET_NAME));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException unused) {
        }
    }

    private void e() {
        try {
            File file = new File(this.c.getCacheDir(), com.baidu.location.h.s.a((this.e + this.b.format(d())).getBytes(), false));
            if (file.isFile()) {
                file.delete();
            }
        } catch (Exception unused) {
        }
    }

    private void f(String str) {
        try {
            FileWriter fileWriter = new FileWriter(new File(this.c.getCacheDir(), "buildings"), true);
            fileWriter.write(str + "\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0042  */
    @Override // com.baidu.location.h.h
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(boolean r6) throws java.security.NoSuchAlgorithmException, java.io.IOException {
        /*
            r5 = this;
            java.lang.String r0 = "anchorinfo"
            r1 = 1
            r2 = 0
            if (r6 == 0) goto L42
            java.lang.String r6 = r5.eg
            if (r6 == 0) goto L42
            java.lang.String r6 = r5.eg     // Catch: java.lang.Exception -> L42
            java.lang.String r3 = new java.lang.String     // Catch: java.lang.Exception -> L42
            byte[] r6 = r6.getBytes()     // Catch: java.lang.Exception -> L42
            byte[] r6 = android.util.Base64.decode(r6, r2)     // Catch: java.lang.Exception -> L42
            r3.<init>(r6)     // Catch: java.lang.Exception -> L42
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch: java.lang.Exception -> L42
            r6.<init>(r3)     // Catch: java.lang.Exception -> L42
            boolean r3 = r6.has(r0)     // Catch: java.lang.Exception -> L42
            if (r3 == 0) goto L42
            java.lang.String r6 = r6.optString(r0)     // Catch: java.lang.Exception -> L42
            if (r6 == 0) goto L42
            java.lang.String r0 = ""
            boolean r0 = r6.equals(r0)     // Catch: java.lang.Exception -> L42
            if (r0 != 0) goto L42
            java.util.HashSet<java.lang.String> r0 = r5.f     // Catch: java.lang.Exception -> L42
            r0.clear()     // Catch: java.lang.Exception -> L42
            r5.c(r6)     // Catch: java.lang.Exception -> L42
            r5.d(r6)     // Catch: java.lang.Exception -> L42
            r5.e()     // Catch: java.lang.Exception -> L40
        L40:
            r6 = 1
            goto L43
        L42:
            r6 = 0
        L43:
            if (r6 != 0) goto L58
            java.lang.String r0 = r5.h
            if (r0 != 0) goto L58
            java.lang.String r0 = r5.e
            r5.h = r0
            android.os.Handler r0 = r5.i
            java.lang.Runnable r1 = r5.j
            r3 = 60000(0xea60, double:2.9644E-319)
            r0.postDelayed(r1, r3)
            goto L79
        L58:
            r0 = 0
            if (r6 == 0) goto L5e
            r5.h = r0
            goto L79
        L5e:
            java.lang.String r3 = r5.h
            r5.f(r3)
            r5.h = r0
            java.util.Date r0 = r5.d()
            java.lang.String r0 = r5.a(r0)
            if (r0 == 0) goto L79
            r5.c(r0)
            com.baidu.location.indoor.a$a r0 = r5.g
            if (r0 == 0) goto L79
            r0.a(r1)
        L79:
            r5.d = r2
            com.baidu.location.indoor.a$a r0 = r5.g
            if (r0 == 0) goto L82
            r0.a(r6)
        L82:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.indoor.a.a(boolean):void");
    }

    public boolean a() {
        HashSet<String> hashSet = this.f;
        return (hashSet == null || hashSet.isEmpty()) ? false : true;
    }

    public boolean a(String str) {
        String str2 = this.e;
        return (str2 == null || !str2.equalsIgnoreCase(str) || this.f.isEmpty()) ? false : true;
    }

    public boolean a(String str, InterfaceC0025a interfaceC0025a) {
        if (!this.d) {
            this.g = interfaceC0025a;
            this.d = true;
            this.e = str;
            try {
                String strA = a(new Date());
                if (strA == null) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (a.get(str) == null || jCurrentTimeMillis - a.get(str).longValue() > 86400000) {
                        a.put(str, Long.valueOf(jCurrentTimeMillis));
                        ExecutorService executorServiceC = al.a().c();
                        if (executorServiceC != null) {
                            a(executorServiceC, com.baidu.location.h.e.s);
                        } else {
                            e(com.baidu.location.h.e.s);
                        }
                    }
                } else {
                    c(strA);
                    InterfaceC0025a interfaceC0025a2 = this.g;
                    if (interfaceC0025a2 != null) {
                        interfaceC0025a2.a(true);
                    }
                    this.d = false;
                }
            } catch (Exception unused) {
                this.d = false;
            }
        }
        return false;
    }

    @Override // com.baidu.location.h.h
    public void b() {
        this.ee = com.baidu.location.h.e.s;
        this.ei.clear();
        this.ei.put("bid", SchedulerSupport.NONE);
        this.ei.put("bldg", this.e);
        this.ei.put("mb", Build.MODEL);
        this.ei.put("msdk", "2.0");
        this.ei.put("cuid", com.baidu.location.h.b.a().c);
        this.ei.put("anchors", "v1");
        this.ei.put("cnloc", Integer.valueOf(com.baidu.location.b.t.a().b()));
    }

    public boolean b(String str) {
        HashSet<String> hashSet;
        return (this.e == null || (hashSet = this.f) == null || hashSet.isEmpty() || !this.f.contains(str)) ? false : true;
    }

    public void c() {
        this.e = null;
        this.f.clear();
    }
}
