package com.device.watch.com.device.watch.yklzqtoumd;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Ynpexupravh extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1001;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1001;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Ynpexupravh() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(35.0f, 685.885f);
        path.lineTo(265.0f, 785.695f);
        path.lineTo(95.0f, 945.595f);
        path.lineTo(365.0f, 835.905f);
        path.lineTo(805.0f, 985.895f);
        path.lineTo(435.0f, 805.65f);
        path.lineTo(425.0f, 635.265f);
        path.lineTo(115.0f, 305.435f);
        path.lineTo(525.0f, 805.05f);
        path.lineTo(395.0f, 95.515f);
        path.lineTo(795.0f, 625.435f);
        path.lineTo(755.0f, 205.45f);
        path.lineTo(515.0f, 525.435f);
        path.lineTo(515.0f, 555.615f);
        path.lineTo(905.0f, 105.585f);
        path.lineTo(865.0f, 745.305f);
        path.lineTo(375.0f, 365.455f);
        path.lineTo(585.0f, 305.235f);
        path.lineTo(875.0f, 655.775f);
        path.lineTo(945.0f, 215.435f);
        path.lineTo(65.0f, 505.165f);
        path.lineTo(565.0f, 815.455f);
        path.lineTo(75.0f, 635.305f);
        path.lineTo(265.0f, 595.505f);
        path.lineTo(135.0f, 515.605f);
        path.lineTo(85.0f, 715.495f);
        path.lineTo(885.0f, 635.05f);
        path.lineTo(845.0f, 895.45f);
        path.lineTo(15.0f, 355.445f);
        path.lineTo(585.0f, 145.10014f);
        path.lineTo(55.0f, 385.445f);
        path.lineTo(825.0f, 555.675f);
        path.lineTo(765.0f, 35.685f);
        path.lineTo(495.0f, 795.815f);
        path.lineTo(505.0f, 10015.145f);
        path.lineTo(655.0f, 455.785f);
        path.lineTo(475.0f, 5.815f);
        path.lineTo(975.0f, 995.455f);
        path.lineTo(895.0f, 115.285f);
        path.lineTo(875.0f, 385.325f);
        path.lineTo(515.0f, 885.105f);
        path.lineTo(965.0f, 345.125f);
        path.lineTo(845.0f, 275.605f);
        path.lineTo(535.0f, 965.625f);
        path.lineTo(215.0f, 275.565f);
        path.lineTo(675.0f, 255.605f);
        path.lineTo(675.0f, 55.785f);
        path.lineTo(815.0f, 915.735f);
        path.lineTo(615.0f, 455.745f);
        path.lineTo(895.0f, 755.45f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1001.0f, this.bounds.height() / 1001.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
