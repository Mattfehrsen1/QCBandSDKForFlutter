package com.baidu.bdhttpdns;

import androidx.core.app.NotificationCompat;
import com.baidu.location.LocationConst;
import com.bumptech.glide.load.Key;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class i {
    private static volatile i a = null;
    private static boolean b = true;
    private static c g;
    private String d;
    private String f;
    private int q;
    private int r;
    private String c = "180.76.76.200";
    private String e = "[240c:4006::6666]";
    private boolean h = true;
    private long i = 0;
    private final Object l = new Object();
    private ArrayList<String> m = new ArrayList<>();
    private String n = "";
    private String o = "";
    private boolean p = false;
    private int s = 10;
    private final Object k = new Object();
    private final HashSet<String> j = new HashSet<>();

    interface a {
        void a(int i, d dVar, Map<String, e> map, String str);
    }

    private class b implements Runnable {
        private String c;
        private d d;
        private a e;
        public boolean a = false;
        private boolean f = false;

        public b(String str, d dVar, a aVar) {
            this.c = str;
            this.d = dVar;
            this.e = aVar;
        }

        private String a(String str, int i) {
            if (str == null || i >= 3) {
                return null;
            }
            this.f = true;
            l.a("Using IDCServerIP(%s)", str);
            return str;
        }

        private String a(String str, d dVar) {
            String strA;
            String str2;
            long jCurrentTimeMillis = (System.currentTimeMillis() / 1000) + 300 + i.this.i;
            String strA2 = i.this.a(str, jCurrentTimeMillis);
            if (strA2 == null) {
                return null;
            }
            if (BDNetworkStateChangeReceiver.isIPv4Reachable()) {
                strA = a(i.this.d, i.this.q);
                if (strA == null) {
                    strA = i.this.c;
                    this.f = false;
                    l.a("Using BGPServerIp(%s)", i.this.c);
                }
                str2 = BDNetworkStateChangeReceiver.isIPv6Reachable() ? "dual_stack" : "ipv4";
            } else {
                if (!BDNetworkStateChangeReceiver.isIPv6Reachable()) {
                    return null;
                }
                strA = a(i.this.f, i.this.r);
                if (strA == null) {
                    strA = i.this.e;
                    this.f = false;
                    l.a("Using BGPServerIp(%s)", i.this.e);
                }
                str2 = "ipv6";
            }
            String str3 = dVar.equals(d.TAG_OF_HOSTS) ? String.format("%s/v4/resolve?account_id=%s&tag=%s&sign=%s&t=%d&sdk_ver=%s&os_type=%s&alt_server_ip=true&type=%s", strA, i.this.n, str, strA2, Long.valueOf(jCurrentTimeMillis), "1.3", "android", str2) : String.format("%s/v4/resolve?account_id=%s&dn=%s&sign=%s&t=%d&sdk_ver=%s&os_type=%s&alt_server_ip=true&type=%s", strA, i.this.n, str, strA2, Long.valueOf(jCurrentTimeMillis), "1.3", "android", str2);
            return i.this.h ? String.format("https://%s", str3) : String.format("http://%s", str3);
        }

        private void a() {
            String strA = a(this.c, this.d);
            l.a("Request url is :%s", strA);
            if (strA != null) {
                a(strA);
            } else {
                this.e.a(-1, this.d, null, this.c);
                l.a("Httpdns request failed for  %s(%s), get url error", this.d.toString(), this.c);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:63:0x0179, code lost:
        
            if (com.baidu.bdhttpdns.BDNetworkStateChangeReceiver.isIPv6Reachable() != false) goto L64;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:117:0x0285  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x015b  */
        /* JADX WARN: Removed duplicated region for block: B:61:0x016e A[PHI: r0 r2 r7
          0x016e: PHI (r0v11 java.util.Map<java.lang.String, com.baidu.bdhttpdns.i$e>) = 
          (r0v0 java.util.Map<java.lang.String, com.baidu.bdhttpdns.i$e>)
          (r0v0 java.util.Map<java.lang.String, com.baidu.bdhttpdns.i$e>)
          (r0v0 java.util.Map<java.lang.String, com.baidu.bdhttpdns.i$e>)
          (r0v12 java.util.Map<java.lang.String, com.baidu.bdhttpdns.i$e>)
         binds: [B:81:0x01d9, B:96:0x0227, B:111:0x0277, B:60:0x016c] A[DONT_GENERATE, DONT_INLINE]
          0x016e: PHI (r2v11 java.net.HttpURLConnection) = 
          (r2v21 java.net.HttpURLConnection)
          (r2v22 java.net.HttpURLConnection)
          (r2v23 java.net.HttpURLConnection)
          (r2v24 java.net.HttpURLConnection)
         binds: [B:81:0x01d9, B:96:0x0227, B:111:0x0277, B:60:0x016c] A[DONT_GENERATE, DONT_INLINE]
          0x016e: PHI (r7v9 java.lang.Boolean) = (r7v36 java.lang.Boolean), (r7v37 java.lang.Boolean), (r7v38 java.lang.Boolean), (r7v39 java.lang.Boolean) binds: [B:81:0x01d9, B:96:0x0227, B:111:0x0277, B:60:0x016c] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:62:0x0175  */
        /* JADX WARN: Removed duplicated region for block: B:64:0x017b A[PHI: r0 r2 r7
          0x017b: PHI (r0v9 java.util.Map<java.lang.String, com.baidu.bdhttpdns.i$e>) = 
          (r0v0 java.util.Map<java.lang.String, com.baidu.bdhttpdns.i$e>)
          (r0v0 java.util.Map<java.lang.String, com.baidu.bdhttpdns.i$e>)
          (r0v0 java.util.Map<java.lang.String, com.baidu.bdhttpdns.i$e>)
          (r0v12 java.util.Map<java.lang.String, com.baidu.bdhttpdns.i$e>)
         binds: [B:84:0x01e0, B:99:0x022f, B:114:0x027f, B:63:0x0179] A[DONT_GENERATE, DONT_INLINE]
          0x017b: PHI (r2v9 java.net.HttpURLConnection) = 
          (r2v36 java.net.HttpURLConnection)
          (r2v37 java.net.HttpURLConnection)
          (r2v38 java.net.HttpURLConnection)
          (r2v39 java.net.HttpURLConnection)
         binds: [B:84:0x01e0, B:99:0x022f, B:114:0x027f, B:63:0x0179] A[DONT_GENERATE, DONT_INLINE]
          0x017b: PHI (r7v7 java.lang.Boolean) = (r7v51 java.lang.Boolean), (r7v52 java.lang.Boolean), (r7v53 java.lang.Boolean), (r7v54 java.lang.Boolean) binds: [B:84:0x01e0, B:99:0x022f, B:114:0x027f, B:63:0x0179] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Type inference failed for: r10v3, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Boolean, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v10 */
        /* JADX WARN: Type inference failed for: r2v17, types: [java.lang.Boolean] */
        /* JADX WARN: Type inference failed for: r2v19 */
        /* JADX WARN: Type inference failed for: r2v20 */
        /* JADX WARN: Type inference failed for: r2v25 */
        /* JADX WARN: Type inference failed for: r2v26 */
        /* JADX WARN: Type inference failed for: r2v27 */
        /* JADX WARN: Type inference failed for: r2v28 */
        /* JADX WARN: Type inference failed for: r2v29 */
        /* JADX WARN: Type inference failed for: r2v30 */
        /* JADX WARN: Type inference failed for: r2v31 */
        /* JADX WARN: Type inference failed for: r2v32 */
        /* JADX WARN: Type inference failed for: r2v33 */
        /* JADX WARN: Type inference failed for: r2v34 */
        /* JADX WARN: Type inference failed for: r2v35 */
        /* JADX WARN: Type inference failed for: r2v4, types: [java.net.HttpURLConnection] */
        /* JADX WARN: Type inference failed for: r7v25, types: [com.baidu.bdhttpdns.i$a] */
        /* JADX WARN: Type inference failed for: r7v28, types: [com.baidu.bdhttpdns.i] */
        /* JADX WARN: Type inference failed for: r7v55 */
        /* JADX WARN: Type inference failed for: r7v56 */
        /* JADX WARN: Type inference failed for: r9v9, types: [java.lang.Object] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void a(java.lang.String r14) {
            /*
                Method dump skipped, instructions count: 712
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.bdhttpdns.i.b.a(java.lang.String):void");
        }

        @Override // java.lang.Runnable
        public void run() {
            a();
            if (this.a) {
                l.a("Retry for %s(%s).", this.d.toString(), this.c);
                a();
            }
            synchronized (i.this.k) {
                if (!this.d.equals(d.TAG_OF_HOSTS)) {
                    for (String str : this.c.split(",")) {
                        i.this.j.remove(str);
                    }
                }
            }
            synchronized (i.this.l) {
                if (this.d.equals(d.DNLIST_HOSTS)) {
                    for (String str2 : this.c.split(",")) {
                        i.this.m.remove(str2);
                    }
                }
            }
        }
    }

    private class c implements HostnameVerifier {
        private c() {
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return HttpsURLConnection.getDefaultHostnameVerifier().verify("httpdns.baidubce.com", sSLSession);
        }
    }

    public enum d {
        DNLIST_HOSTS,
        TAG_OF_HOSTS
    }

    public class e {
        private final ArrayList<String> b;
        private final ArrayList<String> c;
        private final long d;

        public e(ArrayList<String> arrayList, ArrayList<String> arrayList2, long j) {
            this.b = arrayList;
            this.c = arrayList2;
            this.d = j;
        }

        public ArrayList<String> a() {
            return this.b;
        }

        public ArrayList<String> b() {
            return this.c;
        }

        public long c() {
            return this.d;
        }
    }

    private i() {
        g = new c();
    }

    private long a(String str, JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        long j;
        if (jSONObject != null) {
            try {
                j = jSONObject.getLong("ttl");
            } catch (JSONException e2) {
                e2.printStackTrace();
                l.a("Httpdns request failed, host(%s), response has no ttl, will use defaults ttl(60s)", str);
                return -1L;
            }
        } else {
            j = -1;
        }
        long j2 = jSONObject2 != null ? jSONObject2.getLong("ttl") : -1L;
        if (j > 0 && j2 > 0) {
            return j < j2 ? j : j2;
        }
        if (j > 0) {
            return j;
        }
        if (j2 > 0) {
            return j2;
        }
        return -1L;
    }

    static i a() {
        if (a == null) {
            synchronized (i.class) {
                if (a == null) {
                    a = new i();
                }
            }
        }
        return a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(InputStream inputStream, HttpURLConnection httpURLConnection) throws IOException {
        String contentEncoding = httpURLConnection.getContentEncoding();
        if (contentEncoding != null) {
            try {
                if (contentEncoding.contains("gzip")) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    int contentLength = httpURLConnection.getContentLength();
                    if (contentLength <= 0) {
                        contentLength = 1024;
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int i = inputStream.read(bArr);
                            if (-1 == i) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, i);
                        }
                    } else {
                        byte[] bArr2 = new byte[contentLength];
                        inputStream.read(bArr2);
                        byteArrayOutputStream.write(bArr2, 0, contentLength);
                    }
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                    return a(byteArrayOutputStream.toByteArray(), contentLength);
                }
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                return null;
            } catch (IOException e3) {
                e3.printStackTrace();
                return null;
            }
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Key.STRING_CHARSET_NAME));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                bufferedReader.close();
                return sb.toString();
            }
            sb.append(line);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str, long j) {
        return com.baidu.bdhttpdns.e.e(String.format("%s-%s-%d", str, e(), Long.valueOf(j)));
    }

    private String a(byte[] bArr, int i) throws IOException {
        byte[] bArr2 = new byte[i];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(bArr), i);
            do {
                int i2 = gZIPInputStream.read(bArr2, 0, i);
                byteArrayOutputStream.write(bArr2, 0, i2);
                if (i2 == -1) {
                    break;
                }
            } while (!b(byteArrayOutputStream.toString()));
            gZIPInputStream.close();
            return byteArrayOutputStream.toString();
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private ArrayList<String> a(String str, String str2, JSONObject jSONObject) {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("ip");
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() == 0) {
            l.a("Httpdns request warning, host(%s), response has no ip field in %s", str2, str);
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            String strOptString = jSONArrayOptJSONArray.optString(i);
            if (strOptString == null || strOptString.isEmpty()) {
                l.a("Httpdns request warning, host(%s), response of data get ip error in %s", str2, str);
            } else if (com.baidu.bdhttpdns.e.a(strOptString) || com.baidu.bdhttpdns.e.b(strOptString)) {
                arrayList.add(strOptString);
            } else {
                l.a("Httpdns request warning, host(%s), response of data get invalid ip(%s) in %s", str2, strOptString, str);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(Boolean bool) {
        if (bool.booleanValue()) {
            if (BDNetworkStateChangeReceiver.isIPv4Reachable()) {
                int i = this.q + 1;
                this.q = i;
                l.a("requestV4IDCFailNum: %s", Integer.valueOf(i));
            } else if (BDNetworkStateChangeReceiver.isIPv6Reachable()) {
                int i2 = this.r + 1;
                this.r = i2;
                l.a("requestV6IDCFailNum: %s", Integer.valueOf(i2));
            }
        }
    }

    private String b(String str, String str2, JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            return jSONObject.getString(NotificationCompat.CATEGORY_MESSAGE);
        } catch (JSONException e2) {
            e2.printStackTrace();
            l.a("Httpdns request failed, host(%s), response has no msg in %s ", str2, str);
            return null;
        }
    }

    public static boolean b(String str) {
        try {
            new JSONObject(str);
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }

    Map a(String str, String str2) throws JSONException {
        ArrayList<String> arrayListA;
        ArrayList<String> arrayListA2;
        HashMap map = new HashMap();
        boolean z = false;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("serverip")) {
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("serverip");
                JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("ipv4");
                if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                    this.d = jSONArrayOptJSONArray.optString(0);
                }
                JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("ipv6");
                if (jSONArrayOptJSONArray2 != null && jSONArrayOptJSONArray2.length() > 0) {
                    this.f = "[" + jSONArrayOptJSONArray2.optString(0) + "]";
                }
            }
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("data");
            if (jSONObjectOptJSONObject2 == null) {
                l.a("Httpdns request failed, hostsOrTag(%s), response has empty data", str2);
                return null;
            }
            Iterator<String> itKeys = jSONObjectOptJSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                JSONObject jSONObjectOptJSONObject3 = jSONObjectOptJSONObject2.optJSONObject(next);
                JSONObject jSONObjectOptJSONObject4 = jSONObjectOptJSONObject3.optJSONObject("ipv4");
                JSONObject jSONObjectOptJSONObject5 = jSONObjectOptJSONObject3.optJSONObject("ipv6");
                long jA = a(next, jSONObjectOptJSONObject4, jSONObjectOptJSONObject5);
                if (jA < 0) {
                    map.put(next, z);
                } else {
                    String strB = b("ipv4Obj", next, jSONObjectOptJSONObject4);
                    String strB2 = b("ipv6Obj", next, jSONObjectOptJSONObject5);
                    if (strB == null || strB.isEmpty()) {
                        l.a("Host(%s) ipv4Msg(%s), will deprecated the ipv4List result", next, strB);
                        arrayListA = null;
                    } else {
                        arrayListA = a("ipv4Obj", next, jSONObjectOptJSONObject4);
                    }
                    if (strB2 == null || strB2.isEmpty()) {
                        l.a("Host(%s) ipv6Msg(%s), will deprecated the ipv6List result", next, strB2);
                        arrayListA2 = null;
                    } else {
                        arrayListA2 = a("ipv6Obj", next, jSONObjectOptJSONObject5);
                    }
                    if ((arrayListA == null || arrayListA.isEmpty()) && (arrayListA2 == null || arrayListA2.isEmpty())) {
                        l.a("Httpdns request failed, host(%s), response has no valid ip", next);
                        map.put(next, null);
                    } else {
                        map.put(next, new e(arrayListA, arrayListA2, jA));
                    }
                    z = false;
                }
            }
            return map;
        } catch (JSONException e2) {
            e2.printStackTrace();
            l.a("Httpdns request failed, hostsOrTag(%s), response parse data json error", str2);
            return null;
        }
    }

    Map a(String str, String str2, d dVar) {
        HashMap map = new HashMap();
        map.put("isMsgOK", false);
        map.put("isSignExpired", false);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString(NotificationCompat.CATEGORY_MESSAGE);
            if (strOptString == null || strOptString.isEmpty()) {
                l.a("Httpdns request failed for %s(%s), response lack of msg", dVar.toString(), str2);
                return map;
            }
            if (!"SignatureExpired".equals(strOptString)) {
                if ("ok".equals(strOptString)) {
                    map.put("isMsgOK", true);
                    return map;
                }
                l.a("Httpdns request failed for %s(%s), response msg(%s) is not ok", dVar.toString(), str2, strOptString);
                return map;
            }
            int iOptInt = jSONObject.optInt(LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP);
            if (iOptInt == 0) {
                l.a("Httpdns request failed for %s(%s), response get invalid timestamp", dVar.toString(), str2);
            } else {
                this.i = iOptInt - (System.currentTimeMillis() / 1000);
                map.put("isSignExpired", true);
            }
            return map;
        } catch (JSONException e2) {
            e2.printStackTrace();
            l.a("Httpdns request failed for %s(%s), response parse json error", dVar.toString(), str2);
            return map;
        }
    }

    public void a(String str) {
        synchronized (this.l) {
            if (!this.m.contains(str)) {
                this.m.add(str);
            }
        }
    }

    void a(String str, d dVar, a aVar) {
        if (str == null || str.isEmpty()) {
            return;
        }
        synchronized (this.k) {
            if (dVar.equals(d.DNLIST_HOSTS)) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(Arrays.asList(str.split(",")));
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    String str2 = (String) it.next();
                    if (this.j.contains(str2)) {
                        l.a("Httpdns request request for host(%s) is in processing，will exclude it.", str2);
                        it.remove();
                    } else {
                        this.j.add(str2);
                    }
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < arrayList.size(); i++) {
                    sb.append((String) arrayList.get(i));
                    sb.append(",");
                }
                str = sb.toString().replaceAll("^,*|,*$", "");
            }
            if (str != null && !str.isEmpty()) {
                try {
                    m.a().b().execute(new b(str, dVar, aVar));
                } catch (RejectedExecutionException e2) {
                    e2.printStackTrace();
                    l.a("Httpdns request failed, host(%s), async tasks has exceed the maximum thread limit.", str);
                }
            }
        }
    }

    public void a(ArrayList<String> arrayList, a aVar) {
        ArrayList arrayList2 = new ArrayList(new HashSet(arrayList));
        int i = 0;
        int i2 = 0;
        while (i < arrayList2.size()) {
            String str = "";
            int i3 = 0;
            while (true) {
                int i4 = this.s;
                if (i3 >= i4 || (i = i3 + (i4 * i2)) >= arrayList2.size()) {
                    break;
                }
                str = str + ((String) arrayList2.get(i)) + ",";
                i3++;
            }
            i2++;
            if (str != null && !str.isEmpty()) {
                String strSubstring = str.substring(0, str.length() - 1);
                l.a("Hosts for httpdns request is (%s) ", strSubstring);
                a(strSubstring, d.DNLIST_HOSTS, aVar);
            }
        }
    }

    void a(boolean z) {
        this.h = z;
    }

    public void b() {
        this.q = 0;
        this.r = 0;
        this.d = null;
        this.f = null;
    }

    public void b(boolean z) {
        this.p = z;
    }

    public ArrayList c() {
        return this.m;
    }

    void c(String str) {
        this.n = str;
    }

    public int d() {
        return this.s;
    }

    void d(String str) {
        String strF = com.baidu.bdhttpdns.e.f(str);
        this.o = strF;
        if (strF == null) {
            this.o = str;
            b = false;
        }
    }

    String e() {
        return !b ? this.o : com.baidu.bdhttpdns.e.g(this.o);
    }

    boolean f() {
        return this.p;
    }
}
