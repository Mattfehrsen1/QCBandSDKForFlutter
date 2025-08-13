package com.realsil.customer.core.bluetooth.impl;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.os.Build;
import androidx.annotation.RequiresPermission;
import com.realsil.customer.core.bluetooth.utils.BluetoothHelper;
import com.realsil.customer.core.logger.ZLogger;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/impl/BluetoothProfileImpl.class */
public class BluetoothProfileImpl {
    public static final String CLASS_NAME_BLUETOOTH_A2DP = "android.bluetooth.BluetoothA2dp";
    public static int PRIORITY_AUTO_CONNECT = 1000;
    public static final int HID_HOST = 4;
    public static final int PAN = 5;
    public static final int PBAP = 6;
    public static final int MAP = 9;
    public static final int A2DP_SINK = 11;
    public static final int AVRCP_CONTROLLER = 12;
    public static final int AVRCP = 13;
    public static final int HEADSET_CLIENT = 16;
    public static final int PBAP_CLIENT = 17;
    public static final int MAP_CLIENT = 18;
    public static final int HID_DEVICE = 19;
    public static final int OPP = 20;
    public static final int HEARING_AID = 21;

    public static boolean connectProfile(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException {
        if (bluetoothProfile == null || bluetoothDevice == null) {
            return false;
        }
        try {
            ZLogger.v("connectProfile :" + BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true));
            Class<?> cls = bluetoothProfile.getClass();
            Class<?>[] clsArr = new Class[1];
            clsArr[0] = BluetoothDevice.class;
            Method method = cls.getMethod("connect", clsArr);
            method.setAccessible(true);
            return ((Boolean) method.invoke(bluetoothProfile, bluetoothDevice)).booleanValue();
        } catch (IllegalAccessException e) {
            ZLogger.w("Could not execute method 'connect' in profile " + bluetoothProfile.getClass().getName() + ", ignoring request." + e.toString());
            return false;
        } catch (NoSuchMethodException unused) {
            ZLogger.w("No connect method in the " + bluetoothProfile.getClass().getName() + " class, ignoring request.");
            return false;
        } catch (InvocationTargetException e2) {
            ZLogger.w("Could not execute method 'connect' in profile " + bluetoothProfile.getClass().getName() + ", ignoring request." + e2.toString());
            return false;
        }
    }

    public static boolean isConnectMethodSupported(BluetoothProfile bluetoothProfile, String str) {
        if (bluetoothProfile == null) {
            return false;
        }
        try {
            int i = Build.VERSION.SDK_INT;
            Class<? extends U> clsAsSubclass = bluetoothProfile.getClass().asSubclass(Class.forName(str));
            if (clsAsSubclass == 0) {
                ZLogger.w("no class found: " + str);
                return false;
            }
            Class<?>[] clsArr = new Class[1];
            clsArr[0] = BluetoothDevice.class;
            return clsAsSubclass.getMethod("connect", clsArr) != null;
        } catch (ClassNotFoundException e) {
            ZLogger.w("Could not find clas: " + str + ", ignoring request." + e.toString());
            return false;
        } catch (NoSuchMethodException unused) {
            ZLogger.w("No connect method in the " + str + " class, ignoring request.");
            return false;
        }
    }

    public static boolean isDisConnectMethodSupported(BluetoothProfile bluetoothProfile, String str) {
        if (bluetoothProfile == null) {
            return false;
        }
        try {
            int i = Build.VERSION.SDK_INT;
            Class<? extends U> clsAsSubclass = bluetoothProfile.getClass().asSubclass(Class.forName(str));
            if (clsAsSubclass == 0) {
                ZLogger.w("no class found: " + str);
                return false;
            }
            Class<?>[] clsArr = new Class[1];
            clsArr[0] = BluetoothDevice.class;
            return clsAsSubclass.getMethod("disconnect", clsArr) != null;
        } catch (ClassNotFoundException e) {
            ZLogger.w("Could not find clas: " + str + ", ignoring request." + e.toString());
            return false;
        } catch (NoSuchMethodException unused) {
            ZLogger.w("No disconnect method in the " + str + " class, ignoring request.");
            return false;
        }
    }

