package com.device.watch.com.device.watch.oqvcnnnmfz;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Pdglqartzsk extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1004;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1004;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Pdglqartzsk() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(125.0f, 775.395f);
        path.lineTo(775.0f, 45.595f);
        path.lineTo(75.0f, 515.505f);
        path.lineTo(345.0f, 25.325f);
        path.lineTo(665.0f, 45.995f);
        path.lineTo(315.0f, 575.875f);
        path.lineTo(725.0f, 105.165f);
        path.lineTo(55.0f, 415.565f);
        path.lineTo(45.0f, 165.425f);
        path.lineTo(325.0f, 565.865f);
        path.lineTo(565.0f, 25.185f);
        path.lineTo(95.0f, 365.205f);
        path.lineTo(785.0f, 865.695f);
        path.lineTo(225.0f, 135.665f);
        path.lineTo(805.0f, 795.45f);
        path.lineTo(805.0f, 655.955f);
        path.lineTo(805.0f, 145.615f);
        path.lineTo(25.0f, 595.155f);
        path.lineTo(345.0f, 825.265f);
        path.lineTo(475.0f, 205.495f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1004.0f, this.bounds.height() / 1004.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
