package skin.support.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.Toolbar;
import skin.support.appcompat.R;
import skin.support.content.res.SkinCompatResources;
import skin.support.content.res.SkinCompatVectorResources;

/* loaded from: classes5.dex */
public class SkinCompatToolbar extends Toolbar implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private int mNavigationIconResId;
    private int mSubtitleTextColorResId;
    private int mTitleTextColorResId;

    public SkinCompatToolbar(Context context) {
        this(context, null);
    }

    public SkinCompatToolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.toolbarStyle);
    }

    public SkinCompatToolbar(Context context, AttributeSet attributeSet, int i) throws Resources.NotFoundException {
        super(context, attributeSet, i);
        this.mTitleTextColorResId = 0;
        this.mSubtitleTextColorResId = 0;
        this.mNavigationIconResId = 0;
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = new SkinCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = skinCompatBackgroundHelper;
        skinCompatBackgroundHelper.loadFromAttributes(attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Toolbar, i, 0);
        this.mNavigationIconResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.Toolbar_navigationIcon, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.Toolbar_titleTextAppearance, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.Toolbar_subtitleTextAppearance, 0);
        typedArrayObtainStyledAttributes.recycle();
        if (resourceId != 0) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, R.styleable.SkinTextAppearance);
            this.mTitleTextColorResId = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.SkinTextAppearance_android_textColor, 0);
            typedArrayObtainStyledAttributes2.recycle();
        }
        if (resourceId2 != 0) {
            TypedArray typedArrayObtainStyledAttributes3 = context.obtainStyledAttributes(resourceId2, R.styleable.SkinTextAppearance);
            this.mSubtitleTextColorResId = typedArrayObtainStyledAttributes3.getResourceId(R.styleable.SkinTextAppearance_android_textColor, 0);
            typedArrayObtainStyledAttributes3.recycle();
        }
        TypedArray typedArrayObtainStyledAttributes4 = context.obtainStyledAttributes(attributeSet, R.styleable.Toolbar, i, 0);
        if (typedArrayObtainStyledAttributes4.hasValue(R.styleable.Toolbar_titleTextColor)) {
            this.mTitleTextColorResId = typedArrayObtainStyledAttributes4.getResourceId(R.styleable.Toolbar_titleTextColor, 0);
        }
        if (typedArrayObtainStyledAttributes4.hasValue(R.styleable.Toolbar_subtitleTextColor)) {
            this.mSubtitleTextColorResId = typedArrayObtainStyledAttributes4.getResourceId(R.styleable.Toolbar_subtitleTextColor, 0);
        }
        typedArrayObtainStyledAttributes4.recycle();
        applyTitleTextColor();
        applySubtitleTextColor();
        applyNavigationIcon();
    }

    private void applyTitleTextColor() {
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.mTitleTextColorResId);
        this.mTitleTextColorResId = iCheckResourceId;
        if (iCheckResourceId != 0) {
            setTitleTextColor(SkinCompatResources.getColor(getContext(), this.mTitleTextColorResId));
        }
    }

    private void applySubtitleTextColor() {
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.mSubtitleTextColorResId);
        this.mSubtitleTextColorResId = iCheckResourceId;
        if (iCheckResourceId != 0) {
            setSubtitleTextColor(SkinCompatResources.getColor(getContext(), this.mSubtitleTextColorResId));
        }
    }

    private void applyNavigationIcon() {
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.mNavigationIconResId);
        this.mNavigationIconResId = iCheckResourceId;
        if (iCheckResourceId != 0) {
            setNavigationIcon(SkinCompatVectorResources.getDrawableCompat(getContext(), this.mNavigationIconResId));
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.onSetBackgroundResource(i);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(int i) {
        super.setNavigationIcon(i);
        this.mNavigationIconResId = i;
        applyNavigationIcon();
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.applySkin();
        }
        applyTitleTextColor();
        applySubtitleTextColor();
        applyNavigationIcon();
    }
}
