package com.device.watch.com.device.watch.ajdamsmnhh;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes.dex */
public class Bksostuunmx extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1010;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1010;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Bksostuunmx() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(905.0f, 825.375f);
        path.lineTo(825.0f, 515.185f);
        path.lineTo(605.0f, 505.365f);
        path.lineTo(295.0f, 25.855f);
        path.lineTo(315.0f, 585.185f);
        path.lineTo(575.0f, 165.625f);
        path.lineTo(745.0f, 945.975f);
        path.lineTo(775.0f, 325.295f);
        path.lineTo(15.0f, 255.635f);
        path.lineTo(405.0f, 725.105f);
        path.lineTo(995.0f, 915.165f);
        path.lineTo(325.0f, 905.965f);
        path.lineTo(215.0f, 985.545f);
        path.lineTo(45.0f, 375.575f);
        path.lineTo(365.0f, 955.585f);
        path.lineTo(975.0f, 635.745f);
        path.lineTo(305.0f, 385.315f);
        path.lineTo(605.0f, 465.755f);
        path.lineTo(265.0f, 505.10104f);
        path.lineTo(45.0f, 565.575f);
        path.lineTo(435.0f, 75.315f);
        path.lineTo(545.0f, 645.515f);
        path.lineTo(595.0f, 10105.335f);
        path.lineTo(965.0f, 805.145f);
        path.lineTo(75.0f, 665.185f);
        path.lineTo(115.0f, 455.845f);
        path.lineTo(975.0f, 805.495f);
        path.lineTo(645.0f, 495.895f);
        path.lineTo(315.0f, 995.455f);
        path.lineTo(655.0f, 575.75f);
        path.lineTo(365.0f, 135.635f);
        path.lineTo(545.0f, 595.1011f);
        path.lineTo(945.0f, 225.155f);
        path.lineTo(725.0f, 295.745f);
        path.lineTo(985.0f, 475.755f);
        path.lineTo(445.0f, 775.05f);
        path.lineTo(125.0f, 675.965f);
        path.lineTo(755.0f, 10105.135f);
        path.lineTo(375.0f, 25.505f);
        path.lineTo(675.0f, 835.105f);
        path.lineTo(465.0f, 145.675f);
        path.lineTo(215.0f, 855.315f);
        path.lineTo(305.0f, 405.435f);
        path.lineTo(105.0f, 995.55f);
        path.lineTo(835.0f, 465.685f);
        path.lineTo(565.0f, 35.415f);
        path.lineTo(45.0f, 565.945f);
        path.lineTo(315.0f, 485.175f);
        path.lineTo(475.0f, 815.945f);
        path.lineTo(335.0f, 535.655f);
        path.lineTo(155.0f, 5.815f);
        path.lineTo(495.0f, 525.135f);
        path.lineTo(535.0f, 745.325f);
        path.lineTo(625.0f, 665.865f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1010.0f, this.bounds.height() / 1010.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
