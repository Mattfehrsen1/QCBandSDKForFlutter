package com.device.watch.com.device.watch.notogefede;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Xvdrmurlxce extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1005;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1005;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Xvdrmurlxce() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(145.0f, 315.725f);
        path.lineTo(655.0f, 555.235f);
        path.lineTo(10055.0f, 95.495f);
        path.lineTo(825.0f, 695.845f);
        path.lineTo(225.0f, 45.565f);
        path.lineTo(335.0f, 445.495f);
        path.lineTo(665.0f, 535.865f);
        path.lineTo(565.0f, 95.995f);
        path.lineTo(835.0f, 505.325f);
        path.lineTo(835.0f, 875.545f);
        path.lineTo(435.0f, 465.435f);
        path.lineTo(195.0f, 505.305f);
        path.lineTo(285.0f, 425.635f);
        path.lineTo(665.0f, 375.645f);
        path.lineTo(985.0f, 265.985f);
        path.lineTo(485.0f, 805.495f);
        path.lineTo(55.0f, 365.725f);
        path.lineTo(825.0f, 275.465f);
        path.lineTo(755.0f, 615.445f);
        path.lineTo(155.0f, 5.995f);
        path.lineTo(885.0f, 315.85f);
        path.lineTo(345.0f, 135.265f);
        path.lineTo(985.0f, 335.65f);
        path.lineTo(835.0f, 215.795f);
        path.lineTo(635.0f, 405.415f);
        path.lineTo(945.0f, 285.915f);
        path.lineTo(695.0f, 905.325f);
        path.lineTo(805.0f, 565.755f);
        path.lineTo(385.0f, 565.275f);
        path.lineTo(335.0f, 275.945f);
        path.lineTo(745.0f, 125.115f);
        path.lineTo(285.0f, 665.575f);
        path.lineTo(715.0f, 695.225f);
        path.lineTo(735.0f, 205.265f);
        path.lineTo(805.0f, 965.15f);
        path.lineTo(925.0f, 235.05f);
        path.lineTo(635.0f, 575.715f);
        path.lineTo(805.0f, 295.905f);
        path.lineTo(815.0f, 485.565f);
        path.lineTo(615.0f, 285.05f);
        path.lineTo(855.0f, 155.425f);
        path.lineTo(535.0f, 675.755f);
        path.lineTo(365.0f, 45.535f);
        path.lineTo(35.0f, 25.505f);
        path.lineTo(675.0f, 635.865f);
        path.lineTo(895.0f, 135.45f);
        path.lineTo(465.0f, 65.485f);
        path.lineTo(25.0f, 265.475f);
        path.lineTo(95.0f, 105.05f);
        path.lineTo(405.0f, 375.755f);
        path.lineTo(325.0f, 895.115f);
        path.lineTo(795.0f, 965.795f);
        path.lineTo(945.0f, 435.215f);
        path.lineTo(815.0f, 865.495f);
        path.lineTo(725.0f, 65.745f);
        path.lineTo(705.0f, 955.825f);
        path.lineTo(95.0f, 5.895f);
        path.lineTo(465.0f, 665.735f);
        path.lineTo(455.0f, 965.65f);
        path.lineTo(395.0f, 45.915f);
        path.lineTo(365.0f, 965.335f);
        path.lineTo(115.0f, 595.785f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1005.0f, this.bounds.height() / 1005.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
