package com.qcwireless.qcwatch.ui.base.view.ucenter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;
import com.qcwireless.qcwatch.R;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes3.dex */
public class MoneySelectRuleView extends View {
    private final int MAX_FLING_VELOCITY;
    private final int MIN_FLING_VELOCITY;
    private final int TOUCH_SLOP;
    private float balanceGap;
    private String balanceText;
    private float balanceTextSize;
    private int balanceValue;
    private int bgColor;
    private int currentValue;
    private int gradationColor;
    private float gradationHeight;
    private float gradationLongLen;
    private float gradationLongWidth;
    private float gradationShortLen;
    private float gradationShortWidth;
    private int gradationTextColor;
    private float gradationTextSize;
    private float gradationValueGap;
    private int indicatorColor;
    private float mCurrentDistance;
    private int mDownX;
    private int mDownY;
    private int mHalfWidth;
    private int mHeight;
    private boolean mIsMoving;
    private int mLastX;
    private int mLastY;
    private OnValueChangedListener mListener;
    private Paint mPaint;
    private int mRangeDistance;
    private Scroller mScroller;
    private TextPaint mTextPaint;
    private VelocityTracker mVelocityTracker;
    private int mWidth;
    private int mWidthRangeValue;
    private int maxValue;
    private float unitGap;
    private int valuePerCount;
    private int valueUnit;

    public interface OnValueChangedListener {
        void onValueChanged(int newValue);
    }

    public MoneySelectRuleView(Context context) {
        this(context, null);
    }

