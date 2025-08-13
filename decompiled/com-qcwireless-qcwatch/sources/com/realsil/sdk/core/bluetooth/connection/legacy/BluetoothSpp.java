package com.realsil.sdk.core.bluetooth.connection.legacy;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Build;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.UUID;

/* loaded from: classes3.dex */
public final class BluetoothSpp {
    public static final int ROLE_CLIENT = 1;
    public static final int ROLE_SERVER = 2;
    public static final int STATE_CONNECTED = 512;
    public static final int STATE_CONNECTING = 256;
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_DISCONNECTING = 768;
    public static final int STATE_LISTEN = 257;
    public static final int STATE_NONE = 0;
    public static final UUID p = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public boolean a;
    public boolean b;
    public BluetoothAdapter c;
    public volatile int d;
    public BluetoothDevice e;
    public boolean f;
    public SppConnParameters g;
    public int h;
    public UUID i;
    public boolean initialized;
    public b j;
    public c k;
    public a l;
    public int m;
    public BluetoothSppCallback mCallback;
    public final Object n;
    public Boolean o;

    public BluetoothSpp(BluetoothSppCallback bluetoothSppCallback) {
        this(1, p, bluetoothSppCallback);
    }

    public static boolean c(BluetoothSpp bluetoothSpp) {
        return bluetoothSpp.d == 256;
    }

    public final void b() {
        ZLogger.v(this.a, "initialize...");
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.c = defaultAdapter;
        if (defaultAdapter == null) {
            ZLogger.d("bluetoothAdapter not initialized ");
            this.initialized = false;
        } else if (defaultAdapter.isEnabled()) {
            this.initialized = true;
        } else {
            ZLogger.d("bluetooth is disabled");
            this.initialized = false;
        }
    }

    public synchronized boolean connect(SppConnParameters sppConnParameters) {
        if (sppConnParameters == null) {
            ZLogger.v("connParameters can not be null or empty");
            return false;
        }
        if (sppConnParameters.getBluetoothDevice() == null) {
            ZLogger.v("device can not be null or empty");
            return false;
        }
        BluetoothDevice bluetoothDevice = this.e;
        if (bluetoothDevice != null) {
            if (bluetoothDevice.equals(sppConnParameters.getBluetoothDevice())) {
                if (this.d == 512) {
                    ZLogger.v(this.a, "device already connected");
                    a(512);
                    return true;
                }
                if (this.d == 256) {
                    ZLogger.v(this.a, "device is already at connecting state");
                    a(256);
                    return true;
                }
            } else {
                if (this.d == 512) {
                    ZLogger.v(this.a, "other device already connected");
                    a();
                    return false;
                }
                if (this.d == 256) {
                    ZLogger.v(this.a, "other device is at connecting state");
                    a();
                    return false;
                }
            }
        }
        this.f = true;
        return a(sppConnParameters);
    }

    public synchronized void connected(BluetoothSocket bluetoothSocket, BluetoothDevice bluetoothDevice) {
        ZLogger.v(this.a, "spp connected");
        this.e = bluetoothDevice;
        a();
        a aVar = this.l;
        if (aVar != null) {
            aVar.a();
            this.l = null;
        }
        c cVar = new c(bluetoothSocket);
        this.k = cVar;
        cVar.start();
    }

    public synchronized void destroy() {
        ZLogger.v(this.b, "destroy");
        this.mCallback = null;
        stop();
    }

    public int getConnectionState() {
        return this.d;
    }

    public BluetoothDevice getDevice() {
        return this.e;
    }

    public boolean isConnected(BluetoothDevice bluetoothDevice) {
        BluetoothDevice bluetoothDevice2 = this.e;
        return bluetoothDevice2 != null && bluetoothDevice2.equals(bluetoothDevice) && this.d == 512;
    }

    public synchronized void start(boolean z) {
        ZLogger.v(this.a, "start secure: " + z);
        b bVar = this.j;
        if (bVar != null) {
            bVar.a();
            this.j.interrupt();
            this.j = null;
        }
        c cVar = this.k;
        if (cVar != null) {
            cVar.a();
            this.k.interrupt();
            this.k = null;
        }
        if ((this.h & 2) == 2 && this.l == null) {
            a aVar = new a(z);
            this.l = aVar;
            aVar.start();
        }
    }

