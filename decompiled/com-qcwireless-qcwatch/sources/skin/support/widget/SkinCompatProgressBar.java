package skin.support.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* loaded from: classes5.dex */
public class SkinCompatProgressBar extends ProgressBar implements SkinCompatSupportable {
    private SkinCompatProgressBarHelper mSkinCompatProgressBarHelper;

    public SkinCompatProgressBar(Context context) {
        this(context, null);
    }

    public SkinCompatProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.progressBarStyle);
    }

    public SkinCompatProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        SkinCompatProgressBarHelper skinCompatProgressBarHelper = new SkinCompatProgressBarHelper(this);
        this.mSkinCompatProgressBarHelper = skinCompatProgressBarHelper;
        skinCompatProgressBarHelper.loadFromAttributes(attributeSet, i);
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        SkinCompatProgressBarHelper skinCompatProgressBarHelper = this.mSkinCompatProgressBarHelper;
        if (skinCompatProgressBarHelper != null) {
            skinCompatProgressBarHelper.applySkin();
        }
    }
}
