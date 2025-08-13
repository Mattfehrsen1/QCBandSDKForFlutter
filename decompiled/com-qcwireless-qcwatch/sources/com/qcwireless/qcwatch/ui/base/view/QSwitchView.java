package com.qcwireless.qcwatch.ui.base.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Property;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.qcwireless.qcwatch.R;
import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

/* loaded from: classes3.dex */
public class QSwitchView extends View implements SkinCompatSupportable {
    private static final long commonDuration = 300;
    private static final int intrinsicHeight = 0;
    private static final int intrinsicWidth = 0;
    private int backgroundColor;
    private int bgColorId;
    private int centerX;
    private int centerY;
    private int colorStep;
    private Context context;
    private float cornerRadius;
    private boolean dirtyAnimation;
    private int foregroundColor;
    private GestureDetector gestureDetector;
    private GestureDetector.SimpleOnGestureListener gestureListener;
    private int height;
    private ObjectAnimator innerContentAnimator;
    private RectF innerContentBound;
    private Property<QSwitchView, Float> innerContentProperty;
    private float innerContentRate;
    private float intrinsicInnerHeight;
    private float intrinsicInnerWidth;
    private float intrinsicKnobWidth;
    private boolean isAttachedToWindow;
    private boolean isChange;
    private boolean isOn;
    private RectF knobBound;
    private ObjectAnimator knobExpandAnimator;
    private Property<QSwitchView, Float> knobExpandProperty;
    private float knobExpandRate;
    private float knobMaxExpandWidth;
    private ObjectAnimator knobMoveAnimator;
    private Property<QSwitchView, Float> knobMoveProperty;
    private float knobMoveRate;
    private boolean knobState;
    private OnSwitchStateChangeListener onSwitchStateChangeListener;
    private int outerStrokeWidth;
    private RectF ovalForPath;
    private Paint paint;
    private boolean preIsOn;
    private Path roundRectPath;
    private Drawable shadowDrawable;
    private int shadowSpace;
    private RectF tempForRoundRect;
    private int tempTintColor;
    private int tintColor;
    private int tintColorId;
    private int width;

    public interface OnSwitchStateChangeListener {
        void onSwitchStateChange(boolean isOn);
    }

    private int RGBColorTransform(float progress, int fromColor, int toColor) {
        return ((((fromColor >> 16) & 255) + ((int) ((((toColor >> 16) & 255) - r0) * progress))) << 16) | ViewCompat.MEASURED_STATE_MASK | ((((fromColor >> 8) & 255) + ((int) ((((toColor >> 8) & 255) - r1) * progress))) << 8) | ((fromColor & 255) + ((int) (((toColor & 255) - r6) * progress)));
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        applyBackgroundColor();
        postInvalidate();
    }

    public QSwitchView(Context context) {
        this(context, null);
        this.context = context;
    }

