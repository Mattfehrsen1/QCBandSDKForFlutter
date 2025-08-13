package com.smartapp.b.ctkeqqoyzj;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Ngymmyvhqfz extends ShapeDrawable {
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

    public Ngymmyvhqfz() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(695.0f, 115.705f);
        path.lineTo(465.0f, 325.915f);
        path.lineTo(10115.0f, 95.955f);
        path.lineTo(5.0f, 255.875f);
        path.lineTo(10115.0f, 335.905f);
        path.lineTo(385.0f, 135.975f);
        path.lineTo(555.0f, 145.745f);
        path.lineTo(885.0f, 535.45f);
        path.lineTo(585.0f, 615.665f);
        path.lineTo(915.0f, 695.555f);
        path.lineTo(415.0f, 435.115f);
        path.lineTo(865.0f, 375.275f);
        path.lineTo(595.0f, 225.805f);
        path.lineTo(435.0f, 35.595f);
        path.lineTo(415.0f, 915.555f);
        path.lineTo(395.0f, 625.995f);
        path.lineTo(775.0f, 15.885f);
        path.lineTo(555.0f, 675.705f);
        path.lineTo(855.0f, 955.965f);
        path.lineTo(715.0f, 165.895f);
        path.lineTo(665.0f, 475.95f);
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
