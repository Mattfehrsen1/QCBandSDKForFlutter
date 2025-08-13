package com.oudmon.ble.base.util;

import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/util/StringUtils.class */
public class StringUtils {
    private static final Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    private static final String TAG = "Jxr35";
    private static final String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";

    public static boolean isMobileNum(String mobiles) {
        Pattern p = Pattern.compile("^1\\d{10}$");
        Matcher matcher = p.matcher(mobiles);
        return matcher.matches();
    }

    public static boolean isValid(String phone) {
        if (phone.length() != 11) {
            Log.i(TAG, "手机号应为11位数");
            return false;
        }
        return Pattern.compile(regex).matcher(phone).matches();
    }

    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input)) {
            return true;
        }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmail(String email) {
        if (email == null || email.trim().length() == 0) {
            return false;
        }
        return emailer.matcher(email).matches();
    }

    public static int[] stringToIntArray(String s) {
        if (TextUtils.isEmpty(s)) {
            return new int[0];
        }
        String[] strings = s.trim().split(",");
        int[] intArray = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            intArray[i] = (int) Float.parseFloat(strings[i].trim());
        }
        return intArray;
    }

    public static List<Integer> stringToIntegerList(String s) {
        if (TextUtils.isEmpty(s)) {
            return new ArrayList();
        }
        String[] strings = s.trim().split(",");
        List<Integer> list = new ArrayList<>();
        for (String str : strings) {
            list.add(Integer.valueOf(Integer.parseInt(str.trim())));
        }
        return list;
    }

    public static String intArrayToString(int[] intArray) {
        if (intArray == null || intArray.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i : intArray) {
            sb.append(i).append(",");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    public static float[] stringToFloatArray(String s) {
        if (TextUtils.isEmpty(s)) {
            return null;
        }
        String[] strings = s.trim().split(",");
        float[] floatArray = new float[strings.length];
        for (int i = 0; i < strings.length; i++) {
            floatArray[i] = Float.parseFloat(strings[i]);
        }
        return floatArray;
    }

    public static String floatArrayToString(float[] floatArray) {
        if (floatArray == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (float f : floatArray) {
            sb.append(f).append(",");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    public static List<Float> stringToFloatList(String s) {
        if (TextUtils.isEmpty(s)) {
            return new ArrayList();
        }
        String[] strings = s.trim().split(",");
        List<Float> list = new ArrayList<>();
        for (String str : strings) {
            list.add(Float.valueOf(Float.parseFloat(str)));
        }
        return list;
    }
}
