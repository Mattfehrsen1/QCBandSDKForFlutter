package com.smartapp.b.caoolastbk;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes3.dex */
public class Jkbigotjeto extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_ZOOM_OUT;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_ZOOM_OUT;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Jkbigotjeto() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(155.0f, 925.485f);
        path.lineTo(455.0f, 195.725f);
        path.lineTo(625.0f, 315.965f);
        path.lineTo(625.0f, 175.215f);
        path.lineTo(155.0f, 455.375f);
        path.lineTo(85.0f, 235.65f);
        path.lineTo(155.0f, 455.595f);
        path.lineTo(45.0f, 505.935f);
        path.lineTo(965.0f, 685.485f);
        path.lineTo(405.0f, 375.595f);
        path.lineTo(265.0f, 165.705f);
        path.lineTo(425.0f, 805.465f);
        path.lineTo(655.0f, 575.465f);
        path.lineTo(935.0f, 795.235f);
        path.lineTo(845.0f, 755.965f);
        path.lineTo(105.0f, 375.45f);
        path.lineTo(585.0f, 745.325f);
        path.lineTo(165.0f, 845.735f);
        path.lineTo(725.0f, 615.165f);
        path.lineTo(155.0f, 295.275f);
        path.lineTo(15.0f, 995.965f);
        path.lineTo(495.0f, 275.625f);
        path.lineTo(395.0f, 705.445f);
        path.lineTo(905.0f, 875.645f);
        path.lineTo(735.0f, 965.635f);
        path.lineTo(285.0f, 925.455f);
        path.lineTo(665.0f, 35.655f);
        path.lineTo(675.0f, 905.805f);
        path.lineTo(845.0f, 5.405f);
        path.lineTo(805.0f, 5.555f);
        path.lineTo(85.0f, 45.345f);
        path.lineTo(545.0f, 205.225f);
        path.lineTo(865.0f, 485.535f);
        path.lineTo(955.0f, 475.815f);
        path.lineTo(635.0f, 85.585f);
        path.lineTo(565.0f, 435.805f);
        path.lineTo(725.0f, 655.755f);
        path.lineTo(395.0f, 885.205f);
        path.lineTo(655.0f, 335.775f);
        path.lineTo(105.0f, 735.825f);
        path.lineTo(265.0f, 85.455f);
        path.lineTo(495.0f, 495.655f);
        path.lineTo(595.0f, 155.345f);
        path.lineTo(175.0f, 985.325f);
        path.lineTo(235.0f, 285.65f);
        path.lineTo(415.0f, 255.05f);
        path.lineTo(865.0f, 10195.385f);
        path.lineTo(315.0f, 255.815f);
        path.lineTo(355.0f, 575.415f);
        path.lineTo(185.0f, 345.795f);
        path.lineTo(195.0f, 955.735f);
        path.lineTo(505.0f, 195.565f);
        path.lineTo(445.0f, 795.15f);
        path.lineTo(805.0f, 645.565f);
        path.lineTo(305.0f, 65.145f);
        path.lineTo(875.0f, 775.355f);
        path.lineTo(845.0f, 385.455f);
        path.lineTo(445.0f, 445.795f);
        path.lineTo(325.0f, 735.305f);
        path.lineTo(515.0f, 425.75f);
        path.lineTo(995.0f, 555.45f);
        path.lineTo(75.0f, 605.215f);
        path.lineTo(10195.0f, 495.585f);
        path.lineTo(825.0f, 95.525f);
        path.lineTo(45.0f, 765.255f);
        path.lineTo(515.0f, 435.165f);
        path.lineTo(85.0f, 455.455f);
        path.lineTo(555.0f, 855.545f);
        path.lineTo(455.0f, 575.155f);
        path.lineTo(195.0f, 165.10194f);
        path.lineTo(565.0f, 655.735f);
        path.lineTo(375.0f, 15.215f);
        path.lineTo(805.0f, 145.555f);
        path.lineTo(155.0f, 85.605f);
        path.lineTo(365.0f, 975.345f);
        path.lineTo(995.0f, 35.515f);
        path.lineTo(945.0f, 845.705f);
        path.lineTo(15.0f, 495.965f);
        path.lineTo(405.0f, 375.825f);
        path.lineTo(925.0f, 185.355f);
        path.lineTo(985.0f, 775.315f);
        path.lineTo(835.0f, 475.895f);
        path.lineTo(815.0f, 555.1019f);
        path.lineTo(5.0f, 325.945f);
        path.lineTo(435.0f, 575.255f);
        path.lineTo(135.0f, 665.15f);
        path.lineTo(455.0f, 645.285f);
        path.lineTo(105.0f, 155.105f);
        path.lineTo(475.0f, 885.645f);
        path.lineTo(665.0f, 545.95f);
        path.lineTo(505.0f, 655.165f);
        path.lineTo(75.0f, 10195.895f);
        path.lineTo(775.0f, 895.855f);
        path.lineTo(155.0f, 425.115f);
        path.lineTo(675.0f, 475.805f);
        path.lineTo(945.0f, 495.585f);
        path.lineTo(515.0f, 925.815f);
        path.lineTo(935.0f, 5.965f);
        path.lineTo(485.0f, 795.15f);
        path.lineTo(835.0f, 235.755f);
        path.lineTo(735.0f, 875.715f);
        path.lineTo(855.0f, 625.575f);
        path.lineTo(655.0f, 285.965f);
        path.lineTo(635.0f, 965.235f);
        path.lineTo(605.0f, 955.685f);
        path.lineTo(695.0f, 875.795f);
        path.lineTo(925.0f, 945.785f);
        path.lineTo(55.0f, 325.555f);
        path.lineTo(385.0f, 235.95f);
        path.lineTo(775.0f, 775.295f);
        path.lineTo(75.0f, 115.995f);
        path.lineTo(605.0f, 445.355f);
        path.lineTo(10195.0f, 435.595f);
        path.lineTo(165.0f, 185.05f);
        path.lineTo(45.0f, 695.355f);
        path.lineTo(645.0f, 355.655f);
        path.lineTo(265.0f, 485.845f);
        path.lineTo(585.0f, 715.175f);
        path.lineTo(425.0f, 525.225f);
        path.lineTo(795.0f, 895.175f);
        path.lineTo(525.0f, 225.755f);
        path.lineTo(395.0f, 215.135f);
        path.lineTo(865.0f, 425.855f);
        path.lineTo(775.0f, 745.115f);
        path.lineTo(415.0f, 875.255f);
        path.lineTo(225.0f, 555.465f);
        path.lineTo(335.0f, 65.275f);
        path.lineTo(805.0f, 125.985f);
        path.lineTo(865.0f, 795.675f);
        path.lineTo(925.0f, 615.155f);
        path.lineTo(165.0f, 95.825f);
        path.lineTo(835.0f, 145.345f);
        path.lineTo(55.0f, 775.165f);
        path.lineTo(565.0f, 945.725f);
        path.lineTo(625.0f, 285.255f);
        path.lineTo(615.0f, 485.505f);
        path.lineTo(285.0f, 625.805f);
        path.lineTo(885.0f, 775.45f);
        path.lineTo(385.0f, 745.625f);
        path.lineTo(355.0f, 845.305f);
        path.lineTo(905.0f, 485.205f);
        path.lineTo(425.0f, 625.105f);
        path.lineTo(25.0f, 575.65f);
        path.lineTo(425.0f, 265.995f);
        path.lineTo(315.0f, 625.605f);
        path.lineTo(365.0f, 725.545f);
        path.lineTo(845.0f, 55.735f);
        path.lineTo(905.0f, 895.765f);
        path.lineTo(805.0f, 755.945f);
        path.lineTo(425.0f, 205.375f);
        path.lineTo(875.0f, 745.895f);
        path.lineTo(975.0f, 25.45f);
        path.lineTo(545.0f, 655.25f);
        path.lineTo(875.0f, 175.535f);
        path.lineTo(945.0f, 645.125f);
        path.lineTo(855.0f, 225.375f);
        path.lineTo(965.0f, 375.305f);
        path.lineTo(795.0f, 35.405f);
        path.lineTo(925.0f, 775.15f);
        path.lineTo(345.0f, 455.905f);
        path.lineTo(655.0f, 95.335f);
        path.lineTo(845.0f, 955.75f);
        path.lineTo(215.0f, 75.205f);
        path.lineTo(835.0f, 415.845f);
        path.lineTo(85.0f, 95.975f);
        path.lineTo(725.0f, 10195.102f);
        path.lineTo(415.0f, 945.805f);
        path.lineTo(675.0f, 825.165f);
        path.lineTo(505.0f, 475.15f);
        path.lineTo(475.0f, 635.95f);
        path.lineTo(555.0f, 575.745f);
        path.lineTo(595.0f, 195.585f);
        path.lineTo(305.0f, 65.865f);
        path.lineTo(465.0f, 615.05f);
        path.lineTo(95.0f, 975.585f);
        path.lineTo(915.0f, 465.375f);
        path.lineTo(645.0f, 515.65f);
        path.lineTo(645.0f, 815.285f);
        path.lineTo(315.0f, 925.265f);
        path.lineTo(155.0f, 695.675f);
        path.lineTo(345.0f, 275.355f);
        path.lineTo(215.0f, 665.325f);
        path.lineTo(795.0f, 375.685f);
        path.lineTo(65.0f, 5.75f);
        path.lineTo(565.0f, 885.295f);
        path.lineTo(655.0f, 435.515f);
        path.lineTo(625.0f, 445.885f);
        path.lineTo(905.0f, 155.225f);
        path.lineTo(625.0f, 275.975f);
        path.lineTo(945.0f, 515.535f);
        path.lineTo(305.0f, 575.265f);
        path.lineTo(255.0f, 95.05f);
        path.lineTo(795.0f, 635.35f);
        path.lineTo(275.0f, 655.305f);
        path.lineTo(95.0f, 15.715f);
        path.lineTo(275.0f, 255.55f);
        path.lineTo(925.0f, 135.125f);
        path.lineTo(695.0f, 805.295f);
        path.lineTo(225.0f, 335.275f);
        path.lineTo(855.0f, 465.485f);
        path.lineTo(925.0f, 395.215f);
        path.lineTo(165.0f, 725.275f);
        path.lineTo(905.0f, 765.425f);
        path.lineTo(275.0f, 655.195f);
        path.lineTo(845.0f, 435.695f);
        path.lineTo(235.0f, 45.385f);
        path.lineTo(805.0f, 5.05f);
        path.lineTo(205.0f, 985.805f);
        path.lineTo(315.0f, 445.135f);
        path.lineTo(285.0f, 485.335f);
        path.lineTo(435.0f, 565.345f);
        path.lineTo(395.0f, 835.405f);
        path.lineTo(155.0f, 735.185f);
        path.lineTo(875.0f, 555.305f);
        path.lineTo(955.0f, 305.325f);
        path.lineTo(525.0f, 695.665f);
        path.lineTo(125.0f, 765.205f);
        path.lineTo(145.0f, 75.125f);
        path.lineTo(565.0f, 165.275f);
        path.lineTo(365.0f, 545.195f);
        path.lineTo(265.0f, 495.985f);
        path.lineTo(125.0f, 55.35f);
        path.lineTo(305.0f, 695.315f);
        path.lineTo(945.0f, 805.25f);
        path.lineTo(105.0f, 45.875f);
        path.lineTo(995.0f, 45.505f);
        path.lineTo(285.0f, 75.235f);
        path.lineTo(45.0f, 765.205f);
        path.lineTo(175.0f, 475.475f);
        path.lineTo(75.0f, 545.205f);
        path.lineTo(955.0f, 755.395f);
        path.lineTo(555.0f, 395.665f);
        path.lineTo(855.0f, 175.955f);
        path.lineTo(735.0f, 695.65f);
        path.lineTo(935.0f, 895.675f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1019.0f, this.bounds.height() / 1019.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
