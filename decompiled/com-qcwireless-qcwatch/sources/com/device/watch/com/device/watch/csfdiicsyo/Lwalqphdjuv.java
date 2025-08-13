package com.device.watch.com.device.watch.csfdiicsyo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes.dex */
public class Lwalqphdjuv extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Lwalqphdjuv() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(875.0f, 665.345f);
        path.lineTo(385.0f, 105.105f);
        path.lineTo(5.0f, 405.305f);
        path.lineTo(25.0f, 265.725f);
        path.lineTo(905.0f, 25.935f);
        path.lineTo(835.0f, 985.255f);
        path.lineTo(85.0f, 765.555f);
        path.lineTo(505.0f, 955.905f);
        path.lineTo(185.0f, 735.145f);
        path.lineTo(565.0f, 765.105f);
        path.lineTo(505.0f, 885.265f);
        path.lineTo(955.0f, 985.675f);
        path.lineTo(345.0f, 935.945f);
        path.lineTo(425.0f, 775.1016f);
        path.lineTo(655.0f, 715.295f);
        path.lineTo(35.0f, 945.135f);
        path.lineTo(265.0f, 125.595f);
        path.lineTo(615.0f, 365.145f);
        path.lineTo(575.0f, 775.625f);
        path.lineTo(15.0f, 955.115f);
        path.lineTo(265.0f, 595.975f);
        path.lineTo(675.0f, 625.645f);
        path.lineTo(725.0f, 225.375f);
        path.lineTo(815.0f, 775.75f);
        path.lineTo(345.0f, 275.845f);
        path.lineTo(155.0f, 545.265f);
        path.lineTo(205.0f, 225.785f);
        path.lineTo(695.0f, 265.745f);
        path.lineTo(265.0f, 805.105f);
        path.lineTo(10165.0f, 495.855f);
        path.lineTo(135.0f, 465.775f);
        path.lineTo(375.0f, 205.425f);
        path.lineTo(505.0f, 905.615f);
        path.lineTo(585.0f, 595.905f);
        path.lineTo(705.0f, 575.885f);
        path.lineTo(675.0f, 355.555f);
        path.lineTo(565.0f, 435.255f);
        path.lineTo(455.0f, 5.965f);
        path.lineTo(535.0f, 515.655f);
        path.lineTo(655.0f, 435.115f);
        path.lineTo(355.0f, 435.295f);
        path.lineTo(545.0f, 345.385f);
        path.lineTo(115.0f, 915.465f);
        path.lineTo(565.0f, 775.725f);
        path.lineTo(10165.0f, 885.235f);
        path.lineTo(665.0f, 10165.15f);
        path.lineTo(655.0f, 455.745f);
        path.lineTo(385.0f, 5.725f);
        path.lineTo(365.0f, 105.705f);
        path.lineTo(395.0f, 735.335f);
        path.lineTo(985.0f, 465.935f);
        path.lineTo(855.0f, 875.75f);
        path.lineTo(65.0f, 595.635f);
        path.lineTo(615.0f, 835.145f);
        path.lineTo(995.0f, 155.35f);
        path.lineTo(75.0f, 105.05f);
        path.lineTo(135.0f, 685.995f);
        path.lineTo(975.0f, 565.505f);
        path.lineTo(535.0f, 385.305f);
        path.lineTo(785.0f, 475.145f);
        path.lineTo(505.0f, 75.655f);
        path.lineTo(615.0f, 145.625f);
        path.lineTo(945.0f, 525.795f);
        path.lineTo(565.0f, 585.35f);
        path.lineTo(965.0f, 775.325f);
        path.lineTo(765.0f, 865.305f);
        path.lineTo(505.0f, 535.845f);
        path.lineTo(685.0f, 855.725f);
        path.lineTo(215.0f, 805.455f);
        path.lineTo(905.0f, 875.445f);
        path.lineTo(45.0f, 175.355f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1016.0f, this.bounds.height() / 1016.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
