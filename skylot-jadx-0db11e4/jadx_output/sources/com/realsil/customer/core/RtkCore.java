package com.realsil.customer.core;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import com.realsil.customer.bbpro.core.BuildConfig;
import com.realsil.customer.core.bluetooth.BluetoothProfileManager;
import com.realsil.customer.core.bluetooth.GlobalGatt;
import com.realsil.customer.core.bluetooth.connection.le.BluetoothGattClientManagerImpl;
import com.realsil.customer.core.logger.ZLogger;
import java.util.Arrays;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/RtkCore.class */
public final class RtkCore {
    public static Context a;
    public static boolean DEBUG = false;
    public static boolean VDBG = false;

    public static synchronized void initialize(Context context, @NonNull RtkConfigure rtkConfigure) {
        if (a == null) {
            a = context.getApplicationContext();
        }
        ZLogger.v(true, String.format("%s:%s:%s", BuildConfig.GROUP_ID, "rtk-core", "unspecified"));
        DEBUG = rtkConfigure.isDebugEnabled();
        VDBG = rtkConfigure.isDevModeEnabled();
        ZLogger.initialize(rtkConfigure.getLogTag(), rtkConfigure.isPrintLog(), rtkConfigure.getGlobalLogLevel());
        ZLogger.d(rtkConfigure.toString());
        if (GlobalGatt.getInstance() == null) {
            GlobalGatt.initial(a);
        }
        if (BluetoothGattClientManagerImpl.getInstance() == null) {
            BluetoothGattClientManagerImpl.initial(a);
        }
        BluetoothProfileManager.initial(a);
        StringBuilder sb = new StringBuilder("DeviceInfo{");
        StringBuilder sb2 = new StringBuilder("SDK_INT: ");
        int i = Build.VERSION.SDK_INT;
        sb.append(sb2.append(i).toString());
        sb.append("\nDevice name: " + Build.DEVICE);
        sb.append("\nAndroid Version: " + Build.VERSION.RELEASE);
        sb.append("\nManufacture: " + Build.MANUFACTURER);
        sb.append("\nModel: " + Build.MODEL);
        if (i >= 21) {
            sb.append("\nsupportedABIS: " + Arrays.toString(Build.SUPPORTED_ABIS));
        } else {
            sb.append("\ncpuABI: " + Build.CPU_ABI);
        }
        sb.append("}");
        ZLogger.d(sb.toString());
    }

    public static boolean isBluetoothSupported() {
        return BluetoothGattClientManagerImpl.getInstance().isBluetoothSupported();
    }

    public static String getVersion() {
        return "unspecified";
    }
}
