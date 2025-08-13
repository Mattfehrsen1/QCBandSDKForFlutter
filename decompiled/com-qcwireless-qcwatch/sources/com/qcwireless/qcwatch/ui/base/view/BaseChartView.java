package com.qcwireless.qcwatch.ui.base.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.core.view.ViewCompat;
import com.blankj.utilcode.constant.CacheConstants;
import com.qcwireless.qcwatch.R;
import java.text.DecimalFormat;
import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

/* loaded from: classes3.dex */
public class BaseChartView extends View implements SkinCompatSupportable {
    protected ValueAnimator animatorFingerIn;
    protected ValueAnimator animatorFingerOut;
    protected Paint avgLinePaint;
    protected Paint ballPaint;
    protected float bezierHeight;
    protected Paint bezierPaint;
    protected Path bezierPath;
    private int bgColorId;
    protected int bottomOffset;
    protected float circleRadius;
    protected float circleRadiusMax;
    protected float circleRadiusMin;
    protected int clickX;
    protected int colorBall;
    protected int colorBgSelected;
    protected int colorLine;
    protected int colorValue;
    protected int colorValueSelected;
    protected Context context;
    private int dataLineColorId;
    protected DecimalFormat decimalFormat;
    protected int diameterDefault;
    protected float fingerX;
    protected float fingerXDefault;
    protected float fingerXMax;
    protected float fingerXmin;
    protected int height;
    private int lineColorId;
    protected int lineHeight;
    protected Paint linePaint;
    protected int mPaddingBottom;
    protected int mPaddingEnd;
    protected int mPaddingStart;
    protected int mPaddingTop;
    protected int offset;
    Path path;
    Path path1;
    protected int radius;
    protected float spaceToLine;
    protected Paint stepPaint;
    protected int textDownColor;
    private int textDownColorId;
    protected Paint textDownPaint;
    protected Paint textPaint;
    protected float textSelectedSize;
    protected float textSize;
    protected Paint txtSelectedBgPaint;
    protected int valueMax;
    protected int valueMin;
    protected int valueSelected;
    protected int width;

    public BaseChartView(Context context) {
        super(context);
        this.diameterDefault = 30;
        this.bezierHeight = 10.0f;
        this.circleRadiusMin = 8.0f;
        this.circleRadiusMax = 1.5f * 8.0f;
        this.circleRadius = 8.0f;
        this.spaceToLine = 10.0f;
        this.fingerXmin = 8.0f * 2.0f;
        this.textSelectedSize = 14.0f;
        this.textSize = 12.0f;
        this.offset = 80;
        this.bottomOffset = 50;
        this.radius = 24;
        this.path = new Path();
        this.path1 = new Path();
        initBase(context);
        initPublic(context, null);
    }

