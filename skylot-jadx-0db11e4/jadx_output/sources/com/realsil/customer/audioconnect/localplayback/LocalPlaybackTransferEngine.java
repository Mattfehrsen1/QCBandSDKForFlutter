package com.realsil.customer.audioconnect.localplayback;

import com.realsil.customer.core.logger.ZLogger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: qc_sdk_20250409.aar:libs/rtk-audioconnect-localplayback-1.0.9.jar:com/realsil/customer/audioconnect/localplayback/LocalPlaybackTransferEngine.class */
public class LocalPlaybackTransferEngine {
    public static volatile LocalPlaybackTransferEngine k;
    public static final int MAX_SEQUENCE_NUMBER = 65535;
    public LocalPlaybackTransferEngineCallback a;
    public a b;
    public int d;
    public int e;
    public LinkedBlockingQueue<Integer> f;
    public AtomicInteger g;
    public AtomicInteger h;
    public long i;
    public volatile boolean c = false;
    public byte j = 0;

    /* loaded from: qc_sdk_20250409.aar:libs/rtk-audioconnect-localplayback-1.0.9.jar:com/realsil/customer/audioconnect/localplayback/LocalPlaybackTransferEngine$a.class */
    public class a extends Thread {
        public final File a;

        public a(File file) {
            this.a = file;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v19 */
        /* JADX WARN: Type inference failed for: r0v20, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v24, types: [java.lang.String] */
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws IOException {
            super.run();
            try {
                FileInputStream fileInputStream = new FileInputStream(this.a);
                ZLogger.d("transfer thread has running, file name: " + this.a.getName() + ", sendPktSize: " + LocalPlaybackTransferEngine.this.d);
                byte[] bArr = new byte[LocalPlaybackTransferEngine.this.d];
                while (LocalPlaybackTransferEngine.this.c) {
                    try {
                        ZLogger.d("current put buffer type: " + ((Integer) LocalPlaybackTransferEngine.this.f.take()));
                        int i = 0;
                        while (true) {
                            ?? r0 = i;
                            if (r0 < LocalPlaybackTransferEngine.this.e) {
                                try {
                                    int i2 = fileInputStream.read(bArr);
                                    ZLogger.d("read len: " + i2);
                                    if (i2 == -1) {
                                        r0 = "read the end of file, will exit the transfer thread ...";
                                        ZLogger.w("read the end of file, will exit the transfer thread ...");
                                        return;
                                    }
                                    if (LocalPlaybackTransferEngine.this.g.get() > 65535) {
                                        LocalPlaybackTransferEngine.this.g.set(0);
                                        ZLogger.w("sequence number more than the max number(65535), reset to 0");
                                    }
                                    int andIncrement = LocalPlaybackTransferEngine.this.g.getAndIncrement();
                                    int andAdd = LocalPlaybackTransferEngine.this.h.getAndAdd(i2);
                                    short sB = LocalPlaybackTransferEngine.b(bArr, i2 % 2 == 0 ? i2 : i2 - 1);
                                    ZLogger.d("read completed, prepare to send, seq: " + andIncrement + ", Transferred: " + andAdd + ", sendSize: " + i2);
                                    LocalPlaybackModelClient.getInstance().transfer((short) andIncrement, sB, andAdd, (short) i2, bArr);
                                    i++;
                                } catch (IOException unused) {
                                    r0.printStackTrace();
                                    ZLogger.e("read song file data failed, catch a io exception");
                                    return;
                                }
                            }
                        }
                    } catch (InterruptedException unused2) {
                        ZLogger.w("receive a interrupt signal, will exit the transfer thread ... ");
                        return;
                    }
                }
                ZLogger.w("exit transfer thread");
            } catch (FileNotFoundException unused3) {
                ZLogger.e("send file data failed, transfer file can not found");
            }
        }

        public void a() {
            LocalPlaybackTransferEngine.this.c = false;
            interrupt();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.realsil.customer.audioconnect.localplayback.LocalPlaybackTransferEngine>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static LocalPlaybackTransferEngine getInstance() {
        if (k == null) {
            ?? r0 = LocalPlaybackTransferEngine.class;
            synchronized (r0) {
                if (k == null) {
                    k = new LocalPlaybackTransferEngine();
                }
                r0 = r0;
            }
        }
        return k;
    }

    public void setTransferCallback(LocalPlaybackTransferEngineCallback localPlaybackTransferEngineCallback) {
        this.a = localPlaybackTransferEngineCallback;
    }

    public synchronized void init(int i, int i2) {
        if (i <= 0) {
            ZLogger.e("parameter error, sent packet size is invalid");
            return;
        }
        if (i2 <= 0) {
            ZLogger.e("parameter error, buffer check size is invalid");
            return;
        }
        this.d = i;
        this.e = i2 / i;
        if (this.f == null) {
            this.f = new LinkedBlockingQueue<>();
        }
        this.g = new AtomicInteger(0);
        this.h = new AtomicInteger(0);
    }

    public synchronized void startTransfer(File file) throws InterruptedException {
        if (file == null) {
            ZLogger.e("input file can not be null");
            return;
        }
        if (!file.exists()) {
            ZLogger.e("input file not exists");
            return;
        }
        if (file.length() == 0) {
            ZLogger.e("input file content cannot be empty");
        } else if (this.f == null) {
            ZLogger.e("start failed, please call init() at first.");
        } else {
            a(file);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [int] */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.util.concurrent.LinkedBlockingQueue, java.util.concurrent.LinkedBlockingQueue<java.lang.Integer>] */
    public void updateTransferState(int i) throws InterruptedException {
        if (this.f == null) {
            ZLogger.e("start failed, please call init() at first");
            return;
        }
        if (this.b == null) {
            ZLogger.e("update file, transfer has not yet started");
            return;
        }
        if (i == 241) {
            ZLogger.w("onTransferSuccess: >>>>> Buffer A can use");
        } else {
            if (i != 242) {
                ZLogger.e("invalid buffer flag!");
                return;
            }
            ZLogger.w("onTransferSuccess: >>>>> Buffer B can use");
        }
        if (this.a != null) {
            this.a.onTransferProgressChanged((int) (new BigDecimal(this.h.get()).divide(new BigDecimal(this.i), 2, 1).doubleValue() * 100.0d));
        }
        ZLogger.d("(transfer/total): [" + this.h + "/" + this.i + "]");
        ?? r0 = (this.i > this.h.get() ? 1 : (this.i == this.h.get() ? 0 : -1));
        if (r0 != 0) {
            try {
                r0 = this.f;
                r0.put(Integer.valueOf(i));
                return;
            } catch (InterruptedException unused) {
                r0.printStackTrace();
                return;
            }
        }
        if (i == 241) {
            this.j = (byte) (this.j | 1);
        } else {
            this.j = (byte) (this.j | 2);
        }
        ZLogger.d("check transmission state: " + ((int) this.j));
        if (this.j == 3) {
            ZLogger.w("The transmission is complete, valid song...");
            LocalPlaybackModelClient.getInstance().validTransfer((int) this.i);
        }
    }

    public synchronized void destroy() {
        a();
        b();
        this.f = null;
        this.a = null;
        this.d = 0;
        this.e = 0;
    }

    public final void b() {
        a aVar = this.b;
        if (aVar != null) {
            aVar.a();
            this.b = null;
            ZLogger.d("stop last transfer thread");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.realsil.customer.audioconnect.localplayback.LocalPlaybackTransferEngine] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.concurrent.LinkedBlockingQueue, java.util.concurrent.LinkedBlockingQueue<java.lang.Integer>] */
    public final void a(File file) throws InterruptedException {
        ?? r0 = this;
        b();
        a();
        r0.i = file.length();
        try {
            r0.f.put(Integer.valueOf(LocalPlaybackConstants.BUFFER_A_CAN_USE_FLAG));
            r0 = r0.f;
            r0.put(Integer.valueOf(LocalPlaybackConstants.BUFFER_B_CAN_USE_FLAG));
        } catch (InterruptedException unused) {
            r0.printStackTrace();
        }
        this.c = true;
        a aVar = new a(file);
        this.b = aVar;
        aVar.start();
    }

    public static short b(byte[] bArr, int i) {
        short s = 0;
        for (int i2 = 0; i2 < i; i2 += 2) {
            s = (short) (s ^ ((short) ((bArr[i2 + 1] << 8) | (bArr[i2] & 255))));
        }
        return (short) (((s & 255) << 8) | ((s & 65280) >> 8));
    }

    public final void a() {
        LinkedBlockingQueue<Integer> linkedBlockingQueue = this.f;
        if (linkedBlockingQueue != null) {
            linkedBlockingQueue.clear();
        }
        this.g.set(0);
        this.h.set(0);
        this.i = 0L;
        this.j = (byte) 0;
    }
}
