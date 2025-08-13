package com.oudmon.ble.base.scan;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Build;
import android.os.ParcelUuid;
import androidx.core.app.ActivityCompat;
import com.elvishew.xlog.XLog;
import com.hjq.permissions.Permission;
import com.oudmon.ble.base.util.AppUtil;
import com.qcwireless.qc_utils.bluetooth.BluetoothUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
class BluetoothScannerImplLollipop extends BleScannerCompat {
    private static final String TAG = "BluetoothScannerImplLol";
    private Context context;
    private ScanSettings scanSettings;
    private BluetoothLeScanner scanner;
    private List<ScanFilter> filters = new ArrayList();
    private ScanCallback scannerCallback = new ScanCallback() { // from class: com.oudmon.ble.base.scan.BluetoothScannerImplLollipop.1
        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            BluetoothDevice device = scanResult.getDevice();
            byte[] bytes = scanResult.getScanRecord().getBytes();
            if (BluetoothScannerImplLollipop.this.scanWrapperCallback != null) {
                BluetoothScannerImplLollipop.this.scanWrapperCallback.onLeScan(device, scanResult.getRssi(), bytes);
            }
            ScanRecord fromBytes = ScanRecord.parseFromBytes(bytes);
            if (fromBytes == null || BluetoothScannerImplLollipop.this.scanWrapperCallback == null) {
                return;
            }
            BluetoothScannerImplLollipop.this.scanWrapperCallback.onParsedData(device, fromBytes);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onBatchScanResults(List<ScanResult> list) {
            if (list == null || list.size() <= 0 || BluetoothScannerImplLollipop.this.scanWrapperCallback == null) {
                return;
            }
            BluetoothScannerImplLollipop.this.scanWrapperCallback.onBatchScanResults(list);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            XLog.e("Scan Failed Error Code: " + i);
            if (BluetoothScannerImplLollipop.this.scanWrapperCallback != null) {
                BluetoothScannerImplLollipop.this.scanWrapperCallback.onScanFailed(i);
            }
        }
    };

    public BluetoothScannerImplLollipop(Context context) {
        this.context = context;
    }

    @Override // com.oudmon.ble.base.scan.BleScannerCompat
    public void startScan(ScanWrapperCallback scanWrapperCallback) throws SecurityException {
        super.startScan(scanWrapperCallback);
        this.scanning = true;
        if (this.scanner == null) {
            this.scanner = this.bluetoothAdapter.getBluetoothLeScanner();
        }
        if (BluetoothUtils.isEnabledBluetooth(this.context)) {
            setScanSettings();
            if (this.scanner != null) {
                if (Build.VERSION.SDK_INT < 31 || ActivityCompat.checkSelfPermission(this.context, Permission.BLUETOOTH_SCAN) == 0) {
                    this.scanner.startScan(this.filters, this.scanSettings, this.scannerCallback);
                }
            }
        }
    }

    @Override // com.oudmon.ble.base.scan.BleScannerCompat
    public void stopScan() {
        super.stopScan();
        this.scanning = false;
        if (BluetoothUtils.isEnabledBluetooth(this.context)) {
            if (this.scanner == null) {
                this.scanner = this.bluetoothAdapter.getBluetoothLeScanner();
            }
            if (Build.VERSION.SDK_INT < 31 || ActivityCompat.checkSelfPermission(this.context, Permission.BLUETOOTH_SCAN) == 0) {
                this.scanner.stopScan(this.scannerCallback);
            }
        }
    }

    private void setScanSettings() throws SecurityException {
        boolean zIsBackground = AppUtil.isBackground(this.context);
        boolean zIsApplicationBroughtToBackground = AppUtil.isApplicationBroughtToBackground(this.context);
        if (zIsBackground || zIsApplicationBroughtToBackground) {
            this.filters.add(new ScanFilter.Builder().setServiceUuid(ParcelUuid.fromString("0000fee7-0000-1000-8000-00805f9b34fb")).build());
            ScanSettings.Builder builder = new ScanSettings.Builder();
            builder.setScanMode(0);
            if (Build.VERSION.SDK_INT >= 23) {
                builder.setMatchMode(1);
                builder.setCallbackType(1);
            }
            this.scanSettings = builder.build();
            return;
        }
        this.filters.clear();
        this.scanSettings = new ScanSettings.Builder().setScanMode(2).build();
    }
}