    public QSwitchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
    }

    public QSwitchView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.innerContentProperty = new Property<QSwitchView, Float>(Float.class, "innerBound") { // from class: com.qcwireless.qcwatch.ui.base.view.QSwitchView.1
            @Override // android.util.Property
            public void set(QSwitchView sv, Float innerContentRate) {
                sv.setInnerContentRate(innerContentRate.floatValue());
            }

            @Override // android.util.Property
            public Float get(QSwitchView sv) {
                return Float.valueOf(sv.getInnerContentRate());
            }
        };
        this.knobExpandProperty = new Property<QSwitchView, Float>(Float.class, "knobExpand") { // from class: com.qcwireless.qcwatch.ui.base.view.QSwitchView.2
            @Override // android.util.Property
            public void set(QSwitchView sv, Float knobExpandRate) {
                sv.setKnobExpandRate(knobExpandRate.floatValue());
            }

            @Override // android.util.Property
            public Float get(QSwitchView sv) {
                return Float.valueOf(sv.getKnobExpandRate());
            }
        };
        this.knobMoveProperty = new Property<QSwitchView, Float>(Float.class, "knobMove") { // from class: com.qcwireless.qcwatch.ui.base.view.QSwitchView.3
            @Override // android.util.Property
            public void set(QSwitchView sv, Float knobMoveRate) {
                sv.setKnobMoveRate(knobMoveRate.floatValue());
            }

            @Override // android.util.Property
            public Float get(QSwitchView sv) {
                return Float.valueOf(sv.getKnobMoveRate());
            }
        };
        this.gestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.qcwireless.qcwatch.ui.base.view.QSwitchView.4
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onShowPress(MotionEvent event) {
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent event) {
                if (!QSwitchView.this.isEnabled()) {
                    return false;
                }
                QSwitchView qSwitchView = QSwitchView.this;
                qSwitchView.preIsOn = qSwitchView.isOn;
                QSwitchView.this.innerContentAnimator.setFloatValues(QSwitchView.this.innerContentRate, 0.0f);
                QSwitchView.this.innerContentAnimator.start();
                QSwitchView.this.knobExpandAnimator.setFloatValues(QSwitchView.this.knobExpandRate, 1.0f);
                QSwitchView.this.knobExpandAnimator.start();
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent event) {
                if (!QSwitchView.this.isChange) {
                    if (QSwitchView.this.onSwitchStateChangeListener != null) {
                        QSwitchView.this.onSwitchStateChangeListener.onSwitchStateChange(QSwitchView.this.isOn);
                    }
                } else {
                    QSwitchView qSwitchView = QSwitchView.this;
                    qSwitchView.isOn = qSwitchView.knobState;
                    if (QSwitchView.this.preIsOn == QSwitchView.this.isOn) {
                        QSwitchView.this.isOn = !r8.isOn;
                        QSwitchView.this.knobState = !r8.knobState;
                    }
                    if (QSwitchView.this.knobState) {
                        QSwitchView.this.knobMoveAnimator.setFloatValues(QSwitchView.this.knobMoveRate, 1.0f);
                        QSwitchView.this.knobMoveAnimator.start();
                        QSwitchView.this.innerContentAnimator.setFloatValues(QSwitchView.this.innerContentRate, 0.0f);
                        QSwitchView.this.innerContentAnimator.start();
                    } else {
                        QSwitchView.this.knobMoveAnimator.setFloatValues(QSwitchView.this.knobMoveRate, 0.0f);
                        QSwitchView.this.knobMoveAnimator.start();
                        QSwitchView.this.innerContentAnimator.setFloatValues(QSwitchView.this.innerContentRate, 1.0f);
                        QSwitchView.this.innerContentAnimator.start();
                    }
                    QSwitchView.this.knobExpandAnimator.setFloatValues(QSwitchView.this.knobExpandRate, 0.0f);
                    QSwitchView.this.knobExpandAnimator.start();
                    if (QSwitchView.this.isChange && QSwitchView.this.onSwitchStateChangeListener != null && QSwitchView.this.isOn != QSwitchView.this.preIsOn) {
                        QSwitchView.this.onSwitchStateChangeListener.onSwitchStateChange(QSwitchView.this.isOn);
                    }
                }
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (QSwitchView.this.isChange) {
                    if (e2.getX() > QSwitchView.this.centerX) {
                        if (!QSwitchView.this.knobState) {
                            QSwitchView.this.knobState = !r4.knobState;
                            QSwitchView.this.knobMoveAnimator.setFloatValues(QSwitchView.this.knobMoveRate, 1.0f);
                            QSwitchView.this.knobMoveAnimator.start();
                            QSwitchView.this.innerContentAnimator.setFloatValues(QSwitchView.this.innerContentRate, 0.0f);
                            QSwitchView.this.innerContentAnimator.start();
                        }
                    } else if (QSwitchView.this.knobState) {
                        QSwitchView.this.knobState = !r4.knobState;
                        QSwitchView.this.knobMoveAnimator.setFloatValues(QSwitchView.this.knobMoveRate, 0.0f);
                        QSwitchView.this.knobMoveAnimator.start();
                    }
                }
                return true;
            }
        };
        this.innerContentRate = 1.0f;
        this.backgroundColor = -3355444;
        this.colorStep = -3355444;
        this.foregroundColor = -4868163;
        this.dirtyAnimation = false;
        this.isAttachedToWindow = false;
        this.bgColorId = 0;
        this.tintColorId = 0;
        this.isChange = true;
        this.context = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.qc_switchView);
        int color = typedArrayObtainStyledAttributes.getColor(3, 1145599);
        this.tintColor = color;
        this.tempTintColor = color;
        this.backgroundColor = typedArrayObtainStyledAttributes.getColor(0, 15066597);
        this.bgColorId = typedArrayObtainStyledAttributes.getResourceId(0, 15066597);
        this.tintColorId = typedArrayObtainStyledAttributes.getResourceId(3, 1145599);
        int iApplyDimension = (int) TypedValue.applyDimension(1, 1.5f, context.getResources().getDisplayMetrics());
        int iApplyDimension2 = (int) TypedValue.applyDimension(1, 5.0f, context.getResources().getDisplayMetrics());
        this.outerStrokeWidth = typedArrayObtainStyledAttributes.getDimensionPixelOffset(1, iApplyDimension);
        this.shadowSpace = typedArrayObtainStyledAttributes.getDimensionPixelOffset(2, iApplyDimension2);
        typedArrayObtainStyledAttributes.recycle();
        this.knobBound = new RectF();
        this.innerContentBound = new RectF();
        this.ovalForPath = new RectF();
        this.tempForRoundRect = new RectF();
        this.paint = new Paint(1);
        this.roundRectPath = new Path();
        GestureDetector gestureDetector = new GestureDetector(context, this.gestureListener);
        this.gestureDetector = gestureDetector;
        gestureDetector.setIsLongpressEnabled(false);
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, this.innerContentProperty, this.innerContentRate, 1.0f);
        this.innerContentAnimator = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration(commonDuration);
        this.innerContentAnimator.setInterpolator(new DecelerateInterpolator());
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, this.knobExpandProperty, this.knobExpandRate, 1.0f);
        this.knobExpandAnimator = objectAnimatorOfFloat2;
        objectAnimatorOfFloat2.setDuration(commonDuration);
        this.knobExpandAnimator.setInterpolator(new DecelerateInterpolator());
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this, this.knobMoveProperty, this.knobMoveRate, 1.0f);
        this.knobMoveAnimator = objectAnimatorOfFloat3;
        objectAnimatorOfFloat3.setDuration(commonDuration);
        this.knobMoveAnimator.setInterpolator(new DecelerateInterpolator());
        this.shadowDrawable = ContextCompat.getDrawable(context, R.mipmap.shadow_ios);
        applyBackgroundColor();
    }

    private void applyBackgroundColor() {
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.bgColorId);
        if (iCheckResourceId != 0) {
            int color = SkinCompatResources.getColor(this.context, iCheckResourceId);
            this.backgroundColor = color;
            this.paint.setColor(color);
        }
        int iCheckResourceId2 = SkinCompatHelper.checkResourceId(this.tintColorId);
        if (iCheckResourceId2 != 0) {
            int color2 = SkinCompatResources.getColor(this.context, iCheckResourceId2);
            this.tempTintColor = color2;
            this.tintColor = color2;
            this.paint.setColor(color2);
        }
    }

    public void setOnSwitchStateChangeListener(OnSwitchStateChangeListener onSwitchStateChangeListener) {
        this.onSwitchStateChangeListener = onSwitchStateChangeListener;
    }

    public OnSwitchStateChangeListener getOnSwitchStateChangeListener() {
        return this.onSwitchStateChangeListener;
    }

    void setInnerContentRate(float rate) {
        this.innerContentRate = rate;
        invalidate();
    }

    float getInnerContentRate() {
        return this.innerContentRate;
    }

    void setKnobExpandRate(float rate) {
        this.knobExpandRate = rate;
        invalidate();
    }

    float getKnobExpandRate() {
        return this.knobExpandRate;
    }

    void setKnobMoveRate(float rate) {
        this.knobMoveRate = rate;
        invalidate();
    }

    float getKnobMoveRate() {
        return this.knobMoveRate;
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        OnSwitchStateChangeListener onSwitchStateChangeListener;
        boolean z;
        super.onAttachedToWindow();
        this.isAttachedToWindow = true;
        if (this.dirtyAnimation) {
            boolean z2 = this.isOn;
            this.knobState = z2;
            if (z2) {
                this.knobMoveAnimator.setFloatValues(this.knobMoveRate, 1.0f);
                this.knobMoveAnimator.start();
                this.innerContentAnimator.setFloatValues(this.innerContentRate, 0.0f);
                this.innerContentAnimator.start();
            } else {
                this.knobMoveAnimator.setFloatValues(this.knobMoveRate, 0.0f);
                this.knobMoveAnimator.start();
                this.innerContentAnimator.setFloatValues(this.innerContentRate, 1.0f);
                this.innerContentAnimator.start();
            }
            this.knobExpandAnimator.setFloatValues(this.knobExpandRate, 0.0f);
            this.knobExpandAnimator.start();
            if (this.isChange && (onSwitchStateChangeListener = this.onSwitchStateChangeListener) != null && (z = this.isOn) != this.preIsOn) {
                onSwitchStateChangeListener.onSwitchStateChange(z);
            }
            this.dirtyAnimation = false;
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.isAttachedToWindow = false;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.width = View.MeasureSpec.getSize(widthMeasureSpec);
        int size = View.MeasureSpec.getSize(heightMeasureSpec);
        this.height = size;
        int i = this.width;
        if (size / i < 0.33333f) {
            this.height = (int) (i * 0.33333f);
            super.setMeasuredDimension(View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.getMode(widthMeasureSpec)), View.MeasureSpec.makeMeasureSpec(this.height, View.MeasureSpec.getMode(heightMeasureSpec)));
        }
        this.centerX = this.width / 2;
        this.centerY = this.height / 2;
        int i2 = this.shadowSpace;
        this.cornerRadius = r4 - i2;
        this.innerContentBound.left = this.outerStrokeWidth + i2;
        this.innerContentBound.top = this.outerStrokeWidth + this.shadowSpace;
        this.innerContentBound.right = (this.width - this.outerStrokeWidth) - this.shadowSpace;
        this.innerContentBound.bottom = (this.height - this.outerStrokeWidth) - this.shadowSpace;
        this.intrinsicInnerWidth = this.innerContentBound.width();
        this.intrinsicInnerHeight = this.innerContentBound.height();
        this.knobBound.left = this.outerStrokeWidth + this.shadowSpace;
        this.knobBound.top = this.outerStrokeWidth + this.shadowSpace;
        this.knobBound.right = (this.height - this.outerStrokeWidth) - this.shadowSpace;
        this.knobBound.bottom = (this.height - this.outerStrokeWidth) - this.shadowSpace;
        this.intrinsicKnobWidth = this.knobBound.height();
        float f = this.width * 0.7f;
        this.knobMaxExpandWidth = f;
        if (f > this.knobBound.width() * 1.25f) {
            this.knobMaxExpandWidth = this.knobBound.width() * 1.25f;
        }
    }

    public boolean isOn() {
        return this.isOn;
    }

    public void setOn(boolean on) {
        setOn(on, false);
    }

    public void setOff() {
        OnSwitchStateChangeListener onSwitchStateChangeListener;
        this.isOn = false;
        this.knobState = false;
        setKnobMoveRate(0.0f);
        setInnerContentRate(1.0f);
        setKnobExpandRate(0.0f);
        if (!this.isChange || (onSwitchStateChangeListener = this.onSwitchStateChangeListener) == null || this.isOn == this.preIsOn) {
            return;
        }
        onSwitchStateChangeListener.onSwitchStateChange(false);
    }

    public void setOn(boolean on, boolean animated) {
        OnSwitchStateChangeListener onSwitchStateChangeListener;
        boolean z;
        if (this.isOn == on) {
            return;
        }
        if (!this.isAttachedToWindow && animated) {
            this.dirtyAnimation = true;
            this.isOn = on;
            return;
        }
        this.isOn = on;
        this.knobState = on;
        if (!animated) {
            if (on) {
                setKnobMoveRate(1.0f);
                setInnerContentRate(0.0f);
            } else {
                setKnobMoveRate(0.0f);
                setInnerContentRate(1.0f);
            }
            setKnobExpandRate(0.0f);
        } else {
            if (on) {
                this.knobMoveAnimator.setFloatValues(this.knobMoveRate, 1.0f);
                this.knobMoveAnimator.start();
                this.innerContentAnimator.setFloatValues(this.innerContentRate, 0.0f);
                this.innerContentAnimator.start();
            } else {
                this.knobMoveAnimator.setFloatValues(this.knobMoveRate, 0.0f);
                this.knobMoveAnimator.start();
                this.innerContentAnimator.setFloatValues(this.innerContentRate, 1.0f);
                this.innerContentAnimator.start();
            }
            this.knobExpandAnimator.setFloatValues(this.knobExpandRate, 0.0f);
            this.knobExpandAnimator.start();
        }
        if (!this.isChange || (onSwitchStateChangeListener = this.onSwitchStateChangeListener) == null || (z = this.isOn) == this.preIsOn) {
            return;
        }
        onSwitchStateChangeListener.onSwitchStateChange(z);
    }

    public void setTintColor(int tintColor) {
        this.tintColor = tintColor;
        this.tempTintColor = tintColor;
    }

    public int getTintColor() {
        return this.tintColor;
    }

    public void setOnchange(boolean isChange) {
        this.isChange = isChange;
    }

    public boolean getOnchange() {
        return this.isChange;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        OnSwitchStateChangeListener onSwitchStateChangeListener;
        if (!isEnabled()) {
            return false;
        }
        int action = event.getAction();
        if (action == 1 || action == 3) {
            if (!this.knobState) {
                ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, this.innerContentProperty, this.innerContentRate, 1.0f);
                this.innerContentAnimator = objectAnimatorOfFloat;
                objectAnimatorOfFloat.setDuration(commonDuration);
                this.innerContentAnimator.setInterpolator(new DecelerateInterpolator());
                this.innerContentAnimator.start();
            }
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, this.knobExpandProperty, this.knobExpandRate, 0.0f);
            this.knobExpandAnimator = objectAnimatorOfFloat2;
            objectAnimatorOfFloat2.setDuration(commonDuration);
            this.knobExpandAnimator.setInterpolator(new DecelerateInterpolator());
            this.knobExpandAnimator.start();
            boolean z = this.knobState;
            this.isOn = z;
            if (this.isChange && (onSwitchStateChangeListener = this.onSwitchStateChangeListener) != null && z != this.preIsOn) {
                onSwitchStateChangeListener.onSwitchStateChange(z);
            }
        }
        return this.gestureDetector.onTouchEvent(event);
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
            this.tintColor = this.tempTintColor;
        } else {
            this.tintColor = RGBColorTransform(0.5f, this.tempTintColor, -4868163);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = this.intrinsicInnerWidth / 2.0f;
        float f2 = this.innerContentRate;
        float f3 = f * f2;
        float f4 = (this.intrinsicInnerHeight / 2.0f) * f2;
        this.innerContentBound.left = this.centerX - f3;
        this.innerContentBound.top = this.centerY - f4;
        this.innerContentBound.right = this.centerX + f3;
        this.innerContentBound.bottom = this.centerY + f4;
        float f5 = this.intrinsicKnobWidth;
        float f6 = f5 + ((this.knobMaxExpandWidth - f5) * this.knobExpandRate);
        if (this.knobBound.left + (this.knobBound.width() / 2.0f) > ((float) this.centerX)) {
            RectF rectF = this.knobBound;
            rectF.left = rectF.right - f6;
        } else {
            RectF rectF2 = this.knobBound;
            rectF2.right = rectF2.left + f6;
        }
        float fWidth = this.knobBound.width();
        float f7 = this.knobMoveRate;
        this.colorStep = RGBColorTransform(f7, this.backgroundColor, this.tintColor);
        this.knobBound.left = this.shadowSpace + this.outerStrokeWidth + (((this.width - fWidth) - ((this.shadowSpace + this.outerStrokeWidth) * 2)) * f7);
        RectF rectF3 = this.knobBound;
        rectF3.right = rectF3.left + fWidth;
        this.paint.setColor(this.colorStep);
        this.paint.setStyle(Paint.Style.FILL);
        int i = this.shadowSpace;
        drawRoundRect(i, i, this.width - i, this.height - i, this.cornerRadius, canvas, this.paint);
        this.paint.setShadowLayer(2.0f, 0.0f, this.shadowSpace / 2, isEnabled() ? 536870912 : 268435456);
        this.paint.setColor(isEnabled() ? 536870912 : 268435456);
        drawRoundRect(this.knobBound.left, (this.shadowSpace / 2) + this.knobBound.top, this.knobBound.right, (this.shadowSpace / 2) + this.knobBound.bottom, this.cornerRadius - this.outerStrokeWidth, canvas, this.paint);
        this.paint.setColor(-1);
        RectF rectF4 = this.knobBound;
        float f8 = this.cornerRadius;
        int i2 = this.outerStrokeWidth;
        canvas.drawRoundRect(rectF4, f8 - i2, f8 - i2, this.paint);
        if (!this.isOn) {
            this.paint.setColor(this.backgroundColor);
            canvas.drawCircle(this.knobBound.left + ((this.knobBound.right - this.knobBound.left) / 2.0f), this.knobBound.top + ((this.knobBound.bottom - this.knobBound.top) / 2.0f), dp2px(this.context, 4.0f), this.paint);
        }
        this.paint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        this.paint.setColor(this.backgroundColor);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(1.0f);
        RectF rectF5 = this.knobBound;
        float f9 = this.cornerRadius;
        int i3 = this.outerStrokeWidth;
        canvas.drawRoundRect(rectF5, f9 - i3, f9 - i3, this.paint);
    }

    private void drawRoundRect(float left, float top2, float right, float bottom, float radius, Canvas canvas, Paint paint) {
        this.tempForRoundRect.left = left;
        this.tempForRoundRect.top = top2;
        this.tempForRoundRect.right = right;
        this.tempForRoundRect.bottom = bottom;
        canvas.drawRoundRect(this.tempForRoundRect, radius, radius, paint);
    }

    public float dp2px(Context context, float dp) {
        return (dp * context.getResources().getDisplayMetrics().density) + 0.5f;
    }
}
