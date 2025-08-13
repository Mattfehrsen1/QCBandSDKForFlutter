package com.realsil.sdk.core.bluetooth.connection.le;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.work.WorkRequest;
import com.realsil.sdk.core.bluetooth.BluetoothProfileManager;
import com.realsil.sdk.core.bluetooth.GlobalGatt;
import com.realsil.sdk.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothGattImpl;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.c.a;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

/* loaded from: classes3.dex */
public class GattClient extends a {
    public static final int STAGE_BACK_CONNECT = 2048;
    public static final int STAGE_IDLE = 0;
    public static final int STAGE_INIT = 256;
    public static final int STAGE_OTA = 1024;
    public static final int STAGE_PREPARE = 512;
    public static final int STATE_ABORTED = 8193;
    public static final int STATE_CONNECT_FAILED = 4098;
    public static final int STATE_DISCONNECTED = 4097;
    public static final int STATE_DISCONNECTING = 4096;
    public static final int STATE_DISCOVERY_SERVICE = 537;
    public static final int STATE_MASK_BUSY = 16;
    public static final int STATE_MASK_IDLE = 0;
    public static final int STATE_PENDDING_DISCOVERY_SERVICE = 536;
    public static final int STATE_PENDING_ABORT = 8192;
    public static final int STATE_PREPARED = 527;
    public static final int STATE_PREPARE_CONNECTING = 535;
    public static final int STATE_PREPARE_PAIRING_REQUEST = 534;
    public static final int STATE_READ_BATTERY_INFO = 542;
    public static final int STATE_READ_DEVICE_INFO = 539;
    public static final int STATE_READ_IMAGE_INFO = 541;
    public static final int STATE_READ_PROTOCOL_TYPE = 540;
    public BluetoothGatt i;
    public GattClientCallback j;
    public BluetoothDevice k;
    public String m;
    public ConnectParams n;
    public int l = 10;
    public int o = 0;
    public BluetoothGattCharacteristic p = null;
    public BluetoothGattCharacteristic q = null;
    public Object r = new Object();
    public Object s = new Object();
    public boolean t = true;
    public Runnable u = new Runnable() { // from class: com.realsil.sdk.core.bluetooth.connection.le.GattClient.1
        @Override // java.lang.Runnable
        public void run() throws InterruptedException {
            GattClient gattClient = GattClient.this;
            synchronized (gattClient.g) {
                try {
                    if (gattClient.b) {
                        ZLogger.v("waitSyncLock");
                    }
                    gattClient.g.wait(15000L);
                } catch (InterruptedException e) {
                    ZLogger.d(gattClient.a, "wait sync data interrupted: " + e.toString());
                }
            }
            try {
                Thread.sleep(800L);
            } catch (InterruptedException e2) {
                ZLogger.w(e2.toString());
            }
            GattClient.a(GattClient.this);
        }
    };
    public Runnable v = new Runnable() { // from class: com.realsil.sdk.core.bluetooth.connection.le.GattClient.2
        @Override // java.lang.Runnable
        public void run() throws InterruptedException {
            GattClient.a(GattClient.this);
        }
    };
    public Runnable w = new Runnable() { // from class: com.realsil.sdk.core.bluetooth.connection.le.GattClient.3
        @Override // java.lang.Runnable
        public void run() {
            GattClient gattClient = GattClient.this;
            if (gattClient.o != 536) {
                StringBuilder sbA = com.realsil.sdk.core.a.a.a("ignore state:");
                sbA.append(GattClient.this.o);
                ZLogger.d(sbA.toString());
                return;
            }
            gattClient.l = gattClient.getBondState(gattClient.m);
            if (GattClient.this.l == 11) {
                ZLogger.v("BOND_BONDING: wait to discover service");
                new Thread(GattClient.this.u).start();
            } else {
                StringBuilder sbA2 = com.realsil.sdk.core.a.a.a(">> mBondState: ");
                sbA2.append(GattClient.this.l);
                ZLogger.v(sbA2.toString());
                new Thread(GattClient.this.v).start();
            }
        }
    };
    public Handler x = new Handler(Looper.getMainLooper());
    public final BluetoothGattCallback y = new BluetoothGattCallback() { // from class: com.realsil.sdk.core.bluetooth.connection.le.GattClient.4
        public final void a() {
            if (!GattClient.this.isPreparing()) {
                GattClient.this.c(4097);
            } else {
                GattClient.this.notifyLock();
                GattClient.this.b(2);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            GattClient gattClient = GattClient.this;
            GattClientCallback gattClientCallback = gattClient.j;
            if (gattClientCallback != null) {
                gattClientCallback.onDataReceived(bluetoothGattCharacteristic);
            } else {
                ZLogger.v(gattClient.b, "no callback registered");
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            GattClient gattClient = GattClient.this;
            BluetoothGattCharacteristic bluetoothGattCharacteristic2 = gattClient.q;
            if (bluetoothGattCharacteristic2 == null || bluetoothGattCharacteristic2.getUuid() != bluetoothGattCharacteristic.getUuid()) {
                StringBuilder sbA = com.realsil.sdk.core.a.a.a("ignore ");
                sbA.append(bluetoothGattCharacteristic.getUuid());
                ZLogger.v(sbA.toString());
            } else {
                GattClientCallback gattClientCallback = gattClient.j;
                if (gattClientCallback != null) {
                    gattClientCallback.onCharacteristicRead(bluetoothGattCharacteristic, i);
                } else {
                    ZLogger.v(gattClient.b, "no callback registered");
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            GattClient gattClient = GattClient.this;
            BluetoothGattCharacteristic bluetoothGattCharacteristic2 = gattClient.p;
            if (bluetoothGattCharacteristic2 == null || bluetoothGattCharacteristic2.getUuid() != bluetoothGattCharacteristic.getUuid()) {
                StringBuilder sbA = com.realsil.sdk.core.a.a.a("ignore ");
                sbA.append(bluetoothGattCharacteristic.getUuid());
                ZLogger.v(sbA.toString());
            } else {
                GattClientCallback gattClientCallback = gattClient.j;
                if (gattClientCallback != null) {
                    gattClientCallback.onCharacteristicWrite(bluetoothGattCharacteristic, i);
                } else {
                    ZLogger.v(gattClient.b, "no callback registered");
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) throws InterruptedException {
            if (i != 0) {
                a();
                return;
            }
            if (i2 != 2) {
                if (i2 == 0) {
                    GattClient gattClient = GattClient.this;
                    synchronized (gattClient.r) {
                        if (gattClient.b) {
                            ZLogger.v("triggleSyncLock");
                        }
                        gattClient.r.notifyAll();
                    }
                    GattClient.this.notifyLock();
                    GattClient.this.disconnect();
                    a();
                    return;
                }
                return;
            }
            GattClient gattClient2 = GattClient.this;
            gattClient2.i = gattClient2.f.getBluetoothGatt(gattClient2.m);
            ConnectParams connectParams = GattClient.this.n;
            if (connectParams != null && connectParams.isRefreshCache()) {
                BluetoothGattImpl.refresh(bluetoothGatt);
            }
            if (bluetoothGatt == null) {
                a();
                return;
            }
            GattClient gattClient3 = GattClient.this;
            if (gattClient3.o != 536) {
                gattClient3.c(GattClient.STATE_PENDDING_DISCOVERY_SERVICE);
                if (GattClient.this.x == null) {
                    ZLogger.v(true, "mHandler == null");
                    return;
                }
                ZLogger.v("delay to discover service for : 1600");
                GattClient gattClient4 = GattClient.this;
                gattClient4.x.removeCallbacks(gattClient4.w);
                GattClient gattClient5 = GattClient.this;
                boolean zPostDelayed = gattClient5.x.postDelayed(gattClient5.w, 1600L);
                if (zPostDelayed) {
                    return;
                }
                ZLogger.v(true, "postDelayed:" + zPostDelayed);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            try {
                ZLogger.v(GattClient.this.b, "onDescriptorWrite: " + i);
                synchronized (GattClient.this.s) {
                    GattClient gattClient = GattClient.this;
                    gattClient.t = true;
                    gattClient.s.notifyAll();
                }
            } catch (Exception e) {
                ZLogger.w(e.toString());
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            if (i != 0) {
                ZLogger.w("service discovery failed !!!");
                if (GattClient.this.isPreparing()) {
                    GattClient.this.notifyLock();
                    GattClient.this.b(3);
                    return;
                }
                return;
            }
            GattClient.this.getClass();
            GattClient gattClient = GattClient.this;
            if (gattClient.o != 537) {
                gattClient.c(GattClient.STATE_READ_DEVICE_INFO);
            } else {
                gattClient.c(GattClient.STATE_READ_DEVICE_INFO);
                GattClient.this.notifyLock();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic, bArr);
            GattClient gattClient = GattClient.this;
            GattClientCallback gattClientCallback = gattClient.j;
            if (gattClientCallback != null) {
                gattClientCallback.onDataReceived(bluetoothGattCharacteristic);
            } else {
                ZLogger.v(gattClient.b, "no callback registered");
            }
        }
    };

    public static final class ConnectParams {
        public static final int MAX_RECONNECT_TIMES = 2;
        public String a;
        public boolean b;
        public boolean c;
        public int d;
        public int e;
        public boolean f;

        public static class Builder {
            public String a;
            public boolean b;
            public boolean c;
            public int d = 1;
            public int e = 2;
            public boolean f;

            public Builder address(String str) {
                this.a = str;
                return this;
            }

            public ConnectParams build() {
                return new ConnectParams(this.a, this.b, this.c, this.d, this.e, this.f);
            }

            public Builder createBond(boolean z) {
                this.b = z;
                return this;
            }

            public Builder hid(boolean z) {
                this.c = z;
                return this;
            }

            public Builder reconnectTimes(int i) {
                this.d = i;
                return this;
            }

            public Builder refreshCache(boolean z) {
                this.f = z;
                return this;
            }

            public Builder transport(int i) {
                this.e = i;
                return this;
            }
        }

        public ConnectParams(String str, boolean z, boolean z2, int i, int i2, boolean z3) {
            this.a = str;
            this.b = z;
            this.c = z2;
            this.d = i;
            this.e = i2;
            this.f = z3;
        }

        public String getAddress() {
            return this.a;
        }

        public int getReconnectTimes() {
            return this.d;
        }

        public int getTransport() {
            return this.e;
        }

        public boolean isCreateBond() {
            return this.b;
        }

        public boolean isHid() {
            return this.c;
        }

        public boolean isRefreshCache() {
            return this.f;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ConnectParams{\n");
            sb.append(String.format("\n\taddress=%s， isHid=%b", BluetoothHelper.formatAddress(this.a, true), Boolean.valueOf(this.c)));
            Locale locale = Locale.US;
            sb.append(String.format(locale, "\n\ttransport=%d", Integer.valueOf(this.e)));
            sb.append(String.format("\n\trefreshCache=%b\n", Boolean.valueOf(this.f)));
            sb.append(String.format(locale, "\n\treconnectTimes=%d", Integer.valueOf(this.d)));
            sb.append("\n}");
            return sb.toString();
        }
    }

    public static class ConnectState {
        public static final int COMPLETED = 15;
        public static final int CONNECTING = 23;
        public static final int CONNECT_PROILE = 17;
        public static final int CONNECT_PROILE_A2DP = 18;
        public static final int CONNECT_PROILE_HFP = 19;
        public static final int DISCOVERY_SERVICE = 25;
        public static final int ERROR = 14;
        public static final int PAIRING_REQUEST = 22;
        public static final int PENDING_CREATE_BOND = 20;
        public static final int PENDING_DISCOVERY_SERVICE = 24;
        public static final int PENDING_REMOVE_BOND = 21;
        public static final int READ_BATTERY_INFO = 30;
        public static final int READ_DEVICE_INFO = 27;
        public static final int READ_IMAGE_INFO = 29;
        public static final int READ_PROTOCOL_TYPE = 28;
        public static final int SYNC_DATA = 26;
    }

    public static abstract class GattClientCallback {
        public static final int CODE_BT_OFF = 1;
        public static final int CODE_CONNECT_FAIL = 2;
        public static final int CODE_DISCOVERY_SERVICE_FAIL = 3;

        public void onCharacteristicRead(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        }

        public void onCharacteristicWrite(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        }

        public void onDataReceived(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        }

        public void onError(int i) {
        }

        public void onNotificationStatusChanged(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        }

        public void onStateChanged(int i) {
        }
    }

    public GattClient(Context context, GattClientCallback gattClientCallback) {
        this.mContext = context;
        this.j = gattClientCallback;
        a();
    }

    public final boolean a(String str) {
        c(STATE_PREPARE_CONNECTING);
        return this.f.connect(str, this.y);
    }

    public final void b(int i) {
        ZLogger.v(String.format("notifyError >> 0x%04X", Integer.valueOf(i)));
        GattClientCallback gattClientCallback = this.j;
        if (gattClientCallback != null) {
            gattClientCallback.onError(i);
        } else {
            ZLogger.v(this.b, "no callback registered");
        }
    }

    public final void c(int i) {
        int i2 = this.o;
        if (i != i2) {
            ZLogger.v(String.format("DFU 0x%04X >> 0x%04X", Integer.valueOf(i2), Integer.valueOf(i)));
        }
        this.o = i;
        GattClientCallback gattClientCallback = this.j;
        if (gattClientCallback != null) {
            gattClientCallback.onStateChanged(i);
        } else {
            ZLogger.v(this.b, "no callback registered");
        }
    }

    public boolean checkStage(int i) {
        return (this.o & i) == i;
    }

    public boolean checkState(int i, int i2) {
        return this.o == (i | i2);
    }

    public boolean connectDevice(ConnectParams connectParams) throws InterruptedException {
        boolean zA;
        if (connectParams == null) {
            ZLogger.w("ConnectParams can not be null");
            return false;
        }
        if (this.n.getAddress() == null) {
            ZLogger.w("address is null");
            return false;
        }
        this.n = connectParams;
        connectParams.getReconnectTimes();
        ZLogger.v("mConnectParams:" + this.n.toString());
        String str = this.m;
        if (str != null) {
            if (Build.VERSION.SDK_INT >= 19) {
                if (!Objects.equals(str, this.n.getAddress())) {
                    this.f.unRegisterCallback(this.m, this.y);
                    this.f.close(this.m);
                }
            } else if (!equals(str, this.n.getAddress())) {
                this.f.unRegisterCallback(this.m, this.y);
                this.f.close(this.m);
            }
        }
        this.k = getRemoteDevice(this.n.getAddress());
        String address = this.n.getAddress();
        this.m = address;
        int bondState = getBondState(address);
        this.l = bondState;
        ZLogger.v(true, String.format(Locale.US, ">> mBondState: %d", Integer.valueOf(bondState)));
        if (!this.n.isHid() || !(zA = a(getRemoteDevice(this.m)))) {
            zA = a(this.m);
        }
        if (!zA) {
            c(4098);
        }
        return zA;
    }

    @Override // com.realsil.sdk.core.c.a
    public void destroy() throws InterruptedException {
        super.destroy();
        this.j = null;
        disconnect();
        this.o = 0;
        this.n = null;
        this.k = null;
        this.m = null;
        GlobalGatt globalGatt = this.f;
        if (globalGatt != null) {
            globalGatt.unRegisterCallback(null, this.y);
        }
    }

    public void disconnect() throws InterruptedException {
        if (this.m == null) {
            ZLogger.d("no device registered");
            c(4097);
        } else if (!isConnected()) {
            ZLogger.v("already disconnected");
            c(4097);
        } else if (this.f.isCallbackRegisted(this.m, this.y)) {
            c(4096);
            this.f.close(this.m);
        } else {
            ZLogger.v(this.a, "no gatt callback registered");
            c(4097);
        }
        this.i = null;
    }

    public BluetoothGattService getBluetoothGattService(String str, UUID uuid) {
        GlobalGatt globalGatt = this.f;
        if (globalGatt == null) {
            return null;
        }
        return globalGatt.getService(str, uuid);
    }

    public List<BluetoothGattService> getBluetoothGattServices() {
        ArrayList arrayList = new ArrayList();
        BluetoothDevice bluetoothDevice = this.k;
        return bluetoothDevice == null ? arrayList : this.f.getSupportedGattServices(bluetoothDevice.getAddress());
    }

    public int getConnectionState() {
        GlobalGatt globalGatt = this.f;
        if (globalGatt == null) {
            return 0;
        }
        return globalGatt.getConnectionState(this.m);
    }

    public int getState() {
        return this.o;
    }

    public boolean isBackConnecting() {
        return (this.o & 2048) == 2048;
    }

    public boolean isBusy() {
        return (this.o & 16) == 16;
    }

    public boolean isConnected() {
        GlobalGatt globalGatt = this.f;
        if (globalGatt == null) {
            return false;
        }
        return globalGatt.isConnected(this.m);
    }

    public boolean isPreparing() {
        int i = this.o;
        return (i & 512) == 512 && i != 527;
    }

    public boolean isStageBusy(int i) {
        int i2 = this.o;
        return (i2 & i) == i && (i2 & 16) == 16;
    }

    public boolean readCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.i == null) {
            ZLogger.w("mBtGatt is null maybe disconnected just now");
            return false;
        }
        if (this.a) {
            ZLogger.v(String.format(Locale.US, "readCharacteristic:(%d) %s", Integer.valueOf(bluetoothGattCharacteristic.getInstanceId()), bluetoothGattCharacteristic.getUuid().toString()));
        }
        this.q = bluetoothGattCharacteristic;
        if (this.i.readCharacteristic(bluetoothGattCharacteristic)) {
            return true;
        }
        this.q = null;
        ZLogger.w("readCharacteristic failed");
        return false;
    }

    public boolean setCharacteristicNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        if (this.i == null) {
            ZLogger.w("mBtGatt is null maybe disconnected just now");
            return false;
        }
        if ((bluetoothGattCharacteristic.getProperties() & 16) == 0) {
            ZLogger.w("check properties failed: $properties");
            this.t = false;
            return false;
        }
        if (this.a) {
            StringBuilder sbA = com.realsil.sdk.core.a.a.a("setCharacteristicNotification() - uuid: ");
            sbA.append(bluetoothGattCharacteristic.getUuid());
            sbA.append(" enabled: ");
            sbA.append(z);
            ZLogger.v(sbA.toString());
        } else {
            ZLogger.v("setCharacteristicNotification()  enabled: $enabled");
        }
        this.i.setCharacteristicNotification(bluetoothGattCharacteristic, z);
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(a.CLIENT_CHARACTERISTIC_CONFIG);
        if (descriptor != null) {
            boolean z2 = descriptor.getValue() != null && descriptor.getValue().length == 2 && descriptor.getValue()[0] > 0 && descriptor.getValue()[1] == 0;
            ZLogger.v(true, "current cccd state: " + z2);
            if (z && z2) {
                this.t = true;
                ZLogger.w("cccd already enabled");
                return true;
            }
            if (!z && !z2) {
                ZLogger.w("cccd already disable");
                this.t = true;
                return true;
            }
            descriptor.setValue(z ? BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
            if (this.i.writeDescriptor(descriptor)) {
                synchronized (this.s) {
                    ZLogger.d(this.a, "wait write Characteristic Notification 30000ms");
                    try {
                        this.t = false;
                        this.s.wait(WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS);
                    } catch (InterruptedException e) {
                        ZLogger.w("wait writeDescriptor interrupted: " + e.toString());
                    }
                }
                return this.t;
            }
        }
        return false;
    }

    public boolean writeCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.i == null) {
            ZLogger.w("mBtGatt is null maybe disconnected just now");
            return false;
        }
        if (this.a) {
            ZLogger.v(String.format(Locale.US, "readCharacteristic:(%d) %s", Integer.valueOf(bluetoothGattCharacteristic.getInstanceId()), bluetoothGattCharacteristic.getUuid().toString()));
        }
        this.p = bluetoothGattCharacteristic;
        if (this.i.writeCharacteristic(bluetoothGattCharacteristic)) {
            return true;
        }
        this.p = null;
        ZLogger.w("readCharacteristic failed");
        return false;
    }

    public final boolean a(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        if (!BluetoothProfileManager.getInstance().isProfileSupported(4)) {
            ZLogger.w("HID_HOST not supported");
            return false;
        }
        int bondState = bluetoothDevice.getBondState();
        if (bondState != 12) {
            ZLogger.d(true, "connect with not bond device, bond first, current state: " + bondState);
            ZLogger.v(String.format("notifyStateChanged 0x%04X >> 0x%04X", Integer.valueOf(this.o), 532));
            this.o = 532;
            GattClientCallback gattClientCallback = this.j;
            if (gattClientCallback != null) {
                gattClientCallback.onStateChanged(532);
            } else {
                ZLogger.v(this.b, "no callback registered");
            }
            return bluetoothDevice.createBond();
        }
        if (isHogpConnect(bluetoothDevice.getAddress())) {
            ZLogger.d("hogp already connected");
            return a(bluetoothDevice.getAddress());
        }
        if (BluetoothDeviceImpl.removeBond(bluetoothDevice)) {
            ZLogger.d("remove bond first");
            c(533);
            return false;
        }
        ZLogger.d("remove bond failed");
        c(529);
        return BluetoothProfileManager.getInstance().connectHid(bluetoothDevice);
    }

    public BluetoothGattService getBluetoothGattService(UUID uuid) {
        BluetoothDevice bluetoothDevice;
        GlobalGatt globalGatt = this.f;
        if (globalGatt == null || (bluetoothDevice = this.k) == null) {
            return null;
        }
        return globalGatt.getService(bluetoothDevice.getAddress(), uuid);
    }

    public static boolean a(GattClient gattClient) throws InterruptedException {
        boolean zDiscoverServices;
        if (gattClient.o == 537) {
            ZLogger.w("discoverServices already started");
            return false;
        }
        gattClient.c(STATE_DISCOVERY_SERVICE);
        ZLogger.v(gattClient.b, "discoverServices...");
        BluetoothGatt bluetoothGatt = gattClient.i;
        if (bluetoothGatt != null) {
            zDiscoverServices = bluetoothGatt.discoverServices();
        } else {
            ZLogger.d("mBtGatt is null");
            zDiscoverServices = false;
        }
        if (!zDiscoverServices) {
            ZLogger.d(gattClient.a, "discoverServices failed");
            if (!gattClient.isPreparing()) {
                return false;
            }
            gattClient.b(3);
            return false;
        }
        synchronized (gattClient.g) {
            try {
                ZLogger.v(gattClient.b, "wait discover service complete");
                gattClient.g.wait(WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS);
            } catch (InterruptedException e) {
                ZLogger.d(gattClient.a, e.toString());
            }
        }
        if (gattClient.o != 537) {
            return true;
        }
        ZLogger.w("discoverServices timeout");
        gattClient.disconnect();
        return false;
    }

    @Override // com.realsil.sdk.core.c.a
    public final void a(int i) {
        switch (i) {
            case 10:
                ZLogger.v(true, "BOND_NONE");
                if (this.o == 533 && this.k != null) {
                    ZLogger.v(true, "createBond");
                    this.k.createBond();
                    break;
                }
                break;
            case 11:
                ZLogger.v(true, "BOND_BONDING");
                break;
            case 12:
                ZLogger.v(true, "BOND_BONDED");
                if (this.o == 532) {
                    if (this.k != null) {
                        if (!isHogpConnect(this.m)) {
                            ZLogger.v(this.b, "hid not connect");
                            c(529);
                            BluetoothProfileManager.getInstance().connectHid(this.k);
                            break;
                        } else {
                            ZLogger.v(this.b, "hid already connected");
                            a(this.m);
                            break;
                        }
                    }
                } else {
                    notifyLock();
                    break;
                }
                break;
        }
    }
}
