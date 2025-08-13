package com.oudmon.ble.base.request;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import java.util.UUID;

/* loaded from: classes3.dex */
public abstract class BaseRequest {
    private UUID charUuid;
    private UUID serviceUuid;
    public boolean writeRequest;

    public abstract boolean execute(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic);

    public boolean needInitCharacteristic() {
        return true;
    }

    public BaseRequest(UUID uuid, UUID uuid2) {
        this.serviceUuid = uuid;
        this.charUuid = uuid2;
    }

    public UUID getServiceUuid() {
        return this.serviceUuid;
    }

    public UUID getCharUuid() {
        return this.charUuid;
    }
}
