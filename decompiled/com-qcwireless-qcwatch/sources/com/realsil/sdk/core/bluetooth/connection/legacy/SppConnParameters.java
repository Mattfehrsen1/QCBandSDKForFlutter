package com.realsil.sdk.core.bluetooth.connection.legacy;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import java.util.UUID;

/* loaded from: classes3.dex */
public final class SppConnParameters {
    public BluetoothDevice a;
    public BluetoothSocket b;
    public UUID c;
    public boolean d;
    public static final UUID WELL_KNOWN_SPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public static final UUID VENDOR_SPP_UUID = UUID.fromString("6A24EEAB-4B65-4693-986B-3C26C352264F");

    public static final class Builder {
        public BluetoothDevice a;
        public BluetoothSocket b;
        public UUID c = SppConnParameters.VENDOR_SPP_UUID;
        public boolean d = false;

        public Builder(BluetoothDevice bluetoothDevice) {
            this.a = bluetoothDevice;
        }

        public Builder bluetoothSocket(BluetoothSocket bluetoothSocket) {
            this.b = bluetoothSocket;
            return this;
        }

        public SppConnParameters build() {
            return new SppConnParameters(this.a, this.b, this.c, this.d);
        }

        public Builder freshUuid(boolean z) {
            this.d = z;
            return this;
        }

        public Builder uuid(UUID uuid) {
            if (uuid != null) {
                this.c = uuid;
            }
            return this;
        }
    }

    public SppConnParameters(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket, UUID uuid, boolean z) {
        this.a = bluetoothDevice;
        this.b = bluetoothSocket;
        this.c = uuid;
        this.d = z;
    }

    public BluetoothDevice getBluetoothDevice() {
        return this.a;
    }

    public BluetoothSocket getBluetoothSocket() {
        return this.b;
    }

    public UUID getUuid() {
        return this.c;
    }

    public boolean isFreshUuid() {
        return this.d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SppConnParameters{\n");
        if (this.a != null) {
            sb.append("\n\tdevice:");
            sb.append(BluetoothHelper.formatAddress(this.a.getAddress(), true));
        }
        if (this.c != null) {
            sb.append("\n\tuuid:");
            sb.append(this.c.toString());
        }
        sb.append("\n\tfreshUuid:");
        sb.append(this.d);
        sb.append("\n}");
        return sb.toString();
    }
}
