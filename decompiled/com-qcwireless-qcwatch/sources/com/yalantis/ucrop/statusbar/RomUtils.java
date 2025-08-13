package com.yalantis.ucrop.statusbar;

import android.os.Build;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public class RomUtils {
    private static final String[] ROM_SAMSUNG = {"samsung"};
    private static final String UNKNOWN = "unknown";
    private static Integer romType;

    public static class AvailableRomType {
        public static final int ANDROID_NATIVE = 3;
        public static final int FLYME = 2;
        public static final int MIUI = 1;
        public static final int NA = 4;
    }

    public static int getLightStatausBarAvailableRomType() {
        Integer num = romType;
        if (num != null) {
            return num.intValue();
        }
        if (isMIUIV6OrAbove()) {
            Integer num2 = 1;
            romType = num2;
            return num2.intValue();
        }
        if (isFlymeV4OrAbove()) {
            Integer num3 = 2;
            romType = num3;
            return num3.intValue();
        }
        if (isAndroid5OrAbove()) {
            Integer num4 = 3;
            romType = num4;
            return num4.intValue();
        }
        Integer num5 = 4;
        romType = num5;
        return num5.intValue();
    }

    private static boolean isFlymeV4OrAbove() {
        return getFlymeVersion() >= 4;
    }

    public static int getFlymeVersion() {
        String str = Build.DISPLAY;
        if (TextUtils.isEmpty(str) || !str.contains("Flyme")) {
            return 0;
        }
        return stringToInt(str.replaceAll("Flyme", "").replaceAll("OS", "").replaceAll(" ", "").substring(0, 1));
    }

    private static boolean isMIUIV6OrAbove() throws Throwable {
        String systemProperty = getSystemProperty();
        if (TextUtils.isEmpty(systemProperty)) {
            return false;
        }
        try {
            return toInt(systemProperty) >= 4;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getMIUIVersionCode() throws Throwable {
        String systemProperty = getSystemProperty();
        if (TextUtils.isEmpty(systemProperty)) {
            return 0;
        }
        try {
            return toInt(systemProperty);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static boolean isAndroid5OrAbove() {
        return Build.VERSION.SDK_INT >= 21;
    }

    private static String getSystemProperty() throws Throwable {
        BufferedReader bufferedReader;
        Throwable th;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop ro.miui.ui.version.code").getInputStream()), 1024);
        } catch (IOException unused) {
            bufferedReader = null;
        } catch (Throwable th2) {
            bufferedReader = null;
            th = th2;
        }
        try {
            String line = bufferedReader.readLine();
            bufferedReader.close();
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return line;
        } catch (IOException unused2) {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static boolean isSamsung() {
        return isRightRom(getBrand(), getManufacturer(), ROM_SAMSUNG);
    }

    private static boolean isRightRom(String str, String str2, String... strArr) {
        for (String str3 : strArr) {
            if (str.contains(str3) || str2.contains(str3)) {
                return true;
            }
        }
        return false;
    }

    private static String getManufacturer() {
        try {
            String str = Build.MANUFACTURER;
            return !TextUtils.isEmpty(str) ? str.toLowerCase() : "unknown";
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    private static String getBrand() {
        try {
            String str = Build.BRAND;
            return !TextUtils.isEmpty(str) ? str.toLowerCase() : "unknown";
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    public static int stringToInt(String str) {
        if (Pattern.compile("^[-\\+]?[\\d]+$").matcher(str).matches()) {
            return toInt(str);
        }
        return 0;
    }

    public static int toInt(Object obj) {
        return toInt(obj, 0);
    }

    public static int toInt(Object obj, int i) throws NumberFormatException {
        int i2;
        if (obj == null) {
            return i;
        }
        try {
            String strTrim = obj.toString().trim();
            if (strTrim.contains(".")) {
                i2 = Integer.parseInt(strTrim.substring(0, strTrim.lastIndexOf(".")));
            } else {
                i2 = Integer.parseInt(strTrim);
            }
            return i2;
        } catch (Exception unused) {
            return i;
        }
    }
}
