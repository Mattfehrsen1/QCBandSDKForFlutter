package com.oudmon.ble.base.request;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import java.util.UUID;

/* loaded from: classes3.dex */
public class WriteRequest extends BaseRequest {
    private boolean noRsp;
    private byte[] value;

    public WriteRequest(UUID uuid, UUID uuid2) {
        super(uuid, uuid2);
        this.noRsp = false;
        this.writeRequest = true;
    }

    private WriteRequest(UUID uuid, UUID uuid2, boolean z) {
        super(uuid, uuid2);
        this.noRsp = false;
        this.noRsp = z;
        this.writeRequest = true;
    }

    public static WriteRequest getNoRspInstance(UUID uuid, UUID uuid2) {
        return new WriteRequest(uuid, uuid2, true);
    }

    public void setValue(byte[] bArr) {
        this.value = bArr;
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
            if (bluetoothGattCharacteristic.setValue(this.value)) {
                return bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
