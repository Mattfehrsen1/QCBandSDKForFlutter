package com.device.watch.com.device.watch.vbyhaufars;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Eyxjahqkenu extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1012;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1012;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Eyxjahqkenu() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(195.0f, 995.465f);
        path.lineTo(825.0f, 475.575f);
        path.lineTo(455.0f, 115.505f);
        path.lineTo(355.0f, 175.205f);
        path.lineTo(335.0f, 955.105f);
        path.lineTo(845.0f, 805.545f);
        path.lineTo(845.0f, 155.575f);
        path.lineTo(515.0f, 395.645f);
        path.lineTo(855.0f, 335.555f);
        path.lineTo(825.0f, 955.305f);
        path.lineTo(545.0f, 935.825f);
        path.lineTo(775.0f, 125.395f);
        path.lineTo(585.0f, 745.625f);
        path.lineTo(495.0f, 665.405f);
        path.lineTo(755.0f, 845.535f);
        path.lineTo(235.0f, 195.135f);
        path.lineTo(715.0f, 925.605f);
        path.lineTo(775.0f, 115.145f);
        path.lineTo(10125.0f, 315.395f);
        path.lineTo(485.0f, 145.05f);
        path.lineTo(605.0f, 495.295f);
        path.lineTo(725.0f, 535.165f);
        path.lineTo(645.0f, 145.275f);
        path.lineTo(565.0f, 595.65f);
        path.lineTo(795.0f, 455.155f);
        path.lineTo(85.0f, 45.625f);
        path.lineTo(175.0f, 745.195f);
        path.lineTo(65.0f, 775.635f);
        path.lineTo(55.0f, 535.195f);
        path.lineTo(675.0f, 685.925f);
        path.lineTo(375.0f, 515.25f);
        path.lineTo(975.0f, 845.535f);
        path.lineTo(155.0f, 65.185f);
        path.lineTo(815.0f, 505.375f);
        path.lineTo(375.0f, 755.455f);
        path.lineTo(455.0f, 635.605f);
        path.lineTo(825.0f, 125.305f);
        path.lineTo(125.0f, 375.05f);
        path.lineTo(785.0f, 75.955f);
        path.lineTo(735.0f, 615.295f);
        path.lineTo(995.0f, 205.345f);
        path.lineTo(135.0f, 785.785f);
        path.lineTo(155.0f, 975.585f);
        path.lineTo(295.0f, 265.835f);
        path.lineTo(45.0f, 5.45f);
        path.lineTo(35.0f, 315.955f);
        path.lineTo(625.0f, 535.445f);
        path.lineTo(175.0f, 125.35f);
        path.lineTo(815.0f, 175.635f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1012.0f, this.bounds.height() / 1012.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
