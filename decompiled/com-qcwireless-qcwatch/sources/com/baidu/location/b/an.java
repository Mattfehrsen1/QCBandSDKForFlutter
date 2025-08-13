package com.baidu.location.b;

import android.location.GnssNavigationMessage;
import android.text.TextUtils;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class an {
    private b a;
    private long b = 0;
    private long c = 0;

    private static class a {
        private static an a = new an();
    }

    class b extends com.baidu.location.h.h {
        private boolean d = false;
        private String e = null;
        public boolean a = false;
        public long b = 0;

        public b() {
            this.ei = new HashMap();
        }

        public void a(String str, long j) {
            if (this.d) {
                return;
            }
            this.d = true;
            this.e = str;
            this.b = j;
            ExecutorService executorServiceC = al.a().c();
            if (executorServiceC != null) {
                a(executorServiceC, com.baidu.location.h.e.n);
            } else {
                e(com.baidu.location.h.e.n);
            }
        }

        @Override // com.baidu.location.h.h
        public void a(boolean z) {
            if (z && this.eg != null) {
                try {
                    new JSONObject(this.eg);
                    this.a = true;
                } catch (Throwable unused) {
                }
            }
            if (this.ei != null) {
                this.ei.clear();
            }
            this.d = false;
        }

        public boolean a() {
            return this.d;
        }

        @Override // com.baidu.location.h.h
        public void b() {
            String strD = com.baidu.location.h.b.a().d();
            if (strD != null) {
                strD = strD + "&gnsst=" + this.b;
            }
            String strA = w.a().a(strD);
            String strReplaceAll = !TextUtils.isEmpty(strA) ? strA.trim().replaceAll("\r|\n", "") : "null";
            String strA2 = w.a().a(this.e);
            String strReplaceAll2 = TextUtils.isEmpty(strA2) ? "null" : strA2.trim().replaceAll("\r|\n", "");
            try {
                this.ei.put("info", URLEncoder.encode(strReplaceAll, "utf-8"));
                this.ei.put("enl", URLEncoder.encode(strReplaceAll2, "utf-8"));
            } catch (Exception unused) {
            }
        }
    }

    public static an a() {
        return a.a;
    }

    public void a(GnssNavigationMessage gnssNavigationMessage, long j) throws NumberFormatException {
        ag.a().a(gnssNavigationMessage, j);
        this.b = System.currentTimeMillis();
        this.c = j;
    }

    public void b() {
        ArrayList<String> arrayListB;
        if (this.b == 0 || Math.abs(System.currentTimeMillis() - this.b) >= 20000) {
            return;
        }
        if (this.a == null) {
            this.a = new b();
        }
        b bVar = this.a;
        if (bVar == null || bVar.a() || (arrayListB = ag.a().b()) == null || arrayListB.size() <= 0) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        Iterator<String> it = arrayListB.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next());
            i++;
            if (i != arrayListB.size()) {
                stringBuffer.append(";");
            }
        }
        this.a.a(stringBuffer.toString(), this.c);
    }
}
