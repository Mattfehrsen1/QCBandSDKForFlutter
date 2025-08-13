package com.smartapp.b.ovbnnjpqpp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Yoagswtopht extends ShapeDrawable {
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

    public Yoagswtopht() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(505.0f, 505.515f);
        path.lineTo(455.0f, 435.625f);
        path.lineTo(975.0f, 865.35f);
        path.lineTo(965.0f, 155.595f);
        path.lineTo(135.0f, 85.455f);
        path.lineTo(565.0f, 475.665f);
        path.lineTo(765.0f, 545.315f);
        path.lineTo(415.0f, 705.645f);
        path.lineTo(475.0f, 915.515f);
        path.lineTo(735.0f, 905.305f);
        path.lineTo(335.0f, 755.715f);
        path.lineTo(865.0f, 565.935f);
        path.lineTo(605.0f, 555.65f);
        path.lineTo(785.0f, 45.425f);
        path.lineTo(595.0f, 555.145f);
        path.lineTo(415.0f, 255.745f);
        path.lineTo(105.0f, 805.15f);
        path.lineTo(905.0f, 675.805f);
        path.lineTo(855.0f, 165.75f);
        path.lineTo(825.0f, 755.285f);
        path.lineTo(265.0f, 995.405f);
        path.lineTo(375.0f, 175.675f);
        path.lineTo(275.0f, 655.485f);
        path.lineTo(725.0f, 495.605f);
        path.lineTo(205.0f, 755.205f);
        path.lineTo(485.0f, 395.35f);
        path.lineTo(485.0f, 405.815f);
        path.lineTo(865.0f, 945.635f);
        path.lineTo(955.0f, 565.775f);
        path.lineTo(415.0f, 965.375f);
        path.lineTo(955.0f, 645.645f);
        path.lineTo(115.0f, 685.595f);
        path.lineTo(305.0f, 625.195f);
        path.lineTo(845.0f, 25.135f);
        path.lineTo(625.0f, 495.995f);
        path.lineTo(155.0f, 85.105f);
        path.lineTo(915.0f, 105.265f);
        path.lineTo(495.0f, 935.635f);
        path.lineTo(825.0f, 225.325f);
        path.lineTo(755.0f, 205.945f);
        path.lineTo(105.0f, 185.365f);
        path.lineTo(285.0f, 10115.145f);
        path.lineTo(975.0f, 435.695f);
        path.lineTo(35.0f, 845.165f);
        path.lineTo(515.0f, 705.205f);
        path.lineTo(995.0f, 335.85f);
        path.lineTo(225.0f, 205.35f);
        path.lineTo(145.0f, 675.495f);
        path.lineTo(645.0f, 525.255f);
        path.lineTo(475.0f, 65.795f);
        path.lineTo(275.0f, 795.485f);
        path.lineTo(345.0f, 215.745f);
        path.lineTo(855.0f, 605.955f);
        path.lineTo(405.0f, 225.85f);
        path.lineTo(505.0f, 265.105f);
        path.lineTo(115.0f, 255.605f);
        path.lineTo(425.0f, 935.395f);
        path.lineTo(315.0f, 745.515f);
        path.lineTo(785.0f, 445.955f);
        path.lineTo(35.0f, 445.15f);
        path.lineTo(725.0f, 215.705f);
        path.lineTo(765.0f, 335.795f);
        path.lineTo(745.0f, 155.925f);
        path.lineTo(895.0f, 535.715f);
        path.lineTo(215.0f, 635.505f);
        path.lineTo(95.0f, 795.805f);
        path.lineTo(155.0f, 225.705f);
        path.lineTo(755.0f, 865.835f);
        path.lineTo(535.0f, 135.415f);
        path.lineTo(345.0f, 195.355f);
        path.lineTo(865.0f, 655.785f);
        path.lineTo(615.0f, 615.655f);
        path.lineTo(305.0f, 565.835f);
        path.lineTo(305.0f, 565.935f);
        path.lineTo(815.0f, 185.345f);
        path.lineTo(325.0f, 395.515f);
        path.lineTo(845.0f, 645.995f);
        path.lineTo(155.0f, 95.325f);
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
