package com.realsil.customer.core.c;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.ParcelUuid;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.realsil.customer.core.bluetooth.impl.BluetoothClassImpl;
import com.realsil.customer.core.bluetooth.scanner.ScannerCallback;
import com.realsil.customer.core.bluetooth.scanner.ScannerParams;
import com.realsil.customer.core.bluetooth.utils.BluetoothHelper;
import com.realsil.customer.core.bluetooth.utils.BluetoothUuid;
import com.realsil.customer.core.logger.ZLogger;
import com.realsil.customer.core.utility.DataConverter;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/c/b.class */
public class b extends com.realsil.customer.core.c.a {
    public final a q;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/c/b$a.class */
    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.bluetooth.device.action.FOUND".equals(action) || "android.bluetooth.device.action.CLASS_CHANGED".equals(action)) {
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                short shortExtra = intent.getShortExtra("android.bluetooth.device.extra.RSSI", (short) 0);
                if (bluetoothDevice != null) {
                    if (b.this.b) {
                        ZLogger.v(String.format("%s %s", action, BluetoothHelper.dumpBluetoothDevice(bluetoothDevice)));
                    }
                    b.this.a(bluetoothDevice, shortExtra, null);
                    return;
                } else {
                    if (b.this.b) {
                        ZLogger.v(String.format("%s", action));
                        return;
                    }
                    return;
                }
            }
            if (!"android.bluetooth.device.action.NAME_CHANGED".equals(action) && !"android.bluetooth.device.action.UUID".equals(action)) {
                if ("android.bluetooth.adapter.action.DISCOVERY_STARTED".equals(action)) {
                    b.this.a(2);
                    return;
                } else {
                    if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(action)) {
                        b.this.a(3);
                        return;
                    }
                    return;
                }
            }
            BluetoothDevice bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            short shortExtra2 = intent.getShortExtra("android.bluetooth.device.extra.RSSI", (short) 0);
            if (bluetoothDevice2 == null) {
                if (b.this.b) {
                    ZLogger.v(String.format("%s", action));
                }
            } else {
                if (b.this.b) {
                    ZLogger.v(String.format("%s %s/%s", action, bluetoothDevice2.getName(), bluetoothDevice2.toString()));
                }
                b bVar = b.this;
                if (bVar.i == 2) {
                    bVar.a(bluetoothDevice2, shortExtra2, null);
                }
            }
        }
    }

    public b(Context context) {
        this(context, null, null, null);
    }

    @Override // com.realsil.customer.core.c.a
    public final boolean b() {
        if (!super.b()) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.device.action.FOUND");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        intentFilter.addAction("android.bluetooth.device.action.UUID");
        this.c.registerReceiver(this.q, intentFilter);
        ZLogger.v(this.b, "scanner initialized");
        return true;
    }

    @Override // com.realsil.customer.core.c.a
    public void onDestroy() {
        Context context = this.c;
        if (context != null) {
            try {
                context.unregisterReceiver(this.q);
            } catch (Exception e) {
                ZLogger.w(this.b, e.toString());
            }
        }
        super.onDestroy();
    }

    @Override // com.realsil.customer.core.c.a
    public boolean startScan() {
        if (!d()) {
            return true;
        }
        if (this.h.isDiscovering()) {
            this.h.cancelDiscovery();
        }
        if (this.b) {
            ZLogger.v(this.a, "startDiscovery for " + this.d.toString());
        } else {
            ZLogger.v(this.a, "startDiscovery for " + this.d.getScanPeriod() + "ms");
        }
        if (this.h.startDiscovery()) {
            c();
            return true;
        }
        ZLogger.d("startDiscovery failed");
        stopScan();
        return false;
    }

    @Override // com.realsil.customer.core.c.a
    public final boolean e() {
        if (this.h.isDiscovering()) {
            ZLogger.v(this.b, "cancelDiscovery");
            if (!this.h.cancelDiscovery()) {
                ZLogger.d("cancelDiscovery failed");
                return false;
            }
        }
        a(0);
        return true;
    }

    @Override // com.realsil.customer.core.c.a
    public final boolean a(@NonNull BluetoothDevice bluetoothDevice) {
        if (Build.VERSION.SDK_INT >= 18) {
            int type = bluetoothDevice.getType();
            if (this.d.getScanMode() == 33) {
                if (type != 1) {
                    if (!this.b) {
                        return false;
                    }
                    ZLogger.v(String.format(Locale.US, "filter, invalid type: %d, expect type is %d", Integer.valueOf(type), 1));
                    return false;
                }
            } else if (this.d.getScanMode() == 32 && type != 1 && type != 3 && type != 0) {
                if (!this.b) {
                    return false;
                }
                ZLogger.v(String.format(Locale.US, "filter, invalid type: %d, expect type is %d/%d/%d", Integer.valueOf(type), 0, 1, 3));
                return false;
            }
        }
        if (!b(bluetoothDevice)) {
            return false;
        }
        if (!TextUtils.isEmpty(this.d.getAddressFilter()) && !DataConverter.equals(this.d.getAddressFilter(), bluetoothDevice.getAddress())) {
            if (!this.b) {
                return false;
            }
            ZLogger.v("address not match:" + BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true));
            return false;
        }
        ParcelUuid[] uuids = bluetoothDevice.getUuids();
        if (1 == this.d.getFilterProfile()) {
            BluetoothClass bluetoothClass = bluetoothDevice.getBluetoothClass();
            if (bluetoothClass.getMajorDeviceClass() != 1024 && (BluetoothClassImpl.doesClassMatch(bluetoothClass, 0) || BluetoothClassImpl.doesClassMatch(bluetoothClass, 1))) {
                if (!this.b) {
                    return false;
                }
                ZLogger.v("major device class filter failed");
                return false;
            }
            if (bluetoothDevice.getBondState() == 12 && !BluetoothUuid.containsAnyUuid(uuids, BluetoothUuid.HEADSET_PROFILE_UUIDS)) {
                if (!this.b) {
                    return false;
                }
                ZLogger.v("profile filter failed");
                return false;
            }
        }
        if (bluetoothDevice.getBondState() != 12 || BluetoothUuid.containsAllUuids(uuids, this.d.getFilterUuids())) {
            return true;
        }
        if (!this.b) {
            return false;
        }
        ZLogger.v("uuid filter failed");
        return false;
    }

    public b(Context context, ScannerParams scannerParams, ScannerCallback scannerCallback) {
        this(context, null, scannerParams, scannerCallback);
    }

    public b(Context context, Handler handler, ScannerParams scannerParams, ScannerCallback scannerCallback) {
        this.q = new a();
        this.c = context.getApplicationContext();
        this.g = handler;
        this.d = scannerParams;
        this.e = scannerCallback;
        b();
    }
}
