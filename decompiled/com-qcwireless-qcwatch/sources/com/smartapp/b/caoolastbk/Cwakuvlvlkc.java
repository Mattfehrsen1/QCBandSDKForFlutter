package com.smartapp.b.caoolastbk;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Cwakuvlvlkc extends ShapeDrawable {
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

    public Cwakuvlvlkc() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(45.0f, 105.915f);
        path.lineTo(165.0f, 705.865f);
        path.lineTo(5.0f, 205.845f);
        path.lineTo(955.0f, 215.05f);
        path.lineTo(205.0f, 425.705f);
        path.lineTo(225.0f, 745.335f);
        path.lineTo(885.0f, 485.305f);
        path.lineTo(85.0f, 325.145f);
        path.lineTo(145.0f, 405.685f);
        path.lineTo(985.0f, 195.335f);
        path.lineTo(825.0f, 745.765f);
        path.lineTo(705.0f, 585.715f);
        path.lineTo(735.0f, 365.735f);
        path.lineTo(35.0f, 425.35f);
        path.lineTo(685.0f, 655.845f);
        path.lineTo(695.0f, 275.335f);
        path.lineTo(135.0f, 855.985f);
        path.lineTo(5.0f, 275.605f);
        path.lineTo(275.0f, 855.915f);
        path.lineTo(45.0f, 115.505f);
        path.lineTo(10005.0f, 535.555f);
        path.lineTo(875.0f, 115.495f);
        path.lineTo(485.0f, 215.965f);
        path.lineTo(505.0f, 405.35f);
        path.lineTo(185.0f, 75.285f);
        path.lineTo(975.0f, 115.305f);
        path.lineTo(325.0f, 55.965f);
        path.lineTo(265.0f, 95.535f);
        path.lineTo(10005.0f, 465.165f);
        path.lineTo(355.0f, 655.335f);
        path.lineTo(85.0f, 85.955f);
        path.lineTo(85.0f, 545.555f);
        path.lineTo(45.0f, 535.735f);
        path.lineTo(655.0f, 925.415f);
        path.lineTo(365.0f, 165.185f);
        path.lineTo(795.0f, 515.175f);
        path.lineTo(465.0f, 485.825f);
        path.lineTo(795.0f, 955.155f);
        path.lineTo(255.0f, 825.935f);
        path.lineTo(115.0f, 995.525f);
        path.lineTo(355.0f, 195.275f);
        path.lineTo(75.0f, 185.185f);
        path.lineTo(885.0f, 805.745f);
        path.lineTo(165.0f, 375.645f);
        path.lineTo(435.0f, 55.755f);
        path.lineTo(825.0f, 285.655f);
        path.lineTo(755.0f, 415.605f);
        path.lineTo(645.0f, 965.925f);
        path.lineTo(95.0f, 855.865f);
        path.lineTo(595.0f, 105.785f);
        path.lineTo(995.0f, 285.25f);
        path.lineTo(795.0f, 5.205f);
        path.lineTo(355.0f, 285.845f);
        path.lineTo(55.0f, 185.15f);
        path.lineTo(965.0f, 495.285f);
        path.lineTo(815.0f, 735.55f);
        path.lineTo(735.0f, 225.865f);
        path.lineTo(595.0f, 455.545f);
        path.lineTo(195.0f, 785.445f);
        path.lineTo(785.0f, 255.655f);
        path.lineTo(565.0f, 685.175f);
        path.lineTo(835.0f, 585.915f);
        path.lineTo(195.0f, 505.265f);
        path.lineTo(385.0f, 315.775f);
        path.lineTo(85.0f, 805.225f);
        path.lineTo(485.0f, 395.145f);
        path.lineTo(75.0f, 685.735f);
        path.lineTo(315.0f, 505.855f);
        path.lineTo(145.0f, 135.715f);
        path.lineTo(185.0f, 185.965f);
        path.lineTo(845.0f, 795.865f);
        path.lineTo(815.0f, 885.365f);
        path.lineTo(915.0f, 775.45f);
        path.lineTo(15.0f, 95.925f);
        path.lineTo(365.0f, 905.395f);
        path.lineTo(455.0f, 315.265f);
        path.lineTo(975.0f, 845.95f);
        path.lineTo(15.0f, 525.395f);
        path.lineTo(735.0f, 595.935f);
        path.lineTo(665.0f, 675.995f);
        path.lineTo(10005.0f, 375.405f);
        path.lineTo(255.0f, 205.605f);
        path.lineTo(455.0f, 95.845f);
        path.lineTo(845.0f, 315.265f);
        path.lineTo(865.0f, 135.85f);
        path.lineTo(225.0f, 10005.725f);
        path.lineTo(955.0f, 305.255f);
        path.lineTo(945.0f, 265.385f);
        path.lineTo(455.0f, 305.455f);
        path.lineTo(925.0f, 655.10004f);
        path.lineTo(275.0f, 715.65f);
        path.lineTo(795.0f, 895.165f);
        path.lineTo(435.0f, 745.115f);
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
