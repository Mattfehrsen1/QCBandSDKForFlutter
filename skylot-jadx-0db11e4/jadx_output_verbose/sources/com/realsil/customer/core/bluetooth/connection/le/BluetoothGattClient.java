package com.realsil.customer.core.bluetooth.connection.le;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import androidx.annotation.Keep;
import com.realsil.customer.core.bluetooth.GlobalGatt;
import com.realsil.customer.core.bluetooth.RtkBluetoothManager;
import com.realsil.customer.core.bluetooth.RtkBluetoothManagerCallback;
import com.realsil.customer.core.bluetooth.compat.BluetoothGattCompat;
import com.realsil.customer.core.bluetooth.connection.BluetoothClient;
import com.realsil.customer.core.bluetooth.utils.BluetoothHelper;
import com.realsil.customer.core.logger.ZLogger;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/connection/le/BluetoothGattClient.class */
public class BluetoothGattClient extends BluetoothClient {
    public static final UUID CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID = UUID.fromString(com.realsil.customer.core.b.a.CLIENT_CHARACTERISTIC_CONFIG);

    @Keep
    protected GlobalGatt mGlobalGatt;

    @Keep
    protected BluetoothGatt mBluetoothGatt;

    @Keep
    protected GattConnParams mGattConnParams;
    public RtkBluetoothManager e;
    public boolean b = false;

    @Keep
    protected String mDeviceAddress = null;
    public final int c = 2;
    public boolean d = true;
    public final Object f = new Object();
    public final a g = new a();

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/connection/le/BluetoothGattClient$a.class */
    public class a extends RtkBluetoothManagerCallback {
        public a() {
        }

        @Override // com.realsil.customer.core.bluetooth.RtkBluetoothManagerCallback
        public final void onBondStateChanged(BluetoothDevice bluetoothDevice, int i) {
            super.onBondStateChanged(bluetoothDevice, i);
            BluetoothGattClient.this.processBondStateChanged(bluetoothDevice, i);
        }
    }

    @Override // com.realsil.customer.core.bluetooth.connection.BluetoothClient
    public final void a() {
        super.a();
        RtkBluetoothManager rtkBluetoothManager = RtkBluetoothManager.getInstance();
        this.e = rtkBluetoothManager;
        if (rtkBluetoothManager == null) {
            RtkBluetoothManager.initial(this.mContext);
            this.e = RtkBluetoothManager.getInstance();
        }
        RtkBluetoothManager rtkBluetoothManager2 = this.e;
        if (rtkBluetoothManager2 != null) {
            rtkBluetoothManager2.addManagerCallback(this.g);
        } else {
            ZLogger.w(this.DBG, "BluetoothProfileManager not initialized");
        }
        GlobalGatt globalGatt = GlobalGatt.getInstance();
        this.mGlobalGatt = globalGatt;
        if (globalGatt == null) {
            GlobalGatt.initial(this.mContext);
            this.mGlobalGatt = GlobalGatt.getInstance();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.realsil.customer.core.bluetooth.connection.BluetoothClient, com.realsil.customer.core.bluetooth.connection.le.BluetoothGattClient] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    public void notifyConnLock() {
        try {
            ?? r0 = this;
            Object obj = r0.f;
            synchronized (obj) {
                if (r0.VDBG) {
                    ZLogger.v("notifyConnLock");
                }
                r0 = obj;
                this.f.notifyAll();
            }
        } catch (Exception e) {
            if (this.DBG) {
                ZLogger.w(e.toString());
            }
        }
    }

    public boolean connect(GattConnParams gattConnParams) {
        if (gattConnParams == null) {
            ZLogger.w("connectParams can not be null");
            return false;
        }
        if (gattConnParams.getAddress() != null) {
            return true;
        }
        ZLogger.w("address is null");
        return false;
    }

    public void disconnect() {
        if (this.DBG) {
            ZLogger.d("disconnect()");
        }
        this.d = true;
        notifyConnLock();
    }

    @Override // com.realsil.customer.core.bluetooth.connection.BluetoothClient
    public synchronized void destroy() {
        super.destroy();
        ZLogger.v(this.DBG, "destroy");
        RtkBluetoothManager rtkBluetoothManager = this.e;
        if (rtkBluetoothManager != null) {
            rtkBluetoothManager.removeManagerCallback(this.g);
        }
    }

    @Override // com.realsil.customer.core.bluetooth.connection.BluetoothClient
    public boolean isConnected() {
        GlobalGatt globalGatt = this.mGlobalGatt;
        if (globalGatt == null) {
            return false;
        }
        return globalGatt.isConnected(this.mDeviceAddress);
    }

    public boolean setCharacteristicNotification(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        int properties = bluetoothGattCharacteristic.getProperties();
        if ((properties & 16) == 0) {
            ZLogger.w("check properties failed: " + properties);
            dispatchError(4);
            return false;
        }
        if (this.DBG) {
            ZLogger.v(String.format("setCharacteristicNotification(%s) - uuid:%s, enabled:%b ", BluetoothHelper.formatAddress(bluetoothGatt.getDevice().getAddress(), true), bluetoothGattCharacteristic.getUuid(), Boolean.valueOf(z)));
        } else {
            ZLogger.v("setCharacteristicNotification() enabled: " + z);
        }
        if (!bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z)) {
            ZLogger.w("setCharacteristicNotification failed");
            dispatchError(4);
            return false;
        }
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID);
        if (descriptor == null) {
            ZLogger.w("no descriptor exist");
            dispatchError(4);
            return false;
        }
        boolean z2 = descriptor.getValue() != null && descriptor.getValue().length == 2 && descriptor.getValue()[0] > 0 && descriptor.getValue()[1] == 0;
        ZLogger.v(this.DBG, "current cccd state: " + z2);
        if (z && z2) {
            this.b = true;
            ZLogger.w("cccd already enabled");
            return true;
        }
        if (!z && !z2) {
            this.b = true;
            ZLogger.w("cccd already disable");
            return true;
        }
        if (!BluetoothGattCompat.writeDescriptor(bluetoothGatt, descriptor, z ? BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE)) {
            ZLogger.w("writeDescriptor failed");
            dispatchError(5);
            return false;
        }
        if (this.b) {
            return true;
        }
        a(5000L);
        if (this.b || this.d) {
            return true;
        }
        ZLogger.w("cccd timeout");
        dispatchError(6);
        return false;
    }

    public void notifyCccdEnabled(boolean z) {
        this.b = z;
        notifyConnLock();
    }

    public void processBondStateChanged(BluetoothDevice bluetoothDevice, int i) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.realsil.customer.core.bluetooth.connection.BluetoothClient, com.realsil.customer.core.bluetooth.connection.le.BluetoothGattClient] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Object] */
    public final void a(long j) {
        ?? r0 = this;
        Object obj = r0.f;
        synchronized (obj) {
            try {
                if (r0.VDBG) {
                    ZLogger.v("waitConnLock");
                }
                r0 = this.f;
                r0.wait(j);
            } catch (InterruptedException unused) {
                if (this.VDBG) {
                    ZLogger.v("wait conn lock interrupted: ");
                }
            }
            r0 = obj;
        }
    }
}
