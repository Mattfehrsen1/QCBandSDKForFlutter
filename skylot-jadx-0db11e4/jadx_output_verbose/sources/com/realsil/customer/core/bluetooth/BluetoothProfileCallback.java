package com.realsil.customer.core.bluetooth;

import android.bluetooth.BluetoothDevice;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/BluetoothProfileCallback.class */
public abstract class BluetoothProfileCallback {
    public void onA2dpStateChanged(BluetoothDevice bluetoothDevice, int i) {
    }

    public void onA2dpPlayingStateChanged(BluetoothDevice bluetoothDevice, int i) {
    }

    public void onHfpConnectionStateChanged(BluetoothDevice bluetoothDevice, int i) {
    }

    public void onHfpAudioStateChanged(BluetoothDevice bluetoothDevice, int i) {
    }

    public void onVendorSpecificHeadsetEvent(BluetoothDevice bluetoothDevice, String str, int i, Object[] objArr) {
    }

    public void onHidStateChanged(BluetoothDevice bluetoothDevice, int i) {
    }
}
