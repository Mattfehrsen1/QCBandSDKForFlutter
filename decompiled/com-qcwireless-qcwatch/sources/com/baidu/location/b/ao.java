package com.baidu.location.b;

import android.location.Location;
import android.os.Build;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ao {
    private int B;
    long a = 0;
    private a z;
    private static ArrayList<String> b = new ArrayList<>();
    private static ArrayList<String> c = new ArrayList<>();
    private static ArrayList<String> d = new ArrayList<>();
    private static String e = com.baidu.location.h.r.a + "/yo.dat";
    private static String f = com.baidu.location.h.r.a + "/yoh.dat";
    private static String g = com.baidu.location.h.r.a + "/yom.dat";
    private static String h = com.baidu.location.h.r.a + "/yol.dat";
    private static String i = com.baidu.location.h.r.a + "/yor.dat";
    private static File j = null;
    private static int k = 8;
    private static int l = 8;
    private static int m = 16;
    private static int n = 2048;
    private static double o = 0.0d;
    private static double p = 0.1d;
    private static double q = 30.0d;
    private static double r = 100.0d;
    private static int s = 0;
    private static int t = 64;
    private static int u = 128;
    private static Location v = null;
    private static Location w = null;
    private static Location x = null;
    private static com.baidu.location.f.p y = null;
    private static ao A = null;
    private static long C = 0;

    private class a extends com.baidu.location.h.h {
        boolean a = false;
        int b = 0;
        int c = 0;
        private ArrayList<String> e = new ArrayList<>();
        private boolean f = true;

        public a() {
            this.ei = new HashMap();
        }

        public synchronized void a() {
            ExecutorService executorServiceC;
            String strD;
            String strD2;
            if (this.a) {
                return;
            }
            if (eq > 4 && this.c < eq) {
                this.c++;
                return;
            }
            this.c = 0;
            this.a = true;
            this.b = 0;
            try {
                ArrayList<String> arrayList = this.e;
                if (arrayList == null || arrayList.size() < 1) {
                    if (this.e == null) {
                        this.e = new ArrayList<>();
                    }
                    this.b = 0;
                    int length = 0;
                    while (true) {
                        String strA = null;
                        String strB = this.b < 2 ? ao.b() : null;
                        if (strB == null && this.b != 1 && this.f) {
                            this.b = 2;
                            try {
                                strA = r.a();
                            } catch (Exception unused) {
                            }
                        } else {
                            this.b = 1;
                            strA = strB;
                        }
                        if (strA == null) {
                            break;
                        }
                        if (!strA.contains("err!")) {
                            this.e.add(strA);
                            length += strA.length();
                            if (length >= com.baidu.location.h.a.h) {
                                break;
                            }
                        }
                    }
                }
                ArrayList<String> arrayList2 = this.e;
                if (arrayList2 == null || arrayList2.size() < 1) {
                    ArrayList<String> arrayList3 = this.e;
                    if (arrayList3 != null) {
                        arrayList3.clear();
                    }
                    this.a = false;
                    return;
                }
                if (this.b != 1) {
                    executorServiceC = al.a().c();
                    if (executorServiceC != null) {
                        strD2 = com.baidu.location.h.s.d();
                        a(executorServiceC, strD2);
                    } else {
                        strD = com.baidu.location.h.s.d();
                        e(strD);
                    }
                } else {
                    executorServiceC = al.a().c();
                    if (executorServiceC != null) {
                        strD2 = com.baidu.location.h.e.e;
                        a(executorServiceC, strD2);
                    } else {
                        strD = com.baidu.location.h.e.e;
                        e(strD);
                    }
                }
            } catch (Exception unused2) {
                ArrayList<String> arrayList4 = this.e;
                if (arrayList4 != null) {
                    arrayList4.clear();
                }
            }
        }

        @Override // com.baidu.location.h.h
        public void a(boolean z) {
            if (z && this.eg != null) {
                ArrayList<String> arrayList = this.e;
                if (arrayList != null) {
                    arrayList.clear();
                }
                try {
                    JSONObject jSONObject = new JSONObject(this.eg);
                    if (jSONObject.has("ison") && jSONObject.getInt("ison") == 0) {
                        this.f = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (this.ei != null) {
                this.ei.clear();
            }
            this.a = false;
        }

        @Override // com.baidu.location.h.h
        public void b() {
            Map<String, Object> map;
            StringBuilder sb;
            String str;
            if (this.b != 1) {
                this.ee = com.baidu.location.h.s.d();
            }
            this.ef = 2;
            if (this.e != null) {
                for (int i = 0; i < this.e.size(); i++) {
                    if (this.b == 1) {
                        map = this.ei;
                        sb = new StringBuilder();
                        str = "cldc[";
                    } else {
                        map = this.ei;
                        sb = new StringBuilder();
                        str = "cltr[";
                    }
                    sb.append(str);
                    sb.append(i);
                    sb.append("]");
                    map.put(sb.toString(), this.e.get(i));
                }
                this.ei.put("trtm", String.format(Locale.CHINA, "%d", Long.valueOf(System.currentTimeMillis())));
                if (this.b != 1) {
                    this.ei.put("qt", "cltrg");
                }
            }
        }
    }

    private ao() {
        String strI;
        this.z = null;
        this.B = 0;
        this.z = new a();
        this.B = 0;
        if (Build.VERSION.SDK_INT <= 28 || (strI = com.baidu.location.h.s.i()) == null) {
            return;
        }
        e = strI + "/yo2.dat";
        f = strI + "/yoh2.dat";
        g = strI + "/yom2.dat";
        h = strI + "/yol2.dat";
        i = strI + "/yor2.dat";
    }

    private static synchronized int a(List<String> list, int i2) {
        if (list != null && i2 <= 256) {
            if (i2 >= 0) {
                try {
                    if (j == null) {
                        File file = new File(e);
                        j = file;
                        if (!file.exists()) {
                            j = null;
                            return -2;
                        }
                    }
                    RandomAccessFile randomAccessFile = new RandomAccessFile(j, "rw");
                    if (randomAccessFile.length() < 1) {
                        randomAccessFile.close();
                        return -3;
                    }
                    long j2 = i2;
                    randomAccessFile.seek(j2);
                    int i3 = randomAccessFile.readInt();
                    int i4 = randomAccessFile.readInt();
                    int i5 = randomAccessFile.readInt();
                    int i6 = randomAccessFile.readInt();
                    long j3 = randomAccessFile.readLong();
                    long j4 = j3;
                    if (a(i3, i4, i5, i6, j3)) {
                        int i7 = 1;
                        if (i4 >= 1) {
                            byte[] bArr = new byte[n];
                            int i8 = k;
                            while (i8 > 0 && i4 > 0) {
                                long j5 = (((i3 + i4) - i7) % i5) * i6;
                                byte[] bArr2 = bArr;
                                long j6 = j4;
                                randomAccessFile.seek(j5 + j6);
                                int i9 = randomAccessFile.readInt();
                                if (i9 > 0 && i9 < i6) {
                                    randomAccessFile.read(bArr2, 0, i9);
                                    int i10 = i9 - 1;
                                    if (bArr2[i10] == 0) {
                                        list.add(new String(bArr2, 0, i10));
                                    }
                                }
                                i8--;
                                i4--;
                                j4 = j6;
                                bArr = bArr2;
                                i7 = 1;
                            }
                            randomAccessFile.seek(j2);
                            randomAccessFile.writeInt(i3);
                            randomAccessFile.writeInt(i4);
                            randomAccessFile.writeInt(i5);
                            randomAccessFile.writeInt(i6);
                            randomAccessFile.writeLong(j4);
                            randomAccessFile.close();
                            return k - i8;
                        }
                    }
                    randomAccessFile.close();
                    return -4;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return -5;
                }
            }
        }
        return -1;
    }

    public static synchronized ao a() {
        if (A == null) {
            A = new ao();
        }
        return A;
    }

    private static String a(int i2) throws IOException {
        String str;
        ArrayList<String> arrayList;
        String str2 = null;
        if (i2 == 1) {
            str = f;
            arrayList = b;
        } else if (i2 == 2) {
            str = g;
            arrayList = c;
        } else {
            if (i2 == 3) {
                str = h;
            } else {
                if (i2 != 4) {
                    return null;
                }
                str = i;
            }
            arrayList = d;
        }
        if (arrayList == null) {
            return null;
        }
        if (arrayList.size() < 1) {
            a(str, arrayList);
        }
        synchronized (ao.class) {
            int size = arrayList.size();
            if (size > 0) {
                int i3 = size - 1;
                try {
                    String str3 = arrayList.get(i3);
                    try {
                        arrayList.remove(i3);
                    } catch (Exception unused) {
                    }
                    str2 = str3;
                } catch (Exception unused2) {
                }
            }
        }
        return str2;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00e2 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00cf A[EDGE_INSN: B:51:0x00cf->B:42:0x00cf BREAK  A[LOOP:0: B:28:0x005c->B:40:0x00cb], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(int r14, boolean r15) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 233
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.ao.a(int, boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:115:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:135:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:139:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x019f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(com.baidu.location.f.a r10, com.baidu.location.f.p r11, android.location.Location r12, java.lang.String r13, java.lang.String r14) {
        /*
            Method dump skipped, instructions count: 606
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.ao.a(com.baidu.location.f.a, com.baidu.location.f.p, android.location.Location, java.lang.String, java.lang.String):void");
    }

    private static void a(String str) {
        e(str);
    }

    private static boolean a(int i2, int i3, int i4, int i5, long j2) {
        return i2 >= 0 && i2 < i4 && i3 >= 0 && i3 <= i4 && i4 >= 0 && i4 <= 1024 && i5 >= 128 && i5 <= 1024;
    }

    private static boolean a(Location location) {
        if (location == null) {
            return false;
        }
        Location location2 = w;
        if (location2 == null || v == null) {
            w = location;
            return true;
        }
        double dDistanceTo = location.distanceTo(location2);
        return ((double) location.distanceTo(v)) > (((((double) com.baidu.location.h.s.S) * dDistanceTo) * dDistanceTo) + (((double) com.baidu.location.h.s.T) * dDistanceTo)) + ((double) com.baidu.location.h.s.U);
    }

    private static boolean a(Location location, com.baidu.location.f.p pVar) {
        boolean z = false;
        if (location != null && pVar != null && pVar.a != null && !pVar.a.isEmpty()) {
            if (pVar.a(y)) {
                return false;
            }
            z = true;
            if (x == null) {
                x = location;
            }
        }
        return z;
    }

    public static boolean a(Location location, boolean z) {
        return com.baidu.location.f.e.a(v, location, z);
    }

    private static boolean a(String str, List<String> list) throws IOException {
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(8L);
            int i2 = randomAccessFile.readInt();
            int i3 = randomAccessFile.readInt();
            int i4 = randomAccessFile.readInt();
            byte[] bArr = new byte[n];
            int i5 = l + 1;
            boolean z = false;
            while (i5 > 0 && i3 > 0) {
                if (i3 < i4) {
                    i4 = 0;
                }
                try {
                    randomAccessFile.seek(((i3 - 1) * i2) + 128);
                    int i6 = randomAccessFile.readInt();
                    if (i6 > 0 && i6 < i2) {
                        randomAccessFile.read(bArr, 0, i6);
                        int i7 = i6 - 1;
                        if (bArr[i7] == 0) {
                            list.add(0, new String(bArr, 0, i7));
                            z = true;
                        }
                    }
                    i5--;
                    i3--;
                } catch (Exception unused) {
                    return z;
                }
            }
            randomAccessFile.seek(12L);
            randomAccessFile.writeInt(i3);
            randomAccessFile.writeInt(i4);
            randomAccessFile.close();
            return z;
        } catch (Exception unused2) {
            return false;
        }
    }

    public static String b() {
        return f();
    }

    private static void b(String str) {
        e(str);
    }

    private static void c(String str) {
        e(str);
    }

    public static void d() {
        l = 0;
        a(1, false);
        a(2, false);
        a(3, false);
        l = 8;
    }

    private static void d(String str) throws IOException {
        try {
            File file = new File(str);
            if (file.exists()) {
                return;
            }
            File file2 = new File(com.baidu.location.h.r.a);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            if (!file.createNewFile()) {
                file = null;
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(0L);
            randomAccessFile.writeInt(32);
            randomAccessFile.writeInt(2048);
            randomAccessFile.writeInt(5120);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(0);
            randomAccessFile.close();
        } catch (Exception unused) {
        }
    }

    public static String e() throws IOException {
        File file = new File(g);
        String str = null;
        if (file.exists()) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(20L);
                int i2 = randomAccessFile.readInt();
                if (i2 > 128) {
                    String str2 = "&p1=" + i2;
                    try {
                        randomAccessFile.seek(20L);
                        randomAccessFile.writeInt(0);
                        randomAccessFile.close();
                        return str2;
                    } catch (Exception unused) {
                        str = str2;
                    }
                } else {
                    randomAccessFile.close();
                }
            } catch (Exception unused2) {
            }
        }
        File file2 = new File(h);
        if (file2.exists()) {
            try {
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(file2, "rw");
                randomAccessFile2.seek(20L);
                int i3 = randomAccessFile2.readInt();
                if (i3 > 256) {
                    String str3 = "&p2=" + i3;
                    try {
                        randomAccessFile2.seek(20L);
                        randomAccessFile2.writeInt(0);
                        randomAccessFile2.close();
                        return str3;
                    } catch (Exception unused3) {
                        str = str3;
                    }
                } else {
                    randomAccessFile2.close();
                }
            } catch (Exception unused4) {
            }
        }
        File file3 = new File(i);
        if (file3.exists()) {
            try {
                RandomAccessFile randomAccessFile3 = new RandomAccessFile(file3, "rw");
                randomAccessFile3.seek(20L);
                int i4 = randomAccessFile3.readInt();
                if (i4 > 512) {
                    String str4 = "&p3=" + i4;
                    try {
                        randomAccessFile3.seek(20L);
                        randomAccessFile3.writeInt(0);
                        randomAccessFile3.close();
                        return str4;
                    } catch (Exception unused5) {
                        str = str4;
                    }
                } else {
                    randomAccessFile3.close();
                }
            } catch (Exception unused6) {
            }
        }
        return str;
    }

    private static synchronized void e(String str) {
        ArrayList<String> arrayList;
        if (str.contains("err!")) {
            return;
        }
        int i2 = com.baidu.location.h.s.p;
        if (i2 == 1) {
            arrayList = b;
        } else if (i2 == 2) {
            arrayList = c;
        } else if (i2 != 3) {
            return;
        } else {
            arrayList = d;
        }
        if (arrayList == null) {
            return;
        }
        if (arrayList.size() <= m) {
            arrayList.add(str);
        }
        if (arrayList.size() >= m) {
            a(i2, false);
        }
        while (arrayList.size() > m) {
            arrayList.remove(0);
        }
    }

    private static String f() throws IOException {
        String strA = null;
        for (int i2 = 1; i2 < 5; i2++) {
            strA = a(i2);
            if (strA != null) {
                return strA;
            }
        }
        a(d, t);
        try {
            if (d.size() > 0) {
                String str = d.get(0);
                try {
                    d.remove(0);
                } catch (ArrayIndexOutOfBoundsException unused) {
                }
                strA = str;
            }
        } catch (ArrayIndexOutOfBoundsException unused2) {
        }
        if (strA != null) {
            return strA;
        }
        a(d, s);
        try {
            if (d.size() > 0) {
                String str2 = d.get(0);
                try {
                    d.remove(0);
                } catch (ArrayIndexOutOfBoundsException unused3) {
                }
                strA = str2;
            }
        } catch (ArrayIndexOutOfBoundsException unused4) {
        }
        if (strA != null) {
            return strA;
        }
        a(d, u);
        try {
            if (d.size() <= 0) {
                return strA;
            }
            String str3 = d.get(0);
            try {
                d.remove(0);
            } catch (ArrayIndexOutOfBoundsException unused5) {
            }
            return str3;
        } catch (ArrayIndexOutOfBoundsException unused6) {
            return strA;
        }
    }

    public void c() {
        if (com.baidu.location.f.h.a().m() && !com.baidu.location.h.s.b()) {
            this.z.a();
        }
    }
}
