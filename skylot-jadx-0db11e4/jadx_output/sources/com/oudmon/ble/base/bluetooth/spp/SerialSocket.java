package com.oudmon.ble.base.bluetooth.spp;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import com.realsil.customer.bbpro.equalizer.EqConstants;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.Executors;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/bluetooth/spp/SerialSocket.class */
public class SerialSocket implements Runnable {
    private static final UUID BLUETOOTH_SPP = UUID.fromString("6A24EEAB-4B65-4693-986B-3C26C352264F");
    private SerialListener listener;
    private BluetoothDevice device;
    private BluetoothSocket socket;

    public void setDevice(BluetoothDevice device) {
        this.device = device;
    }

    public void connect(SerialListener listener) {
        this.listener = listener;
        Executors.newSingleThreadExecutor().submit(this);
    }

    public void disconnect() throws IOException {
        try {
            this.listener = null;
            if (this.socket != null) {
                try {
                    this.socket.close();
                } catch (Exception e) {
                }
                this.socket = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void write(byte[] data) throws IOException {
        try {
            this.socket.getOutputStream().write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(byte[] data, int off, int len) throws IOException {
        try {
            this.socket.getOutputStream().write(data, off, len);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override // java.lang.Runnable
    public void run() throws IOException {
        try {
            this.socket = this.device.createRfcommSocketToServiceRecord(BLUETOOTH_SPP);
            this.socket.connect();
            if (this.listener != null) {
                this.listener.onSerialConnect();
            }
            try {
                byte[] buffer = new byte[EqConstants.CodeIndex.REALTIME_EQ_2];
                while (true) {
                    int len = this.socket.getInputStream().read(buffer);
                    byte[] data = Arrays.copyOf(buffer, len);
                    if (this.listener != null) {
                        this.listener.onSerialRead(data);
                    }
                }
            } catch (Exception e) {
                if (this.listener != null) {
                    this.listener.onSerialIoError(e);
                }
                try {
                    this.socket.close();
                } catch (Exception e2) {
                }
                this.socket = null;
            }
        } catch (Exception e3) {
            if (this.listener != null) {
                this.listener.onSerialConnectError(e3);
            }
            try {
                this.socket.close();
            } catch (Exception e4) {
            }
            this.socket = null;
        }
    }
}
