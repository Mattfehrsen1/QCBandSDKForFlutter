package com.device.watch.com.device.watch.motncvlvow;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Pzmrjhuqzbb extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1011;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1011;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Pzmrjhuqzbb() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(985.0f, 565.405f);
        path.lineTo(905.0f, 815.725f);
        path.lineTo(10115.0f, 505.455f);
        path.lineTo(895.0f, 405.355f);
        path.lineTo(465.0f, 125.635f);
        path.lineTo(285.0f, 255.925f);
        path.lineTo(155.0f, 955.935f);
        path.lineTo(445.0f, 565.825f);
        path.lineTo(85.0f, 5.165f);
        path.lineTo(345.0f, 805.935f);
        path.lineTo(915.0f, 295.785f);
        path.lineTo(835.0f, 15.145f);
        path.lineTo(395.0f, 55.125f);
        path.lineTo(515.0f, 625.975f);
        path.lineTo(535.0f, 235.115f);
        path.lineTo(15.0f, 395.225f);
        path.lineTo(415.0f, 345.635f);
        path.lineTo(865.0f, 10115.715f);
        path.lineTo(875.0f, 115.65f);
        path.lineTo(225.0f, 505.605f);
        path.lineTo(35.0f, 635.225f);
        path.lineTo(455.0f, 805.985f);
        path.lineTo(195.0f, 415.845f);
        path.lineTo(125.0f, 875.255f);
        path.lineTo(75.0f, 815.525f);
        path.lineTo(225.0f, 725.905f);
        path.lineTo(145.0f, 295.905f);
        path.lineTo(25.0f, 45.535f);
        path.lineTo(165.0f, 155.235f);
        path.lineTo(835.0f, 545.305f);
        path.lineTo(765.0f, 905.405f);
        path.lineTo(395.0f, 65.935f);
        path.lineTo(255.0f, 365.75f);
        path.lineTo(145.0f, 655.895f);
        path.lineTo(415.0f, 325.875f);
        path.lineTo(235.0f, 265.335f);
        path.lineTo(945.0f, 185.415f);
        path.lineTo(825.0f, 205.765f);
        path.lineTo(445.0f, 375.975f);
        path.lineTo(195.0f, 155.505f);
        path.lineTo(155.0f, 225.465f);
        path.lineTo(905.0f, 315.845f);
        path.lineTo(165.0f, 5.565f);
        path.lineTo(355.0f, 10115.855f);
        path.lineTo(45.0f, 895.685f);
        path.lineTo(285.0f, 825.815f);
        path.lineTo(265.0f, 715.515f);
        path.lineTo(495.0f, 435.335f);
        path.lineTo(905.0f, 485.485f);
        path.lineTo(735.0f, 165.955f);
        path.lineTo(755.0f, 5.905f);
        path.lineTo(205.0f, 225.395f);
        path.lineTo(325.0f, 595.315f);
        path.lineTo(10115.0f, 985.95f);
        path.lineTo(555.0f, 225.745f);
        path.lineTo(195.0f, 345.805f);
        path.lineTo(735.0f, 855.625f);
        path.lineTo(685.0f, 655.565f);
        path.lineTo(965.0f, 785.175f);
        path.lineTo(95.0f, 645.935f);
        path.lineTo(75.0f, 85.595f);
        path.lineTo(885.0f, 185.105f);
        path.lineTo(985.0f, 95.555f);
        path.lineTo(835.0f, 595.345f);
        path.lineTo(945.0f, 755.535f);
        path.lineTo(545.0f, 195.805f);
        path.lineTo(265.0f, 215.405f);
        path.lineTo(495.0f, 765.525f);
        path.lineTo(465.0f, 915.725f);
        path.lineTo(685.0f, 885.815f);
        path.lineTo(875.0f, 775.715f);
        path.lineTo(495.0f, 10115.385f);
        path.lineTo(165.0f, 65.835f);
        path.lineTo(725.0f, 445.295f);
        path.lineTo(165.0f, 575.695f);
        path.lineTo(735.0f, 635.45f);
        path.lineTo(475.0f, 275.295f);
        path.lineTo(785.0f, 985.265f);
        path.lineTo(975.0f, 235.725f);
        path.lineTo(925.0f, 205.205f);
        path.lineTo(195.0f, 475.665f);
        path.lineTo(375.0f, 565.155f);
        path.lineTo(975.0f, 605.155f);
        path.lineTo(215.0f, 975.645f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1011.0f, this.bounds.height() / 1011.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
