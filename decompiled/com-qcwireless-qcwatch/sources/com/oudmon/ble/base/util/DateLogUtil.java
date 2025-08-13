package com.oudmon.ble.base.util;

import com.blankj.utilcode.constant.TimeConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class DateLogUtil {
    public static long Hour_S_Min = 3600;
    public static final String dFyyyyMMdd1 = "yyyy-MM-dd";
    public static final String yyyyMMdd_HHmm = "yyyy-MM-dd HH:mm";
    public static final String yyyyMMdd_HHmmss = "yyyy-MM-dd HH:mm:ss";
    private Calendar c;
    public static final Locale localeObject = new Locale("en");
    private static final ThreadLocal<SimpleDateFormat> dFMMdd = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.ble.base.util.DateLogUtil.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM-dd", DateLogUtil.localeObject);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFMMdd_HHmm = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.ble.base.util.DateLogUtil.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM-dd HH:mm", DateLogUtil.localeObject);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFyyyyMM = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.ble.base.util.DateLogUtil.3
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM", DateLogUtil.localeObject);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFyyyyMMdd = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.ble.base.util.DateLogUtil.4
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd", DateLogUtil.localeObject);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFyyyyMMdd_HHmm = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.ble.base.util.DateLogUtil.5
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm", DateLogUtil.localeObject);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFyyyyMMdd_HHmmss = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.ble.base.util.DateLogUtil.6
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", DateLogUtil.localeObject);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFHHmm = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.ble.base.util.DateLogUtil.7
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm", DateLogUtil.localeObject);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFHHmmss = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.ble.base.util.DateLogUtil.8
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss", DateLogUtil.localeObject);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFSyyyyMMdd = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.ble.base.util.DateLogUtil.9
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd", DateLogUtil.localeObject);
        }
    };
    public static SimpleDateFormat yyyyMMdd_HHmmssF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat dFyyyyMMddF = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat dFyyyyMMddmmF = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public enum DateFormater {
        MMdd,
        MMdd_HHmm,
        yyyyMM,
        yyyyMMdd,
        yyyyMMdd_HHmm,
        yyyyMMdd_HHmmss,
        HHmm,
        HHmmss,
        yyyyMMddHHmm,
        SyyyyMMdd,
        dFyyyyMMdd,
        dFHHmm
    }

    public long getZeroTime() {
        return new DateLogUtil(getYear(), getMonth(), getDay()).getUnixTimestamp();
    }

    public String getZeroTimeYyyyMMdd_HHmmssDate() {
        return new DateLogUtil(getYear(), getMonth(), getDay()).getYyyyMMdd_HHmmssDate();
    }

    public long getZeroTime1() {
        return new DateLogUtil(getYear(), getMonth(), getDay()).getTimestamp();
    }

    public static long getFirstDayMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, 0);
        calendar.set(5, 1);
        return calendar.getTimeInMillis();
    }

    public static long getLastDayMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, 0);
        calendar.set(5, calendar.getActualMaximum(5));
        return calendar.getTimeInMillis();
    }

    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(5);
    }

    public int getTodayMin() {
        return Math.round((this.c.getTimeInMillis() - getZeroTime1()) / 60000) + 1;
    }

    public int getTodayMinNoPlus() {
        return Math.round((this.c.getTimeInMillis() - getZeroTime1()) / 60000);
    }

    public static boolean isSameDay(Date date, Date date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return ((calendar.get(1) == calendar2.get(1)) && calendar.get(2) == calendar2.get(2)) && calendar.get(5) == calendar2.get(5);
    }

    public boolean isSameDay(long j, boolean z) {
        DateLogUtil dateLogUtil = new DateLogUtil(j, z);
        return dateLogUtil.getYear() == getYear() && dateLogUtil.getMonth() == getMonth() && dateLogUtil.getDay() == getDay();
    }

    public static boolean isSameMonth(Date date, Date date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return (calendar.get(1) == calendar2.get(1)) && calendar.get(2) == calendar2.get(2);
    }

    public static long getPreOrNextTimeByDay(long j) {
        return System.currentTimeMillis() - (j * 86400000);
    }

    public static long getGMTDate(long j) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            String str = simpleDateFormat.format(new Date(1000 * j));
            return new DateLogUtil(Integer.parseInt(str.substring(0, 4)), Integer.parseInt(str.substring(5, 7)), Integer.parseInt(str.substring(8, 10)), Integer.parseInt(str.substring(11, 13)), Integer.parseInt(str.substring(14, 16)), 0).getUnixTimestamp();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            DateLogUtil dateLogUtil = new DateLogUtil(j, true);
            dateLogUtil.setHour(0);
            dateLogUtil.setMinute(0);
            dateLogUtil.setSecond(0);
            return dateLogUtil.getUnixTimestamp();
        }
    }

    public static DateLogUtil valueOf(String str) {
        Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}");
        try {
            if (Pattern.compile("[0-9]{2}-[0-9]{2}").matcher(str).matches()) {
                return parse(str, DateFormater.MMdd);
            }
            if (Pattern.compile("[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}").matcher(str).matches()) {
                return parse(str, DateFormater.MMdd_HHmm);
            }
            if (Pattern.compile("[0-9]{4}-[0-9]{2}").matcher(str).matches()) {
                return parse(str, DateFormater.yyyyMM);
            }
            if (Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}").matcher(str).matches()) {
                return parse(str, DateFormater.yyyyMMdd);
            }
            if (Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}").matcher(str).matches()) {
                return parse(str, DateFormater.yyyyMMdd_HHmm);
            }
            if (Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}").matcher(str).matches()) {
                return parse(str, DateFormater.yyyyMMdd_HHmmss);
            }
            if (Pattern.compile("[0-9]{2}:[0-9]{2}").matcher(str).matches()) {
                return parse(str, DateFormater.HHmm);
            }
            if (Pattern.compile("[0-9]{2}:[0-9]{2}:[0-9]{2}").matcher(str).matches()) {
                return parse(str, DateFormater.HHmmss);
            }
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: com.oudmon.ble.base.util.DateLogUtil$10, reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$com$oudmon$ble$base$util$DateLogUtil$DateFormater;

        static {
            int[] iArr = new int[DateFormater.values().length];
            $SwitchMap$com$oudmon$ble$base$util$DateLogUtil$DateFormater = iArr;
            try {
                iArr[DateFormater.MMdd.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$oudmon$ble$base$util$DateLogUtil$DateFormater[DateFormater.MMdd_HHmm.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$oudmon$ble$base$util$DateLogUtil$DateFormater[DateFormater.yyyyMM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$oudmon$ble$base$util$DateLogUtil$DateFormater[DateFormater.yyyyMMdd.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$oudmon$ble$base$util$DateLogUtil$DateFormater[DateFormater.dFyyyyMMdd.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$oudmon$ble$base$util$DateLogUtil$DateFormater[DateFormater.yyyyMMdd_HHmm.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$oudmon$ble$base$util$DateLogUtil$DateFormater[DateFormater.yyyyMMdd_HHmmss.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$oudmon$ble$base$util$DateLogUtil$DateFormater[DateFormater.HHmm.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$oudmon$ble$base$util$DateLogUtil$DateFormater[DateFormater.HHmmss.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$oudmon$ble$base$util$DateLogUtil$DateFormater[DateFormater.SyyyyMMdd.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public static DateLogUtil parse(String str, DateFormater dateFormater) throws ParseException {
        Date date;
        switch (AnonymousClass10.$SwitchMap$com$oudmon$ble$base$util$DateLogUtil$DateFormater[dateFormater.ordinal()]) {
            case 1:
                date = dFMMdd.get().parse(str);
                break;
            case 2:
                date = dFMMdd_HHmm.get().parse(str);
                break;
            case 3:
                date = dFyyyyMM.get().parse(str);
                break;
            case 4:
                date = dFSyyyyMMdd.get().parse(str);
                break;
            case 5:
                date = dFyyyyMMdd.get().parse(str);
                break;
            case 6:
                date = dFyyyyMMdd_HHmm.get().parse(str);
                break;
            case 7:
                date = dFyyyyMMdd_HHmmss.get().parse(str);
                break;
            case 8:
                date = dFHHmm.get().parse(str);
                break;
            case 9:
                date = dFHHmmss.get().parse(str);
                break;
            default:
                date = null;
                break;
        }
        return new DateLogUtil(date);
    }

    public DateLogUtil() {
        this.c = Calendar.getInstance();
    }

    public DateLogUtil(long j, boolean z) {
        Calendar calendar = Calendar.getInstance();
        this.c = calendar;
        if (z) {
            calendar.setTimeInMillis(j * 1000);
        } else {
            calendar.setTimeInMillis(j);
        }
    }

    public DateLogUtil(Date date) {
        Calendar calendar = Calendar.getInstance();
        this.c = calendar;
        calendar.setTime(date);
    }

    public DateLogUtil(int i, int i2, int i3) {
        this(i, i2, i3, 0, 0, 0);
    }

    public DateLogUtil(int i, int i2, int i3, int i4, int i5) {
        this(i, i2, i3, i4, i5, 0);
    }

    public DateLogUtil(int i, int i2, int i3, int i4, int i5, int i6) {
        Calendar calendar = Calendar.getInstance();
        this.c = calendar;
        calendar.set(1, i);
        this.c.set(2, i2 - 1);
        this.c.set(5, i3);
        this.c.set(11, i4);
        this.c.set(12, i5);
        this.c.set(13, i6);
    }

    public DateLogUtil(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        this.c = calendar;
        calendar.set(11, i);
        this.c.set(12, i2);
    }

    public boolean isToday() {
        DateLogUtil dateLogUtil = new DateLogUtil();
        return getYear() == dateLogUtil.getYear() && getMonth() == dateLogUtil.getMonth() && getDay() == dateLogUtil.getDay();
    }

    public boolean isSameWeek(int i) {
        return i == new DateLogUtil(new Date()).getWeekOfYear();
    }

    public boolean isSameMonth(int i, int i2) {
        return i == getMonth() && getYear() == i2;
    }

    public int daysBetweenMe(DateLogUtil dateLogUtil) {
        return (int) (Math.abs(getUnixTimestamp() - dateLogUtil.getUnixTimestamp()) / 86400);
    }

    public Date toDate() {
        return this.c.getTime();
    }

    public String toFormatString(DateFormater dateFormater) {
        Date date = toDate();
        switch (AnonymousClass10.$SwitchMap$com$oudmon$ble$base$util$DateLogUtil$DateFormater[dateFormater.ordinal()]) {
            case 1:
                return dFMMdd.get().format(date);
            case 2:
                return dFMMdd_HHmm.get().format(date);
            case 3:
                return dFyyyyMM.get().format(date);
            case 4:
                return dFyyyyMMdd.get().format(date);
            case 5:
                return dFyyyyMMdd.get().format(date);
            case 6:
                return dFyyyyMMdd_HHmm.get().format(date);
            case 7:
                return dFyyyyMMdd_HHmmss.get().format(date);
            case 8:
                return dFHHmm.get().format(date);
            case 9:
                return dFHHmmss.get().format(date);
            case 10:
                return dFSyyyyMMdd.get().format(date);
            default:
                return "Unknown";
        }
    }

    public static Date String2Date(String str, String str2) {
        char c = 65535;
        try {
            int iHashCode = str.hashCode();
            if (iHashCode != -1172057030) {
                if (iHashCode != -159776256) {
                    if (iHashCode == 1333195168 && str.equals("yyyy-MM-dd HH:mm:ss")) {
                        c = 0;
                    }
                } else if (str.equals("yyyy-MM-dd")) {
                    c = 2;
                }
            } else if (str.equals("yyyy-MM-dd HH:mm")) {
                c = 1;
            }
            if (c == 0) {
                return yyyyMMdd_HHmmssF.parse(str2);
            }
            if (c == 1) {
                return dFyyyyMMddmmF.parse(str2);
            }
            if (c != 2) {
                return null;
            }
            return dFyyyyMMddF.parse(str2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getMMddDate() {
        return toFormatString(DateFormater.MMdd);
    }

    public String getMMdd_HHmmDate() {
        return toFormatString(DateFormater.MMdd_HHmm);
    }

    public String getY_M_D() {
        return toFormatString(DateFormater.dFyyyyMMdd);
    }

    public String getY_M_D_H_M_S() {
        return toFormatString(DateFormater.yyyyMMdd_HHmmss);
    }

    public String getY_M_D_H_M() {
        return toFormatString(DateFormater.yyyyMMdd_HHmm);
    }

    public String getYyyyMMDate() {
        return toFormatString(DateFormater.yyyyMM);
    }

    public String getYyyyMMddDate() {
        return toFormatString(DateFormater.yyyyMMdd);
    }

    public String getYyyyMMdd_HHmmDate() {
        return toFormatString(DateFormater.yyyyMMdd_HHmm);
    }

    public String getYyyyMMdd_HHmmssDate() {
        return toFormatString(DateFormater.yyyyMMdd_HHmmss);
    }

    public String getHHmmDate() {
        return toFormatString(DateFormater.HHmm);
    }

    public String getHHmmssDate() {
        return toFormatString(DateFormater.HHmmss);
    }

    public String getSyyyyMMddDate() {
        return toFormatString(DateFormater.SyyyyMMdd);
    }

    public String getyyyyMMddDate() {
        return toFormatString(DateFormater.yyyyMMdd);
    }

    public int getYear() {
        return this.c.get(1);
    }

    public void setYear(int i) {
        this.c.set(1, i);
    }

    public int getMonth() {
        return this.c.get(2) + 1;
    }

    public void setMonth(int i) {
        this.c.set(2, i - 1);
    }

    public int getDay() {
        return this.c.get(5);
    }

    public int getDaysOfThisMonth() {
        return this.c.get(5);
    }

    public void setDay(int i) {
        this.c.set(5, i);
    }

    public void addDay(int i) {
        this.c.add(5, i);
    }

    public void addMonth(int i) {
        this.c.add(2, i);
    }

    public int getHour() {
        return this.c.get(11);
    }

    public void setHour(int i) {
        this.c.set(11, i);
    }

    public int getMinute() {
        return this.c.get(12);
    }

    public void setMinute(int i) {
        this.c.set(12, i);
    }

    public int getSecond() {
        return this.c.get(13);
    }

    public void setSecond(int i) {
        this.c.set(13, i);
    }

    public long getTimestamp() {
        return this.c.getTimeInMillis();
    }

    public void setTimestamp(long j) {
        this.c.setTimeInMillis(j);
    }

    public long getUnixTimestamp() {
        return this.c.getTimeInMillis() / 1000;
    }

    public void setUnixTimestamp(long j) {
        this.c.setTimeInMillis(j * 1000);
    }

    public int getDayOfWeek() {
        return this.c.get(7);
    }

    public int getWeekOfYear() {
        return this.c.get(3);
    }

    public int getWeekOfMonth() {
        return this.c.get(4);
    }

    public String getMonDate() {
        int dayOfWeek = getDayOfWeek();
        Calendar calendar = this.c;
        calendar.add(5, calendar.getFirstDayOfWeek() - dayOfWeek);
        return new SimpleDateFormat("yyyyMMdd").format(this.c.getTime());
    }

    public String toString() {
        return getYyyyMMdd_HHmmssDate();
    }

    public static String getTime(long j) {
        long jCurrentTimeMillis = (System.currentTimeMillis() - j) / 86400000;
        DateLogUtil dateLogUtil = new DateLogUtil(j, false);
        if (jCurrentTimeMillis > 0) {
            return dateLogUtil.getYyyyMMddDate();
        }
        return dateLogUtil.getHHmmDate();
    }

    public static long getSunDayTimeFromWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return calendar.getTime().getTime() - ((calendar.get(7) - 1) * TimeConstants.DAY);
    }

    public static Date getDateByWeekMagin(int i) {
        return new Date(getSunDayTimeFromWeek() + (i * TimeConstants.DAY));
    }

    public static int differentDaysByMillisecond(Date date, Date date2) {
        return (int) ((date2.getTime() - date.getTime()) / 86400000);
    }

    public static long dateStr2Stamp(String str) {
        try {
            return Long.parseLong(String.valueOf(new SimpleDateFormat("yyyyMMdd").parse(str).getTime()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static long dateY_M_D2Stamp(String str) {
        try {
            return Long.parseLong(String.valueOf(new SimpleDateFormat("yyyy-MM-dd").parse(str).getTime()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static String getMarginMin(long j, long j2) {
        return ((j - j2) / 60) + "";
    }

    public static int getAgeByBirthday(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (calendar.before(date)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        int i = calendar.get(1);
        int i2 = calendar.get(2) + 1;
        int i3 = calendar.get(5);
        calendar.setTime(date);
        int i4 = calendar.get(1);
        int i5 = calendar.get(2) + 1;
        int i6 = i - i4;
        return i2 <= i5 ? (i2 != i5 || i3 < calendar.get(5)) ? i6 - 1 : i6 : i6;
    }

    public static int getDayOfMonth() {
        Calendar.getInstance().setTime(new Date());
        return r0.get(5) - 1;
    }
}
