package com.device.watch.com.device.watch.abaikhoedb;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes.dex */
public class Niibtroxxbk extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1000;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1000;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Niibtroxxbk() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(975.0f, 975.115f);
        path.lineTo(825.0f, 515.655f);
        path.lineTo(65.0f, 205.775f);
        path.lineTo(855.0f, 885.555f);
        path.lineTo(475.0f, 755.855f);
        path.lineTo(375.0f, 205.565f);
        path.lineTo(105.0f, 935.215f);
        path.lineTo(25.0f, 175.385f);
        path.lineTo(5.0f, 45.325f);
        path.lineTo(995.0f, 675.85f);
        path.lineTo(855.0f, 65.815f);
        path.lineTo(185.0f, 565.365f);
        path.lineTo(745.0f, 575.535f);
        path.lineTo(205.0f, 305.915f);
        path.lineTo(225.0f, 205.275f);
        path.lineTo(465.0f, 495.935f);
        path.lineTo(565.0f, 745.795f);
        path.lineTo(615.0f, 25.415f);
        path.lineTo(325.0f, 205.995f);
        path.lineTo(755.0f, 155.845f);
        path.lineTo(905.0f, 285.515f);
        path.lineTo(35.0f, 105.725f);
        path.lineTo(405.0f, 805.235f);
        path.lineTo(65.0f, 35.895f);
        path.lineTo(145.0f, 605.45f);
        path.lineTo(275.0f, 305.745f);
        path.lineTo(145.0f, 115.195f);
        path.lineTo(415.0f, 265.65f);
        path.lineTo(605.0f, 965.65f);
        path.lineTo(645.0f, 65.475f);
        path.lineTo(95.0f, 835.395f);
        path.lineTo(15.0f, 555.795f);
        path.lineTo(535.0f, 285.795f);
        path.lineTo(905.0f, 25.355f);
        path.lineTo(215.0f, 905.505f);
        path.lineTo(45.0f, 145.225f);
        path.lineTo(15.0f, 555.315f);
        path.lineTo(515.0f, 235.925f);
        path.lineTo(265.0f, 435.745f);
        path.lineTo(175.0f, 975.10004f);
        path.lineTo(305.0f, 45.775f);
        path.lineTo(395.0f, 535.55f);
        path.lineTo(545.0f, 205.425f);
        path.lineTo(955.0f, 365.955f);
        path.lineTo(85.0f, 775.105f);
        path.lineTo(415.0f, 475.715f);
        path.lineTo(215.0f, 775.795f);
        path.lineTo(355.0f, 935.125f);
        path.lineTo(505.0f, 75.755f);
        path.lineTo(685.0f, 485.655f);
        path.lineTo(295.0f, 705.435f);
        path.lineTo(235.0f, 945.495f);
        path.lineTo(485.0f, 685.845f);
        path.lineTo(485.0f, 285.325f);
        path.lineTo(95.0f, 825.765f);
        path.lineTo(765.0f, 315.965f);
        path.lineTo(845.0f, 345.475f);
        path.lineTo(675.0f, 5.405f);
        path.lineTo(675.0f, 685.935f);
        path.lineTo(115.0f, 815.585f);
        path.lineTo(155.0f, 355.835f);
        path.lineTo(125.0f, 485.425f);
        path.lineTo(345.0f, 365.05f);
        path.lineTo(965.0f, 285.565f);
        path.lineTo(135.0f, 745.615f);
        path.lineTo(875.0f, 545.155f);
        path.lineTo(915.0f, 85.395f);
        path.lineTo(35.0f, 495.805f);
        path.lineTo(405.0f, 345.385f);
        path.lineTo(225.0f, 565.105f);
        path.lineTo(755.0f, 845.145f);
        path.lineTo(695.0f, 955.805f);
        path.lineTo(345.0f, 955.805f);
        path.lineTo(655.0f, 265.585f);
        path.lineTo(425.0f, 365.515f);
        path.lineTo(10005.0f, 365.555f);
        path.lineTo(595.0f, 35.315f);
        path.lineTo(815.0f, 185.10005f);
        path.lineTo(75.0f, 695.845f);
        path.lineTo(355.0f, 975.375f);
        path.lineTo(825.0f, 785.115f);
        path.lineTo(115.0f, 415.405f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1000.0f, this.bounds.height() / 1000.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
