package com.realsil.customer.core.c;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.ParcelUuid;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.realsil.customer.core.RtkCore;
import com.realsil.customer.core.bluetooth.BluetoothProfileManager;
import com.realsil.customer.core.bluetooth.impl.BluetoothClassImpl;
import com.realsil.customer.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.customer.core.bluetooth.scanner.ExtendedBluetoothDevice;
import com.realsil.customer.core.bluetooth.scanner.ScannerCallback;
import com.realsil.customer.core.bluetooth.scanner.ScannerParams;
import com.realsil.customer.core.bluetooth.utils.BluetoothHelper;
import com.realsil.customer.core.bluetooth.utils.BluetoothUuid;
import com.realsil.customer.core.logger.ZLogger;
import com.realsil.customer.core.utility.DataConverter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/c/a.class */
public abstract class a {
    public static final int STATE_IDLE = 0;
    public static final int STATE_DISCOVERY_START_PROCESS = 1;
    public static final int STATE_DISCOVERY_STARTED = 2;
    public static final int STATE_DISCOVERY_FINISHED = 3;
    public Context c;
    public ScannerParams d;
    public ScannerCallback e;
    public HandlerThread f;
    public Handler g;
    public BluetoothAdapter h;
    public boolean a = false;
    public boolean b = false;
    public int i = 0;
    public boolean j = false;
    public long k = 0;
    public final C0004a l = new C0004a();
    public final b m = new b();
    public final c n = new c();
    public boolean o = false;
    public final d p = new d();

    /* renamed from: com.realsil.customer.core.c.a$a, reason: collision with other inner class name */
    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/c/a$a.class */
    public class C0004a extends BroadcastReceiver {

        /* renamed from: com.realsil.customer.core.c.a$a$a, reason: collision with other inner class name */
        /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/c/a$a$a.class */
        public class RunnableC0005a implements Runnable {
            public RunnableC0005a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                a.this.stopScan();
            }
        }

