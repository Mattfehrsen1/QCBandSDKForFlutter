package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes3.dex */
public class DragView extends View {
    protected int mXDelta;
    protected int mYDelta;

    public DragView(Context context) {
        this(context, null);
    }

    public DragView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        int action = event.getAction() & 255;
        if (action == 0) {
            this.mXDelta = ((int) event.getRawX()) - marginLayoutParams.leftMargin;
            this.mYDelta = ((int) event.getRawY()) - marginLayoutParams.topMargin;
        } else if (action == 2) {
            int[] margin = parseMargin(event, (ViewGroup) getParent());
            marginLayoutParams.leftMargin = margin[0];
            marginLayoutParams.topMargin = margin[1];
            setLayoutParams(marginLayoutParams);
        }
        bringToFront();
        return true;
    }

    protected int[] parseMargin(MotionEvent event, ViewGroup parent) {
        int rawX = ((int) event.getRawX()) - this.mXDelta;
        int width = ((parent.getWidth() - parent.getPaddingLeft()) - parent.getPaddingRight()) - getWidth();
        if (rawX <= 0) {
            rawX = 0;
        } else if (rawX >= width) {
            rawX = width;
        }
        int rawY = ((int) event.getRawY()) - this.mYDelta;
        int height = ((parent.getHeight() - parent.getPaddingTop()) - parent.getPaddingBottom()) - getHeight();
        if (rawY <= 0) {
            rawY = 0;
        } else if (rawY >= height) {
            rawY = height;
        }
        return new int[]{rawX, rawY};
    }
}
