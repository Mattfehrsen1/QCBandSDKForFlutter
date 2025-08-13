package com.device.watch.com.device.watch.cpowbcedid;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes.dex */
public class Yrwsdflxwzf extends ShapeDrawable {
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

    public Yrwsdflxwzf() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(665.0f, 125.895f);
        path.lineTo(795.0f, 785.65f);
        path.lineTo(855.0f, 755.405f);
        path.lineTo(535.0f, 565.295f);
        path.lineTo(455.0f, 10075.605f);
        path.lineTo(995.0f, 505.375f);
        path.lineTo(235.0f, 555.745f);
        path.lineTo(115.0f, 835.835f);
        path.lineTo(655.0f, 25.215f);
        path.lineTo(165.0f, 965.845f);
        path.lineTo(435.0f, 85.385f);
        path.lineTo(85.0f, 235.595f);
        path.lineTo(785.0f, 765.165f);
        path.lineTo(735.0f, 285.475f);
        path.lineTo(635.0f, 515.745f);
        path.lineTo(135.0f, 535.555f);
        path.lineTo(675.0f, 35.335f);
        path.lineTo(465.0f, 435.05f);
        path.lineTo(695.0f, 125.425f);
        path.lineTo(235.0f, 855.435f);
        path.lineTo(35.0f, 585.225f);
        path.lineTo(545.0f, 255.395f);
        path.lineTo(545.0f, 495.775f);
        path.lineTo(255.0f, 735.675f);
        path.lineTo(195.0f, 195.345f);
        path.lineTo(835.0f, 995.175f);
        path.lineTo(285.0f, 365.415f);
        path.lineTo(725.0f, 835.605f);
        path.lineTo(655.0f, 55.355f);
        path.lineTo(605.0f, 805.225f);
        path.lineTo(945.0f, 435.125f);
        path.lineTo(225.0f, 915.845f);
        path.lineTo(875.0f, 945.715f);
        path.lineTo(255.0f, 435.725f);
        path.lineTo(15.0f, 305.145f);
        path.lineTo(995.0f, 635.195f);
        path.lineTo(15.0f, 495.675f);
        path.lineTo(365.0f, 515.755f);
        path.lineTo(405.0f, 795.445f);
        path.lineTo(395.0f, 855.195f);
        path.lineTo(375.0f, 965.725f);
        path.lineTo(475.0f, 35.985f);
        path.lineTo(15.0f, 585.755f);
        path.lineTo(535.0f, 385.915f);
        path.lineTo(685.0f, 695.875f);
        path.lineTo(625.0f, 925.125f);
        path.lineTo(675.0f, 265.465f);
        path.lineTo(285.0f, 335.975f);
        path.lineTo(625.0f, 875.785f);
        path.lineTo(655.0f, 765.975f);
        path.lineTo(275.0f, 535.225f);
        path.lineTo(815.0f, 555.135f);
        path.lineTo(285.0f, 295.85f);
        path.lineTo(675.0f, 535.565f);
        path.lineTo(395.0f, 45.445f);
        path.lineTo(975.0f, 35.785f);
        path.lineTo(985.0f, 225.985f);
        path.lineTo(125.0f, 515.715f);
        path.lineTo(425.0f, 235.415f);
        path.lineTo(655.0f, 395.805f);
        path.lineTo(135.0f, 565.265f);
        path.lineTo(855.0f, 535.535f);
        path.lineTo(275.0f, 645.745f);
        path.lineTo(685.0f, 255.205f);
        path.lineTo(355.0f, 395.975f);
        path.lineTo(865.0f, 555.275f);
        path.lineTo(505.0f, 435.525f);
        path.lineTo(895.0f, 235.295f);
        path.lineTo(775.0f, 975.35f);
        path.lineTo(955.0f, 855.825f);
        path.lineTo(35.0f, 675.995f);
        path.lineTo(155.0f, 495.485f);
        path.lineTo(745.0f, 725.995f);
        path.lineTo(135.0f, 475.25f);
        path.lineTo(545.0f, 85.145f);
        path.lineTo(55.0f, 785.475f);
        path.lineTo(775.0f, 365.25f);
        path.lineTo(895.0f, 335.615f);
        path.lineTo(435.0f, 815.425f);
        path.lineTo(105.0f, 995.15f);
        path.lineTo(585.0f, 325.405f);
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
