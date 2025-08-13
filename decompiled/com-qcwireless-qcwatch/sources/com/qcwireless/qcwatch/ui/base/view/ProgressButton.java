package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import com.qcwireless.qcwatch.R;
import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

/* loaded from: classes3.dex */
public class ProgressButton extends AppCompatButton implements SkinCompatSupportable {
    private Context context;
    private int defaultButtonColorId;
    private int defaultProgressColorId;
    private SkinCompatBackgroundHelper helper;
    private float mCornerRadius;
    private GradientDrawable mDrawableButton;
    private GradientDrawable mDrawableProgress;
    private GradientDrawable mDrawableProgressBackground;
    private boolean mFinish;
    private int mMaxProgress;
    private int mMinProgress;
    private int mProgress;
    private float mProgressMargin;

    public ProgressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCornerRadius = 0.0f;
        this.mProgressMargin = 0.0f;
        this.mMaxProgress = 100;
        this.mMinProgress = 0;
        this.defaultButtonColorId = 0;
        this.defaultProgressColorId = 0;
        this.context = context;
        initialize(context, attrs);
    }

    public ProgressButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mCornerRadius = 0.0f;
        this.mProgressMargin = 0.0f;
        this.mMaxProgress = 100;
        this.mMinProgress = 0;
        this.defaultButtonColorId = 0;
        this.defaultProgressColorId = 0;
        this.context = context;
        initialize(context, attrs);
    }

    private void initialize(Context context, AttributeSet attrs) {
        this.mDrawableProgressBackground = new GradientDrawable();
        this.mDrawableProgress = new GradientDrawable();
        this.mDrawableButton = new GradientDrawable();
        int color = ContextCompat.getColor(context, R.color.color_FF6A00);
        int color2 = ContextCompat.getColor(context, R.color.color_FF6A00);
        int color3 = ContextCompat.getColor(context, R.color.color_e3e3e3);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.ProgressButton);
        try {
            this.mProgressMargin = typedArrayObtainStyledAttributes.getDimension(7, this.mProgressMargin);
            this.mCornerRadius = typedArrayObtainStyledAttributes.getDimension(1, this.mCornerRadius);
            int color4 = typedArrayObtainStyledAttributes.getColor(0, color);
            this.defaultButtonColorId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
            this.mDrawableButton.setColor(color4);
            this.mDrawableProgressBackground.setColor(typedArrayObtainStyledAttributes.getColor(5, color3));
            int color5 = typedArrayObtainStyledAttributes.getColor(6, color2);
            this.defaultProgressColorId = typedArrayObtainStyledAttributes.getResourceId(6, 0);
            this.mDrawableProgress.setColor(color5);
            this.mProgress = typedArrayObtainStyledAttributes.getInteger(4, this.mProgress);
            this.mMinProgress = typedArrayObtainStyledAttributes.getInteger(3, this.mMinProgress);
            this.mMaxProgress = typedArrayObtainStyledAttributes.getInteger(2, this.mMaxProgress);
            SkinCompatBackgroundHelper skinCompatBackgroundHelper = new SkinCompatBackgroundHelper(this);
            this.helper = skinCompatBackgroundHelper;
            skinCompatBackgroundHelper.onSetBackgroundResource(R.drawable.sel_btn_user_center);
            typedArrayObtainStyledAttributes.recycle();
            this.mDrawableButton.setCornerRadius(this.mCornerRadius);
            this.mDrawableProgressBackground.setCornerRadius(this.mCornerRadius);
            this.mDrawableProgress.setCornerRadius(this.mCornerRadius - this.mProgressMargin);
            setBackgroundDrawable(this.mDrawableButton);
            this.mFinish = false;
            applyTextColor();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    private void applyTextColor() {
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.defaultButtonColorId);
        if (iCheckResourceId != 0) {
            this.mDrawableButton.setColor(SkinCompatResources.getColor(this.context, iCheckResourceId));
        }
        int iCheckResourceId2 = SkinCompatHelper.checkResourceId(this.defaultProgressColorId);
        if (iCheckResourceId2 != 0) {
            this.mDrawableProgress.setColor(SkinCompatResources.getColor(this.context, iCheckResourceId2));
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        int i = this.mProgress;
        if (i > this.mMinProgress && i <= this.mMaxProgress && !this.mFinish) {
            float measuredWidth = getMeasuredWidth();
            int i2 = this.mProgress;
            float f = measuredWidth * (((i2 - r2) / this.mMaxProgress) - this.mMinProgress);
            float f2 = this.mCornerRadius;
            if (f < f2 * 2.0f) {
                f = f2 * 2.0f;
            }
            GradientDrawable gradientDrawable = this.mDrawableProgress;
            float f3 = this.mProgressMargin;
            gradientDrawable.setBounds((int) f3, (int) f3, (int) (f - f3), getMeasuredHeight() - ((int) this.mProgressMargin));
            this.mDrawableProgress.draw(canvas);
            if (this.mProgress == this.mMaxProgress) {
                setBackgroundDrawable(this.mDrawableButton);
                this.mFinish = true;
            }
        }
        super.onDraw(canvas);
    }

    public void setProgress(int progress) {
        if (this.mFinish) {
            return;
        }
        this.mProgress = progress;
        setBackgroundDrawable(this.mDrawableProgressBackground);
        invalidate();
    }

    public void setMaxProgress(int maxProgress) {
        this.mMaxProgress = maxProgress;
    }

    public void setMinProgress(int minProgress) {
        this.mMinProgress = minProgress;
    }

    public void reset() {
        this.mFinish = false;
        this.mProgress = this.mMinProgress;
    }

    public void setButtonColor(boolean mainColor) {
        if (mainColor) {
            this.mDrawableButton.setColor(SkinCompatResources.getColor(this.context, R.color.color_FF6A00));
        } else {
            this.mDrawableButton.setColor(SkinCompatResources.getColor(this.context, R.color.profile_btn_bg));
        }
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        applyTextColor();
        this.helper.applySkin();
    }
}
