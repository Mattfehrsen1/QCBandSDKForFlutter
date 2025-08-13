package com.smartapp.b.mmwoxmmfto;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Jfirpobhnnc extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1009;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1009;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Jfirpobhnnc() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(305.0f, 155.875f);
        path.lineTo(275.0f, 25.955f);
        path.lineTo(185.0f, 935.825f);
        path.lineTo(815.0f, 645.125f);
        path.lineTo(75.0f, 925.665f);
        path.lineTo(135.0f, 765.195f);
        path.lineTo(445.0f, 815.605f);
        path.lineTo(455.0f, 575.575f);
        path.lineTo(585.0f, 995.235f);
        path.lineTo(275.0f, 15.665f);
        path.lineTo(475.0f, 185.125f);
        path.lineTo(505.0f, 355.145f);
        path.lineTo(705.0f, 655.775f);
        path.lineTo(15.0f, 125.255f);
        path.lineTo(425.0f, 225.125f);
        path.lineTo(785.0f, 165.15f);
        path.lineTo(265.0f, 885.325f);
        path.lineTo(795.0f, 755.545f);
        path.lineTo(285.0f, 365.495f);
        path.lineTo(515.0f, 725.835f);
        path.lineTo(385.0f, 825.335f);
        path.lineTo(515.0f, 445.855f);
        path.lineTo(645.0f, 935.935f);
        path.lineTo(625.0f, 455.415f);
        path.lineTo(255.0f, 75.435f);
        path.lineTo(545.0f, 775.365f);
        path.lineTo(395.0f, 5.335f);
        path.lineTo(915.0f, 305.135f);
        path.lineTo(795.0f, 565.935f);
        path.lineTo(975.0f, 745.915f);
        path.lineTo(525.0f, 515.565f);
        path.lineTo(845.0f, 475.755f);
        path.lineTo(195.0f, 375.75f);
        path.lineTo(375.0f, 355.475f);
        path.lineTo(885.0f, 285.385f);
        path.lineTo(635.0f, 935.795f);
        path.lineTo(625.0f, 515.10095f);
        path.lineTo(485.0f, 935.265f);
        path.lineTo(775.0f, 215.435f);
        path.lineTo(525.0f, 365.125f);
        path.lineTo(205.0f, 635.855f);
        path.lineTo(75.0f, 255.925f);
        path.lineTo(555.0f, 885.525f);
        path.lineTo(985.0f, 705.905f);
        path.lineTo(605.0f, 105.845f);
        path.lineTo(595.0f, 915.725f);
        path.lineTo(325.0f, 945.235f);
        path.lineTo(455.0f, 935.95f);
        path.lineTo(325.0f, 265.255f);
        path.lineTo(485.0f, 215.405f);
        path.lineTo(415.0f, 45.195f);
        path.lineTo(995.0f, 505.555f);
        path.lineTo(315.0f, 225.345f);
        path.lineTo(975.0f, 835.925f);
        path.lineTo(215.0f, 885.595f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1009.0f, this.bounds.height() / 1009.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
