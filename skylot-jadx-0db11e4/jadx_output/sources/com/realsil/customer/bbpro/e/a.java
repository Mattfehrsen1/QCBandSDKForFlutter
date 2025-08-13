package com.realsil.customer.bbpro.e;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import com.realsil.customer.core.bluetooth.utils.BluetoothHelper;
import com.realsil.customer.core.logger.ZLogger;
import java.io.IOException;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/e/a.class */
public class a extends Thread {
    public static final UUID g = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public Context a;
    public BluetoothAdapter b;
    public BluetoothServerSocket c = null;
    public BluetoothSocket d = null;
    public b e;
    public UUID f;

    public a(Context context, @NonNull UUID uuid, b bVar) {
        this.a = context;
        this.f = uuid;
        this.e = bVar;
    }

    public void a() throws IOException {
        ZLogger.d("_stop");
        BluetoothServerSocket bluetoothServerSocket = this.c;
        if (bluetoothServerSocket != null) {
            try {
                bluetoothServerSocket = bluetoothServerSocket;
                bluetoothServerSocket.close();
            } catch (IOException unused) {
                ZLogger.w(bluetoothServerSocket.toString());
            }
        }
        BluetoothSocket bluetoothSocket = this.d;
        if (bluetoothSocket != null) {
            try {
                bluetoothSocket = bluetoothSocket;
                bluetoothSocket.close();
            } catch (IOException unused2) {
                ZLogger.w(bluetoothSocket.toString());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v19, types: [com.realsil.customer.bbpro.e.a] */
    /* JADX WARN: Type inference failed for: r0v20, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v29, types: [android.bluetooth.BluetoothServerSocket] */
    /* JADX WARN: Type inference failed for: r0v42, types: [android.bluetooth.BluetoothServerSocket] */
    /* JADX WARN: Type inference failed for: r0v43, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v45 */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.realsil.customer.bbpro.e.a, java.lang.Throwable] */
    @Override // java.lang.Thread, java.lang.Runnable
    public void run() throws InterruptedException, IOException {
        ?? r0;
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
            for (int i = 0; i < 10 && (r0 = this.c) == 0; i++) {
                try {
                    r0 = 500;
                    Thread.sleep(500L);
                } catch (InterruptedException unused) {
                    ZLogger.w(r0.toString());
                }
            }
            if (this.c == null) {
                ZLogger.d("get BluetoothServerSocket fail");
                b bVar2 = this.e;
                if (bVar2 != null) {
                    bVar2.a(null, false, null);
                }
            } else {
                ?? r02 = this;
                ZLogger.d("2. get BluetoothServerSocket success");
                try {
                    BluetoothSocket bluetoothSocketAccept = r02.c.accept();
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
                    r02 = this.c;
                    r02.close();
                } catch (IOException unused2) {
                    ZLogger.w(r02.getMessage());
                    b bVar4 = this.e;
                    if (bVar4 != null) {
                        bVar4.a(null, false, this.d);
                    }
                }
            }
            ZLogger.d("SppServerThread stopped");
        } catch (IOException unused3) {
            ZLogger.w(getMessage());
            b bVar5 = this.e;
            if (bVar5 != null) {
                bVar5.a(null, false, null);
            }
        }
    }
}
