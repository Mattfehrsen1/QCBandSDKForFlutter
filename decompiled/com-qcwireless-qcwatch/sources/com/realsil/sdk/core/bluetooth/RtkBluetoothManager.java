package com.realsil.sdk.core.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import androidx.work.WorkRequest;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.bluetooth.impl.BluetoothAdapterImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes3.dex */
public final class RtkBluetoothManager {
    public static final String EXTRA_REASON = "android.bluetooth.device.extra.REASON";
    public static final int INDICATOR_ACL = 2;
    public static final int INDICATOR_BOND = 4;
    public static final int INDICATOR_BT = 1;
    public static final int INDICATOR_FULL = 255;
    public static RtkBluetoothManager l;
    public boolean a;
    public boolean b;
    public Context c;
    public List<RtkBluetoothManagerCallback> d;
    public BluetoothManager e;
    public BluetoothAdapter f;
    public BluetoothBroadcastReceiver g = null;
    public Object h = new Object();
    public Object i = new Object();
    public boolean j = false;
    public final a k = new a();

    public class BluetoothBroadcastReceiver extends BroadcastReceiver {
        public BluetoothBroadcastReceiver() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Removed duplicated region for block: B:4:0x0016  */
        /* JADX WARN: Type inference failed for: r9v5, types: [java.util.List<com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void onReceive(android.content.Context r9, android.content.Intent r10) {
            /*
                Method dump skipped, instructions count: 392
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.RtkBluetoothManager.BluetoothBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ZLogger.d("scan delay time reached");
            RtkBluetoothManager.this.b();
        }
    }

    public RtkBluetoothManager(Context context) {
        this.a = false;
        this.b = false;
        this.c = context.getApplicationContext();
        this.a = RtkCore.DEBUG;
        this.b = RtkCore.VDBG;
        a();
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [java.util.List<com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback>, java.util.concurrent.CopyOnWriteArrayList] */
    public static void a(RtkBluetoothManager rtkBluetoothManager, Intent intent) {
        rtkBluetoothManager.getClass();
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (bluetoothDevice == null) {
            return;
        }
        if (rtkBluetoothManager.a) {
            ZLogger.d(String.format(Locale.US, "%s: action=%s", BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true), intent.getAction()));
        }
        ?? r6 = rtkBluetoothManager.d;
        if (r6 != 0) {
            Iterator it = r6.iterator();
            while (it.hasNext()) {
                ((RtkBluetoothManagerCallback) it.next()).onBleAclConnectionStateChanged(bluetoothDevice, true);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [java.util.List<com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback>, java.util.concurrent.CopyOnWriteArrayList] */
    public static void b(RtkBluetoothManager rtkBluetoothManager, Intent intent) {
        rtkBluetoothManager.getClass();
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (bluetoothDevice == null) {
            return;
        }
        if (rtkBluetoothManager.a) {
            ZLogger.d(String.format(Locale.US, "%s: action=%s", BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true), intent.getAction()));
        }
        ?? r6 = rtkBluetoothManager.d;
        if (r6 != 0) {
            Iterator it = r6.iterator();
            while (it.hasNext()) {
                ((RtkBluetoothManagerCallback) it.next()).onBleAclConnectionStateChanged(bluetoothDevice, false);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r7v1, types: [java.util.List<com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback>, java.util.concurrent.CopyOnWriteArrayList] */
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
        ?? r7 = rtkBluetoothManager.d;
        if (r7 != 0) {
            Iterator it = r7.iterator();
            while (it.hasNext()) {
                ((RtkBluetoothManagerCallback) it.next()).onPairingRequestNotify(bluetoothDevice, intExtra);
            }
        }
        if (bluetoothDevice.getBondState() == 12) {
            StringBuilder sbA = com.realsil.sdk.core.a.a.a("device already bonded: ");
            sbA.append(BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true));
            ZLogger.d(sbA.toString());
        }
    }

    /* JADX WARN: Type inference failed for: r9v1, types: [java.util.List<com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback>, java.util.concurrent.CopyOnWriteArrayList] */
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
        ?? r9 = rtkBluetoothManager.d;
        if (r9 != 0) {
            Iterator it = r9.iterator();
            while (it.hasNext()) {
                ((RtkBluetoothManagerCallback) it.next()).onBondStateChanged(bluetoothDevice, intExtra2);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [java.util.List<com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback>, java.util.concurrent.CopyOnWriteArrayList] */
    public static void e(RtkBluetoothManager rtkBluetoothManager, Intent intent) {
        rtkBluetoothManager.getClass();
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (bluetoothDevice == null) {
            return;
        }
        if (rtkBluetoothManager.a) {
            ZLogger.d(String.format(Locale.US, "%s: action=%s", BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true), intent.getAction()));
        }
        ?? r6 = rtkBluetoothManager.d;
        if (r6 != 0) {
            Iterator it = r6.iterator();
            while (it.hasNext()) {
                ((RtkBluetoothManagerCallback) it.next()).onAclConnectionStateChanged(bluetoothDevice, true);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [java.util.List<com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback>, java.util.concurrent.CopyOnWriteArrayList] */
    public static void f(RtkBluetoothManager rtkBluetoothManager, Intent intent) {
        rtkBluetoothManager.getClass();
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (bluetoothDevice == null) {
            return;
        }
        if (rtkBluetoothManager.a) {
            ZLogger.d(String.format(Locale.US, "%s: action=%s", BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true), intent.getAction()));
        }
        ?? r6 = rtkBluetoothManager.d;
        if (r6 != 0) {
            Iterator it = r6.iterator();
            while (it.hasNext()) {
                ((RtkBluetoothManagerCallback) it.next()).onAclConnectionStateChanged(bluetoothDevice, false);
            }
        }
    }

    public static RtkBluetoothManager getInstance() {
        return l;
    }

    public static void initial(Context context) {
        if (l == null) {
            synchronized (RtkBluetoothManager.class) {
                if (l == null) {
                    l = new RtkBluetoothManager(context);
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.List<com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback>, java.util.concurrent.CopyOnWriteArrayList] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List<com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback>, java.util.concurrent.CopyOnWriteArrayList] */
    public void addManagerCallback(RtkBluetoothManagerCallback rtkBluetoothManagerCallback) {
        if (this.d == null) {
            this.d = new CopyOnWriteArrayList();
        }
        if (this.d.contains(rtkBluetoothManagerCallback)) {
            return;
        }
        this.d.add(rtkBluetoothManagerCallback);
    }

    public void close() {
        ZLogger.v(this.a, "close()");
        Context context = this.c;
        if (context != null) {
            try {
                context.unregisterReceiver(this.g);
            } catch (Exception e) {
                ZLogger.e(e.toString());
            }
        }
    }

    public boolean createBond(byte[] bArr) {
        return createBond(BluetoothHelper.convertMac(bArr));
    }

    public boolean disableBT() {
        return disableBT(true);
    }

    public boolean enableBT() {
        return enableBT(true);
    }

    public boolean isBleEnabled() {
        BluetoothManager bluetoothManager = this.e;
        BluetoothAdapter adapter = bluetoothManager != null ? bluetoothManager.getAdapter() : null;
        return adapter != null && adapter.isEnabled();
    }

    public boolean isBleSupported() {
        return this.c.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    public boolean pair(byte[] bArr) {
        if (this.f == null) {
            return false;
        }
        byte[] bArr2 = {bArr[5], bArr[4], bArr[3], bArr[2], bArr[1], bArr[0]};
        if (this.a) {
            StringBuilder sbA = com.realsil.sdk.core.a.a.a("createBondMac=");
            sbA.append(BluetoothHelper.formatAddress(bArr, true));
            ZLogger.d(sbA.toString());
        }
        BluetoothDevice remoteDevice = this.f.getRemoteDevice(bArr2);
        int bondState = remoteDevice.getBondState();
        if (this.b) {
            StringBuilder sbA2 = com.realsil.sdk.core.a.a.a("attempt to createBond, state=");
            sbA2.append(Integer.toString(bondState));
            ZLogger.d(sbA2.toString());
        }
        return remoteDevice.createBond();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List<com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback>, java.util.concurrent.CopyOnWriteArrayList] */
    public void removeManagerCallback(RtkBluetoothManagerCallback rtkBluetoothManagerCallback) {
        ?? r0 = this.d;
        if (r0 != 0) {
            r0.remove(rtkBluetoothManagerCallback);
        }
    }

    public boolean reset() throws InterruptedException, NoSuchMethodException, SecurityException {
        b();
        unBondAllDevices();
        BluetoothAdapter bluetoothAdapter = this.f;
        if (bluetoothAdapter == null) {
            ZLogger.w("BT is not initialized");
            return false;
        }
        if (!bluetoothAdapter.isEnabled()) {
            return enableBT();
        }
        this.j = true;
        if (this.b) {
            StringBuilder sbA = com.realsil.sdk.core.a.a.a("isNeedAutoEnableBt=");
            sbA.append(this.j);
            ZLogger.v(sbA.toString());
        }
        disableBT();
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            ZLogger.w(e.toString());
        }
        if (this.f.isEnabled()) {
            if (this.a) {
                ZLogger.d("BT already enabled");
            }
            return true;
        }
        boolean zEnableBT = enableBT();
        if (this.a) {
            ZLogger.d("enableBT: " + zEnableBT);
        }
        return zEnableBT;
    }

    public void setInterruptPairRequest(boolean z) {
    }

    public boolean startDiscovery(int i) {
        return startDiscovery(i, null);
    }

    public boolean unBondAllDevices() throws NoSuchMethodException, SecurityException {
        BluetoothAdapter bluetoothAdapter = this.f;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w("bluetooth is not enabled");
            return false;
        }
        Set<BluetoothDevice> bondedDevices = this.f.getBondedDevices();
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
                    boolean z = this.a;
                    StringBuilder sbA = com.realsil.sdk.core.a.a.a("already unbond: ");
                    sbA.append(bluetoothDevice.getName());
                    ZLogger.d(z, sbA.toString());
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

    public boolean unBondDevice(byte[] bArr) throws NoSuchMethodException, SecurityException {
        BluetoothAdapter bluetoothAdapter = this.f;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            if (!this.a) {
                return false;
            }
            ZLogger.d("bluetooth is not enabled");
            return false;
        }
        String strConvertMac = BluetoothHelper.convertMac(bArr);
        Set<BluetoothDevice> bondedDevices = this.f.getBondedDevices();
        if (bondedDevices != null && bondedDevices.size() > 0) {
            Iterator<BluetoothDevice> it = bondedDevices.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                BluetoothDevice next = it.next();
                if (strConvertMac.compareToIgnoreCase(next.getAddress()) == 0) {
                    while (true) {
                        int bondState = next.getBondState();
                        if (bondState == 10) {
                            break;
                        }
                        if (bondState == 11) {
                            BluetoothDeviceImpl.cancelBondProcess(next);
                        }
                        if (this.a) {
                            StringBuilder sbA = com.realsil.sdk.core.a.a.a("unBondDevice: ");
                            sbA.append(BluetoothHelper.formatAddress(strConvertMac, true));
                            ZLogger.d(sbA.toString());
                        }
                        boolean zRemoveBond = BluetoothDeviceImpl.removeBond(next);
                        if (this.a) {
                            ZLogger.d("removeBond finished:" + zRemoveBond);
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean createBond(String str) {
        BluetoothAdapter bluetoothAdapter = this.f;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            if (this.a) {
                ZLogger.d("bluetooth is not enabled");
            }
            return false;
        }
        if (str == null) {
            if (this.a) {
                ZLogger.d("mac cannot be null");
            }
            return false;
        }
        if (this.a) {
            ZLogger.d("createBondMac=" + str);
        }
        BluetoothDevice remoteDevice = this.f.getRemoteDevice(str);
        int bondState = remoteDevice.getBondState();
        if (bondState == 12) {
            ZLogger.d("device already bonded: " + bondState);
            return true;
        }
        if (this.a) {
            StringBuilder sbA = com.realsil.sdk.core.a.a.a("attempt to createBond, state=");
            sbA.append(Integer.toString(bondState));
            ZLogger.d(sbA.toString());
        }
        return BluetoothDeviceImpl.createBond(remoteDevice);
    }

    public boolean disableBT(boolean z) {
        BluetoothAdapter bluetoothAdapter = this.f;
        if (bluetoothAdapter == null) {
            ZLogger.w("BT is not initialized..!");
            return false;
        }
        if (bluetoothAdapter.getState() == 10) {
            ZLogger.w("BT already OFF");
            return true;
        }
        if (!this.f.disable()) {
            ZLogger.d("disable BT failed");
            return false;
        }
        if (!z) {
            return true;
        }
        synchronized (this.i) {
            try {
                if (this.a) {
                    ZLogger.d("wait BT disable...");
                }
                this.i.wait(WorkRequest.MIN_BACKOFF_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
                ZLogger.e(e.toString());
            }
        }
        if (this.f.isEnabled()) {
            ZLogger.w("BT disable failed");
            return false;
        }
        if (this.a) {
            ZLogger.d("BT disable success");
        }
        return true;
    }

    public boolean enableBT(boolean z) {
        BluetoothAdapter bluetoothAdapter = this.f;
        if (bluetoothAdapter == null) {
            ZLogger.w("mBluetoothAdapter == null");
            return false;
        }
        if (bluetoothAdapter.getState() == 12) {
            if (this.a) {
                ZLogger.d("BT already on");
            }
            return true;
        }
        this.j = false;
        if (this.b) {
            StringBuilder sbA = com.realsil.sdk.core.a.a.a("isNeedAutoEnableBt=");
            sbA.append(this.j);
            ZLogger.v(sbA.toString());
        }
        if (!this.f.enable()) {
            ZLogger.w("BT enable fail");
            return false;
        }
        if (!z) {
            return true;
        }
        synchronized (this.h) {
            try {
                if (this.a) {
                    ZLogger.v("wait BT enable...");
                }
                this.h.wait(WorkRequest.MIN_BACKOFF_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
                ZLogger.e(e.toString());
            }
        }
        if (!this.f.isEnabled()) {
            ZLogger.d("BT enable fail");
            return false;
        }
        if (this.a) {
            ZLogger.d("BT enable success");
        }
        return true;
    }

    public boolean startDiscovery(int i, String str) {
        BluetoothAdapter bluetoothAdapter = this.f;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w("bluetooth is not supported or disabled");
            return false;
        }
        if (this.f.isDiscovering()) {
            this.f.cancelDiscovery();
        }
        if (this.a) {
            ZLogger.d("address=" + str + " , timeout=" + i);
        }
        this.f.startDiscovery();
        return true;
    }

    public final boolean a() {
        Context context = this.c;
        if (context == null) {
            ZLogger.w("not intialized");
            return false;
        }
        if (this.f == null) {
            if (Build.VERSION.SDK_INT >= 18) {
                BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
                this.e = bluetoothManager;
                if (bluetoothManager == null) {
                    ZLogger.w("Unable to initialize BluetoothManager.");
                    return false;
                }
                this.f = bluetoothManager.getAdapter();
            } else {
                this.f = BluetoothAdapter.getDefaultAdapter();
            }
            if (this.f == null) {
                ZLogger.w("Unable to obtain a BluetoothAdapter.");
                return false;
            }
        }
        if (this.f == null) {
            ZLogger.w("mBluetoothAdapter == null");
            return true;
        }
        this.g = new BluetoothBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction(BluetoothAdapterImpl.ACTION_BLE_ACL_CONNECTED);
        intentFilter.addAction(BluetoothAdapterImpl.ACTION_BLE_ACL_DISCONNECTED);
        intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED");
        this.c.registerReceiver(this.g, intentFilter);
        return true;
    }

    public final boolean b() {
        BluetoothAdapter bluetoothAdapter = this.f;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w("bluetooth is not supported or disabled");
        }
        if (!this.f.isDiscovering()) {
            return true;
        }
        if (this.a) {
            ZLogger.d("stopInquiry()");
        }
        return this.f.cancelDiscovery();
    }
}
