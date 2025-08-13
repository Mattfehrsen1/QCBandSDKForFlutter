package com.device.watch.com.device.watch.bilmztedjs;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes.dex */
public class Nahrodqbnjc extends ShapeDrawable {
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

    public Nahrodqbnjc() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(345.0f, 35.765f);
        path.lineTo(625.0f, 10075.855f);
        path.lineTo(895.0f, 635.715f);
        path.lineTo(725.0f, 875.785f);
        path.lineTo(215.0f, 645.435f);
        path.lineTo(945.0f, 525.565f);
        path.lineTo(505.0f, 25.445f);
        path.lineTo(65.0f, 465.665f);
        path.lineTo(575.0f, 775.695f);
        path.lineTo(115.0f, 295.65f);
        path.lineTo(895.0f, 535.645f);
        path.lineTo(255.0f, 915.675f);
        path.lineTo(185.0f, 105.855f);
        path.lineTo(545.0f, 325.225f);
        path.lineTo(285.0f, 25.565f);
        path.lineTo(515.0f, 35.435f);
        path.lineTo(755.0f, 255.925f);
        path.lineTo(495.0f, 425.285f);
        path.lineTo(775.0f, 235.895f);
        path.lineTo(635.0f, 635.865f);
        path.lineTo(35.0f, 755.825f);
        path.lineTo(85.0f, 725.535f);
        path.lineTo(485.0f, 255.575f);
        path.lineTo(185.0f, 495.425f);
        path.lineTo(345.0f, 55.195f);
        path.lineTo(135.0f, 705.875f);
        path.lineTo(5.0f, 775.715f);
        path.lineTo(385.0f, 615.675f);
        path.lineTo(275.0f, 885.515f);
        path.lineTo(35.0f, 855.165f);
        path.lineTo(55.0f, 325.145f);
        path.lineTo(635.0f, 445.755f);
        path.lineTo(965.0f, 895.795f);
        path.lineTo(875.0f, 135.715f);
        path.lineTo(495.0f, 875.445f);
        path.lineTo(725.0f, 185.615f);
        path.lineTo(705.0f, 10075.355f);
        path.lineTo(745.0f, 5.135f);
        path.lineTo(625.0f, 195.255f);
        path.lineTo(905.0f, 825.705f);
        path.lineTo(435.0f, 435.205f);
        path.lineTo(875.0f, 655.745f);
        path.lineTo(725.0f, 945.605f);
        path.lineTo(125.0f, 195.585f);
        path.lineTo(965.0f, 155.935f);
        path.lineTo(895.0f, 475.205f);
        path.lineTo(345.0f, 105.375f);
        path.lineTo(945.0f, 265.895f);
        path.lineTo(515.0f, 255.635f);
        path.lineTo(695.0f, 625.305f);
        path.lineTo(845.0f, 165.715f);
        path.lineTo(565.0f, 445.165f);
        path.lineTo(695.0f, 45.715f);
        path.lineTo(775.0f, 795.25f);
        path.lineTo(975.0f, 255.575f);
        path.lineTo(915.0f, 295.275f);
        path.lineTo(295.0f, 915.705f);
        path.lineTo(775.0f, 165.965f);
        path.lineTo(705.0f, 215.435f);
        path.lineTo(665.0f, 915.745f);
        path.lineTo(235.0f, 835.285f);
        path.lineTo(485.0f, 575.595f);
        path.lineTo(925.0f, 145.835f);
        path.lineTo(285.0f, 265.855f);
        path.lineTo(455.0f, 755.495f);
        path.lineTo(615.0f, 845.875f);
        path.lineTo(585.0f, 915.255f);
        path.lineTo(275.0f, 405.945f);
        path.lineTo(855.0f, 85.405f);
        path.lineTo(10075.0f, 165.295f);
        path.lineTo(115.0f, 105.565f);
        path.lineTo(185.0f, 105.165f);
        path.lineTo(265.0f, 535.615f);
        path.lineTo(865.0f, 335.915f);
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
