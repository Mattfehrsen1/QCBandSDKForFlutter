package com.king.zxing;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import androidx.core.content.ContextCompat;

/* loaded from: classes3.dex */
public class ViewfinderView extends View {
    private static final int CURRENT_POINT_OPACITY = 160;
    private static final int MAX_RESULT_POINTS = 20;
    private static final int POINT_SIZE = 30;
    private int cornerColor;
    private int cornerRectHeight;
    private int cornerRectWidth;
    private Rect frame;
    private int frameColor;
    private FrameGravity frameGravity;
    private int frameHeight;
    private int frameLineWidth;
    private float framePaddingBottom;
    private float framePaddingLeft;
    private float framePaddingRight;
    private float framePaddingTop;
    private float frameRatio;
    private int frameWidth;
    private int gridColumn;
    private int gridHeight;
    private String labelText;
    private int labelTextColor;
    private TextLocation labelTextLocation;
    private float labelTextPadding;
    private float labelTextSize;
    private int labelTextWidth;
    private int laserColor;
    private LaserStyle laserStyle;
    private int maskColor;
    private Paint paint;
    private Point point;
    private int pointColor;
    private float pointRadius;
    private int pointStrokeColor;
    private float pointStrokeRatio;
    private int scannerAnimationDelay;
    public int scannerEnd;
    private int scannerLineHeight;
    private int scannerLineMoveDistance;
    public int scannerStart;
    private TextPaint textPaint;

    public enum LaserStyle {
        NONE(0),
        LINE(1),
        GRID(2);

        private int mValue;

        LaserStyle(int i) {
            this.mValue = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static LaserStyle getFromInt(int i) {
            for (LaserStyle laserStyle : values()) {
                if (laserStyle.mValue == i) {
                    return laserStyle;
                }
            }
            return LINE;
        }
    }

    public enum TextLocation {
        TOP(0),
        BOTTOM(1);

        private int mValue;

        TextLocation(int i) {
            this.mValue = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static TextLocation getFromInt(int i) {
            for (TextLocation textLocation : values()) {
                if (textLocation.mValue == i) {
                    return textLocation;
                }
            }
            return TOP;
        }
    }

    public enum FrameGravity {
        CENTER(0),
        LEFT(1),
        TOP(2),
        RIGHT(3),
        BOTTOM(4);

        private int mValue;

        FrameGravity(int i) {
            this.mValue = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static FrameGravity getFromInt(int i) {
            for (FrameGravity frameGravity : values()) {
                if (frameGravity.mValue == i) {
                    return frameGravity;
                }
            }
            return CENTER;
        }
    }

    public ViewfinderView(Context context) {
        this(context, null);
    }