    public BaseChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.diameterDefault = 30;
        this.bezierHeight = 10.0f;
        this.circleRadiusMin = 8.0f;
        this.circleRadiusMax = 1.5f * 8.0f;
        this.circleRadius = 8.0f;
        this.spaceToLine = 10.0f;
        this.fingerXmin = 8.0f * 2.0f;
        this.textSelectedSize = 14.0f;
        this.textSize = 12.0f;
        this.offset = 80;
        this.bottomOffset = 50;
        this.radius = 24;
        this.path = new Path();
        this.path1 = new Path();
        initBase(context);
        initPublic(context, attrs);
    }

    public BaseChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.diameterDefault = 30;
        this.bezierHeight = 10.0f;
        this.circleRadiusMin = 8.0f;
        this.circleRadiusMax = 1.5f * 8.0f;
        this.circleRadius = 8.0f;
        this.spaceToLine = 10.0f;
        this.fingerXmin = 8.0f * 2.0f;
        this.textSelectedSize = 14.0f;
        this.textSize = 12.0f;
        this.offset = 80;
        this.bottomOffset = 50;
        this.radius = 24;
        this.path = new Path();
        this.path1 = new Path();
        initBase(context);
        initPublic(context, attrs);
    }

    public BaseChartView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.diameterDefault = 30;
        this.bezierHeight = 10.0f;
        this.circleRadiusMin = 8.0f;
        this.circleRadiusMax = 1.5f * 8.0f;
        this.circleRadius = 8.0f;
        this.spaceToLine = 10.0f;
        this.fingerXmin = 8.0f * 2.0f;
        this.textSelectedSize = 14.0f;
        this.textSize = 12.0f;
        this.offset = 80;
        this.bottomOffset = 50;
        this.radius = 24;
        this.path = new Path();
        this.path1 = new Path();
        initBase(context);
        initPublic(context, attrs);
    }

    private void initBase(Context context) {
        this.context = context;
        this.decimalFormat = new DecimalFormat("#");
        this.textSelectedSize = dp2px(context, 14.0f);
        this.textSize = dp2px(context, 12.0f);
        this.valueMax = CacheConstants.DAY;
        int iDp2px = (int) dp2px(context, 60.0f);
        this.offset = iDp2px;
        this.valueMin = iDp2px;
        this.colorValue = ViewCompat.MEASURED_STATE_MASK;
        this.colorLine = ViewCompat.MEASURED_STATE_MASK;
        this.colorBall = ViewCompat.MEASURED_STATE_MASK;
        this.colorValueSelected = -1;
        this.fingerX = 100.0f;
    }

    public void initPublic(Context context, AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.qc_chart);
        this.textDownColorId = typedArrayObtainStyledAttributes.getResourceId(8, 0);
        this.bgColorId = typedArrayObtainStyledAttributes.getResourceId(3, 0);
        this.lineColorId = typedArrayObtainStyledAttributes.getResourceId(2, 0);
        this.dataLineColorId = typedArrayObtainStyledAttributes.getResourceId(4, 0);
        this.context = context;
        Paint paint = new Paint();
        this.bezierPaint = paint;
        paint.setAntiAlias(true);
        this.bezierPaint.setStyle(Paint.Style.STROKE);
        this.bezierPaint.setColor(this.colorLine);
        this.bezierPaint.setStrokeWidth(2.0f);
        Paint paint2 = new Paint();
        this.stepPaint = paint2;
        paint2.setAntiAlias(true);
        this.stepPaint.setStyle(Paint.Style.STROKE);
        this.stepPaint.setStrokeCap(Paint.Cap.ROUND);
        this.stepPaint.setColor(this.colorLine);
        this.stepPaint.setStrokeWidth(8.0f);
        Paint paint3 = new Paint();
        this.textPaint = paint3;
        paint3.setAntiAlias(true);
        this.textPaint.setStyle(Paint.Style.FILL);
        this.textPaint.setColor(this.colorValue);
        this.textPaint.setStrokeWidth(2.0f);
        this.textPaint.setTypeface(Typeface.create(Typeface.SANS_SERIF, 0));
        this.textPaint.setTextSize(this.textSelectedSize);
        Paint paint4 = new Paint();
        this.avgLinePaint = paint4;
        paint4.setAntiAlias(true);
        this.avgLinePaint.setStyle(Paint.Style.FILL);
        this.avgLinePaint.setColor(this.textDownColor);
        this.avgLinePaint.setStrokeWidth(1.5f);
        this.avgLinePaint.setTextSize(this.textSelectedSize);
        Paint paint5 = new Paint();
        this.linePaint = paint5;
        paint5.setAntiAlias(true);
        this.linePaint.setStyle(Paint.Style.FILL);
        this.linePaint.setColor(this.colorValue);
        this.linePaint.setStyle(Paint.Style.STROKE);
        this.linePaint.setStrokeWidth(2.0f);
        this.linePaint.setTextSize(this.textSelectedSize);
        Paint paint6 = new Paint();
        this.txtSelectedBgPaint = paint6;
        paint6.setAntiAlias(true);
        this.txtSelectedBgPaint.setColor(this.colorBgSelected);
        this.txtSelectedBgPaint.setStyle(Paint.Style.FILL);
        Paint paint7 = new Paint();
        this.textDownPaint = paint7;
        paint7.setAntiAlias(true);
        this.textDownPaint.setStyle(Paint.Style.FILL);
        this.textDownPaint.setColor(this.colorValue);
        this.textDownPaint.setStrokeWidth(2.0f);
        this.textDownPaint.setTextSize(dp2px(context, 10.0f));
        Paint paint8 = new Paint();
        this.ballPaint = paint8;
        paint8.setAntiAlias(true);
        this.ballPaint.setStyle(Paint.Style.FILL);
        this.ballPaint.setColor(this.colorBall);
        Path path = new Path();
        this.bezierPath = path;
        this.fingerXDefault = 200.0f;
        path.moveTo(this.fingerX, 100.0f);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.animatorFingerIn = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(200L);
        this.animatorFingerIn.setInterpolator(new LinearInterpolator());
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(1.0f, 0.0f);
        this.animatorFingerOut = valueAnimatorOfFloat2;
        valueAnimatorOfFloat2.setDuration(200L);
        this.animatorFingerOut.setInterpolator(new LinearInterpolator());
        this.animatorFingerIn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.qcwireless.qcwatch.ui.base.view.BaseChartView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                float fFloatValue = ((Float) animation.getAnimatedValue()).floatValue();
                BaseChartView.this.txtSelectedBgPaint.setAlpha((int) ((fFloatValue - 0.15f) * 255.0f));
                if (fFloatValue >= 0.95f) {
                    BaseChartView.this.textPaint.setColor(BaseChartView.this.colorValueSelected);
                } else {
                    BaseChartView.this.textPaint.setColor(BaseChartView.this.colorValue);
                }
                BaseChartView baseChartView = BaseChartView.this;
                baseChartView.bezierHeight = baseChartView.circleRadiusMax * 1.5f * fFloatValue;
                BaseChartView baseChartView2 = BaseChartView.this;
                baseChartView2.circleRadius = baseChartView2.circleRadiusMin + ((BaseChartView.this.circleRadiusMax - BaseChartView.this.circleRadiusMin) * fFloatValue);
                BaseChartView baseChartView3 = BaseChartView.this;
                baseChartView3.spaceToLine = baseChartView3.circleRadiusMin * 2.0f * (1.0f - fFloatValue);
                BaseChartView.this.postInvalidate();
            }
        });
        this.animatorFingerOut.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.qcwireless.qcwatch.ui.base.view.BaseChartView.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                float fFloatValue = ((Float) animation.getAnimatedValue()).floatValue();
                BaseChartView.this.txtSelectedBgPaint.setAlpha((int) ((fFloatValue - 0.15f) * 255.0f));
                if (fFloatValue >= 0.95f) {
                    BaseChartView.this.textPaint.setColor(BaseChartView.this.colorValueSelected);
                } else {
                    BaseChartView.this.textPaint.setColor(BaseChartView.this.colorValue);
                }
                BaseChartView baseChartView = BaseChartView.this;
                baseChartView.bezierHeight = baseChartView.circleRadiusMax * 1.5f * fFloatValue;
                BaseChartView baseChartView2 = BaseChartView.this;
                baseChartView2.circleRadius = baseChartView2.circleRadiusMin + ((BaseChartView.this.circleRadiusMax - BaseChartView.this.circleRadiusMin) * fFloatValue);
                BaseChartView baseChartView3 = BaseChartView.this;
                baseChartView3.spaceToLine = baseChartView3.circleRadiusMin * 2.0f * (1.0f - fFloatValue);
                BaseChartView.this.postInvalidate();
            }
        });
        this.circleRadius = dp2px(context, 8.0f);
        float fDp2px = dp2px(context, 8.0f);
        this.circleRadiusMin = fDp2px;
        this.circleRadiusMax = fDp2px * 1.2f;
        applyTextColor();
    }

    @Override // android.view.View
    protected void onLayout(boolean changed, int left, int top2, int right, int bottom) {
        super.onLayout(changed, left, top2, right, bottom);
        this.mPaddingStart = getPaddingStart();
        this.mPaddingEnd = getPaddingEnd();
        this.mPaddingTop = getPaddingTop();
        this.mPaddingBottom = getPaddingBottom();
        this.bezierHeight = 0.0f;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = this.circleRadiusMax;
        this.bezierHeight = (f * 1.0f) + 5.0f;
        float f2 = this.circleRadiusMin;
        this.circleRadius = (f - f2) + f2;
        this.spaceToLine = f2 * 2.0f;
        this.bezierPaint.setStyle(Paint.Style.FILL);
        this.path.reset();
        this.path1.reset();
        RectF rectF = new RectF(0.0f, this.lineHeight, this.radius * 2, 0.0f);
        RectF rectF2 = new RectF(r5 - (this.radius * 2), 0.0f, this.width, this.lineHeight);
        Path path = this.path;
        int i = this.radius;
        path.addRoundRect(rectF, new float[]{0.0f, 0.0f, 0.0f, 0.0f, i, i, i, i}, Path.Direction.CW);
        canvas.drawPath(this.path, this.bezierPaint);
        Path path2 = this.path1;
        int i2 = this.radius;
        path2.addRoundRect(rectF2, new float[]{0.0f, 0.0f, 0.0f, 0.0f, i2, i2, i2, i2}, Path.Direction.CW);
        canvas.drawPath(this.path1, this.bezierPaint);
        this.bezierPath.reset();
        this.bezierPath.moveTo(this.radius, 0.0f);
        this.bezierPath.lineTo(this.radius, this.lineHeight);
        this.bezierPath.lineTo(this.fingerX - ((this.circleRadiusMax * 1.1f) * 3.0f), this.lineHeight);
        Path path3 = this.bezierPath;
        float f3 = this.fingerX;
        float f4 = this.circleRadiusMax;
        int i3 = this.lineHeight;
        float f5 = this.bezierHeight;
        path3.cubicTo(f3 - (f4 * 1.1f), i3, f3 - ((f4 * 1.1f) * 1.0f), i3 - f5, f3, i3 - f5);
        this.bezierPath.moveTo(this.fingerX, this.lineHeight - this.bezierHeight);
        Path path4 = this.bezierPath;
        float f6 = this.fingerX;
        float f7 = this.circleRadiusMax;
        int i4 = this.lineHeight;
        path4.cubicTo(f6 + (f7 * 1.1f), i4 - this.bezierHeight, f6 + (f7 * 1.1f), i4, f6 + (f7 * 1.1f * 3.0f), i4);
        this.bezierPath.lineTo(this.width - this.radius, this.lineHeight);
        this.bezierPath.lineTo(this.width - this.radius, 0.0f);
        this.bezierPath.lineTo(this.radius, 0.0f);
        this.bezierPath.close();
        this.bezierPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(this.bezierPath, this.bezierPaint);
        canvas.drawCircle(this.fingerX, this.lineHeight + 5, this.circleRadius, this.ballPaint);
    }

    public int getMySize(int defaultSize, int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.getSize(measureSpec) : defaultSize;
    }

    public float px2dp(Context context, float px) {
        return (px / context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    public float dp2px(Context context, float dp) {
        return (dp * context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    public float getTextWidth(Paint paint, String str) {
        float fCeil = 0.0f;
        if (str != null && str.length() > 0) {
            int length = str.length();
            paint.getTextWidths(str, new float[length]);
            for (int i = 0; i < length; i++) {
                fCeil += (float) Math.ceil(r2[i]);
            }
        }
        return fCeil;
    }

    public float getTextHeight(Paint paint, String str) {
        paint.getTextBounds(str, 0, str.length(), new Rect());
        return r0.height();
    }

    public void setValueMax(int valueMax) {
        this.valueMax = valueMax;
    }

    public void setValueMin(int valueMin) {
        this.valueMin = valueMin;
    }

    public int getValueMax() {
        return this.valueMax;
    }

    public int getValueMin() {
        return this.valueMin;
    }

    public int getColorValue() {
        return this.colorValue;
    }

    public void setColorValue(int colorValue) {
        this.colorValue = colorValue;
        this.textDownPaint.setColor(colorValue);
    }

    public int getColorValueSelected() {
        return this.colorValueSelected;
    }

    public void setColorValueSelected(int colorValueSelected) {
        this.colorValueSelected = colorValueSelected;
        this.textPaint.setColor(this.colorValue);
    }

    public int getColorLine() {
        return this.colorLine;
    }

    public void setColorLine(int colorLine) {
        this.colorLine = colorLine;
        this.bezierPaint.setColor(colorLine);
    }

    public int getColorBall() {
        return this.colorBall;
    }

    public void setColorBgSelected(int colorBgSelected) {
        this.colorBgSelected = colorBgSelected;
        this.txtSelectedBgPaint.setColor(colorBgSelected);
    }

    public void setColorBall(int colorBall) {
        this.colorBall = colorBall;
        this.ballPaint.setColor(colorBall);
    }

    public int getValueSelected() {
        return this.valueSelected;
    }

    private void applyTextColor() {
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.textDownColorId);
        if (iCheckResourceId != 0) {
            this.textDownPaint.setColor(SkinCompatResources.getColor(this.context, iCheckResourceId));
        }
        int iCheckResourceId2 = SkinCompatHelper.checkResourceId(this.bgColorId);
        if (iCheckResourceId2 != 0) {
            int color = SkinCompatResources.getColor(this.context, iCheckResourceId2);
            this.txtSelectedBgPaint.setColor(color);
            this.bezierPaint.setColor(color);
        }
        int iCheckResourceId3 = SkinCompatHelper.checkResourceId(this.lineColorId);
        if (iCheckResourceId3 != 0) {
            this.avgLinePaint.setColor(SkinCompatResources.getColor(this.context, iCheckResourceId3));
        }
        int iCheckResourceId4 = SkinCompatHelper.checkResourceId(this.dataLineColorId);
        if (iCheckResourceId4 != 0) {
            this.stepPaint.setColor(SkinCompatResources.getColor(this.context, iCheckResourceId4));
        }
    }

    public Integer getNumberThree(int[] intarray, Integer number) {
        int iAbs = Math.abs(number.intValue() - intarray[0]);
        int i = intarray[0];
        for (int i2 : intarray) {
            int iAbs2 = Math.abs(number.intValue() - i2);
            if (iAbs2 <= iAbs) {
                i = i2;
                iAbs = iAbs2;
            }
        }
        return Integer.valueOf(i);
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        applyTextColor();
    }

    public class XTextChange {
        public String label;
        public int max;
        public int min;
        public float x;
        public float y;

        public XTextChange() {
        }

        public int getMin() {
            return this.min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return this.max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public String getLabel() {
            return this.label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public float getX() {
            return this.x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return this.y;
        }

        public void setY(float y) {
            this.y = y;
        }
    }
}
