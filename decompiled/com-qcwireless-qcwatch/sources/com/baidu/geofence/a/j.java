package com.baidu.geofence.a;

import android.text.TextUtils;
import com.baidu.location.Jni;
import com.baidu.location.b.al;
import com.baidu.location.h.s;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class j extends com.baidu.location.h.h {
    private int a;
    private List<String> b = null;
    private boolean c = false;
    private boolean d = false;
    private ArrayList<StringBuilder> e;
    private a f;

    public interface a {
        void clear();
    }

    public j() {
        this.ei = new HashMap();
        this.a = 0;
    }

    public void a(a aVar) {
        this.f = aVar;
    }

    public void a(ArrayList<StringBuilder> arrayList) {
        this.e = arrayList;
    }

    @Override // com.baidu.location.h.h
    public void a(boolean z) {
        this.d = false;
        if (z && this.eg != null) {
            try {
                new JSONObject(this.eg);
                a aVar = this.f;
                if (aVar != null) {
                    aVar.clear();
                }
                this.d = true;
            } catch (Exception unused) {
            }
        }
        boolean z2 = this.d;
        if (!z2) {
            this.a++;
        }
        if (z2) {
            this.a = 0;
        }
        this.b.clear();
        this.c = false;
    }

    public boolean a(String[] strArr) {
        if (!this.c && this.a < 10) {
            if (strArr != null) {
                for (String str : strArr) {
                    if (this.b == null) {
                        this.b = new ArrayList();
                    }
                    this.b.add(Jni.encode(str));
                }
            }
            List<String> list = this.b;
            if (list != null && list.size() > 0) {
                this.c = true;
                ExecutorService executorServiceC = al.a().c();
                if (executorServiceC != null) {
                    a(executorServiceC, s.d());
                } else {
                    e(s.d());
                }
                return true;
            }
        }
        return false;
    }

    @Override // com.baidu.location.h.h
    public void b() {
        Map<String, Object> map;
        String str;
        String string;
        this.ei.clear();
        this.ei.put("qt", "cltrw");
        this.ee = s.d();
        for (int i = 0; i < this.b.size(); i++) {
            ArrayList<StringBuilder> arrayList = this.e;
            if (arrayList == null || arrayList.isEmpty() || TextUtils.isEmpty(this.e.get(i).toString())) {
                map = this.ei;
                StringBuilder sb = new StringBuilder();
                sb.append("cltr[");
                sb.append(i);
                sb.append("]");
                string = sb.toString();
                str = this.b.get(i);
                map.put(string, str);
            } else {
                map = this.ei;
                string = "cltr[" + i + "]";
                str = this.b.get(i) + "&" + Jni.encode(this.e.get(i).toString());
                map.put(string, str);
            }
        }
        this.ei.put("info", Jni.encode(com.baidu.location.h.b.a().e() + "&isgeofence=1"));
        this.ei.put("trtm", String.format(Locale.CHINA, "%d", Long.valueOf(System.currentTimeMillis())));
        this.b.clear();
    }
}
