package com.qcwireless.qcwatch.ui.device.contact.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/* loaded from: classes3.dex */
public class PinYinStringHelper {
    public static Map<String, String> specialHanzi;

    static {
        HashMap map = new HashMap();
        specialHanzi = map;
        map.put("重", "虫");
        specialHanzi.put("贾", "甲");
        specialHanzi.put("瞿", "渠");
        specialHanzi.put("单", "擅");
        specialHanzi.put("沈", "审");
        specialHanzi.put("解", "谢");
        specialHanzi.put("俞", "于");
        specialHanzi.put("曾", "增");
    }

    public static String getPingYin(String src) {
        if (src.trim().length() < 1) {
            return null;
        }
        String strSubstring = src.trim().substring(0, 1);
        if (specialHanzi.containsKey(strSubstring)) {
            src = src.replace(strSubstring, specialHanzi.get(strSubstring));
        }
        char[] charArray = src.toCharArray();
        String[] strArr = new String[charArray.length];
        HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
        hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        hanyuPinyinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        int length = charArray.length;
        String str = "";
        for (int i = 0; i < length; i++) {
            try {
                str = Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+") ? str + PinyinHelper.toHanyuPinyinStringArray(charArray[i], hanyuPinyinOutputFormat)[0] : str + Character.toString(charArray[i]);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
                return str.toUpperCase();
            }
        }
        return str.toUpperCase();
    }

    public static String getFirstPingYin(String src) {
        if (src.length() < 1) {
            return null;
        }
        if (!isHanzi(src)) {
            return src.substring(0, 1).toUpperCase();
        }
        String strSubstring = src.substring(0, 1);
        if (specialHanzi.containsKey(strSubstring)) {
            strSubstring = specialHanzi.get(strSubstring);
        }
        char[] charArray = strSubstring.toCharArray();
        String[] strArr = new String[charArray.length];
        HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
        hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        hanyuPinyinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        int length = charArray.length;
        String str = "";
        for (int i = 0; i < length; i++) {
            try {
                str = Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+") ? str + PinyinHelper.toHanyuPinyinStringArray(charArray[i], hanyuPinyinOutputFormat)[0] : str + Character.toString(charArray[i]);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
                return str.toUpperCase();
            }
        }
        return str.toUpperCase();
    }

    public static String getPinYinHeadChar(String str) {
        if (str.trim().length() < 1) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            String[] hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(cCharAt);
            if (hanyuPinyinStringArray != null) {
                sb.append(hanyuPinyinStringArray[0].charAt(0));
            } else {
                sb.append(cCharAt);
            }
        }
        return sb.toString().toUpperCase();
    }

    public static String getAlpha(String str) {
        if (str == null || str.trim().length() == 0) {
            return "#";
        }
        if (!isHanzi(str) && !isLetter(str)) {
            return "#";
        }
        char cCharAt = str.trim().substring(0, 1).charAt(0);
        if (Pattern.compile("^[A-Za-z]+$").matcher(cCharAt + "").matches()) {
            return (cCharAt + "").toUpperCase();
        }
        String headChar = getHeadChar(str);
        return (headChar == null || headChar.length() <= 0) ? "#" : headChar.substring(0, 1);
    }

    public static String getHeadChar(String str) {
        String str2;
        if (str == null) {
            return "#";
        }
        try {
            if (str.trim().length() < 1) {
                return "#";
            }
            String strSubstring = str.trim().substring(0, 1);
            if (specialHanzi.containsKey(strSubstring)) {
                str = specialHanzi.get(strSubstring);
            }
            char cCharAt = str.charAt(0);
            String[] hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(cCharAt);
            if (hanyuPinyinStringArray != null) {
                str2 = "" + hanyuPinyinStringArray[0].charAt(0);
            } else {
                str2 = "" + cCharAt;
            }
            return str2.toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return "#";
        }
    }

    public static boolean isHanzi(String str) {
        char cCharAt = str.charAt(0);
        Pattern patternCompile = Pattern.compile("[\\u4E00-\\u9FA5]+");
        StringBuilder sb = new StringBuilder();
        sb.append(cCharAt);
        sb.append("");
        return patternCompile.matcher(sb.toString()).matches();
    }

    public static boolean isLetter(String str) {
        char cCharAt = str.charAt(0);
        Pattern patternCompile = Pattern.compile("^[A-Za-z]+$");
        StringBuilder sb = new StringBuilder();
        sb.append(cCharAt);
        sb.append("");
        return patternCompile.matcher(sb.toString()).matches();
    }
}
