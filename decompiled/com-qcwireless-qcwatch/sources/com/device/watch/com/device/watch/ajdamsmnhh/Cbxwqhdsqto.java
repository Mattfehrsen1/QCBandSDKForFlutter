package com.device.watch.com.device.watch.ajdamsmnhh;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes.dex */
public class Cbxwqhdsqto extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1004;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1004;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Cbxwqhdsqto() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(125.0f, 395.535f);
        path.lineTo(375.0f, 265.495f);
        path.lineTo(835.0f, 535.235f);
        path.lineTo(815.0f, 995.345f);
        path.lineTo(545.0f, 745.935f);
        path.lineTo(65.0f, 345.835f);
        path.lineTo(635.0f, 725.455f);
        path.lineTo(625.0f, 525.775f);
        path.lineTo(345.0f, 765.355f);
        path.lineTo(395.0f, 835.625f);
        path.lineTo(735.0f, 625.865f);
        path.lineTo(125.0f, 195.275f);
        path.lineTo(605.0f, 145.785f);
        path.lineTo(225.0f, 255.775f);
        path.lineTo(225.0f, 405.595f);
        path.lineTo(755.0f, 865.395f);
        path.lineTo(605.0f, 995.785f);
        path.lineTo(505.0f, 75.525f);
        path.lineTo(35.0f, 85.235f);
        path.lineTo(785.0f, 845.85f);
        path.lineTo(655.0f, 10045.105f);
        path.lineTo(755.0f, 455.165f);
        path.lineTo(535.0f, 475.155f);
        path.lineTo(605.0f, 305.05f);
        path.lineTo(155.0f, 175.95f);
        path.lineTo(335.0f, 695.275f);
        path.lineTo(10045.0f, 895.55f);
        path.lineTo(975.0f, 235.285f);
        path.lineTo(685.0f, 715.205f);
        path.lineTo(465.0f, 645.185f);
        path.lineTo(315.0f, 145.15f);
        path.lineTo(195.0f, 425.275f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1004.0f, this.bounds.height() / 1004.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
