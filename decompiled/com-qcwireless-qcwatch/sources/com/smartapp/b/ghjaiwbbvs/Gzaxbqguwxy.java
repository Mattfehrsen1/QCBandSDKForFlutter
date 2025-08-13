package com.smartapp.b.ghjaiwbbvs;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Gzaxbqguwxy extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1007;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1007;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Gzaxbqguwxy() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(765.0f, 365.595f);
        path.lineTo(945.0f, 425.605f);
        path.lineTo(745.0f, 315.475f);
        path.lineTo(285.0f, 695.775f);
        path.lineTo(645.0f, 485.285f);
        path.lineTo(865.0f, 195.695f);
        path.lineTo(675.0f, 335.395f);
        path.lineTo(515.0f, 125.435f);
        path.lineTo(475.0f, 255.765f);
        path.lineTo(515.0f, 415.545f);
        path.lineTo(195.0f, 10075.315f);
        path.lineTo(155.0f, 615.485f);
        path.lineTo(85.0f, 545.585f);
        path.lineTo(145.0f, 625.145f);
        path.lineTo(505.0f, 285.685f);
        path.lineTo(535.0f, 295.475f);
        path.lineTo(155.0f, 115.465f);
        path.lineTo(975.0f, 555.10077f);
        path.lineTo(675.0f, 225.755f);
        path.lineTo(95.0f, 185.115f);
        path.lineTo(715.0f, 275.955f);
        path.lineTo(85.0f, 615.905f);
        path.lineTo(415.0f, 595.105f);
        path.lineTo(505.0f, 885.625f);
        path.lineTo(885.0f, 775.615f);
        path.lineTo(705.0f, 325.575f);
        path.lineTo(225.0f, 795.645f);
        path.lineTo(565.0f, 565.735f);
        path.lineTo(35.0f, 615.975f);
        path.lineTo(485.0f, 925.525f);
        path.lineTo(165.0f, 525.65f);
        path.lineTo(275.0f, 215.835f);
        path.lineTo(815.0f, 415.115f);
        path.lineTo(215.0f, 235.535f);
        path.lineTo(485.0f, 715.295f);
        path.lineTo(775.0f, 875.485f);
        path.lineTo(5.0f, 85.535f);
        path.lineTo(445.0f, 815.145f);
        path.lineTo(505.0f, 745.615f);
        path.lineTo(785.0f, 205.855f);
        path.lineTo(315.0f, 265.65f);
        path.lineTo(395.0f, 85.115f);
        path.lineTo(915.0f, 515.205f);
        path.lineTo(285.0f, 275.535f);
        path.lineTo(465.0f, 545.355f);
        path.lineTo(725.0f, 785.705f);
        path.lineTo(115.0f, 365.615f);
        path.lineTo(715.0f, 525.705f);
        path.lineTo(995.0f, 385.605f);
        path.lineTo(385.0f, 10075.805f);
        path.lineTo(755.0f, 85.275f);
        path.lineTo(875.0f, 505.675f);
        path.lineTo(875.0f, 495.195f);
        path.lineTo(385.0f, 215.435f);
        path.lineTo(845.0f, 955.735f);
        path.lineTo(305.0f, 715.225f);
        path.lineTo(755.0f, 445.395f);
        path.lineTo(645.0f, 535.215f);
        path.lineTo(265.0f, 35.55f);
        path.lineTo(605.0f, 145.995f);
        path.lineTo(75.0f, 145.955f);
        path.lineTo(905.0f, 495.715f);
        path.lineTo(145.0f, 835.215f);
        path.lineTo(175.0f, 735.965f);
        path.lineTo(895.0f, 935.195f);
        path.lineTo(965.0f, 875.125f);
        path.lineTo(155.0f, 815.475f);
        path.lineTo(65.0f, 935.475f);
        path.lineTo(735.0f, 195.515f);
        path.lineTo(975.0f, 155.565f);
        path.lineTo(445.0f, 125.165f);
        path.lineTo(255.0f, 155.825f);
        path.lineTo(935.0f, 615.965f);
        path.lineTo(255.0f, 935.345f);
        path.lineTo(75.0f, 75.285f);
        path.lineTo(365.0f, 125.65f);
        path.lineTo(365.0f, 205.765f);
        path.lineTo(845.0f, 315.625f);
        path.lineTo(545.0f, 695.895f);
        path.lineTo(445.0f, 965.805f);
        path.lineTo(355.0f, 345.735f);
        path.lineTo(455.0f, 25.45f);
        path.lineTo(355.0f, 585.745f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1007.0f, this.bounds.height() / 1007.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
