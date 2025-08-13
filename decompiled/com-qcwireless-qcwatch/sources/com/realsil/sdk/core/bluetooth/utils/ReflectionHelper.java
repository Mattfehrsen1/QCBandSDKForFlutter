package com.realsil.sdk.core.bluetooth.utils;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import com.realsil.sdk.core.logger.ZLogger;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public final class ReflectionHelper {
    public static int getCurrentAdapterState(Intent intent) {
        return intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
    }

    public static int getCurrentProfileState(Intent intent) {
        return intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
    }

    public static BluetoothDevice getDevice(Intent intent) {
        return (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
    }

    public static int getPreviousAdapterState(Intent intent) {
        return intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", -1);
    }

    public static int getPreviousProfileState(Intent intent) {
        return intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
    }

    public static void printAllInform(Class cls) throws SecurityException {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
