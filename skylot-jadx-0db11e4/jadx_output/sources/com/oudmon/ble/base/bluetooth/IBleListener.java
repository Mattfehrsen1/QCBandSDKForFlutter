package com.oudmon.ble.base.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattDescriptor;
import com.oudmon.ble.base.request.BaseRequest;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/bluetooth/IBleListener.class */
public interface IBleListener {
    void startConnect();

    void bleGattConnected(BluetoothDevice bluetoothDevice);

    void bleGattDisconnect(BluetoothDevice bluetoothDevice);

    void bleServiceDiscovered(int i, String str);

    void bleCharacteristicRead(String str, String str2, int i, byte[] bArr);

    void bleCharacteristicNotification();

    void bleCharacteristicWrite(String str, String str2, int i, byte[] bArr);

    void bleCharacteristicChanged(String str, String str2, byte[] bArr);

    void bleNoCallback();

    boolean execute(BaseRequest baseRequest);

    void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2);

    void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i);

    void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i);

    boolean isConnected();
}
