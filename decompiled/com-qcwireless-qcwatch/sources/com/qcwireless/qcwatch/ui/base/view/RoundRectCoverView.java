package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RoundRectCoverView.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0014J(\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\fH\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/RoundRectCoverView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "bounds", "Landroid/graphics/RectF;", "clipPath", "Landroid/graphics/Path;", "mCoverColor", "", "mPadding", "", "mRoundCorner", "paint", "Landroid/graphics/Paint;", "porterDuffXfermode", "Landroid/graphics/PorterDuffXfermode;", "fun1", "", "canvas", "Landroid/graphics/Canvas;", "fun2", "onDraw", "onSizeChanged", "w", "h", "oldw", "oldh", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RoundRectCoverView extends View {
    private final RectF bounds;
    private final Path clipPath;
    private int mCoverColor;
    private float mPadding;
    private float mRoundCorner;
    private final Paint paint;
    private final PorterDuffXfermode porterDuffXfermode;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RoundRectCoverView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.paint = new Paint(1);
        this.mRoundCorner = 320.0f;
        this.mCoverColor = Color.parseColor("#aaff0000");
        this.bounds = new RectF();
        this.porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
        this.clipPath = new Path();
        setLayerType(1, null);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.bounds.set(0.0f, 0.0f, getWidth(), getHeight());
        Path path = this.clipPath;
        float f = this.mPadding;
        float width = getWidth() - this.mPadding;
        float height = getHeight() - this.mPadding;
        float f2 = this.mRoundCorner;
        path.addRoundRect(f, f, width, height, f2, f2, Path.Direction.CW);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        fun2(canvas);
    }

    private final void fun1(Canvas canvas) {
        float f = this.mPadding;
        float width = getWidth() - this.mPadding;
        float height = getHeight() - this.mPadding;
        float f2 = this.mRoundCorner;
        canvas.drawRoundRect(f, f, width, height, f2, f2, this.paint);
        this.paint.setColor(this.mCoverColor);
        this.paint.setXfermode(this.porterDuffXfermode);
        canvas.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.paint);
        this.paint.setXfermode(null);
    }

    private final void fun2(Canvas canvas) {
        int iSaveLayer = canvas.saveLayer(this.bounds, this.paint);
        int iSave = canvas.save();
        try {
            canvas.drawColor(this.mCoverColor);
            canvas.clipPath(this.clipPath);
            canvas.drawColor(0, PorterDuff.Mode.SRC);
            canvas.restoreToCount(iSave);
            canvas.restoreToCount(iSaveLayer);
        } catch (Throwable th) {
            canvas.restoreToCount(iSave);
            throw th;
        }
    }
}
