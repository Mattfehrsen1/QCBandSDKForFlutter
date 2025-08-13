package com.realsil.customer.core.a;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import com.realsil.customer.core.logger.ZLogger;
import java.util.Objects;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/a/a.class */
public final class a {
    public static boolean a(@NonNull String str) {
        Objects.requireNonNull(str);
        return BluetoothAdapter.checkBluetoothAddress(str) && (Integer.parseInt(str.split(":")[0], 16) & 192) == 192;
    }

    public static BluetoothAdapter a(Context context) {
        if (context == null) {
            return BluetoothAdapter.getDefaultAdapter();
        }
        if (Build.VERSION.SDK_INT >= 18) {
            BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
            if (bluetoothManager == null) {
                ZLogger.w("Unable to initialize BluetoothManager.");
                return BluetoothAdapter.getDefaultAdapter();
            }
            return bluetoothManager.getAdapter();
        }
        return BluetoothAdapter.getDefaultAdapter();
    }
}
