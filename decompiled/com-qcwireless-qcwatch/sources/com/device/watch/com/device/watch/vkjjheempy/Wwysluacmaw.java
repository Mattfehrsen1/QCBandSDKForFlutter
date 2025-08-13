package com.device.watch.com.device.watch.vkjjheempy;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Wwysluacmaw extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1006;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1006;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Wwysluacmaw() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(955.0f, 205.395f);
        path.lineTo(765.0f, 75.85f);
        path.lineTo(385.0f, 65.855f);
        path.lineTo(235.0f, 625.835f);
        path.lineTo(975.0f, 505.735f);
        path.lineTo(235.0f, 845.865f);
        path.lineTo(795.0f, 845.145f);
        path.lineTo(845.0f, 625.585f);
        path.lineTo(705.0f, 565.165f);
        path.lineTo(875.0f, 895.175f);
        path.lineTo(585.0f, 165.815f);
        path.lineTo(875.0f, 235.635f);
        path.lineTo(305.0f, 415.915f);
        path.lineTo(415.0f, 385.985f);
        path.lineTo(945.0f, 415.325f);
        path.lineTo(555.0f, 495.105f);
        path.lineTo(275.0f, 665.355f);
        path.lineTo(75.0f, 775.945f);
        path.lineTo(335.0f, 395.485f);
        path.lineTo(995.0f, 365.805f);
        path.lineTo(25.0f, 615.375f);
        path.lineTo(845.0f, 135.535f);
        path.lineTo(675.0f, 305.945f);
        path.lineTo(965.0f, 15.35f);
        path.lineTo(95.0f, 655.475f);
        path.lineTo(515.0f, 235.665f);
        path.lineTo(115.0f, 475.525f);
        path.lineTo(995.0f, 255.295f);
        path.lineTo(10065.0f, 955.965f);
        path.lineTo(85.0f, 65.555f);
        path.lineTo(725.0f, 25.75f);
        path.lineTo(335.0f, 205.465f);
        path.lineTo(905.0f, 465.445f);
        path.lineTo(555.0f, 365.765f);
        path.lineTo(855.0f, 295.435f);
        path.lineTo(725.0f, 25.585f);
        path.lineTo(325.0f, 165.45f);
        path.lineTo(685.0f, 305.725f);
        path.lineTo(305.0f, 775.555f);
        path.lineTo(795.0f, 295.885f);
        path.lineTo(775.0f, 655.555f);
        path.lineTo(685.0f, 175.575f);
        path.lineTo(995.0f, 75.965f);
        path.lineTo(865.0f, 585.495f);
        path.lineTo(445.0f, 865.505f);
        path.lineTo(635.0f, 925.505f);
        path.lineTo(395.0f, 675.685f);
        path.lineTo(765.0f, 745.745f);
        path.lineTo(345.0f, 235.225f);
        path.lineTo(415.0f, 495.875f);
        path.lineTo(345.0f, 895.45f);
        path.lineTo(135.0f, 825.105f);
        path.lineTo(535.0f, 665.905f);
        path.lineTo(155.0f, 85.565f);
        path.lineTo(815.0f, 325.545f);
        path.lineTo(255.0f, 445.125f);
        path.lineTo(435.0f, 815.265f);
        path.lineTo(435.0f, 525.65f);
        path.lineTo(595.0f, 45.355f);
        path.lineTo(235.0f, 615.815f);
        path.lineTo(5.0f, 25.395f);
        path.lineTo(165.0f, 725.395f);
        path.lineTo(55.0f, 285.895f);
        path.lineTo(315.0f, 425.975f);
        path.lineTo(65.0f, 385.335f);
        path.lineTo(255.0f, 835.175f);
        path.lineTo(235.0f, 15.885f);
        path.lineTo(515.0f, 225.525f);
        path.lineTo(585.0f, 675.395f);
        path.lineTo(235.0f, 235.845f);
        path.lineTo(785.0f, 555.995f);
        path.lineTo(335.0f, 785.485f);
        path.lineTo(285.0f, 635.445f);
        path.lineTo(465.0f, 995.655f);
        path.lineTo(345.0f, 25.455f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1006.0f, this.bounds.height() / 1006.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
