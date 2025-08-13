package com.realsil.customer.core.bluetooth.impl;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.os.Build;
import androidx.annotation.RequiresPermission;
import com.realsil.customer.core.logger.ZLogger;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/impl/BluetoothHeadsetImpl.class */
public class BluetoothHeadsetImpl {
    public static final String CLASS_NAME = "android.bluetooth.BluetoothHeadset";
    public static final String VENDOR_SPECIFIC_HEADSET_EVENT_IPHONEACCEV = "+IPHONEACCEV";
    public static final int VENDOR_SPECIFIC_HEADSET_EVENT_IPHONEACCEV_BATTERY_LEVEL = 1;
    public static final String VENDOR_SPECIFIC_HEADSET_EVENT_XEVENT = "+XEVENT";
    public static final String VENDOR_SPECIFIC_HEADSET_EVENT_XEVENT_BATTERY_LEVEL = "BATTERY";

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v8, types: [boolean] */
    @RequiresPermission("android.permission.BLUETOOTH_ADMIN")
    public static boolean startScoUsingVirtualVoiceCall(BluetoothHeadset bluetoothHeadset) {
        ?? BooleanValue = bluetoothHeadset;
        if (BooleanValue == 0) {
            return false;
        }
        try {
            BooleanValue = ((Boolean) bluetoothHeadset.getClass().getMethod("startScoUsingVirtualVoiceCall", new Class[0]).invoke(bluetoothHeadset, new Object[0])).booleanValue();
            return BooleanValue;
        } catch (Exception unused) {
            BooleanValue.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v8, types: [boolean] */
    @RequiresPermission("android.permission.BLUETOOTH_ADMIN")
    public static boolean stopScoUsingVirtualVoiceCall(BluetoothHeadset bluetoothHeadset) {
        ?? BooleanValue = bluetoothHeadset;
        if (BooleanValue == 0) {
            return false;
        }
        try {
            BooleanValue = ((Boolean) bluetoothHeadset.getClass().getMethod("stopScoUsingVirtualVoiceCall", new Class[0]).invoke(bluetoothHeadset, new Object[0])).booleanValue();
            return BooleanValue;
        } catch (Exception unused) {
            BooleanValue.printStackTrace();
            return false;
        }
    }

    public static int getBatteryLevel(String str, Object[] objArr) {
        int batteryLevelFromAppleBatteryVsc = -1;
        str.getClass();
        if (str.equals(VENDOR_SPECIFIC_HEADSET_EVENT_IPHONEACCEV)) {
            batteryLevelFromAppleBatteryVsc = getBatteryLevelFromAppleBatteryVsc(objArr);
        } else if (str.equals(VENDOR_SPECIFIC_HEADSET_EVENT_XEVENT)) {
            batteryLevelFromAppleBatteryVsc = getBatteryLevelFromXEventVsc(objArr);
        }
        return batteryLevelFromAppleBatteryVsc;
    }

    public static int getBatteryLevelFromAppleBatteryVsc(Object[] objArr) {
        if (objArr.length == 0) {
            ZLogger.w("empty arguments");
            return -1;
        }
        Object obj = objArr[0];
        if (!(obj instanceof Integer)) {
            ZLogger.w("error parsing number of arguments");
            return -1;
        }
        int iIntValue = ((Integer) obj).intValue();
        if (objArr.length != (iIntValue * 2) + 1) {
            ZLogger.w("number of arguments does not match");
            return -1;
        }
        int iIntValue2 = -1;
        int i = 0;
        while (true) {
            if (i >= iIntValue) {
                break;
            }
            int i2 = i * 2;
            Object obj2 = objArr[i2 + 1];
            if (!(obj2 instanceof Integer)) {
                ZLogger.w("error parsing indicator type");
                return -1;
            }
            if (((Integer) obj2).intValue() != 1) {
                i++;
            } else {
                Object obj3 = objArr[i2 + 2];
                if (!(obj3 instanceof Integer)) {
                    ZLogger.w("error parsing indicator value");
                    return -1;
                }
                iIntValue2 = ((Integer) obj3).intValue();
            }
        }
        if (iIntValue2 < 0 || iIntValue2 > 9) {
            return -1;
        }
        return (iIntValue2 + 1) * 10;
    }

    public static int getBatteryLevelFromXEventVsc(Object[] objArr) {
        if (objArr.length == 0) {
            ZLogger.w("empty arguments");
            return -1;
        }
        Object obj = objArr[0];
        if (!(obj instanceof String)) {
            ZLogger.w("error parsing event name");
            return -1;
        }
        String str = (String) obj;
        if (!str.equals(VENDOR_SPECIFIC_HEADSET_EVENT_XEVENT_BATTERY_LEVEL)) {
            ZLogger.w("skip none BATTERY event: ".concat(str));
            return -1;
        }
        if (objArr.length != 5) {
            ZLogger.w("wrong battery level event length: " + String.valueOf(objArr.length));
            return -1;
        }
        Object obj2 = objArr[1];
        if (!(obj2 instanceof Integer) || !(objArr[2] instanceof Integer)) {
            ZLogger.w("error parsing event values");
            return -1;
        }
        int iIntValue = ((Integer) obj2).intValue();
        int iIntValue2 = ((Integer) objArr[2]).intValue();
        if (iIntValue >= 0 && iIntValue2 > 1 && iIntValue <= iIntValue2) {
            return (iIntValue * 100) / (iIntValue2 - 1);
        }
        ZLogger.w("wrong event value, batteryLevel=" + String.valueOf(iIntValue) + ", numberOfLevels=" + String.valueOf(iIntValue2));
        return -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v8, types: [int] */
    @RequiresPermission("android.permission.BLUETOOTH_ADMIN")
    public static int getAudioState(BluetoothHeadset bluetoothHeadset, BluetoothDevice bluetoothDevice) {
        ?? IntValue = bluetoothHeadset;
        if (IntValue == 0) {
            return 10;
        }
        try {
            Class<?> cls = bluetoothHeadset.getClass();
            Class<?>[] clsArr = new Class[1];
            clsArr[0] = BluetoothDevice.class;
            IntValue = ((Integer) cls.getMethod("getAudioState", clsArr).invoke(bluetoothHeadset, bluetoothDevice)).intValue();
            return IntValue;
        } catch (Exception unused) {
            IntValue.printStackTrace();
            return 10;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v9, types: [boolean] */
    public static boolean startScoUsingVirtualVoiceCall(BluetoothHeadset bluetoothHeadset, BluetoothDevice bluetoothDevice) {
        if (Build.VERSION.SDK_INT >= 28) {
            return startScoUsingVirtualVoiceCall(bluetoothHeadset);
        }
        ?? BooleanValue = bluetoothHeadset;
        if (BooleanValue == 0) {
            return false;
        }
        try {
            Class<?> cls = bluetoothHeadset.getClass();
            Class<?>[] clsArr = new Class[1];
            clsArr[0] = BluetoothDevice.class;
            BooleanValue = ((Boolean) cls.getMethod("startScoUsingVirtualVoiceCall", clsArr).invoke(bluetoothHeadset, bluetoothDevice)).booleanValue();
            return BooleanValue;
        } catch (Exception unused) {
            BooleanValue.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v9, types: [boolean] */
    public static boolean stopScoUsingVirtualVoiceCall(BluetoothHeadset bluetoothHeadset, BluetoothDevice bluetoothDevice) {
        if (Build.VERSION.SDK_INT >= 28) {
            return stopScoUsingVirtualVoiceCall(bluetoothHeadset);
        }
        ?? BooleanValue = bluetoothHeadset;
        if (BooleanValue == 0) {
            return false;
        }
        try {
            Class<?> cls = bluetoothHeadset.getClass();
            Class<?>[] clsArr = new Class[1];
            clsArr[0] = BluetoothDevice.class;
            BooleanValue = ((Boolean) cls.getMethod("stopScoUsingVirtualVoiceCall", clsArr).invoke(bluetoothHeadset, bluetoothDevice)).booleanValue();
            return BooleanValue;
        } catch (Exception unused) {
            BooleanValue.printStackTrace();
            return false;
        }
    }
}
