package com.qcwireless.qc_utils.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Build;

/* loaded from: classes3.dex */
public class BluetoothUtils {
    public static boolean isEnabledBluetooth(Context context) {
        try {
            BluetoothAdapter adapter = ((BluetoothManager) context.getSystemService("bluetooth")).getAdapter();
            if (context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le") && adapter != null) {
                return adapter.isEnabled();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= 21;
    }
}
