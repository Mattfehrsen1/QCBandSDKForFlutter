package com.device.watch.com.device.watch.yntrbskhiq;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Vijpumknrvj extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1001;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1001;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Vijpumknrvj() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(905.0f, 465.975f);
        path.lineTo(625.0f, 165.785f);
        path.lineTo(15.0f, 45.515f);
        path.lineTo(25.0f, 295.985f);
        path.lineTo(665.0f, 595.35f);
        path.lineTo(755.0f, 335.145f);
        path.lineTo(765.0f, 405.575f);
        path.lineTo(395.0f, 635.675f);
        path.lineTo(985.0f, 745.755f);
        path.lineTo(755.0f, 445.755f);
        path.lineTo(85.0f, 155.615f);
        path.lineTo(275.0f, 625.605f);
        path.lineTo(225.0f, 95.555f);
        path.lineTo(235.0f, 215.835f);
        path.lineTo(165.0f, 285.645f);
        path.lineTo(545.0f, 365.285f);
        path.lineTo(615.0f, 205.425f);
        path.lineTo(515.0f, 215.195f);
        path.lineTo(5.0f, 665.185f);
        path.lineTo(445.0f, 735.375f);
        path.lineTo(335.0f, 275.835f);
        path.lineTo(655.0f, 585.515f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1001.0f, this.bounds.height() / 1001.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
