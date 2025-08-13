package com.haibin.calendarview;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/* loaded from: classes2.dex */
public abstract class MultiMonthView extends BaseMonthView {
    protected abstract void onDrawScheme(Canvas canvas, Calendar calendar, int i, int i2, boolean z);

    protected abstract boolean onDrawSelected(Canvas canvas, Calendar calendar, int i, int i2, boolean z, boolean z2, boolean z3);

    protected abstract void onDrawText(Canvas canvas, Calendar calendar, int i, int i2, boolean z, boolean z2);

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        return false;
    }

    public MultiMonthView(Context context) {
        super(context);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mLineCount == 0) {
            return;
        }
        this.mItemWidth = ((getWidth() - this.mDelegate.getCalendarPaddingLeft()) - this.mDelegate.getCalendarPaddingRight()) / 7;
        onPreviewHook();
        int i = this.mLineCount * 7;
        int i2 = 0;
        int i3 = 0;
        while (i3 < this.mLineCount) {
            int i4 = i2;
            for (int i5 = 0; i5 < 7; i5++) {
                Calendar calendar = this.mItems.get(i4);
                if (this.mDelegate.getMonthViewShowMode() == 1) {
                    if (i4 > this.mItems.size() - this.mNextDiff) {
                        return;
                    }
                    if (!calendar.isCurrentMonth()) {
                    }
                    i4++;
                } else if (this.mDelegate.getMonthViewShowMode() == 2 && i4 >= i) {
                    return;
                }
                draw(canvas, calendar, i4, i3, i5);
                i4++;
            }
            i3++;
            i2 = i4;
        }
    }

    private void draw(Canvas canvas, Calendar calendar, int i, int i2, int i3) {
        int calendarPaddingLeft = (i3 * this.mItemWidth) + this.mDelegate.getCalendarPaddingLeft();
        int i4 = i2 * this.mItemHeight;
        onLoopStart(calendarPaddingLeft, i4);
        boolean zIsCalendarSelected = isCalendarSelected(calendar);
        boolean zHasScheme = calendar.hasScheme();
        boolean zIsSelectPreCalendar = isSelectPreCalendar(calendar, i);
        boolean zIsSelectNextCalendar = isSelectNextCalendar(calendar, i);
        if (zHasScheme) {
            if ((zIsCalendarSelected ? onDrawSelected(canvas, calendar, calendarPaddingLeft, i4, true, zIsSelectPreCalendar, zIsSelectNextCalendar) : false) || !zIsCalendarSelected) {
                this.mSchemePaint.setColor(calendar.getSchemeColor() != 0 ? calendar.getSchemeColor() : this.mDelegate.getSchemeThemeColor());
                onDrawScheme(canvas, calendar, calendarPaddingLeft, i4, true);
            }
        } else if (zIsCalendarSelected) {
            onDrawSelected(canvas, calendar, calendarPaddingLeft, i4, false, zIsSelectPreCalendar, zIsSelectNextCalendar);
        }
        onDrawText(canvas, calendar, calendarPaddingLeft, i4, zHasScheme, zIsCalendarSelected);
    }

    protected boolean isCalendarSelected(Calendar calendar) {
        return !onCalendarIntercept(calendar) && this.mDelegate.mSelectedCalendars.containsKey(calendar.toString());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Calendar index;
        if (this.isClick && (index = getIndex()) != null) {
            if (this.mDelegate.getMonthViewShowMode() != 1 || index.isCurrentMonth()) {
                if (onCalendarIntercept(index)) {
                    this.mDelegate.mCalendarInterceptListener.onCalendarInterceptClick(index, true);
                    return;
                }
                if (!isInRange(index)) {
                    if (this.mDelegate.mCalendarMultiSelectListener != null) {
                        this.mDelegate.mCalendarMultiSelectListener.onCalendarMultiSelectOutOfRange(index);
                        return;
                    }
                    return;
                }
                String string = index.toString();
                if (this.mDelegate.mSelectedCalendars.containsKey(string)) {
                    this.mDelegate.mSelectedCalendars.remove(string);
                } else {
                    if (this.mDelegate.mSelectedCalendars.size() >= this.mDelegate.getMaxMultiSelectSize()) {
                        if (this.mDelegate.mCalendarMultiSelectListener != null) {
                            this.mDelegate.mCalendarMultiSelectListener.onMultiSelectOutOfSize(index, this.mDelegate.getMaxMultiSelectSize());
                            return;
                        }
                        return;
                    }
                    this.mDelegate.mSelectedCalendars.put(string, index);
                }
                this.mCurrentItem = this.mItems.indexOf(index);
                if (!index.isCurrentMonth() && this.mMonthViewPager != null) {
                    int currentItem = this.mMonthViewPager.getCurrentItem();
                    this.mMonthViewPager.setCurrentItem(this.mCurrentItem < 7 ? currentItem - 1 : currentItem + 1);
                }
                if (this.mDelegate.mInnerListener != null) {
                    this.mDelegate.mInnerListener.onMonthDateSelected(index, true);
                }
                if (this.mParentLayout != null) {
                    if (index.isCurrentMonth()) {
                        this.mParentLayout.updateSelectPosition(this.mItems.indexOf(index));
                    } else {
                        this.mParentLayout.updateSelectWeek(CalendarUtil.getWeekFromDayInMonth(index, this.mDelegate.getWeekStart()));
                    }
                }
                if (this.mDelegate.mCalendarMultiSelectListener != null) {
                    this.mDelegate.mCalendarMultiSelectListener.onCalendarMultiSelect(index, this.mDelegate.mSelectedCalendars.size(), this.mDelegate.getMaxMultiSelectSize());
                }
            }
        }
    }

    protected final boolean isSelectPreCalendar(Calendar calendar, int i) {
        Calendar preCalendar;
        if (i == 0) {
            preCalendar = CalendarUtil.getPreCalendar(calendar);
            this.mDelegate.updateCalendarScheme(preCalendar);
        } else {
            preCalendar = this.mItems.get(i - 1);
        }
        return isCalendarSelected(preCalendar);
    }

    protected final boolean isSelectNextCalendar(Calendar calendar, int i) {
        Calendar nextCalendar;
        if (i == this.mItems.size() - 1) {
            nextCalendar = CalendarUtil.getNextCalendar(calendar);
            this.mDelegate.updateCalendarScheme(nextCalendar);
        } else {
            nextCalendar = this.mItems.get(i + 1);
        }
        return isCalendarSelected(nextCalendar);
    }
}
