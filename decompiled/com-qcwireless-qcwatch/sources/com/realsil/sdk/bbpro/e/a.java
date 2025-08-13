package com.realsil.sdk.bbpro.e;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Build;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.logger.ZLogger;
import java.io.IOException;
import java.util.UUID;

/* loaded from: classes3.dex */
public class a extends Thread {
    public static final UUID g = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public Context a;
    public BluetoothAdapter b;
    public BluetoothServerSocket c = null;
    public BluetoothSocket d = null;
    public b e;
    public UUID f;

    public a(Context context, UUID uuid, b bVar) {
        this.a = context;
        this.f = uuid;
        this.e = bVar;
    }

    public void a() throws IOException {
        ZLogger.d("_stop");
        BluetoothServerSocket bluetoothServerSocket = this.c;
        if (bluetoothServerSocket != null) {
            try {
                bluetoothServerSocket.close();
            } catch (IOException e) {
                ZLogger.w(e.toString());
            }
        }
        BluetoothSocket bluetoothSocket = this.d;
        if (bluetoothSocket != null) {
            try {
                bluetoothSocket.close();
            } catch (IOException e2) {
                ZLogger.w(e2.toString());
            }
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() throws InterruptedException, IOException {
        if (Build.VERSION.SDK_INT > 17) {
            BluetoothManager bluetoothManager = (BluetoothManager) this.a.getSystemService("bluetooth");
            if (bluetoothManager != null) {
                this.b = bluetoothManager.getAdapter();
            }
        } else {
            this.b = BluetoothAdapter.getDefaultAdapter();
        }
        BluetoothAdapter bluetoothAdapter = this.b;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            b bVar = this.e;
            if (bVar != null) {
                bVar.a(null, false, null);
                return;
            }
            return;
        }
        ZLogger.d("SppServerThread running...");
        try {
            this.c = this.b.listenUsingRfcommWithServiceRecord("Serial Port Protocol", g);
            for (int i = 0; i < 10 && this.c == null; i++) {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    ZLogger.w(e.toString());
                }
            }
            if (this.c == null) {
                ZLogger.d("get BluetoothServerSocket fail");
                b bVar2 = this.e;
                if (bVar2 != null) {
                    bVar2.a(null, false, null);
                }
            } else {
                ZLogger.d("2. get BluetoothServerSocket success");
                try {
                    BluetoothSocket bluetoothSocketAccept = this.c.accept();
                    this.d = bluetoothSocketAccept;
                    if (bluetoothSocketAccept != null) {
                        ZLogger.d("accept new btsocket");
                        BluetoothDevice remoteDevice = this.d.getRemoteDevice();
                        if (remoteDevice != null) {
                            ZLogger.d("client socket connected: name=" + remoteDevice.getName() + ", addr=" + BluetoothHelper.formatAddress(remoteDevice.getAddress(), true));
                            b bVar3 = this.e;
                            if (bVar3 != null) {
                                bVar3.a(remoteDevice, true, this.d);
                            }
                        } else {
                            ZLogger.w("btsocket have no remote device");
                        }
                    } else {
                        ZLogger.w("btsocket is null");
                    }
                    this.c.close();
                } catch (IOException e2) {
                    ZLogger.w(e2.getMessage());
                    b bVar4 = this.e;
                    if (bVar4 != null) {
                        bVar4.a(null, false, this.d);
                    }
                }
            }
            ZLogger.d("SppServerThread stopped");
        } catch (IOException e3) {
            ZLogger.w(e3.getMessage());
            b bVar5 = this.e;
            if (bVar5 != null) {
                bVar5.a(null, false, null);
            }
        }
    }
}
