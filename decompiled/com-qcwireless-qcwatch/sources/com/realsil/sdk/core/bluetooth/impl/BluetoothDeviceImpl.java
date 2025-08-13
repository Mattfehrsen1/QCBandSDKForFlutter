package com.realsil.sdk.core.bluetooth.impl;

import android.bluetooth.BluetoothDevice;
import android.os.Build;
import android.os.ParcelUuid;
import com.realsil.sdk.core.a.a;
import com.realsil.sdk.core.logger.ZLogger;
import java.lang.reflect.Method;
import org.koin.core.instance.DefinitionInstance;

/* loaded from: classes3.dex */
public class BluetoothDeviceImpl {
    public static final int BATTERY_LEVEL_UNKNOWN = -1;
    public static final int PAIRING_VARIANT_CONSENT = 3;
    public static final int PAIRING_VARIANT_DISPLAY_PASSKEY = 4;
    public static final int PAIRING_VARIANT_DISPLAY_PIN = 5;
    public static final int PAIRING_VARIANT_OOB_CONSENT = 6;
    public static final int PAIRING_VARIANT_PASSKEY = 1;
    public static final int UNBOND_REASON_AUTH_CANCELED = 3;
    public static final int UNBOND_REASON_AUTH_FAILED = 1;
    public static final int UNBOND_REASON_AUTH_REJECTED = 2;
    public static final int UNBOND_REASON_AUTH_TIMEOUT = 6;
    public static final int UNBOND_REASON_DISCOVERY_IN_PROGRESS = 5;
    public static final int UNBOND_REASON_REMOTE_AUTH_CANCELED = 8;
    public static final int UNBOND_REASON_REMOTE_DEVICE_DOWN = 4;
    public static final int UNBOND_REASON_REMOVED = 9;
    public static final int UNBOND_REASON_REPEATED_ATTEMPTS = 7;

    public static boolean cancelBondProcess(BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException {
        try {
            Method method = bluetoothDevice.getClass().getMethod("cancelBondProcess", null);
            if (method == null) {
                return false;
            }
            method.setAccessible(true);
            return ((Boolean) method.invoke(bluetoothDevice, null)).booleanValue();
        } catch (Exception e) {
            StringBuilder sbA = a.a("An exception occurred while cancelBondProcess : ");
            sbA.append(e.getMessage());
            ZLogger.w(sbA.toString());
            return false;
        }
    }

    public static boolean cancelPairingUserInput(BluetoothDevice bluetoothDevice) throws Exception {
        return ((Boolean) bluetoothDevice.getClass().getMethod("cancelPairingUserInput", new Class[0]).invoke(bluetoothDevice, new Object[0])).booleanValue();
    }

    public static boolean createBond(BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException {
        if (Build.VERSION.SDK_INT >= 19) {
            return bluetoothDevice.createBond();
        }
        try {
            Method method = bluetoothDevice.getClass().getMethod("createBond", null);
            if (method != null) {
                return ((Boolean) method.invoke(bluetoothDevice, new Object[0])).booleanValue();
            }
        } catch (Exception e) {
            StringBuilder sbA = a.a("An exception occurred while creating bond: ");
            sbA.append(e.toString());
            ZLogger.d(sbA.toString());
        }
        return false;
    }

    public static void dumpSupportedUuids(BluetoothDevice bluetoothDevice) {
        ParcelUuid[] uuids = bluetoothDevice.getUuids();
        if (uuids == null || uuids.length <= 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("supported features (UUIDs)");
        for (ParcelUuid parcelUuid : uuids) {
            StringBuilder sbA = a.a(DefinitionInstance.ERROR_SEPARATOR);
            sbA.append(parcelUuid.toString());
            sb.append(sbA.toString());
        }
        ZLogger.v(sb.toString());
    }

    public static boolean isConnected(BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException {
        if (bluetoothDevice == null) {
            return false;
        }
        try {
            Method declaredMethod = BluetoothDevice.class.getDeclaredMethod("isConnected", null);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(bluetoothDevice, null)).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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

    public static String parseDeviceType(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "Unknown" : "DUAL(BR/EDR/LE)" : "LE" : "BR/EDR";
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
            StringBuilder sbA = a.a("An exception occurred while removing bond information: ");
            sbA.append(e.getMessage());
            ZLogger.w(sbA.toString());
            return false;
        }
    }

    public static boolean setPassKey(BluetoothDevice bluetoothDevice, int i) {
        try {
            return ((Boolean) bluetoothDevice.getClass().getDeclaredMethod("setPasskey", Integer.TYPE).invoke(bluetoothDevice, Integer.valueOf(i))).booleanValue();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        } catch (SecurityException e2) {
            e2.printStackTrace();
            return false;
        } catch (Exception e3) {
            e3.printStackTrace();
            return false;
        }
    }

    public static boolean createBond(BluetoothDevice bluetoothDevice, int i) throws NoSuchMethodException, SecurityException {
        if (bluetoothDevice == null) {
            return false;
        }
        try {
            Method method = bluetoothDevice.getClass().getMethod("createBond", Integer.TYPE);
            if (method != null) {
                return ((Boolean) method.invoke(bluetoothDevice, Integer.valueOf(i))).booleanValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
