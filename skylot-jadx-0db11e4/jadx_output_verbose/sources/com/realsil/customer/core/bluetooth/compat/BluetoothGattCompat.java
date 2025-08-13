package com.realsil.customer.core.bluetooth.compat;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.Build;
import androidx.annotation.NonNull;
import com.realsil.customer.core.logger.ZLogger;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/compat/BluetoothGattCompat.class */
public final class BluetoothGattCompat {
    public static boolean writeCharacteristic(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, @NonNull byte[] bArr) {
        if (bluetoothGatt == null) {
            ZLogger.w("gatt cannot be null.");
            return false;
        }
        if (bluetoothGattCharacteristic == null) {
            ZLogger.w("characteristic cannot be null.");
            return false;
        }
        if (Build.VERSION.SDK_INT >= 33) {
            return bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic, bArr, bluetoothGattCharacteristic.getWriteType()) == 0;
        }
        bluetoothGattCharacteristic.setValue(bArr);
        return bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
    }

    public static boolean writeDescriptor(@NonNull BluetoothGatt bluetoothGatt, @NonNull BluetoothGattDescriptor bluetoothGattDescriptor, @NonNull byte[] bArr) {
        if (Build.VERSION.SDK_INT >= 33) {
            return bluetoothGatt.writeDescriptor(bluetoothGattDescriptor, bArr) == 0;
        }
        bluetoothGattDescriptor.setValue(bArr);
        return bluetoothGatt.writeDescriptor(bluetoothGattDescriptor);
    }
}
