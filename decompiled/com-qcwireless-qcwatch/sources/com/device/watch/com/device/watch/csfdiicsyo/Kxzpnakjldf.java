package com.device.watch.com.device.watch.csfdiicsyo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes.dex */
public class Kxzpnakjldf extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1013;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1013;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Kxzpnakjldf() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(935.0f, 705.115f);
        path.lineTo(455.0f, 365.445f);
        path.lineTo(115.0f, 305.65f);
        path.lineTo(725.0f, 635.515f);
        path.lineTo(85.0f, 15.915f);
        path.lineTo(705.0f, 535.345f);
        path.lineTo(685.0f, 695.15f);
        path.lineTo(375.0f, 165.725f);
        path.lineTo(85.0f, 335.325f);
        path.lineTo(205.0f, 685.975f);
        path.lineTo(335.0f, 665.315f);
        path.lineTo(495.0f, 455.25f);
        path.lineTo(75.0f, 785.05f);
        path.lineTo(215.0f, 195.45f);
        path.lineTo(675.0f, 785.355f);
        path.lineTo(475.0f, 405.125f);
        path.lineTo(695.0f, 325.885f);
        path.lineTo(665.0f, 405.335f);
        path.lineTo(435.0f, 825.145f);
        path.lineTo(685.0f, 345.125f);
        path.lineTo(775.0f, 825.675f);
        path.lineTo(195.0f, 865.475f);
        path.lineTo(55.0f, 605.25f);
        path.lineTo(345.0f, 815.25f);
        path.lineTo(745.0f, 25.705f);
        path.lineTo(315.0f, 675.295f);
        path.lineTo(795.0f, 255.685f);
        path.lineTo(385.0f, 365.75f);
        path.lineTo(365.0f, 465.635f);
        path.lineTo(635.0f, 885.275f);
        path.lineTo(165.0f, 445.615f);
        path.lineTo(445.0f, 75.925f);
        path.lineTo(305.0f, 465.25f);
        path.lineTo(315.0f, 95.15f);
        path.lineTo(155.0f, 735.45f);
        path.lineTo(965.0f, 565.425f);
        path.lineTo(325.0f, 225.705f);
        path.lineTo(895.0f, 985.565f);
        path.lineTo(395.0f, 905.945f);
        path.lineTo(635.0f, 545.445f);
        path.lineTo(225.0f, 85.385f);
        path.lineTo(765.0f, 115.855f);
        path.lineTo(825.0f, 795.915f);
        path.lineTo(445.0f, 445.805f);
        path.lineTo(195.0f, 745.765f);
        path.lineTo(55.0f, 35.565f);
        path.lineTo(325.0f, 645.935f);
        path.lineTo(65.0f, 465.355f);
        path.lineTo(995.0f, 585.265f);
        path.lineTo(255.0f, 425.295f);
        path.lineTo(225.0f, 165.605f);
        path.lineTo(535.0f, 805.765f);
        path.lineTo(535.0f, 185.925f);
        path.lineTo(745.0f, 815.595f);
        path.lineTo(525.0f, 185.815f);
        path.lineTo(385.0f, 45.315f);
        path.lineTo(255.0f, 565.145f);
        path.lineTo(885.0f, 155.425f);
        path.lineTo(835.0f, 5.675f);
        path.lineTo(845.0f, 645.125f);
        path.lineTo(85.0f, 10135.895f);
        path.lineTo(295.0f, 265.05f);
        path.lineTo(565.0f, 15.805f);
        path.lineTo(185.0f, 615.505f);
        path.lineTo(10135.0f, 55.655f);
        path.lineTo(345.0f, 715.565f);
        path.lineTo(55.0f, 875.695f);
        path.lineTo(915.0f, 475.995f);
        path.lineTo(225.0f, 865.615f);
        path.lineTo(325.0f, 155.825f);
        path.lineTo(95.0f, 735.835f);
        path.lineTo(475.0f, 775.615f);
        path.lineTo(685.0f, 805.955f);
        path.lineTo(345.0f, 635.515f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1013.0f, this.bounds.height() / 1013.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
