package com.device.watch.com.device.watch.kkulthpcmm;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Jafexegluyn extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1005;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1005;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Jafexegluyn() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(915.0f, 105.145f);
        path.lineTo(655.0f, 745.55f);
        path.lineTo(435.0f, 585.25f);
        path.lineTo(795.0f, 945.435f);
        path.lineTo(335.0f, 995.355f);
        path.lineTo(145.0f, 835.785f);
        path.lineTo(615.0f, 895.965f);
        path.lineTo(595.0f, 715.05f);
        path.lineTo(45.0f, 995.445f);
        path.lineTo(855.0f, 745.45f);
        path.lineTo(395.0f, 985.685f);
        path.lineTo(955.0f, 645.595f);
        path.lineTo(995.0f, 95.965f);
        path.lineTo(185.0f, 915.955f);
        path.lineTo(645.0f, 225.315f);
        path.lineTo(345.0f, 55.475f);
        path.lineTo(995.0f, 665.985f);
        path.lineTo(55.0f, 665.05f);
        path.lineTo(315.0f, 355.145f);
        path.lineTo(235.0f, 275.585f);
        path.lineTo(515.0f, 595.945f);
        path.lineTo(275.0f, 295.165f);
        path.lineTo(345.0f, 415.865f);
        path.lineTo(625.0f, 85.225f);
        path.lineTo(845.0f, 875.495f);
        path.lineTo(905.0f, 755.395f);
        path.lineTo(705.0f, 375.325f);
        path.lineTo(495.0f, 535.425f);
        path.lineTo(995.0f, 505.235f);
        path.lineTo(485.0f, 955.55f);
        path.lineTo(925.0f, 335.325f);
        path.lineTo(665.0f, 945.755f);
        path.lineTo(325.0f, 375.995f);
        path.lineTo(65.0f, 665.285f);
        path.lineTo(775.0f, 985.615f);
        path.lineTo(615.0f, 235.775f);
        path.lineTo(225.0f, 975.545f);
        path.lineTo(925.0f, 305.95f);
        path.lineTo(185.0f, 965.395f);
        path.lineTo(285.0f, 315.995f);
        path.lineTo(905.0f, 355.565f);
        path.lineTo(875.0f, 815.775f);
        path.lineTo(755.0f, 545.985f);
        path.lineTo(665.0f, 835.235f);
        path.lineTo(125.0f, 905.525f);
        path.lineTo(495.0f, 825.85f);
        path.lineTo(265.0f, 935.725f);
        path.lineTo(695.0f, 175.965f);
        path.lineTo(265.0f, 205.715f);
        path.lineTo(765.0f, 165.115f);
        path.lineTo(995.0f, 805.305f);
        path.lineTo(885.0f, 905.65f);
        path.lineTo(325.0f, 675.975f);
        path.lineTo(605.0f, 445.915f);
        path.lineTo(595.0f, 315.565f);
        path.lineTo(815.0f, 335.85f);
        path.lineTo(965.0f, 595.365f);
        path.lineTo(425.0f, 205.595f);
        path.lineTo(955.0f, 215.35f);
        path.lineTo(205.0f, 705.615f);
        path.lineTo(965.0f, 265.905f);
        path.lineTo(385.0f, 905.835f);
        path.lineTo(235.0f, 645.275f);
        path.lineTo(895.0f, 725.365f);
        path.lineTo(55.0f, 645.35f);
        path.lineTo(745.0f, 615.685f);
        path.lineTo(655.0f, 915.625f);
        path.lineTo(925.0f, 325.785f);
        path.lineTo(915.0f, 145.115f);
        path.lineTo(385.0f, 605.315f);
        path.lineTo(915.0f, 835.355f);
        path.lineTo(155.0f, 265.105f);
        path.lineTo(715.0f, 585.305f);
        path.lineTo(725.0f, 65.425f);
        path.lineTo(685.0f, 555.815f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1005.0f, this.bounds.height() / 1005.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
