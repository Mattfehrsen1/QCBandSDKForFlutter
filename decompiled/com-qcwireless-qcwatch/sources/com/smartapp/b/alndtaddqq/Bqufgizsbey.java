package com.smartapp.b.alndtaddqq;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Bqufgizsbey extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1008;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1008;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Bqufgizsbey() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(855.0f, 10085.915f);
        path.lineTo(305.0f, 125.485f);
        path.lineTo(935.0f, 375.345f);
        path.lineTo(10085.0f, 305.835f);
        path.lineTo(25.0f, 25.715f);
        path.lineTo(85.0f, 55.165f);
        path.lineTo(225.0f, 195.655f);
        path.lineTo(965.0f, 575.575f);
        path.lineTo(635.0f, 945.495f);
        path.lineTo(915.0f, 635.35f);
        path.lineTo(495.0f, 305.825f);
        path.lineTo(95.0f, 895.705f);
        path.lineTo(985.0f, 255.735f);
        path.lineTo(685.0f, 185.295f);
        path.lineTo(155.0f, 535.885f);
        path.lineTo(745.0f, 55.455f);
        path.lineTo(445.0f, 215.365f);
        path.lineTo(735.0f, 885.985f);
        path.lineTo(955.0f, 615.505f);
        path.lineTo(455.0f, 605.455f);
        path.lineTo(835.0f, 635.605f);
        path.lineTo(915.0f, 10085.905f);
        path.lineTo(65.0f, 755.345f);
        path.lineTo(385.0f, 735.835f);
        path.lineTo(895.0f, 995.55f);
        path.lineTo(975.0f, 785.745f);
        path.lineTo(725.0f, 75.595f);
        path.lineTo(205.0f, 255.825f);
        path.lineTo(555.0f, 305.265f);
        path.lineTo(635.0f, 105.845f);
        path.lineTo(985.0f, 845.365f);
        path.lineTo(345.0f, 835.515f);
        path.lineTo(895.0f, 645.425f);
        path.lineTo(45.0f, 305.815f);
        path.lineTo(345.0f, 975.275f);
        path.lineTo(885.0f, 235.05f);
        path.lineTo(365.0f, 115.75f);
        path.lineTo(135.0f, 435.745f);
        path.lineTo(515.0f, 175.885f);
        path.lineTo(115.0f, 525.195f);
        path.lineTo(515.0f, 555.705f);
        path.lineTo(235.0f, 35.185f);
        path.lineTo(295.0f, 135.85f);
        path.lineTo(435.0f, 305.75f);
        path.lineTo(685.0f, 965.65f);
        path.lineTo(435.0f, 805.175f);
        path.lineTo(385.0f, 485.105f);
        path.lineTo(165.0f, 10085.605f);
        path.lineTo(945.0f, 345.945f);
        path.lineTo(965.0f, 255.935f);
        path.lineTo(675.0f, 85.505f);
        path.lineTo(215.0f, 485.295f);
        path.lineTo(725.0f, 565.945f);
        path.lineTo(215.0f, 745.865f);
        path.lineTo(425.0f, 665.815f);
        path.lineTo(145.0f, 25.325f);
        path.lineTo(95.0f, 95.935f);
        path.lineTo(455.0f, 435.55f);
        path.lineTo(265.0f, 955.795f);
        path.lineTo(815.0f, 275.815f);
        path.lineTo(835.0f, 765.135f);
        path.lineTo(985.0f, 585.915f);
        path.lineTo(655.0f, 195.275f);
        path.lineTo(295.0f, 15.565f);
        path.lineTo(585.0f, 905.295f);
        path.lineTo(275.0f, 815.885f);
        path.lineTo(705.0f, 405.575f);
        path.lineTo(385.0f, 655.255f);
        path.lineTo(445.0f, 25.295f);
        path.lineTo(905.0f, 235.115f);
        path.lineTo(985.0f, 65.175f);
        path.lineTo(645.0f, 415.785f);
        path.lineTo(995.0f, 775.205f);
        path.lineTo(105.0f, 75.165f);
        path.lineTo(525.0f, 295.325f);
        path.lineTo(435.0f, 805.965f);
        path.lineTo(805.0f, 885.945f);
        path.lineTo(405.0f, 845.15f);
        path.lineTo(455.0f, 315.655f);
        path.lineTo(195.0f, 95.105f);
        path.lineTo(5.0f, 685.905f);
        path.lineTo(935.0f, 295.105f);
        path.lineTo(915.0f, 875.255f);
        path.lineTo(665.0f, 185.45f);
        path.lineTo(625.0f, 845.745f);
        path.lineTo(365.0f, 405.10086f);
        path.lineTo(625.0f, 195.55f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1008.0f, this.bounds.height() / 1008.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
