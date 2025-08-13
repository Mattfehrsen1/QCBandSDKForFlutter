package com.realsil.customer.core.bluetooth.scanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.realsil.customer.core.bluetooth.utils.BluetoothHelper;
import com.realsil.customer.core.e.a;
import com.realsil.customer.core.e.c;
import com.realsil.customer.core.logger.ZLogger;
import com.realsil.customer.core.utility.DataConverter;
import java.util.List;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/scanner/LeScannerPresenter.class */
public final class LeScannerPresenter extends com.realsil.customer.core.c.a {
    public c q;
    public final a r;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/scanner/LeScannerPresenter$a.class */
    public class a implements a.InterfaceC0006a {
        public a() {
        }
    }

    public LeScannerPresenter(Context context) {
        this(context, null, null, null);
    }

    @Override // com.realsil.customer.core.c.a
    public final boolean b() {
        if (!super.b()) {
            return false;
        }
        this.q = new c(this.c, Build.VERSION.SDK_INT);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v20, types: [com.realsil.customer.core.e.a] */
    /* JADX WARN: Type inference failed for: r0v7, types: [com.realsil.customer.core.e.c] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Throwable] */
    @Override // com.realsil.customer.core.c.a
    public boolean startScan() {
        if (!d()) {
            return true;
        }
        if (this.b) {
            ZLogger.v(this.a, "startDiscovery for " + this.d.toString());
        } else {
            ZLogger.v(this.a, "startDiscovery for " + this.d.getScanPeriod() + "ms");
        }
        ?? r0 = this.q;
        a aVar = this.r;
        synchronized (r0) {
            com.realsil.customer.core.e.a aVar2 = r0.a;
            if (aVar2 != null) {
                r0 = aVar2;
                r0.f = aVar;
            }
        }
        if (this.q.a.a(this.d, true)) {
            c();
            ZLogger.v("");
            return true;
        }
        ZLogger.v("scanLeDevice failed");
        stopScan();
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.realsil.customer.core.e.c] */
    /* JADX WARN: Type inference failed for: r0v16, types: [com.realsil.customer.core.e.a] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    @Override // com.realsil.customer.core.c.a
    public final boolean e() {
        ?? r0 = this.q;
        synchronized (r0) {
            com.realsil.customer.core.e.a aVar = r0.a;
            if (aVar != null) {
                r0 = aVar;
                r0.f = null;
            }
        }
        if (this.q.a.d) {
            ZLogger.v(this.b, "stop the le scan process");
            if (!this.q.a.a(null, false)) {
                ZLogger.w("scanLeDevice failed");
                return false;
            }
        }
        a(0);
        return true;
    }

    @Override // com.realsil.customer.core.c.a
    public final boolean a(@NonNull BluetoothDevice bluetoothDevice) {
        if (Build.VERSION.SDK_INT >= 18) {
            int type = bluetoothDevice.getType();
            if (this.d.getScanMode() != 18) {
                this.d.getScanMode();
            } else if (type != 2) {
                if (!this.b) {
                    return false;
                }
                ZLogger.v(String.format(Locale.US, "filter, invalid type: %d, expect type is %d", Integer.valueOf(type), 2));
                return false;
            }
        }
        if (!b(bluetoothDevice)) {
            return false;
        }
        if (TextUtils.isEmpty(this.d.getAddressFilter()) || DataConverter.equals(this.d.getAddressFilter(), bluetoothDevice.getAddress())) {
            return true;
        }
        if (!this.b) {
            return false;
        }
        ZLogger.v("address not match:" + BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true));
        return false;
    }

    @Override // com.realsil.customer.core.c.a
    public final void a() {
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ List getPairedDevicesByProfile(int i) {
        return super.getPairedDevicesByProfile(i);
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ List getPairedDevices() {
        return super.getPairedDevices();
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ boolean isScanning() {
        return super.isScanning();
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ int getState() {
        return super.getState();
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ boolean isBluetoothEnabled() {
        return super.isBluetoothEnabled();
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ boolean isBluetoothSupported() {
        return super.isBluetoothSupported();
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ BluetoothAdapter getBluetoothAdapter() {
        return super.getBluetoothAdapter();
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ void onDestroy() {
        super.onDestroy();
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ boolean scanDevice(boolean z) {
        return super.scanDevice(z);
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ boolean stopScan() {
        return super.stopScan();
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ void setScannerCallback(ScannerCallback scannerCallback) {
        super.setScannerCallback(scannerCallback);
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ void setScanMode(int i) {
        super.setScanMode(i);
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ void setScannerParams(ScannerParams scannerParams) {
        super.setScannerParams(scannerParams);
    }

    public LeScannerPresenter(Context context, ScannerParams scannerParams, ScannerCallback scannerCallback) {
        this(context, null, scannerParams, scannerCallback);
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ List getPairedDevices(int i) {
        return super.getPairedDevices(i);
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ boolean scanDevice(boolean z, boolean z2) {
        return super.scanDevice(z, z2);
    }

    public LeScannerPresenter(Context context, Handler handler, ScannerParams scannerParams, ScannerCallback scannerCallback) {
        this.r = new a();
        this.c = context.getApplicationContext();
        this.g = handler;
        this.d = scannerParams;
        this.e = scannerCallback;
        b();
    }
}
