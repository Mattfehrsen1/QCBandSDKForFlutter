package com.qcwireless.qcwatch.ui.device.contact.bean;

import java.util.Comparator;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class SortComparator implements Comparator {
    private int compareCallNum = 0;

    @Override // java.util.Comparator
    public int compare(Object o1, Object o2) {
        ContactBean contactBean = (ContactBean) o1;
        if (contactBean.getFirstName().equals("#") && !((ContactBean) o2).getFirstName().equals("#")) {
            return 1;
        }
        if (contactBean.getFirstName().equals("#") || !((ContactBean) o2).getFirstName().equals("#")) {
            return contactBean.getFirstName().compareToIgnoreCase(((ContactBean) o2).getFirstName());
        }
        return -1;
    }

    private int compareString(String o1, String o2) {
        int iCompareTo;
        int iCompareTo2;
        int iCompareTo3;
        this.compareCallNum++;
        if (o1.length() == 0 && o2.length() == 0) {
            return 0;
        }
        if (o1.length() == 0) {
            return -1;
        }
        if (o2.length() == 0) {
            return 1;
        }
        String strSubstring = o1.substring(0, 1);
        String strSubstring2 = o2.substring(0, 1);
        int firstCharType = getFirstCharType(o1);
        int firstCharType2 = getFirstCharType(o2);
        if (firstCharType > firstCharType2) {
            return -1;
        }
        if (firstCharType < firstCharType2) {
            return 1;
        }
        if (firstCharType < 9 && firstCharType2 < 9) {
            int iCompareTo4 = strSubstring.compareTo(strSubstring2);
            return iCompareTo4 != 0 ? iCompareTo4 : compareString(o1.substring(1), o2.substring(1));
        }
        String strSubstring3 = PinYinStringHelper.getFirstPingYin(o1).substring(0, 1);
        String strSubstring4 = PinYinStringHelper.getFirstPingYin(o2).substring(0, 1);
        if (this.compareCallNum == 1 && (iCompareTo3 = strSubstring3.compareTo(strSubstring4)) != 0) {
            return iCompareTo3;
        }
        int firstCharType22 = getFirstCharType2(o1);
        int firstCharType23 = getFirstCharType2(o2);
        if (firstCharType22 > firstCharType23) {
            return -1;
        }
        if (firstCharType22 < firstCharType23) {
            return 1;
        }
        if (this.compareCallNum != 1 && (iCompareTo2 = strSubstring3.compareTo(strSubstring4)) != 0) {
            return iCompareTo2;
        }
        if (isLetter(o1) && isLetter(o2) && (iCompareTo = strSubstring.compareTo(strSubstring2)) != 0) {
            return iCompareTo;
        }
        if (isHanzi(o1) && isHanzi(o2)) {
            int iCompareTo5 = PinYinStringHelper.getFirstPingYin(o1).compareTo(PinYinStringHelper.getFirstPingYin(o2));
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
            int iCompareTo6 = strSubstring.compareTo(strSubstring2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return compareString(o1.substring(1), o2.substring(1));
    }

    private int getFirstCharType2(String str) {
        if (isHanzi(str)) {
            return 10;
        }
        if (isLetter(str)) {
            return 9;
        }
        return isNumber(str) ? 8 : 0;
    }

    private int getFirstCharType(String str) {
        if (isHanzi(str) || isLetter(str)) {
            return 10;
        }
        return isNumber(str) ? 8 : 0;
    }

    private boolean isLetter(String str) {
        char cCharAt = str.charAt(0);
        return Pattern.compile("^[A-Za-z]+$").matcher(cCharAt + "").matches();
    }

    private boolean isNumber(String str) {
        char cCharAt = str.charAt(0);
        return Pattern.compile("^[1-9]+$").matcher(cCharAt + "").matches();
    }

    private boolean isHanzi(String str) {
        char cCharAt = str.charAt(0);
        return Pattern.compile("[\\u4E00-\\u9FA5]+").matcher(cCharAt + "").matches();
    }
}
