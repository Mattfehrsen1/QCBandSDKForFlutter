package com.oudmon.ble.base.request;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import com.realsil.customer.core.b.a;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/request/EnableNotifyRequest.class */
public class EnableNotifyRequest extends BaseRequest {
    private static final String TAG = "jxr35";
    private UUID GATT_NOTIFY_CONFIG;
    private boolean isEnable;
    private ListenerCallback callback;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/request/EnableNotifyRequest$ListenerCallback.class */
    public interface ListenerCallback {
        void enable(boolean z);
    }

    public EnableNotifyRequest(UUID serviceUuid, UUID charUuid) {
        super(serviceUuid, charUuid);
        this.GATT_NOTIFY_CONFIG = UUID.fromString(a.CLIENT_CHARACTERISTIC_CONFIG);
        this.isEnable = true;
    }

    public EnableNotifyRequest(UUID serviceUuid, UUID charUuid, boolean isEnable) {
        super(serviceUuid, charUuid);
        this.GATT_NOTIFY_CONFIG = UUID.fromString(a.CLIENT_CHARACTERISTIC_CONFIG);
        this.isEnable = true;
        this.isEnable = isEnable;
    }

    public EnableNotifyRequest(UUID serviceUuid, UUID charUuid, ListenerCallback callback) {
        super(serviceUuid, charUuid);
        this.GATT_NOTIFY_CONFIG = UUID.fromString(a.CLIENT_CHARACTERISTIC_CONFIG);
        this.isEnable = true;
        this.callback = callback;
    }

    @Override // com.oudmon.ble.base.request.BaseRequest
    public boolean execute(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGatt == null) {
            if (this.callback != null) {
                this.callback.enable(false);
                return false;
            }
            return false;
        }
        boolean b = bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, this.isEnable);
        if (!b) {
            if (this.callback != null) {
                this.callback.enable(false);
                return false;
            }
            return false;
        }
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(this.GATT_NOTIFY_CONFIG);
        if (descriptor == null) {
            if (this.callback != null) {
                this.callback.enable(false);
                return false;
            }
            return false;
        }
        descriptor.setValue(this.isEnable ? BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
        boolean flag = bluetoothGatt.writeDescriptor(descriptor);
        if (this.callback != null) {
            this.callback.enable(flag);
        }
        return flag;
    }

    public boolean isEnable() {
        return this.isEnable;
    }

    public void setEnable(boolean enable) {
        this.isEnable = enable;
    }
}
