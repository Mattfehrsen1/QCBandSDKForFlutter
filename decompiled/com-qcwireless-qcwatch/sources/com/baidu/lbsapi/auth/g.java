package com.baidu.lbsapi.auth;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
class g {
    private Context a;
    private List<HashMap<String, String>> b = null;
    private a<String> c = null;

    interface a<Result> {
        void a(Result result);
    }

    protected g(Context context) {
        this.a = context;
    }

    private List<HashMap<String, String>> a(HashMap<String, String> map, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        if (strArr == null || strArr.length <= 0) {
            HashMap map2 = new HashMap();
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                String string = it.next().toString();
                map2.put(string, map.get(string));
            }
            arrayList.add(map2);
        } else {
            for (String str : strArr) {
                HashMap map3 = new HashMap();
                Iterator<String> it2 = map.keySet().iterator();
                while (it2.hasNext()) {
                    String string2 = it2.next().toString();
                    map3.put(string2, map.get(string2));
                }
                map3.put("mcode", str);
                arrayList.add(map3);
            }
        }
        return arrayList;
    }

    private void a(String str) throws JSONException {
        JSONObject jSONObject;
        if (str == null) {
            str = "";
        }
        try {
            jSONObject = new JSONObject(str);
            if (!jSONObject.has(NotificationCompat.CATEGORY_STATUS)) {
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, -1);
            }
        } catch (JSONException unused) {
            jSONObject = new JSONObject();
            try {
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, -1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        a<String> aVar = this.c;
        if (aVar != null) {
            aVar.a(jSONObject.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<HashMap<String, String>> list, String str, int i, String str2, String str3) throws Throwable {
        int i2;
        b.a("syncConnect start Thread id = " + String.valueOf(Thread.currentThread().getId()));
        if (list == null || list.size() == 0) {
            b.c("syncConnect failed,params list is null or size is 0");
            return;
        }
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        while (i3 < list.size()) {
            b.a("syncConnect resuest " + i3 + "  start!!!");
            HashMap<String, String> map = list.get(i3);
            i iVar = new i(this.a);
            if (iVar.a()) {
                String strA = iVar.a(map, str, i, str2, str3);
                if (strA == null) {
                    strA = "";
                }
                b.a("syncConnect resuest " + i3 + "  result:" + strA);
                arrayList.add(strA);
                try {
                    JSONObject jSONObject = new JSONObject(strA);
                    if (jSONObject.has(NotificationCompat.CATEGORY_STATUS) && jSONObject.getInt(NotificationCompat.CATEGORY_STATUS) == 0) {
                        b.a("auth end and break");
                        a(strA);
                        return;
                    }
                } catch (JSONException unused) {
                    b.a("continue-------------------------------");
                }
            } else {
                b.a("Current network is not available.");
                arrayList.add(ErrorMessage.a("Current network is not available."));
            }
            b.a("syncConnect end");
            i3++;
        }
        b.a("--iiiiii:" + i3 + "<><>paramList.size():" + list.size() + "<><>authResults.size():" + arrayList.size());
        if (list.size() <= 0 || i3 != list.size() || arrayList.size() <= 0 || i3 != arrayList.size() || i3 - 1 <= 0) {
            return;
        }
        try {
            JSONObject jSONObject2 = new JSONObject((String) arrayList.get(i2));
            if (!jSONObject2.has(NotificationCompat.CATEGORY_STATUS) || jSONObject2.getInt(NotificationCompat.CATEGORY_STATUS) == 0) {
                return;
            }
            b.a("i-1 result is not 0,return first result");
            a((String) arrayList.get(0));
        } catch (JSONException e) {
            a(ErrorMessage.a("JSONException:" + e.getMessage()));
        }
    }

    protected void a(HashMap<String, String> map, String[] strArr, String str, int i, String str2, String str3, a<String> aVar) {
        this.b = a(map, strArr);
        this.c = aVar;
        new Thread(new h(this, str, i, str2, str3)).start();
    }
}
