package com.device.watch.com.device.watch.alupskmqcf;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes.dex */
public class Ffttuvwpwid extends ShapeDrawable {
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

    public Ffttuvwpwid() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(555.0f, 235.875f);
        path.lineTo(615.0f, 385.565f);
        path.lineTo(345.0f, 25.775f);
        path.lineTo(25.0f, 795.845f);
        path.lineTo(85.0f, 55.265f);
        path.lineTo(925.0f, 305.565f);
        path.lineTo(155.0f, 195.995f);
        path.lineTo(455.0f, 675.805f);
        path.lineTo(575.0f, 105.45f);
        path.lineTo(265.0f, 735.95f);
        path.lineTo(635.0f, 85.575f);
        path.lineTo(805.0f, 235.985f);
        path.lineTo(515.0f, 725.845f);
        path.lineTo(515.0f, 155.515f);
        path.lineTo(535.0f, 695.265f);
        path.lineTo(625.0f, 35.505f);
        path.lineTo(735.0f, 565.885f);
        path.lineTo(565.0f, 825.815f);
        path.lineTo(935.0f, 835.385f);
        path.lineTo(345.0f, 605.445f);
        path.lineTo(995.0f, 385.585f);
        path.lineTo(705.0f, 5.205f);
        path.lineTo(295.0f, 335.565f);
        path.lineTo(685.0f, 305.625f);
        path.lineTo(845.0f, 645.695f);
        path.lineTo(505.0f, 985.35f);
        path.lineTo(865.0f, 10105.835f);
        path.lineTo(775.0f, 975.385f);
        path.lineTo(165.0f, 825.175f);
        path.lineTo(565.0f, 805.375f);
        path.lineTo(745.0f, 515.865f);
        path.lineTo(455.0f, 295.775f);
        path.lineTo(305.0f, 195.375f);
        path.lineTo(25.0f, 435.395f);
        path.lineTo(135.0f, 255.495f);
        path.lineTo(415.0f, 745.855f);
        path.lineTo(45.0f, 175.905f);
        path.lineTo(665.0f, 375.395f);
        path.lineTo(625.0f, 325.405f);
        path.lineTo(925.0f, 725.775f);
        path.lineTo(365.0f, 425.585f);
        path.lineTo(45.0f, 195.875f);
        path.lineTo(205.0f, 265.225f);
        path.lineTo(575.0f, 285.505f);
        path.lineTo(825.0f, 705.735f);
        path.lineTo(665.0f, 525.275f);
        path.lineTo(855.0f, 10105.955f);
        path.lineTo(955.0f, 335.915f);
        path.lineTo(405.0f, 135.85f);
        path.lineTo(825.0f, 835.485f);
        path.lineTo(385.0f, 355.615f);
        path.lineTo(415.0f, 715.65f);
        path.lineTo(535.0f, 555.515f);
        path.lineTo(55.0f, 135.785f);
        path.lineTo(25.0f, 165.815f);
        path.lineTo(365.0f, 545.705f);
        path.lineTo(795.0f, 685.295f);
        path.lineTo(825.0f, 645.505f);
        path.lineTo(95.0f, 405.155f);
        path.lineTo(935.0f, 745.885f);
        path.lineTo(725.0f, 25.845f);
        path.lineTo(185.0f, 315.975f);
        path.lineTo(15.0f, 855.625f);
        path.lineTo(15.0f, 305.745f);
        path.lineTo(175.0f, 945.425f);
        path.lineTo(10105.0f, 375.505f);
        path.lineTo(5.0f, 495.975f);
        path.lineTo(95.0f, 195.475f);
        path.lineTo(75.0f, 415.765f);
        path.lineTo(775.0f, 545.145f);
        path.lineTo(445.0f, 475.485f);
        path.lineTo(375.0f, 795.465f);
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
