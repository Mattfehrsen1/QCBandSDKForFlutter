package com.realsil.sdk.core.bluetooth.impl;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.os.Build;
import com.realsil.sdk.core.a.a;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.logger.ZLogger;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class BluetoothProfileImpl {
    public static final int A2DP_SINK = 11;
    public static final int AVRCP = 13;
    public static final int AVRCP_CONTROLLER = 12;
    public static final String CLASS_NAME_BLUETOOTH_A2DP = "android.bluetooth.BluetoothA2dp";
    public static final int HEADSET_CLIENT = 16;
    public static final int HEARING_AID = 21;
    public static final int HID_DEVICE = 19;
    public static final int HID_HOST = 4;
    public static final int MAP = 9;
    public static final int MAP_CLIENT = 18;
    public static final int OPP = 20;
    public static final int PAN = 5;
    public static final int PBAP = 6;
    public static final int PBAP_CLIENT = 17;
    public static int PRIORITY_AUTO_CONNECT = 1000;

    public static boolean connectProfile(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException {
        if (bluetoothProfile != null && bluetoothDevice != null) {
            try {
                ZLogger.v("connectProfile :" + BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true));
                Method method = bluetoothProfile.getClass().getMethod("connect", BluetoothDevice.class);
                method.setAccessible(true);
                return ((Boolean) method.invoke(bluetoothProfile, bluetoothDevice)).booleanValue();
            } catch (IllegalAccessException e) {
                StringBuilder sbA = a.a("Could not execute method 'connect' in profile ");
                sbA.append(bluetoothProfile.getClass().getName());
                sbA.append(", ignoring request.");
                sbA.append(e.toString());
                ZLogger.w(sbA.toString());
                return false;
            } catch (NoSuchMethodException unused) {
                StringBuilder sbA2 = a.a("No connect method in the ");
                sbA2.append(bluetoothProfile.getClass().getName());
                sbA2.append(" class, ignoring request.");
                ZLogger.w(sbA2.toString());
            } catch (InvocationTargetException e2) {
                StringBuilder sbA3 = a.a("Could not execute method 'connect' in profile ");
                sbA3.append(bluetoothProfile.getClass().getName());
                sbA3.append(", ignoring request.");
                sbA3.append(e2.toString());
                ZLogger.w(sbA3.toString());
                return false;
            }
        }
        return false;
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
            StringBuilder sbA = a.a("Could not execute method 'connect' in profile CLASS_NAME, ignoring request.");
            sbA.append(e.toString());
            ZLogger.w(sbA.toString());
            return arrayList;
        } catch (NoSuchMethodException unused) {
            ZLogger.w("No connect method in the CLASS_NAME class, ignoring request.");
            return arrayList;
        } catch (InvocationTargetException e3) {
            e = e3;
            StringBuilder sbA2 = a.a("Could not execute method 'connect' in profile CLASS_NAME, ignoring request.");
            sbA2.append(e.toString());
            ZLogger.w(sbA2.toString());
            return arrayList;
        }
    }

    public static int getConnectionState(BluetoothProfile bluetoothProfile, String str, BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException {
        if (bluetoothProfile != null && bluetoothDevice != null) {
            try {
                Method method = bluetoothProfile.getClass().asSubclass(Class.forName(str)).getMethod("getConnectionState", BluetoothDevice.class);
                method.setAccessible(true);
                return ((Integer) method.invoke(bluetoothProfile, bluetoothDevice)).intValue();
            } catch (ClassNotFoundException e) {
                ZLogger.w("Could not find clas: " + str + ", ignoring request." + e.toString());
                return 0;
            } catch (IllegalAccessException e2) {
                e = e2;
                StringBuilder sbA = a.a("Could not execute method 'connect' in profile CLASS_NAME, ignoring request.");
                sbA.append(e.toString());
                ZLogger.w(sbA.toString());
                return 0;
            } catch (NoSuchMethodException unused) {
                ZLogger.w("No connect method in the CLASS_NAME class, ignoring request.");
            } catch (InvocationTargetException e3) {
                e = e3;
                StringBuilder sbA2 = a.a("Could not execute method 'connect' in profile CLASS_NAME, ignoring request.");
                sbA2.append(e.toString());
                ZLogger.w(sbA2.toString());
                return 0;
            }
        }
        return 0;
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

    public static boolean isConnectMethodSupported(BluetoothProfile bluetoothProfile, String str) {
        if (bluetoothProfile == null) {
            return false;
        }
        try {
            int i = Build.VERSION.SDK_INT;
            Class<? extends U> clsAsSubclass = bluetoothProfile.getClass().asSubclass(Class.forName(str));
            if (clsAsSubclass != 0) {
                return clsAsSubclass.getMethod("connect", BluetoothDevice.class) != null;
            }
            ZLogger.w("no class found: " + str);
            return false;
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
            if (clsAsSubclass != 0) {
                return clsAsSubclass.getMethod("disconnect", BluetoothDevice.class) != null;
            }
            ZLogger.w("no class found: " + str);
            return false;
        } catch (ClassNotFoundException e) {
            ZLogger.w("Could not find clas: " + str + ", ignoring request." + e.toString());
            return false;
        } catch (NoSuchMethodException unused) {
            ZLogger.w("No disconnect method in the " + str + " class, ignoring request.");
            return false;
        }
    }

    public static boolean setPriority(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice, int i) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (bluetoothProfile == null) {
            return false;
        }
        try {
            bluetoothProfile.getClass().getMethod("setPriority", BluetoothDevice.class, Integer.TYPE).invoke(bluetoothProfile, bluetoothDevice, Integer.valueOf(i));
            return true;
        } catch (Exception e) {
            ZLogger.w(e.toString());
            return false;
        }
    }

    public static boolean disconnect(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException {
        if (bluetoothProfile != null && bluetoothDevice != null) {
            String name = "";
            try {
                name = bluetoothProfile.getClass().getName();
                ZLogger.v(String.format("disconnect : %s : %s", name, BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true)));
                Method method = bluetoothProfile.getClass().getMethod("disconnect", BluetoothDevice.class);
                method.setAccessible(true);
                return ((Boolean) method.invoke(bluetoothProfile, bluetoothDevice)).booleanValue();
            } catch (IllegalAccessException e) {
                e = e;
                ZLogger.w("Could not execute method 'disconnect' in profile " + name + ", ignoring request." + e.toString());
                return false;
            } catch (NoSuchMethodException unused) {
                ZLogger.w("No disconnect method in the " + name + " class, ignoring request.");
            } catch (InvocationTargetException e2) {
                e = e2;
                ZLogger.w("Could not execute method 'disconnect' in profile " + name + ", ignoring request." + e.toString());
                return false;
            }
        }
        return false;
    }

    public static boolean disconnect(BluetoothProfile bluetoothProfile, String str, BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException {
        if (bluetoothProfile != null && bluetoothDevice != null) {
            try {
                ZLogger.v(String.format("disconnect : %s : %s", str, BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true)));
                Method method = bluetoothProfile.getClass().asSubclass(Class.forName(str)).getMethod("disconnect", BluetoothDevice.class);
                method.setAccessible(true);
                return ((Boolean) method.invoke(bluetoothProfile, bluetoothDevice)).booleanValue();
            } catch (ClassNotFoundException e) {
                ZLogger.w("Could not find clas: " + str + ", ignoring request." + e.toString());
            } catch (IllegalAccessException e2) {
                ZLogger.w("Could not execute method 'disconnect' in profile " + str + ", ignoring request." + e2.toString());
            } catch (NoSuchMethodException unused) {
                ZLogger.w("No disconnect method in the " + str + " class, ignoring request.");
            } catch (InvocationTargetException e3) {
                ZLogger.w("Could not execute method 'disconnect' in profile " + str + ", ignoring request." + e3.toString());
            }
        }
        return false;
    }

    public static boolean connectProfile(BluetoothProfile bluetoothProfile, String str, BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException {
        if (bluetoothProfile != null && bluetoothDevice != null) {
            try {
                int i = Build.VERSION.SDK_INT;
                ZLogger.v("connectProfile :" + BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true));
                Class<? extends U> clsAsSubclass = bluetoothProfile.getClass().asSubclass(Class.forName(str));
                if (clsAsSubclass == 0) {
                    ZLogger.w("no class found: " + str);
                    return false;
                }
                Method method = clsAsSubclass.getMethod("connect", BluetoothDevice.class);
                method.setAccessible(true);
                return ((Boolean) method.invoke(bluetoothProfile, bluetoothDevice)).booleanValue();
            } catch (ClassNotFoundException e) {
                ZLogger.w("Could not find clas: " + str + ", ignoring request." + e.toString());
            } catch (IllegalAccessException e2) {
                ZLogger.w("Could not execute method 'connect' in profile " + str + ", ignoring request." + e2.toString());
            } catch (NoSuchMethodException unused) {
                ZLogger.w("No connect method in the " + str + " class, ignoring request.");
            } catch (InvocationTargetException e3) {
                ZLogger.w("Could not execute method 'connect' in profile " + str + ", ignoring request." + e3.toString());
            }
        }
        return false;
    }
}
