package com.baidu.location.e;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.baidu.location.Jni;
import com.baidu.location.b.al;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class l {
    private static final String d = String.format(Locale.US, "DELETE FROM LOG WHERE timestamp NOT IN (SELECT timestamp FROM LOG ORDER BY timestamp DESC LIMIT %d);", 3000);
    private static final String e = String.format(Locale.US, "SELECT * FROM LOG ORDER BY timestamp DESC LIMIT %d;", 3);
    private final SQLiteDatabase b;
    private String a = null;
    private final a c = new a(this);

    private class a extends com.baidu.location.h.h {
        private int b;
        private long c;
        private String d = null;
        private boolean e = false;
        private boolean f = false;
        private l g;

        a(l lVar) {
            this.g = lVar;
            this.ei = new HashMap();
            this.b = 0;
            this.c = -1L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            if (this.e) {
                return;
            }
            this.d = this.g.b();
            long j = this.c;
            if (j != -1 && j + 86400000 <= System.currentTimeMillis()) {
                this.b = 0;
                this.c = -1L;
            }
            if (this.d == null || this.b >= 2) {
                return;
            }
            this.e = true;
            ExecutorService executorServiceC = al.a().c();
            if (executorServiceC != null) {
                a(executorServiceC, "https://ofloc.map.baidu.com/offline_loc");
            } else {
                e("https://ofloc.map.baidu.com/offline_loc");
            }
        }

        @Override // com.baidu.location.h.h
        public void a(boolean z) throws SQLException {
            this.f = false;
            if (z && this.eg != null) {
                try {
                    JSONObject jSONObject = new JSONObject(this.eg);
                    if (jSONObject.has("error") && jSONObject.getInt("error") == 161) {
                        this.f = true;
                    }
                } catch (Exception unused) {
                }
            }
            if (!this.f) {
                this.b++;
                this.c = System.currentTimeMillis();
            }
            this.g.a(this.f);
            this.e = false;
        }

        @Override // com.baidu.location.h.h
        public void b() {
            this.ei.clear();
            this.ei.put("qt", "ofbh");
            this.ei.put("req", this.d);
            this.ee = i.a;
        }
    }

    l(SQLiteDatabase sQLiteDatabase) throws SQLException {
        this.b = sQLiteDatabase;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            return;
        }
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS LOG(timestamp LONG PRIMARY KEY, log VARCHAR(4000))");
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) throws SQLException {
        String str;
        if (z && (str = this.a) != null) {
            String str2 = String.format("DELETE FROM LOG WHERE timestamp in (%s);", str);
            try {
                if (this.a.length() > 0) {
                    this.b.execSQL(str2);
                }
            } catch (Exception unused) {
            }
        }
        this.a = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b() throws Throwable {
        String str;
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        Cursor cursor = null;
        string = null;
        string = null;
        string = null;
        String string = null;
        Cursor cursor2 = null;
        try {
            Cursor cursorRawQuery = this.b.rawQuery(e, null);
            if (cursorRawQuery != null) {
                try {
                    if (cursorRawQuery.getCount() > 0) {
                        StringBuffer stringBuffer = new StringBuffer();
                        cursorRawQuery.moveToFirst();
                        while (!cursorRawQuery.isAfterLast()) {
                            jSONArray.put(cursorRawQuery.getString(1));
                            if (stringBuffer.length() != 0) {
                                stringBuffer.append(",");
                            }
                            stringBuffer.append(cursorRawQuery.getLong(0));
                            cursorRawQuery.moveToNext();
                        }
                        try {
                            jSONObject.put("ofloc", jSONArray);
                            string = jSONObject.toString();
                        } catch (JSONException unused) {
                        }
                        this.a = stringBuffer.toString();
                    }
                } catch (Exception unused2) {
                    str = string;
                    cursor2 = cursorRawQuery;
                    if (cursor2 != null) {
                        try {
                            cursor2.close();
                        } catch (Exception unused3) {
                        }
                    }
                    return str;
                } catch (Throwable th) {
                    th = th;
                    cursor = cursorRawQuery;
                    if (cursor != null) {
                        try {
                            cursor.close();
                        } catch (Exception unused4) {
                        }
                    }
                    throw th;
                }
            }
            if (cursorRawQuery == null) {
                return string;
            }
            try {
                cursorRawQuery.close();
                return string;
            } catch (Exception unused5) {
                return string;
            }
        } catch (Exception unused6) {
            str = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    void a() {
        this.c.a();
    }

    void a(String str) throws SQLException {
        try {
            this.b.execSQL(String.format(Locale.US, "INSERT OR IGNORE INTO LOG VALUES (%d,\"%s\");", Long.valueOf(System.currentTimeMillis()), Jni.encodeOfflineLocationUpdateRequest(str)));
            this.b.execSQL(d);
        } catch (Exception unused) {
        }
    }
}
