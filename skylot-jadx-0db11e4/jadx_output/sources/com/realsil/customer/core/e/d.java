package com.realsil.customer.core.e;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.ParcelUuid;
import com.realsil.customer.core.bluetooth.scanner.LeScannerPresenter;
import com.realsil.customer.core.bluetooth.scanner.ScannerParams;
import com.realsil.customer.core.bluetooth.scanner.compat.CompatScanFilter;
import com.realsil.customer.core.e.a;
import com.realsil.customer.core.logger.ZLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@TargetApi(19)
/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/e/d.class */
public final class d extends com.realsil.customer.core.e.a {
    public final a g;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/e/d$a.class */
    public class a implements BluetoothAdapter.LeScanCallback {
        public a() {
        }

        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public final void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            d dVar = d.this;
            a.InterfaceC0006a interfaceC0006a = dVar.f;
            if (interfaceC0006a != null) {
                LeScannerPresenter.this.a(bluetoothDevice, i, bArr);
            } else {
                ZLogger.v(dVar.b, "no listeners register");
            }
        }
    }

    public d(Context context) {
        super(context);
        this.g = new a();
        ZLogger.v(this.b, "LeScannerV19 init");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v13, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v44 */
    /* JADX WARN: Type inference failed for: r0v45 */
    /* JADX WARN: Type inference failed for: r0v46 */
    /* JADX WARN: Type inference failed for: r0v47 */
    /* JADX WARN: Type inference failed for: r0v7 */
    @Override // com.realsil.customer.core.e.a
    public final boolean a(ScannerParams scannerParams) {
        boolean z;
        BluetoothAdapter bluetoothAdapter = this.c;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w("BT Adapter is not turned ON");
            z = false;
        } else {
            ZLogger.v(this.b, "LeScanner--startScan");
            if (this.f == null) {
                ZLogger.v(this.b, "no listeners register");
            }
            this.d = true;
            this.e = scannerParams;
            z = true;
        }
        if (!z) {
            return false;
        }
        UUID[] uuidArr = null;
        List<CompatScanFilter> scanFilters = scannerParams.getScanFilters();
        Object objStartLeScan = scanFilters;
        if (scanFilters != null) {
            int size = scanFilters.size();
            objStartLeScan = size;
            if (size > 0) {
                ZLogger.v(this.b, "contains " + scanFilters.size() + " filters");
                ArrayList arrayList = new ArrayList();
                for (CompatScanFilter compatScanFilter : scanFilters) {
                    ZLogger.v(compatScanFilter.toString());
                    if (compatScanFilter.getServiceUuid() != null) {
                        arrayList.add(compatScanFilter.getServiceUuid());
                    }
                }
                int size2 = arrayList.size();
                objStartLeScan = size2;
                if (size2 > 0) {
                    uuidArr = new UUID[size2];
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        objStartLeScan = i2;
                        if (i2 < size2) {
                            if (arrayList.get(i) != null) {
                                uuidArr[i] = ((ParcelUuid) arrayList.get(i)).getUuid();
                            }
                            i++;
                        }
                    }
                }
            }
        }
        try {
            objStartLeScan = this.c.startLeScan(uuidArr, this.g);
            return objStartLeScan;
        } catch (Exception unused) {
            ZLogger.w(objStartLeScan.toString());
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x003a, code lost:
    
        r0 = r0.isEnabled();
     */
    /* JADX WARN: Type inference failed for: r0v9, types: [boolean, java.lang.Object] */
    @Override // com.realsil.customer.core.e.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean a() {
        /*
            r4 = this;
            r0 = r4
            com.realsil.customer.core.e.a$a r0 = r0.f
            r1 = r0
            r5 = r1
            if (r0 == 0) goto L23
            r0 = r5
            com.realsil.customer.core.bluetooth.scanner.LeScannerPresenter$a r0 = (com.realsil.customer.core.bluetooth.scanner.LeScannerPresenter.a) r0
            r1 = r0
            com.realsil.customer.core.bluetooth.scanner.LeScannerPresenter r1 = com.realsil.customer.core.bluetooth.scanner.LeScannerPresenter.this
            boolean r1 = r1.b
            java.lang.String r2 = "onLeScanStop"
            com.realsil.customer.core.logger.ZLogger.v(r1, r2)
            com.realsil.customer.core.bluetooth.scanner.LeScannerPresenter r0 = com.realsil.customer.core.bluetooth.scanner.LeScannerPresenter.this
            r1 = 3
            r0.a(r1)
            goto L2c
        L23:
            r0 = r4
            boolean r0 = r0.b
            java.lang.String r1 = "no listeners register"
            com.realsil.customer.core.logger.ZLogger.v(r0, r1)
        L2c:
            r0 = r4
            r1 = r0
            r2 = 0
            r1.d = r2
            android.bluetooth.BluetoothAdapter r0 = r0.c
            r1 = r0
            r5 = r1
            if (r0 == 0) goto L59
            r0 = r5
            boolean r0 = r0.isEnabled()
            if (r0 != 0) goto L44
            goto L59
        L44:
            r0 = r4
            android.bluetooth.BluetoothAdapter r0 = r0.c     // Catch: java.lang.Exception -> L51
            r1 = r4
            com.realsil.customer.core.e.d$a r1 = r1.g     // Catch: java.lang.Exception -> L51
            r0.stopLeScan(r1)     // Catch: java.lang.Exception -> L51
            r0 = 1
            return r0
        L51:
            java.lang.String r0 = r0.toString()
            com.realsil.customer.core.logger.ZLogger.w(r0)
            r0 = 0
            return r0
        L59:
            java.lang.String r0 = "BT Adapter is not turned ON"
            com.realsil.customer.core.logger.ZLogger.w(r0)
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.customer.core.e.d.a():boolean");
    }
}
