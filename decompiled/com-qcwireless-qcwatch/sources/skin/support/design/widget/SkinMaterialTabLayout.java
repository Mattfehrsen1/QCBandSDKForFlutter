package skin.support.design.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.tabs.TabLayout;
import skin.support.content.res.SkinCompatResources;
import skin.support.design.R;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

/* loaded from: classes5.dex */
public class SkinMaterialTabLayout extends TabLayout implements SkinCompatSupportable {
    private int mTabIndicatorColorResId;
    private int mTabSelectedTextColorResId;
    private int mTabTextColorsResId;

    public SkinMaterialTabLayout(Context context) {
        this(context, null);
    }

    public SkinMaterialTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SkinMaterialTabLayout(Context context, AttributeSet attributeSet, int i) throws Resources.NotFoundException {
        super(context, attributeSet, i);
        this.mTabIndicatorColorResId = 0;
        this.mTabTextColorsResId = 0;
        this.mTabSelectedTextColorResId = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TabLayout, i, 0);
        this.mTabIndicatorColorResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.TabLayout_tabIndicatorColor, 0);
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(typedArrayObtainStyledAttributes.getResourceId(R.styleable.TabLayout_tabTextAppearance, R.style.TextAppearance_Design_Tab), R.styleable.SkinTextAppearance);
        try {
            this.mTabTextColorsResId = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.SkinTextAppearance_android_textColor, 0);
            typedArrayObtainStyledAttributes2.recycle();
            if (typedArrayObtainStyledAttributes.hasValue(R.styleable.TabLayout_tabTextColor)) {
                this.mTabTextColorsResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.TabLayout_tabTextColor, 0);
            }
            if (typedArrayObtainStyledAttributes.hasValue(R.styleable.TabLayout_tabSelectedTextColor)) {
                this.mTabSelectedTextColorResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.TabLayout_tabSelectedTextColor, 0);
            }
            typedArrayObtainStyledAttributes.recycle();
            applySkin();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes2.recycle();
            throw th;
        }
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.mTabIndicatorColorResId);
        this.mTabIndicatorColorResId = iCheckResourceId;
        if (iCheckResourceId != 0) {
            setSelectedTabIndicatorColor(SkinCompatResources.getColor(getContext(), this.mTabIndicatorColorResId));
        }
        int iCheckResourceId2 = SkinCompatHelper.checkResourceId(this.mTabTextColorsResId);
        this.mTabTextColorsResId = iCheckResourceId2;
        if (iCheckResourceId2 != 0) {
            setTabTextColors(SkinCompatResources.getColorStateList(getContext(), this.mTabTextColorsResId));
        }
        int iCheckResourceId3 = SkinCompatHelper.checkResourceId(this.mTabSelectedTextColorResId);
        this.mTabSelectedTextColorResId = iCheckResourceId3;
        if (iCheckResourceId3 != 0) {
            int color = SkinCompatResources.getColor(getContext(), this.mTabSelectedTextColorResId);
            if (getTabTextColors() != null) {
                setTabTextColors(getTabTextColors().getDefaultColor(), color);
            }
        }
    }
}
