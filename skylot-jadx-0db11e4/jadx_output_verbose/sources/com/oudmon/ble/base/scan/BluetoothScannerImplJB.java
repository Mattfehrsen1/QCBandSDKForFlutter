package com.oudmon.ble.base.scan;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/scan/BluetoothScannerImplJB.class */
class BluetoothScannerImplJB extends BleScannerCompat {
    private BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() { // from class: com.oudmon.ble.base.scan.BluetoothScannerImplJB.1
        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            BluetoothScannerImplJB.this.scanWrapperCallback.onLeScan(device, rssi, scanRecord);
        }
    };

    BluetoothScannerImplJB() {
    }

    @Override // com.oudmon.ble.base.scan.BleScannerCompat
    public void startScan(ScanWrapperCallback scanWrapperCallback) {
        super.startScan(scanWrapperCallback);
        this.scanning = true;
        this.bluetoothAdapter.startLeScan(this.leScanCallback);
    }

    @Override // com.oudmon.ble.base.scan.BleScannerCompat
    public void stopScan() {
        super.stopScan();
        this.scanning = false;
        this.bluetoothAdapter.stopLeScan(this.leScanCallback);
    }
}
