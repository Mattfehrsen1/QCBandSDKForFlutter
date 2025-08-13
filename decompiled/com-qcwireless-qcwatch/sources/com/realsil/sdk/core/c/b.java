package com.realsil.sdk.core.c;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Build;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.bluetooth.RtkBluetoothManager;
import com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback;
import com.realsil.sdk.core.bluetooth.connection.le.GattError;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes3.dex */
public class b {
    public static boolean CLOSE_GATT_ENABLED = true;
    public static boolean DUMP_SERVICE = false;
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_DISCONNECTED = 0;
    public boolean a;
    public boolean b;
    public BluetoothManager c;
    public BluetoothAdapter d;
    public volatile boolean i;
    public Context k;
    public RtkBluetoothManager l;
    public static int SDK_INT = Build.VERSION.SDK_INT;
    public static final String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";
    public static final UUID CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID = UUID.fromString(CLIENT_CHARACTERISTIC_CONFIG);
    public final Object j = new Object();
    public final a m = new a();
    public HashMap<String, BluetoothGatt> f = new HashMap<>();
    public HashMap<String, Integer> h = new HashMap<>();
    public HashMap<String, List<BluetoothGattCallback>> g = new HashMap<>();
    public List<String> e = new CopyOnWriteArrayList();

    public class a extends RtkBluetoothManagerCallback {
        public a() {
        }

        @Override // com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback
        public final void onBluetoothStateChanged(int i) {
            super.onBluetoothStateChanged(i);
            b.a(b.this, i);
        }
    }

    public b(Context context) {
        this.a = false;
        this.b = false;
        this.k = context;
        this.a = RtkCore.DEBUG;
        this.b = RtkCore.VDBG;
        a();
    }

