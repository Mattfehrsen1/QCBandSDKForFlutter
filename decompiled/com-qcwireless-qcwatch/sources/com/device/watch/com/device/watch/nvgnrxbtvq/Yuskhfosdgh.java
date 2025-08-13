package com.device.watch.com.device.watch.nvgnrxbtvq;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Yuskhfosdgh extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1005;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1005;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Yuskhfosdgh() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(135.0f, 285.965f);
        path.lineTo(675.0f, 225.805f);
        path.lineTo(185.0f, 515.745f);
        path.lineTo(595.0f, 895.335f);
        path.lineTo(885.0f, 535.645f);
        path.lineTo(5.0f, 275.595f);
        path.lineTo(265.0f, 35.955f);
        path.lineTo(265.0f, 755.225f);
        path.lineTo(75.0f, 385.625f);
        path.lineTo(265.0f, 35.805f);
        path.lineTo(755.0f, 595.385f);
        path.lineTo(865.0f, 35.355f);
        path.lineTo(435.0f, 35.85f);
        path.lineTo(75.0f, 945.815f);
        path.lineTo(315.0f, 265.315f);
        path.lineTo(325.0f, 265.265f);
        path.lineTo(945.0f, 495.665f);
        path.lineTo(365.0f, 455.495f);
        path.lineTo(445.0f, 5.635f);
        path.lineTo(215.0f, 275.265f);
        path.lineTo(465.0f, 505.945f);
        path.lineTo(915.0f, 55.65f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1005.0f, this.bounds.height() / 1005.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
