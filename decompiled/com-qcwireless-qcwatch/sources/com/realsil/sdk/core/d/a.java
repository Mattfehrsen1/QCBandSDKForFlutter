package com.realsil.sdk.core.d;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.ParcelUuid;
import android.text.TextUtils;
import androidx.work.WorkRequest;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.bluetooth.BluetoothProfileManager;
import com.realsil.sdk.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.sdk.core.bluetooth.scanner.ExtendedBluetoothDevice;
import com.realsil.sdk.core.bluetooth.scanner.ScannerCallback;
import com.realsil.sdk.core.bluetooth.scanner.ScannerParams;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.bluetooth.utils.BluetoothUuid;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public abstract class a {
    public static final int STATE_DISCOVERY_FINISHED = 3;
    public static final int STATE_DISCOVERY_STARTED = 2;
    public static final int STATE_DISCOVERY_START_PROCESS = 1;
    public static final int STATE_IDLE = 0;
    public Context c;
    public ScannerParams d;
    public ScannerCallback e;
    public Handler f;
    public BluetoothAdapter g;
    public boolean a = false;
    public boolean b = false;
    public int h = 0;
    public boolean i = false;
    public long j = 0;
    public final C0235a k = new C0235a();
    public final b l = new b();
    public final c m = new c();
    public boolean n = false;
    public final d o = new d();

    /* renamed from: com.realsil.sdk.core.d.a$a, reason: collision with other inner class name */
    public class C0235a extends BroadcastReceiver {

        /* renamed from: com.realsil.sdk.core.d.a$a$a, reason: collision with other inner class name */
        public class RunnableC0236a implements Runnable {
            public RunnableC0236a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                a.this.stopScan();
            }
        }

        public C0235a() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                ZLogger.v(String.format(Locale.US, "[%s] %d -> %d", action, Integer.valueOf(intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", -1)), Integer.valueOf(intExtra)));
                if (intExtra == 10 && a.this.isScanning()) {
                    new Thread(new RunnableC0236a()).start();
                }
            }
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            a aVar = a.this;
            if (timeInMillis < aVar.j) {
                aVar.j = 0L;
            }
            long j = timeInMillis - aVar.j;
            int i = aVar.h;
            if (i == 1) {
                if (j > WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS) {
                    ZLogger.d(String.format(Locale.US, "no scan response received after start scan for %d ms", Long.valueOf(WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS)));
                    a.this.e();
                    return;
                }
                return;
            }
            if (i == 2) {
                if (j <= WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS) {
                    a.a(aVar);
                    return;
                } else {
                    ZLogger.d(String.format(Locale.US, "exceed %d ms , no scan response received since last time", Long.valueOf(WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS)));
                    a.this.e();
                    return;
                }
            }
            boolean z = aVar.b;
            StringBuilder sbA = com.realsil.sdk.core.a.a.a("ignore state:");
            sbA.append(a.this.h);
            ZLogger.v(z, sbA.toString());
            a.a(a.this);
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ZLogger.v("scan delay time reached");
            a.this.e();
        }
    }

    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            a aVar = a.this;
            ScannerCallback scannerCallback = aVar.e;
            if (scannerCallback != null) {
                scannerCallback.onAutoScanTrigger();
            } else {
                ZLogger.v(aVar.b, "no callback registered");
            }
            a.this.startScan();
        }
    }

    public void a() {
    }

    public final void a(int i) {
        int i2 = this.h;
        if (i2 != i) {
            if (this.a) {
                ZLogger.d(String.format(Locale.US, "ScanState 0x%02X >> 0x%02X", Integer.valueOf(i2), Integer.valueOf(i)));
            }
            this.h = i;
            ScannerCallback scannerCallback = this.e;
            if (scannerCallback != null) {
                scannerCallback.onScanStateChanged(i);
            } else {
                ZLogger.v(this.b, "no callback registered");
            }
        }
        int i3 = this.h;
        if (i3 == 0 || i3 == 3) {
            Handler handler = this.f;
            if (handler != null) {
                handler.removeCallbacks(this.m);
                this.f.removeCallbacks(this.l);
                this.f.removeCallbacks(this.o);
            }
            boolean z = this.n;
            if (!z) {
                if (this.b) {
                    ZLogger.v(String.format("continousScanEnabled=%b", Boolean.valueOf(z)));
                }
            } else if (this.f != null) {
                ZLogger.v(this.a, "wait to start auto scan");
                this.f.postDelayed(this.o, this.d.getAutoScanDelay());
            }
        }
    }

    public abstract boolean a(BluetoothDevice bluetoothDevice);

    public boolean b() {
        if (this.i) {
            ZLogger.w("please call onDestroy() method first");
            return false;
        }
        this.a = RtkCore.DEBUG;
        this.b = RtkCore.VDBG;
        if (Build.VERSION.SDK_INT >= 18) {
            BluetoothManager bluetoothManager = (BluetoothManager) this.c.getSystemService("bluetooth");
            if (bluetoothManager != null) {
                this.g = bluetoothManager.getAdapter();
            }
        } else {
            this.g = BluetoothAdapter.getDefaultAdapter();
        }
        if (this.d == null) {
            ZLogger.v(this.b, "create new ScannerParams");
            this.d = new ScannerParams();
        }
        if (this.f == null) {
            HandlerThread handlerThread = new HandlerThread("ScannerPresenter");
            handlerThread.start();
            this.f = new Handler(handlerThread.getLooper());
        }
        if (this.e == null) {
            ZLogger.v(this.b, "callback is null");
        }
        this.c.registerReceiver(this.k, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        this.i = true;
        ZLogger.v("initialized");
        return true;
    }

    public final boolean c() {
        if (this.f == null) {
            ZLogger.v(this.b, "mHandler == null");
            return false;
        }
        ZLogger.v(this.b, String.format(Locale.US, "wait to check scan period(%d)", Long.valueOf(WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS)));
        this.f.removeCallbacks(this.l);
        return this.f.postDelayed(this.l, WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS);
    }

    public final boolean d() {
        if (!this.i) {
            ZLogger.w("presenter not initialized");
            return false;
        }
        if (!isBluetoothEnabled()) {
            ZLogger.w("Bluetooth not enabled, ignore scan process.");
            return false;
        }
        int i = this.h;
        if (i == 1 || i == 2) {
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            if (timeInMillis < this.j) {
                this.j = 0L;
            }
            if (timeInMillis - this.j > WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS) {
                ZLogger.d(String.format(Locale.US, "exceed %d ms , no scan response received since last time", Long.valueOf(WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS)));
                e();
            } else {
                c();
            }
            return false;
        }
        a(1);
        Handler handler = this.f;
        if (handler != null) {
            handler.removeCallbacks(this.m);
            this.f.removeCallbacks(this.l);
            this.f.removeCallbacks(this.o);
        }
        this.j = 0L;
        ScannerParams scannerParams = this.d;
        if (scannerParams != null) {
            this.n = scannerParams.isAutoDiscovery();
        } else {
            this.n = false;
        }
        return true;
    }

    public abstract boolean e();

    public BluetoothAdapter getBluetoothAdapter() {
        return this.g;
    }

    public List<ExtendedBluetoothDevice> getPairedDevices() {
        ArrayList arrayList = new ArrayList();
        if (this.g == null) {
            return arrayList;
        }
        if (!this.d.isReusePairedDeviceEnabled()) {
            ZLogger.v(this.a, "don't reuse paired device");
            return arrayList;
        }
        for (BluetoothDevice bluetoothDevice : this.g.getBondedDevices()) {
            ZLogger.v(BluetoothHelper.dumpBluetoothDevice(bluetoothDevice));
            if (a(bluetoothDevice)) {
                arrayList.add(new ExtendedBluetoothDevice(bluetoothDevice, bluetoothDevice.getName(), -1000, bluetoothDevice.getBondState() == 12, false));
            }
        }
        return arrayList;
    }

    public List<ExtendedBluetoothDevice> getPairedDevicesByProfile(int i) throws NoSuchMethodException, SecurityException {
        if (i != 1) {
            return getPairedDevices();
        }
        ParcelUuid[] parcelUuidArr = BluetoothUuid.HEADSET_PROFILE_UUIDS;
        ArrayList arrayList = new ArrayList();
        BluetoothAdapter bluetoothAdapter = this.g;
        if (bluetoothAdapter != null) {
            for (BluetoothDevice bluetoothDevice : bluetoothAdapter.getBondedDevices()) {
                if (BluetoothUuid.containsAnyUuid(bluetoothDevice.getUuids(), parcelUuidArr)) {
                    boolean zIsConnected = BluetoothDeviceImpl.isConnected(bluetoothDevice);
                    arrayList.add(new ExtendedBluetoothDevice(bluetoothDevice, bluetoothDevice.getName(), -1000, bluetoothDevice.getBondState() == 12, zIsConnected ? zIsConnected : BluetoothProfileManager.getInstance().getConnectionState(1, bluetoothDevice) == 2 || BluetoothProfileManager.getInstance().getConnectionState(2, bluetoothDevice) == 2));
                }
            }
        }
        return arrayList;
    }

    public int getState() {
        return this.h;
    }

    public boolean isBluetoothEnabled() {
        BluetoothAdapter bluetoothAdapter = this.g;
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    public boolean isBluetoothSupported() {
        return this.g != null;
    }

    public boolean isScanning() {
        int i = this.h;
        return i == 2 || i == 1;
    }

    public void onDestroy() {
        ZLogger.d("onDestroy");
        Context context = this.c;
        if (context != null) {
            try {
                context.unregisterReceiver(this.k);
            } catch (Exception e) {
                ZLogger.w(this.b, e.toString());
            }
        }
        this.e = null;
        stopScan();
        Handler handler = this.f;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.i = false;
        ZLogger.d("scan presenter destroyed");
    }

    public synchronized boolean scanDevice(boolean z) {
        if (z) {
            return startScan();
        }
        return stopScan();
    }

    public void setScanMode(int i) {
        ScannerParams scannerParams = this.d;
        if (scannerParams != null) {
            scannerParams.setScanMode(i);
        }
    }

    public void setScannerCallback(ScannerCallback scannerCallback) {
        this.e = scannerCallback;
        if (scannerCallback == null) {
            ZLogger.v(this.b, "callback is null");
        }
    }

    public void setScannerParams(ScannerParams scannerParams) {
        this.d = scannerParams;
    }

    public abstract boolean startScan();

    public boolean stopScan() {
        this.n = false;
        if (!this.i) {
            ZLogger.w("presenter not initialized");
            return false;
        }
        Handler handler = this.f;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        return e();
    }

    public synchronized boolean scanDevice(boolean z, boolean z2) {
        if (z) {
            return startScan();
        }
        return stopScan();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0073  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<com.realsil.sdk.core.bluetooth.scanner.ExtendedBluetoothDevice> getPairedDevices(int r12) {
        /*
            r11 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            android.bluetooth.BluetoothAdapter r1 = r11.g
            if (r1 != 0) goto La
            return r0
        La:
            java.util.Set r1 = r1.getBondedDevices()
            java.util.Iterator r1 = r1.iterator()
        L12:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L91
            java.lang.Object r2 = r1.next()
            r4 = r2
            android.bluetooth.BluetoothDevice r4 = (android.bluetooth.BluetoothDevice) r4
            android.bluetooth.BluetoothClass r2 = r4.getBluetoothClass()
            int r2 = r2.getMajorDeviceClass()
            r3 = 1024(0x400, float:1.435E-42)
            r5 = 0
            if (r2 == r12) goto L39
            if (r3 != r12) goto L12
            android.bluetooth.BluetoothClass r2 = r4.getBluetoothClass()
            boolean r2 = com.realsil.sdk.core.bluetooth.impl.BluetoothClassImpl.doesClassMatch(r2, r5)
            if (r2 != 0) goto L39
            goto L12
        L39:
            r2 = 1
            if (r3 != r12) goto L73
            com.realsil.sdk.core.bluetooth.BluetoothProfileManager r3 = com.realsil.sdk.core.bluetooth.BluetoothProfileManager.getInstance()
            int r3 = r3.getConnectionState(r2, r4)
            com.realsil.sdk.core.bluetooth.BluetoothProfileManager r6 = com.realsil.sdk.core.bluetooth.BluetoothProfileManager.getInstance()
            r7 = 2
            int r6 = r6.getConnectionState(r7, r4)
            java.util.Locale r8 = java.util.Locale.US
            r9 = 3
            java.lang.Object[] r9 = new java.lang.Object[r9]
            java.lang.String r10 = r4.getAddress()
            r9[r5] = r10
            java.lang.Integer r10 = java.lang.Integer.valueOf(r3)
            r9[r2] = r10
            java.lang.Integer r10 = java.lang.Integer.valueOf(r6)
            r9[r7] = r10
            java.lang.String r10 = "%s, hfpState= %d,a2dpState= %d"
            java.lang.String r8 = java.lang.String.format(r8, r10, r9)
            com.realsil.sdk.core.logger.ZLogger.v(r8)
            if (r7 == r3) goto L71
            if (r7 != r6) goto L73
        L71:
            r8 = 1
            goto L74
        L73:
            r8 = 0
        L74:
            com.realsil.sdk.core.bluetooth.scanner.ExtendedBluetoothDevice r9 = new com.realsil.sdk.core.bluetooth.scanner.ExtendedBluetoothDevice
            java.lang.String r6 = r4.getName()
            r7 = -1000(0xfffffffffffffc18, float:NaN)
            int r3 = r4.getBondState()
            r10 = 12
            if (r3 != r10) goto L85
            goto L86
        L85:
            r2 = 0
        L86:
            r3 = r9
            r5 = r6
            r6 = r7
            r7 = r2
            r3.<init>(r4, r5, r6, r7, r8)
            r0.add(r9)
            goto L12
        L91:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.d.a.getPairedDevices(int):java.util.List");
    }

    public final boolean a(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        boolean zA;
        this.j = Calendar.getInstance().getTimeInMillis();
        int i2 = this.h;
        if (i2 == 1) {
            a(2);
        } else if (i2 != 2) {
            ZLogger.v(String.format("stop to calibration state: 0x%04X", Integer.valueOf(i2)));
            e();
            return false;
        }
        if (bluetoothDevice == null) {
            ZLogger.d("ignore, device is null");
            return false;
        }
        if (this.d.getRssiFilter() > -1000 && this.d.getRssiFilter() > i) {
            ZLogger.w("filter, low rssi:" + i);
            zA = false;
        } else {
            zA = a(bluetoothDevice);
        }
        if (!zA) {
            return false;
        }
        ExtendedBluetoothDevice extendedBluetoothDevice = new ExtendedBluetoothDevice(bluetoothDevice, bluetoothDevice.getName(), i, bluetoothDevice.getBondState() == 12, false, bArr);
        a();
        ScannerCallback scannerCallback = this.e;
        if (scannerCallback != null) {
            scannerCallback.onNewDevice(extendedBluetoothDevice);
        } else {
            ZLogger.v(this.b, "no callback registered");
        }
        if (this.d.getScanMechanism() == 1) {
            ZLogger.d("SCAN_MECHANISM_FILTER_ONE > scanDevice(false)");
            e();
        }
        return true;
    }

    public final boolean b(BluetoothDevice bluetoothDevice) {
        String name = bluetoothDevice.getName();
        if (!TextUtils.isEmpty(this.d.getNameFilter())) {
            if (TextUtils.isEmpty(name)) {
                return false;
            }
            if (DataConverter.equals(this.d.getNameFilter(), name)) {
                return true;
            }
            if (this.d.isNameFuzzyMatchEnable() && name.contains(this.d.getNameFilter())) {
                return true;
            }
            if (this.a) {
                ZLogger.v(String.format("conflict name: %s", name));
            }
            return false;
        }
        if (!TextUtils.isEmpty(name) || this.d.isNameNullable()) {
            return true;
        }
        if (this.b) {
            ZLogger.v("name is null, ignore");
        }
        return false;
    }

    public static void a(a aVar) {
        Handler handler = aVar.f;
        if (handler != null) {
            handler.removeCallbacksAndMessages(aVar.m);
            aVar.f.postDelayed(aVar.m, aVar.d.getScanPeriod());
        } else {
            ZLogger.v(aVar.b, "mHandler == null");
        }
    }
}
