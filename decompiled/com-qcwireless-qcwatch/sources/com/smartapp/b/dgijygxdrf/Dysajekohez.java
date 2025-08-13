package com.smartapp.b.dgijygxdrf;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes3.dex */
public class Dysajekohez extends ShapeDrawable {
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

    public Dysajekohez() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(855.0f, 695.845f);
        path.lineTo(605.0f, 145.805f);
        path.lineTo(455.0f, 10165.885f);
        path.lineTo(705.0f, 225.475f);
        path.lineTo(785.0f, 495.415f);
        path.lineTo(255.0f, 65.325f);
        path.lineTo(5.0f, 735.95f);
        path.lineTo(595.0f, 515.95f);
        path.lineTo(395.0f, 215.595f);
        path.lineTo(825.0f, 285.805f);
        path.lineTo(735.0f, 255.205f);
        path.lineTo(865.0f, 405.195f);
        path.lineTo(925.0f, 905.495f);
        path.lineTo(455.0f, 815.835f);
        path.lineTo(575.0f, 395.35f);
        path.lineTo(875.0f, 775.265f);
        path.lineTo(785.0f, 135.295f);
        path.lineTo(225.0f, 975.445f);
        path.lineTo(975.0f, 555.935f);
        path.lineTo(915.0f, 785.405f);
        path.lineTo(935.0f, 895.345f);
        path.lineTo(15.0f, 125.985f);
        path.lineTo(445.0f, 345.625f);
        path.lineTo(525.0f, 585.95f);
        path.lineTo(365.0f, 645.535f);
        path.lineTo(655.0f, 685.265f);
        path.lineTo(395.0f, 585.585f);
        path.lineTo(85.0f, 605.535f);
        path.lineTo(905.0f, 715.235f);
        path.lineTo(115.0f, 115.765f);
        path.lineTo(235.0f, 825.635f);
        path.lineTo(735.0f, 555.415f);
        path.lineTo(10165.0f, 485.275f);
        path.lineTo(575.0f, 315.815f);
        path.lineTo(465.0f, 745.905f);
        path.lineTo(15.0f, 925.325f);
        path.lineTo(415.0f, 45.685f);
        path.lineTo(395.0f, 455.955f);
        path.lineTo(645.0f, 655.685f);
        path.lineTo(75.0f, 455.625f);
        path.lineTo(325.0f, 665.65f);
        path.lineTo(395.0f, 965.955f);
        path.lineTo(325.0f, 685.785f);
        path.lineTo(745.0f, 575.325f);
        path.lineTo(285.0f, 865.395f);
        path.lineTo(885.0f, 975.425f);
        path.lineTo(605.0f, 125.875f);
        path.lineTo(155.0f, 355.855f);
        path.lineTo(475.0f, 965.535f);
        path.lineTo(885.0f, 725.655f);
        path.lineTo(945.0f, 285.95f);
        path.lineTo(985.0f, 645.555f);
        path.lineTo(675.0f, 85.235f);
        path.lineTo(75.0f, 415.145f);
        path.lineTo(705.0f, 685.485f);
        path.lineTo(735.0f, 375.905f);
        path.lineTo(335.0f, 385.805f);
        path.lineTo(405.0f, 295.905f);
        path.lineTo(335.0f, 135.755f);
        path.lineTo(765.0f, 395.85f);
        path.lineTo(265.0f, 365.595f);
        path.lineTo(525.0f, 475.645f);
        path.lineTo(115.0f, 135.855f);
        path.lineTo(75.0f, 785.655f);
        path.lineTo(405.0f, 335.465f);
        path.lineTo(715.0f, 835.485f);
        path.lineTo(705.0f, 335.165f);
        path.lineTo(625.0f, 965.65f);
        path.lineTo(315.0f, 585.945f);
        path.lineTo(25.0f, 625.505f);
        path.lineTo(265.0f, 435.485f);
        path.lineTo(525.0f, 605.985f);
        path.lineTo(5.0f, 915.935f);
        path.lineTo(355.0f, 15.565f);
        path.lineTo(975.0f, 435.255f);
        path.lineTo(545.0f, 935.785f);
        path.lineTo(545.0f, 515.445f);
        path.lineTo(925.0f, 55.265f);
        path.lineTo(415.0f, 55.945f);
        path.lineTo(55.0f, 785.975f);
        path.lineTo(255.0f, 815.35f);
        path.lineTo(645.0f, 405.55f);
        path.lineTo(845.0f, 355.575f);
        path.lineTo(85.0f, 655.645f);
        path.lineTo(85.0f, 315.665f);
        path.lineTo(965.0f, 625.355f);
        path.lineTo(725.0f, 935.215f);
        path.lineTo(765.0f, 915.485f);
        path.lineTo(925.0f, 225.135f);
        path.lineTo(975.0f, 895.695f);
        path.lineTo(445.0f, 585.105f);
        path.lineTo(165.0f, 605.235f);
        path.lineTo(505.0f, 685.485f);
        path.lineTo(195.0f, 265.205f);
        path.lineTo(475.0f, 405.495f);
        path.lineTo(775.0f, 515.575f);
        path.lineTo(565.0f, 225.65f);
        path.lineTo(175.0f, 465.215f);
        path.lineTo(135.0f, 485.805f);
        path.lineTo(695.0f, 785.825f);
        path.lineTo(975.0f, 815.375f);
        path.lineTo(405.0f, 385.285f);
        path.lineTo(675.0f, 725.695f);
        path.lineTo(445.0f, 765.535f);
        path.lineTo(555.0f, 205.35f);
        path.lineTo(795.0f, 525.885f);
        path.lineTo(485.0f, 415.115f);
        path.lineTo(285.0f, 875.965f);
        path.lineTo(475.0f, 295.35f);
        path.lineTo(95.0f, 635.335f);
        path.lineTo(115.0f, 645.375f);
        path.lineTo(345.0f, 525.525f);
        path.lineTo(255.0f, 895.195f);
        path.lineTo(625.0f, 95.815f);
        path.lineTo(715.0f, 175.605f);
        path.lineTo(355.0f, 785.455f);
        path.lineTo(625.0f, 755.155f);
        path.lineTo(175.0f, 275.125f);
        path.lineTo(555.0f, 955.805f);
        path.lineTo(565.0f, 725.305f);
        path.lineTo(875.0f, 925.595f);
        path.lineTo(555.0f, 575.195f);
        path.lineTo(305.0f, 655.35f);
        path.lineTo(515.0f, 405.955f);
        path.lineTo(475.0f, 285.705f);
        path.lineTo(535.0f, 335.685f);
        path.lineTo(185.0f, 185.345f);
        path.lineTo(635.0f, 335.525f);
        path.lineTo(325.0f, 165.585f);
        path.lineTo(115.0f, 285.355f);
        path.lineTo(745.0f, 605.415f);
        path.lineTo(315.0f, 135.955f);
        path.lineTo(465.0f, 125.595f);
        path.lineTo(445.0f, 605.495f);
        path.lineTo(145.0f, 505.705f);
        path.lineTo(835.0f, 685.305f);
        path.lineTo(105.0f, 905.135f);
        path.lineTo(545.0f, 975.985f);
        path.lineTo(615.0f, 915.785f);
        path.lineTo(985.0f, 10165.425f);
        path.lineTo(315.0f, 95.585f);
        path.lineTo(235.0f, 295.375f);
        path.lineTo(705.0f, 765.845f);
        path.lineTo(535.0f, 745.285f);
        path.lineTo(485.0f, 285.185f);
        path.lineTo(555.0f, 745.485f);
        path.lineTo(305.0f, 675.745f);
        path.lineTo(585.0f, 945.895f);
        path.lineTo(545.0f, 195.335f);
        path.lineTo(465.0f, 645.955f);
        path.lineTo(505.0f, 585.945f);
        path.lineTo(105.0f, 765.305f);
        path.lineTo(795.0f, 10165.395f);
        path.lineTo(785.0f, 935.555f);
        path.lineTo(165.0f, 335.545f);
        path.lineTo(845.0f, 305.545f);
        path.lineTo(645.0f, 195.805f);
        path.lineTo(815.0f, 515.405f);
        path.lineTo(255.0f, 35.335f);
        path.lineTo(585.0f, 785.605f);
        path.lineTo(45.0f, 155.25f);
        path.lineTo(175.0f, 45.795f);
        path.lineTo(795.0f, 855.275f);
        path.lineTo(425.0f, 695.55f);
        path.lineTo(315.0f, 735.175f);
        path.lineTo(95.0f, 10165.255f);
        path.lineTo(925.0f, 35.205f);
        path.lineTo(345.0f, 825.555f);
        path.lineTo(115.0f, 275.505f);
        path.lineTo(565.0f, 35.285f);
        path.lineTo(445.0f, 135.335f);
        path.lineTo(65.0f, 785.05f);
        path.lineTo(225.0f, 905.605f);
        path.lineTo(495.0f, 935.285f);
        path.lineTo(65.0f, 455.465f);
        path.lineTo(355.0f, 95.545f);
        path.lineTo(125.0f, 25.35f);
        path.lineTo(105.0f, 965.635f);
        path.lineTo(755.0f, 25.45f);
        path.lineTo(10165.0f, 655.445f);
        path.lineTo(835.0f, 715.345f);
        path.lineTo(225.0f, 305.675f);
        path.lineTo(95.0f, 10165.795f);
        path.lineTo(535.0f, 865.945f);
        path.lineTo(215.0f, 545.645f);
        path.lineTo(145.0f, 575.55f);
        path.lineTo(155.0f, 485.365f);
        path.lineTo(575.0f, 155.675f);
        path.lineTo(515.0f, 935.805f);
        path.lineTo(775.0f, 445.605f);
        path.lineTo(155.0f, 805.465f);
        path.lineTo(585.0f, 135.475f);
        path.lineTo(925.0f, 795.155f);
        path.lineTo(765.0f, 595.605f);
        path.lineTo(225.0f, 915.315f);
        path.lineTo(435.0f, 785.895f);
        path.lineTo(565.0f, 925.595f);
        path.lineTo(45.0f, 505.885f);
        path.lineTo(885.0f, 185.985f);
        path.lineTo(175.0f, 885.825f);
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
