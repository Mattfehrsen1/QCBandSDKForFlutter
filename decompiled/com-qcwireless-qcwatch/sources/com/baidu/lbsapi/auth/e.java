package com.baidu.lbsapi.auth;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
class e {
    private Context a;
    private HashMap<String, String> b = null;
    private a<String> c = null;

    interface a<Result> {
        void a(Result result);
    }

    protected e(Context context) {
        this.a = context;
    }

    private HashMap<String, String> a(HashMap<String, String> map) {
        HashMap<String, String> map2 = new HashMap<>();
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String string = it.next().toString();
            map2.put(string, map.get(string));
        }
        return map2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) throws JSONException {
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

    protected void a(HashMap<String, String> map, String str, int i, String str2, String str3, a<String> aVar) {
        this.b = a(map);
        this.c = aVar;
        new Thread(new f(this, str, i, str2, str3)).start();
    }
}
