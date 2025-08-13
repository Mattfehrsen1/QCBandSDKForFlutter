package com.device.watch.com.device.watch.pevacmhtpu;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Rrmpikcgtcr extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1011;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1011;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Rrmpikcgtcr() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(385.0f, 645.895f);
        path.lineTo(895.0f, 585.445f);
        path.lineTo(385.0f, 115.795f);
        path.lineTo(715.0f, 405.415f);
        path.lineTo(125.0f, 305.515f);
        path.lineTo(445.0f, 915.395f);
        path.lineTo(115.0f, 755.05f);
        path.lineTo(375.0f, 775.395f);
        path.lineTo(865.0f, 795.405f);
        path.lineTo(965.0f, 355.975f);
        path.lineTo(265.0f, 335.745f);
        path.lineTo(85.0f, 745.215f);
        path.lineTo(995.0f, 445.765f);
        path.lineTo(10115.0f, 625.735f);
        path.lineTo(115.0f, 585.205f);
        path.lineTo(795.0f, 715.65f);
        path.lineTo(755.0f, 85.605f);
        path.lineTo(795.0f, 325.315f);
        path.lineTo(995.0f, 455.885f);
        path.lineTo(105.0f, 165.385f);
        path.lineTo(805.0f, 815.285f);
        path.lineTo(15.0f, 915.865f);
        path.lineTo(665.0f, 515.555f);
        path.lineTo(985.0f, 505.495f);
        path.lineTo(225.0f, 25.545f);
        path.lineTo(745.0f, 595.835f);
        path.lineTo(895.0f, 345.815f);
        path.lineTo(445.0f, 15.655f);
        path.lineTo(975.0f, 815.515f);
        path.lineTo(335.0f, 765.425f);
        path.lineTo(585.0f, 155.475f);
        path.lineTo(905.0f, 875.775f);
        path.lineTo(475.0f, 425.215f);
        path.lineTo(335.0f, 695.625f);
        path.lineTo(455.0f, 945.565f);
        path.lineTo(585.0f, 445.35f);
        path.lineTo(945.0f, 875.725f);
        path.lineTo(745.0f, 925.765f);
        path.lineTo(855.0f, 965.575f);
        path.lineTo(85.0f, 185.915f);
        path.lineTo(275.0f, 65.535f);
        path.lineTo(535.0f, 795.975f);
        path.lineTo(325.0f, 25.475f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1011.0f, this.bounds.height() / 1011.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