    public synchronized void stop() {
        ZLogger.v(this.b, "stop");
        if (this.d == 512) {
            a(STATE_DISCONNECTING);
        }
        this.e = null;
        b bVar = this.j;
        if (bVar != null) {
            bVar.a();
            this.j.interrupt();
            this.j = null;
        }
        c cVar = this.k;
        if (cVar != null) {
            cVar.a();
            this.k.interrupt();
            this.k = null;
        }
        a aVar = this.l;
        if (aVar != null) {
            aVar.a();
            this.l.interrupt();
            this.l = null;
        }
        synchronized (this.n) {
            this.o = Boolean.FALSE;
        }
    }

    public boolean write(byte[] bArr) {
        return write(bArr, true);
    }

    public BluetoothSpp(UUID uuid, BluetoothSppCallback bluetoothSppCallback) {
        this(1, uuid, bluetoothSppCallback);
    }

    public final void a() throws IOException {
        ZLogger.v(this.a, "cancelPreviousConnection");
        b bVar = this.j;
        if (bVar != null) {
            bVar.a();
            this.j.interrupt();
            this.j = null;
        }
        c cVar = this.k;
        if (cVar != null) {
            cVar.a();
            this.k.interrupt();
            this.k = null;
        }
    }

    public boolean write(byte[] bArr, boolean z) throws IOException {
        synchronized (this) {
            if (this.d != 512) {
                ZLogger.d(this.a, "not connected");
                return false;
            }
            c cVar = this.k;
            if (cVar == null) {
                ZLogger.d("ConnectedThread not created");
                return false;
            }
            if (cVar.c == null) {
                return false;
            }
            try {
                if (BluetoothSpp.this.a) {
                    ZLogger.d(String.format(Locale.US, "<< (%d) %s", Integer.valueOf(bArr.length), DataConverter.bytes2Hex(bArr)));
                }
                cVar.c.write(bArr);
                if (z) {
                    cVar.c.flush();
                }
                return true;
            } catch (IOException e) {
                ZLogger.w("Exception during write： " + e);
                return false;
            }
        }
    }

    public BluetoothSpp(int i, UUID uuid, BluetoothSppCallback bluetoothSppCallback) {
        this.a = false;
        this.b = false;
        this.d = 0;
        this.e = null;
        this.f = false;
        this.m = -1;
        this.n = new Object();
        this.o = Boolean.FALSE;
        this.h = i;
        this.i = uuid;
        this.mCallback = bluetoothSppCallback;
        this.d = 0;
        this.a = RtkCore.DEBUG;
        this.b = RtkCore.VDBG;
        b();
    }

    public class c extends Thread {
        public final BluetoothSocket a;
        public BufferedInputStream b;
        public BufferedOutputStream c;

        public c(BluetoothSocket bluetoothSocket) {
            BufferedInputStream bufferedInputStream;
            BufferedOutputStream bufferedOutputStream = null;
            this.b = null;
            this.c = null;
            ZLogger.d("create ConnectedThread");
            this.a = bluetoothSocket;
            try {
                bufferedInputStream = new BufferedInputStream(bluetoothSocket.getInputStream());
            } catch (IOException e) {
                e = e;
                bufferedInputStream = null;
            }
            try {
                bufferedOutputStream = new BufferedOutputStream(bluetoothSocket.getOutputStream());
            } catch (IOException e2) {
                e = e2;
                ZLogger.w("temp sockets not created: " + e);
                this.b = bufferedInputStream;
                this.c = bufferedOutputStream;
            }
            this.b = bufferedInputStream;
            this.c = bufferedOutputStream;
        }

