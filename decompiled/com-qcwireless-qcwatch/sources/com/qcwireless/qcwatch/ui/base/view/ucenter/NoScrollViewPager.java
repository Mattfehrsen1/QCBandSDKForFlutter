package com.qcwireless.qcwatch.ui.base.view.ucenter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes3.dex */
public class NoScrollViewPager extends ViewPager {
    private boolean isScroll;

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (this.isScroll) {
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        if (this.isScroll) {
            return super.onTouchEvent(ev);
        }
        return true;
    }

    public void setScroll(boolean scroll) {
        this.isScroll = scroll;
    }
}
