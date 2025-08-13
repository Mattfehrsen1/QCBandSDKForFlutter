package com.realsil.sdk.core.f;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.ParcelUuid;
import com.realsil.sdk.core.bluetooth.scanner.LeScannerPresenter;
import com.realsil.sdk.core.bluetooth.scanner.ScannerParams;
import com.realsil.sdk.core.bluetooth.scanner.compat.CompatScanFilter;
import com.realsil.sdk.core.f.a;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* loaded from: classes3.dex */
public final class d extends com.realsil.sdk.core.f.a {
    public a g;

    public class a implements BluetoothAdapter.LeScanCallback {
        public a() {
        }

        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public final void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            d dVar = d.this;
            a.InterfaceC0237a interfaceC0237a = dVar.f;
            if (interfaceC0237a != null) {
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

    @Override // com.realsil.sdk.core.f.a
    public final boolean a(ScannerParams scannerParams) {
        if (!super.a(scannerParams)) {
            return false;
        }
        UUID[] uuidArr = null;
        List<CompatScanFilter> scanFilters = scannerParams.getScanFilters();
        if (scanFilters != null && scanFilters.size() > 0) {
            boolean z = this.b;
            StringBuilder sbA = com.realsil.sdk.core.a.a.a("contains ");
            sbA.append(scanFilters.size());
            sbA.append(" filters");
            ZLogger.v(z, sbA.toString());
            ArrayList arrayList = new ArrayList();
            for (CompatScanFilter compatScanFilter : scanFilters) {
                ZLogger.v(compatScanFilter.toString());
                if (compatScanFilter.getServiceUuid() != null) {
                    arrayList.add(compatScanFilter.getServiceUuid());
                }
            }
            int size = arrayList.size();
            if (size > 0) {
                uuidArr = new UUID[size];
                for (int i = 0; i < size; i++) {
                    if (arrayList.get(i) != null) {
                        uuidArr[i] = ((ParcelUuid) arrayList.get(i)).getUuid();
                    }
                }
            }
        }
        try {
            return this.c.startLeScan(uuidArr, this.g);
        } catch (Exception e) {
            ZLogger.w(e.toString());
            return false;
        }
    }

    @Override // com.realsil.sdk.core.f.a
    public final boolean a() {
        super.a();
        BluetoothAdapter bluetoothAdapter = this.c;
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            try {
                this.c.stopLeScan(this.g);
                return true;
            } catch (Exception e) {
                ZLogger.w(e.toString());
                return false;
            }
        }
        ZLogger.w("BT Adapter is not turned ON");
        return false;
    }
}
