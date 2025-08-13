package com.baidu.lbsapi.auth;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import com.bumptech.glide.load.Key;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes.dex */
public class i {
    private Context a;
    private String e;
    private String g;
    private String h;
    private String b = null;
    private HashMap<String, String> c = null;
    private String d = null;
    private int f = -1;

    public i(Context context) {
        this.a = context;
    }

    private String a(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return null;
            }
            if (Build.VERSION.SDK_INT >= 29) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (networkCapabilities != null) {
                    return networkCapabilities.hasTransport(1) ? "WIFI" : networkCapabilities.hasTransport(0) ? "CELLULAR" : networkCapabilities.hasTransport(3) ? "ETHERNET" : networkCapabilities.hasTransport(6) ? "LoWPAN" : networkCapabilities.hasTransport(4) ? "VPN" : networkCapabilities.hasTransport(5) ? "WifiAware" : "wifi";
                }
                return "wifi";
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                String extraInfo = activeNetworkInfo.getExtraInfo();
                return extraInfo != null ? (extraInfo.trim().toLowerCase().equals("cmwap") || extraInfo.trim().toLowerCase().equals("uniwap") || extraInfo.trim().toLowerCase().equals("3gwap") || extraInfo.trim().toLowerCase().equals("ctwap")) ? extraInfo.trim().toLowerCase().equals("ctwap") ? "ctwap" : "cmwap" : "wifi" : "wifi";
            }
            return null;
        } catch (Exception e) {
            if (b.a) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private static String a(HashMap<String, String> map) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (z) {
                z = false;
            } else {
                sb.append("&");
            }
            sb.append(URLEncoder.encode(entry.getKey(), Key.STRING_CHARSET_NAME));
            sb.append("=");
            sb.append(URLEncoder.encode(entry.getValue(), Key.STRING_CHARSET_NAME));
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0172 A[Catch: all -> 0x0135, TryCatch #20 {all -> 0x0135, blocks: (B:7:0x0032, B:88:0x013a, B:90:0x013e, B:91:0x0141, B:101:0x016e, B:103:0x0172, B:104:0x0175, B:114:0x019c, B:116:0x01a0, B:117:0x01a3), top: B:152:0x0032 }] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x01a0 A[Catch: all -> 0x0135, TryCatch #20 {all -> 0x0135, blocks: (B:7:0x0032, B:88:0x013a, B:90:0x013e, B:91:0x0141, B:101:0x016e, B:103:0x0172, B:104:0x0175, B:114:0x019c, B:116:0x01a0, B:117:0x01a3), top: B:152:0x0032 }] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01cd A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0190 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01c1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x015f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x00fe A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b7 A[Catch: all -> 0x010d, TryCatch #12 {all -> 0x010d, blocks: (B:45:0x00b3, B:47:0x00b7, B:48:0x00d3), top: B:146:0x00b3 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00f8 A[Catch: Exception -> 0x011e, IOException -> 0x0121, MalformedURLException -> 0x0124, all -> 0x0128, TRY_LEAVE, TryCatch #5 {all -> 0x0128, blocks: (B:8:0x0036, B:66:0x0112, B:68:0x011a, B:69:0x011d, B:51:0x00f0, B:53:0x00f8, B:31:0x0098, B:33:0x00a0), top: B:145:0x0036 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0110 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x011a A[Catch: Exception -> 0x011e, IOException -> 0x0121, MalformedURLException -> 0x0124, all -> 0x0128, TryCatch #5 {all -> 0x0128, blocks: (B:8:0x0036, B:66:0x0112, B:68:0x011a, B:69:0x011d, B:51:0x00f0, B:53:0x00f8, B:31:0x0098, B:33:0x00a0), top: B:145:0x0036 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x013e A[Catch: all -> 0x0135, TryCatch #20 {all -> 0x0135, blocks: (B:7:0x0032, B:88:0x013a, B:90:0x013e, B:91:0x0141, B:101:0x016e, B:103:0x0172, B:104:0x0175, B:114:0x019c, B:116:0x01a0, B:117:0x01a3), top: B:152:0x0032 }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0168 A[PHI: r10 r14
      0x0168: PHI (r10v5 int) = (r10v0 int), (r10v1 int), (r10v2 int) binds: [B:97:0x0166, B:110:0x0197, B:123:0x01c8] A[DONT_GENERATE, DONT_INLINE]
      0x0168: PHI (r14v18 'e' java.io.IOException) = (r14v12 'e' java.io.IOException), (r14v17 'e' java.io.IOException), (r14v23 'e' java.io.IOException) binds: [B:97:0x0166, B:110:0x0197, B:123:0x01c8] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(javax.net.ssl.HttpsURLConnection r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 565
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.lbsapi.auth.i.a(javax.net.ssl.HttpsURLConnection):void");
    }

    private HashMap<String, String> b(HashMap<String, String> map) {
        HashMap<String, String> map2 = new HashMap<>();
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String string = it.next().toString();
            map2.put(string, map.get(string));
        }
        return map2;
    }

    private HttpsURLConnection b() throws IOException {
        String str;
        URLConnection uRLConnectionOpenConnection;
        try {
            URL url = new URL(this.b);
            b.a("https URL: " + this.b);
            String strA = a(this.a);
            if (strA != null && !strA.equals("")) {
                if (TextUtils.isEmpty(this.e) || this.f == -1) {
                    b.a("checkNetwork = " + strA);
                    uRLConnectionOpenConnection = "cmwap".equals(strA) ? url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.172", 80))) : "ctwap".equals(strA) ? url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.200", 80))) : url.openConnection();
                } else {
                    b.a("Proxy mProxyHost: = " + this.e + " mProxyPort: " + this.f);
                    Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(this.e, this.f));
                    Authenticator.setDefault(new j(this));
                    uRLConnectionOpenConnection = url.openConnection(proxy);
                }
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) uRLConnectionOpenConnection;
                httpsURLConnection.setHostnameVerifier(new k(this));
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setConnectTimeout(50000);
                httpsURLConnection.setReadTimeout(50000);
                return httpsURLConnection;
            }
            b.c("Current network is not available.");
            this.d = ErrorMessage.a(-10, "Current network is not available.");
            return null;
        } catch (MalformedURLException e) {
            if (b.a) {
                e.printStackTrace();
                b.a(e.getMessage());
            }
            str = "Auth server could not be parsed as a URL.";
            this.d = ErrorMessage.a(-11, str);
            return null;
        } catch (Exception e2) {
            if (b.a) {
                e2.printStackTrace();
                b.a(e2.getMessage());
            }
            str = "Init httpsurlconnection failed.";
            this.d = ErrorMessage.a(-11, str);
            return null;
        }
    }

    protected String a(HashMap<String, String> map, String str, int i, String str2, String str3) throws Throwable {
        HashMap<String, String> mapB = b(map);
        this.c = mapB;
        this.b = mapB.get("url");
        this.e = str;
        this.f = i;
        this.g = str2;
        this.h = str3;
        HttpsURLConnection httpsURLConnectionB = b();
        if (httpsURLConnectionB == null) {
            b.c("syncConnect failed,httpsURLConnection is null");
        } else {
            a(httpsURLConnectionB);
        }
        return this.d;
    }

    protected boolean a() {
        b.a("checkNetwork start");
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.a.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 29) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                return networkCapabilities != null && networkCapabilities.hasCapability(12) && networkCapabilities.hasCapability(16);
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return false;
            }
            b.a("checkNetwork end");
            return true;
        } catch (Exception e) {
            if (b.a) {
                e.printStackTrace();
            }
            return false;
        }
    }
}
