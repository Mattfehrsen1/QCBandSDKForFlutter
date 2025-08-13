package com.blankj.utilcode.util;

import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.util.Objects;

/* loaded from: classes.dex */
public final class PhoneUtils {
    private PhoneUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isPhone() {
        return getTelephonyManager().getPhoneType() != 0;
    }

    public static String getDeviceId() {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        TelephonyManager telephonyManager = getTelephonyManager();
        String deviceId = telephonyManager.getDeviceId();
        if (!TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        if (Build.VERSION.SDK_INT < 26) {
            return "";
        }
        String imei = telephonyManager.getImei();
        if (!TextUtils.isEmpty(imei)) {
            return imei;
        }
        String meid = telephonyManager.getMeid();
        return TextUtils.isEmpty(meid) ? "" : meid;
    }

    public static String getSerial() {
        if (Build.VERSION.SDK_INT < 29) {
            return Build.VERSION.SDK_INT >= 26 ? Build.getSerial() : Build.SERIAL;
        }
        try {
            return Build.getSerial();
        } catch (SecurityException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getIMEI() {
        return getImeiOrMeid(true);
    }

    public static String getMEID() {
        return getImeiOrMeid(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00c0 A[PHI: r2
      0x00c0: PHI (r2v9 java.lang.String) = (r2v7 java.lang.String), (r2v7 java.lang.String), (r2v11 java.lang.String), (r2v11 java.lang.String) binds: [B:52:0x00b7, B:54:0x00bd, B:44:0x00a5, B:46:0x00ab] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getImeiOrMeid(boolean r12) throws java.lang.NoSuchMethodException, java.lang.ClassNotFoundException, java.lang.SecurityException {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            java.lang.String r1 = ""
            r2 = 29
            if (r0 < r2) goto L9
            return r1
        L9:
            android.telephony.TelephonyManager r0 = getTelephonyManager()
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 26
            r4 = 1
            r5 = 0
            if (r2 < r3) goto L31
            if (r12 == 0) goto L24
            java.lang.String r12 = r0.getImei(r5)
            java.lang.String r0 = r0.getImei(r4)
            java.lang.String r12 = getMinOne(r12, r0)
            return r12
        L24:
            java.lang.String r12 = r0.getMeid(r5)
            java.lang.String r0 = r0.getMeid(r4)
            java.lang.String r12 = getMinOne(r12, r0)
            return r12
        L31:
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 21
            r6 = 15
            r7 = 14
            if (r2 < r3) goto Lc6
            if (r12 == 0) goto L41
            java.lang.String r2 = "ril.gsm.imei"
            goto L44
        L41:
            java.lang.String r2 = "ril.cdma.meid"
        L44:
            java.lang.String r2 = getSystemPropertyByReflect(r2)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            r8 = 2
            if (r3 != 0) goto L64
            java.lang.String r12 = ","
            java.lang.String[] r12 = r2.split(r12)
            int r0 = r12.length
            if (r0 != r8) goto L61
            r0 = r12[r5]
            r12 = r12[r4]
            java.lang.String r12 = getMinOne(r0, r12)
            return r12
        L61:
            r12 = r12[r5]
            return r12
        L64:
            java.lang.String r2 = r0.getDeviceId()
            java.lang.Class r3 = r0.getClass()     // Catch: java.lang.reflect.InvocationTargetException -> L8b java.lang.IllegalAccessException -> L90 java.lang.NoSuchMethodException -> L95
            java.lang.String r9 = "getDeviceId"
            java.lang.Class[] r10 = new java.lang.Class[r4]     // Catch: java.lang.reflect.InvocationTargetException -> L8b java.lang.IllegalAccessException -> L90 java.lang.NoSuchMethodException -> L95
            java.lang.Class r11 = java.lang.Integer.TYPE     // Catch: java.lang.reflect.InvocationTargetException -> L8b java.lang.IllegalAccessException -> L90 java.lang.NoSuchMethodException -> L95
            r10[r5] = r11     // Catch: java.lang.reflect.InvocationTargetException -> L8b java.lang.IllegalAccessException -> L90 java.lang.NoSuchMethodException -> L95
            java.lang.reflect.Method r3 = r3.getMethod(r9, r10)     // Catch: java.lang.reflect.InvocationTargetException -> L8b java.lang.IllegalAccessException -> L90 java.lang.NoSuchMethodException -> L95
            java.lang.Object[] r9 = new java.lang.Object[r4]     // Catch: java.lang.reflect.InvocationTargetException -> L8b java.lang.IllegalAccessException -> L90 java.lang.NoSuchMethodException -> L95
            if (r12 == 0) goto L7d
            goto L7e
        L7d:
            r4 = 2
        L7e:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.reflect.InvocationTargetException -> L8b java.lang.IllegalAccessException -> L90 java.lang.NoSuchMethodException -> L95
            r9[r5] = r4     // Catch: java.lang.reflect.InvocationTargetException -> L8b java.lang.IllegalAccessException -> L90 java.lang.NoSuchMethodException -> L95
            java.lang.Object r0 = r3.invoke(r0, r9)     // Catch: java.lang.reflect.InvocationTargetException -> L8b java.lang.IllegalAccessException -> L90 java.lang.NoSuchMethodException -> L95
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.reflect.InvocationTargetException -> L8b java.lang.IllegalAccessException -> L90 java.lang.NoSuchMethodException -> L95
            goto L9a
        L8b:
            r0 = move-exception
            r0.printStackTrace()
            goto L99
        L90:
            r0 = move-exception
            r0.printStackTrace()
            goto L99
        L95:
            r0 = move-exception
            r0.printStackTrace()
        L99:
            r0 = r1
        L9a:
            if (r12 == 0) goto Lae
            if (r2 == 0) goto La5
            int r12 = r2.length()
            if (r12 >= r6) goto La5
            r2 = r1
        La5:
            if (r0 == 0) goto Lc0
            int r12 = r0.length()
            if (r12 >= r6) goto Lc0
            goto Lc1
        Lae:
            if (r2 == 0) goto Lb7
            int r12 = r2.length()
            if (r12 != r7) goto Lb7
            r2 = r1
        Lb7:
            if (r0 == 0) goto Lc0
            int r12 = r0.length()
            if (r12 != r7) goto Lc0
            goto Lc1
        Lc0:
            r1 = r0
        Lc1:
            java.lang.String r12 = getMinOne(r2, r1)
            return r12
        Lc6:
            java.lang.String r0 = r0.getDeviceId()
            if (r12 == 0) goto Ld5
            if (r0 == 0) goto Lde
            int r12 = r0.length()
            if (r12 < r6) goto Lde
            return r0
        Ld5:
            if (r0 == 0) goto Lde
            int r12 = r0.length()
            if (r12 != r7) goto Lde
            return r0
        Lde:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.PhoneUtils.getImeiOrMeid(boolean):java.lang.String");
    }

    private static String getMinOne(String str, String str2) {
        boolean zIsEmpty = TextUtils.isEmpty(str);
        boolean zIsEmpty2 = TextUtils.isEmpty(str2);
        return (zIsEmpty && zIsEmpty2) ? "" : (zIsEmpty || zIsEmpty2) ? !zIsEmpty ? str : str2 : str.compareTo(str2) <= 0 ? str : str2;
    }

    private static String getSystemPropertyByReflect(String str) throws ClassNotFoundException {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, "");
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getIMSI() {
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                getTelephonyManager().getSubscriberId();
            } catch (SecurityException e) {
                e.printStackTrace();
                return "";
            }
        }
        return getTelephonyManager().getSubscriberId();
    }

    public static int getPhoneType() {
        return getTelephonyManager().getPhoneType();
    }

    public static boolean isSimCardReady() {
        return getTelephonyManager().getSimState() == 5;
    }

    public static String getSimOperatorName() {
        return getTelephonyManager().getSimOperatorName();
    }

    public static String getSimOperatorByMnc() {
        String simOperator = getTelephonyManager().getSimOperator();
        if (simOperator == null) {
            return "";
        }
        simOperator.hashCode();
        switch (simOperator) {
            case "46000":
            case "46002":
            case "46007":
            case "46020":
                return "中国移动";
            case "46001":
            case "46006":
            case "46009":
                return "中国联通";
            case "46003":
            case "46005":
            case "46011":
                return "中国电信";
            default:
                return simOperator;
        }
    }

    public static void dial(String str) {
        Objects.requireNonNull(str, "Argument 'phoneNumber' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Utils.getApp().startActivity(UtilsBridge.getDialIntent(str));
    }

    public static void call(String str) {
        Objects.requireNonNull(str, "Argument 'phoneNumber' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Utils.getApp().startActivity(UtilsBridge.getCallIntent(str));
    }

    public static void sendSms(String str, String str2) {
        Objects.requireNonNull(str, "Argument 'phoneNumber' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Utils.getApp().startActivity(UtilsBridge.getSendSmsIntent(str, str2));
    }

    private static TelephonyManager getTelephonyManager() {
        return (TelephonyManager) Utils.getApp().getSystemService("phone");
    }
}
