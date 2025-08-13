package com.baidu.location.indoor;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Build;
import com.king.zxing.util.LogUtils;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes.dex */
public class l {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private Context b;
    private BluetoothAdapter e;
    private boolean f;
    private c g;
    private boolean c = false;
    private boolean d = false;
    private boolean h = false;
    private String i = null;
    private String j = null;
    private int k = -1;
    private int l = -1;
    private long m = -1;
    private ConcurrentMap<String, ScanResult> n = new ConcurrentHashMap();
    private Object o = null;
    private long p = 0;
    private boolean q = false;
    private long r = -1;

    private class a {
        public String a;
        public int b;
        public long c;

        public a(String str, int i, long j) {
            this.a = str;
            this.b = i;
            this.c = j / 1000000;
        }

        public String toString() {
            return this.a.toUpperCase() + ";" + this.b + ";" + this.c;
        }
    }

    private class b {
        public String a;
        public String b;
        public int c;
        public String d;
        public String e;

        public b(String str, int i, int i2, int i3, String str2) {
            this.a = Integer.toHexString(i);
            this.b = Integer.toHexString(i2);
            this.c = i3;
            this.d = str;
            this.e = str2;
        }

        public String toString() {
            return this.d + "," + this.a + "," + this.b + "," + this.c;
        }
    }

    public interface c {
        void a(boolean z, String str, String str2, String str3);
    }

    class d implements Comparator<b> {
        d() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(b bVar, b bVar2) {
            return bVar.c - bVar2.c;
        }
    }

    class e implements Comparator<a> {
        e() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(a aVar, a aVar2) {
            return aVar2.b - aVar.b;
        }
    }

