package com.baidu.location.e;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.net.wifi.ScanResult;
import android.os.AsyncTask;
import android.os.Handler;
import androidx.work.WorkRequest;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.b.aa;
import com.baidu.location.h.s;
import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public final class a {
    private static a b;
    private static final String l = com.baidu.location.h.r.a;
    private static final String m = com.baidu.location.h.r.a + "/ls.db";
    private String c = null;
    private boolean d = false;
    private boolean e = false;
    private double f = 0.0d;
    private double g = 0.0d;
    private double h = 0.0d;
    private double i = 0.0d;
    private double j = 0.0d;
    private volatile boolean k = false;
    private Handler n = null;
    public boolean a = false;

    /* renamed from: com.baidu.location.e.a$a, reason: collision with other inner class name */
    private class AsyncTaskC0017a extends AsyncTask<Boolean, Void, Boolean> {
        private AsyncTaskC0017a() {
        }

        /* synthetic */ AsyncTaskC0017a(a aVar, com.baidu.location.e.b bVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Boolean... boolArr) throws SQLException {
            if (boolArr.length != 4) {
                return false;
            }
            SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase = null;
            try {
                sQLiteDatabaseOpenOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(a.m, (SQLiteDatabase.CursorFactory) null);
            } catch (Exception unused) {
            }
            if (sQLiteDatabaseOpenOrCreateDatabase == null) {
                return false;
            }
            int iCurrentTimeMillis = (int) (System.currentTimeMillis() >> 28);
            try {
                sQLiteDatabaseOpenOrCreateDatabase.beginTransaction();
                if (boolArr[0].booleanValue()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("delete from wof where ac < ");
                    sb.append(iCurrentTimeMillis - 35);
                    try {
                        sQLiteDatabaseOpenOrCreateDatabase.execSQL(sb.toString());
                    } catch (Exception unused2) {
                    }
                }
                if (boolArr[1].booleanValue()) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("delete from bdcltb09 where ac is NULL or ac < ");
                    sb2.append(iCurrentTimeMillis - 130);
                    try {
                        sQLiteDatabaseOpenOrCreateDatabase.execSQL(sb2.toString());
                    } catch (Exception unused3) {
                    }
                }
                sQLiteDatabaseOpenOrCreateDatabase.setTransactionSuccessful();
                sQLiteDatabaseOpenOrCreateDatabase.endTransaction();
                sQLiteDatabaseOpenOrCreateDatabase.close();
            } catch (Exception unused4) {
            }
            return true;
        }
    }

    private class b extends AsyncTask<Object, Void, Boolean> {
        private b() {
        }

        /* synthetic */ b(a aVar, com.baidu.location.e.b bVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Object... objArr) {
            if (objArr.length == 4) {
                SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase = null;
                try {
                    sQLiteDatabaseOpenOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(a.m, (SQLiteDatabase.CursorFactory) null);
                } catch (Exception unused) {
                }
                if (sQLiteDatabaseOpenOrCreateDatabase != null) {
                    try {
                        sQLiteDatabaseOpenOrCreateDatabase.beginTransaction();
                        a.this.a((String) objArr[0], (com.baidu.location.f.a) objArr[1], sQLiteDatabaseOpenOrCreateDatabase);
                        a.this.a((com.baidu.location.f.p) objArr[2], (BDLocation) objArr[3], sQLiteDatabaseOpenOrCreateDatabase);
                        sQLiteDatabaseOpenOrCreateDatabase.setTransactionSuccessful();
                        sQLiteDatabaseOpenOrCreateDatabase.endTransaction();
                        sQLiteDatabaseOpenOrCreateDatabase.close();
                    } catch (Exception unused2) {
                    }
                    a.this.k = false;
                    return true;
                }
            }
            a.this.k = false;
            return false;
        }
    }

    private a() {
        b();
    }

    public static synchronized a a() {
        if (b == null) {
            b = new a();
        }
        return b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.baidu.location.f.p pVar, BDLocation bDLocation, SQLiteDatabase sQLiteDatabase) {
        Iterator<ScanResult> it;
        int i;
        SQLiteDatabase sQLiteDatabase2;
        double d;
        double d2;
        int i2;
        int i3;
        boolean z;
        String str;
        String str2;
        SQLiteDatabase sQLiteDatabase3 = sQLiteDatabase;
        if (bDLocation == null || bDLocation.getLocType() != 161) {
            return;
        }
        if (("wf".equals(bDLocation.getNetworkLocationType()) || bDLocation.getRadius() < 300.0f) && pVar.a != null) {
            int iCurrentTimeMillis = (int) (System.currentTimeMillis() >> 28);
            System.currentTimeMillis();
            Iterator<ScanResult> it2 = pVar.a.iterator();
            char c = 0;
            int i4 = 0;
            while (it2.hasNext()) {
                ScanResult next = it2.next();
                if (next.level != 0) {
                    int i5 = i4 + 1;
                    if (i5 > 6) {
                        return;
                    }
                    ContentValues contentValues = new ContentValues();
                    String strEncode2 = Jni.encode2(next.BSSID.replace(":", ""));
                    try {
                        String[] strArr = new String[1];
                        strArr[c] = strEncode2;
                        Cursor cursorRawQuery = sQLiteDatabase3.rawQuery("select * from wof where id = ?", strArr);
                        d = 0.0d;
                        if (cursorRawQuery == null || !cursorRawQuery.moveToFirst()) {
                            d2 = 0.0d;
                            i2 = 0;
                            i3 = 0;
                            z = false;
                        } else {
                            double d3 = cursorRawQuery.getDouble(1) - 113.2349d;
                            double d4 = cursorRawQuery.getDouble(2) - 432.1238d;
                            i2 = cursorRawQuery.getInt(4);
                            i3 = cursorRawQuery.getInt(5);
                            z = true;
                            d = d4;
                            d2 = d3;
                        }
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        it = it2;
                    } catch (Exception unused) {
                        it = it2;
                    }
                    if (z) {
                        if (i3 != 0) {
                            i = i5;
                            try {
                                float[] fArr = new float[1];
                                Location.distanceBetween(d, d2, bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
                                if (fArr[0] > 1500.0f) {
                                    int i6 = i3 + 1;
                                    if (i6 <= 10 || i6 <= i2 * 3) {
                                        contentValues.put("cc", Integer.valueOf(i6));
                                    } else {
                                        contentValues.put("mktime", Double.valueOf(bDLocation.getLongitude() + 113.2349d));
                                        contentValues.put("time", Double.valueOf(bDLocation.getLatitude() + 432.1238d));
                                        contentValues.put("bc", (Integer) 1);
                                        contentValues.put("cc", (Integer) 1);
                                        contentValues.put("ac", Integer.valueOf(iCurrentTimeMillis));
                                    }
                                    str = strEncode2;
                                    str2 = "wof";
                                } else {
                                    str = strEncode2;
                                    double d5 = i2;
                                    int i7 = i2 + 1;
                                    str2 = "wof";
                                    double d6 = i7;
                                    double longitude = ((d2 * d5) + bDLocation.getLongitude()) / d6;
                                    double latitude = ((d * d5) + bDLocation.getLatitude()) / d6;
                                    contentValues.put("mktime", Double.valueOf(longitude + 113.2349d));
                                    contentValues.put("time", Double.valueOf(latitude + 432.1238d));
                                    contentValues.put("bc", Integer.valueOf(i7));
                                    contentValues.put("ac", Integer.valueOf(iCurrentTimeMillis));
                                }
                                sQLiteDatabase2 = sQLiteDatabase;
                                try {
                                    sQLiteDatabase2.update(str2, contentValues, "id = \"" + str + "\"", null);
                                } catch (Exception unused2) {
                                }
                            } catch (Exception unused3) {
                                sQLiteDatabase2 = sQLiteDatabase;
                            }
                        }
                        sQLiteDatabase3 = sQLiteDatabase2;
                        it2 = it;
                        i4 = i;
                        c = 0;
                    } else {
                        try {
                            contentValues.put("mktime", Double.valueOf(bDLocation.getLongitude() + 113.2349d));
                            contentValues.put("time", Double.valueOf(bDLocation.getLatitude() + 432.1238d));
                            contentValues.put("bc", (Integer) 1);
                            contentValues.put("cc", (Integer) 1);
                            contentValues.put("ac", Integer.valueOf(iCurrentTimeMillis));
                            contentValues.put(BreakpointSQLiteKey.ID, strEncode2);
                            sQLiteDatabase3.insert("wof", null, contentValues);
                        } catch (Exception unused4) {
                        }
                    }
                    sQLiteDatabase2 = sQLiteDatabase3;
                    i = i5;
                    sQLiteDatabase3 = sQLiteDatabase2;
                    it2 = it;
                    i4 = i;
                    c = 0;
                }
            }
        }
    }

    private void a(String str, SQLiteDatabase sQLiteDatabase) {
        if (str == null || str.equals(this.c)) {
            return;
        }
        this.d = false;
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = sQLiteDatabase.rawQuery("select * from bdcltb09 where id = ?", new String[]{str});
            this.c = str;
            if (cursorRawQuery.moveToFirst()) {
                this.g = cursorRawQuery.getDouble(1) - 1235.4323d;
                this.f = cursorRawQuery.getDouble(2) - 4326.0d;
                this.h = cursorRawQuery.getDouble(3) - 2367.3217d;
                this.d = true;
            }
            if (cursorRawQuery == null) {
                return;
            }
        } catch (Exception unused) {
            if (cursorRawQuery == null) {
                return;
            }
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                try {
                    cursorRawQuery.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
        try {
            cursorRawQuery.close();
        } catch (Exception unused3) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00d9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00da  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.lang.String r20, com.baidu.location.f.a r21, android.database.sqlite.SQLiteDatabase r22) throws org.json.JSONException, java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 312
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.e.a.a(java.lang.String, com.baidu.location.f.a, android.database.sqlite.SQLiteDatabase):void");
    }

    private void a(String str, List<ScanResult> list) {
        this.d = false;
        this.e = false;
        SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase = null;
        try {
            sQLiteDatabaseOpenOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(m, (SQLiteDatabase.CursorFactory) null);
        } catch (Throwable unused) {
        }
        if (str != null && sQLiteDatabaseOpenOrCreateDatabase != null) {
            a(str, sQLiteDatabaseOpenOrCreateDatabase);
        }
        if (list != null && sQLiteDatabaseOpenOrCreateDatabase != null) {
            a(list, sQLiteDatabaseOpenOrCreateDatabase);
        }
        if (sQLiteDatabaseOpenOrCreateDatabase == null || !sQLiteDatabaseOpenOrCreateDatabase.isOpen()) {
            return;
        }
        sQLiteDatabaseOpenOrCreateDatabase.close();
    }

    private void a(List<ScanResult> list, SQLiteDatabase sQLiteDatabase) {
        double[] dArr;
        int i;
        System.currentTimeMillis();
        this.e = false;
        if (list == null || list.size() == 0 || sQLiteDatabase == null || list == null) {
            return;
        }
        int i2 = 8;
        double[] dArr2 = new double[8];
        StringBuffer stringBuffer = new StringBuffer();
        int i3 = 0;
        for (ScanResult scanResult : list) {
            if (i3 > 10) {
                break;
            }
            if (i3 > 0) {
                stringBuffer.append(",");
            }
            i3++;
            String strEncode2 = Jni.encode2(scanResult.BSSID.replace(":", ""));
            stringBuffer.append("\"");
            stringBuffer.append(strEncode2);
            stringBuffer.append("\"");
        }
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = sQLiteDatabase.rawQuery("select * from wof where id in (" + stringBuffer.toString() + ");", null);
            if (cursorRawQuery.moveToFirst()) {
                double d = 0.0d;
                double d2 = 0.0d;
                boolean z = false;
                boolean z2 = false;
                int i4 = 0;
                int i5 = 0;
                while (!cursorRawQuery.isAfterLast()) {
                    double d3 = cursorRawQuery.getDouble(1) - 113.2349d;
                    double d4 = cursorRawQuery.getDouble(2) - 432.1238d;
                    int i6 = cursorRawQuery.getInt(4);
                    int i7 = cursorRawQuery.getInt(5);
                    if (i7 <= i2 || i7 <= i6) {
                        if (this.d) {
                            dArr = dArr2;
                            Location.distanceBetween(d4, d3, this.h, this.g, new float[1]);
                            if (r2[0] <= this.f + 2000.0d) {
                                d2 += d3;
                                d += d4;
                                i5++;
                                z = true;
                                i = 4;
                            }
                            cursorRawQuery.moveToNext();
                        } else {
                            dArr = dArr2;
                            if (z) {
                                float[] fArr = new float[1];
                                double d5 = i5;
                                Location.distanceBetween(d4, d3, d / d5, d2 / d5, fArr);
                                if (fArr[0] > 1000.0f) {
                                    cursorRawQuery.moveToNext();
                                }
                                i = 4;
                            } else if (z2) {
                                for (int i8 = 0; i8 < i4; i8 += 2) {
                                    float[] fArr2 = new float[1];
                                    int i9 = i8 + 1;
                                    Location.distanceBetween(d4, d3, dArr[i9], dArr[i8], fArr2);
                                    if (fArr2[0] < 1000.0f) {
                                        d2 += dArr[i8];
                                        d += dArr[i9];
                                        i5++;
                                        z = true;
                                    }
                                }
                                if (!z) {
                                    if (i4 >= 8) {
                                        break;
                                    }
                                    int i10 = i4 + 1;
                                    dArr[i4] = d3;
                                    i4 = i10 + 1;
                                    dArr[i10] = d4;
                                } else {
                                    d2 += d3;
                                    d += d4;
                                    i5++;
                                }
                                i = 4;
                            } else {
                                int i11 = i4 + 1;
                                dArr[i4] = d3;
                                i4 = i11 + 1;
                                dArr[i11] = d4;
                                i = 4;
                                z2 = true;
                            }
                        }
                        if (i5 > i) {
                            break;
                        }
                        cursorRawQuery.moveToNext();
                    } else {
                        cursorRawQuery.moveToNext();
                        dArr = dArr2;
                    }
                    dArr2 = dArr;
                    i2 = 8;
                }
                if (i5 > 0) {
                    this.e = true;
                    double d6 = i5;
                    this.i = d2 / d6;
                    this.j = d / d6;
                }
            }
            if (cursorRawQuery == null) {
                return;
            }
        } catch (Exception unused) {
            if (cursorRawQuery == null) {
                return;
            }
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                try {
                    cursorRawQuery.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
        try {
            cursorRawQuery.close();
        } catch (Exception unused3) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x009d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String b(boolean r15) {
        /*
            r14 = this;
            boolean r0 = r14.e
            r1 = 0
            r3 = 0
            r4 = 1
            if (r0 == 0) goto L14
            double r1 = r14.i
            double r5 = r14.j
            r7 = 4642873445846928589(0x406ecccccccccccd, double:246.4)
        L11:
            r0 = 1
            r9 = 1
            goto L23
        L14:
            boolean r0 = r14.d
            if (r0 == 0) goto L1f
            double r1 = r14.g
            double r5 = r14.h
            double r7 = r14.f
            goto L11
        L1f:
            r5 = r1
            r7 = r5
            r0 = 0
            r9 = 0
        L23:
            java.lang.String r10 = "{\"result\":{\"time\":\""
            if (r0 == 0) goto L9d
            r0 = 3
            r11 = 2
            r12 = 4
            java.lang.String r13 = "\",\"error\":\"66\"},\"content\":{\"point\":{\"x\":\"%f\",\"y\":\"%f\"},\"radius\":\"%f\",\"isCellChanged\":\"%b\"}}"
            if (r15 == 0) goto L66
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r15.append(r10)
            java.lang.String r9 = com.baidu.location.h.s.a()
            r15.append(r9)
            r15.append(r13)
            java.lang.String r15 = r15.toString()
            java.util.Locale r9 = java.util.Locale.CHINA
            java.lang.Object[] r10 = new java.lang.Object[r12]
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            r10[r3] = r1
            java.lang.Double r1 = java.lang.Double.valueOf(r5)
            r10[r4] = r1
            java.lang.Double r1 = java.lang.Double.valueOf(r7)
            r10[r11] = r1
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r4)
            r10[r0] = r1
            java.lang.String r15 = java.lang.String.format(r9, r15, r10)
            goto Lc9
        L66:
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r15.append(r10)
            java.lang.String r10 = com.baidu.location.h.s.a()
            r15.append(r10)
            r15.append(r13)
            java.lang.String r15 = r15.toString()
            java.util.Locale r10 = java.util.Locale.CHINA
            java.lang.Object[] r12 = new java.lang.Object[r12]
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            r12[r3] = r1
            java.lang.Double r1 = java.lang.Double.valueOf(r5)
            r12[r4] = r1
            java.lang.Double r1 = java.lang.Double.valueOf(r7)
            r12[r11] = r1
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r9)
            r12[r0] = r1
            java.lang.String r15 = java.lang.String.format(r10, r15, r12)
            goto Lc9
        L9d:
            if (r15 == 0) goto Lb1
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r15.append(r10)
            java.lang.String r0 = com.baidu.location.h.s.a()
            r15.append(r0)
            java.lang.String r0 = "\",\"error\":\"67\"}}"
            goto Lc2
        Lb1:
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r15.append(r10)
            java.lang.String r0 = com.baidu.location.h.s.a()
            r15.append(r0)
            java.lang.String r0 = "\",\"error\":\"63\"}}"
        Lc2:
            r15.append(r0)
            java.lang.String r15 = r15.toString()
        Lc9:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.e.a.b(boolean):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase;
        com.baidu.location.e.b bVar = null;
        try {
            sQLiteDatabaseOpenOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(m, (SQLiteDatabase.CursorFactory) null);
        } catch (Exception unused) {
            sQLiteDatabaseOpenOrCreateDatabase = null;
        }
        if (sQLiteDatabaseOpenOrCreateDatabase == null) {
            return;
        }
        try {
            long jQueryNumEntries = DatabaseUtils.queryNumEntries(sQLiteDatabaseOpenOrCreateDatabase, "wof");
            long jQueryNumEntries2 = DatabaseUtils.queryNumEntries(sQLiteDatabaseOpenOrCreateDatabase, "bdcltb09");
            boolean z = jQueryNumEntries > WorkRequest.MIN_BACKOFF_MILLIS;
            boolean z2 = jQueryNumEntries2 > WorkRequest.MIN_BACKOFF_MILLIS;
            sQLiteDatabaseOpenOrCreateDatabase.close();
            if (z || z2) {
                new AsyncTaskC0017a(this, bVar).execute(Boolean.valueOf(z), Boolean.valueOf(z2));
            }
        } catch (Exception unused2) {
        }
    }

    public BDLocation a(String str, List<ScanResult> list, boolean z) {
        if (!this.a) {
            return new BDLocation("{\"result\":{\"time\":\"" + s.a() + "\",\"error\":\"67\"}}");
        }
        String str2 = "{\"result\":{\"time\":\"" + s.a() + "\",\"error\":\"67\"}}";
        try {
            a(str, list);
            String strB = b(true);
            if (strB != null) {
                str2 = strB;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return new BDLocation(str2);
    }

    public BDLocation a(boolean z) {
        if (!this.a) {
            return new BDLocation("{\"result\":{\"time\":\"" + s.a() + "\",\"error\":\"67\"}}");
        }
        com.baidu.location.f.a aVarF = com.baidu.location.f.h.a().f();
        String strD = (aVarF == null || !aVarF.e()) ? null : com.baidu.location.f.h.a().d(aVarF);
        com.baidu.location.f.p pVarU = com.baidu.location.f.h.a().u();
        BDLocation bDLocationA = pVarU != null ? a(strD, pVarU.a, true) : null;
        if (bDLocationA != null && bDLocationA.getLocType() == 66) {
            String.format(Locale.CHINA, "&ofl=%f|%f|%f", Double.valueOf(bDLocationA.getLatitude()), Double.valueOf(bDLocationA.getLongitude()), Float.valueOf(bDLocationA.getRadius()));
            if (pVarU != null && pVarU.a() > 0) {
                com.baidu.location.f.h.a().a(15, pVarU);
            }
            if (aVarF != null) {
                com.baidu.location.f.h.a().b(aVarF);
            }
            s.f(com.baidu.location.f.getServiceContext());
            com.baidu.location.h.b.a().a(false);
            com.baidu.location.b.c.a().d();
        }
        return bDLocationA;
    }

    public void a(String str, com.baidu.location.f.a aVar, com.baidu.location.f.p pVar, BDLocation bDLocation) {
        if (this.a) {
            boolean z = (aVar.b() && aa.c().i()) ? false : true;
            boolean z2 = bDLocation == null || bDLocation.getLocType() != 161 || (!"wf".equals(bDLocation.getNetworkLocationType()) && bDLocation.getRadius() >= 300.0f);
            if (pVar.a == null) {
                z2 = true;
            }
            if ((z && z2) || this.k) {
                return;
            }
            this.k = true;
            new b(this, null).execute(str, aVar, pVar, bDLocation);
        }
    }

    public void b() {
        try {
            File file = new File(l);
            File file2 = new File(m);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
            if (file2.exists()) {
                SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(file2, (SQLiteDatabase.CursorFactory) null);
                sQLiteDatabaseOpenOrCreateDatabase.execSQL("CREATE TABLE IF NOT EXISTS bdcltb09(id CHAR(40) PRIMARY KEY,time DOUBLE,tag DOUBLE, type DOUBLE , ac INT);");
                sQLiteDatabaseOpenOrCreateDatabase.execSQL("CREATE TABLE IF NOT EXISTS wof(id CHAR(15) PRIMARY KEY,mktime DOUBLE,time DOUBLE, ac INT, bc INT, cc INT);");
                sQLiteDatabaseOpenOrCreateDatabase.setVersion(1);
                sQLiteDatabaseOpenOrCreateDatabase.close();
            }
            this.a = true;
        } catch (Throwable unused) {
            this.a = false;
        }
    }

    public void c() {
        if (this.n == null) {
            this.n = new Handler();
        }
        this.n.postDelayed(new com.baidu.location.e.b(this), 3000L);
    }
}
