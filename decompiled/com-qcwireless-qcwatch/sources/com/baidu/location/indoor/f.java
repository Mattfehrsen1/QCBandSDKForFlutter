package com.baidu.location.indoor;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import androidx.work.WorkRequest;
import com.king.zxing.util.LogUtils;
import com.qcwireless.qcwatch.ui.home.gps.service.TrackingService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes.dex */
public class f {
    private static final char[] c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static f d = null;
    private Context g;
    private BluetoothAdapter h;
    private String k;
    private String o;
    private int p;
    private int q;
    private String r;
    private ConcurrentMap<String, ScanResult> e = new ConcurrentHashMap();
    private ConcurrentMap<String, List<ScanResult>> f = new ConcurrentHashMap();
    private volatile int i = 0;
    public volatile boolean a = false;
    private volatile boolean j = false;
    private long l = -1;
    private long m = -1;
    private Object n = null;
    public long b = -1;
    private long s = 0;
    private long t = -1;
    private long u = 0;

    public class a {
        private String b;
        private String c;
        private String d;
        private int e;
        private int f;
        private int g;
        private long h;

        public a(String str, String str2, int i, long j) {
            this.b = str;
            this.c = str2;
            this.g = -i;
            this.h = j / 1000000;
        }

        public String a() {
            return this.b.toUpperCase() + ";" + this.g + ";" + this.h;
        }

        public void a(int i) {
            this.e = i;
        }

        public void a(String str) {
            this.d = str;
        }

        public String b() {
            return this.e + "," + this.f + "," + this.g;
        }

        public void b(int i) {
            this.f = i;
        }

        public void b(String str) {
            this.b = str;
        }

        public String toString() {
            return this.c + ";" + this.b + ";" + this.d + ";" + this.e + ";" + this.f + ";" + this.g + ";" + this.h;
        }
    }

    class b implements Comparator<a> {
        b() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(a aVar, a aVar2) {
            return aVar.g - aVar2.g;
        }
    }

    private f() {
    }

    private a a(ScanResult scanResult) throws NumberFormatException {
        if (TextUtils.isEmpty(scanResult.getDevice().getAddress()) || scanResult.getRssi() > 0) {
            return null;
        }
        boolean z = false;
        String strReplaceAll = scanResult.getDevice().getAddress().replaceAll(":", "");
        a aVar = new a(strReplaceAll, scanResult.getDevice().getName(), scanResult.getRssi(), scanResult.getTimestampNanos());
        if (scanResult.getScanRecord() != null) {
            byte[] bytes = scanResult.getScanRecord().getBytes();
            int i = 2;
            while (true) {
                if (i > 5) {
                    break;
                }
                int i2 = i + 2;
                if ((bytes[i2] & 255) == 2 && (bytes[i + 3] & 255) == 21) {
                    z = true;
                    break;
                }
                if (((bytes[i] & 255) != 45 || (bytes[i + 1] & 255) != 36 || (bytes[i2] & 255) != 191 || (bytes[i + 3] & 255) != 22) && (bytes[i] & 255) == 173 && (bytes[i + 1] & 255) == 119 && (bytes[i2] & 255) == 0) {
                    byte b2 = bytes[i + 3];
                }
                i++;
            }
            if (!z) {
                return aVar;
            }
            int i3 = ((bytes[i + 20] & 255) * 256) + (bytes[i + 21] & 255);
            int i4 = ((bytes[i + 22] & 255) * 256) + (bytes[i + 23] & 255);
            if (bytes.length > 26) {
                a(aVar, a(Arrays.copyOfRange(bytes, 9, 25)), i3, i4, strReplaceAll);
            }
        }
        return aVar;
    }

