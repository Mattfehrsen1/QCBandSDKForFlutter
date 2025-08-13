package com.oudmon.ble.base.request;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;

/* loaded from: classes3.dex */
public class ReadRssiRequest extends BaseRequest {
    private static ReadRssiRequest readRequest = new ReadRssiRequest();

    @Override // com.oudmon.ble.base.request.BaseRequest
    public boolean needInitCharacteristic() {
        return false;
    }

    public static ReadRssiRequest getInstance() {
        return readRequest;
    }

    private ReadRssiRequest() {
        super(null, null);
    }

    @Override // com.oudmon.ble.base.request.BaseRequest
    public boolean execute(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return bluetoothGatt.readRemoteRssi();
    }
}
