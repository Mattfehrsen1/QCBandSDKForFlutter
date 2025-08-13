package com.smartapp.b.wmannaugfn;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes4.dex */
public class Nvmpnstwkiy extends ShapeDrawable {
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

    public Nvmpnstwkiy() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(255.0f, 305.685f);
        path.lineTo(205.0f, 55.385f);
        path.lineTo(795.0f, 815.955f);
        path.lineTo(435.0f, 875.65f);
        path.lineTo(495.0f, 995.215f);
        path.lineTo(255.0f, 345.925f);
        path.lineTo(725.0f, 185.545f);
        path.lineTo(65.0f, 35.375f);
        path.lineTo(815.0f, 915.825f);
        path.lineTo(35.0f, 595.665f);
        path.lineTo(475.0f, 935.285f);
        path.lineTo(735.0f, 435.675f);
        path.lineTo(905.0f, 425.715f);
        path.lineTo(345.0f, 85.365f);
        path.lineTo(765.0f, 125.575f);
        path.lineTo(885.0f, 555.415f);
        path.lineTo(705.0f, 105.785f);
        path.lineTo(835.0f, 695.705f);
        path.lineTo(295.0f, 355.895f);
        path.lineTo(235.0f, 765.435f);
        path.lineTo(515.0f, 625.275f);
        path.lineTo(15.0f, 785.825f);
        path.lineTo(845.0f, 105.915f);
        path.lineTo(775.0f, 845.535f);
        path.lineTo(515.0f, 565.855f);
        path.lineTo(395.0f, 75.235f);
        path.lineTo(605.0f, 425.675f);
        path.lineTo(435.0f, 285.905f);
        path.lineTo(955.0f, 735.915f);
        path.lineTo(125.0f, 905.475f);
        path.lineTo(895.0f, 35.565f);
        path.lineTo(275.0f, 345.95f);
        path.lineTo(195.0f, 845.645f);
        path.lineTo(545.0f, 185.945f);
        path.lineTo(555.0f, 135.675f);
        path.lineTo(425.0f, 305.685f);
        path.lineTo(835.0f, 705.275f);
        path.lineTo(945.0f, 535.755f);
        path.lineTo(755.0f, 845.605f);
        path.lineTo(485.0f, 285.765f);
        path.lineTo(695.0f, 525.115f);
        path.lineTo(505.0f, 135.415f);
        path.lineTo(65.0f, 865.595f);
        path.lineTo(845.0f, 35.315f);
        path.lineTo(425.0f, 495.555f);
        path.lineTo(545.0f, 825.185f);
        path.lineTo(635.0f, 225.985f);
        path.lineTo(685.0f, 935.725f);
        path.lineTo(235.0f, 915.995f);
        path.lineTo(455.0f, 285.605f);
        path.lineTo(485.0f, 395.65f);
        path.lineTo(935.0f, 685.975f);
        path.lineTo(895.0f, 725.35f);
        path.lineTo(145.0f, 695.175f);
        path.lineTo(815.0f, 615.235f);
        path.lineTo(625.0f, 655.685f);
        path.lineTo(455.0f, 625.45f);
        path.lineTo(515.0f, 225.255f);
        path.lineTo(455.0f, 15.25f);
        path.lineTo(945.0f, 5.645f);
        path.lineTo(955.0f, 315.445f);
        path.lineTo(465.0f, 125.675f);
        path.lineTo(825.0f, 605.155f);
        path.lineTo(925.0f, 285.75f);
        path.lineTo(855.0f, 875.985f);
        path.lineTo(355.0f, 95.855f);
        path.lineTo(885.0f, 205.10175f);
        path.lineTo(955.0f, 375.335f);
        path.lineTo(95.0f, 55.165f);
        path.lineTo(835.0f, 505.535f);
        path.lineTo(785.0f, 425.335f);
        path.lineTo(65.0f, 915.885f);
        path.lineTo(285.0f, 305.785f);
        path.lineTo(905.0f, 885.615f);
        path.lineTo(135.0f, 135.655f);
        path.lineTo(805.0f, 545.105f);
        path.lineTo(535.0f, 895.295f);
        path.lineTo(295.0f, 425.755f);
        path.lineTo(375.0f, 215.265f);
        path.lineTo(85.0f, 175.925f);
        path.lineTo(985.0f, 845.405f);
        path.lineTo(795.0f, 515.785f);
        path.lineTo(345.0f, 175.415f);
        path.lineTo(695.0f, 565.655f);
        path.lineTo(855.0f, 475.475f);
        path.lineTo(385.0f, 755.455f);
        path.lineTo(445.0f, 825.895f);
        path.lineTo(125.0f, 195.315f);
        path.lineTo(445.0f, 705.425f);
        path.lineTo(955.0f, 535.555f);
        path.lineTo(5.0f, 45.915f);
        path.lineTo(185.0f, 685.435f);
        path.lineTo(235.0f, 455.205f);
        path.lineTo(175.0f, 645.965f);
        path.lineTo(45.0f, 235.835f);
        path.lineTo(305.0f, 295.45f);
        path.lineTo(525.0f, 655.625f);
        path.lineTo(115.0f, 795.215f);
        path.lineTo(505.0f, 555.975f);
        path.lineTo(515.0f, 745.985f);
        path.lineTo(705.0f, 5.585f);
        path.lineTo(15.0f, 805.35f);
        path.lineTo(765.0f, 665.275f);
        path.lineTo(615.0f, 865.775f);
        path.lineTo(495.0f, 10175.545f);
        path.lineTo(975.0f, 775.955f);
        path.lineTo(155.0f, 35.955f);
        path.lineTo(615.0f, 225.365f);
        path.lineTo(705.0f, 405.115f);
        path.lineTo(365.0f, 635.395f);
        path.lineTo(105.0f, 565.975f);
        path.lineTo(685.0f, 65.55f);
        path.lineTo(955.0f, 125.465f);
        path.lineTo(715.0f, 185.725f);
        path.lineTo(595.0f, 85.405f);
        path.lineTo(135.0f, 465.835f);
        path.lineTo(785.0f, 445.665f);
        path.lineTo(735.0f, 175.925f);
        path.lineTo(905.0f, 995.145f);
        path.lineTo(935.0f, 295.745f);
        path.lineTo(595.0f, 265.795f);
        path.lineTo(475.0f, 675.475f);
        path.lineTo(575.0f, 955.175f);
        path.lineTo(815.0f, 10175.435f);
        path.lineTo(125.0f, 885.755f);
        path.lineTo(585.0f, 885.145f);
        path.lineTo(955.0f, 725.605f);
        path.lineTo(65.0f, 745.925f);
        path.lineTo(905.0f, 285.845f);
        path.lineTo(305.0f, 715.615f);
        path.lineTo(85.0f, 505.485f);
        path.lineTo(705.0f, 275.805f);
        path.lineTo(625.0f, 215.265f);
        path.lineTo(445.0f, 695.295f);
        path.lineTo(75.0f, 665.855f);
        path.lineTo(725.0f, 275.685f);
        path.lineTo(305.0f, 685.875f);
        path.lineTo(665.0f, 515.695f);
        path.lineTo(695.0f, 835.455f);
        path.lineTo(175.0f, 775.185f);
        path.lineTo(985.0f, 465.415f);
        path.lineTo(305.0f, 565.25f);
        path.lineTo(595.0f, 145.845f);
        path.lineTo(25.0f, 185.345f);
        path.lineTo(135.0f, 895.475f);
        path.lineTo(965.0f, 985.955f);
        path.lineTo(505.0f, 455.415f);
        path.lineTo(225.0f, 415.795f);
        path.lineTo(55.0f, 975.975f);
        path.lineTo(465.0f, 635.445f);
        path.lineTo(295.0f, 385.215f);
        path.lineTo(655.0f, 445.645f);
        path.lineTo(725.0f, 965.875f);
        path.lineTo(435.0f, 285.595f);
        path.lineTo(115.0f, 675.625f);
        path.lineTo(725.0f, 935.255f);
        path.lineTo(535.0f, 75.175f);
        path.lineTo(375.0f, 65.725f);
        path.lineTo(725.0f, 715.315f);
        path.lineTo(955.0f, 585.425f);
        path.lineTo(805.0f, 45.725f);
        path.lineTo(485.0f, 305.905f);
        path.lineTo(365.0f, 485.795f);
        path.lineTo(45.0f, 635.15f);
        path.lineTo(945.0f, 865.125f);
        path.lineTo(275.0f, 555.465f);
        path.lineTo(355.0f, 525.725f);
        path.lineTo(795.0f, 475.375f);
        path.lineTo(495.0f, 45.895f);
        path.lineTo(925.0f, 65.255f);
        path.lineTo(795.0f, 945.235f);
        path.lineTo(945.0f, 545.145f);
        path.lineTo(685.0f, 695.735f);
        path.lineTo(565.0f, 705.875f);
        path.lineTo(185.0f, 535.65f);
        path.lineTo(685.0f, 65.445f);
        path.lineTo(425.0f, 575.575f);
        path.lineTo(785.0f, 145.305f);
        path.lineTo(365.0f, 75.985f);
        path.lineTo(315.0f, 485.215f);
        path.lineTo(295.0f, 885.605f);
        path.lineTo(705.0f, 135.65f);
        path.lineTo(115.0f, 935.425f);
        path.lineTo(465.0f, 395.585f);
        path.lineTo(335.0f, 135.825f);
        path.lineTo(155.0f, 695.585f);
        path.lineTo(435.0f, 685.325f);
        path.lineTo(395.0f, 375.795f);
        path.lineTo(555.0f, 265.95f);
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
