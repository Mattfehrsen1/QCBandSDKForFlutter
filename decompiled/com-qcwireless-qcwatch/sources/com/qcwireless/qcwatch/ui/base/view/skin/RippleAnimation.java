package com.qcwireless.qcwatch.ui.base.view.skin;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes3.dex */
public class RippleAnimation extends View {
    private boolean isStarted;
    private Animator.AnimatorListener mAnimatorListener;
    private ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener;
    private Bitmap mBackground;
    private int mCurrentRadius;
    private long mDuration;
    private int mMaxRadius;
    private OnAnimationEndListener mOnAnimationEndListener;
    private Paint mPaint;
    private ViewGroup mRootView;
    private int mStartRadius;
    private float mStartX;
    private float mStartY;

    public interface OnAnimationEndListener {
        void onAnimationEnd();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    public static RippleAnimation create(View onClickView) {
        Context context = onClickView.getContext();
        int width = onClickView.getWidth() / 2;
        int height = onClickView.getHeight() / 2;
        onClickView.getLocationOnScreen(new int[2]);
        return new RippleAnimation(context, getAbsoluteX(onClickView) + width, r2[1] + height, Math.max(width, height));
    }

    private RippleAnimation(Context context, float startX, float startY, int radius) {
        super(context);
        this.mRootView = (ViewGroup) getActivityFromContext(context).getWindow().getDecorView();
        this.mStartX = startX;
        this.mStartY = startY;
        this.mStartRadius = radius;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        updateMaxRadius();
        initListener();
    }

    private Activity getActivityFromContext(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        throw new RuntimeException("Activity not found!");
    }

    public void start(int delay) {
        if (this.isStarted) {
            return;
        }
        this.isStarted = true;
        updateBackground();
        attachToRootView();
        ValueAnimator animator = getAnimator();
        animator.setStartDelay(delay);
        animator.start();
    }

    public RippleAnimation setDuration(long duration) {
        this.mDuration = duration;
        return this;
    }

    private void initListener() {
        this.mAnimatorListener = new AnimatorListenerAdapter() { // from class: com.qcwireless.qcwatch.ui.base.view.skin.RippleAnimation.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                if (RippleAnimation.this.mOnAnimationEndListener != null) {
                    RippleAnimation.this.mOnAnimationEndListener.onAnimationEnd();
                }
                RippleAnimation.this.isStarted = false;
                RippleAnimation.this.detachFromRootView();
            }
        };
        this.mAnimatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.qcwireless.qcwatch.ui.base.view.skin.RippleAnimation.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                RippleAnimation.this.mCurrentRadius = ((int) ((Float) animation.getAnimatedValue()).floatValue()) + RippleAnimation.this.mStartRadius;
                RippleAnimation.this.postInvalidate();
            }
        };
    }

    private void updateMaxRadius() {
        float f = this.mStartX;
        int i = this.mStartRadius;
        RectF rectF = new RectF(0.0f, 0.0f, f + i, this.mStartY + i);
        RectF rectF2 = new RectF(rectF.right, 0.0f, this.mRootView.getRight(), rectF.bottom);
        RectF rectF3 = new RectF(0.0f, rectF.bottom, rectF.right, this.mRootView.getBottom());
        RectF rectF4 = new RectF(rectF3.right, rectF.bottom, this.mRootView.getRight(), rectF3.bottom);
        this.mMaxRadius = (int) Math.max(Math.max(Math.sqrt(Math.pow(rectF.width(), 2.0d) + Math.pow(rectF.height(), 2.0d)), Math.sqrt(Math.pow(rectF2.width(), 2.0d) + Math.pow(rectF2.height(), 2.0d))), Math.max(Math.sqrt(Math.pow(rectF3.width(), 2.0d) + Math.pow(rectF3.height(), 2.0d)), Math.sqrt(Math.pow(rectF4.width(), 2.0d) + Math.pow(rectF4.height(), 2.0d))));
    }

    private void attachToRootView() {
        setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.mRootView.addView(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void detachFromRootView() {
        ViewGroup viewGroup = this.mRootView;
        if (viewGroup != null) {
            viewGroup.removeView(this);
            this.mRootView = null;
        }
        Bitmap bitmap = this.mBackground;
        if (bitmap != null) {
            if (!bitmap.isRecycled()) {
                this.mBackground.recycle();
            }
            this.mBackground = null;
        }
        if (this.mPaint != null) {
            this.mPaint = null;
        }
    }

    private void updateBackground() {
        Bitmap bitmap = this.mBackground;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.mBackground.recycle();
        }
        this.mRootView.setDrawingCacheEnabled(true);
        Bitmap drawingCache = this.mRootView.getDrawingCache();
        this.mBackground = drawingCache;
        this.mBackground = Bitmap.createBitmap(drawingCache);
        this.mRootView.setDrawingCacheEnabled(false);
    }

    private static float getAbsoluteX(View view) {
        float x = view.getX();
        Object parent = view.getParent();
        return (parent == null || !(parent instanceof View)) ? x : x + getAbsoluteX((View) parent);
    }

    private static float getAbsoluteY(View view) {
        float y = view.getY();
        Object parent = view.getParent();
        return (parent == null || !(parent instanceof View)) ? y : y + getAbsoluteY((View) parent);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int iSaveLayer;
        if (Build.VERSION.SDK_INT >= 21) {
            iSaveLayer = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), null);
        } else {
            iSaveLayer = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), null, 31);
        }
        canvas.drawBitmap(this.mBackground, 0.0f, 0.0f, (Paint) null);
        canvas.drawCircle(this.mStartX, this.mStartY, this.mCurrentRadius, this.mPaint);
        canvas.restoreToCount(iSaveLayer);
    }

    private ValueAnimator getAnimator() {
        ValueAnimator duration = ValueAnimator.ofFloat(0.0f, this.mMaxRadius).setDuration(this.mDuration);
        duration.addUpdateListener(this.mAnimatorUpdateListener);
        duration.addListener(this.mAnimatorListener);
        return duration;
    }

    public RippleAnimation setOnAnimationEndListener(OnAnimationEndListener listener) {
        this.mOnAnimationEndListener = listener;
        return this;
    }
}