    private a a(a aVar, String str, int i, int i2, String str2) throws NumberFormatException {
        String[] strArrB;
        c cVarB = d.a().b();
        if (com.baidu.location.b.e.a().dr == 1 && cVarB != null && (strArrB = cVarB.b()) != null && strArrB.length > 0) {
            for (String str3 : strArrB) {
                if (str.equalsIgnoreCase(str3)) {
                    String[] strArrA = m.a(i, i2, str2);
                    try {
                        if (strArrA.length >= 3) {
                            i = Integer.parseInt(strArrA[0]);
                            i2 = Integer.parseInt(strArrA[1]);
                            str2 = strArrA[2].toUpperCase();
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
        aVar.a(str);
        aVar.a(i);
        aVar.b(i2);
        aVar.b(str2);
        return aVar;
    }

    public static synchronized f a() {
        if (d == null) {
            d = new f();
        }
        return d;
    }

    private static String a(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            char[] cArr2 = c;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        String str = new String(cArr);
        try {
            return str.toUpperCase();
        } catch (Exception unused) {
            return str;
        }
    }

    private boolean f() {
        boolean z;
        BluetoothAdapter bluetoothAdapter;
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!this.g.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            if (!this.g.getPackageManager().hasSystemFeature("android.hardware.bluetooth")) {
                z = false;
            }
            bluetoothAdapter = this.h;
            if (bluetoothAdapter != null && z) {
                try {
                    return bluetoothAdapter.isEnabled();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return false;
        }
        z = true;
        bluetoothAdapter = this.h;
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.isEnabled();
        }
        return false;
    }

    private void g() {
        if (this.h == null) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                this.n = new g(this);
                this.h.getBluetoothLeScanner().startScan((List<ScanFilter>) null, new ScanSettings.Builder().setScanMode(2).build(), (ScanCallback) this.n);
                this.a = true;
                if (d.a().b() != null || com.baidu.location.g.a.a() == null) {
                    return;
                }
                com.baidu.location.g.a.a().postDelayed(new h(this), com.baidu.location.h.s.aR * 1000);
            }
        } catch (Exception unused) {
        }
    }

    private void h() {
        this.e.clear();
        this.o = "";
        this.p = -1;
        this.q = -1;
        this.r = "";
        this.k = "";
        this.b = -1L;
    }

    private void i() {
        if (System.currentTimeMillis() - this.u <= WorkRequest.MIN_BACKOFF_MILLIS || d.a().b() == null) {
            return;
        }
        this.u = System.currentTimeMillis();
        Handler handlerA = com.baidu.location.g.a.a();
        if (handlerA != null) {
            handlerA.postDelayed(new i(this), 1000L);
            handlerA.postDelayed(new j(this), TrackingService.Constant.FASTEST_UPDATE_INTERVAL);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        ConcurrentMap<String, ScanResult> concurrentMap = this.e;
        if (concurrentMap == null || concurrentMap.values().size() <= 0) {
            return;
        }
        try {
            ArrayList arrayList = new ArrayList(this.e.values());
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            StringBuilder sb3 = new StringBuilder();
            c cVarB = d.a().b();
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                a aVarA = a((ScanResult) it.next());
                if (aVarA != null) {
                    arrayList2.add(aVarA);
                }
            }
            if (arrayList2.size() > 0) {
                Collections.sort(arrayList2, new b());
                sb.append(((a) arrayList2.get(0)).toString());
                for (int i = 1; i < arrayList2.size(); i++) {
                    sb.append(LogUtils.VERTICAL);
                    sb.append(((a) arrayList2.get(i)).toString());
                }
                if (cVarB != null) {
                    int size = arrayList2.size();
                    if (size > cVarB.a()) {
                        size = cVarB.a();
                    }
                    this.p = size;
                    for (int i2 = 0; i2 < size; i2++) {
                        a aVar = (a) arrayList2.get(i2);
                        String[] strArrC = cVarB.c();
                        if (!TextUtils.isEmpty(aVar.d) && strArrC != null) {
                            for (String str : strArrC) {
                                if (aVar.d.equalsIgnoreCase(str)) {
                                    sb2.append(((a) arrayList2.get(i2)).a());
                                    sb2.append(LogUtils.VERTICAL);
                                    sb3.append(((a) arrayList2.get(i2)).b());
                                    sb3.append(LogUtils.VERTICAL);
                                }
                            }
                        }
                    }
                }
            }
            if (TextUtils.isEmpty(sb2) || TextUtils.isEmpty(sb2.toString())) {
                this.o = "";
            } else {
                this.o = sb2.toString();
            }
            if (TextUtils.isEmpty(sb3) || TextUtils.isEmpty(sb3.toString())) {
                this.r = "";
            } else {
                this.r = sb3.toString();
            }
            this.k = sb.toString();
            this.l = System.currentTimeMillis();
        } catch (Exception unused) {
        }
    }

    private boolean k() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = this.b;
        long j2 = jCurrentTimeMillis - j;
        if (j2 > WorkRequest.MIN_BACKOFF_MILLIS && j > 100000 && this.a) {
            i();
        }
        return Math.abs(j2) <= 5000 && Math.abs(jCurrentTimeMillis - this.l) <= 5000;
    }

    public synchronized void b() {
        if (f()) {
            if (!this.a) {
                g();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0032 A[Catch: all -> 0x004c, TryCatch #1 {, blocks: (B:3:0x0001, B:7:0x0007, B:9:0x000b, B:11:0x0011, B:15:0x0023, B:17:0x0032, B:18:0x0047, B:14:0x0020), top: B:26:0x0001, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void c() {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.a     // Catch: java.lang.Throwable -> L4c
            if (r0 != 0) goto L7
            monitor-exit(r4)
            return
        L7:
            android.bluetooth.BluetoothAdapter r0 = r4.h     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> L4c
            if (r0 == 0) goto L23
            android.bluetooth.le.BluetoothLeScanner r0 = r0.getBluetoothLeScanner()     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> L4c
            if (r0 == 0) goto L23
            android.bluetooth.BluetoothAdapter r0 = r4.h     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> L4c
            android.bluetooth.le.BluetoothLeScanner r0 = r0.getBluetoothLeScanner()     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> L4c
            java.lang.Object r1 = r4.n     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> L4c
            android.bluetooth.le.ScanCallback r1 = (android.bluetooth.le.ScanCallback) r1     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> L4c
            r0.stopScan(r1)     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> L4c
            goto L23
        L1f:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L4c
        L23:
            r0 = 0
            r4.i = r0     // Catch: java.lang.Throwable -> L4c
            r4.a = r0     // Catch: java.lang.Throwable -> L4c
            long r1 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L4c
            r4.t = r1     // Catch: java.lang.Throwable -> L4c
            android.content.Context r1 = r4.g     // Catch: java.lang.Throwable -> L4c
            if (r1 == 0) goto L47
            java.lang.String r2 = "BluetoothManager"
            android.content.SharedPreferences r0 = r1.getSharedPreferences(r2, r0)     // Catch: java.lang.Throwable -> L4c
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch: java.lang.Throwable -> L4c
            java.lang.String r1 = "lastStopScanBluetoothTime"
            long r2 = r4.t     // Catch: java.lang.Throwable -> L4c
            r0.putLong(r1, r2)     // Catch: java.lang.Throwable -> L4c
            r0.apply()     // Catch: java.lang.Throwable -> L4c
        L47:
            r4.h()     // Catch: java.lang.Throwable -> L4c
            monitor-exit(r4)
            return
        L4c:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.indoor.f.c():void");
    }

    public int d() {
        return this.q;
    }

    public synchronized String e() {
        if (!k() || TextUtils.isEmpty(this.r)) {
            return null;
        }
        return this.r;
    }
}
