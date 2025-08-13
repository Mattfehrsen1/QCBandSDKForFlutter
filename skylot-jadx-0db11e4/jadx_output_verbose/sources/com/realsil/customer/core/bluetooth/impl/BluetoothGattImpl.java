package com.realsil.customer.core.bluetooth.impl;

import android.bluetooth.BluetoothGatt;
import android.text.TextUtils;
import androidx.annotation.RequiresApi;
import com.realsil.customer.bbpro.equalizer.EqConstants;
import com.realsil.customer.core.logger.ZLogger;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/impl/BluetoothGattImpl.class */
public class BluetoothGattImpl {
    public static final HashMap<Integer, String> a;
    public static final HashMap<Integer, String> b;
    public static final HashMap<Integer, String> c;

    public static String getCharPermission(int i) {
        return a(a, i);
    }

    public static String getCharPropertie(int i) {
        return a(b, i);
    }

    public static String getDescPermission(int i) {
        return a(c, i);
    }

    public static String a(HashMap<Integer, String> map, int i) {
        String str = map.get(Integer.valueOf(i));
        String str2 = str;
        if (TextUtils.isEmpty(str)) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < 32; i2++) {
                int i3 = 1 << i2;
                if ((i & i3) > 0) {
                    arrayList.add(Integer.valueOf(i3));
                }
            }
            String str3 = "";
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                str3 = str3 + map.get(arrayList.get(i4)) + "|";
            }
            str2 = str3;
        }
        return str2;
    }

    public static List<String> parseProperty(int i) {
        ArrayList arrayList = new ArrayList();
        if ((i & 1) == 1) {
            arrayList.add("BROADCAST");
        }
        if ((i & 2) == 2) {
            arrayList.add("READ");
        }
        if ((i & 4) == 4) {
            arrayList.add("WRITE_NO_RESPONSE");
        }
        if ((i & 8) == 8) {
            arrayList.add("WRITE");
        }
        if ((i & 16) == 16) {
            arrayList.add("NOTIFY");
        }
        if ((i & 32) == 32) {
            arrayList.add("INDICATE");
        }
        return arrayList;
    }

    public static String parseProperty2(int i) {
        StringBuilder sb = new StringBuilder();
        List<String> property = parseProperty(i);
        if (property.size() > 0) {
            for (String str : property) {
                if (sb.length() > 0) {
                    sb.append("|");
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }

    @RequiresApi(api = 18)
    public static boolean refresh(BluetoothGatt bluetoothGatt) {
        try {
            return ((Boolean) bluetoothGatt.getClass().getMethod("refresh", new Class[0]).invoke(bluetoothGatt, new Object[0])).booleanValue();
        } catch (Exception e) {
            ZLogger.w("refreshing device failed: " + e.toString());
            return false;
        }
    }

    @RequiresApi(api = 18)
    public static boolean refreshDeviceCache(BluetoothGatt bluetoothGatt, boolean z) {
        if (z || bluetoothGatt.getDevice().getBondState() == 10) {
            return refresh(bluetoothGatt);
        }
        return false;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        a = map;
        map.put(0, "UNKNOW");
        map.put(1, "READ");
        map.put(2, "READ_ENCRYPTED");
        map.put(4, "READ_ENCRYPTED_MITM");
        map.put(16, "WRITE");
        map.put(32, "WRITE_ENCRYPTED");
        map.put(64, "WRITE_ENCRYPTED_MITM");
        map.put(Integer.valueOf(EqConstants.CodeIndex.BUILD_IN_EQ_4), "WRITE_SIGNED");
        map.put(256, "WRITE_SIGNED_MITM");
        HashMap<Integer, String> map2 = new HashMap<>();
        b = map2;
        map2.put(1, "BROADCAST");
        map2.put(Integer.valueOf(EqConstants.CodeIndex.BUILD_IN_EQ_4), "EXTENDED_PROPS");
        map2.put(32, "INDICATE");
        map2.put(16, "NOTIFY");
        map2.put(2, "READ");
        map2.put(64, "SIGNED_WRITE");
        map2.put(8, "WRITE");
        map2.put(4, "WRITE_NO_RESPONSE");
        HashMap<Integer, String> map3 = new HashMap<>();
        c = map3;
        map3.put(0, "UNKNOW");
        map3.put(1, "READ");
        map3.put(2, "READ_ENCRYPTED");
        map3.put(4, "READ_ENCRYPTED_MITM");
        map3.put(16, "WRITE");
        map3.put(32, "WRITE_ENCRYPTED");
        map3.put(64, "WRITE_ENCRYPTED_MITM");
        map3.put(Integer.valueOf(EqConstants.CodeIndex.BUILD_IN_EQ_4), "WRITE_SIGNED");
        map3.put(256, "WRITE_SIGNED_MITM");
    }

    public boolean requestLeConnectionUpdate(BluetoothGatt bluetoothGatt, int i, int i2, int i3, int i4, int i5, int i6) throws NoSuchMethodException, SecurityException {
        try {
            Class<?> cls = bluetoothGatt.getClass();
            Class<?>[] clsArr = new Class[6];
            Class<?> cls2 = Integer.TYPE;
            clsArr[0] = cls2;
            clsArr[1] = cls2;
            clsArr[2] = cls2;
            clsArr[3] = cls2;
            clsArr[4] = cls2;
            clsArr[5] = cls2;
            Method method = cls.getMethod("requestLeConnectionUpdate", clsArr);
            ZLogger.d("requestLeConnectionUpdate() - min=(" + i + ")" + (i * 1.25d) + "msec, max=(" + i2 + ")" + (i2 * 1.25d) + "msec, latency=" + i3 + ", timeout=" + i4 + "msec, min_ce=" + i5 + ", max_ce=" + i6);
            Object[] objArr = new Object[6];
            objArr[0] = Integer.valueOf(i);
            objArr[1] = Integer.valueOf(i2);
            objArr[2] = Integer.valueOf(i3);
            objArr[3] = Integer.valueOf(i4);
            objArr[4] = Integer.valueOf(i5);
            objArr[5] = Integer.valueOf(i4);
            return ((Boolean) method.invoke(bluetoothGatt, objArr)).booleanValue();
        } catch (Exception e) {
            ZLogger.w("refreshing device failed: " + e.toString());
            return false;
        }
    }
}
