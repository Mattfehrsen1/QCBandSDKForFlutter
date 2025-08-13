package com.baidu.location.indoor;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.baidu.location.b.af;
import com.bumptech.glide.load.Key;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class d {
    public CopyOnWriteArrayList<c> a;
    public CopyOnWriteArrayList<x> b;
    private SharedPreferences c;
    private a d;
    private boolean e;
    private c f;
    private long g;
    private String h;
    private x i;

    class a extends com.baidu.location.h.h {
        public boolean a;
        final /* synthetic */ d b;

        @Override // com.baidu.location.h.h
        public void a(boolean z) throws Throwable {
            if (z && !TextUtils.isEmpty(this.eg)) {
                try {
                    byte[] bArrB = com.baidu.location.h.s.b(Base64.decode(new JSONObject(this.eg).optString("data").getBytes(), 0));
                    String str = bArrB != null ? new String(bArrB, Key.STRING_CHARSET_NAME) : "";
                    JSONObject jSONObject = new JSONObject(str);
                    if (this.b.c != null) {
                        this.b.c.getString("Indoor-> BleWalkNavConfig_ver", "0");
                        String string = jSONObject.has("ver") ? jSONObject.getString("ver") : null;
                        SharedPreferences.Editor editorEdit = this.b.c.edit();
                        editorEdit.putString("Indoor-> BleWalkNavConfig_data", str);
                        editorEdit.putString("Indoor-> BleWalkNavConfig_ver", string);
                        editorEdit.apply();
                        this.b.a(str);
                        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
                        this.b.g = jCurrentTimeMillis;
                        editorEdit.putLong("Indoor-> BleWalkNavConfig_lastCheckTime", jCurrentTimeMillis);
                        editorEdit.putString("Indoor-> BleWalkNavConfig_cityCode", this.b.h);
                        editorEdit.apply();
                    }
                } catch (Exception unused) {
                }
            }
            if (this.ei != null) {
                this.ei.clear();
            }
            this.a = false;
        }

        @Override // com.baidu.location.h.h
        public void b() {
            this.ef = 2;
            this.b.c.getString("Indoor-> BleWalkNavConfig_ver", "0");
            this.ei.put("ver", "0");
            this.ei.put("newIn", "newIn");
            this.ei.put("sdk", Float.valueOf(9.601f));
            this.ei.put("stp", 1);
            this.ei.put("city_code", this.b.h);
            String strB = com.baidu.location.h.b.a().b();
            if (strB == null || strB.isEmpty()) {
                return;
            }
            this.ei.put("cu", strB);
        }
    }

    private static class b {
        public static final d a = new d(null);
    }

    private d() {
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = null;
        this.g = 0L;
        this.h = "";
        this.i = null;
        this.h = af.a().a("mapcity", "");
    }

    /* synthetic */ d(e eVar) {
        this();
    }

    public static d a() {
        return b.a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("city_indoor_loc_rects")) {
                JSONArray jSONArray = jSONObject.getJSONArray("city_indoor_loc_rects");
                if (jSONArray.length() > 0) {
                    ArrayList<c> arrayList = new ArrayList<>();
                    ArrayList<x> arrayList2 = new ArrayList<>();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                        if (jSONObjectOptJSONObject != null) {
                            if (jSONObjectOptJSONObject.has("city_code")) {
                                jSONObjectOptJSONObject.optString("city_code");
                            }
                            a(jSONObjectOptJSONObject, arrayList);
                            b(jSONObjectOptJSONObject, arrayList2);
                        }
                    }
                    a(arrayList);
                    b(arrayList2);
                }
            }
        } catch (Exception unused) {
        }
    }

    private void a(ArrayList<c> arrayList) {
        CopyOnWriteArrayList<c> copyOnWriteArrayList = this.a;
        if (copyOnWriteArrayList == null) {
            this.a = new CopyOnWriteArrayList<>();
        } else if (copyOnWriteArrayList.size() > 0) {
            this.a.clear();
        }
        if (arrayList.size() > 0) {
            this.a.addAll(arrayList);
        }
    }

    private void a(JSONObject jSONObject, ArrayList<c> arrayList) {
        JSONArray jSONArrayOptJSONArray;
        if (!jSONObject.has("indoor_loc_rect_info") || (jSONArrayOptJSONArray = jSONObject.optJSONArray("indoor_loc_rect_info")) == null) {
            return;
        }
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            arrayList.add(new c(jSONArrayOptJSONArray.optJSONObject(i)));
        }
    }

    private void b(ArrayList<x> arrayList) {
        CopyOnWriteArrayList<x> copyOnWriteArrayList = this.b;
        if (copyOnWriteArrayList == null) {
            this.b = new CopyOnWriteArrayList<>();
        } else if (copyOnWriteArrayList.size() > 0) {
            this.b.clear();
        }
        if (arrayList.size() > 0) {
            this.b.addAll(arrayList);
        }
    }

    private void b(JSONObject jSONObject, ArrayList<x> arrayList) {
        JSONArray jSONArrayOptJSONArray;
        if (!jSONObject.has("outdoor_loc_rect_info") || (jSONArrayOptJSONArray = jSONObject.optJSONArray("outdoor_loc_rect_info")) == null) {
            return;
        }
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            arrayList.add(new x(jSONArrayOptJSONArray.optJSONObject(i)));
        }
    }

    public synchronized c b() {
        return this.f;
    }
}
