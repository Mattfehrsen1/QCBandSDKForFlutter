package skin.support.widget;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.SeekBar;
import skin.support.appcompat.R;
import skin.support.content.res.SkinCompatVectorResources;

/* loaded from: classes5.dex */
public class SkinCompatSeekBarHelper extends SkinCompatProgressBarHelper {
    private int mThumbResId;
    private final SeekBar mView;

    public SkinCompatSeekBarHelper(SeekBar seekBar) {
        super(seekBar);
        this.mThumbResId = 0;
        this.mView = seekBar;
    }

    @Override // skin.support.widget.SkinCompatProgressBarHelper
    void loadFromAttributes(AttributeSet attributeSet, int i) {
        super.loadFromAttributes(attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = this.mView.getContext().obtainStyledAttributes(attributeSet, R.styleable.AppCompatSeekBar, i, 0);
        this.mThumbResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.AppCompatSeekBar_android_thumb, 0);
        typedArrayObtainStyledAttributes.recycle();
        applySkin();
    }

    @Override // skin.support.widget.SkinCompatProgressBarHelper, skin.support.widget.SkinCompatHelper
    public void applySkin() {
        super.applySkin();
        int iCheckResourceId = checkResourceId(this.mThumbResId);
        this.mThumbResId = iCheckResourceId;
        if (iCheckResourceId != 0) {
            SeekBar seekBar = this.mView;
            seekBar.setThumb(SkinCompatVectorResources.getDrawableCompat(seekBar.getContext(), this.mThumbResId));
        }
    }
}
