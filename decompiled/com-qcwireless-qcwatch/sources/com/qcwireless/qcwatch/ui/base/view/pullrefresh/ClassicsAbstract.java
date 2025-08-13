package com.qcwireless.qcwatch.ui.base.view.pullrefresh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.qcwireless.qcwatch.ui.base.view.pullrefresh.ClassicsAbstract;
import com.scwang.smart.refresh.layout.api.RefreshComponent;
import com.scwang.smart.refresh.layout.api.RefreshKernel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;
import com.scwang.smart.refresh.layout.simple.SimpleComponent;
import com.scwang.smart.refresh.layout.util.SmartUtil;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: classes3.dex */
public abstract class ClassicsAbstract<T extends ClassicsAbstract> extends SimpleComponent implements RefreshComponent {
    public static final int ID_IMAGE_ARROW = 2131297361;
    public static final int ID_IMAGE_PROGRESS = 2131297362;
    public static final int ID_TEXT_TITLE = 2131297363;
    protected PaintDrawable mArrowDrawable;
    protected ImageView mArrowView;
    protected int mBackgroundColor;
    protected int mFinishDuration;
    protected int mMinHeightOfContent;
    protected int mPaddingBottom;
    protected int mPaddingTop;
    protected PaintDrawable mProgressDrawable;
    protected ImageView mProgressView;
    protected RefreshKernel mRefreshKernel;
    protected boolean mSetAccentColor;
    protected boolean mSetPrimaryColor;
    protected TextView mTitleText;

    protected T self() {
        return this;
    }

    public ClassicsAbstract(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mFinishDuration = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
        this.mPaddingTop = 20;
        this.mPaddingBottom = 20;
        this.mMinHeightOfContent = 0;
        this.mSpinnerStyle = SpinnerStyle.Translate;
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.mMinHeightOfContent == 0) {
            this.mPaddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            this.mPaddingBottom = paddingBottom;
            if (this.mPaddingTop == 0 || paddingBottom == 0) {
                int paddingLeft = getPaddingLeft();
                int paddingRight = getPaddingRight();
                int iDp2px = this.mPaddingTop;
                if (iDp2px == 0) {
                    iDp2px = SmartUtil.dp2px(20.0f);
                }
                this.mPaddingTop = iDp2px;
                int iDp2px2 = this.mPaddingBottom;
                if (iDp2px2 == 0) {
                    iDp2px2 = SmartUtil.dp2px(20.0f);
                }
                this.mPaddingBottom = iDp2px2;
                setPadding(paddingLeft, this.mPaddingTop, paddingRight, iDp2px2);
            }
            setClipToPadding(false);
        }
        if (View.MeasureSpec.getMode(heightMeasureSpec) == 1073741824) {
            int size = View.MeasureSpec.getSize(heightMeasureSpec);
            int i = this.mMinHeightOfContent;
            if (size < i) {
                int i2 = (size - i) / 2;
                setPadding(getPaddingLeft(), i2, getPaddingRight(), i2);
            } else {
                setPadding(getPaddingLeft(), 0, getPaddingRight(), 0);
            }
        } else {
            setPadding(getPaddingLeft(), this.mPaddingTop, getPaddingRight(), this.mPaddingBottom);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.mMinHeightOfContent == 0) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                int measuredHeight = getChildAt(i3).getMeasuredHeight();
                if (this.mMinHeightOfContent < measuredHeight) {
                    this.mMinHeightOfContent = measuredHeight;
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ImageView imageView = this.mArrowView;
        ImageView imageView2 = this.mProgressView;
        imageView.animate().cancel();
        imageView2.animate().cancel();
        Object drawable = this.mProgressView.getDrawable();
        if (drawable instanceof Animatable) {
            Animatable animatable = (Animatable) drawable;
            if (animatable.isRunning()) {
                animatable.stop();
            }
        }
    }

    @Override // com.scwang.smart.refresh.layout.simple.SimpleComponent, com.scwang.smart.refresh.layout.api.RefreshComponent
    public void onInitialized(RefreshKernel kernel, int height, int maxDragHeight) {
        this.mRefreshKernel = kernel;
        kernel.requestDrawBackgroundFor(this, this.mBackgroundColor);
    }