    public MoneySelectRuleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoneySelectRuleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.balanceText = "";
        initAttrs(context, attrs);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.TOUCH_SLOP = viewConfiguration.getScaledTouchSlop();
        this.MIN_FLING_VELOCITY = viewConfiguration.getScaledMinimumFlingVelocity();
        this.MAX_FLING_VELOCITY = viewConfiguration.getScaledMaximumFlingVelocity();
        calculateValues();
        init(context);
        setNestedScrollingEnabled(false);
    }

    private void calculateValues() {
        float f = this.currentValue;
        int i = this.valueUnit;
        float f2 = this.unitGap;
        this.mCurrentDistance = (f / i) * f2;
        this.mRangeDistance = (int) ((this.maxValue / i) * f2);
        this.mWidthRangeValue = (int) ((this.mWidth / f2) * i);
    }

    private void init(Context context) {
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setColor(this.gradationColor);
        TextPaint textPaint = new TextPaint(1);
        this.mTextPaint = textPaint;
        textPaint.setTextSize(this.balanceTextSize);
        this.mTextPaint.setColor(this.gradationTextColor);
        this.mScroller = new Scroller(context);
        this.mVelocityTracker = VelocityTracker.obtain();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.MoneySelectRuleView);
        this.bgColor = typedArrayObtainStyledAttributes.getColor(16, SkinCompatResources.getColor(context, R.color.color_F9F9F9));
        this.gradationColor = typedArrayObtainStyledAttributes.getColor(17, SkinCompatResources.getColor(context, R.color.q_view_temperature_5));
        this.gradationHeight = typedArrayObtainStyledAttributes.getDimension(5, dp2px(20.0f));
        float dimension = typedArrayObtainStyledAttributes.getDimension(8, dp2px(15.0f));
        this.gradationShortLen = dimension;
        this.gradationLongLen = typedArrayObtainStyledAttributes.getDimension(6, dimension * 2.0f);
        float dimension2 = typedArrayObtainStyledAttributes.getDimension(9, dp2px(1.2f));
        this.gradationShortWidth = dimension2;
        this.gradationLongWidth = typedArrayObtainStyledAttributes.getDimension(7, dimension2);
        this.gradationValueGap = typedArrayObtainStyledAttributes.getDimension(11, dp2px(10.0f));
        this.gradationTextSize = typedArrayObtainStyledAttributes.getDimension(10, sp2px(12.0f));
        this.gradationTextColor = typedArrayObtainStyledAttributes.getColor(19, -7829368);
        this.indicatorColor = typedArrayObtainStyledAttributes.getColor(18, SkinCompatResources.getColor(context, R.color.color_FF6A00));
        this.balanceTextSize = typedArrayObtainStyledAttributes.getDimension(2, sp2px(10.0f));
        this.unitGap = typedArrayObtainStyledAttributes.getDimension(13, dp2px(6.0f));
        this.balanceText = typedArrayObtainStyledAttributes.getString(1);
        this.balanceGap = typedArrayObtainStyledAttributes.getDimension(0, dp2px(4.0f));
        this.maxValue = typedArrayObtainStyledAttributes.getInt(12, 50000);
        this.currentValue = typedArrayObtainStyledAttributes.getInt(4, 0);
        this.balanceValue = typedArrayObtainStyledAttributes.getInt(3, 0);
        this.valueUnit = typedArrayObtainStyledAttributes.getInt(15, 100);
        this.valuePerCount = typedArrayObtainStyledAttributes.getInt(14, 10);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        this.mWidth = size;
        this.mHalfWidth = size >> 1;
        int mode = View.MeasureSpec.getMode(heightMeasureSpec);
        this.mHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        if (mode == Integer.MIN_VALUE) {
            this.mHeight = dp2px(80.0f);
            this.gradationHeight = dp2px(60.0f);
        }
        int i = this.mWidth;
        this.mWidthRangeValue = (int) ((i / this.unitGap) * this.valueUnit);
        setMeasuredDimension(i, this.mHeight);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(event);
        logD("onTouchEvent: action=%d", Integer.valueOf(action));
        if (action == 0) {
            this.mIsMoving = false;
            this.mDownX = x;
            this.mDownY = y;
            if (!this.mScroller.isFinished()) {
                this.mScroller.forceFinished(true);
            }
        } else if (action != 1) {
            if (action == 2) {
                int i = x - this.mLastX;
                if (!this.mIsMoving) {
                    int i2 = y - this.mLastY;
                    if (Math.abs(x - this.mDownX) > this.TOUCH_SLOP && Math.abs(i) >= Math.abs(i2)) {
                        this.mIsMoving = true;
                        this.mCurrentDistance -= i;
                        computeValue();
                    }
                } else {
                    this.mCurrentDistance -= i;
                    computeValue();
                }
            } else if (action == 3 && !this.mScroller.isFinished()) {
                this.mScroller.abortAnimation();
            }
        } else if (this.mIsMoving) {
            this.mVelocityTracker.computeCurrentVelocity(1000, this.MAX_FLING_VELOCITY);
            int xVelocity = (int) this.mVelocityTracker.getXVelocity();
            logD("up: xVelocity=%d", Integer.valueOf(xVelocity));
            if (Math.abs(xVelocity) < this.MIN_FLING_VELOCITY) {
                scrollToGradation();
            } else {
                this.mScroller.fling((int) this.mCurrentDistance, 0, -xVelocity, 0, 0, this.mRangeDistance, 0, 0);
                invalidate();
            }
        }
        this.mLastX = x;
        this.mLastY = y;
        return true;
    }

    private void scrollToGradation() {
        int iRound = Math.round(this.mCurrentDistance / this.unitGap) * this.valueUnit;
        this.currentValue = iRound;
        int iMin = Math.min(this.maxValue, Math.max(0, iRound));
        this.currentValue = iMin;
        this.mCurrentDistance = (iMin / this.valueUnit) * this.unitGap;
        logD("scrollToGradation: currentValue=%d, mCurrentDistance=%f", Integer.valueOf(iMin), Float.valueOf(this.mCurrentDistance));
        OnValueChangedListener onValueChangedListener = this.mListener;
        if (onValueChangedListener != null) {
            onValueChangedListener.onValueChanged(this.currentValue);
        }
        invalidate();
    }

    private void computeValue() {
        logD("computeValue: mRangeDistance=%d, mCurrentDistance=%f", Integer.valueOf(this.mRangeDistance), Float.valueOf(this.mCurrentDistance));
        float fMin = Math.min(this.mRangeDistance, Math.max(0.0f, this.mCurrentDistance));
        this.mCurrentDistance = fMin;
        int i = ((int) (fMin / this.unitGap)) * this.valueUnit;
        this.currentValue = i;
        OnValueChangedListener onValueChangedListener = this.mListener;
        if (onValueChangedListener != null) {
            onValueChangedListener.onValueChanged(i);
        }
        postInvalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(this.bgColor);
        drawRule(canvas);
        drawIndicator(canvas);
    }

    private void drawRule(Canvas canvas) {
        canvas.save();
        canvas.translate(0.0f, this.gradationHeight);
        int i = this.valueUnit;
        int i2 = i * 3;
        int iMax = Math.max(0, (((int) ((this.mCurrentDistance - this.mHalfWidth) / this.unitGap)) * i) - i2);
        int iMin = Math.min(this.maxValue, iMax + i2 + this.mWidthRangeValue + i2);
        float f = this.mHalfWidth;
        float f2 = this.mCurrentDistance;
        int i3 = this.valueUnit;
        float f3 = f - (f2 - ((iMax / i3) * this.unitGap));
        int i4 = this.valuePerCount * i3;
        int i5 = this.balanceValue / i3;
        logD("drawRule: mCurrentDistance=%f, start=%d, end=%d, startOffset=%f, perCount=%d", Float.valueOf(f2), Integer.valueOf(iMax), Integer.valueOf(iMin), Float.valueOf(f3), Integer.valueOf(i4));
        while (iMax <= iMin) {
            if (iMax % i4 == 0) {
                this.mPaint.setStrokeWidth(this.gradationLongWidth);
                canvas.drawLine(f3, 0.0f, f3, -this.gradationLongLen, this.mPaint);
                this.mTextPaint.setTextSize(this.gradationTextSize);
                this.mTextPaint.setColor(this.gradationTextColor);
                String string = Integer.toString(iMax);
                canvas.drawText(string, f3 - (this.mTextPaint.measureText(string) * 0.5f), -(this.gradationLongLen + this.gradationValueGap), this.mTextPaint);
            } else {
                this.mPaint.setStrokeWidth(this.gradationShortWidth);
                canvas.drawLine(f3, 0.0f, f3, -this.gradationShortLen, this.mPaint);
            }
            iMax += this.valueUnit;
            f3 += this.unitGap;
        }
        canvas.restore();
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            if (this.mScroller.getCurrX() == this.mScroller.getFinalX()) {
                scrollToGradation();
            } else {
                this.mCurrentDistance = this.mScroller.getCurrX();
                computeValue();
            }
        }
    }

    private void drawIndicator(Canvas canvas) {
        this.mPaint.setColor(this.indicatorColor);
        this.mPaint.setStrokeWidth(dp2px(2.0f));
        canvas.drawLine(this.mHalfWidth, dp2px(30.0f), this.mHalfWidth, this.gradationHeight + dp2px(10.0f), this.mPaint);
        canvas.drawCircle(this.mHalfWidth, (this.gradationHeight + dp2px(10.0f)) - dp2px(1.5f), dp2px(3.0f), this.mPaint);
        this.mPaint.setColor(this.gradationColor);
    }

    private int dp2px(float dp) {
        return (int) TypedValue.applyDimension(1, dp, getResources().getDisplayMetrics());
    }

    private int sp2px(float sp) {
        return (int) TypedValue.applyDimension(2, sp, getResources().getDisplayMetrics());
    }

    private void logD(String format, Object... args) {
        Log.d("MoneySelectRuleView", String.format("zjun@" + format, args));
    }

    public int getValue() {
        return this.currentValue;
    }

    public void setValue(float value) {
        int i = this.valueUnit;
        int i2 = (((int) value) / i) * i;
        this.currentValue = i2;
        this.currentValue = Math.min(this.maxValue, Math.max(0, i2));
        if (!this.mScroller.isFinished()) {
            this.mScroller.forceFinished(true);
        }
        OnValueChangedListener onValueChangedListener = this.mListener;
        if (onValueChangedListener != null) {
            onValueChangedListener.onValueChanged(this.currentValue);
        }
        calculateValues();
        postInvalidate();
    }

    public int getBalance() {
        return this.balanceValue;
    }

    public void setBalance(float balance) {
        int i = this.valueUnit;
        this.balanceValue = (((int) balance) / i) * i;
        postInvalidate();
    }

    public void setOnValueChangedListener(OnValueChangedListener listener) {
        this.mListener = listener;
    }
}
