package com.realsil.customer.core.bluetooth.connection.le;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.Context;
import androidx.annotation.NonNull;
import com.realsil.customer.core.bluetooth.connection.BluetoothClient;
import com.realsil.customer.core.bluetooth.utils.BluetoothHelper;
import com.realsil.customer.core.logger.ZLogger;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/connection/le/BluetoothGattClientImpl.class */
public abstract class BluetoothGattClientImpl extends BluetoothGattClient {
    public ThreadPoolExecutor h;
    public boolean i = true;
    public boolean j = false;
    public boolean k = false;
    public boolean l = false;
    public final Runnable m = new Runnable() { // from class: com.realsil.customer.core.bluetooth.connection.le.BluetoothGattClientImpl.1
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:54:0x01ad  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x0245  */
        /* JADX WARN: Type inference failed for: r0v103 */
        /* JADX WARN: Type inference failed for: r0v104, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v107, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v123 */
        /* JADX WARN: Type inference failed for: r0v124 */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 817
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.realsil.customer.core.bluetooth.connection.le.BluetoothGattClientImpl.AnonymousClass1.run():void");
        }
    };
    public final BluetoothGattCallbackCompat n = new BluetoothGattCallbackCompat() { // from class: com.realsil.customer.core.bluetooth.connection.le.BluetoothGattClientImpl.2
        @Override // com.realsil.customer.core.bluetooth.connection.le.BluetoothGattCallbackCompat
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            bluetoothGatt.getDevice();
            if (i != 0) {
                if (((BluetoothClient) BluetoothGattClientImpl.this).DBG) {
                    ZLogger.w("status " + i + " newState: " + i2);
                }
                BluetoothGattClientImpl bluetoothGattClientImpl = BluetoothGattClientImpl.this;
                if (bluetoothGattClientImpl.j || bluetoothGattClientImpl.i) {
                    ZLogger.v(String.format("connectionEstablishProcessing=%b, connectionEstablished=%b", Boolean.valueOf(bluetoothGattClientImpl.i), Boolean.valueOf(BluetoothGattClientImpl.this.j)));
                    BluetoothGattClientImpl bluetoothGattClientImpl2 = BluetoothGattClientImpl.this;
                    bluetoothGattClientImpl2.j = false;
                    bluetoothGattClientImpl2.i = false;
                    bluetoothGattClientImpl2.closeGatt();
                }
                ZLogger.v(((BluetoothClient) BluetoothGattClientImpl.this).VDBG, String.format("connectionEstablishProcessing=%b, connectionEstablished=%b", Boolean.valueOf(BluetoothGattClientImpl.this.i), Boolean.valueOf(BluetoothGattClientImpl.this.j)));
                BluetoothGattClientImpl.this.updateConnectionState(0);
                return;
            }
            if (i2 == 2) {
                if (((BluetoothClient) BluetoothGattClientImpl.this).DBG) {
                    ZLogger.d("Connected to GATT server.");
                }
                BluetoothGattClientImpl bluetoothGattClientImpl3 = BluetoothGattClientImpl.this;
                bluetoothGattClientImpl3.mBluetoothGatt = bluetoothGatt;
                bluetoothGattClientImpl3.j = true;
                bluetoothGattClientImpl3.i = false;
                ZLogger.v(((BluetoothClient) bluetoothGattClientImpl3).VDBG, String.format("connectionEstablishProcessing=%b, connectionEstablished=%b", Boolean.valueOf(BluetoothGattClientImpl.this.i), Boolean.valueOf(BluetoothGattClientImpl.this.j)));
                BluetoothGattClientImpl.this.notifyConnLock();
                return;
            }
            if (i2 == 0) {
                if (((BluetoothClient) BluetoothGattClientImpl.this).DBG) {
                    ZLogger.w("Disconnected from GATT server.");
                }
                BluetoothGattClientImpl bluetoothGattClientImpl4 = BluetoothGattClientImpl.this;
                if (bluetoothGattClientImpl4.j || bluetoothGattClientImpl4.i) {
                    ZLogger.v(String.format("connectionEstablishProcessing=%b, connectionEstablished=%b", Boolean.valueOf(bluetoothGattClientImpl4.i), Boolean.valueOf(BluetoothGattClientImpl.this.j)));
                    BluetoothGattClientImpl bluetoothGattClientImpl5 = BluetoothGattClientImpl.this;
                    bluetoothGattClientImpl5.j = false;
                    bluetoothGattClientImpl5.i = false;
                    bluetoothGattClientImpl5.closeGatt();
                }
                ZLogger.v(((BluetoothClient) BluetoothGattClientImpl.this).VDBG, String.format("connectionEstablishProcessing=%b, connectionEstablished=%b", Boolean.valueOf(BluetoothGattClientImpl.this.i), Boolean.valueOf(BluetoothGattClientImpl.this.j)));
                BluetoothGattClientImpl.this.updateConnectionState(0);
            }
        }

        @Override // com.realsil.customer.core.bluetooth.connection.le.BluetoothGattCallbackCompat
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            if (i != 0) {
                if (((BluetoothClient) BluetoothGattClientImpl.this).DBG) {
                    ZLogger.w("onServicesDiscovered failed: " + i);
                }
                BluetoothGattClientImpl bluetoothGattClientImpl = BluetoothGattClientImpl.this;
                bluetoothGattClientImpl.k = false;
                bluetoothGattClientImpl.notifyConnLock();
                BluetoothGattClientImpl.this.disconnect();
                return;
            }
            if (((BluetoothClient) BluetoothGattClientImpl.this).DBG) {
                ZLogger.v(BluetoothHelper.dumpBluetoothGattService(bluetoothGatt));
            }
            BluetoothGattClientImpl bluetoothGattClientImpl2 = BluetoothGattClientImpl.this;
            bluetoothGattClientImpl2.k = true;
            if (!bluetoothGattClientImpl2.processServices(bluetoothGatt)) {
                BluetoothGattClientImpl.this.disconnect();
            }
            BluetoothGattClientImpl.this.notifyConnLock();
        }

        @Override // com.realsil.customer.core.bluetooth.connection.le.BluetoothGattCallbackCompat
        public void onCharacteristicRead(@NonNull BluetoothGatt bluetoothGatt, @NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, @NonNull byte[] bArr, int i) {
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, bArr, i);
            BluetoothGattClientImpl.this.getClass();
        }

        @Override // com.realsil.customer.core.bluetooth.connection.le.BluetoothGattCallbackCompat
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            BluetoothGattClientImpl.this.getClass();
        }

        @Override // com.realsil.customer.core.bluetooth.connection.le.BluetoothGattCallbackCompat
        public void onCharacteristicChanged(@NonNull BluetoothGatt bluetoothGatt, @NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, @NonNull byte[] bArr) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic, bArr);
            BluetoothGattClientImpl.this.processCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic, bArr);
        }

        @Override // com.realsil.customer.core.bluetooth.connection.le.BluetoothGattCallbackCompat
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onMtuChanged(bluetoothGatt, i, i2);
            BluetoothGattClientImpl bluetoothGattClientImpl = BluetoothGattClientImpl.this;
            bluetoothGattClientImpl.l = true;
            bluetoothGattClientImpl.notifyConnLock();
        }

        @Override // com.realsil.customer.core.bluetooth.connection.le.BluetoothGattCallbackCompat
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            bluetoothGatt.getDevice();
            BluetoothGattClientImpl.this.processDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
        }
    };

    public BluetoothGattClientImpl(Context context, BluetoothGattClientCallback bluetoothGattClientCallback) {
        this.mContext = context;
        this.mCallback = bluetoothGattClientCallback;
        b();
    }

    @Override // com.realsil.customer.core.bluetooth.connection.le.BluetoothGattClient
    public boolean connect(GattConnParams gattConnParams) {
        if (!super.connect(gattConnParams)) {
            return false;
        }
        if (this.i) {
            ZLogger.d(this.DBG, "there ia already a connection is processing, wait to close");
            notifyConnLock();
        }
        if (this.DBG) {
            ZLogger.d(gattConnParams.toString());
        }
        this.mGattConnParams = gattConnParams;
        String address = gattConnParams.getAddress();
        this.mDeviceAddress = address;
        this.mDevice = a(address);
        this.h.execute(this.m);
        return true;
    }

    @Override // com.realsil.customer.core.bluetooth.connection.le.BluetoothGattClient
    public void disconnect() {
        super.disconnect();
        this.mGlobalGatt.disconnectGatt(this.mDeviceAddress);
        this.h.remove(this.m);
    }

    public void closeGatt() {
        if (this.DBG) {
            ZLogger.d("closeGatt()");
        }
        disconnect();
    }

    public void close() {
        if (this.DBG) {
            ZLogger.d("close()");
        }
        closeGatt();
        ThreadPoolExecutor threadPoolExecutor = this.h;
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdown();
        }
    }

    @Override // com.realsil.customer.core.bluetooth.connection.le.BluetoothGattClient, com.realsil.customer.core.bluetooth.connection.BluetoothClient
    public void destroy() {
        super.destroy();
        closeGatt();
        ThreadPoolExecutor threadPoolExecutor = this.h;
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdown();
        }
    }

    public boolean processServices(BluetoothGatt bluetoothGatt) {
        return true;
    }

    public boolean enableCccd() {
        ZLogger.v("enable notification");
        return true;
    }

    public void processCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, @NonNull byte[] bArr) {
    }

    public void processDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        if (i == 0) {
            notifyCccdEnabled(true);
        } else {
            notifyCccdEnabled(false);
        }
    }

    public int getConnectState() {
        return getConnectionState();
    }

    @Override // com.realsil.customer.core.bluetooth.connection.le.BluetoothGattClient
    public void processBondStateChanged(BluetoothDevice bluetoothDevice, int i) {
        BluetoothDevice bluetoothDevice2 = this.mDevice;
        if (bluetoothDevice2 == null || !bluetoothDevice2.equals(bluetoothDevice)) {
            ZLogger.v(this.VDBG, "bonded device not match with current device");
            return;
        }
        this.mBondState = i;
        switch (i) {
            case 10:
                ZLogger.v(this.VDBG, "BOND_NONE");
                if (this.a == 5) {
                    notifyConnLock();
                    break;
                }
                break;
            case 11:
                ZLogger.v(this.VDBG, "BOND_BONDING");
                break;
            case 12:
                ZLogger.v(this.VDBG, "BOND_BONDED");
                if (this.a == 5) {
                    notifyConnLock();
                    break;
                }
                break;
        }
    }

    public final void b() {
        a();
        this.h = new ThreadPoolExecutor(10, 10, 1000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadPoolExecutor.AbortPolicy());
        updateConnectionState(0);
    }
}
