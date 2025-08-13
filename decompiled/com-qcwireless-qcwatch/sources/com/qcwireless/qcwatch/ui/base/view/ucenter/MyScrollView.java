package com.qcwireless.qcwatch.ui.base.view.ucenter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.core.widget.NestedScrollView;

/* loaded from: classes3.dex */
public class MyScrollView extends NestedScrollView {
    private int downX;
    private int downY;
    private int mTouchSlop;
    private int moveX;
    private int moveY;

    public MyScrollView(Context context) {
        super(context);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override // androidx.core.widget.NestedScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        if (action == 0) {
            this.downX = (int) e.getRawX();
            this.downY = (int) e.getRawY();
        } else if (action == 2) {
            this.moveY = (int) e.getRawY();
            int rawX = (int) e.getRawX();
            this.moveX = rawX;
            if (Math.abs(rawX - this.downX) > this.mTouchSlop) {
                return false;
            }
        }
        return super.onInterceptTouchEvent(e);
    }
}
