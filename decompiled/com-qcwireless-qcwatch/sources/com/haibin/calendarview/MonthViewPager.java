package com.haibin.calendarview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import java.util.List;

/* loaded from: classes2.dex */
public final class MonthViewPager extends ViewPager {
    private boolean isUpdateMonthView;
    private boolean isUsingScrollToCalendar;
    private int mCurrentViewHeight;
    private CalendarViewDelegate mDelegate;
    private int mMonthCount;
    private int mNextViewHeight;
    CalendarLayout mParentLayout;
    private int mPreViewHeight;
    WeekBar mWeekBar;
    WeekViewPager mWeekPager;

    public MonthViewPager(Context context) {
        this(context, null);
    }

    public MonthViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isUsingScrollToCalendar = false;
    }

    void setup(CalendarViewDelegate calendarViewDelegate) {
        this.mDelegate = calendarViewDelegate;
        updateMonthViewHeight(calendarViewDelegate.getCurrentDay().getYear(), this.mDelegate.getCurrentDay().getMonth());
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = this.mCurrentViewHeight;
        setLayoutParams(layoutParams);
        init();
    }

    private void init() {
        this.mMonthCount = (((this.mDelegate.getMaxYear() - this.mDelegate.getMinYear()) * 12) - this.mDelegate.getMinYearMonth()) + 1 + this.mDelegate.getMaxYearMonth();
        setAdapter(new MonthViewPagerAdapter());
        addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.haibin.calendarview.MonthViewPager.1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
                float f2;
                int i3;
                if (MonthViewPager.this.mDelegate.getMonthViewShowMode() == 0) {
                    return;
                }
                if (i < MonthViewPager.this.getCurrentItem()) {
                    f2 = MonthViewPager.this.mPreViewHeight * (1.0f - f);
                    i3 = MonthViewPager.this.mCurrentViewHeight;
                } else {
                    f2 = MonthViewPager.this.mCurrentViewHeight * (1.0f - f);
                    i3 = MonthViewPager.this.mNextViewHeight;
                }
                int i4 = (int) (f2 + (i3 * f));
                ViewGroup.LayoutParams layoutParams = MonthViewPager.this.getLayoutParams();
                layoutParams.height = i4;
                MonthViewPager.this.setLayoutParams(layoutParams);
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                Calendar firstCalendarFromMonthViewPager = CalendarUtil.getFirstCalendarFromMonthViewPager(i, MonthViewPager.this.mDelegate);
                if (MonthViewPager.this.getVisibility() == 0) {
                    if (!MonthViewPager.this.mDelegate.isShowYearSelectedLayout && MonthViewPager.this.mDelegate.mIndexCalendar != null && firstCalendarFromMonthViewPager.getYear() != MonthViewPager.this.mDelegate.mIndexCalendar.getYear() && MonthViewPager.this.mDelegate.mYearChangeListener != null) {
                        MonthViewPager.this.mDelegate.mYearChangeListener.onYearChange(firstCalendarFromMonthViewPager.getYear());
                    }
                    MonthViewPager.this.mDelegate.mIndexCalendar = firstCalendarFromMonthViewPager;
                }
                if (MonthViewPager.this.mDelegate.mMonthChangeListener != null) {
                    MonthViewPager.this.mDelegate.mMonthChangeListener.onMonthChange(firstCalendarFromMonthViewPager.getYear(), firstCalendarFromMonthViewPager.getMonth());
                }
                if (MonthViewPager.this.mWeekPager.getVisibility() == 0) {
                    MonthViewPager.this.updateMonthViewHeight(firstCalendarFromMonthViewPager.getYear(), firstCalendarFromMonthViewPager.getMonth());
                    return;
                }
                if (MonthViewPager.this.mDelegate.getSelectMode() != 0) {
                    if (MonthViewPager.this.mDelegate.mSelectedStartRangeCalendar == null || !MonthViewPager.this.mDelegate.mSelectedStartRangeCalendar.isSameMonth(MonthViewPager.this.mDelegate.mIndexCalendar)) {
                        if (firstCalendarFromMonthViewPager.isSameMonth(MonthViewPager.this.mDelegate.mSelectedCalendar)) {
                            MonthViewPager.this.mDelegate.mIndexCalendar = MonthViewPager.this.mDelegate.mSelectedCalendar;
                        }
                    } else {
                        MonthViewPager.this.mDelegate.mIndexCalendar = MonthViewPager.this.mDelegate.mSelectedStartRangeCalendar;
                    }
                } else {
                    if (!firstCalendarFromMonthViewPager.isCurrentMonth()) {
                        MonthViewPager.this.mDelegate.mSelectedCalendar = firstCalendarFromMonthViewPager;
                    } else {
                        MonthViewPager.this.mDelegate.mSelectedCalendar = CalendarUtil.getRangeEdgeCalendar(firstCalendarFromMonthViewPager, MonthViewPager.this.mDelegate);
                    }
                    MonthViewPager.this.mDelegate.mIndexCalendar = MonthViewPager.this.mDelegate.mSelectedCalendar;
                }
                MonthViewPager.this.mDelegate.updateSelectCalendarScheme();
                if (!MonthViewPager.this.isUsingScrollToCalendar && MonthViewPager.this.mDelegate.getSelectMode() == 0) {
                    MonthViewPager.this.mWeekBar.onDateSelected(MonthViewPager.this.mDelegate.mSelectedCalendar, MonthViewPager.this.mDelegate.getWeekStart(), false);
                    if (MonthViewPager.this.mDelegate.mCalendarSelectListener != null) {
                        MonthViewPager.this.mDelegate.mCalendarSelectListener.onCalendarSelect(MonthViewPager.this.mDelegate.mSelectedCalendar, false);
                    }
                }
                BaseMonthView baseMonthView = (BaseMonthView) MonthViewPager.this.findViewWithTag(Integer.valueOf(i));
                if (baseMonthView != null) {
                    int selectedIndex = baseMonthView.getSelectedIndex(MonthViewPager.this.mDelegate.mIndexCalendar);
                    if (MonthViewPager.this.mDelegate.getSelectMode() == 0) {
                        baseMonthView.mCurrentItem = selectedIndex;
                    }
                    if (selectedIndex >= 0 && MonthViewPager.this.mParentLayout != null) {
                        MonthViewPager.this.mParentLayout.updateSelectPosition(selectedIndex);
                    }
                    baseMonthView.invalidate();
                }
                MonthViewPager.this.mWeekPager.updateSelected(MonthViewPager.this.mDelegate.mIndexCalendar, false);
                MonthViewPager.this.updateMonthViewHeight(firstCalendarFromMonthViewPager.getYear(), firstCalendarFromMonthViewPager.getMonth());
                MonthViewPager.this.isUsingScrollToCalendar = false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMonthViewHeight(int i, int i2) {
        if (this.mDelegate.getMonthViewShowMode() == 0) {
            this.mCurrentViewHeight = this.mDelegate.getCalendarItemHeight() * 6;
            getLayoutParams().height = this.mCurrentViewHeight;
            return;
        }
        if (this.mParentLayout != null) {
            if (getVisibility() != 0) {
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.height = CalendarUtil.getMonthViewHeight(i, i2, this.mDelegate.getCalendarItemHeight(), this.mDelegate.getWeekStart(), this.mDelegate.getMonthViewShowMode());
                setLayoutParams(layoutParams);
            }
            this.mParentLayout.updateContentViewTranslateY();
        }
        this.mCurrentViewHeight = CalendarUtil.getMonthViewHeight(i, i2, this.mDelegate.getCalendarItemHeight(), this.mDelegate.getWeekStart(), this.mDelegate.getMonthViewShowMode());
        if (i2 == 1) {
            this.mPreViewHeight = CalendarUtil.getMonthViewHeight(i - 1, 12, this.mDelegate.getCalendarItemHeight(), this.mDelegate.getWeekStart(), this.mDelegate.getMonthViewShowMode());
            this.mNextViewHeight = CalendarUtil.getMonthViewHeight(i, 2, this.mDelegate.getCalendarItemHeight(), this.mDelegate.getWeekStart(), this.mDelegate.getMonthViewShowMode());
            return;
        }
        this.mPreViewHeight = CalendarUtil.getMonthViewHeight(i, i2 - 1, this.mDelegate.getCalendarItemHeight(), this.mDelegate.getWeekStart(), this.mDelegate.getMonthViewShowMode());
        if (i2 == 12) {
            this.mNextViewHeight = CalendarUtil.getMonthViewHeight(i + 1, 1, this.mDelegate.getCalendarItemHeight(), this.mDelegate.getWeekStart(), this.mDelegate.getMonthViewShowMode());
        } else {
            this.mNextViewHeight = CalendarUtil.getMonthViewHeight(i, i2 + 1, this.mDelegate.getCalendarItemHeight(), this.mDelegate.getWeekStart(), this.mDelegate.getMonthViewShowMode());
        }
    }

    void notifyDataSetChanged() {
        this.mMonthCount = (((this.mDelegate.getMaxYear() - this.mDelegate.getMinYear()) * 12) - this.mDelegate.getMinYearMonth()) + 1 + this.mDelegate.getMaxYearMonth();
        notifyAdapterDataSetChanged();
    }

    void updateMonthViewClass() {
        this.isUpdateMonthView = true;
        notifyAdapterDataSetChanged();
        this.isUpdateMonthView = false;
    }

    final void updateRange() {
        this.isUpdateMonthView = true;
        notifyDataSetChanged();
        this.isUpdateMonthView = false;
        if (getVisibility() != 0) {
            return;
        }
        this.isUsingScrollToCalendar = false;
        Calendar calendar = this.mDelegate.mSelectedCalendar;
        int year = (((calendar.getYear() - this.mDelegate.getMinYear()) * 12) + calendar.getMonth()) - this.mDelegate.getMinYearMonth();
        setCurrentItem(year, false);
        BaseMonthView baseMonthView = (BaseMonthView) findViewWithTag(Integer.valueOf(year));
        if (baseMonthView != null) {
            baseMonthView.setSelectedCalendar(this.mDelegate.mIndexCalendar);
            baseMonthView.invalidate();
            CalendarLayout calendarLayout = this.mParentLayout;
            if (calendarLayout != null) {
                calendarLayout.updateSelectPosition(baseMonthView.getSelectedIndex(this.mDelegate.mIndexCalendar));
            }
        }
        if (this.mParentLayout != null) {
            this.mParentLayout.updateSelectWeek(CalendarUtil.getWeekFromDayInMonth(calendar, this.mDelegate.getWeekStart()));
        }
        if (this.mDelegate.mInnerListener != null) {
            this.mDelegate.mInnerListener.onMonthDateSelected(calendar, false);
        }
        if (this.mDelegate.mCalendarSelectListener != null) {
            this.mDelegate.mCalendarSelectListener.onCalendarSelect(calendar, false);
        }
        updateSelected();
    }

    void scrollToCalendar(int i, int i2, int i3, boolean z, boolean z2) {
        this.isUsingScrollToCalendar = true;
        Calendar calendar = new Calendar();
        calendar.setYear(i);
        calendar.setMonth(i2);
        calendar.setDay(i3);
        calendar.setCurrentDay(calendar.equals(this.mDelegate.getCurrentDay()));
        LunarCalendar.setupLunarCalendar(calendar);
        this.mDelegate.mIndexCalendar = calendar;
        this.mDelegate.mSelectedCalendar = calendar;
        this.mDelegate.updateSelectCalendarScheme();
        int year = (((calendar.getYear() - this.mDelegate.getMinYear()) * 12) + calendar.getMonth()) - this.mDelegate.getMinYearMonth();
        if (getCurrentItem() == year) {
            this.isUsingScrollToCalendar = false;
        }
        setCurrentItem(year, z);
        BaseMonthView baseMonthView = (BaseMonthView) findViewWithTag(Integer.valueOf(year));
        if (baseMonthView != null) {
            baseMonthView.setSelectedCalendar(this.mDelegate.mIndexCalendar);
            baseMonthView.invalidate();
            CalendarLayout calendarLayout = this.mParentLayout;
            if (calendarLayout != null) {
                calendarLayout.updateSelectPosition(baseMonthView.getSelectedIndex(this.mDelegate.mIndexCalendar));
            }
        }
        if (this.mParentLayout != null) {
            this.mParentLayout.updateSelectWeek(CalendarUtil.getWeekFromDayInMonth(calendar, this.mDelegate.getWeekStart()));
        }
        if (this.mDelegate.mCalendarSelectListener != null && z2) {
            this.mDelegate.mCalendarSelectListener.onCalendarSelect(calendar, false);
        }
        if (this.mDelegate.mInnerListener != null) {
            this.mDelegate.mInnerListener.onMonthDateSelected(calendar, false);
        }
        updateSelected();
    }

    void scrollToCurrent(boolean z) {
        this.isUsingScrollToCalendar = true;
        int year = (((this.mDelegate.getCurrentDay().getYear() - this.mDelegate.getMinYear()) * 12) + this.mDelegate.getCurrentDay().getMonth()) - this.mDelegate.getMinYearMonth();
        if (getCurrentItem() == year) {
            this.isUsingScrollToCalendar = false;
        }
        setCurrentItem(year, z);
        BaseMonthView baseMonthView = (BaseMonthView) findViewWithTag(Integer.valueOf(year));
        if (baseMonthView != null) {
            baseMonthView.setSelectedCalendar(this.mDelegate.getCurrentDay());
            baseMonthView.invalidate();
            CalendarLayout calendarLayout = this.mParentLayout;
            if (calendarLayout != null) {
                calendarLayout.updateSelectPosition(baseMonthView.getSelectedIndex(this.mDelegate.getCurrentDay()));
            }
        }
        if (this.mDelegate.mCalendarSelectListener == null || getVisibility() != 0) {
            return;
        }
        this.mDelegate.mCalendarSelectListener.onCalendarSelect(this.mDelegate.mSelectedCalendar, false);
    }

    List<Calendar> getCurrentMonthCalendars() {
        BaseMonthView baseMonthView = (BaseMonthView) findViewWithTag(Integer.valueOf(getCurrentItem()));
        if (baseMonthView == null) {
            return null;
        }
        return baseMonthView.mItems;
    }

    void updateDefaultSelect() {
        CalendarLayout calendarLayout;
        BaseMonthView baseMonthView = (BaseMonthView) findViewWithTag(Integer.valueOf(getCurrentItem()));
        if (baseMonthView != null) {
            int selectedIndex = baseMonthView.getSelectedIndex(this.mDelegate.mSelectedCalendar);
            baseMonthView.mCurrentItem = selectedIndex;
            if (selectedIndex >= 0 && (calendarLayout = this.mParentLayout) != null) {
                calendarLayout.updateSelectPosition(selectedIndex);
            }
            baseMonthView.invalidate();
        }
    }

    void updateSelected() {
        for (int i = 0; i < getChildCount(); i++) {
            BaseMonthView baseMonthView = (BaseMonthView) getChildAt(i);
            baseMonthView.setSelectedCalendar(this.mDelegate.mSelectedCalendar);
            baseMonthView.invalidate();
        }
    }

    final void updateStyle() {
        for (int i = 0; i < getChildCount(); i++) {
            BaseMonthView baseMonthView = (BaseMonthView) getChildAt(i);
            baseMonthView.updateStyle();
            baseMonthView.invalidate();
        }
    }

    void updateScheme() {
        for (int i = 0; i < getChildCount(); i++) {
            ((BaseMonthView) getChildAt(i)).update();
        }
    }

    void updateCurrentDate() {
        for (int i = 0; i < getChildCount(); i++) {
            ((BaseMonthView) getChildAt(i)).updateCurrentDate();
        }
    }

    void updateShowMode() {
        for (int i = 0; i < getChildCount(); i++) {
            BaseMonthView baseMonthView = (BaseMonthView) getChildAt(i);
            baseMonthView.updateShowMode();
            baseMonthView.requestLayout();
        }
        if (this.mDelegate.getMonthViewShowMode() == 0) {
            int calendarItemHeight = this.mDelegate.getCalendarItemHeight() * 6;
            this.mCurrentViewHeight = calendarItemHeight;
            this.mNextViewHeight = calendarItemHeight;
            this.mPreViewHeight = calendarItemHeight;
        } else {
            updateMonthViewHeight(this.mDelegate.mSelectedCalendar.getYear(), this.mDelegate.mSelectedCalendar.getMonth());
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = this.mCurrentViewHeight;
        setLayoutParams(layoutParams);
        CalendarLayout calendarLayout = this.mParentLayout;
        if (calendarLayout != null) {
            calendarLayout.updateContentViewTranslateY();
        }
    }

    void updateWeekStart() {
        for (int i = 0; i < getChildCount(); i++) {
            BaseMonthView baseMonthView = (BaseMonthView) getChildAt(i);
            baseMonthView.updateWeekStart();
            baseMonthView.requestLayout();
        }
        updateMonthViewHeight(this.mDelegate.mSelectedCalendar.getYear(), this.mDelegate.mSelectedCalendar.getMonth());
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = this.mCurrentViewHeight;
        setLayoutParams(layoutParams);
        if (this.mParentLayout != null) {
            this.mParentLayout.updateSelectWeek(CalendarUtil.getWeekFromDayInMonth(this.mDelegate.mSelectedCalendar, this.mDelegate.getWeekStart()));
        }
        updateSelected();
    }

    final void updateItemHeight() {
        for (int i = 0; i < getChildCount(); i++) {
            BaseMonthView baseMonthView = (BaseMonthView) getChildAt(i);
            baseMonthView.updateItemHeight();
            baseMonthView.requestLayout();
        }
        int year = this.mDelegate.mIndexCalendar.getYear();
        int month = this.mDelegate.mIndexCalendar.getMonth();
        this.mCurrentViewHeight = CalendarUtil.getMonthViewHeight(year, month, this.mDelegate.getCalendarItemHeight(), this.mDelegate.getWeekStart(), this.mDelegate.getMonthViewShowMode());
        if (month == 1) {
            this.mPreViewHeight = CalendarUtil.getMonthViewHeight(year - 1, 12, this.mDelegate.getCalendarItemHeight(), this.mDelegate.getWeekStart(), this.mDelegate.getMonthViewShowMode());
            this.mNextViewHeight = CalendarUtil.getMonthViewHeight(year, 2, this.mDelegate.getCalendarItemHeight(), this.mDelegate.getWeekStart(), this.mDelegate.getMonthViewShowMode());
        } else {
            this.mPreViewHeight = CalendarUtil.getMonthViewHeight(year, month - 1, this.mDelegate.getCalendarItemHeight(), this.mDelegate.getWeekStart(), this.mDelegate.getMonthViewShowMode());
            if (month == 12) {
                this.mNextViewHeight = CalendarUtil.getMonthViewHeight(year + 1, 1, this.mDelegate.getCalendarItemHeight(), this.mDelegate.getWeekStart(), this.mDelegate.getMonthViewShowMode());
            } else {
                this.mNextViewHeight = CalendarUtil.getMonthViewHeight(year, month + 1, this.mDelegate.getCalendarItemHeight(), this.mDelegate.getWeekStart(), this.mDelegate.getMonthViewShowMode());
            }
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = this.mCurrentViewHeight;
        setLayoutParams(layoutParams);
    }

    final void clearSelectRange() {
        for (int i = 0; i < getChildCount(); i++) {
            ((BaseMonthView) getChildAt(i)).invalidate();
        }
    }

    final void clearSingleSelect() {
        for (int i = 0; i < getChildCount(); i++) {
            BaseMonthView baseMonthView = (BaseMonthView) getChildAt(i);
            baseMonthView.mCurrentItem = -1;
            baseMonthView.invalidate();
        }
    }

    final void clearMultiSelect() {
        for (int i = 0; i < getChildCount(); i++) {
            BaseMonthView baseMonthView = (BaseMonthView) getChildAt(i);
            baseMonthView.mCurrentItem = -1;
            baseMonthView.invalidate();
        }
    }

    private void notifyAdapterDataSetChanged() {
        if (getAdapter() == null) {
            return;
        }
        getAdapter().notifyDataSetChanged();
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mDelegate.isMonthViewScrollable() && super.onTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mDelegate.isMonthViewScrollable() && super.onInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setCurrentItem(int i) {
        setCurrentItem(i, true);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setCurrentItem(int i, boolean z) {
        if (Math.abs(getCurrentItem() - i) > 1) {
            super.setCurrentItem(i, false);
        } else {
            super.setCurrentItem(i, z);
        }
    }

    private final class MonthViewPagerAdapter extends PagerAdapter {
        private MonthViewPagerAdapter() {
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return MonthViewPager.this.mMonthCount;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getItemPosition(Object obj) {
            if (MonthViewPager.this.isUpdateMonthView) {
                return -2;
            }
            return super.getItemPosition(obj);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view.equals(obj);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            int minYearMonth = (((MonthViewPager.this.mDelegate.getMinYearMonth() + i) - 1) / 12) + MonthViewPager.this.mDelegate.getMinYear();
            int minYearMonth2 = (((MonthViewPager.this.mDelegate.getMinYearMonth() + i) - 1) % 12) + 1;
            try {
                BaseMonthView baseMonthView = (BaseMonthView) MonthViewPager.this.mDelegate.getMonthViewClass().getConstructor(Context.class).newInstance(MonthViewPager.this.getContext());
                baseMonthView.mMonthViewPager = MonthViewPager.this;
                baseMonthView.mParentLayout = MonthViewPager.this.mParentLayout;
                baseMonthView.setup(MonthViewPager.this.mDelegate);
                baseMonthView.setTag(Integer.valueOf(i));
                baseMonthView.initMonthWithDate(minYearMonth, minYearMonth2);
                baseMonthView.setSelectedCalendar(MonthViewPager.this.mDelegate.mSelectedCalendar);
                viewGroup.addView(baseMonthView);
                return baseMonthView;
            } catch (Exception e) {
                e.printStackTrace();
                return new DefaultMonthView(MonthViewPager.this.getContext());
            }
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            BaseView baseView = (BaseView) obj;
            baseView.onDestroy();
            viewGroup.removeView(baseView);
        }
    }
}