        public final void a() throws IOException {
            BluetoothSocket bluetoothSocket = this.a;
            if (bluetoothSocket == null) {
                return;
            }
            try {
                bluetoothSocket.close();
            } catch (IOException e) {
                ZLogger.w("close socket failed: " + e);
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() throws IOException {
            byte[] bArr = new byte[1024];
            BluetoothSpp bluetoothSpp = BluetoothSpp.this;
            BluetoothDevice bluetoothDevice = bluetoothSpp.e;
            bluetoothSpp.a(512);
            synchronized (BluetoothSpp.this.n) {
                BluetoothSpp.this.o = Boolean.FALSE;
            }
            while (!Thread.currentThread().isInterrupted() && BluetoothSpp.this.d == 512) {
                try {
                    int i = this.b.read(bArr);
                    if (i > 0) {
                        byte[] bArr2 = new byte[i];
                        System.arraycopy(bArr, 0, bArr2, 0, i);
                        if (BluetoothSpp.this.a) {
                            ZLogger.d(String.format(Locale.US, ">> (%d) %s", Integer.valueOf(i), DataConverter.bytes2Hex(bArr2)));
                        }
                        BluetoothSppCallback bluetoothSppCallback = BluetoothSpp.this.mCallback;
                        if (bluetoothSppCallback != null) {
                            bluetoothSppCallback.onDataReceive(bArr2);
                        }
                    }
                } catch (IOException e) {
                    StringBuilder sbA = com.realsil.sdk.core.a.a.a("connectionLost: ");
                    sbA.append(e.toString());
                    ZLogger.w(sbA.toString());
                    BluetoothSpp.a(BluetoothSpp.this);
                }
            }
            if (BluetoothSpp.this.d == 768) {
                a();
                BluetoothSpp.a(BluetoothSpp.this);
            }
        }
    }

    public boolean isConnected() {
        return this.e != null && this.d == 512;
    }

    public final boolean a(SppConnParameters sppConnParameters) throws IOException {
        synchronized (this.n) {
            if (this.o.booleanValue()) {
                ZLogger.w("device is busy");
                return false;
            }
            this.o = Boolean.TRUE;
            if (!this.initialized) {
                b();
            }
            boolean z = this.a;
            StringBuilder sbA = com.realsil.sdk.core.a.a.a("createNewConnection:");
            sbA.append(sppConnParameters.toString());
            ZLogger.v(z, sbA.toString());
            this.g = sppConnParameters;
            this.e = sppConnParameters.getBluetoothDevice();
            this.i = sppConnParameters.getUuid();
            if (sppConnParameters.getBluetoothSocket() != null) {
                connected(sppConnParameters.getBluetoothSocket(), sppConnParameters.getBluetoothDevice());
                return true;
            }
            a();
            b bVar = new b(this.e);
            this.j = bVar;
            bVar.start();
            return true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0074  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b(com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSpp r7) {
        /*
            boolean r0 = r7.f
            r1 = 0
            r2 = 0
            if (r0 == 0) goto Lad
            r7.f = r2
            android.bluetooth.BluetoothDevice r0 = r7.e
            r3 = 1
            if (r0 != 0) goto L17
            boolean r0 = r7.b
            java.lang.String r3 = "mDevice == null"
            com.realsil.sdk.core.logger.ZLogger.v(r0, r3)
        L15:
            r3 = 0
            goto L72
        L17:
            int r0 = r7.d
            r4 = 256(0x100, float:3.59E-43)
            if (r0 == r4) goto L34
            boolean r0 = r7.b
            java.lang.Object[] r3 = new java.lang.Object[r3]
            int r4 = r7.d
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r2] = r4
            java.lang.String r4 = "mConnState=0x%04X"
            java.lang.String r3 = java.lang.String.format(r4, r3)
            com.realsil.sdk.core.logger.ZLogger.v(r0, r3)
            goto L15
        L34:
            android.bluetooth.BluetoothDevice r0 = r7.e
            int r0 = r0.getBondState()
            boolean r4 = r7.b
            java.lang.Object[] r5 = new java.lang.Object[r3]
            java.lang.Integer r6 = java.lang.Integer.valueOf(r0)
            r5[r2] = r6
            java.lang.String r6 = "bondState=0x%02X"
            java.lang.String r5 = java.lang.String.format(r6, r5)
            com.realsil.sdk.core.logger.ZLogger.v(r4, r5)
            r4 = 12
            if (r0 == r4) goto L52
            goto L15
        L52:
            com.realsil.sdk.core.bluetooth.BluetoothProfileManager r0 = com.realsil.sdk.core.bluetooth.BluetoothProfileManager.getInstance()
            android.bluetooth.BluetoothDevice r4 = r7.e
            int r0 = r0.getConnectionState(r3, r4)
            boolean r4 = r7.b
            java.lang.Object[] r5 = new java.lang.Object[r3]
            java.lang.Integer r6 = java.lang.Integer.valueOf(r0)
            r5[r2] = r6
            java.lang.String r6 = "hfpState=0x%02X"
            java.lang.String r5 = java.lang.String.format(r6, r5)
            com.realsil.sdk.core.logger.ZLogger.v(r4, r5)
            r4 = 2
            if (r0 != r4) goto L15
        L72:
            if (r3 == 0) goto Lad
            r7.f = r2
            java.lang.Object r0 = r7.n
            monitor-enter(r0)
            java.lang.Boolean r3 = java.lang.Boolean.FALSE     // Catch: java.lang.Throwable -> Laa
            r7.o = r3     // Catch: java.lang.Throwable -> Laa
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Laa
            r7.d = r2
            boolean r0 = r7.b
            java.lang.String r4 = "processAbnormalDisconnection .."
            com.realsil.sdk.core.logger.ZLogger.v(r0, r4)
            com.realsil.sdk.core.bluetooth.connection.legacy.SppConnParameters r0 = r7.g
            boolean r0 = r7.a(r0)
            if (r0 != 0) goto Lc4
            boolean r0 = r7.a
            java.lang.String r4 = "processAbnormalDisconnection failed"
            com.realsil.sdk.core.logger.ZLogger.v(r0, r4)
            r7.a(r2)
            r7.e = r1
            java.lang.Object r0 = r7.n
            monitor-enter(r0)
            r7.o = r3     // Catch: java.lang.Throwable -> La7
            monitor-exit(r0)     // Catch: java.lang.Throwable -> La7
            r7.start()
            goto Lc4
        La7:
            r7 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> La7
            throw r7
        Laa:
            r7 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Laa
            throw r7
        Lad:
            boolean r0 = r7.a
            java.lang.String r3 = "connectionFailed"
            com.realsil.sdk.core.logger.ZLogger.v(r0, r3)
            r7.a(r2)
            r7.e = r1
            java.lang.Object r0 = r7.n
            monitor-enter(r0)
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch: java.lang.Throwable -> Lc5
            r7.o = r1     // Catch: java.lang.Throwable -> Lc5
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lc5
            r7.start()
        Lc4:
            return
        Lc5:
            r7 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lc5
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSpp.b(com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSpp):void");
    }

    public synchronized void start() {
        start(true);
    }

    public static void a(BluetoothSpp bluetoothSpp) {
        ZLogger.v(bluetoothSpp.a, "connectionLost");
        bluetoothSpp.a(0);
        bluetoothSpp.e = null;
        synchronized (bluetoothSpp.n) {
            bluetoothSpp.o = Boolean.FALSE;
        }
        bluetoothSpp.start();
    }

    public final synchronized void a(int i) {
        if (i != this.d) {
            ZLogger.v(String.format(Locale.US, ">> ConnectionState=0x%04X > 0x%04X", Integer.valueOf(this.d), Integer.valueOf(i)));
        }
        this.d = i;
        BluetoothSppCallback bluetoothSppCallback = this.mCallback;
        if (bluetoothSppCallback != null) {
            bluetoothSppCallback.onConnectionStateChanged(this, true, this.d);
        } else {
            ZLogger.v(this.b, "no callback registered");
        }
    }

    public class a extends Thread {
        public final BluetoothServerSocket a;

        public a(boolean z) {
            this.a = a(z);
            BluetoothDevice bluetoothDevice = BluetoothSpp.this.e;
            BluetoothSpp.this.a(257);
        }

        public final BluetoothServerSocket a(boolean z) throws IOException {
            BluetoothServerSocket bluetoothServerSocketListenUsingInsecureRfcommWithServiceRecord;
            try {
                if (z) {
                    BluetoothSpp bluetoothSpp = BluetoothSpp.this;
                    bluetoothServerSocketListenUsingInsecureRfcommWithServiceRecord = bluetoothSpp.c.listenUsingRfcommWithServiceRecord("RtkSppSecure", bluetoothSpp.i);
                } else {
                    BluetoothSpp bluetoothSpp2 = BluetoothSpp.this;
                    bluetoothServerSocketListenUsingInsecureRfcommWithServiceRecord = bluetoothSpp2.c.listenUsingInsecureRfcommWithServiceRecord("RtkSppInsecure", bluetoothSpp2.i);
                }
                return bluetoothServerSocketListenUsingInsecureRfcommWithServiceRecord;
            } catch (IOException e) {
                StringBuilder sbA = com.realsil.sdk.core.a.a.a("createServerSocket failed: ");
                sbA.append(e.toString());
                ZLogger.d(sbA.toString());
                return null;
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() throws IOException {
            ZLogger.v(BluetoothSpp.this.a, "BEGIN mAcceptThread");
            setName("AcceptThread:BluetoothSpp");
            while (BluetoothSpp.this.d != 512) {
                try {
                    BluetoothSocket bluetoothSocketAccept = this.a.accept();
                    if (bluetoothSocketAccept != null) {
                        synchronized (BluetoothSpp.this) {
                            int i = BluetoothSpp.this.d;
                            if (i == 0 || i == 512) {
                                try {
                                    bluetoothSocketAccept.close();
                                } catch (IOException e) {
                                    ZLogger.w("Could not close unwanted socket： " + e);
                                }
                            } else if (i == 256 || i == 257) {
                                if (Build.VERSION.SDK_INT >= 23) {
                                    BluetoothSpp.this.m = bluetoothSocketAccept.getConnectionType();
                                }
                                BluetoothSpp.this.connected(bluetoothSocketAccept, bluetoothSocketAccept.getRemoteDevice());
                            }
                        }
                    }
                } catch (IOException e2) {
                    ZLogger.w("accept() failed" + e2);
                    BluetoothSpp bluetoothSpp = BluetoothSpp.this;
                    BluetoothDevice bluetoothDevice = bluetoothSpp.e;
                    bluetoothSpp.a(0);
                }
            }
            ZLogger.d(BluetoothSpp.this.a, "END AcceptThread");
        }

        public final void a() throws IOException {
            try {
                if (this.a != null) {
                    ZLogger.v(BluetoothSpp.this.a, "cancel AcceptThread");
                    this.a.close();
                }
            } catch (IOException e) {
                ZLogger.w("close() of server failed： " + e);
            }
        }
    }

    public class b extends Thread {
        public BluetoothSocket a;
        public final BluetoothDevice b;

        public b(BluetoothDevice bluetoothDevice) {
            this.b = bluetoothDevice;
            this.a = a(bluetoothDevice, true);
        }

        public final BluetoothSocket a(BluetoothDevice bluetoothDevice, boolean z) {
            BluetoothSocket bluetoothSocketCreateRfcommSocketToServiceRecord;
            boolean z2 = BluetoothSpp.this.b;
            StringBuilder sbA = com.realsil.sdk.core.a.a.a("mSecureUuid=");
            sbA.append(BluetoothSpp.this.i);
            ZLogger.v(z2, sbA.toString());
            try {
                bluetoothSocketCreateRfcommSocketToServiceRecord = z ? bluetoothDevice.createRfcommSocketToServiceRecord(BluetoothSpp.this.i) : bluetoothDevice.createInsecureRfcommSocketToServiceRecord(BluetoothSpp.this.i);
            } catch (IOException e) {
                StringBuilder sbA2 = com.realsil.sdk.core.a.a.a("createBluetoothSocket failed: ");
                sbA2.append(e.toString());
                ZLogger.w(sbA2.toString());
                bluetoothSocketCreateRfcommSocketToServiceRecord = null;
            }
            if (bluetoothSocketCreateRfcommSocketToServiceRecord != null && Build.VERSION.SDK_INT >= 23) {
                BluetoothSpp.this.m = bluetoothSocketCreateRfcommSocketToServiceRecord.getConnectionType();
            }
            return bluetoothSocketCreateRfcommSocketToServiceRecord;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() throws InterruptedException, IOException {
            BluetoothSpp bluetoothSpp;
            setName("ConnectThread:BluetoothSpp");
            if (BluetoothSpp.this.b) {
                StringBuilder sbA = com.realsil.sdk.core.a.a.a("SocketConnectionType: ");
                sbA.append(BluetoothSpp.this.m);
                ZLogger.v(sbA.toString());
            }
            BluetoothAdapter bluetoothAdapter = BluetoothSpp.this.c;
            if (bluetoothAdapter != null) {
                bluetoothAdapter.cancelDiscovery();
            }
            BluetoothSocket bluetoothSocket = this.a;
            if (bluetoothSocket == null) {
                ZLogger.w("create BluetoothSocket fail");
                BluetoothSpp bluetoothSpp2 = BluetoothSpp.this;
                BluetoothDevice bluetoothDevice = bluetoothSpp2.e;
                bluetoothSpp2.a(0);
                synchronized (BluetoothSpp.this.n) {
                    BluetoothSpp.this.o = Boolean.FALSE;
                }
                return;
            }
            if (bluetoothSocket.isConnected()) {
                ZLogger.d(BluetoothSpp.this.a, "socket already connected");
            } else {
                if (BluetoothSpp.c(BluetoothSpp.this)) {
                    ZLogger.i("is already in connecting, ignore connect req, and wait connect result");
                    return;
                }
                BluetoothSpp bluetoothSpp3 = BluetoothSpp.this;
                BluetoothDevice bluetoothDevice2 = bluetoothSpp3.e;
                bluetoothSpp3.a(256);
                ZLogger.v(BluetoothSpp.this.b, "connect socket ...");
                try {
                    this.a.connect();
                } catch (Exception e) {
                    StringBuilder sbA2 = com.realsil.sdk.core.a.a.a("connect socket failed, ");
                    sbA2.append(e.toString());
                    ZLogger.w(sbA2.toString());
                    try {
                        this.a.close();
                    } catch (IOException e2) {
                        ZLogger.w("unable to close socket during connection failure: " + e2);
                    }
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e3) {
                        ZLogger.v(BluetoothSpp.this.b, e3.toString());
                    }
                    if (!"Connect refused".equals(e.getMessage())) {
                        BluetoothSpp.b(BluetoothSpp.this);
                        return;
                    }
                    if (this.b.getBondState() == 12) {
                        this.a = a(this.b, false);
                    }
                    BluetoothSocket bluetoothSocket2 = this.a;
                    if (bluetoothSocket2 == null) {
                        ZLogger.d("create Insecure BluetoothSocket fail");
                        BluetoothSpp bluetoothSpp4 = BluetoothSpp.this;
                        BluetoothDevice bluetoothDevice3 = bluetoothSpp4.e;
                        bluetoothSpp4.a(0);
                        synchronized (BluetoothSpp.this.n) {
                            BluetoothSpp.this.o = Boolean.FALSE;
                            return;
                        }
                    }
                    if (bluetoothSocket2.isConnected()) {
                        ZLogger.d("socket already connected");
                        return;
                    }
                    BluetoothSpp bluetoothSpp5 = BluetoothSpp.this;
                    BluetoothDevice bluetoothDevice4 = bluetoothSpp5.e;
                    bluetoothSpp5.a(256);
                    ZLogger.v(BluetoothSpp.this.b, "refused, connect socket ...");
                    try {
                        this.a.connect();
                        return;
                    } catch (Exception e4) {
                        StringBuilder sbA3 = com.realsil.sdk.core.a.a.a("connect socket failed, ");
                        sbA3.append(e4.toString());
                        ZLogger.d(sbA3.toString());
                        try {
                            this.a.close();
                        } catch (IOException e5) {
                            ZLogger.w("unable to close socket during connection failure: " + e5);
                        }
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e6) {
                            ZLogger.v(BluetoothSpp.this.b, e6.toString());
                        }
                        BluetoothSpp.b(BluetoothSpp.this);
                        return;
                    }
                }
            }
            synchronized (BluetoothSpp.this) {
                bluetoothSpp = BluetoothSpp.this;
                bluetoothSpp.j = null;
            }
            bluetoothSpp.connected(this.a, this.b);
        }

        public final void a() throws IOException {
            try {
                BluetoothSocket bluetoothSocket = this.a;
                if (bluetoothSocket != null) {
                    bluetoothSocket.close();
                }
            } catch (IOException e) {
                ZLogger.w("close socket failed: " + e);
            }
        }
    }
}
