package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/* loaded from: classes3.dex */
public class DiyCircleView extends FrameLayout {
    private float mRadius;

    public DiyCircleView(Context context) {
        super(context);
    }

    public DiyCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DiyCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        Path path = new Path();
        RectF rectF = new RectF(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        float f = this.mRadius;
        path.addRoundRect(rectF, f, f, Path.Direction.CW);
        if (Build.VERSION.SDK_INT >= 26) {
            canvas.clipPath(path);
        } else {
            canvas.clipPath(path, Region.Op.REPLACE);
        }
        super.dispatchDraw(canvas);
    }

    public void setRadius(int radius) {
        this.mRadius = radius;
        postInvalidate();
    }
}
