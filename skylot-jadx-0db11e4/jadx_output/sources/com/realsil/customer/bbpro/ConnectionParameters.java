package com.realsil.customer.bbpro;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import androidx.annotation.NonNull;
import com.realsil.customer.bbpro.core.transportlayer.TransportConnParams;
import com.realsil.customer.core.bluetooth.utils.BluetoothHelper;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/ConnectionParameters.class */
public final class ConnectionParameters {
    public static final UUID f = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public static final UUID g = UUID.fromString("6A24EEAB-4B65-4693-986B-3C26C352264F");
    public BluetoothDevice a;
    public BluetoothSocket b;
    public UUID c;
    public boolean d;
    public int e;

    public BluetoothDevice a() {
        return this.a;
    }

    public BluetoothSocket b() {
        return this.b;
    }

    public UUID d() {
        return this.c;
    }

    public boolean e() {
        return this.d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ConnectionParameters{\n");
        if (this.a != null) {
            sb.append("\n\tdevice:").append(BluetoothHelper.formatAddress(this.a.getAddress(), true));
        }
        if (this.c != null) {
            sb.append("\n\tuuid:").append(this.c.toString());
        }
        sb.append("\n\tfreshUuid:").append(this.d);
        sb.append("\n}");
        return sb.toString();
    }

    public TransportConnParams c() {
        return new TransportConnParams.Builder(this.a).bluetoothSocket(this.b).uuid(this.c).freshUuid(this.d).transport(this.e).build();
    }

    public ConnectionParameters(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket, UUID uuid, boolean z, int i) {
        this.a = bluetoothDevice;
        this.b = bluetoothSocket;
        this.c = uuid;
        this.d = z;
        this.e = i;
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/ConnectionParameters$Builder.class */
    public static final class Builder {
        public BluetoothDevice a;
        public BluetoothSocket b;
        public UUID c = ConnectionParameters.g;
        public boolean d = false;
        public int e = 0;

        public Builder(@NonNull BluetoothDevice bluetoothDevice) {
            this.a = bluetoothDevice;
        }

        public Builder bluetoothDevice(BluetoothDevice bluetoothDevice) {
            this.a = bluetoothDevice;
            return this;
        }

        public Builder bluetoothSocket(BluetoothSocket bluetoothSocket) {
            this.b = bluetoothSocket;
            return this;
        }

        public Builder uuid(UUID uuid) {
            if (uuid != null) {
                this.c = uuid;
            }
            return this;
        }

        public Builder freshUuid(boolean z) {
            this.d = z;
            return this;
        }

        public Builder transport(int i) {
            this.e = i;
            return this;
        }

        public ConnectionParameters build() {
            return new ConnectionParameters(this.a, this.b, this.c, this.d, this.e);
        }

        public Builder(@NonNull BluetoothSocket bluetoothSocket) {
            this.b = bluetoothSocket;
        }
    }
}
