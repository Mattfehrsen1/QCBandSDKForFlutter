package com.realsil.sdk.core.bluetooth.scanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.f.a;
import com.realsil.sdk.core.f.c;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class LeScannerPresenter extends com.realsil.sdk.core.d.a {
    public c p;
    public final a q;

    public class a implements a.InterfaceC0237a {
        public a() {
        }
    }

    public LeScannerPresenter(Context context) {
        this(context, null, null, null);
    }

    @Override // com.realsil.sdk.core.d.a
    public final void a() {
    }

    @Override // com.realsil.sdk.core.d.a
    public final boolean a(BluetoothDevice bluetoothDevice) {
        if (Build.VERSION.SDK_INT >= 18) {
            if (this.d.getScanMode() != 18) {
                this.d.getScanMode();
            } else if (bluetoothDevice.getType() != 2) {
                if (this.b) {
                    ZLogger.v(String.format(Locale.US, "filter, invalid type: %d, expect type is %d", Integer.valueOf(bluetoothDevice.getType()), 2));
                }
                return false;
            }
        }
        if (!b(bluetoothDevice)) {
            return false;
        }
        if (TextUtils.isEmpty(this.d.getAddressFilter()) || DataConverter.equals(this.d.getAddressFilter(), bluetoothDevice.getAddress())) {
            return true;
        }
        if (this.b) {
            StringBuilder sbA = com.realsil.sdk.core.a.a.a("address not match:");
            sbA.append(BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true));
            ZLogger.v(sbA.toString());
        }
        return false;
    }

    @Override // com.realsil.sdk.core.d.a
    public final boolean b() {
        if (!super.b()) {
            return false;
        }
        Context context = this.c;
        int i = Build.VERSION.SDK_INT;
        this.p = new c(context);
        return true;
    }

    @Override // com.realsil.sdk.core.d.a
    public final boolean e() {
        c cVar = this.p;
        synchronized (cVar) {
            com.realsil.sdk.core.f.a aVar = cVar.a;
            if (aVar != null) {
                aVar.f = null;
            }
        }
        if (this.p.a.d) {
            ZLogger.v(this.b, "stop the le scan process");
            if (!this.p.a.a(null, false)) {
                ZLogger.w("scanLeDevice failed");
                return false;
            }
        }
        a(0);
        return true;
    }

    @Override // com.realsil.sdk.core.d.a
    public /* bridge */ /* synthetic */ BluetoothAdapter getBluetoothAdapter() {
        return super.getBluetoothAdapter();
    }

    @Override // com.realsil.sdk.core.d.a
    public /* bridge */ /* synthetic */ List getPairedDevices() {
        return super.getPairedDevices();
    }

    @Override // com.realsil.sdk.core.d.a
    public /* bridge */ /* synthetic */ List getPairedDevicesByProfile(int i) {
        return super.getPairedDevicesByProfile(i);
    }

    @Override // com.realsil.sdk.core.d.a
    public /* bridge */ /* synthetic */ int getState() {
        return super.getState();
    }

    @Override // com.realsil.sdk.core.d.a
    public /* bridge */ /* synthetic */ boolean isBluetoothEnabled() {
        return super.isBluetoothEnabled();
    }

    @Override // com.realsil.sdk.core.d.a
    public /* bridge */ /* synthetic */ boolean isBluetoothSupported() {
        return super.isBluetoothSupported();
    }

    @Override // com.realsil.sdk.core.d.a
    public /* bridge */ /* synthetic */ boolean isScanning() {
        return super.isScanning();
    }

    @Override // com.realsil.sdk.core.d.a
    public /* bridge */ /* synthetic */ void onDestroy() {
        super.onDestroy();
    }

    @Override // com.realsil.sdk.core.d.a
    public /* bridge */ /* synthetic */ boolean scanDevice(boolean z) {
        return super.scanDevice(z);
    }

    @Override // com.realsil.sdk.core.d.a
    public /* bridge */ /* synthetic */ void setScanMode(int i) {
        super.setScanMode(i);
    }

    @Override // com.realsil.sdk.core.d.a
    public /* bridge */ /* synthetic */ void setScannerCallback(ScannerCallback scannerCallback) {
        super.setScannerCallback(scannerCallback);
    }

    @Override // com.realsil.sdk.core.d.a
    public /* bridge */ /* synthetic */ void setScannerParams(ScannerParams scannerParams) {
        super.setScannerParams(scannerParams);
    }

    @Override // com.realsil.sdk.core.d.a
    public boolean startScan() {
        if (!d()) {
            return true;
        }
        if (this.b) {
            boolean z = this.a;
            StringBuilder sbA = com.realsil.sdk.core.a.a.a("startDiscovery for ");
            sbA.append(this.d.toString());
            ZLogger.v(z, sbA.toString());
        } else {
            boolean z2 = this.a;
            StringBuilder sbA2 = com.realsil.sdk.core.a.a.a("startDiscovery for ");
            sbA2.append(this.d.getScanPeriod());
            sbA2.append("ms");
            ZLogger.v(z2, sbA2.toString());
        }
        c cVar = this.p;
        a aVar = this.q;
        synchronized (cVar) {
            com.realsil.sdk.core.f.a aVar2 = cVar.a;
            if (aVar2 != null) {
                aVar2.f = aVar;
            }
        }
        c cVar2 = this.p;
        if (cVar2.a.a(this.d, true)) {
            c();
            ZLogger.v("");
            return true;
        }
        ZLogger.v("scanLeDevice failed");
        stopScan();
        return false;
    }

    @Override // com.realsil.sdk.core.d.a
    public /* bridge */ /* synthetic */ boolean stopScan() {
        return super.stopScan();
    }

    public LeScannerPresenter(Context context, ScannerParams scannerParams, ScannerCallback scannerCallback) {
        this(context, null, scannerParams, scannerCallback);
    }

    @Override // com.realsil.sdk.core.d.a
    public /* bridge */ /* synthetic */ List getPairedDevices(int i) {
        return super.getPairedDevices(i);
    }

    @Override // com.realsil.sdk.core.d.a
    public /* bridge */ /* synthetic */ boolean scanDevice(boolean z, boolean z2) {
        return super.scanDevice(z, z2);
    }

    public LeScannerPresenter(Context context, Handler handler, ScannerParams scannerParams, ScannerCallback scannerCallback) {
        this.q = new a();
        this.c = context.getApplicationContext();
        this.f = handler;
        this.d = scannerParams;
        this.e = scannerCallback;
        b();
    }
}
