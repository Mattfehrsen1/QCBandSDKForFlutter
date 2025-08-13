package com.realsil.customer.core.bluetooth.connection.legacy;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import com.realsil.customer.core.bluetooth.connection.BluetoothClient;
import com.realsil.customer.core.bluetooth.connection.legacy.SppConnParameters;
import com.realsil.customer.core.logger.ZLogger;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/connection/legacy/BluetoothSppManager.class */
public final class BluetoothSppManager {
    public static UUID UUID_SECURE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public static volatile BluetoothSppManager f;
    public BluetoothSpp b;
    public volatile boolean c;
    public final Object d = new Object();
    public final a e = new a();
    public CopyOnWriteArrayList a = new CopyOnWriteArrayList();

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/connection/legacy/BluetoothSppManager$a.class */
    public class a extends BluetoothSppCallback {
        public a() {
        }

        @Override // com.realsil.customer.core.bluetooth.connection.BluetoothClientCallback
        public final void onConnectionStateChanged(BluetoothClient bluetoothClient, boolean z, int i) {
            super.onConnectionStateChanged(bluetoothClient, z, i);
            synchronized (BluetoothSppManager.this.a) {
                CopyOnWriteArrayList copyOnWriteArrayList = BluetoothSppManager.this.a;
                if (copyOnWriteArrayList != null && copyOnWriteArrayList.size() > 0) {
                    Iterator it = BluetoothSppManager.this.a.iterator();
                    while (it.hasNext()) {
                        ((BluetoothSppCallback) it.next()).onConnectionStateChanged(bluetoothClient, z, i);
                    }
                }
            }
        }

        @Override // com.realsil.customer.core.bluetooth.connection.BluetoothClientCallback
        public final void onDataReceive(BluetoothClient bluetoothClient, byte[] bArr) {
            super.onDataReceive(bluetoothClient, bArr);
            synchronized (BluetoothSppManager.this.a) {
                CopyOnWriteArrayList copyOnWriteArrayList = BluetoothSppManager.this.a;
                if (copyOnWriteArrayList != null && copyOnWriteArrayList.size() > 0) {
                    Iterator it = BluetoothSppManager.this.a.iterator();
                    while (it.hasNext()) {
                        ((BluetoothSppCallback) it.next()).onDataReceive(bluetoothClient, bArr);
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Class<com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSppManager>] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4 */
    public static synchronized void initialize() {
        if (f == null) {
            ?? r0 = BluetoothSppManager.class;
            synchronized (r0) {
                if (f == null) {
                    f = new BluetoothSppManager();
                }
                r0 = r0;
            }
        }
    }

    public static BluetoothSppManager getInstance() {
        return f;
    }

    public synchronized boolean connect(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket, BluetoothSppCallback bluetoothSppCallback) throws IOException {
        register(bluetoothSppCallback);
        if (getConnectionState() == 2) {
            BluetoothDevice device = a().getDevice();
            if (device != null && device.equals(bluetoothDevice)) {
                bluetoothSppCallback.onConnectionStateChanged(a(), true, 2);
                return true;
            }
            ZLogger.d("current connected device is conflict with the connecting device");
        }
        boolean zConnect = a().connect(new SppConnParameters.Builder(bluetoothDevice).bluetoothSocket(bluetoothSocket).build());
        if (!zConnect) {
            unregister(bluetoothSppCallback);
        }
        return zConnect;
    }

    public int getConnectionState() {
        return a().getConnectionState();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, java.lang.Throwable] */
    public void notifyAck() {
        synchronized (this.d) {
            this.c = true;
            this.d.notifyAll();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v7, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSppManager, java.lang.Throwable] */
    public synchronized boolean sendPacket(byte[] bArr, boolean z) {
        if (bArr == null) {
            return false;
        }
        if (!z) {
            this.c = false;
        }
        if (!a().write(bArr)) {
            ZLogger.w("send spp data failed");
            return false;
        }
        if (z) {
            return true;
        }
        synchronized (this.d) {
            ?? r0 = this.c;
            if (r0 != 0) {
                return true;
            }
            try {
                r0 = this.d;
                r0.wait(5000L);
            } catch (InterruptedException unused) {
                r0.printStackTrace();
            }
            return this.c;
        }
    }

    public boolean write(byte[] bArr) {
        return a().write(bArr);
    }

    public void disconnect() throws IOException {
        a().stop();
    }

    public void destroy() {
        synchronized (this.a) {
            CopyOnWriteArrayList copyOnWriteArrayList = this.a;
            if (copyOnWriteArrayList != null) {
                copyOnWriteArrayList.clear();
            }
        }
        disconnect();
    }

    public void register(BluetoothSppCallback bluetoothSppCallback) {
        synchronized (this.a) {
            if (this.a == null) {
                this.a = new CopyOnWriteArrayList();
            }
            if (!this.a.contains(bluetoothSppCallback)) {
                this.a.add(bluetoothSppCallback);
            }
            ZLogger.v("callback's size=" + this.a.size());
        }
    }

    public void unregister(BluetoothSppCallback bluetoothSppCallback) {
        synchronized (this.a) {
            CopyOnWriteArrayList copyOnWriteArrayList = this.a;
            if (copyOnWriteArrayList != null) {
                copyOnWriteArrayList.remove(bluetoothSppCallback);
            }
        }
    }

    public final BluetoothSpp a() {
        if (this.b == null) {
            this.b = new BluetoothSpp(UUID_SECURE, this.e);
        }
        return this.b;
    }

    public synchronized boolean connect(BluetoothDevice bluetoothDevice, BluetoothSppCallback bluetoothSppCallback) throws IOException {
        register(bluetoothSppCallback);
        if (getConnectionState() == 2) {
            BluetoothDevice device = a().getDevice();
            if (device != null && device.equals(bluetoothDevice)) {
                ZLogger.d("connection already connected");
                bluetoothSppCallback.onConnectionStateChanged(a(), true, 2);
                return true;
            }
            ZLogger.d("current connected device is conflict with the connecting device");
        }
        boolean zConnect = a().connect(new SppConnParameters.Builder(bluetoothDevice).build());
        if (!zConnect) {
            unregister(bluetoothSppCallback);
        }
        return zConnect;
    }
}
