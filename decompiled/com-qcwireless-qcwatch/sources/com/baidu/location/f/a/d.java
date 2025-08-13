package com.baidu.location.f.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.baidu.location.f.g;
import com.baidu.location.f.k;
import com.baidu.location.f.p;
import com.king.zxing.util.LogUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class d implements com.baidu.location.f.b.a {
    private Context h;
    private boolean b = false;
    private WifiManager e = null;
    private b f = null;
    private p g = null;
    private AtomicInteger i = new AtomicInteger(0);
    public long a = 0;
    private long j = 0;
    private long k = 0;
    private long l = 0;
    private String m = null;
    private final Object n = new Object();
    private final Object o = new Object();
    private Handler p = null;
    private String q = null;

    private static class a {
        private static d a = new d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    class b extends BroadcastReceiver {
        private b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context == null) {
                return;
            }
            if ((!com.baidu.location.f.b.a.d || k.h().a(intent)) && intent.getAction().equals("android.net.wifi.SCAN_RESULTS")) {
                d.this.a = System.currentTimeMillis() / 1000;
                d.this.p.post(new f(this));
            }
        }
    }

    public static d a() {
        return a.a;
    }

    private p a(p pVar) {
        if (pVar != null) {
            return new p(pVar.a, pVar.b);
        }
        return null;
    }

    public static boolean a(p pVar, p pVar2, float f) {
        if (pVar != null && pVar2 != null) {
            List<ScanResult> list = pVar.a;
            List<ScanResult> list2 = pVar2.a;
            if (list == list2) {
                return true;
            }
            if (list != null && list2 != null) {
                int size = list.size();
                int size2 = list2.size();
                if (size == 0 && size2 == 0) {
                    return true;
                }
                if (size != 0 && size2 != 0) {
                    int i = 0;
                    for (int i2 = 0; i2 < size; i2++) {
                        String str = list.get(i2) != null ? list.get(i2).BSSID : null;
                        if (str != null) {
                            int i3 = 0;
                            while (true) {
                                if (i3 >= size2) {
                                    break;
                                }
                                String str2 = list2.get(i3) != null ? list2.get(i3).BSSID : null;
                                if (str2 != null && str.equals(str2)) {
                                    i++;
                                    break;
                                }
                                i3++;
                            }
                        }
                    }
                    if (c && d) {
                        k.h().a("wifi same!" + (i / size));
                    }
                    if (i >= size * f) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile("wpa|wep", 2).matcher(str).find();
    }

    private String b(long j) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.valueOf((int) (j & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j >> 8) & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j >> 16) & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j >> 24) & 255)));
        return stringBuffer.toString();
    }

    private String b(String str) {
        return str != null ? (str.contains("&") || str.contains(";")) ? str.replace("&", "_").replace(";", "_") : str : str;
    }

    private void g() {
        int i;
        try {
            if (this.e.isWifiEnabled() || (Build.VERSION.SDK_INT > 17 && this.e.isScanAlwaysAvailable())) {
                this.e.startScan();
                if (c && d) {
                    k.h().a("wifimanager start scan ...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.j = System.currentTimeMillis();
        synchronized (this.n) {
            try {
                i = this.i.get();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (i != 0) {
                this.n.wait(i);
            }
        }
    }

    private String h() {
        DhcpInfo dhcpInfo;
        WifiManager wifiManager = this.e;
        if (wifiManager == null || (dhcpInfo = wifiManager.getDhcpInfo()) == null) {
            return null;
        }
        return b(dhcpInfo.gateway);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        WifiManager wifiManager = this.e;
        if (wifiManager == null) {
            return;
        }
        try {
            List<ScanResult> scanResults = wifiManager.getScanResults();
            if (c && d) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (scanResults != null && scanResults.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    long j = 0;
                    for (int i = 0; i < scanResults.size(); i++) {
                        if (i == 0) {
                            try {
                                j = (jCurrentTimeMillis - scanResults.get(0).timestamp) / 1000000;
                            } catch (Exception e) {
                                e.printStackTrace();
                                j = 0;
                            }
                            sb.append(scanResults.get(0).BSSID + ";" + Math.abs(scanResults.get(0).level) + ";" + scanResults.get(0).SSID.trim() + ";" + scanResults.get(0).frequency + ";" + j + LogUtils.VERTICAL);
                        }
                        sb.append(scanResults.get(i).BSSID + ";" + Math.abs(scanResults.get(i).level) + ";" + scanResults.get(i).SSID.trim() + ";" + scanResults.get(i).frequency + ";" + (((jCurrentTimeMillis - scanResults.get(i).timestamp) / 1000000) - j) + LogUtils.VERTICAL);
                    }
                    sb.append("\t");
                    sb.append(simpleDateFormat.format(new Date(jCurrentTimeMillis)));
                    sb.append("\t");
                    sb.append(jCurrentTimeMillis);
                    sb.append("\tnull\n");
                    k.h().a(sb.toString());
                }
            }
            if (scanResults != null) {
                p pVar = new p(scanResults, System.currentTimeMillis());
                synchronized (this.o) {
                    p pVar2 = this.g;
                    if (pVar2 == null || !a(pVar, pVar2)) {
                        this.g = pVar;
                    }
                }
            }
        } catch (Exception e2) {
            if (c) {
                e2.printStackTrace();
            }
        }
    }

    public p a(long j) {
        g gVarH;
        String str;
        if (this.e != null && j < 2147483647L) {
            if (c && d) {
                k.h().a("Wi-Fi diffTime = " + j + "mLastDiffTime = " + this.k);
            }
            long j2 = this.k;
            boolean z = c;
            if (j == j2) {
                if (z && d) {
                    k.h().a("System.currentTimeMillis() = " + System.currentTimeMillis() + "wifi diffTime = " + j + ", mScanTime = " + this.j);
                }
                if (System.currentTimeMillis() - this.j > j) {
                    if (c && d) {
                        gVarH = k.h();
                        str = "time is over";
                        gVarH.a(str);
                    }
                    g();
                }
            } else {
                if (z && d) {
                    gVarH = k.h();
                    str = "diffTime is changed";
                    gVarH.a(str);
                }
                g();
            }
        }
        this.k = j;
        return this.g;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01c0 A[Catch: Exception -> 0x031b, Error -> 0x031d, TryCatch #12 {Error -> 0x031d, Exception -> 0x031b, blocks: (B:66:0x011d, B:68:0x0121, B:70:0x0125, B:72:0x0143, B:74:0x014e, B:76:0x0152, B:79:0x0174, B:94:0x01a1, B:96:0x01a6, B:98:0x01b4, B:100:0x01c0, B:103:0x01d7, B:105:0x01f1, B:107:0x01f7, B:97:0x01ae, B:62:0x0114), top: B:257:0x011d }] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x034d A[Catch: Exception -> 0x04fb, Error -> 0x04fd, TryCatch #5 {Error -> 0x04fd, blocks: (B:109:0x0203, B:116:0x021f, B:118:0x0225, B:120:0x0231, B:122:0x0241, B:151:0x032e, B:135:0x02b7, B:137:0x02bb, B:127:0x026e, B:129:0x0276, B:131:0x0282, B:133:0x0292, B:138:0x02c0, B:140:0x02c8, B:142:0x02cc, B:143:0x02ef, B:152:0x033e, B:154:0x034d, B:156:0x0351, B:159:0x0371, B:161:0x0379, B:163:0x0384, B:167:0x0393, B:169:0x039d, B:171:0x03a9, B:174:0x03bd, B:183:0x03e7, B:185:0x03ff, B:189:0x0412, B:192:0x042c, B:194:0x0432, B:196:0x0441, B:197:0x0459, B:199:0x045f, B:201:0x0467, B:205:0x0484, B:202:0x0470, B:204:0x047e, B:206:0x0488, B:208:0x048c, B:210:0x0490, B:211:0x04ad, B:213:0x04b6, B:215:0x04d6, B:219:0x04e5, B:221:0x04ea, B:222:0x04f4, B:180:0x03de, B:168:0x039a), top: B:249:0x0203 }] */
    /* JADX WARN: Removed duplicated region for block: B:158:0x036e  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x03e4 A[PHI: r4
      0x03e4: PHI (r4v2 java.lang.String) = (r4v1 java.lang.String), (r4v14 java.lang.String) binds: [B:157:0x036c, B:262:0x03e4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:183:0x03e7 A[Catch: Exception -> 0x04fb, Error -> 0x04fd, TryCatch #5 {Error -> 0x04fd, blocks: (B:109:0x0203, B:116:0x021f, B:118:0x0225, B:120:0x0231, B:122:0x0241, B:151:0x032e, B:135:0x02b7, B:137:0x02bb, B:127:0x026e, B:129:0x0276, B:131:0x0282, B:133:0x0292, B:138:0x02c0, B:140:0x02c8, B:142:0x02cc, B:143:0x02ef, B:152:0x033e, B:154:0x034d, B:156:0x0351, B:159:0x0371, B:161:0x0379, B:163:0x0384, B:167:0x0393, B:169:0x039d, B:171:0x03a9, B:174:0x03bd, B:183:0x03e7, B:185:0x03ff, B:189:0x0412, B:192:0x042c, B:194:0x0432, B:196:0x0441, B:197:0x0459, B:199:0x045f, B:201:0x0467, B:205:0x0484, B:202:0x0470, B:204:0x047e, B:206:0x0488, B:208:0x048c, B:210:0x0490, B:211:0x04ad, B:213:0x04b6, B:215:0x04d6, B:219:0x04e5, B:221:0x04ea, B:222:0x04f4, B:180:0x03de, B:168:0x039a), top: B:249:0x0203 }] */
    /* JADX WARN: Removed duplicated region for block: B:224:0x04f9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0508  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x017a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:259:0x033e A[EDGE_INSN: B:259:0x033e->B:152:0x033e BREAK  A[LOOP:0: B:44:0x00d3->B:151:0x032e], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:270:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x014e A[Catch: Exception -> 0x031b, Error -> 0x031d, TryCatch #12 {Error -> 0x031d, Exception -> 0x031b, blocks: (B:66:0x011d, B:68:0x0121, B:70:0x0125, B:72:0x0143, B:74:0x014e, B:76:0x0152, B:79:0x0174, B:94:0x01a1, B:96:0x01a6, B:98:0x01b4, B:100:0x01c0, B:103:0x01d7, B:105:0x01f1, B:107:0x01f7, B:97:0x01ae, B:62:0x0114), top: B:257:0x011d }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01a6 A[Catch: Exception -> 0x031b, Error -> 0x031d, TryCatch #12 {Error -> 0x031d, Exception -> 0x031b, blocks: (B:66:0x011d, B:68:0x0121, B:70:0x0125, B:72:0x0143, B:74:0x014e, B:76:0x0152, B:79:0x0174, B:94:0x01a1, B:96:0x01a6, B:98:0x01b4, B:100:0x01c0, B:103:0x01d7, B:105:0x01f1, B:107:0x01f7, B:97:0x01ae, B:62:0x0114), top: B:257:0x011d }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01ae A[Catch: Exception -> 0x031b, Error -> 0x031d, TryCatch #12 {Error -> 0x031d, Exception -> 0x031b, blocks: (B:66:0x011d, B:68:0x0121, B:70:0x0125, B:72:0x0143, B:74:0x014e, B:76:0x0152, B:79:0x0174, B:94:0x01a1, B:96:0x01a6, B:98:0x01b4, B:100:0x01c0, B:103:0x01d7, B:105:0x01f1, B:107:0x01f7, B:97:0x01ae, B:62:0x0114), top: B:257:0x011d }] */
    /* JADX WARN: Type inference failed for: r6v35 */
    /* JADX WARN: Type inference failed for: r6v37 */
    /* JADX WARN: Type inference failed for: r6v47 */
    /* JADX WARN: Type inference failed for: r6v49 */
    /* JADX WARN: Type inference failed for: r6v50 */
    /* JADX WARN: Type inference failed for: r6v51 */
    /* JADX WARN: Type inference failed for: r6v52 */
    /* JADX WARN: Type inference failed for: r6v53 */
    /* JADX WARN: Type inference failed for: r6v54 */
    /* JADX WARN: Type inference failed for: r6v55 */
    /* JADX WARN: Type inference failed for: r6v56 */
    /* JADX WARN: Type inference failed for: r6v57 */
    /* JADX WARN: Type inference failed for: r6v58 */
    /* JADX WARN: Type inference failed for: r6v59 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String a(int r36, boolean r37, com.baidu.location.f.p r38, int r39) {
        /*
            Method dump skipped, instructions count: 1296
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.f.a.d.a(int, boolean, com.baidu.location.f.p, int):java.lang.String");
    }

    public synchronized String a(WifiInfo wifiInfo, String str) {
        if (wifiInfo == null && str == null) {
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.l > 1000) {
            if (wifiInfo != null) {
                this.m = wifiInfo.getBSSID();
            } else {
                this.m = str;
            }
            this.l = jCurrentTimeMillis;
        }
        return this.m;
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x0150 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0136 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01c6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01c8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String a(com.baidu.location.f.p r27, int r28, java.lang.String r29, boolean r30, int r31) {
        /*
            Method dump skipped, instructions count: 614
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.f.a.d.a(com.baidu.location.f.p, int, java.lang.String, boolean, int):java.lang.String");
    }

    public void a(int i) {
        this.i.set(i);
    }

    public void a(Context context, List<String> list) {
        if (this.b) {
            return;
        }
        this.h = context;
        this.e = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        this.f = new b();
        if (this.p == null) {
            this.p = new Handler(Looper.getMainLooper());
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        if (!list.contains("android.net.wifi.SCAN_RESULTS")) {
            list.add("android.net.wifi.SCAN_RESULTS");
        }
        try {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                this.h.registerReceiver(this.f, new IntentFilter(it.next()));
            }
        } catch (Exception e) {
            if (c) {
                e.printStackTrace();
            }
        }
        this.b = true;
        if (c && d) {
            k.h().a("wifimanager start ...");
        }
    }

    public boolean a(p pVar, p pVar2) {
        if (pVar.a == null || pVar2 == null || pVar2.a == null) {
            return false;
        }
        int iMin = Math.min(pVar.a.size(), pVar2.a.size());
        for (int i = 0; i < iMin; i++) {
            try {
                if (pVar.a.get(i) != null) {
                    String str = pVar.a.get(i).BSSID;
                    String str2 = pVar2.a.get(i).BSSID;
                    if (!TextUtils.isEmpty(str) && !str.equals(str2)) {
                        return false;
                    }
                }
            } catch (Exception e) {
                if (c) {
                    e.printStackTrace();
                }
                return false;
            }
        }
        return true;
    }

    public void b() {
        if (this.b) {
            try {
                this.h.unregisterReceiver(this.f);
                this.a = 0L;
            } catch (Exception e) {
                if (c) {
                    e.printStackTrace();
                }
            }
            this.f = null;
            this.e = null;
            this.b = false;
            if (c && d) {
                k.h().a("wifimanager stop ...");
            }
        }
    }

    public long c() {
        return this.j;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0030 -> B:22:0x0035). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0032 -> B:22:0x0035). Please report as a decompilation issue!!! */
    public p d() {
        p pVarA;
        synchronized (this.o) {
            pVarA = a(this.g);
        }
        if (pVarA == null || !pVarA.b()) {
            try {
                WifiManager wifiManager = this.e;
                pVarA = wifiManager != null ? new p(wifiManager.getScanResults(), this.j) : new p(null, 0L);
            } catch (Exception e) {
                if (c) {
                    e.printStackTrace();
                }
            }
        }
        return pVarA;
    }

    public String e() {
        StringBuffer stringBuffer = new StringBuffer();
        WifiInfo wifiInfoF = a().f();
        String strA = a(wifiInfoF, (String) null);
        if (wifiInfoF == null || strA == null) {
            return null;
        }
        String strReplace = strA.replace(":", "");
        int rssi = wifiInfoF.getRssi();
        String strH = a().h();
        if (rssi < 0) {
            rssi = -rssi;
        }
        if (strReplace == null || rssi >= 100 || "020000000000".equals(strReplace)) {
            return null;
        }
        stringBuffer.append("&wf=");
        stringBuffer.append(strReplace);
        stringBuffer.append(";");
        stringBuffer.append("" + rssi + ";");
        String ssid = wifiInfoF.getSSID();
        if (ssid != null && (ssid.contains("&") || ssid.contains(";"))) {
            ssid = ssid.replace("&", "_");
        }
        stringBuffer.append(ssid);
        stringBuffer.append("&wf_n=1");
        if (strH != null) {
            stringBuffer.append("&wf_gw=");
            stringBuffer.append(strH);
        }
        return stringBuffer.toString();
    }

    public WifiInfo f() {
        try {
            WifiManager wifiManager = this.e;
            WifiInfo connectionInfo = wifiManager != null ? wifiManager.getConnectionInfo() : null;
            String strA = a(connectionInfo, (String) null);
            if (connectionInfo != null && strA != null && connectionInfo.getRssi() > -100) {
                if (strA != null) {
                    String strReplace = strA.replace(":", "");
                    if (!"000000000000".equals(strReplace) && !"".equals(strReplace)) {
                        if ("020000000000".equals(strReplace)) {
                        }
                    }
                    return null;
                }
                return connectionInfo;
            }
        } catch (Error | Exception unused) {
        }
        return null;
    }
}
