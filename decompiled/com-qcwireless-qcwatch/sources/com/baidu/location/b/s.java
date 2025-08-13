package com.baidu.location.b;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import com.baidu.location.Jni;
import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class s {
    private static s d;
    private static Object c = new Object();
    private static final String e = com.baidu.location.h.s.g() + "/hst.db";
    private SQLiteDatabase f = null;
    private boolean g = false;
    a a = null;
    a b = null;
    private String h = null;
    private int i = -2;

    class a extends com.baidu.location.h.h {
        private String b = null;
        private String c = null;
        private boolean d = true;
        private boolean e = false;

        a() {
            this.ei = new HashMap();
        }

        public void a(String str, String str2) {
            if (s.this.g) {
                return;
            }
            s.this.g = true;
            this.b = str;
            if (w.a().b()) {
                this.c = str2 + "&enc=2";
            } else {
                this.c = str2;
            }
            ExecutorService executorServiceC = al.a().c();
            if (executorServiceC != null) {
                a(executorServiceC, com.baidu.location.h.e.e);
            } else {
                e(com.baidu.location.h.e.e);
            }
        }

        @Override // com.baidu.location.h.h
        public void a(boolean z) throws JSONException, NumberFormatException {
            if (z && this.eg != null) {
                try {
                    String strE = this.eg;
                    if (strE.contains("enc") && w.a().b()) {
                        try {
                            JSONObject jSONObject = new JSONObject(strE);
                            if (jSONObject.has("enc")) {
                                strE = w.a().b(jSONObject.getString("enc"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (strE.contains("enc3")) {
                        strE = com.baidu.location.h.s.e(strE);
                    }
                    if (this.d) {
                        JSONObject jSONObject2 = new JSONObject(strE);
                        JSONObject jSONObject3 = jSONObject2.has(Constant.MODIFY_ACTIVITY_INTENT_CONTENT) ? jSONObject2.getJSONObject(Constant.MODIFY_ACTIVITY_INTENT_CONTENT) : null;
                        if (jSONObject3 != null && jSONObject3.has("imo")) {
                            Long lValueOf = Long.valueOf(jSONObject3.getJSONObject("imo").getString("mac"));
                            int i = jSONObject3.getJSONObject("imo").getInt("mv");
                            if (Jni.encode3(this.b).longValue() == lValueOf.longValue()) {
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("tt", Integer.valueOf((int) (System.currentTimeMillis() / 1000)));
                                contentValues.put("hst", Integer.valueOf(i));
                                try {
                                    if (s.this.f.update("hstdata", contentValues, "id = \"" + lValueOf + "\"", null) <= 0) {
                                        contentValues.put(BreakpointSQLiteKey.ID, lValueOf);
                                        s.this.f.insert("hstdata", null, contentValues);
                                    }
                                } catch (Exception unused) {
                                }
                                Bundle bundle = new Bundle();
                                bundle.putByteArray("mac", this.b.getBytes());
                                bundle.putInt("hotspot", i);
                                s.this.a(bundle);
                            }
                        }
                    }
                } catch (Exception unused2) {
                }
            } else if (this.d) {
                s.this.f();
            }
            if (this.ei != null) {
                this.ei.clear();
            }
            s.this.g = false;
        }

        @Override // com.baidu.location.h.h
        public void b() {
            this.ef = 1;
            String strT = com.baidu.location.f.h.a().t();
            if (strT != null) {
                this.eo = Jni.encodeTp4(strT);
            }
            String strEncodeTp4 = Jni.encodeTp4(this.c);
            this.c = null;
            this.ei.put("bloc", strEncodeTp4);
        }
    }

    public static s a() {
        s sVar;
        synchronized (c) {
            if (d == null) {
                d = new s();
            }
            sVar = d;
        }
        return sVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a(boolean r8) {
        /*
            r7 = this;
            com.baidu.location.f.h r0 = com.baidu.location.f.h.a()
            com.baidu.location.f.a r0 = r0.f()
            com.baidu.location.f.h r1 = com.baidu.location.f.h.a()
            com.baidu.location.f.p r1 = r1.u()
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r3 = 1024(0x400, float:1.435E-42)
            r2.<init>(r3)
            if (r0 == 0) goto L2a
            boolean r3 = r0.b()
            if (r3 == 0) goto L2a
            com.baidu.location.f.h r3 = com.baidu.location.f.h.a()
            java.lang.String r0 = r3.b(r0)
            r2.append(r0)
        L2a:
            r0 = 1
            r3 = 0
            if (r1 == 0) goto L41
            int r4 = r1.a()
            if (r4 <= r0) goto L41
            com.baidu.location.f.h r4 = com.baidu.location.f.h.a()
            r5 = 15
            int r6 = com.baidu.location.h.s.ay
            java.lang.String r1 = r4.a(r5, r3, r1, r6)
            goto L4b
        L41:
            com.baidu.location.f.h r1 = com.baidu.location.f.h.a()
            java.lang.String r1 = r1.q()
            if (r1 == 0) goto L4e
        L4b:
            r2.append(r1)
        L4e:
            if (r8 == 0) goto L55
            java.lang.String r8 = "&imo=1"
            r2.append(r8)
        L55:
            com.baidu.location.f.e r8 = com.baidu.location.f.e.a()
            java.lang.String r8 = r8.n()
            r2.append(r8)
            com.baidu.location.h.b r8 = com.baidu.location.h.b.a()
            java.lang.String r8 = r8.a(r3)
            r2.append(r8)
            com.baidu.location.b.c r8 = com.baidu.location.b.c.a()
            java.lang.String r8 = r8.d()
            r2.append(r8)
            com.baidu.location.b.i r8 = com.baidu.location.b.i.a()
            java.lang.String r8 = r8.c()
            r2.append(r8)
            android.content.Context r8 = com.baidu.location.f.getServiceContext()
            java.lang.String r8 = com.baidu.location.h.s.e(r8)
            r2.append(r8)
            android.content.Context r8 = com.baidu.location.f.getServiceContext()
            int r8 = com.baidu.location.h.s.c(r8)
            if (r8 < 0) goto L9e
            java.lang.String r1 = "&lmd="
            r2.append(r1)
            r2.append(r8)
        L9e:
            java.lang.String r8 = "&cnloc="
            r2.append(r8)
            com.baidu.location.b.t r8 = com.baidu.location.b.t.a()
            int r8 = r8.b()
            r2.append(r8)
            java.lang.String r8 = r2.toString()
            int r1 = r8.length()
            int r2 = com.baidu.location.h.s.aN
            if (r1 <= r2) goto Lfb
            java.lang.String r1 = "&cl_list="
            java.lang.String[] r1 = r8.split(r1)
            int r2 = r1.length
            r4 = 2
            if (r2 != r4) goto Lfb
            r8 = r1[r0]
            java.lang.String r2 = "&"
            java.lang.String[] r8 = r8.split(r2, r4)
            int r2 = r8.length
            if (r2 != r4) goto Le8
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r1 = r1[r3]
            r2.append(r1)
            java.lang.String r1 = "&cl_list=null&"
            r2.append(r1)
            r8 = r8[r0]
            r2.append(r8)
            java.lang.String r8 = r2.toString()
            goto Lfb
        Le8:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r0 = r1[r3]
            r8.append(r0)
            java.lang.String r0 = "&cl_list=null"
            r8.append(r0)
            java.lang.String r8 = r8.toString()
        Lfb:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.s.a(boolean):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Bundle bundle) {
        c.a().a(bundle, 406);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        Bundle bundle = new Bundle();
        bundle.putInt("hotspot", -1);
        a(bundle);
    }

    public void a(String str) {
        if (this.g) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = jSONObject.has(Constant.MODIFY_ACTIVITY_INTENT_CONTENT) ? jSONObject.getJSONObject(Constant.MODIFY_ACTIVITY_INTENT_CONTENT) : null;
            if (jSONObject2 == null || !jSONObject2.has("imo")) {
                return;
            }
            Long lValueOf = Long.valueOf(jSONObject2.getJSONObject("imo").getString("mac"));
            int i = jSONObject2.getJSONObject("imo").getInt("mv");
            ContentValues contentValues = new ContentValues();
            contentValues.put("tt", Integer.valueOf((int) (System.currentTimeMillis() / 1000)));
            contentValues.put("hst", Integer.valueOf(i));
            if (this.f.update("hstdata", contentValues, "id = \"" + lValueOf + "\"", null) <= 0) {
                contentValues.put(BreakpointSQLiteKey.ID, lValueOf);
                this.f.insert("hstdata", null, contentValues);
            }
        } catch (Exception unused) {
        }
    }

    public void b() {
        try {
            File file = new File(e);
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.exists()) {
                SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(file, (SQLiteDatabase.CursorFactory) null);
                this.f = sQLiteDatabaseOpenOrCreateDatabase;
                sQLiteDatabaseOpenOrCreateDatabase.execSQL("CREATE TABLE IF NOT EXISTS hstdata(id Long PRIMARY KEY,hst INT,tt INT);");
                this.f.setVersion(1);
            }
        } catch (Exception unused) {
            this.f = null;
        }
    }

    public void c() {
        SQLiteDatabase sQLiteDatabase = this.f;
        if (sQLiteDatabase != null) {
            try {
                sQLiteDatabase.close();
            } catch (Exception unused) {
            } catch (Throwable th) {
                this.f = null;
                throw th;
            }
            this.f = null;
        }
    }

    public synchronized int d() {
        int i;
        int i2 = -3;
        if (this.g) {
            return -3;
        }
        try {
            if (com.baidu.location.f.h.a().m() && this.f != null) {
                WifiInfo wifiInfoR = com.baidu.location.f.h.a().r();
                Cursor cursorRawQuery = null;
                String strA = com.baidu.location.f.h.a().a(wifiInfoR, (String) null);
                if (wifiInfoR != null && strA != null) {
                    String strReplace = strA.replace(":", "");
                    Long lEncode3 = Jni.encode3(strReplace);
                    String str = this.h;
                    if (str == null || !strReplace.equals(str) || (i = this.i) <= -2) {
                        try {
                            cursorRawQuery = this.f.rawQuery("select * from hstdata where id = ?", new String[]{String.valueOf(lEncode3)});
                            if (cursorRawQuery == null || !cursorRawQuery.moveToFirst()) {
                                i2 = -2;
                            } else {
                                i2 = cursorRawQuery.getInt(1);
                                this.h = strReplace;
                                this.i = i2;
                            }
                        } catch (Exception unused) {
                            if (cursorRawQuery != null) {
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
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                    } else {
                        i2 = i;
                    }
                }
            }
        } catch (Exception unused3) {
        }
        this.i = i2;
        return i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0085 A[EXC_TOP_SPLITTER, PHI: r2 r3
      0x0085: PHI (r2v4 android.database.Cursor) = (r2v3 android.database.Cursor), (r2v6 android.database.Cursor) binds: [B:35:0x0095, B:25:0x0083] A[DONT_GENERATE, DONT_INLINE]
      0x0085: PHI (r3v1 boolean) = (r3v0 boolean), (r3v4 boolean) binds: [B:35:0x0095, B:25:0x0083] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e() {
        /*
            r10 = this;
            boolean r0 = r10.g
            if (r0 == 0) goto L5
            return
        L5:
            com.baidu.location.f.h r0 = com.baidu.location.f.h.a()     // Catch: java.lang.Exception -> Lb2
            boolean r0 = r0.m()     // Catch: java.lang.Exception -> Lb2
            if (r0 == 0) goto Laf
            android.database.sqlite.SQLiteDatabase r0 = r10.f     // Catch: java.lang.Exception -> Lb2
            if (r0 == 0) goto Laf
            com.baidu.location.f.h r0 = com.baidu.location.f.h.a()     // Catch: java.lang.Exception -> Lb2
            android.net.wifi.WifiInfo r0 = r0.r()     // Catch: java.lang.Exception -> Lb2
            if (r0 == 0) goto Laf
            java.lang.String r1 = r0.getBSSID()     // Catch: java.lang.Exception -> Lb2
            if (r1 == 0) goto Laf
            java.lang.String r0 = r0.getBSSID()     // Catch: java.lang.Exception -> Lb2
            java.lang.String r1 = ":"
            java.lang.String r2 = ""
            java.lang.String r0 = r0.replace(r1, r2)     // Catch: java.lang.Exception -> Lb2
            java.lang.Long r1 = com.baidu.location.Jni.encode3(r0)     // Catch: java.lang.Exception -> Lb2
            r2 = 0
            r3 = 0
            r4 = 1
            android.database.sqlite.SQLiteDatabase r5 = r10.f     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L92
            java.lang.String r6 = "select * from hstdata where id = ?"
            java.lang.String[] r7 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L92
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L92
            r7[r3] = r1     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L92
            android.database.Cursor r2 = r5.rawQuery(r6, r7)     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L92
            if (r2 == 0) goto L82
            boolean r1 = r2.moveToFirst()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L92
            if (r1 == 0) goto L82
            int r1 = r2.getInt(r4)     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L92
            r5 = 2
            int r5 = r2.getInt(r5)     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L92
            long r6 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L92
            r8 = 1000(0x3e8, double:4.94E-321)
            long r6 = r6 / r8
            long r8 = (long) r5     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L92
            long r6 = r6 - r8
            r8 = 259200(0x3f480, double:1.28062E-318)
            int r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r5 <= 0) goto L69
            goto L82
        L69:
            android.os.Bundle r5 = new android.os.Bundle     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L92
            r5.<init>()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L92
            java.lang.String r6 = "mac"
            byte[] r7 = r0.getBytes()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L92
            r5.putByteArray(r6, r7)     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L92
            java.lang.String r6 = "hotspot"
            r5.putInt(r6, r1)     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L92
            r10.a(r5)     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L92
            goto L83
        L82:
            r3 = 1
        L83:
            if (r2 == 0) goto L96
        L85:
            r2.close()     // Catch: java.lang.Exception -> L89
            goto L96
        L89:
            goto L96
        L8b:
            r0 = move-exception
            if (r2 == 0) goto L91
            r2.close()     // Catch: java.lang.Exception -> L91
        L91:
            throw r0     // Catch: java.lang.Exception -> Lb2
        L92:
            if (r2 == 0) goto L96
            goto L85
        L96:
            if (r3 == 0) goto Lb2
            com.baidu.location.b.s$a r1 = r10.a     // Catch: java.lang.Exception -> Lb2
            if (r1 != 0) goto La3
            com.baidu.location.b.s$a r1 = new com.baidu.location.b.s$a     // Catch: java.lang.Exception -> Lb2
            r1.<init>()     // Catch: java.lang.Exception -> Lb2
            r10.a = r1     // Catch: java.lang.Exception -> Lb2
        La3:
            com.baidu.location.b.s$a r1 = r10.a     // Catch: java.lang.Exception -> Lb2
            if (r1 == 0) goto Lb2
            java.lang.String r2 = r10.a(r4)     // Catch: java.lang.Exception -> Lb2
            r1.a(r0, r2)     // Catch: java.lang.Exception -> Lb2
            goto Lb2
        Laf:
            r10.f()     // Catch: java.lang.Exception -> Lb2
        Lb2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.s.e():void");
    }
}
