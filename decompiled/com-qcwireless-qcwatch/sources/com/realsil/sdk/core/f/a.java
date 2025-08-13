package com.realsil.sdk.core.f;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.bluetooth.scanner.LeScannerPresenter;
import com.realsil.sdk.core.bluetooth.scanner.ScannerParams;
import com.realsil.sdk.core.logger.ZLogger;

/* loaded from: classes3.dex */
public abstract class a {
    public boolean a;
    public boolean b;
    public BluetoothAdapter c;
    public boolean d;
    public ScannerParams e;
    public InterfaceC0237a f;

    /* renamed from: com.realsil.sdk.core.f.a$a, reason: collision with other inner class name */
    public interface InterfaceC0237a {
    }

    public a(Context context) {
        this.a = false;
        this.b = false;
        this.a = RtkCore.DEBUG;
        this.b = RtkCore.VDBG;
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
        this.c = bluetoothManager != null ? bluetoothManager.getAdapter() : null;
    }

    public boolean a(ScannerParams scannerParams) {
        BluetoothAdapter bluetoothAdapter = this.c;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w("BT Adapter is not turned ON");
            return false;
        }
        ZLogger.v(this.b, "LeScanner--startScan");
        if (this.f == null) {
            ZLogger.v(this.b, "no listeners register");
        }
        this.d = true;
        this.e = scannerParams;
        return true;
    }

    public boolean a() {
        InterfaceC0237a interfaceC0237a = this.f;
        if (interfaceC0237a != null) {
            LeScannerPresenter.a aVar = (LeScannerPresenter.a) interfaceC0237a;
            ZLogger.v(LeScannerPresenter.this.b, "onLeScanStop");
            LeScannerPresenter.this.a(3);
        } else {
            ZLogger.v(this.b, "no listeners register");
        }
        this.d = false;
        return true;
    }

    public final boolean a(ScannerParams scannerParams, boolean z) {
        if (z) {
            if (!this.c.isEnabled()) {
                ZLogger.d("BT Adapter is not enable");
                return false;
            }
            return a(scannerParams);
        }
        return a();
    }
}
