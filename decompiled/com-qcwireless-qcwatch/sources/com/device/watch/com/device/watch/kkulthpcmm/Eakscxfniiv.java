package com.device.watch.com.device.watch.kkulthpcmm;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Eakscxfniiv extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1003;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1003;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Eakscxfniiv() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(535.0f, 665.675f);
        path.lineTo(975.0f, 515.55f);
        path.lineTo(835.0f, 535.965f);
        path.lineTo(585.0f, 995.635f);
        path.lineTo(25.0f, 205.795f);
        path.lineTo(705.0f, 305.755f);
        path.lineTo(865.0f, 745.35f);
        path.lineTo(735.0f, 995.135f);
        path.lineTo(455.0f, 185.755f);
        path.lineTo(385.0f, 485.575f);
        path.lineTo(415.0f, 555.435f);
        path.lineTo(585.0f, 115.875f);
        path.lineTo(415.0f, 885.925f);
        path.lineTo(225.0f, 685.125f);
        path.lineTo(395.0f, 105.445f);
        path.lineTo(685.0f, 315.325f);
        path.lineTo(395.0f, 355.525f);
        path.lineTo(125.0f, 755.345f);
        path.lineTo(35.0f, 705.985f);
        path.lineTo(965.0f, 905.775f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1003.0f, this.bounds.height() / 1003.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
