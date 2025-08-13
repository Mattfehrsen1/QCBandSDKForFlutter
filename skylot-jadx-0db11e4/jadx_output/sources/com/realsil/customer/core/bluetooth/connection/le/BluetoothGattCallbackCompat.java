package com.realsil.customer.core.bluetooth.connection.le;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import androidx.annotation.NonNull;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/connection/le/BluetoothGattCallbackCompat.class */
public abstract class BluetoothGattCallbackCompat {
    public void onPhyUpdate(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
    }

    public void onPhyRead(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
    }

    public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
    }

    public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
    }

    public void onCharacteristicRead(@NonNull BluetoothGatt bluetoothGatt, @NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, @NonNull byte[] bArr, int i) {
    }

    public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
    }

    public void onCharacteristicChanged(@NonNull BluetoothGatt bluetoothGatt, @NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, @NonNull byte[] bArr) {
    }

    public void onDescriptorRead(@NonNull BluetoothGatt bluetoothGatt, @NonNull BluetoothGattDescriptor bluetoothGattDescriptor, int i, @NonNull byte[] bArr) {
    }

    public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
    }

    public void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int i) {
    }

    public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
    }

    public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
    }

    public void onConnectionUpdated(BluetoothGatt bluetoothGatt, int i, int i2, int i3, int i4) {
    }

    public void onServiceChanged(@NonNull BluetoothGatt bluetoothGatt) {
    }
}
