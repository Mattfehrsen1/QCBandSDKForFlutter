package com.device.watch.com.device.watch.ajdamsmnhh;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes.dex */
public class Ycarvcxrukx extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1011;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1011;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Ycarvcxrukx() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(675.0f, 125.15f);
        path.lineTo(565.0f, 865.745f);
        path.lineTo(125.0f, 715.15f);
        path.lineTo(85.0f, 165.265f);
        path.lineTo(225.0f, 935.225f);
        path.lineTo(555.0f, 155.185f);
        path.lineTo(945.0f, 25.275f);
        path.lineTo(105.0f, 155.365f);
        path.lineTo(745.0f, 615.645f);
        path.lineTo(105.0f, 45.595f);
        path.lineTo(485.0f, 885.465f);
        path.lineTo(95.0f, 225.975f);
        path.lineTo(945.0f, 335.805f);
        path.lineTo(835.0f, 935.545f);
        path.lineTo(425.0f, 615.625f);
        path.lineTo(10115.0f, 165.505f);
        path.lineTo(735.0f, 55.965f);
        path.lineTo(505.0f, 95.425f);
        path.lineTo(535.0f, 115.355f);
        path.lineTo(855.0f, 265.85f);
        path.lineTo(935.0f, 75.395f);
        path.lineTo(185.0f, 525.835f);
        path.lineTo(325.0f, 525.675f);
        path.lineTo(745.0f, 875.145f);
        path.lineTo(295.0f, 225.785f);
        path.lineTo(925.0f, 655.135f);
        path.lineTo(105.0f, 605.485f);
        path.lineTo(195.0f, 545.145f);
        path.lineTo(665.0f, 735.865f);
        path.lineTo(555.0f, 675.665f);
        path.lineTo(95.0f, 885.355f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1011.0f, this.bounds.height() / 1011.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