    public static boolean disconnect(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException {
        if (bluetoothProfile == null || bluetoothDevice == null) {
            return false;
        }
        try {
            Object[] objArr = new Object[2];
            objArr[0] = bluetoothProfile.getClass().getName();
            objArr[1] = BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true);
            ZLogger.v(String.format("disconnect : %s : %s", objArr));
            Class<?> cls = bluetoothProfile.getClass();
            Class<?>[] clsArr = new Class[1];
            clsArr[0] = BluetoothDevice.class;
            Method method = cls.getMethod("disconnect", clsArr);
            method.setAccessible(true);
            return ((Boolean) method.invoke(bluetoothProfile, bluetoothDevice)).booleanValue();
        } catch (IllegalAccessException e) {
            e = e;
            ZLogger.w("Could not execute method 'disconnect' in profile , ignoring request." + e.toString());
            return false;
        } catch (NoSuchMethodException unused) {
            ZLogger.w("No disconnect method in the  class, ignoring request.");
            return false;
        } catch (InvocationTargetException e2) {
            e = e2;
            ZLogger.w("Could not execute method 'disconnect' in profile , ignoring request." + e.toString());
            return false;
        }
    }

    @RequiresPermission("android.permission.BLUETOOTH")
    @TargetApi(19)
    public static int getConnectionState(BluetoothProfile bluetoothProfile, String str, BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException {
        if (bluetoothProfile == null || bluetoothDevice == null) {
            return 0;
        }
        try {
            Class<? extends U> clsAsSubclass = bluetoothProfile.getClass().asSubclass(Class.forName(str));
            Class<?>[] clsArr = new Class[1];
            clsArr[0] = BluetoothDevice.class;
            Method method = clsAsSubclass.getMethod("getConnectionState", clsArr);
            method.setAccessible(true);
            return ((Integer) method.invoke(bluetoothProfile, bluetoothDevice)).intValue();
        } catch (ClassNotFoundException e) {
            ZLogger.w("Could not find clas: " + str + ", ignoring request." + e.toString());
            return 0;
        } catch (IllegalAccessException e2) {
            e = e2;
            ZLogger.w("Could not execute method 'connect' in profile CLASS_NAME, ignoring request." + e.toString());
            return 0;
        } catch (NoSuchMethodException unused) {
            ZLogger.w("No connect method in the CLASS_NAME class, ignoring request.");
            return 0;
        } catch (InvocationTargetException e3) {
            e = e3;
            ZLogger.w("Could not execute method 'connect' in profile CLASS_NAME, ignoring request." + e.toString());
            return 0;
        }
    }

    public static List<BluetoothDevice> getConnectedDevices(BluetoothProfile bluetoothProfile, String str) throws NoSuchMethodException, SecurityException {
        ArrayList arrayList = new ArrayList();
        if (bluetoothProfile == null) {
            return arrayList;
        }
        try {
            Method method = bluetoothProfile.getClass().asSubclass(Class.forName(str)).getMethod("getConnectedDevices", null);
            method.setAccessible(true);
            return (List) method.invoke(bluetoothProfile, null);
        } catch (ClassNotFoundException e) {
            ZLogger.w("Could not find clas: " + str + ", ignoring request." + e.toString());
            return arrayList;
        } catch (IllegalAccessException e2) {
            e = e2;
            ZLogger.w("Could not execute method 'connect' in profile CLASS_NAME, ignoring request." + e.toString());
            return arrayList;
        } catch (NoSuchMethodException unused) {
            ZLogger.w("No connect method in the CLASS_NAME class, ignoring request.");
            return arrayList;
        } catch (InvocationTargetException e3) {
            e = e3;
            ZLogger.w("Could not execute method 'connect' in profile CLASS_NAME, ignoring request." + e.toString());
            return arrayList;
        }
    }

    public static boolean setPriority(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice, int i) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (bluetoothProfile == null) {
            return false;
        }
        try {
            Class<?> cls = bluetoothProfile.getClass();
            Class<?>[] clsArr = new Class[2];
            clsArr[0] = BluetoothDevice.class;
            clsArr[1] = Integer.TYPE;
            Method method = cls.getMethod("setPriority", clsArr);
            Object[] objArr = new Object[2];
            objArr[0] = bluetoothDevice;
            objArr[1] = Integer.valueOf(i);
            method.invoke(bluetoothProfile, objArr);
            return true;
        } catch (Exception unused) {
            ZLogger.w(bluetoothProfile.toString());
            return false;
        }
    }

