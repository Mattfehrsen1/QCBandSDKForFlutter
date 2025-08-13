package com.haibin.calendarview;

import android.content.Context;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes2.dex */
public final class CalendarUtil {
    private static final long ONE_DAY = 86400000;

    static boolean isMonthInRange(int i, int i2, int i3, int i4, int i5, int i6) {
        return i >= i3 && i <= i5 && (i != i3 || i2 >= i4) && (i != i5 || i2 <= i6);
    }

    static int getDate(String str, Date date) {
        return Integer.parseInt(new SimpleDateFormat(str).format(date));
    }

    public static boolean isWeekend(Calendar calendar) {
        int weekFormCalendar = getWeekFormCalendar(calendar);
        return weekFormCalendar == 0 || weekFormCalendar == 6;
    }

    public static int getMonthDaysCount(int i, int i2) {
        int i3 = (i2 == 1 || i2 == 3 || i2 == 5 || i2 == 7 || i2 == 8 || i2 == 10 || i2 == 12) ? 31 : 0;
        if (i2 == 4 || i2 == 6 || i2 == 9 || i2 == 11) {
            i3 = 30;
        }
        return i2 == 2 ? isLeapYear(i) ? 29 : 28 : i3;
    }

    public static boolean isLeapYear(int i) {
        return (i % 4 == 0 && i % 100 != 0) || i % 400 == 0;
    }

    public static int getMonthViewLineCount(int i, int i2, int i3, int i4) {
        if (i4 == 0) {
            return 6;
        }
        return ((getMonthViewStartDiff(i, i2, i3) + getMonthDaysCount(i, i2)) + getMonthEndDiff(i, i2, i3)) / 7;
    }

    public static int getMonthViewHeight(int i, int i2, int i3, int i4) {
        java.util.Calendar.getInstance().set(i, i2 - 1, 1, 12, 0, 0);
        int monthViewStartDiff = getMonthViewStartDiff(i, i2, i4);
        int monthDaysCount = getMonthDaysCount(i, i2);
        return (((monthViewStartDiff + monthDaysCount) + getMonthEndDiff(i, i2, monthDaysCount, i4)) / 7) * i3;
    }

    public static int getMonthViewHeight(int i, int i2, int i3, int i4, int i5) {
        return i5 == 0 ? i3 * 6 : getMonthViewHeight(i, i2, i3, i4);
    }

    public static int getWeekFromDayInMonth(Calendar calendar, int i) {
        java.util.Calendar.getInstance().set(calendar.getYear(), calendar.getMonth() - 1, 1, 12, 0, 0);
        return (((calendar.getDay() + getMonthViewStartDiff(calendar, i)) - 1) / 7) + 1;
    }

    public static Calendar getPreCalendar(Calendar calendar) {
        java.util.Calendar calendar2 = java.util.Calendar.getInstance();
        calendar2.set(calendar.getYear(), calendar.getMonth() - 1, calendar.getDay(), 12, 0, 0);
        calendar2.setTimeInMillis(calendar2.getTimeInMillis() - ONE_DAY);
        Calendar calendar3 = new Calendar();
        calendar3.setYear(calendar2.get(1));
        calendar3.setMonth(calendar2.get(2) + 1);
        calendar3.setDay(calendar2.get(5));
        return calendar3;
    }

    public static Calendar getNextCalendar(Calendar calendar) {
        java.util.Calendar calendar2 = java.util.Calendar.getInstance();
        calendar2.set(calendar.getYear(), calendar.getMonth() - 1, calendar.getDay(), 12, 0, 0);
        calendar2.setTimeInMillis(calendar2.getTimeInMillis() + ONE_DAY);
        Calendar calendar3 = new Calendar();
        calendar3.setYear(calendar2.get(1));
        calendar3.setMonth(calendar2.get(2) + 1);
        calendar3.setDay(calendar2.get(5));
        return calendar3;
    }

    static int getMonthViewStartDiff(Calendar calendar, int i) {
        java.util.Calendar calendar2 = java.util.Calendar.getInstance();
        calendar2.set(calendar.getYear(), calendar.getMonth() - 1, 1, 12, 0, 0);
        int i2 = calendar2.get(7);
        if (i == 1) {
            return i2 - 1;
        }
        if (i == 2) {
            if (i2 == 1) {
                return 6;
            }
            return i2 - i;
        }
        if (i2 == 7) {
            return 0;
        }
        return i2;
    }

