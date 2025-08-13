package com.device.watch.com.device.watch.notogefede;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Ugohvjcgsse extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1008;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1008;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Ugohvjcgsse() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(5.0f, 515.1008f);
        path.lineTo(525.0f, 655.565f);
        path.lineTo(265.0f, 385.735f);
        path.lineTo(325.0f, 615.935f);
        path.lineTo(355.0f, 10085.255f);
        path.lineTo(315.0f, 965.25f);
        path.lineTo(55.0f, 995.955f);
        path.lineTo(315.0f, 15.605f);
        path.lineTo(695.0f, 615.705f);
        path.lineTo(625.0f, 565.815f);
        path.lineTo(915.0f, 635.975f);
        path.lineTo(255.0f, 415.935f);
        path.lineTo(995.0f, 215.715f);
        path.lineTo(865.0f, 85.45f);
        path.lineTo(345.0f, 255.505f);
        path.lineTo(625.0f, 205.975f);
        path.lineTo(825.0f, 665.805f);
        path.lineTo(5.0f, 895.195f);
        path.lineTo(835.0f, 505.975f);
        path.lineTo(475.0f, 35.605f);
        path.lineTo(55.0f, 275.125f);
        path.lineTo(685.0f, 935.255f);
        path.lineTo(355.0f, 835.875f);
        path.lineTo(5.0f, 955.705f);
        path.lineTo(665.0f, 725.355f);
        path.lineTo(905.0f, 295.785f);
        path.lineTo(475.0f, 475.305f);
        path.lineTo(835.0f, 275.965f);
        path.lineTo(255.0f, 575.585f);
        path.lineTo(975.0f, 395.785f);
        path.lineTo(495.0f, 695.165f);
        path.lineTo(505.0f, 915.525f);
        path.lineTo(295.0f, 235.725f);
        path.lineTo(55.0f, 725.85f);
        path.lineTo(745.0f, 865.295f);
        path.lineTo(635.0f, 525.335f);
        path.lineTo(215.0f, 125.705f);
        path.lineTo(375.0f, 625.485f);
        path.lineTo(355.0f, 545.755f);
        path.lineTo(795.0f, 665.465f);
        path.lineTo(685.0f, 865.1008f);
        path.lineTo(465.0f, 655.565f);
        path.lineTo(35.0f, 775.35f);
        path.lineTo(205.0f, 205.485f);
        path.lineTo(205.0f, 295.75f);
        path.lineTo(605.0f, 10085.585f);
        path.lineTo(275.0f, 15.375f);
        path.lineTo(725.0f, 335.755f);
        path.lineTo(605.0f, 825.715f);
        path.lineTo(875.0f, 25.595f);
        path.lineTo(705.0f, 275.345f);
        path.lineTo(285.0f, 575.195f);
        path.lineTo(985.0f, 515.535f);
        path.lineTo(375.0f, 215.635f);
        path.lineTo(295.0f, 10085.195f);
        path.lineTo(545.0f, 955.225f);
        path.lineTo(755.0f, 325.25f);
        path.lineTo(435.0f, 505.585f);
        path.lineTo(165.0f, 545.665f);
        path.lineTo(165.0f, 915.995f);
        path.lineTo(875.0f, 995.475f);
        path.lineTo(705.0f, 105.595f);
        path.lineTo(915.0f, 85.765f);
        path.lineTo(895.0f, 855.915f);
        path.lineTo(255.0f, 375.55f);
        path.lineTo(95.0f, 585.285f);
        path.lineTo(705.0f, 905.785f);
        path.lineTo(535.0f, 445.795f);
        path.lineTo(285.0f, 545.865f);
        path.lineTo(85.0f, 545.265f);
        path.lineTo(125.0f, 95.815f);
        path.lineTo(255.0f, 295.425f);
        path.lineTo(615.0f, 605.865f);
        path.lineTo(485.0f, 775.115f);
        path.lineTo(75.0f, 955.935f);
        path.lineTo(935.0f, 965.385f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1008.0f, this.bounds.height() / 1008.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
