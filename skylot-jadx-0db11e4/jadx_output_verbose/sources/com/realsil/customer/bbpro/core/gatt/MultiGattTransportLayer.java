package com.realsil.customer.bbpro.core.gatt;

import android.bluetooth.BluetoothDevice;
import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.core.transportlayer.TransportLayer;
import com.realsil.customer.core.bluetooth.connection.BluetoothClient;
import com.realsil.customer.core.bluetooth.connection.le.BluetoothGattClientCallback;
import com.realsil.customer.core.bluetooth.connection.le.GattConnParams;
import com.realsil.customer.core.logger.ZLogger;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/core/gatt/MultiGattTransportLayer.class */
public final class MultiGattTransportLayer extends TransportLayer {
    public static volatile MultiGattTransportLayer o;
    public final HashMap<String, GattLayer> m;
    public final Object l = new Object();
    public final BluetoothGattClientCallback n = new BluetoothGattClientCallback() { // from class: com.realsil.customer.bbpro.core.gatt.MultiGattTransportLayer.1
        @Override // com.realsil.customer.core.bluetooth.connection.BluetoothClientCallback
        public void onConnectionStateChanged(BluetoothClient bluetoothClient, boolean z, int i) {
            if (!z || i == 0) {
                MultiGattTransportLayer.this.a();
            }
            MultiGattTransportLayer.this.a(bluetoothClient.getDevice(), z, i);
        }

        @Override // com.realsil.customer.core.bluetooth.connection.BluetoothClientCallback
        public void onDataReceive(byte[] bArr) {
            MultiGattTransportLayer.this.a(bArr);
        }

        @Override // com.realsil.customer.core.bluetooth.connection.BluetoothClientCallback
        public void onDataReceive(BluetoothClient bluetoothClient, byte[] bArr) {
            MultiGattTransportLayer.this.a(bArr);
        }
    };

    public MultiGattTransportLayer() {
        c();
        this.m = new HashMap<>();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.realsil.customer.bbpro.core.gatt.MultiGattTransportLayer>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static synchronized MultiGattTransportLayer getInstance() {
        if (o == null) {
            ?? r0 = MultiGattTransportLayer.class;
            synchronized (r0) {
                if (o == null) {
                    o = new MultiGattTransportLayer();
                }
                r0 = r0;
            }
        }
        return o;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.realsil.customer.bbpro.core.gatt.MultiGattTransportLayer] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.realsil.customer.bbpro.core.gatt.GattLayer, com.realsil.customer.core.bluetooth.connection.le.BluetoothGattClientImpl] */
    public boolean connect(GattConnParams gattConnParams) {
        ?? A = this;
        b();
        String address = gattConnParams.getAddress();
        synchronized (A) {
            A = A.a(address, true);
            return A.connect(gattConnParams);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.realsil.customer.bbpro.core.transportlayer.TransportLayer
    public boolean isConnected(BluetoothDevice bluetoothDevice) {
        synchronized (this.l) {
            GattLayer gattLayerA = a(bluetoothDevice.getAddress(), false);
            if (gattLayerA == null) {
                return false;
            }
            return gattLayerA.getConnectState() == 2;
        }
    }

    @Override // com.realsil.customer.bbpro.core.transportlayer.TransportLayer
    public synchronized boolean sendCommandInner(Command command) {
        return a(command.getDst()).sendData(command.encode());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.realsil.customer.bbpro.core.gatt.MultiGattTransportLayer, com.realsil.customer.bbpro.core.transportlayer.TransportLayer] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3 */
    @Override // com.realsil.customer.bbpro.core.transportlayer.TransportLayer
    public void destroy() {
        ?? r0 = this;
        super.destroy();
        Object obj = r0.l;
        synchronized (obj) {
            HashMap<String, GattLayer> map = r0.m;
            if (map != null) {
                Iterator<GattLayer> it = map.values().iterator();
                while (it.hasNext()) {
                    it.next().destroy();
                }
            }
            r0 = obj;
        }
    }

    public void destory() {
        destroy();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.realsil.customer.bbpro.core.gatt.MultiGattTransportLayer] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3 */
    public void disconnect(BluetoothDevice bluetoothDevice) {
        ?? r0 = this;
        Object obj = this.l;
        synchronized (obj) {
            GattLayer gattLayerA = r0.a(bluetoothDevice.getAddress(), false);
            if (gattLayerA != null) {
                ZLogger.v("pending to disconnect :" + bluetoothDevice.getAddress());
                gattLayerA.disconnect();
            }
            r0 = obj;
        }
    }

    public final synchronized GattLayer a(String str) {
        return a(str, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.realsil.customer.bbpro.core.gatt.MultiGattTransportLayer] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.realsil.customer.bbpro.core.gatt.GattLayer] */
    public final synchronized GattLayer a(String str, boolean z) {
        GattLayer gattLayer;
        ?? r0 = this;
        GattLayer gattLayer2 = null;
        synchronized (r0.l) {
            HashMap<String, GattLayer> map = r0.m;
            if (map != null) {
                gattLayer2 = map.get(str);
            }
            if (gattLayer2 == null && z) {
                gattLayer2 = gattLayer;
                gattLayer = new GattLayer(this.n);
                this.m.put(str, gattLayer2);
            }
            r0 = gattLayer2;
        }
        return r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.realsil.customer.bbpro.core.gatt.MultiGattTransportLayer, com.realsil.customer.bbpro.core.transportlayer.TransportLayer] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3 */
    @Override // com.realsil.customer.bbpro.core.transportlayer.TransportLayer
    public void disconnect() {
        ?? r0 = this;
        super.disconnect();
        Object obj = r0.l;
        synchronized (obj) {
            HashMap<String, GattLayer> map = r0.m;
            if (map != null) {
                Iterator<GattLayer> it = map.values().iterator();
                while (it.hasNext()) {
                    it.next().closeGatt();
                }
            }
            r0 = obj;
        }
    }
}
