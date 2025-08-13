package com.haibin.calendarview;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/* loaded from: classes2.dex */
public class WeekBar extends LinearLayout {
    private CalendarViewDelegate mDelegate;

    protected void onDateSelected(Calendar calendar, int i, boolean z) {
    }

    public WeekBar(Context context) {
        super(context);
        if ("com.haibin.calendarview.WeekBar".equals(getClass().getName())) {
            LayoutInflater.from(context).inflate(R.layout.cv_week_bar, (ViewGroup) this, true);
        }
    }

    void setup(CalendarViewDelegate calendarViewDelegate) {
        this.mDelegate = calendarViewDelegate;
        if ("com.haibin.calendarview.WeekBar".equalsIgnoreCase(getClass().getName())) {
            setTextSize(this.mDelegate.getWeekTextSize());
            setTextColor(calendarViewDelegate.getWeekTextColor());
            setBackgroundColor(calendarViewDelegate.getWeekBackground());
            setPadding(calendarViewDelegate.getCalendarPaddingLeft(), 0, calendarViewDelegate.getCalendarPaddingRight(), 0);
        }
    }

    protected void setTextColor(int i) {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            ((TextView) getChildAt(i2)).setTextColor(i);
        }
    }

    protected void setTextSize(int i) {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            ((TextView) getChildAt(i2)).setTextSize(0, i);
        }
    }

    protected void onWeekStartChange(int i) {
        if ("com.haibin.calendarview.WeekBar".equalsIgnoreCase(getClass().getName())) {
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                ((TextView) getChildAt(i2)).setText(getWeekString(i2, i));
            }
        }
    }

    protected int getViewIndexByCalendar(Calendar calendar, int i) {
        int week = calendar.getWeek() + 1;
        if (i == 1) {
            return week - 1;
        }
        if (i == 2) {
            if (week == 1) {
                return 6;
            }
            return week - 2;
        }
        if (week == 7) {
            return 0;
        }
        return week;
    }

    private String getWeekString(int i, int i2) throws Resources.NotFoundException {
        String[] stringArray = getContext().getResources().getStringArray(R.array.week_string_array);
        if (i2 == 1) {
            return stringArray[i];
        }
        if (i2 == 2) {
            return stringArray[i == 6 ? 0 : i + 1];
        }
        return stringArray[i != 0 ? i - 1 : 6];
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int iMakeMeasureSpec;
        CalendarViewDelegate calendarViewDelegate = this.mDelegate;
        if (calendarViewDelegate != null) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(calendarViewDelegate.getWeekBarHeight(), 1073741824);
        } else {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(CalendarUtil.dipToPx(getContext(), 40.0f), 1073741824);
        }
        super.onMeasure(i, iMakeMeasureSpec);
    }
}
