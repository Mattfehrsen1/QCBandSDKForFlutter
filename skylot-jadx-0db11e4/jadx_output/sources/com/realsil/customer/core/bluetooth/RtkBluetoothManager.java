package com.realsil.customer.core.bluetooth;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.realsil.customer.core.RtkCore;
import com.realsil.customer.core.bluetooth.impl.BluetoothAdapterImpl;
import com.realsil.customer.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.customer.core.bluetooth.utils.BluetoothHelper;
import com.realsil.customer.core.logger.ZLogger;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/RtkBluetoothManager.class */
public final class RtkBluetoothManager {
    public static RtkBluetoothManager j;
    public static final int INDICATOR_FULL = 255;
    public static final int INDICATOR_BT = 1;
    public static final int INDICATOR_ACL = 2;
    public static final int INDICATOR_BOND = 4;
    public static final String EXTRA_REASON = "android.bluetooth.device.extra.REASON";
    public final boolean a;
    public final boolean b;
    public final Context c;
    public CopyOnWriteArrayList d;
    public BluetoothAdapter e;
    public BluetoothBroadcastReceiver f = null;
    public final Object g = new Object();
    public final Object h = new Object();
    public boolean i = false;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/RtkBluetoothManager$BluetoothBroadcastReceiver.class */
    public class BluetoothBroadcastReceiver extends BroadcastReceiver {
        public BluetoothBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        @TargetApi(19)
        public final void onReceive(Context context, Intent intent) {
            String action;
            action = intent.getAction();
            action.getClass();
            switch (action) {
                case "android.bluetooth.adapter.action.STATE_CHANGED":
                    RtkBluetoothManager rtkBluetoothManager = RtkBluetoothManager.this;
                    rtkBluetoothManager.getClass();
                    String action2 = intent.getAction();
                    int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                    ZLogger.d(String.format(Locale.US, "action=%s, state: %d->%d", action2, Integer.valueOf(intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", -1)), Integer.valueOf(intExtra)));
                    switch (intExtra) {
                        case 10:
                            if (rtkBluetoothManager.a) {
                                ZLogger.v("ACTION_STATE_CHANGED: STATE_OFF");
                            }
                            synchronized (rtkBluetoothManager.h) {
                                rtkBluetoothManager.h.notifyAll();
                            }
                            break;
                        case 11:
                            if (rtkBluetoothManager.a) {
                                ZLogger.v("ACTION_STATE_CHANGED: STATE_TURNING_ON");
                                break;
                            }
                            break;
                        case 12:
                            if (rtkBluetoothManager.a) {
                                ZLogger.v("ACTION_STATE_CHANGED: STATE_ON");
                            }
                            synchronized (rtkBluetoothManager.g) {
                                rtkBluetoothManager.g.notifyAll();
                            }
                            break;
                        case 13:
                            if (rtkBluetoothManager.a) {
                                ZLogger.v("ACTION_STATE_CHANGED: STATE_TURNING_OFF");
                                break;
                            }
                            break;
                        default:
                            if (rtkBluetoothManager.a) {
                                ZLogger.v("ACTION_STATE_CHANGED: " + intExtra);
                                break;
                            }
                            break;
                    }
                    CopyOnWriteArrayList copyOnWriteArrayList = rtkBluetoothManager.d;
                    if (copyOnWriteArrayList != null) {
                        Iterator it = copyOnWriteArrayList.iterator();
                        while (it.hasNext()) {
                            ((RtkBluetoothManagerCallback) it.next()).onBluetoothStateChanged(intExtra);
                        }
                        return;
                    }
                    return;
                case "android.bluetooth.device.action.ACL_CONNECTED":
                    RtkBluetoothManager.e(RtkBluetoothManager.this, intent);
                    return;
                case "android.bluetooth.device.action.PAIRING_REQUEST":
                    RtkBluetoothManager.c(RtkBluetoothManager.this, intent);
                    return;
                case "android.bluetooth.adapter.action.BLE_ACL_CONNECTED":
                    RtkBluetoothManager.a(RtkBluetoothManager.this, intent);
                    return;
                case "android.bluetooth.adapter.action.BLE_ACL_DISCONNECTED":
                    RtkBluetoothManager.b(RtkBluetoothManager.this, intent);
                    return;
                case "android.bluetooth.device.action.ACL_DISCONNECTED":
                    RtkBluetoothManager.f(RtkBluetoothManager.this, intent);
                    return;
                case "android.bluetooth.device.action.BOND_STATE_CHANGED":
                    RtkBluetoothManager.d(RtkBluetoothManager.this, intent);
                    return;
                default:
                    ZLogger.d("action:".concat(action));
                    return;
            }
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/RtkBluetoothManager$a.class */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ZLogger.d("scan delay time reached");
            RtkBluetoothManager.this.b();
        }
    }

    public void addManagerCallback(RtkBluetoothManagerCallback rtkBluetoothManagerCallback) {
        if (this.d == null) {
            this.d = new CopyOnWriteArrayList();
        }
        if (this.d.contains(rtkBluetoothManagerCallback)) {
            return;
        }
        this.d.add(rtkBluetoothManagerCallback);
    }

    public void removeManagerCallback(RtkBluetoothManagerCallback rtkBluetoothManagerCallback) {
        CopyOnWriteArrayList copyOnWriteArrayList = this.d;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.remove(rtkBluetoothManagerCallback);
        }
    }

    public void setInterruptPairRequest(boolean z) {
    }

    public void close() {
        ZLogger.v(this.a, "close()");
        Context context = this.c;
        if (context != null) {
            try {
                context = context;
                context.unregisterReceiver(this.f);
            } catch (Exception unused) {
                ZLogger.e(context.toString());
            }
        }
    }

    public boolean isBleSupported() {
        return this.c.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    public boolean isBleEnabled() {
        BluetoothAdapter bluetoothAdapter = this.e;
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    public boolean reset() throws NoSuchMethodException, SecurityException {
        b();
        unBondAllDevices();
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter == null) {
            ZLogger.w("BT is not initialized");
            return false;
        }
        if (!bluetoothAdapter.isEnabled()) {
            return false;
        }
        this.i = true;
        if (!this.b) {
            return false;
        }
        ZLogger.v("isNeedAutoEnableBt=" + this.i);
        return false;
    }

    public boolean startDiscovery(int i) {
        return startDiscovery(i, null);
    }

    @TargetApi(19)
    public boolean pair(byte[] bArr) {
        if (this.e == null) {
            return false;
        }
        byte[] bArr2 = {bArr[5], bArr[4], bArr[3], bArr[2], bArr[1], bArr[0]};
        if (this.a) {
            ZLogger.d("createBondMac=" + BluetoothHelper.formatAddress(bArr, true));
        }
        BluetoothDevice remoteDevice = this.e.getRemoteDevice(bArr2);
        int bondState = remoteDevice.getBondState();
        if (this.b) {
            ZLogger.d("attempt to createBond, state=" + Integer.toString(bondState));
        }
        return remoteDevice.createBond();
    }

    public boolean createBond(byte[] bArr) {
        return createBond(BluetoothHelper.convertMac(bArr));
    }

    public boolean unBondDevice(byte[] bArr) throws NoSuchMethodException, SecurityException {
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            if (!this.a) {
                return false;
            }
            ZLogger.d("bluetooth is not enabled");
            return false;
        }
        String strConvertMac = BluetoothHelper.convertMac(bArr);
        Set<BluetoothDevice> bondedDevices = this.e.getBondedDevices();
        if (bondedDevices == null || bondedDevices.size() <= 0) {
            return true;
        }
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            if (strConvertMac.compareToIgnoreCase(bluetoothDevice.getAddress()) == 0) {
                while (true) {
                    int bondState = bluetoothDevice.getBondState();
                    if (bondState == 10) {
                        return true;
                    }
                    if (bondState == 11) {
                        BluetoothDeviceImpl.cancelBondProcess(bluetoothDevice);
                    }
                    if (this.a) {
                        ZLogger.d("unBondDevice: " + BluetoothHelper.formatAddress(strConvertMac, true));
                    }
                    boolean zRemoveBond = BluetoothDeviceImpl.removeBond(bluetoothDevice);
                    if (this.a) {
                        ZLogger.d("removeBond finished:" + zRemoveBond);
                    }
                }
            }
        }
        return true;
    }

