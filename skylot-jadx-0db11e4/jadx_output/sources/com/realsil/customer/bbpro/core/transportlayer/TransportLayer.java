package com.realsil.customer.bbpro.core.transportlayer;

import android.bluetooth.BluetoothDevice;
import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.core.RtkCore;
import com.realsil.customer.core.base.BaseThread;
import com.realsil.customer.core.logger.ZLogger;
import com.realsil.customer.core.utility.DataConverter;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/core/transportlayer/TransportLayer.class */
public class TransportLayer {
    public static boolean D = true;
    public static boolean k = true;
    public volatile int b;
    public volatile int c;
    public CommandThread d;
    public AckThread e;
    public ThreadRx f;
    public CopyOnWriteArrayList g;
    public volatile Command h;
    public volatile boolean i;
    public final Object a = new Object();
    public final Object j = new Object();

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/core/transportlayer/TransportLayer$AckThread.class */
    public class AckThread extends BaseThread<Command> {
        public AckThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws InterruptedException {
            setName("AckThread");
            if (TransportLayer.k) {
                ZLogger.v("AckThread is running...");
            }
            while (!Thread.currentThread().isInterrupted() && !isCanceled()) {
                Command commandTake = take();
                if (commandTake != null) {
                    if (commandTake.getPayload() == null) {
                        ZLogger.v("payload == null");
                    } else {
                        synchronized (TransportLayer.this.a) {
                            commandTake.setSn(TransportLayer.this.b);
                            TransportLayer transportLayer = TransportLayer.this;
                            if (transportLayer.b != 255) {
                                transportLayer.b++;
                            } else {
                                transportLayer.b = 1;
                            }
                        }
                        TransportLayer.this.sendCommandInner(commandTake);
                    }
                }
            }
            if (TransportLayer.k) {
                ZLogger.v("TxThread stopped");
            }
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/core/transportlayer/TransportLayer$CommandThread.class */
    public class CommandThread extends BaseThread<Command> {
        public CommandThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws InterruptedException {
            setName("CommandThread");
            if (TransportLayer.k) {
                ZLogger.v("CommandThread is running...");
            }
            TransportLayer.this.h = null;
            while (!Thread.currentThread().isInterrupted() && !isCanceled()) {
                Command commandTake = take();
                if (commandTake != null) {
                    if (commandTake.isCommandIdAvailable()) {
                        TransportLayer.this.h = commandTake;
                    }
                    a(commandTake);
                }
            }
            TransportLayer.this.h = null;
            if (TransportLayer.k) {
                ZLogger.v("TxThread stopped");
            }
        }

        public final void a(Command command) {
            synchronized (TransportLayer.this.a) {
                command.setSn(TransportLayer.this.b);
                TransportLayer transportLayer = TransportLayer.this;
                if (transportLayer.b != 255) {
                    transportLayer.b++;
                } else {
                    transportLayer.b = 1;
                }
            }
            if (command.getWriteType() == 1) {
                TransportLayer.this.sendCommandInner(command);
                return;
            }
            TransportLayer.this.i = false;
            boolean z = false;
            int i = 0;
            while (TransportLayer.this.sendCommandInner(command)) {
                synchronized (TransportLayer.this.j) {
                    if (!TransportLayer.this.i) {
                        try {
                            TransportLayer.this.j.wait(500L);
                        } catch (InterruptedException e) {
                            ZLogger.w(e.toString());
                        }
                        z = !TransportLayer.this.i;
                        boolean z2 = TransportLayer.this.i;
                        if (!TransportLayer.this.i) {
                            boolean z3 = TransportLayer.k;
                            Locale locale = Locale.US;
                            Object[] objArr = new Object[2];
                            int i2 = i;
                            objArr[0] = 500;
                            objArr[1] = Integer.valueOf(i2);
                            ZLogger.v(z3, String.format(locale, "ACK timeout for %d ms, times=%d", objArr));
                        }
                    }
                }
                int i3 = i + 1;
                i = i3;
                if (i3 > command.getRetransCount() || !z) {
                    return;
                }
            }
        }
    }

    public TransportLayer() {
        c();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, java.lang.Throwable] */
    public final void d() {
        synchronized (this.j) {
            this.i = true;
            this.j.notifyAll();
        }
    }

    public final void c() {
        D = RtkCore.DEBUG;
        k = RtkCore.VDBG;
        this.g = new CopyOnWriteArrayList();
    }

    public boolean isConnected(BluetoothDevice bluetoothDevice) {
        return false;
    }

    public synchronized boolean sendCommandInner(Command command) {
        return false;
    }

    public synchronized boolean sendData(byte[] bArr) {
        return false;
    }

    public void register(TransportLayerCallback transportLayerCallback) {
        synchronized (this.g) {
            if (this.g == null) {
                this.g = new CopyOnWriteArrayList();
            }
            if (!this.g.contains(transportLayerCallback)) {
                this.g.add(transportLayerCallback);
            }
            if (k) {
                ZLogger.v("callback's size=" + this.g.size());
            }
        }
    }

    public void unregister(TransportLayerCallback transportLayerCallback) {
        synchronized (this.g) {
            CopyOnWriteArrayList copyOnWriteArrayList = this.g;
            if (copyOnWriteArrayList != null) {
                copyOnWriteArrayList.remove(transportLayerCallback);
            }
        }
    }

    public final void a(BluetoothDevice bluetoothDevice, boolean z, int i) {
        try {
            synchronized (this.g) {
                CopyOnWriteArrayList copyOnWriteArrayList = this.g;
                if (copyOnWriteArrayList != null && copyOnWriteArrayList.size() > 0) {
                    Iterator it = this.g.iterator();
                    while (it.hasNext()) {
                        ((TransportLayerCallback) it.next()).onConnectionStateChanged(bluetoothDevice, z, i);
                    }
                }
            }
        } catch (Exception unused) {
            ZLogger.w(th.toString());
        }
    }

    public synchronized boolean sendCommand(Command command) {
        if (command == null) {
            return false;
        }
        CommandThread commandThread = this.d;
        if (commandThread == null) {
            if (commandThread != null) {
                commandThread.cancel(true);
            }
            if (k) {
                ZLogger.v("startTxSchedule.");
            }
            CommandThread commandThread2 = new CommandThread();
            this.d = commandThread2;
            commandThread2.start();
        }
        if (this.d == null) {
            return false;
        }
        if (k) {
            ZLogger.v(String.format(Locale.US, "<< writeType=%d, (%d)%s", Integer.valueOf(command.getWriteType()), Integer.valueOf(command.getPayloadLength()), DataConverter.bytes2Hex(command.getPayload())));
        }
        this.d.addQueue(command);
        return true;
    }

    public boolean sendCmd(byte[] bArr) {
        return sendCommand(new Command.Builder().writeType(2).payload(bArr).build());
    }

    public synchronized boolean sendAck(Command command) {
        if (command == null) {
            return false;
        }
        AckThread ackThread = this.e;
        if (ackThread == null) {
            if (ackThread != null) {
                ackThread.cancel(true);
            }
            if (k) {
                ZLogger.v("startAckThread.");
            }
            AckThread ackThread2 = new AckThread();
            this.e = ackThread2;
            ackThread2.start();
        }
        if (this.e == null) {
            return false;
        }
        if (k) {
            ZLogger.v(String.format(Locale.US, "<< writeType=%d, (%d)%s", Integer.valueOf(command.getWriteType()), Integer.valueOf(command.getPayloadLength()), DataConverter.bytes2Hex(command.getPayload())));
        }
        this.e.addQueue(command);
        return true;
    }

    public final void b() {
        this.c = 0;
        this.b = 1;
        CommandThread commandThread = this.d;
        if (commandThread != null) {
            commandThread.cancel(true);
        }
        if (k) {
            ZLogger.v("startTxSchedule.");
        }
        CommandThread commandThread2 = new CommandThread();
        this.d = commandThread2;
        commandThread2.start();
        AckThread ackThread = this.e;
        if (ackThread != null) {
            ackThread.cancel(true);
        }
        if (k) {
            ZLogger.v("startAckThread.");
        }
        AckThread ackThread2 = new AckThread();
        this.e = ackThread2;
        ackThread2.start();
        ThreadRx threadRx = this.f;
        if (threadRx != null) {
            threadRx.cancel(true);
        }
        ThreadRx threadRx2 = new ThreadRx();
        this.f = threadRx2;
        threadRx2.start();
    }

    public void disconnect() {
        if (k) {
            ZLogger.v("disconnect");
        }
        a();
    }

    public void destroy() {
        if (k) {
            ZLogger.v("destory");
        }
        synchronized (this.g) {
            CopyOnWriteArrayList copyOnWriteArrayList = this.g;
            if (copyOnWriteArrayList != null) {
                copyOnWriteArrayList.clear();
            }
        }
        a();
    }

    public boolean sendCmd(short s, byte[] bArr) {
        return sendCommand(new Command.Builder().writeType(2).packet(s, bArr).build());
    }

    public final void a(byte[] bArr) {
        ThreadRx threadRx = this.f;
        if (threadRx == null || bArr == null) {
            return;
        }
        threadRx.addQueue(bArr);
    }

    public final void a() {
        if (this.f != null) {
            if (k) {
                ZLogger.v("clearRx");
            }
            this.f.clearQueue();
            this.f.cancel(true);
        }
        if (this.d != null) {
            if (k) {
                ZLogger.v("clearTx.");
            }
            this.d.clearQueue();
            d();
        }
        if (this.e != null) {
            if (k) {
                ZLogger.v("clearAck.");
            }
            this.e.clearQueue();
            d();
        }
    }

    public boolean sendAck(short s, byte b) {
        return sendAck(new Command.Builder().writeType(1).commandId(s).payload(AckPacket.encode(s, b)).build());
    }

    public boolean sendCommand(short s, short s2, byte[] bArr) {
        return sendCommand(new Command.Builder().writeType(2).packet(s, bArr).eventId(s2).build());
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/core/transportlayer/TransportLayer$ThreadRx.class */
    public class ThreadRx extends BaseThread<byte[]> {
        public ThreadRx() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v19, types: [byte[], java.lang.Object] */
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws InterruptedException {
            TransportLayerPacket transportLayerPacketBuilderPacket;
            if (TransportLayer.D) {
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
                            i = new byte[i];
                            System.arraycopy(bArrTake, packetLength, i, 0, i);
                            transportLayerPacketBuilderPacket = TransportLayerPacket.builderPacket(i);
                        } catch (Exception unused) {
                            ZLogger.w(i.toString());
                        }
                        if (transportLayerPacketBuilderPacket == null) {
                            break;
                        }
                        b(transportLayerPacketBuilderPacket);
                        packetLength += transportLayerPacketBuilderPacket.getPacketLength();
                    } while (packetLength < length);
                }
            }
            if (TransportLayer.D) {
                ZLogger.d("RxThread stopped");
            }
        }