        public C0004a() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                ZLogger.v(String.format(Locale.US, "[%s] %d -> %d", action, Integer.valueOf(intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", -1)), Integer.valueOf(intExtra)));
                if (intExtra == 10 && a.this.isScanning()) {
                    new Thread(new RunnableC0005a()).start();
                }
            }
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/c/a$b.class */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            a aVar = a.this;
            if (timeInMillis < aVar.k) {
                aVar.k = 0L;
            }
            long j = timeInMillis - aVar.k;
            int i = aVar.i;
            if (i == 1) {
                if (j > 30000) {
                    ZLogger.d(String.format(Locale.US, "no scan response received after start scan for %d ms", 30000L));
                    a.this.e();
                    return;
                }
                return;
            }
            if (i != 2) {
                ZLogger.v(aVar.b, "ignore state:" + a.this.i);
                a aVar2 = a.this;
                Handler handler = aVar2.g;
                if (handler == null) {
                    ZLogger.v(aVar2.b, "mHandler == null");
                    return;
                } else {
                    handler.removeCallbacksAndMessages(aVar2.n);
                    aVar2.g.postDelayed(aVar2.n, aVar2.d.getScanPeriod());
                    return;
                }
            }
            if (j > 30000) {
                ZLogger.d(String.format(Locale.US, "exceed %d ms , no scan response received since last time", 30000L));
                a.this.e();
                return;
            }
            Handler handler2 = aVar.g;
            if (handler2 == null) {
                ZLogger.v(aVar.b, "mHandler == null");
            } else {
                handler2.removeCallbacksAndMessages(aVar.n);
                aVar.g.postDelayed(aVar.n, aVar.d.getScanPeriod());
            }
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/c/a$c.class */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ZLogger.v("scan delay time reached");
            a.this.e();
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/c/a$d.class */
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

    public boolean b() {
        if (this.j) {
            ZLogger.w("please call onDestroy() method first");
            return false;
        }
        this.a = RtkCore.DEBUG;
        this.b = RtkCore.VDBG;
        this.h = com.realsil.customer.core.a.a.a(this.c);
        if (this.d == null) {
            ZLogger.v(this.b, "create new ScannerParams");
            this.d = new ScannerParams();
        }
        if (this.g == null) {
            HandlerThread handlerThread = new HandlerThread("ScannerPresenter");
            this.f = handlerThread;
            handlerThread.start();
            this.g = new Handler(this.f.getLooper());
        }
        if (this.e == null) {
            ZLogger.v(this.b, "callback is null");
        }
        this.c.registerReceiver(this.l, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        this.j = true;
        ZLogger.v(this.a, "scan presenter initialized");
        return true;
    }

    public void setScannerParams(ScannerParams scannerParams) {
        this.d = scannerParams;
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

    public abstract boolean startScan();

    public final boolean d() {
        if (!this.j) {
            ZLogger.w("presenter not initialized");
            return false;
        }
        if (!isBluetoothEnabled()) {
            ZLogger.w("Bluetooth not enabled, ignore scan process.");
            return false;
        }
        int i = this.i;
        if (i == 1 || i == 2) {
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            if (timeInMillis < this.k) {
                this.k = 0L;
            }
            if (timeInMillis - this.k <= 30000) {
                c();
                return false;
            }
            ZLogger.d(String.format(Locale.US, "exceed %d ms , no scan response received since last time", 30000L));
            e();
            return false;
        }
        a(1);
        Handler handler = this.g;
        if (handler != null) {
            handler.removeCallbacks(this.n);
            this.g.removeCallbacks(this.m);
            this.g.removeCallbacks(this.p);
        }
        this.k = 0L;
        ScannerParams scannerParams = this.d;
        if (scannerParams != null) {
            this.o = scannerParams.isAutoDiscovery();
            return true;
        }
        this.o = false;
        return true;
    }

    public boolean stopScan() {
        this.o = false;
        if (!this.j) {
            ZLogger.w("presenter not initialized");
            return false;
        }
        Handler handler = this.g;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        return e();
    }

    public abstract boolean e();

    public synchronized boolean scanDevice(boolean z) {
        return z ? startScan() : stopScan();
    }

    public void onDestroy() {
        ZLogger.d(this.a, "onDestroy");
        Context context = this.c;
        if (context != null) {
            try {
                context.unregisterReceiver(this.l);
            } catch (Exception unused) {
            }
        }
        this.e = null;
        stopScan();
        Handler handler = this.g;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.g = null;
        }
        HandlerThread handlerThread = this.f;
        if (handlerThread != null) {
            handlerThread.quit();
            this.f = null;
        }
        this.j = false;
        ZLogger.v("scan presenter destroyed");
    }

    public BluetoothAdapter getBluetoothAdapter() {
        return this.h;
    }

    public boolean isBluetoothSupported() {
        return this.h != null;
    }

    public boolean isBluetoothEnabled() {
        BluetoothAdapter bluetoothAdapter = this.h;
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    public int getState() {
        return this.i;
    }

    public boolean isScanning() {
        int i = this.i;
        return i == 2 || i == 1;
    }

    public final void a(int i) {
        int i2 = this.i;
        if (i2 != i) {
            if (this.a) {
                ZLogger.d(String.format(Locale.US, "ScanState 0x%02X >> 0x%02X", Integer.valueOf(i2), Integer.valueOf(i)));
            }
            this.i = i;
            ScannerCallback scannerCallback = this.e;
            if (scannerCallback != null) {
                scannerCallback.onScanStateChanged(i);
            } else {
                ZLogger.v(this.b, "no callback registered");
            }
        }
        int i3 = this.i;
        if (i3 == 0 || i3 == 3) {
            Handler handler = this.g;
            if (handler != null) {
                handler.removeCallbacks(this.n);
                this.g.removeCallbacks(this.m);
                this.g.removeCallbacks(this.p);
            }
            boolean z = this.o;
            if (!z) {
                if (this.b) {
                    ZLogger.v(String.format("continousScanEnabled=%b", Boolean.valueOf(z)));
                }
            } else if (this.g != null) {
                ZLogger.v(this.a, "wait to start auto scan");
                this.g.postDelayed(this.p, this.d.getAutoScanDelay());
            }
        }
    }

    public List<ExtendedBluetoothDevice> getPairedDevices() {
        ArrayList arrayList = new ArrayList();
        if (this.h == null) {
            return arrayList;
        }
        if (!this.d.isReusePairedDeviceEnabled()) {
            ZLogger.v(this.a, "don't reuse paired device");
            return arrayList;
        }
        for (BluetoothDevice bluetoothDevice : this.h.getBondedDevices()) {
            ZLogger.v(BluetoothHelper.dumpBluetoothDevice(bluetoothDevice));
            if (a(bluetoothDevice)) {
                arrayList.add(new ExtendedBluetoothDevice(bluetoothDevice, bluetoothDevice.getName(), ExtendedBluetoothDevice.NO_RSSI, bluetoothDevice.getBondState() == 12, false));
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
        BluetoothAdapter bluetoothAdapter = this.h;
        if (bluetoothAdapter != null) {
            for (BluetoothDevice bluetoothDevice : bluetoothAdapter.getBondedDevices()) {
                if (BluetoothUuid.containsAnyUuid(bluetoothDevice.getUuids(), parcelUuidArr)) {
                    boolean zIsConnected = BluetoothDeviceImpl.isConnected(bluetoothDevice);
                    boolean z = zIsConnected;
                    if (!zIsConnected) {
                        z = BluetoothProfileManager.getInstance().getConnectionState(1, bluetoothDevice) == 2 || BluetoothProfileManager.getInstance().getConnectionState(2, bluetoothDevice) == 2;
                    }
                    arrayList.add(new ExtendedBluetoothDevice(bluetoothDevice, bluetoothDevice.getName(), ExtendedBluetoothDevice.NO_RSSI, bluetoothDevice.getBondState() == 12, z));
                }
            }
        }
        return arrayList;
    }

    public abstract boolean a(@NonNull BluetoothDevice bluetoothDevice);

    public void a() {
    }

    public final void c() {
        if (this.g == null) {
            ZLogger.v(this.b, "mHandler == null");
            return;
        }
        ZLogger.v(this.b, String.format(Locale.US, "wait to check scan period(%d)", 30000L));
        this.g.removeCallbacks(this.m);
        this.g.postDelayed(this.m, 30000L);
    }

    public synchronized boolean scanDevice(boolean z, boolean z2) {
        if (z) {
            return startScan();
        }
        return stopScan();
    }

    public List<ExtendedBluetoothDevice> getPairedDevices(int i) {
        ArrayList arrayList = new ArrayList();
        BluetoothAdapter bluetoothAdapter = this.h;
        if (bluetoothAdapter == null) {
            return arrayList;
        }
        for (BluetoothDevice bluetoothDevice : bluetoothAdapter.getBondedDevices()) {
            if (bluetoothDevice.getBluetoothClass().getMajorDeviceClass() == i || (1024 == i && BluetoothClassImpl.doesClassMatch(bluetoothDevice.getBluetoothClass(), 0))) {
                boolean z = false;
                if (1024 == i) {
                    int connectionState = BluetoothProfileManager.getInstance().getConnectionState(1, bluetoothDevice);
                    int connectionState2 = BluetoothProfileManager.getInstance().getConnectionState(2, bluetoothDevice);
                    ZLogger.v(String.format(Locale.US, "%s, hfpState= %d,a2dpState= %d", bluetoothDevice.getAddress(), Integer.valueOf(connectionState), Integer.valueOf(connectionState2)));
                    z = 2 == connectionState || 2 == connectionState2;
                }
                arrayList.add(new ExtendedBluetoothDevice(bluetoothDevice, bluetoothDevice.getName(), ExtendedBluetoothDevice.NO_RSSI, bluetoothDevice.getBondState() == 12, z));
            }
        }
        return arrayList;
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
            if (!this.a) {
                return false;
            }
            ZLogger.v(String.format("conflict name: %s", name));
            return false;
        }
        if (!TextUtils.isEmpty(name) || this.d.isNameNullable()) {
            return true;
        }
        if (!this.b) {
            return false;
        }
        ZLogger.v("name is null, ignore");
        return false;
    }

    public final void a(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        boolean zA;
        this.k = Calendar.getInstance().getTimeInMillis();
        int i2 = this.i;
        if (i2 == 1) {
            a(2);
        } else if (i2 != 2) {
            ZLogger.v(String.format("stop to calibration state: 0x%04X", Integer.valueOf(i2)));
            e();
            return;
        }
        if (bluetoothDevice == null) {
            ZLogger.d("ignore, device is null");
            return;
        }
        if (this.d.getRssiFilter() > -1000 && this.d.getRssiFilter() > i) {
            ZLogger.w("filter, low rssi:" + i);
            zA = false;
        } else {
            zA = a(bluetoothDevice);
        }
        if (zA) {
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
        }
    }
}
