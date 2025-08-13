package com.realsil.customer.core.bluetooth;

import android.bluetooth.BluetoothDevice;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/RtkBluetoothManagerCallback.class */
public abstract class RtkBluetoothManagerCallback {
    public void onBluetoothStateChaned(BluetoothDevice bluetoothDevice, int i) {
    }

    public void onBluetoothStateChanged(int i) {
    }

    public void onBondStateChanged(BluetoothDevice bluetoothDevice, int i) {
    }

    public void onPairingRequestNotify(BluetoothDevice bluetoothDevice, int i) {
    }

    public void onBleAclConnectionStateChanged(BluetoothDevice bluetoothDevice, boolean z) {
    }

    public void onAclConnectionStateChanged(BluetoothDevice bluetoothDevice, boolean z) {
    }
}
