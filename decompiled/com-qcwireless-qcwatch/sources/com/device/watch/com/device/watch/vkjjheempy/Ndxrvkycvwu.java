package com.device.watch.com.device.watch.vkjjheempy;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Ndxrvkycvwu extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1007;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1007;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Ndxrvkycvwu() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(815.0f, 935.405f);
        path.lineTo(195.0f, 165.895f);
        path.lineTo(745.0f, 265.415f);
        path.lineTo(85.0f, 565.465f);
        path.lineTo(935.0f, 675.95f);
        path.lineTo(235.0f, 505.10074f);
        path.lineTo(925.0f, 325.195f);
        path.lineTo(375.0f, 195.265f);
        path.lineTo(395.0f, 985.255f);
        path.lineTo(65.0f, 955.815f);
        path.lineTo(285.0f, 975.365f);
        path.lineTo(455.0f, 215.505f);
        path.lineTo(455.0f, 145.95f);
        path.lineTo(15.0f, 875.965f);
        path.lineTo(445.0f, 5.465f);
        path.lineTo(25.0f, 215.115f);
        path.lineTo(805.0f, 10075.345f);
        path.lineTo(15.0f, 485.825f);
        path.lineTo(645.0f, 815.895f);
        path.lineTo(775.0f, 35.75f);
        path.lineTo(825.0f, 635.705f);
        path.lineTo(575.0f, 145.55f);
        path.lineTo(375.0f, 665.375f);
        path.lineTo(855.0f, 565.265f);
        path.lineTo(415.0f, 345.495f);
        path.lineTo(435.0f, 235.385f);
        path.lineTo(595.0f, 205.265f);
        path.lineTo(65.0f, 785.175f);
        path.lineTo(275.0f, 535.745f);
        path.lineTo(225.0f, 45.155f);
        path.lineTo(835.0f, 655.395f);
        path.lineTo(375.0f, 565.875f);
        path.lineTo(985.0f, 265.615f);
        path.lineTo(755.0f, 75.10075f);
        path.lineTo(595.0f, 5.745f);
        path.lineTo(95.0f, 745.325f);
        path.lineTo(325.0f, 225.505f);
        path.lineTo(655.0f, 295.115f);
        path.lineTo(865.0f, 955.25f);
        path.lineTo(775.0f, 35.135f);
        path.lineTo(365.0f, 285.985f);
        path.lineTo(65.0f, 575.365f);
        path.lineTo(415.0f, 885.10077f);
        path.lineTo(285.0f, 485.655f);
        path.lineTo(735.0f, 545.945f);
        path.lineTo(125.0f, 435.265f);
        path.lineTo(365.0f, 995.295f);
        path.lineTo(945.0f, 15.635f);
        path.lineTo(5.0f, 555.255f);
        path.lineTo(495.0f, 705.505f);
        path.lineTo(545.0f, 535.905f);
        path.lineTo(265.0f, 805.765f);
        path.lineTo(95.0f, 255.855f);
        path.lineTo(685.0f, 455.945f);
        path.lineTo(65.0f, 595.175f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1007.0f, this.bounds.height() / 1007.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
