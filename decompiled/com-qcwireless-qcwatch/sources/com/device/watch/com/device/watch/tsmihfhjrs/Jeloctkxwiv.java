package com.device.watch.com.device.watch.tsmihfhjrs;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Jeloctkxwiv extends ShapeDrawable {
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

    public Jeloctkxwiv() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(835.0f, 485.275f);
        path.lineTo(825.0f, 355.635f);
        path.lineTo(985.0f, 95.465f);
        path.lineTo(545.0f, 115.515f);
        path.lineTo(755.0f, 905.45f);
        path.lineTo(10055.0f, 255.825f);
        path.lineTo(885.0f, 75.10055f);
        path.lineTo(5.0f, 805.185f);
        path.lineTo(225.0f, 745.405f);
        path.lineTo(915.0f, 665.175f);
        path.lineTo(735.0f, 805.435f);
        path.lineTo(475.0f, 545.95f);
        path.lineTo(305.0f, 605.565f);
        path.lineTo(545.0f, 545.385f);
        path.lineTo(565.0f, 725.725f);
        path.lineTo(415.0f, 875.235f);
        path.lineTo(645.0f, 175.85f);
        path.lineTo(65.0f, 715.975f);
        path.lineTo(305.0f, 425.595f);
        path.lineTo(345.0f, 365.85f);
        path.lineTo(895.0f, 985.85f);
        path.lineTo(815.0f, 785.715f);
        path.lineTo(365.0f, 255.905f);
        path.lineTo(825.0f, 10055.135f);
        path.lineTo(585.0f, 805.525f);
        path.lineTo(115.0f, 125.395f);
        path.lineTo(975.0f, 715.405f);
        path.lineTo(845.0f, 655.925f);
        path.lineTo(735.0f, 545.445f);
        path.lineTo(575.0f, 515.525f);
        path.lineTo(585.0f, 425.95f);
        path.lineTo(35.0f, 675.585f);
        path.lineTo(725.0f, 975.655f);
        path.lineTo(985.0f, 475.185f);
        path.lineTo(415.0f, 185.815f);
        path.lineTo(215.0f, 295.965f);
        path.lineTo(895.0f, 295.305f);
        path.lineTo(305.0f, 765.355f);
        path.lineTo(305.0f, 205.195f);
        path.lineTo(155.0f, 855.195f);
        path.lineTo(75.0f, 985.345f);
        path.lineTo(755.0f, 615.375f);
        path.lineTo(825.0f, 845.925f);
        path.lineTo(615.0f, 125.435f);
        path.lineTo(985.0f, 85.635f);
        path.lineTo(995.0f, 765.455f);
        path.lineTo(575.0f, 985.895f);
        path.lineTo(415.0f, 775.05f);
        path.lineTo(25.0f, 285.415f);
        path.lineTo(365.0f, 75.485f);
        path.lineTo(945.0f, 495.825f);
        path.lineTo(85.0f, 45.875f);
        path.lineTo(945.0f, 95.135f);
        path.lineTo(745.0f, 515.745f);
        path.lineTo(715.0f, 945.935f);
        path.lineTo(535.0f, 715.265f);
        path.lineTo(955.0f, 275.385f);
        path.lineTo(665.0f, 195.495f);
        path.lineTo(745.0f, 565.555f);
        path.lineTo(465.0f, 205.785f);
        path.lineTo(145.0f, 765.345f);
        path.lineTo(505.0f, 825.275f);
        path.lineTo(805.0f, 125.625f);
        path.lineTo(495.0f, 695.365f);
        path.lineTo(415.0f, 935.405f);
        path.lineTo(535.0f, 465.595f);
        path.lineTo(845.0f, 945.335f);
        path.lineTo(725.0f, 465.565f);
        path.lineTo(395.0f, 795.705f);
        path.lineTo(415.0f, 305.195f);
        path.lineTo(295.0f, 595.535f);
        path.lineTo(495.0f, 575.385f);
        path.lineTo(965.0f, 705.225f);
        path.lineTo(125.0f, 595.475f);
        path.lineTo(905.0f, 315.975f);
        path.lineTo(95.0f, 95.455f);
        path.lineTo(555.0f, 955.235f);
        path.lineTo(675.0f, 75.475f);
        path.lineTo(625.0f, 685.295f);
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
