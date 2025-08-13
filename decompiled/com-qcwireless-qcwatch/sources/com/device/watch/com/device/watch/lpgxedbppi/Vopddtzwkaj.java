package com.device.watch.com.device.watch.lpgxedbppi;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes2.dex */
public class Vopddtzwkaj extends ShapeDrawable {
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

    public Vopddtzwkaj() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(985.0f, 935.665f);
        path.lineTo(545.0f, 455.375f);
        path.lineTo(495.0f, 615.645f);
        path.lineTo(355.0f, 615.985f);
        path.lineTo(295.0f, 275.985f);
        path.lineTo(445.0f, 935.905f);
        path.lineTo(95.0f, 365.925f);
        path.lineTo(265.0f, 25.45f);
        path.lineTo(475.0f, 335.855f);
        path.lineTo(865.0f, 875.905f);
        path.lineTo(725.0f, 795.505f);
        path.lineTo(85.0f, 605.185f);
        path.lineTo(525.0f, 595.215f);
        path.lineTo(115.0f, 125.455f);
        path.lineTo(175.0f, 505.725f);
        path.lineTo(545.0f, 375.375f);
        path.lineTo(885.0f, 165.155f);
        path.lineTo(475.0f, 495.985f);
        path.lineTo(45.0f, 345.395f);
        path.lineTo(285.0f, 65.525f);
        path.lineTo(135.0f, 285.145f);
        path.lineTo(465.0f, 495.725f);
        path.lineTo(395.0f, 265.335f);
        path.lineTo(655.0f, 195.715f);
        path.lineTo(85.0f, 935.635f);
        path.lineTo(515.0f, 835.855f);
        path.lineTo(715.0f, 695.465f);
        path.lineTo(565.0f, 815.95f);
        path.lineTo(795.0f, 445.65f);
        path.lineTo(135.0f, 115.465f);
        path.lineTo(475.0f, 575.655f);
        path.lineTo(345.0f, 855.35f);
        path.lineTo(215.0f, 585.405f);
        path.lineTo(945.0f, 255.685f);
        path.lineTo(505.0f, 105.505f);
        path.lineTo(75.0f, 85.655f);
        path.lineTo(215.0f, 215.885f);
        path.lineTo(965.0f, 295.535f);
        path.lineTo(935.0f, 445.905f);
        path.lineTo(995.0f, 405.365f);
        path.lineTo(375.0f, 505.525f);
        path.lineTo(295.0f, 465.545f);
        path.lineTo(885.0f, 535.525f);
        path.lineTo(265.0f, 65.975f);
        path.lineTo(435.0f, 265.845f);
        path.lineTo(825.0f, 85.645f);
        path.lineTo(755.0f, 955.785f);
        path.lineTo(145.0f, 355.25f);
        path.lineTo(325.0f, 365.455f);
        path.lineTo(445.0f, 775.95f);
        path.lineTo(825.0f, 995.235f);
        path.lineTo(55.0f, 615.475f);
        path.lineTo(815.0f, 855.815f);
        path.lineTo(575.0f, 165.75f);
        path.lineTo(535.0f, 845.965f);
        path.lineTo(955.0f, 675.775f);
        path.lineTo(965.0f, 315.965f);
        path.lineTo(545.0f, 435.445f);
        path.lineTo(55.0f, 235.435f);
        path.lineTo(355.0f, 365.995f);
        path.lineTo(965.0f, 645.715f);
        path.lineTo(385.0f, 435.895f);
        path.lineTo(675.0f, 565.175f);
        path.lineTo(875.0f, 865.55f);
        path.lineTo(755.0f, 95.335f);
        path.lineTo(555.0f, 75.895f);
        path.lineTo(45.0f, 705.365f);
        path.lineTo(855.0f, 75.225f);
        path.lineTo(405.0f, 395.225f);
        path.lineTo(565.0f, 705.385f);
        path.lineTo(355.0f, 995.1016f);
        path.lineTo(575.0f, 765.305f);
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
