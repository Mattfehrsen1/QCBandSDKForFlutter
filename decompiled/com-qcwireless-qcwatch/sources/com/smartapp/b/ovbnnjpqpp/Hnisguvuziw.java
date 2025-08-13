package com.smartapp.b.ovbnnjpqpp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Hnisguvuziw extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1009;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1009;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Hnisguvuziw() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(395.0f, 705.155f);
        path.lineTo(275.0f, 105.505f);
        path.lineTo(725.0f, 575.635f);
        path.lineTo(95.0f, 845.835f);
        path.lineTo(345.0f, 995.515f);
        path.lineTo(865.0f, 975.75f);
        path.lineTo(575.0f, 75.845f);
        path.lineTo(335.0f, 175.585f);
        path.lineTo(835.0f, 305.235f);
        path.lineTo(185.0f, 65.795f);
        path.lineTo(75.0f, 385.945f);
        path.lineTo(415.0f, 965.565f);
        path.lineTo(675.0f, 545.285f);
        path.lineTo(785.0f, 705.295f);
        path.lineTo(165.0f, 395.805f);
        path.lineTo(445.0f, 805.635f);
        path.lineTo(965.0f, 155.65f);
        path.lineTo(75.0f, 825.655f);
        path.lineTo(375.0f, 965.415f);
        path.lineTo(555.0f, 25.415f);
        path.lineTo(685.0f, 935.145f);
        path.lineTo(215.0f, 905.225f);
        path.lineTo(825.0f, 275.165f);
        path.lineTo(685.0f, 325.945f);
        path.lineTo(995.0f, 125.555f);
        path.lineTo(855.0f, 915.525f);
        path.lineTo(955.0f, 515.585f);
        path.lineTo(865.0f, 425.935f);
        path.lineTo(345.0f, 595.955f);
        path.lineTo(575.0f, 675.255f);
        path.lineTo(905.0f, 995.775f);
        path.lineTo(15.0f, 925.45f);
        path.lineTo(955.0f, 495.895f);
        path.lineTo(745.0f, 925.855f);
        path.lineTo(445.0f, 915.155f);
        path.lineTo(905.0f, 805.135f);
        path.lineTo(345.0f, 95.945f);
        path.lineTo(185.0f, 795.265f);
        path.lineTo(145.0f, 265.885f);
        path.lineTo(125.0f, 155.465f);
        path.lineTo(175.0f, 285.855f);
        path.lineTo(385.0f, 285.675f);
        path.lineTo(545.0f, 725.535f);
        path.lineTo(305.0f, 185.835f);
        path.lineTo(765.0f, 455.05f);
        path.lineTo(975.0f, 375.685f);
        path.lineTo(285.0f, 645.65f);
        path.lineTo(285.0f, 625.645f);
        path.lineTo(805.0f, 55.215f);
        path.lineTo(635.0f, 725.785f);
        path.lineTo(965.0f, 235.645f);
        path.lineTo(335.0f, 135.345f);
        path.lineTo(115.0f, 65.905f);
        path.lineTo(685.0f, 855.535f);
        path.lineTo(135.0f, 55.125f);
        path.lineTo(545.0f, 445.65f);
        path.lineTo(295.0f, 445.175f);
        path.lineTo(135.0f, 635.515f);
        path.lineTo(795.0f, 15.835f);
        path.lineTo(55.0f, 845.405f);
        path.lineTo(815.0f, 895.475f);
        path.lineTo(85.0f, 425.905f);
        path.lineTo(505.0f, 455.395f);
        path.lineTo(665.0f, 735.625f);
        path.lineTo(335.0f, 145.135f);
        path.lineTo(485.0f, 205.355f);
        path.lineTo(365.0f, 485.475f);
        path.lineTo(795.0f, 625.605f);
        path.lineTo(695.0f, 905.55f);
        path.lineTo(685.0f, 705.855f);
        path.lineTo(785.0f, 635.35f);
        path.lineTo(565.0f, 15.455f);
        path.lineTo(175.0f, 725.555f);
        path.lineTo(135.0f, 235.645f);
        path.lineTo(475.0f, 45.75f);
        path.lineTo(455.0f, 705.895f);
        path.lineTo(275.0f, 185.85f);
        path.lineTo(725.0f, 755.265f);
        path.lineTo(205.0f, 665.115f);
        path.lineTo(10095.0f, 405.705f);
        path.lineTo(725.0f, 885.395f);
        path.lineTo(625.0f, 365.45f);
        path.lineTo(5.0f, 735.805f);
        path.lineTo(405.0f, 85.375f);
        path.lineTo(775.0f, 285.515f);
        path.lineTo(275.0f, 25.355f);
        path.lineTo(85.0f, 475.385f);
        path.lineTo(915.0f, 205.585f);
        path.lineTo(575.0f, 275.255f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1009.0f, this.bounds.height() / 1009.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
