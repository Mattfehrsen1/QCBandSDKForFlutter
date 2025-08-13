package skin.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import skin.support.content.res.SkinCompatVectorResources;
import skin.support.design.R;
import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

/* loaded from: classes5.dex */
public class SkinMaterialCollapsingToolbarLayout extends CollapsingToolbarLayout implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private int mContentScrimResId;
    private int mStatusBarScrimResId;

    public SkinMaterialCollapsingToolbarLayout(Context context) {
        this(context, null);
    }

    public SkinMaterialCollapsingToolbarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SkinMaterialCollapsingToolbarLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContentScrimResId = 0;
        this.mStatusBarScrimResId = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CollapsingToolbarLayout, i, R.style.Widget_Design_CollapsingToolbar);
        this.mContentScrimResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CollapsingToolbarLayout_contentScrim, 0);
        this.mStatusBarScrimResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CollapsingToolbarLayout_statusBarScrim, 0);
        typedArrayObtainStyledAttributes.recycle();
        applyContentScrimResource();
        applyStatusBarScrimResource();
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = new SkinCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = skinCompatBackgroundHelper;
        skinCompatBackgroundHelper.loadFromAttributes(attributeSet, 0);
    }

    private void applyStatusBarScrimResource() {
        Drawable drawableCompat;
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.mStatusBarScrimResId);
        this.mStatusBarScrimResId = iCheckResourceId;
        if (iCheckResourceId == 0 || (drawableCompat = SkinCompatVectorResources.getDrawableCompat(getContext(), this.mStatusBarScrimResId)) == null) {
            return;
        }
        setStatusBarScrim(drawableCompat);
    }

    private void applyContentScrimResource() {
        Drawable drawableCompat;
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.mContentScrimResId);
        this.mContentScrimResId = iCheckResourceId;
        if (iCheckResourceId == 0 || (drawableCompat = SkinCompatVectorResources.getDrawableCompat(getContext(), this.mContentScrimResId)) == null) {
            return;
        }
        setContentScrim(drawableCompat);
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        applyContentScrimResource();
        applyStatusBarScrimResource();
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.applySkin();
        }
    }
}
