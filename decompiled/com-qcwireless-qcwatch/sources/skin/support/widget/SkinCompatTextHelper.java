package skin.support.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;
import skin.support.R;
import skin.support.content.res.SkinCompatResources;
import skin.support.content.res.SkinCompatVectorResources;

/* loaded from: classes5.dex */
public class SkinCompatTextHelper extends SkinCompatHelper {
    private static final String TAG = "SkinCompatTextHelper";
    final TextView mView;
    private int mTextColorResId = 0;
    private int mTextColorHintResId = 0;
    protected int mDrawableBottomResId = 0;
    protected int mDrawableLeftResId = 0;
    protected int mDrawableRightResId = 0;
    protected int mDrawableTopResId = 0;

    public static SkinCompatTextHelper create(TextView textView) {
        if (Build.VERSION.SDK_INT >= 17) {
            return new SkinCompatTextHelperV17(textView);
        }
        return new SkinCompatTextHelper(textView);
    }

    public SkinCompatTextHelper(TextView textView) {
        this.mView = textView;
    }

    public void loadFromAttributes(AttributeSet attributeSet, int i) {
        Context context = this.mView.getContext();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SkinCompatTextHelper, i, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.SkinCompatTextHelper_android_textAppearance, 0);
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.SkinCompatTextHelper_android_drawableLeft)) {
            this.mDrawableLeftResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.SkinCompatTextHelper_android_drawableLeft, 0);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.SkinCompatTextHelper_android_drawableTop)) {
            this.mDrawableTopResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.SkinCompatTextHelper_android_drawableTop, 0);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.SkinCompatTextHelper_android_drawableRight)) {
            this.mDrawableRightResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.SkinCompatTextHelper_android_drawableRight, 0);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.SkinCompatTextHelper_android_drawableBottom)) {
            this.mDrawableBottomResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.SkinCompatTextHelper_android_drawableBottom, 0);
        }
        typedArrayObtainStyledAttributes.recycle();
        if (resourceId != 0) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, R.styleable.SkinTextAppearance);
            if (typedArrayObtainStyledAttributes2.hasValue(R.styleable.SkinTextAppearance_android_textColor)) {
                this.mTextColorResId = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.SkinTextAppearance_android_textColor, 0);
            }
            if (typedArrayObtainStyledAttributes2.hasValue(R.styleable.SkinTextAppearance_android_textColorHint)) {
                this.mTextColorHintResId = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.SkinTextAppearance_android_textColorHint, 0);
            }
            typedArrayObtainStyledAttributes2.recycle();
        }
        TypedArray typedArrayObtainStyledAttributes3 = context.obtainStyledAttributes(attributeSet, R.styleable.SkinTextAppearance, i, 0);
        if (typedArrayObtainStyledAttributes3.hasValue(R.styleable.SkinTextAppearance_android_textColor)) {
            this.mTextColorResId = typedArrayObtainStyledAttributes3.getResourceId(R.styleable.SkinTextAppearance_android_textColor, 0);
        }
        if (typedArrayObtainStyledAttributes3.hasValue(R.styleable.SkinTextAppearance_android_textColorHint)) {
            this.mTextColorHintResId = typedArrayObtainStyledAttributes3.getResourceId(R.styleable.SkinTextAppearance_android_textColorHint, 0);
        }
        typedArrayObtainStyledAttributes3.recycle();
        applySkin();
    }

    public void onSetTextAppearance(Context context, int i) throws Resources.NotFoundException {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, R.styleable.SkinTextAppearance);
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.SkinTextAppearance_android_textColor)) {
            this.mTextColorResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.SkinTextAppearance_android_textColor, 0);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.SkinTextAppearance_android_textColorHint)) {
            this.mTextColorHintResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.SkinTextAppearance_android_textColorHint, 0);
        }
        typedArrayObtainStyledAttributes.recycle();
        applyTextColorResource();
        applyTextColorHintResource();
    }

    private void applyTextColorHintResource() {
        int iCheckResourceId = checkResourceId(this.mTextColorHintResId);
        this.mTextColorHintResId = iCheckResourceId;
        if (iCheckResourceId != 0) {
            try {
                this.mView.setHintTextColor(SkinCompatResources.getColorStateList(this.mView.getContext(), this.mTextColorHintResId));
            } catch (Exception unused) {
            }
        }
    }

    private void applyTextColorResource() {
        int iCheckResourceId = checkResourceId(this.mTextColorResId);
        this.mTextColorResId = iCheckResourceId;
        if (iCheckResourceId != 0) {
            try {
                this.mView.setTextColor(SkinCompatResources.getColorStateList(this.mView.getContext(), this.mTextColorResId));
            } catch (Exception unused) {
            }
        }
    }

    public void onSetCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        this.mDrawableLeftResId = i;
        this.mDrawableTopResId = i2;
        this.mDrawableRightResId = i3;
        this.mDrawableBottomResId = i4;
        applyCompoundDrawablesRelativeResource();
    }

    public void onSetCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        this.mDrawableLeftResId = i;
        this.mDrawableTopResId = i2;
        this.mDrawableRightResId = i3;
        this.mDrawableBottomResId = i4;
        applyCompoundDrawablesResource();
    }

    protected void applyCompoundDrawablesRelativeResource() {
        applyCompoundDrawablesResource();
    }

    protected void applyCompoundDrawablesResource() {
        int iCheckResourceId = checkResourceId(this.mDrawableLeftResId);
        this.mDrawableLeftResId = iCheckResourceId;
        Drawable drawableCompat = iCheckResourceId != 0 ? SkinCompatVectorResources.getDrawableCompat(this.mView.getContext(), this.mDrawableLeftResId) : null;
        int iCheckResourceId2 = checkResourceId(this.mDrawableTopResId);
        this.mDrawableTopResId = iCheckResourceId2;
        Drawable drawableCompat2 = iCheckResourceId2 != 0 ? SkinCompatVectorResources.getDrawableCompat(this.mView.getContext(), this.mDrawableTopResId) : null;
        int iCheckResourceId3 = checkResourceId(this.mDrawableRightResId);
        this.mDrawableRightResId = iCheckResourceId3;
        Drawable drawableCompat3 = iCheckResourceId3 != 0 ? SkinCompatVectorResources.getDrawableCompat(this.mView.getContext(), this.mDrawableRightResId) : null;
        int iCheckResourceId4 = checkResourceId(this.mDrawableBottomResId);
        this.mDrawableBottomResId = iCheckResourceId4;
        Drawable drawableCompat4 = iCheckResourceId4 != 0 ? SkinCompatVectorResources.getDrawableCompat(this.mView.getContext(), this.mDrawableBottomResId) : null;
        if (this.mDrawableLeftResId == 0 && this.mDrawableTopResId == 0 && this.mDrawableRightResId == 0 && this.mDrawableBottomResId == 0) {
            return;
        }
        this.mView.setCompoundDrawablesWithIntrinsicBounds(drawableCompat, drawableCompat2, drawableCompat3, drawableCompat4);
    }

    public int getTextColorResId() {
        return this.mTextColorResId;
    }

    @Override // skin.support.widget.SkinCompatHelper
    public void applySkin() {
        applyCompoundDrawablesRelativeResource();
        applyTextColorResource();
        applyTextColorHintResource();
    }
}
