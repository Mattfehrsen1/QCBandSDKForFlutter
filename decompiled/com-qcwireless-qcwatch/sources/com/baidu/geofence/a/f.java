package com.baidu.geofence.a;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.geofence.GeoFence;
import com.baidu.geofence.GeoFenceListener;
import com.baidu.geofence.PoiItem;
import com.baidu.geofence.model.DPoint;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.lbsapi.auth.LBSAuthManagerListener;
import com.baidu.location.b.al;
import com.baidu.location.h.s;
import com.bumptech.glide.load.Key;
import com.google.android.gms.actions.SearchIntents;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class f extends com.baidu.location.h.h implements LBSAuthManagerListener {
    private String b;
    private String c;
    private String d;
    private boolean e;
    private DPoint f;
    private float g;
    private int h;
    private String i;
    private GeoFenceListener k;
    private String l;
    private ArrayList<GeoFence> m;
    private Context n;
    private a o;
    private final String a = "https://api.map.baidu.com/sdkproxy/v2/lbs_locsdk/place/v2/search";
    private boolean j = false;

    public interface a {
        void a(int i, ArrayList<PoiItem> arrayList);
    }

    public f(Context context, boolean z, GeoFenceListener geoFenceListener, ArrayList<GeoFence> arrayList) {
        this.ei = new HashMap();
        this.e = z;
        this.m = arrayList;
        this.k = geoFenceListener;
        this.n = context;
        LBSAuthManager.getInstance(context).authenticate(false, "lbs_locsdk", null, this);
    }

    private void a() {
        if (this.j) {
            return;
        }
        this.j = true;
        ExecutorService executorServiceC = al.a().c();
        if (executorServiceC != null) {
            a(executorServiceC);
        } else {
            c(true);
        }
    }

    public void a(float f) {
        this.g = f;
    }

    public void a(int i) {
        this.h = i;
    }

    public void a(a aVar) {
        this.o = aVar;
    }

    public void a(DPoint dPoint) {
        this.f = dPoint;
    }

    public void a(String str) {
        this.l = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x01b8 A[Catch: JSONException -> 0x01e5, LOOP:0: B:24:0x0072->B:58:0x01b8, LOOP_END, TryCatch #0 {JSONException -> 0x01e5, blocks: (B:50:0x0137, B:55:0x01b3, B:60:0x01c8, B:58:0x01b8, B:52:0x0157, B:61:0x01d7, B:63:0x01dc), top: B:76:0x0068 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01b7 A[SYNTHETIC] */
    @Override // com.baidu.location.h.h
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(boolean r27) {
        /*
            Method dump skipped, instructions count: 510
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.geofence.a.f.a(boolean):void");
    }

    @Override // com.baidu.location.h.h
    public void b() {
        String strValueOf;
        String str;
        StringBuffer stringBuffer = new StringBuffer(128);
        HashMap map = new HashMap();
        map.put(SearchIntents.EXTRA_QUERY, this.b);
        map.put("token", this.c);
        map.put("tag", this.d);
        map.put("output", "json");
        map.put("page_size", "20");
        map.put("ret_coordtype", "gcj02ll");
        try {
            stringBuffer.append("query=");
            stringBuffer.append(URLEncoder.encode(this.b, Key.STRING_CHARSET_NAME));
            stringBuffer.append("&output=");
            stringBuffer.append(URLEncoder.encode("json", Key.STRING_CHARSET_NAME));
            stringBuffer.append("&ret_coordtype=");
            stringBuffer.append(URLEncoder.encode("gcj02ll", Key.STRING_CHARSET_NAME));
            stringBuffer.append("&token=");
            stringBuffer.append(URLEncoder.encode(this.c, Key.STRING_CHARSET_NAME));
            stringBuffer.append("&tag=");
            stringBuffer.append(URLEncoder.encode(this.d, Key.STRING_CHARSET_NAME));
            stringBuffer.append("&page_size=");
            stringBuffer.append(URLEncoder.encode("20", Key.STRING_CHARSET_NAME));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (this.e) {
            try {
                stringBuffer.append("&location=");
                stringBuffer.append(URLEncoder.encode(this.f.getLatitude() + "," + this.f.getLongitude(), Key.STRING_CHARSET_NAME));
                stringBuffer.append("&coord_type=");
                stringBuffer.append(URLEncoder.encode(String.valueOf(2), Key.STRING_CHARSET_NAME));
                stringBuffer.append("&radius=");
                stringBuffer.append(URLEncoder.encode(String.valueOf(this.g), Key.STRING_CHARSET_NAME));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
            map.put("location", this.f.getLatitude() + "," + this.f.getLongitude());
            map.put("coord_type", String.valueOf(2));
            strValueOf = String.valueOf(this.g);
            str = "radius";
        } else {
            stringBuffer.append("&region=");
            try {
                stringBuffer.append(URLEncoder.encode(this.i, Key.STRING_CHARSET_NAME));
            } catch (UnsupportedEncodingException e3) {
                e3.printStackTrace();
            }
            strValueOf = this.i;
            str = "region";
        }
        map.put(str, strValueOf);
        String strA = com.baidu.geofence.a.a.a(map, "&");
        stringBuffer.append("&sign=");
        stringBuffer.append(strA);
        this.ee = "https://api.map.baidu.com/sdkproxy/v2/lbs_locsdk/place/v2/search?" + stringBuffer.toString();
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.d = str;
    }

    public void d(String str) {
        this.i = str;
    }

    @Override // com.baidu.lbsapi.auth.LBSAuthManagerListener
    public void onAuthResult(int i, String str) {
        GeoFenceListener geoFenceListener;
        if (i != 0 && (geoFenceListener = this.k) != null) {
            geoFenceListener.onGeoFenceCreateFinished(null, 11, this.l);
        }
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("token")) {
                    this.c = jSONObject.optString("token");
                    if (s.b() || TextUtils.isEmpty(this.c)) {
                        return;
                    }
                    a();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
