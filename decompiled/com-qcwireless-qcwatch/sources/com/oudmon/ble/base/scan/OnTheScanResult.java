package com.oudmon.ble.base.scan;

import android.bluetooth.BluetoothDevice;

/* loaded from: classes3.dex */
public interface OnTheScanResult {
    void onResult(BluetoothDevice bluetoothDevice);

    void onScanFailed(int i);
}
