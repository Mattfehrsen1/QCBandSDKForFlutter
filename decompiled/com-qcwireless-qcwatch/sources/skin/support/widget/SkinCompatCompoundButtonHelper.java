package skin.support.widget;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.core.widget.CompoundButtonCompat;
import skin.support.appcompat.R;
import skin.support.content.res.SkinCompatResources;
import skin.support.content.res.SkinCompatVectorResources;

/* loaded from: classes5.dex */
public class SkinCompatCompoundButtonHelper extends SkinCompatHelper {
    private int mButtonResourceId = 0;
    private int mButtonTintResId = 0;
    private final CompoundButton mView;

    public SkinCompatCompoundButtonHelper(CompoundButton compoundButton) {
        this.mView = compoundButton;
    }

    void loadFromAttributes(AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = this.mView.getContext().obtainStyledAttributes(attributeSet, R.styleable.CompoundButton, i, 0);
        try {
            if (typedArrayObtainStyledAttributes.hasValue(R.styleable.CompoundButton_android_button)) {
                this.mButtonResourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CompoundButton_android_button, 0);
            }
            if (typedArrayObtainStyledAttributes.hasValue(R.styleable.CompoundButton_buttonTint)) {
                this.mButtonTintResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CompoundButton_buttonTint, 0);
            }
            typedArrayObtainStyledAttributes.recycle();
            applySkin();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    public void setButtonDrawable(int i) {
        this.mButtonResourceId = i;
        applySkin();
    }

    @Override // skin.support.widget.SkinCompatHelper
    public void applySkin() {
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.mButtonResourceId);
        this.mButtonResourceId = iCheckResourceId;
        if (iCheckResourceId != 0) {
            CompoundButton compoundButton = this.mView;
            compoundButton.setButtonDrawable(SkinCompatVectorResources.getDrawableCompat(compoundButton.getContext(), this.mButtonResourceId));
        }
        int iCheckResourceId2 = SkinCompatHelper.checkResourceId(this.mButtonTintResId);
        this.mButtonTintResId = iCheckResourceId2;
        if (iCheckResourceId2 != 0) {
            CompoundButton compoundButton2 = this.mView;
            CompoundButtonCompat.setButtonTintList(compoundButton2, SkinCompatResources.getColorStateList(compoundButton2.getContext(), this.mButtonTintResId));
        }
    }
}
