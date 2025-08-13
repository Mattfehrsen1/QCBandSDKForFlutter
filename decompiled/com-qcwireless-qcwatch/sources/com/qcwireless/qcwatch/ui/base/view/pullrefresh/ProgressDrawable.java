package com.qcwireless.qcwatch.ui.base.view.pullrefresh;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import androidx.work.WorkRequest;
import com.blankj.utilcode.constant.CacheConstants;

/* loaded from: classes3.dex */
public class ProgressDrawable extends PaintDrawable implements Animatable, ValueAnimator.AnimatorUpdateListener {
    protected ValueAnimator mValueAnimator;
    protected int mWidth = 0;
    protected int mHeight = 0;
    protected int mProgressDegree = 0;
    protected Path mPath = new Path();

    public ProgressDrawable() {
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(30, CacheConstants.HOUR);
        this.mValueAnimator = valueAnimatorOfInt;
        valueAnimatorOfInt.setDuration(WorkRequest.MIN_BACKOFF_MILLIS);
        this.mValueAnimator.setInterpolator(null);
        this.mValueAnimator.setRepeatCount(-1);
        this.mValueAnimator.setRepeatMode(1);
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator animation) {
        this.mProgressDegree = (((Integer) animation.getAnimatedValue()).intValue() / 30) * 30;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int iWidth = bounds.width();
        int iHeight = bounds.height();
        float f = iWidth;
        float fMax = Math.max(1.0f, f / 22.0f);
        if (this.mWidth != iWidth || this.mHeight != iHeight) {
            this.mPath.reset();
            float f2 = f - fMax;
            float f3 = iHeight / 2.0f;
            this.mPath.addCircle(f2, f3, fMax, Path.Direction.CW);
            float f4 = f - (5.0f * fMax);
            this.mPath.addRect(f4, f3 - fMax, f2, f3 + fMax, Path.Direction.CW);
            this.mPath.addCircle(f4, f3, fMax, Path.Direction.CW);
            this.mWidth = iWidth;
            this.mHeight = iHeight;
        }
        canvas.save();
        float f5 = f / 2.0f;
        float f6 = iHeight / 2.0f;
        canvas.rotate(this.mProgressDegree, f5, f6);
        for (int i = 0; i < 12; i++) {
            this.mPaint.setAlpha((i + 5) * 17);
            canvas.rotate(30.0f, f5, f6);
            canvas.drawPath(this.mPath, this.mPaint);
        }
        canvas.restore();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        if (this.mValueAnimator.isRunning()) {
            return;
        }
        this.mValueAnimator.addUpdateListener(this);
        this.mValueAnimator.start();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        if (this.mValueAnimator.isRunning()) {
            this.mValueAnimator.removeAllListeners();
            this.mValueAnimator.removeAllUpdateListeners();
            this.mValueAnimator.cancel();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.mValueAnimator.isRunning();
    }
}
