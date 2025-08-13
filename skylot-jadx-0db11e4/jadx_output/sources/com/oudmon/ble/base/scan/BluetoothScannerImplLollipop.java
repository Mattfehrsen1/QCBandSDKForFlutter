package com.oudmon.ble.base.scan;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Build;
import android.os.ParcelUuid;
import android.util.Log;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.util.AppUtil;
import com.oudmon.ble.base.util.BluetoothUtils;
import java.util.ArrayList;
import java.util.List;

@TargetApi(21)
/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/scan/BluetoothScannerImplLollipop.class */
class BluetoothScannerImplLollipop extends BleScannerCompat {
    private static final String TAG = "BluetoothScannerImplLol";
    private BluetoothLeScanner scanner;
    private ScanSettings scanSettings;
    private Context context;
    private List<ScanFilter> filters = new ArrayList();
    private ScanCallback scannerCallback = new ScanCallback() { // from class: com.oudmon.ble.base.scan.BluetoothScannerImplLollipop.1
        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int callbackType, ScanResult result) {
            BluetoothDevice device = result.getDevice();
            byte[] scanRecord = result.getScanRecord().getBytes();
            if (BluetoothScannerImplLollipop.this.scanWrapperCallback != null) {
                BluetoothScannerImplLollipop.this.scanWrapperCallback.onLeScan(device, result.getRssi(), scanRecord);
            }
            ScanRecord parseRecord = ScanRecord.parseFromBytes(scanRecord);
            if (parseRecord != null && BluetoothScannerImplLollipop.this.scanWrapperCallback != null) {
                BluetoothScannerImplLollipop.this.scanWrapperCallback.onParsedData(device, parseRecord);
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onBatchScanResults(List<ScanResult> results) {
            if (results != null && results.size() > 0 && BluetoothScannerImplLollipop.this.scanWrapperCallback != null) {
                BluetoothScannerImplLollipop.this.scanWrapperCallback.onBatchScanResults(results);
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int errorCode) {
            Log.i(Constants.TAG, "Scan Failed Error Code: " + errorCode);
            if (BluetoothScannerImplLollipop.this.scanWrapperCallback != null) {
                BluetoothScannerImplLollipop.this.scanWrapperCallback.onScanFailed(errorCode);
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
        setScanSettings();
        if (this.scanner != null) {
            this.scanner.startScan(this.filters, this.scanSettings, this.scannerCallback);
        }
    }

    @Override // com.oudmon.ble.base.scan.BleScannerCompat
    public void stopScan() {
        super.stopScan();
        this.scanning = false;
        if (!BluetoothUtils.isEnabledBluetooth(this.context)) {
            return;
        }
        if (this.scanner == null) {
            this.scanner = this.bluetoothAdapter.getBluetoothLeScanner();
        }
        this.scanner.stopScan(this.scannerCallback);
    }

    private void setScanSettings() throws SecurityException {
        boolean background = AppUtil.isBackground(this.context);
        boolean background1 = AppUtil.isApplicationBroughtToBackground(this.context);
        if (background || background1) {
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
