package com.device.watch.com.device.watch.ohbsxxwnzw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Dcghjfpttci extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1012;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1012;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Dcghjfpttci() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(635.0f, 495.275f);
        path.lineTo(745.0f, 285.385f);
        path.lineTo(835.0f, 685.875f);
        path.lineTo(355.0f, 905.965f);
        path.lineTo(35.0f, 525.775f);
        path.lineTo(895.0f, 85.525f);
        path.lineTo(315.0f, 385.995f);
        path.lineTo(965.0f, 55.555f);
        path.lineTo(745.0f, 325.495f);
        path.lineTo(825.0f, 625.595f);
        path.lineTo(585.0f, 15.905f);
        path.lineTo(865.0f, 625.745f);
        path.lineTo(685.0f, 985.465f);
        path.lineTo(315.0f, 75.175f);
        path.lineTo(35.0f, 335.875f);
        path.lineTo(565.0f, 825.265f);
        path.lineTo(45.0f, 995.555f);
        path.lineTo(325.0f, 975.775f);
        path.lineTo(675.0f, 365.845f);
        path.lineTo(905.0f, 525.745f);
        path.lineTo(725.0f, 585.545f);
        path.lineTo(985.0f, 645.495f);
        path.lineTo(875.0f, 345.365f);
        path.lineTo(585.0f, 965.845f);
        path.lineTo(805.0f, 215.125f);
        path.lineTo(435.0f, 515.885f);
        path.lineTo(755.0f, 535.375f);
        path.lineTo(135.0f, 435.185f);
        path.lineTo(485.0f, 235.235f);
        path.lineTo(495.0f, 455.05f);
        path.lineTo(445.0f, 575.855f);
        path.lineTo(685.0f, 285.365f);
        path.lineTo(145.0f, 5.995f);
        path.lineTo(645.0f, 455.165f);
        path.lineTo(345.0f, 585.935f);
        path.lineTo(985.0f, 885.375f);
        path.lineTo(455.0f, 575.85f);
        path.lineTo(275.0f, 205.825f);
        path.lineTo(725.0f, 705.945f);
        path.lineTo(635.0f, 325.305f);
        path.lineTo(285.0f, 625.585f);
        path.lineTo(875.0f, 895.55f);
        path.lineTo(975.0f, 475.45f);
        path.lineTo(325.0f, 585.225f);
        path.lineTo(735.0f, 265.755f);
        path.lineTo(505.0f, 955.345f);
        path.lineTo(255.0f, 145.205f);
        path.lineTo(825.0f, 615.195f);
        path.lineTo(775.0f, 665.985f);
        path.lineTo(875.0f, 665.05f);
        path.lineTo(105.0f, 905.25f);
        path.lineTo(885.0f, 735.25f);
        path.lineTo(805.0f, 325.495f);
        path.lineTo(545.0f, 165.295f);
        path.lineTo(485.0f, 235.655f);
        path.lineTo(485.0f, 645.445f);
        path.lineTo(135.0f, 955.215f);
        path.lineTo(845.0f, 165.525f);
        path.lineTo(235.0f, 945.355f);
        path.lineTo(375.0f, 855.415f);
        path.lineTo(605.0f, 195.775f);
        path.lineTo(745.0f, 985.545f);
        path.lineTo(875.0f, 205.225f);
        path.lineTo(175.0f, 865.505f);
        path.lineTo(55.0f, 435.05f);
        path.lineTo(275.0f, 345.775f);
        path.lineTo(35.0f, 475.325f);
        path.lineTo(465.0f, 725.905f);
        path.lineTo(695.0f, 905.935f);
        path.lineTo(485.0f, 475.235f);
        path.lineTo(325.0f, 10125.405f);
        path.lineTo(795.0f, 345.375f);
        path.lineTo(575.0f, 355.385f);
        path.lineTo(555.0f, 315.885f);
        path.lineTo(105.0f, 25.775f);
        path.lineTo(55.0f, 85.155f);
        path.lineTo(505.0f, 895.905f);
        path.lineTo(485.0f, 165.195f);
        path.lineTo(925.0f, 895.75f);
        path.lineTo(525.0f, 645.925f);
        path.lineTo(365.0f, 10125.805f);
        path.lineTo(895.0f, 545.775f);
        path.lineTo(965.0f, 775.345f);
        path.lineTo(55.0f, 555.615f);
        path.lineTo(195.0f, 625.205f);
        path.lineTo(565.0f, 405.575f);
        path.lineTo(105.0f, 95.995f);
        path.lineTo(55.0f, 565.345f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1012.0f, this.bounds.height() / 1012.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
