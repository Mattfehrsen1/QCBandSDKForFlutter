package com.baidu.location.c;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.b.al;
import com.baidu.location.b.t;
import com.baidu.location.h.s;
import com.bumptech.glide.load.Key;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes.dex */
public class a {
    private static a c;
    private static Object b = new Object();
    private static final String d = s.g() + "/gal.db";
    private static Lock f = new ReentrantLock();
    private SQLiteDatabase e = null;
    private boolean g = false;
    C0015a a = null;
    private Map<String, Integer> h = new HashMap();
    private String i = null;
    private int j = -1;
    private String k = null;
    private double l = Double.MAX_VALUE;
    private double m = Double.MAX_VALUE;

    /* renamed from: com.baidu.location.c.a$a, reason: collision with other inner class name */
    class C0015a extends com.baidu.location.h.h {
        int a;
        int b;
        int c;
        int d;
        double e;

        C0015a() {
            this.ei = new HashMap();
        }

        public void a(double d, double d2, double d3) {
            if (a.this.g) {
                return;
            }
            double[] dArrCoorEncrypt = Jni.coorEncrypt(d, d2, "gcj2wgs");
            this.a = (int) Math.floor(dArrCoorEncrypt[0] * 100.0d);
            this.b = (int) Math.floor(dArrCoorEncrypt[1] * 100.0d);
            this.c = (int) Math.floor(d * 100.0d);
            this.d = (int) Math.floor(d2 * 100.0d);
            this.e = d3;
            a.this.g = true;
            if (s.b()) {
                return;
            }
            ExecutorService executorServiceC = al.a().c();
            if (executorServiceC != null) {
                a(executorServiceC, com.baidu.location.h.e.r);
            } else {
                e(com.baidu.location.h.e.r);
            }
        }

        /* JADX WARN: Can't wrap try/catch for region: R(9:18|19|(1:21)(2:23|(1:25)(7:26|(1:28)(1:29)|30|78|31|(2:33|86)(1:87)|34))|22|30|78|31|(0)(0)|34) */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0131 A[Catch: Exception -> 0x013d, TRY_LEAVE, TryCatch #1 {Exception -> 0x013d, blocks: (B:31:0x0112, B:33:0x0131), top: B:78:0x0112 }] */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0163  */
        /* JADX WARN: Removed duplicated region for block: B:87:0x013d A[SYNTHETIC] */
        @Override // com.baidu.location.h.h
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void a(boolean r30) throws org.json.JSONException {
            /*
                Method dump skipped, instructions count: 599
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.c.a.C0015a.a(boolean):void");
        }

        @Override // com.baidu.location.h.h
        public void b() {
            Map<String, Object> map;
            String str;
            this.ee = com.baidu.location.h.e.r;
            String str2 = String.format(Locale.CHINESE, "&is_vdr=1&x=%d&y=%d%s", Integer.valueOf(this.a), Integer.valueOf(this.b), com.baidu.location.h.b.a().d()) + "&cnloc=" + t.a().b();
            String strEncode = Jni.encode(str2);
            if (strEncode.contains("err!")) {
                try {
                    strEncode = new String(Base64.encode(str2.getBytes(), 0), Key.STRING_CHARSET_NAME);
                } catch (Exception unused) {
                    strEncode = "err2!";
                }
                map = this.ei;
                str = "gpszb";
            } else {
                map = this.ei;
                str = "gpsz";
            }
            map.put(str, strEncode);
        }
    }

    public static a a() {
        a aVar;
        synchronized (b) {
            if (c == null) {
                c = new a();
            }
            aVar = c;
        }
        return aVar;
    }

    private void a(double d2, double d3, double d4) {
        if (this.a == null) {
            this.a = new C0015a();
        }
        this.a.a(d2, d3, d4);
    }

    public int a(BDLocation bDLocation) {
        double altitude;
        float radius;
        if (bDLocation != null) {
            radius = bDLocation.getRadius();
            altitude = bDLocation.getAltitude();
        } else {
            altitude = 0.0d;
            radius = 0.0f;
        }
        if (this.e == null || radius <= 0.0f || altitude <= 0.0d || bDLocation == null) {
            return 0;
        }
        double d2 = a(bDLocation.getLongitude(), bDLocation.getLatitude())[0];
        if (d2 == Double.MAX_VALUE) {
            return 0;
        }
        double gpsSwiftRadius = Jni.getGpsSwiftRadius(radius, altitude, d2);
        if (gpsSwiftRadius > 50.0d) {
            return 3;
        }
        return gpsSwiftRadius > 20.0d ? 2 : 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0117 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public double[] a(double r20, double r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 318
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.c.a.a(double, double):double[]");
    }

    public void b() {
        try {
            File file = new File(d);
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.exists()) {
                SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(file, (SQLiteDatabase.CursorFactory) null);
                this.e = sQLiteDatabaseOpenOrCreateDatabase;
                Cursor cursorRawQuery = sQLiteDatabaseOpenOrCreateDatabase.rawQuery("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='galdata'", null);
                if (cursorRawQuery.moveToFirst()) {
                    if (cursorRawQuery.getInt(0) == 0) {
                        this.e.execSQL("CREATE TABLE IF NOT EXISTS galdata_new(id CHAR(40) PRIMARY KEY,aldata DOUBLE, sigma DOUBLE,tt INT);");
                    } else {
                        this.e.execSQL("DROP TABLE galdata");
                        this.e.execSQL("CREATE TABLE galdata_new(id CHAR(40) PRIMARY KEY,aldata DOUBLE, sigma DOUBLE,tt INT);");
                    }
                    this.e.execSQL("CREATE TABLE IF NOT EXISTS locStateData(id CHAR(40) PRIMARY KEY,state INT);");
                }
                this.e.setVersion(1);
                cursorRawQuery.close();
            }
        } catch (Exception unused) {
            this.e = null;
        }
    }

    public void c() {
        SQLiteDatabase sQLiteDatabase = this.e;
        if (sQLiteDatabase != null) {
            try {
                sQLiteDatabase.close();
            } catch (Exception unused) {
            } catch (Throwable th) {
                this.e = null;
                throw th;
            }
            this.e = null;
        }
    }
}
