package com.haibin.calendarview;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/* loaded from: classes2.dex */
public abstract class RangeWeekView extends BaseWeekView {
    protected abstract void onDrawScheme(Canvas canvas, Calendar calendar, int i, boolean z);

    protected abstract boolean onDrawSelected(Canvas canvas, Calendar calendar, int i, boolean z, boolean z2, boolean z3);

    protected abstract void onDrawText(Canvas canvas, Calendar calendar, int i, boolean z, boolean z2);

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        return false;
    }

    public RangeWeekView(Context context) {
        super(context);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mItems.size() == 0) {
            return;
        }
        this.mItemWidth = ((getWidth() - this.mDelegate.getCalendarPaddingLeft()) - this.mDelegate.getCalendarPaddingRight()) / 7;
        onPreviewHook();
        for (int i = 0; i < 7; i++) {
            int calendarPaddingLeft = (this.mItemWidth * i) + this.mDelegate.getCalendarPaddingLeft();
            onLoopStart(calendarPaddingLeft);
            Calendar calendar = this.mItems.get(i);
            boolean zIsCalendarSelected = isCalendarSelected(calendar);
            boolean zIsSelectPreCalendar = isSelectPreCalendar(calendar, i);
            boolean zIsSelectNextCalendar = isSelectNextCalendar(calendar, i);
            boolean zHasScheme = calendar.hasScheme();
            if (zHasScheme) {
                if ((zIsCalendarSelected ? onDrawSelected(canvas, calendar, calendarPaddingLeft, true, zIsSelectPreCalendar, zIsSelectNextCalendar) : false) || !zIsCalendarSelected) {
                    this.mSchemePaint.setColor(calendar.getSchemeColor() != 0 ? calendar.getSchemeColor() : this.mDelegate.getSchemeThemeColor());
                    onDrawScheme(canvas, calendar, calendarPaddingLeft, zIsCalendarSelected);
                }
            } else if (zIsCalendarSelected) {
                onDrawSelected(canvas, calendar, calendarPaddingLeft, false, zIsSelectPreCalendar, zIsSelectNextCalendar);
            }
            onDrawText(canvas, calendar, calendarPaddingLeft, zHasScheme, zIsCalendarSelected);
        }
    }

    protected boolean isCalendarSelected(Calendar calendar) {
        if (this.mDelegate.mSelectedStartRangeCalendar == null || onCalendarIntercept(calendar)) {
            return false;
        }
        return this.mDelegate.mSelectedEndRangeCalendar == null ? calendar.compareTo(this.mDelegate.mSelectedStartRangeCalendar) == 0 : calendar.compareTo(this.mDelegate.mSelectedStartRangeCalendar) >= 0 && calendar.compareTo(this.mDelegate.mSelectedEndRangeCalendar) <= 0;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Calendar index;
        if (this.isClick && (index = getIndex()) != null) {
            if (onCalendarIntercept(index)) {
                this.mDelegate.mCalendarInterceptListener.onCalendarInterceptClick(index, true);
                return;
            }
            if (!isInRange(index)) {
                if (this.mDelegate.mCalendarRangeSelectListener != null) {
                    this.mDelegate.mCalendarRangeSelectListener.onCalendarSelectOutOfRange(index);
                    return;
                }
                return;
            }
            if (this.mDelegate.mSelectedStartRangeCalendar != null && this.mDelegate.mSelectedEndRangeCalendar == null) {
                int iDiffer = CalendarUtil.differ(index, this.mDelegate.mSelectedStartRangeCalendar);
                if (iDiffer >= 0 && this.mDelegate.getMinSelectRange() != -1 && this.mDelegate.getMinSelectRange() > iDiffer + 1) {
                    if (this.mDelegate.mCalendarRangeSelectListener != null) {
                        this.mDelegate.mCalendarRangeSelectListener.onSelectOutOfRange(index, true);
                        return;
                    }
                    return;
                } else if (this.mDelegate.getMaxSelectRange() != -1 && this.mDelegate.getMaxSelectRange() < CalendarUtil.differ(index, this.mDelegate.mSelectedStartRangeCalendar) + 1) {
                    if (this.mDelegate.mCalendarRangeSelectListener != null) {
                        this.mDelegate.mCalendarRangeSelectListener.onSelectOutOfRange(index, false);
                        return;
                    }
                    return;
                }
            }
            if (this.mDelegate.mSelectedStartRangeCalendar == null || this.mDelegate.mSelectedEndRangeCalendar != null) {
                this.mDelegate.mSelectedStartRangeCalendar = index;
                this.mDelegate.mSelectedEndRangeCalendar = null;
            } else {
                int iCompareTo = index.compareTo(this.mDelegate.mSelectedStartRangeCalendar);
                if ((this.mDelegate.getMinSelectRange() == -1 && iCompareTo <= 0) || iCompareTo < 0) {
                    this.mDelegate.mSelectedStartRangeCalendar = index;
                    this.mDelegate.mSelectedEndRangeCalendar = null;
                } else if (iCompareTo != 0 || this.mDelegate.getMinSelectRange() == 1) {
                    this.mDelegate.mSelectedEndRangeCalendar = index;
                } else {
                    this.mDelegate.mSelectedEndRangeCalendar = index;
                }
            }
            this.mCurrentItem = this.mItems.indexOf(index);
            if (this.mDelegate.mInnerListener != null) {
                this.mDelegate.mInnerListener.onWeekDateSelected(index, true);
            }
            if (this.mParentLayout != null) {
                this.mParentLayout.updateSelectWeek(CalendarUtil.getWeekFromDayInMonth(index, this.mDelegate.getWeekStart()));
            }
            if (this.mDelegate.mCalendarRangeSelectListener != null) {
                this.mDelegate.mCalendarRangeSelectListener.onCalendarRangeSelect(index, this.mDelegate.mSelectedEndRangeCalendar != null);
            }
            invalidate();
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
        return this.mDelegate.mSelectedStartRangeCalendar != null && isCalendarSelected(preCalendar);
    }

    protected final boolean isSelectNextCalendar(Calendar calendar, int i) {
        Calendar nextCalendar;
        if (i == this.mItems.size() - 1) {
            nextCalendar = CalendarUtil.getNextCalendar(calendar);
            this.mDelegate.updateCalendarScheme(nextCalendar);
        } else {
            nextCalendar = this.mItems.get(i + 1);
        }
        return this.mDelegate.mSelectedStartRangeCalendar != null && isCalendarSelected(nextCalendar);
    }
}
