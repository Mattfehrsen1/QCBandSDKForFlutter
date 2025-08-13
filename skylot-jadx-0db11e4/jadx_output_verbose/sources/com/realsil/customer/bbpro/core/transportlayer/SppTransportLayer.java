package com.realsil.customer.bbpro.core.transportlayer;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.ParcelUuid;
import com.realsil.customer.bbpro.core.Utils;
import com.realsil.customer.core.bluetooth.connection.BluetoothClient;
import com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp;
import com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSppCallback;
import com.realsil.customer.core.bluetooth.connection.legacy.SppConnParameters;
import com.realsil.customer.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.customer.core.bluetooth.utils.BluetoothHelper;
import com.realsil.customer.core.logger.ZLogger;
import java.io.IOException;
import java.util.Locale;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/core/transportlayer/SppTransportLayer.class */
public final class SppTransportLayer extends TransportLayer {
    public static volatile SppTransportLayer o;
    public BluetoothSpp l;
    public UUID m = TransportConnParams.VENDOR_SPP_UUID;
    public final BluetoothSppCallback n = new BluetoothSppCallback() { // from class: com.realsil.customer.bbpro.core.transportlayer.SppTransportLayer.1
        @Override // com.realsil.customer.core.bluetooth.connection.BluetoothClientCallback
        public void onConnectionStateChanged(BluetoothClient bluetoothClient, boolean z, int i) {
            super.onConnectionStateChanged(bluetoothClient, z, i);
            BluetoothDevice device = bluetoothClient.getDevice();
            String address = device != null ? device.getAddress() : null;
            if (TransportLayer.k) {
                ZLogger.v(String.format(Locale.US, "%s status: %b 0x%04X", BluetoothHelper.formatAddress(address, true), Boolean.valueOf(z), Integer.valueOf(i)));
            }
            if (!z || i == 0) {
                SppTransportLayer.this.a();
            }
            SppTransportLayer.this.a(device, z, i);
        }

        @Override // com.realsil.customer.core.bluetooth.connection.BluetoothClientCallback
        public void onDataReceive(byte[] bArr) {
            SppTransportLayer.this.a(bArr);
        }

        @Override // com.realsil.customer.core.bluetooth.connection.BluetoothClientCallback
        public void onDataReceive(BluetoothClient bluetoothClient, byte[] bArr) {
            SppTransportLayer.this.a(bArr);
        }
    };

    public SppTransportLayer() {
        c();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.realsil.customer.bbpro.core.transportlayer.SppTransportLayer>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static synchronized SppTransportLayer getInstance() {
        if (o == null) {
            ?? r0 = SppTransportLayer.class;
            synchronized (r0) {
                if (o == null) {
                    o = new SppTransportLayer();
                }
                r0 = r0;
            }
        }
        return o;
    }

    @Override // com.realsil.customer.bbpro.core.transportlayer.TransportLayer
    public boolean isConnected(BluetoothDevice bluetoothDevice) {
        BluetoothSpp bluetoothSpp = this.l;
        if (bluetoothSpp == null) {
            return false;
        }
        return bluetoothSpp.isConnected(bluetoothDevice);
    }

    public int getConnectionState() {
        if (this.l == null) {
            this.l = new BluetoothSpp(this.n);
        }
        return this.l.getConnectionState();
    }

    public boolean connect(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket) {
        return a(bluetoothDevice, bluetoothSocket, this.m, 0);
    }

    public void destory() throws IOException {
        destroy();
    }

    @Override // com.realsil.customer.bbpro.core.transportlayer.TransportLayer
    public void disconnect() throws IOException {
        super.disconnect();
        BluetoothSpp bluetoothSpp = this.l;
        if (bluetoothSpp != null) {
            bluetoothSpp.stop();
        }
    }

    @Override // com.realsil.customer.bbpro.core.transportlayer.TransportLayer
    public void destroy() throws IOException {
        super.destroy();
        BluetoothSpp bluetoothSpp = this.l;
        if (bluetoothSpp != null) {
            bluetoothSpp.destroy();
            this.l = null;
        }
    }

    @Override // com.realsil.customer.bbpro.core.transportlayer.TransportLayer
    public synchronized boolean sendCommandInner(Command command) {
        return sendData(command.encode());
    }

    @Override // com.realsil.customer.bbpro.core.transportlayer.TransportLayer
    public boolean sendData(byte[] bArr) {
        if (this.l == null) {
            this.l = new BluetoothSpp(this.n);
        }
        return this.l.write(bArr);
    }

    public final boolean a(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket, UUID uuid, int i) {
        if (bluetoothDevice == null) {
            return false;
        }
        if (this.l == null) {
            this.l = new BluetoothSpp(this.n);
        }
        if (this.l.isConnected(bluetoothDevice)) {
            BluetoothSppCallback bluetoothSppCallback = this.n;
            if (bluetoothSppCallback == null) {
                return true;
            }
            if (this.l == null) {
                this.l = new BluetoothSpp(this.n);
            }
            bluetoothSppCallback.onConnectionStateChanged(this.l, true, 2);
            return true;
        }
        b();
        if (TransportLayer.k) {
            BluetoothDeviceImpl.dumpSupportedUuids(bluetoothDevice);
        }
        ParcelUuid uuid2 = Utils.getUuid(bluetoothDevice.getUuids(), uuid, true);
        ParcelUuid parcelUuid = uuid2;
        if (uuid2 != null) {
            ZLogger.v(TransportLayer.D, "use pref spp: " + uuid);
        } else {
            if (i == 1) {
                ZLogger.w(TransportLayer.D, "not find pref spp: " + uuid);
                return false;
            }
            parcelUuid = TransportConnParams.WELL_KNOWN_SPP_UUID;
            ZLogger.v(TransportLayer.D, "use well-known spp: " + parcelUuid.toString());
        }
        this.m = parcelUuid.getUuid();
        SppConnParameters sppConnParametersBuild = new SppConnParameters.Builder(bluetoothDevice).bluetoothSocket(bluetoothSocket).uuid(parcelUuid.getUuid()).build();
        if (this.l == null) {
            this.l = new BluetoothSpp(this.n);
        }
        return this.l.connect(sppConnParametersBuild);
    }

    public boolean connect(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket, UUID uuid) {
        return a(bluetoothDevice, bluetoothSocket, uuid, 0);
    }

    public boolean connect(TransportConnParams transportConnParams) {
        if (transportConnParams == null) {
            ZLogger.w("connParams can not be null");
            return false;
        }
        return a(transportConnParams.getBluetoothDevice(), transportConnParams.getBluetoothSocket(), transportConnParams.getUuid(), transportConnParams.getTransport());
    }
}
