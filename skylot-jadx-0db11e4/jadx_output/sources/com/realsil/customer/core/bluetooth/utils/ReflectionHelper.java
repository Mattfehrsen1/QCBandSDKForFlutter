package com.realsil.customer.core.bluetooth.utils;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import com.realsil.customer.core.logger.ZLogger;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/utils/ReflectionHelper.class */
public final class ReflectionHelper {
    public static int getPreviousAdapterState(Intent intent) {
        return intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", -1);
    }

    public static int getCurrentAdapterState(Intent intent) {
        return intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
    }

    public static int getPreviousProfileState(Intent intent) {
        return intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
    }

    public static int getCurrentProfileState(Intent intent) {
        return intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
    }

    public static BluetoothDevice getDevice(Intent intent) {
        return (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
    }

    /* JADX WARN: Not initialized variable reg: 0, insn: 0x005b: INVOKE (r0 I:java.lang.Throwable) VIRTUAL call: java.lang.Throwable.printStackTrace():void A[Catch: Exception -> 0x005b, MD:():void (c), TRY_LEAVE] (LINE:21), block:B:26:0x005b */
    public static void printAllInform(Class cls) throws SecurityException {
        Throwable thPrintStackTrace;
        try {
            Method[] methods = cls.getMethods();
            if (methods == null || methods.length <= 0) {
                ZLogger.w("no method");
            } else {
                for (Method method : methods) {
                    ZLogger.d(method.toString());
                }
            }
            Field[] fields = cls.getFields();
            if (fields == null || fields.length <= 0) {
                ZLogger.w("no fields");
                return;
            }
            for (Field field : fields) {
                ZLogger.d(field.toString());
            }
        } catch (Exception unused) {
            thPrintStackTrace.printStackTrace();
        }
    }
}
