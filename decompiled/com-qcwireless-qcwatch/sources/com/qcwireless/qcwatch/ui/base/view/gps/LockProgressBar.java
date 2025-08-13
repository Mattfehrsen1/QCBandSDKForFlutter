package com.qcwireless.qcwatch.ui.base.view.gps;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes3.dex */
public class LockProgressBar extends View {
    private onAnimEnd animListener;
    private boolean isCut;
    private boolean isRunning;
    private int mColor;
    private Paint mPaint;
    private float pregss;
    private RectF rectF;
    private int roundColor;
    private Paint roundPaint;
    private Thread thread;

    public interface onAnimEnd {
        void animEndListener(boolean isLock);
    }

    static /* synthetic */ float access$216(LockProgressBar lockProgressBar, float f) {
        float f2 = lockProgressBar.pregss + f;
        lockProgressBar.pregss = f2;
        return f2;
    }

    static /* synthetic */ float access$224(LockProgressBar lockProgressBar, float f) {
        float f2 = lockProgressBar.pregss - f;
        lockProgressBar.pregss = f2;
        return f2;
    }

    public LockProgressBar(Context context) {
        super(context);
        this.isRunning = false;
        initPaint();
    }

    public LockProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.isRunning = false;
        initPaint();
    }

    public LockProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.isRunning = false;
        initPaint();
    }

    public LockProgressBar(Context context, int color, int color2) {
        super(context);
        this.isRunning = false;
        this.mColor = color;
        this.roundColor = color2;
        initPaint();
    }

    private void initPaint() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(this.mColor);
        this.mPaint.setStrokeWidth(10.0f);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        Paint paint2 = new Paint();
        this.roundPaint = paint2;
        paint2.setAntiAlias(true);
        this.roundPaint.setStyle(Paint.Style.STROKE);
        this.roundPaint.setColor(this.roundColor);
        this.roundPaint.setStrokeWidth(7.0f);
        this.roundPaint.setStrokeCap(Paint.Cap.ROUND);
        this.rectF = new RectF();
        this.pregss = 0.0f;
        this.isCut = false;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        RectF rectF = this.rectF;
        if (rectF != null) {
            rectF.left = 10.0f;
            this.rectF.top = 10.0f;
            this.rectF.right = getMeasuredWidth() - 10;
            this.rectF.bottom = getMeasuredHeight() - 10;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = this.rectF;
        if (rectF == null || rectF.right <= 0.0f) {
            return;
        }
        canvas.drawArc(this.rectF, 360.0f, 360.0f, false, this.roundPaint);
        canvas.drawArc(this.rectF, -90.0f, this.pregss, false, this.mPaint);
    }

    public void startAnim() {
        setVisibility(0);
        this.isRunning = true;
        this.isCut = false;
        this.pregss = 0.0f;
        Thread thread = this.thread;
        if (thread == null || !thread.isAlive()) {
            Thread thread2 = new Thread(new Runnable() { // from class: com.qcwireless.qcwatch.ui.base.view.gps.LockProgressBar.1
                @Override // java.lang.Runnable
                public void run() throws InterruptedException {
                    while (LockProgressBar.this.isRunning) {
                        try {
                            if (LockProgressBar.this.isCut) {
                                LockProgressBar.access$224(LockProgressBar.this, 10.8f);
                            } else {
                                LockProgressBar.access$216(LockProgressBar.this, 10.8f);
                            }
                            if (LockProgressBar.this.pregss <= 0.0f) {
                                LockProgressBar.this.isCut = false;
                                LockProgressBar.this.pregss = 0.0f;
                                LockProgressBar.this.isRunning = false;
                                LockProgressBar.this.animListener.animEndListener(false);
                            }
                            if (LockProgressBar.this.pregss >= 360.0f) {
                                LockProgressBar.this.isCut = true;
                                LockProgressBar.this.pregss = 360.0f;
                                LockProgressBar.this.isRunning = false;
                                LockProgressBar.this.animListener.animEndListener(true);
                            }
                            LockProgressBar.this.postInvalidate();
                            Thread.sleep(30L);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                }
            });
            this.thread = thread2;
            thread2.start();
        }
    }

    public void setCut(boolean isCut) {
        this.isCut = isCut;
    }

    public void setAnimListener(onAnimEnd animListener) {
        this.animListener = animListener;
    }

    public void setPaintColor(int color1, int color2) {
        this.mColor = color1;
        this.roundColor = color2;
        Paint paint = this.mPaint;
        if (paint != null) {
            paint.setColor(color1);
            this.roundPaint.setColor(this.roundColor);
        }
    }
}
