package com.stfalcon.chatkit.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes4.dex */
public final class DateFormatter {

    public interface Formatter {
        String format(Date date);
    }

    private DateFormatter() {
        throw new AssertionError();
    }

    public static String format(Date date, Template template) {
        return format(date, template.get());
    }

    public static String format(Date date, String format) {
        return date == null ? "" : new SimpleDateFormat(format, Locale.getDefault()).format(date);
    }

    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("Dates must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return isSameDay(calendar, calendar2);
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("Dates must not be null");
        }
        return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
    }

    public static boolean isSameYear(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("Dates must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return isSameYear(calendar, calendar2);
    }

    public static boolean isSameYear(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("Dates must not be null");
        }
        return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1);
    }

    public static boolean isToday(Calendar calendar) {
        return isSameDay(calendar, Calendar.getInstance());
    }

    public static boolean isToday(Date date) {
        return isSameDay(date, Calendar.getInstance().getTime());
    }

    public static boolean isYesterday(Calendar calendar) {
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(5, -1);
        return isSameDay(calendar, calendar2);
    }

    public static boolean isYesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -1);
        return isSameDay(date, calendar.getTime());
    }

    public static boolean isCurrentYear(Date date) {
        return isSameYear(date, Calendar.getInstance().getTime());
    }

    public static boolean isCurrentYear(Calendar calendar) {
        return isSameYear(calendar, Calendar.getInstance());
    }

    public enum Template {
        STRING_DAY_MONTH_YEAR("yyyy-MM-dd"),
        STRING_DAY_MONTH("d MMMM"),
        TIME("HH:mm");

        private String template;

        Template(String template) {
            this.template = template;
        }

        public String get() {
            return this.template;
        }
    }
}