    public l(Context context) {
        this.f = false;
        this.b = context;
        if (this.e == null) {
            try {
                if (Build.VERSION.SDK_INT >= 21) {
                    this.e = ((BluetoothManager) context.getSystemService("bluetooth")).getAdapter();
                    this.f = this.b.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
                } else {
                    this.e = BluetoothAdapter.getDefaultAdapter();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private b a(String str, int i, int i2, int i3, String str2, com.baidu.location.indoor.c cVar) throws NumberFormatException {
        int i4;
        int i5;
        String str3;
        String[] strArrB;
        if (cVar == null) {
            return null;
        }
        if (com.baidu.location.b.e.a().dr != 1 || (strArrB = cVar.b()) == null || strArrB.length <= 0) {
            i4 = i;
            i5 = i2;
            str3 = str2;
        } else {
            int i6 = i;
            int i7 = i2;
            String upperCase = str2;
            for (String str4 : strArrB) {
                if (str.equalsIgnoreCase(str4)) {
                    String[] strArrA = m.a(i6, i7, upperCase);
                    try {
                        if (strArrA.length >= 3) {
                            i6 = Integer.parseInt(strArrA[0]);
                            i7 = Integer.parseInt(strArrA[1]);
                            upperCase = strArrA[2].toUpperCase();
                        }
                    } catch (Exception unused) {
                    }
                }
            }
            i4 = i6;
            i5 = i7;
            str3 = upperCase;
        }
        return new b(str, i4, i5, i3, str3);
    }

    private String a(List<a> list, int i) {
        StringBuilder sb = new StringBuilder();
        Collections.sort(list, new e());
        sb.append(list.get(0).toString());
        for (int i2 = 1; i2 < list.size() && i2 < i; i2++) {
            sb.append(LogUtils.VERTICAL);
            sb.append(list.get(i2).toString());
        }
        this.k = list.size();
        return sb.toString();
    }

    public static String a(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            char[] cArr2 = a;
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

    private void a(ConcurrentMap<String, ScanResult> concurrentMap) {
        byte[] bArr;
        ArrayList<ScanResult> arrayList = new ArrayList(concurrentMap.values());
        ArrayList arrayList2 = new ArrayList();
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (ScanResult scanResult : arrayList) {
            byte[] bytes = scanResult.getScanRecord().getBytes();
            if (bytes.length >= 26) {
                b bVarA = a(bytes, scanResult.getRssi(), scanResult.getDevice().getAddress().replaceAll(":", ""));
                if (bVarA != null) {
                    arrayList4.add(bVarA);
                    bArr = bytes;
                    arrayList3.add(new a(bVarA.e, scanResult.getRssi(), scanResult.getTimestampNanos()));
                } else {
                    bArr = bytes;
                }
                String strA = a(Arrays.copyOfRange(bArr, 9, 25));
                arrayList2.add(strA);
                map.put(strA, scanResult.getDevice().getName());
                map2.put(strA, a(Arrays.copyOfRange(bArr, 0, 9)));
                if (map3.get(strA) == null) {
                    map3.put(strA, 0);
                }
                map3.put(strA, Integer.valueOf(((Integer) map3.get(strA)).intValue() + 1));
            }
        }
        String str = null;
        int iIntValue = 0;
        for (String str2 : map3.keySet()) {
            if (((Integer) map3.get(str2)).intValue() > iIntValue) {
                iIntValue = ((Integer) map3.get(str2)).intValue();
                str = str2;
            }
        }
        boolean z = iIntValue > 3;
        c cVar = this.g;
        if (cVar != null && this.c) {
            cVar.a(z, str, (String) map.get(str), (String) map2.get(str));
            this.c = false;
        }
        if (arrayList3.size() > 2) {
            this.i = a(arrayList3, 32);
            this.j = b(arrayList4, 32);
        } else {
            this.i = "";
            this.j = "";
        }
        if (this.h && com.baidu.location.indoor.d.a().b() != null && h() && !this.q && i()) {
            g();
        }
    }

    private String b(List<b> list, int i) {
        if (list == null || list.size() <= 0) {
            this.l = 0;
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Collections.sort(list, new d());
        sb.append(list.get(0).toString());
        for (int i2 = 1; i2 < list.size() && i2 < i; i2++) {
            sb.append(LogUtils.VERTICAL);
            sb.append(list.get(i2).toString());
        }
        this.l = list.size();
        return sb.toString();
    }

    private void g() {
        a();
    }

    private boolean h() {
        return System.currentTimeMillis() - this.m > 5000;
    }

    private boolean i() {
        return System.currentTimeMillis() - this.r > 5000;
    }

    private void j() {
        this.i = "";
        this.j = "";
        this.l = -1;
        this.k = -1;
        this.m = -1L;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006a A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean k() throws java.io.IOException {
        /*
            r7 = this;
            java.lang.String r0 = ""
            java.io.File r1 = new java.io.File
            android.content.Context r2 = r7.b
            java.io.File r2 = r2.getCacheDir()
            java.lang.String r3 = "ibct"
            r1.<init>(r2, r3)
            boolean r2 = r1.exists()
            r3 = 0
            if (r2 != 0) goto L18
            return r3
        L18:
            r2 = 0
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: java.lang.Exception -> L41
            java.io.FileReader r5 = new java.io.FileReader     // Catch: java.lang.Exception -> L41
            r5.<init>(r1)     // Catch: java.lang.Exception -> L41
            r4.<init>(r5)     // Catch: java.lang.Exception -> L41
            r1 = r2
            r2 = r0
        L25:
            java.lang.String r1 = r4.readLine()     // Catch: java.lang.Exception -> L3f
            if (r1 == 0) goto L3b
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L3f
            r5.<init>()     // Catch: java.lang.Exception -> L3f
            r5.append(r2)     // Catch: java.lang.Exception -> L3f
            r5.append(r1)     // Catch: java.lang.Exception -> L3f
            java.lang.String r2 = r5.toString()     // Catch: java.lang.Exception -> L3f
            goto L25
        L3b:
            r4.close()     // Catch: java.lang.Exception -> L41
            goto L49
        L3f:
            r2 = move-exception
            goto L45
        L41:
            r1 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
        L45:
            r2.printStackTrace()
            r2 = r1
        L49:
            if (r2 == 0) goto L6c
            java.lang.String r1 = r2.trim()
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L56
            goto L6c
        L56:
            java.lang.Long r0 = java.lang.Long.valueOf(r2)     // Catch: java.lang.Exception -> L6c
            long r0 = r0.longValue()     // Catch: java.lang.Exception -> L6c
            long r4 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L6c
            long r4 = r4 - r0
            r0 = 259200(0x3f480, double:1.28062E-318)
            int r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r2 >= 0) goto L6c
            r0 = 1
            return r0
        L6c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.indoor.l.k():boolean");
    }

    private void l() {
        try {
            FileWriter fileWriter = new FileWriter(new File(this.b.getCacheDir(), "ibct"));
            fileWriter.write(System.currentTimeMillis() + "");
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception unused) {
        }
    }

    public b a(byte[] bArr, int i, String str) {
        com.baidu.location.indoor.c cVarB;
        int i2;
        boolean z;
        int i3 = -i;
        if (str == null || i3 < 0 || (cVarB = com.baidu.location.indoor.d.a().b()) == null) {
            return null;
        }
        int i4 = 2;
        while (true) {
            if (i4 > 5) {
                z = false;
                break;
            }
            int i5 = i4 + 2;
            if ((bArr[i5] & 255) == 2 && (bArr[i4 + 3] & 255) == 21) {
                z = true;
                break;
            }
            if (((bArr[i4] & 255) != 45 || (bArr[i4 + 1] & 255) != 36 || (bArr[i5] & 255) != 191 || (bArr[i4 + 3] & 255) != 22) && (bArr[i4] & 255) == 173 && (bArr[i4 + 1] & 255) == 119 && (bArr[i5] & 255) == 0) {
                byte b2 = bArr[i4 + 3];
            }
            i4++;
        }
        if (!z) {
            return null;
        }
        String strA = a(Arrays.copyOfRange(bArr, 9, 25));
        String[] strArrC = cVarB.c();
        if (strArrC != null && strArrC.length > 0) {
            for (String str2 : strArrC) {
                if (strA.equalsIgnoreCase(str2)) {
                    return a(strA, ((bArr[i4 + 20] & 255) * 256) + (bArr[i4 + 21] & 255), ((bArr[i4 + 22] & 255) * 256) + (bArr[i4 + 23] & 255), i3, str, cVarB);
                }
            }
        }
        return null;
    }

    public boolean a() {
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter != null && this.f) {
            try {
                return bluetoothAdapter.isEnabled();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public boolean a(c cVar) {
        if (this.c || this.d) {
            return false;
        }
        this.d = true;
        if (!a() || k()) {
            return false;
        }
        l();
        this.g = cVar;
        return true;
    }

    public void b() {
        this.d = false;
        this.c = false;
        this.q = false;
        this.r = -1L;
    }

    public void c() {
        if (this.h) {
            try {
                BluetoothLeScanner bluetoothLeScanner = this.e.getBluetoothLeScanner();
                if (bluetoothLeScanner != null) {
                    bluetoothLeScanner.stopScan((ScanCallback) this.o);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            this.h = false;
            this.q = false;
            this.r = -1L;
            j();
        }
    }

    public String d() {
        a(this.n);
        return this.i;
    }

    public long e() {
        return this.m;
    }

    public boolean f() {
        return System.currentTimeMillis() - this.m <= 20000;
    }
}
