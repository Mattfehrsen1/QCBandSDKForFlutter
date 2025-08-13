package com.realsil.customer.core.bluetooth.connection;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/connection/BluetoothClientCallback.class */
public abstract class BluetoothClientCallback {
    public void onConnectionStateChanged(BluetoothClient bluetoothClient, boolean z, int i) {
    }

    public void onDataReceive(byte[] bArr) {
    }

    public void onDataReceive(BluetoothClient bluetoothClient, byte[] bArr) {
        onDataReceive(bArr);
    }

    public void onError(int i) {
    }
}
