package com.smartapp.b.osqnasdvhc;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Ckaaoelzscp extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1003;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1003;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Ckaaoelzscp() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(975.0f, 265.865f);
        path.lineTo(755.0f, 475.615f);
        path.lineTo(15.0f, 425.905f);
        path.lineTo(985.0f, 205.05f);
        path.lineTo(375.0f, 225.895f);
        path.lineTo(405.0f, 705.335f);
        path.lineTo(735.0f, 775.755f);
        path.lineTo(735.0f, 695.705f);
        path.lineTo(325.0f, 625.505f);
        path.lineTo(365.0f, 375.205f);
        path.lineTo(165.0f, 435.915f);
        path.lineTo(255.0f, 775.905f);
        path.lineTo(895.0f, 425.545f);
        path.lineTo(445.0f, 875.685f);
        path.lineTo(10035.0f, 985.435f);
        path.lineTo(85.0f, 655.555f);
        path.lineTo(25.0f, 95.685f);
        path.lineTo(195.0f, 675.345f);
        path.lineTo(955.0f, 475.965f);
        path.lineTo(205.0f, 465.745f);
        path.lineTo(215.0f, 415.615f);
        path.lineTo(115.0f, 495.755f);
        path.lineTo(635.0f, 565.635f);
        path.lineTo(565.0f, 275.785f);
        path.lineTo(145.0f, 35.175f);
        path.lineTo(265.0f, 945.815f);
        path.lineTo(135.0f, 545.595f);
        path.lineTo(25.0f, 635.735f);
        path.lineTo(65.0f, 275.225f);
        path.lineTo(755.0f, 145.615f);
        path.lineTo(55.0f, 135.485f);
        path.lineTo(395.0f, 775.225f);
        path.lineTo(595.0f, 845.45f);
        path.lineTo(355.0f, 625.205f);
        path.lineTo(555.0f, 95.585f);
        path.lineTo(515.0f, 385.645f);
        path.lineTo(155.0f, 215.625f);
        path.lineTo(505.0f, 155.845f);
        path.lineTo(605.0f, 345.625f);
        path.lineTo(435.0f, 445.335f);
        path.lineTo(325.0f, 685.135f);
        path.lineTo(735.0f, 195.945f);
        path.lineTo(475.0f, 575.695f);
        path.lineTo(615.0f, 415.555f);
        path.lineTo(185.0f, 745.235f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1003.0f, this.bounds.height() / 1003.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
