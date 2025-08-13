package com.oudmon.ble.base.util;

import android.util.Log;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/util/GenericsUtils.class */
public class GenericsUtils {
    public static Class getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    public static Class getSuperClassGenricType(Class clazz, int index) throws IndexOutOfBoundsException {
        Type[] genType = clazz.getGenericInterfaces();
        Type[] params = ((ParameterizedType) genType[0]).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            Log.e("---", "2");
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            Log.e("---", "3");
            return Object.class;
        }
        return (Class) params[index];
    }
}
