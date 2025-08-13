package com.oudmon.ble.base.util;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Build;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/util/BluetoothUtils.class */
public class BluetoothUtils {
    @SuppressLint({"MissingPermission"})
    public static boolean isEnabledBluetooth(Context context) {
        try {
            BluetoothAdapter adapter = ((BluetoothManager) context.getSystemService("bluetooth")).getAdapter();
            if (!context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le") || adapter == null) {
                return false;
            }
            return adapter.isEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= 21;
    }
}
