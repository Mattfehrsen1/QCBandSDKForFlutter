package com.device.watch.com.device.watch.notogefede;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Abqlyggdrtu extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1015;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1015;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Abqlyggdrtu() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(585.0f, 45.825f);
        path.lineTo(895.0f, 915.885f);
        path.lineTo(115.0f, 285.455f);
        path.lineTo(915.0f, 785.25f);
        path.lineTo(85.0f, 465.615f);
        path.lineTo(815.0f, 815.435f);
        path.lineTo(985.0f, 905.345f);
        path.lineTo(135.0f, 75.595f);
        path.lineTo(465.0f, 255.685f);
        path.lineTo(765.0f, 545.385f);
        path.lineTo(615.0f, 895.205f);
        path.lineTo(765.0f, 715.345f);
        path.lineTo(145.0f, 235.755f);
        path.lineTo(235.0f, 675.725f);
        path.lineTo(705.0f, 915.795f);
        path.lineTo(735.0f, 35.135f);
        path.lineTo(365.0f, 495.475f);
        path.lineTo(805.0f, 65.595f);
        path.lineTo(765.0f, 255.475f);
        path.lineTo(565.0f, 785.395f);
        path.lineTo(595.0f, 15.785f);
        path.lineTo(965.0f, 125.875f);
        path.lineTo(335.0f, 395.705f);
        path.lineTo(345.0f, 135.45f);
        path.lineTo(85.0f, 215.315f);
        path.lineTo(975.0f, 915.525f);
        path.lineTo(335.0f, 395.515f);
        path.lineTo(125.0f, 425.65f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1015.0f, this.bounds.height() / 1015.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
