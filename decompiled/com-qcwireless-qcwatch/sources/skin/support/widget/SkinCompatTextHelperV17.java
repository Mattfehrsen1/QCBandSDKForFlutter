package skin.support.widget;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
import skin.support.R;
import skin.support.content.res.SkinCompatVectorResources;

/* loaded from: classes5.dex */
public class SkinCompatTextHelperV17 extends SkinCompatTextHelper {
    private int mDrawableEndResId;
    private int mDrawableStartResId;

    public SkinCompatTextHelperV17(TextView textView) {
        super(textView);
        this.mDrawableStartResId = 0;
        this.mDrawableEndResId = 0;
    }

    @Override // skin.support.widget.SkinCompatTextHelper
    public void loadFromAttributes(AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = this.mView.getContext().obtainStyledAttributes(attributeSet, R.styleable.SkinCompatTextHelper, i, 0);
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.SkinCompatTextHelper_android_drawableStart)) {
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.SkinCompatTextHelper_android_drawableStart, 0);
            this.mDrawableStartResId = resourceId;
            this.mDrawableStartResId = SkinCompatHelper.checkResourceId(resourceId);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.SkinCompatTextHelper_android_drawableEnd)) {
            int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.SkinCompatTextHelper_android_drawableEnd, 0);
            this.mDrawableEndResId = resourceId2;
            this.mDrawableEndResId = SkinCompatHelper.checkResourceId(resourceId2);
        }
        typedArrayObtainStyledAttributes.recycle();
        super.loadFromAttributes(attributeSet, i);
    }

    @Override // skin.support.widget.SkinCompatTextHelper
    public void onSetCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        this.mDrawableStartResId = i;
        this.mDrawableTopResId = i2;
        this.mDrawableEndResId = i3;
        this.mDrawableBottomResId = i4;
        applyCompoundDrawablesRelativeResource();
    }

    @Override // skin.support.widget.SkinCompatTextHelper
    protected void applyCompoundDrawablesRelativeResource() {
        this.mDrawableLeftResId = checkResourceId(this.mDrawableLeftResId);
        Drawable drawableCompat = this.mDrawableLeftResId != 0 ? SkinCompatVectorResources.getDrawableCompat(this.mView.getContext(), this.mDrawableLeftResId) : null;
        this.mDrawableTopResId = checkResourceId(this.mDrawableTopResId);
        Drawable drawableCompat2 = this.mDrawableTopResId != 0 ? SkinCompatVectorResources.getDrawableCompat(this.mView.getContext(), this.mDrawableTopResId) : null;
        this.mDrawableRightResId = checkResourceId(this.mDrawableRightResId);
        Drawable drawableCompat3 = this.mDrawableRightResId != 0 ? SkinCompatVectorResources.getDrawableCompat(this.mView.getContext(), this.mDrawableRightResId) : null;
        this.mDrawableBottomResId = checkResourceId(this.mDrawableBottomResId);
        Drawable drawableCompat4 = this.mDrawableBottomResId != 0 ? SkinCompatVectorResources.getDrawableCompat(this.mView.getContext(), this.mDrawableBottomResId) : null;
        Drawable drawableCompat5 = this.mDrawableStartResId != 0 ? SkinCompatVectorResources.getDrawableCompat(this.mView.getContext(), this.mDrawableStartResId) : null;
        if (drawableCompat5 != null) {
            drawableCompat = drawableCompat5;
        }
        Drawable drawableCompat6 = this.mDrawableEndResId != 0 ? SkinCompatVectorResources.getDrawableCompat(this.mView.getContext(), this.mDrawableEndResId) : null;
        if (drawableCompat6 != null) {
            drawableCompat3 = drawableCompat6;
        }
        if (this.mDrawableLeftResId == 0 && this.mDrawableTopResId == 0 && this.mDrawableRightResId == 0 && this.mDrawableBottomResId == 0 && this.mDrawableStartResId == 0 && this.mDrawableEndResId == 0) {
            return;
        }
        this.mView.setCompoundDrawablesWithIntrinsicBounds(drawableCompat, drawableCompat2, drawableCompat3, drawableCompat4);
    }
}
