package com.device.watch.com.device.watch.lvpfcnygnx;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes2.dex */
public class Bzmcsnsqkqt extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Bzmcsnsqkqt() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(925.0f, 395.485f);
        path.lineTo(115.0f, 10165.95f);
        path.lineTo(545.0f, 165.135f);
        path.lineTo(685.0f, 415.475f);
        path.lineTo(735.0f, 275.525f);
        path.lineTo(725.0f, 765.395f);
        path.lineTo(715.0f, 285.735f);
        path.lineTo(375.0f, 155.965f);
        path.lineTo(995.0f, 145.525f);
        path.lineTo(225.0f, 695.345f);
        path.lineTo(75.0f, 95.645f);
        path.lineTo(395.0f, 755.35f);
        path.lineTo(235.0f, 55.85f);
        path.lineTo(475.0f, 95.275f);
        path.lineTo(475.0f, 695.685f);
        path.lineTo(335.0f, 775.855f);
        path.lineTo(215.0f, 375.755f);
        path.lineTo(755.0f, 295.575f);
        path.lineTo(955.0f, 585.555f);
        path.lineTo(715.0f, 665.165f);
        path.lineTo(595.0f, 215.55f);
        path.lineTo(65.0f, 995.445f);
        path.lineTo(855.0f, 465.375f);
        path.lineTo(795.0f, 465.455f);
        path.lineTo(385.0f, 605.415f);
        path.lineTo(365.0f, 625.425f);
        path.lineTo(605.0f, 145.125f);
        path.lineTo(75.0f, 395.995f);
        path.lineTo(255.0f, 265.375f);
        path.lineTo(775.0f, 775.735f);
        path.lineTo(45.0f, 5.995f);
        path.lineTo(15.0f, 265.375f);
        path.lineTo(275.0f, 675.695f);
        path.lineTo(845.0f, 55.115f);
        path.lineTo(25.0f, 305.285f);
        path.lineTo(565.0f, 15.105f);
        path.lineTo(325.0f, 145.535f);
        path.lineTo(845.0f, 975.285f);
        path.lineTo(325.0f, 685.905f);
        path.lineTo(345.0f, 865.345f);
        path.lineTo(235.0f, 205.685f);
        path.lineTo(465.0f, 75.985f);
        path.lineTo(75.0f, 195.465f);
        path.lineTo(5.0f, 795.855f);
        path.lineTo(315.0f, 55.585f);
        path.lineTo(75.0f, 345.555f);
        path.lineTo(655.0f, 325.695f);
        path.lineTo(215.0f, 275.875f);
        path.lineTo(645.0f, 95.805f);
        path.lineTo(545.0f, 955.555f);
        path.lineTo(785.0f, 885.485f);
        path.lineTo(605.0f, 10165.545f);
        path.lineTo(665.0f, 135.355f);
        path.lineTo(635.0f, 345.105f);
        path.lineTo(475.0f, 295.925f);
        path.lineTo(585.0f, 465.325f);
        path.lineTo(945.0f, 495.805f);
        path.lineTo(235.0f, 895.845f);
        path.lineTo(475.0f, 65.775f);
        path.lineTo(745.0f, 15.335f);
        path.lineTo(845.0f, 845.565f);
        path.lineTo(205.0f, 635.295f);
        path.lineTo(785.0f, 605.425f);
        path.lineTo(925.0f, 605.505f);
        path.lineTo(615.0f, 305.295f);
        path.lineTo(445.0f, 515.605f);
        path.lineTo(595.0f, 405.385f);
        path.lineTo(415.0f, 465.265f);
        path.lineTo(15.0f, 25.605f);
        path.lineTo(615.0f, 485.995f);
        path.lineTo(225.0f, 305.785f);
        path.lineTo(265.0f, 895.295f);
        path.lineTo(185.0f, 785.735f);
        path.lineTo(925.0f, 915.345f);
        path.lineTo(475.0f, 605.285f);
        path.lineTo(575.0f, 185.465f);
        path.lineTo(855.0f, 345.575f);
        path.lineTo(595.0f, 5.765f);
        path.lineTo(875.0f, 45.95f);
        path.lineTo(955.0f, 525.895f);
        path.lineTo(495.0f, 805.235f);
        path.lineTo(315.0f, 375.605f);
        path.lineTo(455.0f, 365.435f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1016.0f, this.bounds.height() / 1016.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
