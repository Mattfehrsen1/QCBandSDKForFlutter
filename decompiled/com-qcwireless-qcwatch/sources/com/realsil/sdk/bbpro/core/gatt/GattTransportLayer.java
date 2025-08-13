package com.realsil.sdk.bbpro.core.gatt;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import com.realsil.sdk.bbpro.core.transportlayer.AckPacket;
import com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback;
import com.realsil.sdk.bbpro.core.transportlayer.TransportLayerPacket;
import com.realsil.sdk.core.base.BaseThread;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.util.Locale;

/* loaded from: classes3.dex */
public class GattTransportLayer {
    public TransportLayerCallback b;
    public volatile int c;
    public volatile int d;
    public volatile boolean e;
    public int g;
    public ThreadTx h;
    public ThreadRx i;
    public GattLayer a = null;
    public final Object f = new Object();
    public ChannelCallback j = new ChannelCallback() { // from class: com.realsil.sdk.bbpro.core.gatt.GattTransportLayer.1
        @Override // com.realsil.sdk.bbpro.core.gatt.ChannelCallback
        public void onConnectionStateChange(BluetoothDevice bluetoothDevice, boolean z, int i) {
            super.onConnectionStateChange(bluetoothDevice, z, i);
            if (!z || i == 1) {
                GattTransportLayer gattTransportLayer = GattTransportLayer.this;
                gattTransportLayer.getClass();
                ZLogger.v("closePassive");
                gattTransportLayer.b();
                gattTransportLayer.c();
            } else {
                ZLogger.v("status: " + z + ", newState: " + i);
            }
            TransportLayerCallback transportLayerCallback = GattTransportLayer.this.b;
            if (transportLayerCallback != null) {
                transportLayerCallback.onConnectionStateChanged(bluetoothDevice, z, i);
            }
        }

        @Override // com.realsil.sdk.bbpro.core.gatt.ChannelCallback
        public void onDataReceive(byte[] bArr) {
            ThreadRx threadRx = GattTransportLayer.this.i;
            if (threadRx == null || bArr == null) {
                return;
            }
            threadRx.addQueue(bArr);
        }
    };

