package skin.support.widget;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.ViewCompat;
import skin.support.R;
import skin.support.content.res.SkinCompatVectorResources;

/* loaded from: classes5.dex */
public class SkinCompatBackgroundHelper extends SkinCompatHelper {
    private int mBackgroundResId = 0;
    private final View mView;

    public SkinCompatBackgroundHelper(View view) {
        this.mView = view;
    }

    public void loadFromAttributes(AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = this.mView.getContext().obtainStyledAttributes(attributeSet, R.styleable.SkinBackgroundHelper, i, 0);
        try {
            if (typedArrayObtainStyledAttributes.hasValue(R.styleable.SkinBackgroundHelper_android_background)) {
                this.mBackgroundResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.SkinBackgroundHelper_android_background, 0);
            }
            typedArrayObtainStyledAttributes.recycle();
            applySkin();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    public void onSetBackgroundResource(int i) {
        this.mBackgroundResId = i;
        applySkin();
    }

    @Override // skin.support.widget.SkinCompatHelper
    public void applySkin() {
        Drawable drawableCompat;
        int iCheckResourceId = checkResourceId(this.mBackgroundResId);
        this.mBackgroundResId = iCheckResourceId;
        if (iCheckResourceId == 0 || (drawableCompat = SkinCompatVectorResources.getDrawableCompat(this.mView.getContext(), this.mBackgroundResId)) == null) {
            return;
        }
        int paddingLeft = this.mView.getPaddingLeft();
        int paddingTop = this.mView.getPaddingTop();
        int paddingRight = this.mView.getPaddingRight();
        int paddingBottom = this.mView.getPaddingBottom();
        ViewCompat.setBackground(this.mView, drawableCompat);
        this.mView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }
}
