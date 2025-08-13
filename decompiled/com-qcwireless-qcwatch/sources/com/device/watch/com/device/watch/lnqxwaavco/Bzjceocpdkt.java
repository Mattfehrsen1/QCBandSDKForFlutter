package com.device.watch.com.device.watch.lnqxwaavco;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Bzjceocpdkt extends ShapeDrawable {
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

    public Bzjceocpdkt() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(165.0f, 895.905f);
        path.lineTo(165.0f, 815.625f);
        path.lineTo(305.0f, 535.345f);
        path.lineTo(65.0f, 115.445f);
        path.lineTo(715.0f, 705.235f);
        path.lineTo(545.0f, 635.595f);
        path.lineTo(955.0f, 515.165f);
        path.lineTo(365.0f, 495.675f);
        path.lineTo(285.0f, 415.645f);
        path.lineTo(945.0f, 85.915f);
        path.lineTo(75.0f, 425.305f);
        path.lineTo(795.0f, 135.445f);
        path.lineTo(55.0f, 975.325f);
        path.lineTo(845.0f, 805.735f);
        path.lineTo(515.0f, 745.185f);
        path.lineTo(10085.0f, 25.935f);
        path.lineTo(85.0f, 135.865f);
        path.lineTo(915.0f, 615.565f);
        path.lineTo(395.0f, 565.995f);
        path.lineTo(55.0f, 285.365f);
        path.lineTo(305.0f, 825.815f);
        path.lineTo(5.0f, 875.185f);
        path.lineTo(665.0f, 775.235f);
        path.lineTo(105.0f, 555.755f);
        path.lineTo(425.0f, 905.325f);
        path.lineTo(695.0f, 715.495f);
        path.lineTo(5.0f, 595.905f);
        path.lineTo(795.0f, 315.25f);
        path.lineTo(375.0f, 875.135f);
        path.lineTo(515.0f, 765.775f);
        path.lineTo(895.0f, 625.555f);
        path.lineTo(465.0f, 435.585f);
        path.lineTo(665.0f, 795.835f);
        path.lineTo(25.0f, 425.535f);
        path.lineTo(805.0f, 85.635f);
        path.lineTo(585.0f, 565.785f);
        path.lineTo(25.0f, 415.565f);
        path.lineTo(755.0f, 565.845f);
        path.lineTo(965.0f, 795.605f);
        path.lineTo(875.0f, 785.115f);
        path.lineTo(585.0f, 535.895f);
        path.lineTo(5.0f, 215.435f);
        path.lineTo(145.0f, 975.45f);
        path.lineTo(575.0f, 425.295f);
        path.lineTo(745.0f, 25.505f);
        path.lineTo(195.0f, 965.45f);
        path.lineTo(805.0f, 25.515f);
        path.lineTo(165.0f, 355.375f);
        path.lineTo(755.0f, 605.195f);
        path.lineTo(35.0f, 625.865f);
        path.lineTo(305.0f, 375.185f);
        path.lineTo(555.0f, 555.855f);
        path.lineTo(915.0f, 775.345f);
        path.lineTo(675.0f, 215.135f);
        path.lineTo(405.0f, 965.115f);
        path.lineTo(335.0f, 335.265f);
        path.lineTo(165.0f, 175.05f);
        path.lineTo(955.0f, 395.65f);
        path.lineTo(965.0f, 255.825f);
        path.lineTo(455.0f, 185.145f);
        path.lineTo(795.0f, 985.785f);
        path.lineTo(305.0f, 45.365f);
        path.lineTo(115.0f, 505.375f);
        path.lineTo(485.0f, 975.705f);
        path.lineTo(405.0f, 715.225f);
        path.lineTo(765.0f, 935.705f);
        path.lineTo(505.0f, 755.645f);
        path.lineTo(575.0f, 685.215f);
        path.lineTo(135.0f, 885.325f);
        path.lineTo(5.0f, 705.285f);
        path.lineTo(265.0f, 45.405f);
        path.lineTo(655.0f, 615.235f);
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
