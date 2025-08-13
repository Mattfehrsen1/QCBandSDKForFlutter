package com.oudmon.ble.base.request;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import java.util.UUID;

/* loaded from: classes3.dex */
public class ReadRequest extends BaseRequest {
    public ReadRequest(UUID uuid, UUID uuid2) {
        super(uuid, uuid2);
    }

    @Override // com.oudmon.ble.base.request.BaseRequest
    public boolean execute(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return bluetoothGatt.readCharacteristic(bluetoothGattCharacteristic);
    }
}
