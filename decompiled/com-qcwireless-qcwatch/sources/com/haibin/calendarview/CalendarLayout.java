package com.haibin.calendarview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes2.dex */
public class CalendarLayout extends LinearLayout {
    private static final int ACTIVE_POINTER = 1;
    private static final int CALENDAR_SHOW_MODE_BOTH_MONTH_WEEK_VIEW = 0;
    private static final int CALENDAR_SHOW_MODE_ONLY_MONTH_VIEW = 2;
    private static final int CALENDAR_SHOW_MODE_ONLY_WEEK_VIEW = 1;
    private static final int GESTURE_MODE_DEFAULT = 0;
    private static final int GESTURE_MODE_DISABLED = 2;
    private static final int INVALID_POINTER = -1;
    private static final int STATUS_EXPAND = 0;
    private static final int STATUS_SHRINK = 1;
    private float downY;
    private boolean isAnimating;
    private boolean isWeekView;
    private int mActivePointerId;
    private int mCalendarShowMode;
    CalendarView mCalendarView;
    ViewGroup mContentView;
    private int mContentViewId;
    private int mContentViewTranslateY;
    private int mDefaultStatus;
    private CalendarViewDelegate mDelegate;
    private int mGestureMode;
    private int mItemHeight;
    private float mLastX;
    private float mLastY;
    private int mMaximumVelocity;
    MonthViewPager mMonthView;
    private VelocityTracker mVelocityTracker;
    private int mViewPagerTranslateY;
    WeekBar mWeekBar;
    WeekViewPager mWeekPager;
    YearViewPager mYearView;

    public interface CalendarScrollView {
        boolean isScrollToTop();
    }

