package com.device.watch.com.device.watch.bilmztedjs;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes.dex */
public class Jxhrbqjqazr extends ShapeDrawable {
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

    public Jxhrbqjqazr() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(755.0f, 985.805f);
        path.lineTo(345.0f, 295.535f);
        path.lineTo(405.0f, 375.765f);
        path.lineTo(665.0f, 425.665f);
        path.lineTo(425.0f, 525.735f);
        path.lineTo(15.0f, 235.285f);
        path.lineTo(165.0f, 525.105f);
        path.lineTo(25.0f, 835.885f);
        path.lineTo(945.0f, 955.895f);
        path.lineTo(355.0f, 325.445f);
        path.lineTo(385.0f, 965.765f);
        path.lineTo(845.0f, 885.745f);
        path.lineTo(305.0f, 815.525f);
        path.lineTo(595.0f, 685.585f);
        path.lineTo(295.0f, 255.25f);
        path.lineTo(735.0f, 925.365f);
        path.lineTo(575.0f, 285.945f);
        path.lineTo(275.0f, 285.195f);
        path.lineTo(515.0f, 155.415f);
        path.lineTo(565.0f, 315.385f);
        path.lineTo(135.0f, 765.445f);
        path.lineTo(735.0f, 85.945f);
        path.lineTo(85.0f, 415.765f);
        path.lineTo(735.0f, 975.735f);
        path.lineTo(925.0f, 395.725f);
        path.lineTo(295.0f, 205.445f);
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
