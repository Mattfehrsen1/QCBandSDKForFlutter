package com.smartapp.b.cluknmwhtl;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes3.dex */
public class Rvslfziourm extends ShapeDrawable {
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

    public Rvslfziourm() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(75.0f, 755.455f);
        path.lineTo(605.0f, 315.285f);
        path.lineTo(225.0f, 305.915f);
        path.lineTo(175.0f, 165.515f);
        path.lineTo(85.0f, 445.645f);
        path.lineTo(10165.0f, 905.515f);
        path.lineTo(995.0f, 715.215f);
        path.lineTo(565.0f, 385.605f);
        path.lineTo(335.0f, 565.85f);
        path.lineTo(805.0f, 445.255f);
        path.lineTo(715.0f, 315.625f);
        path.lineTo(625.0f, 585.725f);
        path.lineTo(235.0f, 615.345f);
        path.lineTo(215.0f, 555.495f);
        path.lineTo(975.0f, 85.755f);
        path.lineTo(55.0f, 10165.745f);
        path.lineTo(465.0f, 705.15f);
        path.lineTo(825.0f, 875.575f);
        path.lineTo(275.0f, 455.175f);
        path.lineTo(175.0f, 305.145f);
        path.lineTo(25.0f, 205.925f);
        path.lineTo(55.0f, 595.445f);
        path.lineTo(515.0f, 505.375f);
        path.lineTo(905.0f, 735.45f);
        path.lineTo(695.0f, 635.935f);
        path.lineTo(725.0f, 255.285f);
        path.lineTo(325.0f, 535.295f);
        path.lineTo(405.0f, 365.165f);
        path.lineTo(785.0f, 805.985f);
        path.lineTo(15.0f, 225.905f);
        path.lineTo(945.0f, 725.465f);
        path.lineTo(515.0f, 805.555f);
        path.lineTo(465.0f, 365.665f);
        path.lineTo(745.0f, 15.925f);
        path.lineTo(925.0f, 975.625f);
        path.lineTo(95.0f, 465.915f);
        path.lineTo(745.0f, 10165.765f);
        path.lineTo(65.0f, 15.275f);
        path.lineTo(965.0f, 155.615f);
        path.lineTo(785.0f, 625.915f);
        path.lineTo(965.0f, 15.685f);
        path.lineTo(735.0f, 745.665f);
        path.lineTo(345.0f, 885.105f);
        path.lineTo(865.0f, 45.95f);
        path.lineTo(965.0f, 115.475f);
        path.lineTo(315.0f, 135.65f);
        path.lineTo(705.0f, 855.835f);
        path.lineTo(705.0f, 335.965f);
        path.lineTo(995.0f, 495.735f);
        path.lineTo(275.0f, 965.585f);
        path.lineTo(525.0f, 135.265f);
        path.lineTo(55.0f, 85.555f);
        path.lineTo(925.0f, 485.875f);
        path.lineTo(215.0f, 335.665f);
        path.lineTo(95.0f, 395.895f);
        path.lineTo(145.0f, 855.535f);
        path.lineTo(205.0f, 985.475f);
        path.lineTo(395.0f, 355.735f);
        path.lineTo(875.0f, 775.585f);
        path.lineTo(845.0f, 115.875f);
        path.lineTo(875.0f, 135.395f);
        path.lineTo(935.0f, 785.505f);
        path.lineTo(105.0f, 345.525f);
        path.lineTo(145.0f, 465.395f);
        path.lineTo(495.0f, 575.735f);
        path.lineTo(905.0f, 85.945f);
        path.lineTo(755.0f, 725.335f);
        path.lineTo(815.0f, 35.535f);
        path.lineTo(705.0f, 795.135f);
        path.lineTo(10165.0f, 175.555f);
        path.lineTo(435.0f, 875.595f);
        path.lineTo(995.0f, 45.855f);
        path.lineTo(265.0f, 325.665f);
        path.lineTo(985.0f, 5.195f);
        path.lineTo(725.0f, 605.355f);
        path.lineTo(375.0f, 285.525f);
        path.lineTo(255.0f, 425.445f);
        path.lineTo(655.0f, 375.115f);
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
