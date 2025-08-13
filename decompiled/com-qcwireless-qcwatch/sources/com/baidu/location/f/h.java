package com.baidu.location.f;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityNr;
import android.telephony.CellIdentityTdscdma;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoNr;
import android.telephony.CellInfoTdscdma;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthNr;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import androidx.work.WorkRequest;
import com.baidu.location.f.k;
import com.baidu.location.h.s;
import com.baidu.location.pb.CellCommonValue;
import com.baidu.location.pb.CellValue;
import com.baidu.location.pb.CellValueList;
import com.baidu.location.pb.LteCellValue;
import com.baidu.location.pb.NrCellValue;
import com.google.protobuf.micro.ByteStringMicro;
import com.king.zxing.util.LogUtils;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImage;
import com.qcwireless.qcwatch.ui.home.gps.service.TrackingService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class h {
    private k e;
    private b f;
    private TelephonyManager g;
    private final String a = "NetLocDataManager";
    private final int b = 2000;
    private final int c = 100;
    private final int d = 30000;
    private ConnectivityManager h = null;
    private WifiManager i = null;
    private Handler j = null;
    private String k = null;
    private boolean l = false;
    private long m = 0;
    private long n = 0;
    private long o = 0;
    private long p = 0;
    private boolean q = false;
    private boolean r = false;
    private long s = 0;

    private static class a {
        private static final h a = new h();
    }

    public class b extends g {
        private long b = 0;
        private boolean c = false;

        public b() {
        }

        @Override // com.baidu.location.f.g
        public void a(String str) {
        }

        @Override // com.baidu.location.f.g
        public boolean a(Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                boolean booleanExtra = intent.getBooleanExtra("resultsUpdated", true);
                h.this.n = System.currentTimeMillis() / 1000;
                if (h.this.j == null) {
                    return true;
                }
                h.this.j.post(new j(this, booleanExtra));
            } else if (action.equals("android.net.wifi.STATE_CHANGE")) {
                if (!((NetworkInfo) intent.getParcelableExtra("networkInfo")).getState().equals(NetworkInfo.State.CONNECTED) || System.currentTimeMillis() - this.b < 5000) {
                    return false;
                }
                this.b = System.currentTimeMillis();
                if (!this.c) {
                    this.c = true;
                    return false;
                }
                if (h.this.j == null) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.baidu.location.f.g
        public boolean a(List<CellInfo> list) {
            com.baidu.location.c.b.a().e();
            return true;
        }
    }

    static class c implements Comparator<ScanResult> {
        c() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(ScanResult scanResult, ScanResult scanResult2) {
            return scanResult2.level - scanResult.level;
        }
    }

    private static class d implements Comparator<m> {
        private d() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(m mVar, m mVar2) {
            return mVar.g - mVar2.g;
        }
    }

    public static h a() {
        return a.a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private m a(CellInfo cellInfo, TelephonyManager telephonyManager) {
        m mVar;
        long jElapsedRealtimeNanos;
        long jElapsedRealtimeNanos2;
        long jElapsedRealtimeNanos3;
        long jCurrentTimeMillis;
        long jElapsedRealtimeNanos4;
        long jCurrentTimeMillis2;
        long jElapsedRealtimeNanos5;
        long jCurrentTimeMillis3;
        long jElapsedRealtimeNanos6;
        long jCurrentTimeMillis4;
        int i = Build.VERSION.SDK_INT;
        CellIdentityNr cellIdentityNr = null;
        if (i < 17) {
            return null;
        }
        try {
            if (cellInfo instanceof CellInfoGsm) {
                m mVar2 = new m();
                CellIdentityGsm cellIdentity = ((CellInfoGsm) cellInfo).getCellIdentity();
                mVar2.a = 1;
                if (cellInfo.isRegistered()) {
                    mVar2.d = 1;
                }
                if (i >= 28) {
                    mVar2.b = cellIdentity.getMccString();
                    mVar2.c = cellIdentity.getMncString();
                    mVar2.f = cellInfo.getCellConnectionStatus();
                } else {
                    mVar2.b = cellIdentity.getMcc() == Integer.MAX_VALUE ? null : String.valueOf(cellIdentity.getMcc());
                    mVar2.c = cellIdentity.getMnc() != Integer.MAX_VALUE ? String.valueOf(cellIdentity.getMnc()) : null;
                }
                if (i >= 30) {
                    jElapsedRealtimeNanos6 = SystemClock.elapsedRealtime() - cellInfo.getTimestampMillis();
                    jCurrentTimeMillis4 = System.currentTimeMillis();
                } else {
                    jElapsedRealtimeNanos6 = (SystemClock.elapsedRealtimeNanos() - cellInfo.getTimeStamp()) / 1000000;
                    jCurrentTimeMillis4 = System.currentTimeMillis();
                }
                mVar2.e = jCurrentTimeMillis4 - jElapsedRealtimeNanos6;
                mVar = mVar2;
            } else {
                if (cellInfo instanceof CellInfoCdma) {
                    m mVar3 = new m();
                    CellIdentityCdma cellIdentity2 = ((CellInfoCdma) cellInfo).getCellIdentity();
                    mVar3.a = 2;
                    mVar3.c = cellIdentity2.getSystemId() != Integer.MAX_VALUE ? String.valueOf(cellIdentity2.getSystemId()) : null;
                    if (cellInfo.isRegistered()) {
                        mVar3.d = 1;
                    }
                    if (i >= 28) {
                        mVar3.f = cellInfo.getCellConnectionStatus();
                    }
                    try {
                        String networkOperator = telephonyManager.getNetworkOperator();
                        if (!TextUtils.isEmpty(networkOperator) && networkOperator.length() >= 3) {
                            mVar3.b = networkOperator.substring(0, 3);
                        }
                    } catch (Exception unused) {
                    }
                    try {
                        if (i >= 30) {
                            jElapsedRealtimeNanos5 = SystemClock.elapsedRealtime() - cellInfo.getTimestampMillis();
                            jCurrentTimeMillis3 = System.currentTimeMillis();
                        } else {
                            jElapsedRealtimeNanos5 = (SystemClock.elapsedRealtimeNanos() - cellInfo.getTimeStamp()) / 1000000;
                            jCurrentTimeMillis3 = System.currentTimeMillis();
                        }
                        mVar3.e = jCurrentTimeMillis3 - jElapsedRealtimeNanos5;
                    } catch (Error unused2) {
                        mVar3.e = System.currentTimeMillis();
                    }
                    return mVar3;
                }
                if (cellInfo instanceof CellInfoWcdma) {
                    m mVar4 = new m();
                    CellIdentityWcdma cellIdentity3 = ((CellInfoWcdma) cellInfo).getCellIdentity();
                    mVar4.a = 4;
                    if (cellInfo.isRegistered()) {
                        mVar4.d = 1;
                    }
                    if (i >= 28) {
                        mVar4.b = cellIdentity3.getMccString();
                        mVar4.c = cellIdentity3.getMncString();
                        mVar4.f = cellInfo.getCellConnectionStatus();
                    } else {
                        mVar4.b = cellIdentity3.getMcc() == Integer.MAX_VALUE ? null : String.valueOf(cellIdentity3.getMcc());
                        mVar4.c = cellIdentity3.getMnc() != Integer.MAX_VALUE ? String.valueOf(cellIdentity3.getMnc()) : null;
                    }
                    if (i >= 30) {
                        jElapsedRealtimeNanos4 = SystemClock.elapsedRealtime() - cellInfo.getTimestampMillis();
                        jCurrentTimeMillis2 = System.currentTimeMillis();
                    } else {
                        jElapsedRealtimeNanos4 = (SystemClock.elapsedRealtimeNanos() - cellInfo.getTimeStamp()) / 1000000;
                        jCurrentTimeMillis2 = System.currentTimeMillis();
                    }
                    mVar4.e = jCurrentTimeMillis2 - jElapsedRealtimeNanos4;
                    mVar = mVar4;
                } else {
                    if (i >= 29 && (cellInfo instanceof CellInfoTdscdma)) {
                        m mVar5 = new m();
                        CellIdentityTdscdma cellIdentity4 = ((CellInfoTdscdma) cellInfo).getCellIdentity();
                        mVar5.a = 5;
                        if (cellInfo.isRegistered()) {
                            mVar5.d = 1;
                        }
                        mVar5.b = cellIdentity4.getMccString();
                        mVar5.c = cellIdentity4.getMncString();
                        mVar5.f = cellInfo.getCellConnectionStatus();
                        try {
                            if (i >= 30) {
                                jElapsedRealtimeNanos3 = SystemClock.elapsedRealtime() - cellInfo.getTimestampMillis();
                                jCurrentTimeMillis = System.currentTimeMillis();
                            } else {
                                jElapsedRealtimeNanos3 = (SystemClock.elapsedRealtimeNanos() - cellInfo.getTimeStamp()) / 1000000;
                                jCurrentTimeMillis = System.currentTimeMillis();
                            }
                            mVar5.e = jCurrentTimeMillis - jElapsedRealtimeNanos3;
                            return mVar5;
                        } catch (Error unused3) {
                            mVar5.e = System.currentTimeMillis();
                            return mVar5;
                        }
                    }
                    if (cellInfo instanceof CellInfoLte) {
                        n nVar = new n();
                        CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                        CellIdentityLte cellIdentity5 = cellInfoLte.getCellIdentity();
                        CellSignalStrengthLte cellSignalStrength = cellInfoLte.getCellSignalStrength();
                        nVar.a = 3;
                        if (cellInfo.isRegistered()) {
                            nVar.d = 1;
                        }
                        nVar.h = cellIdentity5.getCi();
                        nVar.i = cellIdentity5.getPci();
                        nVar.j = cellIdentity5.getTac();
                        nVar.r = cellSignalStrength.getTimingAdvance();
                        if (i >= 28) {
                            nVar.b = cellIdentity5.getMccString();
                            nVar.c = cellIdentity5.getMncString();
                            nVar.f = cellInfo.getCellConnectionStatus();
                            nVar.l = cellIdentity5.getBandwidth();
                        } else {
                            if (cellIdentity5.getMcc() != Integer.MAX_VALUE) {
                                nVar.b = String.valueOf(cellIdentity5.getMcc());
                            }
                            if (cellIdentity5.getMnc() != Integer.MAX_VALUE) {
                                nVar.c = String.valueOf(cellIdentity5.getMnc());
                            }
                        }
                        if (i >= 24) {
                            nVar.k = cellIdentity5.getEarfcn();
                        }
                        if (i >= 29) {
                            nVar.m = Math.abs(cellSignalStrength.getRssi());
                        }
                        if (i >= 26) {
                            nVar.n = Math.abs(cellSignalStrength.getRsrp());
                            nVar.g = Math.abs(cellSignalStrength.getRsrp());
                            nVar.o = cellSignalStrength.getRsrq();
                            int rssnr = cellSignalStrength.getRssnr();
                            if (rssnr == Integer.MAX_VALUE && cellInfo.isRegistered()) {
                                rssnr = w();
                            }
                            nVar.p = rssnr;
                            nVar.q = cellSignalStrength.getCqi();
                        }
                        try {
                            if (i >= 30) {
                                jElapsedRealtimeNanos2 = SystemClock.elapsedRealtime() - cellInfo.getTimestampMillis();
                            } else {
                                jElapsedRealtimeNanos2 = (SystemClock.elapsedRealtimeNanos() - cellInfo.getTimeStamp()) / 1000000;
                            }
                            nVar.e = System.currentTimeMillis() - jElapsedRealtimeNanos2;
                            return nVar;
                        } catch (Error unused4) {
                            nVar.e = System.currentTimeMillis();
                            return nVar;
                        }
                    }
                    if (i < 29 || !(cellInfo instanceof CellInfoNr)) {
                        return null;
                    }
                    o oVar = new o();
                    try {
                        cellIdentityNr = (CellIdentityNr) ((CellInfoNr) cellInfo).getCellIdentity();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    CellSignalStrengthNr cellSignalStrengthNr = (CellSignalStrengthNr) ((CellInfoNr) cellInfo).getCellSignalStrength();
                    if (cellIdentityNr != null) {
                        oVar.a = 6;
                        oVar.b = cellIdentityNr.getMccString();
                        oVar.c = cellIdentityNr.getMncString();
                        oVar.h = cellIdentityNr.getNci();
                        oVar.i = cellIdentityNr.getPci();
                        oVar.j = cellIdentityNr.getTac();
                        if (oVar.j == Integer.MAX_VALUE) {
                            try {
                                oVar.j = a(cellIdentityNr);
                            } catch (Throwable unused5) {
                            }
                        }
                        if (oVar.j == Integer.MAX_VALUE) {
                            try {
                                oVar.j = a(cellIdentityNr.toString());
                            } catch (Throwable unused6) {
                            }
                        }
                        oVar.k = cellIdentityNr.getNrarfcn();
                    }
                    if (cellInfo.isRegistered()) {
                        oVar.d = 1;
                    }
                    oVar.f = cellInfo.getCellConnectionStatus();
                    oVar.l = Math.abs(cellSignalStrengthNr.getSsRsrp());
                    oVar.g = Math.abs(cellSignalStrengthNr.getSsRsrp());
                    oVar.m = Math.abs(cellSignalStrengthNr.getSsRsrq());
                    oVar.n = cellSignalStrengthNr.getSsSinr();
                    oVar.o = Math.abs(cellSignalStrengthNr.getCsiRsrp());
                    oVar.p = Math.abs(cellSignalStrengthNr.getCsiRsrq());
                    oVar.q = cellSignalStrengthNr.getCsiSinr();
                    try {
                        if (i >= 30) {
                            jElapsedRealtimeNanos = SystemClock.elapsedRealtime() - cellInfo.getTimestampMillis();
                        } else {
                            jElapsedRealtimeNanos = (SystemClock.elapsedRealtimeNanos() - cellInfo.getTimeStamp()) / 1000000;
                        }
                        oVar.e = System.currentTimeMillis() - jElapsedRealtimeNanos;
                        mVar = oVar;
                    } catch (Error unused7) {
                        oVar.e = System.currentTimeMillis();
                        mVar = oVar;
                    }
                }
            }
        } catch (Error unused8) {
            telephonyManager.e = System.currentTimeMillis();
            mVar = telephonyManager;
        }
        return mVar;
    }

    private String a(List<CellInfo> list, TelephonyManager telephonyManager, int i, int[] iArr, boolean z, int i2) {
        ArrayList arrayList = new ArrayList();
        if (i != 1 && com.baidu.location.b.e.a().cb != 1) {
            return null;
        }
        boolean z2 = com.baidu.location.b.e.a().cb != 1 && z;
        Iterator<CellInfo> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next(), telephonyManager));
        }
        return a(a(arrayList, i2), iArr, z2);
    }

    private String a(List<m> list, int[] iArr, boolean z) {
        if (list == null || list.size() == 0) {
            return null;
        }
        CellValueList cellValueList = new CellValueList();
        for (m mVar : list) {
            CellValue cellValue = new CellValue();
            CellCommonValue cellCommonValue = new CellCommonValue();
            cellCommonValue.setCellType(mVar.a);
            if (mVar.b != null) {
                cellCommonValue.setMcc(ByteStringMicro.copyFromUtf8(mVar.b));
            }
            if (mVar.c != null) {
                cellCommonValue.setMnc(ByteStringMicro.copyFromUtf8(mVar.c));
            }
            cellCommonValue.setRegistered(mVar.d);
            cellCommonValue.setTimestamp(mVar.e);
            if (mVar.f != Integer.MAX_VALUE) {
                cellCommonValue.setCellconnectionstatus(mVar.f);
            }
            if (!z) {
                cellValue.setCellCommonValue(cellCommonValue);
            }
            if (mVar instanceof n) {
                LteCellValue lteCellValue = new LteCellValue();
                n nVar = (n) mVar;
                if (nVar.h != Integer.MAX_VALUE && (!z || iArr[0] == 1)) {
                    lteCellValue.setCi(nVar.h);
                }
                if (nVar.i != Integer.MAX_VALUE && (!z || iArr[1] == 1)) {
                    lteCellValue.setPci(nVar.i);
                }
                if (nVar.j != Integer.MAX_VALUE && (!z || iArr[2] == 1)) {
                    lteCellValue.setTac(nVar.j);
                }
                if (nVar.k != Integer.MAX_VALUE && (!z || iArr[3] == 1)) {
                    lteCellValue.setEarfcn(nVar.k);
                }
                if (nVar.l != Integer.MAX_VALUE && (!z || iArr[4] == 1)) {
                    lteCellValue.setBandwidth(nVar.l);
                }
                if (nVar.m != Integer.MAX_VALUE && (!z || iArr[5] == 1)) {
                    lteCellValue.setRssi(nVar.m);
                }
                if (nVar.n != Integer.MAX_VALUE && (!z || iArr[6] == 1)) {
                    lteCellValue.setRsrp(nVar.n);
                }
                if (nVar.o != Integer.MAX_VALUE && (!z || iArr[7] == 1)) {
                    lteCellValue.setRsrq(nVar.o);
                }
                if (nVar.p != Integer.MAX_VALUE && (!z || iArr[8] == 1)) {
                    lteCellValue.setRssnr(nVar.p);
                }
                if (nVar.q != Integer.MAX_VALUE && (!z || iArr[9] == 1)) {
                    lteCellValue.setCqi(nVar.q);
                }
                if (nVar.r != Integer.MAX_VALUE && (!z || iArr[10] == 1)) {
                    lteCellValue.setTimingadvance(nVar.r);
                }
                cellValue.setLteCellValue(lteCellValue);
            } else if (mVar instanceof o) {
                NrCellValue nrCellValue = new NrCellValue();
                o oVar = (o) mVar;
                if (oVar.h != Long.MAX_VALUE && (!z || iArr[0] == 1)) {
                    nrCellValue.setCi(oVar.h);
                }
                if (oVar.i != Integer.MAX_VALUE && (!z || iArr[1] == 1)) {
                    nrCellValue.setPci(oVar.i);
                }
                if (oVar.j != Integer.MAX_VALUE && (!z || iArr[2] == 1)) {
                    nrCellValue.setTac(oVar.j);
                }
                if (oVar.k != Integer.MAX_VALUE && (!z || iArr[11] == 1)) {
                    nrCellValue.setNrarfcn(oVar.k);
                }
                if (oVar.l != Integer.MAX_VALUE && (!z || iArr[12] == 1)) {
                    nrCellValue.setSsrsrp(oVar.l);
                }
                if (oVar.m != Integer.MAX_VALUE && (!z || iArr[13] == 1)) {
                    nrCellValue.setSsrsrq(oVar.m);
                }
                if (oVar.n != Integer.MAX_VALUE && (!z || iArr[14] == 1)) {
                    nrCellValue.setSssinr(oVar.n);
                }
                if (oVar.o != Integer.MAX_VALUE && (!z || iArr[15] == 1)) {
                    nrCellValue.setCsirsrp(oVar.o);
                }
                if (oVar.p != Integer.MAX_VALUE && (!z || iArr[16] == 1)) {
                    nrCellValue.setCsirsrq(oVar.p);
                }
                if (oVar.q != Integer.MAX_VALUE && (!z || iArr[17] == 1)) {
                    nrCellValue.setCsisinr(oVar.q);
                }
                cellValue.setNrCellValue(nrCellValue);
            }
            cellValueList.addCellValue(cellValue);
        }
        return Base64.encodeToString(cellValueList.toByteArray(), 0);
    }

    private static List<m> a(List<m> list, int i) {
        if (list.size() == 0) {
            return null;
        }
        if (list.size() == 1) {
            return list;
        }
        Collections.sort(list.subList(1, list.size()), new d());
        list.size();
        return list.subList(0, Math.min(list.size(), i));
    }

    private void a(TelephonyManager telephonyManager, int i, int[] iArr, boolean z, int i2) {
        if (telephonyManager != null) {
            try {
                this.k = a(telephonyManager.getAllCellInfo(), telephonyManager, i, iArr, z, i2);
            } catch (Throwable unused) {
            }
        }
    }

    private boolean a(com.baidu.location.f.a aVar, int i, int i2) {
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(com.baidu.location.f.p r19, long r20) {
        /*
            r18 = this;
            r0 = r19
            int r1 = android.os.Build.VERSION.SDK_INT
            r3 = 1000(0x3e8, double:4.94E-321)
            r5 = 0
            r6 = 0
            r8 = 17
            if (r1 < r8) goto L1a
            long r8 = android.os.SystemClock.elapsedRealtimeNanos()     // Catch: java.lang.Throwable -> L13
            long r8 = r8 / r3
            goto L14
        L13:
            r8 = r6
        L14:
            int r1 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r1 <= 0) goto L1b
            r1 = 1
            goto L1c
        L1a:
            r8 = r6
        L1b:
            r1 = 0
        L1c:
            if (r1 != 0) goto L1f
            return r5
        L1f:
            java.util.List<android.net.wifi.ScanResult> r10 = r0.a
            if (r10 == 0) goto L86
            java.util.List<android.net.wifi.ScanResult> r10 = r0.a
            int r10 = r10.size()
            if (r10 != 0) goto L2c
            goto L86
        L2c:
            java.util.List<android.net.wifi.ScanResult> r10 = r0.a
            int r10 = r10.size()
            r11 = 16
            if (r10 <= r11) goto L38
            r10 = 16
        L38:
            r12 = r6
            r14 = r12
            r11 = 0
        L3b:
            if (r11 >= r10) goto L73
            java.util.List<android.net.wifi.ScanResult> r2 = r0.a
            java.lang.Object r2 = r2.get(r11)
            if (r2 == 0) goto L6e
            java.util.List<android.net.wifi.ScanResult> r2 = r0.a
            java.lang.Object r2 = r2.get(r11)
            android.net.wifi.ScanResult r2 = (android.net.wifi.ScanResult) r2
            int r2 = r2.level
            if (r2 != 0) goto L52
            goto L6e
        L52:
            if (r1 == 0) goto L6e
            java.util.List<android.net.wifi.ScanResult> r2 = r0.a     // Catch: java.lang.Throwable -> L66
            java.lang.Object r2 = r2.get(r11)     // Catch: java.lang.Throwable -> L66
            android.net.wifi.ScanResult r2 = (android.net.wifi.ScanResult) r2     // Catch: java.lang.Throwable -> L66
            long r6 = r2.timestamp     // Catch: java.lang.Throwable -> L66
            long r6 = r8 - r6
            r16 = 1000000(0xf4240, double:4.940656E-318)
            long r6 = r6 / r16
            goto L68
        L66:
            r6 = 0
        L68:
            long r12 = r12 + r6
            int r2 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r2 <= 0) goto L6e
            r14 = r6
        L6e:
            int r11 = r11 + 1
            r6 = 0
            goto L3b
        L73:
            long r0 = (long) r10
            long r12 = r12 / r0
            long r14 = r14 * r3
            int r0 = (r14 > r20 ? 1 : (r14 == r20 ? 0 : -1))
            if (r0 > 0) goto L84
            long r12 = r12 * r3
            int r0 = (r12 > r20 ? 1 : (r12 == r20 ? 0 : -1))
            if (r0 <= 0) goto L82
            goto L84
        L82:
            r2 = 0
            goto L85
        L84:
            r2 = 1
        L85:
            return r2
        L86:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.f.h.a(com.baidu.location.f.p, long):boolean");
    }

    private String b(String str) {
        if (str != null && str.length() > com.baidu.location.b.e.a().dm) {
            str = str.substring(0, com.baidu.location.b.e.a().dm);
        }
        return str != null ? (str.contains("&") || str.contains(";")) ? str.replace("&", "_").replace(";", "_") : str : str;
    }

    private String c(String str) {
        return str != null ? (str.contains("&") || str.contains(";")) ? str.replace("&", "_").replace(";", "_") : str : str;
    }

    private synchronized String v() {
        String strReplace;
        strReplace = null;
        String str = this.k;
        if (str != null && str.length() != 0) {
            strReplace = this.k.replace("\n", "");
        }
        return strReplace;
    }

    private int w() {
        try {
            TelephonyManager telephonyManager = this.g;
            if (telephonyManager != null) {
                return s.a(telephonyManager.getSignalStrength(), "getLteRssnr");
            }
            return Integer.MAX_VALUE;
        } catch (Exception unused) {
            return Integer.MAX_VALUE;
        }
    }

    public int a(CellIdentityNr cellIdentityNr) {
        try {
            return s.a(cellIdentityNr, "getHwTac");
        } catch (Throwable unused) {
            return -1;
        }
    }

    public int a(p pVar) {
        int i;
        for (int i2 = 0; i2 < pVar.a(); i2++) {
            if (pVar.a.get(i2) != null && (i = -pVar.a.get(i2).level) > 0) {
                return i;
            }
        }
        return 0;
    }

    public int a(String str) {
        if (str == null || !str.contains("mNrTac")) {
            return -1;
        }
        Matcher matcher = Pattern.compile("mNrTac=(.+?)\\}").matcher(str.replace(" ", ""));
        while (true) {
            int i = -1;
            while (matcher.find()) {
                if (matcher.groupCount() >= 1) {
                    try {
                        i = Integer.parseInt(matcher.group(1));
                    } catch (Throwable unused) {
                    }
                }
            }
            return i;
        }
    }

    public com.baidu.location.f.a a(com.baidu.location.f.a aVar, TelephonyManager telephonyManager) {
        k kVar = this.e;
        if (kVar != null) {
            return kVar.a(aVar, telephonyManager);
        }
        return null;
    }

    public p a(int i) {
        k kVar = this.e;
        if (kVar != null) {
            return kVar.e(i);
        }
        return null;
    }

    public String a(int i, p pVar) {
        if (pVar.a() < 1) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(512);
        int size = pVar.a.size();
        if (size <= i) {
            i = size;
        }
        boolean z = true;
        for (int i2 = 0; i2 < i; i2++) {
            if (pVar.a.get(i2) != null && pVar.a.get(i2).level != 0 && pVar.a.get(i2).BSSID != null) {
                if (z) {
                    z = false;
                } else {
                    stringBuffer.append(LogUtils.VERTICAL);
                }
                stringBuffer.append(pVar.a.get(i2).BSSID.replace(":", ""));
                int i3 = pVar.a.get(i2).level;
                if (i3 < 0) {
                    i3 = -i3;
                }
                stringBuffer.append(String.format(Locale.CHINA, ";%d;", Integer.valueOf(i3)));
            }
        }
        if (z) {
            return null;
        }
        return stringBuffer.toString();
    }

    public String a(int i, boolean z, p pVar, int i2) {
        k kVar = this.e;
        if (kVar != null) {
            return kVar.a(i, z, pVar, i2);
        }
        return null;
    }

    public String a(WifiInfo wifiInfo, String str) {
        k kVar = this.e;
        if (kVar != null) {
            return kVar.a(wifiInfo, str);
        }
        return null;
    }

    public String a(com.baidu.location.f.a aVar) {
        k kVar = this.e;
        if (kVar != null) {
            return kVar.a(aVar);
        }
        return null;
    }

    public String a(p pVar, int i, String str, boolean z, int i2) {
        k kVar = this.e;
        if (kVar != null) {
            return kVar.a(pVar, i, str, z, i2);
        }
        return null;
    }

    public synchronized void a(Context context) {
        if (this.e == null) {
            return;
        }
        try {
            this.g = (TelephonyManager) context.getSystemService("phone");
            this.i = (WifiManager) context.getSystemService("wifi");
            this.h = (ConnectivityManager) context.getSystemService("connectivity");
            if (Looper.myLooper() != null) {
                this.j = new Handler();
            }
            this.e.a(k.a.GET_ALL_DATA);
            a(true);
            this.e.b(true);
            this.e.c(30);
            this.e.a(false);
            this.e.a(context, new ArrayList());
        } catch (Exception unused) {
        }
    }

    public void a(boolean z) {
        int i;
        k kVar;
        k kVar2 = this.e;
        if (kVar2 == null || this.r == z) {
            return;
        }
        if (z) {
            kVar2.a(100);
            kVar = this.e;
            i = 2000;
        } else {
            i = 0;
            kVar2.a(0);
            kVar = this.e;
        }
        kVar.b(i);
        this.r = z;
    }

    public boolean a(long j) {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.o;
        if (jCurrentTimeMillis >= 0 && jCurrentTimeMillis <= TrackingService.Constant.FASTEST_UPDATE_INTERVAL) {
            return false;
        }
        this.o = System.currentTimeMillis();
        if (s.f != 4) {
            a(0);
        }
        k kVar = this.e;
        return kVar != null && kVar.c() - j > 0;
    }

    public boolean a(com.baidu.location.f.a aVar, com.baidu.location.f.a aVar2) {
        k kVar = this.e;
        if (kVar != null) {
            return kVar.a(aVar, aVar2);
        }
        return false;
    }

    public boolean a(p pVar, p pVar2, float f) {
        boolean zA = pVar2.a(pVar, f);
        long jCurrentTimeMillis = System.currentTimeMillis() - com.baidu.location.b.c.c;
        if (jCurrentTimeMillis <= 0 || jCurrentTimeMillis >= WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS || !zA || d(pVar2) - d(pVar) <= 30) {
            return zA;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x002d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long b(com.baidu.location.f.p r14) {
        /*
            r13 = this;
            java.util.List<android.net.wifi.ScanResult> r0 = r14.a
            r1 = 0
            if (r0 == 0) goto L77
            java.util.List<android.net.wifi.ScanResult> r0 = r14.a
            int r0 = r0.size()
            if (r0 != 0) goto L10
            goto L77
        L10:
            r3 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r0 = android.os.Build.VERSION.SDK_INT
            r5 = 17
            r6 = 0
            if (r0 < r5) goto L29
            long r7 = android.os.SystemClock.elapsedRealtimeNanos()     // Catch: java.lang.Throwable -> L22
            r9 = 1000(0x3e8, double:4.94E-321)
            long r7 = r7 / r9
            goto L23
        L22:
            r7 = r1
        L23:
            int r0 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r0 <= 0) goto L2a
            r0 = 1
            goto L2b
        L29:
            r7 = r1
        L2a:
            r0 = 0
        L2b:
            if (r0 != 0) goto L2e
            return r1
        L2e:
            java.util.List<android.net.wifi.ScanResult> r5 = r14.a
            int r5 = r5.size()
            r9 = 16
            if (r5 <= r9) goto L3a
            r5 = 16
        L3a:
            if (r6 >= r5) goto L6d
            java.util.List<android.net.wifi.ScanResult> r9 = r14.a
            java.lang.Object r9 = r9.get(r6)
            if (r9 == 0) goto L6a
            java.util.List<android.net.wifi.ScanResult> r9 = r14.a
            java.lang.Object r9 = r9.get(r6)
            android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9
            int r9 = r9.level
            if (r9 != 0) goto L51
            goto L6a
        L51:
            if (r0 == 0) goto L6a
            java.util.List<android.net.wifi.ScanResult> r9 = r14.a     // Catch: java.lang.Throwable -> L64
            java.lang.Object r9 = r9.get(r6)     // Catch: java.lang.Throwable -> L64
            android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9     // Catch: java.lang.Throwable -> L64
            long r9 = r9.timestamp     // Catch: java.lang.Throwable -> L64
            long r9 = r7 - r9
            r11 = 1000000(0xf4240, double:4.940656E-318)
            long r9 = r9 / r11
            goto L65
        L64:
            r9 = r1
        L65:
            int r11 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r11 >= 0) goto L6a
            r3 = r9
        L6a:
            int r6 = r6 + 1
            goto L3a
        L6d:
            if (r0 == 0) goto L70
            goto L71
        L70:
            r3 = r1
        L71:
            int r14 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r14 >= 0) goto L76
            goto L77
        L76:
            r1 = r3
        L77:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.f.h.b(com.baidu.location.f.p):long");
    }

    public String b(int i, p pVar) {
        if (i == 0) {
            return null;
        }
        int i2 = 1;
        if (pVar.a() < 1) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(256);
        int size = pVar.a.size();
        if (size > s.N) {
            size = s.N;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            if (pVar.a.get(i4) != null) {
                if ((i2 & i) != 0 && pVar.a.get(i4).BSSID != null) {
                    stringBuffer.append(i3 == 0 ? "&ssid=" : LogUtils.VERTICAL);
                    stringBuffer.append(pVar.a.get(i4).BSSID.replace(":", ""));
                    stringBuffer.append(";");
                    stringBuffer.append(c(pVar.a.get(i4).SSID));
                    i3++;
                }
                i2 <<= 1;
            }
        }
        return stringBuffer.toString();
    }

    public String b(com.baidu.location.f.a aVar) {
        if (this.e == null) {
            return null;
        }
        return this.e.c(aVar) + "&cl_list=" + v();
    }

    public synchronized void b() {
        if (this.e == null) {
            this.e = new k();
        }
        try {
            b bVar = new b();
            this.f = bVar;
            k kVar = this.e;
            if (kVar != null) {
                kVar.a(bVar);
            }
        } catch (Exception unused) {
        }
    }

    public boolean b(long j) {
        p pVarU;
        try {
            if ((!this.i.isWifiEnabled() && (Build.VERSION.SDK_INT <= 17 || !this.i.isScanAlwaysAvailable())) || m() || (pVarU = u()) == null) {
                return false;
            }
            return a(pVarU, j);
        } catch (Exception | NoSuchMethodError unused) {
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0029 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long c(com.baidu.location.f.p r14) {
        /*
            r13 = this;
            java.util.List<android.net.wifi.ScanResult> r0 = r14.a
            r1 = 0
            if (r0 == 0) goto L6b
            java.util.List<android.net.wifi.ScanResult> r0 = r14.a
            int r0 = r0.size()
            if (r0 != 0) goto Lf
            goto L6b
        Lf:
            int r0 = android.os.Build.VERSION.SDK_INT
            r3 = 17
            r4 = 0
            if (r0 < r3) goto L25
            long r5 = android.os.SystemClock.elapsedRealtimeNanos()     // Catch: java.lang.Throwable -> L1e
            r7 = 1000(0x3e8, double:4.94E-321)
            long r5 = r5 / r7
            goto L1f
        L1e:
            r5 = r1
        L1f:
            int r0 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r0 <= 0) goto L26
            r0 = 1
            goto L27
        L25:
            r5 = r1
        L26:
            r0 = 0
        L27:
            if (r0 != 0) goto L2a
            return r1
        L2a:
            java.util.List<android.net.wifi.ScanResult> r3 = r14.a
            int r3 = r3.size()
            r7 = 16
            if (r3 <= r7) goto L36
            r3 = 16
        L36:
            r7 = r1
        L37:
            if (r4 >= r3) goto L6a
            java.util.List<android.net.wifi.ScanResult> r9 = r14.a
            java.lang.Object r9 = r9.get(r4)
            if (r9 == 0) goto L67
            java.util.List<android.net.wifi.ScanResult> r9 = r14.a
            java.lang.Object r9 = r9.get(r4)
            android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9
            int r9 = r9.level
            if (r9 != 0) goto L4e
            goto L67
        L4e:
            if (r0 == 0) goto L67
            java.util.List<android.net.wifi.ScanResult> r9 = r14.a     // Catch: java.lang.Throwable -> L61
            java.lang.Object r9 = r9.get(r4)     // Catch: java.lang.Throwable -> L61
            android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9     // Catch: java.lang.Throwable -> L61
            long r9 = r9.timestamp     // Catch: java.lang.Throwable -> L61
            long r9 = r5 - r9
            r11 = 1000000(0xf4240, double:4.940656E-318)
            long r9 = r9 / r11
            goto L62
        L61:
            r9 = r1
        L62:
            int r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r11 <= 0) goto L67
            r7 = r9
        L67:
            int r4 = r4 + 1
            goto L37
        L6a:
            return r7
        L6b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.f.h.c(com.baidu.location.f.p):long");
    }

    public HashSet<String> c(com.baidu.location.f.a aVar) {
        k kVar = this.e;
        if (kVar != null) {
            return kVar.b(aVar);
        }
        return null;
    }

    public void c() {
        k kVar = this.e;
        if (kVar != null) {
            kVar.b();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0025 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long d(com.baidu.location.f.p r19) {
        /*
            r18 = this;
            r0 = r19
            int r1 = r19.a()
            r2 = 0
            if (r1 != 0) goto Lb
            return r2
        Lb:
            int r1 = android.os.Build.VERSION.SDK_INT
            r4 = 17
            r5 = 0
            if (r1 < r4) goto L21
            long r6 = android.os.SystemClock.elapsedRealtimeNanos()     // Catch: java.lang.Throwable -> L1a
            r8 = 1000(0x3e8, double:4.94E-321)
            long r6 = r6 / r8
            goto L1b
        L1a:
            r6 = r2
        L1b:
            int r1 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r1 <= 0) goto L22
            r1 = 1
            goto L23
        L21:
            r6 = r2
        L22:
            r1 = 0
        L23:
            if (r1 != 0) goto L26
            return r2
        L26:
            java.util.List<android.net.wifi.ScanResult> r4 = r0.a
            int r4 = r4.size()
            r8 = 16
            if (r4 <= r8) goto L32
            r4 = 16
        L32:
            r8 = r2
            r10 = r8
            r12 = r10
        L35:
            r14 = 1
            if (r5 >= r4) goto L70
            java.util.List<android.net.wifi.ScanResult> r2 = r0.a
            java.lang.Object r2 = r2.get(r5)
            if (r2 == 0) goto L6b
            java.util.List<android.net.wifi.ScanResult> r2 = r0.a
            java.lang.Object r2 = r2.get(r5)
            android.net.wifi.ScanResult r2 = (android.net.wifi.ScanResult) r2
            int r2 = r2.level
            if (r2 != 0) goto L4e
            goto L6b
        L4e:
            if (r1 == 0) goto L6b
            java.util.List<android.net.wifi.ScanResult> r2 = r0.a     // Catch: java.lang.Throwable -> L62
            java.lang.Object r2 = r2.get(r5)     // Catch: java.lang.Throwable -> L62
            android.net.wifi.ScanResult r2 = (android.net.wifi.ScanResult) r2     // Catch: java.lang.Throwable -> L62
            long r2 = r2.timestamp     // Catch: java.lang.Throwable -> L62
            long r2 = r6 - r2
            r16 = 1000000(0xf4240, double:4.940656E-318)
            long r2 = r2 / r16
            goto L64
        L62:
            r2 = 0
        L64:
            long r12 = r12 + r2
            long r8 = r8 + r14
            int r14 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r14 <= 0) goto L6b
            r10 = r2
        L6b:
            int r5 = r5 + 1
            r2 = 0
            goto L35
        L70:
            int r0 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r0 <= 0) goto L78
            long r12 = r12 - r10
            long r8 = r8 - r14
            long r10 = r12 / r8
        L78:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.f.h.d(com.baidu.location.f.p):long");
    }

    public String d(com.baidu.location.f.a aVar) {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append(aVar.b + 23);
        stringBuffer.append("H");
        stringBuffer.append(aVar.a + 45);
        stringBuffer.append("K");
        stringBuffer.append(aVar.d + 54);
        stringBuffer.append("Q");
        stringBuffer.append(aVar.c + CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
        return stringBuffer.toString();
    }

    public synchronized void d() {
        c();
        this.g = null;
        this.i = null;
        this.f = null;
    }

    public boolean e() {
        k kVar = this.e;
        if (kVar != null) {
            return kVar.d();
        }
        return false;
    }

    public synchronized com.baidu.location.f.a f() {
        if (this.e == null) {
            return null;
        }
        a(this.g, s.aJ, s.aK, s.aL, s.aM);
        com.baidu.location.f.a aVarD = this.e.d(30000);
        this.q = a(aVarD, 30000, com.baidu.location.b.e.a().dI);
        return aVarD;
    }

    public int g() {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = this.h;
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return 0;
            }
            return activeNetworkInfo.getSubtype();
        } catch (Exception unused) {
            return 0;
        }
    }

    public String h() {
        int simState = -1;
        try {
            TelephonyManager telephonyManager = this.g;
            if (telephonyManager != null) {
                simState = telephonyManager.getSimState();
            }
        } catch (Exception unused) {
        }
        return "&sim=" + simState;
    }

    public void i() {
        this.s = 0L;
    }

    public boolean j() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = this.m;
        if (jCurrentTimeMillis - j > 0 && jCurrentTimeMillis - j <= 5000) {
            return false;
        }
        this.m = jCurrentTimeMillis;
        i();
        return k();
    }

    public boolean k() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        k kVar = this.e;
        long jC = kVar != null ? kVar.c() : 0L;
        long j = jCurrentTimeMillis - jC;
        if (j > 0) {
            long j2 = this.s;
            if (j <= j2 + 5000 || jCurrentTimeMillis - (this.n * 1000) <= j2 + 5000) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 28 && j < 25000) {
                return false;
            }
            if (m() && !n() && j <= this.s + WorkRequest.MIN_BACKOFF_MILLIS) {
                return false;
            }
        }
        return a(jC);
    }

    public long l() {
        k kVar = this.e;
        if (kVar != null) {
            return kVar.c();
        }
        return 0L;
    }

    public boolean m() {
        try {
            ConnectivityManager connectivityManager = this.h;
            if (connectivityManager != null) {
                return connectivityManager.getNetworkInfo(1).isConnected();
            }
            return false;
        } catch (Error | Exception unused) {
            return false;
        }
    }

    public boolean n() {
        return false;
    }

    public List<WifiConfiguration> o() {
        try {
            WifiManager wifiManager = this.i;
            if (wifiManager != null) {
                return wifiManager.getConfiguredNetworks();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String p() {
        WifiManager wifiManager = this.i;
        if (wifiManager == null) {
            return "";
        }
        try {
            if (!wifiManager.isWifiEnabled()) {
                if (Build.VERSION.SDK_INT <= 17) {
                    return "";
                }
                if (!this.i.isScanAlwaysAvailable()) {
                    return "";
                }
            }
            return "&wifio=1";
        } catch (Exception | NoSuchMethodError unused) {
            return "";
        }
    }

    public String q() {
        if (this.e == null || s.f == 4) {
            return null;
        }
        return this.e.e();
    }

    public WifiInfo r() {
        k kVar;
        if (s.f == 4 || (kVar = this.e) == null) {
            return null;
        }
        return kVar.f();
    }

    public boolean s() {
        try {
            if (!this.i.isWifiEnabled()) {
                if (Build.VERSION.SDK_INT > 17) {
                    if (this.i.isScanAlwaysAvailable()) {
                    }
                }
                return false;
            }
            return true;
        } catch (Exception | NoSuchMethodError unused) {
            return false;
        }
    }

    public String t() {
        p pVarU;
        if (com.baidu.location.b.e.a().dl == 0 || (pVarU = u()) == null || pVarU.a() < 1) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (ScanResult scanResult : pVarU.a) {
            if (scanResult != null && scanResult.level != 0 && scanResult.BSSID != null) {
                arrayList.add(scanResult);
            }
        }
        Collections.sort(arrayList, new c());
        int iMin = Math.min(com.baidu.location.b.e.a().dn, arrayList.size());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iMin; i++) {
            String strReplace = ((ScanResult) arrayList.get(i)).BSSID.replace(":", "");
            String strB = b(((ScanResult) arrayList.get(i)).SSID);
            int i2 = ((ScanResult) arrayList.get(i)).level;
            if (i2 < 0) {
                i2 = -i2;
            }
            sb.append(strReplace + "," + strB + "," + i2);
            sb.append(";");
        }
        return sb.toString();
    }

    public p u() {
        return (this.e == null || s.f == 4) ? new p(null, 0L) : this.e.g();
    }
}
