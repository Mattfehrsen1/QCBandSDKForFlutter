package skin.support.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import androidx.cardview.widget.CardView;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes5.dex */
public class SkinCompatCardView extends CardView implements SkinCompatSupportable {
    private static final int[] COLOR_BACKGROUND_ATTR = {R.attr.colorBackground};
    private int mBackgroundColorResId;
    private int mThemeColorBackgroundResId;

    public SkinCompatCardView(Context context) {
        this(context, null);
    }

    public SkinCompatCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SkinCompatCardView(Context context, AttributeSet attributeSet, int i) throws Resources.NotFoundException {
        super(context, attributeSet, i);
        this.mThemeColorBackgroundResId = 0;
        this.mBackgroundColorResId = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, skin.support.cardview.R.styleable.CardView, i, skin.support.cardview.R.style.CardView);
        if (typedArrayObtainStyledAttributes.hasValue(skin.support.cardview.R.styleable.CardView_cardBackgroundColor)) {
            this.mBackgroundColorResId = typedArrayObtainStyledAttributes.getResourceId(skin.support.cardview.R.styleable.CardView_cardBackgroundColor, 0);
        } else {
            TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(COLOR_BACKGROUND_ATTR);
            this.mThemeColorBackgroundResId = typedArrayObtainStyledAttributes2.getResourceId(0, 0);
            typedArrayObtainStyledAttributes2.recycle();
        }
        typedArrayObtainStyledAttributes.recycle();
        applyBackgroundColorResource();
    }

    private void applyBackgroundColorResource() throws Resources.NotFoundException {
        int color;
        this.mBackgroundColorResId = SkinCompatHelper.checkResourceId(this.mBackgroundColorResId);
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.mThemeColorBackgroundResId);
        this.mThemeColorBackgroundResId = iCheckResourceId;
        if (this.mBackgroundColorResId != 0) {
            setCardBackgroundColor(SkinCompatResources.getColorStateList(getContext(), this.mBackgroundColorResId));
            return;
        }
        if (iCheckResourceId != 0) {
            float[] fArr = new float[3];
            Color.colorToHSV(SkinCompatResources.getColor(getContext(), this.mThemeColorBackgroundResId), fArr);
            if (fArr[2] > 0.5f) {
                color = getResources().getColor(skin.support.cardview.R.color.cardview_light_background);
            } else {
                color = getResources().getColor(skin.support.cardview.R.color.cardview_dark_background);
            }
            setCardBackgroundColor(ColorStateList.valueOf(color));
        }
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() throws Resources.NotFoundException {
        applyBackgroundColorResource();
    }
}