    @Override // com.scwang.smart.refresh.layout.simple.SimpleComponent, com.scwang.smart.refresh.layout.api.RefreshComponent
    public void onStartAnimator(RefreshLayout refreshLayout, int height, int maxDragHeight) {
        ImageView imageView = this.mProgressView;
        if (imageView.getVisibility() != 0) {
            imageView.setVisibility(0);
            Object drawable = this.mProgressView.getDrawable();
            if (drawable instanceof Animatable) {
                ((Animatable) drawable).start();
            } else {
                imageView.animate().rotation(36000.0f).setDuration(100000L);
            }
        }
    }

    @Override // com.scwang.smart.refresh.layout.simple.SimpleComponent, com.scwang.smart.refresh.layout.api.RefreshComponent
    public void onReleased(RefreshLayout refreshLayout, int height, int maxDragHeight) {
        onStartAnimator(refreshLayout, height, maxDragHeight);
    }

    @Override // com.scwang.smart.refresh.layout.simple.SimpleComponent, com.scwang.smart.refresh.layout.api.RefreshComponent
    public int onFinish(RefreshLayout refreshLayout, boolean success) {
        ImageView imageView = this.mProgressView;
        Object drawable = imageView.getDrawable();
        if (drawable instanceof Animatable) {
            Animatable animatable = (Animatable) drawable;
            if (animatable.isRunning()) {
                animatable.stop();
            }
        } else {
            imageView.animate().rotation(0.0f).setDuration(0L);
        }
        imageView.setVisibility(8);
        return this.mFinishDuration;
    }

    @Override // com.scwang.smart.refresh.layout.simple.SimpleComponent, com.scwang.smart.refresh.layout.api.RefreshComponent
    public void setPrimaryColors(int... colors) {
        if (colors.length > 0) {
            if (!(getBackground() instanceof BitmapDrawable) && !this.mSetPrimaryColor) {
                setPrimaryColor(colors[0]);
                this.mSetPrimaryColor = false;
            }
            if (this.mSetAccentColor) {
                return;
            }
            if (colors.length > 1) {
                setAccentColor(colors[1]);
            }
            this.mSetAccentColor = false;
        }
    }

    public T setProgressBitmap(Bitmap bitmap) {
        this.mProgressDrawable = null;
        this.mProgressView.setImageBitmap(bitmap);
        return (T) self();
    }

    public T setProgressDrawable(Drawable drawable) {
        this.mProgressDrawable = null;
        this.mProgressView.setImageDrawable(drawable);
        return (T) self();
    }

    public T setProgressResource(int i) {
        this.mProgressDrawable = null;
        this.mProgressView.setImageResource(i);
        return (T) self();
    }

    public T setArrowBitmap(Bitmap bitmap) {
        this.mArrowDrawable = null;
        this.mArrowView.setImageBitmap(bitmap);
        return (T) self();
    }

    public T setArrowDrawable(Drawable drawable) {
        this.mArrowDrawable = null;
        this.mArrowView.setImageDrawable(drawable);
        return (T) self();
    }

    public T setArrowResource(int i) {
        this.mArrowDrawable = null;
        this.mArrowView.setImageResource(i);
        return (T) self();
    }

    public T setSpinnerStyle(SpinnerStyle spinnerStyle) {
        this.mSpinnerStyle = spinnerStyle;
        return (T) self();
    }

    public T setPrimaryColor(int i) {
        this.mSetPrimaryColor = true;
        this.mBackgroundColor = i;
        RefreshKernel refreshKernel = this.mRefreshKernel;
        if (refreshKernel != null) {
            refreshKernel.requestDrawBackgroundFor(this, i);
        }
        return (T) self();
    }

    public T setAccentColor(int i) {
        this.mSetAccentColor = true;
        this.mTitleText.setTextColor(i);
        PaintDrawable paintDrawable = this.mArrowDrawable;
        if (paintDrawable != null) {
            paintDrawable.setColor(i);
            this.mArrowView.invalidateDrawable(this.mArrowDrawable);
        }
        PaintDrawable paintDrawable2 = this.mProgressDrawable;
        if (paintDrawable2 != null) {
            paintDrawable2.setColor(i);
            this.mProgressView.invalidateDrawable(this.mProgressDrawable);
        }
        return (T) self();
    }

