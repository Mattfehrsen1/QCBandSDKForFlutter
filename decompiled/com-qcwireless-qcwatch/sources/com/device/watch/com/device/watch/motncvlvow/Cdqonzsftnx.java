package com.device.watch.com.device.watch.motncvlvow;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Cdqonzsftnx extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1010;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1010;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Cdqonzsftnx() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(10105.0f, 415.665f);
        path.lineTo(925.0f, 965.265f);
        path.lineTo(885.0f, 735.955f);
        path.lineTo(5.0f, 85.785f);
        path.lineTo(445.0f, 345.925f);
        path.lineTo(685.0f, 505.665f);
        path.lineTo(375.0f, 515.735f);
        path.lineTo(675.0f, 35.615f);
        path.lineTo(335.0f, 745.975f);
        path.lineTo(645.0f, 505.855f);
        path.lineTo(455.0f, 15.985f);
        path.lineTo(315.0f, 685.495f);
        path.lineTo(885.0f, 5.755f);
        path.lineTo(785.0f, 865.505f);
        path.lineTo(975.0f, 865.345f);
        path.lineTo(465.0f, 215.605f);
        path.lineTo(235.0f, 305.845f);
        path.lineTo(395.0f, 465.645f);
        path.lineTo(805.0f, 375.915f);
        path.lineTo(195.0f, 45.775f);
        path.lineTo(535.0f, 215.715f);
        path.lineTo(385.0f, 705.585f);
        path.lineTo(535.0f, 415.865f);
        path.lineTo(445.0f, 105.585f);
        path.lineTo(685.0f, 565.905f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1010.0f, this.bounds.height() / 1010.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