    public static String getProfileName(int i) {
        switch (i) {
            case 1:
                return "HEADSET";
            case 2:
                return "A2DP";
            case 3:
            case 14:
            case 15:
            default:
                return "UNKNOWN_PROFILE";
            case 4:
                return "HID_HOST";
            case 5:
                return "PAN";
            case 6:
                return "PBAP";
            case 7:
                return "GATT";
            case 8:
                return "GATT_SERVER";
            case 9:
                return "MAP";
            case 10:
                return "SAP";
            case 11:
                return "A2DP_SINK";
            case 12:
                return "AVRCP_CONTROLLER";
            case 13:
                return "AVRCP";
            case 16:
                return "HEADSET_CLIENT";
            case 17:
                return "PBAP_CLIENT";
            case 18:
                return "MAP_CLIENT";
            case 19:
                return "HID_DEVICE";
            case 20:
                return "OPP";
            case 21:
                return "HEARING_AID";
        }
    }

    public static boolean disconnect(BluetoothProfile bluetoothProfile, String str, BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException {
        if (bluetoothProfile == null || bluetoothDevice == null) {
            return false;
        }
        try {
            Object[] objArr = new Object[2];
            objArr[0] = str;
            objArr[1] = BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true);
            ZLogger.v(String.format("disconnect : %s : %s", objArr));
            Class<? extends U> clsAsSubclass = bluetoothProfile.getClass().asSubclass(Class.forName(str));
            Class<?>[] clsArr = new Class[1];
            clsArr[0] = BluetoothDevice.class;
            Method method = clsAsSubclass.getMethod("disconnect", clsArr);
            method.setAccessible(true);
            return ((Boolean) method.invoke(bluetoothProfile, bluetoothDevice)).booleanValue();
        } catch (ClassNotFoundException e) {
            ZLogger.w("Could not find clas: " + str + ", ignoring request." + e.toString());
            return false;
        } catch (IllegalAccessException e2) {
            ZLogger.w("Could not execute method 'disconnect' in profile " + str + ", ignoring request." + e2.toString());
            return false;
        } catch (NoSuchMethodException unused) {
            ZLogger.w("No disconnect method in the " + str + " class, ignoring request.");
            return false;
        } catch (InvocationTargetException e3) {
            ZLogger.w("Could not execute method 'disconnect' in profile " + str + ", ignoring request." + e3.toString());
            return false;
        }
    }

    public static boolean connectProfile(BluetoothProfile bluetoothProfile, String str, BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException {
        if (bluetoothProfile == null || bluetoothDevice == null) {
            return false;
        }
        try {
            int i = Build.VERSION.SDK_INT;
            ZLogger.v("connectProfile :" + BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true));
            Class<? extends U> clsAsSubclass = bluetoothProfile.getClass().asSubclass(Class.forName(str));
            if (clsAsSubclass == 0) {
                ZLogger.w("no class found: " + str);
                return false;
            }
            Class<?>[] clsArr = new Class[1];
            clsArr[0] = BluetoothDevice.class;
            Method method = clsAsSubclass.getMethod("connect", clsArr);
            method.setAccessible(true);
            return ((Boolean) method.invoke(bluetoothProfile, bluetoothDevice)).booleanValue();
        } catch (ClassNotFoundException e) {
            ZLogger.w("Could not find clas: " + str + ", ignoring request." + e.toString());
            return false;
        } catch (IllegalAccessException e2) {
            ZLogger.w("Could not execute method 'connect' in profile " + str + ", ignoring request." + e2.toString());
            return false;
        } catch (NoSuchMethodException unused) {
            ZLogger.w("No connect method in the " + str + " class, ignoring request.");
            return false;
        } catch (InvocationTargetException e3) {
            ZLogger.w("Could not execute method 'connect' in profile " + str + ", ignoring request." + e3.toString());
            return false;
        }
    }
}
