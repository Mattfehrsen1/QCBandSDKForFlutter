package com.realsil.sdk.core.c;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.text.TextUtils;
import com.realsil.sdk.core.bluetooth.BluetoothProfileManager;
import com.realsil.sdk.core.bluetooth.GlobalGatt;
import com.realsil.sdk.core.bluetooth.RtkBluetoothManager;
import com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.UUID;

/* loaded from: classes3.dex */
public abstract class a {
    public static final UUID CLIENT_CHARACTERISTIC_CONFIG = UUID.fromString(b.CLIENT_CHARACTERISTIC_CONFIG);
    public BluetoothAdapter c;
    public BluetoothProfileManager d;
    public RtkBluetoothManager e;
    public GlobalGatt f;
    public Context mContext;
    public boolean a = true;
    public boolean b = true;
    public Object g = new Object();
    public final C0233a h = new C0233a();

    /* renamed from: com.realsil.sdk.core.c.a$a, reason: collision with other inner class name */
    public class C0233a extends RtkBluetoothManagerCallback {
        public C0233a() {
        }

        @Override // com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback
        public final void onBondStateChanged(BluetoothDevice bluetoothDevice, int i) {
            super.onBondStateChanged(bluetoothDevice, i);
            a.this.a(i);
        }
    }

    public final void a() {
        this.c = BluetoothAdapter.getDefaultAdapter();
        BluetoothProfileManager bluetoothProfileManager = BluetoothProfileManager.getInstance();
        this.d = bluetoothProfileManager;
        if (bluetoothProfileManager == null) {
            BluetoothProfileManager.initial(this.mContext);
            this.d = BluetoothProfileManager.getInstance();
        }
        BluetoothProfileManager bluetoothProfileManager2 = this.d;
        if (bluetoothProfileManager2 != null) {
            bluetoothProfileManager2.addManagerCallback(null);
        } else {
            ZLogger.w(this.a, "BluetoothProfileManager not initialized");
        }
        RtkBluetoothManager rtkBluetoothManager = RtkBluetoothManager.getInstance();
        this.e = rtkBluetoothManager;
        if (rtkBluetoothManager == null) {
            RtkBluetoothManager.initial(this.mContext);
            this.e = RtkBluetoothManager.getInstance();
        }
        RtkBluetoothManager rtkBluetoothManager2 = this.e;
        if (rtkBluetoothManager2 != null) {
            rtkBluetoothManager2.addManagerCallback(this.h);
        } else {
            ZLogger.w(this.a, "BluetoothProfileManager not initialized");
        }
        GlobalGatt globalGatt = GlobalGatt.getInstance();
        this.f = globalGatt;
        if (globalGatt == null) {
            GlobalGatt.initial(this.mContext);
            this.f = GlobalGatt.getInstance();
        }
    }

    public void a(int i) {
    }

    public void destroy() {
        ZLogger.v(this.a, "destroy");
        RtkBluetoothManager rtkBluetoothManager = this.e;
        if (rtkBluetoothManager != null) {
            rtkBluetoothManager.removeManagerCallback(this.h);
        }
        BluetoothProfileManager bluetoothProfileManager = this.d;
        if (bluetoothProfileManager != null) {
            bluetoothProfileManager.removeManagerCallback(null);
        }
    }

    public boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public int getBondState(String str) {
        BluetoothDevice remoteDevice;
        if (this.c == null || (remoteDevice = getRemoteDevice(str)) == null) {
            return 10;
        }
        return remoteDevice.getBondState();
    }

    public Context getContext() {
        return this.mContext;
    }

    public BluetoothDevice getRemoteDevice(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        BluetoothAdapter bluetoothAdapter = this.c;
        if (bluetoothAdapter == null) {
            ZLogger.w("mBluetoothAdapter == null");
            return null;
        }
        try {
            return bluetoothAdapter.getRemoteDevice(str);
        } catch (Exception e) {
            ZLogger.e(e.toString());
            return null;
        }
    }

    public boolean isBluetoothSupported() {
        return this.c != null;
    }

    public boolean isHogpConnect(String str) {
        return isHogpConnect(getRemoteDevice(str));
    }

    public void notifyLock() {
        try {
            synchronized (this.g) {
                if (this.b) {
                    ZLogger.v("notifyLock");
                }
                this.g.notifyAll();
            }
        } catch (Exception e) {
            ZLogger.w(e.toString());
        }
    }

    public boolean isHogpConnect(BluetoothDevice bluetoothDevice) {
        return bluetoothDevice != null && BluetoothProfileManager.getInstance().getConnectionState(4, bluetoothDevice) == 2;
    }
}
