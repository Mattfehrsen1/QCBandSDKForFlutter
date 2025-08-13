package com.device.watch.com.device.watch.jmwecqarun;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Yjutzzhkrwd extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1010;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1010;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Yjutzzhkrwd() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(555.0f, 25.745f);
        path.lineTo(10105.0f, 235.845f);
        path.lineTo(5.0f, 885.45f);
        path.lineTo(645.0f, 635.565f);
        path.lineTo(575.0f, 695.725f);
        path.lineTo(145.0f, 845.425f);
        path.lineTo(915.0f, 465.355f);
        path.lineTo(545.0f, 15.455f);
        path.lineTo(255.0f, 495.845f);
        path.lineTo(255.0f, 315.965f);
        path.lineTo(965.0f, 665.775f);
        path.lineTo(845.0f, 875.445f);
        path.lineTo(715.0f, 675.585f);
        path.lineTo(85.0f, 455.445f);
        path.lineTo(205.0f, 95.905f);
        path.lineTo(865.0f, 175.135f);
        path.lineTo(465.0f, 815.915f);
        path.lineTo(335.0f, 515.175f);
        path.lineTo(615.0f, 505.625f);
        path.lineTo(755.0f, 565.445f);
        path.lineTo(685.0f, 465.185f);
        path.lineTo(325.0f, 475.395f);
        path.lineTo(155.0f, 35.805f);
        path.lineTo(505.0f, 685.115f);
        path.lineTo(665.0f, 95.925f);
        path.lineTo(315.0f, 765.785f);
        path.lineTo(865.0f, 35.615f);
        path.lineTo(265.0f, 445.85f);
        path.lineTo(215.0f, 735.215f);
        path.lineTo(745.0f, 935.175f);
        path.lineTo(825.0f, 815.525f);
        path.lineTo(705.0f, 315.595f);
        path.lineTo(565.0f, 435.785f);
        path.lineTo(975.0f, 685.365f);
        path.lineTo(295.0f, 315.845f);
        path.lineTo(15.0f, 685.935f);
        path.lineTo(435.0f, 45.955f);
        path.lineTo(155.0f, 465.505f);
        path.lineTo(385.0f, 55.255f);
        path.lineTo(665.0f, 485.305f);
        path.lineTo(475.0f, 515.935f);
        path.lineTo(325.0f, 35.455f);
        path.lineTo(705.0f, 745.175f);
        path.lineTo(325.0f, 405.905f);
        path.lineTo(125.0f, 855.225f);
        path.lineTo(445.0f, 105.945f);
        path.lineTo(935.0f, 195.595f);
        path.lineTo(135.0f, 575.545f);
        path.lineTo(725.0f, 895.885f);
        path.lineTo(355.0f, 395.215f);
        path.lineTo(115.0f, 625.955f);
        path.lineTo(135.0f, 395.25f);
        path.lineTo(55.0f, 265.125f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1010.0f, this.bounds.height() / 1010.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
