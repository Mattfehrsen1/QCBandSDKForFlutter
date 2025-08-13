package com.realsil.sdk.bbpro;

import android.bluetooth.BluetoothDevice;
import com.realsil.sdk.bbpro.model.DeviceInfo;

/* loaded from: classes3.dex */
public abstract class BumblebeeCallback {
    public void onAckReceived(int i, byte b) {
    }

    public void onConnectionStateChanged(BluetoothDevice bluetoothDevice, int i, int i2) {
    }

    public void onDeviceInfoChanged(DeviceInfo deviceInfo, int i) {
    }

    public void onEventReported(int i, byte[] bArr) {
    }

    public void onServiceConnectionStateChanged(boolean z) {
    }

    public void onStateChanged(int i) {
    }
}
