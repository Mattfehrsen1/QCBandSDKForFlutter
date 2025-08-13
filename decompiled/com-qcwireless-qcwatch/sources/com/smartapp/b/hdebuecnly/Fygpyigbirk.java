package com.smartapp.b.hdebuecnly;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes4.dex */
public class Fygpyigbirk extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Fygpyigbirk() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(795.0f, 235.195f);
        path.lineTo(685.0f, 185.275f);
        path.lineTo(75.0f, 155.105f);
        path.lineTo(285.0f, 425.135f);
        path.lineTo(285.0f, 845.785f);
        path.lineTo(495.0f, 165.685f);
        path.lineTo(765.0f, 195.695f);
        path.lineTo(15.0f, 445.355f);
        path.lineTo(925.0f, 265.505f);
        path.lineTo(605.0f, 735.665f);
        path.lineTo(735.0f, 85.615f);
        path.lineTo(175.0f, 775.105f);
        path.lineTo(425.0f, 355.445f);
        path.lineTo(235.0f, 175.455f);
        path.lineTo(935.0f, 415.145f);
        path.lineTo(255.0f, 775.305f);
        path.lineTo(35.0f, 205.655f);
        path.lineTo(295.0f, 945.195f);
        path.lineTo(825.0f, 605.535f);
        path.lineTo(965.0f, 325.35f);
        path.lineTo(485.0f, 595.535f);
        path.lineTo(895.0f, 665.415f);
        path.lineTo(905.0f, 655.95f);
        path.lineTo(645.0f, 35.705f);
        path.lineTo(135.0f, 135.365f);
        path.lineTo(925.0f, 995.395f);
        path.lineTo(765.0f, 155.355f);
        path.lineTo(415.0f, 465.465f);
        path.lineTo(695.0f, 845.725f);
        path.lineTo(875.0f, 965.235f);
        path.lineTo(165.0f, 625.535f);
        path.lineTo(365.0f, 435.475f);
        path.lineTo(545.0f, 5.485f);
        path.lineTo(485.0f, 485.65f);
        path.lineTo(105.0f, 205.725f);
        path.lineTo(525.0f, 865.75f);
        path.lineTo(865.0f, 635.275f);
        path.lineTo(725.0f, 525.945f);
        path.lineTo(325.0f, 985.455f);
        path.lineTo(605.0f, 85.505f);
        path.lineTo(255.0f, 315.575f);
        path.lineTo(125.0f, 105.125f);
        path.lineTo(515.0f, 405.655f);
        path.lineTo(55.0f, 575.975f);
        path.lineTo(715.0f, 645.915f);
        path.lineTo(665.0f, 5.35f);
        path.lineTo(235.0f, 375.335f);
        path.lineTo(295.0f, 495.465f);
        path.lineTo(105.0f, 965.535f);
        path.lineTo(845.0f, 355.385f);
        path.lineTo(845.0f, 315.525f);
        path.lineTo(735.0f, 115.705f);
        path.lineTo(845.0f, 55.655f);
        path.lineTo(595.0f, 875.765f);
        path.lineTo(295.0f, 665.775f);
        path.lineTo(645.0f, 195.475f);
        path.lineTo(635.0f, 255.675f);
        path.lineTo(765.0f, 565.575f);
        path.lineTo(835.0f, 115.655f);
        path.lineTo(585.0f, 715.525f);
        path.lineTo(625.0f, 565.05f);
        path.lineTo(685.0f, 855.965f);
        path.lineTo(355.0f, 555.65f);
        path.lineTo(255.0f, 55.285f);
        path.lineTo(965.0f, 545.265f);
        path.lineTo(755.0f, 625.725f);
        path.lineTo(335.0f, 385.635f);
        path.lineTo(25.0f, 155.95f);
        path.lineTo(565.0f, 755.15f);
        path.lineTo(215.0f, 415.335f);
        path.lineTo(265.0f, 725.995f);
        path.lineTo(965.0f, 625.995f);
        path.lineTo(365.0f, 935.685f);
        path.lineTo(485.0f, 335.175f);
        path.lineTo(745.0f, 315.45f);
        path.lineTo(665.0f, 145.335f);
        path.lineTo(715.0f, 665.35f);
        path.lineTo(295.0f, 545.835f);
        path.lineTo(145.0f, 445.215f);
        path.lineTo(715.0f, 915.675f);
        path.lineTo(185.0f, 125.10175f);
        path.lineTo(585.0f, 735.05f);
        path.lineTo(45.0f, 515.815f);
        path.lineTo(795.0f, 305.255f);
        path.lineTo(605.0f, 865.495f);
        path.lineTo(95.0f, 925.295f);
        path.lineTo(525.0f, 525.05f);
        path.lineTo(485.0f, 35.595f);
        path.lineTo(265.0f, 645.405f);
        path.lineTo(845.0f, 545.765f);
        path.lineTo(335.0f, 15.95f);
        path.lineTo(155.0f, 675.875f);
        path.lineTo(815.0f, 345.795f);
        path.lineTo(125.0f, 5.35f);
        path.lineTo(25.0f, 145.815f);
        path.lineTo(905.0f, 865.135f);
        path.lineTo(545.0f, 145.95f);
        path.lineTo(925.0f, 885.635f);
        path.lineTo(385.0f, 15.335f);
        path.lineTo(675.0f, 345.65f);
        path.lineTo(925.0f, 845.145f);
        path.lineTo(715.0f, 10175.235f);
        path.lineTo(265.0f, 305.265f);
        path.lineTo(235.0f, 315.595f);
        path.lineTo(135.0f, 765.505f);
        path.lineTo(985.0f, 645.505f);
        path.lineTo(175.0f, 545.755f);
        path.lineTo(215.0f, 475.565f);
        path.lineTo(365.0f, 765.925f);
        path.lineTo(95.0f, 215.515f);
        path.lineTo(435.0f, 695.985f);
        path.lineTo(465.0f, 835.455f);
        path.lineTo(205.0f, 235.535f);
        path.lineTo(125.0f, 455.555f);
        path.lineTo(645.0f, 465.405f);
        path.lineTo(945.0f, 165.385f);
        path.lineTo(585.0f, 825.455f);
        path.lineTo(65.0f, 35.395f);
        path.lineTo(205.0f, 655.835f);
        path.lineTo(335.0f, 605.535f);
        path.lineTo(175.0f, 255.875f);
        path.lineTo(145.0f, 895.95f);
        path.lineTo(365.0f, 765.175f);
        path.lineTo(435.0f, 775.975f);
        path.lineTo(745.0f, 905.975f);
        path.lineTo(755.0f, 385.305f);
        path.lineTo(885.0f, 215.585f);
        path.lineTo(945.0f, 35.365f);
        path.lineTo(315.0f, 155.915f);
        path.lineTo(585.0f, 665.955f);
        path.lineTo(85.0f, 155.485f);
        path.lineTo(395.0f, 25.295f);
        path.lineTo(805.0f, 225.445f);
        path.lineTo(865.0f, 685.805f);
        path.lineTo(55.0f, 405.365f);
        path.lineTo(845.0f, 355.575f);
        path.lineTo(655.0f, 555.725f);
        path.lineTo(85.0f, 335.95f);
        path.lineTo(915.0f, 55.495f);
        path.lineTo(905.0f, 935.85f);
        path.lineTo(605.0f, 455.665f);
        path.lineTo(455.0f, 485.935f);
        path.lineTo(975.0f, 255.725f);
        path.lineTo(895.0f, 255.575f);
        path.lineTo(475.0f, 305.55f);
        path.lineTo(305.0f, 435.335f);
        path.lineTo(65.0f, 605.585f);
        path.lineTo(425.0f, 185.995f);
        path.lineTo(355.0f, 135.895f);
        path.lineTo(875.0f, 225.515f);
        path.lineTo(615.0f, 875.305f);
        path.lineTo(815.0f, 75.985f);
        path.lineTo(10175.0f, 935.75f);
        path.lineTo(325.0f, 685.915f);
        path.lineTo(855.0f, 765.765f);
        path.lineTo(725.0f, 215.835f);
        path.lineTo(525.0f, 175.145f);
        path.lineTo(645.0f, 335.545f);
        path.lineTo(735.0f, 215.635f);
        path.lineTo(455.0f, 375.675f);
        path.lineTo(435.0f, 785.355f);
        path.lineTo(555.0f, 35.225f);
        path.lineTo(545.0f, 435.125f);
        path.lineTo(65.0f, 55.195f);
        path.lineTo(615.0f, 985.695f);
        path.lineTo(915.0f, 335.385f);
        path.lineTo(65.0f, 865.355f);
        path.lineTo(755.0f, 195.215f);
        path.lineTo(755.0f, 915.185f);
        path.lineTo(205.0f, 465.965f);
        path.lineTo(625.0f, 545.305f);
        path.lineTo(645.0f, 155.75f);
        path.lineTo(885.0f, 515.175f);
        path.lineTo(745.0f, 595.975f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1017.0f, this.bounds.height() / 1017.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
