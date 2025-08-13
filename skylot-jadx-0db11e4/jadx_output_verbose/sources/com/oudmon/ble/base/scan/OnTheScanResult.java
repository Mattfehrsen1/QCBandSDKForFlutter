package com.oudmon.ble.base.scan;

import android.bluetooth.BluetoothDevice;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/scan/OnTheScanResult.class */
public interface OnTheScanResult {
    void onResult(BluetoothDevice bluetoothDevice);

    void onScanFailed(int i);
}
