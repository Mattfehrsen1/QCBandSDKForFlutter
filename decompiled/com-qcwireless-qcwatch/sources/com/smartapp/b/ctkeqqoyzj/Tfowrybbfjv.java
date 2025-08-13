package com.smartapp.b.ctkeqqoyzj;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Tfowrybbfjv extends ShapeDrawable {
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

    public Tfowrybbfjv() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(255.0f, 155.605f);
        path.lineTo(385.0f, 775.655f);
        path.lineTo(135.0f, 975.605f);
        path.lineTo(225.0f, 955.85f);
        path.lineTo(325.0f, 165.985f);
        path.lineTo(525.0f, 565.785f);
        path.lineTo(415.0f, 285.385f);
        path.lineTo(175.0f, 615.635f);
        path.lineTo(445.0f, 155.875f);
        path.lineTo(465.0f, 805.255f);
        path.lineTo(685.0f, 205.115f);
        path.lineTo(495.0f, 915.805f);
        path.lineTo(895.0f, 225.975f);
        path.lineTo(655.0f, 125.365f);
        path.lineTo(255.0f, 545.795f);
        path.lineTo(775.0f, 795.415f);
        path.lineTo(425.0f, 945.315f);
        path.lineTo(185.0f, 275.565f);
        path.lineTo(95.0f, 735.635f);
        path.lineTo(675.0f, 835.805f);
        path.lineTo(675.0f, 655.595f);
        path.lineTo(485.0f, 305.565f);
        path.lineTo(815.0f, 195.535f);
        path.lineTo(175.0f, 415.505f);
        path.lineTo(785.0f, 805.365f);
        path.lineTo(215.0f, 65.575f);
        path.lineTo(855.0f, 10145.865f);
        path.lineTo(345.0f, 805.715f);
        path.lineTo(655.0f, 435.215f);
        path.lineTo(505.0f, 205.445f);
        path.lineTo(225.0f, 225.235f);
        path.lineTo(415.0f, 85.795f);
        path.lineTo(725.0f, 485.895f);
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
