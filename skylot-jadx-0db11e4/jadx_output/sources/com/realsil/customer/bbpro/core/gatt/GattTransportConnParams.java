package com.realsil.customer.bbpro.core.gatt;

import android.bluetooth.BluetoothDevice;
import com.realsil.customer.core.bluetooth.utils.BluetoothHelper;
import java.util.Objects;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/core/gatt/GattTransportConnParams.class */
public final class GattTransportConnParams {
    public final BluetoothDevice a;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/core/gatt/GattTransportConnParams$Builder.class */
    public static final class Builder {
        public BluetoothDevice a;

        public Builder(BluetoothDevice bluetoothDevice) {
            this.a = bluetoothDevice;
        }

        public Builder bluetoothDevice(BluetoothDevice bluetoothDevice) {
            this.a = bluetoothDevice;
            return this;
        }

        public Builder serviceUuid(UUID uuid) {
            return this;
        }

        public Builder txUuid(UUID uuid) {
            return this;
        }

        public Builder rxUuid(UUID uuid) {
            return this;
        }

        public GattTransportConnParams build() {
            Objects.requireNonNull(this.a, "bluetoothDevice cannot be null");
            return new GattTransportConnParams(this.a);
        }
    }

    public GattTransportConnParams(BluetoothDevice bluetoothDevice) {
        this.a = bluetoothDevice;
    }

    public BluetoothDevice getBluetoothDevice() {
        return this.a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GattTransportConnParams{\n");
        if (this.a != null) {
            sb.append("\n\tdevice:").append(BluetoothHelper.formatAddress(this.a.getAddress(), true));
        }
        sb.append("\n}");
        return sb.toString();
    }
}
