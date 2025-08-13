package com.device.watch.com.device.watch.sanvogidiy;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Lmhzsssnvij extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1004;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1004;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Lmhzsssnvij() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(305.0f, 555.65f);
        path.lineTo(515.0f, 985.785f);
        path.lineTo(305.0f, 275.775f);
        path.lineTo(355.0f, 545.255f);
        path.lineTo(105.0f, 935.35f);
        path.lineTo(545.0f, 975.695f);
        path.lineTo(325.0f, 65.335f);
        path.lineTo(505.0f, 105.55f);
        path.lineTo(585.0f, 115.645f);
        path.lineTo(995.0f, 115.895f);
        path.lineTo(295.0f, 235.05f);
        path.lineTo(335.0f, 875.615f);
        path.lineTo(235.0f, 85.95f);
        path.lineTo(605.0f, 185.65f);
        path.lineTo(615.0f, 565.95f);
        path.lineTo(455.0f, 605.935f);
        path.lineTo(915.0f, 875.675f);
        path.lineTo(615.0f, 925.715f);
        path.lineTo(535.0f, 635.135f);
        path.lineTo(555.0f, 795.395f);
        path.lineTo(725.0f, 45.405f);
        path.lineTo(965.0f, 745.125f);
        path.lineTo(175.0f, 345.185f);
        path.lineTo(985.0f, 45.345f);
        path.lineTo(885.0f, 925.665f);
        path.lineTo(395.0f, 205.485f);
        path.lineTo(105.0f, 535.865f);
        path.lineTo(5.0f, 995.525f);
        path.lineTo(725.0f, 255.615f);
        path.lineTo(125.0f, 315.685f);
        path.lineTo(45.0f, 705.705f);
        path.lineTo(815.0f, 345.115f);
        path.lineTo(875.0f, 685.145f);
        path.lineTo(935.0f, 525.295f);
        path.lineTo(365.0f, 105.595f);
        path.lineTo(135.0f, 105.625f);
        path.lineTo(75.0f, 275.545f);
        path.lineTo(95.0f, 955.335f);
        path.lineTo(145.0f, 535.255f);
        path.lineTo(275.0f, 55.375f);
        path.lineTo(925.0f, 25.535f);
        path.lineTo(795.0f, 825.995f);
        path.lineTo(855.0f, 335.95f);
        path.lineTo(235.0f, 695.805f);
        path.lineTo(125.0f, 475.445f);
        path.lineTo(425.0f, 435.495f);
        path.lineTo(825.0f, 455.335f);
        path.lineTo(215.0f, 525.935f);
        path.lineTo(35.0f, 225.745f);
        path.lineTo(595.0f, 375.855f);
        path.lineTo(115.0f, 655.635f);
        path.lineTo(415.0f, 565.375f);
        path.lineTo(995.0f, 305.795f);
        path.lineTo(375.0f, 875.115f);
        path.lineTo(945.0f, 185.835f);
        path.lineTo(905.0f, 455.745f);
        path.lineTo(125.0f, 955.495f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1004.0f, this.bounds.height() / 1004.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
