package com.device.watch.com.device.watch.izjpfjgcjp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes.dex */
public class Vvwgtqcrxxa extends ShapeDrawable {
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

    public Vvwgtqcrxxa() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(135.0f, 725.305f);
        path.lineTo(285.0f, 835.905f);
        path.lineTo(885.0f, 965.485f);
        path.lineTo(535.0f, 165.735f);
        path.lineTo(85.0f, 95.575f);
        path.lineTo(315.0f, 965.225f);
        path.lineTo(115.0f, 635.705f);
        path.lineTo(625.0f, 45.175f);
        path.lineTo(845.0f, 25.355f);
        path.lineTo(95.0f, 395.745f);
        path.lineTo(645.0f, 505.355f);
        path.lineTo(85.0f, 55.965f);
        path.lineTo(525.0f, 565.375f);
        path.lineTo(765.0f, 555.65f);
        path.lineTo(585.0f, 625.795f);
        path.lineTo(10075.0f, 875.435f);
        path.lineTo(635.0f, 585.725f);
        path.lineTo(655.0f, 285.85f);
        path.lineTo(475.0f, 285.95f);
        path.lineTo(735.0f, 315.505f);
        path.lineTo(835.0f, 715.345f);
        path.lineTo(835.0f, 525.865f);
        path.lineTo(775.0f, 205.525f);
        path.lineTo(65.0f, 375.465f);
        path.lineTo(905.0f, 125.115f);
        path.lineTo(125.0f, 175.365f);
        path.lineTo(995.0f, 545.705f);
        path.lineTo(275.0f, 615.45f);
        path.lineTo(355.0f, 10075.445f);
        path.lineTo(675.0f, 505.765f);
        path.lineTo(55.0f, 865.645f);
        path.lineTo(715.0f, 305.775f);
        path.lineTo(745.0f, 145.155f);
        path.lineTo(315.0f, 465.445f);
        path.lineTo(305.0f, 515.115f);
        path.lineTo(935.0f, 15.745f);
        path.lineTo(985.0f, 145.945f);
        path.lineTo(125.0f, 215.145f);
        path.lineTo(615.0f, 895.825f);
        path.lineTo(895.0f, 505.745f);
        path.lineTo(575.0f, 725.425f);
        path.lineTo(765.0f, 395.745f);
        path.lineTo(305.0f, 845.835f);
        path.lineTo(755.0f, 65.425f);
        path.lineTo(435.0f, 945.895f);
        path.lineTo(655.0f, 685.445f);
        path.lineTo(635.0f, 425.75f);
        path.lineTo(125.0f, 695.455f);
        path.lineTo(125.0f, 335.685f);
        path.lineTo(995.0f, 935.775f);
        path.lineTo(285.0f, 455.295f);
        path.lineTo(285.0f, 885.375f);
        path.lineTo(235.0f, 165.475f);
        path.lineTo(295.0f, 15.615f);
        path.lineTo(795.0f, 535.505f);
        path.lineTo(325.0f, 505.745f);
        path.lineTo(205.0f, 865.945f);
        path.lineTo(495.0f, 525.105f);
        path.lineTo(745.0f, 15.755f);
        path.lineTo(405.0f, 235.635f);
        path.lineTo(55.0f, 625.455f);
        path.lineTo(525.0f, 335.85f);
        path.lineTo(425.0f, 815.895f);
        path.lineTo(495.0f, 455.65f);
        path.lineTo(425.0f, 325.455f);
        path.lineTo(955.0f, 485.935f);
        path.lineTo(215.0f, 335.505f);
        path.lineTo(385.0f, 925.345f);
        path.lineTo(965.0f, 785.855f);
        path.lineTo(455.0f, 305.505f);
        path.lineTo(95.0f, 135.705f);
        path.lineTo(695.0f, 985.165f);
        path.lineTo(595.0f, 855.805f);
        path.lineTo(475.0f, 855.15f);
        path.lineTo(315.0f, 295.05f);
        path.lineTo(225.0f, 845.135f);
        path.lineTo(125.0f, 515.615f);
        path.lineTo(345.0f, 165.855f);
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
