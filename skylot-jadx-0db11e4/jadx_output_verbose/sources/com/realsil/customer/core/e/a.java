package com.realsil.customer.core.e;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import com.realsil.customer.core.RtkCore;
import com.realsil.customer.core.bluetooth.scanner.ScannerParams;
import com.realsil.customer.core.logger.ZLogger;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/e/a.class */
public abstract class a {
    public final boolean a;
    public final boolean b;
    public final BluetoothAdapter c;
    public boolean d;
    public ScannerParams e;
    public InterfaceC0006a f;

    /* renamed from: com.realsil.customer.core.e.a$a, reason: collision with other inner class name */
    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/e/a$a.class */
    public interface InterfaceC0006a {
    }

    public a(Context context) {
        this.a = false;
        this.b = false;
        this.a = RtkCore.DEBUG;
        this.b = RtkCore.VDBG;
        this.c = com.realsil.customer.core.a.a.a(context);
    }

    public abstract boolean a(ScannerParams scannerParams);

    public abstract boolean a();

    public final boolean a(ScannerParams scannerParams, boolean z) {
        if (!z) {
            return a();
        }
        if (this.c.isEnabled()) {
            return a(scannerParams);
        }
        ZLogger.d("BT Adapter is not enable");
        return false;
    }
}
