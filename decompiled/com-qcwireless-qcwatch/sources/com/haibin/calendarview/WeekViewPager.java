package com.haibin.calendarview;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import java.util.List;

/* loaded from: classes2.dex */
public final class WeekViewPager extends ViewPager {
    private boolean isUpdateWeekView;
    private boolean isUsingScrollToCalendar;
    private CalendarViewDelegate mDelegate;
    CalendarLayout mParentLayout;
    private int mWeekCount;

    public WeekViewPager(Context context) {
        this(context, null);
    }

    public WeekViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isUsingScrollToCalendar = false;
    }

    void setup(CalendarViewDelegate calendarViewDelegate) {
        this.mDelegate = calendarViewDelegate;
        init();
    }

    private void init() {
        this.mWeekCount = CalendarUtil.getWeekCountBetweenBothCalendar(this.mDelegate.getMinYear(), this.mDelegate.getMinYearMonth(), this.mDelegate.getMinYearDay(), this.mDelegate.getMaxYear(), this.mDelegate.getMaxYearMonth(), this.mDelegate.getMaxYearDay(), this.mDelegate.getWeekStart());
        setAdapter(new WeekViewPagerAdapter());
        addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.haibin.calendarview.WeekViewPager.1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                if (WeekViewPager.this.getVisibility() != 0) {
                    WeekViewPager.this.isUsingScrollToCalendar = false;
                    return;
                }
                if (WeekViewPager.this.isUsingScrollToCalendar) {
                    WeekViewPager.this.isUsingScrollToCalendar = false;
                    return;
                }
                BaseWeekView baseWeekView = (BaseWeekView) WeekViewPager.this.findViewWithTag(Integer.valueOf(i));
                if (baseWeekView != null) {
                    baseWeekView.performClickCalendar(WeekViewPager.this.mDelegate.getSelectMode() != 0 ? WeekViewPager.this.mDelegate.mIndexCalendar : WeekViewPager.this.mDelegate.mSelectedCalendar, !WeekViewPager.this.isUsingScrollToCalendar);
                    if (WeekViewPager.this.mDelegate.mWeekChangeListener != null) {
                        WeekViewPager.this.mDelegate.mWeekChangeListener.onWeekChange(WeekViewPager.this.getCurrentWeekCalendars());
                    }
                }
                WeekViewPager.this.isUsingScrollToCalendar = false;
            }
        });
    }

    List<Calendar> getCurrentWeekCalendars() {
        List<Calendar> weekCalendars = CalendarUtil.getWeekCalendars(this.mDelegate.mIndexCalendar, this.mDelegate);
        this.mDelegate.addSchemesFromMap(weekCalendars);
        return weekCalendars;
    }

    void notifyDataSetChanged() {
        this.mWeekCount = CalendarUtil.getWeekCountBetweenBothCalendar(this.mDelegate.getMinYear(), this.mDelegate.getMinYearMonth(), this.mDelegate.getMinYearDay(), this.mDelegate.getMaxYear(), this.mDelegate.getMaxYearMonth(), this.mDelegate.getMaxYearDay(), this.mDelegate.getWeekStart());
        notifyAdapterDataSetChanged();
    }

    void updateWeekViewClass() {
        this.isUpdateWeekView = true;
        notifyAdapterDataSetChanged();
        this.isUpdateWeekView = false;
    }

    void updateRange() {
        this.isUpdateWeekView = true;
        notifyDataSetChanged();
        this.isUpdateWeekView = false;
        if (getVisibility() != 0) {
            return;
        }
        this.isUsingScrollToCalendar = true;
        Calendar calendar = this.mDelegate.mSelectedCalendar;
        updateSelected(calendar, false);
        if (this.mDelegate.mInnerListener != null) {
            this.mDelegate.mInnerListener.onWeekDateSelected(calendar, false);
        }
        if (this.mDelegate.mCalendarSelectListener != null) {
            this.mDelegate.mCalendarSelectListener.onCalendarSelect(calendar, false);
        }
        this.mParentLayout.updateSelectWeek(CalendarUtil.getWeekFromDayInMonth(calendar, this.mDelegate.getWeekStart()));
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
        updateSelected(calendar, z);
        if (this.mDelegate.mInnerListener != null) {
            this.mDelegate.mInnerListener.onWeekDateSelected(calendar, false);
        }
        if (this.mDelegate.mCalendarSelectListener != null && z2) {
            this.mDelegate.mCalendarSelectListener.onCalendarSelect(calendar, false);
        }
        this.mParentLayout.updateSelectWeek(CalendarUtil.getWeekFromDayInMonth(calendar, this.mDelegate.getWeekStart()));
    }

    void scrollToCurrent(boolean z) {
        this.isUsingScrollToCalendar = true;
        int weekFromCalendarStartWithMinCalendar = CalendarUtil.getWeekFromCalendarStartWithMinCalendar(this.mDelegate.getCurrentDay(), this.mDelegate.getMinYear(), this.mDelegate.getMinYearMonth(), this.mDelegate.getMinYearDay(), this.mDelegate.getWeekStart()) - 1;
        if (getCurrentItem() == weekFromCalendarStartWithMinCalendar) {
            this.isUsingScrollToCalendar = false;
        }
        setCurrentItem(weekFromCalendarStartWithMinCalendar, z);
        BaseWeekView baseWeekView = (BaseWeekView) findViewWithTag(Integer.valueOf(weekFromCalendarStartWithMinCalendar));
        if (baseWeekView != null) {
            baseWeekView.performClickCalendar(this.mDelegate.getCurrentDay(), false);
            baseWeekView.setSelectedCalendar(this.mDelegate.getCurrentDay());
            baseWeekView.invalidate();
        }
        if (this.mDelegate.mCalendarSelectListener != null && getVisibility() == 0) {
            this.mDelegate.mCalendarSelectListener.onCalendarSelect(this.mDelegate.mSelectedCalendar, false);
        }
        if (getVisibility() == 0) {
            this.mDelegate.mInnerListener.onWeekDateSelected(this.mDelegate.getCurrentDay(), false);
        }
        this.mParentLayout.updateSelectWeek(CalendarUtil.getWeekFromDayInMonth(this.mDelegate.getCurrentDay(), this.mDelegate.getWeekStart()));
    }

    void updateSelected(Calendar calendar, boolean z) {
        int weekFromCalendarStartWithMinCalendar = CalendarUtil.getWeekFromCalendarStartWithMinCalendar(calendar, this.mDelegate.getMinYear(), this.mDelegate.getMinYearMonth(), this.mDelegate.getMinYearDay(), this.mDelegate.getWeekStart()) - 1;
        this.isUsingScrollToCalendar = getCurrentItem() != weekFromCalendarStartWithMinCalendar;
        setCurrentItem(weekFromCalendarStartWithMinCalendar, z);
        BaseWeekView baseWeekView = (BaseWeekView) findViewWithTag(Integer.valueOf(weekFromCalendarStartWithMinCalendar));
        if (baseWeekView != null) {
            baseWeekView.setSelectedCalendar(calendar);
            baseWeekView.invalidate();
        }
    }

    void updateSingleSelect() {
        if (this.mDelegate.getSelectMode() == 0) {
            return;
        }
        for (int i = 0; i < getChildCount(); i++) {
            ((BaseWeekView) getChildAt(i)).updateSingleSelect();
        }
    }

    void updateDefaultSelect() {
        BaseWeekView baseWeekView = (BaseWeekView) findViewWithTag(Integer.valueOf(getCurrentItem()));
        if (baseWeekView != null) {
            baseWeekView.setSelectedCalendar(this.mDelegate.mSelectedCalendar);
            baseWeekView.invalidate();
        }
    }

    void updateSelected() {
        for (int i = 0; i < getChildCount(); i++) {
            BaseWeekView baseWeekView = (BaseWeekView) getChildAt(i);
            baseWeekView.setSelectedCalendar(this.mDelegate.mSelectedCalendar);
            baseWeekView.invalidate();
        }
    }

    final void updateStyle() {
        for (int i = 0; i < getChildCount(); i++) {
            BaseWeekView baseWeekView = (BaseWeekView) getChildAt(i);
            baseWeekView.updateStyle();
            baseWeekView.invalidate();
        }
    }

    void updateScheme() {
        for (int i = 0; i < getChildCount(); i++) {
            ((BaseWeekView) getChildAt(i)).update();
        }
    }

    void updateCurrentDate() {
        for (int i = 0; i < getChildCount(); i++) {
            ((BaseWeekView) getChildAt(i)).updateCurrentDate();
        }
    }

    void updateShowMode() {
        for (int i = 0; i < getChildCount(); i++) {
            ((BaseWeekView) getChildAt(i)).updateShowMode();
        }
    }

    void updateWeekStart() {
        if (getAdapter() == null) {
            return;
        }
        int count = getAdapter().getCount();
        int weekCountBetweenBothCalendar = CalendarUtil.getWeekCountBetweenBothCalendar(this.mDelegate.getMinYear(), this.mDelegate.getMinYearMonth(), this.mDelegate.getMinYearDay(), this.mDelegate.getMaxYear(), this.mDelegate.getMaxYearMonth(), this.mDelegate.getMaxYearDay(), this.mDelegate.getWeekStart());
        this.mWeekCount = weekCountBetweenBothCalendar;
        if (count != weekCountBetweenBothCalendar) {
            this.isUpdateWeekView = true;
            getAdapter().notifyDataSetChanged();
        }
        for (int i = 0; i < getChildCount(); i++) {
            ((BaseWeekView) getChildAt(i)).updateWeekStart();
        }
        this.isUpdateWeekView = false;
        updateSelected(this.mDelegate.mSelectedCalendar, false);
    }

    final void updateItemHeight() {
        for (int i = 0; i < getChildCount(); i++) {
            BaseWeekView baseWeekView = (BaseWeekView) getChildAt(i);
            baseWeekView.updateItemHeight();
            baseWeekView.requestLayout();
        }
    }

    final void clearSelectRange() {
        for (int i = 0; i < getChildCount(); i++) {
            ((BaseWeekView) getChildAt(i)).invalidate();
        }
    }

    final void clearSingleSelect() {
        for (int i = 0; i < getChildCount(); i++) {
            BaseWeekView baseWeekView = (BaseWeekView) getChildAt(i);
            baseWeekView.mCurrentItem = -1;
            baseWeekView.invalidate();
        }
    }

    final void clearMultiSelect() {
        for (int i = 0; i < getChildCount(); i++) {
            BaseWeekView baseWeekView = (BaseWeekView) getChildAt(i);
            baseWeekView.mCurrentItem = -1;
            baseWeekView.invalidate();
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
        return this.mDelegate.isWeekViewScrollable() && super.onTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mDelegate.isWeekViewScrollable() && super.onInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    protected void onMeasure(int i, int i2) throws Resources.NotFoundException {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(this.mDelegate.getCalendarItemHeight(), 1073741824));
    }

    private class WeekViewPagerAdapter extends PagerAdapter {
        private WeekViewPagerAdapter() {
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return WeekViewPager.this.mWeekCount;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getItemPosition(Object obj) {
            if (WeekViewPager.this.isUpdateWeekView) {
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
            Calendar firstCalendarStartWithMinCalendar = CalendarUtil.getFirstCalendarStartWithMinCalendar(WeekViewPager.this.mDelegate.getMinYear(), WeekViewPager.this.mDelegate.getMinYearMonth(), WeekViewPager.this.mDelegate.getMinYearDay(), i + 1, WeekViewPager.this.mDelegate.getWeekStart());
            try {
                BaseWeekView baseWeekView = (BaseWeekView) WeekViewPager.this.mDelegate.getWeekViewClass().getConstructor(Context.class).newInstance(WeekViewPager.this.getContext());
                baseWeekView.mParentLayout = WeekViewPager.this.mParentLayout;
                baseWeekView.setup(WeekViewPager.this.mDelegate);
                baseWeekView.setup(firstCalendarStartWithMinCalendar);
                baseWeekView.setTag(Integer.valueOf(i));
                baseWeekView.setSelectedCalendar(WeekViewPager.this.mDelegate.mSelectedCalendar);
                viewGroup.addView(baseWeekView);
                return baseWeekView;
            } catch (Exception e) {
                e.printStackTrace();
                return new DefaultWeekView(WeekViewPager.this.getContext());
            }
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            BaseWeekView baseWeekView = (BaseWeekView) obj;
            baseWeekView.onDestroy();
            viewGroup.removeView(baseWeekView);
        }
    }
}