    public class ThreadRx extends BaseThread<byte[]> {
        public ThreadRx() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            TransportLayerCallback transportLayerCallback;
            ZLogger.d("RxThread is running");
            while (!Thread.currentThread().isInterrupted() && !isCanceled()) {
                byte[] bArrTake = take();
                if (bArrTake != null) {
                    GattTransportLayer gattTransportLayer = GattTransportLayer.this;
                    gattTransportLayer.getClass();
                    try {
                        int length = bArrTake.length;
                        int packetLength = 0;
                        do {
                            int i = length - packetLength;
                            if (i <= 0) {
                                break;
                            }
                            byte[] bArr = new byte[i];
                            System.arraycopy(bArrTake, packetLength, bArr, 0, i);
                            ZLogger.v(DataConverter.bytes2Hex(bArrTake));
                            TransportLayerPacket transportLayerPacketBuilderPacket = TransportLayerPacket.builderPacket(bArr);
                            if (transportLayerPacketBuilderPacket == null) {
                                break;
                            }
                            short opcode = transportLayerPacketBuilderPacket.getOpcode();
                            transportLayerPacketBuilderPacket.getPayload();
                            byte[] parameters = transportLayerPacketBuilderPacket.getParameters();
                            ZLogger.v(String.format(Locale.US, "[0x%04X >>] %s", Short.valueOf(opcode), DataConverter.bytes2HexWithSeparate(parameters)));
                            if (opcode != 0) {
                                gattTransportLayer.sendAck(opcode, (byte) 0);
                                if (transportLayerPacketBuilderPacket.getSeqNum() != gattTransportLayer.d) {
                                    gattTransportLayer.d = transportLayerPacketBuilderPacket.getSeqNum();
                                    TransportLayerCallback transportLayerCallback2 = gattTransportLayer.b;
                                    if (transportLayerCallback2 != null) {
                                        transportLayerCallback2.onDataReceive(transportLayerPacketBuilderPacket);
                                    }
                                }
                            } else if (transportLayerPacketBuilderPacket.getSeqNum() != gattTransportLayer.d) {
                                gattTransportLayer.d = transportLayerPacketBuilderPacket.getSeqNum();
                                synchronized (gattTransportLayer.f) {
                                    gattTransportLayer.e = true;
                                    gattTransportLayer.f.notifyAll();
                                }
                                AckPacket ackPacketBuilder = AckPacket.builder(parameters);
                                if (ackPacketBuilder != null && (transportLayerCallback = gattTransportLayer.b) != null) {
                                    transportLayerCallback.onAckReceive(ackPacketBuilder);
                                }
                            }
                            packetLength += transportLayerPacketBuilderPacket.getPacketLength();
                        } while (packetLength < length);
                    } catch (Exception e) {
                        ZLogger.e(e.toString());
                    }
                }
            }
            ZLogger.d("RxThread stopped");
        }
    }

    public class ThreadTx extends BaseThread<byte[]> {
        public ThreadTx() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            ZLogger.v("TxThread is running");
            while (!Thread.currentThread().isInterrupted() && !isCanceled()) {
                byte[] bArrTake = take();
                if (bArrTake != null) {
                    GattTransportLayer gattTransportLayer = GattTransportLayer.this;
                    gattTransportLayer.g = 0;
                    if (gattTransportLayer.a(bArrTake, false)) {
                        continue;
                    } else {
                        while (GattTransportLayer.this.g < 3) {
                            if (isCanceled()) {
                                return;
                            }
                            GattTransportLayer.this.g++;
                            ZLogger.v("<< Retrans " + GattTransportLayer.this.g + ", data: " + DataConverter.bytes2Hex(bArrTake));
                            if (GattTransportLayer.this.a(bArrTake, false)) {
                                break;
                            }
                            GattTransportLayer gattTransportLayer2 = GattTransportLayer.this;
                            if (gattTransportLayer2.g >= 3 && gattTransportLayer2.b != null) {
                                ZLogger.v(true, ">> ERR_TRANSPORT_RETRAINS_EXCEED_MAX_TIMES: 3");
                                GattTransportLayer.this.b.onError(64);
                            }
                        }
                    }
                }
            }
            ZLogger.d("TxThread stopped");
        }
    }

    public GattTransportLayer(TransportLayerCallback transportLayerCallback) {
        this.b = transportLayerCallback;
        a();
    }

    public final GattLayer a() {
        if (this.a == null) {
            this.a = new GattLayer(this.j);
        }
        return this.a;
    }

    public final void b() {
        ZLogger.v("stopRxSchedule.");
        ThreadRx threadRx = this.i;
        if (threadRx != null) {
            threadRx.cancel(true);
        }
    }

    public final void c() {
        if (this.h != null) {
            ZLogger.v("stopTxSchedule.");
            this.h.cancel(true);
            synchronized (this.f) {
                this.e = false;
                this.f.notifyAll();
            }
        }
    }

    public boolean connect(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket) {
        this.c = 1;
        this.g = 0;
        this.d = 0;
        ThreadTx threadTx = this.h;
        if (threadTx != null) {
            threadTx.cancel(true);
        }
        ZLogger.v("startTxSchedule.");
        ThreadTx threadTx2 = new ThreadTx();
        this.h = threadTx2;
        threadTx2.start();
        ZLogger.v("startRxSchedule.");
        ThreadRx threadRx = this.i;
        if (threadRx != null) {
            threadRx.cancel(true);
        }
        ThreadRx threadRx2 = new ThreadRx();
        this.i = threadRx2;
        threadRx2.start();
        return this.a.connect(bluetoothDevice);
    }

    public void destory() {
        ZLogger.v("destory");
        this.b = null;
        b();
        c();
    }

    public void disconnect() {
        ZLogger.v("disconnect");
        b();
        c();
        GattLayer gattLayer = this.a;
        if (gattLayer != null) {
            gattLayer.close();
        }
    }

    public void sendAck(short s, byte b) {
        byte[] bArrEncode = TransportLayerPacket.encode(this.c, AckPacket.encode(s, b));
        ZLogger.v(String.format("[0x%02X ACK<-0x%04x]", Integer.valueOf(this.c), Short.valueOf(s)));
        int i = this.c;
        this.c = i != 255 ? i + 1 : 1;
        a(bArrEncode, true);
    }

    public boolean sendCmd(byte[] bArr) {
        byte[] bArrEncode = TransportLayerPacket.encode(this.c, bArr);
        if (bArrEncode == null) {
            return false;
        }
        ZLogger.v(String.format(Locale.US, "<< 0x%02x (%d) %s", Integer.valueOf(this.c), Integer.valueOf(bArrEncode.length), DataConverter.bytes2Hex(bArrEncode)));
        int i = this.c;
        this.c = i != 255 ? i + 1 : 1;
        ThreadTx threadTx = this.h;
        if (threadTx != null) {
            threadTx.addQueue(bArrEncode);
        }
        return true;
    }

    public final boolean a(byte[] bArr, boolean z) {
        if (!z) {
            this.e = false;
        }
        GattLayer gattLayer = this.a;
        if (gattLayer == null || gattLayer.sendData(bArr)) {
            ZLogger.w("send gatt data failed");
            return false;
        }
        if (z) {
            return true;
        }
        synchronized (this.f) {
            if (this.e) {
                return true;
            }
            try {
                this.f.wait(5000L);
            } catch (InterruptedException e) {
                ZLogger.w(e.toString());
            }
            return this.e;
        }
    }
}
