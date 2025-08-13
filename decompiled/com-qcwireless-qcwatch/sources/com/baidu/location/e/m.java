package com.baidu.location.e;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.baidu.location.LocationClientOption;
import com.blankj.utilcode.constant.TimeConstants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class m {
    private static final double[] b = {45.0d, 135.0d, 225.0d, 315.0d};
    private final i a;
    private final int c;
    private final SQLiteDatabase d;
    private int e = -1;
    private int f = -1;

    private static final class a {
        private double a;
        private double b;

        private a(double d, double d2) {
            this.a = d;
            this.b = d2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    static abstract class b {
        public static final b a;
        public static final b b;
        public static final b c;
        public static final b d;
        private static final /* synthetic */ b[] j;
        private final int e;
        private final String f;
        private final String g;
        private final String h;
        private final int i;

        static {
            o oVar = new o("AREA", 0, "RGCAREA", "area", "addrv", 0, 1000);
            a = oVar;
            p pVar = new p("ROAD", 1, "RGCROAD", "road", "addrv", 1000, LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL);
            b = pVar;
            q qVar = new q("SITE", 2, "RGCSITE", "site", "addrv", 100, 50000);
            c = qVar;
            r rVar = new r("POI", 3, "RGCPOI", "poi", "poiv", 1000, 5000);
            d = rVar;
            j = new b[]{oVar, pVar, qVar, rVar};
        }

        private b(String str, int i, String str2, String str3, String str4, int i2, int i3) {
            this.f = str2;
            this.g = str3;
            this.h = str4;
            this.e = i2;
            this.i = i3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String a(int i, double d2, double d3) {
            HashSet hashSet = new HashSet();
            hashSet.add(m.b(i, d2, d3));
            int i2 = this.e;
            double d4 = i2 * 1.414d;
            if (i2 > 0) {
                for (int i3 = 0; i3 < m.b.length; i3++) {
                    double[] dArrB = m.b(d3, d2, d4, m.b[i3]);
                    hashSet.add(m.b(i, dArrB[1], dArrB[0]));
                }
            }
            StringBuffer stringBuffer = new StringBuffer();
            Iterator it = hashSet.iterator();
            boolean z = true;
            while (it.hasNext()) {
                String str = (String) it.next();
                if (z) {
                    z = false;
                } else {
                    stringBuffer.append(',');
                }
                stringBuffer.append("\"");
                stringBuffer.append(str);
                stringBuffer.append("\"");
            }
            return String.format("SELECT * FROM %s WHERE gridkey IN (%s);", this.f, stringBuffer.toString());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String a(JSONObject jSONObject) {
            Iterator<String> itKeys = jSONObject.keys();
            StringBuffer stringBuffer = new StringBuffer();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (stringBuffer.length() != 0) {
                    stringBuffer.append(",");
                }
                stringBuffer.append("\"");
                stringBuffer.append(next);
                stringBuffer.append("\"");
            }
            return String.format(Locale.US, "DELETE FROM %s WHERE gridkey IN (%s)", this.f, stringBuffer);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void b(StringBuffer stringBuffer, String str, String str2, int i) {
            if (stringBuffer.length() > 0) {
                stringBuffer.append(",");
            }
            stringBuffer.append("(\"");
            stringBuffer.append(str);
            stringBuffer.append("\",\"");
            stringBuffer.append(str2);
            stringBuffer.append("\",");
            stringBuffer.append(i);
            stringBuffer.append(",");
            stringBuffer.append(System.currentTimeMillis() / 86400000);
            stringBuffer.append(")");
        }

        public static b valueOf(String str) {
            return (b) Enum.valueOf(b.class, str);
        }

        public static b[] values() {
            return (b[]) j.clone();
        }

        abstract List<String> a(JSONObject jSONObject, String str, int i);
    }

    m(i iVar, SQLiteDatabase sQLiteDatabase, int i) throws SQLException {
        this.a = iVar;
        this.d = sQLiteDatabase;
        this.c = i;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            return;
        }
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS RGCAREA(gridkey VARCHAR(10) PRIMARY KEY, country VARCHAR(100),countrycode VARCHAR(100), province VARCHAR(100), city VARCHAR(100), citycode VARCHAR(100), district VARCHAR(100), timestamp INTEGER, version VARCHAR(50))");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS RGCROAD(_id INTEGER PRIMARY KEY AUTOINCREMENT, gridkey VARCHAR(10), street VARCHAR(100), x1 DOUBLE, y1 DOUBLE, x2 DOUBLE, y2 DOUBLE)");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS RGCSITE(_id INTEGER PRIMARY KEY AUTOINCREMENT, gridkey VARCHAR(10), street VARCHAR(100), streetnumber VARCHAR(100), x DOUBLE, y DOUBLE)");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS RGCPOI(pid VARCHAR(50) PRIMARY KEY , gridkey VARCHAR(10), name VARCHAR(100), type VARCHAR(50), x DOUBLE, y DOUBLE, rank INTEGER)");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS RGCUPDATE(gridkey VARCHAR(10), version VARCHAR(50), type INTEGER, timestamp INTEGER, PRIMARY KEY(gridkey, type))");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double a(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = d5 - d3;
        double d8 = d - d3;
        double d9 = d6 - d4;
        double d10 = d2 - d4;
        double d11 = (d7 * d8) + (d9 * d10);
        if (d11 <= 0.0d) {
            return Math.sqrt((d8 * d8) + (d10 * d10));
        }
        double d12 = (d7 * d7) + (d9 * d9);
        if (d11 >= d12) {
            double d13 = d - d5;
            double d14 = d2 - d6;
            return Math.sqrt((d13 * d13) + (d14 * d14));
        }
        double d15 = d11 / d12;
        double d16 = d - (d3 + (d7 * d15));
        double d17 = (d4 + (d9 * d15)) - d2;
        return Math.sqrt((d16 * d16) + (d17 * d17));
    }

    private static int a(int i, int i2) {
        double d;
        int i3;
        if (100 > i2) {
            d = -0.1d;
            i3 = TimeConstants.MIN;
        } else if (500 > i2) {
            d = -0.75d;
            i3 = 55500;
        } else {
            d = -0.5d;
            i3 = 0;
        }
        return ((int) ((d * i2) + i3)) + i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(int i, double d, double d2) {
        double d3;
        a aVar;
        int i2 = i * 5;
        char[] cArr = new char[i + 1];
        a aVar2 = new a(90.0d, -90.0d);
        a aVar3 = new a(180.0d, -180.0d);
        int i3 = 1;
        boolean z = true;
        int i4 = 0;
        for (int i5 = 1; i5 <= i2; i5++) {
            if (z) {
                d3 = d;
                aVar = aVar3;
            } else {
                d3 = d2;
                aVar = aVar2;
            }
            double d4 = (aVar.b + aVar.a) / 2.0d;
            i4 <<= i3;
            if (((int) (d3 * 1000000.0d)) > ((int) (d4 * 1000000.0d))) {
                aVar.b = d4;
                i4 |= 1;
            } else {
                aVar.a = d4;
            }
            if (i5 % 5 == 0) {
                i3 = 1;
                cArr[(i5 / 5) - 1] = "0123456789bcdefghjkmnpqrstuvwxyz".charAt(i4);
                i4 = 0;
            } else {
                i3 = 1;
            }
            z = !z;
        }
        cArr[i] = 0;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i6 = 0; i6 < i; i6++) {
            stringBuffer.append(cArr[i6]);
        }
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double[] b(double d, double d2, double d3, double d4) {
        double radians = Math.toRadians(d);
        double radians2 = Math.toRadians(d2);
        double radians3 = Math.toRadians(d4);
        double d5 = d3 / 6378137.0d;
        double dAsin = Math.asin((Math.sin(radians) * Math.cos(d5)) + (Math.cos(radians) * Math.sin(d5) * Math.cos(radians3)));
        return new double[]{Math.toDegrees(dAsin), Math.toDegrees(radians2 + Math.atan2(Math.sin(radians3) * Math.sin(d5) * Math.cos(radians), Math.cos(d5) - (Math.sin(radians) * Math.sin(dAsin))))};
    }

    private double c(double d, double d2, double d3, double d4) {
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01e8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01cb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:188:0x00a4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x009e A[Catch: Exception -> 0x0083, TRY_ENTER, TRY_LEAVE, TryCatch #28 {Exception -> 0x0083, blocks: (B:39:0x009e, B:25:0x007f), top: B:177:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0175 A[Catch: all -> 0x01d0, Exception -> 0x01d2, TryCatch #19 {Exception -> 0x01d2, blocks: (B:82:0x016f, B:84:0x0175, B:86:0x017b), top: B:166:0x016f }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01c4  */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v10 */
    /* JADX WARN: Type inference failed for: r15v11 */
    /* JADX WARN: Type inference failed for: r15v12 */
    /* JADX WARN: Type inference failed for: r15v15 */
    /* JADX WARN: Type inference failed for: r15v2, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r15v20 */
    /* JADX WARN: Type inference failed for: r15v25 */
    /* JADX WARN: Type inference failed for: r15v26 */
    /* JADX WARN: Type inference failed for: r15v27 */
    /* JADX WARN: Type inference failed for: r1v12, types: [android.database.sqlite.SQLiteDatabase] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    com.baidu.location.Address a(double r35, double r37) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 670
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.e.m.a(double, double):com.baidu.location.Address");
    }

    void a(JSONObject jSONObject) {
        SQLiteDatabase sQLiteDatabase = this.d;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            return;
        }
        try {
            this.d.beginTransaction();
            for (b bVar : b.values()) {
                if (jSONObject.has(bVar.g)) {
                    String string = jSONObject.has(bVar.h) ? jSONObject.getString(bVar.h) : "";
                    ArrayList arrayList = new ArrayList();
                    JSONObject jSONObject2 = jSONObject.getJSONObject(bVar.g);
                    arrayList.add(bVar.a(jSONObject2));
                    arrayList.addAll(bVar.a(jSONObject2, string, bVar.i));
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        this.d.execSQL((String) it.next());
                    }
                }
            }
            this.d.setTransactionSuccessful();
            this.e = -1;
            this.f = -1;
        } catch (Exception unused) {
        } catch (Throwable th) {
            try {
                this.d.endTransaction();
            } catch (Exception unused2) {
            }
            throw th;
        }
        try {
            this.d.endTransaction();
        } catch (Exception unused3) {
        }
    }

    boolean a() throws Throwable {
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        Cursor cursor;
        Cursor cursor2;
        Cursor cursorRawQuery;
        if (this.a.l().l() && this.f == -1 && this.e == -1 && (sQLiteDatabase = this.d) != null && sQLiteDatabase.isOpen()) {
            Cursor cursorRawQuery2 = null;
            try {
                try {
                    cursorRawQuery = this.d.rawQuery("SELECT COUNT(*) FROM RGCSITE;", null);
                } catch (Exception unused) {
                }
                try {
                    cursorRawQuery.moveToFirst();
                    this.f = cursorRawQuery.getInt(0);
                    cursorRawQuery2 = this.d.rawQuery("SELECT COUNT(*) FROM RGCAREA;", null);
                    cursorRawQuery2.moveToFirst();
                    this.e = cursorRawQuery2.getInt(0);
                    if (cursorRawQuery != null) {
                        try {
                            cursorRawQuery.close();
                        } catch (Exception unused2) {
                        }
                    }
                } catch (Exception unused3) {
                    cursor2 = cursorRawQuery2;
                    cursorRawQuery2 = cursorRawQuery;
                    if (cursorRawQuery2 != null) {
                        try {
                            cursorRawQuery2.close();
                        } catch (Exception unused4) {
                        }
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (this.f != 0) {
                    }
                } catch (Throwable th2) {
                    cursor = cursorRawQuery2;
                    cursorRawQuery2 = cursorRawQuery;
                    th = th2;
                    if (cursorRawQuery2 != null) {
                        try {
                            cursorRawQuery2.close();
                        } catch (Exception unused5) {
                        }
                    }
                    if (cursor == null) {
                        throw th;
                    }
                    try {
                        cursor.close();
                        throw th;
                    } catch (Exception unused6) {
                        throw th;
                    }
                }
            } catch (Exception unused7) {
                cursor2 = null;
            } catch (Throwable th3) {
                th = th3;
                cursor = null;
            }
            if (cursorRawQuery2 != null) {
                cursorRawQuery2.close();
            }
        }
        return this.f != 0 && this.e == 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b5  */
    /* JADX WARN: Type inference failed for: r3v5, types: [com.baidu.location.Poi] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    java.util.List<com.baidu.location.Poi> b(double r26, double r28) throws java.lang.Throwable {
        /*
            r25 = this;
            r10 = r25
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.baidu.location.e.m$b r1 = com.baidu.location.e.m.b.d
            int r2 = r10.c
            r3 = r26
            r5 = r28
            java.lang.String r1 = com.baidu.location.e.m.b.a(r1, r2, r3, r5)
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r10.d     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Lac
            android.database.Cursor r11 = r3.rawQuery(r1, r2)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Lac
            boolean r1 = r11.moveToFirst()     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La2
            if (r1 == 0) goto L97
            r12 = 0
            r13 = r2
            r14 = 0
        L23:
            boolean r1 = r11.isAfterLast()     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            if (r1 != 0) goto L96
            java.lang.String r15 = r11.getString(r12)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            r1 = 2
            java.lang.String r16 = r11.getString(r1)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            r1 = 4
            double r8 = r11.getDouble(r1)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            r1 = 5
            double r6 = r11.getDouble(r1)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            r1 = 6
            int r4 = r11.getInt(r1)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            r1 = r25
            r2 = r28
            r17 = r4
            r4 = r26
            double r1 = r1.c(r2, r4, r6, r8)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            com.baidu.location.e.m$b r3 = com.baidu.location.e.m.b.d     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            int r3 = com.baidu.location.e.m.b.d(r3)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            double r3 = (double) r3     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 >= 0) goto L92
            com.baidu.location.Poi r3 = new com.baidu.location.Poi     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            java.lang.String r4 = new java.lang.String     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            byte[] r5 = r15.getBytes()     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            byte[] r5 = android.util.Base64.decode(r5, r12)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            java.lang.String r5 = new java.lang.String     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            byte[] r6 = r16.getBytes()     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            byte[] r6 = android.util.Base64.decode(r6, r12)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            r21 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            java.lang.String r23 = ""
            java.lang.String r24 = ""
            r18 = r3
            r19 = r4
            r20 = r5
            r18.<init>(r19, r20, r21, r23, r24)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            float r1 = (float) r1     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            int r1 = java.lang.Math.round(r1)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            r2 = r17
            int r1 = a(r2, r1)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            if (r1 <= r14) goto L92
            r14 = r1
            r13 = r3
        L92:
            r11.moveToNext()     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La3
            goto L23
        L96:
            r2 = r13
        L97:
            if (r11 == 0) goto Lb3
            r11.close()     // Catch: java.lang.Exception -> L9d
            goto Lb3
        L9d:
            goto Lb3
        L9f:
            r0 = move-exception
            r2 = r11
            goto La6
        La2:
            r13 = r2
        La3:
            r2 = r11
            goto Lad
        La5:
            r0 = move-exception
        La6:
            if (r2 == 0) goto Lab
            r2.close()     // Catch: java.lang.Exception -> Lab
        Lab:
            throw r0
        Lac:
            r13 = r2
        Lad:
            if (r2 == 0) goto Lb2
            r2.close()     // Catch: java.lang.Exception -> Lb2
        Lb2:
            r2 = r13
        Lb3:
            if (r2 == 0) goto Lb8
            r0.add(r2)
        Lb8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.e.m.b(double, double):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x021f A[Catch: Exception -> 0x01f4, TRY_ENTER, TRY_LEAVE, TryCatch #6 {Exception -> 0x01f4, blocks: (B:110:0x021f, B:84:0x01f0), top: B:125:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0208 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x020f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0218 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:153:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:155:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0196  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    org.json.JSONObject b() throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 560
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.e.m.b():org.json.JSONObject");
    }
}
