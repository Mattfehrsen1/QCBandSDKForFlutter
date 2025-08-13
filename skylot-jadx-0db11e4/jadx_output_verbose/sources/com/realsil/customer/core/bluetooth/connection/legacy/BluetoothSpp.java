package com.realsil.customer.core.bluetooth.connection.legacy;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Build;
import com.realsil.customer.bbpro.equalizer.EqConstants;
import com.realsil.customer.core.bluetooth.connection.BluetoothClient;
import com.realsil.customer.core.logger.ZLogger;
import com.realsil.customer.core.utility.DataConverter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/connection/legacy/BluetoothSpp.class */
public final class BluetoothSpp extends BluetoothClient {
    public static final UUID l = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public static final int ROLE_CLIENT = 1;
    public static final int ROLE_SERVER = 2;
    public boolean b;
    public SppConnParameters c;
    public boolean initialized;
    public final int d;
    public UUID e;
    public b f;
    public c g;
    public a h;
    public int i;
    public final Object j;
    public Boolean k;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/connection/legacy/BluetoothSpp$c.class */
    public class c extends Thread {
        public final BluetoothSocket a;
        public final BufferedInputStream b;
        public final BufferedOutputStream c;

        public c(BluetoothSocket bluetoothSocket) {
            BufferedInputStream bufferedInputStream;
            IOException e;
            BufferedInputStream bufferedInputStream2;
            this.b = null;
            this.c = null;
            ZLogger.d("create ConnectedThread");
            this.a = bluetoothSocket;
            BufferedOutputStream bufferedOutputStream = null;
            try {
                bufferedInputStream = bufferedInputStream2;
                bufferedInputStream2 = new BufferedInputStream(bluetoothSocket.getInputStream());
            } catch (IOException e2) {
                bufferedInputStream = null;
                e = e2;
            }
            try {
                bufferedOutputStream = new BufferedOutputStream(bluetoothSocket.getOutputStream());
            } catch (IOException e3) {
                e = e3;
                ZLogger.w("temp sockets not created: " + e);
                this.b = bufferedInputStream;
                this.c = bufferedOutputStream;
            }
            this.b = bufferedInputStream;
            this.c = bufferedOutputStream;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r11v0 */
        /* JADX WARN: Type inference failed for: r11v1 */
        /* JADX WARN: Type inference failed for: r11v2 */
        /* JADX WARN: Type inference failed for: r11v3, types: [int] */
        /* JADX WARN: Type inference failed for: r11v4 */
        /* JADX WARN: Type inference failed for: r11v5 */
        /* JADX WARN: Type inference failed for: r11v7 */
        /* JADX WARN: Type inference failed for: r11v8 */
        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() throws IOException {
            byte[] bArr = new byte[EqConstants.CodeIndex.REALTIME_EQ_2];
            BluetoothSpp.this.updateConnectionState(2);
            Object obj = BluetoothSpp.this.j;
            int i = obj;
            synchronized (i) {
                try {
                    BluetoothSpp.this.k = Boolean.FALSE;
                    while (!Thread.currentThread().isInterrupted() && BluetoothSpp.this.a == 2) {
                        try {
                            int i2 = this.b.read(bArr);
                            i = i2;
                            if (i2 > 0) {
                                byte[] bArr2 = new byte[i];
                                System.arraycopy(bArr, 0, bArr2, 0, i);
                                i = i;
                                if (((BluetoothClient) BluetoothSpp.this).DBG) {
                                    Locale locale = Locale.US;
                                    Object[] objArr = new Object[2];
                                    objArr[0] = Integer.valueOf((int) i);
                                    i = 1;
                                    objArr[1] = DataConverter.bytes2Hex(bArr2);
                                    ZLogger.d(String.format(locale, ">> (%d) %s", objArr));
                                }
                                BluetoothSpp.this.dispatchDataReceived(bArr2);
                            }
                        } catch (IOException e) {
                            ZLogger.w("connectionLost: " + e.toString());
                            BluetoothSpp.m(BluetoothSpp.this);
                        }
                    }
                    if (BluetoothSpp.this.a == 3) {
                        a();
                        BluetoothSpp.m(BluetoothSpp.this);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
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
    }

    public synchronized void start(boolean z) throws IOException {
        ZLogger.v(this.DBG, "start secure: " + z);
        b bVar = this.f;
        if (bVar != null) {
            bVar.a();
            this.f.interrupt();
            this.f = null;
        }
        c cVar = this.g;
        if (cVar != null) {
            cVar.a();
            this.g.interrupt();
            this.g = null;
        }
        if ((this.d & 2) == 2 && this.h == null) {
            a aVar = new a(z);
            this.h = aVar;
            aVar.start();
        }
    }

    public synchronized boolean connect(SppConnParameters sppConnParameters) throws IOException {
        if (sppConnParameters == null) {
            ZLogger.v("connParameters can not be null or empty");
            return false;
        }
        if (sppConnParameters.getBluetoothDevice() == null) {
            ZLogger.v("device can not be null or empty");
            return false;
        }
        BluetoothDevice bluetoothDevice = this.mDevice;
        if (bluetoothDevice != null) {
            if (bluetoothDevice.equals(sppConnParameters.getBluetoothDevice())) {
                int i = this.a;
                if (i == 2) {
                    ZLogger.v(this.DBG, "device already connected");
                    updateConnectionState(2);
                    return true;
                }
                if (i == 1) {
                    ZLogger.v(this.DBG, "device is already at connecting state");
                    updateConnectionState(1);
                    return true;
                }
            } else {
                int i2 = this.a;
                if (i2 == 2) {
                    ZLogger.v(this.DBG, "other device already connected");
                    b();
                    return false;
                }
                if (i2 == 1) {
                    ZLogger.v(this.DBG, "other device is at connecting state");
                    b();
                    return false;
                }
            }
        }
        this.b = true;
        return a(sppConnParameters);
    }

    public synchronized void connected(BluetoothSocket bluetoothSocket, BluetoothDevice bluetoothDevice) throws IOException {
        ZLogger.d(this.DBG, "spp connected");
        this.mDevice = bluetoothDevice;
        b();
        a aVar = this.h;
        if (aVar != null) {
            aVar.a();
            this.h = null;
        }
        c cVar = new c(bluetoothSocket);
        this.g = cVar;
        cVar.start();
    }

    public boolean write(byte[] bArr) {
        return write(bArr, true);
    }

    @Override // com.realsil.customer.core.bluetooth.connection.BluetoothClient
    public synchronized void destroy() throws IOException {
        super.destroy();
        stop();
    }

    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Object, java.lang.Throwable] */
    public synchronized void stop() throws IOException {
        ZLogger.v(this.VDBG, "stop");
        if (this.a == 2) {
            updateConnectionState(3);
        }
        this.mDevice = null;
        b bVar = this.f;
        if (bVar != null) {
            bVar.a();
            this.f.interrupt();
            this.f = null;
        }
        c cVar = this.g;
        if (cVar != null) {
            cVar.a();
            this.g.interrupt();
            this.g = null;
        }
        a aVar = this.h;
        if (aVar != null) {
            aVar.a();
            this.h.interrupt();
            this.h = null;
        }
        synchronized (this.j) {
            this.k = Boolean.FALSE;
        }
    }

    @Override // com.realsil.customer.core.bluetooth.connection.BluetoothClient
    public boolean isConnected() {
        return this.mDevice != null && this.a == 2;
    }

    public BluetoothSpp(BluetoothSppCallback bluetoothSppCallback) {
        this(1, l, bluetoothSppCallback);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void d(com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp r7) {
        /*
            Method dump skipped, instructions count: 222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp.d(com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp):void");
    }

    public static boolean i(BluetoothSpp bluetoothSpp) {
        return bluetoothSpp.a == 1;
    }

    public static void m(BluetoothSpp bluetoothSpp) {
        ZLogger.v(bluetoothSpp.DBG, "connectionLost");
        bluetoothSpp.updateConnectionState(0);
        bluetoothSpp.mDevice = null;
        synchronized (bluetoothSpp.j) {
            bluetoothSpp.k = Boolean.FALSE;
        }
        bluetoothSpp.start();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean write(byte[] bArr, boolean z) throws IOException {
        boolean z2;
        synchronized (this) {
            if (this.a != 2) {
                ZLogger.d(this.DBG, "not connected");
                return false;
            }
            c cVar = this.g;
            if (cVar == null) {
                ZLogger.d("ConnectedThread not created");
                return false;
            }
            if (cVar.c == null) {
                z2 = false;
            } else {
                try {
                    if (BluetoothSpp.this.DBG) {
                        Locale locale = Locale.US;
                        Object[] objArr = new Object[2];
                        objArr[0] = Integer.valueOf(bArr.length);
                        objArr[1] = DataConverter.bytes2Hex(bArr);
                        ZLogger.d(String.format(locale, "<< (%d) %s", objArr));
                    }
                    cVar.c.write(bArr);
                    if (z) {
                        cVar.c.flush();
                    }
                    z2 = true;
                } catch (IOException e) {
                    ZLogger.w("Exception during write： " + e);
                    z2 = false;
                }
            }
            return z2;
        }
    }

    public final void c() {
        ZLogger.v(this.DBG, "initialize...");
        a();
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter == null) {
            ZLogger.d("bluetoothAdapter not initialized ");
            this.initialized = false;
        } else if (bluetoothAdapter.isEnabled()) {
            this.initialized = true;
        } else {
            ZLogger.d("bluetooth is disabled");
            this.initialized = false;
        }
    }

    public final void b() throws IOException {
        ZLogger.v(this.DBG, "cancelPreviousConnection");
        b bVar = this.f;
        if (bVar != null) {
            bVar.a();
            this.f.interrupt();
            this.f = null;
        }
        c cVar = this.g;
        if (cVar != null) {
            cVar.a();
            this.g.interrupt();
            this.g = null;
        }
    }

    public final boolean a(SppConnParameters sppConnParameters) throws IOException {
        synchronized (this.j) {
            if (this.k.booleanValue()) {
                ZLogger.w("device is busy");
                return false;
            }
            this.k = Boolean.TRUE;
            if (!this.initialized) {
                c();
            }
            ZLogger.v(this.DBG, "createNewConnection:" + sppConnParameters.toString());
            this.c = sppConnParameters;
            this.mDevice = sppConnParameters.getBluetoothDevice();
            this.e = sppConnParameters.getUuid();
            if (sppConnParameters.getBluetoothSocket() != null) {
                connected(sppConnParameters.getBluetoothSocket(), sppConnParameters.getBluetoothDevice());
                return true;
            }
            b();
            b bVar = new b(this.mDevice);
            this.f = bVar;
            bVar.start();
            return true;
        }
    }

    public BluetoothSpp(UUID uuid, BluetoothSppCallback bluetoothSppCallback) {
        this(1, uuid, bluetoothSppCallback);
    }

    public BluetoothSpp(int i, UUID uuid, BluetoothSppCallback bluetoothSppCallback) {
        this.b = false;
        this.d = 1;
        this.i = -1;
        this.j = new Object();
        this.k = Boolean.FALSE;
        this.d = i;
        this.e = uuid;
        this.mCallback = bluetoothSppCallback;
        c();
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/connection/legacy/BluetoothSpp$a.class */
    public class a extends Thread {
        public final BluetoothServerSocket a;

        public a(boolean z) {
            this.a = a(z);
            BluetoothSpp.this.updateConnectionState(4);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0054 A[Catch: all -> 0x0095, TRY_ENTER, TRY_LEAVE, TryCatch #2 {, blocks: (B:10:0x0031, B:12:0x0036, B:31:0x0091, B:22:0x0054, B:24:0x005c, B:25:0x0064, B:27:0x0074, B:28:0x007a, B:29:0x007d), top: B:45:0x0031, inners: #0 }] */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0073  */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void run() throws java.io.IOException {
            /*
                r6 = this;
                r0 = r6
                r1 = r0
                com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp r1 = com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp.this
                boolean r1 = com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp.a(r1)
                java.lang.String r2 = "BEGIN mAcceptThread"
                com.realsil.customer.core.logger.ZLogger.v(r1, r2)
                java.lang.String r1 = "AcceptThread:BluetoothSpp"
                r0.setName(r1)
            L12:
                r0 = r6
                com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp r0 = com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp.this
                int r0 = r0.a
                r1 = 2
                if (r0 == r1) goto Lb7
                r0 = r6
                android.bluetooth.BluetoothServerSocket r0 = r0.a     // Catch: java.io.IOException -> L9a
                android.bluetooth.BluetoothSocket r0 = r0.accept()     // Catch: java.io.IOException -> L9a
                r1 = r0
                r7 = r1
                if (r0 == 0) goto L12
                r0 = r6
                r1 = r0
                com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp r1 = com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp.this
                r2 = r1
                r8 = r2
                monitor-enter(r1)
                com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp r0 = com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp.this     // Catch: java.lang.Throwable -> L95
                r1 = r0
                r9 = r1
                int r0 = r0.a     // Catch: java.lang.Throwable -> L95
                r1 = r0
                r10 = r1
                if (r0 == 0) goto L73
                r0 = r10
                r1 = 1
                if (r0 == r1) goto L54
                r0 = r10
                r1 = 2
                if (r0 == r1) goto L73
                r0 = r10
                r1 = 4
                if (r0 == r1) goto L54
                goto L90
            L54:
                int r0 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L95
                r1 = 23
                if (r0 < r1) goto L64
                r0 = r9
                r1 = r7
                int r1 = r1.getConnectionType()     // Catch: java.lang.Throwable -> L95
                r0.i = r1     // Catch: java.lang.Throwable -> L95
            L64:
                r0 = r6
                com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp r0 = com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp.this     // Catch: java.lang.Throwable -> L95
                r1 = r7
                r2 = r1
                android.bluetooth.BluetoothDevice r2 = r2.getRemoteDevice()     // Catch: java.lang.Throwable -> L95
                r0.connected(r1, r2)     // Catch: java.lang.Throwable -> L95
                goto L90
            L73:
                r0 = r7
                r0.close()     // Catch: java.io.IOException -> L7a java.lang.Throwable -> L95
                goto L90
            L7a:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L7a java.lang.Throwable -> L95 java.lang.Throwable -> L95
                r2 = r1
                r2.<init>()     // Catch: java.lang.Throwable -> L95
                java.lang.String r2 = "Could not close unwanted socket： "
                java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L95
                r2 = r0; r0 = r1; r1 = r2;      // Catch: java.lang.Throwable -> L95
                java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Throwable -> L95
                java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L95
                com.realsil.customer.core.logger.ZLogger.w(r0)     // Catch: java.lang.Throwable -> L95
            L90:
                r0 = r8
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L95
                goto L12
            L95:
                r6 = move-exception
                r0 = r6
                r1 = r8
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L95
                throw r0     // Catch: java.lang.Throwable -> L95
            L9a:
                r7 = move-exception
                r0 = r6
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r2 = r1
                r3 = r7
                r4 = r2; r2 = r3; r3 = r4; 
                java.lang.String r4 = "accept() failed"
                r3.<init>(r4)
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.String r1 = r1.toString()
                com.realsil.customer.core.logger.ZLogger.w(r1)
                com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp r0 = com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp.this
                r1 = 0
                r0.updateConnectionState(r1)
            Lb7:
                r0 = r6
                com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp r0 = com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp.this
                boolean r0 = com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp.o(r0)
                java.lang.String r1 = "END AcceptThread"
                com.realsil.customer.core.logger.ZLogger.d(r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp.a.run():void");
        }

        public final BluetoothServerSocket a(boolean z) {
            BluetoothServerSocket bluetoothServerSocketListenUsingRfcommWithServiceRecord = null;
            try {
                bluetoothServerSocketListenUsingRfcommWithServiceRecord = z ? ((BluetoothClient) BluetoothSpp.this).mBluetoothAdapter.listenUsingRfcommWithServiceRecord("RtkSppSecure", BluetoothSpp.this.e) : ((BluetoothClient) BluetoothSpp.this).mBluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord("RtkSppInsecure", BluetoothSpp.this.e);
            } catch (IOException e) {
                ZLogger.d("createServerSocket failed: " + e.toString());
            }
            return bluetoothServerSocketListenUsingRfcommWithServiceRecord;
        }

        public final void a() throws IOException {
            try {
                if (this.a != null) {
                    ZLogger.v(((BluetoothClient) BluetoothSpp.this).DBG, "cancel AcceptThread");
                    this.a.close();
                }
            } catch (IOException e) {
                ZLogger.w("close() of server failed： " + e);
            }
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/connection/legacy/BluetoothSpp$b.class */
    public class b extends Thread {
        public BluetoothSocket a;
        public final BluetoothDevice b;

        public b(BluetoothDevice bluetoothDevice) {
            this.b = bluetoothDevice;
            this.a = a(bluetoothDevice, true);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v18 */
        /* JADX WARN: Type inference failed for: r0v19 */
        /* JADX WARN: Type inference failed for: r0v28 */
        /* JADX WARN: Type inference failed for: r0v49 */
        /* JADX WARN: Type inference failed for: r0v50 */
        /* JADX WARN: Type inference failed for: r1v10, types: [com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp$b, java.lang.InterruptedException] */
        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() throws InterruptedException, IOException {
            Object obj;
            BluetoothSpp bluetoothSpp;
            setName("ConnectThread:BluetoothSpp");
            if (((BluetoothClient) BluetoothSpp.this).VDBG) {
                ZLogger.v("SocketConnectionType: " + BluetoothSpp.this.i);
            }
            if (((BluetoothClient) BluetoothSpp.this).mBluetoothAdapter != null) {
                ((BluetoothClient) BluetoothSpp.this).mBluetoothAdapter.cancelDiscovery();
            }
            BluetoothSocket bluetoothSocket = this.a;
            if (bluetoothSocket == null) {
                ZLogger.w("create BluetoothSocket fail");
                BluetoothSpp.this.updateConnectionState(0);
                synchronized (BluetoothSpp.this.j) {
                    BluetoothSpp.this.k = Boolean.FALSE;
                }
                return;
            }
            if (bluetoothSocket.isConnected()) {
                ZLogger.d(((BluetoothClient) BluetoothSpp.this).DBG, "socket already connected");
            } else {
                if (BluetoothSpp.i(BluetoothSpp.this)) {
                    ZLogger.i("is already in connecting, ignore connect req, and wait connect result");
                    return;
                }
                BluetoothSpp.this.updateConnectionState(1);
                ZLogger.v(((BluetoothClient) BluetoothSpp.this).VDBG, "connect socket ...");
                try {
                    this.a.connect();
                } catch (Exception e) {
                    ZLogger.w("connect socket failed, " + e.toString());
                    try {
                        BluetoothSocket bluetoothSocket2 = this.a;
                        bluetoothSocket2.close();
                        obj = bluetoothSocket2;
                    } catch (IOException e2) {
                        String str = "unable to close socket during connection failure: " + e2;
                        ZLogger.w(str);
                        obj = str;
                    }
                    try {
                        obj = 1000;
                        Thread.sleep(1000L);
                    } catch (InterruptedException e3) {
                        ZLogger.v(((BluetoothClient) BluetoothSpp.this).VDBG, obj.toString());
                    }
                    if ("Connect refused".equals(e.getMessage())) {
                        b();
                        return;
                    } else {
                        BluetoothSpp.d(BluetoothSpp.this);
                        return;
                    }
                }
            }
            synchronized (BluetoothSpp.this) {
                bluetoothSpp = BluetoothSpp.this;
                bluetoothSpp.f = null;
            }
            bluetoothSpp.connected(this.a, this.b);
        }

        public final BluetoothSocket a(BluetoothDevice bluetoothDevice, boolean z) {
            BluetoothSocket bluetoothSocketCreateRfcommSocketToServiceRecord = null;
            ZLogger.v(((BluetoothClient) BluetoothSpp.this).VDBG, "mSecureUuid=" + BluetoothSpp.this.e);
            try {
                bluetoothSocketCreateRfcommSocketToServiceRecord = z ? bluetoothDevice.createRfcommSocketToServiceRecord(BluetoothSpp.this.e) : bluetoothDevice.createInsecureRfcommSocketToServiceRecord(BluetoothSpp.this.e);
            } catch (IOException e) {
                ZLogger.w("createBluetoothSocket failed: " + e.toString());
            }
            if (bluetoothSocketCreateRfcommSocketToServiceRecord != null && Build.VERSION.SDK_INT >= 23) {
                BluetoothSpp.this.i = bluetoothSocketCreateRfcommSocketToServiceRecord.getConnectionType();
            }
            return bluetoothSocketCreateRfcommSocketToServiceRecord;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v12 */
        /* JADX WARN: Type inference failed for: r0v13 */
        /* JADX WARN: Type inference failed for: r0v17 */
        /* JADX WARN: Type inference failed for: r0v24 */
        /* JADX WARN: Type inference failed for: r0v25 */
        /* JADX WARN: Type inference failed for: r1v10, types: [com.realsil.customer.core.bluetooth.connection.legacy.BluetoothSpp$b, java.lang.InterruptedException] */
        public final void b() throws InterruptedException, IOException {
            Object obj;
            if (this.b.getBondState() == 12) {
                this.a = a(this.b, false);
            }
            BluetoothSocket bluetoothSocket = this.a;
            if (bluetoothSocket == null) {
                ZLogger.d("create Insecure BluetoothSocket fail");
                BluetoothSpp.this.updateConnectionState(0);
                synchronized (BluetoothSpp.this.j) {
                    BluetoothSpp.this.k = Boolean.FALSE;
                }
                return;
            }
            if (bluetoothSocket.isConnected()) {
                ZLogger.d("socket already connected");
                return;
            }
            BluetoothSpp.this.updateConnectionState(1);
            ZLogger.v(((BluetoothClient) BluetoothSpp.this).VDBG, "refused, connect socket ...");
            try {
                this.a.connect();
            } catch (Exception e) {
                ZLogger.d("connect socket failed, " + e.toString());
                try {
                    BluetoothSocket bluetoothSocket2 = this.a;
                    bluetoothSocket2.close();
                    obj = bluetoothSocket2;
                } catch (IOException e2) {
                    String str = "unable to close socket during connection failure: " + e2;
                    ZLogger.w(str);
                    obj = str;
                }
                try {
                    obj = 1000;
                    Thread.sleep(1000L);
                } catch (InterruptedException e3) {
                    ZLogger.v(((BluetoothClient) BluetoothSpp.this).VDBG, obj.toString());
                }
                BluetoothSpp.d(BluetoothSpp.this);
            }
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

    public synchronized void start() throws IOException {
        start(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void d() {
        Boolean bool;
        this.b = false;
        synchronized (this.j) {
            bool = Boolean.FALSE;
            this.k = bool;
        }
        this.a = 0;
        ZLogger.v(this.VDBG, "processAbnormalDisconnection ..");
        if (a(this.c)) {
            return;
        }
        ZLogger.v(this.DBG, "processAbnormalDisconnection failed");
        updateConnectionState(0);
        this.mDevice = null;
        synchronized (this.j) {
            this.k = bool;
        }
        start();
    }
}
