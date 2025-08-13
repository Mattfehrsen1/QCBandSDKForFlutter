package com.realsil.sdk.bbpro.core.transportlayer;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.ParcelUuid;
import com.realsil.sdk.bbpro.core.Utils;
import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.base.BaseThread;
import com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSpp;
import com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSppCallback;
import com.realsil.sdk.core.bluetooth.connection.legacy.SppConnParameters;
import com.realsil.sdk.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes3.dex */
public final class SppTransportLayer {
    public static boolean D = true;
    public static boolean n = true;
    public static volatile SppTransportLayer o;
    public BluetoothSpp b;
    public volatile int e;
    public volatile int f;
    public CommandThread g;
    public AckThread h;
    public ThreadRx i;
    public volatile boolean j;
    public volatile Command l;
    public UUID c = TransportConnParams.VENDOR_SPP_UUID;
    public Object d = new Object();
    public final Object k = new Object();
    public BluetoothSppCallback m = new BluetoothSppCallback() { // from class: com.realsil.sdk.bbpro.core.transportlayer.SppTransportLayer.1
        /* JADX WARN: Type inference failed for: r1v2, types: [java.util.List<com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        /* JADX WARN: Type inference failed for: r1v5, types: [java.util.List<com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        @Override // com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSppCallback
        public void onConnectionStateChanged(BluetoothSpp bluetoothSpp, boolean z, int i) {
            super.onConnectionStateChanged(bluetoothSpp, z, i);
            BluetoothDevice device = bluetoothSpp.getDevice();
            String address = device != null ? device.getAddress() : null;
            if (SppTransportLayer.n) {
                ZLogger.v(String.format(Locale.US, "%s status: %b 0x%04X", BluetoothHelper.formatAddress(address, true), Boolean.valueOf(z), Integer.valueOf(i)));
            }
            if (!z || i == 0) {
                SppTransportLayer.this.a();
            }
            try {
                synchronized (SppTransportLayer.this.a) {
                    ?? r1 = SppTransportLayer.this.a;
                    if (r1 != 0 && r1.size() > 0) {
                        Iterator it = SppTransportLayer.this.a.iterator();
                        while (it.hasNext()) {
                            ((TransportLayerCallback) it.next()).onConnectionStateChanged(device, z, i);
                        }
                    }
                }
            } catch (Exception e) {
                ZLogger.w(e.toString());
            }
        }

        @Override // com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSppCallback
        public void onDataReceive(byte[] bArr) {
            ThreadRx threadRx = SppTransportLayer.this.i;
            if (threadRx == null || bArr == null) {
                return;
            }
            threadRx.addQueue(bArr);
        }
    };
    public List<TransportLayerCallback> a = new CopyOnWriteArrayList();

    public class AckThread extends BaseThread<Command> {
        public AckThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            byte[] bArrEncode;
            setName("AckThread");
            if (SppTransportLayer.n) {
                ZLogger.v("AckThread is running...");
            }
            while (!Thread.currentThread().isInterrupted() && !isCanceled()) {
                Command commandTake = take();
                if (commandTake != null) {
                    if (commandTake.getPayload() == null) {
                        ZLogger.v("payload == null");
                    } else {
                        synchronized (SppTransportLayer.this.d) {
                            bArrEncode = commandTake.encode(SppTransportLayer.this.e);
                            SppTransportLayer.a(SppTransportLayer.this);
                        }
                        SppTransportLayer.this.b().write(bArrEncode);
                    }
                }
            }
            if (SppTransportLayer.n) {
                ZLogger.v("TxThread stopped");
            }
        }
    }

    public class CommandThread extends BaseThread<Command> {
        public CommandThread() {
        }

        public final boolean a(Command command) {
            byte[] bArrEncode;
            boolean zWrite;
            synchronized (SppTransportLayer.this.d) {
                bArrEncode = command.encode(SppTransportLayer.this.e);
                SppTransportLayer.a(SppTransportLayer.this);
            }
            if (command.getWriteType() == 1) {
                return SppTransportLayer.this.b().write(bArrEncode);
            }
            boolean z = false;
            SppTransportLayer.this.j = false;
            int i = 0;
            do {
                zWrite = SppTransportLayer.this.b().write(bArrEncode);
                if (!zWrite) {
                    break;
                }
                synchronized (SppTransportLayer.this.k) {
                    if (!SppTransportLayer.this.j) {
                        try {
                            SppTransportLayer.this.k.wait(500L);
                        } catch (InterruptedException e) {
                            ZLogger.w(e.toString());
                        }
                        z = !SppTransportLayer.this.j;
                        zWrite = SppTransportLayer.this.j;
                        if (!SppTransportLayer.this.j) {
                            ZLogger.v(SppTransportLayer.n, "ACK timeout for 500 ms");
                        }
                    }
                }
                i++;
                if (i > command.getRetransCount()) {
                    break;
                }
            } while (z);
            return zWrite;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            setName("CommandThread");
            if (SppTransportLayer.n) {
                ZLogger.v("CommandThread is running...");
            }
            SppTransportLayer.this.l = null;
            while (!Thread.currentThread().isInterrupted() && !isCanceled()) {
                Command commandTake = take();
                if (commandTake != null) {
                    if (commandTake.isCommandIdAvailable()) {
                        SppTransportLayer.this.l = commandTake;
                    }
                    a(commandTake);
                }
            }
            SppTransportLayer.this.l = null;
            if (SppTransportLayer.n) {
                ZLogger.v("TxThread stopped");
            }
        }
    }

    public class ThreadRx extends BaseThread<byte[]> {
        public ThreadRx() {
        }

        /* JADX WARN: Type inference failed for: r0v17, types: [java.util.List<com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        /* JADX WARN: Type inference failed for: r0v20, types: [java.util.List<com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        /* JADX WARN: Type inference failed for: r1v2, types: [java.util.List<com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        /* JADX WARN: Type inference failed for: r1v5, types: [java.util.List<com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        public final synchronized void a(TransportLayerPacket transportLayerPacket) {
            short opcode = transportLayerPacket.getOpcode();
            transportLayerPacket.getPayload();
            byte[] parameters = transportLayerPacket.getParameters();
            boolean z = transportLayerPacket.getSeqNum() == SppTransportLayer.this.f;
            SppTransportLayer.this.f = transportLayerPacket.getSeqNum();
            if (!transportLayerPacket.isAckPkt()) {
                SppTransportLayer.this.sendAck(transportLayerPacket.getOpcode(), (byte) 0);
                if (SppTransportLayer.D) {
                    ZLogger.v(String.format(Locale.US, "[0x%02X(%b) PACK->0x%04X] %s", Byte.valueOf(transportLayerPacket.getSeqNum()), Boolean.valueOf(z), Short.valueOf(opcode), DataConverter.bytes2Hex(parameters)));
                }
                if (!z) {
                    try {
                        if (SppTransportLayer.this.l != null && SppTransportLayer.this.l.getEventId() == transportLayerPacket.getOpcode()) {
                            SppTransportLayer.this.c();
                            SppTransportLayer.this.l = null;
                        }
                        synchronized (SppTransportLayer.this.a) {
                            ?? r1 = SppTransportLayer.this.a;
                            if (r1 != 0 && r1.size() > 0) {
                                Iterator it = SppTransportLayer.this.a.iterator();
                                while (it.hasNext()) {
                                    ((TransportLayerCallback) it.next()).onDataReceive(transportLayerPacket);
                                }
                            }
                        }
                    } catch (Exception e) {
                        ZLogger.w(e.toString());
                    }
                }
            }
            if (z) {
                return;
            }
            AckPacket ackPacket = transportLayerPacket.toAckPacket();
            if (ackPacket != null) {
                if (SppTransportLayer.D) {
                    ZLogger.v(String.format(Locale.US, "[0x%02X ACK->0x%04X] 0x%02X", Byte.valueOf(transportLayerPacket.getSeqNum()), Short.valueOf(ackPacket.getToAckId()), Byte.valueOf(ackPacket.getStatus())));
                }
                try {
                    if (SppTransportLayer.this.l == null) {
                        SppTransportLayer.this.c();
                    } else if (SppTransportLayer.this.l.getCommandId() == ackPacket.getToAckId()) {
                        SppTransportLayer.this.c();
                        SppTransportLayer.this.l = null;
                    } else {
                        ZLogger.v(SppTransportLayer.n, String.format("ignore ACK, expect is 0x%04X", Short.valueOf(SppTransportLayer.this.l.getCommandId())));
                    }
                    synchronized (SppTransportLayer.this.a) {
                        ?? r0 = SppTransportLayer.this.a;
                        if (r0 != 0 && r0.size() > 0) {
                            Iterator it2 = SppTransportLayer.this.a.iterator();
                            while (it2.hasNext()) {
                                ((TransportLayerCallback) it2.next()).onAckReceive(ackPacket);
                            }
                        }
                    }
                } catch (Exception e2) {
                    ZLogger.e(e2.toString());
                }
            } else {
                ZLogger.d(String.format(Locale.US, "[0x%02X NA->0x%04X] %s", Byte.valueOf(transportLayerPacket.getSeqNum()), Short.valueOf(opcode), DataConverter.bytes2Hex(parameters)));
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (SppTransportLayer.D) {
                ZLogger.d("RxThread is running");
            }
            while (!Thread.currentThread().isInterrupted() && !isCanceled()) {
                byte[] bArrTake = take();
                if (bArrTake != null) {
                    int length = bArrTake.length;
                    int packetLength = 0;
                    do {
                        int i = length - packetLength;
                        if (i <= 0) {
                            break;
                        }
                        try {
                            byte[] bArr = new byte[i];
                            System.arraycopy(bArrTake, packetLength, bArr, 0, i);
                            TransportLayerPacket transportLayerPacketBuilderPacket = TransportLayerPacket.builderPacket(bArr);
                            if (transportLayerPacketBuilderPacket == null) {
                                break;
                            }
                            a(transportLayerPacketBuilderPacket);
                            packetLength += transportLayerPacketBuilderPacket.getPacketLength();
                        } catch (Exception e) {
                            ZLogger.w(e.toString());
                        }
                    } while (packetLength < length);
                }
            }
            if (SppTransportLayer.D) {
                ZLogger.d("RxThread stopped");
            }
        }
    }

    public SppTransportLayer() {
        D = RtkCore.DEBUG;
        n = RtkCore.VDBG;
    }

    public static synchronized SppTransportLayer getInstance() {
        if (o == null) {
            synchronized (SppTransportLayer.class) {
                if (o == null) {
                    o = new SppTransportLayer();
                }
            }
        }
        return o;
    }

    public final boolean a(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket, UUID uuid, int i) {
        if (bluetoothDevice == null) {
            return false;
        }
        if (b().isConnected(bluetoothDevice)) {
            BluetoothSppCallback bluetoothSppCallback = this.m;
            if (bluetoothSppCallback != null) {
                bluetoothSppCallback.onConnectionStateChanged(b(), true, 512);
            }
            return true;
        }
        this.e = 1;
        this.f = 0;
        d();
        AckThread ackThread = this.h;
        if (ackThread != null) {
            ackThread.cancel(true);
        }
        if (n) {
            ZLogger.v("startAckThread.");
        }
        AckThread ackThread2 = new AckThread();
        this.h = ackThread2;
        ackThread2.start();
        ThreadRx threadRx = this.i;
        if (threadRx != null) {
            threadRx.cancel(true);
        }
        ThreadRx threadRx2 = new ThreadRx();
        this.i = threadRx2;
        threadRx2.start();
        if (n) {
            BluetoothDeviceImpl.dumpSupportedUuids(bluetoothDevice);
        }
        ParcelUuid uuid2 = Utils.getUuid(bluetoothDevice.getUuids(), uuid, true);
        if (uuid2 != null) {
            ZLogger.v(D, "use pref spp: " + uuid);
        } else {
            if (i == 1) {
                ZLogger.w(D, "not find pref spp: " + uuid);
                return false;
            }
            uuid2 = TransportConnParams.WELL_KNOWN_SPP_UUID;
            ZLogger.v(D, "use well-known spp: " + uuid2.toString());
        }
        this.c = uuid2.getUuid();
        return b().connect(new SppConnParameters.Builder(bluetoothDevice).bluetoothSocket(bluetoothSocket).uuid(uuid2.getUuid()).build());
    }

    public final BluetoothSpp b() {
        if (this.b == null) {
            this.b = new BluetoothSpp(this.m);
        }
        return this.b;
    }

    public final void c() {
        synchronized (this.k) {
            this.j = true;
            this.k.notifyAll();
        }
    }

    public boolean connect(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket) {
        return a(bluetoothDevice, bluetoothSocket, this.c, 0);
    }

    public final void d() {
        CommandThread commandThread = this.g;
        if (commandThread != null) {
            commandThread.cancel(true);
        }
        if (n) {
            ZLogger.v("startTxSchedule.");
        }
        CommandThread commandThread2 = new CommandThread();
        this.g = commandThread2;
        commandThread2.start();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.List<com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback>, java.util.concurrent.CopyOnWriteArrayList] */
    public void destory() {
        if (n) {
            ZLogger.v("destory");
        }
        synchronized (this.a) {
            ?? r1 = this.a;
            if (r1 != 0) {
                r1.clear();
            }
        }
        if (this.i != null) {
            if (n) {
                ZLogger.v("stopRxSchedule.");
            }
            this.i.clearQueue();
            this.i.cancel(true);
        }
        if (this.g != null) {
            if (n) {
                ZLogger.v("stopTxSchedule.");
            }
            this.g.clearQueue();
            this.g.cancel(true);
            c();
        }
        if (this.h != null) {
            if (n) {
                ZLogger.v("stopAckThread.");
            }
            this.h.clearQueue();
            this.h.cancel(true);
            c();
        }
        BluetoothSpp bluetoothSpp = this.b;
        if (bluetoothSpp != null) {
            bluetoothSpp.destroy();
            this.b = null;
        }
    }

    public void disconnect() {
        a();
        if (this.b != null) {
            if (n) {
                ZLogger.v("disconnect");
            }
            this.b.stop();
        }
    }

    public int getConnectionState() {
        return b().getConnectionState();
    }

    public boolean isConnected(BluetoothDevice bluetoothDevice) {
        BluetoothSpp bluetoothSpp = this.b;
        if (bluetoothSpp == null) {
            return false;
        }
        return bluetoothSpp.isConnected(bluetoothDevice);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.List<com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback>, java.util.concurrent.CopyOnWriteArrayList] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.List<com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback>, java.util.concurrent.CopyOnWriteArrayList] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.util.List<com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback>, java.util.concurrent.CopyOnWriteArrayList] */
    public void register(TransportLayerCallback transportLayerCallback) {
        synchronized (this.a) {
            if (this.a == null) {
                this.a = new CopyOnWriteArrayList();
            }
            if (!this.a.contains(transportLayerCallback)) {
                this.a.add(transportLayerCallback);
            }
            if (n) {
                ZLogger.v("callback's size=" + this.a.size());
            }
        }
    }

    public synchronized boolean sendAck(Command command) {
        if (command == null) {
            return false;
        }
        if (this.h == null) {
            d();
        }
        if (this.h == null) {
            return false;
        }
        if (n) {
            ZLogger.v(String.format(Locale.US, "<< writeType=%d, (%d)%s", Integer.valueOf(command.getWriteType()), Integer.valueOf(command.getPayloadLength()), DataConverter.bytes2Hex(command.getPayload())));
        }
        this.h.addQueue(command);
        return true;
    }

    public boolean sendCmd(byte[] bArr) {
        return sendCommand(new Command.Builder().writeType(2).payload(bArr).build());
    }

    public synchronized boolean sendCommand(Command command) {
        if (command == null) {
            return false;
        }
        if (this.g == null) {
            d();
        }
        if (this.g == null) {
            return false;
        }
        if (n) {
            ZLogger.v(String.format(Locale.US, "<< writeType=%d, (%d)%s", Integer.valueOf(command.getWriteType()), Integer.valueOf(command.getPayloadLength()), DataConverter.bytes2Hex(command.getPayload())));
        }
        this.g.addQueue(command);
        return true;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.List<com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback>, java.util.concurrent.CopyOnWriteArrayList] */
    public void unregister(TransportLayerCallback transportLayerCallback) {
        synchronized (this.a) {
            ?? r1 = this.a;
            if (r1 != 0) {
                r1.remove(transportLayerCallback);
            }
        }
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

    public boolean sendCmd(short s, byte[] bArr) {
        return sendCommand(new Command.Builder().writeType(2).packet(s, bArr).build());
    }

    public boolean sendAck(short s, byte b) {
        return sendAck(new Command.Builder().writeType(1).commandId(s).payload(AckPacket.encode(s, b)).build());
    }

    public boolean sendCommand(short s, short s2, byte[] bArr) {
        return sendCommand(new Command.Builder().writeType(2).packet(s, bArr).eventId(s2).build());
    }

    public final void a() {
        if (this.i != null) {
            if (n) {
                ZLogger.v("clearRx");
            }
            this.i.clearQueue();
            this.i.cancel(true);
        }
        if (this.g != null) {
            if (n) {
                ZLogger.v("clearTx.");
            }
            this.g.clearQueue();
            c();
        }
        if (this.h != null) {
            if (n) {
                ZLogger.v("clearAck.");
            }
            this.h.clearQueue();
            c();
        }
    }

    public static void a(SppTransportLayer sppTransportLayer) {
        if (sppTransportLayer.e != 255) {
            sppTransportLayer.e++;
        } else {
            sppTransportLayer.e = 1;
        }
    }
}
