package com.realsil.customer.core.b;

import android.annotation.TargetApi;
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
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.realsil.customer.core.RtkCore;
import com.realsil.customer.core.bluetooth.RtkBluetoothManager;
import com.realsil.customer.core.bluetooth.RtkBluetoothManagerCallback;
import com.realsil.customer.core.bluetooth.compat.BluetoothGattCompat;
import com.realsil.customer.core.bluetooth.connection.le.BluetoothGattCallbackCompat;
import com.realsil.customer.core.bluetooth.connection.le.GattError;
import com.realsil.customer.core.bluetooth.utils.BluetoothHelper;
import com.realsil.customer.core.logger.ZLogger;
import com.realsil.customer.core.utility.DataConverter;
import com.realsil.customer.core.utility.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/b/a.class */
public class a {
    public static boolean DUMP_SERVICE = false;
    public static boolean CLOSE_GATT_ENABLED = true;
    public static final int PHY_LE_1M_MASK = 1;
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_CONNECTED = 2;
    public final boolean a;
    public final boolean b;
    public BluetoothManager c;
    public BluetoothAdapter d;
    public volatile boolean i;
    public final Context k;
    public RtkBluetoothManager l;
    public static int SDK_INT = Build.VERSION.SDK_INT;
    public static final String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";
    public static final UUID CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID = UUID.fromString(CLIENT_CHARACTERISTIC_CONFIG);
    public final Object j = new Object();
    public final C0003a m = new C0003a();
    public final HashMap<String, BluetoothGatt> f = new HashMap<>();
    public final HashMap<String, Integer> h = new HashMap<>();
    public final HashMap<String, List<BluetoothGattCallbackCompat>> g = new HashMap<>();
    public final CopyOnWriteArrayList e = new CopyOnWriteArrayList();

    /* renamed from: com.realsil.customer.core.b.a$a, reason: collision with other inner class name */
    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/b/a$a.class */
    public class C0003a extends RtkBluetoothManagerCallback {
        public C0003a() {
        }

