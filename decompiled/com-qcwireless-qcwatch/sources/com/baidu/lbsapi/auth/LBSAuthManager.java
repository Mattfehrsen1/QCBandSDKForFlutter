package com.baidu.lbsapi.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;
import javax.crypto.NoSuchPaddingException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class LBSAuthManager {
    public static final int CODE_AUTHENTICATE_SUCC = 0;
    public static final int CODE_AUTHENTICATING = 602;
    public static final int CODE_INNER_ERROR = -1;
    public static final int CODE_KEY_NOT_EXIST = 101;
    public static final int CODE_NETWORK_FAILED = -11;
    public static final int CODE_NETWORK_INVALID = -10;
    public static final int CODE_UNAUTHENTICATE = 601;
    public static final String VERSION = "1.0.28";
    private static Context a = null;
    private static String b = null;
    private static int c = -1;
    private static String d;
    private static String e;
    private static p h;
    private static int i;
    private static LBSAuthManager k;
    private byte[] m;
    private static Hashtable<String, LBSAuthManagerListener> j = new Hashtable<>();
    private static String n = "";
    private static String o = "";
    private static String p = "";
    private static boolean q = false;
    private static String r = null;
    private e f = null;
    private g g = null;
    private boolean l = false;
    private final Handler s = new l(this, Looper.getMainLooper());

    private LBSAuthManager(Context context) {
        a = context;
        p pVar = h;
        if (pVar != null && !pVar.isAlive()) {
            h = null;
        }
        b.b("BaiduApiAuth SDK Version:1.0.28");
        h();
    }

    private int a(String str) throws JSONException {
        JSONObject jSONObject;
        int i2 = -1;
        try {
            jSONObject = new JSONObject(str);
            if (!jSONObject.has(NotificationCompat.CATEGORY_STATUS)) {
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, -1);
            }
            i2 = jSONObject.getInt(NotificationCompat.CATEGORY_STATUS);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (jSONObject.has("current") && i2 == 0) {
            long j2 = jSONObject.getLong("current");
            long jCurrentTimeMillis = System.currentTimeMillis();
            if ((jCurrentTimeMillis - j2) / 3600000.0d >= 24.0d) {
                i2 = 601;
            } else if (this.l) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                if (!simpleDateFormat.format(Long.valueOf(jCurrentTimeMillis)).equals(simpleDateFormat.format(Long.valueOf(j2)))) {
                    i2 = 601;
                }
            }
            return i2;
        }
        if (jSONObject.has("current") && i2 == 602) {
            if ((System.currentTimeMillis() - jSONObject.getLong("current")) / 1000 > 180.0d) {
                return 601;
            }
        }
        return i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0035 A[PHI: r0 r6
      0x0035: PHI (r0v6 java.lang.String) = (r0v0 java.lang.String), (r0v0 java.lang.String), (r0v12 java.lang.String) binds: [B:36:0x0074, B:44:0x0084, B:7:0x002f] A[DONT_GENERATE, DONT_INLINE]
      0x0035: PHI (r6v10 java.io.FileInputStream) = (r6v8 java.io.FileInputStream), (r6v9 java.io.FileInputStream), (r6v13 java.io.FileInputStream) binds: [B:36:0x0074, B:44:0x0084, B:7:0x002f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.io.BufferedReader] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a(int r6) throws java.lang.Throwable {
        /*
            r5 = this;
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L67 java.io.FileNotFoundException -> L77
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L67 java.io.FileNotFoundException -> L77
            r2.<init>()     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L67 java.io.FileNotFoundException -> L77
            java.lang.String r3 = "/proc/"
            r2.append(r3)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L67 java.io.FileNotFoundException -> L77
            r2.append(r6)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L67 java.io.FileNotFoundException -> L77
            java.lang.String r6 = "/cmdline"
            r2.append(r6)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L67 java.io.FileNotFoundException -> L77
            java.lang.String r6 = r2.toString()     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L67 java.io.FileNotFoundException -> L77
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L67 java.io.FileNotFoundException -> L77
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L67 java.io.FileNotFoundException -> L77
            r6.<init>(r1)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L67 java.io.FileNotFoundException -> L77
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4e java.io.FileNotFoundException -> L50
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4e java.io.FileNotFoundException -> L50
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45 java.io.FileNotFoundException -> L47
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45 java.io.FileNotFoundException -> L47
            java.lang.String r0 = r2.readLine()     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c java.io.FileNotFoundException -> L3e
            r2.close()
            r1.close()
        L35:
            r6.close()
            goto L87
        L3a:
            r0 = move-exception
            goto L57
        L3c:
            goto L6a
        L3e:
            goto L7a
        L40:
            r2 = move-exception
            r4 = r2
            r2 = r0
            r0 = r4
            goto L57
        L45:
            r2 = r0
            goto L6a
        L47:
            r2 = r0
            goto L7a
        L49:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
            goto L57
        L4e:
            r1 = r0
            goto L69
        L50:
            r1 = r0
            goto L79
        L52:
            r6 = move-exception
            r1 = r0
            r2 = r1
            r0 = r6
            r6 = r2
        L57:
            if (r2 == 0) goto L5c
            r2.close()
        L5c:
            if (r1 == 0) goto L61
            r1.close()
        L61:
            if (r6 == 0) goto L66
            r6.close()
        L66:
            throw r0
        L67:
            r6 = r0
            r1 = r6
        L69:
            r2 = r1
        L6a:
            if (r2 == 0) goto L6f
            r2.close()
        L6f:
            if (r1 == 0) goto L74
            r1.close()
        L74:
            if (r6 == 0) goto L87
            goto L35
        L77:
            r6 = r0
            r1 = r6
        L79:
            r2 = r1
        L7a:
            if (r2 == 0) goto L7f
            r2.close()
        L7f:
            if (r1 == 0) goto L84
            r1.close()
        L84:
            if (r6 == 0) goto L87
            goto L35
        L87:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.lbsapi.auth.LBSAuthManager.a(int):java.lang.String");
    }

    private String a(Context context) throws Throwable {
        String strA;
        try {
            strA = a(Process.myPid());
        } catch (IOException unused) {
            strA = null;
        }
        return strA != null ? strA : a.getPackageName();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0048 A[Catch: NameNotFoundException -> 0x005b, TryCatch #1 {NameNotFoundException -> 0x005b, blocks: (B:14:0x0042, B:16:0x0048, B:18:0x0052), top: B:29:0x0042 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a(android.content.Context r6, java.lang.String r7) throws android.content.pm.PackageManager.NameNotFoundException {
        /*
            r5 = this;
            java.lang.String r0 = "无法在AndroidManifest.xml中获取com.baidu.android.lbs.API_KEY的值"
            java.lang.String r1 = ""
            java.lang.String r2 = com.baidu.lbsapi.auth.LBSAuthManager.n
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L10
            java.lang.String r6 = com.baidu.lbsapi.auth.LBSAuthManager.n
            return r6
        L10:
            java.lang.String r2 = r6.getPackageName()
            r3 = 101(0x65, float:1.42E-43)
            android.content.pm.PackageManager r6 = r6.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L5d
            r4 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r6 = r6.getApplicationInfo(r2, r4)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L5d
            android.os.Bundle r2 = r6.metaData     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L5d
            if (r2 != 0) goto L38
            java.util.Hashtable<java.lang.String, com.baidu.lbsapi.auth.LBSAuthManagerListener> r6 = com.baidu.lbsapi.auth.LBSAuthManager.j     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L5d
            java.lang.Object r6 = r6.get(r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L5d
            com.baidu.lbsapi.auth.LBSAuthManagerListener r6 = (com.baidu.lbsapi.auth.LBSAuthManagerListener) r6     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L5d
            if (r6 == 0) goto L6f
            java.lang.String r2 = "AndroidManifest.xml的application中没有meta-data标签"
            java.lang.String r2 = com.baidu.lbsapi.auth.ErrorMessage.a(r3, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L5d
            r6.onAuthResult(r3, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L5d
            goto L6f
        L38:
            android.os.Bundle r6 = r6.metaData     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L5d
            java.lang.String r2 = "com.baidu.lbsapi.API_KEY"
            java.lang.String r6 = r6.getString(r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L5d
            if (r6 == 0) goto L48
            boolean r1 = r6.equals(r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L5b
            if (r1 == 0) goto L59
        L48:
            java.util.Hashtable<java.lang.String, com.baidu.lbsapi.auth.LBSAuthManagerListener> r1 = com.baidu.lbsapi.auth.LBSAuthManager.j     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L5b
            java.lang.Object r1 = r1.get(r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L5b
            com.baidu.lbsapi.auth.LBSAuthManagerListener r1 = (com.baidu.lbsapi.auth.LBSAuthManagerListener) r1     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L5b
            if (r1 == 0) goto L59
            java.lang.String r2 = com.baidu.lbsapi.auth.ErrorMessage.a(r3, r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L5b
            r1.onAuthResult(r3, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L5b
        L59:
            r1 = r6
            goto L6f
        L5b:
            r1 = r6
            goto L5e
        L5d:
        L5e:
            java.util.Hashtable<java.lang.String, com.baidu.lbsapi.auth.LBSAuthManagerListener> r6 = com.baidu.lbsapi.auth.LBSAuthManager.j
            java.lang.Object r6 = r6.get(r7)
            com.baidu.lbsapi.auth.LBSAuthManagerListener r6 = (com.baidu.lbsapi.auth.LBSAuthManagerListener) r6
            if (r6 == 0) goto L6f
            java.lang.String r7 = com.baidu.lbsapi.auth.ErrorMessage.a(r3, r0)
            r6.onAuthResult(r3, r7)
        L6f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.lbsapi.auth.LBSAuthManager.a(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002a A[Catch: JSONException -> 0x0066, all -> 0x00ba, TryCatch #0 {JSONException -> 0x0066, blocks: (B:7:0x000e, B:9:0x001c, B:10:0x0022, B:12:0x002a, B:13:0x0033, B:15:0x0042, B:16:0x0047), top: B:33:0x000e, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0042 A[Catch: JSONException -> 0x0066, all -> 0x00ba, TryCatch #0 {JSONException -> 0x0066, blocks: (B:7:0x000e, B:9:0x001c, B:10:0x0022, B:12:0x002a, B:13:0x0033, B:15:0x0042, B:16:0x0047), top: B:33:0x000e, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x008a A[Catch: all -> 0x00ba, TryCatch #1 {, blocks: (B:4:0x0003, B:5:0x0007, B:7:0x000e, B:9:0x001c, B:10:0x0022, B:12:0x002a, B:13:0x0033, B:15:0x0042, B:16:0x0047, B:20:0x0086, B:22:0x008a, B:23:0x008d, B:25:0x00ae, B:27:0x00b2, B:19:0x0067), top: B:35:0x0003, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001c A[Catch: JSONException -> 0x0066, all -> 0x00ba, TryCatch #0 {JSONException -> 0x0066, blocks: (B:7:0x000e, B:9:0x001c, B:10:0x0022, B:12:0x002a, B:13:0x0033, B:15:0x0042, B:16:0x0047), top: B:33:0x000e, outer: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void a(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            monitor-enter(r5)
            if (r6 != 0) goto L7
            java.lang.String r6 = r5.j()     // Catch: java.lang.Throwable -> Lba
        L7:
            android.os.Handler r0 = r5.s     // Catch: java.lang.Throwable -> Lba
            android.os.Message r0 = r0.obtainMessage()     // Catch: java.lang.Throwable -> Lba
            r1 = -1
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lba
            r2.<init>(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lba
            java.lang.String r6 = "status"
            boolean r6 = r2.has(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lba
            if (r6 != 0) goto L22
            java.lang.String r6 = "status"
            r2.put(r6, r1)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lba
        L22:
            java.lang.String r6 = "current"
            boolean r6 = r2.has(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lba
            if (r6 != 0) goto L33
            java.lang.String r6 = "current"
            long r3 = java.lang.System.currentTimeMillis()     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lba
            r2.put(r6, r3)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lba
        L33:
            java.lang.String r6 = r2.toString()     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lba
            r5.c(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lba
            java.lang.String r6 = "current"
            boolean r6 = r2.has(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lba
            if (r6 == 0) goto L47
            java.lang.String r6 = "current"
            r2.remove(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lba
        L47:
            java.lang.String r6 = "status"
            int r1 = r2.getInt(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lba
            r0.what = r1     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lba
            r0.obj = r2     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lba
            android.os.Bundle r6 = new android.os.Bundle     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lba
            r6.<init>()     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lba
            java.lang.String r2 = "listenerKey"
            r6.putString(r2, r7)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lba
            r0.setData(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lba
            android.os.Handler r6 = r5.s     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lba
            r6.sendMessage(r0)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lba
            goto L86
        L66:
            r6 = move-exception
            r6.printStackTrace()     // Catch: java.lang.Throwable -> Lba
            r0.what = r1     // Catch: java.lang.Throwable -> Lba
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch: java.lang.Throwable -> Lba
            r6.<init>()     // Catch: java.lang.Throwable -> Lba
            r0.obj = r6     // Catch: java.lang.Throwable -> Lba
            android.os.Bundle r6 = new android.os.Bundle     // Catch: java.lang.Throwable -> Lba
            r6.<init>()     // Catch: java.lang.Throwable -> Lba
            java.lang.String r1 = "listenerKey"
            r6.putString(r1, r7)     // Catch: java.lang.Throwable -> Lba
            r0.setData(r6)     // Catch: java.lang.Throwable -> Lba
            android.os.Handler r6 = r5.s     // Catch: java.lang.Throwable -> Lba
            r6.sendMessage(r0)     // Catch: java.lang.Throwable -> Lba
        L86:
            com.baidu.lbsapi.auth.p r6 = com.baidu.lbsapi.auth.LBSAuthManager.h     // Catch: java.lang.Throwable -> Lba
            if (r6 == 0) goto L8d
            r6.c()     // Catch: java.lang.Throwable -> Lba
        L8d:
            int r6 = com.baidu.lbsapi.auth.LBSAuthManager.i     // Catch: java.lang.Throwable -> Lba
            int r6 = r6 + (-1)
            com.baidu.lbsapi.auth.LBSAuthManager.i = r6     // Catch: java.lang.Throwable -> Lba
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lba
            r6.<init>()     // Catch: java.lang.Throwable -> Lba
            java.lang.String r7 = "httpRequest called mAuthCounter-- = "
            r6.append(r7)     // Catch: java.lang.Throwable -> Lba
            int r7 = com.baidu.lbsapi.auth.LBSAuthManager.i     // Catch: java.lang.Throwable -> Lba
            r6.append(r7)     // Catch: java.lang.Throwable -> Lba
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> Lba
            com.baidu.lbsapi.auth.b.a(r6)     // Catch: java.lang.Throwable -> Lba
            int r6 = com.baidu.lbsapi.auth.LBSAuthManager.i     // Catch: java.lang.Throwable -> Lba
            if (r6 != 0) goto Lb8
            com.baidu.lbsapi.auth.p r6 = com.baidu.lbsapi.auth.LBSAuthManager.h     // Catch: java.lang.Throwable -> Lba
            if (r6 == 0) goto Lb8
            r6.a()     // Catch: java.lang.Throwable -> Lba
            r6 = 0
            com.baidu.lbsapi.auth.LBSAuthManager.h = r6     // Catch: java.lang.Throwable -> Lba
        Lb8:
            monitor-exit(r5)
            return
        Lba:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.lbsapi.auth.LBSAuthManager.a(java.lang.String, java.lang.String):void");
    }

    private void a(HashMap<String, String> map, String str, String str2) {
        if (map == null || map.size() <= 0 || str == null || str.length() <= 0 || str2 == null || str2.length() <= 0) {
            return;
        }
        try {
            String strA = r.a(str2);
            if (strA == null || strA.length() <= 0) {
                map.put(str, str2);
            } else {
                map.put(str, strA);
            }
        } catch (Exception e2) {
            map.put(str, str2);
            Log.e("LBSAuthManager", "encodeAuthParam", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null || jSONObject.length() <= 0 || !jSONObject.has("en")) {
            return;
        }
        if (jSONObject.optInt("en", 0) == 0) {
            if (jSONObject.optString("ck").length() > 0) {
                this.m = c.a(jSONObject.optString("ck").getBytes(StandardCharsets.UTF_8));
            }
        } else {
            a(jSONObject, "ak");
            a(jSONObject, "ck");
            a(jSONObject, "sk");
            a(jSONObject, "uid");
            b(jSONObject);
        }
    }

    private void a(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject == null || jSONObject.length() <= 0 || str == null || str.length() <= 0 || !jSONObject.has(str)) {
            return;
        }
        try {
            byte[] bArrB = r.b(jSONObject.optString(str));
            if (bArrB != null && bArrB.length > 0) {
                jSONObject.put(str, new String(bArrB, StandardCharsets.UTF_8));
                if ("ck".equals(str)) {
                    this.m = bArrB;
                    return;
                }
                return;
            }
            jSONObject.put(str, "");
            jSONObject.put("decode_status", -1);
        } catch (Exception e2) {
            Log.e("LBSAuthManager", " decodeAuthResult ", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, String str, Hashtable<String, String> hashtable, String str2, String str3, int i2, String str4, String str5) throws PackageManager.NameNotFoundException, NoSuchAlgorithmException {
        String strA;
        String strA2 = a(a, str2);
        if (strA2 == null || strA2.equals("")) {
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        r.a();
        map.put("pk", r.b() != null ? r.b() : "");
        map.put("url", "https://api.map.baidu.com/sdkcs/verify");
        b.a("url:https://api.map.baidu.com/sdkcs/verify");
        map.put("output", "json");
        a(map, "ak", strA2);
        b.a("ak:" + map.get("ak"));
        a(map, "mcode", d.a(a));
        map.put(TypedValues.TransitionType.S_FROM, "lbs_yunsdk");
        if (hashtable != null && hashtable.size() > 0) {
            for (Map.Entry<String, String> entry : hashtable.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                    map.put(key, value);
                }
            }
        }
        String cuid = !TextUtils.isEmpty(o) ? getCUID() : r;
        b.a("cuid:" + r);
        if (TextUtils.isEmpty(cuid)) {
            map.put("cuid", "");
        } else {
            map.put("cuid", cuid);
        }
        map.put("pcn", a.getPackageName());
        map.put("version", VERSION);
        map.put("macaddr", "");
        try {
            strA = d.a();
        } catch (Exception unused) {
            strA = "";
        }
        if (TextUtils.isEmpty(strA)) {
            map.put("language", "");
        } else {
            map.put("language", strA);
        }
        if (z) {
            map.put("force", z ? "1" : "0");
        }
        if (str == null) {
            map.put("from_service", "");
        } else {
            map.put("from_service", str);
        }
        String strI = i();
        if (!TextUtils.isEmpty(strI)) {
            map.put("extend", strI);
        }
        e eVar = new e(a);
        this.f = eVar;
        eVar.a(map, str3, i2, str4, str5, new n(this, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, String str, Hashtable<String, String> hashtable, String[] strArr, String str2, String str3, int i2, String str4, String str5) throws NoSuchPaddingException, PackageManager.NameNotFoundException, NoSuchAlgorithmException, InvalidKeyException {
        String strA;
        String strA2 = a(a, str2);
        if (strA2 == null || strA2.equals("")) {
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        r.a();
        map.put("pk", r.b() != null ? r.b() : "");
        map.put("url", "https://api.map.baidu.com/sdkcs/verify");
        map.put("output", "json");
        a(map, "ak", strA2);
        map.put(TypedValues.TransitionType.S_FROM, "lbs_yunsdk");
        if (hashtable != null && hashtable.size() > 0) {
            for (Map.Entry<String, String> entry : hashtable.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                    map.put(key, value);
                }
            }
        }
        String cuid = !TextUtils.isEmpty(o) ? getCUID() : r;
        b.a("sendAuthRequests : cuid: " + cuid);
        if (TextUtils.isEmpty(cuid)) {
            map.put("cuid", "");
        } else {
            map.put("cuid", cuid);
        }
        map.put("pcn", a.getPackageName());
        map.put("version", VERSION);
        map.put("macaddr", "");
        try {
            strA = d.a();
        } catch (Exception unused) {
            strA = "";
        }
        if (TextUtils.isEmpty(strA)) {
            map.put("language", "");
        } else {
            map.put("language", strA);
        }
        if (z) {
            map.put("force", z ? "1" : "0");
        }
        if (str == null) {
            map.put("from_service", "");
        } else {
            map.put("from_service", str);
        }
        String strI = i();
        if (!TextUtils.isEmpty(strI)) {
            map.put("extend", strI);
        }
        String[] strArr2 = new String[strArr.length];
        for (int i3 = 0; strArr != null && i3 < strArr.length; i3++) {
            String strA3 = r.a(strArr[i3]);
            if (strA3 == null || strA3.length() <= 0) {
                strArr2[i3] = strArr[i3];
            } else {
                strArr2[i3] = strA3;
            }
        }
        g gVar = new g(a);
        this.g = gVar;
        gVar.a(map, strArr2, str3, i2, str4, str5, new o(this, str2));
    }

    private void b(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() <= 0) {
            return;
        }
        if (jSONObject.has("ck")) {
            jSONObject.remove("ck");
        }
        if (jSONObject.has("en")) {
            jSONObject.remove("en");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(String str) throws JSONException, PackageManager.NameNotFoundException {
        String string;
        JSONObject jSONObject;
        String strA = a(a, str);
        try {
            jSONObject = new JSONObject(j());
        } catch (JSONException e2) {
            e2.printStackTrace();
            string = "";
        }
        if (!jSONObject.has("ak")) {
            return true;
        }
        string = jSONObject.getString("ak");
        return (strA == null || string == null || strA.equals(string)) ? false : true;
    }

    private void c(String str) {
        a.getSharedPreferences("authStatus_" + a(a), 0).edit().putString(NotificationCompat.CATEGORY_STATUS, str).apply();
    }

    public static LBSAuthManager getInstance(Context context) {
        if (k == null) {
            synchronized (LBSAuthManager.class) {
                if (k == null) {
                    k = new LBSAuthManager(context);
                }
            }
        } else if (context != null) {
            a = context;
        } else if (b.a) {
            b.c("input context is null");
            new RuntimeException("here").printStackTrace();
        }
        return k;
    }

    private void h() {
        synchronized (LBSAuthManager.class) {
            if (h == null) {
                p pVar = new p("auth");
                h = pVar;
                pVar.start();
                while (h.a == null) {
                    try {
                        b.a("wait for create auth thread.");
                        Thread.sleep(3L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    private String i() {
        try {
            JSONObject jSONObject = new JSONObject(j());
            return !jSONObject.has("extend") ? "" : jSONObject.getString("extend");
        } catch (JSONException unused) {
            return "";
        }
    }

    private String j() {
        return a.getSharedPreferences("authStatus_" + a(a), 0).getString(NotificationCompat.CATEGORY_STATUS, "{\"status\":601}");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0037 A[Catch: all -> 0x00e3, TryCatch #0 {, blocks: (B:6:0x0007, B:9:0x0013, B:12:0x001e, B:14:0x0037, B:15:0x003c, B:17:0x0044, B:20:0x004e, B:23:0x0089, B:27:0x00a3, B:29:0x00aa, B:32:0x00af, B:33:0x00da, B:36:0x00dd, B:26:0x00a0, B:39:0x00e1), top: B:44:0x0007, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0089 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int authenticate(boolean r12, java.lang.String r13, java.util.Hashtable<java.lang.String, java.lang.String> r14, com.baidu.lbsapi.auth.LBSAuthManagerListener r15) {
        /*
            Method dump skipped, instructions count: 230
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.lbsapi.auth.LBSAuthManager.authenticate(boolean, java.lang.String, java.util.Hashtable, com.baidu.lbsapi.auth.LBSAuthManagerListener):int");
    }

    public String decodeAESMessage(String str) {
        byte[] bArr;
        if (str != null && str.length() > 0 && (bArr = this.m) != null && bArr.length > 0) {
            try {
                byte[] bArrA = c.a(str.getBytes(StandardCharsets.UTF_8));
                byte[] bArr2 = this.m;
                return new String(a.a(bArr2, bArr2, bArrA), StandardCharsets.UTF_8);
            } catch (Exception e2) {
                Log.e("LBSAuthManager", "decodeAESMessage", e2);
            }
        }
        return null;
    }

    public String getCUID() {
        if (!TextUtils.isEmpty(r)) {
            return r;
        }
        String string = "";
        if (a == null) {
            return "";
        }
        try {
            b.a("mIsPrivacyMode " + q);
            if (q) {
                string = com.baidu.android.bbalbs.common.util.c.a(a);
                r = string;
                b.a("getCUID: " + string);
            } else {
                SharedPreferences sharedPreferences = a.getSharedPreferences("Map_Privacy", 0);
                if (sharedPreferences.contains("cuid")) {
                    string = sharedPreferences.getString("cuid", "");
                } else {
                    string = q.a(UUID.randomUUID().toString().getBytes(), true) + "|MAPSDK001";
                    SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                    editorEdit.putString("cuid", string);
                    editorEdit.apply();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return string;
    }

    public String getKey() {
        Context context = a;
        if (context == null) {
            return "";
        }
        try {
            return getPublicKey(context);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public String getMCode() {
        Context context = a;
        return context == null ? "" : d.a(context);
    }

    public boolean getPrivacyMode() {
        return q;
    }

    public String getPublicKey(Context context) throws PackageManager.NameNotFoundException {
        if (!TextUtils.isEmpty(n)) {
            return n;
        }
        return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("com.baidu.lbsapi.API_KEY");
    }

    public void setAndroidId(String str) {
        if (a == null || TextUtils.isEmpty(str)) {
            return;
        }
        o = str;
    }

    public void setHttpProxyUsernameAndPassword(String str, String str2) {
        d = str;
        e = str2;
    }

    public void setKey(String str) {
        if (a == null || TextUtils.isEmpty(str)) {
            return;
        }
        n = str;
    }

    public void setPackageName(String str) {
        p = str;
    }

    public void setPrivacyMode(boolean z) {
        Context context = a;
        if (context == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("Map_Privacy", 0);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        if (z) {
            editorEdit.putBoolean("privacyMode", z);
            editorEdit.apply();
        } else {
            z = sharedPreferences.getBoolean("privacyMode", false);
        }
        q = z;
    }

    public void setProxy(String str, int i2) {
        b = str;
        c = i2;
    }
}
