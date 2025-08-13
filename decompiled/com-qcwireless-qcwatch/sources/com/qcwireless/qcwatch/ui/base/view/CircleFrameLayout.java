package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.core.internal.view.SupportMenu;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public class CircleFrameLayout extends FrameLayout {
    private Paint imagePaint;
    private int mBorderColor;
    private float mBorderWidth;
    private float mRadius;
    private Paint roundPaint;

    public CircleFrameLayout(Context context) {
        this(context, null);
    }

    public CircleFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (attrs != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.CircleFrameLayout);
            this.mRadius = typedArrayObtainStyledAttributes.getDimension(2, 0.0f);
            this.mBorderWidth = typedArrayObtainStyledAttributes.getDimension(1, 0.0f);
            this.mBorderColor = typedArrayObtainStyledAttributes.getColor(0, -1);
            typedArrayObtainStyledAttributes.recycle();
        }
        Paint paint = new Paint();
        this.roundPaint = paint;
        paint.setColor(SupportMenu.CATEGORY_MASK);
        this.roundPaint.setAntiAlias(true);
        this.roundPaint.setStyle(Paint.Style.FILL);
        this.roundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        Paint paint2 = new Paint();
        this.imagePaint = paint2;
        paint2.setXfermode(null);
    }

    public void setRadius(int radius) {
        this.mRadius = radius;
        postInvalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(new RectF(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight()), this.imagePaint, 31);
        super.dispatchDraw(canvas);
        drawBorder(canvas);
        drawTopLeft(canvas);
        drawTopRight(canvas);
        drawBottomLeft(canvas);
        drawBottomRight(canvas);
        canvas.restore();
    }

    private void drawBorder(Canvas canvas) {
        if (this.mBorderWidth != 0.0f && getWidth() == getHeight() && this.mRadius == getWidth() / 2) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(this.mBorderWidth);
            paint.setColor(this.mBorderColor);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, paint);
        }
    }

    private void drawTopLeft(Canvas canvas) {
        if (this.mRadius > 0.0f) {
            Path path = new Path();
            path.moveTo(0.0f, this.mRadius);
            path.lineTo(0.0f, 0.0f);
            path.lineTo(this.mRadius, 0.0f);
            float f = this.mRadius;
            path.arcTo(new RectF(0.0f, 0.0f, f * 2.0f, f * 2.0f), -90.0f, -90.0f);
            path.close();
            canvas.drawPath(path, this.roundPaint);
        }
    }

    private void drawTopRight(Canvas canvas) {
        if (this.mRadius > 0.0f) {
            int width = getWidth();
            Path path = new Path();
            float f = width;
            path.moveTo(f - this.mRadius, 0.0f);
            path.lineTo(f, 0.0f);
            path.lineTo(f, this.mRadius);
            float f2 = this.mRadius;
            path.arcTo(new RectF(f - (f2 * 2.0f), 0.0f, f, f2 * 2.0f), 0.0f, -90.0f);
            path.close();
            canvas.drawPath(path, this.roundPaint);
        }
    }

    private void drawBottomLeft(Canvas canvas) {
        if (this.mRadius > 0.0f) {
            int height = getHeight();
            Path path = new Path();
            float f = height;
            path.moveTo(0.0f, f - this.mRadius);
            path.lineTo(0.0f, f);
            path.lineTo(this.mRadius, f);
            float f2 = this.mRadius;
            path.arcTo(new RectF(0.0f, f - (f2 * 2.0f), f2 * 2.0f, f), 90.0f, 90.0f);
            path.close();
            canvas.drawPath(path, this.roundPaint);
        }
    }

    private void drawBottomRight(Canvas canvas) {
        if (this.mRadius > 0.0f) {
            int height = getHeight();
            int width = getWidth();
            Path path = new Path();
            float f = width;
            float f2 = height;
            path.moveTo(f - this.mRadius, f2);
            path.lineTo(f, f2);
            path.lineTo(f, f2 - this.mRadius);
            float f3 = this.mRadius;
            path.arcTo(new RectF(f - (f3 * 2.0f), f2 - (f3 * 2.0f), f, f2), 0.0f, 90.0f);
            path.close();
            canvas.drawPath(path, this.roundPaint);
        }
    }
}
