package com.device.watch.com.device.watch.motncvlvow;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Urvohhumsjr extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1008;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1008;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Urvohhumsjr() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(755.0f, 415.775f);
        path.lineTo(515.0f, 425.75f);
        path.lineTo(655.0f, 995.285f);
        path.lineTo(735.0f, 685.425f);
        path.lineTo(865.0f, 925.505f);
        path.lineTo(65.0f, 925.605f);
        path.lineTo(185.0f, 535.815f);
        path.lineTo(575.0f, 125.885f);
        path.lineTo(95.0f, 895.425f);
        path.lineTo(555.0f, 945.205f);
        path.lineTo(565.0f, 445.335f);
        path.lineTo(865.0f, 635.725f);
        path.lineTo(995.0f, 315.45f);
        path.lineTo(105.0f, 645.655f);
        path.lineTo(255.0f, 195.635f);
        path.lineTo(675.0f, 195.835f);
        path.lineTo(325.0f, 585.545f);
        path.lineTo(645.0f, 75.155f);
        path.lineTo(525.0f, 295.835f);
        path.lineTo(535.0f, 615.875f);
        path.lineTo(795.0f, 455.815f);
        path.lineTo(335.0f, 85.65f);
        path.lineTo(475.0f, 835.45f);
        path.lineTo(685.0f, 175.515f);
        path.lineTo(435.0f, 235.755f);
        path.lineTo(715.0f, 455.365f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1008.0f, this.bounds.height() / 1008.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
