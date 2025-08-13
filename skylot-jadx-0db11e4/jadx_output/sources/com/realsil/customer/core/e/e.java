package com.realsil.customer.core.e;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Build;
import com.realsil.customer.core.bluetooth.scanner.LeScannerPresenter;
import com.realsil.customer.core.bluetooth.scanner.ScannerParams;
import com.realsil.customer.core.bluetooth.scanner.compat.CompatScanFilter;
import com.realsil.customer.core.bluetooth.utils.BluetoothHelper;
import com.realsil.customer.core.e.a;
import com.realsil.customer.core.logger.ZLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@TargetApi(21)
/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/e/e.class */
public final class e extends com.realsil.customer.core.e.a {
    public BluetoothLeScanner g;
    public final a h;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/e/e$a.class */
    public class a extends ScanCallback {
        public a() {
        }

        @Override // android.bluetooth.le.ScanCallback
        public final void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            e eVar = e.this;
            if (!eVar.d) {
                ZLogger.v("scan procedure has already been stopped, ignore.");
                e.this.a();
                return;
            }
            ScannerParams scannerParams = eVar.e;
            if (scannerParams != null && scannerParams.isConnectable() && Build.VERSION.SDK_INT >= 26 && !scanResult.isConnectable()) {
                if (e.this.b) {
                    ZLogger.v("ignore non-connectable device");
                    return;
                }
                return;
            }
            if (e.this.b) {
                StringBuilder sb = new StringBuilder("ScanResult {");
                BluetoothDevice device = scanResult.getDevice();
                if (device != null) {
                    sb.append(String.format("\n\t%s", BluetoothHelper.dumpBluetoothDevice(device)));
                }
                if (scanResult.getScanRecord() != null) {
                    sb.append("\n\tscanRecord=").append(b.a(scanResult.getScanRecord()));
                }
                sb.append("\n\trssi=").append(scanResult.getRssi());
                sb.append("\n\ttimestampNanos=").append(scanResult.getTimestampNanos());
                if (Build.VERSION.SDK_INT >= 26) {
                    sb.append("\n\tisConnectable=").append(scanResult.isConnectable());
                    sb.append("\n\tisLegacy=").append(scanResult.isLegacy());
                    sb.append(String.format(Locale.US, "\n\tprimaryPhy=%d,secondaryPhy=%d", Integer.valueOf(scanResult.getPrimaryPhy()), Integer.valueOf(scanResult.getSecondaryPhy())));
                    sb.append("\n\tadvertisingSid=").append(scanResult.getAdvertisingSid());
                    sb.append("\n\ttxPower=").append(scanResult.getTxPower());
                    sb.append("\n\tperiodicAdvertisingInterval=").append(scanResult.getPeriodicAdvertisingInterval());
                }
                sb.append("\n}");
                ZLogger.v(sb.toString());
            }
            ScanRecord scanRecord = scanResult.getScanRecord();
            e eVar2 = e.this;
            BluetoothDevice device2 = scanResult.getDevice();
            int rssi = scanResult.getRssi();
            byte[] bytes = scanRecord != null ? scanRecord.getBytes() : new byte[0];
            a.InterfaceC0006a interfaceC0006a = eVar2.f;
            if (interfaceC0006a != null) {
                LeScannerPresenter.this.a(device2, rssi, bytes);
            } else {
                ZLogger.v(eVar2.b, "no listeners register");
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public final void onBatchScanResults(List<ScanResult> list) {
            super.onBatchScanResults(list);
        }

        @Override // android.bluetooth.le.ScanCallback
        public final void onScanFailed(int i) {
            super.onScanFailed(i);
            ZLogger.w(e.this.a, "scan failed with " + i);
            e eVar = e.this;
            a.InterfaceC0006a interfaceC0006a = eVar.f;
            if (interfaceC0006a != null) {
                ZLogger.v(LeScannerPresenter.this.b, "onLeScanFailed:" + i);
            } else {
                ZLogger.v(eVar.b, "no listeners register");
            }
        }
    }

    public e(Context context) {
        super(context);
        this.h = new a();
        ZLogger.v(this.b, "LeScannerV21 init");
        BluetoothAdapter bluetoothAdapter = this.c;
        if (bluetoothAdapter != null) {
            this.g = bluetoothAdapter.getBluetoothLeScanner();
        }
        if (this.g == null) {
            ZLogger.d("mBluetoothLeScanner == null");
        }
    }

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
            ZLogger.w("startScan failed");
            return false;
        }
        if (this.g == null) {
            ZLogger.d("getBluetoothLeScanner...");
            this.g = this.c.getBluetoothLeScanner();
        }
        if (this.g == null) {
            ZLogger.w("mBluetoothLeScanner is null");
            a();
            return false;
        }
        ArrayList arrayList = new ArrayList();
        List<CompatScanFilter> scanFilters = scannerParams.getScanFilters();
        if (scanFilters != null && scanFilters.size() > 0) {
            ZLogger.v(this.b, "contains " + scanFilters.size() + " filters");
            for (CompatScanFilter compatScanFilter : scanFilters) {
                ScanFilter.Builder builder = new ScanFilter.Builder();
                builder.setServiceUuid(compatScanFilter.getServiceUuid()).setDeviceAddress(compatScanFilter.getDeviceAddress()).setDeviceName(compatScanFilter.getDeviceName()).setManufacturerData(compatScanFilter.getManufacturerId(), compatScanFilter.getManufacturerData(), compatScanFilter.getManufacturerDataMask());
                if (compatScanFilter.getServiceDataUuid() != null) {
                    builder.setServiceData(compatScanFilter.getServiceDataUuid(), compatScanFilter.getServiceData());
                }
                if (Build.VERSION.SDK_INT >= 29) {
                    builder.setServiceSolicitationUuid(compatScanFilter.getServiceSolicitationUuid());
                }
                arrayList.add(builder.build());
                ZLogger.v(this.b, compatScanFilter.toString());
            }
        }
        ScanSettings.Builder scanMode = new ScanSettings.Builder().setScanMode(2);
        if (Build.VERSION.SDK_INT >= 26) {
            scanMode.setPhy(scannerParams.getPhy()).setLegacy(false);
        }
        try {
            this.g.startScan(arrayList, scanMode.build(), this.h);
            return true;
        } catch (Exception unused) {
            ZLogger.w(toString());
            return false;
        }
    }

    @Override // com.realsil.customer.core.e.a
    public final boolean a() {
        a.InterfaceC0006a interfaceC0006a = this.f;
        if (interfaceC0006a != null) {
            LeScannerPresenter.a aVar = (LeScannerPresenter.a) interfaceC0006a;
            ZLogger.v(LeScannerPresenter.this.b, "onLeScanStop");
            LeScannerPresenter.this.a(3);
        } else {
            ZLogger.v(this.b, "no listeners register");
        }
        this.d = false;
        BluetoothAdapter bluetoothAdapter = this.c;
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            BluetoothLeScanner bluetoothLeScanner = this.g;
            if (bluetoothLeScanner == null) {
                ZLogger.w("BluetoothLeScanner has not been initialized");
                return false;
            }
            try {
                ZLogger.v("stop LE Scan");
                this.g.stopScan(this.h);
                return true;
            } catch (Exception unused) {
                ZLogger.w(bluetoothLeScanner.toString());
                return false;
            }
        }
        ZLogger.w("BT Adapter is not turned ON");
        return false;
    }
}