    static int getMonthViewStartDiff(int i, int i2, int i3) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(i, i2 - 1, 1, 12, 0, 0);
        int i4 = calendar.get(7);
        if (i3 == 1) {
            return i4 - 1;
        }
        if (i3 == 2) {
            if (i4 == 1) {
                return 6;
            }
            return i4 - i3;
        }
        if (i4 == 7) {
            return 0;
        }
        return i4;
    }

    static int getMonthEndDiff(int i, int i2, int i3) {
        return getMonthEndDiff(i, i2, getMonthDaysCount(i, i2), i3);
    }

    private static int getMonthEndDiff(int i, int i2, int i3, int i4) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(i, i2 - 1, i3);
        int i5 = calendar.get(7);
        if (i4 == 1) {
            return 7 - i5;
        }
        if (i4 == 2) {
            if (i5 == 1) {
                return 0;
            }
            return (7 - i5) + 1;
        }
        if (i5 == 7) {
            return 6;
        }
        return (7 - i5) - 1;
    }

    static int getWeekFormCalendar(Calendar calendar) {
        java.util.Calendar.getInstance().set(calendar.getYear(), calendar.getMonth() - 1, calendar.getDay());
        return r0.get(7) - 1;
    }

    static int getWeekViewIndexFromCalendar(Calendar calendar, int i) {
        return getWeekViewStartDiff(calendar.getYear(), calendar.getMonth(), calendar.getDay(), i);
    }

    static boolean isCalendarInRange(Calendar calendar, int i, int i2, int i3, int i4, int i5, int i6) {
        java.util.Calendar calendar2 = java.util.Calendar.getInstance();
        calendar2.set(i, i2 - 1, i3);
        long timeInMillis = calendar2.getTimeInMillis();
        calendar2.set(i4, i5 - 1, i6);
        long timeInMillis2 = calendar2.getTimeInMillis();
        calendar2.set(calendar.getYear(), calendar.getMonth() - 1, calendar.getDay());
        long timeInMillis3 = calendar2.getTimeInMillis();
        return timeInMillis3 >= timeInMillis && timeInMillis3 <= timeInMillis2;
    }

    public static int getWeekCountBetweenBothCalendar(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(i, i2 - 1, i3);
        long timeInMillis = calendar.getTimeInMillis();
        int weekViewStartDiff = getWeekViewStartDiff(i, i2, i3, i7);
        calendar.set(i4, i5 - 1, i6);
        return ((weekViewStartDiff + getWeekViewEndDiff(i4, i5, i6, i7)) + (((int) ((calendar.getTimeInMillis() - timeInMillis) / ONE_DAY)) + 1)) / 7;
    }

    public static int getWeekFromCalendarStartWithMinCalendar(Calendar calendar, int i, int i2, int i3, int i4) {
        java.util.Calendar calendar2 = java.util.Calendar.getInstance();
        calendar2.set(i, i2 - 1, i3);
        long timeInMillis = calendar2.getTimeInMillis();
        int weekViewStartDiff = getWeekViewStartDiff(i, i2, i3, i4);
        calendar2.set(calendar.getYear(), calendar.getMonth() - 1, getWeekViewStartDiff(calendar.getYear(), calendar.getMonth(), calendar.getDay(), i4) == 0 ? calendar.getDay() + 1 : calendar.getDay());
        return ((weekViewStartDiff + ((int) ((calendar2.getTimeInMillis() - timeInMillis) / ONE_DAY))) / 7) + 1;
    }

    public static Calendar getFirstCalendarStartWithMinCalendar(int i, int i2, int i3, int i4, int i5) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(i, i2 - 1, i3, 12, 0);
        long timeInMillis = ((i4 - 1) * 7 * ONE_DAY) + calendar.getTimeInMillis();
        calendar.setTimeInMillis(timeInMillis);
        calendar.setTimeInMillis(timeInMillis - (getWeekViewStartDiff(calendar.get(1), calendar.get(2) + 1, calendar.get(5), i5) * ONE_DAY));
        Calendar calendar2 = new Calendar();
        calendar2.setYear(calendar.get(1));
        calendar2.setMonth(calendar.get(2) + 1);
        calendar2.setDay(calendar.get(5));
        return calendar2;
    }

    static boolean isCalendarInRange(Calendar calendar, CalendarViewDelegate calendarViewDelegate) {
        return isCalendarInRange(calendar, calendarViewDelegate.getMinYear(), calendarViewDelegate.getMinYearMonth(), calendarViewDelegate.getMinYearDay(), calendarViewDelegate.getMaxYear(), calendarViewDelegate.getMaxYearMonth(), calendarViewDelegate.getMaxYearDay());
    }

    public static int differ(Calendar calendar, Calendar calendar2) {
        if (calendar == null) {
            return Integer.MIN_VALUE;
        }
        if (calendar2 == null) {
            return Integer.MAX_VALUE;
        }
        java.util.Calendar calendar3 = java.util.Calendar.getInstance();
        calendar3.set(calendar.getYear(), calendar.getMonth() - 1, calendar.getDay(), 12, 0, 0);
        long timeInMillis = calendar3.getTimeInMillis();
        calendar3.set(calendar2.getYear(), calendar2.getMonth() - 1, calendar2.getDay(), 12, 0, 0);
        return (int) ((timeInMillis - calendar3.getTimeInMillis()) / ONE_DAY);
    }

    public static int compareTo(int i, int i2, int i3, int i4, int i5, int i6) {
        Calendar calendar = new Calendar();
        calendar.setYear(i);
        calendar.setMonth(i2);
        calendar.setDay(i3);
        Calendar calendar2 = new Calendar();
        calendar2.setYear(i4);
        calendar2.setMonth(i5);
        calendar2.setDay(i6);
        return calendar.compareTo(calendar2);
    }

    static List<Calendar> initCalendarForMonthView(int i, int i2, Calendar calendar, int i3) {
        int monthDaysCount;
        int i4;
        int i5;
        int i6;
        int i7 = i2 - 1;
        java.util.Calendar.getInstance().set(i, i7, 1);
        int monthViewStartDiff = getMonthViewStartDiff(i, i2, i3);
        int monthDaysCount2 = getMonthDaysCount(i, i2);
        ArrayList arrayList = new ArrayList();
        int i8 = 12;
        if (i2 == 1) {
            i4 = i - 1;
            int i9 = i2 + 1;
            monthDaysCount = monthViewStartDiff == 0 ? 0 : getMonthDaysCount(i4, 12);
            i5 = i9;
            i6 = i;
        } else if (i2 == 12) {
            i6 = i + 1;
            monthDaysCount = monthViewStartDiff == 0 ? 0 : getMonthDaysCount(i, i7);
            i5 = 1;
            i8 = i7;
            i4 = i;
        } else {
            int i10 = i2 + 1;
            i8 = i7;
            monthDaysCount = monthViewStartDiff == 0 ? 0 : getMonthDaysCount(i, i7);
            i4 = i;
            i5 = i10;
            i6 = i4;
        }
        int i11 = 1;
        for (int i12 = 0; i12 < 42; i12++) {
            Calendar calendar2 = new Calendar();
            if (i12 < monthViewStartDiff) {
                calendar2.setYear(i4);
                calendar2.setMonth(i8);
                calendar2.setDay((monthDaysCount - monthViewStartDiff) + i12 + 1);
            } else if (i12 >= monthDaysCount2 + monthViewStartDiff) {
                calendar2.setYear(i6);
                calendar2.setMonth(i5);
                calendar2.setDay(i11);
                i11++;
            } else {
                calendar2.setYear(i);
                calendar2.setMonth(i2);
                calendar2.setCurrentMonth(true);
                calendar2.setDay((i12 - monthViewStartDiff) + 1);
            }
            if (calendar2.equals(calendar)) {
                calendar2.setCurrentDay(true);
            }
            LunarCalendar.setupLunarCalendar(calendar2);
            arrayList.add(calendar2);
        }
        return arrayList;
    }

    static List<Calendar> getWeekCalendars(Calendar calendar, CalendarViewDelegate calendarViewDelegate) {
        long timeInMillis = calendar.getTimeInMillis();
        java.util.Calendar calendar2 = java.util.Calendar.getInstance();
        calendar2.set(calendar.getYear(), calendar.getMonth() - 1, calendar.getDay(), 12, 0);
        int weekStart = calendar2.get(7);
        if (calendarViewDelegate.getWeekStart() == 1) {
            weekStart--;
        } else if (calendarViewDelegate.getWeekStart() == 2) {
            weekStart = weekStart == 1 ? 6 : weekStart - calendarViewDelegate.getWeekStart();
        } else if (weekStart == 7) {
            weekStart = 0;
        }
        long j = timeInMillis - (weekStart * ONE_DAY);
        java.util.Calendar calendar3 = java.util.Calendar.getInstance();
        calendar3.setTimeInMillis(j);
        Calendar calendar4 = new Calendar();
        calendar4.setYear(calendar3.get(1));
        calendar4.setMonth(calendar3.get(2) + 1);
        calendar4.setDay(calendar3.get(5));
        return initCalendarForWeekView(calendar4, calendarViewDelegate, calendarViewDelegate.getWeekStart());
    }

    static List<Calendar> initCalendarForWeekView(Calendar calendar, CalendarViewDelegate calendarViewDelegate, int i) {
        java.util.Calendar calendar2 = java.util.Calendar.getInstance();
        calendar2.set(calendar.getYear(), calendar.getMonth() - 1, calendar.getDay(), 12, 0);
        long timeInMillis = calendar2.getTimeInMillis();
        ArrayList arrayList = new ArrayList();
        calendar2.setTimeInMillis(timeInMillis);
        Calendar calendar3 = new Calendar();
        calendar3.setYear(calendar.getYear());
        calendar3.setMonth(calendar.getMonth());
        calendar3.setDay(calendar.getDay());
        if (calendar3.equals(calendarViewDelegate.getCurrentDay())) {
            calendar3.setCurrentDay(true);
        }
        LunarCalendar.setupLunarCalendar(calendar3);
        calendar3.setCurrentMonth(true);
        arrayList.add(calendar3);
        for (int i2 = 1; i2 <= 6; i2++) {
            calendar2.setTimeInMillis((i2 * ONE_DAY) + timeInMillis);
            Calendar calendar4 = new Calendar();
            calendar4.setYear(calendar2.get(1));
            calendar4.setMonth(calendar2.get(2) + 1);
            calendar4.setDay(calendar2.get(5));
            if (calendar4.equals(calendarViewDelegate.getCurrentDay())) {
                calendar4.setCurrentDay(true);
            }
            LunarCalendar.setupLunarCalendar(calendar4);
            calendar4.setCurrentMonth(true);
            arrayList.add(calendar4);
        }
        return arrayList;
    }

    private static int getWeekViewStartDiff(int i, int i2, int i3, int i4) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(i, i2 - 1, i3, 12, 0);
        int i5 = calendar.get(7);
        if (i4 == 1) {
            return i5 - 1;
        }
        if (i4 == 2) {
            if (i5 == 1) {
                return 6;
            }
            return i5 - i4;
        }
        if (i5 == 7) {
            return 0;
        }
        return i5;
    }

    public static int getWeekViewEndDiff(int i, int i2, int i3, int i4) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(i, i2 - 1, i3, 12, 0);
        int i5 = calendar.get(7);
        if (i4 == 1) {
            return 7 - i5;
        }
        if (i4 == 2) {
            if (i5 == 1) {
                return 0;
            }
            return (7 - i5) + 1;
        }
        if (i5 == 7) {
            return 6;
        }
        return (7 - i5) - 1;
    }

    static Calendar getFirstCalendarFromMonthViewPager(int i, CalendarViewDelegate calendarViewDelegate) {
        Calendar calendar = new Calendar();
        calendar.setYear((((calendarViewDelegate.getMinYearMonth() + i) - 1) / 12) + calendarViewDelegate.getMinYear());
        calendar.setMonth((((i + calendarViewDelegate.getMinYearMonth()) - 1) % 12) + 1);
        if (calendarViewDelegate.getDefaultCalendarSelectDay() != 0) {
            int monthDaysCount = getMonthDaysCount(calendar.getYear(), calendar.getMonth());
            Calendar calendar2 = calendarViewDelegate.mIndexCalendar;
            if (calendar2 == null || calendar2.getDay() == 0) {
                monthDaysCount = 1;
            } else if (monthDaysCount >= calendar2.getDay()) {
                monthDaysCount = calendar2.getDay();
            }
            calendar.setDay(monthDaysCount);
        } else {
            calendar.setDay(1);
        }
        if (!isCalendarInRange(calendar, calendarViewDelegate)) {
            if (isMinRangeEdge(calendar, calendarViewDelegate)) {
                calendar = calendarViewDelegate.getMinRangeCalendar();
            } else {
                calendar = calendarViewDelegate.getMaxRangeCalendar();
            }
        }
        calendar.setCurrentMonth(calendar.getYear() == calendarViewDelegate.getCurrentDay().getYear() && calendar.getMonth() == calendarViewDelegate.getCurrentDay().getMonth());
        calendar.setCurrentDay(calendar.equals(calendarViewDelegate.getCurrentDay()));
        LunarCalendar.setupLunarCalendar(calendar);
        return calendar;
    }

    static Calendar getRangeEdgeCalendar(Calendar calendar, CalendarViewDelegate calendarViewDelegate) {
        if (isCalendarInRange(calendarViewDelegate.getCurrentDay(), calendarViewDelegate) && calendarViewDelegate.getDefaultCalendarSelectDay() != 2) {
            return calendarViewDelegate.createCurrentDate();
        }
        if (isCalendarInRange(calendar, calendarViewDelegate)) {
            return calendar;
        }
        if (calendarViewDelegate.getMinRangeCalendar().isSameMonth(calendar)) {
            return calendarViewDelegate.getMinRangeCalendar();
        }
        return calendarViewDelegate.getMaxRangeCalendar();
    }

    private static boolean isMinRangeEdge(Calendar calendar, CalendarViewDelegate calendarViewDelegate) {
        java.util.Calendar calendar2 = java.util.Calendar.getInstance();
        calendar2.set(calendarViewDelegate.getMinYear(), calendarViewDelegate.getMinYearMonth() - 1, calendarViewDelegate.getMinYearDay(), 12, 0);
        long timeInMillis = calendar2.getTimeInMillis();
        calendar2.set(calendar.getYear(), calendar.getMonth() - 1, calendar.getDay(), 12, 0);
        return calendar2.getTimeInMillis() < timeInMillis;
    }

    static int dipToPx(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
