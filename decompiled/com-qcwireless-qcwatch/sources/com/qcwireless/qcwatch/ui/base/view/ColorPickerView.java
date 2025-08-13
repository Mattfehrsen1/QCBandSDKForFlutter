package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.util.SizeUtilsJava;
import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatSupportable;

/* loaded from: classes3.dex */
public class ColorPickerView extends View implements SkinCompatSupportable {
    private static final int defaultSizeLong = 420;
    private static final int defaultSizeShort = 70;
    private Bitmap bitmapForColor;
    private Bitmap bitmapForIndicator;
    private OnColorPickerChangeListener colorPickerChangeListener;
    private int[] colors;
    private Context context;
    public int curX;
    public int curY;
    private int currentColor;
    private boolean errorFlag;
    private LinearGradient linearGradient;
    private int mBottom;
    private int mIndicatorColor;
    private boolean mIndicatorEnable;
    private int mLeft;
    private int mRadius;
    private int mRight;
    private int mTop;
    private boolean needReDrawColorTable;
    private boolean needReDrawIndicator;
    int newRadius;
    private Orientation orientation;
    private final Paint paint;
    private final Paint paintForIndicator;
    int radius;
    private final Rect rect;
    private final Rect rectForIndicator;

    public interface OnColorPickerChangeListener {
        void onColorChanged(ColorPickerView picker, int color);

        void onErrorTouch(ColorPickerView picker);

        void onStartTrackingTouch(ColorPickerView picker);

