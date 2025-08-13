package com.oudmon.ble.base.request;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/request/ReadRssiRequest.class */
public class ReadRssiRequest extends BaseRequest {
    private static ReadRssiRequest readRequest = new ReadRssiRequest();

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

    @Override // com.oudmon.ble.base.request.BaseRequest
    public boolean needInitCharacteristic() {
        return false;
    }
}
