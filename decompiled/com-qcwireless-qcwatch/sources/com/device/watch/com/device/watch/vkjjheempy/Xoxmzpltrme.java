package com.device.watch.com.device.watch.vkjjheempy;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Xoxmzpltrme extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1009;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1009;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Xoxmzpltrme() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(695.0f, 545.75f);
        path.lineTo(135.0f, 445.625f);
        path.lineTo(275.0f, 435.85f);
        path.lineTo(315.0f, 45.65f);
        path.lineTo(485.0f, 105.135f);
        path.lineTo(895.0f, 10095.205f);
        path.lineTo(435.0f, 295.125f);
        path.lineTo(185.0f, 235.135f);
        path.lineTo(835.0f, 575.75f);
        path.lineTo(675.0f, 795.655f);
        path.lineTo(155.0f, 395.125f);
        path.lineTo(295.0f, 595.375f);
        path.lineTo(105.0f, 565.545f);
        path.lineTo(835.0f, 955.35f);
        path.lineTo(975.0f, 55.105f);
        path.lineTo(275.0f, 205.655f);
        path.lineTo(595.0f, 5.825f);
        path.lineTo(855.0f, 735.85f);
        path.lineTo(605.0f, 595.865f);
        path.lineTo(595.0f, 435.605f);
        path.lineTo(745.0f, 35.555f);
        path.lineTo(295.0f, 815.955f);
        path.lineTo(315.0f, 945.625f);
        path.lineTo(725.0f, 825.425f);
        path.lineTo(315.0f, 115.555f);
        path.lineTo(805.0f, 435.725f);
        path.lineTo(825.0f, 165.645f);
        path.lineTo(775.0f, 565.615f);
        path.lineTo(825.0f, 285.935f);
        path.lineTo(835.0f, 125.625f);
        path.lineTo(615.0f, 945.225f);
        path.lineTo(755.0f, 325.615f);
        path.lineTo(315.0f, 595.435f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1009.0f, this.bounds.height() / 1009.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
