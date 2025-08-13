package com.device.watch.com.device.watch.bilmztedjs;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes.dex */
public class Xyohhkobzwc extends ShapeDrawable {
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

    public Xyohhkobzwc() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(625.0f, 945.345f);
        path.lineTo(515.0f, 665.85f);
        path.lineTo(375.0f, 145.405f);
        path.lineTo(265.0f, 15.305f);
        path.lineTo(25.0f, 135.205f);
        path.lineTo(705.0f, 875.315f);
        path.lineTo(875.0f, 695.125f);
        path.lineTo(865.0f, 275.475f);
        path.lineTo(515.0f, 825.475f);
        path.lineTo(445.0f, 215.95f);
        path.lineTo(665.0f, 845.625f);
        path.lineTo(315.0f, 135.695f);
        path.lineTo(565.0f, 175.115f);
        path.lineTo(285.0f, 685.305f);
        path.lineTo(175.0f, 845.885f);
        path.lineTo(765.0f, 685.10034f);
        path.lineTo(275.0f, 455.05f);
        path.lineTo(585.0f, 185.165f);
        path.lineTo(275.0f, 815.255f);
        path.lineTo(925.0f, 325.575f);
        path.lineTo(265.0f, 115.835f);
        path.lineTo(925.0f, 385.715f);
        path.lineTo(435.0f, 695.705f);
        path.lineTo(565.0f, 465.965f);
        path.lineTo(645.0f, 835.285f);
        path.lineTo(365.0f, 405.935f);
        path.lineTo(385.0f, 55.465f);
        path.lineTo(15.0f, 305.675f);
        path.lineTo(545.0f, 475.665f);
        path.lineTo(645.0f, 965.445f);
        path.lineTo(305.0f, 465.745f);
        path.lineTo(715.0f, 765.335f);
        path.lineTo(715.0f, 275.505f);
        path.lineTo(755.0f, 135.325f);
        path.lineTo(215.0f, 115.795f);
        path.lineTo(605.0f, 265.885f);
        path.lineTo(25.0f, 615.115f);
        path.lineTo(115.0f, 825.15f);
        path.lineTo(975.0f, 565.895f);
        path.lineTo(155.0f, 995.885f);
        path.lineTo(845.0f, 545.465f);
        path.lineTo(575.0f, 145.175f);
        path.lineTo(455.0f, 835.745f);
        path.lineTo(775.0f, 685.05f);
        path.lineTo(15.0f, 305.905f);
        path.lineTo(175.0f, 495.205f);
        path.lineTo(885.0f, 595.255f);
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
