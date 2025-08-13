package com.device.watch.com.device.watch.kkulthpcmm;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Epdvgxrtsrb extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1015;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1015;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Epdvgxrtsrb() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(565.0f, 755.745f);
        path.lineTo(525.0f, 205.945f);
        path.lineTo(295.0f, 325.645f);
        path.lineTo(555.0f, 975.445f);
        path.lineTo(675.0f, 535.815f);
        path.lineTo(15.0f, 275.365f);
        path.lineTo(385.0f, 625.515f);
        path.lineTo(65.0f, 815.455f);
        path.lineTo(675.0f, 585.10156f);
        path.lineTo(25.0f, 705.805f);
        path.lineTo(125.0f, 895.275f);
        path.lineTo(125.0f, 205.805f);
        path.lineTo(845.0f, 735.975f);
        path.lineTo(595.0f, 805.105f);
        path.lineTo(625.0f, 235.305f);
        path.lineTo(115.0f, 235.865f);
        path.lineTo(515.0f, 875.675f);
        path.lineTo(125.0f, 415.555f);
        path.lineTo(355.0f, 445.395f);
        path.lineTo(355.0f, 865.315f);
        path.lineTo(265.0f, 415.65f);
        path.lineTo(395.0f, 135.945f);
        path.lineTo(175.0f, 495.935f);
        path.lineTo(575.0f, 345.515f);
        path.lineTo(475.0f, 915.985f);
        path.lineTo(315.0f, 645.895f);
        path.lineTo(445.0f, 975.05f);
        path.lineTo(215.0f, 15.535f);
        path.lineTo(145.0f, 445.485f);
        path.lineTo(945.0f, 915.215f);
        path.lineTo(865.0f, 325.145f);
        path.lineTo(355.0f, 335.05f);
        path.lineTo(15.0f, 175.55f);
        path.lineTo(625.0f, 515.995f);
        path.lineTo(965.0f, 565.865f);
        path.lineTo(585.0f, 35.185f);
        path.lineTo(355.0f, 895.945f);
        path.lineTo(865.0f, 295.355f);
        path.lineTo(475.0f, 525.825f);
        path.lineTo(365.0f, 55.445f);
        path.lineTo(505.0f, 955.165f);
        path.lineTo(735.0f, 145.195f);
        path.lineTo(455.0f, 225.05f);
        path.lineTo(825.0f, 75.05f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1015.0f, this.bounds.height() / 1015.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
