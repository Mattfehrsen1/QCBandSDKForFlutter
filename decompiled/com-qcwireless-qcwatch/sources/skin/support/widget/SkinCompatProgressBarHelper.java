package skin.support.widget;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.baidu.location.LocationClientOption;
import java.lang.reflect.InvocationTargetException;
import skin.support.appcompat.R;
import skin.support.content.res.SkinCompatResources;
import skin.support.content.res.SkinCompatVectorResources;
import skin.support.utils.SkinCompatVersionUtils;

/* loaded from: classes5.dex */
public class SkinCompatProgressBarHelper extends SkinCompatHelper {
    private Bitmap mSampleTile;
    private final ProgressBar mView;
    private int mIndeterminateDrawableResId = 0;
    private int mProgressDrawableResId = 0;
    private int mIndeterminateTintResId = 0;

    SkinCompatProgressBarHelper(ProgressBar progressBar) {
        this.mView = progressBar;
    }

    void loadFromAttributes(AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = this.mView.getContext().obtainStyledAttributes(attributeSet, R.styleable.SkinCompatProgressBar, i, 0);
        this.mIndeterminateDrawableResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.SkinCompatProgressBar_android_indeterminateDrawable, 0);
        this.mProgressDrawableResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.SkinCompatProgressBar_android_progressDrawable, 0);
        typedArrayObtainStyledAttributes.recycle();
        if (Build.VERSION.SDK_INT > 21) {
            TypedArray typedArrayObtainStyledAttributes2 = this.mView.getContext().obtainStyledAttributes(attributeSet, new int[]{android.R.attr.indeterminateTint}, i, 0);
            this.mIndeterminateTintResId = typedArrayObtainStyledAttributes2.getResourceId(0, 0);
            typedArrayObtainStyledAttributes2.recycle();
        }
        applySkin();
    }

    private Drawable tileify(Drawable drawable, boolean z) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (SkinCompatVersionUtils.isV4WrappedDrawable(drawable)) {
            Drawable v4WrappedDrawableWrappedDrawable = SkinCompatVersionUtils.getV4WrappedDrawableWrappedDrawable(drawable);
            if (v4WrappedDrawableWrappedDrawable != null) {
                SkinCompatVersionUtils.setV4WrappedDrawableWrappedDrawable(drawable, tileify(v4WrappedDrawableWrappedDrawable, z));
            }
        } else if (SkinCompatVersionUtils.isV4DrawableWrapper(drawable)) {
            Drawable v4DrawableWrapperWrappedDrawable = SkinCompatVersionUtils.getV4DrawableWrapperWrappedDrawable(drawable);
            if (v4DrawableWrapperWrappedDrawable != null) {
                SkinCompatVersionUtils.setV4DrawableWrapperWrappedDrawable(drawable, tileify(v4DrawableWrapperWrappedDrawable, z));
            }
        } else {
            if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                Drawable[] drawableArr = new Drawable[numberOfLayers];
                for (int i = 0; i < numberOfLayers; i++) {
                    int id = layerDrawable.getId(i);
                    drawableArr[i] = tileify(layerDrawable.getDrawable(i), id == 16908301 || id == 16908303);
                }
                LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
                for (int i2 = 0; i2 < numberOfLayers; i2++) {
                    layerDrawable2.setId(i2, layerDrawable.getId(i2));
                }
                return layerDrawable2;
            }
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                if (this.mSampleTile == null) {
                    this.mSampleTile = bitmap;
                }
                ShapeDrawable shapeDrawable = new ShapeDrawable(getDrawableShape());
                shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
                shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
                return z ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
            }
        }
        return drawable;
    }

    private Drawable tileifyIndeterminate(Drawable drawable) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (!(drawable instanceof AnimationDrawable)) {
            return drawable;
        }
        AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
        int numberOfFrames = animationDrawable.getNumberOfFrames();
        AnimationDrawable animationDrawable2 = new AnimationDrawable();
        animationDrawable2.setOneShot(animationDrawable.isOneShot());
        for (int i = 0; i < numberOfFrames; i++) {
            Drawable drawableTileify = tileify(animationDrawable.getFrame(i), true);
            drawableTileify.setLevel(LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL);
            animationDrawable2.addFrame(drawableTileify, animationDrawable.getDuration(i));
        }
        animationDrawable2.setLevel(LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL);
        return animationDrawable2;
    }

    private Shape getDrawableShape() {
        return new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null);
    }

    @Override // skin.support.widget.SkinCompatHelper
    public void applySkin() {
        int iCheckResourceId = checkResourceId(this.mIndeterminateDrawableResId);
        this.mIndeterminateDrawableResId = iCheckResourceId;
        if (iCheckResourceId != 0) {
            Drawable drawableCompat = SkinCompatVectorResources.getDrawableCompat(this.mView.getContext(), this.mIndeterminateDrawableResId);
            drawableCompat.setBounds(this.mView.getIndeterminateDrawable().getBounds());
            this.mView.setIndeterminateDrawable(tileifyIndeterminate(drawableCompat));
        }
        int iCheckProgressDrawableResId = checkProgressDrawableResId(this.mProgressDrawableResId);
        this.mProgressDrawableResId = iCheckProgressDrawableResId;
        if (iCheckProgressDrawableResId != 0) {
            this.mView.setProgressDrawable(tileify(SkinCompatVectorResources.getDrawableCompat(this.mView.getContext(), this.mProgressDrawableResId), false));
        }
        if (Build.VERSION.SDK_INT > 21) {
            int iCheckResourceId2 = checkResourceId(this.mIndeterminateTintResId);
            this.mIndeterminateTintResId = iCheckResourceId2;
            if (iCheckResourceId2 != 0) {
                ProgressBar progressBar = this.mView;
                progressBar.setIndeterminateTintList(SkinCompatResources.getColorStateList(progressBar.getContext(), this.mIndeterminateTintResId));
            }
        }
    }

    private int checkProgressDrawableResId(int i) {
        return checkResourceId(i);
    }
}