        @Override // com.realsil.customer.core.bluetooth.RtkBluetoothManagerCallback
        public final void onBluetoothStateChanged(int i) {
            super.onBluetoothStateChanged(i);
            a.a(a.this, i);
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/b/a$b.class */
    public class b extends BluetoothGattCallback {
        public b() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        @TargetApi(21)
        public final void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            String address = bluetoothGatt.getDevice().getAddress();
            ZLogger.d(String.format(Locale.US, ">> onMtuChanged(%s) mtu=%d, addr=%s", GattError.parse(i2), Integer.valueOf(i), BluetoothHelper.formatAddress(address, true)));
            List<BluetoothGattCallbackCompat> list = a.this.g.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallbackCompat> it = list.iterator();
            while (it.hasNext()) {
                it.next().onMtuChanged(bluetoothGatt, i, i2);
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
                a.this.h.put(address, 2);
                a.this.f.put(address, bluetoothGatt);
            } else {
                a.this.h.put(address, 0);
            }
            List<BluetoothGattCallbackCompat> list = a.this.g.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallbackCompat> it = list.iterator();
            while (it.hasNext()) {
                it.next().onConnectionStateChange(bluetoothGatt, i, i2);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            String address = bluetoothGatt.getDevice().getAddress();
            ZLogger.d(String.format(Locale.US, ">> onServicesDiscovered(%s), status=%s", BluetoothHelper.formatAddress(address, true), GattError.parse(i)));
            if (a.DUMP_SERVICE) {
                ZLogger.v(BluetoothHelper.dumpBluetoothGattService(bluetoothGatt));
            }
            List<BluetoothGattCallbackCompat> list = a.this.g.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallbackCompat> it = list.iterator();
            while (it.hasNext()) {
                it.next().onServicesDiscovered(bluetoothGatt, i);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onCharacteristicRead(@NonNull BluetoothGatt bluetoothGatt, @NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, @NonNull byte[] bArr, int i) {
            a(bluetoothGatt, bluetoothGattCharacteristic, bArr, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            a(bluetoothGatt, bluetoothGattCharacteristic, bluetoothGattCharacteristic.getValue());
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            String address = bluetoothGatt.getDevice().getAddress();
            UUID uuid = bluetoothGattDescriptor.getCharacteristic().getUuid();
            if (a.this.a) {
                byte[] value = bluetoothGattDescriptor.getValue();
                if (value != null) {
                    ZLogger.d(String.format(Locale.US, "<< onDescriptorWrite(%s):%s {\nCharacteristic:%s\nDescriptor:%s\nvalue:(%d)%s\n}", BluetoothHelper.formatAddress(address, true), GattError.parse(i), uuid, bluetoothGattDescriptor.getUuid(), Integer.valueOf(value.length), DataConverter.bytes2Hex(value)));
                } else {
                    ZLogger.d(String.format(Locale.US, "<< onDescriptorWrite(%s):%s {\nCharacteristic:%s\nDescriptor:%s}", BluetoothHelper.formatAddress(address, true), GattError.parse(i), uuid, bluetoothGattDescriptor.getUuid()));
                }
            }
            synchronized (a.this.j) {
                a.this.i = true;
                a.this.j.notifyAll();
            }
            List<BluetoothGattCallbackCompat> list = a.this.g.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallbackCompat> it = list.iterator();
            while (it.hasNext()) {
                it.next().onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            a(bluetoothGatt, bluetoothGattDescriptor, i, bluetoothGattDescriptor.getValue());
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onServiceChanged(@NonNull BluetoothGatt bluetoothGatt) {
            super.onServiceChanged(bluetoothGatt);
            String address = bluetoothGatt.getDevice().getAddress();
            if (a.this.a) {
                ZLogger.v(String.format("onServiceChanged(%s)", BluetoothHelper.formatAddress(address, true)));
            }
            List<BluetoothGattCallbackCompat> list = a.this.g.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallbackCompat> it = list.iterator();
            while (it.hasNext()) {
                it.next().onServiceChanged(bluetoothGatt);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            String address = bluetoothGatt.getDevice().getAddress();
            if (a.this.a) {
                Locale locale = Locale.US;
                ZLogger.d(String.format(locale, "<< onCharacteristicWrite(%s):%s 0x%02X-%s", BluetoothHelper.formatAddress(address, true), GattError.parse(i), Integer.valueOf(bluetoothGattCharacteristic.getInstanceId()), bluetoothGattCharacteristic.getUuid()));
                byte[] value = bluetoothGattCharacteristic.getValue();
                if (value != null) {
                    ZLogger.d(String.format(locale, "<<<<(%d)%s", Integer.valueOf(value.length), DataConverter.bytes2Hex(value)));
                }
            }
            synchronized (a.this.j) {
                a.this.i = true;
                a.this.j.notifyAll();
            }
            List<BluetoothGattCallbackCompat> list = a.this.g.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallbackCompat> it = list.iterator();
            while (it.hasNext()) {
                it.next().onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        @RequiresApi(api = 26)
        public final void onPhyUpdate(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            super.onPhyUpdate(bluetoothGatt, i, i2, i3);
            String address = bluetoothGatt.getDevice().getAddress();
            ZLogger.d(String.format(Locale.US, ">> onPhyUpdate(%s) %s: txPhy=%d, rxPhy=%d", BluetoothHelper.formatAddress(address, true), GattError.parse(i3), Integer.valueOf(i), Integer.valueOf(i2)));
            List<BluetoothGattCallbackCompat> list = a.this.g.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallbackCompat> it = list.iterator();
            while (it.hasNext()) {
                it.next().onPhyUpdate(bluetoothGatt, i, i2, i3);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        @RequiresApi(api = 26)
        public final void onPhyRead(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            super.onPhyRead(bluetoothGatt, i, i2, i3);
            String address = bluetoothGatt.getDevice().getAddress();
            ZLogger.d(String.format(Locale.US, "<< onPhyRead(%s) %s: txPhy=%d, rxPhy=%d", BluetoothHelper.formatAddress(address, true), GattError.parse(i3), Integer.valueOf(i), Integer.valueOf(i2)));
            List<BluetoothGattCallbackCompat> list = a.this.g.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallbackCompat> it = list.iterator();
            while (it.hasNext()) {
                it.next().onPhyRead(bluetoothGatt, i, i2, i3);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int i) {
            super.onReliableWriteCompleted(bluetoothGatt, i);
            if (a.this.a) {
                ZLogger.v(String.format(Locale.US, "onReliableWriteCompleted(%s):status=%d", BluetoothHelper.formatAddress(bluetoothGatt.getDevice().getAddress(), true), Integer.valueOf(i)));
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onReadRemoteRssi(bluetoothGatt, i, i2);
            if (a.this.a) {
                ZLogger.v(String.format(Locale.US, "onReadRemoteRssi(%s):rssi=%d, status=%d", BluetoothHelper.formatAddress(bluetoothGatt.getDevice().getAddress(), true), Integer.valueOf(i), Integer.valueOf(i2)));
            }
        }

        public final void a(@NonNull BluetoothGatt bluetoothGatt, @NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, @NonNull byte[] bArr, int i) {
            String address = bluetoothGatt.getDevice().getAddress();
            if (a.this.a) {
                ZLogger.d(String.format(Locale.US, "<< onCharacteristicRead(%s): 0x%02X-%s, %s \n\t(%d)%s", BluetoothHelper.formatAddress(address, true), Integer.valueOf(bluetoothGattCharacteristic.getInstanceId()), bluetoothGattCharacteristic.getUuid(), GattError.parse(i), Integer.valueOf(bArr.length), DataConverter.bytes2Hex(bArr)));
            }
            synchronized (a.this.j) {
                a.this.i = true;
                a.this.j.notifyAll();
            }
            List<BluetoothGattCallbackCompat> list = a.this.g.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallbackCompat> it = list.iterator();
            while (it.hasNext()) {
                it.next().onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, bArr, i);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            a(bluetoothGatt, bluetoothGattCharacteristic, bluetoothGattCharacteristic.getValue(), i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onCharacteristicChanged(@NonNull BluetoothGatt bluetoothGatt, @NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, @NonNull byte[] bArr) {
            a(bluetoothGatt, bluetoothGattCharacteristic, bArr);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onDescriptorRead(@NonNull BluetoothGatt bluetoothGatt, @NonNull BluetoothGattDescriptor bluetoothGattDescriptor, int i, @NonNull byte[] bArr) {
            a(bluetoothGatt, bluetoothGattDescriptor, i, bArr);
        }

        public final void a(@NonNull BluetoothGatt bluetoothGatt, @NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, @NonNull byte[] bArr) {
            String address = bluetoothGatt.getDevice().getAddress();
            if (a.this.a) {
                ZLogger.d(String.format(Locale.US, ">> onCharacteristicChanged(%s):0x%02X-%s\n(%d)%s", BluetoothHelper.formatAddress(address, true), Integer.valueOf(bluetoothGattCharacteristic.getInstanceId()), bluetoothGattCharacteristic.getUuid(), Integer.valueOf(bArr.length), DataConverter.bytes2Hex(bArr)));
            }
            List<BluetoothGattCallbackCompat> list = a.this.g.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallbackCompat> it = list.iterator();
            while (it.hasNext()) {
                it.next().onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic, bArr);
            }
        }

        public final void a(@NonNull BluetoothGatt bluetoothGatt, @NonNull BluetoothGattDescriptor bluetoothGattDescriptor, int i, @NonNull byte[] bArr) {
            String address = bluetoothGatt.getDevice().getAddress();
            if (a.this.a) {
                ZLogger.d(String.format(Locale.US, "<< onDescriptorRead(%s):%s, %s\n(%d)%s", BluetoothHelper.formatAddress(address, true), GattError.parse(i), bluetoothGattDescriptor.getUuid(), Integer.valueOf(bArr.length), DataConverter.bytes2Hex(bArr)));
            }
            List<BluetoothGattCallbackCompat> list = a.this.g.get(address);
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator<BluetoothGattCallbackCompat> it = list.iterator();
            while (it.hasNext()) {
                it.next().onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i, bArr);
            }
        }
    }

    public a(Context context) {
        this.a = false;
        this.b = false;
        this.k = context;
        this.a = RtkCore.DEBUG;
        this.b = RtkCore.VDBG;
        a();
    }

    public static void a(a aVar, int i) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        aVar.getClass();
        if (i != 10 || Build.VERSION.SDK_INT < 29 || (copyOnWriteArrayList = aVar.e) == null || copyOnWriteArrayList.size() <= 0) {
            return;
        }
        ZLogger.d("Bluetooth is turned off, disconnect all client connections");
        Iterator it = aVar.e.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            BluetoothGatt bluetoothGatt = aVar.getBluetoothGatt(str);
            if (aVar.isConnected(str)) {
                aVar.h.put(str, 0);
                List<BluetoothGattCallbackCompat> list = aVar.g.get(str);
                if (list != null && list.size() > 0) {
                    Iterator<BluetoothGattCallbackCompat> it2 = list.iterator();
                    while (it2.hasNext()) {
                        it2.next().onConnectionStateChange(bluetoothGatt, 0, 0);
                    }
                }
            }
        }
    }

    public boolean isBluetoothSupported() {
        return this.d != null || a();
    }

    public boolean isHostConnected(String str) {
        BluetoothManager bluetoothManager = this.c;
        if (bluetoothManager == null) {
            if (!this.a) {
                return false;
            }
            ZLogger.w("mBluetoothManager == null");
            return false;
        }
        List<BluetoothDevice> connectedDevices = bluetoothManager.getConnectedDevices(7);
        if (!this.b) {
            return false;
        }
        if (connectedDevices != null) {
            Iterator<BluetoothDevice> it = connectedDevices.iterator();
            while (it.hasNext()) {
                if (it.next().getAddress().equals(str)) {
                    ZLogger.v("addr: " + BluetoothHelper.formatAddress(str, true) + ", Connected.");
                    return true;
                }
            }
        }
        ZLogger.v("addr: " + BluetoothHelper.formatAddress(str, true) + ", Disconnected.");
        return false;
    }

    public boolean isConnected(String str) {
        Integer num = this.h.get(str);
        return num != null && num.intValue() == 2;
    }

    public int getConnectionState(String str) {
        Integer num = this.h.get(str);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public List<String> getBluetoothDeviceAddresss() {
        return this.e;
    }

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

    public BluetoothGatt getBluetoothGatt(String str) {
        return this.f.get(str);
    }

    public BluetoothDevice getBluetoothDevice(String str) {
        BluetoothGatt bluetoothGatt = this.f.get(str);
        if (bluetoothGatt != null) {
            return bluetoothGatt.getDevice();
        }
        if (!this.b) {
            return null;
        }
        ZLogger.w("no bluetoothGatt exist, addr=" + BluetoothHelper.formatAddress(str, true));
        return null;
    }

    public String getDeviceName(String str) {
        BluetoothDevice bluetoothDevice = getBluetoothDevice(str);
        return bluetoothDevice == null ? "" : bluetoothDevice.getName();
    }

    public BluetoothAdapter getBluetoothAdapter() {
        return this.d;
    }

    public List<BluetoothGattService> getSupportedGattServices(String str) {
        ArrayList arrayList = new ArrayList();
        BluetoothGatt bluetoothGatt = getBluetoothGatt(str);
        return bluetoothGatt == null ? arrayList : bluetoothGatt.getServices();
    }

    public BluetoothGattService getService(String str, UUID uuid) {
        List<BluetoothGattService> supportedGattServices = getSupportedGattServices(str);
        BluetoothGattService bluetoothGattService = null;
        for (BluetoothGattService bluetoothGattService2 : supportedGattServices) {
            if (bluetoothGattService2.getUuid().equals(uuid)) {
                bluetoothGattService = bluetoothGattService2;
            }
        }
        return bluetoothGattService;
    }

    public boolean isCallbackRegisterd(String str, BluetoothGattCallbackCompat bluetoothGattCallbackCompat) {
        List<BluetoothGattCallbackCompat> callback = getCallback(str);
        return callback != null && callback.contains(bluetoothGattCallbackCompat);
    }

    public boolean isCallbackRegisted(String str, BluetoothGattCallbackCompat bluetoothGattCallbackCompat) {
        return isCallbackRegisterd(str, bluetoothGattCallbackCompat);
    }

    public List<BluetoothGattCallbackCompat> getCallback(String str) {
        HashMap<String, List<BluetoothGattCallbackCompat>> map = this.g;
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    public synchronized void registerCallback(String str, BluetoothGattCallbackCompat bluetoothGattCallbackCompat) {
        List<BluetoothGattCallbackCompat> callback = getCallback(str);
        if (callback == null) {
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.add(bluetoothGattCallbackCompat);
            this.g.put(str, copyOnWriteArrayList);
        } else {
            if (callback.contains(bluetoothGattCallbackCompat)) {
                return;
            }
            callback.add(bluetoothGattCallbackCompat);
            this.g.put(str, callback);
        }
    }

    public synchronized void unRegisterCallback(String str, BluetoothGattCallbackCompat bluetoothGattCallbackCompat) {
        List<BluetoothGattCallbackCompat> callback = getCallback(str);
        if (callback == null) {
            if (this.a) {
                ZLogger.v("callback not registered, addr= " + BluetoothHelper.formatAddress(str, true));
            }
        } else if (callback.contains(bluetoothGattCallbackCompat)) {
            callback.remove(bluetoothGattCallbackCompat);
            this.g.put(str, callback);
        }
    }

    public void unRegisterAllCallback(String str) {
        if (this.g.get(str) == null) {
            if (this.a) {
                ZLogger.d("mCallbacks.get(addr) == null");
            }
        } else {
            if (this.a) {
                ZLogger.v("addr: " + BluetoothHelper.formatAddress(str, true));
            }
            this.g.remove(str);
        }
    }

    public boolean connect(String str, BluetoothGattCallbackCompat bluetoothGattCallbackCompat) {
        return Build.VERSION.SDK_INT >= 23 ? connect(str, 2, bluetoothGattCallbackCompat) : connect(str, 2, bluetoothGattCallbackCompat);
    }

    public synchronized void closeGatt(String str) {
        closeGatt(str, CLOSE_GATT_ENABLED);
    }

    public boolean disconnectGatt(String str) {
        return disconnect(str);
    }

    /* JADX WARN: Type inference failed for: r0v25, types: [android.bluetooth.BluetoothGatt] */
    /* JADX WARN: Type inference failed for: r0v26 */
    /* JADX WARN: Type inference failed for: r0v29 */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.realsil.customer.core.b.a, java.lang.InterruptedException] */
    public boolean disconnect(String str) throws InterruptedException {
        BluetoothGatt bluetoothGatt = this.f.get(str);
        if (bluetoothGatt == null) {
            return false;
        }
        if (!isConnected(str)) {
            List<BluetoothGattCallbackCompat> list = this.g.get(str);
            if (list == null || list.size() <= 0) {
                return true;
            }
            Iterator<BluetoothGattCallbackCompat> it = list.iterator();
            while (it.hasNext()) {
                it.next().onConnectionStateChange(bluetoothGatt, 0, 0);
            }
            return true;
        }
        if (this.a) {
            ZLogger.v("disconnect : " + BluetoothHelper.formatAddress(str, true));
        }
        Object obj = bluetoothGatt;
        obj.disconnect();
        try {
            obj = 500;
            Thread.sleep(500L);
            return true;
        } catch (InterruptedException e) {
            ZLogger.w(e.b, obj.toString());
            return true;
        }
    }

    public boolean requestConnectionPriority(String str, int i) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 21) {
            ZLogger.w("requestConnectionPriority not support, Build.VERSION.SDK_INT=" + i2);
            return false;
        }
        if (StringUtils.isEmpty(str)) {
            ZLogger.w("address can not be null or empty");
            return false;
        }
        if (i < 0 || i > 2) {
            ZLogger.w("connectionPriority not within valid range");
            return false;
        }
        BluetoothGatt bluetoothGatt = this.f.get(str);
        if (bluetoothGatt != null) {
            return bluetoothGatt.requestConnectionPriority(i);
        }
        ZLogger.w("no GATT client registered");
        return false;
    }

    public void close(String str) throws InterruptedException {
        if (str == null) {
            return;
        }
        BluetoothGatt bluetoothGatt = this.f.get(str);
        if (bluetoothGatt != null) {
            if (isConnected(str)) {
                if (this.a) {
                    ZLogger.v("disconnect : ".concat(str));
                }
                bluetoothGatt.disconnect();
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException unused) {
                }
            }
            if (CLOSE_GATT_ENABLED) {
                if (this.b) {
                    ZLogger.v("closeGatt, addr:=" + BluetoothHelper.formatAddress(str, true));
                }
                bluetoothGatt.close();
            }
            this.f.remove(str);
        }
        HashMap<String, List<BluetoothGattCallbackCompat>> map = this.g;
        if (map != null) {
            map.remove(str);
        }
        CopyOnWriteArrayList copyOnWriteArrayList = this.e;
        if (copyOnWriteArrayList == null || !copyOnWriteArrayList.contains(str)) {
            return;
        }
        this.e.remove(str);
    }

    public void closeAll() throws InterruptedException {
        CopyOnWriteArrayList copyOnWriteArrayList = this.e;
        if (copyOnWriteArrayList != null && copyOnWriteArrayList.size() > 0) {
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
            ZLogger.v("readCharacteristic, address=" + BluetoothHelper.formatAddress(str, true));
        }
        return bluetoothGatt.readCharacteristic(bluetoothGattCharacteristic);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.realsil.customer.core.b.a] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4 */
    public boolean readCharacteristicSync(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.i = false;
        if (!readCharacteristic(str, bluetoothGattCharacteristic)) {
            return false;
        }
        ?? r0 = this;
        Object obj = r0.j;
        synchronized (obj) {
            try {
                if (!r0.i) {
                    if (this.b) {
                        ZLogger.v("wait for 3000ms");
                    }
                    this.j.wait(3000L);
                    if (this.b) {
                        ZLogger.v("wait time reached");
                    }
                }
            } catch (InterruptedException unused) {
            }
            r0 = obj;
            return true;
        }
    }

    public boolean writeCharacteristic(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return writeCharacteristic(str, bluetoothGattCharacteristic, bluetoothGattCharacteristic.getValue());
    }

    public synchronized boolean writeCharacteristicSync(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return writeCharacteristicSync(str, bluetoothGattCharacteristic, bluetoothGattCharacteristic.getValue());
    }

    public boolean setCharacteristicNotification(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        return setCharacteristicNotification(str, bluetoothGattCharacteristic, CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID, z);
    }

    public boolean writeDescriptor(@NonNull BluetoothGatt bluetoothGatt, @NonNull BluetoothGattDescriptor bluetoothGattDescriptor, @NonNull byte[] bArr) {
        return BluetoothGattCompat.writeDescriptor(bluetoothGatt, bluetoothGattDescriptor, bArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.realsil.customer.core.b.a] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4 */
    public boolean setCharacteristicNotificationSync(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, UUID uuid, boolean z) {
        this.i = false;
        if (!setCharacteristicNotification(str, bluetoothGattCharacteristic, uuid, z)) {
            return false;
        }
        ?? r0 = this;
        Object obj = r0.j;
        synchronized (obj) {
            try {
                if (!r0.i) {
                    if (this.b) {
                        ZLogger.v("wait for 3000ms");
                    }
                    this.j.wait(3000L);
                    if (this.b) {
                        ZLogger.v("wait time reached");
                    }
                }
            } catch (InterruptedException unused) {
            }
            r0 = obj;
            return true;
        }
    }

    public boolean setCharacteristicIndication(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        return setCharacteristicIndication(str, bluetoothGattCharacteristic, CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID, z);
    }

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
                    ZLogger.v("closeGatt, addr=" + BluetoothHelper.formatAddress(str, true));
                }
                bluetoothGatt.close();
            }
            this.f.remove(str);
        }
        HashMap<String, List<BluetoothGattCallbackCompat>> map2 = this.g;
        if (map2 != null) {
            map2.remove(str);
        }
        CopyOnWriteArrayList copyOnWriteArrayList = this.e;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.remove(str);
        }
    }

    public boolean writeCharacteristic(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, @NonNull byte[] bArr) {
        if (this.d != null) {
            return writeCharacteristic(this.f.get(str), bluetoothGattCharacteristic, bArr);
        }
        ZLogger.w("BluetoothAdapter not initialized");
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.realsil.customer.core.b.a] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public synchronized boolean writeCharacteristicSync(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, @NonNull byte[] bArr) {
        this.i = false;
        if (!writeCharacteristic(str, bluetoothGattCharacteristic, bArr)) {
            return false;
        }
        ?? r0 = this;
        Object obj = r0.j;
        synchronized (obj) {
            if (!r0.i) {
                if (this.b) {
                    ZLogger.v("wait for 3000ms");
                }
                try {
                    this.j.wait(3000L);
                } catch (InterruptedException unused) {
                }
                if (this.b) {
                    ZLogger.v("wait time reached");
                }
            }
            r0 = obj;
            return true;
        }
    }

    public boolean setCharacteristicNotification(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, UUID uuid, boolean z) {
        if (this.d == null) {
            ZLogger.w("BluetoothAdapter not initialized");
            return false;
        }
        BluetoothGatt bluetoothGatt = this.f.get(str);
        if (bluetoothGatt == null) {
            ZLogger.w("BluetoothGatt can not be null, addr=" + BluetoothHelper.formatAddress(str, true));
            return false;
        }
        if (this.a) {
            ZLogger.d("addr:=" + BluetoothHelper.formatAddress(str, true) + ", enabled=" + z);
        }
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z);
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(uuid);
        if (descriptor != null) {
            return z ? BluetoothGattCompat.writeDescriptor(bluetoothGatt, descriptor, BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE) : BluetoothGattCompat.writeDescriptor(bluetoothGatt, descriptor, BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
        }
        ZLogger.w("descriptor not found, uuid=" + uuid.toString());
        return false;
    }

    public boolean setCharacteristicIndication(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, UUID uuid, boolean z) {
        if (this.d == null) {
            ZLogger.w("BluetoothAdapter not initialized");
            return false;
        }
        BluetoothGatt bluetoothGatt = this.f.get(str);
        if (bluetoothGatt == null) {
            ZLogger.w("BluetoothGatt can not be null, address=" + BluetoothHelper.formatAddress(str, true));
            return false;
        }
        if (bluetoothGattCharacteristic == null) {
            ZLogger.w("characteristic is null");
            return false;
        }
        if (this.a) {
            ZLogger.d("address:=" + BluetoothHelper.formatAddress(str, true) + ", enabled=" + z);
        }
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z);
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(uuid);
        if (descriptor != null) {
            return z ? BluetoothGattCompat.writeDescriptor(bluetoothGatt, descriptor, BluetoothGattDescriptor.ENABLE_INDICATION_VALUE) : BluetoothGattCompat.writeDescriptor(bluetoothGatt, descriptor, BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
        }
        ZLogger.w("descriptor not found, uuid=" + uuid.toString());
        return false;
    }

    public boolean connect(String str, int i, BluetoothGattCallbackCompat bluetoothGattCallbackCompat) {
        if (Build.VERSION.SDK_INT >= 26) {
            return connect(str, false, i, 1, bluetoothGattCallbackCompat);
        }
        return connect(str, false, i, 1, bluetoothGattCallbackCompat);
    }

    public boolean connect(String str, int i, int i2, BluetoothGattCallbackCompat bluetoothGattCallbackCompat) {
        return connect(str, false, i, i2, bluetoothGattCallbackCompat);
    }

    public boolean writeCharacteristic(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, @NonNull byte[] bArr) {
        if (this.d == null) {
            ZLogger.w("BluetoothAdapter not initialized");
            return false;
        }
        return BluetoothGattCompat.writeCharacteristic(bluetoothGatt, bluetoothGattCharacteristic, bArr);
    }

    public boolean connect(String str, boolean z, int i, int i2, BluetoothGattCallbackCompat bluetoothGattCallbackCompat) {
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
                registerCallback(str, bluetoothGattCallbackCompat);
                if (bluetoothGattCallbackCompat == null) {
                    return true;
                }
                bluetoothGattCallbackCompat.onConnectionStateChange(bluetoothGatt, 0, 2);
                return true;
            }
            if (z) {
                registerCallback(str, bluetoothGattCallbackCompat);
                if (this.a) {
                    ZLogger.v("re-connect previous device: ".concat(str));
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
            ZLogger.v("create connection to " + BluetoothHelper.formatAddress(str, true));
        }
        registerCallback(str, bluetoothGattCallbackCompat);
        this.h.put(str, 1);
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 26) {
            bluetoothGattConnectGatt = remoteDevice.connectGatt(this.k, z, new b(), i, i2);
        } else if (i3 >= 23) {
            bluetoothGattConnectGatt = remoteDevice.connectGatt(this.k, z, new b(), i);
        } else {
            bluetoothGattConnectGatt = remoteDevice.connectGatt(this.k, z, new b());
        }
        if (bluetoothGattConnectGatt == null) {
            ZLogger.d("BluetoothGatt not exist.  Unable to connect.");
            this.h.put(str, 0);
            closeGatt(str);
            return false;
        }
        this.f.put(str, bluetoothGattConnectGatt);
        if (this.e.contains(str)) {
            return true;
        }
        this.e.add(str);
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
            BluetoothAdapter bluetoothAdapterA = com.realsil.customer.core.a.a.a(this.k);
            this.d = bluetoothAdapterA;
            if (bluetoothAdapterA == null) {
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
}
