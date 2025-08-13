package com.oudmon.ble.base.bluetooth.spp;

import android.bluetooth.BluetoothDevice;
import com.oudmon.ble.base.bluetooth.spp.bean.MyDeviceInfo;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/bluetooth/spp/MyBumblebeeCallback.class */
public interface MyBumblebeeCallback {
    void onStateChanged(int i);

    void onServiceConnectionStateChanged(boolean z);

    void onConnectionStateChanged(BluetoothDevice bluetoothDevice, int i, int i2);

    void onDeviceInfoChanged(MyDeviceInfo myDeviceInfo, int i);

    void onAckReceived(int i, byte b);

    void onEventReported(int i, byte[] bArr);
}
