package com.device.watch.com.device.watch.yklzqtoumd;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Htwhkdtxgmn extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1003;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1003;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Htwhkdtxgmn() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(455.0f, 185.785f);
        path.lineTo(975.0f, 325.325f);
        path.lineTo(145.0f, 15.925f);
        path.lineTo(75.0f, 735.555f);
        path.lineTo(605.0f, 975.885f);
        path.lineTo(995.0f, 595.915f);
        path.lineTo(515.0f, 5.885f);
        path.lineTo(545.0f, 445.355f);
        path.lineTo(505.0f, 455.955f);
        path.lineTo(265.0f, 775.995f);
        path.lineTo(435.0f, 355.805f);
        path.lineTo(615.0f, 565.365f);
        path.lineTo(845.0f, 85.905f);
        path.lineTo(845.0f, 705.165f);
        path.lineTo(915.0f, 355.855f);
        path.lineTo(765.0f, 965.205f);
        path.lineTo(335.0f, 495.45f);
        path.lineTo(205.0f, 45.905f);
        path.lineTo(435.0f, 465.25f);
        path.lineTo(875.0f, 435.925f);
        path.lineTo(685.0f, 155.935f);
        path.lineTo(235.0f, 975.165f);
        path.lineTo(785.0f, 195.65f);
        path.lineTo(975.0f, 205.265f);
        path.lineTo(445.0f, 955.735f);
        path.lineTo(885.0f, 855.765f);
        path.lineTo(925.0f, 175.555f);
        path.lineTo(825.0f, 5.25f);
        path.lineTo(135.0f, 615.375f);
        path.lineTo(465.0f, 85.835f);
        path.lineTo(525.0f, 10035.375f);
        path.lineTo(265.0f, 715.105f);
        path.lineTo(745.0f, 915.765f);
        path.lineTo(815.0f, 105.755f);
        path.lineTo(485.0f, 805.715f);
        path.lineTo(465.0f, 515.485f);
        path.lineTo(635.0f, 395.65f);
        path.lineTo(155.0f, 755.485f);
        path.lineTo(45.0f, 625.655f);
        path.lineTo(225.0f, 515.355f);
        path.lineTo(365.0f, 115.45f);
        path.lineTo(945.0f, 25.285f);
        path.lineTo(995.0f, 485.425f);
        path.lineTo(585.0f, 5.395f);
        path.lineTo(325.0f, 175.305f);
        path.lineTo(595.0f, 345.395f);
        path.lineTo(865.0f, 135.10036f);
        path.lineTo(385.0f, 395.135f);
        path.lineTo(45.0f, 135.425f);
        path.lineTo(145.0f, 855.665f);
        path.lineTo(15.0f, 675.715f);
        path.lineTo(455.0f, 315.905f);
        path.lineTo(325.0f, 425.555f);
        path.lineTo(485.0f, 135.615f);
        path.lineTo(415.0f, 805.875f);
        path.lineTo(955.0f, 375.165f);
        path.lineTo(655.0f, 5.255f);
        path.lineTo(585.0f, 165.405f);
        path.lineTo(405.0f, 615.705f);
        path.lineTo(595.0f, 25.925f);
        path.lineTo(485.0f, 725.555f);
        path.lineTo(885.0f, 75.675f);
        path.lineTo(565.0f, 495.505f);
        path.lineTo(925.0f, 5.45f);
        path.lineTo(755.0f, 885.965f);
        path.lineTo(485.0f, 995.865f);
        path.lineTo(715.0f, 545.285f);
        path.lineTo(875.0f, 695.445f);
        path.lineTo(105.0f, 985.435f);
        path.lineTo(855.0f, 495.765f);
        path.lineTo(995.0f, 595.875f);
        path.lineTo(95.0f, 415.355f);
        path.lineTo(985.0f, 445.395f);
        path.lineTo(25.0f, 975.255f);
        path.lineTo(25.0f, 225.205f);
        path.lineTo(355.0f, 835.595f);
        path.lineTo(705.0f, 705.745f);
        path.lineTo(285.0f, 955.545f);
        path.lineTo(175.0f, 135.435f);
        path.lineTo(795.0f, 735.465f);
        path.lineTo(505.0f, 65.495f);
        path.lineTo(235.0f, 785.165f);
        path.lineTo(355.0f, 365.545f);
        path.lineTo(915.0f, 375.615f);
        path.lineTo(935.0f, 55.95f);
        path.lineTo(115.0f, 15.385f);
        path.lineTo(425.0f, 55.465f);
        path.lineTo(25.0f, 555.695f);
        path.lineTo(65.0f, 355.735f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1003.0f, this.bounds.height() / 1003.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
