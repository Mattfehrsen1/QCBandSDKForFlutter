package com.realsil.customer.core.bluetooth.impl;

import android.bluetooth.BluetoothAdapter;
import com.realsil.customer.core.logger.ZLogger;
import java.lang.reflect.Method;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/impl/BluetoothAdapterImpl.class */
public class BluetoothAdapterImpl {
    public static final String ACTION_BLE_ACL_CONNECTED = "android.bluetooth.adapter.action.BLE_ACL_CONNECTED";
    public static final String ACTION_BLE_ACL_DISCONNECTED = "android.bluetooth.adapter.action.BLE_ACL_DISCONNECTED";

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v8, types: [int] */
    public static int getConnectionState(BluetoothAdapter bluetoothAdapter) throws NoSuchMethodException, SecurityException {
        ?? IntValue = bluetoothAdapter;
        if (IntValue == 0) {
            ZLogger.w("BT is not enabled");
            return 0;
        }
        try {
            Method method = bluetoothAdapter.getClass().getMethod("getConnectionState", null);
            method.setAccessible(true);
            IntValue = ((Integer) method.invoke(bluetoothAdapter, null)).intValue();
            return IntValue;
        } catch (Exception unused) {
            IntValue.printStackTrace();
            return 0;
        }
    }
}
