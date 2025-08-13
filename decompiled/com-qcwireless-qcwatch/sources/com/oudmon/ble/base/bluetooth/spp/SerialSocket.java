package com.oudmon.ble.base.bluetooth.spp;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import com.elvishew.xlog.XLog;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.Executors;

/* loaded from: classes3.dex */
public class SerialSocket implements Runnable {
    private static final UUID BLUETOOTH_SPP_JieLi = UUID.fromString("FE010000-1234-5678-ABCD-00805F9B34FB");
    private static SerialSocket serialSocket;
    private BluetoothDevice device;
    private SerialListener listener;
    private BluetoothSocket socket;

    private SerialSocket() {
    }

    public static SerialSocket getInstance() {
        if (serialSocket == null) {
            synchronized (SerialSocket.class) {
                if (serialSocket == null) {
                    serialSocket = new SerialSocket();
                }
            }
        }
        return serialSocket;
    }

    public void setListener(SerialListener serialListener) {
        this.listener = serialListener;
    }

    public void setDevice(BluetoothDevice bluetoothDevice) {
        this.device = bluetoothDevice;
    }

    public void connect(SerialListener serialListener) {
        this.listener = serialListener;
        Executors.newSingleThreadExecutor().submit(this);
    }

    public void connect() {
        Executors.newSingleThreadExecutor().submit(this);
    }

    public boolean isConnected() {
        BluetoothSocket bluetoothSocket = this.socket;
        if (bluetoothSocket == null) {
            return false;
        }
        return bluetoothSocket.isConnected();
    }

    public void disconnect() throws IOException {
        try {
            this.listener = null;
            BluetoothSocket bluetoothSocket = this.socket;
            if (bluetoothSocket != null) {
                try {
                    bluetoothSocket.close();
                } catch (Exception unused) {
                }
                this.socket = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(byte[] bArr) throws IOException {
        try {
            BluetoothSocket bluetoothSocket = this.socket;
            if (bluetoothSocket == null) {
                SerialListener serialListener = this.listener;
                if (serialListener != null) {
                    serialListener.onSerialConnect();
                }
                XLog.i("spp 断开了");
                return;
            }
            bluetoothSocket.getOutputStream().write(bArr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        try {
            this.socket.getOutputStream().write(bArr, i, i2);
        } catch (IOException e) {
            SerialListener serialListener = this.listener;
            if (serialListener != null) {
                serialListener.onSerialIoError(e);
            }
            e.printStackTrace();
        }
    }

    @Override // java.lang.Runnable
    public void run() throws IOException {
        try {
            BluetoothSocket bluetoothSocketCreateRfcommSocketToServiceRecord = this.device.createRfcommSocketToServiceRecord(BLUETOOTH_SPP_JieLi);
            this.socket = bluetoothSocketCreateRfcommSocketToServiceRecord;
            bluetoothSocketCreateRfcommSocketToServiceRecord.connect();
            SerialListener serialListener = this.listener;
            if (serialListener != null) {
                serialListener.onSerialConnect();
            }
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    byte[] bArrCopyOf = Arrays.copyOf(bArr, this.socket.getInputStream().read(bArr));
                    SerialListener serialListener2 = this.listener;
                    if (serialListener2 != null && bArrCopyOf.length > 0) {
                        serialListener2.onSerialRead(bArrCopyOf);
                    }
                }
            } catch (Exception e) {
                SerialListener serialListener3 = this.listener;
                if (serialListener3 != null) {
                    serialListener3.onSerialIoError(e);
                }
                try {
                    this.socket.close();
                } catch (Exception unused) {
                }
                this.socket = null;
            }
        } catch (Exception e2) {
            SerialListener serialListener4 = this.listener;
            if (serialListener4 != null) {
                serialListener4.onSerialConnectError(e2);
            }
            try {
                this.socket.close();
            } catch (Exception unused2) {
            }
            this.socket = null;
        }
    }
}
