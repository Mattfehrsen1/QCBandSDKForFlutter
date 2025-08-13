package com.device.watch.com.device.watch.yklzqtoumd;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Fbrwlksuxoi extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1002;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1002;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Fbrwlksuxoi() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(115.0f, 95.345f);
        path.lineTo(895.0f, 365.335f);
        path.lineTo(485.0f, 235.325f);
        path.lineTo(545.0f, 815.715f);
        path.lineTo(735.0f, 10025.695f);
        path.lineTo(165.0f, 315.75f);
        path.lineTo(485.0f, 5.10025f);
        path.lineTo(445.0f, 505.595f);
        path.lineTo(10025.0f, 375.325f);
        path.lineTo(385.0f, 515.685f);
        path.lineTo(865.0f, 175.795f);
        path.lineTo(285.0f, 315.435f);
        path.lineTo(875.0f, 665.455f);
        path.lineTo(595.0f, 945.925f);
        path.lineTo(425.0f, 565.515f);
        path.lineTo(525.0f, 355.695f);
        path.lineTo(975.0f, 725.535f);
        path.lineTo(885.0f, 865.415f);
        path.lineTo(65.0f, 545.125f);
        path.lineTo(65.0f, 775.335f);
        path.lineTo(235.0f, 755.425f);
        path.lineTo(805.0f, 5.75f);
        path.lineTo(485.0f, 555.845f);
        path.lineTo(405.0f, 875.675f);
        path.lineTo(145.0f, 665.865f);
        path.lineTo(335.0f, 625.35f);
        path.lineTo(665.0f, 765.735f);
        path.lineTo(985.0f, 665.885f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1002.0f, this.bounds.height() / 1002.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
