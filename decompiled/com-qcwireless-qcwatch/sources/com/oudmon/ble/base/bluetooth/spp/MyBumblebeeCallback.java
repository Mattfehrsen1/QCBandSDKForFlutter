package com.oudmon.ble.base.bluetooth.spp;

import android.bluetooth.BluetoothDevice;
import com.oudmon.ble.base.bluetooth.spp.bean.MyDeviceInfo;

/* loaded from: classes3.dex */
public interface MyBumblebeeCallback {
    void onAckReceived(int i, byte b);

    void onConnectionStateChanged(BluetoothDevice bluetoothDevice, int i, int i2);

    void onDeviceInfoChanged(MyDeviceInfo myDeviceInfo, int i);

    void onEventReported(int i, byte[] bArr);

    void onServiceConnectionStateChanged(boolean z);

    void onStateChanged(int i);
}
