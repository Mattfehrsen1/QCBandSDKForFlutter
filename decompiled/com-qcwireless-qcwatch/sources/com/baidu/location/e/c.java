package com.baidu.location.e;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.text.Typography;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class c {
    private final i a;
    private int b;
    private double c;
    private double d;
    private Long e;
    private final SQLiteDatabase h;
    private final SQLiteDatabase i;
    private boolean p = false;
    private final C0018c f = new C0018c(this, true);
    private final C0018c g = new C0018c(this, false);
    private StringBuffer o = new StringBuffer();
    private StringBuffer j = null;
    private StringBuffer k = null;
    private HashSet<Long> l = new HashSet<>();
    private ConcurrentMap<Long, Integer> m = new ConcurrentHashMap();
    private ConcurrentMap<Long, String> n = new ConcurrentHashMap();

    private static final class a {
        double a;
        double b;
        double c;

        private a(double d, double d2, double d3) {
            this.a = d;
            this.b = d2;
            this.c = d3;
        }

        /* synthetic */ a(double d, double d2, double d3, d dVar) {
            this(d, d2, d3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class b extends Thread {
        private String a;
        private Long c;
        private BDLocation d;
        private BDLocation e;
        private BDLocation f;
        private String g;
        private LinkedHashMap<String, Integer> h;

        private b(String str, Long l, BDLocation bDLocation, BDLocation bDLocation2, BDLocation bDLocation3, String str2, LinkedHashMap<String, Integer> linkedHashMap) {
            this.a = str;
            this.c = l;
            this.d = bDLocation;
            this.e = bDLocation2;
            this.f = bDLocation3;
            this.g = str2;
            this.h = linkedHashMap;
        }

        /* synthetic */ b(c cVar, String str, Long l, BDLocation bDLocation, BDLocation bDLocation2, BDLocation bDLocation3, String str2, LinkedHashMap linkedHashMap, d dVar) {
            this(str, l, bDLocation, bDLocation2, bDLocation3, str2, linkedHashMap);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                c.this.a(this.a, this.c, this.d);
                c.this.j = null;
                c.this.k = null;
                c.this.a(this.h);
                c.this.a(this.f, this.d, this.e, this.a, this.c);
                if (this.g != null) {
                    c.this.a.j().a(this.g);
                }
            } catch (Exception unused) {
            }
            this.h = null;
            this.a = null;
            this.g = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.baidu.location.e.c$c, reason: collision with other inner class name */
    final class C0018c extends com.baidu.location.h.h {
        private String b;
        private final String c;
        private String d;
        private c e;
        private boolean f = false;
        private int g = 0;
        private long h = -1;
        private long i = -1;
        private long j = -1;
        private long k = -1;

        C0018c(c cVar, boolean z) {
            this.e = cVar;
            this.c = z ? "load" : "update";
            this.ei = new HashMap();
            this.b = i.a;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String str, String str2, String str3) {
            this.d = str3;
            this.b = String.format("http://%s/%s", str, str2);
            a(false, "ofloc.map.baidu.com");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c() {
            this.g++;
            this.h = System.currentTimeMillis();
        }

        private boolean d() {
            if (this.g < 2) {
                return true;
            }
            if (this.h + 43200000 >= System.currentTimeMillis()) {
                return false;
            }
            this.g = 0;
            this.h = -1L;
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x002c  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x005a  */
        /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void e() throws java.lang.Throwable {
            /*
                r9 = this;
                r0 = 0
                r9.d = r0
                boolean r0 = r9.j()
                r1 = 86400000(0x5265c00, double:4.2687272E-316)
                r3 = -1
                if (r0 == 0) goto L22
                long r5 = r9.i
                int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
                if (r0 == 0) goto L1d
                long r5 = r5 + r1
                long r7 = java.lang.System.currentTimeMillis()
                int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                if (r0 > 0) goto L28
            L1d:
                java.lang.String r0 = r9.f()
                goto L26
            L22:
                java.lang.String r0 = r9.g()
            L26:
                r9.d = r0
            L28:
                java.lang.String r0 = r9.d
                if (r0 != 0) goto L56
                long r5 = r9.j
                int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
                if (r0 == 0) goto L3b
                long r5 = r5 + r1
                long r0 = java.lang.System.currentTimeMillis()
                int r2 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
                if (r2 > 0) goto L56
            L3b:
                com.baidu.location.e.c r0 = com.baidu.location.e.c.this
                com.baidu.location.e.i r0 = com.baidu.location.e.c.a(r0)
                com.baidu.location.e.m r0 = r0.k()
                boolean r0 = r0.a()
                if (r0 == 0) goto L50
                java.lang.String r0 = r9.h()
                goto L54
            L50:
                java.lang.String r0 = r9.i()
            L54:
                r9.d = r0
            L56:
                java.lang.String r0 = r9.d
                if (r0 == 0) goto L6e
                com.baidu.location.b.al r0 = com.baidu.location.b.al.a()
                java.util.concurrent.ExecutorService r0 = r0.c()
                java.lang.String r1 = "https://ofloc.map.baidu.com/offline_loc"
                if (r0 == 0) goto L6b
                r9.a(r0, r1)
                goto L6e
            L6b:
                r9.e(r1)
            L6e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.e.c.C0018c.e():void");
        }

        private String f() throws JSONException {
            JSONObject jSONObject;
            try {
                jSONObject = new JSONObject();
                jSONObject.put("type", "0");
                jSONObject.put("cuid", com.baidu.location.h.b.a().c);
                jSONObject.put("ver", "1");
                jSONObject.put("prod", com.baidu.location.h.b.f + ":" + com.baidu.location.h.b.e);
            } catch (Exception unused) {
                jSONObject = null;
            }
            if (jSONObject != null) {
                return Jni.encodeOfflineLocationUpdateRequest(jSONObject.toString());
            }
            return null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:108:? A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:110:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:17:0x006c  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x0132  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x015b  */
        /* JADX WARN: Removed duplicated region for block: B:78:0x015e  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x0128 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:90:0x00fa A[EXC_TOP_SPLITTER, PHI: r6 r7 r8
          0x00fa: PHI (r6v2 org.json.JSONObject) = (r6v1 org.json.JSONObject), (r6v4 org.json.JSONObject) binds: [B:66:0x012d, B:37:0x00f8] A[DONT_GENERATE, DONT_INLINE]
          0x00fa: PHI (r7v4 android.database.Cursor) = (r7v3 android.database.Cursor), (r7v9 android.database.Cursor) binds: [B:66:0x012d, B:37:0x00f8] A[DONT_GENERATE, DONT_INLINE]
          0x00fa: PHI (r8v3 android.database.Cursor) = (r8v2 android.database.Cursor), (r8v13 android.database.Cursor) binds: [B:66:0x012d, B:37:0x00f8] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:93:0x0117 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:96:0x011e A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private java.lang.String g() throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 365
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.e.c.C0018c.g():java.lang.String");
        }

        private String h() throws JSONException {
            JSONObject jSONObject;
            try {
                jSONObject = new JSONObject();
                try {
                    jSONObject.put("type", "2");
                    jSONObject.put("ver", "1");
                    jSONObject.put("cuid", com.baidu.location.h.b.a().c);
                    jSONObject.put("prod", com.baidu.location.h.b.f + ":" + com.baidu.location.h.b.e);
                    this.j = System.currentTimeMillis();
                } catch (Exception unused) {
                }
            } catch (Exception unused2) {
                jSONObject = null;
            }
            if (jSONObject != null) {
                return Jni.encodeOfflineLocationUpdateRequest(jSONObject.toString());
            }
            return null;
        }

        private String i() throws Throwable {
            JSONObject jSONObject;
            JSONObject jSONObjectB;
            try {
                jSONObjectB = c.this.a.k().b();
            } catch (Exception unused) {
            }
            if (jSONObjectB != null) {
                jSONObject = new JSONObject();
                try {
                    jSONObject.put("type", "3");
                    jSONObject.put("ver", "1");
                    jSONObject.put("cuid", com.baidu.location.h.b.a().c);
                    jSONObject.put("prod", com.baidu.location.h.b.f + ":" + com.baidu.location.h.b.e);
                    jSONObject.put("rgc", jSONObjectB);
                    this.j = System.currentTimeMillis();
                } catch (Exception unused2) {
                }
            } else {
                jSONObject = null;
            }
            if (jSONObject != null) {
                return Jni.encodeOfflineLocationUpdateRequest(jSONObject.toString());
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private boolean j() throws java.lang.Throwable {
            /*
                r6 = this;
                r0 = 0
                r1 = 1
                com.baidu.location.e.c r2 = com.baidu.location.e.c.this     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L5f
                android.database.sqlite.SQLiteDatabase r2 = com.baidu.location.e.c.b(r2)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L5f
                java.lang.String r3 = "SELECT COUNT(*) FROM AP;"
                android.database.Cursor r2 = r2.rawQuery(r3, r0)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L5f
                com.baidu.location.e.c r3 = com.baidu.location.e.c.this     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L4b
                android.database.sqlite.SQLiteDatabase r3 = com.baidu.location.e.c.b(r3)     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L4b
                java.lang.String r4 = "SELECT COUNT(*) FROM CL"
                android.database.Cursor r0 = r3.rawQuery(r4, r0)     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L4b
                r3 = 0
                if (r2 == 0) goto L38
                boolean r4 = r2.moveToFirst()     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L4b
                if (r4 == 0) goto L38
                if (r0 == 0) goto L38
                boolean r4 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L4b
                if (r4 == 0) goto L38
                int r4 = r2.getInt(r3)     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L4b
                if (r4 != 0) goto L37
                int r4 = r0.getInt(r3)     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L4b
                if (r4 == 0) goto L38
            L37:
                r1 = 0
            L38:
                if (r2 == 0) goto L3f
                r2.close()     // Catch: java.lang.Exception -> L3e
                goto L3f
            L3e:
            L3f:
                if (r0 == 0) goto L6c
                r0.close()     // Catch: java.lang.Exception -> L6c
                goto L6c
            L45:
                r1 = move-exception
                r5 = r1
                r1 = r0
                r0 = r2
                r2 = r5
                goto L52
            L4b:
                r5 = r2
                r2 = r0
                r0 = r5
                goto L60
            L4f:
                r1 = move-exception
                r2 = r1
                r1 = r0
            L52:
                if (r0 == 0) goto L59
                r0.close()     // Catch: java.lang.Exception -> L58
                goto L59
            L58:
            L59:
                if (r1 == 0) goto L5e
                r1.close()     // Catch: java.lang.Exception -> L5e
            L5e:
                throw r2
            L5f:
                r2 = r0
            L60:
                if (r0 == 0) goto L67
                r0.close()     // Catch: java.lang.Exception -> L66
                goto L67
            L66:
            L67:
                if (r2 == 0) goto L6c
                r2.close()     // Catch: java.lang.Exception -> L6c
            L6c:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.e.c.C0018c.j():boolean");
        }

        void a() throws Throwable {
            if (!d() || this.f) {
                return;
            }
            c.this.g.e();
        }

        @Override // com.baidu.location.h.h
        public void a(boolean z) {
            if (z && this.eg != null) {
                new e(this).start();
            } else {
                this.f = false;
                c();
            }
        }

        @Override // com.baidu.location.h.h
        public void b() {
            this.f = true;
            this.ee = this.b;
            this.ei.clear();
            this.ei.put("qt", this.c);
            this.ei.put("req", this.d);
        }
    }

    c(i iVar) throws IOException, SQLException {
        SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase;
        this.a = iVar;
        SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase2 = null;
        try {
            File file = new File(iVar.c(), "ofl_location.db");
            if (!file.exists()) {
                file.createNewFile();
            }
            sQLiteDatabaseOpenOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(file, (SQLiteDatabase.CursorFactory) null);
        } catch (Exception unused) {
            sQLiteDatabaseOpenOrCreateDatabase = null;
        }
        this.h = sQLiteDatabaseOpenOrCreateDatabase;
        if (sQLiteDatabaseOpenOrCreateDatabase != null) {
            try {
                sQLiteDatabaseOpenOrCreateDatabase.execSQL("CREATE TABLE IF NOT EXISTS AP (id LONG PRIMARY KEY,x DOUBLE,y DOUBLE,r INTEGER,cl DOUBLE,timestamp INTEGER, frequency INTEGER DEFAULT 0);");
                sQLiteDatabaseOpenOrCreateDatabase.execSQL("CREATE TABLE IF NOT EXISTS CL (id LONG PRIMARY KEY,x DOUBLE,y DOUBLE,r INTEGER,cl DOUBLE,timestamp INTEGER, frequency INTEGER DEFAULT 0);");
            } catch (Exception unused2) {
            }
        }
        try {
            File file2 = new File(this.a.c(), "ofl_statistics.db");
            if (!file2.exists()) {
                file2.createNewFile();
            }
            sQLiteDatabaseOpenOrCreateDatabase2 = SQLiteDatabase.openOrCreateDatabase(file2, (SQLiteDatabase.CursorFactory) null);
        } catch (Exception unused3) {
        }
        this.i = sQLiteDatabaseOpenOrCreateDatabase2;
        if (sQLiteDatabaseOpenOrCreateDatabase2 != null) {
            try {
                sQLiteDatabaseOpenOrCreateDatabase2.execSQL("CREATE TABLE IF NOT EXISTS AP (id LONG PRIMARY KEY, originid VARCHAR(15), frequency INTEGER DEFAULT 0);");
                sQLiteDatabaseOpenOrCreateDatabase2.execSQL("CREATE TABLE IF NOT EXISTS CL (id LONG PRIMARY KEY, originid VARCHAR(40), frequency INTEGER DEFAULT 0);");
            } catch (Exception unused4) {
            }
        }
    }

    private double a(double d, double d2, double d3, double d4) {
        double d5 = d4 - d2;
        double d6 = d3 - d;
        double radians = Math.toRadians(d);
        Math.toRadians(d2);
        double radians2 = Math.toRadians(d3);
        Math.toRadians(d4);
        double radians3 = Math.toRadians(d5);
        double radians4 = Math.toRadians(d6) / 2.0d;
        double d7 = radians3 / 2.0d;
        double dSin = (Math.sin(radians4) * Math.sin(radians4)) + (Math.cos(radians) * Math.cos(radians2) * Math.sin(d7) * Math.sin(d7));
        return Math.atan2(Math.sqrt(dSin), Math.sqrt(1.0d - dSin)) * 2.0d * 6378137.0d;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int a(java.util.ArrayList<com.baidu.location.e.c.a> r22, double r23) {
        /*
            r21 = this;
            r0 = r22
            int r1 = r22.size()
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            r1 = 0
        Lb:
            int r3 = r22.size()
            r4 = 3
            if (r3 < r4) goto L8d
            r3 = 0
            r6 = r3
            r8 = 0
        L16:
            int r9 = r22.size()
            if (r8 >= r9) goto L31
            java.lang.Object r9 = r0.get(r8)
            com.baidu.location.e.c$a r9 = (com.baidu.location.e.c.a) r9
            double r9 = r9.a
            double r3 = r3 + r9
            java.lang.Object r9 = r0.get(r8)
            com.baidu.location.e.c$a r9 = (com.baidu.location.e.c.a) r9
            double r9 = r9.b
            double r6 = r6 + r9
            int r8 = r8 + 1
            goto L16
        L31:
            int r8 = r22.size()
            double r8 = (double) r8
            double r3 = r3 / r8
            int r8 = r22.size()
            double r8 = (double) r8
            double r6 = r6 / r8
            r8 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r10 = -1
            r13 = 0
            r15 = -1
        L42:
            int r10 = r22.size()
            if (r13 >= r10) goto L79
            java.lang.Object r10 = r0.get(r13)
            com.baidu.location.e.c$a r10 = (com.baidu.location.e.c.a) r10
            double r11 = r10.b
            java.lang.Object r10 = r0.get(r13)
            com.baidu.location.e.c$a r10 = (com.baidu.location.e.c.a) r10
            r19 = r3
            double r2 = r10.a
            r10 = r21
            r16 = r11
            r11 = r6
            r4 = r13
            r13 = r19
            r5 = r15
            r15 = r16
            r17 = r2
            double r2 = r10.a(r11, r13, r15, r17)
            int r10 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r10 <= 0) goto L72
            r8 = r2
            r15 = r4
            goto L73
        L72:
            r15 = r5
        L73:
            int r13 = r4 + 1
            r3 = r19
            r2 = 0
            goto L42
        L79:
            r5 = r15
            int r2 = (r8 > r23 ? 1 : (r8 == r23 ? 0 : -1))
            if (r2 <= 0) goto L8d
            if (r5 < 0) goto L8d
            int r2 = r22.size()
            if (r5 >= r2) goto L8d
            int r1 = r1 + 1
            r0.remove(r5)
            r2 = 1
            goto L8e
        L8d:
            r2 = 0
        L8e:
            r3 = 1
            if (r2 == r3) goto L92
            return r1
        L92:
            r2 = 0
            goto Lb
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.e.c.a(java.util.ArrayList, double):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00d2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.baidu.location.BDLocation a(java.lang.Long r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 241
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.e.c.a(java.lang.Long):com.baidu.location.BDLocation");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:118:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0370  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0333 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:175:0x034e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:183:0x013a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x033e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0149 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.baidu.location.BDLocation a(java.util.LinkedHashMap<java.lang.String, java.lang.Integer> r41, com.baidu.location.BDLocation r42, int r43) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 883
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.e.c.a(java.util.LinkedHashMap, com.baidu.location.BDLocation, int):com.baidu.location.BDLocation");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BDLocation bDLocation, BDLocation bDLocation2, BDLocation bDLocation3, String str, Long l) throws SQLException {
        if (bDLocation == null || bDLocation.getLocType() != 161) {
            return;
        }
        if (bDLocation2 != null && bDLocation.getNetworkLocationType() != null && bDLocation.getNetworkLocationType().equals("cl") && a(bDLocation2.getLatitude(), bDLocation2.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude()) > 300.0d) {
            String str2 = String.format(Locale.US, "UPDATE CL SET cl = 0 WHERE id = %d;", l);
            String str3 = String.format(Locale.US, "INSERT OR REPLACE INTO CL VALUES (%d,\"%s\",%d);", l, str, 100000);
            try {
                this.h.execSQL(str2);
                this.i.execSQL(str3);
            } catch (Exception unused) {
            }
        }
        if (bDLocation3 == null || bDLocation.getNetworkLocationType() == null || !bDLocation.getNetworkLocationType().equals("wf") || a(bDLocation3.getLatitude(), bDLocation3.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude()) <= 100.0d) {
            return;
        }
        try {
            String str4 = String.format("UPDATE AP SET cl = 0 WHERE id In (%s);", this.j.toString());
            String str5 = String.format("INSERT OR REPLACE INTO AP VALUES %s;", this.k.toString());
            this.h.execSQL(str4);
            this.i.execSQL(str5);
        } catch (Exception unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, Long l, BDLocation bDLocation) throws SQLException {
        if (str != null) {
            try {
                if (bDLocation != null) {
                    this.h.execSQL(String.format(Locale.US, "UPDATE CL SET frequency=frequency+1 WHERE id = %d;", l));
                } else {
                    String str2 = String.format(Locale.US, "INSERT OR IGNORE INTO CL VALUES (%d,\"%s\",0);", l, str);
                    String str3 = String.format(Locale.US, "UPDATE CL SET frequency=frequency+1 WHERE id = %d;", l);
                    this.i.execSQL(str2);
                    this.i.execSQL(str3);
                }
            } catch (Exception unused) {
            }
            if (this.p) {
                try {
                    this.i.execSQL(String.format(Locale.US, "INSERT OR IGNORE INTO CL VALUES (%d,\"%s\",%d);", l, str, 100000));
                } catch (Exception unused2) {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, String str3) {
        this.f.a(str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(LinkedHashMap<String, Integer> linkedHashMap) throws SQLException {
        if (linkedHashMap == null || linkedHashMap.size() <= 0) {
            return;
        }
        this.j = new StringBuffer();
        this.k = new StringBuffer();
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        ConcurrentMap<Long, Integer> concurrentMap = this.m;
        if (concurrentMap != null && concurrentMap.keySet() != null) {
            boolean z = true;
            boolean z2 = true;
            for (Long l : this.m.keySet()) {
                try {
                    if (this.l.contains(l)) {
                        if (z) {
                            z = false;
                        } else {
                            this.j.append(',');
                            this.k.append(',');
                        }
                        this.j.append(l);
                        String str = this.n.get(l);
                        StringBuffer stringBuffer3 = this.k;
                        stringBuffer3.append('(');
                        stringBuffer3.append(l);
                        stringBuffer3.append(',');
                        stringBuffer3.append(Typography.quote);
                        stringBuffer3.append(str);
                        stringBuffer3.append(Typography.quote);
                        stringBuffer3.append(',');
                        stringBuffer3.append(100000);
                        stringBuffer3.append(')');
                    } else {
                        String str2 = this.n.get(l);
                        if (z2) {
                            z2 = false;
                        } else {
                            stringBuffer.append(',');
                            stringBuffer2.append(',');
                        }
                        stringBuffer.append(l);
                        stringBuffer2.append('(');
                        stringBuffer2.append(l);
                        stringBuffer2.append(',');
                        stringBuffer2.append(Typography.quote);
                        stringBuffer2.append(str2);
                        stringBuffer2.append(Typography.quote);
                        stringBuffer2.append(",0)");
                    }
                } catch (Exception unused) {
                }
            }
        }
        try {
            this.h.execSQL(String.format(Locale.US, "UPDATE AP SET frequency=frequency+1 WHERE id IN(%s)", this.j.toString()));
        } catch (Exception unused2) {
        }
        StringBuffer stringBuffer4 = this.o;
        if (stringBuffer4 != null && stringBuffer4.length() > 0) {
            if (stringBuffer2.length() > 0) {
                stringBuffer2.append(",");
            }
            stringBuffer2.append(this.o);
        }
        try {
            String str3 = String.format("INSERT OR IGNORE INTO AP VALUES %s;", stringBuffer2.toString());
            String str4 = String.format("UPDATE AP SET frequency=frequency+1 WHERE id in (%s);", stringBuffer.toString());
            if (stringBuffer2.length() > 0) {
                this.i.execSQL(str3);
            }
            if (stringBuffer.length() > 0) {
                this.i.execSQL(str4);
            }
        } catch (Exception unused3) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String[] strArr) throws SQLException {
        this.a.l().a(strArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x010d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    android.database.Cursor a(com.baidu.location.e.k.a r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 513
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.e.c.a(com.baidu.location.e.k$a):android.database.Cursor");
    }

    SQLiteDatabase a() {
        return this.i;
    }

    void b() throws Throwable {
        this.g.a();
    }
}
