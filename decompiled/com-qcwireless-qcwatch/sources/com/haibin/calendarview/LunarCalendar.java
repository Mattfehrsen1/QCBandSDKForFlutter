package com.haibin.calendarview;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class LunarCalendar {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static String[] DAY_STR;
    private static String[] MONTH_STR;
    private static String[] SPECIAL_FESTIVAL_STR;
    private static String[] TRADITION_FESTIVAL_STR;
    private static final Map<Integer, String[]> SPECIAL_FESTIVAL = new HashMap();
    private static String[] SOLAR_CALENDAR = null;
    private static final Map<Integer, String[]> SOLAR_TERMS = new HashMap();
    private static final int[] LUNAR_INFO = {19416, 19168, 42352, 21717, 53856, 55632, 91476, 22176, 39632, 21970, 19168, 42422, 42192, 53840, 119381, 46400, 54944, 44450, 38320, 84343, 18800, 42160, 46261, 27216, 27968, 109396, 11104, 38256, 21234, 18800, 25958, 54432, 59984, 28309, 23248, 11104, 100067, 37600, 116951, 51536, 54432, 120998, 46416, 22176, 107956, 9680, 37584, 53938, 43344, 46423, 27808, 46416, 86869, 19872, 42416, 83315, 21168, 43432, 59728, 27296, 44710, 43856, 19296, 43748, 42352, 21088, 62051, 55632, 23383, 22176, 38608, 19925, 19152, 42192, 54484, 53840, 54616, 46400, 46752, 103846, 38320, 18864, 43380, 42160, 45690, 27216, 27968, 44870, 43872, 38256, 19189, 18800, 25776, 29859, 59984, 27480, 21952, 43872, 38613, 37600, 51552, 55636, 54432, 55888, 30034, 22176, 43959, 9680, 37584, 51893, 43344, 46240, 47780, 44368, 21977, 19360, 42416, 86390, 21168, 43312, 31060, 27296, 44368, 23378, 19296, 42726, 42208, 53856, 60005, 54576, 23200, 30371, 38608, 19195, 19152, 42192, 118966, 53840, 54560, 56645, 46496, 22224, 21938, 18864, 42359, 42160, 43600, 111189, 27936, 44448, 84835, 37744, 18936, 18800, 25776, 92326, 59984, 27424, 108228, 43744, 41696, 53987, 51552, 54615, 54432, 55888, 23893, 22176, 42704, 21972, 21200, 43448, 43344, 46240, 46758, 44368, 21920, 43940, 42416, 21168, 45683, 26928, 29495, 27296, 44368, 84821, 19296, 42352, 21732, 53600, 59752, 54560, 55968, 92838, 22224, 19168, 43476, 41680, 53584, 62034, 54560};

    static void init(Context context) {
        if (MONTH_STR != null) {
            return;
        }
        TrunkBranchAnnals.init(context);
        SolarTermUtil.init(context);
        MONTH_STR = context.getResources().getStringArray(R.array.lunar_first_of_month);
        TRADITION_FESTIVAL_STR = context.getResources().getStringArray(R.array.tradition_festival);
        DAY_STR = context.getResources().getStringArray(R.array.lunar_str);
        SPECIAL_FESTIVAL_STR = context.getResources().getStringArray(R.array.special_festivals);
        SOLAR_CALENDAR = context.getResources().getStringArray(R.array.solar_festival);
    }

    private static String getTraditionFestival(int i, int i2, int i3) {
        if (i2 == 12 && i3 == daysInLunarMonth(i, i2)) {
            return TRADITION_FESTIVAL_STR[0];
        }
        String string = getString(i2, i3);
        for (String str : TRADITION_FESTIVAL_STR) {
            if (str.contains(string)) {
                return str.replace(string, "");
            }
        }
        return "";
    }

    private static String numToChineseMonth(int i, int i2) {
        if (i2 == 1) {
            return "闰" + MONTH_STR[i - 1];
        }
        return MONTH_STR[i - 1];
    }

    private static String numToChinese(int i, int i2, int i3) {
        if (i2 == 1) {
            return numToChineseMonth(i, i3);
        }
        return DAY_STR[i2 - 1];
    }

    public static int daysInLunarMonth(int i, int i2) {
        return (LUNAR_INFO[i + (-1900)] & (65536 >> i2)) == 0 ? 29 : 30;
    }

    private static String gregorianFestival(int i, int i2) {
        String string = getString(i, i2);
        for (String str : SOLAR_CALENDAR) {
            if (str.contains(string)) {
                return str.replace(string, "");
            }
        }
        return "";
    }

    private static String getString(int i, int i2) {
        String strValueOf;
        Object objValueOf;
        StringBuilder sb = new StringBuilder();
        if (i >= 10) {
            strValueOf = String.valueOf(i);
        } else {
            strValueOf = "0" + i;
        }
        sb.append(strValueOf);
        if (i2 >= 10) {
            objValueOf = Integer.valueOf(i2);
        } else {
            objValueOf = "0" + i2;
        }
        sb.append(objValueOf);
        return sb.toString();
    }

    private static String getSolarTerm(int i, int i2, int i3) {
        Map<Integer, String[]> map = SOLAR_TERMS;
        if (!map.containsKey(Integer.valueOf(i))) {
            map.put(Integer.valueOf(i), SolarTermUtil.getSolarTerms(i));
        }
        String[] strArr = map.get(Integer.valueOf(i));
        String str = i + getString(i2, i3);
        for (String str2 : strArr) {
            if (str2.contains(str)) {
                return str2.replace(str, "");
            }
        }
        return "";
    }

    public static String getLunarText(int i, int i2, int i3) {
        String solarTerm = getSolarTerm(i, i2, i3);
        String strGregorianFestival = gregorianFestival(i2, i3);
        if (!TextUtils.isEmpty(strGregorianFestival)) {
            return strGregorianFestival;
        }
        if (!TextUtils.isEmpty(solarTerm)) {
            return solarTerm;
        }
        int[] iArrSolarToLunar = LunarUtil.solarToLunar(i, i2, i3);
        String traditionFestival = getTraditionFestival(iArrSolarToLunar[0], iArrSolarToLunar[1], iArrSolarToLunar[2]);
        return !TextUtils.isEmpty(traditionFestival) ? traditionFestival : numToChinese(iArrSolarToLunar[1], iArrSolarToLunar[2], iArrSolarToLunar[3]);
    }

    private static String getSpecialFestival(int i, int i2, int i3) {
        Map<Integer, String[]> map = SPECIAL_FESTIVAL;
        if (!map.containsKey(Integer.valueOf(i))) {
            map.put(Integer.valueOf(i), getSpecialFestivals(i));
        }
        String[] strArr = map.get(Integer.valueOf(i));
        String str = i + getString(i2, i3);
        for (String str2 : strArr) {
            if (str2.contains(str)) {
                return str2.replace(str, "");
            }
        }
        return "";
    }

    private static String[] getSpecialFestivals(int i) {
        String[] strArr = new String[3];
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(i, 4, 1);
        int i2 = (7 - calendar.get(7)) + 1;
        if (i2 == 7) {
            strArr[0] = dateToString(i, 5, i2 + 1) + SPECIAL_FESTIVAL_STR[0];
        } else {
            strArr[0] = dateToString(i, 5, i2 + 7 + 1) + SPECIAL_FESTIVAL_STR[0];
        }
        calendar.set(i, 5, 1);
        int i3 = (7 - calendar.get(7)) + 1;
        if (i3 == 7) {
            strArr[1] = dateToString(i, 6, i3 + 7 + 1) + SPECIAL_FESTIVAL_STR[1];
        } else {
            strArr[1] = dateToString(i, 6, i3 + 7 + 7 + 1) + SPECIAL_FESTIVAL_STR[1];
        }
        calendar.set(i, 10, 1);
        int i4 = (7 - calendar.get(7)) + 1;
        if (i4 <= 2) {
            strArr[2] = dateToString(i, 11, i4 + 21 + 5) + SPECIAL_FESTIVAL_STR[2];
        } else {
            strArr[2] = dateToString(i, 11, i4 + 14 + 5) + SPECIAL_FESTIVAL_STR[2];
        }
        return strArr;
    }

    private static String dateToString(int i, int i2, int i3) {
        return i + getString(i2, i3);
    }

    public static void setupLunarCalendar(Calendar calendar) {
        int year = calendar.getYear();
        int month = calendar.getMonth();
        int day = calendar.getDay();
        calendar.setWeekend(CalendarUtil.isWeekend(calendar));
        calendar.setWeek(CalendarUtil.getWeekFormCalendar(calendar));
        Calendar calendar2 = new Calendar();
        calendar.setLunarCalendar(calendar2);
        int[] iArrSolarToLunar = LunarUtil.solarToLunar(year, month, day);
        calendar2.setYear(iArrSolarToLunar[0]);
        calendar2.setMonth(iArrSolarToLunar[1]);
        calendar2.setDay(iArrSolarToLunar[2]);
        calendar.setLeapYear(CalendarUtil.isLeapYear(year));
        if (iArrSolarToLunar[3] == 1) {
            calendar.setLeapMonth(iArrSolarToLunar[1]);
            calendar2.setLeapMonth(iArrSolarToLunar[1]);
        }
        String solarTerm = getSolarTerm(year, month, day);
        String strGregorianFestival = gregorianFestival(month, day);
        String traditionFestival = getTraditionFestival(iArrSolarToLunar[0], iArrSolarToLunar[1], iArrSolarToLunar[2]);
        String strNumToChinese = numToChinese(iArrSolarToLunar[1], iArrSolarToLunar[2], iArrSolarToLunar[3]);
        if (TextUtils.isEmpty(strGregorianFestival)) {
            strGregorianFestival = getSpecialFestival(year, month, day);
        }
        calendar.setSolarTerm(solarTerm);
        calendar.setGregorianFestival(strGregorianFestival);
        calendar.setTraditionFestival(traditionFestival);
        calendar2.setTraditionFestival(traditionFestival);
        calendar2.setSolarTerm(solarTerm);
        if (!TextUtils.isEmpty(solarTerm)) {
            calendar.setLunar(solarTerm);
        } else if (!TextUtils.isEmpty(strGregorianFestival)) {
            calendar.setLunar(strGregorianFestival);
        } else if (!TextUtils.isEmpty(traditionFestival)) {
            calendar.setLunar(traditionFestival);
        } else {
            calendar.setLunar(strNumToChinese);
        }
        calendar2.setLunar(strNumToChinese);
    }

    public static String getLunarText(Calendar calendar) {
        return getLunarText(calendar.getYear(), calendar.getMonth(), calendar.getDay());
    }
}
