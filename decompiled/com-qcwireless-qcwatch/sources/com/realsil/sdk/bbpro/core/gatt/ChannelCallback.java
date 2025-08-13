package com.realsil.sdk.bbpro.core.gatt;

import android.bluetooth.BluetoothDevice;

/* loaded from: classes3.dex */
public abstract class ChannelCallback {
    public void onConnectionStateChange(BluetoothDevice bluetoothDevice, boolean z, int i) {
    }

    public void onDataReceive(byte[] bArr) {
    }

    public void onFlowCtrlAck() {
    }

    public void onParamsUpdate() {
    }
}
