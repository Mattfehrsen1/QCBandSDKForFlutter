package com.oudmon.ble.base.request;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/request/WriteRequest.class */
public class WriteRequest extends BaseRequest {
    private byte[] value;
    private boolean noRsp;

    public WriteRequest(UUID serviceUuid, UUID charUuid) {
        super(serviceUuid, charUuid);
        this.noRsp = false;
    }

    private WriteRequest(UUID serviceUuid, UUID charUuid, boolean noResponse) {
        super(serviceUuid, charUuid);
        this.noRsp = false;
        this.noRsp = noResponse;
    }

    public static WriteRequest getNoRspInstance(UUID serviceUuid, UUID charUuid) {
        return new WriteRequest(serviceUuid, charUuid, true);
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    public byte[] getValue() {
        return this.value;
    }

    @Override // com.oudmon.ble.base.request.BaseRequest
    public boolean execute(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.value == null) {
            return false;
        }
        try {
            bluetoothGattCharacteristic.setWriteType(this.noRsp ? 1 : 2);
            boolean setValue = bluetoothGattCharacteristic.setValue(this.value);
            if (setValue) {
                if (bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
