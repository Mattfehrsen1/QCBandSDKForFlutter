package com.realsil.customer.bbpro;

import android.bluetooth.BluetoothDevice;
import com.realsil.customer.bbpro.model.DeviceInfo;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/BumblebeeCallback.class */
public abstract class BumblebeeCallback {
    public void onStateChanged(int i) {
    }

    public void onServiceConnectionStateChanged(boolean z) {
    }

    public void onConnectionStateChanged(BluetoothDevice bluetoothDevice, int i, int i2) {
    }

    public void onDeviceInfoChanged(DeviceInfo deviceInfo, int i) {
    }

    public void onAckReceived(int i, byte b) {
    }

    public void onEventReported(int i, byte[] bArr) {
    }
}