    public boolean unBondAllDevices() throws NoSuchMethodException, SecurityException {
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w("bluetooth is not enabled");
            return false;
        }
        Set<BluetoothDevice> bondedDevices = this.e.getBondedDevices();
        if (bondedDevices == null || bondedDevices.size() <= 0) {
            ZLogger.d("no bond device exist");
            return true;
        }
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            int i = 0;
            while (true) {
                if (i >= 10) {
                    break;
                }
                int bondState = bluetoothDevice.getBondState();
                if (bondState == 10) {
                    ZLogger.d(this.a, "already unbond: " + bluetoothDevice.getName());
                    break;
                }
                if (bondState == 11) {
                    boolean zCancelBondProcess = BluetoothDeviceImpl.cancelBondProcess(bluetoothDevice);
                    if (this.a) {
                        ZLogger.v(String.format(Locale.US, "cancelBondProcess(%d): %s, ret=%b", Integer.valueOf(i), BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true), Boolean.valueOf(zCancelBondProcess)));
                    }
                    if (zCancelBondProcess) {
                        break;
                    }
                    i++;
                } else {
                    if (bondState == 12) {
                        boolean zRemoveBond = BluetoothDeviceImpl.removeBond(bluetoothDevice);
                        if (this.a) {
                            ZLogger.v(String.format(Locale.US, "removeBond(%d): %s, ret=%b", Integer.valueOf(i), BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true), Boolean.valueOf(zRemoveBond)));
                        }
                        if (zRemoveBond) {
                            break;
                        }
                    } else {
                        continue;
                    }
                    i++;
                }
            }
        }
        return true;
    }

    public RtkBluetoothManager(Context context) {
        this.a = false;
        this.b = false;
        new a();
        this.c = context.getApplicationContext();
        this.a = RtkCore.DEBUG;
        this.b = RtkCore.VDBG;
        a();
    }

    public static RtkBluetoothManager getInstance() {
        return j;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Class<com.realsil.customer.core.bluetooth.RtkBluetoothManager>] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4 */
    public static void initial(Context context) {
        if (j == null) {
            ?? r0 = RtkBluetoothManager.class;
            synchronized (r0) {
                if (j == null) {
                    j = new RtkBluetoothManager(context);
                }
                r0 = r0;
            }
        }
    }

    public static void a(RtkBluetoothManager rtkBluetoothManager, Intent intent) {
        rtkBluetoothManager.getClass();
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (bluetoothDevice == null) {
            return;
        }
        if (rtkBluetoothManager.a) {
            ZLogger.d(String.format(Locale.US, "%s: action=%s", BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true), intent.getAction()));
        }
        CopyOnWriteArrayList copyOnWriteArrayList = rtkBluetoothManager.d;
        if (copyOnWriteArrayList != null) {
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ((RtkBluetoothManagerCallback) it.next()).onBleAclConnectionStateChanged(bluetoothDevice, true);
            }
        }
    }

    public static void b(RtkBluetoothManager rtkBluetoothManager, Intent intent) {
        rtkBluetoothManager.getClass();
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (bluetoothDevice == null) {
            return;
        }
        if (rtkBluetoothManager.a) {
            ZLogger.d(String.format(Locale.US, "%s: action=%s", BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true), intent.getAction()));
        }
        CopyOnWriteArrayList copyOnWriteArrayList = rtkBluetoothManager.d;
        if (copyOnWriteArrayList != null) {
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ((RtkBluetoothManagerCallback) it.next()).onBleAclConnectionStateChanged(bluetoothDevice, false);
            }
        }
    }

    public static void c(RtkBluetoothManager rtkBluetoothManager, Intent intent) {
        rtkBluetoothManager.getClass();
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (bluetoothDevice == null) {
            return;
        }
        int intExtra = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", 0);
        if (rtkBluetoothManager.a) {
            ZLogger.d(String.format(Locale.US, "%s: action=%s", BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true), intent.getAction()));
            ZLogger.v("android.bluetooth.device.extra.PAIRING_VARIANT>> " + BluetoothDeviceImpl.pairingVariantToString(intExtra) + " (" + intExtra + ")");
        }
        CopyOnWriteArrayList copyOnWriteArrayList = rtkBluetoothManager.d;
        if (copyOnWriteArrayList != null) {
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ((RtkBluetoothManagerCallback) it.next()).onPairingRequestNotify(bluetoothDevice, intExtra);
            }
        }
        if (bluetoothDevice.getBondState() == 12) {
            ZLogger.d("device already bonded: " + BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true));
        }
    }

    public static void d(RtkBluetoothManager rtkBluetoothManager, Intent intent) {
        rtkBluetoothManager.getClass();
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int intExtra = intent.getIntExtra("android.bluetooth.device.extra.PREVIOUS_BOND_STATE", -1);
        int intExtra2 = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE);
        int intExtra3 = intent.getIntExtra(EXTRA_REASON, -1);
        if (bluetoothDevice == null) {
            return;
        }
        ZLogger.d(String.format(Locale.US, "%s: action=%s, bondState:%d->%d, reason=%d", BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true), intent.getAction(), Integer.valueOf(intExtra), Integer.valueOf(intExtra2), Integer.valueOf(intExtra3)));
        CopyOnWriteArrayList copyOnWriteArrayList = rtkBluetoothManager.d;
        if (copyOnWriteArrayList != null) {
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ((RtkBluetoothManagerCallback) it.next()).onBondStateChanged(bluetoothDevice, intExtra2);
            }
        }
    }

    public static void e(RtkBluetoothManager rtkBluetoothManager, Intent intent) {
        rtkBluetoothManager.getClass();
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (bluetoothDevice == null) {
            return;
        }
        if (rtkBluetoothManager.a) {
            ZLogger.d(String.format(Locale.US, "%s: action=%s", BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true), intent.getAction()));
        }
        CopyOnWriteArrayList copyOnWriteArrayList = rtkBluetoothManager.d;
        if (copyOnWriteArrayList != null) {
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ((RtkBluetoothManagerCallback) it.next()).onAclConnectionStateChanged(bluetoothDevice, true);
            }
        }
    }

    public static void f(RtkBluetoothManager rtkBluetoothManager, Intent intent) {
        rtkBluetoothManager.getClass();
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (bluetoothDevice == null) {
            return;
        }
        if (rtkBluetoothManager.a) {
            ZLogger.d(String.format(Locale.US, "%s: action=%s", BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true), intent.getAction()));
        }
        CopyOnWriteArrayList copyOnWriteArrayList = rtkBluetoothManager.d;
        if (copyOnWriteArrayList != null) {
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ((RtkBluetoothManagerCallback) it.next()).onAclConnectionStateChanged(bluetoothDevice, false);
            }
        }
    }

    public boolean startDiscovery(int i, String str) {
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w("bluetooth is not supported or disabled");
            return false;
        }
        if (this.e.isDiscovering()) {
            this.e.cancelDiscovery();
        }
        if (this.a) {
            ZLogger.d("address=" + str + " , timeout=" + i);
        }
        this.e.startDiscovery();
        return true;
    }

    public boolean createBond(String str) {
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            if (!this.a) {
                return false;
            }
            ZLogger.d("bluetooth is not enabled");
            return false;
        }
        if (str == null) {
            if (!this.a) {
                return false;
            }
            ZLogger.d("mac cannot be null");
            return false;
        }
        if (this.a) {
            ZLogger.d("createBondMac=".concat(str));
        }
        BluetoothDevice remoteDevice = this.e.getRemoteDevice(str);
        int bondState = remoteDevice.getBondState();
        if (bondState == 12) {
            ZLogger.d("device already bonded: " + bondState);
            return true;
        }
        if (this.a) {
            ZLogger.d("attempt to createBond, state=" + Integer.toString(bondState));
        }
        return BluetoothDeviceImpl.createBond(remoteDevice);
    }

    public final boolean b() {
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w("bluetooth is not supported or disabled");
        }
        if (!this.e.isDiscovering()) {
            return true;
        }
        if (this.a) {
            ZLogger.d("stopInquiry()");
        }
        return this.e.cancelDiscovery();
    }

    public final void a() {
        Context context = this.c;
        if (context == null) {
            ZLogger.w("not intialized");
            return;
        }
        if (this.e == null) {
            BluetoothAdapter bluetoothAdapterA = com.realsil.customer.core.a.a.a(context);
            this.e = bluetoothAdapterA;
            if (bluetoothAdapterA == null) {
                ZLogger.w("Unable to obtain a BluetoothAdapter.");
                return;
            }
        }
        if (this.e == null) {
            ZLogger.w("mBluetoothAdapter == null");
            return;
        }
        this.f = new BluetoothBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction(BluetoothAdapterImpl.ACTION_BLE_ACL_CONNECTED);
        intentFilter.addAction(BluetoothAdapterImpl.ACTION_BLE_ACL_DISCONNECTED);
        intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED");
        this.c.registerReceiver(this.f, intentFilter);
    }
}
