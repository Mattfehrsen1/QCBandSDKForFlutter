package com.realsil.sdk.core.bluetooth.impl;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.os.Build;
import com.realsil.sdk.core.a.a;
import com.realsil.sdk.core.logger.ZLogger;

/* loaded from: classes3.dex */
public class BluetoothHeadsetImpl {
    public static final String CLASS_NAME = "android.bluetooth.BluetoothHeadset";
    public static final String VENDOR_SPECIFIC_HEADSET_EVENT_IPHONEACCEV = "+IPHONEACCEV";
    public static final int VENDOR_SPECIFIC_HEADSET_EVENT_IPHONEACCEV_BATTERY_LEVEL = 1;
    public static final String VENDOR_SPECIFIC_HEADSET_EVENT_XEVENT = "+XEVENT";
    public static final String VENDOR_SPECIFIC_HEADSET_EVENT_XEVENT_BATTERY_LEVEL = "BATTERY";

    public static int getAudioState(BluetoothHeadset bluetoothHeadset, BluetoothDevice bluetoothDevice) {
        if (bluetoothHeadset == null) {
            return 10;
        }
        try {
            return ((Integer) bluetoothHeadset.getClass().getMethod("getAudioState", BluetoothDevice.class).invoke(bluetoothHeadset, bluetoothDevice)).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 10;
        }
    }

    public static int getBatteryLevel(String str, Object[] objArr) {
        str.getClass();
        if (str.equals(VENDOR_SPECIFIC_HEADSET_EVENT_IPHONEACCEV)) {
            return getBatteryLevelFromAppleBatteryVsc(objArr);
        }
        if (str.equals(VENDOR_SPECIFIC_HEADSET_EVENT_XEVENT)) {
            return getBatteryLevelFromXEventVsc(objArr);
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x005a, code lost:
    
        if (r7 < 0) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005e, code lost:
    
        if (r7 <= 9) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0064, code lost:
    
        return (r7 + 1) * 10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:?, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:?, code lost:
    
        return -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getBatteryLevelFromAppleBatteryVsc(java.lang.Object[] r7) {
        /*
            int r0 = r7.length
            r1 = -1
            if (r0 != 0) goto La
            java.lang.String r7 = "empty arguments"
            com.realsil.sdk.core.logger.ZLogger.w(r7)
            return r1
        La:
            r0 = 0
            r2 = r7[r0]
            boolean r2 = r2 instanceof java.lang.Integer
            if (r2 == 0) goto L65
            r2 = r7[r0]
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            int r3 = r7.length
            int r4 = r2 * 2
            r5 = 1
            int r4 = r4 + r5
            if (r3 == r4) goto L27
            java.lang.String r7 = "number of arguments does not match"
            com.realsil.sdk.core.logger.ZLogger.w(r7)
            return r1
        L27:
            if (r0 >= r2) goto L59
            int r3 = r0 * 2
            int r4 = r3 + 1
            r4 = r7[r4]
            boolean r6 = r4 instanceof java.lang.Integer
            if (r6 == 0) goto L53
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            if (r4 == r5) goto L3e
            int r0 = r0 + 1
            goto L27
        L3e:
            int r3 = r3 + 2
            r7 = r7[r3]
            boolean r0 = r7 instanceof java.lang.Integer
            if (r0 == 0) goto L4d
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            goto L5a
        L4d:
            java.lang.String r7 = "error parsing indicator value"
            com.realsil.sdk.core.logger.ZLogger.w(r7)
            return r1
        L53:
            java.lang.String r7 = "error parsing indicator type"
            com.realsil.sdk.core.logger.ZLogger.w(r7)
            return r1
        L59:
            r7 = -1
        L5a:
            if (r7 < 0) goto L64
            r0 = 9
            if (r7 <= r0) goto L61
            goto L64
        L61:
            int r7 = r7 + r5
            int r1 = r7 * 10
        L64:
            return r1
        L65:
            java.lang.String r7 = "error parsing number of arguments"
            com.realsil.sdk.core.logger.ZLogger.w(r7)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.impl.BluetoothHeadsetImpl.getBatteryLevelFromAppleBatteryVsc(java.lang.Object[]):int");
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
            ZLogger.w("skip none BATTERY event: " + str);
            return -1;
        }
        if (objArr.length != 5) {
            StringBuilder sbA = a.a("wrong battery level event length: ");
            sbA.append(String.valueOf(objArr.length));
            ZLogger.w(sbA.toString());
            return -1;
        }
        if (!(objArr[1] instanceof Integer) || !(objArr[2] instanceof Integer)) {
            ZLogger.w("error parsing event values");
            return -1;
        }
        int iIntValue = ((Integer) objArr[1]).intValue();
        int iIntValue2 = ((Integer) objArr[2]).intValue();
        if (iIntValue >= 0 && iIntValue2 > 1 && iIntValue <= iIntValue2) {
            return (iIntValue * 100) / (iIntValue2 - 1);
        }
        StringBuilder sbA2 = a.a("wrong event value, batteryLevel=");
        sbA2.append(String.valueOf(iIntValue));
        sbA2.append(", numberOfLevels=");
        sbA2.append(String.valueOf(iIntValue2));
        ZLogger.w(sbA2.toString());
        return -1;
    }

    public static boolean startScoUsingVirtualVoiceCall(BluetoothHeadset bluetoothHeadset) {
        if (bluetoothHeadset == null) {
            return false;
        }
        try {
            return ((Boolean) bluetoothHeadset.getClass().getMethod("startScoUsingVirtualVoiceCall", new Class[0]).invoke(bluetoothHeadset, new Object[0])).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean stopScoUsingVirtualVoiceCall(BluetoothHeadset bluetoothHeadset) {
        if (bluetoothHeadset == null) {
            return false;
        }
        try {
            return ((Boolean) bluetoothHeadset.getClass().getMethod("stopScoUsingVirtualVoiceCall", new Class[0]).invoke(bluetoothHeadset, new Object[0])).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean startScoUsingVirtualVoiceCall(BluetoothHeadset bluetoothHeadset, BluetoothDevice bluetoothDevice) {
        if (Build.VERSION.SDK_INT >= 28) {
            return startScoUsingVirtualVoiceCall(bluetoothHeadset);
        }
        if (bluetoothHeadset == null) {
            return false;
        }
        try {
            return ((Boolean) bluetoothHeadset.getClass().getMethod("startScoUsingVirtualVoiceCall", BluetoothDevice.class).invoke(bluetoothHeadset, bluetoothDevice)).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean stopScoUsingVirtualVoiceCall(BluetoothHeadset bluetoothHeadset, BluetoothDevice bluetoothDevice) {
        if (Build.VERSION.SDK_INT >= 28) {
            return stopScoUsingVirtualVoiceCall(bluetoothHeadset);
        }
        if (bluetoothHeadset == null) {
            return false;
        }
        try {
            return ((Boolean) bluetoothHeadset.getClass().getMethod("stopScoUsingVirtualVoiceCall", BluetoothDevice.class).invoke(bluetoothHeadset, bluetoothDevice)).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
