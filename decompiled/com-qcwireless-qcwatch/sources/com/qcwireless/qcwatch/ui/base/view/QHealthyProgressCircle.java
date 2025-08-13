package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.qcwireless.qcwatch.R;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes3.dex */
public class QHealthyProgressCircle extends View {
    private Context context;
    private int height;
    private Paint mCompletedPaint;
    private Paint mUncompletedPaint;
    private int percentage;
    private int radius;
    private int width;

    private void applyTextColor() {
    }

    public QHealthyProgressCircle(Context context) {
        this(context, null);
        this.context = context;
    }

    public QHealthyProgressCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
    }

    public QHealthyProgressCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        Paint paint = new Paint();
        this.mUncompletedPaint = paint;
        paint.setColor(ContextCompat.getColor(this.context, R.color.healthy_module_bg));
        this.mUncompletedPaint.setAntiAlias(true);
        this.mUncompletedPaint.setStrokeWidth(dp2px(this.context, 6.0f));
        this.mUncompletedPaint.setStyle(Paint.Style.STROKE);
        Paint paint2 = new Paint();
        this.mCompletedPaint = paint2;
        paint2.setColor(SkinCompatResources.getColor(this.context, R.color.healthy_module_value_bg));
        this.mCompletedPaint.setAntiAlias(true);
        this.mCompletedPaint.setStrokeWidth(dp2px(this.context, 6.0f));
        this.mCompletedPaint.setStyle(Paint.Style.STROKE);
        this.mCompletedPaint.setStrokeCap(Paint.Cap.ROUND);
        this.percentage = 0;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.width = getMySize(200, widthMeasureSpec);
        if (getLayoutParams().height == -2) {
            this.height = 200;
        } else {
            this.height = getMySize(200, heightMeasureSpec);
        }
        setMeasuredDimension(this.width, this.height);
        this.radius = ((Math.max(this.width, this.height) - 40) - ((int) dp2px(this.context, 6.0f))) / 2;
        postInvalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF();
        float f = (this.percentage * 360) / 100;
        rectF.set(dp2px(this.context, 6.0f) * 2.0f, dp2px(this.context, 6.0f) * 2.0f, (this.radius * 2) + dp2px(this.context, 6.0f), (this.radius * 2) + dp2px(this.context, 6.0f));
        canvas.drawArc(rectF, -90.0f, 360.0f, false, this.mUncompletedPaint);
        canvas.drawArc(rectF, -90.0f, f, false, this.mCompletedPaint);
    }

    public int getPercentage() {
        return this.percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
        postInvalidate();
    }

    public int getMySize(int defaultSize, int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.getSize(measureSpec) : defaultSize;
    }

    public float dp2px(Context context, float dp) {
        return (dp * context.getResources().getDisplayMetrics().density) + 0.5f;
    }
}
