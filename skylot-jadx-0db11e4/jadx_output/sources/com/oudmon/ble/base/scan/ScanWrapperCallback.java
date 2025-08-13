package com.oudmon.ble.base.scan;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import androidx.annotation.RequiresApi;
import java.util.List;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/scan/ScanWrapperCallback.class */
public interface ScanWrapperCallback {
    void onStart();

    void onStop();

    void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr);

    void onScanFailed(int i);

    @RequiresApi(api = 21)
    void onParsedData(BluetoothDevice bluetoothDevice, ScanRecord scanRecord);

    void onBatchScanResults(List<ScanResult> list);
}
