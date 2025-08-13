package skin.support.design.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.material.appbar.AppBarLayout;
import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatSupportable;

/* loaded from: classes5.dex */
public class SkinMaterialAppBarLayout extends AppBarLayout implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;

    public SkinMaterialAppBarLayout(Context context) {
        this(context, null);
    }

    public SkinMaterialAppBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = new SkinCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = skinCompatBackgroundHelper;
        skinCompatBackgroundHelper.loadFromAttributes(attributeSet, 0);
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.applySkin();
        }
    }
}
