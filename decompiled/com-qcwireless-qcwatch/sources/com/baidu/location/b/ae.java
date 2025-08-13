package com.baidu.location.b;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* loaded from: classes.dex */
public class ae {
    private static final Lock c = new ReentrantLock();
    private OkHttpClient a;
    private String b = null;

    public interface a {
        void a(int i, String str);

        void a(int i, String str, byte[] bArr);
    }

    private static class b {
        private static final ae a = new ae();
    }

    ae() {
        b();
    }

    public static ae a() {
        return b.a;
    }

    private RequestBody a(Map<String, Object> map) {
        c.lock();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("&");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        String string = sb.toString();
        c.unlock();
        return RequestBody.create(mediaType, string);
    }

    private synchronized void b() {
        if (this.a == null) {
            try {
                this.a = new OkHttpClient.Builder().connectTimeout(12000L, TimeUnit.MILLISECONDS).readTimeout(12000L, TimeUnit.MILLISECONDS).writeTimeout(12000L, TimeUnit.MILLISECONDS).dns(t.a().c()).build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Request.Builder c() {
        Request.Builder builder = new Request.Builder();
        builder.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        if (com.baidu.location.h.s.aw != null) {
            builder.addHeader("bd-loc-android", com.baidu.location.h.s.aw);
        }
        return builder;
    }

    public void a(String str) {
        this.b = str;
    }

    public void a(Map<String, Object> map, String str, a aVar) {
        StringBuilder sb;
        String message;
        int iCode;
        String strMessage;
        try {
            RequestBody requestBodyA = a(map);
            Request.Builder builderC = c();
            String str2 = this.b;
            if (str2 != null) {
                builderC.addHeader("alwd", str2);
            }
            Response responseExecute = this.a.newCall(builderC.url(str).post(requestBodyA).build()).execute();
            if (!responseExecute.isSuccessful()) {
                iCode = responseExecute.code();
                strMessage = responseExecute.message();
            } else if (responseExecute.body() != null) {
                aVar.a(200, responseExecute.body().string(), new byte[1]);
                return;
            } else {
                iCode = 400;
                strMessage = responseExecute.message();
            }
            aVar.a(iCode, strMessage);
        } catch (IOException e) {
            e.printStackTrace();
            if (aVar != null) {
                sb = new StringBuilder();
                sb.append("e=");
                message = e.getMessage();
                sb.append(message);
                aVar.a(-100, sb.toString());
            }
        } catch (Exception e2) {
            if (aVar != null) {
                sb = new StringBuilder();
                sb.append("e=");
                message = e2.getMessage();
                sb.append(message);
                aVar.a(-100, sb.toString());
            }
        }
    }
}
