package com.stfalcon.chatkit.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

/* loaded from: classes4.dex */
public class RoundedImageView extends AppCompatImageView {
    private Drawable mDrawable;
    private float[] mRadii;
    private int mResource;

    public RoundedImageView(Context context) {
        super(context);
        this.mResource = 0;
        this.mRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    }

    public RoundedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mResource = 0;
        this.mRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    }

    public RoundedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mResource = 0;
        this.mRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        this.mResource = 0;
        Drawable drawableFromDrawable = RoundedCornerDrawable.fromDrawable(drawable, getResources());
        this.mDrawable = drawableFromDrawable;
        super.setImageDrawable(drawableFromDrawable);
        updateDrawable();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bm) {
        this.mResource = 0;
        RoundedCornerDrawable roundedCornerDrawableFromBitmap = RoundedCornerDrawable.fromBitmap(bm, getResources());
        this.mDrawable = roundedCornerDrawableFromBitmap;
        super.setImageDrawable(roundedCornerDrawableFromBitmap);
        updateDrawable();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int resId) {
        if (this.mResource != resId) {
            this.mResource = resId;
            Drawable drawableResolveResource = resolveResource();
            this.mDrawable = drawableResolveResource;
            super.setImageDrawable(drawableResolveResource);
            updateDrawable();
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        setImageDrawable(getDrawable());
    }

    public void setCorners(int leftTop, int rightTop, int rightBottom, int leftBottom) {
        setCorners(leftTop == 0 ? 0.0f : getResources().getDimension(leftTop), rightTop == 0 ? 0.0f : getResources().getDimension(rightTop), rightBottom == 0 ? 0.0f : getResources().getDimension(rightBottom), leftBottom != 0 ? getResources().getDimension(leftBottom) : 0.0f);
    }

    public void setCorners(float leftTop, float rightTop, float rightBottom, float leftBottom) {
        this.mRadii = new float[]{leftTop, leftTop, rightTop, rightTop, rightBottom, rightBottom, leftBottom, leftBottom};
        updateDrawable();
    }

    private Drawable resolveResource() {
        Drawable drawable;
        if (this.mResource != 0) {
            try {
                drawable = ContextCompat.getDrawable(getContext(), this.mResource);
            } catch (Resources.NotFoundException unused) {
                this.mResource = 0;
            }
        } else {
            drawable = null;
        }
        return RoundedCornerDrawable.fromDrawable(drawable, getResources());
    }

    private void updateDrawable() {
        Drawable drawable = this.mDrawable;
        if (drawable == null) {
            return;
        }
        ((RoundedCornerDrawable) drawable).setCornerRadii(this.mRadii);
    }

    private static class RoundedCornerDrawable extends Drawable {
        private Bitmap mBitmap;
        private final int mBitmapHeight;
        private final Paint mBitmapPaint;
        private final RectF mBitmapRect;
        private final int mBitmapWidth;
        private RectF mBounds = new RectF();
        private boolean mBoundsConfigured;
        private Path mPath;
        private float[] mRadii;

        private RoundedCornerDrawable(Bitmap bitmap, Resources r) {
            RectF rectF = new RectF();
            this.mBitmapRect = rectF;
            this.mRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
            this.mPath = new Path();
            this.mBoundsConfigured = false;
            this.mBitmap = bitmap;
            BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            int scaledWidth = bitmap.getScaledWidth(r.getDisplayMetrics());
            this.mBitmapWidth = scaledWidth;
            int scaledHeight = bitmap.getScaledHeight(r.getDisplayMetrics());
            this.mBitmapHeight = scaledHeight;
            rectF.set(0.0f, 0.0f, scaledWidth, scaledHeight);
            Paint paint = new Paint(1);
            this.mBitmapPaint = paint;
            paint.setStyle(Paint.Style.FILL);
            paint.setShader(bitmapShader);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RoundedCornerDrawable fromBitmap(Bitmap bitmap, Resources r) {
            if (bitmap != null) {
                return new RoundedCornerDrawable(bitmap, r);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Drawable fromDrawable(Drawable drawable, Resources r) {
            if (drawable == null || (drawable instanceof RoundedCornerDrawable)) {
                return drawable;
            }
            if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                for (int i = 0; i < numberOfLayers; i++) {
                    layerDrawable.setDrawableByLayerId(layerDrawable.getId(i), fromDrawable(layerDrawable.getDrawable(i), r));
                }
                return layerDrawable;
            }
            Bitmap bitmapDrawableToBitmap = drawableToBitmap(drawable);
            return bitmapDrawableToBitmap != null ? new RoundedCornerDrawable(bitmapDrawableToBitmap, r) : drawable;
        }

        private static Bitmap drawableToBitmap(Drawable drawable) {
            if (drawable == null) {
                return null;
            }
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }
            try {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(Math.max(drawable.getIntrinsicWidth(), 2), Math.max(drawable.getIntrinsicHeight(), 2), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                drawable.draw(canvas);
                return bitmapCreateBitmap;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return null;
            }
        }

        private void configureBounds(Canvas canvas) {
            applyScaleToRadii(canvas.getMatrix());
            this.mBounds.set(this.mBitmapRect);
        }

        private void applyScaleToRadii(Matrix m) {
            float[] fArr = new float[9];
            m.getValues(fArr);
            int i = 0;
            while (true) {
                float[] fArr2 = this.mRadii;
                if (i >= fArr2.length) {
                    return;
                }
                fArr2[i] = fArr2[i] / fArr[0];
                i++;
            }
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            canvas.save();
            if (!this.mBoundsConfigured) {
                configureBounds(canvas);
                this.mBoundsConfigured = true;
            }
            this.mPath.addRoundRect(this.mBounds, this.mRadii, Path.Direction.CW);
            canvas.drawPath(this.mPath, this.mBitmapPaint);
            canvas.restore();
        }

        void setCornerRadii(float[] radii) {
            if (radii == null) {
                return;
            }
            if (radii.length != 8) {
                throw new ArrayIndexOutOfBoundsException("radii[] needs 8 values");
            }
            System.arraycopy(radii, 0, this.mRadii, 0, radii.length);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            Bitmap bitmap = this.mBitmap;
            return (bitmap == null || bitmap.hasAlpha() || this.mBitmapPaint.getAlpha() < 255) ? -3 : -1;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int alpha) {
            this.mBitmapPaint.setAlpha(alpha);
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter cf) {
            this.mBitmapPaint.setColorFilter(cf);
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void setDither(boolean dither) {
            this.mBitmapPaint.setDither(dither);
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void setFilterBitmap(boolean filter) {
            this.mBitmapPaint.setFilterBitmap(filter);
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return this.mBitmapWidth;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return this.mBitmapHeight;
        }
    }
}
