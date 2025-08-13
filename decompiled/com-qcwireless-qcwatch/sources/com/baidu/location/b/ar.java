package com.baidu.location.b;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ar {

    private static class a {
        public static final ar a = new ar();
    }

    private ar() {
    }

    public static ar a() {
        return a.a;
    }

    private void a(String str, String str2, String str3) {
        Log.d("UrlConfigManger", "setConfig");
        Log.d("baidu_location_dev", "type:" + str + ", owner: " + str2 + " ,url: " + str3);
        if (str2.contains("default_owner") || "default_url".equals(str3) || !str3.contains("https")) {
            Log.d("baidu_location_dev", "url 不合法");
            return;
        }
        str.hashCode();
        switch (str) {
            case "indoor_roadnet":
                com.baidu.location.h.e.h = str3;
                break;
            case "vdr_log_update":
                com.baidu.location.h.e.k = str3;
                break;
            case "hdyawupdate":
                com.baidu.location.h.e.p = str3;
                break;
            case "basement_inout":
                com.baidu.location.h.e.q = str3;
                break;
            case "indoor_poi_data":
                com.baidu.location.h.e.j = str3;
                break;
            case "loc":
                com.baidu.location.h.e.e = str3;
                break;
            case "cfgs":
                com.baidu.location.h.e.f = str3;
                break;
            case "indoor_rects":
                com.baidu.location.h.e.i = str3;
                break;
        }
    }

    private boolean a(String str) {
        JSONArray jSONArrayOptJSONArray;
        Log.d("UrlConfigManger", "parseConfig");
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("url_config") || (jSONArrayOptJSONArray = jSONObject.optJSONArray("url_config")) == null) {
                return true;
            }
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                a(jSONObjectOptJSONObject.optString("type"), jSONObjectOptJSONObject.optString("owner"), jSONObjectOptJSONObject.optString("url"));
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void a(Context context) throws IOException {
        Log.d("UrlConfigManger", "updateUrl");
        if (context == null) {
            return;
        }
        try {
            String str = context.getExternalFilesDir(Environment.DIRECTORY_MOVIES) + File.separator + "baiduLocDev" + File.separator + "loc_local_config";
            StringBuilder sb = new StringBuilder();
            try {
                if (!new File(str).exists()) {
                    Log.d("baidu_location_dev", "loc_local_config not exit...");
                    return;
                }
                FileInputStream fileInputStream = new FileInputStream(str);
                byte[] bArr = new byte[1024];
                while (true) {
                    int i = fileInputStream.read(bArr);
                    if (i <= 0) {
                        fileInputStream.close();
                        a(sb.toString());
                        return;
                    }
                    sb.append(new String(bArr, 0, i));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}