    public ViewfinderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ViewfinderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.scannerStart = 0;
        this.scannerEnd = 0;
        this.pointStrokeRatio = 1.2f;
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ViewfinderView);
        this.maskColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ViewfinderView_maskColor, ContextCompat.getColor(context, R.color.viewfinder_mask));
        this.frameColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ViewfinderView_frameColor, ContextCompat.getColor(context, R.color.viewfinder_frame));
        this.cornerColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ViewfinderView_cornerColor, ContextCompat.getColor(context, R.color.viewfinder_corner));
        this.laserColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ViewfinderView_laserColor, ContextCompat.getColor(context, R.color.viewfinder_laser));
        this.labelText = typedArrayObtainStyledAttributes.getString(R.styleable.ViewfinderView_labelText);
        this.labelTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ViewfinderView_labelTextColor, ContextCompat.getColor(context, R.color.viewfinder_text_color));
        this.labelTextSize = typedArrayObtainStyledAttributes.getDimension(R.styleable.ViewfinderView_labelTextSize, TypedValue.applyDimension(2, 14.0f, getResources().getDisplayMetrics()));
        this.labelTextPadding = typedArrayObtainStyledAttributes.getDimension(R.styleable.ViewfinderView_labelTextPadding, TypedValue.applyDimension(1, 24.0f, getResources().getDisplayMetrics()));
        this.labelTextWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ViewfinderView_labelTextWidth, 0);
        this.labelTextLocation = TextLocation.getFromInt(typedArrayObtainStyledAttributes.getInt(R.styleable.ViewfinderView_labelTextLocation, 0));
        this.frameWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ViewfinderView_frameWidth, 0);
        this.frameHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ViewfinderView_frameHeight, 0);
        this.laserStyle = LaserStyle.getFromInt(typedArrayObtainStyledAttributes.getInt(R.styleable.ViewfinderView_laserStyle, LaserStyle.LINE.mValue));
        this.gridColumn = typedArrayObtainStyledAttributes.getInt(R.styleable.ViewfinderView_gridColumn, 20);
        this.gridHeight = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.ViewfinderView_gridHeight, TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics()));
        this.cornerRectWidth = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.ViewfinderView_cornerRectWidth, TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics()));
        this.cornerRectHeight = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.ViewfinderView_cornerRectHeight, TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics()));
        this.scannerLineMoveDistance = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.ViewfinderView_scannerLineMoveDistance, TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics()));
        this.scannerLineHeight = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.ViewfinderView_scannerLineHeight, TypedValue.applyDimension(1, 5.0f, getResources().getDisplayMetrics()));
        this.frameLineWidth = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.ViewfinderView_frameLineWidth, TypedValue.applyDimension(1, 1.0f, getResources().getDisplayMetrics()));
        this.scannerAnimationDelay = typedArrayObtainStyledAttributes.getInteger(R.styleable.ViewfinderView_scannerAnimationDelay, 20);
        this.frameRatio = typedArrayObtainStyledAttributes.getFloat(R.styleable.ViewfinderView_frameRatio, 0.625f);
        this.framePaddingLeft = typedArrayObtainStyledAttributes.getDimension(R.styleable.ViewfinderView_framePaddingLeft, 0.0f);
        this.framePaddingTop = typedArrayObtainStyledAttributes.getDimension(R.styleable.ViewfinderView_framePaddingTop, 0.0f);
        this.framePaddingRight = typedArrayObtainStyledAttributes.getDimension(R.styleable.ViewfinderView_framePaddingRight, 0.0f);
        this.framePaddingBottom = typedArrayObtainStyledAttributes.getDimension(R.styleable.ViewfinderView_framePaddingBottom, 0.0f);
        this.frameGravity = FrameGravity.getFromInt(typedArrayObtainStyledAttributes.getInt(R.styleable.ViewfinderView_frameGravity, FrameGravity.CENTER.mValue));
        typedArrayObtainStyledAttributes.recycle();
        this.pointColor = this.laserColor;
        this.pointStrokeColor = -1;
        this.pointRadius = TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        this.paint = new Paint(1);
        this.textPaint = new TextPaint(1);
    }

    private DisplayMetrics getDisplayMetrics() {
        return getResources().getDisplayMetrics();
    }

    public void setLabelText(String str) {
        this.labelText = str;
    }

    public void setLabelTextColor(int i) {
        this.labelTextColor = i;
    }

    public void setLabelTextColorResource(int i) {
        this.labelTextColor = ContextCompat.getColor(getContext(), i);
    }

    public void setLabelTextSize(float f) {
        this.labelTextSize = f;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        initFrame(i, i2);
    }

    private void initFrame(int i, int i2) {
        int iMin = (int) (Math.min(i, i2) * this.frameRatio);
        int i3 = this.frameWidth;
        if (i3 <= 0 || i3 > i) {
            this.frameWidth = iMin;
        }
        int i4 = this.frameHeight;
        if (i4 <= 0 || i4 > i2) {
            this.frameHeight = iMin;
        }
        if (this.labelTextWidth <= 0) {
            this.labelTextWidth = (i - getPaddingLeft()) - getPaddingRight();
        }
        float f = (((i - this.frameWidth) / 2) + this.framePaddingLeft) - this.framePaddingRight;
        float f2 = (((i2 - this.frameHeight) / 2) + this.framePaddingTop) - this.framePaddingBottom;
        int i5 = AnonymousClass1.$SwitchMap$com$king$zxing$ViewfinderView$FrameGravity[this.frameGravity.ordinal()];
        if (i5 == 1) {
            f = this.framePaddingLeft;
        } else if (i5 == 2) {
            f2 = this.framePaddingTop;
        } else if (i5 == 3) {
            f = (i - this.frameWidth) + this.framePaddingRight;
        } else if (i5 == 4) {
            f2 = (i2 - this.frameHeight) + this.framePaddingBottom;
        }
        int i6 = (int) f;
        int i7 = (int) f2;
        this.frame = new Rect(i6, i7, this.frameWidth + i6, this.frameHeight + i7);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Rect rect = this.frame;
        if (rect == null) {
            return;
        }
        if (this.scannerStart == 0 || this.scannerEnd == 0) {
            this.scannerStart = rect.top;
            this.scannerEnd = this.frame.bottom - this.scannerLineHeight;
        }
        drawExterior(canvas, this.frame, canvas.getWidth(), canvas.getHeight());
        drawLaserScanner(canvas, this.frame);
        drawFrame(canvas, this.frame);
        drawCorner(canvas, this.frame);
        drawTextInfo(canvas, this.frame);
        postInvalidateDelayed(this.scannerAnimationDelay, this.frame.left, this.frame.top, this.frame.right, this.frame.bottom);
    }

    private void drawTextInfo(Canvas canvas, Rect rect) {
        if (TextUtils.isEmpty(this.labelText)) {
            return;
        }
        this.textPaint.setColor(this.labelTextColor);
        this.textPaint.setTextSize(this.labelTextSize);
        this.textPaint.setTextAlign(Paint.Align.CENTER);
        StaticLayout staticLayout = new StaticLayout(this.labelText, this.textPaint, this.labelTextWidth, Layout.Alignment.ALIGN_NORMAL, 1.2f, 0.0f, true);
        if (this.labelTextLocation == TextLocation.BOTTOM) {
            canvas.translate(rect.left + (rect.width() / 2), rect.bottom + this.labelTextPadding);
        } else {
            canvas.translate(rect.left + (rect.width() / 2), (rect.top - this.labelTextPadding) - staticLayout.getHeight());
        }
        staticLayout.draw(canvas);
    }

    private void drawCorner(Canvas canvas, Rect rect) {
        this.paint.setColor(this.cornerColor);
        canvas.drawRect(rect.left, rect.top, rect.left + this.cornerRectWidth, rect.top + this.cornerRectHeight, this.paint);
        canvas.drawRect(rect.left, rect.top, rect.left + this.cornerRectHeight, rect.top + this.cornerRectWidth, this.paint);
        canvas.drawRect(rect.right - this.cornerRectWidth, rect.top, rect.right, rect.top + this.cornerRectHeight, this.paint);
        canvas.drawRect(rect.right - this.cornerRectHeight, rect.top, rect.right, rect.top + this.cornerRectWidth, this.paint);
        canvas.drawRect(rect.left, rect.bottom - this.cornerRectWidth, rect.left + this.cornerRectHeight, rect.bottom, this.paint);
        canvas.drawRect(rect.left, rect.bottom - this.cornerRectHeight, rect.left + this.cornerRectWidth, rect.bottom, this.paint);
        canvas.drawRect(rect.right - this.cornerRectWidth, rect.bottom - this.cornerRectHeight, rect.right, rect.bottom, this.paint);
        canvas.drawRect(rect.right - this.cornerRectHeight, rect.bottom - this.cornerRectWidth, rect.right, rect.bottom, this.paint);
    }

    private void drawLaserScanner(Canvas canvas, Rect rect) {
        if (this.laserStyle != null) {
            this.paint.setColor(this.laserColor);
            int i = AnonymousClass1.$SwitchMap$com$king$zxing$ViewfinderView$LaserStyle[this.laserStyle.ordinal()];
            if (i == 1) {
                drawLineScanner(canvas, rect);
            } else if (i == 2) {
                drawGridScanner(canvas, rect);
            }
            this.paint.setShader(null);
        }
    }

    /* renamed from: com.king.zxing.ViewfinderView$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$king$zxing$ViewfinderView$FrameGravity;
        static final /* synthetic */ int[] $SwitchMap$com$king$zxing$ViewfinderView$LaserStyle;

        static {
            int[] iArr = new int[LaserStyle.values().length];
            $SwitchMap$com$king$zxing$ViewfinderView$LaserStyle = iArr;
            try {
                iArr[LaserStyle.LINE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$king$zxing$ViewfinderView$LaserStyle[LaserStyle.GRID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[FrameGravity.values().length];
            $SwitchMap$com$king$zxing$ViewfinderView$FrameGravity = iArr2;
            try {
                iArr2[FrameGravity.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$king$zxing$ViewfinderView$FrameGravity[FrameGravity.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$king$zxing$ViewfinderView$FrameGravity[FrameGravity.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$king$zxing$ViewfinderView$FrameGravity[FrameGravity.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private void drawLineScanner(Canvas canvas, Rect rect) {
        this.paint.setShader(new LinearGradient(rect.left, this.scannerStart, rect.left, this.scannerStart + this.scannerLineHeight, shadeColor(this.laserColor), this.laserColor, Shader.TileMode.MIRROR));
        if (this.scannerStart <= this.scannerEnd) {
            float f = rect.left + (this.scannerLineHeight * 2);
            float f2 = this.scannerStart;
            int i = rect.right;
            int i2 = this.scannerLineHeight;
            canvas.drawOval(new RectF(f, f2, i - (i2 * 2), this.scannerStart + i2), this.paint);
            this.scannerStart += this.scannerLineMoveDistance;
            return;
        }
        this.scannerStart = rect.top;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void drawGridScanner(android.graphics.Canvas r14, android.graphics.Rect r15) {
        /*
            Method dump skipped, instructions count: 206
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.king.zxing.ViewfinderView.drawGridScanner(android.graphics.Canvas, android.graphics.Rect):void");
    }

    public int shadeColor(int i) {
        return Integer.valueOf("01" + Integer.toHexString(i).substring(2), 16).intValue();
    }

    private void drawFrame(Canvas canvas, Rect rect) {
        this.paint.setColor(this.frameColor);
        canvas.drawRect(rect.left, rect.top, rect.right, rect.top + this.frameLineWidth, this.paint);
        canvas.drawRect(rect.left, rect.top, rect.left + this.frameLineWidth, rect.bottom, this.paint);
        canvas.drawRect(rect.right - this.frameLineWidth, rect.top, rect.right, rect.bottom, this.paint);
        canvas.drawRect(rect.left, rect.bottom - this.frameLineWidth, rect.right, rect.bottom, this.paint);
    }

    private void drawExterior(Canvas canvas, Rect rect, int i, int i2) {
        int i3 = this.maskColor;
        if (i3 != 0) {
            this.paint.setColor(i3);
            float f = i;
            canvas.drawRect(0.0f, 0.0f, f, rect.top, this.paint);
            canvas.drawRect(0.0f, rect.top, rect.left, rect.bottom, this.paint);
            canvas.drawRect(rect.right, rect.top, f, rect.bottom, this.paint);
            canvas.drawRect(0.0f, rect.bottom, f, i2, this.paint);
        }
    }

    public void drawViewfinder() {
        invalidate();
    }

    public void setLaserStyle(LaserStyle laserStyle) {
        this.laserStyle = laserStyle;
    }
}
