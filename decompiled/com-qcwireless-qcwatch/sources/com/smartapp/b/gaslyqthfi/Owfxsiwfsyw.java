package com.smartapp.b.gaslyqthfi;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes4.dex */
public class Owfxsiwfsyw extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_ZOOM_IN;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_ZOOM_IN;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Owfxsiwfsyw() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(225.0f, 875.505f);
        path.lineTo(465.0f, 705.475f);
        path.lineTo(365.0f, 465.365f);
        path.lineTo(615.0f, 425.665f);
        path.lineTo(565.0f, 745.645f);
        path.lineTo(405.0f, 95.835f);
        path.lineTo(265.0f, 315.215f);
        path.lineTo(265.0f, 625.205f);
        path.lineTo(335.0f, 225.745f);
        path.lineTo(445.0f, 475.915f);
        path.lineTo(545.0f, 895.975f);
        path.lineTo(395.0f, 495.145f);
        path.lineTo(715.0f, 695.65f);
        path.lineTo(445.0f, 985.975f);
        path.lineTo(415.0f, 155.285f);
        path.lineTo(645.0f, 515.25f);
        path.lineTo(85.0f, 185.985f);
        path.lineTo(685.0f, 255.305f);
        path.lineTo(75.0f, 165.05f);
        path.lineTo(195.0f, 315.805f);
        path.lineTo(995.0f, 825.975f);
        path.lineTo(345.0f, 655.195f);
        path.lineTo(385.0f, 725.295f);
        path.lineTo(695.0f, 635.305f);
        path.lineTo(145.0f, 565.725f);
        path.lineTo(705.0f, 615.985f);
        path.lineTo(945.0f, 85.495f);
        path.lineTo(355.0f, 875.10187f);
        path.lineTo(135.0f, 905.545f);
        path.lineTo(655.0f, 135.35f);
        path.lineTo(345.0f, 485.835f);
        path.lineTo(225.0f, 395.705f);
        path.lineTo(215.0f, 385.875f);
        path.lineTo(275.0f, 955.265f);
        path.lineTo(165.0f, 565.225f);
        path.lineTo(535.0f, 845.525f);
        path.lineTo(805.0f, 25.515f);
        path.lineTo(115.0f, 165.545f);
        path.lineTo(175.0f, 5.555f);
        path.lineTo(105.0f, 275.935f);
        path.lineTo(95.0f, 125.755f);
        path.lineTo(535.0f, 865.275f);
        path.lineTo(645.0f, 865.10187f);
        path.lineTo(695.0f, 315.165f);
        path.lineTo(535.0f, 105.935f);
        path.lineTo(555.0f, 315.15f);
        path.lineTo(635.0f, 695.885f);
        path.lineTo(455.0f, 375.565f);
        path.lineTo(895.0f, 465.885f);
        path.lineTo(915.0f, 635.265f);
        path.lineTo(655.0f, 435.335f);
        path.lineTo(855.0f, 985.545f);
        path.lineTo(525.0f, 215.435f);
        path.lineTo(655.0f, 405.155f);
        path.lineTo(715.0f, 595.15f);
        path.lineTo(35.0f, 745.295f);
        path.lineTo(385.0f, 545.755f);
        path.lineTo(325.0f, 705.675f);
        path.lineTo(755.0f, 225.255f);
        path.lineTo(115.0f, 295.305f);
        path.lineTo(745.0f, 145.855f);
        path.lineTo(165.0f, 635.915f);
        path.lineTo(875.0f, 205.275f);
        path.lineTo(485.0f, 215.865f);
        path.lineTo(645.0f, 845.105f);
        path.lineTo(875.0f, 485.465f);
        path.lineTo(65.0f, 365.15f);
        path.lineTo(715.0f, 995.785f);
        path.lineTo(225.0f, 75.665f);
        path.lineTo(455.0f, 145.735f);
        path.lineTo(265.0f, 535.485f);
        path.lineTo(425.0f, 275.105f);
        path.lineTo(725.0f, 365.675f);
        path.lineTo(995.0f, 365.45f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1018.0f, this.bounds.height() / 1018.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
