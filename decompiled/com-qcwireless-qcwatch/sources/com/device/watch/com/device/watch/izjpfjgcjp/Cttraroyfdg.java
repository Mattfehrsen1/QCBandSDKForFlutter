package com.device.watch.com.device.watch.izjpfjgcjp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes.dex */
public class Cttraroyfdg extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1006;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1006;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Cttraroyfdg() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(625.0f, 705.225f);
        path.lineTo(505.0f, 625.995f);
        path.lineTo(895.0f, 425.275f);
        path.lineTo(415.0f, 665.625f);
        path.lineTo(745.0f, 875.445f);
        path.lineTo(885.0f, 655.265f);
        path.lineTo(405.0f, 645.455f);
        path.lineTo(875.0f, 255.435f);
        path.lineTo(135.0f, 635.785f);
        path.lineTo(805.0f, 35.445f);
        path.lineTo(975.0f, 925.505f);
        path.lineTo(975.0f, 625.145f);
        path.lineTo(825.0f, 365.915f);
        path.lineTo(935.0f, 735.10065f);
        path.lineTo(235.0f, 945.745f);
        path.lineTo(785.0f, 765.705f);
        path.lineTo(805.0f, 855.745f);
        path.lineTo(85.0f, 765.655f);
        path.lineTo(355.0f, 695.205f);
        path.lineTo(955.0f, 665.455f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1006.0f, this.bounds.height() / 1006.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