        void onStopTrackingTouch(ColorPickerView picker);
    }

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
    }

    private void init() {
        this.newRadius = (int) SizeUtilsJava.dp2px(this.context, 8.0f);
        this.radius = (int) SizeUtilsJava.dp2px(this.context, 1.0f);
    }

    public ColorPickerView(Context context) {
        super(context);
        this.rect = new Rect();
        this.rectForIndicator = new Rect();
        this.needReDrawColorTable = true;
        this.needReDrawIndicator = true;
        this.colors = null;
        this.errorFlag = true;
        this.bitmapForColor = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        this.bitmapForIndicator = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        setLayerType(1, null);
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.paintForIndicator = paint2;
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL);
        this.curY = Integer.MAX_VALUE;
        this.curX = Integer.MAX_VALUE;
        this.context = context;
        init();
    }

    public ColorPickerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
        init();
    }

    public ColorPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.rect = new Rect();
        this.rectForIndicator = new Rect();
        this.needReDrawColorTable = true;
        this.needReDrawIndicator = true;
        this.colors = null;
        this.errorFlag = true;
        this.bitmapForColor = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        this.bitmapForIndicator = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        setLayerType(1, null);
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.paintForIndicator = paint2;
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL);
        this.curY = Integer.MAX_VALUE;
        this.curX = Integer.MAX_VALUE;
        this.context = context;
        init();
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.QColorView, defStyleAttr, 0);
        this.mIndicatorColor = typedArrayObtainStyledAttributes.getColor(0, -1);
        this.orientation = typedArrayObtainStyledAttributes.getInteger(2, 0) == 0 ? Orientation.HORIZONTAL : Orientation.VERTICAL;
        this.mIndicatorEnable = typedArrayObtainStyledAttributes.getBoolean(1, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        if (mode != 1073741824) {
            size = getSuggestedMinimumWidth() + getPaddingLeft() + getPaddingRight();
        }
        if (mode2 != 1073741824) {
            size2 = getSuggestedMinimumHeight() + getPaddingTop() + getPaddingBottom();
        }
        setMeasuredDimension(Math.max(size, this.orientation == Orientation.HORIZONTAL ? 420 : 70), Math.max(size2, this.orientation == Orientation.HORIZONTAL ? 70 : 420));
    }

    @Override // android.view.View
    protected void onLayout(boolean changed, int left, int top2, int right, int bottom) {
        super.onLayout(changed, left, top2, right, bottom);
        this.mTop = getPaddingTop();
        this.mLeft = getPaddingLeft();
        this.mBottom = getMeasuredHeight() - getPaddingBottom();
        this.mRight = getMeasuredWidth() - getPaddingRight();
        int i = this.curX;
        int i2 = this.curY;
        if (i == i2 || i2 == Integer.MAX_VALUE) {
            this.curX = getWidth() / 2;
            this.curY = getHeight() / 2;
        }
        calcBounds();
        int[] iArr = this.colors;
        if (iArr == null) {
            setColors(createDefaultColorTable());
        } else {
            setColors(iArr);
        }
        createBitmap();
        if (this.mIndicatorEnable) {
            this.needReDrawIndicator = true;
        }
    }

    private void createBitmap() {
        int iHeight = this.rect.height();
        int iWidth = this.rect.width();
        int i = this.mRadius * 2;
        Bitmap bitmap = this.bitmapForColor;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.bitmapForColor.recycle();
            this.bitmapForColor = null;
        }
        Bitmap bitmap2 = this.bitmapForIndicator;
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            this.bitmapForIndicator.recycle();
            this.bitmapForIndicator = null;
        }
        this.bitmapForColor = Bitmap.createBitmap(iWidth, iHeight, Bitmap.Config.ARGB_8888);
        this.bitmapForIndicator = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
    }

    private void calcBounds() {
        int height;
        int width;
        int height2;
        int i;
        int i2 = this.mBottom - this.mTop;
        int i3 = this.mRight - this.mLeft;
        int iMin = Math.min(i3, i2);
        if (this.orientation == Orientation.HORIZONTAL) {
            if (i3 <= i2) {
                iMin = i3 / 6;
            }
        } else if (i3 >= i2) {
            iMin = i2 / 6;
        }
        int i4 = iMin / 9;
        this.mRadius = (i4 * 7) / 2;
        int i5 = (i4 * 3) / 2;
        if (this.orientation == Orientation.HORIZONTAL) {
            int i6 = this.mLeft;
            int i7 = this.mRadius;
            i = i6 + i7;
            width = this.mRight - i7;
            height2 = (getHeight() / 2) - i5;
            height = (getHeight() / 2) + i5;
        } else {
            int i8 = this.mTop;
            int i9 = this.mRadius;
            int i10 = i8 + i9;
            height = this.mBottom - i9;
            int width2 = (getWidth() / 2) - i5;
            width = (getWidth() / 2) + i5;
            height2 = i10;
            i = width2;
        }
        this.rect.set(i, height2, width, height);
    }

    public void setColors(int... colors) {
        this.linearGradient = null;
        this.colors = colors;
        if (this.orientation == Orientation.HORIZONTAL) {
            this.linearGradient = new LinearGradient(this.rect.left, this.rect.top, this.rect.right, this.rect.top, colors, (float[]) null, Shader.TileMode.CLAMP);
        } else {
            this.linearGradient = new LinearGradient(this.rect.left, this.rect.top, this.rect.left, this.rect.bottom, colors, (float[]) null, Shader.TileMode.CLAMP);
        }
        this.needReDrawColorTable = true;
        invalidate();
    }

    public int[] createDefaultColorTable() {
        return new int[]{Color.rgb(255, 0, 0), Color.rgb(255, 255, 0), Color.rgb(0, 255, 0), Color.rgb(0, 255, 255), Color.rgb(0, 0, 255), Color.rgb(255, 0, 255), Color.rgb(255, 0, 0)};
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.needReDrawColorTable) {
            createColorTableBitmap();
        }
        canvas.drawBitmap(this.bitmapForColor, (Rect) null, this.rect, this.paint);
        this.paintForIndicator.setColor(ContextCompat.getColor(this.context, R.color.color_FFFFFF));
        canvas.drawCircle(this.curX, this.curY, this.newRadius, this.paintForIndicator);
        this.paintForIndicator.setColor(SkinCompatResources.getColor(this.context, R.color.color_FF6A00));
        canvas.drawCircle(this.curX, this.curY, this.newRadius - this.radius, this.paintForIndicator);
    }

    private void createColorTableBitmap() {
        int width;
        Canvas canvas = new Canvas(this.bitmapForColor);
        RectF rectF = new RectF(0.0f, 0.0f, this.bitmapForColor.getWidth(), this.bitmapForColor.getHeight());
        if (this.orientation == Orientation.HORIZONTAL) {
            width = this.bitmapForColor.getHeight() / 2;
        } else {
            width = this.bitmapForColor.getWidth() / 2;
        }
        this.paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        float f = width;
        canvas.drawRoundRect(rectF, f, f, this.paint);
        this.paint.setShader(this.linearGradient);
        canvas.drawRoundRect(rectF, f, f, this.paint);
        this.paint.setShader(null);
        this.needReDrawColorTable = false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (!inBoundOfColorTable(x, y)) {
            if (this.errorFlag) {
                this.errorFlag = false;
                if (this.colorPickerChangeListener != null) {
                    calcuColor();
                    this.colorPickerChangeListener.onColorChanged(this, this.currentColor);
                    this.colorPickerChangeListener.onErrorTouch(this);
                }
            }
            return true;
        }
        this.errorFlag = true;
        if (this.orientation == Orientation.HORIZONTAL) {
            this.curX = x;
            this.curY = getHeight() / 2;
        } else {
            this.curX = getWidth() / 2;
            this.curY = y;
        }
        if (event.getAction() == 0) {
            getParent().requestDisallowInterceptTouchEvent(true);
            OnColorPickerChangeListener onColorPickerChangeListener = this.colorPickerChangeListener;
            if (onColorPickerChangeListener != null) {
                onColorPickerChangeListener.onStartTrackingTouch(this);
                calcuColor();
                this.colorPickerChangeListener.onColorChanged(this, this.currentColor);
            }
        } else if (event.getAction() == 1) {
            OnColorPickerChangeListener onColorPickerChangeListener2 = this.colorPickerChangeListener;
            if (onColorPickerChangeListener2 != null) {
                onColorPickerChangeListener2.onStopTrackingTouch(this);
                calcuColor();
                this.colorPickerChangeListener.onColorChanged(this, this.currentColor);
            }
        } else if (this.colorPickerChangeListener != null) {
            calcuColor();
            this.colorPickerChangeListener.onColorChanged(this, this.currentColor);
        }
        invalidate();
        return true;
    }

    public int getColor() {
        return calcuColor();
    }

    private boolean inBoundOfColorTable(int ex, int ey) {
        if (this.orientation == Orientation.HORIZONTAL) {
            int i = this.mLeft;
            int i2 = this.mRadius;
            return ex > i + i2 && ex < this.mRight - i2;
        }
        int i3 = this.mTop;
        int i4 = this.mRadius;
        return ey > i3 + i4 && ey < this.mBottom - i4;
    }

    private int calcuColor() {
        int height;
        int i;
        int width = 1;
        if (this.orientation == Orientation.HORIZONTAL) {
            i = (this.rect.bottom - this.rect.top) / 2;
            if (this.curX >= this.rect.left) {
                if (this.curX > this.rect.right) {
                    width = this.bitmapForColor.getWidth() - 1;
                } else {
                    width = this.curX - this.rect.left;
                }
            }
        } else {
            int i2 = (this.rect.right - this.rect.left) / 2;
            if (this.curY < this.rect.top) {
                width = i2;
                i = 1;
            } else {
                if (this.curY > this.rect.bottom) {
                    height = this.bitmapForColor.getHeight() - 1;
                } else {
                    height = this.curY - this.rect.top;
                }
                int i3 = height;
                width = i2;
                i = i3;
            }
        }
        int iPixelToColor = pixelToColor(this.bitmapForColor.getPixel(width, i));
        this.currentColor = iPixelToColor;
        return iPixelToColor;
    }

    private int pixelToColor(int pixel) {
        return Color.argb(Color.alpha(pixel), Color.red(pixel), Color.green(pixel), Color.blue(pixel));
    }

    public void setOnColorPickerChangeListener(OnColorPickerChangeListener l) {
        this.colorPickerChangeListener = l;
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.selX = this.curX;
        savedState.selY = this.curY;
        savedState.color = this.bitmapForColor;
        if (this.mIndicatorEnable) {
            savedState.indicator = this.bitmapForIndicator;
        }
        return savedState;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.curX = savedState.selX;
        this.curY = savedState.selY;
        this.colors = savedState.colors;
        this.bitmapForColor = savedState.color;
        if (this.mIndicatorEnable) {
            this.bitmapForIndicator = savedState.indicator;
            this.needReDrawIndicator = true;
        }
        this.needReDrawColorTable = true;
    }

    private class SavedState extends View.BaseSavedState {
        Bitmap color;
        int[] colors;
        Bitmap indicator;
        int selX;
        int selY;

        SavedState(Parcelable source) {
            super(source);
            this.indicator = null;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            try {
                out.writeInt(this.selX);
                out.writeInt(this.selY);
                out.writeParcelable(this.color, flags);
                out.writeIntArray(this.colors);
                Bitmap bitmap = this.indicator;
                if (bitmap != null) {
                    out.writeParcelable(bitmap, flags);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setPosition(int x, int y) {
        if (inBoundOfColorTable(x, y)) {
            this.curX = x;
            this.curY = y;
            if (this.mIndicatorEnable) {
                this.needReDrawIndicator = true;
            }
            invalidate();
        }
    }

    public void showDefaultColorTable() {
        setColors(createDefaultColorTable());
    }

    public int getIndicatorColor() {
        return this.mIndicatorColor;
    }

    public void setIndicatorColor(int color) {
        this.mIndicatorColor = color;
        this.needReDrawIndicator = true;
        invalidate();
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
        this.needReDrawIndicator = true;
        this.needReDrawColorTable = true;
        requestLayout();
    }
}
