package com.oudmon.ble.base.request;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/request/BaseRequest.class */
public abstract class BaseRequest {
    private UUID serviceUuid;
    private UUID charUuid;

    public abstract boolean execute(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic);

    public BaseRequest(UUID serviceUuid, UUID charUuid) {
        this.serviceUuid = serviceUuid;
        this.charUuid = charUuid;
    }

    public UUID getServiceUuid() {
        return this.serviceUuid;
    }

    public UUID getCharUuid() {
        return this.charUuid;
    }

    public boolean needInitCharacteristic() {
        return true;
    }
}
