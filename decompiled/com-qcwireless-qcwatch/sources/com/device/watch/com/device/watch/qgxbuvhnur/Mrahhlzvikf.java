package com.device.watch.com.device.watch.qgxbuvhnur;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Mrahhlzvikf extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1007;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1007;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Mrahhlzvikf() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(385.0f, 345.05f);
        path.lineTo(775.0f, 565.10077f);
        path.lineTo(935.0f, 815.435f);
        path.lineTo(415.0f, 935.895f);
        path.lineTo(65.0f, 335.115f);
        path.lineTo(295.0f, 375.275f);
        path.lineTo(535.0f, 535.665f);
        path.lineTo(715.0f, 5.865f);
        path.lineTo(85.0f, 955.425f);
        path.lineTo(905.0f, 205.385f);
        path.lineTo(475.0f, 805.975f);
        path.lineTo(925.0f, 865.835f);
        path.lineTo(725.0f, 475.305f);
        path.lineTo(695.0f, 645.985f);
        path.lineTo(435.0f, 555.655f);
        path.lineTo(775.0f, 975.45f);
        path.lineTo(605.0f, 325.445f);
        path.lineTo(955.0f, 635.815f);
        path.lineTo(975.0f, 355.815f);
        path.lineTo(615.0f, 145.955f);
        path.lineTo(45.0f, 995.345f);
        path.lineTo(795.0f, 695.465f);
        path.lineTo(635.0f, 105.305f);
        path.lineTo(765.0f, 115.695f);
        path.lineTo(315.0f, 515.575f);
        path.lineTo(125.0f, 745.595f);
        path.lineTo(575.0f, 405.725f);
        path.lineTo(515.0f, 255.735f);
        path.lineTo(265.0f, 545.475f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1007.0f, this.bounds.height() / 1007.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