        public final synchronized void b(TransportLayerPacket transportLayerPacket) {
            short opcode = transportLayerPacket.getOpcode();
            transportLayerPacket.getPayload();
            byte[] parameters = transportLayerPacket.getParameters();
            boolean z = transportLayerPacket.getSeqNum() == TransportLayer.this.c;
            TransportLayer.this.c = transportLayerPacket.getSeqNum();
            if (!transportLayerPacket.isAckPkt()) {
                TransportLayer.this.sendAck(transportLayerPacket.getOpcode(), (byte) 0);
                if (TransportLayer.D) {
                    ZLogger.v(String.format(Locale.US, "[0x%02X(%b) PACK->0x%04X] %s", Byte.valueOf(transportLayerPacket.getSeqNum()), Boolean.valueOf(z), Short.valueOf(opcode), DataConverter.bytes2Hex(parameters)));
                }
                if (z) {
                    return;
                }
                a(transportLayerPacket);
                return;
            }
            if (z) {
                return;
            }
            AckPacket ackPacket = transportLayerPacket.toAckPacket();
            if (ackPacket == null) {
                ZLogger.d(String.format(Locale.US, "[0x%02X NA->0x%04X] %s", Byte.valueOf(transportLayerPacket.getSeqNum()), Short.valueOf(opcode), DataConverter.bytes2Hex(parameters)));
                return;
            }
            if (TransportLayer.D) {
                ZLogger.v(String.format(Locale.US, "[0x%02X ACK->0x%04X] 0x%02X", Byte.valueOf(transportLayerPacket.getSeqNum()), Short.valueOf(ackPacket.getToAckId()), Byte.valueOf(ackPacket.getStatus())));
            }
            a(ackPacket);
        }

