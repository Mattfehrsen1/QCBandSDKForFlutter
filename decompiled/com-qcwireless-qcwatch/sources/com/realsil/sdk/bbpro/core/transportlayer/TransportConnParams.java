package com.realsil.sdk.bbpro.core.transportlayer;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.ParcelUuid;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import java.util.Locale;
import java.util.UUID;

/* loaded from: classes3.dex */
public final class TransportConnParams {
    public static final int TRANSPORT_AUTO = 0;
    public static final int TRANSPORT_VENDOR = 1;
    public BluetoothDevice a;
    public BluetoothSocket b;
    public UUID c;
    public boolean d;
    public int e;
    public static final ParcelUuid WELL_KNOWN_SPP_UUID = ParcelUuid.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public static final UUID VENDOR_SPP_UUID = UUID.fromString("6A24EEAB-4B65-4693-986B-3C26C352264F");

    public TransportConnParams(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket, UUID uuid, boolean z, int i) {
        this.a = bluetoothDevice;
        this.b = bluetoothSocket;
        this.c = uuid;
        this.d = z;
        this.e = i;
    }

    public BluetoothDevice getBluetoothDevice() {
        return this.a;
    }

    public BluetoothSocket getBluetoothSocket() {
        return this.b;
    }

    public int getTransport() {
        return this.e;
    }

    public UUID getUuid() {
        return this.c;
    }

    public boolean isFreshUuid() {
        return this.d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ConnectionParameters{\n");
        if (this.a != null) {
            sb.append("\n\tdevice:");
            sb.append(BluetoothHelper.formatAddress(this.a.getAddress(), true));
        }
        UUID uuid = this.c;
        if (uuid != null) {
            sb.append(String.format(Locale.US, "\n\tuuid:%s, fresh=%b, transport=%d", uuid.toString(), Boolean.valueOf(this.d), Integer.valueOf(this.e)));
        }
        sb.append("\n}");
        return sb.toString();
    }

    public static final class Builder {
        public BluetoothDevice a;
        public BluetoothSocket b;
        public UUID c = TransportConnParams.VENDOR_SPP_UUID;
        public boolean d = false;
        public int e = 0;

        public Builder(BluetoothDevice bluetoothDevice) {
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

        public TransportConnParams build() {
            return new TransportConnParams(this.a, this.b, this.c, this.d, this.e);
        }

        public Builder freshUuid(boolean z) {
            this.d = z;
            return this;
        }

        public Builder transport(int i) {
            this.e = i;
            return this;
        }

        public Builder uuid(UUID uuid) {
            if (uuid != null) {
                this.c = uuid;
            }
            return this;
        }

        public Builder(BluetoothSocket bluetoothSocket) {
            this.b = bluetoothSocket;
        }
    }
}
