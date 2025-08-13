package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public class CircleImageView extends ImageView {
    private static final int COLORDRAWABLE_DIMENSION = 2;
    private static final int DEFAULT_BORDER_COLOR = -16777216;
    private static final boolean DEFAULT_BORDER_OVERLAY = false;
    private static final int DEFAULT_BORDER_WIDTH = 0;
    private Bitmap mBitmap;
    private int mBitmapHeight;
    private final Paint mBitmapPaint;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private int mBorderColor;
    private boolean mBorderOverlay;
    private final Paint mBorderPaint;
    private float mBorderRadius;
    private final RectF mBorderRect;
    private int mBorderWidth;
    private ColorFilter mColorFilter;
    private float mDrawableRadius;
    private final RectF mDrawableRect;
    private boolean mReady;
    private boolean mSetupPending;
    private final Matrix mShaderMatrix;
    private static final ImageView.ScaleType SCALE_TYPE = ImageView.ScaleType.CENTER_CROP;
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;

    public CircleImageView(Context context) {
        super(context);
        this.mDrawableRect = new RectF();
        this.mBorderRect = new RectF();
        this.mShaderMatrix = new Matrix();
        this.mBitmapPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mBorderColor = -16777216;
        this.mBorderWidth = 0;
        init();
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mDrawableRect = new RectF();
        this.mBorderRect = new RectF();
        this.mShaderMatrix = new Matrix();
        this.mBitmapPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mBorderColor = -16777216;
        this.mBorderWidth = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView, defStyle, 0);
        this.mBorderWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, 0);
        this.mBorderColor = typedArrayObtainStyledAttributes.getColor(0, -16777216);
        this.mBorderOverlay = typedArrayObtainStyledAttributes.getBoolean(1, false);
        typedArrayObtainStyledAttributes.recycle();
        init();
    }

    private void init() {
        super.setScaleType(SCALE_TYPE);
        this.mReady = true;
        if (this.mSetupPending) {
            setup();
            this.mSetupPending = false;
        }
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return SCALE_TYPE;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType != SCALE_TYPE) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", scaleType));
        }
    }

    @Override // android.widget.ImageView
    public void setAdjustViewBounds(boolean adjustViewBounds) {
        if (adjustViewBounds) {
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, this.mDrawableRadius, this.mBitmapPaint);
        if (this.mBorderWidth != 0) {
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, this.mBorderRadius, this.mBorderPaint);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setup();
    }

    public int getBorderColor() {
        return this.mBorderColor;
    }

    public void setBorderColor(int borderColor) {
        if (borderColor == this.mBorderColor) {
            return;
        }
        this.mBorderColor = borderColor;
        this.mBorderPaint.setColor(borderColor);
        invalidate();
    }

    public void setBorderColorResource(int borderColorRes) {
        setBorderColor(getContext().getResources().getColor(borderColorRes));
    }

    public int getBorderWidth() {
        return this.mBorderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        if (borderWidth == this.mBorderWidth) {
            return;
        }
        this.mBorderWidth = borderWidth;
        setup();
    }

    public boolean isBorderOverlay() {
        return this.mBorderOverlay;
    }

    public void setBorderOverlay(boolean borderOverlay) {
        if (borderOverlay == this.mBorderOverlay) {
            return;
        }
        this.mBorderOverlay = borderOverlay;
        setup();
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        this.mBitmap = bm;
        setup();
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        this.mBitmap = getBitmapFromDrawable(drawable);
        setup();
    }

    @Override // android.widget.ImageView
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        this.mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        this.mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    @Override // android.widget.ImageView
    public void setColorFilter(ColorFilter cf) {
        if (cf == this.mColorFilter) {
            return;
        }
        this.mColorFilter = cf;
        this.mBitmapPaint.setColorFilter(cf);
        invalidate();
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        Bitmap bitmapCreateBitmap;
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            if (drawable instanceof ColorDrawable) {
                bitmapCreateBitmap = Bitmap.createBitmap(2, 2, BITMAP_CONFIG);
            } else {
                bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmapCreateBitmap;
        } catch (OutOfMemoryError unused) {
            return null;
        }
    }

    private void setup() {
        if (!this.mReady) {
            this.mSetupPending = true;
            return;
        }
        if (this.mBitmap == null) {
            return;
        }
        this.mBitmapShader = new BitmapShader(this.mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        this.mBitmapPaint.setAntiAlias(true);
        this.mBitmapPaint.setShader(this.mBitmapShader);
        this.mBorderPaint.setStyle(Paint.Style.STROKE);
        this.mBorderPaint.setAntiAlias(true);
        this.mBorderPaint.setColor(this.mBorderColor);
        this.mBorderPaint.setStrokeWidth(this.mBorderWidth);
        this.mBitmapHeight = this.mBitmap.getHeight();
        this.mBitmapWidth = this.mBitmap.getWidth();
        this.mBorderRect.set(0.0f, 0.0f, getWidth(), getHeight());
        this.mBorderRadius = Math.min((this.mBorderRect.height() - this.mBorderWidth) / 2.0f, (this.mBorderRect.width() - this.mBorderWidth) / 2.0f);
        this.mDrawableRect.set(this.mBorderRect);
        if (!this.mBorderOverlay) {
            RectF rectF = this.mDrawableRect;
            int i = this.mBorderWidth;
            rectF.inset(i, i);
        }
        this.mDrawableRadius = Math.min(this.mDrawableRect.height() / 2.0f, this.mDrawableRect.width() / 2.0f);
        updateShaderMatrix();
        invalidate();
    }

    private void updateShaderMatrix() {
        float fWidth;
        float fHeight;
        this.mShaderMatrix.set(null);
        float fWidth2 = 0.0f;
        if (this.mBitmapWidth * this.mDrawableRect.height() > this.mDrawableRect.width() * this.mBitmapHeight) {
            fWidth = this.mDrawableRect.height() / this.mBitmapHeight;
            fWidth2 = (this.mDrawableRect.width() - (this.mBitmapWidth * fWidth)) * 0.5f;
            fHeight = 0.0f;
        } else {
            fWidth = this.mDrawableRect.width() / this.mBitmapWidth;
            fHeight = (this.mDrawableRect.height() - (this.mBitmapHeight * fWidth)) * 0.5f;
        }
        this.mShaderMatrix.setScale(fWidth, fWidth);
        this.mShaderMatrix.postTranslate(((int) (fWidth2 + 0.5f)) + this.mDrawableRect.left, ((int) (fHeight + 0.5f)) + this.mDrawableRect.top);
        this.mBitmapShader.setLocalMatrix(this.mShaderMatrix);
    }
}
