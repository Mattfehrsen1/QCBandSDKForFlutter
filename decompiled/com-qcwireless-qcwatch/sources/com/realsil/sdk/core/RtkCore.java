package com.realsil.sdk.core;

import android.content.Context;
import android.os.Build;
import com.realsil.sdk.bbpro.core.BuildConfig;
import com.realsil.sdk.core.a.a;
import com.realsil.sdk.core.bluetooth.BluetoothProfileManager;
import com.realsil.sdk.core.bluetooth.GlobalGatt;
import com.realsil.sdk.core.bluetooth.connection.le.BluetoothGattManager;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.Arrays;

/* loaded from: classes3.dex */
public final class RtkCore {
    public static boolean DEBUG = false;
    public static boolean VDBG = false;
    public static Context a;

    public static String getVersion() {
        return "1.2.20";
    }

    public static synchronized void initialize(Context context, RtkConfigure rtkConfigure) {
        if (a == null) {
            a = context.getApplicationContext();
        }
        ZLogger.v(true, String.format("%s:%s:%s", BuildConfig.GROUP_ID, "rtk-core", "1.2.20"));
        DEBUG = rtkConfigure.isDebugEnabled();
        ZLogger.initialize(rtkConfigure.getLogTag(), rtkConfigure.isPrintLog(), rtkConfigure.getGlobalLogLevel());
        ZLogger.d(rtkConfigure.toString());
        if (GlobalGatt.getInstance() == null) {
            GlobalGatt.initial(a);
        }
        if (BluetoothGattManager.getInstance() == null) {
            BluetoothGattManager.initial(a);
        }
        BluetoothProfileManager.initial(a);
        StringBuilder sb = new StringBuilder();
        sb.append("DeviceInfo{");
        StringBuilder sbA = a.a("SDK_INT: ");
        int i = Build.VERSION.SDK_INT;
        sbA.append(i);
        sb.append(sbA.toString());
        sb.append("\nDevice name: " + Build.DEVICE);
        sb.append("\nAndroid Version: " + Build.VERSION.RELEASE);
        sb.append("\nManufacture: " + Build.MANUFACTURER);
        sb.append("\nModel: " + Build.MODEL);
        if (i >= 21) {
            StringBuilder sbA2 = a.a("\nsupportedABIS: ");
            sbA2.append(Arrays.toString(Build.SUPPORTED_ABIS));
            sb.append(sbA2.toString());
        } else {
            StringBuilder sbA3 = a.a("\ncpuABI: ");
            sbA3.append(Build.CPU_ABI);
            sb.append(sbA3.toString());
        }
        sb.append("}");
        ZLogger.d(sb.toString());
    }

    public static boolean isBluetoothSupported() {
        return BluetoothGattManager.getInstance().isBluetoothSupported();
    }
}
