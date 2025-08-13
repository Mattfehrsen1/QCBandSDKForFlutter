package com.realsil.customer.core.bluetooth.impl;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.os.Build;
import android.os.ParcelUuid;
import androidx.annotation.RequiresPermission;
import com.realsil.customer.core.logger.ZLogger;
import java.lang.reflect.Method;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/impl/BluetoothDeviceImpl.class */
public class BluetoothDeviceImpl {
    public static final int BATTERY_LEVEL_UNKNOWN = -1;
    public static final int PAIRING_VARIANT_PASSKEY = 1;
    public static final int PAIRING_VARIANT_CONSENT = 3;
    public static final int PAIRING_VARIANT_DISPLAY_PASSKEY = 4;
    public static final int PAIRING_VARIANT_DISPLAY_PIN = 5;
    public static final int PAIRING_VARIANT_OOB_CONSENT = 6;

    @SuppressLint({"NewApi"})
    public static boolean createBond(BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException {
        if (Build.VERSION.SDK_INT >= 19) {
            return bluetoothDevice.createBond();
        }
        try {
            Method method = bluetoothDevice.getClass().getMethod("createBond", null);
            if (method != null) {
                return ((Boolean) method.invoke(bluetoothDevice, new Object[0])).booleanValue();
            }
            return false;
        } catch (Exception e) {
            ZLogger.d("An exception occurred while creating bond: " + e.toString());
            return false;
        }
    }

    public static boolean cancelBondProcess(BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException {
        try {
            Method method = bluetoothDevice.getClass().getMethod("cancelBondProcess", null);
            if (method == null) {
                return false;
            }
            method.setAccessible(true);
            return ((Boolean) method.invoke(bluetoothDevice, null)).booleanValue();
        } catch (Exception e) {
            ZLogger.w("An exception occurred while cancelBondProcess : " + e.getMessage());
            return false;
        }
    }

    public static boolean removeBond(BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException {
        try {
            Method method = bluetoothDevice.getClass().getMethod("removeBond", null);
            if (method == null) {
                return false;
            }
            method.setAccessible(true);
            return ((Boolean) method.invoke(bluetoothDevice, null)).booleanValue();
        } catch (Exception e) {
            ZLogger.w("An exception occurred while removing bond information: " + e.getMessage());
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Class, java.lang.Class<android.bluetooth.BluetoothDevice>] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7, types: [boolean] */
    @RequiresPermission("android.permission.BLUETOOTH")
    public static boolean isConnected(BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException {
        if (bluetoothDevice == null) {
            return false;
        }
        ?? BooleanValue = BluetoothDevice.class;
        try {
            Method declaredMethod = BooleanValue.getDeclaredMethod("isConnected", null);
            declaredMethod.setAccessible(true);
            BooleanValue = ((Boolean) declaredMethod.invoke(bluetoothDevice, null)).booleanValue();
            return BooleanValue;
        } catch (Exception unused) {
            BooleanValue.printStackTrace();
            return false;
        }
    }

    public static String parseDeviceType(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "Unknown" : "DUAL(BR/EDR/LE)" : "LE" : "BR/EDR";
    }

    public static String pairingVariantToString(int i) {
        switch (i) {
            case 0:
                return "PAIRING_VARIANT_PIN";
            case 1:
                return "PAIRING_VARIANT_PASSKEY";
            case 2:
                return "PAIRING_VARIANT_PASSKEY_CONFIRMATION";
            case 3:
                return "PAIRING_VARIANT_CONSENT";
            case 4:
                return "PAIRING_VARIANT_DISPLAY_PASSKEY";
            case 5:
                return "PAIRING_VARIANT_DISPLAY_PIN";
            case 6:
                return "PAIRING_VARIANT_OOB_CONSENT";
            default:
                return "UNKNOWN";
        }
    }

    public static void dumpSupportedUuids(BluetoothDevice bluetoothDevice) {
        ParcelUuid[] uuids = bluetoothDevice.getUuids();
        if (uuids == null || uuids.length <= 0) {
            return;
        }
        StringBuilder sb = new StringBuilder("supported features (UUIDs)");
        for (ParcelUuid parcelUuid : uuids) {
            sb.append("\n\t" + parcelUuid.toString());
        }
        ZLogger.v(sb.toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v10, types: [boolean] */
    public static boolean createBond(BluetoothDevice bluetoothDevice, int i) throws NoSuchMethodException, SecurityException {
        ?? BooleanValue = bluetoothDevice;
        if (BooleanValue == 0) {
            return false;
        }
        try {
            Class<?> cls = bluetoothDevice.getClass();
            Class<?>[] clsArr = new Class[1];
            clsArr[0] = Integer.TYPE;
            Method method = cls.getMethod("createBond", clsArr);
            if (method == null) {
                return false;
            }
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(i);
            BooleanValue = ((Boolean) method.invoke(bluetoothDevice, objArr)).booleanValue();
            return BooleanValue;
        } catch (Exception unused) {
            BooleanValue.printStackTrace();
            return false;
        }
    }
}
