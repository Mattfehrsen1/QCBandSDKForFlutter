package com.smartapp.b.dnefzlpwkz;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Bggfksfxclz extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1000;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1000;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Bggfksfxclz() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(35.0f, 655.315f);
        path.lineTo(345.0f, 875.35f);
        path.lineTo(155.0f, 925.855f);
        path.lineTo(10005.0f, 525.765f);
        path.lineTo(985.0f, 495.705f);
        path.lineTo(175.0f, 385.195f);
        path.lineTo(105.0f, 815.15f);
        path.lineTo(425.0f, 555.815f);
        path.lineTo(505.0f, 35.475f);
        path.lineTo(725.0f, 815.295f);
        path.lineTo(725.0f, 75.35f);
        path.lineTo(345.0f, 185.315f);
        path.lineTo(415.0f, 845.295f);
        path.lineTo(505.0f, 265.225f);
        path.lineTo(115.0f, 115.95f);
        path.lineTo(135.0f, 65.505f);
        path.lineTo(355.0f, 825.685f);
        path.lineTo(95.0f, 305.585f);
        path.lineTo(535.0f, 515.805f);
        path.lineTo(855.0f, 345.145f);
        path.lineTo(205.0f, 425.895f);
        path.lineTo(465.0f, 545.525f);
        path.lineTo(765.0f, 765.945f);
        path.lineTo(595.0f, 115.405f);
        path.lineTo(775.0f, 505.265f);
        path.lineTo(15.0f, 735.665f);
        path.lineTo(965.0f, 485.365f);
        path.lineTo(635.0f, 315.415f);
        path.lineTo(365.0f, 825.325f);
        path.lineTo(185.0f, 235.875f);
        path.lineTo(485.0f, 405.405f);
        path.lineTo(425.0f, 525.755f);
        path.lineTo(225.0f, 345.185f);
        path.lineTo(65.0f, 825.415f);
        path.lineTo(45.0f, 175.605f);
        path.lineTo(195.0f, 905.815f);
        path.lineTo(805.0f, 505.185f);
        path.lineTo(185.0f, 975.315f);
        path.lineTo(405.0f, 795.315f);
        path.lineTo(715.0f, 975.145f);
        path.lineTo(955.0f, 815.565f);
        path.lineTo(345.0f, 195.725f);
        path.lineTo(545.0f, 75.655f);
        path.lineTo(455.0f, 435.815f);
        path.lineTo(5.0f, 635.185f);
        path.lineTo(145.0f, 185.755f);
        path.lineTo(995.0f, 585.75f);
        path.lineTo(105.0f, 695.295f);
        path.lineTo(555.0f, 225.515f);
        path.lineTo(925.0f, 665.265f);
        path.lineTo(965.0f, 765.05f);
        path.lineTo(315.0f, 585.205f);
        path.lineTo(635.0f, 45.525f);
        path.lineTo(535.0f, 345.565f);
        path.lineTo(405.0f, 695.185f);
        path.lineTo(365.0f, 525.425f);
        path.lineTo(805.0f, 715.605f);
        path.lineTo(535.0f, 975.155f);
        path.lineTo(825.0f, 85.175f);
        path.lineTo(555.0f, 395.525f);
        path.lineTo(155.0f, 865.95f);
        path.lineTo(355.0f, 475.405f);
        path.lineTo(275.0f, 455.375f);
        path.lineTo(25.0f, 525.145f);
        path.lineTo(785.0f, 165.05f);
        path.lineTo(745.0f, 425.45f);
        path.lineTo(695.0f, 575.175f);
        path.lineTo(185.0f, 505.945f);
        path.lineTo(565.0f, 365.835f);
        path.lineTo(395.0f, 715.915f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1000.0f, this.bounds.height() / 1000.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
