package com.baidu.location.f.a;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.telephony.CellIdentityNr;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import com.baidu.location.f.g;
import com.baidu.location.f.k;
import com.king.zxing.util.LogUtils;
import com.realsil.sdk.core.bluetooth.impl.BluetoothClassImpl;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class a implements com.baidu.location.f.b.a {
    public static int b;
    private static Class<?> w;
    private C0019a t;
    private b u;
    private c v;
    private Context x;
    private int e = 30;
    private int f = -1;
    private AtomicInteger g = new AtomicInteger(0);
    private float h = 0.2f;
    private boolean i = true;
    private boolean j = false;
    private TelephonyManager k = null;
    private TelephonyManager l = null;
    private TelephonyManager m = null;
    private SubscriptionManager n = null;
    private com.baidu.location.f.a o = new com.baidu.location.f.a();
    private com.baidu.location.f.a p = null;
    private List<com.baidu.location.f.a> q = null;
    private Executor r = null;
    private d s = null;
    private boolean y = false;
    public int a = 0;
    private boolean z = false;
    private long A = 0;
    private long B = 0;
    private boolean C = false;
    private boolean D = true;
    private boolean E = false;
    private Handler F = null;
    private int G = -1;
    private int H = -1;
    private final Object I = new Object();

    /* renamed from: com.baidu.location.f.a.a$a, reason: collision with other inner class name */
    private class C0019a extends TelephonyManager.CellInfoCallback {
        private C0019a() {
        }

        /* synthetic */ C0019a(a aVar, com.baidu.location.f.a.b bVar) {
            this();
        }

        @Override // android.telephony.TelephonyManager.CellInfoCallback
        public void onCellInfo(List<CellInfo> list) {
            if (list == null) {
                return;
            }
            if (!com.baidu.location.f.b.a.d || k.h().a(list)) {
                a.this.i();
            }
        }

        @Override // android.telephony.TelephonyManager.CellInfoCallback
        public void onError(int i, Throwable th) {
            if (th != null) {
                th.printStackTrace();
            }
            if (com.baidu.location.f.b.a.c && com.baidu.location.f.b.a.d) {
                k.h().a("cell onError = " + i);
            }
        }
    }

    private class b extends TelephonyManager.CellInfoCallback {
        private b() {
        }

        /* synthetic */ b(a aVar, com.baidu.location.f.a.b bVar) {
            this();
        }

        @Override // android.telephony.TelephonyManager.CellInfoCallback
        public void onCellInfo(List<CellInfo> list) {
            if (com.baidu.location.f.b.a.c && com.baidu.location.f.b.a.d) {
                k.h().a("onCellInfo");
            }
            if (list == null) {
                return;
            }
            if (!com.baidu.location.f.b.a.d || k.h().a(list)) {
                if (com.baidu.location.f.b.a.c && com.baidu.location.f.b.a.d) {
                    k.h().a("request sim1 cellInfo");
                }
                if (a.this.C) {
                    a.this.D = !r3.D;
                }
                if (!a.this.C || a.this.D) {
                    a.this.i();
                }
            }
        }
    }

    private class c extends TelephonyManager.CellInfoCallback {
        private c() {
        }

        /* synthetic */ c(a aVar, com.baidu.location.f.a.b bVar) {
            this();
        }

        @Override // android.telephony.TelephonyManager.CellInfoCallback
        public void onCellInfo(List<CellInfo> list) {
            if (list == null) {
                return;
            }
            if (!com.baidu.location.f.b.a.d || k.h().a(list)) {
                if (com.baidu.location.f.b.a.c && com.baidu.location.f.b.a.d) {
                    k.h().a("request sim2 cellInfo");
                }
                if (a.this.C) {
                    a.this.D = !r2.D;
                }
                if (!a.this.C || a.this.D) {
                    a.this.i();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class d extends PhoneStateListener {
        public d() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onCellInfoChanged(List<CellInfo> list) {
            if (list == null) {
                return;
            }
            a.this.F.post(new com.baidu.location.f.a.c(this));
        }

        @Override // android.telephony.PhoneStateListener
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            com.baidu.location.f.a aVar;
            int cdmaDbm;
            if (a.this.o != null) {
                if (a.this.o.i != 'g') {
                    if (a.this.o.i == 'c') {
                        aVar = a.this.o;
                        cdmaDbm = signalStrength.getCdmaDbm();
                    }
                    if (com.baidu.location.f.b.a.c || !com.baidu.location.f.b.a.d) {
                    }
                    k.h().a("cell strength===== cell singal strength changed : " + a.this.o.h);
                    return;
                }
                aVar = a.this.o;
                cdmaDbm = signalStrength.getGsmSignalStrength();
                aVar.h = cdmaDbm;
                if (com.baidu.location.f.b.a.c) {
                }
            }
        }
    }

    private static class e {
        private static a a = new a();
    }

    private static int a(CellIdentityNr cellIdentityNr) {
        try {
            int iA = com.baidu.location.f.b.b.a(cellIdentityNr, "getHwTac");
            if (!c || !d) {
                return iA;
            }
            k.h().a(" get hw tac = " + iA);
            return iA;
        } catch (Throwable th) {
            if (c && d) {
                k.h().a(" get hw tac exception !" + th);
            }
            return -1;
        }
    }

    private int a(String str) {
        if (str == null || !str.contains("cl_s2")) {
            return -1;
        }
        try {
            Matcher matcher = Pattern.compile("cl_s2=[0-9]{1,}").matcher(str);
            if (!matcher.find()) {
                return -1;
            }
            String strGroup = matcher.group();
            return Integer.parseInt(strGroup.substring(strGroup.indexOf("cl_s2=") + 6, strGroup.length()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public static a a() {
        return e.a;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0177  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.baidu.location.f.a a(android.telephony.CellInfo r20, com.baidu.location.f.a r21, android.telephony.TelephonyManager r22) {
        /*
            Method dump skipped, instructions count: 1616
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.f.a.a.a(android.telephony.CellInfo, com.baidu.location.f.a, android.telephony.TelephonyManager):com.baidu.location.f.a");
    }

    private com.baidu.location.f.a a(CellLocation cellLocation) {
        return a(cellLocation, false);
    }

    private com.baidu.location.f.a a(CellLocation cellLocation, boolean z) throws IOException {
        if (cellLocation == null || this.k == null) {
            return null;
        }
        if (c && d) {
            k.h().a("set cell info..");
        }
        com.baidu.location.f.a aVar = new com.baidu.location.f.a();
        aVar.m = 1;
        if (z) {
            aVar.q = true;
        }
        aVar.g = System.currentTimeMillis();
        try {
            String networkOperator = this.k.getNetworkOperator();
            if (networkOperator != null && networkOperator.length() > 0) {
                int iIntValue = -1;
                if (networkOperator.length() >= 3) {
                    iIntValue = Integer.valueOf(networkOperator.substring(0, 3)).intValue();
                    aVar.c = iIntValue < 0 ? this.o.c : iIntValue;
                }
                String strSubstring = networkOperator.substring(3);
                if (strSubstring != null) {
                    char[] charArray = strSubstring.toCharArray();
                    int i = 0;
                    while (i < charArray.length && Character.isDigit(charArray[i])) {
                        i++;
                    }
                    iIntValue = Integer.valueOf(strSubstring.substring(0, i)).intValue();
                }
                if (iIntValue < 0) {
                    iIntValue = this.o.d;
                }
                aVar.d = iIntValue;
            }
            this.a = this.k.getSimState();
            if (c && d) {
                k.h().a("sim state:" + this.a);
            }
        } catch (Exception e2) {
            if (c) {
                e2.printStackTrace();
            }
            b = 1;
        }
        if (cellLocation instanceof GsmCellLocation) {
            aVar.a = ((GsmCellLocation) cellLocation).getLac();
            aVar.b = r9.getCid();
            aVar.i = 'g';
            if (c && d) {
                k.h().a("bslocation mNetworkType = 'g'");
            }
        } else if (cellLocation instanceof CdmaCellLocation) {
            aVar.i = 'c';
            if (c && d) {
                k.h().a("bslocation mNetworkType = 'c'");
            }
            if (w == null) {
                try {
                    w = Class.forName("android.telephony.cdma.CdmaCellLocation");
                } catch (Exception unused) {
                    w = null;
                    return aVar;
                }
            }
            Class<?> cls = w;
            if (cls != null && cls.isInstance(cellLocation)) {
                try {
                    int systemId = ((CdmaCellLocation) cellLocation).getSystemId();
                    if (systemId < 0) {
                        systemId = this.o.d;
                    }
                    aVar.d = systemId;
                    aVar.b = ((CdmaCellLocation) cellLocation).getBaseStationId();
                    aVar.a = ((CdmaCellLocation) cellLocation).getNetworkId();
                    int baseStationLatitude = ((CdmaCellLocation) cellLocation).getBaseStationLatitude();
                    if (c && d) {
                        k.h().a("bslocation lat " + (baseStationLatitude / 14400.0d));
                    }
                    if (baseStationLatitude < Integer.MAX_VALUE) {
                        aVar.e = baseStationLatitude;
                    }
                    int baseStationLongitude = ((CdmaCellLocation) cellLocation).getBaseStationLongitude();
                    if (c && d) {
                        k.h().a("bslocation lon" + (baseStationLongitude / 14400.0d));
                    }
                    if (baseStationLongitude < Integer.MAX_VALUE) {
                        aVar.f = baseStationLongitude;
                    }
                } catch (Exception e3) {
                    if (c) {
                        e3.printStackTrace();
                    }
                    b = 3;
                    return aVar;
                }
            }
        }
        g(aVar);
        return aVar;
    }

    private static int b(String str) {
        if (str == null || !str.contains("mNrTac")) {
            return -1;
        }
        Matcher matcher = Pattern.compile("mNrTac=(.+?)\\}").matcher(str.replace(" ", ""));
        while (true) {
            int i = -1;
            while (matcher.find()) {
                if (matcher.groupCount() >= 1) {
                    String strGroup = matcher.group(1);
                    if (c && d) {
                        k.h().a(" pasrse mnrtac = " + strGroup);
                    }
                    try {
                        i = Integer.parseInt(strGroup);
                    } catch (Throwable th) {
                        if (c) {
                            th.printStackTrace();
                        }
                    }
                }
            }
            return i;
        }
    }

    private static int d(int i) {
        if (i == Integer.MAX_VALUE) {
            return -1;
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0128  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String d(com.baidu.location.f.a r14) {
        /*
            Method dump skipped, instructions count: 301
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.f.a.a.d(com.baidu.location.f.a):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00b1 A[Catch: Exception -> 0x010a, TryCatch #0 {Exception -> 0x010a, blocks: (B:6:0x0010, B:8:0x0014, B:9:0x0021, B:11:0x0031, B:13:0x0034, B:16:0x003a, B:18:0x003d, B:19:0x003f, B:21:0x0045, B:23:0x004b, B:25:0x0050, B:27:0x0054, B:29:0x0058, B:30:0x008c, B:32:0x0093, B:34:0x0097, B:35:0x009d, B:39:0x00ad, B:41:0x00b1, B:42:0x00b8, B:44:0x00bc, B:46:0x00c0, B:48:0x00c9, B:50:0x00cf, B:52:0x00d3, B:53:0x00d9, B:57:0x00e9, B:59:0x00ed, B:60:0x00f4, B:62:0x00f8, B:64:0x00fc, B:66:0x0105, B:54:0x00dc, B:56:0x00e0, B:65:0x0103, B:36:0x00a0, B:38:0x00a4, B:47:0x00c7, B:24:0x004e), top: B:95:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:4:0x000a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ed A[Catch: Exception -> 0x010a, TryCatch #0 {Exception -> 0x010a, blocks: (B:6:0x0010, B:8:0x0014, B:9:0x0021, B:11:0x0031, B:13:0x0034, B:16:0x003a, B:18:0x003d, B:19:0x003f, B:21:0x0045, B:23:0x004b, B:25:0x0050, B:27:0x0054, B:29:0x0058, B:30:0x008c, B:32:0x0093, B:34:0x0097, B:35:0x009d, B:39:0x00ad, B:41:0x00b1, B:42:0x00b8, B:44:0x00bc, B:46:0x00c0, B:48:0x00c9, B:50:0x00cf, B:52:0x00d3, B:53:0x00d9, B:57:0x00e9, B:59:0x00ed, B:60:0x00f4, B:62:0x00f8, B:64:0x00fc, B:66:0x0105, B:54:0x00dc, B:56:0x00e0, B:65:0x0103, B:36:0x00a0, B:38:0x00a4, B:47:0x00c7, B:24:0x004e), top: B:95:0x0010 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d() {
        /*
            Method dump skipped, instructions count: 328
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.f.a.a.d():void");
    }

    private String e(com.baidu.location.f.a aVar) {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("&nw2=");
        stringBuffer.append(aVar.i);
        stringBuffer.append(String.format(Locale.CHINA, "&cl2=%d|%d|%d|%d&cl_s2=%d&clp2=%d&cl_t2=%d", Integer.valueOf(aVar.c), Integer.valueOf(aVar.d), Integer.valueOf(aVar.a), Long.valueOf(aVar.b), Integer.valueOf(aVar.h), Integer.valueOf(aVar.l), Long.valueOf(aVar.g)));
        if (aVar.j != Integer.MAX_VALUE) {
            stringBuffer.append("&cl_cs2=");
            stringBuffer.append(aVar.j);
        }
        if (aVar.p != null) {
            stringBuffer.append("&clnrs2=");
            stringBuffer.append(aVar.p);
        }
        return stringBuffer.toString();
    }

    private void e() {
        if (this.t == null) {
            this.t = new C0019a(this, null);
        }
        Executor executor = this.r;
        if (executor != null) {
            this.k.requestCellInfoUpdate(executor, this.t);
        }
    }

    private String f(com.baidu.location.f.a aVar) {
        return String.format(Locale.CHINA, "%d|%d|%d|%d", Integer.valueOf(aVar.c), Integer.valueOf(aVar.d), Integer.valueOf(aVar.a), Long.valueOf(aVar.b));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void f() {
        CellLocation cellLocation;
        com.baidu.location.f.a aVarA = a(this.o, this.k);
        if (c && d && aVarA != null) {
            k.h().a("new cell api = " + f(aVarA));
        }
        if (aVarA != null) {
            g(aVarA);
        }
        boolean z = true;
        if (this.f >= 0 && Build.VERSION.SDK_INT > this.f) {
            z = false;
        }
        if (z && (aVarA == null || !aVarA.b())) {
            try {
                cellLocation = this.k.getCellLocation();
            } catch (Throwable unused) {
                cellLocation = null;
            }
            com.baidu.location.f.a aVarA2 = cellLocation != null ? a(cellLocation) : null;
            if (c && d && aVarA2 != null) {
                k.h().a(" old cell api = " + f(aVarA2));
            }
        }
    }

    private void g() throws IOException {
        char c2;
        long j;
        g gVarH;
        String str;
        String strA = com.baidu.location.f.b.b.a(this.x);
        if (strA == null) {
            return;
        }
        File file = new File(strA + File.separator + "lcvif2.dat");
        if (file.exists()) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(0L);
                long j2 = randomAccessFile.readLong();
                if (System.currentTimeMillis() - j2 > 60000) {
                    if (c && d) {
                        k.h().a("cellbuffer System.currentTimeMillis() - time > 1 *60 *1000" + j2);
                    }
                    randomAccessFile.close();
                    file.delete();
                    return;
                }
                randomAccessFile.readInt();
                for (int i = 0; i < 3; i++) {
                    long j3 = randomAccessFile.readLong();
                    int i2 = randomAccessFile.readInt();
                    int i3 = randomAccessFile.readInt();
                    int i4 = randomAccessFile.readInt();
                    long j4 = randomAccessFile.readLong();
                    int i5 = randomAccessFile.readInt();
                    if (c && d) {
                        k.h().a("cellbuffer cell info = " + j3 + " " + i2 + " " + i3 + " " + i4 + " " + j4 + " " + i5);
                    }
                    char c3 = i5 == 1 ? 'g' : (char) 0;
                    if (i5 == 2) {
                        j = 0;
                        c2 = 'c';
                    } else {
                        c2 = c3;
                        j = 0;
                    }
                    if (j3 != j) {
                        com.baidu.location.f.a aVar = new com.baidu.location.f.a(i4, j4, i2, i3, 0, c2, -1);
                        aVar.g = j3;
                        if (aVar.b()) {
                            this.E = true;
                            this.q.add(aVar);
                        }
                        if (c && d) {
                            gVarH = k.h();
                            str = "loc cell " + b(aVar);
                            gVarH.a(str);
                        }
                    } else if (c && d) {
                        gVarH = k.h();
                        str = "loc cell time1 == 0";
                        gVarH.a(str);
                    }
                }
                randomAccessFile.close();
            } catch (Exception e2) {
                if (c) {
                    e2.printStackTrace();
                }
                file.delete();
            }
        }
    }

    private void g(com.baidu.location.f.a aVar) throws IOException {
        com.baidu.location.f.a aVar2;
        com.baidu.location.f.a aVar3 = this.o;
        if (aVar.b() && ((aVar2 = this.o) == null || !aVar2.a(aVar) || a(this.o, aVar))) {
            this.o = aVar;
        }
        if (aVar.b()) {
            if (aVar3 == null || !aVar3.a(aVar)) {
                if (!aVar.b()) {
                    List<com.baidu.location.f.a> list = this.q;
                    if (list != null) {
                        list.clear();
                        return;
                    }
                    return;
                }
                int size = this.q.size();
                com.baidu.location.f.a aVar4 = size == 0 ? null : this.q.get(size - 1);
                if (aVar4 != null && aVar4.b == this.o.b && aVar4.a == this.o.a) {
                    return;
                }
                this.q.add(this.o);
                if (this.q.size() > 3) {
                    this.q.remove(0);
                }
                if (this.j) {
                    h();
                }
                this.E = false;
            }
        }
    }

    private void h() throws IOException {
        List<com.baidu.location.f.a> list = this.q;
        if (list == null && this.p == null) {
            if (c && d) {
                k.h().a("cellbuffer mTrackList == null");
                return;
            }
            return;
        }
        if (list == null && this.p != null) {
            LinkedList linkedList = new LinkedList();
            this.q = linkedList;
            linkedList.add(this.p);
        }
        String strA = com.baidu.location.f.b.b.a(this.x);
        if (strA == null || this.q == null) {
            return;
        }
        File file = new File(strA + File.separator + "lcvif2.dat");
        int size = this.q.size();
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(0L);
            randomAccessFile.writeLong(this.q.get(size - 1).g);
            randomAccessFile.writeInt(size);
            for (int i = 0; i < 3 - size; i++) {
                randomAccessFile.writeLong(0L);
                randomAccessFile.writeInt(-1);
                randomAccessFile.writeInt(-1);
                randomAccessFile.writeInt(-1);
                randomAccessFile.writeLong(-1L);
                randomAccessFile.writeInt(2);
            }
            for (int i2 = 0; i2 < size; i2++) {
                randomAccessFile.writeLong(this.q.get(i2).g);
                randomAccessFile.writeInt(this.q.get(i2).c);
                randomAccessFile.writeInt(this.q.get(i2).d);
                randomAccessFile.writeInt(this.q.get(i2).a);
                randomAccessFile.writeLong(this.q.get(i2).b);
                if (this.q.get(i2).i == 'g') {
                    randomAccessFile.writeInt(1);
                } else if (this.q.get(i2).i == 'c') {
                    randomAccessFile.writeInt(2);
                } else {
                    randomAccessFile.writeInt(3);
                }
            }
            randomAccessFile.close();
        } catch (Exception e2) {
            if (c) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        this.F.post(new com.baidu.location.f.a.b(this));
    }

    public com.baidu.location.f.a a(com.baidu.location.f.a aVar, TelephonyManager telephonyManager) {
        if (Integer.valueOf(Build.VERSION.SDK_INT).intValue() < 17) {
            return null;
        }
        try {
            this.a = telephonyManager.getSimState();
            List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();
            if (allCellInfo == null || allCellInfo.size() <= 0) {
                if (!c || !d) {
                    return null;
                }
                k.h().a("getAllCellInfo=null");
                return null;
            }
            com.baidu.location.f.a aVar2 = null;
            for (CellInfo cellInfo : allCellInfo) {
                if (cellInfo.isRegistered()) {
                    boolean z = aVar2 != null;
                    com.baidu.location.f.a aVarA = a(cellInfo, aVar, telephonyManager);
                    if (aVarA != null) {
                        if (aVarA.b()) {
                            if (c && d) {
                                k.h().a(" cell res.isValid() = " + f(aVarA));
                            }
                            if (z && aVar2 != null) {
                                aVar2.n = e(aVarA);
                                aVar2.o = f(aVarA);
                            }
                        } else {
                            if (c && d) {
                                k.h().a("res.isValid()");
                            }
                            aVarA = null;
                        }
                        if (aVar2 == null) {
                            aVar2 = aVarA;
                        }
                    }
                }
            }
            return aVar2;
        } catch (Throwable th) {
            if (!c) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public String a(com.baidu.location.f.a aVar) {
        String strD;
        int iIntValue;
        String str = "";
        try {
            strD = d(aVar);
            iIntValue = Integer.valueOf(Build.VERSION.SDK_INT).intValue();
            if (strD != null && !"".equals(strD)) {
                if (!"&nc=".equals(strD)) {
                    return strD;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (iIntValue >= 17) {
            return strD;
        }
        str = strD;
        if (str == null || !"&nc=".equals(str)) {
            return str;
        }
        return null;
    }

    public void a(int i) {
        this.g.set(i);
    }

    public void a(Context context) {
        if (this.y) {
            return;
        }
        this.x = context;
        this.k = (TelephonyManager) context.getSystemService("phone");
        this.q = new LinkedList();
        if (Looper.myLooper() != null) {
            this.s = new d();
        }
        if (this.F == null) {
            this.F = new Handler(Looper.getMainLooper());
        }
        if (this.j) {
            g();
        }
        if (Build.VERSION.SDK_INT >= this.e) {
            if (this.r == null) {
                this.r = this.x.getMainExecutor();
            }
            this.z = com.baidu.location.f.b.b.a("android.telephony.TelephonyManager$CellInfoCallback");
            if (c && d) {
                k.h().a("isCellinfoCallbackExist = " + this.z);
            }
        }
        if (this.k == null || this.s == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < this.e || !this.z) {
            try {
                this.k.listen(this.s, BluetoothClassImpl.Device.PERIPHERAL_NON_KEYBOARD_NON_POINTING);
            } catch (Exception unused) {
            }
        }
        if (c && d) {
            k.h().a("cell manager start...");
        }
        this.y = true;
    }

    public void a(boolean z) {
        this.i = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(com.baidu.location.f.a r8, com.baidu.location.f.a r9) {
        /*
            r7 = this;
            r0 = 0
            if (r8 != 0) goto L6
            if (r9 != 0) goto L6
            return r0
        L6:
            r1 = 1
            if (r8 == 0) goto Lbc
            if (r9 != 0) goto Ld
            goto Lbc
        Ld:
            int r2 = r8.h
            int r3 = r9.h
            int r2 = r2 - r3
            int r2 = java.lang.Math.abs(r2)
            float r2 = (float) r2
            int r3 = r8.h
            r4 = -1
            if (r3 == 0) goto L1f
            int r3 = r8.h
            goto L20
        L1f:
            r3 = -1
        L20:
            float r3 = (float) r3
            float r2 = r2 / r3
            boolean r3 = com.baidu.location.f.a.a.c
            if (r3 == 0) goto L66
            boolean r3 = com.baidu.location.f.a.a.d
            if (r3 == 0) goto L66
            com.baidu.location.f.g r3 = com.baidu.location.f.k.h()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "cl-cache, str, old:"
            r5.append(r6)
            int r6 = r8.h
            r5.append(r6)
            java.lang.String r6 = " new:"
            r5.append(r6)
            int r6 = r9.h
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r3.a(r5)
            com.baidu.location.f.g r3 = com.baidu.location.f.k.h()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "cl-cache, str, diffRate:"
            r5.append(r6)
            r5.append(r2)
            java.lang.String r5 = r5.toString()
            r3.a(r5)
        L66:
            java.lang.String r3 = r8.n
            if (r3 == 0) goto L91
            java.lang.String r3 = r9.n
            if (r3 == 0) goto L91
            java.lang.String r8 = r8.n
            int r8 = r7.a(r8)
            java.lang.String r9 = r9.n
            int r9 = r7.a(r9)
            int r9 = r8 - r9
            int r9 = java.lang.Math.abs(r9)
            if (r8 == 0) goto L83
            r4 = r8
        L83:
            int r9 = r9 / r4
            int r8 = java.lang.Math.abs(r9)
            float r8 = (float) r8
            float r9 = r7.h
            int r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r8 <= 0) goto L91
            r8 = 1
            goto L92
        L91:
            r8 = 0
        L92:
            boolean r9 = com.baidu.location.f.a.a.c
            if (r9 == 0) goto Lb2
            boolean r9 = com.baidu.location.f.a.a.d
            if (r9 == 0) goto Lb2
            com.baidu.location.f.g r9 = com.baidu.location.f.k.h()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "cl-cache, isStrengthChange2:"
            r3.append(r4)
            r3.append(r8)
            java.lang.String r3 = r3.toString()
            r9.a(r3)
        Lb2:
            float r9 = r7.h
            int r9 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r9 > 0) goto Lba
            if (r8 == 0) goto Lbb
        Lba:
            r0 = 1
        Lbb:
            return r0
        Lbc:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.f.a.a.a(com.baidu.location.f.a, com.baidu.location.f.a):boolean");
    }

    public com.baidu.location.f.a b(com.baidu.location.f.a aVar, TelephonyManager telephonyManager) {
        CellLocation cellLocation;
        com.baidu.location.f.a aVarA = a(aVar, telephonyManager);
        if (Build.VERSION.SDK_INT <= 28 && (aVarA == null || !aVarA.b())) {
            try {
                cellLocation = telephonyManager.getCellLocation();
            } catch (Throwable unused) {
                cellLocation = null;
            }
            if (cellLocation != null) {
                aVarA = a(cellLocation);
            }
            if (c && d && aVarA != null) {
                k.h().a("main process: old cell api = " + f(aVarA));
            }
        }
        return aVarA;
    }

    public String b(com.baidu.location.f.a aVar) {
        if (aVar == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("&nw=");
        stringBuffer.append(aVar.i);
        stringBuffer.append(String.format(Locale.CHINA, "&cl=%d|%d|%d|%d&cl_s=%d", Integer.valueOf(aVar.c), Integer.valueOf(aVar.d), Integer.valueOf(aVar.a), Long.valueOf(aVar.b), Integer.valueOf(aVar.h)));
        if (aVar.e < Integer.MAX_VALUE && aVar.f < Integer.MAX_VALUE) {
            stringBuffer.append(String.format(Locale.CHINA, "&cdmall=%.6f|%.6f", Double.valueOf(aVar.f / 14400.0d), Double.valueOf(aVar.e / 14400.0d)));
        }
        stringBuffer.append("&cl_t=");
        stringBuffer.append(aVar.g);
        stringBuffer.append("&cl_api=");
        stringBuffer.append(aVar.m);
        stringBuffer.append("&clp=");
        stringBuffer.append(aVar.l);
        if (aVar.p != null) {
            stringBuffer.append("&clnrs=");
            stringBuffer.append(aVar.p);
        }
        if (Build.VERSION.SDK_INT >= 28 && aVar.j != Integer.MAX_VALUE) {
            stringBuffer.append("&cl_cs=");
            stringBuffer.append(aVar.j);
        }
        try {
            List<com.baidu.location.f.a> list = this.q;
            if (list != null && list.size() > 0) {
                int size = this.q.size();
                stringBuffer.append("&clt=");
                for (int i = 0; i < size; i++) {
                    com.baidu.location.f.a aVar2 = this.q.get(i);
                    if (aVar2 != null) {
                        if (aVar2.c != aVar.c) {
                            stringBuffer.append(aVar2.c);
                        }
                        stringBuffer.append(LogUtils.VERTICAL);
                        if (aVar2.d != aVar.d) {
                            stringBuffer.append(aVar2.d);
                        }
                        stringBuffer.append(LogUtils.VERTICAL);
                        if (aVar2.a != aVar.a) {
                            stringBuffer.append(aVar2.a);
                        }
                        stringBuffer.append(LogUtils.VERTICAL);
                        if (aVar2.b != aVar.b) {
                            stringBuffer.append(aVar2.b);
                        }
                        stringBuffer.append(LogUtils.VERTICAL);
                        stringBuffer.append((System.currentTimeMillis() - aVar2.g) / 1000);
                        stringBuffer.append(";");
                    }
                }
            }
        } catch (Exception e2) {
            if (c) {
                e2.printStackTrace();
            }
        }
        if (this.a > 100) {
            this.a = 0;
        }
        int i2 = this.a + (b << 8);
        if (c && d) {
            k.h().a("sim state:" + this.a + "," + i2);
        }
        stringBuffer.append("&cs=" + i2);
        if (aVar.n != null) {
            stringBuffer.append(aVar.n);
        }
        if (c && d) {
            k.h().a("cell sb.toString() = " + stringBuffer.toString());
        }
        return stringBuffer.toString();
    }

    public void b() {
        TelephonyManager telephonyManager;
        if (this.y) {
            try {
                d dVar = this.s;
                if (dVar != null && (telephonyManager = this.k) != null) {
                    telephonyManager.listen(dVar, 0);
                }
                this.s = null;
                this.k = null;
                this.l = null;
                this.m = null;
                List<com.baidu.location.f.a> list = this.q;
                if (list != null) {
                    list.clear();
                    this.q = null;
                }
                if (this.j) {
                    h();
                }
            } catch (Exception e2) {
                if (c) {
                    e2.printStackTrace();
                }
            }
            if (c && d) {
                k.h().a("cell manager stop ...");
            }
            this.y = false;
        }
    }

    public void b(int i) {
        this.e = i;
    }

    public void b(boolean z) {
        this.j = z;
    }

    public com.baidu.location.f.a c(int i) {
        if (this.k != null) {
            try {
                f();
                if (c && d) {
                    k.h().a(" lastDiffTime = " + this.B + ", diffTime = " + i);
                }
                if (Build.VERSION.SDK_INT >= 29 && this.z) {
                    if (i < Integer.MAX_VALUE) {
                        long j = i;
                        if (j == this.B) {
                            if (System.currentTimeMillis() - this.A > j) {
                                if (c && d) {
                                    k.h().a(" over diff time");
                                }
                            }
                        } else if (c && d) {
                            k.h().a("diff time is changed");
                        }
                        d();
                    }
                    this.B = i;
                }
            } catch (Exception e2) {
                if (c) {
                    e2.printStackTrace();
                }
            }
        }
        com.baidu.location.f.a aVar = this.o;
        if (aVar != null && aVar.e()) {
            this.p = null;
            this.p = new com.baidu.location.f.a(this.o);
        }
        com.baidu.location.f.a aVar2 = this.o;
        if (aVar2 != null && aVar2.d() && this.p != null && this.o.i == 'g') {
            this.o.d = this.p.d;
            this.o.c = this.p.c;
        }
        return this.o;
    }

    public HashSet<String> c(com.baidu.location.f.a aVar) {
        com.baidu.location.f.a aVarA;
        StringBuilder sb;
        HashSet<String> hashSet = new HashSet<>();
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                List<CellInfo> allCellInfo = this.k.getAllCellInfo();
                if (allCellInfo != null && allCellInfo.size() > 0) {
                    for (CellInfo cellInfo : allCellInfo) {
                        if (!cellInfo.isRegistered() && (aVarA = a(cellInfo, this.o, this.k)) != null) {
                            String string = "";
                            if (aVarA.a != -1 && aVarA.b != -1) {
                                if (aVar.a != aVarA.a) {
                                    sb = new StringBuilder();
                                    sb.append(aVarA.a);
                                    sb.append(LogUtils.VERTICAL);
                                    sb.append(aVarA.b);
                                } else {
                                    sb = new StringBuilder();
                                    sb.append(aVarA.b);
                                    sb.append("");
                                }
                                string = sb.toString();
                            }
                            hashSet.add(string);
                        }
                    }
                } else if (c) {
                    Log.d("getAllCellInfo", "=null");
                }
            } catch (Exception | NoSuchMethodError e2) {
                e2.printStackTrace();
            }
        }
        return hashSet;
    }

    public boolean c() {
        return this.E;
    }
}