    public T setPrimaryColorId(int i) {
        setPrimaryColor(ContextCompat.getColor(getContext(), i));
        return (T) self();
    }

    public T setAccentColorId(int i) {
        setAccentColor(ContextCompat.getColor(getContext(), i));
        return (T) self();
    }

    public T setFinishDuration(int i) {
        this.mFinishDuration = i;
        return (T) self();
    }

    public T setTextSizeTitle(float f) {
        this.mTitleText.setTextSize(f);
        RefreshKernel refreshKernel = this.mRefreshKernel;
        if (refreshKernel != null) {
            refreshKernel.requestRemeasureHeightFor(this);
        }
        return (T) self();
    }

    public T setTextSizeTitle(int i, float f) {
        this.mTitleText.setTextSize(i, f);
        RefreshKernel refreshKernel = this.mRefreshKernel;
        if (refreshKernel != null) {
            refreshKernel.requestRemeasureHeightFor(this);
        }
        return (T) self();
    }

    public T setDrawableMarginRight(float f) {
        ImageView imageView = this.mArrowView;
        ImageView imageView2 = this.mProgressView;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) imageView2.getLayoutParams();
        int iDp2px = SmartUtil.dp2px(f);
        marginLayoutParams2.rightMargin = iDp2px;
        marginLayoutParams.rightMargin = iDp2px;
        imageView.setLayoutParams(marginLayoutParams);
        imageView2.setLayoutParams(marginLayoutParams2);
        return (T) self();
    }

    public T setDrawableMarginRightPx(int i) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mArrowView.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mProgressView.getLayoutParams();
        marginLayoutParams2.rightMargin = i;
        marginLayoutParams.rightMargin = i;
        this.mArrowView.setLayoutParams(marginLayoutParams);
        this.mProgressView.setLayoutParams(marginLayoutParams2);
        return (T) self();
    }

    public T setDrawableSize(float f) {
        ImageView imageView = this.mArrowView;
        ImageView imageView2 = this.mProgressView;
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = imageView2.getLayoutParams();
        int iDp2px = SmartUtil.dp2px(f);
        layoutParams2.width = iDp2px;
        layoutParams.width = iDp2px;
        int iDp2px2 = SmartUtil.dp2px(f);
        layoutParams2.height = iDp2px2;
        layoutParams.height = iDp2px2;
        imageView.setLayoutParams(layoutParams);
        imageView2.setLayoutParams(layoutParams2);
        return (T) self();
    }

    public T setDrawableSizePx(int i) {
        ViewGroup.LayoutParams layoutParams = this.mArrowView.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = this.mProgressView.getLayoutParams();
        layoutParams2.width = i;
        layoutParams.width = i;
        layoutParams2.height = i;
        layoutParams.height = i;
        this.mArrowView.setLayoutParams(layoutParams);
        this.mProgressView.setLayoutParams(layoutParams2);
        return (T) self();
    }

    public T setDrawableArrowSize(float f) {
        ImageView imageView = this.mArrowView;
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        int iDp2px = SmartUtil.dp2px(f);
        layoutParams.width = iDp2px;
        layoutParams.height = iDp2px;
        imageView.setLayoutParams(layoutParams);
        return (T) self();
    }

    public T setDrawableArrowSizePx(int i) {
        ViewGroup.LayoutParams layoutParams = this.mArrowView.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i;
        this.mArrowView.setLayoutParams(layoutParams);
        return (T) self();
    }

    public T setDrawableProgressSize(float f) {
        ImageView imageView = this.mProgressView;
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        int iDp2px = SmartUtil.dp2px(f);
        layoutParams.width = iDp2px;
        layoutParams.height = iDp2px;
        imageView.setLayoutParams(layoutParams);
        return (T) self();
    }

    public T setDrawableProgressSizePx(int i) {
        ViewGroup.LayoutParams layoutParams = this.mProgressView.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i;
        this.mProgressView.setLayoutParams(layoutParams);
        return (T) self();
    }
}
