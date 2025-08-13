package skin.support.widget;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.core.widget.ImageViewCompat;
import skin.support.R;
import skin.support.content.res.SkinCompatResources;
import skin.support.content.res.SkinCompatVectorResources;

/* loaded from: classes5.dex */
public class SkinCompatImageHelper extends SkinCompatHelper {
    private static final String TAG = "SkinCompatImageHelper";
    private final ImageView mView;
    private int mSrcResId = 0;
    private int mSrcCompatResId = 0;
    private int mSrcTintResId = 0;

    public SkinCompatImageHelper(ImageView imageView) {
        this.mView = imageView;
    }

    public void loadFromAttributes(AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = null;
        try {
            typedArrayObtainStyledAttributes = this.mView.getContext().obtainStyledAttributes(attributeSet, R.styleable.SkinCompatImageView, i, 0);
            this.mSrcResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.SkinCompatImageView_android_src, 0);
            this.mSrcCompatResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.SkinCompatImageView_srcCompat, 0);
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.SkinCompatImageView_tint, 0);
            this.mSrcTintResId = resourceId;
            if (resourceId == 0) {
                this.mSrcTintResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.SkinCompatImageView_android_tint, 0);
            }
            applySkin();
        } finally {
            if (typedArrayObtainStyledAttributes != null) {
                typedArrayObtainStyledAttributes.recycle();
            }
        }
    }

    public void setImageResource(int i) {
        this.mSrcResId = i;
        this.mSrcCompatResId = 0;
        applySkin();
    }

    @Override // skin.support.widget.SkinCompatHelper
    public void applySkin() {
        Drawable drawableCompat;
        int iCheckResourceId = checkResourceId(this.mSrcCompatResId);
        this.mSrcCompatResId = iCheckResourceId;
        if (iCheckResourceId != 0) {
            Drawable drawableCompat2 = SkinCompatVectorResources.getDrawableCompat(this.mView.getContext(), this.mSrcCompatResId);
            if (drawableCompat2 != null) {
                this.mView.setImageDrawable(drawableCompat2);
            }
        } else {
            int iCheckResourceId2 = checkResourceId(this.mSrcResId);
            this.mSrcResId = iCheckResourceId2;
            if (iCheckResourceId2 != 0 && (drawableCompat = SkinCompatVectorResources.getDrawableCompat(this.mView.getContext(), this.mSrcResId)) != null) {
                this.mView.setImageDrawable(drawableCompat);
            }
        }
        int iCheckResourceId3 = checkResourceId(this.mSrcTintResId);
        this.mSrcTintResId = iCheckResourceId3;
        if (iCheckResourceId3 != 0) {
            ImageViewCompat.setImageTintList(this.mView, SkinCompatResources.getColorStateList(this.mView.getContext(), this.mSrcTintResId));
        }
    }
}
