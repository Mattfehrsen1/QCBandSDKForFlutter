package skin.support.design.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.google.android.material.navigation.NavigationView;
import skin.support.content.res.SkinCompatResources;
import skin.support.content.res.SkinCompatThemeUtils;
import skin.support.content.res.SkinCompatV7ThemeUtils;
import skin.support.content.res.SkinCompatVectorResources;
import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

/* loaded from: classes5.dex */
public class SkinMaterialNavigationView extends NavigationView implements SkinCompatSupportable {
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private static final int[] DISABLED_STATE_SET = {-16842910};
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private int mDefaultTintResId;
    private int mIconTintResId;
    private int mItemBackgroundResId;
    private int mTextColorResId;

    public SkinMaterialNavigationView(Context context) {
        this(context, null);
    }

    public SkinMaterialNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SkinMaterialNavigationView(Context context, AttributeSet attributeSet, int i) throws Resources.NotFoundException {
        int resourceId;
        super(context, attributeSet, i);
        this.mItemBackgroundResId = 0;
        this.mTextColorResId = 0;
        this.mDefaultTintResId = 0;
        this.mIconTintResId = 0;
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = new SkinCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = skinCompatBackgroundHelper;
        skinCompatBackgroundHelper.loadFromAttributes(attributeSet, 0);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, skin.support.design.R.styleable.NavigationView, i, skin.support.design.R.style.Widget_Design_NavigationView);
        if (typedArrayObtainStyledAttributes.hasValue(skin.support.design.R.styleable.NavigationView_itemIconTint)) {
            this.mIconTintResId = typedArrayObtainStyledAttributes.getResourceId(skin.support.design.R.styleable.NavigationView_itemIconTint, 0);
        } else {
            this.mDefaultTintResId = SkinCompatV7ThemeUtils.getColorPrimaryResId(context);
        }
        if (typedArrayObtainStyledAttributes.hasValue(skin.support.design.R.styleable.NavigationView_itemTextAppearance) && (resourceId = typedArrayObtainStyledAttributes.getResourceId(skin.support.design.R.styleable.NavigationView_itemTextAppearance, 0)) != 0) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, skin.support.design.R.styleable.SkinTextAppearance);
            if (typedArrayObtainStyledAttributes2.hasValue(skin.support.design.R.styleable.SkinTextAppearance_android_textColor)) {
                this.mTextColorResId = typedArrayObtainStyledAttributes2.getResourceId(skin.support.design.R.styleable.SkinTextAppearance_android_textColor, 0);
            }
            typedArrayObtainStyledAttributes2.recycle();
        }
        if (typedArrayObtainStyledAttributes.hasValue(skin.support.design.R.styleable.NavigationView_itemTextColor)) {
            this.mTextColorResId = typedArrayObtainStyledAttributes.getResourceId(skin.support.design.R.styleable.NavigationView_itemTextColor, 0);
        } else {
            this.mDefaultTintResId = SkinCompatV7ThemeUtils.getColorPrimaryResId(context);
        }
        if (this.mTextColorResId == 0) {
            this.mTextColorResId = SkinCompatThemeUtils.getTextColorPrimaryResId(context);
        }
        this.mItemBackgroundResId = typedArrayObtainStyledAttributes.getResourceId(skin.support.design.R.styleable.NavigationView_itemBackground, 0);
        typedArrayObtainStyledAttributes.recycle();
        applyItemIconTintResource();
        applyItemTextColorResource();
        applyItemBackgroundResource();
    }

    @Override // com.google.android.material.navigation.NavigationView
    public void setItemBackgroundResource(int i) {
        super.setItemBackgroundResource(i);
        this.mItemBackgroundResId = i;
        applyItemBackgroundResource();
    }

    private void applyItemBackgroundResource() {
        Drawable drawableCompat;
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.mItemBackgroundResId);
        this.mItemBackgroundResId = iCheckResourceId;
        if (iCheckResourceId == 0 || (drawableCompat = SkinCompatVectorResources.getDrawableCompat(getContext(), this.mItemBackgroundResId)) == null) {
            return;
        }
        setItemBackground(drawableCompat);
    }

    @Override // com.google.android.material.navigation.NavigationView
    public void setItemTextAppearance(int i) throws Resources.NotFoundException {
        super.setItemTextAppearance(i);
        if (i != 0) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(i, skin.support.design.R.styleable.SkinTextAppearance);
            if (typedArrayObtainStyledAttributes.hasValue(skin.support.design.R.styleable.SkinTextAppearance_android_textColor)) {
                this.mTextColorResId = typedArrayObtainStyledAttributes.getResourceId(skin.support.design.R.styleable.SkinTextAppearance_android_textColor, 0);
            }
            typedArrayObtainStyledAttributes.recycle();
            applyItemTextColorResource();
        }
    }

    private void applyItemTextColorResource() {
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.mTextColorResId);
        this.mTextColorResId = iCheckResourceId;
        if (iCheckResourceId != 0) {
            setItemTextColor(SkinCompatResources.getColorStateList(getContext(), this.mTextColorResId));
            return;
        }
        int iCheckResourceId2 = SkinCompatHelper.checkResourceId(this.mDefaultTintResId);
        this.mDefaultTintResId = iCheckResourceId2;
        if (iCheckResourceId2 != 0) {
            setItemTextColor(createDefaultColorStateList(R.attr.textColorPrimary));
        }
    }

    private void applyItemIconTintResource() {
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.mIconTintResId);
        this.mIconTintResId = iCheckResourceId;
        if (iCheckResourceId != 0) {
            setItemIconTintList(SkinCompatResources.getColorStateList(getContext(), this.mIconTintResId));
            return;
        }
        int iCheckResourceId2 = SkinCompatHelper.checkResourceId(this.mDefaultTintResId);
        this.mDefaultTintResId = iCheckResourceId2;
        if (iCheckResourceId2 != 0) {
            setItemIconTintList(createDefaultColorStateList(R.attr.textColorSecondary));
        }
    }

    private ColorStateList createDefaultColorStateList(int i) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateList = SkinCompatResources.getColorStateList(getContext(), typedValue.resourceId);
        int color = SkinCompatResources.getColor(getContext(), this.mDefaultTintResId);
        int defaultColor = colorStateList.getDefaultColor();
        int[] iArr = DISABLED_STATE_SET;
        return new ColorStateList(new int[][]{iArr, CHECKED_STATE_SET, EMPTY_STATE_SET}, new int[]{colorStateList.getColorForState(iArr, defaultColor), color, defaultColor});
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.applySkin();
        }
        applyItemIconTintResource();
        applyItemTextColorResource();
        applyItemBackgroundResource();
    }
}
