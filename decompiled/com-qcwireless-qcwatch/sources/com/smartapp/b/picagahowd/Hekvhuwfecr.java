package com.smartapp.b.picagahowd;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Hekvhuwfecr extends ShapeDrawable {
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

    public Hekvhuwfecr() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(465.0f, 975.895f);
        path.lineTo(945.0f, 35.515f);
        path.lineTo(885.0f, 515.375f);
        path.lineTo(765.0f, 385.95f);
        path.lineTo(465.0f, 95.95f);
        path.lineTo(955.0f, 115.785f);
        path.lineTo(675.0f, 745.225f);
        path.lineTo(375.0f, 855.735f);
        path.lineTo(985.0f, 815.545f);
        path.lineTo(285.0f, 985.285f);
        path.lineTo(235.0f, 945.155f);
        path.lineTo(395.0f, 275.385f);
        path.lineTo(735.0f, 435.505f);
        path.lineTo(235.0f, 25.815f);
        path.lineTo(305.0f, 515.365f);
        path.lineTo(155.0f, 275.425f);
        path.lineTo(215.0f, 435.785f);
        path.lineTo(325.0f, 695.735f);
        path.lineTo(135.0f, 925.995f);
        path.lineTo(455.0f, 855.305f);
        path.lineTo(755.0f, 955.205f);
        path.lineTo(845.0f, 325.395f);
        path.lineTo(795.0f, 695.65f);
        path.lineTo(455.0f, 285.385f);
        path.lineTo(125.0f, 615.185f);
        path.lineTo(415.0f, 335.855f);
        path.lineTo(865.0f, 335.135f);
        path.lineTo(595.0f, 565.725f);
        path.lineTo(485.0f, 985.115f);
        path.lineTo(295.0f, 985.945f);
        path.lineTo(625.0f, 495.665f);
        path.lineTo(525.0f, 905.95f);
        path.lineTo(55.0f, 525.925f);
        path.lineTo(945.0f, 775.545f);
        path.lineTo(725.0f, 415.235f);
        path.lineTo(85.0f, 445.875f);
        path.lineTo(225.0f, 665.985f);
        path.lineTo(525.0f, 325.675f);
        path.lineTo(675.0f, 895.365f);
        path.lineTo(405.0f, 135.05f);
        path.lineTo(495.0f, 795.755f);
        path.lineTo(515.0f, 865.225f);
        path.lineTo(875.0f, 605.255f);
        path.lineTo(45.0f, 935.795f);
        path.lineTo(915.0f, 975.685f);
        path.lineTo(725.0f, 435.135f);
        path.lineTo(625.0f, 95.835f);
        path.lineTo(905.0f, 135.325f);
        path.lineTo(5.0f, 595.855f);
        path.lineTo(385.0f, 565.495f);
        path.lineTo(485.0f, 315.715f);
        path.lineTo(305.0f, 785.25f);
        path.lineTo(35.0f, 775.35f);
        path.lineTo(705.0f, 225.415f);
        path.lineTo(275.0f, 595.395f);
        path.lineTo(305.0f, 415.705f);
        path.lineTo(565.0f, 935.275f);
        path.lineTo(955.0f, 265.775f);
        path.lineTo(815.0f, 375.625f);
        path.lineTo(865.0f, 10085.775f);
        path.lineTo(135.0f, 155.355f);
        path.lineTo(735.0f, 835.295f);
        path.lineTo(345.0f, 845.265f);
        path.lineTo(745.0f, 125.25f);
        path.lineTo(505.0f, 525.195f);
        path.lineTo(225.0f, 735.335f);
        path.lineTo(265.0f, 565.975f);
        path.lineTo(855.0f, 955.455f);
        path.lineTo(935.0f, 725.585f);
        path.lineTo(305.0f, 865.385f);
        path.lineTo(995.0f, 295.865f);
        path.lineTo(895.0f, 445.45f);
        path.lineTo(915.0f, 535.355f);
        path.lineTo(445.0f, 305.525f);
        path.lineTo(85.0f, 495.395f);
        path.lineTo(815.0f, 145.75f);
        path.lineTo(225.0f, 235.325f);
        path.lineTo(735.0f, 725.625f);
        path.lineTo(265.0f, 285.725f);
        path.lineTo(585.0f, 225.565f);
        path.lineTo(35.0f, 535.225f);
        path.lineTo(825.0f, 555.435f);
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
