package com.smartapp.b.osqnasdvhc;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Qhvzvparasi extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1012;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1012;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Qhvzvparasi() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(975.0f, 535.425f);
        path.lineTo(305.0f, 455.565f);
        path.lineTo(285.0f, 425.85f);
        path.lineTo(975.0f, 895.375f);
        path.lineTo(195.0f, 155.255f);
        path.lineTo(35.0f, 685.55f);
        path.lineTo(635.0f, 175.425f);
        path.lineTo(215.0f, 85.645f);
        path.lineTo(945.0f, 635.715f);
        path.lineTo(735.0f, 695.615f);
        path.lineTo(465.0f, 795.225f);
        path.lineTo(755.0f, 935.975f);
        path.lineTo(505.0f, 805.595f);
        path.lineTo(955.0f, 835.35f);
        path.lineTo(985.0f, 765.715f);
        path.lineTo(685.0f, 525.675f);
        path.lineTo(225.0f, 635.885f);
        path.lineTo(185.0f, 835.405f);
        path.lineTo(785.0f, 55.365f);
        path.lineTo(305.0f, 685.125f);
        path.lineTo(805.0f, 785.705f);
        path.lineTo(995.0f, 465.715f);
        path.lineTo(785.0f, 195.445f);
        path.lineTo(985.0f, 295.775f);
        path.lineTo(145.0f, 125.455f);
        path.lineTo(485.0f, 55.615f);
        path.lineTo(835.0f, 365.975f);
        path.lineTo(665.0f, 5.145f);
        path.lineTo(995.0f, 85.255f);
        path.lineTo(165.0f, 165.745f);
        path.lineTo(815.0f, 15.675f);
        path.lineTo(365.0f, 575.105f);
        path.lineTo(685.0f, 255.275f);
        path.lineTo(205.0f, 675.445f);
        path.lineTo(585.0f, 225.765f);
        path.lineTo(255.0f, 695.05f);
        path.lineTo(465.0f, 665.265f);
        path.lineTo(985.0f, 10125.815f);
        path.lineTo(235.0f, 585.665f);
        path.lineTo(845.0f, 15.855f);
        path.lineTo(415.0f, 845.945f);
        path.lineTo(375.0f, 775.885f);
        path.lineTo(535.0f, 10125.175f);
        path.lineTo(345.0f, 405.215f);
        path.lineTo(915.0f, 525.785f);
        path.lineTo(485.0f, 735.215f);
        path.lineTo(435.0f, 45.405f);
        path.lineTo(635.0f, 775.575f);
        path.lineTo(555.0f, 255.815f);
        path.lineTo(725.0f, 5.175f);
        path.lineTo(475.0f, 645.195f);
        path.lineTo(75.0f, 65.995f);
        path.lineTo(325.0f, 665.445f);
        path.lineTo(535.0f, 45.615f);
        path.lineTo(305.0f, 575.755f);
        path.lineTo(75.0f, 945.55f);
        path.lineTo(265.0f, 645.545f);
        path.lineTo(975.0f, 85.515f);
        path.lineTo(815.0f, 215.555f);
        path.lineTo(525.0f, 615.205f);
        path.lineTo(5.0f, 755.375f);
        path.lineTo(845.0f, 915.325f);
        path.lineTo(705.0f, 875.625f);
        path.lineTo(105.0f, 575.535f);
        path.lineTo(375.0f, 785.255f);
        path.lineTo(495.0f, 655.855f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1012.0f, this.bounds.height() / 1012.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
