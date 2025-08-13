package com.device.watch.com.device.watch.notogefede;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Tlefzncszsd extends ShapeDrawable {
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

    public Tlefzncszsd() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(105.0f, 25.615f);
        path.lineTo(575.0f, 525.955f);
        path.lineTo(975.0f, 615.275f);
        path.lineTo(415.0f, 85.65f);
        path.lineTo(355.0f, 565.805f);
        path.lineTo(135.0f, 845.925f);
        path.lineTo(335.0f, 735.25f);
        path.lineTo(475.0f, 425.865f);
        path.lineTo(445.0f, 465.415f);
        path.lineTo(235.0f, 685.885f);
        path.lineTo(675.0f, 175.195f);
        path.lineTo(165.0f, 515.295f);
        path.lineTo(135.0f, 265.395f);
        path.lineTo(535.0f, 355.435f);
        path.lineTo(525.0f, 445.545f);
        path.lineTo(105.0f, 425.755f);
        path.lineTo(255.0f, 625.435f);
        path.lineTo(995.0f, 815.975f);
        path.lineTo(155.0f, 545.655f);
        path.lineTo(255.0f, 385.225f);
        path.lineTo(645.0f, 835.375f);
        path.lineTo(175.0f, 375.355f);
        path.lineTo(75.0f, 155.585f);
        path.lineTo(645.0f, 235.465f);
        path.lineTo(785.0f, 895.635f);
        path.lineTo(655.0f, 95.395f);
        path.lineTo(295.0f, 755.755f);
        path.lineTo(865.0f, 425.745f);
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
