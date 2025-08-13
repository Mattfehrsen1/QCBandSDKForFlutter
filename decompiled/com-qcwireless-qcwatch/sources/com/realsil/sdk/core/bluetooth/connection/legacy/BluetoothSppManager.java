package com.realsil.sdk.core.bluetooth.connection.legacy;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import com.realsil.sdk.core.bluetooth.connection.legacy.SppConnParameters;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes3.dex */
public final class BluetoothSppManager {
    public static UUID UUID_SECURE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public static volatile BluetoothSppManager f;
    public BluetoothSpp b;
    public volatile boolean c;
    public final Object d = new Object();
    public a e = new a();
    public List<BluetoothSppCallback> a = new CopyOnWriteArrayList();

    public class a extends BluetoothSppCallback {
        public a() {
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [java.util.List<com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSppCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        /* JADX WARN: Type inference failed for: r1v4, types: [java.util.List<com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSppCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        @Override // com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSppCallback
        public final void onConnectionStateChanged(BluetoothSpp bluetoothSpp, boolean z, int i) {
            super.onConnectionStateChanged(bluetoothSpp, z, i);
            synchronized (BluetoothSppManager.this.a) {
                ?? r1 = BluetoothSppManager.this.a;
                if (r1 != 0 && r1.size() > 0) {
                    Iterator it = BluetoothSppManager.this.a.iterator();
                    while (it.hasNext()) {
                        ((BluetoothSppCallback) it.next()).onConnectionStateChanged(bluetoothSpp, z, i);
                    }
                }
            }
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [java.util.List<com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSppCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        /* JADX WARN: Type inference failed for: r1v4, types: [java.util.List<com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSppCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        @Override // com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSppCallback
        public final void onDataReceive(byte[] bArr) {
            super.onDataReceive(bArr);
            synchronized (BluetoothSppManager.this.a) {
                ?? r1 = BluetoothSppManager.this.a;
                if (r1 != 0 && r1.size() > 0) {
                    Iterator it = BluetoothSppManager.this.a.iterator();
                    while (it.hasNext()) {
                        ((BluetoothSppCallback) it.next()).onDataReceive(bArr);
                    }
                }
            }
        }
    }

    public static BluetoothSppManager getInstance() {
        return f;
    }

    public static synchronized void initialize() {
        if (f == null) {
            synchronized (BluetoothSppManager.class) {
                if (f == null) {
                    f = new BluetoothSppManager();
                }
            }
        }
    }

    public final BluetoothSpp a() {
        if (this.b == null) {
            this.b = new BluetoothSpp(UUID_SECURE, this.e);
        }
        return this.b;
    }

    public synchronized boolean connect(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket, BluetoothSppCallback bluetoothSppCallback) {
        register(bluetoothSppCallback);
        if (getConnectionState() == 512) {
            BluetoothDevice device = a().getDevice();
            if (device != null && device.equals(bluetoothDevice)) {
                bluetoothSppCallback.onConnectionStateChanged(a(), true, 512);
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

    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.List<com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSppCallback>, java.util.concurrent.CopyOnWriteArrayList] */
    public void destroy() {
        synchronized (this.a) {
            ?? r1 = this.a;
            if (r1 != 0) {
                r1.clear();
            }
        }
        disconnect();
    }

    public void disconnect() {
        a().stop();
    }

    public int getConnectionState() {
        return a().getConnectionState();
    }

    public void notifyAck() {
        synchronized (this.d) {
            this.c = true;
            this.d.notifyAll();
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.List<com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSppCallback>, java.util.concurrent.CopyOnWriteArrayList] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.List<com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSppCallback>, java.util.concurrent.CopyOnWriteArrayList] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.util.List<com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSppCallback>, java.util.concurrent.CopyOnWriteArrayList] */
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

    /* JADX WARN: Removed duplicated region for block: B:11:0x0014 A[Catch: all -> 0x003f, TRY_LEAVE, TryCatch #2 {, blocks: (B:8:0x0008, B:9:0x000a, B:11:0x0014, B:18:0x0021, B:19:0x0023, B:35:0x003e, B:20:0x0024, B:22:0x0028, B:26:0x0034, B:27:0x0036, B:30:0x0039, B:25:0x0031), top: B:42:0x0008, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean sendPacket(byte[] r3, boolean r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            r0 = 0
            if (r3 != 0) goto L6
            monitor-exit(r2)
            return r0
        L6:
            if (r4 != 0) goto La
            r2.c = r0     // Catch: java.lang.Throwable -> L3f
        La:
            com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSpp r1 = r2.a()     // Catch: java.lang.Throwable -> L3f
            boolean r3 = r1.write(r3)     // Catch: java.lang.Throwable -> L3f
            if (r3 != 0) goto L1c
            java.lang.String r3 = "send spp data failed"
            com.realsil.sdk.core.logger.ZLogger.w(r3)     // Catch: java.lang.Throwable -> L3f
            monitor-exit(r2)
            return r0
        L1c:
            r3 = 1
            if (r4 == 0) goto L21
            monitor-exit(r2)
            return r3
        L21:
            java.lang.Object r4 = r2.d     // Catch: java.lang.Throwable -> L3f
            monitor-enter(r4)     // Catch: java.lang.Throwable -> L3f
            boolean r0 = r2.c     // Catch: java.lang.Throwable -> L3c
            if (r0 != 0) goto L39
            java.lang.Object r3 = r2.d     // Catch: java.lang.InterruptedException -> L30 java.lang.Throwable -> L3c
            r0 = 5000(0x1388, double:2.4703E-320)
            r3.wait(r0)     // Catch: java.lang.InterruptedException -> L30 java.lang.Throwable -> L3c
            goto L34
        L30:
            r3 = move-exception
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L3c
        L34:
            boolean r3 = r2.c     // Catch: java.lang.Throwable -> L3c
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L3c
            monitor-exit(r2)
            return r3
        L39:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L3c
            monitor-exit(r2)
            return r3
        L3c:
            r3 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L3c
            throw r3     // Catch: java.lang.Throwable -> L3f
        L3f:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSppManager.sendPacket(byte[], boolean):boolean");
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.List<com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSppCallback>, java.util.concurrent.CopyOnWriteArrayList] */
    public void unregister(BluetoothSppCallback bluetoothSppCallback) {
        synchronized (this.a) {
            ?? r1 = this.a;
            if (r1 != 0) {
                r1.remove(bluetoothSppCallback);
            }
        }
    }

    public boolean write(byte[] bArr) {
        return a().write(bArr);
    }

    public synchronized boolean connect(BluetoothDevice bluetoothDevice, BluetoothSppCallback bluetoothSppCallback) {
        register(bluetoothSppCallback);
        if (getConnectionState() == 512) {
            BluetoothDevice device = a().getDevice();
            if (device != null && device.equals(bluetoothDevice)) {
                ZLogger.d("connection already connected");
                bluetoothSppCallback.onConnectionStateChanged(a(), true, 512);
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
