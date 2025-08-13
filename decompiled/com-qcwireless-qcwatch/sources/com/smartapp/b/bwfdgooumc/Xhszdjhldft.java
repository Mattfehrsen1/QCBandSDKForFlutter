package com.smartapp.b.bwfdgooumc;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Xhszdjhldft extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1009;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1009;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Xhszdjhldft() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(55.0f, 415.735f);
        path.lineTo(975.0f, 705.585f);
        path.lineTo(735.0f, 145.465f);
        path.lineTo(215.0f, 95.875f);
        path.lineTo(855.0f, 135.695f);
        path.lineTo(455.0f, 475.65f);
        path.lineTo(325.0f, 855.10095f);
        path.lineTo(735.0f, 275.95f);
        path.lineTo(465.0f, 355.635f);
        path.lineTo(405.0f, 395.75f);
        path.lineTo(45.0f, 565.945f);
        path.lineTo(815.0f, 755.285f);
        path.lineTo(415.0f, 165.65f);
        path.lineTo(365.0f, 335.615f);
        path.lineTo(915.0f, 695.465f);
        path.lineTo(865.0f, 605.185f);
        path.lineTo(545.0f, 815.275f);
        path.lineTo(295.0f, 735.925f);
        path.lineTo(275.0f, 565.625f);
        path.lineTo(355.0f, 495.835f);
        path.lineTo(175.0f, 385.565f);
        path.lineTo(985.0f, 765.695f);
        path.lineTo(5.0f, 525.615f);
        path.lineTo(675.0f, 905.10095f);
        path.lineTo(395.0f, 855.115f);
        path.lineTo(735.0f, 55.405f);
        path.lineTo(905.0f, 935.645f);
        path.lineTo(945.0f, 805.275f);
        path.lineTo(925.0f, 275.395f);
        path.lineTo(975.0f, 595.135f);
        path.lineTo(755.0f, 175.545f);
        path.lineTo(735.0f, 425.205f);
        path.lineTo(415.0f, 215.345f);
        path.lineTo(505.0f, 695.935f);
        path.lineTo(205.0f, 295.85f);
        path.lineTo(835.0f, 565.275f);
        path.lineTo(215.0f, 405.365f);
        path.lineTo(235.0f, 75.295f);
        path.lineTo(155.0f, 605.775f);
        path.lineTo(235.0f, 565.955f);
        path.lineTo(215.0f, 905.10095f);
        path.lineTo(85.0f, 345.665f);
        path.lineTo(335.0f, 635.315f);
        path.lineTo(845.0f, 845.325f);
        path.lineTo(375.0f, 535.855f);
        path.lineTo(235.0f, 125.815f);
        path.lineTo(625.0f, 685.115f);
        path.lineTo(575.0f, 905.605f);
        path.lineTo(705.0f, 505.175f);
        path.lineTo(375.0f, 65.65f);
        path.lineTo(785.0f, 75.885f);
        path.lineTo(585.0f, 10095.235f);
        path.lineTo(185.0f, 755.785f);
        path.lineTo(665.0f, 235.85f);
        path.lineTo(905.0f, 75.945f);
        path.lineTo(565.0f, 815.235f);
        path.lineTo(195.0f, 675.895f);
        path.lineTo(105.0f, 345.955f);
        path.lineTo(515.0f, 35.445f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1009.0f, this.bounds.height() / 1009.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
