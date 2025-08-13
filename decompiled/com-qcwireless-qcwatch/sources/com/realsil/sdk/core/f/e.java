package com.realsil.sdk.core.f;

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
import com.realsil.sdk.core.bluetooth.scanner.LeScannerPresenter;
import com.realsil.sdk.core.bluetooth.scanner.ScannerParams;
import com.realsil.sdk.core.bluetooth.scanner.compat.CompatScanFilter;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.f.a;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public final class e extends com.realsil.sdk.core.f.a {
    public BluetoothLeScanner g;
    public final a h;

    public class a extends ScanCallback {
        public a() {
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
            a.InterfaceC0237a interfaceC0237a = eVar.f;
            if (interfaceC0237a == null) {
                ZLogger.v(eVar.b, "no listeners register");
                return;
            }
            ZLogger.v(LeScannerPresenter.this.b, "onLeScanFailed:" + i);
        }

        @Override // android.bluetooth.le.ScanCallback
        public final void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            e eVar = e.this;
            if (eVar.a) {
                eVar.getClass();
                StringBuilder sb = new StringBuilder();
                sb.append("ScanResult{");
                if (scanResult.getDevice() != null) {
                    sb.append(String.format("\n\tdevice=%s/%s", BluetoothHelper.formatAddress(scanResult.getDevice().getAddress(), true), scanResult.getDevice().getName()));
                }
                if (scanResult.getScanRecord() != null) {
                    sb.append("\n\tscanRecord=");
                    sb.append(b.a(scanResult.getScanRecord()));
                }
                sb.append("\n\trssi=");
                sb.append(scanResult.getRssi());
                sb.append("\n\ttimestampNanos=");
                sb.append(scanResult.getTimestampNanos());
                if (Build.VERSION.SDK_INT >= 26) {
                    sb.append("\n\tisLegacy=");
                    sb.append(scanResult.isConnectable());
                    sb.append("\n\tisLegacy=");
                    sb.append(scanResult.isLegacy());
                    sb.append("\n\tprimaryPhy=");
                    sb.append(scanResult.getPrimaryPhy());
                    sb.append("\n\tsecondaryPhy=");
                    sb.append(scanResult.getSecondaryPhy());
                    sb.append("\n\tadvertisingSid=");
                    sb.append(scanResult.getAdvertisingSid());
                    sb.append("\n\ttxPower=");
                    sb.append(scanResult.getTxPower());
                    sb.append("\n\tperiodicAdvertisingInterval=");
                    sb.append(scanResult.getPeriodicAdvertisingInterval());
                }
                sb.append("\n}");
                ZLogger.v(sb.toString());
            }
            e eVar2 = e.this;
            if (!eVar2.d) {
                ZLogger.v("scan procedure has already been stopped, ignore.");
                return;
            }
            ScannerParams scannerParams = eVar2.e;
            if (scannerParams != null && scannerParams.isConnectable() && Build.VERSION.SDK_INT >= 26 && !scanResult.isConnectable()) {
                if (e.this.b) {
                    ZLogger.v("ignore noconnectable device");
                    return;
                }
                return;
            }
            ScanRecord scanRecord = scanResult.getScanRecord();
            e eVar3 = e.this;
            BluetoothDevice device = scanResult.getDevice();
            int rssi = scanResult.getRssi();
            byte[] bytes = scanRecord != null ? scanRecord.getBytes() : new byte[0];
            a.InterfaceC0237a interfaceC0237a = eVar3.f;
            if (interfaceC0237a != null) {
                LeScannerPresenter.this.a(device, rssi, bytes);
            } else {
                ZLogger.v(eVar3.b, "no listeners register");
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

    @Override // com.realsil.sdk.core.f.a
    public final boolean a(ScannerParams scannerParams) {
        if (!super.a(scannerParams)) {
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
            boolean z = this.b;
            StringBuilder sbA = com.realsil.sdk.core.a.a.a("contains ");
            sbA.append(scanFilters.size());
            sbA.append(" filters");
            ZLogger.v(z, sbA.toString());
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
            BluetoothLeScanner bluetoothLeScanner = this.g;
            if (bluetoothLeScanner == null) {
                ZLogger.w("BluetoothLeScanner has not been initialized");
                return false;
            }
            try {
                bluetoothLeScanner.stopScan(this.h);
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
