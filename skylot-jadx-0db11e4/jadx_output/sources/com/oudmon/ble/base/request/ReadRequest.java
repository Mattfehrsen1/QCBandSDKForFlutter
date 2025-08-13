package com.oudmon.ble.base.request;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/request/ReadRequest.class */
public class ReadRequest extends BaseRequest {
    public ReadRequest(UUID serviceUuid, UUID charUuid) {
        super(serviceUuid, charUuid);
    }

    @Override // com.oudmon.ble.base.request.BaseRequest
    public boolean execute(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return bluetoothGatt.readCharacteristic(bluetoothGattCharacteristic);
    }
}