        public final void a(AckPacket ackPacket) {
            try {
                if (TransportLayer.this.h == null) {
                    TransportLayer.this.d();
                } else if (TransportLayer.this.h.getCommandId() == ackPacket.getToAckId()) {
                    TransportLayer.this.d();
                    TransportLayer.this.h = null;
                } else {
                    boolean z = TransportLayer.k;
                    Object[] objArr = new Object[1];
                    objArr[0] = Short.valueOf(TransportLayer.this.h.getCommandId());
                    ZLogger.v(z, String.format("ignore ACK, expect is 0x%04X", objArr));
                }
                synchronized (TransportLayer.this.g) {
                    CopyOnWriteArrayList copyOnWriteArrayList = TransportLayer.this.g;
                    if (copyOnWriteArrayList != null && copyOnWriteArrayList.size() > 0) {
                        Iterator it = TransportLayer.this.g.iterator();
                        while (it.hasNext()) {
                            ((TransportLayerCallback) it.next()).onAckReceive(ackPacket);
                        }
                    }
                }
            } catch (Exception unused) {
                ZLogger.w(th.toString());
            }
        }

        public final void a(TransportLayerPacket transportLayerPacket) {
            try {
                if (TransportLayer.this.h != null && TransportLayer.this.h.getEventId() == transportLayerPacket.getOpcode()) {
                    TransportLayer.this.d();
                    TransportLayer.this.h = null;
                }
                synchronized (TransportLayer.this.g) {
                    CopyOnWriteArrayList copyOnWriteArrayList = TransportLayer.this.g;
                    if (copyOnWriteArrayList != null && copyOnWriteArrayList.size() > 0) {
                        Iterator it = TransportLayer.this.g.iterator();
                        while (it.hasNext()) {
                            ((TransportLayerCallback) it.next()).onDataReceive(transportLayerPacket);
                        }
                    }
                }
            } catch (Exception unused) {
                ZLogger.w(th.toString());
            }
        }
    }
}
