package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes3.dex */
public class MyRecycleView extends RecyclerView {
    private static final String TAG = "MyRecycleView";
    private int INVALID_POINTER;
    private int initialTouchX;
    private int initialTouchY;
    private Context mContext;
    private int scrollPointerId;
    private int touchSlop;

    public MyRecycleView(Context context) {
        this(context, null);
    }

    public MyRecycleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRecycleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.INVALID_POINTER = -1;
        this.scrollPointerId = -1;
        this.touchSlop = ViewConfiguration.get(context).getScaledEdgeSlop();
        this.mContext = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setScrollingTouchSlop(int slopConstant) {
        super.setScrollingTouchSlop(slopConstant);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(this.mContext);
        if (slopConstant == 0) {
            this.touchSlop = viewConfiguration.getScaledTouchSlop();
        } else {
            if (slopConstant != 1) {
                return;
            }
            this.touchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (e == null) {
            return false;
        }
        int actionMasked = MotionEventCompat.getActionMasked(e);
        int actionIndex = MotionEventCompat.getActionIndex(e);
        if (actionMasked == 0) {
            this.scrollPointerId = MotionEventCompat.getPointerId(e, 0);
            this.initialTouchX = Math.round(e.getX() + 0.5f);
            this.initialTouchY = Math.round(e.getY() + 0.5f);
            return super.onInterceptTouchEvent(e);
        }
        if (actionMasked != 2) {
            if (actionMasked == 5) {
                this.scrollPointerId = MotionEventCompat.getPointerId(e, actionIndex);
                this.initialTouchX = Math.round(MotionEventCompat.getX(e, actionIndex) + 0.5f);
                this.initialTouchY = Math.round(MotionEventCompat.getY(e, actionIndex) + 0.5f);
                return super.onInterceptTouchEvent(e);
            }
            return super.onInterceptTouchEvent(e);
        }
        int iFindPointerIndex = MotionEventCompat.findPointerIndex(e, this.scrollPointerId);
        if (iFindPointerIndex < 0) {
            return false;
        }
        int iRound = Math.round(MotionEventCompat.getX(e, iFindPointerIndex) + 0.5f);
        int iRound2 = Math.round(MotionEventCompat.getY(e, iFindPointerIndex) + 0.5f);
        if (getScrollState() != 1) {
            int i = iRound - this.initialTouchX;
            int i2 = iRound2 - this.initialTouchY;
            boolean z = getLayoutManager().canScrollHorizontally() && Math.abs(i) > this.touchSlop && Math.abs(i) > Math.abs(i2);
            if (getLayoutManager().canScrollVertically() && Math.abs(i2) > this.touchSlop && Math.abs(i2) - Math.abs(i) > 100) {
                z = true;
            }
            return z && super.onInterceptTouchEvent(e);
        }
        return super.onInterceptTouchEvent(e);
    }
}
