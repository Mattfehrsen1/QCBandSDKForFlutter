package com.realsil.customer.core.bluetooth.connection;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.realsil.customer.core.RtkCore;
import com.realsil.customer.core.a.a;
import com.realsil.customer.core.logger.ZLogger;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/connection/BluetoothClient.class */
public class BluetoothClient {
    public static final int STATE_NONE = 0;
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_DISCONNECTING = 3;
    public static final int STATE_LISTEN = 4;
    public static final int STATE_BONDING = 5;

    @Keep
    protected Context mContext;

    @Keep
    protected BluetoothManager mBluetoothManager;

    @Keep
    protected BluetoothAdapter mBluetoothAdapter;

    @Keep
    protected BluetoothClientCallback mCallback;

    @Keep
    protected boolean DBG = false;

    @Keep
    protected boolean VDBG = false;

    @Keep
    protected BluetoothDevice mDevice = null;

    @Keep
    protected int mBondState = 10;
    public int a = 0;

    public void a() {
        this.DBG = RtkCore.DEBUG;
        this.VDBG = RtkCore.VDBG;
        this.mBluetoothAdapter = a.a(this.mContext);
        this.a = 0;
    }

    public Context getContext() {
        return this.mContext;
    }

    public BluetoothDevice getDevice() {
        return this.mDevice;
    }

    public synchronized void destroy() {
        if (this.DBG) {
            ZLogger.d("destroy()");
        }
        this.mCallback = null;
    }

    public boolean isConnected() {
        return false;
    }

    public boolean isConnected(BluetoothDevice bluetoothDevice) {
        BluetoothDevice bluetoothDevice2 = this.mDevice;
        if (bluetoothDevice2 == null || !bluetoothDevice2.equals(bluetoothDevice)) {
            return false;
        }
        return isConnected();
    }

    public int getConnectionState() {
        return this.a;
    }

    public synchronized void updateConnectionState(int i) {
        int i2 = this.a;
        if (i != i2) {
            ZLogger.v(String.format(Locale.US, "connection sate changed: %d -> %d", Integer.valueOf(i2), Integer.valueOf(i)));
        }
        this.a = i;
        BluetoothClientCallback bluetoothClientCallback = this.mCallback;
        if (bluetoothClientCallback != null) {
            bluetoothClientCallback.onConnectionStateChanged(this, true, i);
        } else {
            ZLogger.v("no channel callback");
        }
    }

    public void dispatchDataReceived(byte[] bArr) {
        BluetoothClientCallback bluetoothClientCallback = this.mCallback;
        if (bluetoothClientCallback != null) {
            bluetoothClientCallback.onDataReceive(this, bArr);
        }
    }

    public void dispatchError(int i) {
        BluetoothClientCallback bluetoothClientCallback = this.mCallback;
        if (bluetoothClientCallback != null) {
            bluetoothClientCallback.onError(i);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [android.bluetooth.BluetoothAdapter] */
    /* JADX WARN: Type inference failed for: r0v8, types: [android.bluetooth.BluetoothDevice] */
    public final BluetoothDevice a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter == null) {
            ZLogger.w("mBluetoothAdapter == null");
            return null;
        }
        Object remoteDevice = bluetoothAdapter;
        BluetoothDevice bluetoothDevice = null;
        try {
            remoteDevice = remoteDevice.getRemoteDevice(str);
            bluetoothDevice = remoteDevice;
        } catch (Exception unused) {
            ZLogger.w(remoteDevice.toString());
        }
        return bluetoothDevice;
    }
}
