package com.oudmon.ble.base.request;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import com.elvishew.xlog.XLog;
import com.realsil.sdk.core.c.b;
import java.util.UUID;

/* loaded from: classes3.dex */
public class EnableNotifyRequest extends BaseRequest {
    private UUID GATT_NOTIFY_CONFIG;
    private ListenerCallback callback;
    private boolean isEnable;

    public interface ListenerCallback {
        void enable(boolean z);
    }

    public EnableNotifyRequest(UUID uuid, UUID uuid2, ListenerCallback listenerCallback) {
        super(uuid, uuid2);
        this.GATT_NOTIFY_CONFIG = UUID.fromString(b.CLIENT_CHARACTERISTIC_CONFIG);
        this.isEnable = true;
        this.callback = listenerCallback;
    }

    public EnableNotifyRequest(UUID uuid, UUID uuid2, boolean z) {
        super(uuid, uuid2);
        this.GATT_NOTIFY_CONFIG = UUID.fromString(b.CLIENT_CHARACTERISTIC_CONFIG);
        this.isEnable = true;
        this.isEnable = z;
    }

    @Override // com.oudmon.ble.base.request.BaseRequest
    public boolean execute(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGatt == null) {
            ListenerCallback listenerCallback = this.callback;
            if (listenerCallback != null) {
                listenerCallback.enable(false);
            }
            return false;
        }
        if (!bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, this.isEnable)) {
            XLog.i("open local notify failed");
            ListenerCallback listenerCallback2 = this.callback;
            if (listenerCallback2 != null) {
                listenerCallback2.enable(false);
            }
            return false;
        }
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(this.GATT_NOTIFY_CONFIG);
        if (descriptor == null) {
            XLog.i("descriptor is null, execute failed");
            ListenerCallback listenerCallback3 = this.callback;
            if (listenerCallback3 != null) {
                listenerCallback3.enable(false);
            }
            return false;
        }
        descriptor.setValue(this.isEnable ? BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
        boolean zWriteDescriptor = bluetoothGatt.writeDescriptor(descriptor);
        ListenerCallback listenerCallback4 = this.callback;
        if (listenerCallback4 != null) {
            listenerCallback4.enable(zWriteDescriptor);
        }
        return zWriteDescriptor;
    }

    public boolean isEnable() {
        return this.isEnable;
    }

    public void setEnable(boolean z) {
        this.isEnable = z;
    }
}
