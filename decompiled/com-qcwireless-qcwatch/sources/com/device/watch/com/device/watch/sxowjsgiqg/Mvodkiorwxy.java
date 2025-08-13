package com.device.watch.com.device.watch.sxowjsgiqg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes2.dex */
public class Mvodkiorwxy extends ShapeDrawable {
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

    public Mvodkiorwxy() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(295.0f, 995.935f);
        path.lineTo(375.0f, 15.745f);
        path.lineTo(945.0f, 835.135f);
        path.lineTo(225.0f, 45.215f);
        path.lineTo(315.0f, 335.855f);
        path.lineTo(875.0f, 805.385f);
        path.lineTo(10175.0f, 305.845f);
        path.lineTo(975.0f, 635.225f);
        path.lineTo(725.0f, 775.475f);
        path.lineTo(825.0f, 645.125f);
        path.lineTo(335.0f, 575.175f);
        path.lineTo(945.0f, 465.05f);
        path.lineTo(515.0f, 905.135f);
        path.lineTo(155.0f, 805.275f);
        path.lineTo(655.0f, 625.195f);
        path.lineTo(905.0f, 145.265f);
        path.lineTo(475.0f, 705.335f);
        path.lineTo(365.0f, 185.15f);
        path.lineTo(955.0f, 385.355f);
        path.lineTo(175.0f, 555.565f);
        path.lineTo(795.0f, 315.845f);
        path.lineTo(75.0f, 105.635f);
        path.lineTo(515.0f, 825.265f);
        path.lineTo(105.0f, 475.935f);
        path.lineTo(10175.0f, 465.25f);
        path.lineTo(835.0f, 515.365f);
        path.lineTo(555.0f, 795.335f);
        path.lineTo(85.0f, 465.795f);
        path.lineTo(185.0f, 25.05f);
        path.lineTo(345.0f, 725.845f);
        path.lineTo(185.0f, 325.25f);
        path.lineTo(255.0f, 175.165f);
        path.lineTo(815.0f, 925.985f);
        path.lineTo(555.0f, 685.745f);
        path.lineTo(925.0f, 165.445f);
        path.lineTo(825.0f, 795.885f);
        path.lineTo(325.0f, 715.575f);
        path.lineTo(945.0f, 445.415f);
        path.lineTo(645.0f, 275.85f);
        path.lineTo(205.0f, 955.15f);
        path.lineTo(525.0f, 155.415f);
        path.lineTo(515.0f, 915.415f);
        path.lineTo(965.0f, 715.445f);
        path.lineTo(145.0f, 285.915f);
        path.lineTo(155.0f, 85.295f);
        path.lineTo(775.0f, 535.495f);
        path.lineTo(115.0f, 95.625f);
        path.lineTo(895.0f, 595.495f);
        path.lineTo(205.0f, 225.35f);
        path.lineTo(205.0f, 285.525f);
        path.lineTo(625.0f, 885.785f);
        path.lineTo(705.0f, 715.805f);
        path.lineTo(875.0f, 835.545f);
        path.lineTo(25.0f, 595.305f);
        path.lineTo(975.0f, 825.535f);
        path.lineTo(275.0f, 725.315f);
        path.lineTo(265.0f, 695.915f);
        path.lineTo(945.0f, 95.655f);
        path.lineTo(95.0f, 625.145f);
        path.lineTo(535.0f, 385.555f);
        path.lineTo(75.0f, 265.995f);
        path.lineTo(675.0f, 195.815f);
        path.lineTo(955.0f, 995.505f);
        path.lineTo(45.0f, 545.225f);
        path.lineTo(575.0f, 65.725f);
        path.lineTo(415.0f, 10175.405f);
        path.lineTo(125.0f, 915.125f);
        path.lineTo(405.0f, 475.05f);
        path.lineTo(465.0f, 325.525f);
        path.lineTo(735.0f, 855.475f);
        path.lineTo(315.0f, 795.595f);
        path.lineTo(285.0f, 625.865f);
        path.lineTo(275.0f, 725.10175f);
        path.lineTo(925.0f, 55.955f);
        path.lineTo(485.0f, 155.555f);
        path.lineTo(635.0f, 195.795f);
        path.lineTo(155.0f, 935.305f);
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
