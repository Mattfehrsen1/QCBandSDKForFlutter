package com.haibin.calendarview;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.haibin.calendarview.YearRecyclerView;

/* loaded from: classes2.dex */
public final class YearViewPager extends ViewPager {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean isUpdateYearView;
    private CalendarViewDelegate mDelegate;
    private YearRecyclerView.OnMonthSelectedListener mListener;
    private int mYearCount;

    public YearViewPager(Context context) {
        this(context, null);
    }

    public YearViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    void setup(CalendarViewDelegate calendarViewDelegate) {
        this.mDelegate = calendarViewDelegate;
        this.mYearCount = (calendarViewDelegate.getMaxYear() - this.mDelegate.getMinYear()) + 1;
        setAdapter(new PagerAdapter() { // from class: com.haibin.calendarview.YearViewPager.1
            @Override // androidx.viewpager.widget.PagerAdapter
            public boolean isViewFromObject(View view, Object obj) {
                return view == obj;
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public int getCount() {
                return YearViewPager.this.mYearCount;
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public int getItemPosition(Object obj) {
                if (YearViewPager.this.isUpdateYearView) {
                    return -2;
                }
                return super.getItemPosition(obj);
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public Object instantiateItem(ViewGroup viewGroup, int i) {
                YearRecyclerView yearRecyclerView = new YearRecyclerView(YearViewPager.this.getContext());
                viewGroup.addView(yearRecyclerView);
                yearRecyclerView.setup(YearViewPager.this.mDelegate);
                yearRecyclerView.setOnMonthSelectedListener(YearViewPager.this.mListener);
                yearRecyclerView.init(i + YearViewPager.this.mDelegate.getMinYear());
                return yearRecyclerView;
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
                viewGroup.removeView((View) obj);
            }
        });
        setCurrentItem(this.mDelegate.getCurrentDay().getYear() - this.mDelegate.getMinYear());
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setCurrentItem(int i) {
        setCurrentItem(i, false);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setCurrentItem(int i, boolean z) {
        if (Math.abs(getCurrentItem() - i) > 1) {
            super.setCurrentItem(i, false);
        } else {
            super.setCurrentItem(i, false);
        }
    }

    void notifyDataSetChanged() {
        this.mYearCount = (this.mDelegate.getMaxYear() - this.mDelegate.getMinYear()) + 1;
        if (getAdapter() != null) {
            getAdapter().notifyDataSetChanged();
        }
    }

    void scrollToYear(int i, boolean z) {
        setCurrentItem(i - this.mDelegate.getMinYear(), z);
    }

    final void updateRange() {
        this.isUpdateYearView = true;
        notifyDataSetChanged();
        this.isUpdateYearView = false;
    }

    final void update() {
        for (int i = 0; i < getChildCount(); i++) {
            ((YearRecyclerView) getChildAt(i)).notifyAdapterDataSetChanged();
        }
    }

    final void updateWeekStart() {
        for (int i = 0; i < getChildCount(); i++) {
            YearRecyclerView yearRecyclerView = (YearRecyclerView) getChildAt(i);
            yearRecyclerView.updateWeekStart();
            yearRecyclerView.notifyAdapterDataSetChanged();
        }
    }

    final void updateStyle() {
        for (int i = 0; i < getChildCount(); i++) {
            ((YearRecyclerView) getChildAt(i)).updateStyle();
        }
    }

    final void setOnMonthSelectedListener(YearRecyclerView.OnMonthSelectedListener onMonthSelectedListener) {
        this.mListener = onMonthSelectedListener;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    protected void onMeasure(int i, int i2) throws Resources.NotFoundException {
        super.onMeasure(i, i2);
    }

    private static int getHeight(Context context, View view) {
        int height = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getHeight();
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        view.getLocationOnScreen(iArr);
        return height - iArr[1];
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mDelegate.isYearViewScrollable() && super.onTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mDelegate.isYearViewScrollable() && super.onInterceptTouchEvent(motionEvent);
    }
}
