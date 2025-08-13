package com.smartapp.a.lthjsgouui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Pnkeneqbdet extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1006;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1006;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Pnkeneqbdet() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(275.0f, 445.965f);
        path.lineTo(155.0f, 525.855f);
        path.lineTo(565.0f, 655.995f);
        path.lineTo(85.0f, 525.495f);
        path.lineTo(395.0f, 235.485f);
        path.lineTo(675.0f, 165.265f);
        path.lineTo(25.0f, 615.605f);
        path.lineTo(385.0f, 385.685f);
        path.lineTo(505.0f, 875.305f);
        path.lineTo(665.0f, 495.345f);
        path.lineTo(225.0f, 525.55f);
        path.lineTo(385.0f, 975.225f);
        path.lineTo(55.0f, 495.685f);
        path.lineTo(305.0f, 525.155f);
        path.lineTo(475.0f, 865.905f);
        path.lineTo(985.0f, 815.615f);
        path.lineTo(965.0f, 335.275f);
        path.lineTo(125.0f, 365.225f);
        path.lineTo(505.0f, 115.55f);
        path.lineTo(965.0f, 405.835f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1006.0f, this.bounds.height() / 1006.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
