package com.qcwireless.qcwatch.ui.base.view.pullrefresh;

import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/* loaded from: classes3.dex */
public abstract class PaintDrawable extends Drawable {
    protected Paint mPaint;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    protected PaintDrawable() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(-5592406);
    }

    public void setColor(int color) {
        this.mPaint.setColor(color);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        this.mPaint.setAlpha(alpha);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter cf) {
        this.mPaint.setColorFilter(cf);
    }
}
