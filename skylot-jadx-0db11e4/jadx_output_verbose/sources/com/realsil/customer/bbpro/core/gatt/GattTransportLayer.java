package com.realsil.customer.bbpro.core.gatt;

import android.bluetooth.BluetoothDevice;
import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.core.transportlayer.TransportLayer;
import com.realsil.customer.core.bluetooth.connection.BluetoothClient;
import com.realsil.customer.core.bluetooth.connection.le.BluetoothGattClientCallback;
import com.realsil.customer.core.bluetooth.connection.le.GattConnParams;
import com.realsil.customer.core.logger.ZLogger;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/core/gatt/GattTransportLayer.class */
public final class GattTransportLayer extends TransportLayer {
    public static volatile GattTransportLayer n;
    public GattLayer l = null;
    public final BluetoothGattClientCallback m = new BluetoothGattClientCallback() { // from class: com.realsil.customer.bbpro.core.gatt.GattTransportLayer.1
        @Override // com.realsil.customer.core.bluetooth.connection.BluetoothClientCallback
        public void onConnectionStateChanged(BluetoothClient bluetoothClient, boolean z, int i) {
            if (!z || i == 0) {
                GattTransportLayer.this.a();
            }
            GattTransportLayer.this.a(bluetoothClient.getDevice(), z, i);
        }

        @Override // com.realsil.customer.core.bluetooth.connection.BluetoothClientCallback
        public void onDataReceive(byte[] bArr) {
            GattTransportLayer.this.a(bArr);
        }

        @Override // com.realsil.customer.core.bluetooth.connection.BluetoothClientCallback
        public void onDataReceive(BluetoothClient bluetoothClient, byte[] bArr) {
            GattTransportLayer.this.a(bArr);
        }
    };

    public GattTransportLayer() {
        c();
        e();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.realsil.customer.bbpro.core.gatt.GattTransportLayer>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static synchronized GattTransportLayer getInstance() {
        if (n == null) {
            ?? r0 = GattTransportLayer.class;
            synchronized (r0) {
                if (n == null) {
                    n = new GattTransportLayer();
                }
                r0 = r0;
            }
        }
        return n;
    }

    public boolean connect(GattConnParams gattConnParams) {
        b();
        return this.l.connect(gattConnParams);
    }

    @Override // com.realsil.customer.bbpro.core.transportlayer.TransportLayer
    public boolean isConnected(BluetoothDevice bluetoothDevice) {
        GattLayer gattLayer = this.l;
        if (gattLayer != null) {
            return gattLayer.getConnectState() == 2;
        }
        ZLogger.w("GattLayer == null");
        return false;
    }

    @Override // com.realsil.customer.bbpro.core.transportlayer.TransportLayer
    public synchronized boolean sendCommandInner(Command command) {
        return sendData(command.encode());
    }

    @Override // com.realsil.customer.bbpro.core.transportlayer.TransportLayer
    public synchronized boolean sendData(byte[] bArr) {
        GattLayer gattLayer = this.l;
        if (gattLayer == null) {
            return false;
        }
        return gattLayer.sendData(bArr);
    }

    @Override // com.realsil.customer.bbpro.core.transportlayer.TransportLayer
    public void destroy() {
        super.destroy();
        GattLayer gattLayer = this.l;
        if (gattLayer != null) {
            gattLayer.destroy();
        }
    }

    @Override // com.realsil.customer.bbpro.core.transportlayer.TransportLayer
    public void disconnect() {
        super.disconnect();
        GattLayer gattLayer = this.l;
        if (gattLayer != null) {
            gattLayer.closeGatt();
        }
    }

    public final void e() {
        if (this.l == null) {
            this.l = new GattLayer(this.m);
        }
    }
}
