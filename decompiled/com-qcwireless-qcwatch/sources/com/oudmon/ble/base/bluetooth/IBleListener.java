package com.oudmon.ble.base.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattDescriptor;
import com.oudmon.ble.base.request.BaseRequest;

/* loaded from: classes3.dex */
public interface IBleListener {
    void bleCharacteristicChanged(String str, String str2, byte[] bArr);

    void bleCharacteristicNotification();

    void bleCharacteristicRead(String str, String str2, int i, byte[] bArr);

    void bleCharacteristicWrite(String str, String str2, int i, byte[] bArr);

    void bleGattConnected(BluetoothDevice bluetoothDevice);

    void bleGattDisconnect(BluetoothDevice bluetoothDevice);

    void bleNoCallback();

    void bleServiceDiscovered(int i, String str);

    void bleStatus(int i, int i2);

    boolean execute(BaseRequest baseRequest);

    boolean isConnected();

    void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i);

    void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i);

    void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2);

    void startConnect();
}