    public CalendarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mViewPagerTranslateY = 0;
        this.isAnimating = false;
        setOrientation(1);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CalendarLayout);
        this.mContentViewId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CalendarLayout_calendar_content_view_id, 0);
        this.mDefaultStatus = typedArrayObtainStyledAttributes.getInt(R.styleable.CalendarLayout_default_status, 0);
        this.mCalendarShowMode = typedArrayObtainStyledAttributes.getInt(R.styleable.CalendarLayout_calendar_show_mode, 0);
        this.mGestureMode = typedArrayObtainStyledAttributes.getInt(R.styleable.CalendarLayout_gesture_mode, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.mVelocityTracker = VelocityTracker.obtain();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        viewConfiguration.getScaledTouchSlop();
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    final void setup(CalendarViewDelegate calendarViewDelegate) {
        this.mDelegate = calendarViewDelegate;
        this.mItemHeight = calendarViewDelegate.getCalendarItemHeight();
        initCalendarPosition(calendarViewDelegate.mSelectedCalendar.isAvailable() ? calendarViewDelegate.mSelectedCalendar : calendarViewDelegate.createCurrentDate());
        updateContentViewTranslateY();
    }

    private void initCalendarPosition(Calendar calendar) {
        updateSelectPosition((CalendarUtil.getMonthViewStartDiff(calendar, this.mDelegate.getWeekStart()) + calendar.getDay()) - 1);
    }

    final void updateSelectPosition(int i) {
        this.mViewPagerTranslateY = (((i + 7) / 7) - 1) * this.mItemHeight;
    }

    final void updateSelectWeek(int i) {
        this.mViewPagerTranslateY = (i - 1) * this.mItemHeight;
    }

    void updateContentViewTranslateY() {
        ViewGroup viewGroup;
        Calendar calendar = this.mDelegate.mIndexCalendar;
        if (this.mDelegate.getMonthViewShowMode() == 0) {
            this.mContentViewTranslateY = this.mItemHeight * 5;
        } else {
            this.mContentViewTranslateY = CalendarUtil.getMonthViewHeight(calendar.getYear(), calendar.getMonth(), this.mItemHeight, this.mDelegate.getWeekStart()) - this.mItemHeight;
        }
        if (this.mWeekPager.getVisibility() != 0 || (viewGroup = this.mContentView) == null) {
            return;
        }
        viewGroup.setTranslationY(-this.mContentViewTranslateY);
    }

    final void updateCalendarItemHeight() {
        this.mItemHeight = this.mDelegate.getCalendarItemHeight();
        if (this.mContentView == null) {
            return;
        }
        Calendar calendar = this.mDelegate.mIndexCalendar;
        updateSelectWeek(CalendarUtil.getWeekFromDayInMonth(calendar, this.mDelegate.getWeekStart()));
        if (this.mDelegate.getMonthViewShowMode() == 0) {
            this.mContentViewTranslateY = this.mItemHeight * 5;
        } else {
            this.mContentViewTranslateY = CalendarUtil.getMonthViewHeight(calendar.getYear(), calendar.getMonth(), this.mItemHeight, this.mDelegate.getWeekStart()) - this.mItemHeight;
        }
        translationViewPager();
        if (this.mWeekPager.getVisibility() == 0) {
            this.mContentView.setTranslationY(-this.mContentViewTranslateY);
        }
    }

    public void hideCalendarView() {
        CalendarView calendarView = this.mCalendarView;
        if (calendarView == null) {
            return;
        }
        calendarView.setVisibility(8);
        if (!isExpand()) {
            expand(0);
        }
        requestLayout();
    }

    public void showCalendarView() {
        this.mCalendarView.setVisibility(0);
        requestLayout();
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x005f  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r9) {
        /*
            Method dump skipped, instructions count: 379
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.haibin.calendarview.CalendarLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        CalendarView calendarView;
        ViewGroup viewGroup;
        if (this.isAnimating) {
            return super.dispatchTouchEvent(motionEvent);
        }
        if (this.mGestureMode == 2) {
            return super.dispatchTouchEvent(motionEvent);
        }
        if (this.mYearView == null || (calendarView = this.mCalendarView) == null || calendarView.getVisibility() == 8 || (viewGroup = this.mContentView) == null || viewGroup.getVisibility() != 0) {
            return super.dispatchTouchEvent(motionEvent);
        }
        int i = this.mCalendarShowMode;
        if (i == 2 || i == 1) {
            return super.dispatchTouchEvent(motionEvent);
        }
        if (this.mYearView.getVisibility() == 0 || this.mDelegate.isShowYearSelectedLayout) {
            return super.dispatchTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        if (action == 2 && y - this.mLastY > 0.0f && this.mContentView.getTranslationY() == (-this.mContentViewTranslateY) && isScrollTop()) {
            requestDisallowInterceptTouchEvent(false);
            return super.dispatchTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        CalendarView calendarView;
        ViewGroup viewGroup;
        if (this.isAnimating) {
            return true;
        }
        if (this.mGestureMode == 2) {
            return false;
        }
        if (this.mYearView == null || (calendarView = this.mCalendarView) == null || calendarView.getVisibility() == 8 || (viewGroup = this.mContentView) == null || viewGroup.getVisibility() != 0) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        int i = this.mCalendarShowMode;
        if (i == 2 || i == 1) {
            return false;
        }
        if (this.mYearView.getVisibility() == 0 || this.mDelegate.isShowYearSelectedLayout) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        float x = motionEvent.getX();
        if (action == 0) {
            this.mActivePointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
            this.downY = y;
            this.mLastY = y;
            this.mLastX = x;
        } else if (action == 2) {
            float f = y - this.mLastY;
            float f2 = x - this.mLastX;
            if (f < 0.0f && this.mContentView.getTranslationY() == (-this.mContentViewTranslateY)) {
                return false;
            }
            if (f > 0.0f && this.mContentView.getTranslationY() == (-this.mContentViewTranslateY) && y >= this.mDelegate.getCalendarItemHeight() + this.mDelegate.getWeekBarHeight() && !isScrollTop()) {
                return false;
            }
            if (f > 0.0f && this.mContentView.getTranslationY() == 0.0f && y >= CalendarUtil.dipToPx(getContext(), 98.0f)) {
                return false;
            }
            if (Math.abs(f) > Math.abs(f2) && ((f > 0.0f && this.mContentView.getTranslationY() <= 0.0f) || (f < 0.0f && this.mContentView.getTranslationY() >= (-this.mContentViewTranslateY)))) {
                this.mLastY = y;
                return true;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    private int getPointerIndex(MotionEvent motionEvent, int i) {
        int iFindPointerIndex = motionEvent.findPointerIndex(i);
        if (iFindPointerIndex == -1) {
            this.mActivePointerId = -1;
        }
        return iFindPointerIndex;
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.mContentView == null || this.mCalendarView == null) {
            super.onMeasure(i, i2);
            return;
        }
        int year = this.mDelegate.mIndexCalendar.getYear();
        int month = this.mDelegate.mIndexCalendar.getMonth();
        int iDipToPx = CalendarUtil.dipToPx(getContext(), 1.0f) + this.mDelegate.getWeekBarHeight();
        int monthViewHeight = CalendarUtil.getMonthViewHeight(year, month, this.mDelegate.getCalendarItemHeight(), this.mDelegate.getWeekStart(), this.mDelegate.getMonthViewShowMode()) + iDipToPx;
        int size = View.MeasureSpec.getSize(i2);
        if (this.mDelegate.isFullScreenCalendar()) {
            super.onMeasure(i, i2);
            this.mContentView.measure(i, View.MeasureSpec.makeMeasureSpec((size - iDipToPx) - this.mDelegate.getCalendarItemHeight(), 1073741824));
            ViewGroup viewGroup = this.mContentView;
            viewGroup.layout(viewGroup.getLeft(), this.mContentView.getTop(), this.mContentView.getRight(), this.mContentView.getBottom());
            return;
        }
        if (monthViewHeight >= size && this.mMonthView.getHeight() > 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(monthViewHeight + iDipToPx + this.mDelegate.getWeekBarHeight(), 1073741824);
            size = monthViewHeight;
        } else if (monthViewHeight < size && this.mMonthView.getHeight() > 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(size, 1073741824);
        }
        if (this.mCalendarShowMode == 2 || this.mCalendarView.getVisibility() == 8) {
            monthViewHeight = this.mCalendarView.getVisibility() == 8 ? 0 : this.mCalendarView.getHeight();
        } else if (this.mGestureMode != 2 || this.isAnimating || !isExpand()) {
            size -= iDipToPx;
            monthViewHeight = this.mItemHeight;
        }
        super.onMeasure(i, i2);
        this.mContentView.measure(i, View.MeasureSpec.makeMeasureSpec(size - monthViewHeight, 1073741824));
        ViewGroup viewGroup2 = this.mContentView;
        viewGroup2.layout(viewGroup2.getLeft(), this.mContentView.getTop(), this.mContentView.getRight(), this.mContentView.getBottom());
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mMonthView = (MonthViewPager) findViewById(R.id.vp_month);
        this.mWeekPager = (WeekViewPager) findViewById(R.id.vp_week);
        if (getChildCount() > 0) {
            this.mCalendarView = (CalendarView) getChildAt(0);
        }
        this.mContentView = (ViewGroup) findViewById(this.mContentViewId);
        this.mYearView = (YearViewPager) findViewById(R.id.selectLayout);
    }

    private void translationViewPager() {
        this.mMonthView.setTranslationY(this.mViewPagerTranslateY * ((this.mContentView.getTranslationY() * 1.0f) / this.mContentViewTranslateY));
    }

    public void setModeBothMonthWeekView() {
        this.mCalendarShowMode = 0;
        requestLayout();
    }

    public void setModeOnlyWeekView() {
        this.mCalendarShowMode = 1;
        requestLayout();
    }

    public void setModeOnlyMonthView() {
        this.mCalendarShowMode = 2;
        requestLayout();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("super", super.onSaveInstanceState());
        bundle.putBoolean("isExpand", isExpand());
        return bundle;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        Bundle bundle = (Bundle) parcelable;
        Parcelable parcelable2 = bundle.getParcelable("super");
        if (bundle.getBoolean("isExpand")) {
            post(new Runnable() { // from class: com.haibin.calendarview.CalendarLayout.1
                @Override // java.lang.Runnable
                public void run() {
                    CalendarLayout.this.expand(0);
                }
            });
        } else {
            post(new Runnable() { // from class: com.haibin.calendarview.CalendarLayout.2
                @Override // java.lang.Runnable
                public void run() {
                    CalendarLayout.this.shrink(0);
                }
            });
        }
        super.onRestoreInstanceState(parcelable2);
    }

    public final boolean isExpand() {
        return this.mMonthView.getVisibility() == 0;
    }

    public boolean expand() {
        return expand(240);
    }

    public boolean expand(int i) {
        if (this.isAnimating || this.mCalendarShowMode == 1 || this.mContentView == null) {
            return false;
        }
        if (this.mMonthView.getVisibility() != 0) {
            this.mWeekPager.setVisibility(8);
            onShowMonthView();
            this.isWeekView = false;
            this.mMonthView.setVisibility(0);
        }
        ViewGroup viewGroup = this.mContentView;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewGroup, "translationY", viewGroup.getTranslationY(), 0.0f);
        objectAnimatorOfFloat.setDuration(i);
        objectAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.haibin.calendarview.CalendarLayout.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CalendarLayout.this.mMonthView.setTranslationY(CalendarLayout.this.mViewPagerTranslateY * ((((Float) valueAnimator.getAnimatedValue()).floatValue() * 1.0f) / CalendarLayout.this.mContentViewTranslateY));
                CalendarLayout.this.isAnimating = true;
            }
        });
        objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.haibin.calendarview.CalendarLayout.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                CalendarLayout.this.isAnimating = false;
                if (CalendarLayout.this.mGestureMode == 2) {
                    CalendarLayout.this.requestLayout();
                }
                CalendarLayout.this.hideWeek(true);
                if (CalendarLayout.this.mDelegate.mViewChangeListener != null && CalendarLayout.this.isWeekView) {
                    CalendarLayout.this.mDelegate.mViewChangeListener.onViewChange(true);
                }
                CalendarLayout.this.isWeekView = false;
            }
        });
        objectAnimatorOfFloat.start();
        return true;
    }

    public boolean shrink() {
        return shrink(240);
    }

    public boolean shrink(int i) {
        ViewGroup viewGroup;
        if (this.mGestureMode == 2) {
            requestLayout();
        }
        if (this.isAnimating || (viewGroup = this.mContentView) == null) {
            return false;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewGroup, "translationY", viewGroup.getTranslationY(), -this.mContentViewTranslateY);
        objectAnimatorOfFloat.setDuration(i);
        objectAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.haibin.calendarview.CalendarLayout.5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CalendarLayout.this.mMonthView.setTranslationY(CalendarLayout.this.mViewPagerTranslateY * ((((Float) valueAnimator.getAnimatedValue()).floatValue() * 1.0f) / CalendarLayout.this.mContentViewTranslateY));
                CalendarLayout.this.isAnimating = true;
            }
        });
        objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.haibin.calendarview.CalendarLayout.6
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                CalendarLayout.this.isAnimating = false;
                CalendarLayout.this.showWeek();
                CalendarLayout.this.isWeekView = true;
            }
        });
        objectAnimatorOfFloat.start();
        return true;
    }

    final void initStatus() {
        if ((this.mDefaultStatus == 1 || this.mCalendarShowMode == 1) && this.mCalendarShowMode != 2) {
            if (this.mContentView == null) {
                this.mWeekPager.setVisibility(0);
                this.mMonthView.setVisibility(8);
                return;
            } else {
                post(new Runnable() { // from class: com.haibin.calendarview.CalendarLayout.7
                    @Override // java.lang.Runnable
                    public void run() {
                        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(CalendarLayout.this.mContentView, "translationY", CalendarLayout.this.mContentView.getTranslationY(), -CalendarLayout.this.mContentViewTranslateY);
                        objectAnimatorOfFloat.setDuration(0L);
                        objectAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.haibin.calendarview.CalendarLayout.7.1
                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                CalendarLayout.this.mMonthView.setTranslationY(CalendarLayout.this.mViewPagerTranslateY * ((((Float) valueAnimator.getAnimatedValue()).floatValue() * 1.0f) / CalendarLayout.this.mContentViewTranslateY));
                                CalendarLayout.this.isAnimating = true;
                            }
                        });
                        objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.haibin.calendarview.CalendarLayout.7.2
                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                            public void onAnimationEnd(Animator animator) {
                                super.onAnimationEnd(animator);
                                CalendarLayout.this.isAnimating = false;
                                CalendarLayout.this.isWeekView = true;
                                CalendarLayout.this.showWeek();
                                if (CalendarLayout.this.mDelegate == null || CalendarLayout.this.mDelegate.mViewChangeListener == null) {
                                    return;
                                }
                                CalendarLayout.this.mDelegate.mViewChangeListener.onViewChange(false);
                            }
                        });
                        objectAnimatorOfFloat.start();
                    }
                });
                return;
            }
        }
        if (this.mDelegate.mViewChangeListener == null) {
            return;
        }
        post(new Runnable() { // from class: com.haibin.calendarview.CalendarLayout.8
            @Override // java.lang.Runnable
            public void run() {
                CalendarLayout.this.mDelegate.mViewChangeListener.onViewChange(true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideWeek(boolean z) {
        if (z) {
            onShowMonthView();
        }
        this.mWeekPager.setVisibility(8);
        this.mMonthView.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showWeek() {
        onShowWeekView();
        WeekViewPager weekViewPager = this.mWeekPager;
        if (weekViewPager != null && weekViewPager.getAdapter() != null) {
            this.mWeekPager.getAdapter().notifyDataSetChanged();
            this.mWeekPager.setVisibility(0);
        }
        this.mMonthView.setVisibility(4);
    }

    private void onShowWeekView() {
        CalendarViewDelegate calendarViewDelegate;
        if (this.mWeekPager.getVisibility() == 0 || (calendarViewDelegate = this.mDelegate) == null || calendarViewDelegate.mViewChangeListener == null || this.isWeekView) {
            return;
        }
        this.mDelegate.mViewChangeListener.onViewChange(false);
    }

    private void onShowMonthView() {
        CalendarViewDelegate calendarViewDelegate;
        if (this.mMonthView.getVisibility() == 0 || (calendarViewDelegate = this.mDelegate) == null || calendarViewDelegate.mViewChangeListener == null || !this.isWeekView) {
            return;
        }
        this.mDelegate.mViewChangeListener.onViewChange(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected boolean isScrollTop() {
        ViewGroup viewGroup = this.mContentView;
        if (viewGroup instanceof CalendarScrollView) {
            return ((CalendarScrollView) viewGroup).isScrollToTop();
        }
        if (viewGroup instanceof RecyclerView) {
            return ((RecyclerView) viewGroup).computeVerticalScrollOffset() == 0;
        }
        if (!(viewGroup instanceof AbsListView)) {
            return viewGroup.getScrollY() == 0;
        }
        AbsListView absListView = (AbsListView) viewGroup;
        if (absListView.getFirstVisiblePosition() == 0) {
            return absListView.getChildAt(0).getTop() == 0;
        }
        return false;
    }

    final void hideContentView() {
        ViewGroup viewGroup = this.mContentView;
        if (viewGroup == null) {
            return;
        }
        viewGroup.animate().translationY(getHeight() - this.mMonthView.getHeight()).setDuration(220L).setInterpolator(new LinearInterpolator()).setListener(new AnimatorListenerAdapter() { // from class: com.haibin.calendarview.CalendarLayout.9
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                CalendarLayout.this.mContentView.setVisibility(4);
                CalendarLayout.this.mContentView.clearAnimation();
            }
        });
    }

    final void showContentView() {
        ViewGroup viewGroup = this.mContentView;
        if (viewGroup == null) {
            return;
        }
        viewGroup.setTranslationY(getHeight() - this.mMonthView.getHeight());
        this.mContentView.setVisibility(0);
        this.mContentView.animate().translationY(0.0f).setDuration(180L).setInterpolator(new LinearInterpolator()).setListener(new AnimatorListenerAdapter() { // from class: com.haibin.calendarview.CalendarLayout.10
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
            }
        });
    }

    private int getCalendarViewHeight() {
        int weekBarHeight;
        int calendarItemHeight;
        if (this.mMonthView.getVisibility() == 0) {
            weekBarHeight = this.mDelegate.getWeekBarHeight();
            calendarItemHeight = this.mMonthView.getHeight();
        } else {
            weekBarHeight = this.mDelegate.getWeekBarHeight();
            calendarItemHeight = this.mDelegate.getCalendarItemHeight();
        }
        return weekBarHeight + calendarItemHeight;
    }
}
