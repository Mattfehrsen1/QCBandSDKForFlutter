package skin.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import skin.support.content.res.SkinCompatResources;
import skin.support.design.R;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatImageHelper;
import skin.support.widget.SkinCompatSupportable;

/* loaded from: classes5.dex */
public class SkinMaterialFloatingActionButton extends FloatingActionButton implements SkinCompatSupportable {
    private int mBackgroundTintResId;
    private SkinCompatImageHelper mImageHelper;
    private int mRippleColorResId;

    public SkinMaterialFloatingActionButton(Context context) {
        this(context, null);
    }

    public SkinMaterialFloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SkinMaterialFloatingActionButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRippleColorResId = 0;
        this.mBackgroundTintResId = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FloatingActionButton, i, R.style.Widget_Design_FloatingActionButton);
        this.mBackgroundTintResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.FloatingActionButton_backgroundTint, 0);
        this.mRippleColorResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.FloatingActionButton_rippleColor, 0);
        typedArrayObtainStyledAttributes.recycle();
        applyBackgroundTintResource();
        applyRippleColorResource();
        SkinCompatImageHelper skinCompatImageHelper = new SkinCompatImageHelper(this);
        this.mImageHelper = skinCompatImageHelper;
        skinCompatImageHelper.loadFromAttributes(attributeSet, i);
    }

    private void applyBackgroundTintResource() {
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.mBackgroundTintResId);
        this.mBackgroundTintResId = iCheckResourceId;
        if (iCheckResourceId != 0) {
            setBackgroundTintList(SkinCompatResources.getColorStateList(getContext(), this.mBackgroundTintResId));
        }
    }

    private void applyRippleColorResource() {
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.mRippleColorResId);
        this.mRippleColorResId = iCheckResourceId;
        if (iCheckResourceId != 0) {
            setRippleColor(SkinCompatResources.getColor(getContext(), this.mRippleColorResId));
        }
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        applyBackgroundTintResource();
        applyRippleColorResource();
        SkinCompatImageHelper skinCompatImageHelper = this.mImageHelper;
        if (skinCompatImageHelper != null) {
            skinCompatImageHelper.applySkin();
        }
    }
}
