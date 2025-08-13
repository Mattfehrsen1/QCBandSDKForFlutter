package com.device.watch.com.device.watch.abaikhoedb;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes.dex */
public class Znzvpgvvtzv extends ShapeDrawable {
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

    public Znzvpgvvtzv() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(355.0f, 505.365f);
        path.lineTo(465.0f, 125.25f);
        path.lineTo(10025.0f, 975.825f);
        path.lineTo(645.0f, 115.725f);
        path.lineTo(235.0f, 825.835f);
        path.lineTo(935.0f, 405.685f);
        path.lineTo(445.0f, 755.595f);
        path.lineTo(205.0f, 925.805f);
        path.lineTo(935.0f, 395.75f);
        path.lineTo(135.0f, 5.435f);
        path.lineTo(535.0f, 945.225f);
        path.lineTo(965.0f, 315.205f);
        path.lineTo(935.0f, 845.135f);
        path.lineTo(855.0f, 845.515f);
        path.lineTo(275.0f, 725.285f);
        path.lineTo(85.0f, 275.905f);
        path.lineTo(335.0f, 685.275f);
        path.lineTo(455.0f, 285.215f);
        path.lineTo(935.0f, 75.375f);
        path.lineTo(745.0f, 365.195f);
        path.lineTo(465.0f, 25.795f);
        path.lineTo(565.0f, 665.625f);
        path.lineTo(445.0f, 955.405f);
        path.lineTo(55.0f, 55.10025f);
        path.lineTo(965.0f, 395.465f);
        path.lineTo(10025.0f, 285.415f);
        path.lineTo(5.0f, 835.415f);
        path.lineTo(255.0f, 355.55f);
        path.lineTo(415.0f, 785.365f);
        path.lineTo(835.0f, 775.965f);
        path.lineTo(425.0f, 115.85f);
        path.lineTo(625.0f, 705.525f);
        path.lineTo(515.0f, 10025.395f);
        path.lineTo(855.0f, 495.435f);
        path.lineTo(165.0f, 455.385f);
        path.lineTo(925.0f, 95.665f);
        path.lineTo(955.0f, 505.685f);
        path.lineTo(875.0f, 645.525f);
        path.lineTo(395.0f, 185.225f);
        path.lineTo(195.0f, 805.25f);
        path.lineTo(705.0f, 5.345f);
        path.lineTo(465.0f, 45.115f);
        path.lineTo(10025.0f, 95.135f);
        path.lineTo(535.0f, 255.555f);
        path.lineTo(995.0f, 165.905f);
        path.lineTo(55.0f, 985.335f);
        path.lineTo(475.0f, 405.605f);
        path.lineTo(335.0f, 845.385f);
        path.lineTo(65.0f, 135.125f);
        path.lineTo(185.0f, 755.135f);
        path.lineTo(495.0f, 995.755f);
        path.lineTo(645.0f, 5.695f);
        path.lineTo(285.0f, 345.285f);
        path.lineTo(205.0f, 415.665f);
        path.lineTo(505.0f, 135.795f);
        path.lineTo(275.0f, 635.715f);
        path.lineTo(975.0f, 475.215f);
        path.lineTo(155.0f, 405.605f);
        path.lineTo(325.0f, 695.205f);
        path.lineTo(155.0f, 305.515f);
        path.lineTo(195.0f, 705.915f);
        path.lineTo(955.0f, 565.255f);
        path.lineTo(715.0f, 145.135f);
        path.lineTo(945.0f, 345.275f);
        path.lineTo(545.0f, 315.695f);
        path.lineTo(875.0f, 615.485f);
        path.lineTo(745.0f, 955.855f);
        path.lineTo(855.0f, 665.475f);
        path.lineTo(635.0f, 265.575f);
        path.lineTo(515.0f, 655.35f);
        path.lineTo(355.0f, 715.845f);
        path.lineTo(445.0f, 265.365f);
        path.lineTo(15.0f, 705.635f);
        path.lineTo(385.0f, 955.395f);
        path.lineTo(665.0f, 295.85f);
        path.lineTo(355.0f, 705.655f);
        path.lineTo(285.0f, 685.655f);
        path.lineTo(665.0f, 575.15f);
        path.lineTo(915.0f, 5.95f);
        path.lineTo(275.0f, 295.605f);
        path.lineTo(265.0f, 815.105f);
        path.lineTo(405.0f, 125.985f);
        path.lineTo(705.0f, 345.675f);
        path.lineTo(695.0f, 215.855f);
        path.lineTo(185.0f, 695.275f);
        path.lineTo(75.0f, 715.435f);
        path.lineTo(825.0f, 475.585f);
        path.lineTo(255.0f, 715.915f);
        path.lineTo(25.0f, 215.155f);
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
