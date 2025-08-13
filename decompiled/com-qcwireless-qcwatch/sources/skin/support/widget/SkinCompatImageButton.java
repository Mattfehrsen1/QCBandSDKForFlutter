package skin.support.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.R;
import androidx.appcompat.widget.AppCompatImageButton;

/* loaded from: classes5.dex */
public class SkinCompatImageButton extends AppCompatImageButton implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private SkinCompatImageHelper mImageHelper;

    public SkinCompatImageButton(Context context) {
        this(context, null);
    }

    public SkinCompatImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.imageButtonStyle);
    }

    public SkinCompatImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = new SkinCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = skinCompatBackgroundHelper;
        skinCompatBackgroundHelper.loadFromAttributes(attributeSet, i);
        SkinCompatImageHelper skinCompatImageHelper = new SkinCompatImageHelper(this);
        this.mImageHelper = skinCompatImageHelper;
        skinCompatImageHelper.loadFromAttributes(attributeSet, i);
    }

    @Override // androidx.appcompat.widget.AppCompatImageButton, android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.onSetBackgroundResource(i);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageButton, android.widget.ImageView
    public void setImageResource(int i) {
        SkinCompatImageHelper skinCompatImageHelper = this.mImageHelper;
        if (skinCompatImageHelper != null) {
            skinCompatImageHelper.setImageResource(i);
        }
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.applySkin();
        }
        SkinCompatImageHelper skinCompatImageHelper = this.mImageHelper;
        if (skinCompatImageHelper != null) {
            skinCompatImageHelper.applySkin();
        }
    }
}
