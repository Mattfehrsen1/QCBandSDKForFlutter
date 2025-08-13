package com.qcwireless.qcwatch.base.utils;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes3.dex */
public class DragUtils {
    protected int mXDelta;
    protected int mYDelta;

    public void dragView(final View view) {
        view.setOnTouchListener(new View.OnTouchListener() { // from class: com.qcwireless.qcwatch.base.utils.DragUtils.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                int action = event.getAction() & 255;
                if (action == 0) {
                    DragUtils.this.mXDelta = ((int) event.getRawX()) - marginLayoutParams.leftMargin;
                    DragUtils.this.mYDelta = ((int) event.getRawY()) - marginLayoutParams.topMargin;
                } else if (action == 2) {
                    int[] margin = DragUtils.this.parseMargin(event, (ViewGroup) view.getParent(), view);
                    marginLayoutParams.leftMargin = margin[0];
                    marginLayoutParams.topMargin = margin[1];
                    view.setLayoutParams(marginLayoutParams);
                }
                view.bringToFront();
                return true;
            }
        });
    }

    protected int[] parseMargin(MotionEvent event, ViewGroup parent, View view) {
        int rawX = ((int) event.getRawX()) - this.mXDelta;
        int width = ((parent.getWidth() - parent.getPaddingLeft()) - parent.getPaddingRight()) - view.getWidth();
        if (rawX <= 0) {
            rawX = 0;
        } else if (rawX >= width) {
            rawX = width;
        }
        int rawY = ((int) event.getRawY()) - this.mYDelta;
        int height = ((parent.getHeight() - parent.getPaddingTop()) - parent.getPaddingBottom()) - view.getHeight();
        if (rawY <= 0) {
            rawY = 0;
        } else if (rawY >= height) {
            rawY = height;
        }
        return new int[]{rawX, rawY};
    }
}
