package com.smartapp.b.grdzjoxewp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Njiptxzcaen extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1014;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1014;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Njiptxzcaen() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(255.0f, 285.225f);
        path.lineTo(435.0f, 285.915f);
        path.lineTo(865.0f, 385.865f);
        path.lineTo(445.0f, 75.695f);
        path.lineTo(15.0f, 105.515f);
        path.lineTo(485.0f, 685.275f);
        path.lineTo(375.0f, 855.275f);
        path.lineTo(45.0f, 475.195f);
        path.lineTo(595.0f, 35.95f);
        path.lineTo(895.0f, 475.255f);
        path.lineTo(995.0f, 615.655f);
        path.lineTo(335.0f, 735.225f);
        path.lineTo(145.0f, 655.265f);
        path.lineTo(225.0f, 975.975f);
        path.lineTo(715.0f, 855.735f);
        path.lineTo(775.0f, 415.75f);
        path.lineTo(345.0f, 685.575f);
        path.lineTo(10145.0f, 55.595f);
        path.lineTo(815.0f, 145.435f);
        path.lineTo(155.0f, 405.195f);
        path.lineTo(635.0f, 365.565f);
        path.lineTo(225.0f, 335.275f);
        path.lineTo(925.0f, 495.05f);
        path.lineTo(225.0f, 165.855f);
        path.lineTo(135.0f, 435.815f);
        path.lineTo(105.0f, 665.445f);
        path.lineTo(785.0f, 145.855f);
        path.lineTo(475.0f, 735.175f);
        path.lineTo(285.0f, 955.965f);
        path.lineTo(25.0f, 35.615f);
        path.lineTo(355.0f, 575.105f);
        path.lineTo(765.0f, 265.365f);
        path.lineTo(855.0f, 605.565f);
        path.lineTo(55.0f, 475.425f);
        path.lineTo(125.0f, 315.335f);
        path.lineTo(315.0f, 775.255f);
        path.lineTo(805.0f, 225.375f);
        path.lineTo(325.0f, 625.325f);
        path.lineTo(155.0f, 85.915f);
        path.lineTo(555.0f, 815.675f);
        path.lineTo(375.0f, 955.615f);
        path.lineTo(385.0f, 295.385f);
        path.lineTo(885.0f, 135.975f);
        path.lineTo(145.0f, 945.95f);
        path.lineTo(495.0f, 565.975f);
        path.lineTo(965.0f, 555.725f);
        path.lineTo(435.0f, 535.965f);
        path.lineTo(525.0f, 115.675f);
        path.lineTo(735.0f, 35.845f);
        path.lineTo(895.0f, 595.325f);
        path.lineTo(475.0f, 15.465f);
        path.lineTo(405.0f, 85.225f);
        path.lineTo(545.0f, 215.835f);
        path.lineTo(875.0f, 5.125f);
        path.lineTo(45.0f, 895.535f);
        path.lineTo(95.0f, 515.325f);
        path.lineTo(55.0f, 25.45f);
        path.lineTo(555.0f, 775.185f);
        path.lineTo(165.0f, 105.745f);
        path.lineTo(255.0f, 295.585f);
        path.lineTo(145.0f, 425.715f);
        path.lineTo(805.0f, 745.845f);
        path.lineTo(395.0f, 745.555f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1014.0f, this.bounds.height() / 1014.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
