package com.oudmon.ble.base.scan;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Build;

/* loaded from: classes3.dex */
public abstract class BleScannerCompat {
    private static BleScannerCompat mInstance;
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    ScanWrapperCallback scanWrapperCallback;
    public boolean scanning;

    public static BleScannerCompat getScanner(Context context) {
        BleScannerCompat bleScannerCompat = mInstance;
        if (bleScannerCompat != null) {
            return bleScannerCompat;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            BluetoothScannerImplLollipop bluetoothScannerImplLollipop = new BluetoothScannerImplLollipop(context);
            mInstance = bluetoothScannerImplLollipop;
            return bluetoothScannerImplLollipop;
        }
        BluetoothScannerImplJB bluetoothScannerImplJB = new BluetoothScannerImplJB();
        mInstance = bluetoothScannerImplJB;
        return bluetoothScannerImplJB;
    }

    public void startScan(ScanWrapperCallback scanWrapperCallback) {
        this.scanning = true;
        this.scanWrapperCallback = scanWrapperCallback;
        scanWrapperCallback.onStart();
    }

    public void stopScan() {
        this.scanning = false;
        ScanWrapperCallback scanWrapperCallback = this.scanWrapperCallback;
        if (scanWrapperCallback != null) {
            scanWrapperCallback.onStop();
        }
    }

    public boolean isScanning() {
        return this.scanning;
    }
}
