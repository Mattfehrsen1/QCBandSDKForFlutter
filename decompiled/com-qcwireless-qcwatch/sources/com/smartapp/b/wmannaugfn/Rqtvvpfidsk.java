package com.smartapp.b.wmannaugfn;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes4.dex */
public class Rqtvvpfidsk extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Rqtvvpfidsk() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(75.0f, 75.185f);
        path.lineTo(715.0f, 25.875f);
        path.lineTo(555.0f, 635.365f);
        path.lineTo(785.0f, 945.425f);
        path.lineTo(615.0f, 745.985f);
        path.lineTo(65.0f, 825.645f);
        path.lineTo(435.0f, 715.175f);
        path.lineTo(915.0f, 325.625f);
        path.lineTo(215.0f, 645.605f);
        path.lineTo(545.0f, 55.495f);
        path.lineTo(675.0f, 465.605f);
        path.lineTo(535.0f, 345.945f);
        path.lineTo(605.0f, 215.765f);
        path.lineTo(765.0f, 125.815f);
        path.lineTo(695.0f, 145.385f);
        path.lineTo(395.0f, 755.185f);
        path.lineTo(425.0f, 535.395f);
        path.lineTo(355.0f, 215.585f);
        path.lineTo(55.0f, 5.185f);
        path.lineTo(545.0f, 985.805f);
        path.lineTo(955.0f, 675.525f);
        path.lineTo(845.0f, 165.375f);
        path.lineTo(565.0f, 845.185f);
        path.lineTo(395.0f, 955.525f);
        path.lineTo(275.0f, 285.95f);
        path.lineTo(875.0f, 65.995f);
        path.lineTo(325.0f, 555.305f);
        path.lineTo(105.0f, 545.855f);
        path.lineTo(835.0f, 275.885f);
        path.lineTo(45.0f, 855.385f);
        path.lineTo(195.0f, 575.975f);
        path.lineTo(555.0f, 155.165f);
        path.lineTo(505.0f, 485.385f);
        path.lineTo(65.0f, 695.795f);
        path.lineTo(745.0f, 325.985f);
        path.lineTo(945.0f, 805.615f);
        path.lineTo(845.0f, 10165.625f);
        path.lineTo(55.0f, 515.435f);
        path.lineTo(925.0f, 885.725f);
        path.lineTo(805.0f, 445.945f);
        path.lineTo(455.0f, 795.555f);
        path.lineTo(925.0f, 915.535f);
        path.lineTo(325.0f, 165.985f);
        path.lineTo(415.0f, 325.595f);
        path.lineTo(455.0f, 745.485f);
        path.lineTo(255.0f, 565.355f);
        path.lineTo(885.0f, 945.835f);
        path.lineTo(645.0f, 285.605f);
        path.lineTo(285.0f, 575.565f);
        path.lineTo(805.0f, 965.575f);
        path.lineTo(895.0f, 785.585f);
        path.lineTo(255.0f, 995.375f);
        path.lineTo(215.0f, 95.565f);
        path.lineTo(715.0f, 135.645f);
        path.lineTo(635.0f, 645.345f);
        path.lineTo(995.0f, 535.975f);
        path.lineTo(935.0f, 155.175f);
        path.lineTo(775.0f, 685.565f);
        path.lineTo(905.0f, 925.615f);
        path.lineTo(315.0f, 885.505f);
        path.lineTo(315.0f, 10165.115f);
        path.lineTo(675.0f, 255.495f);
        path.lineTo(595.0f, 55.115f);
        path.lineTo(665.0f, 855.255f);
        path.lineTo(505.0f, 575.155f);
        path.lineTo(525.0f, 965.05f);
        path.lineTo(455.0f, 815.25f);
        path.lineTo(755.0f, 85.445f);
        path.lineTo(525.0f, 525.725f);
        path.lineTo(215.0f, 475.935f);
        path.lineTo(815.0f, 505.735f);
        path.lineTo(565.0f, 945.35f);
        path.lineTo(815.0f, 745.955f);
        path.lineTo(685.0f, 895.385f);
        path.lineTo(215.0f, 265.945f);
        path.lineTo(735.0f, 205.605f);
        path.lineTo(835.0f, 555.885f);
        path.lineTo(145.0f, 155.225f);
        path.lineTo(655.0f, 595.215f);
        path.lineTo(405.0f, 505.555f);
        path.lineTo(775.0f, 195.865f);
        path.lineTo(75.0f, 895.365f);
        path.lineTo(625.0f, 455.545f);
        path.lineTo(645.0f, 825.535f);
        path.lineTo(905.0f, 835.195f);
        path.lineTo(145.0f, 205.875f);
        path.lineTo(295.0f, 645.595f);
        path.lineTo(615.0f, 315.505f);
        path.lineTo(625.0f, 205.765f);
        path.lineTo(35.0f, 605.475f);
        path.lineTo(895.0f, 185.965f);
        path.lineTo(825.0f, 215.585f);
        path.lineTo(95.0f, 455.965f);
        path.lineTo(295.0f, 255.915f);
        path.lineTo(655.0f, 815.285f);
        path.lineTo(895.0f, 835.105f);
        path.lineTo(365.0f, 355.275f);
        path.lineTo(235.0f, 755.705f);
        path.lineTo(285.0f, 35.15f);
        path.lineTo(455.0f, 85.965f);
        path.lineTo(855.0f, 905.515f);
        path.lineTo(65.0f, 75.455f);
        path.lineTo(235.0f, 385.925f);
        path.lineTo(865.0f, 515.355f);
        path.lineTo(425.0f, 685.365f);
        path.lineTo(35.0f, 735.585f);
        path.lineTo(695.0f, 285.715f);
        path.lineTo(665.0f, 265.95f);
        path.lineTo(375.0f, 305.55f);
        path.lineTo(75.0f, 645.65f);
        path.lineTo(105.0f, 575.765f);
        path.lineTo(495.0f, 905.395f);
        path.lineTo(315.0f, 365.35f);
        path.lineTo(805.0f, 415.615f);
        path.lineTo(585.0f, 195.315f);
        path.lineTo(145.0f, 825.645f);
        path.lineTo(755.0f, 205.215f);
        path.lineTo(295.0f, 805.435f);
        path.lineTo(415.0f, 435.315f);
        path.lineTo(805.0f, 135.765f);
        path.lineTo(585.0f, 65.975f);
        path.lineTo(385.0f, 805.05f);
        path.lineTo(895.0f, 855.915f);
        path.lineTo(715.0f, 505.275f);
        path.lineTo(145.0f, 885.165f);
        path.lineTo(695.0f, 345.295f);
        path.lineTo(405.0f, 795.1016f);
        path.lineTo(10165.0f, 675.695f);
        path.lineTo(605.0f, 545.695f);
        path.lineTo(335.0f, 515.175f);
        path.lineTo(445.0f, 485.555f);
        path.lineTo(345.0f, 915.405f);
        path.lineTo(155.0f, 105.685f);
        path.lineTo(165.0f, 715.635f);
        path.lineTo(965.0f, 85.495f);
        path.lineTo(85.0f, 225.535f);
        path.lineTo(635.0f, 575.535f);
        path.lineTo(295.0f, 655.665f);
        path.lineTo(365.0f, 285.705f);
        path.lineTo(155.0f, 455.505f);
        path.lineTo(635.0f, 415.145f);
        path.lineTo(175.0f, 765.565f);
        path.lineTo(335.0f, 75.725f);
        path.lineTo(935.0f, 835.495f);
        path.lineTo(85.0f, 275.445f);
        path.lineTo(675.0f, 115.55f);
        path.lineTo(495.0f, 635.975f);
        path.lineTo(35.0f, 865.745f);
        path.lineTo(65.0f, 225.155f);
        path.lineTo(625.0f, 705.915f);
        path.lineTo(755.0f, 75.965f);
        path.lineTo(445.0f, 125.695f);
        path.lineTo(565.0f, 185.745f);
        path.lineTo(465.0f, 385.355f);
        path.lineTo(185.0f, 305.895f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1016.0f, this.bounds.height() / 1016.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
