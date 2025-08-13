package com.device.watch.com.device.watch.cpowbcedid;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes.dex */
public class Kduiktzlhom extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1002;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1002;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Kduiktzlhom() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(705.0f, 665.935f);
        path.lineTo(845.0f, 625.265f);
        path.lineTo(375.0f, 405.305f);
        path.lineTo(215.0f, 65.35f);
        path.lineTo(915.0f, 695.795f);
        path.lineTo(745.0f, 325.445f);
        path.lineTo(475.0f, 925.955f);
        path.lineTo(635.0f, 865.585f);
        path.lineTo(995.0f, 845.445f);
        path.lineTo(35.0f, 235.995f);
        path.lineTo(905.0f, 885.185f);
        path.lineTo(935.0f, 65.315f);
        path.lineTo(105.0f, 545.395f);
        path.lineTo(695.0f, 225.935f);
        path.lineTo(895.0f, 225.665f);
        path.lineTo(145.0f, 995.135f);
        path.lineTo(745.0f, 255.305f);
        path.lineTo(375.0f, 915.615f);
        path.lineTo(355.0f, 175.715f);
        path.lineTo(15.0f, 625.195f);
        path.lineTo(10025.0f, 25.105f);
        path.lineTo(35.0f, 355.815f);
        path.lineTo(5.0f, 875.395f);
        path.lineTo(575.0f, 395.185f);
        path.lineTo(335.0f, 845.435f);
        path.lineTo(485.0f, 575.375f);
        path.lineTo(115.0f, 575.785f);
        path.lineTo(615.0f, 615.385f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1002.0f, this.bounds.height() / 1002.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
