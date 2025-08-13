package skin.support.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatRadioButton;
import skin.support.appcompat.R;

/* loaded from: classes5.dex */
public class SkinCompatRadioButton extends AppCompatRadioButton implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private SkinCompatCompoundButtonHelper mCompoundButtonHelper;
    private SkinCompatTextHelper mTextHelper;

    public SkinCompatRadioButton(Context context) {
        this(context, null);
    }

    public SkinCompatRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.radioButtonStyle);
    }

    public SkinCompatRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        SkinCompatCompoundButtonHelper skinCompatCompoundButtonHelper = new SkinCompatCompoundButtonHelper(this);
        this.mCompoundButtonHelper = skinCompatCompoundButtonHelper;
        skinCompatCompoundButtonHelper.loadFromAttributes(attributeSet, i);
        SkinCompatTextHelper skinCompatTextHelperCreate = SkinCompatTextHelper.create(this);
        this.mTextHelper = skinCompatTextHelperCreate;
        skinCompatTextHelperCreate.loadFromAttributes(attributeSet, i);
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = new SkinCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = skinCompatBackgroundHelper;
        skinCompatBackgroundHelper.loadFromAttributes(attributeSet, i);
    }

    @Override // androidx.appcompat.widget.AppCompatRadioButton, android.widget.CompoundButton
    public void setButtonDrawable(int i) {
        super.setButtonDrawable(i);
        SkinCompatCompoundButtonHelper skinCompatCompoundButtonHelper = this.mCompoundButtonHelper;
        if (skinCompatCompoundButtonHelper != null) {
            skinCompatCompoundButtonHelper.setButtonDrawable(i);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i) throws Resources.NotFoundException {
        setTextAppearance(getContext(), i);
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) throws Resources.NotFoundException {
        super.setTextAppearance(context, i);
        SkinCompatTextHelper skinCompatTextHelper = this.mTextHelper;
        if (skinCompatTextHelper != null) {
            skinCompatTextHelper.onSetTextAppearance(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(i, i2, i3, i4);
        SkinCompatTextHelper skinCompatTextHelper = this.mTextHelper;
        if (skinCompatTextHelper != null) {
            skinCompatTextHelper.onSetCompoundDrawablesRelativeWithIntrinsicBounds(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        super.setCompoundDrawablesWithIntrinsicBounds(i, i2, i3, i4);
        SkinCompatTextHelper skinCompatTextHelper = this.mTextHelper;
        if (skinCompatTextHelper != null) {
            skinCompatTextHelper.onSetCompoundDrawablesWithIntrinsicBounds(i, i2, i3, i4);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatRadioButton, android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.onSetBackgroundResource(i);
        }
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.applySkin();
        }
        SkinCompatCompoundButtonHelper skinCompatCompoundButtonHelper = this.mCompoundButtonHelper;
        if (skinCompatCompoundButtonHelper != null) {
            skinCompatCompoundButtonHelper.applySkin();
        }
        SkinCompatTextHelper skinCompatTextHelper = this.mTextHelper;
        if (skinCompatTextHelper != null) {
            skinCompatTextHelper.applySkin();
        }
    }
}
