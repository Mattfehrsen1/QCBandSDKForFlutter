package com.device.watch.com.device.watch.qgxbuvhnur;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Icbgobmzpnr extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1014;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1014;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Icbgobmzpnr() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(205.0f, 115.10145f);
        path.lineTo(905.0f, 175.145f);
        path.lineTo(925.0f, 605.415f);
        path.lineTo(965.0f, 955.205f);
        path.lineTo(515.0f, 985.635f);
        path.lineTo(665.0f, 105.475f);
        path.lineTo(125.0f, 805.795f);
        path.lineTo(375.0f, 525.775f);
        path.lineTo(625.0f, 795.665f);
        path.lineTo(635.0f, 605.375f);
        path.lineTo(235.0f, 315.445f);
        path.lineTo(225.0f, 495.865f);
        path.lineTo(105.0f, 205.685f);
        path.lineTo(595.0f, 745.715f);
        path.lineTo(955.0f, 135.15f);
        path.lineTo(555.0f, 935.835f);
        path.lineTo(125.0f, 365.905f);
        path.lineTo(915.0f, 105.85f);
        path.lineTo(765.0f, 375.115f);
        path.lineTo(645.0f, 985.795f);
        path.lineTo(185.0f, 375.205f);
        path.lineTo(435.0f, 835.945f);
        path.lineTo(855.0f, 925.485f);
        path.lineTo(505.0f, 385.465f);
        path.lineTo(755.0f, 755.85f);
        path.lineTo(945.0f, 505.745f);
        path.lineTo(585.0f, 705.665f);
        path.lineTo(555.0f, 335.955f);
        path.lineTo(5.0f, 985.975f);
        path.lineTo(755.0f, 815.925f);
        path.lineTo(535.0f, 905.825f);
        path.lineTo(445.0f, 705.615f);
        path.lineTo(625.0f, 335.645f);
        path.lineTo(105.0f, 125.765f);
        path.lineTo(955.0f, 695.405f);
        path.lineTo(765.0f, 535.515f);
        path.lineTo(615.0f, 585.705f);
        path.lineTo(415.0f, 495.445f);
        path.lineTo(935.0f, 205.705f);
        path.lineTo(625.0f, 745.795f);
        path.lineTo(145.0f, 635.745f);
        path.lineTo(715.0f, 385.665f);
        path.lineTo(535.0f, 225.315f);
        path.lineTo(5.0f, 635.955f);
        path.lineTo(385.0f, 595.895f);
        path.lineTo(755.0f, 465.345f);
        path.lineTo(835.0f, 15.635f);
        path.lineTo(685.0f, 695.135f);
        path.lineTo(225.0f, 435.275f);
        path.lineTo(225.0f, 125.535f);
        path.lineTo(585.0f, 335.735f);
        path.lineTo(415.0f, 655.625f);
        path.lineTo(435.0f, 685.125f);
        path.lineTo(545.0f, 765.445f);
        path.lineTo(15.0f, 165.895f);
        path.lineTo(75.0f, 335.935f);
        path.lineTo(715.0f, 735.495f);
        path.lineTo(875.0f, 85.395f);
        path.lineTo(185.0f, 855.765f);
        path.lineTo(205.0f, 825.705f);
        path.lineTo(505.0f, 625.635f);
        path.lineTo(975.0f, 145.145f);
        path.lineTo(75.0f, 725.175f);
        path.lineTo(185.0f, 975.85f);
        path.lineTo(10145.0f, 595.505f);
        path.lineTo(605.0f, 185.315f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1014.0f, this.bounds.height() / 1014.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
