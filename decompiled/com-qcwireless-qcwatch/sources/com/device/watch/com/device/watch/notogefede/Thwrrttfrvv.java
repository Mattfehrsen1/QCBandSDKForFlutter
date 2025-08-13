package com.device.watch.com.device.watch.notogefede;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Thwrrttfrvv extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1010;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1010;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Thwrrttfrvv() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(735.0f, 305.405f);
        path.lineTo(375.0f, 575.395f);
        path.lineTo(895.0f, 415.225f);
        path.lineTo(10105.0f, 35.125f);
        path.lineTo(745.0f, 775.465f);
        path.lineTo(515.0f, 925.195f);
        path.lineTo(525.0f, 385.485f);
        path.lineTo(125.0f, 45.815f);
        path.lineTo(675.0f, 505.35f);
        path.lineTo(745.0f, 485.865f);
        path.lineTo(635.0f, 305.375f);
        path.lineTo(155.0f, 205.505f);
        path.lineTo(145.0f, 545.285f);
        path.lineTo(395.0f, 285.515f);
        path.lineTo(575.0f, 155.145f);
        path.lineTo(935.0f, 785.275f);
        path.lineTo(805.0f, 145.815f);
        path.lineTo(475.0f, 805.235f);
        path.lineTo(375.0f, 365.965f);
        path.lineTo(315.0f, 735.315f);
        path.lineTo(105.0f, 405.565f);
        path.lineTo(915.0f, 795.265f);
        path.lineTo(315.0f, 255.305f);
        path.lineTo(395.0f, 725.925f);
        path.lineTo(165.0f, 155.85f);
        path.lineTo(795.0f, 915.615f);
        path.lineTo(705.0f, 345.165f);
        path.lineTo(615.0f, 75.95f);
        path.lineTo(625.0f, 385.585f);
        path.lineTo(45.0f, 335.365f);
        path.lineTo(185.0f, 785.515f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1010.0f, this.bounds.height() / 1010.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