    /* JADX WARN: Type inference failed for: r6v2, types: [java.util.List<java.lang.String>, java.util.concurrent.CopyOnWriteArrayList] */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.util.List<java.lang.String>, java.util.concurrent.CopyOnWriteArrayList] */
    public static void a(b bVar, int i) {
        ?? r6;
        bVar.getClass();
        if (i != 10 || Build.VERSION.SDK_INT < 29 || (r6 = bVar.e) == 0 || r6.size() <= 0) {
            return;
        }
        ZLogger.d("Bluetooth is turned off, disconnect all client connections");
        Iterator it = bVar.e.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            BluetoothGatt bluetoothGatt = bVar.getBluetoothGatt(str);
            if (bVar.isConnected(str)) {
                bVar.h.put(str, 0);
                bVar.a(str, bluetoothGatt, 0, 0);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.List<java.lang.String>, java.util.concurrent.CopyOnWriteArrayList] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.util.List<java.lang.String>, java.util.concurrent.CopyOnWriteArrayList] */
    public void close(String str) throws InterruptedException {
        if (str == null) {
            return;
        }
        BluetoothGatt bluetoothGatt = this.f.get(str);
        if (bluetoothGatt != null) {
            if (isConnected(str)) {
                if (this.a) {
                    ZLogger.v("disconnect : " + str);
                }
                bluetoothGatt.disconnect();
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (CLOSE_GATT_ENABLED) {
                if (this.b) {
                    StringBuilder sbA = com.realsil.sdk.core.a.a.a("closeGatt, addr:=");
                    sbA.append(BluetoothHelper.formatAddress(str, true));
                    ZLogger.v(sbA.toString());
                }
                bluetoothGatt.close();
            }
            this.f.remove(str);
        }
        HashMap<String, List<BluetoothGattCallback>> map = this.g;
        if (map != null) {
            map.remove(str);
        }
        ?? r0 = this.e;
        if (r0 == 0 || !r0.contains(str)) {
            return;
        }
        this.e.remove(str);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List<java.lang.String>, java.util.concurrent.CopyOnWriteArrayList] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List<java.lang.String>, java.util.concurrent.CopyOnWriteArrayList] */
    public void closeAll() throws InterruptedException {
        ?? r0 = this.e;
        if (r0 != 0 && r0.size() > 0) {
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                close((String) it.next());
            }
        }
        RtkBluetoothManager rtkBluetoothManager = this.l;
        if (rtkBluetoothManager != null) {
            rtkBluetoothManager.removeManagerCallback(this.m);
        }
    }

    public synchronized void closeGatt(String str) {
        closeGatt(str, CLOSE_GATT_ENABLED);
    }

    public boolean connect(String str, BluetoothGattCallback bluetoothGattCallback) {
        return Build.VERSION.SDK_INT >= 23 ? connect(str, 2, bluetoothGattCallback) : connect(str, 2, bluetoothGattCallback);
    }

    public boolean disconnectGatt(String str) throws InterruptedException {
        BluetoothGatt bluetoothGatt = this.f.get(str);
        if (bluetoothGatt == null) {
            return false;
        }
        if (isConnected(str)) {
            if (this.a) {
                StringBuilder sbA = com.realsil.sdk.core.a.a.a("disconnect : ");
                sbA.append(BluetoothHelper.formatAddress(str, true));
                ZLogger.v(sbA.toString());
            }
            bluetoothGatt.disconnect();
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                ZLogger.w(e.toString());
            }
        } else {
            a(str, bluetoothGatt, 0, 0);
        }
        return true;
    }

    public BluetoothAdapter getBluetoothAdapter() {
        return this.d;
    }

    public List<String> getBluetoothDeviceAddresss() {
        return this.e;
    }

    public BluetoothGatt getBluetoothGatt(String str) {
        return this.f.get(str);
    }

    public List<BluetoothGattCallback> getCallback(String str) {
        HashMap<String, List<BluetoothGattCallback>> map = this.g;
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List<java.lang.String>, java.util.concurrent.CopyOnWriteArrayList] */
    public BluetoothDevice getConnectedDevice() {
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (isConnected(str)) {
                return getBluetoothGatt(str).getDevice();
            }
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.List<java.lang.String>, java.util.concurrent.CopyOnWriteArrayList] */
    public ArrayList<BluetoothDevice> getConnectedDevices() {
        ArrayList<BluetoothDevice> arrayList = new ArrayList<>();
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (isConnected(str)) {
                arrayList.add(getBluetoothGatt(str).getDevice());
            }
        }
        return arrayList;
    }

    public int getConnectionState(String str) {
        return this.h.get(str).intValue();
    }

    public String getDeviceName(String str) {
        BluetoothGatt bluetoothGatt = this.f.get(str);
        if (bluetoothGatt != null) {
            return bluetoothGatt.getDevice().getName();
        }
        if (!this.a) {
            return null;
        }
        StringBuilder sbA = com.realsil.sdk.core.a.a.a("no bluetoothGatt exist, addr=");
        sbA.append(BluetoothHelper.formatAddress(str, true));
        ZLogger.w(sbA.toString());
        return null;
    }

    public BluetoothGattService getService(String str, UUID uuid) {
        BluetoothGattService bluetoothGattService = null;
        for (BluetoothGattService bluetoothGattService2 : getSupportedGattServices(str)) {
            if (bluetoothGattService2.getUuid().equals(uuid)) {
                bluetoothGattService = bluetoothGattService2;
            }
        }
        return bluetoothGattService;
    }

    public List<BluetoothGattService> getSupportedGattServices(String str) {
        ArrayList arrayList = new ArrayList();
        BluetoothGatt bluetoothGatt = getBluetoothGatt(str);
        return bluetoothGatt == null ? arrayList : bluetoothGatt.getServices();
    }

    public boolean isBluetoothSupported() {
        return this.d != null || a();
    }

    public boolean isCallbackRegisted(String str, BluetoothGattCallback bluetoothGattCallback) {
        List<BluetoothGattCallback> callback = getCallback(str);
        return callback != null && callback.contains(bluetoothGattCallback);
    }

    public boolean isConnected(String str) {
        Integer num = this.h.get(str);
        return num != null && num.intValue() == 2;
    }

    public boolean isHostConnected(String str) {
        BluetoothManager bluetoothManager = this.c;
        if (bluetoothManager == null) {
            if (this.a) {
                ZLogger.w("mBluetoothManager == null");
            }
            return false;
        }
        List<BluetoothDevice> connectedDevices = bluetoothManager.getConnectedDevices(7);
        if (connectedDevices != null) {
            Iterator<BluetoothDevice> it = connectedDevices.iterator();
            while (it.hasNext()) {
                if (it.next().getAddress().equals(str)) {
                    if (this.b) {
                        StringBuilder sbA = com.realsil.sdk.core.a.a.a("addr: ");
                        sbA.append(BluetoothHelper.formatAddress(str, true));
                        sbA.append(", Connected.");
                        ZLogger.d(sbA.toString());
                    }
                    return true;
                }
            }
        }
        if (this.b) {
            StringBuilder sbA2 = com.realsil.sdk.core.a.a.a("addr: ");
            sbA2.append(BluetoothHelper.formatAddress(str, true));
            sbA2.append(", Disconnected.");
            ZLogger.v(sbA2.toString());
        }
        return false;
    }

    public boolean readCharacteristic(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.d == null) {
            ZLogger.w("BluetoothAdapter not initialized");
            return false;
        }
        BluetoothGatt bluetoothGatt = this.f.get(str);
        if (bluetoothGatt == null) {
            ZLogger.w("unspecified address.");
            return false;
        }
        if (this.b) {
            StringBuilder sbA = com.realsil.sdk.core.a.a.a("addr: ");
            sbA.append(BluetoothHelper.formatAddress(str, true));
            ZLogger.d(sbA.toString());
        }
        return bluetoothGatt.readCharacteristic(bluetoothGattCharacteristic);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0037 A[Catch: all -> 0x002d, DONT_GENERATE, TryCatch #1 {, blocks: (B:7:0x000d, B:9:0x0011, B:11:0x0015, B:12:0x001b, B:14:0x0026, B:20:0x0037, B:19:0x0030), top: B:25:0x000d, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean readCharacteristicSync(java.lang.String r3, android.bluetooth.BluetoothGattCharacteristic r4) {
        /*
            r2 = this;
            r0 = 0
            r2.i = r0
            boolean r3 = r2.readCharacteristic(r3, r4)
            if (r3 != 0) goto La
            return r0
        La:
            java.lang.Object r3 = r2.j
            monitor-enter(r3)
            boolean r4 = r2.i     // Catch: java.lang.Throwable -> L2d java.lang.InterruptedException -> L2f
            if (r4 != 0) goto L37
            boolean r4 = r2.b     // Catch: java.lang.Throwable -> L2d java.lang.InterruptedException -> L2f
            if (r4 == 0) goto L1b
            java.lang.String r4 = "wait for 3000ms"
            com.realsil.sdk.core.logger.ZLogger.v(r4)     // Catch: java.lang.Throwable -> L2d java.lang.InterruptedException -> L2f
        L1b:
            java.lang.Object r4 = r2.j     // Catch: java.lang.Throwable -> L2d java.lang.InterruptedException -> L2f
            r0 = 3000(0xbb8, double:1.482E-320)
            r4.wait(r0)     // Catch: java.lang.Throwable -> L2d java.lang.InterruptedException -> L2f
            boolean r4 = r2.b     // Catch: java.lang.Throwable -> L2d java.lang.InterruptedException -> L2f
            if (r4 == 0) goto L37
            java.lang.String r4 = "wait time reached"
            com.realsil.sdk.core.logger.ZLogger.v(r4)     // Catch: java.lang.Throwable -> L2d java.lang.InterruptedException -> L2f
            goto L37
        L2d:
            r4 = move-exception
            goto L3a
        L2f:
            r4 = move-exception
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L2d
            com.realsil.sdk.core.logger.ZLogger.w(r4)     // Catch: java.lang.Throwable -> L2d
        L37:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L2d
            r3 = 1
            return r3
        L3a:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L2d
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.c.b.readCharacteristicSync(java.lang.String, android.bluetooth.BluetoothGattCharacteristic):boolean");
    }

    public synchronized void registerCallback(String str, BluetoothGattCallback bluetoothGattCallback) {
        List<BluetoothGattCallback> callback = getCallback(str);
        if (callback == null) {
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.add(bluetoothGattCallback);
            this.g.put(str, copyOnWriteArrayList);
        } else {
            if (!callback.contains(bluetoothGattCallback)) {
                callback.add(bluetoothGattCallback);
                this.g.put(str, callback);
            }
        }
    }

    public boolean requestConnectionPriority(String str, int i) {
        BluetoothGatt bluetoothGatt;
        if (str == null || (bluetoothGatt = this.f.get(str)) == null) {
            return false;
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 21) {
            ZLogger.w("requestConnectionPriority not support, Build.VERSION.SDK_INT=" + i2);
            return false;
        }
        if (i >= 0 && i <= 2) {
            return bluetoothGatt.requestConnectionPriority(i);
        }
        ZLogger.w("connectionPriority not within valid range");
        return false;
    }

    public boolean setCharacteristicIndication(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        return setCharacteristicIndication(str, bluetoothGattCharacteristic, CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID, z);
    }

    public boolean setCharacteristicNotification(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        return setCharacteristicNotification(str, bluetoothGattCharacteristic, CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0037 A[Catch: all -> 0x002d, DONT_GENERATE, TryCatch #1 {, blocks: (B:7:0x000d, B:9:0x0011, B:11:0x0015, B:12:0x001b, B:14:0x0026, B:20:0x0037, B:19:0x0030), top: B:25:0x000d, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean setCharacteristicNotificationSync(java.lang.String r2, android.bluetooth.BluetoothGattCharacteristic r3, java.util.UUID r4, boolean r5) {
        /*
            r1 = this;
            r0 = 0
            r1.i = r0
            boolean r2 = r1.setCharacteristicNotification(r2, r3, r4, r5)
            if (r2 != 0) goto La
            return r0
        La:
            java.lang.Object r2 = r1.j
            monitor-enter(r2)
            boolean r3 = r1.i     // Catch: java.lang.Throwable -> L2d java.lang.InterruptedException -> L2f
            if (r3 != 0) goto L37
            boolean r3 = r1.b     // Catch: java.lang.Throwable -> L2d java.lang.InterruptedException -> L2f
            if (r3 == 0) goto L1b
            java.lang.String r3 = "wait for 3000ms"
            com.realsil.sdk.core.logger.ZLogger.v(r3)     // Catch: java.lang.Throwable -> L2d java.lang.InterruptedException -> L2f
        L1b:
            java.lang.Object r3 = r1.j     // Catch: java.lang.Throwable -> L2d java.lang.InterruptedException -> L2f
            r4 = 3000(0xbb8, double:1.482E-320)
            r3.wait(r4)     // Catch: java.lang.Throwable -> L2d java.lang.InterruptedException -> L2f
            boolean r3 = r1.b     // Catch: java.lang.Throwable -> L2d java.lang.InterruptedException -> L2f
            if (r3 == 0) goto L37
            java.lang.String r3 = "wait time reached"
            com.realsil.sdk.core.logger.ZLogger.v(r3)     // Catch: java.lang.Throwable -> L2d java.lang.InterruptedException -> L2f
            goto L37
        L2d:
            r3 = move-exception
            goto L3a
        L2f:
            r3 = move-exception
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L2d
            com.realsil.sdk.core.logger.ZLogger.w(r3)     // Catch: java.lang.Throwable -> L2d
        L37:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L2d
            r2 = 1
            return r2
        L3a:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L2d
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.c.b.setCharacteristicNotificationSync(java.lang.String, android.bluetooth.BluetoothGattCharacteristic, java.util.UUID, boolean):boolean");
    }

    public void unRegisterAllCallback(String str) {
        if (this.g.get(str) == null) {
            if (this.a) {
                ZLogger.d("mCallbacks.get(addr) == null");
            }
        } else {
            if (this.a) {
                StringBuilder sbA = com.realsil.sdk.core.a.a.a("addr: ");
                sbA.append(BluetoothHelper.formatAddress(str, true));
                ZLogger.v(sbA.toString());
            }
            this.g.remove(str);
        }
    }

    public synchronized void unRegisterCallback(String str, BluetoothGattCallback bluetoothGattCallback) {
        List<BluetoothGattCallback> callback = getCallback(str);
        if (callback != null) {
            if (callback.contains(bluetoothGattCallback)) {
                callback.remove(bluetoothGattCallback);
                this.g.put(str, callback);
            }
        } else {
            if (this.a) {
                StringBuilder sbA = com.realsil.sdk.core.a.a.a("callback not registered, addr= ");
                sbA.append(BluetoothHelper.formatAddress(str, true));
                ZLogger.v(sbA.toString());
            }
        }
    }

    public boolean writeCharacteristic(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.d == null) {
            ZLogger.w("BluetoothAdapter not initialized");
            return false;
        }
        BluetoothGatt bluetoothGatt = this.f.get(str);
        if (bluetoothGatt != null) {
            return bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
        }
        ZLogger.w("unspecified address.");
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0039 A[Catch: all -> 0x002f, DONT_GENERATE, TRY_LEAVE, TryCatch #1 {, blocks: (B:10:0x000f, B:12:0x0013, B:14:0x0017, B:15:0x001d, B:17:0x0028, B:23:0x0039, B:22:0x0032), top: B:32:0x000f, outer: #2, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean writeCharacteristicSync(java.lang.String r3, android.bluetooth.BluetoothGattCharacteristic r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            r0 = 0
            r2.i = r0     // Catch: java.lang.Throwable -> L3f
            boolean r3 = r2.writeCharacteristic(r3, r4)     // Catch: java.lang.Throwable -> L3f
            if (r3 != 0) goto Lc
            monitor-exit(r2)
            return r0
        Lc:
            java.lang.Object r3 = r2.j     // Catch: java.lang.Throwable -> L3f
            monitor-enter(r3)     // Catch: java.lang.Throwable -> L3f
            boolean r4 = r2.i     // Catch: java.lang.Throwable -> L2f java.lang.InterruptedException -> L31
            if (r4 != 0) goto L39
            boolean r4 = r2.b     // Catch: java.lang.Throwable -> L2f java.lang.InterruptedException -> L31
            if (r4 == 0) goto L1d
            java.lang.String r4 = "wait for 3000ms"
            com.realsil.sdk.core.logger.ZLogger.v(r4)     // Catch: java.lang.Throwable -> L2f java.lang.InterruptedException -> L31
        L1d:
            java.lang.Object r4 = r2.j     // Catch: java.lang.Throwable -> L2f java.lang.InterruptedException -> L31
            r0 = 3000(0xbb8, double:1.482E-320)
            r4.wait(r0)     // Catch: java.lang.Throwable -> L2f java.lang.InterruptedException -> L31
            boolean r4 = r2.b     // Catch: java.lang.Throwable -> L2f java.lang.InterruptedException -> L31
            if (r4 == 0) goto L39
            java.lang.String r4 = "wait time reached"
            com.realsil.sdk.core.logger.ZLogger.v(r4)     // Catch: java.lang.Throwable -> L2f java.lang.InterruptedException -> L31
            goto L39
        L2f:
            r4 = move-exception
            goto L3d
        L31:
            r4 = move-exception
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L2f
            com.realsil.sdk.core.logger.ZLogger.w(r4)     // Catch: java.lang.Throwable -> L2f
        L39:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L2f
            r3 = 1
            monitor-exit(r2)
            return r3
        L3d:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L2f
            throw r4     // Catch: java.lang.Throwable -> L3f
        L3f:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.c.b.writeCharacteristicSync(java.lang.String, android.bluetooth.BluetoothGattCharacteristic):boolean");
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [java.util.List<java.lang.String>, java.util.concurrent.CopyOnWriteArrayList] */
    public synchronized void closeGatt(String str, boolean z) {
        BluetoothGatt bluetoothGatt;
        if (str == null) {
            ZLogger.d(this.a, "Invalid address");
            return;
        }
        HashMap<String, BluetoothGatt> map = this.f;
        if (map != null) {
            if (z && (bluetoothGatt = map.get(str)) != null) {
                if (this.b) {
                    StringBuilder sbA = com.realsil.sdk.core.a.a.a("closeGatt, addr=");
                    sbA.append(BluetoothHelper.formatAddress(str, true));
                    ZLogger.v(sbA.toString());
                }
                bluetoothGatt.close();
            }
            this.f.remove(str);
        }
        HashMap<String, List<BluetoothGattCallback>> map2 = this.g;
        if (map2 != null) {
            map2.remove(str);
        }
        ?? r4 = this.e;
        if (r4 != 0) {
            r4.remove(str);
        }
    }

    public boolean setCharacteristicIndication(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, UUID uuid, boolean z) {
        if (this.d == null) {
            ZLogger.w("BluetoothAdapter not initialized");
            return false;
        }
        BluetoothGatt bluetoothGatt = this.f.get(str);
        if (bluetoothGatt == null) {
            StringBuilder sbA = com.realsil.sdk.core.a.a.a("BluetoothGatt can not be null, addr=");
            sbA.append(BluetoothHelper.formatAddress(str, true));
            ZLogger.w(sbA.toString());
            return false;
        }
        if (bluetoothGattCharacteristic == null) {
            ZLogger.w("characteristic is null");
            return false;
        }
        if (this.a) {
            StringBuilder sbA2 = com.realsil.sdk.core.a.a.a("addr:=");
            sbA2.append(BluetoothHelper.formatAddress(str, true));
            sbA2.append(", enabled=");
            sbA2.append(z);
            ZLogger.d(sbA2.toString());
        }
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z);
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(uuid);
        if (descriptor == null) {
            StringBuilder sbA3 = com.realsil.sdk.core.a.a.a("descriptor not found, uuid=");
            sbA3.append(uuid.toString());
            ZLogger.w(sbA3.toString());
            return false;
        }
        if (z) {
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
        } else {
            descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
        }
        bluetoothGatt.writeDescriptor(descriptor);
        return true;
    }

    public boolean setCharacteristicNotification(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, UUID uuid, boolean z) {
        if (this.d == null) {
            ZLogger.w("BluetoothAdapter not initialized");
            return false;
        }
        BluetoothGatt bluetoothGatt = this.f.get(str);
        if (bluetoothGatt == null) {
            StringBuilder sbA = com.realsil.sdk.core.a.a.a("BluetoothGatt can not be null, addr=");
            sbA.append(BluetoothHelper.formatAddress(str, true));
            ZLogger.w(sbA.toString());
            return false;
        }
        if (this.a) {
            StringBuilder sbA2 = com.realsil.sdk.core.a.a.a("addr:=");
            sbA2.append(BluetoothHelper.formatAddress(str, true));
            sbA2.append(", enabled=");
            sbA2.append(z);
            ZLogger.d(sbA2.toString());
        }
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z);
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(uuid);
        if (descriptor == null) {
            StringBuilder sbA3 = com.realsil.sdk.core.a.a.a("descriptor not found, uuid=");
            sbA3.append(uuid.toString());
            ZLogger.w(sbA3.toString());
            return false;
        }
        if (z) {
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        } else {
            descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
        }
        bluetoothGatt.writeDescriptor(descriptor);
        return true;
    }

    public boolean connect(String str, int i, BluetoothGattCallback bluetoothGattCallback) {
        if (Build.VERSION.SDK_INT >= 26) {
            return connect(str, false, i, 1, bluetoothGattCallback);
        }
        return connect(str, false, i, 1, bluetoothGattCallback);
    }

    public boolean connect(String str, int i, int i2, BluetoothGattCallback bluetoothGattCallback) {
        return connect(str, false, i, i2, bluetoothGattCallback);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.List<java.lang.String>, java.util.concurrent.CopyOnWriteArrayList] */
    /* JADX WARN: Type inference failed for: r11v4, types: [java.util.List<java.lang.String>, java.util.concurrent.CopyOnWriteArrayList] */
    /* JADX WARN: Type inference failed for: r11v6, types: [java.util.List<java.lang.String>, java.util.concurrent.CopyOnWriteArrayList] */
    public boolean connect(String str, boolean z, int i, int i2, BluetoothGattCallback bluetoothGattCallback) {
        BluetoothGatt bluetoothGattConnectGatt;
        BluetoothGatt bluetoothGatt;
        BluetoothAdapter bluetoothAdapter = this.d;
        if (bluetoothAdapter == null) {
            ZLogger.w("BluetoothAdapter not initialized");
            return false;
        }
        if (str == null) {
            ZLogger.w("unspecified address.");
            return false;
        }
        BluetoothDevice remoteDevice = bluetoothAdapter.getRemoteDevice(str);
        if (remoteDevice == null) {
            ZLogger.w("Device not found.  Unable to connect.");
            return false;
        }
        if (this.e.contains(str) && (bluetoothGatt = this.f.get(str)) != null) {
            if (isConnected(str)) {
                if (this.a) {
                    ZLogger.v(BluetoothHelper.formatAddress(str, true) + " already connected");
                }
                registerCallback(str, bluetoothGattCallback);
                if (bluetoothGattCallback != null) {
                    bluetoothGattCallback.onConnectionStateChange(bluetoothGatt, 0, 2);
                }
                return true;
            }
            if (z) {
                registerCallback(str, bluetoothGattCallback);
                if (this.a) {
                    ZLogger.v("re-connect previous device: " + str);
                }
                if (bluetoothGatt.connect()) {
                    this.h.put(str, 1);
                    return true;
                }
                ZLogger.d("reconnect failed.");
                closeGatt(str);
                return false;
            }
            closeGatt(str);
        }
        if (this.a) {
            StringBuilder sbA = com.realsil.sdk.core.a.a.a("create connection to ");
            sbA.append(BluetoothHelper.formatAddress(str, true));
            ZLogger.v(sbA.toString());
        }
        registerCallback(str, bluetoothGattCallback);
        this.h.put(str, 1);
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 26) {
            bluetoothGattConnectGatt = remoteDevice.connectGatt(this.k, z, new C0234b(), i, i2);
        } else if (i3 >= 23) {
            bluetoothGattConnectGatt = remoteDevice.connectGatt(this.k, z, new C0234b(), i);
        } else {
            bluetoothGattConnectGatt = remoteDevice.connectGatt(this.k, z, new C0234b());
        }
        if (bluetoothGattConnectGatt == null) {
            ZLogger.d("BluetoothGatt not exist.  Unable to connect.");
            this.h.put(str, 0);
            closeGatt(str);
            return false;
        }
        this.f.put(str, bluetoothGattConnectGatt);
        if (!this.e.contains(str)) {
            this.e.add(str);
        }
        return true;
    }

    public final boolean a() {
        if (this.c == null) {
            BluetoothManager bluetoothManager = (BluetoothManager) this.k.getSystemService("bluetooth");
            this.c = bluetoothManager;
            if (bluetoothManager == null) {
                ZLogger.w("BLUETOOTH_SERVICE not supported.");
                return false;
            }
        }
        if (this.d == null) {
            BluetoothAdapter adapter = this.c.getAdapter();
            this.d = adapter;
            if (adapter == null) {
                ZLogger.w("BluetoothAdapter is not supported");
                return false;
            }
        }
        RtkBluetoothManager rtkBluetoothManager = RtkBluetoothManager.getInstance();
        this.l = rtkBluetoothManager;
        if (rtkBluetoothManager == null) {
            RtkBluetoothManager.initial(this.k);
            this.l = RtkBluetoothManager.getInstance();
        }
        RtkBluetoothManager rtkBluetoothManager2 = this.l;
        if (rtkBluetoothManager2 != null) {
            rtkBluetoothManager2.addManagerCallback(this.m);
        } else {
            ZLogger.w("BluetoothProfileManager not initialized");
        }
        ZLogger.d("initialize success");
        return true;
    }

    /* renamed from: com.realsil.sdk.core.c.b$b, reason: collision with other inner class name */
    public class C0234b extends BluetoothGattCallback {
        public C0234b() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            String address = bluetoothGatt.getDevice().getAddress();
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (b.this.a) {
                if (value != null) {
                    ZLogger.d(String.format(Locale.US, ">> onCharacteristicChanged(%s)\n(%d)%s", bluetoothGattCharacteristic.getUuid(), Integer.valueOf(value.length), DataConverter.bytes2Hex(value)));
                } else {
                    ZLogger.d(String.format(Locale.US, ">> onCharacteristicChanged(%s)", bluetoothGattCharacteristic.getUuid()));
                }
            }
            List<BluetoothGattCallback> list = b.this.g.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, int i) {
            List<BluetoothGattCallback> list;
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, bArr, i);
            String address = bluetoothGatt.getDevice().getAddress();
            if (b.this.a) {
                ZLogger.d(String.format(Locale.US, "<< onCharacteristicRead(%s): %s, %s \n\t(%d)%s", BluetoothHelper.formatAddress(address, true), bluetoothGattCharacteristic.getUuid(), GattError.parse(i), Integer.valueOf(bArr.length), DataConverter.bytes2Hex(bArr)));
            }
            synchronized (b.this.j) {
                b.this.i = true;
                b.this.j.notifyAll();
            }
            if (Build.VERSION.SDK_INT < 33 || (list = b.this.g.get(address)) == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, bArr, i);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            String address = bluetoothGatt.getDevice().getAddress();
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (b.this.a) {
                if (value != null) {
                    ZLogger.d(String.format(Locale.US, "<< onDescriptorWrite(%s) %s\n(%d)%s", GattError.parse(i), bluetoothGattCharacteristic.getUuid(), Integer.valueOf(value.length), DataConverter.bytes2Hex(value)));
                } else {
                    ZLogger.d(String.format(Locale.US, "<< onDescriptorWrite(%s) %s", GattError.parse(i), bluetoothGattCharacteristic.getUuid()));
                }
            }
            synchronized (b.this.j) {
                b.this.i = true;
                b.this.j.notifyAll();
            }
            List<BluetoothGattCallback> list = b.this.g.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            BluetoothDevice device = bluetoothGatt.getDevice();
            if (device == null) {
                return;
            }
            String address = device.getAddress();
            ZLogger.v(String.format(Locale.US, ">> onConnectionStateChange(%s), status: %s , newState: %s", BluetoothHelper.formatAddress(address, true), GattError.parseConnectionError(i), BluetoothHelper.parseProfileState(i2)));
            if (i == 0 && i2 == 2) {
                b.this.h.put(address, 2);
                b.this.f.put(address, bluetoothGatt);
            } else {
                b.this.h.put(address, 0);
            }
            b.this.a(address, bluetoothGatt, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i, byte[] bArr) {
            List<BluetoothGattCallback> list;
            super.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i, bArr);
            if (b.this.a) {
                ZLogger.d(String.format(Locale.US, "<< onDescriptorRead(%s) %s\n(%d)%s", GattError.parse(i), bluetoothGattDescriptor.getUuid(), Integer.valueOf(bArr.length), DataConverter.bytes2Hex(bArr)));
            }
            if (Build.VERSION.SDK_INT < 33 || (list = b.this.g.get(bluetoothGatt.getDevice().getAddress())) == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i, bArr);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            String address = bluetoothGatt.getDevice().getAddress();
            UUID uuid = bluetoothGattDescriptor.getCharacteristic().getUuid();
            byte[] value = bluetoothGattDescriptor.getValue();
            if (b.this.a) {
                if (value != null) {
                    ZLogger.d(String.format(Locale.US, "<< onDescriptorWrite(%s) {\nCharacteristic:%s\nDescriptor:%s\nvalue:(%d)%s\n}", GattError.parse(i), uuid, bluetoothGattDescriptor.getUuid(), Integer.valueOf(value.length), DataConverter.bytes2Hex(value)));
                } else {
                    ZLogger.d(String.format(Locale.US, "<< onDescriptorWrite(%s) {\nCharacteristic:%s\nDescriptor:%s}", GattError.parse(i), uuid, bluetoothGattDescriptor.getUuid()));
                }
            }
            synchronized (b.this.j) {
                b.this.i = true;
                b.this.j.notifyAll();
            }
            List<BluetoothGattCallback> list = b.this.g.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            String address = bluetoothGatt.getDevice().getAddress();
            ZLogger.d(String.format(Locale.US, ">> onMtuChanged(%s) mtu=%d, addr=%s", GattError.parse(i2), Integer.valueOf(i), BluetoothHelper.formatAddress(address, true)));
            List<BluetoothGattCallback> list = b.this.g.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onMtuChanged(bluetoothGatt, i, i2);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onPhyRead(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            super.onPhyRead(bluetoothGatt, i, i2, i3);
            String address = bluetoothGatt.getDevice().getAddress();
            ZLogger.d(String.format(Locale.US, "<< onPhyRead(%s) %s: txPhy=%d, rxPhy=%d", BluetoothHelper.formatAddress(address, true), GattError.parse(i3), Integer.valueOf(i), Integer.valueOf(i2)));
            List<BluetoothGattCallback> list = b.this.g.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onPhyRead(bluetoothGatt, i, i2, i3);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onPhyUpdate(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            super.onPhyUpdate(bluetoothGatt, i, i2, i3);
            String address = bluetoothGatt.getDevice().getAddress();
            ZLogger.d(String.format(Locale.US, ">> onPhyUpdate(%s) %s: txPhy=%d, rxPhy=%d", BluetoothHelper.formatAddress(address, true), GattError.parse(i3), Integer.valueOf(i), Integer.valueOf(i2)));
            List<BluetoothGattCallback> list = b.this.g.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onPhyUpdate(bluetoothGatt, i, i2, i3);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onReadRemoteRssi(bluetoothGatt, i, i2);
            if (b.this.a) {
                ZLogger.v(String.format(Locale.US, "onReadRemoteRssi:rssi=%d, status=%d", Integer.valueOf(i), Integer.valueOf(i2)));
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int i) {
            super.onReliableWriteCompleted(bluetoothGatt, i);
            if (b.this.a) {
                ZLogger.v(String.format(Locale.US, "onReliableWriteCompleted:status=%d", Integer.valueOf(i)));
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onServiceChanged(BluetoothGatt bluetoothGatt) {
            super.onServiceChanged(bluetoothGatt);
            if (b.this.a) {
                ZLogger.v("onServiceChanged");
            }
            if (Build.VERSION.SDK_INT >= 31) {
                List<BluetoothGattCallback> list = b.this.g.get(bluetoothGatt.getDevice().getAddress());
                if (list == null || list.size() <= 0) {
                    return;
                }
                Iterator<BluetoothGattCallback> it = list.iterator();
                while (it.hasNext()) {
                    it.next().onServiceChanged(bluetoothGatt);
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            String address = bluetoothGatt.getDevice().getAddress();
            ZLogger.d(String.format(Locale.US, ">> onServicesDiscovered(%s) addr=%s", GattError.parse(i), BluetoothHelper.formatAddress(address, true)));
            if (b.DUMP_SERVICE) {
                for (BluetoothGattService bluetoothGattService : bluetoothGatt.getServices()) {
                    ZLogger.d(String.format(Locale.US, "service: type=%d, %d/%s", Integer.valueOf(bluetoothGattService.getType()), Integer.valueOf(bluetoothGattService.getInstanceId()), bluetoothGattService.getUuid().toString()));
                    for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                        ZLogger.v(String.format(Locale.US, "\tcharacteristic: %d/%s", Integer.valueOf(bluetoothGattCharacteristic.getInstanceId()), bluetoothGattCharacteristic.getUuid().toString()));
                    }
                }
            }
            List<BluetoothGattCallback> list = b.this.g.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onServicesDiscovered(bluetoothGatt, i);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
            if (b.this.a) {
                ZLogger.v(String.format(Locale.US, "onDescriptorRead(%s):status=%d", bluetoothGattDescriptor.getUuid(), Integer.valueOf(i)));
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
            List<BluetoothGattCallback> list;
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic, bArr);
            String address = bluetoothGatt.getDevice().getAddress();
            if (b.this.a) {
                ZLogger.d(String.format(Locale.US, ">> onCharacteristicChanged(%s)\n(%d)%s", bluetoothGattCharacteristic.getUuid(), Integer.valueOf(bArr.length), DataConverter.bytes2Hex(bArr)));
            }
            if (Build.VERSION.SDK_INT < 33 || (list = b.this.g.get(address)) == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic, bArr);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            String address = bluetoothGatt.getDevice().getAddress();
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (b.this.a) {
                if (value != null) {
                    ZLogger.d(String.format(Locale.US, "<< onCharacteristicRead(%s): %s, %s \n\t(%d)%s", BluetoothHelper.formatAddress(address, true), bluetoothGattCharacteristic.getUuid(), GattError.parse(i), Integer.valueOf(value.length), DataConverter.bytes2Hex(value)));
                } else {
                    ZLogger.d(String.format(Locale.US, "<< onCharacteristicRead(%s): %s,%s", BluetoothHelper.formatAddress(address, true), bluetoothGattCharacteristic.getUuid(), GattError.parse(i)));
                }
            }
            synchronized (b.this.j) {
                b.this.i = true;
                b.this.j.notifyAll();
            }
            List<BluetoothGattCallback> list = b.this.g.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            }
        }
    }

    public final void a(String str, BluetoothGatt bluetoothGatt, int i, int i2) {
        List<BluetoothGattCallback> list = this.g.get(str);
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<BluetoothGattCallback> it = list.iterator();
        while (it.hasNext()) {
            it.next().onConnectionStateChange(bluetoothGatt, i, i2);
        }
    }
}
