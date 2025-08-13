package skin.support.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatRatingBar;
import skin.support.appcompat.R;

/* loaded from: classes5.dex */
public class SkinCompatRatingBar extends AppCompatRatingBar implements SkinCompatSupportable {
    private SkinCompatProgressBarHelper mSkinCompatProgressBarHelper;

    public SkinCompatRatingBar(Context context) {
        this(context, null);
    }

    public SkinCompatRatingBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.ratingBarStyle);
    }

    public SkinCompatRatingBar(Context context, AttributeSet attributeSet, int i) {
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
