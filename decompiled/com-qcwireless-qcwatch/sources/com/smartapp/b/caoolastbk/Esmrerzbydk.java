package com.smartapp.b.caoolastbk;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes3.dex */
public class Esmrerzbydk extends ShapeDrawable {
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

    public Esmrerzbydk() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(465.0f, 305.485f);
        path.lineTo(925.0f, 935.55f);
        path.lineTo(705.0f, 655.265f);
        path.lineTo(325.0f, 965.785f);
        path.lineTo(665.0f, 35.55f);
        path.lineTo(5.0f, 375.205f);
        path.lineTo(855.0f, 535.535f);
        path.lineTo(565.0f, 635.745f);
        path.lineTo(575.0f, 415.685f);
        path.lineTo(325.0f, 795.265f);
        path.lineTo(10175.0f, 305.845f);
        path.lineTo(995.0f, 675.685f);
        path.lineTo(395.0f, 815.115f);
        path.lineTo(375.0f, 435.65f);
        path.lineTo(705.0f, 765.355f);
        path.lineTo(465.0f, 765.585f);
        path.lineTo(295.0f, 895.655f);
        path.lineTo(15.0f, 925.815f);
        path.lineTo(285.0f, 885.885f);
        path.lineTo(845.0f, 205.265f);
        path.lineTo(385.0f, 635.935f);
        path.lineTo(825.0f, 765.535f);
        path.lineTo(825.0f, 495.225f);
        path.lineTo(275.0f, 935.395f);
        path.lineTo(405.0f, 835.155f);
        path.lineTo(835.0f, 545.365f);
        path.lineTo(645.0f, 535.275f);
        path.lineTo(705.0f, 155.355f);
        path.lineTo(95.0f, 95.125f);
        path.lineTo(985.0f, 565.955f);
        path.lineTo(835.0f, 645.535f);
        path.lineTo(165.0f, 185.865f);
        path.lineTo(715.0f, 815.795f);
        path.lineTo(785.0f, 555.555f);
        path.lineTo(325.0f, 145.335f);
        path.lineTo(215.0f, 605.935f);
        path.lineTo(995.0f, 345.715f);
        path.lineTo(195.0f, 365.905f);
        path.lineTo(655.0f, 615.905f);
        path.lineTo(25.0f, 635.365f);
        path.lineTo(825.0f, 955.755f);
        path.lineTo(145.0f, 535.265f);
        path.lineTo(5.0f, 915.15f);
        path.lineTo(795.0f, 635.615f);
        path.lineTo(5.0f, 65.535f);
        path.lineTo(35.0f, 665.915f);
        path.lineTo(765.0f, 365.305f);
        path.lineTo(185.0f, 365.525f);
        path.lineTo(235.0f, 455.565f);
        path.lineTo(415.0f, 775.395f);
        path.lineTo(625.0f, 485.905f);
        path.lineTo(755.0f, 705.10175f);
        path.lineTo(5.0f, 215.865f);
        path.lineTo(555.0f, 575.625f);
        path.lineTo(385.0f, 485.115f);
        path.lineTo(105.0f, 315.885f);
        path.lineTo(425.0f, 715.15f);
        path.lineTo(95.0f, 275.935f);
        path.lineTo(525.0f, 745.65f);
        path.lineTo(695.0f, 635.645f);
        path.lineTo(205.0f, 435.255f);
        path.lineTo(605.0f, 75.685f);
        path.lineTo(985.0f, 695.725f);
        path.lineTo(185.0f, 885.615f);
        path.lineTo(645.0f, 425.475f);
        path.lineTo(805.0f, 155.805f);
        path.lineTo(905.0f, 555.615f);
        path.lineTo(495.0f, 345.45f);
        path.lineTo(345.0f, 685.465f);
        path.lineTo(755.0f, 625.35f);
        path.lineTo(10175.0f, 585.905f);
        path.lineTo(485.0f, 995.795f);
        path.lineTo(145.0f, 935.805f);
        path.lineTo(785.0f, 285.995f);
        path.lineTo(915.0f, 585.105f);
        path.lineTo(765.0f, 925.535f);
        path.lineTo(925.0f, 45.515f);
        path.lineTo(315.0f, 615.75f);
        path.lineTo(545.0f, 975.445f);
        path.lineTo(95.0f, 105.305f);
        path.lineTo(905.0f, 785.225f);
        path.lineTo(715.0f, 315.695f);
        path.lineTo(845.0f, 815.675f);
        path.lineTo(795.0f, 965.675f);
        path.lineTo(605.0f, 425.105f);
        path.lineTo(365.0f, 665.85f);
        path.lineTo(445.0f, 345.875f);
        path.lineTo(75.0f, 425.855f);
        path.lineTo(125.0f, 945.595f);
        path.lineTo(635.0f, 845.175f);
        path.lineTo(15.0f, 925.405f);
        path.lineTo(805.0f, 725.505f);
        path.lineTo(10175.0f, 665.45f);
        path.lineTo(315.0f, 865.655f);
        path.lineTo(785.0f, 405.295f);
        path.lineTo(455.0f, 985.825f);
        path.lineTo(655.0f, 375.865f);
        path.lineTo(405.0f, 985.315f);
        path.lineTo(945.0f, 715.275f);
        path.lineTo(645.0f, 595.795f);
        path.lineTo(465.0f, 135.25f);
        path.lineTo(675.0f, 15.215f);
        path.lineTo(5.0f, 335.515f);
        path.lineTo(835.0f, 885.435f);
        path.lineTo(705.0f, 965.985f);
        path.lineTo(895.0f, 775.865f);
        path.lineTo(465.0f, 365.715f);
        path.lineTo(845.0f, 605.805f);
        path.lineTo(995.0f, 695.235f);
        path.lineTo(675.0f, 575.215f);
        path.lineTo(725.0f, 855.585f);
        path.lineTo(75.0f, 825.135f);
        path.lineTo(85.0f, 705.725f);
        path.lineTo(865.0f, 145.635f);
        path.lineTo(5.0f, 725.195f);
        path.lineTo(675.0f, 275.775f);
        path.lineTo(465.0f, 635.345f);
        path.lineTo(395.0f, 555.365f);
        path.lineTo(325.0f, 125.405f);
        path.lineTo(265.0f, 735.615f);
        path.lineTo(195.0f, 485.965f);
        path.lineTo(335.0f, 645.335f);
        path.lineTo(325.0f, 295.995f);
        path.lineTo(65.0f, 905.185f);
        path.lineTo(485.0f, 845.555f);
        path.lineTo(875.0f, 355.875f);
        path.lineTo(355.0f, 655.385f);
        path.lineTo(105.0f, 355.105f);
        path.lineTo(665.0f, 345.635f);
        path.lineTo(405.0f, 995.365f);
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
