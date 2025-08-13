package com.oudmon.ble.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/util/DateUtil.class */
public class DateUtil {
    private Calendar c;
    public static long Hour_S_Min = 3600;
    private static final ThreadLocal<SimpleDateFormat> dFMMdd = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.ble.base.util.DateUtil.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM-dd");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFMMdd_HHmm = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.ble.base.util.DateUtil.2
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM-dd HH:mm");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFyyyyMM = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.ble.base.util.DateUtil.3
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFyyyyMMdd = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.ble.base.util.DateUtil.4
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DateUtil.dFyyyyMMdd1);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFyyyyMMdd_HHmm = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.ble.base.util.DateUtil.5
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DateUtil.yyyyMMdd_HHmm);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFyyyyMMdd_HHmmss = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.ble.base.util.DateUtil.6
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DateUtil.yyyyMMdd_HHmmss);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFHHmm = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.ble.base.util.DateUtil.7
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFHHmmss = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.ble.base.util.DateUtil.8
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFSyyyyMMdd = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.ble.base.util.DateUtil.9
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };
    public static final String yyyyMMdd_HHmmss = "yyyy-MM-dd HH:mm:ss";
    public static SimpleDateFormat yyyyMMdd_HHmmssF = new SimpleDateFormat(yyyyMMdd_HHmmss);
    public static final String dFyyyyMMdd1 = "yyyy-MM-dd";
    public static SimpleDateFormat dFyyyyMMddF = new SimpleDateFormat(dFyyyyMMdd1);
    public static final String yyyyMMdd_HHmm = "yyyy-MM-dd HH:mm";
    public static SimpleDateFormat dFyyyyMMddmmF = new SimpleDateFormat(yyyyMMdd_HHmm);

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/util/DateUtil$DateFormater.class */
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
        DateUtil dateUtil = new DateUtil(getYear(), getMonth(), getDay());
        return dateUtil.getUnixTimestamp();
    }

    public String getZeroTimeYyyyMMdd_HHmmssDate() {
        DateUtil dateUtil = new DateUtil(getYear(), getMonth(), getDay());
        return dateUtil.getYyyyMMdd_HHmmssDate();
    }

    public long getZeroTime1() {
        DateUtil dateUtil = new DateUtil(getYear(), getMonth(), getDay());
        return dateUtil.getTimestamp();
    }

    public static long getFirstDayMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(2, 0);
        c.set(5, 1);
        return c.getTimeInMillis();
    }

    public static long getLastDayMonth(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(2, 0);
        ca.set(5, ca.getActualMaximum(5));
        return ca.getTimeInMillis();
    }

    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(5);
    }

    public int getTodayMin() {
        long zeroTime = getZeroTime1();
        int i = Math.round((this.c.getTimeInMillis() - zeroTime) / 60000) + 1;
        return i;
    }

    public static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        boolean isSameYear = cal1.get(1) == cal2.get(1);
        boolean isSameMonth = isSameYear && cal1.get(2) == cal2.get(2);
        boolean isSameDate = isSameMonth && cal1.get(5) == cal2.get(5);
        return isSameDate;
    }

    public boolean isSameDay(long compare_time, boolean isUnix) {
        DateUtil compare_dt = new DateUtil(compare_time, isUnix);
        if (compare_dt.getYear() == getYear() && compare_dt.getMonth() == getMonth() && compare_dt.getDay() == getDay()) {
            return true;
        }
        return false;
    }

    public static boolean isSameMonth(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        boolean isSameYear = cal1.get(1) == cal2.get(1);
        boolean isSameMonth = isSameYear && cal1.get(2) == cal2.get(2);
        return isSameMonth;
    }

    public static long getPreOrNextTimeByDay(long marignSize) {
        long lastTime = System.currentTimeMillis();
        return lastTime - (marignSize * 86400000);
    }

    public static long getGMTDate(long record_date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            String time = sdf.format(new Date(record_date * 1000));
            String ti_year = time.substring(0, 4);
            String ti_month = time.substring(5, 7);
            String ti_date = time.substring(8, 10);
            String ti_hour = time.substring(11, 13);
            String ti_min = time.substring(14, 16);
            return new DateUtil(Integer.parseInt(ti_year), Integer.parseInt(ti_month), Integer.parseInt(ti_date), Integer.parseInt(ti_hour), Integer.parseInt(ti_min), 0).getUnixTimestamp();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            DateUtil dateUtil = new DateUtil(record_date, true);
            dateUtil.setHour(0);
            dateUtil.setMinute(0);
            dateUtil.setSecond(0);
            return dateUtil.getUnixTimestamp();
        }
    }

    public static DateUtil valueOf(String sdate) {
        Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}");
        try {
            Pattern p = Pattern.compile("[0-9]{2}-[0-9]{2}");
            if (p.matcher(sdate).matches()) {
                return parse(sdate, DateFormater.MMdd);
            }
            Pattern p2 = Pattern.compile("[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}");
            if (p2.matcher(sdate).matches()) {
                return parse(sdate, DateFormater.MMdd_HHmm);
            }
            Pattern p3 = Pattern.compile("[0-9]{4}-[0-9]{2}");
            if (p3.matcher(sdate).matches()) {
                return parse(sdate, DateFormater.yyyyMM);
            }
            Pattern p4 = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
            if (p4.matcher(sdate).matches()) {
                return parse(sdate, DateFormater.yyyyMMdd);
            }
            Pattern p5 = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}");
            if (p5.matcher(sdate).matches()) {
                return parse(sdate, DateFormater.yyyyMMdd_HHmm);
            }
            Pattern p6 = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}");
            if (p6.matcher(sdate).matches()) {
                return parse(sdate, DateFormater.yyyyMMdd_HHmmss);
            }
            Pattern p7 = Pattern.compile("[0-9]{2}:[0-9]{2}");
            if (p7.matcher(sdate).matches()) {
                return parse(sdate, DateFormater.HHmm);
            }
            Pattern p8 = Pattern.compile("[0-9]{2}:[0-9]{2}:[0-9]{2}");
            if (p8.matcher(sdate).matches()) {
                return parse(sdate, DateFormater.HHmmss);
            }
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static DateUtil parse(String sdate, DateFormater formater) throws ParseException {
        Date date = null;
        switch (formater) {
            case MMdd:
                date = dFMMdd.get().parse(sdate);
                break;
            case MMdd_HHmm:
                date = dFMMdd_HHmm.get().parse(sdate);
                break;
            case yyyyMM:
                date = dFyyyyMM.get().parse(sdate);
                break;
            case yyyyMMdd:
                date = dFSyyyyMMdd.get().parse(sdate);
                break;
            case dFyyyyMMdd:
                date = dFyyyyMMdd.get().parse(sdate);
                break;
            case yyyyMMdd_HHmm:
                date = dFyyyyMMdd_HHmm.get().parse(sdate);
                break;
            case yyyyMMdd_HHmmss:
                date = dFyyyyMMdd_HHmmss.get().parse(sdate);
                break;
            case HHmm:
                date = dFHHmm.get().parse(sdate);
                break;
            case HHmmss:
                date = dFHHmmss.get().parse(sdate);
                break;
        }
        return new DateUtil(date);
    }

    public DateUtil() {
        this.c = Calendar.getInstance();
    }

    public DateUtil(long timestamp, boolean isUnix) {
        this.c = Calendar.getInstance();
        if (isUnix) {
            this.c.setTimeInMillis(timestamp * 1000);
        } else {
            this.c.setTimeInMillis(timestamp);
        }
    }

    public DateUtil(Date date) {
        this.c = Calendar.getInstance();
        this.c.setTime(date);
    }

    public DateUtil(int year, int month, int day) {
        this(year, month, day, 0, 0, 0);
    }

    public DateUtil(int year, int month, int day, int hour, int minute) {
        this(year, month, day, hour, minute, 0);
    }

    public DateUtil(int year, int month, int day, int hour, int minute, int second) {
        this.c = Calendar.getInstance();
        this.c.set(1, year);
        this.c.set(2, month - 1);
        this.c.set(5, day);
        this.c.set(11, hour);
        this.c.set(12, minute);
        this.c.set(13, second);
    }

    public DateUtil(int hour, int minute) {
        this.c = Calendar.getInstance();
        this.c.set(11, hour);
        this.c.set(12, minute);
    }

    public boolean isToday() {
        DateUtil d = new DateUtil();
        return getYear() == d.getYear() && getMonth() == d.getMonth() && getDay() == d.getDay();
    }

    public boolean isSameWeek(int number) {
        DateUtil date = new DateUtil(new Date());
        int index = date.getWeekOfYear();
        return number == index;
    }

    public boolean isSameMonth(int month, int year) {
        int index = getMonth();
        int nYear = getYear();
        return month == index && nYear == year;
    }

    public int daysBetweenMe(DateUtil dateUtil) {
        return (int) (Math.abs(getUnixTimestamp() - dateUtil.getUnixTimestamp()) / 86400);
    }

    public Date toDate() {
        return this.c.getTime();
    }

    public String toFormatString(DateFormater formater) {
        Date date = toDate();
        String sdate = "Unknown";
        switch (formater) {
            case MMdd:
                sdate = dFMMdd.get().format(date);
                break;
            case MMdd_HHmm:
                sdate = dFMMdd_HHmm.get().format(date);
                break;
            case yyyyMM:
                sdate = dFyyyyMM.get().format(date);
                break;
            case yyyyMMdd:
                sdate = dFyyyyMMdd.get().format(date);
                break;
            case dFyyyyMMdd:
                sdate = dFyyyyMMdd.get().format(date);
                break;
            case yyyyMMdd_HHmm:
                sdate = dFyyyyMMdd_HHmm.get().format(date);
                break;
            case yyyyMMdd_HHmmss:
                sdate = dFyyyyMMdd_HHmmss.get().format(date);
                break;
            case HHmm:
                sdate = dFHHmm.get().format(date);
                break;
            case HHmmss:
                sdate = dFHHmmss.get().format(date);
                break;
            case SyyyyMMdd:
                sdate = dFSyyyyMMdd.get().format(date);
                break;
        }
        return sdate;
    }

    public static Date String2Date(String formater, String dateString) {
        Date date = null;
        try {
            switch (formater) {
                case "yyyy-MM-dd HH:mm:ss":
                    date = yyyyMMdd_HHmmssF.parse(dateString);
                    break;
                case "yyyy-MM-dd HH:mm":
                    date = dFyyyyMMddmmF.parse(dateString);
                    break;
                case "yyyy-MM-dd":
                    date = dFyyyyMMddF.parse(dateString);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
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

    public void setYear(int year) {
        this.c.set(1, year);
    }

    public int getMonth() {
        return this.c.get(2) + 1;
    }

    public void setMonth(int month) {
        this.c.set(2, month - 1);
    }

    public int getDay() {
        return this.c.get(5);
    }

    public int getDaysOfThisMonth() {
        return this.c.get(5);
    }

    public void setDay(int day) {
        this.c.set(5, day);
    }

    public void addDay(int day) {
        this.c.add(5, day);
    }

    public void addMonth(int month) {
        this.c.add(2, month);
    }

    public int getHour() {
        return this.c.get(11);
    }

    public void setHour(int hour) {
        this.c.set(11, hour);
    }

    public int getMinute() {
        return this.c.get(12);
    }

    public void setMinute(int minute) {
        this.c.set(12, minute);
    }

    public int getSecond() {
        return this.c.get(13);
    }

    public void setSecond(int second) {
        this.c.set(13, second);
    }

    public long getTimestamp() {
        return this.c.getTimeInMillis();
    }

    public void setTimestamp(long timestamp) {
        this.c.setTimeInMillis(timestamp);
    }

    public long getUnixTimestamp() {
        return this.c.getTimeInMillis() / 1000;
    }

    public void setUnixTimestamp(long unix_timestamp) {
        this.c.setTimeInMillis(unix_timestamp * 1000);
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
        int day = getDayOfWeek();
        this.c.add(5, this.c.getFirstDayOfWeek() - day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(this.c.getTime());
    }

    public String toString() {
        return getYyyyMMdd_HHmmssDate();
    }

    public static String getTime(long time) {
        long now = System.currentTimeMillis();
        long dTime = (now - time) / 86400000;
        DateUtil dateUtil = new DateUtil(time, false);
        if (dTime > 0) {
            return dateUtil.getYyyyMMddDate();
        }
        return dateUtil.getHHmmDate();
    }

    public static long getSunDayTimeFromWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        int i = cal.get(7) - 1;
        long l = cal.getTime().getTime() - (i * 86400000);
        return l;
    }

    public static Date getDateByWeekMagin(int size) {
        long sunDayTimeFromWeek = getSunDayTimeFromWeek();
        return new Date(sunDayTimeFromWeek + (size * 86400000));
    }

    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / 86400000);
        return days;
    }

    public static long dateStr2Stamp(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            return Long.parseLong(String.valueOf(sdf.parse(date).getTime()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static long dateY_M_D2Stamp(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dFyyyyMMdd1);
            return Long.parseLong(String.valueOf(sdf.parse(date).getTime()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static String getMarginMin(long start, long startTime) {
        return ((start - startTime) / 60) + "";
    }

    public static int getAgeByBirthday(Date birthday) {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthday)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(1);
        int monthNow = cal.get(2) + 1;
        int dayOfMonthNow = cal.get(5);
        cal.setTime(birthday);
        int yearBirth = cal.get(1);
        int monthBirth = cal.get(2) + 1;
        int dayOfMonthBirth = cal.get(5);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth && (monthNow != monthBirth || dayOfMonthNow < dayOfMonthBirth)) {
            age--;
        }
        return age;
    }
}
